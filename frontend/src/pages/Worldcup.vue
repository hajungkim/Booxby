<template>
    <div class="worldcup_container">
        <div class="worldcup">
            <div class="worldcup_main">
                <Oxbook :abc="oxbooks[i]"></Oxbook>
                <q-card-actions style="margin: 10px 0px 0px 70px;">
                    <q-icon @click="signUp" class="btn" style="font-size: 4.0em; color: red; margin-left:50px;" name="close"/>
                    <q-icon class="btn" style="font-size: 4.0em; color: orange;" name="change_history"/>
                    <q-icon @click="next" class="btn" style="font-size: 4.0em; color: green;" name="done"/>
                </q-card-actions>
            </div>
        </div>
    </div>
</template>

<script>
import Oxbook from '../components/Oxbook.vue'
import { useStore } from 'vuex'
// import { onMounted } from 'vue'
export default {
    components: {
        Oxbook,
    },
    setup() {
        const store = useStore()
        const infos = store.getters['module/getInfos']
        const hashtags_list = (store.getters['module/getHashtags'])
        const oxbooks = store.getters['module/getOxbooks']
        let i = 0
        let hashtag = ''
        hashtags_list.forEach(e => {
            hashtag = hashtag + e
        });
        function next(){
        i = i + 1
        console.log(i,oxbooks[i],'oxbook')
        }
        function signUp(){
            store.dispatch('module/signup',{
                age: infos.age,
                email: infos.email,
                emotionScore:infos.emotionScore,
                gender: infos.gender,
                hashtag: hashtag,
                nickname: infos.nickname,
                password: infos.password,
            }).then((res)=>{
                console.log(res,'res')
            }).catch((err)=>{
                console.log(err,'err')
            })
        }
        return{
            oxbooks,
            i,
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