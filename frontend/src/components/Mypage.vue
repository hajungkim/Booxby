<template>
  <div>
    <div class="top_bar">
      <!-- 로고랑 뒤로가기 버튼 -->
    </div>
    <!-- 유저 정보들 -->
    <div class="user_info_container">
      <img class="user_img" src="../assets/user_default.png">
      <div class="user_info">
        <p>userkim</p>
        <div style="width:200px;">
          <p>#갬성 #느낌 #섬세 #방가 #헬로</p>
        </div>
      </div>
      <img class="emotion_chr" src="../assets/user_default.png">
    </div>

    <div class="select_container">
      <q-tabs
        v-model="tab"
        align="justify"
        narrow-indicator
        class="q-mb-lg"
      >
        <q-tab class="text-purple" name="zzim" label="찜 목록" />
        <q-tab class="text-orange" name="reviews" label="MY 리뷰" />
        <q-tab class="text-teal" name="settings" label="설정" />
      </q-tabs>

      <div class="q-gutter-y-sm">
        <q-tab-panels
          v-model="tab"
          animated
          transition-prev="fade"
          transition-next="fade"
          class="text-black"
        >
          <q-tab-panel name="zzim">
            <div class="zzim_container">
              <img src="../assets/sampleImg.jpg" alt="">
              <img src="../assets/sampleImg.jpg" alt="">
              <img src="../assets/sampleImg.jpg" alt="">
              <img src="../assets/sampleImg.jpg" alt="">
            </div>
          </q-tab-panel>
        </q-tab-panels>

        <q-tab-panels
          v-model="tab"
          animated
          transition-prev="fade"
          transition-next="fade"
          class="text-black text-center"
        >
          <q-tab-panel name="reviews">
            <div class="review_container">
              <div class="review_info">
                <div style="display:flex;">
                  <p style="font-size:20px;">올라의 모험</p>
                  <span style="font-size:15px; margin-top:3px;">★5</span>
                </div>
                <div style="display:flex;">
                  <div class="review_category">
                    <img style="width:20px; height:20px;" src="../assets/user_default.png" alt="">
                    <span style="margin-left:10px;">dasdas</span>
                  </div>
                  <div class="review_category">
                    <img style="width:20px; height:20px;" src="../assets/user_default.png" alt="">
                    <span style="margin-left:10px;">dasdas</span>
                  </div>
                </div>
              </div>
              <img style="width:90px; height:130px;" src="../assets/sampleImg.jpg" alt="">
            </div>
          </q-tab-panel>
        </q-tab-panels>

        <q-tab-panels
          v-model="tab"
          animated
          transition-prev="fade"
          transition-next="fade"
          class="bg-teal text-white text-center"
        >
          <q-tab-panel name="settings">
            <div class="q-col-gutter-md row items-start">
              <div class="col-4">
                <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
                  <div class="absolute-bottom text-subtitle1 text-center">
                    Caption
                  </div>
                </q-img>
              </div>
              <div class="col-4">
                <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
                  <div class="absolute-bottom text-subtitle1 text-center">
                    Caption
                  </div>
                </q-img>
              </div>
              <div class="col-4">
                <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
                  <div class="absolute-bottom text-subtitle1 text-center">
                    Caption
                  </div>
                </q-img>
              </div>
            </div>
          </q-tab-panel>
        </q-tab-panels>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
export default {
  setup(){
    return{
      tab: ref('zzim')
    }
  }
}
</script>

<style scoped>
.top_bar{
  margin-top:20px;
  height:50px;
  border:1px solid black;
}
.user_img{
  width: 120px;
  height:120px;
  border-radius: 50px;
  margin-right:50px;
}
.emotion_chr{
  width: 100px;
  height:100px;
  border-radius: 50px;
  margin-left:100px;
  margin-top:10px;
}
.user_info_container{
  display:flex;
  justify-content: center;
  margin-right:100px;
}
.user_info{
  margin: 10px 0px 0px 20px;
}
.select_container{
  margin-top:30px;
  margin-left:200px;
  max-width: 900px;
}
.zzim_container{
  display: flex;
  justify-content: space-around;
}
.review_container{
  border:1px solid black;
  display: flex;
  justify-content: space-around;
}
.review_info{
  display:flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
}
.review_category{
  width:90px;
  border:1px solid black;
  border-radius: 10px;
}
</style>