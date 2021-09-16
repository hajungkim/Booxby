<template>
  <div>
    <div class="top_bar">
      <span class="logo">BooxBy</span>
      <q-icon class="back_btn" size="md" name="arrow_back"></q-icon>
    </div>
    <!-- 유저 정보들 -->
    <div class="user_info_container">
      <img class="user_img" src="../assets/user_default.png">
      <div class="user_info">
        <p style="font-size:30px;">{{loginUser.nickname}}</p>
        <div style="display:flex; flex-wrap:wrap; width:200px; font-weight:bold;">
          <div v-for="(word,idx) in hashtags" :key="idx">#{{ word }} </div>
        </div>
      </div>
      <img class="emotion_chr" src="../assets/user_default.png">
    </div>

    <q-card class="card_container no-shadow" style="width:1000px;">
      <q-tabs
        v-model="tab"
        dense
        align="justify"
      >
        <q-tab class="text-red" name="zzim" label="찜 목록" />
        <q-tab class="text-green" name="reviews" label="MY 리뷰" />
        <q-tab class="text-blue" name="settings" label="설정" />
      </q-tabs>

      <q-separator />

      <q-tab-panels v-model="tab" animated style="background-color: rgb(227,231,234);">

        <q-tab-panel class="three_options zzims" name="zzim">
          <q-card class="my-card no-shadow cardbooks" id="card">
            <q-img class="zzim_img" src="https://cdn.quasar.dev/img/parallax2.jpg">
              <div class="absolute-bottom text-subtitle2 text-center">
                Title
              </div>
            </q-img>
          </q-card>
          <q-card class="my-card no-shadow cardbooks" id="card">
            <q-img class="zzim_img" src="https://cdn.quasar.dev/img/parallax2.jpg">
              <div class="absolute-bottom text-subtitle2 text-center">
                Title
              </div>
            </q-img>
          </q-card>
          <q-card class="my-card no-shadow cardbooks" id="card">
            <q-img class="zzim_img" src="https://cdn.quasar.dev/img/parallax2.jpg">
              <div class="absolute-bottom text-subtitle2 text-center">
                Title
              </div>
            </q-img>
          </q-card>
          <q-card class="my-card no-shadow cardbooks" id="card">
            <q-img class="zzim_img" src="https://cdn.quasar.dev/img/parallax2.jpg">
              <div class="absolute-bottom text-subtitle2 text-center">
                Title
              </div>
            </q-img>
          </q-card>
          <q-card class="my-card no-shadow cardbooks" id="card">
            <q-img class="zzim_img" src="https://cdn.quasar.dev/img/parallax2.jpg">
              <div class="absolute-bottom text-subtitle2 text-center">
                Title
              </div>
            </q-img>
          </q-card>
        </q-tab-panel>

        <q-tab-panel class="three_options" name="reviews">
            <div class="review_container">
              <div class="review_info" @click="moveDetail">
                <div class="title">
                  <p style="font-size:30px;">올라의 모험</p>
                  <span style="font-size:20px; margin-top:10px; color:gray;"><q-icon style="margin-top:-4px;" name="star"></q-icon>5</span>
                </div>
                <p style="margin-left:25px;">이 책 정말 재밌어요 주인공의 심경 변화가 이렇게 잘 묘사된 책은 없을 겁니다!</p>
                <div class="review_categories">
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Surprised_Emoji.png" alt="">
                    <div class="word">기발해요</div>
                  </div>
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Thumbs_Up_Emoji.png" alt="">
                    <span class="word">유용해요</span>
                  </div>
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Eyes_Emoji.png" alt="">
                    <span class="word">잘읽혀요</span>
                  </div>
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Heart_Emoji.png" alt="">
                    <span class="word">추천해요</span>
                  </div>
                </div>
              </div>
              <img style="width:90px; height:130px; margin-right:20px;" src="../assets/sampleImg.jpg" alt="">
            </div>
            <div class="review_container">
              <div class="review_info">
                <div class="title">
                  <p style="font-size:30px;">올라의 모험</p>
                  <span style="font-size:20px; margin-top:10px; color:gray;"><q-icon style="margin-top:-4px;" name="star"></q-icon>5</span>
                </div>
                <p style="margin-left:25px;">이 책 정말 재밌어요 주인공의 심경 변화가 이렇게 잘 묘사된 책은 없을 겁니다!</p>
                <div class="review_categories">
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Surprised_Emoji.png" alt="">
                    <div class="word">기발해요</div>
                  </div>
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Thumbs_Up_Emoji.png" alt="">
                    <span class="word">유용해요</span>
                  </div>
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Eyes_Emoji.png" alt="">
                    <span class="word">잘읽혀요</span>
                  </div>
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Heart_Emoji.png" alt="">
                    <span class="word">추천해요</span>
                  </div>
                </div>
              </div>
              <img style="width:90px; height:130px; margin-right:20px;" src="../assets/sampleImg.jpg" alt="">
            </div>
            <div class="review_container">
              <div class="review_info">
                <div class="title">
                  <p style="font-size:30px;">올라의 모험</p>
                  <span style="font-size:20px; margin-top:10px; color:gray;"><q-icon style="margin-top:-4px;" name="star"></q-icon>5</span>
                </div>
                <p style="margin-left:25px;">이 책 정말 재밌어요 주인공의 심경 변화가 이렇게 잘 묘사된 책은 없을 겁니다!</p>
                <div class="review_categories">
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Surprised_Emoji.png" alt="">
                    <div class="word">기발해요</div>
                  </div>
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Thumbs_Up_Emoji.png" alt="">
                    <span class="word">유용해요</span>
                  </div>
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Eyes_Emoji.png" alt="">
                    <span class="word">잘읽혀요</span>
                  </div>
                  <div class="review_category">
                    <img class="review_icon" src="../assets/Heart_Emoji.png" alt="">
                    <span class="word">추천해요</span>
                  </div>
                </div>
              </div>
              <img style="width:90px; height:130px; margin-right:20px;" src="../assets/sampleImg.jpg" alt="">
            </div>
          </q-tab-panel>

        <q-tab-panel class="three_options" name="settings">
          <div class="q-col-gutter-md row items-start">
            <div class="col-4" @click="moveModify">
              <q-img class="img" src="https://cdn.quasar.dev/img/parallax2.jpg">
                <div class="absolute-bottom text-subtitle1 text-center">
                  개인 정보 수정
                </div>
              </q-img>
            </div>
            <div class="col-4">
              <q-img class="img" src="https://cdn.quasar.dev/img/parallax2.jpg">
                <div class="absolute-bottom text-subtitle1 text-center">
                  Book World cup
                </div>
              </q-img>
            </div>
            <div class="col-4" @click="moveHash">
              <q-img class="img" src="https://cdn.quasar.dev/img/parallax2.jpg">
                <div class="absolute-bottom text-subtitle1 text-center">
                  해시태그 재설정 하기
                </div>
              </q-img>
            </div>
          </div>
        </q-tab-panel>
      </q-tab-panels>
    </q-card>

  </div>
