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
  margin:70 auto;
  width:1300px;
  height:650px;
  border: 1px solid rgb(241, 237, 237);
  border-radius: 38px;
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