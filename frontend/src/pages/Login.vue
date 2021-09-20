<template>
  <div class="login_container">
      <div class="login_img">
      </div>
      <div class="login">
          <div class="login_form">
              <h2 class="head">Login</h2>
              <q-input v-on:keyup.enter="login" class="login_form_main" label="Email" type="email" 
              v-model="form.email"
              lazy-rules
                :rules="[
                val => !!val || '필수입력항목 입니다.',
                checkId
                ]"/>
              <q-input v-on:keyup.enter="login" type="password" class="form" label="PASSWORD" v-model="form.password"
              lazy-rules
                :rules="[
                  val => val && val.length > 0 || '필수입력항목 입니다.',
                  checkPassWord
                ]"/>
              <q-btn @click="prompt" class="findBt" flat style="color: rgb(71, 76, 80)" label="비밀번호 찾기" />
              <q-btn @click="login" class="loginBt" color="primary" label="로그인" />
              <q-btn @click="goSignUp" class="signBt" flat style="color: black;" label="회원가입하기!" />
          </div>
      </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { useQuasar } from 'quasar'

export default {
    setup() {
        // 라우터 사용시 필요한 변수
        const router = useRouter()
        // 스토어 사용시 필요한 변수
        const store = useStore()
        // sweetalert 2 사용시 필요한 변수
        const Swal = require('sweetalert2')

        // v-model을 위해 필요한 형태
        const form = reactive({
            email: '',
            password: '',
            findpw: '',
        })

        const $q = useQuasar()

        function  prompt () {
            $q.dialog({
            title: '비밀번호 찾기',
            message: '이메일로 임시 비밀번호를 발급해드립니다',
            prompt: {
                model: '',
                isValid: val => checkemail(val), // << here is the magic
                type: 'text' // optional
            },
            cancel: true,
            persistent: false,
            }).onOk(data => {
                console.log('>>>> OK, received', data)
                alert("이메일이 발송되었습니다.")
            })
        }
        // 아이디 유효성 검사 
        function checkId (val) {
            const reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
            return (reg.test(val) || '이메일 형식이 잘못되었습니다.')
        }
        function checkemail (val) {
            const reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
            return (reg.test(val) || false)
        }
        // 비밀번호 유효성 검사
        function checkPassWord (val) {
            const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,}$/
            return (reg.test(val) || '최소 각 하나의 문자, 숫자, 특수 문자가 포함되어야 합니다.')
        }
        // 로컬스토리지 삭제
        localStorage.clear()
        // 로그인
        function login() {
            store.dispatch('module/login', { email: form.email, password: form.password })
                .then(function (result) {
                    // 로그인 성공
                    if(result.data.data) {
                        // 로컬 스토리지 저장
                        const userId = result.data.data.userId
                        const token = result.data.data.token
                        localStorage.setItem('userId', userId)
                        localStorage.setItem('token', token)
                        
                        // 회원정보 가져오기
                        store.dispatch('module/requestInfo', userId)
                            .then(function (result) {
                                const data = result.data.data
                                const loginUser = {
                                    userId: userId,
                                    email: data.email,
                                    password: data.password,
                                    nickname: data.nickname,
                                    gender: data.gender,
                                    age: data.age,
                                    score: data.emotionScore,
                                    profile: data.profilePath,
                                    hashtag: data.hashtag,
                                    token: token
                                }
                                // store에 저장
                                store.commit('module/setLoginUser', loginUser)
                                router.push('/main')
                            })
                    }
                    // 로그인 실패
                    else {
                        Swal.fire({
                            icon: 'error',
                            title: '<span style="font-size:25px;">아이디 또는 비밀번호를 확인해주세요</span>',
                            confirmButtonColor: '#ce1919',
                            confirmButtonText: '<span style="font-size:18px;">확인</span>'
                        })
                    }
                })
                .catch(function(){
                })
        }
        // 회원가입 창 이동
        function goSignUp() {
            router.push('/signUp')
        }

        return {
            form,
            prompt,
            checkId,
            checkPassWord,
            login,
            goSignUp,
        }
    }
}
</script>

<style>
.login_container{
    width:1300px;
    height:650px;
}
.login_img{
    width:650px;
    height:650px;
    background-color:skyblue;
    float:left;
    border-top-left-radius: 38px;
    border-bottom-left-radius: 38px;
    /* background-image: url('assets/images/book.jpg');
    background-size:1200px; */
}
.login{
    display:flex;
    justify-content: center;
    align-items: center;
    width:650px;
    height:650px;
    float:right;
    border-top-right-radius: 38px;
    border-bottom-right-radius: 38px;
    background-color: rgb(241, 237, 237);
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
.login_form_main{
    width:100%;
    margin-top:40px;
    font-size:25px;
    font-weight:bold;
}
.findBt{
    width:150px;
    color:rgb(71, 76, 80);
    font-weight:bold;
    font-size:18px;
    float:right;
}
.loginBt{
    border-radius: 15px;
    width:300px;
    height:40px;
    font-size:20px;
    font-weight:bold;
    margin: 20px 100px;
    box-shadow:black 3px 3px 3px;
}
.signBt{
    border-radius: 15px;
    width:300px;
    height:40px;
    font-size:20px;
    margin: 0 100px;
    margin-top:70px;
    font-weight:bold;
}
</style>