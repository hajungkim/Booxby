<template>
    <div class="hashtag_container">
        <div class="hashtag">
            <div class="hashtag_form">
                <div>
                    <q-img src="~assets/images/problem.png" class="character"/>
                    <div class="hashtag_text">
                        나를 설명할 수 있는<br>
                        태그를 선택해보세요 !
                    </div>
                </div>
                <div class="hashtag_form_main" >
                    <q-btn :id="idx" @click="selectHashtag(hashtags,idx)" v-for="(hashtags,idx) in hashtag_list" :key="idx" class="tag notselect" :label="hashtags.hashtag"/>
                </div>
                <q-btn @click="goWorldcup" class="nextBt main_color" label="Next" v-if="hashflag"/>
                <q-btn @click="goMy" class="nextBt main_color" label="변경하기" v-else/>
            </div>
        </div>
    </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { onMounted } from 'vue'
import { login } from 'src/store/module/actions'
export default {
    setup() {
        let hashflag = true
        const store = useStore()
        const router = useRouter()
        const hashtag_list = [
            { hashtag:'#공격적', score:-177 },
            { hashtag:'#대담한', score:-133 },
            { hashtag:'#고집센', score:-103 },
            { hashtag:'#낙천적', score:-22 },
            { hashtag:'#성실한', score:305 },
            { hashtag:'#용감한', score:6 },
            { hashtag:'#조심스러운', score:37 },
            { hashtag:'#호기심많은', score:123 },
            { hashtag:'#열혈', score:30 },
            { hashtag:'#온화한', score:4 },
            { hashtag:'#활발한', score:360 },
            { hashtag:'#신중한', score:-27 },
            { hashtag:'#표현력있는', score:663 },
            { hashtag:'#인심좋은', score:11 },
            { hashtag:'#분석적인', score:-242 },
            { hashtag:'#체계적인', score:137 },
            { hashtag:'#친절한', score:307 },
            { hashtag:'#참신한', score:-95 },
            { hashtag:'#느긋한', score:-9 },
            { hashtag:'#절제된', score:-17 },
            { hashtag:'#포용력', score:57 },
            { hashtag:'#이해심많은', score:-16 },
            { hashtag:'#영향력', score:383 },
            { hashtag:'#완벽주의', score:284 },
            { hashtag:'#희생적인', score:-150 },
            { hashtag:'#변덕스러운', score:-9 },
            { hashtag:'#차분한', score:10 },
            { hashtag:'#태평한', score:60 },
            { hashtag:'#이쁜', score:452 },
            { hashtag:'#잘생긴', score:430 },
            { hashtag:'#개방적인', score:-45 },
            { hashtag:'#귀요미', score:13 },
            { hashtag:'#귀찮은', score:-123 },
            { hashtag:'#주도적인', score:236 },
            { hashtag:'#까다로운', score:-20 },
        ]
        let select_hashtag = []
        let hashscore = 0
        if (localStorage.getItem('userId') != null){
            hashflag = false
        }
        function selectHashtag(hashtags,idx){
            const hashbtn = document.getElementById(idx)
            if (hashbtn.classList.contains('select')){
                hashbtn.classList.remove('select')
                hashbtn.classList.add('notselect')
                var index = select_hashtag.indexOf(hashtags.hashtag)
                select_hashtag.splice(index,1)
                hashscore = hashscore - hashtags.score
            }
            else {
                hashbtn.classList.remove('notselect')
                hashbtn.classList.add('select')
                select_hashtag.push(hashtags.hashtag)
                hashscore = hashscore + hashtags.score
            }
        }
        function goWorldcup() {
            store.commit('module/setHashtag_list',select_hashtag)
            store.commit('module/setHashscore',hashscore)
            router.push('worldCup')
        }
        function goMy() {
            // store에 저장
            const loginUser = store.getters['module/getLoginUser']
            let hashString = ''
            select_hashtag.forEach(e=>{
                hashString = hashString + e
            })
            store.dispatch('module/modifyInfo', {
                    hashtag : hashString, 
                    nickname : loginUser.nickname, 
                    password: loginUser.password, 
                    worldcupScore: loginUser.worldcupScore, 
                    hashScore: hashscore 
                    })
                .then(()=>{
                    store.commit('module/setLoginUser', {
                        age:loginUser.age,
                        email:loginUser.email,
                        gender:loginUser.gender,
                        hashScore:hashscore,
                        hashtag:hashString,
                        nickname:loginUser.nickname,
                        password:loginUser.password,
                        token:loginUser.token,
                        userId:loginUser.userId,
                        worldcupScore:loginUser.worldcupScore,
                    })
                    store.commit('module/setHashtags',hashString)
                    store.commit('module/setHashscore',hashscore)
                    router.push('my')
                })
                .catch((err)=>{
                    console.log(err)
                })
        }
        onMounted(()=>{
            store.dispatch('module/oxbooks').then(function (res) {
                store.commit('module/setOxbooks',res.data)
        })  
        })
        return {
            hashtag_list,
            hashflag,
            goWorldcup,
            goMy,
            selectHashtag
        }
    }
}
</script>

<style scoped>
.main_color{
  background-color: rgb(86,86,239);
  color : white;
}
.hashtag_container{
    width:1300px;
    height:650px;
    border-radius: 38px;
}
.hashtag{
    display:flex;
    justify-content: center;
    align-items: center;
    width:1298px;
    height:648px;
    border-radius: 38px;
    background-color: rgb(241, 237, 237);
}
.hashtag_form{
    width:1000px;
    height:600px;
    display:flex;
    flex-direction: column;
}
.character{
    margin-left:75px;
    width:150px;
}
.hashtag_text{
    display:inline-block;
    font-size:23px;
    position:relative;
    top:40px;
    left:20px;
}
.hashtag_form_main{
    height:350px;
    background-color:white;
    border-radius: 38px;
    padding:15px 15px;
}
.tag{
    font-weight:bold;
    border:1px solid #1976D2;
    font-size:20px;
    margin-left:15px;
    margin-bottom: 15px;
    border-radius: 15px;
}

.select{
    background-color: rgb(86,86,239);
    color:white;
}

.notselect{
    color:rgb(86,86,239);
    background-color: white;
}

.nextBt{
    width:250px;
    height:30px;
    font-size:20px;
    font-weight:bold;
    border-radius: 25px;
    margin:auto;
}
</style>