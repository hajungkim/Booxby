<template>
    <div class="worldcup_container">
        <div class="worldcup">
            <div class="worldcup_main">
                <Oxbook :abc="select_books"></Oxbook>
                <q-card-actions style="margin: 10px 0px 0px 75px;">
                    <q-icon @click="Bad" class="btn" style="font-size: 4.0em; color: red; margin-left:50px;" name="close"/>
                    <q-icon @click="next" class="btn" style="font-size: 4.0em; color: orange;" name="change_history"/>
                    <q-icon @click="Like" class="btn" style="font-size: 4.0em; color: green;" name="done"/>
                </q-card-actions>
            </div>
        </div>
    </div>
</template>

<script>
import Oxbook from '../components/Oxbook.vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
// import { onMounted } from 'vue'
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
                console.log(res.data.status,'res')
                if (res.data.status == 'success'){
                    Swal.fire({
                        icon: 'success',
                        title: '<span style="font-size:25px;">회원 가입이 완료 되었습니다.</span>',
                        confirmButtonColor: '#ce1919',
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
                router.push('/')
            }).catch((err)=>{
                console.log(err,'err')
            })
            
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
.worldcup_container{
    width:1300px;
    height:650px;
    border-radius: 38px;
}
.worldcup{
    display:flex;
    justify-content: center;
    align-items: center;
    width:1298px;
    height:648px;
    border-radius: 38px;
    background-color: rgb(241, 237, 237);
}
.worldcup_main{
    width:550px;
    height:650px;
}
.btn{
    width:65px;
    height:65px;
    border:1px solid rgb(219, 219, 219);
    border-radius: 75px;
    margin-left:30px;
    background-color:white;
    box-shadow:black 3px 3px 3px;
    cursor: pointer;
    position:relative;
    top:-2px;
}
.btn:hover{
    background-color: rgb(199, 199, 199);
}
</style>