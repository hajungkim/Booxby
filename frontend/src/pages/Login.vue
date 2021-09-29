<template>
  <div class="login_container">
      <div class="login_img">
      </div>
      <div class="login">
          <div class="login_form">
              <div class="login_logo_box">
                <q-img class="login_logo" src="~assets/images/logo.png"/>
              </div>
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
              <q-btn @click="pwdMode = true" class="findBt" flat style="color: rgb(71, 76, 80)" label="비밀번호 찾기" />
              <q-btn @click="login" class="loginBt" color="primary" label="로그인" />
              <q-btn @click="goSignUp" class="signBt" flat style="color: black;" label="회원가입하기!" />
              <!-- 비밀번호 찾기 모달 -->
              <q-dialog v-model="pwdMode">
                <q-card style="background: #E3E7EA">
                    <q-card-section class="row items-center">
                        <div class="find_form">
                            <div style="font-size:25px;">비밀번호 찾기</div>
                            <div style="font-size:18px; padding-left:10px; padding-top:10px;">이메일로 임시 비밀번호를 발급해드립니다.</div>
                            <q-input v-model="find.email" style="width:90%; margin-left:10px;" label="e-mail" />
                        </div>
                    </q-card-section>
                    <q-card-actions>
                        <div style="display:inline-block; margin:0 auto;">
                            <q-btn flat  @click="downPwd" class="find_btn" label="취소" color="black" v-close-popup />
                            <q-btn flat  @click="findPwd" class="find_btn" label="발송" color="black"/>
                        </div>
                    </q-card-actions>
                </q-card>
            </q-dialog>
          </div>
      </div>
  </div>
</template>

<script>
import { reactive, ref } from 'vue'
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
        const find = reactive({
            email: ''
        })
        const pwdMode = ref(false)
    
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
                                    worldcupScore: data.worldcupScore,
                                    hashScore: data.hashScore,
                                    profile: data.profilePath,
                                    hashtag: data.hashtag,
                                    token: token
                                }
                                // store에 저장
                                store.commit('module/setLoginUser', loginUser)
                                console.log('로그인정보', store.getters['module/getLoginUser'])
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

        // 모달 false
        const downPwd = function() {
            pwdMode.value = false
        }
        // 이메일 발송
        const findPwd = function() {
            store.dispatch('module/findPwd', { email: find.email })
                .then((res) => {
                    console.log(res)
                    Swal.fire({
                        icon: 'success',
                        title: '<span style="font-size:25px;">이메일을 발송했습니다.</span>',
                        confirmButtonColor: '#primary',
                        confirmButtonText: '<span style="font-size:18px;">확인</span>'
                    })
                    pwdMode.value = false
                })
        }
        return {
            form,
            prompt,
            checkId,
            checkPassWord,
            login,
            goSignUp,
            pwdMode,
            downPwd,
            findPwd,
            find
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
    /* background-color:skyblue; */
    float:left;
    border-top-left-radius: 38px;
    border-bottom-left-radius: 38px;
    background-image: url('assets/images/book2.jpg');
    background-size:1000px;
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
.login_logo_box{
    text-align: center;
}
.login_logo{
    width:70%;
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
.form{
    font-size:25px;
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
    margin-top:60px;
    font-weight:bold;
}
.find_form{
    width:370px;
    height:130px;
}
.find_btn{
    font-weight:bold;
    font-size:20px;
}
</style>