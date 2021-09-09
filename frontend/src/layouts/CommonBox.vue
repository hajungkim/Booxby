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
  width:1600px;
  height:800px;
  border: 1px solid black;
  border-radius: 38px;
}
.container{
  display:flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>