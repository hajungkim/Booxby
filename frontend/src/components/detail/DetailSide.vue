<template>
  <div class="main_side">
      <div class="logo">
        Booxby
      </div>
      <div class="side_content">
            <div>
                <q-icon class="bookmark" style="font-size: 3.7em; color: red;" name="bookmark"/>
            </div>
            <div class="detail_name">
                {{selectBook.title}}
            </div>
            <div class="detail_author">
                {{selectBook.author}}
            </div>
            <div class="detail_book">
                <q-img :src="selectBook.img_url" class="detail_img" />
            </div>
      </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  setup() {
    const store = useStore()

    const selectBook = computed(() => store.getters['module/getSelectBook'])

    return {
      selectBook
    }
  }
}
</script>

<style scoped>
.main_side{
  width:350px;
  height:650px;
  /* border:1px solid red; */
}
.logo{
  /* border:1px solid blue; */
  font-size:50px;
  font-weight:bold;
  padding-left:105px;
  padding-top:15px;
}
.side_content{
  width:90%;
  height:525px;
  background-color: white;
  position:relative;
  top:10px;
  left:30px;
  border-radius: 30px;
}
.bookmark{
  margin-left:30px;
}
.detail_name{
    font-size:20px;
    width:95%;
    margin:0 auto;
    /* margin-top:5px; */
    font-weight:bold;
    text-align:center;
}
.detail_author{
    width:95%;
    margin:0 auto;
    text-align:center;
    font-size:16px;
}
.detail_book{
    margin: 0 auto;
    margin-top:25px;
    width:250px;
    height:320px;
}
.detail_img{
    width:250px;
    height:320px;
}
</style>