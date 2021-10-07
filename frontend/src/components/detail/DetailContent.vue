<template>
  <div class="detail_box">
      <div class="detail_back">
          <q-btn class="back_btn" @click="back" round style="background:rgb(86,86,239); color:white;" icon="undo" />
      </div>
      <div class="detail_content">
        <div class="q-pa-md" style="max-width: 900px;">
            <q-card class="no-shadow" style="border-radius:30px;">
                <q-tabs
                    v-model="tab"
                    dense
                    class="text-grey"
                    active-color="secondary"
                    indicator-color="secondary"
                    align="justify"
                    narrow-indicator
                >
                    <q-tab name="view" label="상세설명" />
                    <q-tab name="review" label="리뷰" />
                    <q-tab name="author" label="작가의 다른 책" />
                </q-tabs>

            <q-separator />

            <q-tab-panels v-model="tab" animated>
                <q-tab-panel name="view" class="q-pa-none">
                    <div class="detail_view">
                        <div class="view_head">
                            <div style="font-weight:bold; font-size:25px;">책 소개</div>
                            <div class="detail_view_introduce">
                                {{selectBook.description}} 
                            </div>
                        </div>  
                        <div class="view_bot">
                            <div style="font-weight:bold; font-size:25px;">키워드</div>
                            <ul class="cloud" role="navigation" aria-label="Webdev word cloud">
                                <li v-for="(item, index) in words" :key='index'>
                                    <a :data-weight="item.value">{{item.name}}</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </q-tab-panel>

                <q-tab-panel name="review" class="q-pa-none">
                    <div class="detail_view_review">
                        <div class="review_head">책리뷰
                            <q-btn @click="writeMode = true" style="float:right; margin-right:25px; font-weight:bold; border-radius:15px; background:rgb(86,86,239); color:white;" label="리뷰쓰기" />
                        </div>
                        <!-- 리뷰 모달 -->
                        <q-dialog v-model="writeMode">
                            <q-card style="background:whitesmoke;">
                                <q-card-section class="row items-center">
                                    <div class="review_modal">
                                        <div class="modal_name">
                                            리뷰 남기기
                                        </div>
                                        <div class="modal_score">
                                            <span style="font-weight:bold;">장점 </span>- 이 책에 대한 별점을 남겨주세요
                                            <q-rating
                                            style="margin-left:5px; margin-top:10px;"
                                            v-model="review_score"
                                            max="5"
                                            size="2em"
                                            color="secondary"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            />
                                        </div>
                                        <div class="modal_tag">
                                            <span style="font-weight:bold;">태그 </span>- 이 책을 읽었을 때의 느낌을 남겨주세요
                                            <div class="write_categories">
                                                <div v-if="!form.tag1" @click="tag1" class="write_category">
                                                    <img class="write_icon" src="../../assets/Surprised_Emoji.png">
                                                    <span class="write_word">기발해요</span>
                                                </div>
                                                <div v-if="form.tag1" @click="tag1" class="write_category2">
                                                    <img class="write_icon" src="../../assets/Surprised_Emoji.png">
                                                    <span class="write_word">기발해요</span>
                                                </div>
                                                <div v-if="!form.tag2" @click="tag2" class="write_category">
                                                    <img class="write_icon" src="../../assets/Thumbs_Up_Emoji.png">
                                                    <span class="write_word">유용해요</span>
                                                </div>
                                                <div v-if="form.tag2" @click="tag2" class="write_category2">
                                                    <img class="write_icon" src="../../assets/Thumbs_Up_Emoji.png">
                                                    <span class="write_word">유용해요</span>
                                                </div>
                                                <div v-if="!form.tag3" @click="tag3" class="write_category">
                                                    <img class="write_icon" src="../../assets/Eyes_Emoji.png">
                                                    <span class="write_word">잘읽혀요</span>
                                                </div>
                                                <div v-if="form.tag3" @click="tag3" class="write_category2">
                                                    <img class="write_icon" src="../../assets/Eyes_Emoji.png">
                                                    <span class="write_word">잘읽혀요</span>
                                                </div>
                                                <div v-if="!form.tag4" @click="tag4" class="write_category">
                                                    <img class="write_icon" src="../../assets/Heart_Emoji.png">
                                                    <span class="write_word">추천해요</span>
                                                </div>
                                                <div v-if="form.tag4" @click="tag4" class="write_category2">
                                                    <img class="write_icon" src="../../assets/Heart_Emoji.png">
                                                    <span class="write_word">추천해요</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal_write">
                                            <span style="font-weight:bold;">리뷰 </span>- 이 책의 감상평을 간단히 적어주세요
                                            <div style="max-width: 350px; margin-left:5px; margin-top:10px;">
                                                <q-input
                                                v-model="form.text"
                                                class="textarea"
                                                filled
                                                type="textarea"
                                                rows="5"
                                                />
                                            </div>
                                        </div>
                                    </div>
                                </q-card-section>
                                <q-card-actions>
                                    <div style="display:inline-block; margin:0 auto;">
                                        <q-btn @click="downReview" class="modal_btn" label="취소" style="background:rgb(86,86,239); color:white;" v-close-popup />
                                        <q-btn @click="review" class="modal_btn" label="등록" style="background:rgb(86,86,239); color:white;" />
                                    </div>
                                </q-card-actions>
                            </q-card>
                        </q-dialog>
                        <div class="review_list">
                            <div class="review_state">
                                <div class="state_left">
                                    <div>
                                        <q-rating
                                            v-model="score_5"
                                            max="5"
                                            size="1.3em"
                                            color="secondary"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            readonly
                                        />
                                        <q-linear-progress class="progress" rounded size="10px" :value="progress5" color="secondary"/>
                                    </div>
                                    <div>
                                        <q-rating
                                            v-model="score_4"
                                            max="5"
                                            size="1.3em"
                                            color="secondary"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            readonly
                                        />
                                        <q-linear-progress class="progress" rounded size="10px" :value="progress4" color="secondary"/>
                                    </div>
                                    <div>
                                        <q-rating
                                            v-model="score_3"
                                            max="5"
                                            size="1.3em"
                                            color="secondary"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            readonly
                                        />
                                        <q-linear-progress class="progress" rounded size="10px" :value="progress3" color="secondary"/>
                                    </div>
                                    <div>
                                        <q-rating
                                            v-model="score_2"
                                            max="5"
                                            size="1.3em"
                                            color="secondary"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            readonly
                                        />
                                        <q-linear-progress class="progress" rounded size="10px" :value="progress2" color="secondary"/>
                                    </div>
                                    <div>
                                        <q-rating
                                            v-model="score_1"
                                            max="5"
                                            size="1.3em"
                                            color="secondary"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            readonly
                                        />
                                        <q-linear-progress class="progress" rounded size="10px" :value="progress1" color="secondary"/>
                                    </div>
                                </div>
                                <div class="state_right">
                                        <q-linear-progress stripe class="progress2" style="margin-left:27px;" size="30px" :value="progress6" color="blue-3"/>
                                        <q-linear-progress stripe class="progress2" style="margin-left:26px;" size="30px" :value="progress7" color="blue-3"/>
                                        <q-linear-progress stripe class="progress2" style="margin-left:25px;" size="30px" :value="progress8" color="blue-3"/>
                                        <q-linear-progress stripe class="progress2" style="margin-left:25px;" size="30px" :value="progress9" color="blue-3"/>
                                    <div class="state_categories">
                                        <div class="state_category">
                                            <img class="review_icon" src="../../assets/Surprised_Emoji.png">
                                            <span class="review_word">기발해요</span>
                                        </div>
                                        <div class="state_category">
                                            <img class="review_icon" src="../../assets/Thumbs_Up_Emoji.png">
                                            <span class="review_word">유용해요</span>
                                        </div>
                                        <div class="state_category">
                                            <img class="review_icon" src="../../assets/Eyes_Emoji.png">
                                            <span class="review_word">잘읽혀요</span>
                                        </div>
                                        <div class="state_category">
                                            <img class="review_icon" src="../../assets/Heart_Emoji.png">
                                            <span class="review_word">추천해요</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div v-if="!reviewList" style="font-size:20px; text-align:center; margin-top:85px; color:lightgrey;">
                                리뷰가 없습니다. 리뷰를 작성해주세요.
                            </div>
                            <div v-for="(item, index) in reviewList" :key='index' class='review_item'>
                                <div class="review_left">
                                    <q-img v-if="-2972.6<=(item.hashScore + item.worldcupScore) / 2 && (item.hashScore + item.worldcupScore) / 2 <=-126.1" src='~assets/character/purple.png' class="review_profile"/>
                                    <q-img v-if="-126.1<(item.hashScore + item.worldcupScore) / 2 && (item.hashScore + item.worldcupScore) / 2 <=-31.8" src='~assets/character/navy.png' class="review_profile"/>
                                    <q-img v-if="-31.8<=(item.hashScore + item.worldcupScore) / 2 && (item.hashScore + item.worldcupScore) / 2 <=0" src='~assets/character/blue.png' class="review_profile"/>
                                    <q-img v-if="0<(item.hashScore + item.worldcupScore) / 2 && (item.hashScore + item.worldcupScore) / 2 <=36.1" src='~assets/character/green.png' class="review_profile"/>
                                    <q-img v-if="36.1<(item.hashScore + item.worldcupScore) / 2 && (item.hashScore + item.worldcupScore) / 2 <=84.5" src='~assets/character/orange.png' class="review_profile"/>
                                    <q-img v-if="84.5<(item.hashScore + item.worldcupScore) / 2 && (item.hashScore + item.worldcupScore) / 2 <=157.6" src='~assets/character/yellow.png' class="review_profile"/>
                                    <q-img v-if="157.6<(item.hashScore + item.worldcupScore) / 2 && (item.hashScore + item.worldcupScore) / 2 <=2438.6" src='~assets/character/red.png' class="review_profile"/>
                                    <div class="review_name">
                                        {{item.nickname}}
                                    </div>
                                </div>
                                <div class="review_right">
                                    <div class="review_score">
                                        <q-rating
                                            v-model="item.reviewScore"
                                            max="5"
                                            size="1.4em"
                                            color="secondary"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            readonly
                                        />
                                    </div>
                                    <div class="review_text">
                                        {{item.reviewContent}}
                                    </div>
                                    <div class="review_categories">
                                        <div v-if="item.reviewIdea" class="review_category">
                                            <img class="review_icon" src="../../assets/Surprised_Emoji.png">
                                            <span class="review_word">기발해요</span>
                                        </div>
                                        <div v-if="item.reviewLike" class="review_category">
                                            <img class="review_icon" src="../../assets/Thumbs_Up_Emoji.png">
                                            <span class="review_word">유용해요</span>
                                        </div>
                                        <div v-if="item.reviewRead" class="review_category">
                                            <img class="review_icon" src="../../assets/Eyes_Emoji.png">
                                            <span class="review_word">잘읽혀요</span>
                                        </div>
                                        <div v-if="item.reviewUseful" class="review_category">
                                            <img class="review_icon" src="../../assets/Heart_Emoji.png">
                                            <span class="review_word">추천해요</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </q-tab-panel>
                <q-tab-panel name="author" class="q-pa-none">
                    <div class="detail_view_author">
                        <!-- <div class="author_head">{{ show.writer }}의 다른 책</div> -->
                        <div class="author_list">
                            <div v-if="!writerList" style="font-size:30px; color:lightgrey; margin:0 auto; margin-top:185px;">
                                작가의 다른책이 없습니다.
                            </div>
                            <!-- <q-img v-for="item in writerList" :key="item.isbn" class="author_img" :src="item.img_url"/> -->
                            <q-card v-for="item in writerList" :key="item.isbn13" @click="writerDetail(item.isbn13)" class="my-card no-shadow cardbooks">
                                <q-img class="author_img" :src="item.img_url">
                                <div class="absolute-bottom text-subtitle2 text-center">
                                    {{item.title}}
                                </div>
                                </q-img>
                            </q-card>
                        </div>
                    </div>
                </q-tab-panel>
            </q-tab-panels>
            </q-card>
        </div>
      </div>
  </div>

