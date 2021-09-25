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

export function setHashtags (state, hashtags,hashString) {
  state.loginUser.hashtag = hashString
  state.hash_tags = hashtags
}
export function setHashscore (state, score) {
  state.user_info.hashScore = score
}

export function setBookList (state, bookList) {
  state.bookList = bookList
}

export function setSelectBook (state, selectBook) {
  state.selectBook = selectBook
}

export function setZzimList (state, zzimList) {
  state.zzimList = zzimList
}

export function setZzim (state, zzim) {
  state.zzim = zzim
}

export function setOxbooks (state, oxbooks) {
  state.oxbooks = oxbooks
}