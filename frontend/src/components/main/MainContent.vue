<template>
  <div class="main_box">
      <div class="main_search">
          <q-select outlined v-model="model" :options="options" style="width:117px; height:10px; margin-right:5px;"/>
          <q-input v-model="form.keyword" class="main_search_input" outlined label="검색" @keyup.enter="onInput"/>
          <q-btn @click="onInput" round class="main_search_btn" color="primary" icon="search" />
      </div>
      <div class="main_content">
          <div class="main_content_view">
              <div class="main_content_left">
                <div class="book_name">{{selectBook.title}}</div>
                <div class="book_author">{{selectBook.author}}</div>
                <q-btn @click="goDetail" class="book_detail_btn" color="primary" label="자세히 보기" />
              </div>
              <div class="main_content_right">
                  <div class="main_circle">
                  </div>
                  <q-img :src="selectBook.img_url" class="main_book"/>
              </div>
          </div>
          <div class="main_content_list">
              <!-- <q-icon class="list_btn" style="font-size: 2.8em; color: grey; margin-right:20px;" name="navigate_before"/> -->
              <q-img v-for="(book, index) in bookList" :src="book.img_url" :key="index" @click="select(index)" class="list_book" />
              <!-- <q-icon class="list_btn" style="font-size: 2.8em; color: grey;" name="navigate_next"/> -->
          </div>
      </div>
  </div>
</template>

<script>
import { computed, onMounted, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
export default {
    setup () {
        const store = useStore()
        const router = useRouter()
        const Swal = require('sweetalert2')

        const bookList = computed(() => store.getters['module/getBookList'])
        const selectBook = computed(() => store.getters['module/getSelectBook'])
        const zzimList = computed(() => store.getters['module/getZzimList'])

        const form = reactive({
            keyword : ''
        })
        const model = ref('책 검색')
        const options = [
                '책 검색', '작가검색'
        ]
        function onInput(){
            console.log(form.keyword,'@@')
            console.log(model.value,'model')
            if (model.value == '책 검색'){
                store.dispatch('module/getSearchbook',form.keyword)
                .then((result)=>{
                    if (result.data == null){
                        Swal.fire({
                            icon: 'error',
                            title: '<span style="font-size:25px;">검색한 내용이 없습니다.</span>',
                            confirmButtonColor: '#ce1919',
                            confirmButtonText: '<span style="font-size:18px;">확인</span>'
                        })
                    }
                    else{
                        store.commit('module/setBookList', result.data)
                        store.commit('module/setSelectBook', result.data[0])
                    }
                })
            }
            else if (model.value == '작가검색'){
                store.dispatch('module/getWriter',form.keyword)
                .then((result)=>{
                    if (result.data == null){
                        Swal.fire({
                            icon: 'error',
                            title: '<span style="font-size:25px;">검색한 내용이 없습니다.</span>',
                            confirmButtonColor: '#ce1919',
                            confirmButtonText: '<span style="font-size:18px;">확인</span>'
                        })
                    }
                    else{
                        store.commit('module/setBookList', result.data)
                        store.commit('module/setSelectBook', result.data[0])
                    }
                })
            }
        }

        const goDetail = function() {
            // store.dispatch('module/getwords',selectBook.value.isbn13).then((res)=>{
            //     let tmp = []
            //     for (let i = 0; i < res.data.length; i++) {
            //         let j = {
            //             "name": '',
            //             "value": ''
            //         }
            //         j.name = res.data[i][0]
            //         j.value = res.data[i][1]
            //         tmp.push(j)
            //     }
            //     store.commit('module/setwords', tmp)
               
            // }).catch((err)=>{
            //     console.log(err)
            // })
            router.push('/detail')
        }
        const select = function(index) {
            const list = store.getters['module/getBookList']
            store.commit('module/setSelectBook', list[index])
        }
        onMounted(() => {
            store.dispatch('module/emojiRecommend')
                .then(function (result) {
                    store.commit('module/setBookList', result.data)
                    store.commit('module/setSelectBook', result.data[0])
                })
            const userId = localStorage.getItem('userId')
            store.dispatch('module/requestzzim', userId)
                .then(function (result) {
                    store.commit('module/setZzimList', result.data.data)
                })
        })

        return {
            model,
            options,
            bookList,
            selectBook,
            form,
            goDetail,
            select,
            onInput,
            zzimList
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
    display:flex;
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
    width:420px;
    font-size:25px;
    font-weight:bold;
}
.book_author{
    width:420px;
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
    height:280px;
    animation: wiggle 1.5s infinite;
}
.main_content_list{
    /* border:3px solid pink; */
    padding-top:25px;
    padding-left:20px;
    height:32%;
    width:100%;
    margin:0 auto;
}
.list_book{
    width:105px;
    height:135px;
    margin-right:20px;
    cursor:pointer;
    /* animation: wiggle 1s infinite; */
}
/*.list_book:nth-child(5){
    width:120px;
    height:170px;
    margin-right:20px;
     animation: wiggle 1s infinite; 
}*/
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