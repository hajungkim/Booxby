<template>
  <div class="detail_side">
      <div class="logo">
        <q-img class="side_logo" src="~assets/images/logo.png"/>
      </div>
      <div class="detail_side_content">
            <div>
                <!-- <q-icon v-if="zzim" @click="zzimOff" class="bookmark" style="font-size: 3.7em; color: red;" name="bookmark"/>
                <q-icon v-if="!zzim" @click="zzimOn" class="bookmark" style="font-size: 3.7em; color: black;" name="bookmark_border"/> -->
                <div class="placement">
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
                <q-img :src="selectBook.img_url" class="detail_img" />
            </div>
            </div>
      </div>
  </div>
</template>

<script>
import { computed, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()

    const selectBook = computed(() => store.getters['module/getSelectBook'])
    const zzim = computed(() => store.getters['module/getZzim'])

    const userId = localStorage.getItem('userId')
    store.dispatch('module/requestzzim', userId)
      .then(function (result) {
          for(let i = 0; i < result.data.data.length; i++) {
            console.log('selectBook.value.isbn13', result.data.data[i].isbn)
              if(selectBook.value.isbn13 == result.data.data[i].isbn) {
                store.commit('module/setZzim', true)
                console.log('트루')
                break
              }
              if(i==result.data.data.length-1) {
                store.commit('module/setZzim', false)
                console.log('펄스')
              }
          }
          const heart = document.getElementById('heart')
          const zzim = store.getters['module/getZzim']
          console.log('온마운트', zzim, heart)
          if(zzim){
            heart.classList.add('is-active')
            console.log('1',heart)
          }
          else if(!zzim){
            heart.classList.remove('is-active')
            console.log('2',heart)
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
    
    onMounted(() => {
      // const heart = document.getElementById('heart')
      // const zzim = store.getters['module/getZzim']
      // console.log('온마운트', zzim, heart)
      // if(zzim){
      //   heart.classList.add('is-active')
      //   console.log('1',heart)
      // }
      // else if(!zzim){
      //   heart.classList.remove('is-active')
      //   console.log('2',heart)
      // }
    })
    return {
      selectBook,
      zzim,
      bookmark
    }
  }
}
</script>

<style lang="scss">
.detail_side{
  width:350px;
  height:650px;
  /* border:1px solid red; */
}
.logo{
  /* border:1px solid blue; */
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
// .placement {
//   // transform: translate(-50%, -50%);
// }
</style>