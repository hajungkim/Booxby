<template>
  <div>
    <div class="top_bar">
      <div class="logo">
        <q-img class="mypage_logo" src="~assets/images/logo.png"/>
      </div>
      <q-btn class="back_btn" @click="back" round style="background:rgb(86,86,239); color:white;" icon="undo" />
    </div>
    <!-- 유저 정보들 -->
    <div class="user_info_container">
      <q-img v-if="-2972.6<=score && score <=-126.1" src='~assets/character/purple.png' class="user_img"/>
      <q-img v-if="-126.1<score && score <=-31.8" src='~assets/character/navy.png' class="user_img"/>
      <q-img v-if="-31.8<=score && score <=0" src='~assets/character/blue.png' class="user_img"/>
      <q-img v-if="0<score && score <=36.1" src='~assets/character/green.png' class="user_img"/>
      <q-img v-if="36.1<score && score <=84.5" src='~assets/character/orange.png' class="user_img"/>
      <q-img v-if="84.5<score && score <=157.6" src='~assets/character/yellow.png' class="user_img"/>
      <q-img v-if="157.6<score && score <=2438.6" src='~assets/character/red.png' class="user_img"/>
      <div class="user_info">
        <p style="font-size:30px;">{{loginUser.nickname}}</p>
        <div style="display:flex; flex-wrap:wrap; width:400px; font-weight:bold;">
          <q-btn outline size="11px" v-for="(word,idx) in hashtags" :key="idx" disable="true" 
           style="margin:0px 5px 5px 0px; color:rgb(86,86,239); padding-right:7px; padding-left:7px;">#{{ word }}</q-btn>
        </div>
      </div>
    </div>

    <q-card class="card_container no-shadow" style="width:1000px;">
      <q-tabs
        v-model="tab"
        dense
        align="justify"
      >
        <q-tab class="text-red" name="zzim" label="찜 목록"/>
        <q-tab class="text-green" name="reviews" label="MY 리뷰" />
        <q-tab class="text-blue" name="settings" label="설정" />
      </q-tabs>

      <q-separator />

      <q-tab-panels v-model="tab" animated style="background-color: whitesmoke;">

        <q-tab-panel class="three_options zzims" name="zzim">
          <q-card v-for="zzimBook in zzimList" :key="zzimBook.isbn" @click="zzimDetail(zzimBook.isbn)" class="my-card no-shadow cardbooks">
            <q-img class="zzim_img" :src="zzimBook.imgUrl">
              <div class="absolute-bottom text-subtitle2 text-center">
                {{zzimBook.title}}
              </div>
            </q-img>
          </q-card>
        </q-tab-panel>

        <q-tab-panel class="three_options" name="reviews">
            <div v-for="item in myReview" :key="item.reviewId" class="review_container" @click="moveDetail(item.isbn)">
              <div class="review_info">
                <div class="title">
                  <p style="font-size:25px; width:600px;">{{item.title}}</p>
                  <q-rating
                      class="star"
                      v-if="item.reviewScore == 5"
                      v-model="score_5"
                      max="5"
                      size="1.5em"
                      color="secondary"
                      icon="star_border"
                      icon-selected="star"
                      icon-half="star_half"
                      readonly
                  />
                  <q-rating
                      class="star"
                      v-if="item.reviewScore == 4"
                      v-model="score_4"
                      max="5"
                      size="1.5em"
                      color="secondary"
                      icon="star_border"
                      icon-selected="star"
                      icon-half="star_half"
                      readonly
                  />
                  <q-rating
                      class="star"
                      v-if="item.reviewScore == 3"
                      v-model="score_3"
                      max="5"
                      size="1.5em"
                      color="secondary"
                      icon="star_border"
                      icon-selected="star"
                      icon-half="star_half"
                      readonly
                  />
                  <q-rating
                      class="star"
                      v-if="item.reviewScore == 2"
                      v-model="score_2"
                      max="5"
                      size="1.5em"
                      color="secondary"
                      icon="star_border"
                      icon-selected="star"
                      icon-half="star_half"
                      readonly
                  />
                  <q-rating
                      class="star"
                      v-if="item.reviewScore == 1"
                      v-model="score_1"
                      max="5"
                      size="1.5em"
                      color="secondary"
                      icon="star_border"
                      icon-selected="star"
                      icon-half="star_half"
                      readonly
                  />
                </div>
                <p style="margin-left:25px;">{{item.reviewContent}}</p>
                <div class="review_categories">
                  <div v-if="item.reviewIdea" class="review_category">
                    <img class="review_icon" src="../assets/Surprised_Emoji.png" alt="">
                    <div class="word">기발해요</div>
                  </div>
                  <div v-if="item.reviewLike" class="review_category">
                    <img class="review_icon" src="../assets/Thumbs_Up_Emoji.png" alt="">
                    <span class="word">유용해요</span>
                  </div>
                  <div v-if="item.reviewRead" class="review_category">
                    <img class="review_icon" src="../assets/Eyes_Emoji.png" alt="">
                    <span class="word">잘읽혀요</span>
                  </div>
                  <div v-if="item.reviewUseful" class="review_category">
                    <img class="review_icon" src="../assets/Heart_Emoji.png" alt="">
                    <span class="word">추천해요</span>
                  </div>
                </div>
              </div>
              <img style="width:90px; height:130px; margin-right:20px; margin-top:10px;" :src="item.imgUrl" alt="">
            </div>
          </q-tab-panel>

        <q-tab-panel class="three_options" name="settings">
          <div class="q-col-gutter-md row items-start">
            <div class="col-4">
              <q-img class="img" src="~assets/images/repair.png" @click="moveModify">
                <div class="absolute-bottom text-subtitle1 text-center" style="font-weight:bold; font-size:18px;">
                  개인 정보 수정
                </div>
              </q-img>
            </div>
            <div class="col-4">
              <q-img class="img" src="~assets/images/worldcup.png" @click="moveWorldcup">
                <div class="absolute-bottom text-subtitle1 text-center" style="font-weight:bold; font-size:18px;">
                  책 선호도 재설정
                </div>
              </q-img>
            </div>
            <div class="col-4" >
              <div>
              <q-img class="img" src="~assets/images/hashtag.png" @click="moveHash">
                <div class="absolute-bottom text-subtitle1 text-center" style="font-weight:bold; font-size:18px;">
                  해시태그 재설정 하기
                </div>
              </q-img>
              </div>
            </div>
          </div>
        </q-tab-panel>
      </q-tab-panels>
    </q-card>

  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router' 

