<template>
  <div class="main_box">
      <div class="main_search">
          <q-select outlined v-model="model" :options="options" style="width:117px; height:10px; margin-right:5px;"/>
          <q-input v-model="form.keyword" class="main_search_input" outlined label="검색" @keyup.enter="onInput"/>
          <span class="main_search_btn_span"><q-btn @click="onInput" round class="main_search_btn" style="background:rgb(86,86,239); color:white;" icon="search" /></span>
      </div>
      <div class="main_content">
          <div class="main_content_view">
              <div class="main_content_left">
                <div class="book_name">{{selectBook.title}}</div>
                <div class="book_author">{{selectBook.author}}</div>
                <q-btn @click="goDetail" class="book_detail_btn" style="background:rgb(86,86,239); color:white;" label="자세히 보기" />
              </div>
              <div class="main_content_right">
                  <div class="main_circle">
                  </div>
                  <div class="main_pen"> 
                    <q-img class="main_pen_photo" src="~/assets/images/pen.png"/>
                  </div>
                  <div class="main_book_box">
                    <q-img :src="selectBook.img_url" class="main_book"
                        v-bind:class="{'shadow_red':selectBook.emotion_score<24386 && selectBook.emotion_score>=1576,
                        'shadow_orange':selectBook.emotion_score<1576 && selectBook.emotion_score>=845,
                        'shadow_yellow':selectBook.emotion_score<845 && selectBook.emotion_score>361,
                        'shadow_green':selectBook.emotion_score<=361 && selectBook.emotion_score>0,
                        'shadow_blue':selectBook.emotion_score<=0 && selectBook.emotion_score>-318,
                        'shadow_navy':selectBook.emotion_score<=-318 && selectBook.emotion_score>-1261,
                        'shadow_purple':selectBook.emotion_score<=-1261 && selectBook.emotion_score>=-29726
                        }"
                    />
                  </div>
              </div>
          </div>
          <div class="main_content_list" style="display: flex;">
                <div v-for="(book, index) in bookList" :key="index">
                    <q-img  :src="book.img_url"  @click="select(index)" class="list_book"
                        v-bind:class="{'shadow_red':book.emotion_score<24386 && book.emotion_score>=1576,
                        'shadow_orange':book.emotion_score<1576 && book.emotion_score>=845,
                        'shadow_yellow':book.emotion_score<845 && book.emotion_score>361,
                        'shadow_green':book.emotion_score<=361 && book.emotion_score>0,
                        'shadow_blue':book.emotion_score<=0 && book.emotion_score>-318,
                        'shadow_navy':book.emotion_score<=-318 && book.emotion_score>-1261,
                        'shadow_purple':book.emotion_score<=-1261 && book.emotion_score>=-29726
                        }"    
                    />
                </div>
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
                store.dispatch('module/getWriterList',form.keyword)
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
            store.dispatch('module/getwords',selectBook.value.isbn13)
                .then((res)=>{
                    let tmp = []
                    for (let i = 0; i < res.data.length; i++) {
                        let j = {
                            "name": '',
                            "value": ''
                        }
                        j.name = res.data[i][0]
                        j.value = res.data[i][1]
                        tmp.push(j)
                    }
                store.commit('module/setwords', tmp)
                router.push('/detail')
            })
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
    width:240px;
    display:inline-block;
}
.main_search_btn{
    margin-left:10px;
    margin-bottom:20px;
    height:45px;
    width:45px;
    font-size:13px;
}
.main_search_btn_span{
    margin-top:5px;
}
.main_content{
    clear:both;
    width:100%;
    height:532px;
}
.main_content_view{
    width:100%;
    height:68%;
    position:relative;
}
.main_content_left{
    position:absolute;
    top:100px;
    left:65px;
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
    display:inline-block;
    position:absolute;
    top:50px;
    left:460px;
    width:390px;
    height:300px;
}
.main_circle{
    /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#1e5799+0,207cca+20,2dc6ed+53,7db9e8+96,7db9e8+100 */
    background: rgb(58,106,154);
    background: linear-gradient(90deg, rgba(58,106,154,0.9700924315038515) 0%, rgba(139,180,238,1) 31%, rgba(219,232,254,1) 100%);
    border-radius: 200px;
    width:320px;
    height:320px;
    float:right;
    position:relative;
    top:-20px;
    /* box-shadow: 3px 3px 3px; */
    transform:rotate(240deg)
}
.main_pen{
    height:200px;
}
.main_pen_photo{
    z-index:100;
    width:160px;
    position:absolute;
    top:80px;
    left:40px;  
    animation: float2 2s infinite;
    transition: 1.3s;
}
.main_book{
    /* position:absolute; */
    /* top:5px; */
    /* left:110px; */
    width:200px;
    height:280px;
    animation: float 2s infinite;
    transition: 1.3s;
}
.main_book_box{
    position:absolute;
    top:-5px;
    left:110px;
    width:200px;
    height:280px;
    display: block;
    transition: 1.3s;
    /* 관찰자 시점을 설정 */
    transform-style: preserve-3d;
    perspective: 500px; 
}
.main_content_list{
    padding-top:25px;
    padding-left:5px;
    height:32%;
    width:100%;
    margin:0 auto;
}
.list_book{
    width:105px;
    height:135px;
    margin-right:25px;
    cursor:pointer;
    transition: .5s;
}
.list_book:hover{
    transform: scale(1.15);
}
.shadow_red{
    box-shadow: 10px 10px 15px rgb(253, 185, 185);
}
.shadow_orange{
    box-shadow: 10px 10px 15px rgb(255, 202, 159);
}
.shadow_yellow{
    box-shadow: 10px 10px 15px rgb(254, 235, 168);
}
.shadow_green{
    box-shadow: 10px 10px 15px rgb(211, 236, 197);
}
.shadow_blue{
    box-shadow: 10px 10px 15px rgb(164, 194, 242);
}
.shadow_navy{
    box-shadow: 10px 10px 15px rgb(124, 138, 210);
}
.shadow_purple{
    box-shadow: 10px 10px 15px rgb(200, 167, 213);
}

@keyframes float {
	0% {
		box-shadow: 20px 20px 20px 0px rgba(0,0,0,0.6);
		transform: translatey(0px) rotate(15deg) rotateX(-10deg) rotateY(-10deg);
	}
	50% {
		box-shadow: 40px 40px 20px 0px rgba(0,0,0,0.2);
		transform: translatey(-20px) rotate(15deg) rotateX(-0deg) rotateY(-5deg);
	}
	100% {
		box-shadow: 20px 20px 20px 0px rgba(0,0,0,0.6);
		transform: translatey(0px) rotate(15deg) rotateX(-10deg) rotateY(-10deg);
	}
}
@keyframes float2 {
	0% {
		transform: translatey(0px) rotate(300deg) rotateX(-10deg) rotateY(-10deg);
	}
	50% {
		transform: translatey(-20px) rotate(300deg) rotateX(-0deg) rotateY(-5deg);
	}
	100% {
		transform: translatey(0px) rotate(300deg) rotateX(-10deg) rotateY(-10deg);
	}
}
</style>