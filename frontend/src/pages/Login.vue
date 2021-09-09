<template>
  <div class="login_container">
      <div class="img">
          <!-- <img src=""> -->
      </div>
      <div class="login">
          <div class="login_form">
              <h2 class="head">Login</h2>
              <q-input class="form" label="ID" type="email" 
              v-model="form.id"
              lazy-rules
                :rules="[
                val => !!val || '필수입력항목 입니다.',
                checkId
                ]"/>
              <q-input type="password" class="form" label="PASSWORD" v-model="form.password"
              lazy-rules
                :rules="[
                  val => val && val.length > 0 || '필수입력항목 입니다.',
                  checkPassWord
                ]"/>
              <q-btn class="bt1" flat style="color: rgb(71, 76, 80)" label="비밀번호 찾기" />
              <q-btn class="bt2" color="primary" label="로그인하기" />
              <q-btn @click="goSignUp" class="bt3" flat style="color: black;" label="회원가입하기!" />
          </div>
      </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'

export default {
    setup() {
        // 라우터 사용시 필요한 변수
        const router = useRouter()

        // v-model을 위해 필요한 형태
        const form = reactive({
            id: '',
            password: ''
        })
        // 아이디 유효성 검사 
        function checkId (val) {
            const reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
            return (reg.test(val) || '이메일 형식이 잘못되었습니다.')
        }
        // 비밀번호 유효성 검사
        function checkPassWord (val) {
            const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,}$/
            return (reg.test(val) || '최소 각 하나의 문자, 숫자, 특수 문자가 포함되어야 합니다.')
        }

        function goSignUp() {
            router.push('/signUp')
        }

        return {
            form,
            checkId,
            checkPassWord,
            goSignUp
        }
    }
}
</script>

<style>
.login_container{
    width:1600px;
    height:800px;
}
.img{
    width:800px;
    height:800px;
    background-color:skyblue;
    float:left;
    border-top-left-radius: 38px;
    border-bottom-left-radius: 38px;
}
.login{
    display:flex;
    justify-content: center;
    align-items: center;
    width:800px;
    height:800px;
    float:right;
    border-top-right-radius: 38px;
    border-bottom-right-radius: 38px;
}
.login_form{
    padding-top:100px;
    width:500px;
    height:700px;
    /* border:1px solid red; */
}
.head{
    margin-left:180px;
    font-weight:bold;
}
.form{
    width:100%;
    margin-top:40px;
    font-size:25px;
}
.bt1{
    width:150px;
    color:rgb(71, 76, 80);
    font-weight:bold;
    font-size:18px;
    float:right;
}
.bt2{
    border-radius: 15px;
    width:300px;
    height:40px;
    font-size:18px;
    margin: 20px 100px;
}
.bt3{
    border-radius: 15px;
    width:300px;
    height:40px;
    font-size:18px;
    margin: 0 100px;
    margin-top:120px;
    font-weight:bold;
}
</style>