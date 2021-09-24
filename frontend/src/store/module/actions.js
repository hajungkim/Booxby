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

// 테스트용
export function test ({}) {
  const url = basePythonUrl + '/MyRecommend'
  return $axios.get(url)
}

// 테스트용
export function test2 ({},isbn) {
  const url = basePythonUrl + '/isbn/' + isbn
  console.log(url,'url')
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
  return $axios.delete(url, zzim)
}