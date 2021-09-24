from flask import Flask  # 서버 구현을 위한 Flask 객체 import
from flask_restx import Api, Resource  # Api 구현을 위한 Api 객체 import
from flask import make_response
# from flask_cors import CORS
import json
import csv

import pandas as pd

app = Flask(__name__)  # Flask 객체 선언, 파라미터로 어플리케이션 패키지의 이름을 넣어줌.
# CORS(app)
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


@api.route('/MyRecommend')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
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

@api.route('/EmojiRecommend')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class randomEmotion(Resource):
    def get(self):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        df1 = df[df['color'] == 1].sample(n=1)

        for i in range(2,8):
            temp_df = df[df['color'] == i].sample(n=1)    
            df1 = pd.concat([df1,temp_df])

        return toJson(df1)

@api.route('/agegender')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class ageGenderRecommend(Resource):
    def get(self):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        age = '20대'  # 유아 초등학생 청소년 20대 30대 40대 50대 60대 이상
        gender = '남성' # 남성 여성
        df = pd.read_csv('booxby_gender_age_data2.csv', encoding='cp949')
        df1 = df[(df['age'] == age) & (df['sex'] == gender)].sample(n=7)

        return toJson(df1)

@api.route('/category')  # 데코레이터 이용, '/hello' 경로에 클래스 등록
class categoryRecommend(Resource):
    def get(self):  # GET 요청시 리턴 값에 해당 하는 dict를 JSON 형태로 반환
        category = '아동' # 아동 문학 취미 청소년 학문 오락 가정 교육 기타
        df = pd.read_csv('booxby_emotion_data.csv', encoding='cp949')
        df1 = df[(df['category'] == category)].sample(n=7)

        return toJson(df1)

if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=80)