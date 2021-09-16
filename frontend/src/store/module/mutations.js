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
  state.user_info = info
}
export function setHashtags (state, hashtags) {
  state.hash_tags = hashtags
}
export function setEmojiscore (state, score) {
  state.user_info.emotionScore = score
}
