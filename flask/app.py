from flask import Flask, request  # 서버 구현을 위한 Flask 객체 import
from flask_restx import Api, Resource  # Api 구현을 위한 Api 객체 import
from flask import make_response
from flask_cors import CORS
import json
import csv

import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel

app = Flask(__name__)  # Flask 객체 선언, 파라미터로 어플리케이션 패키지의 이름을 넣어줌.
CORS(app,resources={r"/*": {"origins": "*"}})
api = Api(app)  # Flask 객체에 Api 객체 등록
app.config['JSON_AS_ASCII'] = False

def toJson(df):
    df = df.to_json(orient="records")
    parsed = json.loads(df)
    result = json.dumps(parsed,ensure_ascii=False)
    res = make_response(result)   
    return res

@api.route('/hello')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class HelloWorld(Resource):
    def get(self):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        data = {}
        with open('./sample_five.csv',encoding='CP949') as csvf:
            csvReader = csv.DictReader(csvf)
            for rows in csvReader:
                key = rows['no']
                data[key] = rows
            result = json.dumps(data,ensure_ascii=False)
            print(result,'result')
            res = make_response(result)
        return res


@api.route('/data/MyRecommend')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class userEmotionRecommend(Resource):
    def get(self):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        user_number = 5
        colornum=0
        if -29726<=user_number <=-1170:
            colornum=7
        elif -1169<=user_number <= -241:
            colornum=6
        elif -240<=user_number <= 0:
            colornum=5
        elif 1<=user_number <= 295:
            colornum=4
        elif 296<=user_number <= 777:
            colornum=3
        elif 778<=user_number <= 1539:
            colornum=2
        elif 1540<=user_number <= 24386:
            colornum=1
        df1 = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        recommend = df1['color'] == colornum
        df1 = df1[recommend]
        df1 = df1.sample(n=7)

        return toJson(df1)

@api.route('/data/EmojiRecommend')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class randomEmotion(Resource):
    def get(self):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        df1 = df[df['color'] == 1].sample(n=1)

        for i in range(2,8):
            temp_df = df[df['color'] == i].sample(n=1)    
            df1 = pd.concat([df1,temp_df])

        return toJson(df1)

@api.route('/data/agegender')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class ageGenderRecommend(Resource):
    def get(self):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        age = '20대'  # 유아 초등학생 청소년 20대 30대 40대 50대 60대 이상
        gender = '남성' # 남성 여성
        df = pd.read_csv('booxby_gender_age_data2.csv', encoding='cp949')
        df1 = df[(df['age'] == age) & (df['sex'] == gender)].sample(n=7)

        return toJson(df1)

@api.route('/data/category')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class categoryRecommend(Resource):
    def get(self):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        category = '아동' # 아동 문학 취미 청소년 학문 오락 가정 교육 기타
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        df1 = df[(df['category'] == category)].sample(n=7)

        return toJson(df1)

@api.route('/data/isbn/<isbn>')
class getIsbn(Resource):
    def get(self,isbn):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        df1 = df[(df['isbn13'] == int(isbn))]
        return toJson(df1)

@api.route('/data/oxbooks')
class OXbooks(Resource):
    def get(self):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        df1 = df[df['color'] == 1].sample(n=1)
        for i in range(2,8):
            temp_df = df[df['color'] == i].sample(n=1)    
            df1 = pd.concat([df1,temp_df])

        return toJson(df1)

@api.route('/data/scrap-recommend')
class scrapRecommend(Resource):
    def post(self):
        """찜하기 목록에 따른 추천 리스트"""
        isbn_list = request.json.get('isbn_list')
        print(isbn_list)
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949').reset_index(drop=True)
        df['description'] = df['description'].fillna('')

        tfidf = TfidfVectorizer(stop_words=None)
        tfidf_matrix = tfidf.fit_transform(df['description'])
        print(tfidf_matrix.shape)

        cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)

        indices = pd.Series(df.index, index=df['isbn13']).drop_duplicates()
        booxby_indices = []

        for isbn in isbn_list:
            # 선택한 책의 인덱스 받아오기
            idx = indices[isbn]
            # 모든 책에 대해 해당 책과의 유사도 구하기
            sim_scores = list(enumerate(cosine_sim[idx]))
            # 유사도에 따라 책들을 정렬
            sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
            # 가장 유사한 책 받아오기
            sim_scores = sim_scores[1:5]
            # 가장 유사한 책의 인덱스 받아오기
            for sim in sim_scores:
                if sim[1] > 0.8:    # 같은 책 패스
                    continue
                booxby_indices.append(sim[0])

        booxby_indices = list(set(booxby_indices))
        # 가장 유사한 책 리턴
        return toJson(df.iloc[booxby_indices])

#작가 이름으로 책 찾기        
@api.route('/search/author/<search>')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class searchAuthor(Resource):
    def get(self, search):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        print(search)
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        author = df[ df['author'].str.contains(search, na=False) ]

        return toJson(author) 

#제목으로 책 찾기
@api.route('/search/title/<search>')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class searchTitle(Resource):
    def get(self, search):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        print(search)
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        title = df[ df['title'].str.contains(search, na=False) ]

        return toJson(title)  
if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=5000)