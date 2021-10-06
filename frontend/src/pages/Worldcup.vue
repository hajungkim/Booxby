<template>
    <div class="worldcup_container">
        <div class="explain_ox">
            <div style="margin-top:20px;">
                <h2 style="margin-bottom:30px;">선호 책 조사</h2>
                <div style="font-size:20px;">1. 관심가는 책이라면<img class="text_img" src="~assets/check.png">를 눌러주세요</div>
                <div style="font-size:20px;">2. 그저 그렇다면 <img class="text_img" src="~assets/warning.png">를 눌러주세요</div>
                <div style="font-size:20px;">3. 별로 읽고 싶지 않다면 <img class="text_img" src="~assets/x_real.png">를 눌러주세요</div>
            </div>
        <div style="font-size:15px; margin-top:100px;">※마우스를 책에 갖다 대면 책 설명을 볼 수 있어요</div>
        </div>
        <div class="worldcup">
            <div class="worldcup_main">
                <Oxbook :abc="select_books"></Oxbook>
                <q-card-actions style="margin: 30px 0px 0px 155px;">
                    <div class="button_box">
                        <a @click="Bad" class="button bgred bt1" style="margin-right:30px;"><span class="button-text">싫어요</span> <i class="fa fa-times" aria-hidden="true"></i></a>
                        <a @click="next" class="button bgorange bt2" style="margin-right:30px;"><span class="button-text">몰라요</span> <i class="fa fa-exclamation" aria-hidden="true"></i></a>
                        <a @click="Like" class="button bggreen bt3"><span class="button-text">좋아요</span> <i class="fa fa-check" aria-hidden="true"></i></a>
                    </div>
                </q-card-actions>
            </div>
        </div>
    </div>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</template>

<script>
import Oxbook from '../components/Oxbook.vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { login } from 'src/store/module/actions'
export default {
    components: {
        Oxbook,
    },
    setup() {
        const store = useStore()
        const router = useRouter()
        const Swal = require('sweetalert2')

        const infos = store.getters['module/getInfos']
        const hashtags_list = (store.getters['module/getHashtags'])
        const oxbooks = store.getters['module/getOxbooks']

        let final_score = 0


        let select_books = {}
        let i = 0
        select_books = oxbooks[i]
        store.commit('module/setselectOxbooks', oxbooks[i])
        
        function next(){
            i = i + 1
            if (i==7){
                final_score = final_score / 7
                final_score = Math.round(final_score)
                signUp()
                return
            }
            select_books = oxbooks[i]
            store.commit('module/setselectOxbooks', oxbooks[i])
        }

        function Bad(){
            final_score = final_score - parseInt(select_books.emotion_score)
            next()
        }
        function Like(){
            final_score = final_score + parseInt(select_books.emotion_score)
            next()
        }
        function signUp(){
            let hashtag = ''
            hashtags_list.forEach(e => {
                hashtag = hashtag + e
            });
            if (!localStorage.getItem('userId')){
                store.dispatch('module/signup',{
                    age: infos.age,
                    email: infos.email,
                    hashScore:infos.hashScore,
                    worldcupScore:final_score,
                    gender: infos.gender,
                    hashtag: hashtag,
                    nickname: infos.nickname,
                    password: infos.password,
                }).then((res)=>{
                    if (res.data.status == 'success'){
                        Swal.fire({
                            icon: 'success',
                            title: '<span style="font-size:25px;">회원 가입이 완료 되었습니다.</span>',
                            confirmButtonColor: 'rgb(86,86,239)',
                            confirmButtonText: '<span style="font-size:18px;">확인</span>'
                        })
                    }
                    else{
                        Swal.fire({
                            icon: 'error',
                            title: '<span style="font-size:25px;">오류발생 회원가입을 다시 시도해 주세요.</span>',
                            confirmButtonColor: '#ce1919',
                            confirmButtonText: '<span style="font-size:18px;">확인</span>'
                        })
                    }
                    router.push('/login')
                }).catch((err)=>{
                    console.log(err,'err')
                })
            }
            else{
                const loginUser = store.getters['module/getLoginUser']
                store.dispatch('module/modifyInfo', {
                    hashtag : loginUser.hashtag, 
                    nickname : loginUser.nickname, 
                    password: loginUser.password, 
                    worldcupScore: final_score, 
                    hashScore: loginUser.hashScore 
                    })
                .then(()=>{
                    store.commit('module/setLoginUser', {
                        age:loginUser.age,
                        email:loginUser.email,
                        gender:loginUser.gender,
                        hashScore:loginUser.hashScore,
                        hashtag:loginUser.hashtag,
                        nickname:loginUser.nickname,
                        password:loginUser.password,
                        token:loginUser.token,
                        userId:loginUser.userId,
                        worldcupScore:final_score,
                    })
                    Swal.fire({
                            icon: 'success',
                            title: '<span style="font-size:25px;">책 선호도 점수가 재설정 되었습니다.</span>',
                            confirmButtonColor: 'rgb(86,86,239)',
                            confirmButtonText: '<span style="font-size:18px;">확인</span>'
                        })
                    router.push('my')
                })
                .catch((err)=>{
                    console.log(err)
                })
            }         
        }
        return{
            select_books,
            i,
            Bad,
            Like,
            signUp,
            next
        }
    }
}
</script>