</template>

<script>
import { ref, computed, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export default {
    setup () {
        const store = useStore()
        const router = useRouter()

        const selectBook = computed(() => store.getters['module/getSelectBook'])
        const review_score = ref(4)
        const writeMode = ref(false)

        const reviewList = computed(() => store.getters['module/getReviewList'])
        const writerList = computed(() => store.getters['module/getWriterList'])
        const words = computed(() => store.getters['module/getwords'])
        console.log(reviewList,'@@')
        console.log(selectBook,'@@')
        const show = reactive({
            writer: ''
        })
        const tab = ref('view')

        const progress1 = ref(0)
        const progress2 = ref(0)
        const progress3 = ref(0)
        const progress4 = ref(0)
        const progress5 = ref(0)
        const progress6 = ref(0)
        const progress7 = ref(0)
        const progress8 = ref(0)
        const progress9 = ref(0)

        const form = reactive({
            text: '',
            tag1: false,
            tag2: false,
            tag3: false,
            tag4: false
        })

        // word cloud
        const back = function() {
            store.commit('module/setZzim', false)
            router.go(-1)
        }
        
        const downReview = function() {
            form.text = ''
            form.tag1 = false
            form.tag2 = false
            form.tag3 = false
            form.tag4 = false
            review_score.value = 4
        }

        const review = function() {
            const userId = localStorage.getItem('userId')
            const review = {
                imgUrl: selectBook.value.img_url,
                isbn: selectBook.value.isbn13,
                reviewContent: form.text,
                reviewIdea: form.tag1,
                reviewLike: form.tag2,
                reviewRead: form.tag3,
                reviewScore: review_score.value,
                reviewUseful: form.tag4,
                title: selectBook.value.title,
                userId: userId
            }
            store.dispatch('module/writeReview', review)
                .then(function () {
                    store.dispatch('module/requestReview', selectBook.value.isbn13)
                        .then(function (result) {
                            store.commit('module/setReviewList', result.data.data)
                            requestReview()
                        })
                })
            form.text = ''
            form.tag1 = false
            form.tag2 = false
            form.tag3 = false
            form.tag4 = false
            review_score.value = 4
            writeMode.value = false
        }

        const tag1 = function() {
            form.tag1 = !form.tag1
        }
        const tag2 = function() {
            form.tag2 = !form.tag2
        }
        const tag3 = function() {
            form.tag3 = !form.tag3
        }
        const tag4 = function() {
            form.tag4 = !form.tag4
        }
        const requestReview = function() {
            store.dispatch('module/requestReview', selectBook.value.isbn13)
                .then(function (result) {
                    let reviewIdea = 0
                    let reviewLike = 0
                    let reviewRead = 0
                    let reviewUseful = 0
                    let emotionCount = 0
                    let star1 = 0
                    let star2 = 0
                    let star3 = 0
                    let star4 = 0
                    let star5 = 0
                    let scoreCount = 0
                    for(let i=0; i<result.data.data.length; i++) {
                        if(result.data.data[i].reviewScore == 1) {
                            star1++
                            scoreCount++
                        }
                        if(result.data.data[i].reviewScore == 2) {
                            star2++
                            scoreCount++
                        }
                        if(result.data.data[i].reviewScore == 3) {
                            star3++
                            scoreCount++
                        }
                        if(result.data.data[i].reviewScore == 4) {
                            star4++
                            scoreCount++
                        }
                        if(result.data.data[i].reviewScore == 5) {
                            star5++
                            scoreCount++
                        }
                        if(result.data.data[i].reviewIdea){
                            reviewIdea++
                            emotionCount++
                        }
                        if(result.data.data[i].reviewLike){
                            reviewLike++
                            emotionCount++
                        }
                        if(result.data.data[i].reviewRead){
                            reviewRead++
                            emotionCount++
                        }
                        if(result.data.data[i].reviewUseful){
                            reviewUseful++
                            emotionCount++
                        }
                    }
                    if(scoreCount != 0) {
                        progress1.value = star1 / scoreCount
                        progress2.value = star2 / scoreCount
                        progress3.value = star3 / scoreCount
                        progress4.value = star4 / scoreCount
                        progress5.value = star5 / scoreCount
                    }
                    if(emotionCount != 0) {
                        progress6.value = reviewIdea / emotionCount
                        progress7.value = reviewLike / emotionCount
                        progress8.value = reviewRead / emotionCount
                        progress9.value = reviewUseful / emotionCount
                    }
                    store.commit('module/setReviewList', result.data.data)
                })
        }

        const writerDetail = function(isbn){
            store.dispatch('module/getisbnInfo',isbn)
                .then((res) =>{
                    store.commit('module/setSelectBook', res.data[0])
                    tab.value = 'view'
                })
        }

        onMounted(() => {
            requestReview()
            const select = store.getters['module/getSelectBook']
            store.dispatch('module/getWriterList', select.author)
                .then((res) => {
                    store.commit('module/setWriterList', res.data)
                    show.writer = select.author
                })
        })

        return {
            tab,
            writeMode,
            selectBook,
            review_score,
            score_5: ref(5),
            score_4: ref(4),
            score_3: ref(3),
            score_2: ref(2),
            score_1: ref(1),
            back,
            form,
            review,
            tag1,
            tag2,
            tag3,
            tag4,
            downReview,
            reviewList,
            progress1,
            progress2,
            progress3,
            progress4,
            progress5,
            progress6,
            progress7,
            progress8,
            progress9,
            writerList,
            show,
            writerDetail,
            words,
        }
    }
}
</script>

<style scoped>
.no_keyword{
    display:flex;
    justify-content: center;
    align-items: center;
    font-size:20px;
    width:500px;
    height:200px;
}
ul.cloud {
  list-style: none;
  padding-left: 0;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  line-height: 3.5rem;
  width: 450px;
  margin-top: 10px;
  margin-left: 150px;
}
ul.cloud a {
  /*   
  Not supported by any browser at the moment :(
  --size: attr(data-weight number); 
  */
  --size: 2;
  --color: #a33;
  color: var(--color);
  font-size: calc(var(--size) * 0.25rem + 0.5rem);
  display: block;
  padding: 0.125rem 0.25rem;
  position: relative;
  text-decoration: none;
  /* 
  For different tones of a single color
  opacity: calc((15 - (9 - var(--size))) / 15); 
  */
}
ul.cloud a[data-weight="1"] { --size: 4; }
ul.cloud a[data-weight="2"] { --size: 8; }
ul.cloud a[data-weight="3"] { --size: 12; }
ul.cloud a[data-weight="4"] { --size: 16; }
ul.cloud a[data-weight="5"] { --size: 14; }
ul.cloud a[data-weight="6"] { --size: 16; }
ul.cloud a[data-weight="7"] { --size: 18; }
ul.cloud a[data-weight="8"] { --size: 21; }
ul.cloud a[data-weight="9"] { --size: 24; }
ul[data-show-value] a::after {
  content: " (" attr(data-weight) ")";
  font-size: 1rem;
}
ul.cloud li:nth-child(2n+1) a { --color: #181; transform: rotate( 30deg );}
ul.cloud li:nth-child(3n+1) a { --color: #33a;transform: rotate( 90deg ); }
ul.cloud li:nth-child(4n+1) a { --color: #c38;transform: rotate( -45deg ); }
ul.cloud a:focus {
  outline: 1px dashed;
}
ul.cloud a::before {
  content: "";
  position: absolute;
  top: 0;
  left: 50%;
  width: 0;
  height: 100%;
  background: var(--color);
  transform: translate(-50%, 0);
  opacity: 0.15;
  transition: width 0.25s;
}
ul.cloud a:focus::before,
ul.cloud a:hover::before {
  width: 100%;
}
@media (prefers-reduced-motion) {
  ul.cloud * {
    transition: none !important;
  }
}
#container {
  min-width: 310px;
  max-width: 800px;
  margin: 0 auto
}
.detail_box{
    /* border:1px solid red; */
    width:900px;
    margin:0 auto;
}
.detail_back{    
    margin-top:10px;
    margin-right:-10px;
    float:right;
}
.back_btn{
    margin-top:20px;
    margin-right:10px;
    height:45px;
    width:45px;
    font-size:13px;
}
.detail_content{
    clear:both;
    margin-top:82px;
}
.detail_view{
    /* border:1px solid red; */
    height:486px;
    padding-top:25px;
    padding-left:25px;
}
.detail_view_introduce{
    /* border:1px solid blue; */
    margin-top:5px;
    margin-left:10px;
    width:94%;
    height:100px;
    font-size:17px;
    color:grey;
}
.view_bot{
    /* border:1px solid pink; */
    margin-top:15px;
    height:280px;
}
.keyword{
    margin-left:40px;
    margin-top:10px;
    width:500px;
    min-height:210px;
}
#keyword_img{
    width:498px;
    height:208px;
}
.detail_view_review{
    height:486px;
    padding-top:25px;
    padding-left:25px;
    overflow:auto;
    -ms-overflow-style:none;
    scrollbar-width:none;
}
.detail_view_review::-webkit-scrollbar{
    display:none;
}
.review_head{
    font-weight:bold;
    font-size:25px;
}
.review_modal{
    width:400px;
    height:375px;
    padding:15px;
}
.modal_name{
    color:rgb(86,86,239);
    font-weight:700;
    font-size:23px;
}
.modal_tag{
    margin-top:15px;
    padding-left:5px;
}
.modal_score{
    margin-top:15px;
    padding-left:5px;
}
.write_categories{
    margin-left:5px;
    margin-top:10px;
    display:flex;
}
.write_category{
  display: flex;
  width:90px;
  height:30px;
  background-color: rgb(202, 202, 202);
  border-radius: 20px;
  margin-right:4px;
  cursor:pointer;
}
.write_category2{
  display: flex;
  width:90px;
  height:30px;
  background-color: rgb(187, 221, 241);
  border-radius: 20px;
  margin-right:4px;
  cursor:pointer;
}
.write_icon{
  width:20px;
  height:20px;
  margin: 5px 0px 0px 7px;
}
.write_word{
  margin:6px 0px 0px 2px;
  color:rgb(8, 84, 173);
  font-weight: bold;
}
.review_categories{
    margin-left:5px;
    margin-top:5px;
    position:absolute;
    top:83px;
    display:flex;
}
.review_category{
  display: flex;
  width:90px;
  height:30px;
  background-color: rgb(187, 221, 241);
  border-radius: 20px;
  margin-right:5px;
}
.review_icon{
  width:20px;
  height:20px;
  margin: 5px 0px 0px 7px;
}
.review_word{
  margin:6px 0px 0px 2px;
  color:rgb(8, 84, 173);
  font-weight: bold;
}
.modal_write{
    margin-top:15px;
    padding-left:5px;
}
.modal_btn{
    width:120px;
    font-weight:bold;
    font-size:15px;
    border-radius: 15px;
    margin:0 20px;
    margin-bottom:8px;
}
.review_list{
    margin-top:18px;
}
.progress{
    margin-top:7px;
    float:right;
    width:130px;
}
.progress2{
    margin-top:40px;
    display:inline-block;
    width:85px;
    transform: rotate(270deg);
}
.state_left{
    width:33%;
    height:100px;
    display: inline-block;
    vertical-align: top;
    padding-left: 10px;
    position:relative;
    top:-5px;
}   
.state_right{
    width:67%;
    height:150px;
    display:inline-block;
    vertical-align: top;
    position:relative;
    top:-15px;
}
.state_categories{
    margin-left:5px;
    position:absolute;
    top:100px;
    left:20px;
    display:flex;
}
.state_category{
  display: flex;
  width:90px;
  height:30px;
  /* background-color: rgb(187, 221, 241);
  border-radius: 20px; */
  margin-right:20px;
}
.review_item{
    border:1px solid lightgrey;
    height:130px;
    margin-bottom: 15px;
    margin-right:25px;
    border-radius: 15px;
    position:relative;
    top:-15px;
    box-shadow: lightgrey 1px 1px 3px;
}
.review_left{
    width:20%;
    height:130px;
    display: inline-block;
    vertical-align: top;
    border-right:0.5px solid rgb(209, 209, 209);
}
.review_profile{
    width:60px;
    height:60px;
    border-radius: 50px;
    margin-top:20px;
    margin-left:53px;
}
.review_name{
    font-size:15px;
    margin-top:5px;
    text-align: center;
}
.review_text{
    /* border:1px solid red; */
    padding-top:5px;
    padding-left:10px;
    font-size:15px;
}
.review_right{
    width:80%;
    height:150px;
    padding-left:5px;
    display:inline-block;
    vertical-align: top;
}
.review_score{
    /* border:1px solid red; */
    margin-top:8px;
    margin-left:8px;
}
.detail_view_author{
    height:486px;
    overflow:auto;
    -ms-overflow-style:none;
    scrollbar-width:none;
    padding-top:25px;
    padding-left:25px;
}
.detail_view_author::-webkit-scrollbar{
    display:none;
}
.author_head{
    text-align: center;
    font-weight:bold;
    font-size:25px;
}
.author_list{
    display: flex;
    flex-wrap: wrap;
    width:830px;
    padding-top:15px;
    padding-left:20px;
}
.author_img{
    width:170px;
    height:200px;
    cursor: pointer;
}
.word_item{
    display:inline-block;
    margin:0px 7px;
    animation: wiggle 2.3s infinite;
}
.cardbooks{
    transition: .5s;
    margin-left:25px;
    margin-bottom:25px;
}
.cardbooks:hover{
    transform:scale(1.1)
}
@keyframes wiggle {
    0% { transform: rotate(1.7deg); }
    10% { transform: rotate(-1.7deg); }
   20% { transform: rotate(1.7deg); }
   30% { transform: rotate(-1.7deg); }
   40% { transform: rotate(1.7deg); }
   50% { transform: rotate(-1.7deg); }
   60% { transform: rotate(1.7deg); }
   70% { transform: rotate(-1.7deg); }
   80% { transform: rotate(1.7deg); }
   90% { transform: rotate(-1.7deg); }
   100% { transform: rotate(1.7deg); }
}
</style>