export default {
  setup(){
    const kindofcolor = ['rgb(86,86,239)','primary','secondary','deep-orange','amber','brown-5','purple','black']

    const store = useStore()
    const router = useRouter()

    const loginUser = computed(()=>store.getters['module/getLoginUser'])
    const score = (loginUser.value.hashScore + loginUser.value.worldcupScore) / 2
    const zzimList = computed(() => store.getters['module/getZzimList'])
    const myReview = computed(() => store.getters['module/getMyReview'])
    
    console.log(loginUser.value.hashScore,'@@@')
    let hashtags = loginUser.value.hashtag.split('#')
    hashtags.shift()

    const back = function() {
      router.push('/main')
    }

    const moveModify = function(){
      router.push('/modify')
    }
    function moveDetail(isbn){
      store.dispatch('module/getisbnInfo',isbn)
        .then(function (result) {
          store.commit('module/setSelectBook', result.data[0])
          router.push('/detail')
        })
    }
    function moveHash(){
      router.push('hashtag')
    }
    function moveWorldcup() {
      router.push('worldCup')
    }

    function zzimDetail(isbn){
      store.dispatch('module/getwords',isbn)
          .then((res)=>{
              let tmp = []
              for (let i = 0; i < res.data.length; i++) {
                  let j = {
                      "name": '',
                      "value": ''
                  }
                  j.name = res.data[i][0]
                  j.value = res.data[i][1]
                  tmp.push(j)
              }
          store.commit('module/setwords', tmp)
      })
      store.dispatch('module/getisbnInfo',isbn)
      .then((res) =>{
        store.commit('module/setSelectBook', res.data[0])
        router.push('/detail')
      })
    }
    
    onMounted(() => {
      store.dispatch('module/oxbooks').then(function (res) {
        store.commit('module/setOxbooks',res.data)
      })  
      store.dispatch('module/requestMyReview')
        .then(function (result){
          store.commit('module/setMyReview', result.data.data)
      })
    })
    return{
      tab: ref('zzim'),
      loginUser,
      hashtags,
      kindofcolor,
      zzimDetail,
      moveModify,
      back,
      moveHash,
      moveDetail,
      moveWorldcup,
      zzimList,
      myReview,
      score,
      score_5: ref(5),
      score_4: ref(4),
      score_3: ref(3),
      score_2: ref(2),
      score_1: ref(1),
    }
  }
}
</script>

<style scoped>
/* 상단바 */
.top_bar{
  margin-top:20px;
  height:50px;
}
.logo{
  width:350px;
  display:inline-block;
}
.mypage_logo{
  width:70%;
  position:relative;
  top:-10px;
  left:0px;
}
.back_btn{
  float:right;
  margin-top:5px;
  margin-right:25px;
  height:54px;
  width:54px;
  font-size:18px;
}
/* 유저 정보창 */
.user_img{
  width: 100px;
  height:100px;
  border-radius: 70px;
  margin-right:30px;
}
.user_info{
  margin: 10px 0px 0px 20px;
}
.user_info_container{
  display:flex;
  justify-content: center;
}
.hashtag{
  display: flex;
}
/* tab 부분 */
.card_container{
  margin-top:30px;
  margin-left:210px;
  max-width: 900px;
  background-color: whitesmoke;
}
.three_options{
  height: 350px;
  overflow:auto;
  -ms-overflow-style: none;
  scrollbar-width: none;
}
.three_options::-webkit-scrollbar{
  display: none;
}
/* 찜목록 */
.zzims{
  display:flex;
  flex-wrap: wrap;
  width:860px;
  margin-left:40px;
  padding-left:50px;
}
.zzim_img{
  width:160px;
  height: 200px;
  transition: .5s;
}
.zzim_img:hover{
  transform: scale(1.1);
}
.cardbooks{
  margin-right:30px;
  margin-top:20px;
  height:200px;
  cursor: pointer;
}
/* My 리뷰 */
.review_container{
  margin-bottom:20px;
  padding-bottom: 10px;
  display: flex;
  justify-content: space-between;
  border-radius: 20px;
  cursor: pointer;
  background: white;
  box-shadow: lightgrey 3px 3px 3px;
}
.review_container:hover{
  background: rgb(233, 233, 233);
}
.review_info{
  display:flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
}
.title{
  display:flex;
  margin-left:20px;
}
.review_categories{
  display:flex;
  margin-left:25px;
}
.review_category{
  display: flex;
  width:90px;
  height:30px;
  background-color: rgb(218, 237, 248);
  border-radius: 20px;
  margin-right:20px;
}
.review_icon{
  width:20px;
  height:20px;
  margin: 5px 0px 0px 7px;
}
.word{
  margin:6px 0px 0px 5px;
  color:rgb(8, 84, 173);
  font-weight: bold;
}
.star{
  margin-bottom:15px;
  margin-left:12px;
}
/* 설정 */
.img{
  margin-left:20px;
  width:250px;
  height: 300px;
  cursor: pointer;
}
</style>