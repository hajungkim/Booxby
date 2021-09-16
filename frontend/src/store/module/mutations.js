export function setTextMode (state, textMode) {
  state.textMode = textMode
}

export function setCategoryMode (state, categoryMode) {
    state.categoryMode = categoryMode
}

export function setLoginUser (state, loginUser) {
  state.loginUser = loginUser
}

export function setInfos (state, info) {
  console.log('뮤테이션', info)
  state.user_info = info
}

export function setHashtags (state, hashtags) {
  state.hash_tags = hashtags
}
export function setHashscore (state, score) {
  state.user_info.hashScore = score
}
