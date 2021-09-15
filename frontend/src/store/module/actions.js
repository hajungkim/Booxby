import $axios from 'axios'

// 기본 url
const baseUrl = 'http://localhost:8080'

// 로그인 Axios
export function login ({}, user) {
  const url = baseUrl + '/user/login'
  return $axios.post(url, user)
}

export function saveInfo ({state}, info) {
  console.log(info,'@@')
  console.log(state)
}

// 회원정보 가져오기
export function requestInfo ({}, userId) {
  const url = baseUrl + '/user/' + userId
  return $axios.get(url)
}