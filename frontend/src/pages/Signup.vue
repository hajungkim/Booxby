<template>
  <div class="signup_container">
    <div class="signup_wrap">
      <div class="text-group">
        <h4 class="m-0">회원가입</h4>
      </div>
      <div class="form-group">
        <div class="form-input-email">
          <q-input
              class="input"
              label="Email"
              type="email"
              v-model="form.email"
              lazy-rules
              :rules="[
              val => !!val || '필수입력항목 입니다.',
              checkEmail
              ]"
            />
          <div>
            <q-btn rounded size="xs" class="mail_button" color="primary" label="중복 확인" />
          </div>
        </div>

        <div class="form-mb" style="margin-top:2px;">
          <q-input class="input" label="Password" color="teal" v-model="form.password" type="password"
            lazy-rules
              :rules="[
                val => val && val.length > 8 || '8자리 이상 입력해주세요.',
                checkPassWord
              ]"/>
        </div>

        <div class="form-mb">
          <q-input label="Confirm Password" type="password" v-model="form.passwordconfirmation"
            lazy-rules
              :rules="[
                val => val && val.length > 0 || '필수입력항목 입니다.',
                checkPassWordConfirmation
              ]"/>
        </div>

        <div>
          <q-input label="닉네임"
            v-model="form.nickname"
            lazy-rules
              :rules="[
                val => val && val.length >= 2 || '두글자 이상 입력 바랍니다.',
                checkName
              ]"/>
        </div>
        <!-- 성별 -->
        <div style="margin-top:5px;">
          <q-radio style="margin-right:10px; margin-left:-10px;" keep-color v-model="color" val=0 label="남자" color="cyan" />
          <q-radio keep-color v-model="color" val=1 label="여자" color="red" />
        </div>
        <!-- 나이 -->
        <div style="margin-top:10px;">
           <q-select v-model="model" :options="options" label="연령대" />
        </div>
        <!-- 버튼 -->
        <div class="submit_bt">
          <q-btn @click="setInfos" class="submit" color="primary" label="다음 단계로" />
        </div>

      </div>
    </div>
    <div class="signupImg">
      <!-- <img src="" alt=""> -->
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
export default {
  setup(){
    const router = useRouter()

    const store = useStore()

    const form = reactive({
      email: '',
      confirmNum: 0,
      password: '',
      passwordconfirmation: '',
      nickname: '',
      male: 0,
      female: 1,
    })
    const color = ref(0)
    
    const model = ref('나이대를 선택해주세요')

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
    // function checkCertification (val) {
    //   store.dispatch('module/authcheck', {
    //     inputNum: val,
    //   }).then(function (){
    //     // 제대로된경우
    //   })
    // }
    function moveHashtag(){
      router.push('/hashtag')
    }
    function setInfos() {
      let info = {
        age: model.value,
        email: form.email,
        gender: parseInt(color.value),
        nickname: form.nickname,
        password: form.password,
      }
      store.commit('module/setInfos',info)
      moveHashtag()
    }
    return {
      model,
      options: [
        '유아', '초등학생', '청소년', '10대', '20대', '30대', '40대' ,'50대', '60대 이상'
      ],
      color,
      // state,
      form,
      checkName,
      checkPassWord,
      checkEmail,
      checkPassWordConfirmation,
      setInfos,
      moveHashtag
    }
  }
}
</script>

<style lang="scss" scoped>
@import "../css/app.scss";
.signup_container{
  display:flex;
}
.signup_wrap{
  display:flex;
  flex-direction: column;
  width:650px;
  height:648px;
  justify-content: center;
  align-items: center;
  margin-top:25px;
}
.text-group{
  display: flex;
  justify-content: center;
  margin-top:30px;
}
.form-group{
  width:400px;
  height:600px;
}
.form-mb{
   margin-top:-5px;
}
.form-input-email{
  width:400px;
  margin-top:20px;
}
.mail_button{
  float:right;
  margin: -19px 0px 0px;
}
.signupImg{
  float:right;
  width:650px;
  height:648px;
  background-color : lightblue;
  border-top-right-radius: 38px;
  border-bottom-right-radius: 38px;
}
.submit_bt{
  display: flex;
  justify-content: center;
  margin-top:30px;
}
.submit{
  width:250px;
  height:50px;
  border-radius: 15px;
}
</style>