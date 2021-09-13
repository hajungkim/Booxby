<template>
    <div class="hashtag_container">
        <div class="hashtag">
            <div class="hashtag_form">
                <q-img src="~assets/images/problem.png" class="character"/>
                <div class="hashtag_text">
                    나를 설명할 수 있는<br>
                    태그를 선택해보세요 !
                </div>
                <div class="hashtag_form_main">
                    <q-btn class="tag" outline color="primary" label="#감성적인" />
                    <q-btn class="tag" outline color="primary" label="#감성적인" />
                    <q-btn class="tag" outline color="primary" label="#감성적인" />
                    <q-btn class="tag" outline color="primary" label="#감성적인" />
                    <q-btn class="tag" outline color="primary" label="#감성적인" />
                    <q-btn class="tag" outline color="primary" label="#감성적인" />
                    <q-btn class="tag" outline color="primary" label="#감성적인" />
                    <q-btn class="tag" outline color="primary" label="#감성적인" />
                </div>
                <q-btn @click="goWorldcup" class="nextBt" color="primary" label="Next" />
            </div>
        </div>
    </div>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
    setup() {
        const router = useRouter()

        function goWorldcup() {
            router.push('worldCup')
        }

        return {
            goWorldcup
        }
    }
}
</script>

<style>
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
    font-size:20px;
    margin-left:15px;
    margin-bottom: 15px;
    border-radius: 15px;
}
.nextBt{
    width:250px;
    height:30px;
    font-size:20px;
    font-weight:bold;
    border-radius: 25px;
    margin-top:40px;
    margin-left:360px;
}
</style>