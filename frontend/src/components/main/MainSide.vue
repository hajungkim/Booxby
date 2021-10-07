<template>
  <div class="main_side">
      <div class="logo">
        <q-img class="main_logo" src="~assets/images/logo.png"/>
      </div>
      <div class="side_content">
        <div class="side_user">
          <q-img v-if="-2972.6<=score && score <=-126.1" @click="goMypage" src='~assets/character/purple.png' class="user_profile"/>
          <q-img v-if="-126.1<score && score <=-31.8" @click="goMypage" src='~assets/character/navy.png' class="user_profile"/>
          <q-img v-if="-31.8<=score && score <=0" @click="goMypage" src='~assets/character/blue.png' class="user_profile"/>
          <q-img v-if="0<score && score <=36.1" @click="goMypage" src='~assets/character/green.png' class="user_profile"/>
          <q-img v-if="36.1<score && score <=84.5" @click="goMypage" src='~assets/character/orange.png' class="user_profile"/>
          <q-img v-if="84.5<score && score <=157.6" @click="goMypage" src='~assets/character/yellow.png' class="user_profile"/>
          <q-img v-if="157.6<score && score <=24386" @click="goMypage" src='~assets/character/red.png' class="user_profile"/>
          <div @click="goMypage" class="user_name">{{ loginUser.nickname }}</div>
        </div>
        <div class="side_menu">
          <div @click="emojiRecommend" class="side_list">
            <q-icon  style="font-size: 2em;" name="emoji_emotions"
                v-bind:class="{'basic':cate1==false,
                'main_color':cate1==true}"
            />
            <span class="list_text">색깔별 감성책</span>
          </div>
          <div @click="myRecommend" class="side_list">
            <q-icon style="font-size: 2em;" name="brush"
                v-bind:class="{'basic':cate2==false,
                'main_color':cate2==true}"
            />
            <span class="list_text">나의 감성 책 추천</span>
          </div>
          <div @click="zzimRecommend" class="side_list">
            <q-icon style="font-size: 2em;" name="favorite"
              v-bind:class="{'basic':cate3==false,
              'main_color':cate3==true}"
            />
            <span class="list_text">찜기반 책 추천</span>
          </div>
          <div @click="ageRecommend" class="side_list">
            <q-icon style="font-size: 2em;" name="people"
              v-bind:class="{'basic':cate4==false,
              'main_color':cate4==true}"
            />
            <span  class="list_text">나이와 성별 추천</span>
          </div>
          <div @click="showCategory" class="side_list">
            <q-icon style="font-size: 2em;" name="widgets"
              v-bind:class="{'basic':cate5==false,
              'main_color':cate5==true}"
            />
            <span class="list_text">카테고리 추천</span>
            <q-icon class="basic" style="font-size: 2.0em; margin-left:33px;" name="expand_more"
              v-bind:class="{'basic':cate5==false,
              'main_color':cate5==true}"
            />
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
            <q-icon style="font-size: 2em; color: grey; margin-left:20px;" name="logout"/>
            <span class="list_text" style="margin-left:5px;">로그아웃</span>
          </div>
        </div>
        
      </div>
  </div>
</template>

<script>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { login } from 'src/store/module/actions'