</template>

<script>
import { computed, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router' 
export default {
  setup(){
    const router = useRouter()
    const store = useStore()
    const userId = localStorage.getItem('userId')
    let hashtags = []

    const loginUser = store.getters['module/getLoginUser']
    const arr = loginUser.hashtag.split('#')
    hashtags = arr.splice(1,arr.length)
    console.log(hashtags,'@@')

    const moveModify = function(){
      router.push('/modify')
    }
    function moveDetail(){
      router.push('detail')
    }
    function moveHash(){
      router.push('hashtag')
    }
    return{
      tab: ref('zzim'),
      userId,
      loginUser,
      hashtags,
      moveModify,
      moveHash,
      moveDetail
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
  font-size:30px;
  margin-left:50px;
}
.back_btn{
  float:right;
  margin-right:30px;
}
/* 유저 정보창 */
.user_img{
  width: 120px;
  height:120px;
  border-radius: 50px;
  margin-right:50px;
}
.user_info{
  margin: 10px 0px 0px 20px;
}
.user_info_container{
  display:flex;
  justify-content: center;
}
.emotion_chr{
  width: 100px;
  height:100px;
  border-radius: 50px;
  margin-left:100px;
  margin-top:10px;
}
.hashtag{
  display: flex;
}
/* tab 부분 */
.card_container{
  margin-top:30px;
  margin-left:210px;
  max-width: 900px;
  background-color: rgb(227,231,234);
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
}
.cardbooks{
  margin-right:30px;
  margin-top:20px;
  height:200px;
  cursor: pointer;
}
/* My 리뷰 */
.review_container{
  box-shadow: 1px 1px 1px 1px gray;
  margin-bottom:20px;
  padding-bottom: 10px;
  display: flex;
  justify-content: space-between;
  border-radius: 20px;
  cursor: pointer;
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
  margin: 5px 0px 0px 3px;
}
.word{
  margin:6px 0px 0px 5px;
  color:rgb(8, 84, 173);
  font-weight: bold;
}
/* 설정 */
.img{
  height: 318px;
  cursor: pointer;
}
</style>