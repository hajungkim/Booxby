from flask import Flask, request
from flask_restx import Api, Resource
from flask import make_response
from flask_cors import CORS
import json
import csv

import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
from konlpy.tag import Okt
from typing import Counter

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

@api.route('/data/myrecommend/<score>')
class userEmotionRecommend(Resource):
    def get(self,score):
        """감정점수에 따라 책 반환하기"""
        user_number = int(score)
        colornum=0
        if -2972.6<=user_number <=-126.1:
            colornum=7
        elif -126.1<user_number <= -31.8:
            colornum=6
        elif -31.8<user_number <= 0:
            colornum=5
        elif 0<user_number <= 36.1:
            colornum=4
        elif 36.1<user_number <= 84.5:
            colornum=3
        elif 84.5<user_number <= 157.6:
            colornum=2
        elif 157.6<user_number <= 2438.6:
            colornum=1
        df1 = pd.read_csv('booxby_color_data.csv', encoding='cp949')
        recommend = df1['color'] == colornum
        df1 = df1[recommend]
        df1 = df1.sample(n=7)

        return toJson(df1)

@api.route('/data/emojirecommend')
class randomEmotion(Resource):
    def get(self):
        """메인 책 7권 반환하기"""
        df = pd.read_csv('booxby_color_data.csv', encoding='cp949')
        df1 = df[df['color'] == 1].sample(n=1)

        for i in range(2,8):
            temp_df = df[df['color'] == i].sample(n=1)    
            df1 = pd.concat([df1,temp_df])

        return toJson(df1)

@api.route('/data/agegender/<age>/<gender>')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class ageGenderRecommend(Resource):
    pd.set_option('display.max_colwidth',-1)
    def get(self,age,gender):
        """나이, 성별에 따라 책 반환하기"""
        # age = '20대'  # 유아 초등학생 청소년 20대 30대 40대 50대 60대 이상
        # gender = '남성' # 남성 여성
        df = pd.read_csv('booxby_gender_age_emotion_data.csv', encoding='cp949')
        df2 = df[(df['isbn13'] == 9788950976903)]['emotion_score']
        df1 = df[(df['age'] == age) & (df['sex'] == gender)].sample(n=7)
        return toJson(df1)

@api.route('/data/category/<category>')
class categoryRecommend(Resource):
    def get(self,category):
        """카테고리에 따라 책 반환하기"""
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        category = category.split(',')
        df1 = pd.DataFrame(columns=['no','rank','isbn13','title','author','description','publisher','pub_date','img_url','emotion_score','color','category'])
        if len(category)==1:
            temp_df = df[(df['category'] == category[0])]
            df1 = pd.concat([df1,temp_df]).sample(n=7)
            return toJson(df1)
        else:
            for c in category:
                # category = c # 아동 문학 취미 청소년 학문 오락 가정 교육 기타
                temp_df = df[(df['category'] == c)]
                df1 = pd.concat([df1,temp_df])
        df1 = df1.sample(n=7)
        return toJson(df1)

@api.route('/data/isbn/<isbn>')
class getIsbn(Resource):
    def get(self,isbn):
        """isbn으로 책 상세정보 반환하기"""
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        df1 = df[(df['isbn13'] == int(isbn))]
        return toJson(df1)

@api.route('/data/oxbooks')
class OXbooks(Resource):
    def get(self):
        """"""
        df = pd.read_csv('booxby_color_data.csv', encoding='cp949')
        # df1 = df[df['isbn13']==9791190382267]
        # df2 = df[df['isbn13']==9788934972204]
        # df3 = df[df['isbn13']==9788960981768]
        # df4 = df[df['isbn13']==9788967355265]
        # df5 = df[df['isbn13']==9788950982249]
        # df6 = df[df['isbn13']==9788935212187]
        # df7 = df[df['isbn13']==9791187252016]
        # df1 = pd.concat([df1,df2])
        # df1 = pd.concat([df1,df3])
        # df1 = pd.concat([df1,df4])
        # df1 = pd.concat([df1,df5])
        # df1 = pd.concat([df1,df6])
        # df1 = pd.concat([df1,df7])
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
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949').reset_index(drop=True)
        df['description'] = df['description'].fillna('')
        
        tfidf = TfidfVectorizer(stop_words=None)
        tfidf_matrix = tfidf.fit_transform(df['description'])

        cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)

        indices = pd.Series(df.index, index=df['isbn13']).drop_duplicates()
        booxby_indices = []

        for isbn in isbn_list:
            # 선택한 책의 인덱스 받아오기
            idx = indices[int(isbn)]
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
        if len(df.iloc[booxby_indices])<=7:
            return toJson(df.iloc[booxby_indices])
        df = df.iloc[booxby_indices].sample(n=7)
        # 가장 유사한 책 리턴
        return toJson(df)
        
@api.route('/data/author/<search>')
class searchAuthor(Resource):
    def get(self, search):
        """작가 이름으로 책 찾기"""
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        author = df[ df['author'].str.contains(search, na=False) ]
        if len(author) == 0:
            return None
        elif len(author) > 7:
            author = author.sample(n=7)
        return toJson(author)  

@api.route('/data/title/<search>')
class searchTitle(Resource):
    def get(self, search):
        """제목으로 책 찾기"""
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        title = df[ df['title'].str.contains(search, na=False) ]
        if len(title) == 0:
            return None
        elif len(title) > 7:
            title = title.sample(n=7)
        return toJson(title)

@api.route('/data/nouns-count/<isbn>')
class nounsCount(Resource):
    pd.set_option('display.max_colwidth',-1)
    def get(self, isbn):
        """워드클라우드 배열 보내기"""
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        description = df[(df['isbn13'] == int(isbn))]['description'].to_string()
        okt = Okt()
        noun = okt.nouns(description)
        temp = []
        for i,v in enumerate(noun):
            if len(v) > 1:
                temp.append(noun[i])
        
        count = Counter(temp)

        noun_list = count.most_common(100)

        print(isbn)

        return noun_list


if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=5000)