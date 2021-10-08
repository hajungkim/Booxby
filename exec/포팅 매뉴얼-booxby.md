# 📃 포팅 매뉴얼

## ✔ 개발 환경

```
1. 서버 및 DevOps
AWS EC2, Nginx, Docker, Jenkins

2. 프론트엔드
vue, Quasar

3. 백엔드
기본 API
- springboot : 2.5.4
- Gradle : 7.1.1

추천 API
- flask

4. DB
MySQL
```

- IDE

```
IntelliJ : 2021.1.3

Visual Studio Code : 1.59.0
```

<br>

## ✔ 빌드 및 배포 작업 문서

- 포트 설정

```
프론트 : 3000
백엔드(스프링) : 7777
백엔드(플라스크) : 5000
DB : 3306
Jenkins : 9999
```

- 데이터베이스 접속 정보

```
MySQL
계정 : root
Database : booxby
```

- 빌드 및 실행 방법

```
1. 서버 접속 : ssh -i cert.pem booxby@j5b203.p.ssafy.io
2. 폴더 이동 :  cd Booxby
3. 도커 빌드 및 실행 : docker-compose up --build
4. 페이지 접속 : https://i5b207.p.ssafy.io/
```

<br>

## ✔ 외부 서비스 정보 문서

- 데이터셋

```
1. 감성 점수 데이터
출처 : 국립중앙도서관

2. 책 데이터
출처 : 국립중앙도서관

3. 알라딘 API
http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx

4. KoNLPY 파이썬 패키지
- 텍스트에서 명사를 추출하기 위해 사용
[공식문서] https://konlpy.org/ko/latest/
```
