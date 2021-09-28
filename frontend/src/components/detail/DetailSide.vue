<template>
  <div class="main_side">
      <div class="logo">
        Booxby
      </div>
      <div class="side_content">
            <div>
                <q-icon v-if="zzim" @click="zzimOff" class="bookmark" style="font-size: 3.7em; color: red;" name="bookmark"/>
                <q-icon v-if="!zzim" @click="zzimOn" class="bookmark" style="font-size: 3.7em; color: black;" name="bookmark_border"/>
            </div>
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
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()

    const selectBook = computed(() => store.getters['module/getSelectBook'])
    const zzim = computed(() => store.getters['module/getZzim'])

    const zzimOn = function () {
      const userId = localStorage.getItem('userId')
      store.commit('module/setZzim', true)
      console.log(selectBook.value)
      store.dispatch('module/zzimOn', {isbn: selectBook.value.isbn13, userId: userId, title: selectBook.value.title, imgUrl: selectBook.value.img_url})
        .then(function() {
        })
    }
    const zzimOff = function () {
      const userId = localStorage.getItem('userId')
      store.commit('module/setZzim', false)
      store.dispatch('module/zzimOff', {isbn: selectBook.value.isbn13, userId: userId})
        .then(function() {
        })
    }

    const userId = localStorage.getItem('userId')
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
      })

    return {
      selectBook,
      zzim,
      zzimOn,
      zzimOff
    }
  }
}
</script>

<style scoped>
.main_side{
  width:350px;
  height:650px;
  /* border:1px solid red; */
}
.logo{
  /* border:1px solid blue; */
  font-size:50px;
  font-weight:bold;
  padding-left:105px;
  padding-top:15px;
}
.side_content{
  width:90%;
  height:525px;
  background-color: white;
  position:relative;
  top:10px;
  left:30px;
  border-radius: 30px;
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
</style>