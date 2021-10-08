<template>
    <div class="main_container">
        <div class="main">
            <main-side></main-side>
            <main-content></main-content>
        </div>
    </div>
</template>

<script>
import { useRouter } from 'vue-router'
import MainSide from '../components/main/MainSide.vue'
import MainContent from '../components/main/MainContent.vue'

export default {
    components: {
        MainSide,
        MainContent
    },
    setup () {
        const router = useRouter()
        const Swal = require('sweetalert2')

        const loginCheck = localStorage.getItem('userId')
        
        // 로그인안된 상태
        if(loginCheck == null) {
            Swal.fire({
                        icon: 'error',
                        title: '<span style="font-size:25px;">로그인 후 이용해주세요.</span>',
                        confirmButtonColor: '#ce1919',
                        confirmButtonText: '<span style="font-size:18px;">확인</span>'
                    })
            router.push('/')
        }
    }
}
</script>

<style>
.main_container{
    width:1300px;
    height:650px;
    border-radius: 38px;
}
.main{
    display:flex;
    width:1298px;
    height:648px;
    border-radius: 38px;
    background-color: whitesmoke;
}
</style>