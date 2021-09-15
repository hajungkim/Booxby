export default function () {
  // 회원가입 월드컵 hover시 설명 보이기 
  const textMode = false
  
  // mainSide 카테고리 창
  const categoryMode = false

  // 로그인 유저 정보
  const loginUser = {
    userId:'',
    email: '',
    nickname: '',
    gender: '',
    age:'',
    score:'',
    profile:'',
    hashtag:'',
    token:''
  }

  const user_info = {}
  
  return {
    textMode,
    categoryMode,
    loginUser,
    user_info
  }
}