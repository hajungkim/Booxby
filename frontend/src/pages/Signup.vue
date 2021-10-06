<template>
  <div class="signup_container">
    <div class="signup_wrap">
      <div class="text-group" style="position:relative; top:20px;">
        <h4 class="m-0">회원가입</h4>
      </div>
      <div class="form-group">
        <div class="form-input-email" style="position:relative; top:20px;">
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
            <q-btn @click="duplicateEmail" rounded class="mail_button main_color" label="중복 확인" style="width:88px;"/>
        </div>

        <div class="form-mb">
          <q-input class="input np" label="Password" color="teal" v-model="form.password" type="password" style="width:400px;"
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
          <q-input class="input" label="Nickname"
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
           <q-select v-model="model" :options="options" label="Age" />
        </div>
        <!-- 버튼 -->
        <div class="submit_bt">
          <q-btn @click="setInfos" class="submit main_color" label="다음 단계로" />
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
    const Swal = require('sweetalert2')

    const form = reactive({
      email: '',
      password: '',
      passwordconfirmation: '',
      nickname: '',
      male: 0,
      female: 1,
    })

    // 빈칸체크용 변수
    let valid = {
      email: false,
      password: false,
      passwordconfirmation: false,
      nickname: false,
    }
    const color = ref(0)
    const model = ref('나이대를 선택해주세요')

    function checkEmail (val) {
      const reg = /^[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
      if (reg.test(val)==true) valid.email = true
      else valid.email = false
      return (reg.test(val)||regExp.test(val)||'이메일 형식이 잘못되었습니다.')
    }
    function duplicateEmail() {
      if(form.email==''){
        Swal.fire({
              icon: 'error',
              title: '<span style="font-size:25px;">이메일을 입력해주세요.</span>',
              confirmButtonColor: '#ce1919',
              confirmButtonText: '<span style="font-size:18px;">확인</span>'
        })
        return 
      }
      store.dispatch('module/checkEmail', { email: form.email })
      .then((res) => {
        if (res.data.data==true){
          Swal.fire({
              icon: 'success',
              title: '<span style="font-size:25px;">사용 가능한 이메일 입니다.</span>',
              confirmButtonColor: "rgb(86,86,239)",
              confirmButtonText: '<span style="font-size:18px;">확인</span>'
          })
        }
        else{
          Swal.fire({
              icon: 'error',
              title: '<span style="font-size:25px;">중복된 이메일 입니다.</span>',
              confirmButtonColor: '#ce1919',
              confirmButtonText: '<span style="font-size:18px;">확인</span>'
          })
        }
      })
    }
    function checkPassWord (val) {
      const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/
      if (reg.test(val)==true) valid.password = true
      else valid.password = false
      return (reg.test(val)||'최소 각 하나의 문자, 숫자, 특수 문자가 포함된 8자리 이상이어야 합니다.')
    }
    function checkPassWordConfirmation (val) {
      if (form.password != val){
        valid.passwordconfirmation = false
        return ('비밀번호가 일치하지 않습니다.')
      }
      else{
        valid.passwordconfirmation = true
      }
    }
    // 이메일 유효성 체크
    function checkEmail (val) {
      const reg = /^[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
      if (reg.test(val)==true) valid.email = true
      else valid.email = false
      return (reg.test(val)||'이메일 형식이 잘못되었습니다.')
    }
    
    function checkName (val) {
      const reg = /[ㄱ-ㅎㅏ-ㅣ가-힣]/g
      if (reg.test(val)==true) valid.nickname = true
      else valid.nickname = false
      return (reg.test(val)||'한글만 입력가능합니다.')
    }
    function moveHashtag(){
      router.push('/hashtag')
    }

    function setInfos() {
      if (valid.email == false || valid.password == false || valid.passwordconfirmation == false || valid.nickname == false
        || model.value == '나이대를 선택해주세요' || typeof(color.value) == 'number'){
        Swal.fire({
          icon: 'error',
          title: '<span style="font-size:25px;">항목들을 모두 입력해주세요.</span>',
          confirmButtonColor: '#ce1919',
          confirmButtonText: '<span style="font-size:18px;">확인</span>'
        })
        return
      }
      const info = {
        age: model.value,
        email: form.email,
        gender: parseInt(color.value),
        nickname: form.nickname,
        password: form.password,
        profilePath: 'https://isobarscience.com/wp-content/uploads/2020/09/default-profile-picture1.jpg'
      }
      store.commit('module/setInfos', info)
      moveHashtag()
    }
    return {
      model,
      options: [
        '유아', '초등학생', '청소년', '10대', '20대', '30대', '40대' ,'50대', '60대 이상'
      ],
      color,
      form,
      checkName,
      checkPassWord,
      checkEmail,
      checkPassWordConfirmation,
      setInfos,
      moveHashtag,
      duplicateEmail,
      valid
    }
  }
}
</script>

<style lang="scss" scoped>
@import "../css/app.scss";
.np{
  padding-top:0px !important;
}
.main_color{
  background-color: rgb(86,86,239);
  color : white;
}
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
  margin-top:10px;
}
.form-input-password{
  width:400px;
  margin-top:20px;
}
.input{
  font-size:25px;
}
.mail_button{
  position:relative;
  top:-45px;
  float:right;
  margin: -19px 0px 0px;
  left:0px;
}
.signupImg{
  float:right;
  width:650px;
  height:650px;
  border-top-right-radius: 38px;
  border-bottom-right-radius: 38px;
  background-image: url('assets/images/book.jpg');
  background-size:1000px;
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
  font-size:17px;
  font-weight:bold;
}
</style>