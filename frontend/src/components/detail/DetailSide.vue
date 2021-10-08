<template>
  <div class="detail_side">
      <div class="logo">
        <q-img class="side_logo" src="~assets/images/logo.png"/>
      </div>
      <div class="detail_side_content">
            <div>
                <!-- <q-icon v-if="zzim" @click="zzimOff" class="bookmark" style="font-size: 3.7em; color: red;" name="bookmark"/>
                <q-icon v-if="!zzim" @click="zzimOn" class="bookmark" style="font-size: 3.7em; color: black;" name="bookmark_border"/> -->
                <div class="placement" style="display:flex;">
                  <div @click="bookmark" id="heart"></div>
                </div>
            </div>
            <div style="position:relative; top:-40px;">
              <div class="detail_name">
                  {{selectBook.title}}
              </div>
              <div class="detail_author">
                  {{selectBook.author}}
              </div>
              <div class="detail_book">
                  <q-img v-if="-29726<=score && score <=-1261" src='~assets/character/purple.png' class="book_character"/>
                  <q-img v-if="-1261<score && score <=-318" src='~assets/character/navy.png' class="book_character"/>
                  <q-img v-if="-318<=score && score <=0" src='~assets/character/blue.png' class="book_character"/>
                  <q-img v-if="0<score && score <=361" src='~assets/character/green.png' class="book_character"/>
                  <q-img v-if="361<score && score <=845" src='~assets/character/yellow.png' class="book_character"/>
                  <q-img v-if="845<score && score <=1576" src='~assets/character/orange.png' class="book_character"/>
                  <q-img v-if="1576<score && score <=24386" src='~assets/character/red.png' class="book_character"/>
                  <q-img :src="selectBook.img_url" class="detail_img" 
                  v-bind:class="{'shadow_red':selectBook.emotion_score<24386 && selectBook.emotion_score>=1576,
                        'shadow_orange':selectBook.emotion_score<1576 && selectBook.emotion_score>=845,
                        'shadow_yellow':selectBook.emotion_score<845 && selectBook.emotion_score>361,
                        'shadow_green':selectBook.emotion_score<=361 && selectBook.emotion_score>0,
                        'shadow_blue':selectBook.emotion_score<=0 && selectBook.emotion_score>-318,
                        'shadow_navy':selectBook.emotion_score<=-318 && selectBook.emotion_score>-1261,
                        'shadow_purple':selectBook.emotion_score<=-1261 && selectBook.emotion_score>=-29726
                        }"      />
              </div>
            </div>
      </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()

    const selectBook = computed(() => store.getters['module/getSelectBook'])
    const zzim = computed(() => store.getters['module/getZzim'])

    const userId = localStorage.getItem('userId')
    const score = selectBook.value.emotion_score

    store.dispatch('module/requestzzim', userId)
      .then(function (result) {
          for(let i = 0; i < result.data.data.length; i++) {
              if(selectBook.value.isbn13 == result.data.data[i].isbn) {
                store.commit('module/setZzim', true)
                break
              }
              if(i==result.data.data.length-1) {
                store.commit('module/setZzim', false)
              }
          }
          const heart = document.getElementById('heart')
          const zzim = store.getters['module/getZzim']
          if(zzim){
            heart.classList.add('is-active')
          }
          else if(!zzim){
            heart.classList.remove('is-active')
          }
      })

    const bookmark = function() {
      const heart = document.getElementById('heart')
      const zzim = store.getters['module/getZzim']
      const userId = localStorage.getItem('userId')
      if(zzim) {
        store.commit('module/setZzim', false)
        store.dispatch('module/zzimOff', {isbn: selectBook.value.isbn13, userId: userId})
          .then(function() {
            store.dispatch('module/requestzzim', userId)
                  .then(function (result) {
                      store.commit('module/setZzimList', result.data.data)
                      heart.classList.remove('is-active')
                  })
          })
      }
      else if(!zzim) {
        store.commit('module/setZzim', true)
        store.dispatch('module/zzimOn', {isbn: selectBook.value.isbn13, userId: userId, title: selectBook.value.title, imgUrl: selectBook.value.img_url})
          .then(function() {
            store.dispatch('module/requestzzim', userId)
                  .then(function (result) {
                      store.commit('module/setZzimList', result.data.data)
                      heart.classList.add('is-active')
                  })
          })
      }
    }
    return {
      selectBook,
      zzim,
      score,
      bookmark
    }
  }
}
</script>

<style lang="scss">
.book_character{
  width:60px;
  height: 60px;
  position:absolute;
  z-index:100;
  top:-25px;
  left:-20px;
}
.detail_book{
  position: relative;
}
.detail_side{
  width:350px;
  height:650px;
}
.logo{
  font-size:50px;
  font-weight:bold;
  text-align:center;
}
.side_logo{
  position:relative;
  top:7px;
  left:0px;
  width:70%;
}
.detail_side_content{
  width:90%;
  height:525px;
  background-color: white;
  position:relative;
  top:5px;
  left:30px;
  border-radius: 30px;
  box-shadow: lightgrey 2px 3px 3px;
}
.bookmark{
  margin-left:30px;
  cursor:pointer;
}
.detail_name{
    font-size:20px;
    width:95%;
    margin:0 auto;
    /* margin-top:5px; */
    font-weight:bold;
    text-align:center;
}
.detail_author{
    width:95%;
    margin:0 auto;
    text-align:center;
    font-size:16px;
}
.detail_book{
    margin: 0 auto;
    margin-top:25px;
    width:250px;
    height:320px;
}
.detail_img{
    width:250px;
    height:320px;
}
#heart {
  width:100px;
  height:100px;
  background: url("https://cssanimation.rocks/images/posts/steps/heart.png") no-repeat;
  background-position: 0 0;
  cursor: pointer;
  transition: background-position 1s steps(28);
  transition-duration: 0s;
  position:relative;
  top:-15px;
  &.is-active {
    transition-duration: 1s;
    background-position: -2800px 0;
  }
}
.shadow_red{
    box-shadow: 10px 10px 15px rgb(253, 185, 185);
}
.shadow_orange{
    box-shadow: 10px 10px 15px rgb(255, 202, 159);
}
.shadow_yellow{
    box-shadow: 10px 10px 15px rgb(254, 235, 168);
}
.shadow_green{
    box-shadow: 10px 10px 15px rgb(211, 236, 197);
}
.shadow_blue{
    box-shadow: 10px 10px 15px rgb(164, 194, 242);
}
.shadow_navy{
    box-shadow: 10px 10px 15px rgb(124, 138, 210);
}
.shadow_purple{
    box-shadow: 10px 10px 15px rgb(200, 167, 213);
}
// .placement {
//   // transform: translate(-50%, -50%);
// }
</style>