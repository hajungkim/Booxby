<template>
  <div class="detail_box">
      <div class="detail_back">
          <q-btn class="back_btn" @click="back" round color="primary" icon="undo" />
      </div>
      <div class="detail_content">
        <div class="q-pa-md" style="max-width: 900px;">
            <q-card class="no-shadow">
                <q-tabs
                    v-model="tab"
                    dense
                    class="text-grey"
                    active-color="primary"
                    indicator-color="primary"
                    align="justify"
                >
                    <q-tab name="view" style="color:black;" label="상세설명" />
                    <q-tab name="review" style="color:black;" label="리뷰" />
                    <q-tab name="author" style="color:black;" label="작가의 다른 책" />
                </q-tabs>

            <q-separator />

            <q-tab-panels v-model="tab" animated>
                <q-tab-panel name="view" class="q-pa-none">
                    <div class="detail_view">
                        <div class="view_head">
                            <div style="font-weight:bold; font-size:25px;">책 소개</div>
                            <div class="detail_view_introduce">
                                이 책은 싸피에서 준 HTML + CSS 책이다. 이 책은 싸피에서 준 HTML + CSS 책이다.
                                이 책은 싸피에서 준 HTML + CSS 책이다. 이 책은 싸피에서 준 HTML + CSS 책이다.
                                이 책은 싸피에서 준 HTML + CSS 책이다. 이 책은 싸피에서 준 HTML + CSS 책이다.
                                이 책은 싸피에서 준 HTML + CSS 책이다. 이 책은 싸피에서 준 HTML + CSS 책이다.   
                            </div>
                        </div>  
                        <div class="view_bot">
                            <div style="font-weight:bold; font-size:25px;">키워드</div>
                            <div class="keyword">
                                <q-img id="keyword_img" src="~assets/images/keyword.png"/>
                            </div>
                        </div>
                    </div>
                </q-tab-panel>

                <q-tab-panel name="review" class="q-pa-none">
                    <div class="detail_view_review">
                        <div class="review_head">책리뷰 (1)
                            <q-btn @click="writeMode = true" style="float:right; margin-right:25px; font-weight:bold; border-radius:15px;" color="primary" label="리뷰쓰기" />
                        </div>
                        <q-dialog v-model="writeMode" persistent>
                            <q-card>
                                <q-card-section class="row items-center">
                                    <div style="width:400px;"></div>
                                </q-card-section>

                                <q-card-actions align="center">
                                <q-btn label="취소" color="primary" v-close-popup />
                                <q-btn label="등록" color="primary"/>
                                </q-card-actions>
                            </q-card>
                        </q-dialog>
                        <div class="review_list">
                            <div class='review_item'>
                                <div class="review_left">
                                    <q-img src="~assets/images/book.jpg" class="review_profile"/>
                                    <div class="review_name">
                                        김싸피
                                    </div>
                                </div>
                                <div class="review_right">
                                    <div class="review_score">
                                        <q-rating
                                            v-model="review_score"
                                            max="5"
                                            size="2em"
                                            color="green-5"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            readonly
                                        />
                                    </div>
                                    <div class="review_text">
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요.
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요.
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요
                                    </div>
                                </div>
                            </div>
                            <div class='review_item'>
                                <div class="review_left">
                                    <q-img src="~assets/images/book.jpg" class="review_profile"/>
                                    <div class="review_name">
                                        김싸피
                                    </div>
                                </div>
                                <div class="review_right">
                                    <div class="review_score">
                                        <q-rating
                                            v-model="review_score"
                                            max="5"
                                            size="2em"
                                            color="green-5"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            readonly
                                        />
                                    </div>
                                    <div class="review_text">
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요.
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요.
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요
                                    </div>
                                </div>
                            </div>
                            <div class='review_item'>
                                <div class="review_left">
                                    <q-img src="~assets/images/book.jpg" class="review_profile"/>
                                    <div class="review_name">
                                        김싸피
                                    </div>
                                </div>
                                <div class="review_right">
                                    <div class="review_score">
                                        <q-rating
                                            v-model="review_score"
                                            max="5"
                                            size="2em"
                                            color="green-5"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            readonly
                                        />
                                    </div>
                                    <div class="review_text">
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요.
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요.
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요
                                    </div>
                                </div>
                            </div>
                            <div class='review_item'>
                                <div class="review_left">
                                    <q-img src="~assets/images/book.jpg" class="review_profile"/>
                                    <div class="review_name">
                                        김싸피
                                    </div>
                                </div>
                                <div class="review_right">
                                    <div class="review_score">
                                        <q-rating
                                            v-model="review_score"
                                            max="5"
                                            size="2em"
                                            color="green-5"
                                            icon="star_border"
                                            icon-selected="star"
                                            icon-half="star_half"
                                            readonly
                                        />
                                    </div>
                                    <div class="review_text">
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요.
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요.
                                        이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요. 이 책을 추천해요
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </q-tab-panel>

                <q-tab-panel name="author" class="q-pa-none">
                    <div class="detail_view_author">
                        <div class="author_head">ㅇㅇㅇ 작가의 다른 책</div>
                        <div class="author_list">
                            <q-img class="author_img" src="~assets/images/html.jpg"/>
                            <q-img class="author_img" src="~assets/images/html.jpg"/>
                            <q-img class="author_img" src="~assets/images/html.jpg"/>
                            <q-img class="author_img" src="~assets/images/html.jpg"/>
                            <q-img class="author_img" src="~assets/images/html.jpg"/>
                            <q-img class="author_img" src="~assets/images/html.jpg"/>
                            <q-img class="author_img" src="~assets/images/html.jpg"/>
                            <q-img class="author_img" src="~assets/images/html.jpg"/>
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
import { ref } from 'vue'
import { useRouter } from 'vue-router'

export default {
    setup () {
        const router = useRouter()

        const review_score = ref(4)

        const back = function() {
            router.push('/main')
        }
        return {
            tab: ref('review'),
            writeMode: ref(false),
            review_score,
            back
        }
    }
}
</script>

<style>
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
    margin-top:15px;
    margin-right:10px;
    height:54px;
    width:54px;
    font-size:18px;
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
    border:1px solid grey;
    margin-left:40px;
    margin-top:10px;
    width:500px;
    height:210px;
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
.review_list{
    margin-top:15px;
}
.review_item{
    border:1px solid grey;
    height:150px;
    margin-bottom: 15px;
    margin-right:25px;
    border-radius: 15px;
}
.review_left{
    width:20%;
    height:150px;
    display: inline-block;
    vertical-align: top;
    border-right:0.5px solid rgb(209, 209, 209);
}
.review_profile{
    width:70px;
    height:70px;
    border-radius: 50px;
    margin-top:27px;
    margin-left:46px;
}
.review_name{
    font-size:18px;
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
    margin-top:15px;
}
.author_img{
    border:1px solid grey;
    width:20%;
    margin-left:30px;
    margin-bottom:15px;
}
</style>