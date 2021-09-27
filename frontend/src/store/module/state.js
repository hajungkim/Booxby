export default function () {
  // 회원가입 월드컵 hover시 설명 보이기 
  const textMode = false
  
  // mainSide 카테고리 창
  const categoryMode = false

  // SignUp
  const user_info = {}
  const hash_tags = []

  // 로그인 유저 정보
  const loginUser = {
    userId:'',
    email: '',
    password: '',
    nickname: '',
    gender: '',
    age:'',
    score:'',
    profile:'',
    hashtag:'',
    token:''
  }
  
  // 책 리스트
  const bookList = []

  // 선택된 책
  const selectBook = {}

  const zzimList = []
  const zzim = false

  // 세부창 리뷰리스트
  const reviewList = []

  // 마이페이지 리뷰리스트
  const myReview = [] 
  const oxbooks = []
  const selectoxbooks = {}


  return {
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
    selectoxbooks
  }
}