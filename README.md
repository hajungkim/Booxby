# 북스비 | Booxby 📖

- 서비스명 : 북스비 Booxby
- 개발기간 : 2021.08.30 ~ 2021.10.08 (6주)
- 팀명 : 쓰리빅

<br>

# 💁‍♀️ 서비스 소개

   ### 사용자 감성 기반 책 추천 서비스, 북스비
   - 사용자의 성향과 책 선호도에 따라 감성 점수를 계산하여, 감성 점수와 일치하는 책 추천 서비스
   - 감정점수란?
        - 사용자가 선호한 책  + 해시태그 + 찜한 책
        - 기준의 description 단어들의 점수를  기반으로 7가지 색 중 유저의 색을 정해줌


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