<style scoped>
.text_img{
    position: relative;
    width:60px;
    height:60px;
    top:20px;
}
.button_box{
    border:1px solid red;
    position:relative;
}
.button{
  text-decoration: none;
  color:white;
  display:inline-block;
  padding:30px 0;
  border-radius:50px;
  width: 60px;
  cursor: pointer;
}
.bt1{
    position:absolute;
    left:-15px;
}
.bt2{
    position:absolute;
    left:90px;
}
.bt3{
    position:absolute;
    left:195px;
}
.bgred{
    background-color:red;
}
.bgorange{
    background-color:orange;
}
.bggreen{
    background-color:green;
}
.button:hover{
  animation:expandWidth 0.5s;
  animation-fill-mode: forwards;
}
.button .button-text{
  visibility:hidden;
  position:absolute;
  left:10%;
  top:50%;
  transform:translate(0,-50%);
  font-size:18px;
}
.button:hover .button-text{
  animation: showText 0.3s;
  animation-fill-mode: forwards;
}
.button .fa{
  position:absolute;
  font-size:18px;
  top:50%;
  right:50%;
  transform: translate(50%,-50%);
}
.button:hover .fa{
  animation:translateArrow 0.5s;
  animation-fill-mode: forwards;
}

.left{
    animation:translateleftArrow 0.5s;
    animation-fill-mode: both;
}

@keyframes expandWidth {
  0% { width: 70px; }
  80% {
    transform:scale(0.8,0.8) translate3d(0,0,0);
  }
  99% {
    transform:scale(1.2,1.2) translate3d(0,0,0);
  }
  100% { 
    width: 90px; 
    transform:scale(1,1) translate3d(0,0,0);
  }
}
@keyframes showText {
  0% { visibility:hidden; }
  50% { visibility:hidden; }
  100% { visibility:visible; }
}
@keyframes translateArrow {
  0% { right:50%; }
  100% { right:20%; }
}

@keyframes translateleftArrow {
  0% { left:50%; }
  100% { left:20%; }
}

.worldcup_container{
    width:1300px;
    height:650px;
    border-radius: 38px;
}
.explain_ox{
    display:flex;
    flex-direction: column;
    float:left;
    justify-content: center;
    align-items: center;
    width:650px;
    height:650px;
}
.worldcup{
    display:flex;
    float:right;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width:650px;
    height:648px;
    border-radius: 38px;
    background-color: rgb(241, 237, 237);
}
.worldcup_main{
    width:550px;
    height:650px;
}
</style>