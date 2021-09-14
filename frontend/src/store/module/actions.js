import $axios from 'axios'

const baseUrl = 'https://localhost:8080/'

// 로그인 Axios
export function login ({ state }, user) {
    console.log('login', state, user)
    const url = baseUrl + 'user/login'
    return $axios.post(url, user)
  }