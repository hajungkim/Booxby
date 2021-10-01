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

export function setwords (state, words) {
  state.words = words
}

export function setHashtags (state,hashString) {
  state.loginUser.hashtag = hashString
  state.hashtag = hashString
}

export function setHashtag_list (state,hashlist) {
  state.hash_tags = hashlist
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

export function setReviewList (state, reviewList) {
  state.reviewList = reviewList
}

export function setOxbooks (state, oxbooks) {
  state.oxbooks = oxbooks
}

export function setselectOxbooks (state, oxbooks) {
  state.selectoxbooks = oxbooks
}

export function setMyReview (state, myReview) {
  state.myReview = myReview
}

export function setWriterList (state, writerList) {
  state.writerList = writerList
}

export function setTrueCate1 (state) {
  state.cate1 = true
}

export function setfalseCate1 (state) {
  state.cate1 = false
}

export function setTrueCate2 (state) {
  state.cate2 = true
}

export function setfalseCate2 (state) {
  state.cate2 = false
}
export function setTrueCate3 (state) {
  state.cate3 = true
}

export function setfalseCate3 (state) {
  state.cate3 = false
}
export function setTrueCate4 (state) {
  state.cate4 = true
}

export function setfalseCate4 (state) {
  state.cate4 = false
}
export function setTrueCate5 (state) {
  state.cate5 = true
}

export function setfalseCate5 (state) {
  state.cate5 = false
}