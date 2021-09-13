<template>
    <div class="main_container">
        <div class="main">
            <main-side></main-side>
            <main-content></main-content>
        </div>
    </div>
</template>

<script>
import MainSide from '../components/main/MainSide.vue'
import MainContent from '../components/main/MainContent.vue'

export default {
    components: {
        MainSide,
        MainContent
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
    background-color: rgb(241, 237, 237);
}
</style>