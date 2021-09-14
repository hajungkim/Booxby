export default function () {
  // 회원가입 월드컵 hover시 설명 보이기 
  const textMode = false
  
  // mainSide 카테고리 창
  const categoryMode = false

  // 로그인 유저 정보
  const loginUser = {
    email: '',
    password: '',
    nickname: '',
    gender: '',
    age:'',
    score:'',
    profile:'',
    hashtag:''
  }

  return {
    textMode,
    categoryMode,
    loginUser
  }
}