<template>
  <div class="main_side">
      <div class="logo">
        Booxby
      </div>
      <div class="side_content">
        <div class="side_user">
          <q-img @click="goMypage" :src="loginUser.profile" class="user_profile"/>
          <div class="user_name">{{ loginUser.nickname }}</div>
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
              <q-checkbox v-model="category1" />아동
              <q-checkbox v-model="category2" />문학
              <q-checkbox v-model="category3" />취미
            </div>
            <div class="col-4">
              <q-checkbox v-model="category4" />청소년
              <q-checkbox v-model="category5" />학문
              <q-checkbox v-model="category6" />오락
            </div>
            <div class="col-4">
              <q-checkbox v-model="category7" />가정
              <q-checkbox v-model="category8" />교육
              <q-checkbox v-model="category9" />기타
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

    const loginUser = store.getters['module/getLoginUser']
    const categoryMode = computed(() => store.getters['module/getCategoryMode'])

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
      // loginUser.score 넘겨주기
      const score = '4321'
      store.dispatch('module/myRecommend',score).then((result)=>{
        store.commit('module/setBookList', result.data)
        store.commit('module/setSelectBook', result.data[0])
      }).catch((err)=>{
        console.log(err)
      })
    }
    function ageRecommend(){
      // loginUser.age / loginUser.gender 넘겨주기
      const age = '20대'
      const gender = '남성'
      store.dispatch('module/ageRecommend',age,gender).then((result)=>{
        store.commit('module/setBookList', result.data)
        store.commit('module/setSelectBook', result.data[0])
      }).catch((err)=>{
        console.log(err)
      })
    }
    return {
      category1: ref(false),
      category2: ref(false),
      category3: ref(false),
      category4: ref(false),
      category5: ref(false),
      category6: ref(false),
      category7: ref(false),
      category8: ref(false),
      category9: ref(false),
      loginUser,
      categoryMode,
      showCategory,
      goMypage,
      logout,
      emojiRecommend,
      myRecommend,
      ageRecommend
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