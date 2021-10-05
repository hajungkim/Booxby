import $axios from 'axios'

// [서버] 배포 url
// const baseUrl = 'https://j5b203.p.ssafy.io/api'
// const basePythonUrl = 'https://j5b203.p.ssafy.io/data'

// [로컬] 기본 url
const baseUrl = 'http://localhost:7777/api'
const basePythonUrl = 'http://localhost:5000/data'

// 회원가입 axios
export function signup ({}, info) {
  const url = baseUrl + '/user/signup'
  return $axios.post(url, info)
}

export function checkEmail ({}, email) {
  const url = baseUrl + '/user/check'
  return $axios.post(url, email)
}

// 로그인 Axios
export function login ({}, user) {
  const url = baseUrl + '/user/login'
  return $axios.post(url, user)
}

// 회원정보 가져오기
export function requestInfo ({}, userId) {
  const url = baseUrl + '/user/' + userId
  return $axios.get(url)
}

// 회원정보 수정
export function modifyInfo ({state}, user) {
  const url = baseUrl + '/user/' + state.loginUser.userId
  return $axios.put(url, user)
}

// 찜 정보 가져오기
export function requestzzim ({}, userId) {
  const url = baseUrl + '/scrap/' + userId
  return $axios.get(url)
}

// 7권 책 반환
export function emojiRecommend ({}) {
  const url = basePythonUrl + '/emojirecommend'
  return $axios.get(url)
}
// my 추천
export function myRecommend ({},score) {
  console.log(score, typeof(score))
  const url = basePythonUrl + '/myrecommend/' + score
  return $axios.get(url)
}
// zzim 추천
export function zzimRecommend ({},isbn_list) {
  console.log(isbn_list, typeof(isbn_list))
  const url = basePythonUrl + '/scrap-recommend'
  return $axios.post(url,isbn_list)
}
// age/gender 추천
export function ageRecommend ({},ageGender) {
  const url = basePythonUrl + '/agegender/' + ageGender.age + '/' + ageGender.gender
  return $axios.get(url)
}
// category 추천
export function getCategory ({},category) {
  const url = basePythonUrl + '/category/' + category
  return $axios.get(url)
}
// 책 검색
export function getSearchbook ({},keyword) {
  const url = basePythonUrl + '/title/' + keyword
  return $axios.get(url)
}
// 작가 검색
export function getWriterList ({},keyword) {
  const url = basePythonUrl + '/author/' + keyword
  return $axios.get(url)
}
// isbn 정보반환
export function getisbnInfo ({},isbn) {
  const url = basePythonUrl + '/isbn/' + isbn
  return $axios.get(url)
}
// oxbooks 랜덤
export function oxbooks () {
  const url = basePythonUrl + '/oxbooks'
  return $axios.get(url)
}
// 찜 하기
export function zzimOn ({}, zzim) {
  const url = baseUrl + '/scrap'
  return $axios.post(url, zzim)
}
// 찜 취소
export function zzimOff ({}, zzim) {
  const url = baseUrl + '/scrap/' + zzim.userId + '/' + zzim.isbn
  return $axios.delete(url, zzim)
}
// 리뷰 쓰기
export function writeReview ({}, review) {
  const url = baseUrl + '/book/review'
  return $axios.post(url, review)
}
// 리뷰 가져오기
export function requestReview ({}, isbn) {
  const url = baseUrl + '/book/review/' + isbn + '/newest'
  return $axios.get(url)
}
// 나의 리뷰 가져오기
export function requestMyReview ({}) {
  const userId = localStorage.getItem('userId')
  const url = baseUrl + '/book/review/my/' + userId
  return $axios.get(url)
}

// 워드클라우드
export function getwords ({},isbn) {
  const url = basePythonUrl + '/nouns-count/' + isbn
  return $axios.get(url)
}

//비밀번호 찾기
export function findPwd ({}, email) {
  const url = baseUrl + '/changepw'
  return $axios.put(url, email)
}