![logo 투명화2](https://user-images.githubusercontent.com/36887393/136737252-2379c203-8082-4b3d-82e9-a301a1c9f502.png)

# 북스비 | Booxby 📖

- 서비스명 : 북스비 Booxby
- 개발기간 : 2021.08.30 ~ 2021.10.08 (6주)
- 팀명 : 쓰리빅

<br>

# 📌서비스 소개

   ### 사용자 감성 기반 책 추천 서비스, 북스비
   - 사용자의 책 선호도와 성향에 따라 감성 점수를 계산하여, 감성 점수와 일치하는 책을 추천하는 서비스

  ### 북스비가 제공하는 추천 기능
  - 사용자가 좋아하는 책과 성향을 바탕으로 감성과 일치하는 책 추천
  - 사용자의 나이와 성별에 따른 추천
  - 사용자가 찜한 책을 바탕으로 컨텐츠 기반 필터링 추천
  - 카테고리별 추천

<br>

# 🎨북스비 감성점수란?
  ![image](https://user-images.githubusercontent.com/36887393/136736218-68060997-f770-4ed1-9abc-8c09447baefe.png)
  > 감성점수는 내가 좋아요한 책의 디스크립션 단어들로 계산됩니다.
  점수가 높으면 밝은 분위기의 책을, 점수가 낮으면 어두운 분위기의 책을 추천해줍니다.

  ![image](https://user-images.githubusercontent.com/36887393/136736241-e2a25086-36d9-485d-89b0-737bc53d3bf3.png)

  ![image](https://user-images.githubusercontent.com/36887393/136736168-ff12de4c-ce10-4c63-9a9d-eeb90351d037.png)
  > 이용된 도서 감성 데이터는 단어의 긍정점수와 부정점수를 가지고 있는데 이걸 바탕으로 밝은 분위기를 가진 책의 설명을 점수로 계산하면 1264점, 어두운 분위기를 가진 책의 설명은 -1313점이 나오게 됩니다.

<br>  

  ![image](https://user-images.githubusercontent.com/36887393/136737396-c165c122-f6e9-41c3-a493-28c1f848a565.png)
  > 회원가입시, 좋아하는 책 선택하기

  ![image](https://user-images.githubusercontent.com/36887393/136737414-fcd3fc51-30a2-469c-8ced-8e43731c94e7.png)
  > 회원가입시, 나의 성향 선택하기

  ![image](https://user-images.githubusercontent.com/36887393/136736644-5dc7a319-6844-4b71-9e03-0fb909cb37a6.png)
  > 좋아요한 책과 성향으로 계산된 나의 감성점수는 각각의 캐릭터를 가집니다.

<br>

# 👩 팀원 역할
| 팀원 | 역할 | 담당 업무
| ------ | ------ | ------ |
| 박선주 | 팀장 | 프론트엔드 개발, Flask 개발 |
| 황지훈 | 팀원 | 프론트엔드 개발 |
| 김하정 | 팀원 | 백엔드 개발, Flask 개발 |
| 이수정 | 팀원 | 백엔드 개발, Flask 개발, CI/CD |

<br>

# 💻 기술 스택
> ### Front : Vue.js , Quasar
> ### Back : Spring, Flask
> ### Devops : docker, jenkins, nginx
> ### Server : AWS
> ### DB : Mysql

<br>

# 📝개발 규칙

   ### ✔Git 규칙
   
   - #### Branch 규칙
      ### ``` develop -> BE,FE/feature/기능명 ``` 
      #### ex) FE/feature/login  
   
   - #### Commit 규칙
      ### ``` [ FE / BE ] Git 컨벤션(대문자) : Commit 메시지 ```
      #### ex) [FE] ADD : 로그인 버튼 추가

<br>

### ✔ Naming Rule

|프론트엔드|백엔드|
|------|---|
|폴더명 : 소문자 <br>파일명 : Pascal case <br>변수/함수명 : Camel case |클래스명 : 첫글자 대문자 + Camel case<br>변수/함수명 : 첫글자 소문자 + Camel case|


<br>

# 💻 구현

### [인트로 화면]

![intro](/uploads/95f08015e373cea9e5cea15b5cf0edf1/intro.gif)

> 주소를 입력하여 Booxby에 접속하면 인트로화면이 나옵니다.

<br><br>

###  [회원가입]

![signup](/uploads/49887bdb90706af10d6506d31bae7f58/signup.gif)

- 회원 정보 입력

> 이메일.(중복확인), 비밀번호, 닉네임, 나이, 성별을 입력합니다.

<br>

![hashtag](/uploads/fb3ce2f57837dba1e4f478863c22d155/hashtag.gif)

- 해시태그 입력

> 나를 나타내는 해시태그를 선택합니다.

<br>

![bookworldcup](/uploads/088fcba316c46a4113c3aee16a2197dc/bookworldcup.gif)

- 책 선호도 조사

> 책 이미지 위에 마우스를 올려서 책의 제목과 설명을 확인한 후,
>
> 선호하는 책인지 아닌지, 아니면 잘 모르겠는지 선택합니다.
>
> 이는 추후 유저의 감성 점수에 반영됩니다.

<br><br>

### [메인화면]

![main](/uploads/82eb5a2c6fe243d204d8ba1510882eea/main.gif)

> 메인에서는 책 추천 기능, 검색기능을 이용할 수 있습니다.
>
> 각 추천 별로 7가지의 책을 보여줍니다.

<br><br>

### [책 검색 기능]

![search-func](/uploads/15dfa64797430bdb44d7586550000bac/search-func.gif)

> 책 제목과 작가 이름으로 책을 검색할 수 있습니다.

<br><br>

### [책 상세 페이지]

- 자세히 보기를 클릭하면 책의 상세 정보 및 리뷰를 확인할 수 있습니다.

![detail-content](/uploads/112c8528d0b85bbfb6de57b79bb63224/detail-content.gif)

> 책의 상세 정보 - 작가, 책의 감성 색, 책 설명글, 워드 클라우드를 확인할 수 있습니다.
<br>

![detail-review](/uploads/24e5e47d1e8d3843d77a55626ea18f26/detail-review.gif)

> 리뷰를 작성하고 등록된 리뷰도 확인할 수 있습니다.

<br>

![detail-author](/uploads/bb2f1d61ec977b16f93c065c8b5012d0/detail-author.gif)

> 작가의 다른 책도 확인할 수 있습니다.

<br>

![scrap-func](/uploads/8de9839f866b28b75edf99eeb7e79100/scrap-func.gif)

> 찜(스크랩) 기능을 통해 관심이 있는 책을 저장할 수 있습니다.
>
> 이렇게 찜한 책들은 찜 기반 추천 서비스를 사용할 때, 활용됩니다.

<br>

<br>

### [추천서비스]

![recommendation](/uploads/866132d13fe98a14739ffd19ce53e25d/recommendation.gif)

> 총 5가지의 추천 서비스를 제공합니다.
>
> 1.  색깔별 감성책
> 2. 나의 감성 책 추천
> 3. 찜기반 책 추천
> 4.  나이와 성별 추천
> 5.  카테고리 추천



#### ❤찜 기반 책 추천 서비스

![scrap](/uploads/4abf3ab5422ad8bbd027fb3e97b97f7c/scrap.gif)

![scrap-recommendation](/uploads/e603d6821e7a99be06ef511456351805/scrap-recommendation.gif)

>  프로그래밍과 주식 관련된 책을 찜하고 
>
> 찜 추천 서비스를 클릭하면 비슷한 내용의 책을 추천 받을 수 있습니다.
>
> - 컨텐츠 기반 필터링 활용

<br>

<br>

### [마이페이지]

![mypage](/uploads/4ad59ef710de4096f1667301b1080b54/mypage.gif)

> 마이페이지에서는 찜(스크랩)한 책과 작성한 리뷰를 확인할 수 있고, 클릭시 해당 책 페이지로 이동합니다.
>
> 개인정보 수정 및 회원가입시에 진행한 해시태그 선택/책 선호도 조사를 다시 진행할 수 있습니다.

<br>

<br>
