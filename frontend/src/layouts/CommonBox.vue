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