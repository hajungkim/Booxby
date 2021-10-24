<template>
    <div class="main_container">
        <div class="main">
            <detail-side></detail-side>
            <detail-content></detail-content>
        </div>
    </div>
</template>

<script>
import DetailSide from '../components/detail/DetailSide.vue'
import DetailContent from '../components/detail/DetailContent.vue'

export default {
    components: {
        DetailSide,
        DetailContent
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