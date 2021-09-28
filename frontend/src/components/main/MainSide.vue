<template>
  <div class="main_side">
      <div class="logo">
        Booxby
      </div>
      <div class="side_content">
        <div class="side_user">
          <q-img @click="goMypage" :src="loginUser.profile" class="user_profile"/>
          <div @click="goMypage" class="user_name">{{ loginUser.nickname }}</div>
        </div>
        <div class="side_menu">
          <div @click="emojiRecommend" class="side_list">
            <q-icon style="font-size: 2.5em; color: grey;" name="emoji_emotions"/>
            <span class="list_text">감성책 추천</span>
          </div>
          <div @click="myRecommend" class="side_list">
            <q-icon style="font-size: 2.5em; color: grey;" name="brush"/>
            <span class="list_text">나의 감성 책 추천</span>
          </div>
          <div @click="zzimRecommend" class="side_list">
            <q-icon style="font-size: 2.5em; color: grey;" name="thumb_up"/>
            <span class="list_text">찜기반 책 추천</span>
          </div>
          <div @click="ageRecommend" class="side_list">
            <q-icon style="font-size: 2.5em; color: grey;" name="people"/>
            <span class="list_text">나이와 성별 추천</span>
          </div>
          <div @click="showCategory" class="side_list">
            <q-icon style="font-size: 2.5em; color: grey;" name="widgets"/>
            <span class="list_text">카테고리 추천</span>
            <q-icon style="font-size: 2.0em; color: grey; margin-left:33px;" name="expand_more"/>
          </div>
          <div class="side_category row" v-show="categoryMode">
            <div class="col-4">
              <q-checkbox @click="sendCategory" v-model="category1" />아동
              <q-checkbox @click="sendCategory" v-model="category2" />문학
              <q-checkbox @click="sendCategory" v-model="category3" />취미
            </div>
            <div class="col-4">
              <q-checkbox @click="sendCategory" v-model="category4" />청소년
              <q-checkbox @click="sendCategory" v-model="category5" />학문
              <q-checkbox @click="sendCategory" v-model="category6" />오락
            </div>
            <div class="col-4">
              <q-checkbox @click="sendCategory" v-model="category7" />가정
              <q-checkbox @click="sendCategory" v-model="category8" />교육
              <q-checkbox @click="sendCategory" v-model="category9" />기타
            </div>
          </div>
          <div @click="logout" id="logout">
            <q-icon style="font-size: 2.5em; color: grey;" name="logout"/>
            <span class="list_text">로그아웃</span>
          </div>
        </div>
        
      </div>
  </div>
</template>