export default {
  setup () {
    const store = useStore()
    const router = useRouter()
    const Swal = require('sweetalert2')

    const loginUser = store.getters['module/getLoginUser']
    const categoryMode = computed(() => store.getters['module/getCategoryMode'])
    const zzimList = computed(() => store.getters['module/getZzimList'])

    const score = (loginUser.hashScore + loginUser.worldcupScore) / 2

    const category1= ref(false)
    const category2= ref(false)
    const category3= ref(false)
    const category4= ref(false)
    const category5= ref(false)
    const category6= ref(false)
    const category7= ref(false)
    const category8= ref(false)
    const category9= ref(false)
    
    let change=true

    let cate1= computed(() => store.getters['module/getCate1'])
    let cate2= computed(() => store.getters['module/getCate2'])
    let cate3= computed(() => store.getters['module/getCate3'])
    let cate4= computed(() => store.getters['module/getCate4'])
    let cate5= computed(() => store.getters['module/getCate5'])

    let category = []

    const showCategory = function () {
      if (cate5.value==false){
        store.commit('module/setTrueCate5')
      }
      if (cate2.value == true || cate3.value == true || cate4.value == true || cate1.value == true){
        store.commit('module/setfalseCate2')
        store.commit('module/setfalseCate3')
        store.commit('module/setfalseCate4')
        store.commit('module/setfalseCate1')
      }
      store.commit('module/setCategoryMode', !store.getters['module/getCategoryMode'])
    }
    const goMypage = function() {
      router.push('/my')
    }
    const logout = function() {
      router.push('/login')
    }
    function emojiRecommend(){  
      if (cate1.value==false){
        store.commit('module/setTrueCate1')
      }
      if (cate2.value == true || cate3.value == true || cate4.value == true || cate5.value == true){
        store.commit('module/setfalseCate2')
        store.commit('module/setfalseCate3')
        store.commit('module/setfalseCate4')
        store.commit('module/setfalseCate5')
      }
      store.dispatch('module/emojiRecommend').then((result)=>{
        store.commit('module/setBookList', result.data)
        store.commit('module/setSelectBook', result.data[0])
      }).catch((err)=>{
        console.log(err)
      })
    }
    function myRecommend(){
      if (cate2.value==false){
        store.commit('module/setTrueCate2')
      }
      if (cate1.value == true || cate3.value == true || cate4.value == true || cate5.value == true){
        store.commit('module/setfalseCate1')
        store.commit('module/setfalseCate3')
        store.commit('module/setfalseCate4')
        store.commit('module/setfalseCate5')
      }
      let score = (loginUser.hashScore + loginUser.worldcupScore) / 2
      score = Math.floor(score)
      store.dispatch('module/myRecommend',score).then((result)=>{
        store.commit('module/setBookList', result.data)
        store.commit('module/setSelectBook', result.data[0])
      }).catch((err)=>{
        console.log(err)
      })
    }
    function zzimRecommend(){
      if (cate3.value==false){
        store.commit('module/setTrueCate3')
      }
      if (cate2.value == true || cate1.value == true || cate4.value == true || cate5.value == true){
        store.commit('module/setfalseCate2')
        store.commit('module/setfalseCate1')
        store.commit('module/setfalseCate4')
        store.commit('module/setfalseCate5')
      }
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
      if (cate4.value==false){
        store.commit('module/setTrueCate4')
      }
      if (cate2.value == true || cate3.value == true || cate1.value == true || cate5.value == true){
        store.commit('module/setfalseCate2')
        store.commit('module/setfalseCate3')
        store.commit('module/setfalseCate1')
        store.commit('module/setfalseCate5')
      }
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
      change,
      cate1,
      cate2,
      cate3,
      cate4,
      cate5,
      score,
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
.basic{
  color:gray;
}
.main_color{
  color:rgb(86,86,239); 
}
.main_side{
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
.main_logo{
  position:relative;
  top:7px;
  left:0px;
  width:70%;
}
.side_content{
  width:90%;
  height:525px;
  background-color: white;
  position:relative;
  top:5px;
  left:30px;
  border-radius: 30px;
  box-shadow: lightgrey 2px 3px 3px;
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
  font-size:24px;
  font-weight:bold;
  margin-top:38px;
  cursor: pointer;
}
.side_menu{
  width:85%;
  height:400px;
  margin: 15px auto;
}
.side_list{
  margin-bottom:17px;
  padding-left:20px;
  margin-left:10px;
  margin-right:10px;
  cursor: pointer;
  /* box-shadow:grey 1px 1px 1px; */
}
.side_list:hover{
  background-color:rgb(228, 228, 228)
}
.list_text{
  margin-left:20px;
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