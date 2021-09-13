<template>
    <div class="worldcup_container">
        <div class="worldcup">
            <div class="worldcup_main">
                <q-card class="my-card" id="card">
                    <q-img src="~assets/images/html.jpg" class="worldcup_img">
                        <div class="absolute-bottom" v-if="textMode">
                            <div class="text-h6" style="font-weight:bold;">HTML5 + CSS3 바이블</div>
                            <div class="text-subtitle2" style="font-weight:bold;">이 책은 HTML + CSS 책입니다 이 책은 HTML + CSS 책입니다 이 책은 HTML + CSS 책입니다 이 책은 HTML + CSS 책입니다 이 책은 HTML + CSS 책입니다 이 책은 HTML + CSS 책입니다</div>
                        </div>
                    </q-img>
                    <q-card-actions>
                        <q-icon class="btn" style="font-size: 4.0em; color: red; margin-left:50px;" name="close"/>
                        <q-icon class="btn" style="font-size: 4.0em; color: orange;" name="change_history"/>
                        <q-icon class="btn" style="font-size: 4.0em; color: green;" name="done"/>
                    </q-card-actions>
                </q-card>
            </div>
        </div>
    </div>
</template>

<script>
import { onMounted, computed } from 'vue'
import { useStore } from 'vuex'

export default {
    setup() {
        // store 사용을 위한 변수 선언
        const store = useStore()
        const textMode = computed(() => store.getters['module/getTextMode'])    // 설명 박스

        onMounted(() => {
            const card = document.getElementById('card')
            console.log(card)
            card.addEventListener('mouseover', function () {
                store.commit('module/setTextMode', true)
            }, true)
            card.addEventListener('mouseout', function () {
                store.commit('module/setTextMode', false)
            }, true)
        })

        return {
            textMode
        }
    }
}
</script>

<style>
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
.my-card{
    margin-top:30px;
    margin-left:80px;
    width:375px;
}
.btn{
    width:65px;
    height:65px;
    border:1px solid rgb(219, 219, 219);
    border-radius: 75px;
    margin-left:30px;
    background-color:rgb(219, 219, 219);
    box-shadow:black 3px 3px 3px;
    cursor: pointer;
    position:relative;
    top:-2px;
}
.btn:hover{
    background-color: rgb(199, 199, 199);
}
</style>