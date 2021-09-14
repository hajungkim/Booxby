<template>
  <div class="main_box">
      <div class="main_search">
          <q-input class="main_search_input" outlined  label="검색" />
          <q-btn round class="main_search_btn" color="primary" icon="search" />
      </div>
      <div class="main_content">
          <div class="main_content_view">
              <div class="main_content_left">
                <div class="book_name">모던웹을 위한 HTML+CSS 바이블</div>
                <div class="book_author">김싸피</div>
                <q-btn @click="goDetail" class="book_detail_btn" color="primary" label="자세히 보기" />
              </div>
              <div class="main_content_right">
                  <div class="main_circle">
                  </div>
                  <q-img src="~assets/images/html.jpg" class="main_book"/>
              </div>
          </div>
          <div class="main_content_list">
              <q-icon class="list_btn" style="font-size: 2.8em; color: grey; margin-right:20px;" name="navigate_before"/>
              <q-img src="~assets/images/html.jpg" class="list_book"/>
              <q-img src="~assets/images/html.jpg" class="list_book"/>
              <q-img src="~assets/images/html.jpg" class="list_book"/>
              <q-img src="~assets/images/html.jpg" class="list_book_choice"/>
              <q-img src="~assets/images/html.jpg" class="list_book"/>
              <q-img src="~assets/images/html.jpg" class="list_book"/>
              <q-img src="~assets/images/html.jpg" class="list_book"/>
              <q-icon class="list_btn" style="font-size: 2.8em; color: grey;" name="navigate_next"/>
          </div>
      </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  setup () {
    const store = useStore()
    const router = useRouter()

    const categoryMode = computed(() => store.getters['module/getCategoryMode'])
    const tmp = true

    const showCategory = function () {
      store.commit('module/setCategroyMode', !store.getters['module/getCategoryMode'])
    }
    const logout = function() {
      router.push('/')
    }
    const goDetail = function() {
      router.push('/detail')
    }
    return {
      tmp,
      categoryMode,
      showCategory,
      logout,
      goDetail
    }
  }
}
</script>

<style scoped>
.main_box{
    /* border:1px solid red; */
    width:900px;
    margin:0 auto;
}
.main_search{
    margin-top:25px;
    float:right;
}
.main_search_input{
    width:270px;
    display:inline-block;
}
.main_search_btn{
    margin-left:10px;
    margin-bottom:20px;
    height:54px;
    width:54px;
    font-size:18px;
}
.main_content{
    clear:both;
    /* border:1px solid green; */
    width:100%;
    height:532px;
}
.main_content_view{
    width:100%;
    height:68%;
    /* border:1px solid yellow; */
    position:relative;
}
.main_content_left{
    /* border:1px solid orange; */
    position:absolute;
    top:100px;
    left:100px;
    display:inline-block;
}
.book_name{
    font-size:25px;
    font-weight:bold;
}
.book_author{
    font-size:17px;
    margin-left:10px;
}
.book_detail_btn{
    width:200px;
    font-size:18px;
    font-weight:bold;
    border-radius: 10px;
    margin-top:20px;
    margin-left:10px;
}
.main_content_right{
    /* border:1px solid red; */
    display:inline-block;
    position:absolute;
    top:50px;
    left:460px;
    width:390px;
    height:300px;
}
.main_circle{
    /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#1e5799+0,207cca+20,2dc6ed+53,7db9e8+96,7db9e8+100 */
    background: #1e5799; /* Old browsers */
    background: -moz-linear-gradient(45deg,  #1e5799 0%, #207cca 20%, #2dc6ed 53%, #7db9e8 96%, #7db9e8 100%); /* FF3.6-15 */
    background: -webkit-linear-gradient(45deg,  #1e5799 0%,#207cca 20%,#2dc6ed 53%,#7db9e8 96%,#7db9e8 100%); /* Chrome10-25,Safari5.1-6 */
    background: linear-gradient(45deg,  #1e5799 0%,#207cca 20%,#2dc6ed 53%,#7db9e8 96%,#7db9e8 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
    filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#1e5799', endColorstr='#7db9e8',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
    border-radius: 200px;
    width:300px;
    height:300px;
    float:right;
    box-shadow: 3px 3px 3px;
}
.main_book{
    position:absolute;
    top:5px;
    left:110px;
    width:200px;
    animation: wiggle 1.5s infinite;
}
.main_content_list{
    /* border:3px solid pink; */
    height:32%;
    width:100%;
    margin:0 auto;
}
.list_book{
    width:90px;
    margin-right:20px;
    /* animation: wiggle 1s infinite; */
}
.list_book_choice{
    width:120px;
    margin-right:20px;
    /* animation: wiggle 1s infinite; */
}
.list_btn{
    cursor: pointer;
}
@keyframes wiggle {
    0% { transform: rotate(2deg); }
    10% { transform: rotate(-2deg); }
   20% { transform: rotate(2deg); }
   30% { transform: rotate(-2deg); }
   40% { transform: rotate(2deg); }
   50% { transform: rotate(-2deg); }
   60% { transform: rotate(2deg); }
   70% { transform: rotate(-2deg); }
   80% { transform: rotate(2deg); }
   90% { transform: rotate(-2deg); }
   100% { transform: rotate(2deg); }
}

</style>