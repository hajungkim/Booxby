import $axios from 'axios'

const baseUrl = 'http://localhost:8080'

export function signup ({}, info) {
  const url = baseUrl + '/user/signup'
  return $axios.post(url,info)
}