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
                모던웹을 위한 HTML+CSS
            </div>
            <div class="detail_author">
                작가이름 / 출판사
            </div>
            <div class="detail_book">
                <q-img src="~assets/images/html.jpg"/>
            </div>
      </div>
  </div>
</template>

<script>
export default {
}
</script>

<style>
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
    border:1px solid grey;
    margin: 0 auto;
    margin-top:5px;
    width:250px;
}
</style>