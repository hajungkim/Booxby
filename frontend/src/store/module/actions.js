import $axios from 'axios'

// 기본 url
const baseUrl = 'http://localhost:7777/api'
const basePythonUrl = 'http://localhost:5000/data'
// 회원가입 axios
export function signup ({}, info) {
  console.log('signup', info)
  const url = baseUrl + '/user/signup'
  return $axios.post(url, info)
}

export function checkEmail ({}, email) {
  const url = baseUrl + '/user/check'
  return $axios.post(url, email)
}

// 로그인 Axios
export function login ({}, user) {
  console.log('login')
  const url = baseUrl + '/user/login'
  return $axios.post(url, user)
}

// 회원정보 가져오기
export function requestInfo ({}, userId) {
  console.log('requestInfo')
  const url = baseUrl + '/user/' + userId
  return $axios.get(url)
}

// 회원정보 수정
export function modifyInfo ({state}, user) {
  console.log('modifyInfo')
  const url = baseUrl + '/user/' + state.loginUser.userId
  return $axios.put(url, user)
}

// 찜 정보 가져오기
export function requestzzim ({}, userId) {
  console.log('requestzzim')
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
// age/gender 추천
export function ageRecommend ({},ageGender) {
  console.log(ageGender.gender)
  const url = basePythonUrl + '/agegender/' + ageGender.age + '/' + ageGender.gender
  console.log(url,'url')
  return $axios.get(url)
}
// category 추천
export function getCategory ({},category) {
  console.log(category,'액션카테고리')
  const url = basePythonUrl + '/category/' + category
  console.log(url,'url')
  return $axios.get(url)
}
// 책 검색
export function getSearchbook ({},keyword) {
  console.log(keyword,'서치북')
  const url = basePythonUrl + '/title/' + keyword
  console.log(url,'url')
  return $axios.get(url)
}
// 작가 검색
export function getWriter ({},keyword) {
  console.log(keyword,'서치작가')
  const url = basePythonUrl + '/author/' + keyword
  console.log(url,'url')
  return $axios.get(url)
}

// isbn 정보반환
export function getisbnInfo ({},isbn) {
  console.log(isbn)
  const url = basePythonUrl + '/isbn'
  return $axios.post(url,isbn)
}

// oxbooks 랜덤
export function oxbooks () {
  const url = basePythonUrl + '/oxbooks'
  return $axios.get(url)
}

export function zzimOn ({}, zzim) {
  console.log('찜',zzim)
  const url = baseUrl + '/scrap'
  return $axios.post(url, zzim)
}

export function zzimOff ({}, zzim) {
  console.log('취소',zzim)
  const url = baseUrl + '/scrap'
  return $axios.delete(url, zzim,{
    headers:{
      'Content-Type': 'application/json'
    }
  })
}