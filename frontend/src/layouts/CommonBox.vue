<template>
  <div class="container">
    <div class="mainBox">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.mainBox{
  width:1300px;
  height:650px;
  border: 1px solid rgb(241, 237, 237);
  border-radius: 38px;
  background-color: rgb(227,231,234);
}
.container{
  display:flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: rgb(185, 217, 241);
}
.container{
  /* display:flex; 
  height: 100vh;  */
  display: flex;
  position: absolute;
  width: 100%;
  height: 100%;
  /* flex-direction: column; */
  justify-content: center;
  align-items: center;
}
</style>