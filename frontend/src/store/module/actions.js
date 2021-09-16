import $axios from 'axios'

// 기본 url
const baseUrl = 'http://localhost:8080'

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
  console.log('modifyInfo', user)
  const url = baseUrl + '/user/' + state.loginUser.userId
  return $axios.put(url, user)
}