<script>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  setup () {
    const store = useStore()
    const router = useRouter()
    const Swal = require('sweetalert2')

    const loginUser = store.getters['module/getLoginUser']
    const categoryMode = computed(() => store.getters['module/getCategoryMode'])
    const zzimList = computed(() => store.getters['module/getZzimList'])

    const category1= ref(false)
    const category2= ref(false)
    const category3= ref(false)
    const category4= ref(false)
    const category5= ref(false)
    const category6= ref(false)
    const category7= ref(false)
    const category8= ref(false)
    const category9= ref(false)
    
    let category = []

    const showCategory = function () {
      store.commit('module/setCategoryMode', !store.getters['module/getCategoryMode'])
    }
    const goMypage = function() {
      router.push('/my')
    }
    const logout = function() {
      router.push('/')
    }
    function emojiRecommend(){
      store.dispatch('module/emojiRecommend').then((result)=>{
        store.commit('module/setBookList', result.data)
        store.commit('module/setSelectBook', result.data[0])
      }).catch((err)=>{
        console.log(err)
      })
    }
    function myRecommend(){
      let score = (loginUser.hashscore + loginUser.worldcupscore) / 2
      store.dispatch('module/myRecommend',score).then((result)=>{
        store.commit('module/setBookList', result.data)
        store.commit('module/setSelectBook', result.data[0])
      }).catch((err)=>{
        console.log(err)
      })
    }
    function zzimRecommend(){
      if (!zzimList.value.length){
        Swal.fire({
          icon: 'error',
          title: '<span style="font-size:25px;">찜을 하지 않았어요!!</span>',
          confirmButtonColor: '#ce1919',
          confirmButtonText: '<span style="font-size:18px;">확인</span>'
        })
        return
      }
      let timerInterval
      Swal.fire({
        title: '찜목록을 분석하고 있습니다',
        html: '<b></b>ms 뒤에 종료됩니다',
        timer: 2500,
        timerProgressBar: true,
        didOpen: () => {
          Swal.showLoading()
          const b = Swal.getHtmlContainer().querySelector('b')
          timerInterval = setInterval(() => {
            b.textContent = Swal.getTimerLeft()
          }, 100)
        },
        willClose: () => {
          clearInterval(timerInterval)
        }
      }).then(() => {
      })
      let sendzzim = {
        isbn_list:[]
      }
      zzimList.value.forEach(e => {
        sendzzim.isbn_list.push(e.isbn)
      });
      store.dispatch('module/zzimRecommend',sendzzim).then((result)=>{
        store.commit('module/setBookList', result.data)
        store.commit('module/setSelectBook', result.data[0])
      }).catch((err)=>{
        console.log(err)
      })
    }
    function ageRecommend(){
      const age = loginUser.age
      const gender = loginUser.gender=='0' ? "남성" : "여성"
      const ageGender = {
        age: age,
        gender: gender
      }
      store.dispatch('module/ageRecommend',ageGender).then((result)=>{
        store.commit('module/setBookList', result.data)
        store.commit('module/setSelectBook', result.data[0])
      }).catch((err)=>{
        console.log(err)
      })
    }
    function sendCategory(){
      if (category1.value == true && !category.includes('아동')) category.push('아동')
      else if (category1.value == false && category.includes('아동')) {
        var index = category.indexOf('아동')
        category.splice(index,1)
      }
      if (category2.value == true && !category.includes('문학')) category.push('문학')
      else if (category2.value == false && category.includes('문학')) {
        var index = category.indexOf('문학')
        category.splice(index,1)
      }
      if (category3.value == true && !category.includes('취미')) category.push('취미')
      else if (category3.value == false && category.includes('취미')) {
        var index = category.indexOf('취미')
        category.splice(index,1)
      }
      if (category4.value == true && !category.includes('청소년')) category.push('청소년')
      else if (category4.value == false && category.includes('청소년')) {
        var index = category.indexOf('청소년')
        category.splice(index,1)
      }
      if (category5.value == true && !category.includes('학문')) category.push('학문')
      else if (category5.value == false && category.includes('학문')) {
        var index = category.indexOf('학문')
        category.splice(index,1)
      }
      if (category6.value == true && !category.includes('오락')) category.push('오락')
      else if (category6.value == false && category.includes('오락')) {
        var index = category.indexOf('오락')
        category.splice(index,1)
      }
      if (category7.value == true && !category.includes('가정')) category.push('가정')
      else if (category7.value == false && category.includes('가정')) {
        var index = category.indexOf('가정')
        category.splice(index,1)
      }
      if (category8.value == true && !category.includes('교육')) category.push('교육')
      else if (category8.value == false && category.includes('교육')) {
        var index = category.indexOf('교육')
        category.splice(index,1)
      }
      if (category9.value == true && !category.includes('기타')) category.push('기타')
      else if (category9.value == false && category.includes('기타')) {
        var index = category.indexOf('기타')
        category.splice(index,1)
      }
      if (category.length == 0) return
      store.dispatch('module/getCategory',category).then((result)=>{
        store.commit('module/setBookList', result.data)
        store.commit('module/setSelectBook', result.data[0])
      })
      .catch((err)=>{
        console.log(err)
      })
    }
    return {
      category1,
      category2,
      category3,
      category4,
      category5,
      category6,
      category7,
      category8,
      category9,
      loginUser,
      categoryMode,
      showCategory,
      goMypage,
      logout,
      emojiRecommend,
      myRecommend,
      zzimRecommend,
      ageRecommend,
      sendCategory
    }
  }
}
</script>

<style>
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
.side_user{
  width:75%;
  margin: auto;
  height:100px;
  /* border:1px solid red; */
}
.user_profile{
  width:70px;
  height:70px;
  border-radius: 50px;
  margin-top:20px;
  margin-left:20px;
  float:left;
  cursor: pointer;
}
.user_name{
  float:right;
  width:53%;
  font-size:20px;
  font-weight:bold;
  margin-top:40px;
  /* border:1px solid green; */
}
.side_menu{
  width:85%;
  height:400px;
  margin: 15px auto;
}
.side_list{
  margin-bottom:5px;
  padding-left:15px;
  margin-left:10px;
  margin-right:10px;
  cursor: pointer;
  /* box-shadow:grey 1px 1px 1px; */
}
.side_list:hover{
  background-color:rgb(228, 228, 228)
}
.list_text{
  margin-left:30px;
  font-weight:550;
  font-size:15px;
  position: relative;
  top:3px;
}
.side_category{
  margin-left:8px;
  border:1px solid grey;
  border-radius: 15px;
  height:120px;
  width:93%;
  animation: bottomFadeIn 0.7s ease-in-out;
}
#logout{
  /* margin-top:85px;
  margin-left:40px;
  margin-right:40px; */
  position:absolute;
  top:480px;
  left:60px;
  width:180px;
  padding-left:25px;
  cursor: pointer;
}
#logout:hover{
  background-color:rgb(228, 228, 228)
}
@keyframes bottomFadeIn {
  from {
    opacity: 0;
    transform: translateY(-5px);
  }
  to {
    opacity: 3;
    transform: none;
  }
}
</style>