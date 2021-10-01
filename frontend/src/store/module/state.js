export default function () {
  // 회원가입 월드컵 hover시 설명 보이기 
  const textMode = false
  
  // mainSide 카테고리 창
  const categoryMode = false

  // SignUp
  const user_info = {}
  const hash_tags = []

  //해시태그
  const hashtag = ''

  // 로그인 유저 정보
  const loginUser = {}
  
  // 책 리스트
  const bookList = []

  // 선택된 책
  const selectBook = {}

  const zzimList = []
  const zzim = false

  // 워드클라우드
  const words = []

  // 세부창 리뷰리스트
  const reviewList = []

  // 세부창 작가의 다른책
  const writerList = []

  // 마이페이지 리뷰리스트
  const myReview = [] 
  const oxbooks = []
  const selectoxbooks = {}

  const cate1 = true
  const cate2 = false
  const cate3 = false
  const cate4 = false
  const cate5 = false

  return {
    cate1,
    cate2,
    cate3,
    cate4,
    cate5,
    textMode,
    categoryMode,
    loginUser,
    user_info,
    hash_tags,
    bookList,
    selectBook,
    zzimList,
    zzim,
    reviewList,
    myReview,
    oxbooks,
    selectoxbooks,
    writerList,
    words,
    hashtag
  }
}