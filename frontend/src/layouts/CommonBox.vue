<template>
  <div class="container">
    <div class="mainBox">
      <router-view />
    </div>
    <div class="ocean">
      <div class="wave"></div>
      <div class="wave"></div>
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
  border-radius: 38px;
  background-color: rgb(227,231,234);
  z-index: 100;
}
.container{
  display:flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: rgb(170, 196, 216);
}
body {
  background:radial-gradient(ellipse at center, rgba(255,254,234,1) 0%, rgba(255,254,234,1) 35%, #B7E8EB 100%);
  overflow: hidden;
}
.ocean { 
  height: 5%;
  width:100%;
  position:absolute;
  bottom:0;
  left:0;
  background: #015871;
}
.wave {
  background: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/85486/wave.svg) repeat-x; 
  position: absolute;
  top: -198px;
  width: 6400px;
  height: 198px;
  animation: wave 2s cubic-bezier( 0.36, 0.45, 0.63, 0.53) infinite;
  transform: translate3d(0, 0, 0);
}
.wave:nth-of-type(2) {
  top: -175px;
  animation: wave 3s cubic-bezier( 0.36, 0.45, 0.63, 0.53) -.125s infinite, swell 7s ease -1.25s infinite;
  opacity: 1;
}
@keyframes wave {
  0% {
    margin-left: 0;
  }
  100% {
    margin-left: -1600px;
  }
}

@keyframes swell {
  0%, 100% {
    transform: translate3d(0,-25px,0);
  }
  50% {
    transform: translate3d(0,5px,0);
  }
}

@font-face {
    font-family: 'IM_Hyemin-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2106@1.1/IM_Hyemin-Bold.woff2') format('woff');
    font-weight: normal;
    font-style: normal;
}
body {
  font-family: 'IM_Hyemin-Bold';
}
</style>