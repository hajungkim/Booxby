// import $axios from 'axios'

// 기본 url
const baseUrl = 'http://localhost:8080'

// 로그인 Axios
export function login ({ state }, user) {
    console.log('login', state, user)
    const url = baseUrl + '/user/login'
    return $axios.post(url, user)
  }

export function saveInfo ({state}, info) {
  console.log(info,'@@')
  console.log(state)
}
