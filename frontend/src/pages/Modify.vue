<template>
  <div class="modify_container">
    <div class="modify_wrap">
      <div class="modify_top">
        <q-icon class="back_btn" size="md" name="arrow_back" @click="moveMy"></q-icon>
      </div>
      <div class="text-group">
        <h4 class="m-0">개인 정보 수정</h4>
      </div>
      <div class="form-group">
        
        <!-- <img class="change_img" :src="form.profile" alt=""> -->
        <q-img v-if="-29726<=score && score <=-1261" src='~assets/character/purple.png' class="change_img"/>
        <q-img v-if="-1261<score && score <=-318" src='~assets/character/navy.png' class="change_img"/>
        <q-img v-if="-318<=score && score <=0" src='~assets/character/blue.png' class="change_img"/>
        <q-img v-if="0<score && score <=361" src='~assets/character/green.png' class="change_img"/>
        <q-img v-if="361<score && score <=845" src='~assets/character/orange.png' class="change_img"/>
        <q-img v-if="845<score && score <=1576" src='~assets/character/yellow.png' class="change_img"/>
        <q-img v-if="1576<score && score <=24386" src='~assets/character/red.png' class="change_img"/>

        <div class="form-mb">
          <q-input
              class="input"
              label="Email"
              type="email"
              v-model="form.email"
              lazy-rules
              disable
              :rules="[
              val => !!val || '필수입력항목 입니다.',
              checkEmail
              ]"
            />
        </div>

        <div class="form-mb">
          <q-input class="input" label="Password" color="teal" v-model="form.password" type="password"
            lazy-rules
              :rules="[
                val => val && val.length >= 8 || '8자리 이상 입력해주세요.',
                checkPassWord
              ]"/>
        </div>

        <div class="form-mb">
          <q-input class="input" label="Confirm Password" type="password" v-model="form.passwordconfirmation"
            lazy-rules
              :rules="[
                val => val && val.length > 0 || '필수입력항목 입니다.',
                checkPassWordConfirmation
              ]"/>
        </div>

        <div>
          <q-input class="input" label="NickName"
            v-model="form.nickname"
            lazy-rules
              :rules="[
                val => val && val.length >= 2 || '두글자 이상 입력 바랍니다.',
                checkName
              ]"/>
        </div>
        <!-- 버튼 -->
        <div class="submit_bt">
          <q-btn @click="modify" class="submit" color="primary" label="정보수정하기" />
        </div>

      </div>
    </div>
    <div class="signupImg">
      <!-- <img src="" alt=""> -->
    </div>
  </div>
</template>

<script>
import { reactive, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router' 
// import axios from 'axios'
export default {
  setup(){
    const router = useRouter()
    const store = useStore()

    const Swal = require('sweetalert2')

    const form = reactive({
      email: '',
      password: '',
      passwordconfirmation: '',
      nickname: '',
      profile: '',
    })

    function checkName (val) {
      const reg = /[ㄱ-ㅎㅏ-ㅣ가-힣]/g
      return (reg.test(val)||'한글만 입력가능합니다.')
    }
    // 비밀번호 유효성 체크
    function checkPassWord (val) {
      const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/
      return (reg.test(val)||'최소 각 하나의 문자, 숫자, 특수 문자가 포함된 8자리 이상이어야 합니다.')
    }
    function checkPassWordConfirmation (val) {
      if (form.password != val){
        return ('비밀번호가 일치하지 않습니다.')
      }
    }
    // 이메일 유효성 체크
    function checkEmail (val) {
      const reg = /^[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
      return (reg.test(val)||'이메일 형식이 잘못되었습니다.')
    }
    const loginUser = store.getters['module/getLoginUser']
    const score = (loginUser.hashScore + loginUser.worldcupScore) / 2

    onMounted(() => {
      // 초기값 셋팅
      const loginUser = store.getters['module/getLoginUser']
      form.email = loginUser.email
      form.password = loginUser.password
      form.passwordconfirmation = loginUser.password
      form.nickname = loginUser.nickname
      form.profile = loginUser.profile
    })

    // 뒤로가기
    const moveMy = function(){
      router.push('/my')
    }

    // 회원정보 수정
    const modify = function () {
      const loginUser = store.getters['module/getLoginUser']
      store.dispatch('module/modifyInfo', {hashtag: loginUser.hashtag, nickname: form.nickname, password: form.password, profilePath: form.profile })
        .then(function () {
          Swal.fire({
                icon: 'success',
                title: '<span style="font-size:25px;">성공적으로 수정되었습니다.</span>',
                confirmButtonColor: '#skyblue',
                confirmButtonText: '<span style="font-size:18px;">확인</span>'
            })
            const tmp = {
              userId: loginUser.userId,
              email: loginUser.email,
              nickname: form.nickname,
              gender: loginUser.gender,
              age: loginUser.age,
              score: loginUser.score,
              profile: form.profile,
              hashtag: loginUser.hashtag,
              token: loginUser.token,
              hashScore: loginUser.hashScore,
              worldcupScore: loginUser.worldcupScore
            }
            store.commit('module/setLoginUser', tmp)
        })
      router.push('/my')
    }

    return {
      form,
      score,
      checkName,
      checkPassWord,
      checkEmail,
      checkPassWordConfirmation,
      moveMy,
      modify
    }
  }
}
</script>

<style lang="scss" scoped>
@import "../css/app.scss";
.modify_container{
  display:flex;
}
.modify_wrap{
  display:flex;
  flex-direction: column;
  width:650px;
  height:648px;
  justify-content: center;
  align-items: center;
  margin-top:40px;
}
.back_btn{
  cursor: pointer;
}
.modify_top{
  position:relative;
  top:-25px;
  left:-290px;
}
.text-group{
  display: flex;
  justify-content: center;
}
.form-group{
  width:400px;
  height:600px;
}
.form-mb{
   margin-top:-5px;
}
.input{
  font-size:25px;
}
.submit_bt{
  display: flex;
  justify-content: center;
  margin-top:20px;
}
.submit{
  width:250px;
  height:50px;
  border-radius: 15px;
  font-weight:bold;
  font-size:18px;
}
.change_img{
  margin:20px 0px 10px 155px;
  width:100px;
  height: 100px;
  border-radius: 50%;
}
.signupImg{
  float:right;
  width:650px;
  height:650px;
  background-color : lightblue;
  border-top-right-radius: 38px;
  border-bottom-right-radius: 38px;
  background-image: url('assets/images/book3.png');
  background-size:700px;
}
</style>