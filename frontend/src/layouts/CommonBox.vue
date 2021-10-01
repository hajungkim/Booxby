<template>
  <div class="container">
    <div class="mainBox">
      <router-view />
        <ul class="circles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
    <!-- <div class="ocean">
      <div class="wave"></div>
      <div class="wave"></div>
    </div> -->
  </div>
</template>

<script>
export default {

}
</script>

<style>
.container{
  display:flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.mainBox{
  width:1300px;
  height:650px;
  border-radius: 38px;
  background-color: whitesmoke;
  z-index: 100;
}
body {
	width: 100wh;
	height: 90vh;
	background: linear-gradient(-45deg, rgb(207, 132, 132), rgb(224, 161, 109),rgb(158, 189, 140),rgb(164,194,242),rgb(124,138,210),rgb(200,167,213));
	background-size: 1000% 400%;
	-webkit-animation: Gradient 15s ease infinite;
	-moz-animation: Gradient 15s ease infinite;
	animation: Gradient 10s ease infinite;
  font-family: 'Lato', sans-serif;
}

@-webkit-keyframes Gradient {
	0% {
		background-position: 0% 50%
	}
	50% {
		background-position: 100% 50%
	}
	100% {
		background-position: 0% 50%
	}
}

@-moz-keyframes Gradient {
	0% {
		background-position: 0% 50%
	}
	50% {
		background-position: 100% 50%
	}
	100% {
		background-position: 0% 50%
	}
}

@keyframes Gradient {
	0% {
		background-position: 0% 50%
	}
	50% {
		background-position: 100% 50%
	}
	100% {
		background-position: 0% 50%
	}
}

.circles{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
    z-index: -1;
}

.circles li{
    position: absolute;
    display: block;
    list-style: none;
    width: 20px;
    height: 20px;
    background: rgba(255, 255, 255, 0.2);
    animation: animate 25s linear infinite;
    bottom: -150px;
    
}

.circles li:nth-child(1){
    left: 5%;
    width: 120px;
    height: 120px;
    animation-delay: 1s;
    animation-duration: 6s;
}


.circles li:nth-child(2){
    left: 10%;
    width: 20px;
    height: 20px;
    animation-delay: 2s;
    animation-duration: 12s;
}

.circles li:nth-child(3){
    left: 15%;
    width: 20px;
    height: 20px;
    animation-delay: 4s;
    animation-duration: 4s;
}

.circles li:nth-child(4){
    left: 20%;
    width: 60px;
    height: 60px;
    animation-delay: 7s;
    animation-duration: 12s;
}

.circles li:nth-child(5){
    left: 25%;
    width: 20px;
    height: 20px;
    animation-delay: 1s;
}

.circles li:nth-child(6){
    left: 30%;
    width: 110px;
    height: 110px;
    animation-delay: 5s;
    animation-duration: 6s;
}

.circles li:nth-child(7){
    left: 35%;
    width: 150px;
    height: 150px;
    animation-delay: 7s;
    animation-duration: 3s;
}

.circles li:nth-child(8){
    left: 40%;
    width: 40px;
    height: 40px;
    animation-delay: 0s;
    animation-duration: 6s;
}

.circles li:nth-child(9){
    left: 45%;
    width: 15px;
    height: 15px;
    animation-delay: 2s;
    animation-duration: 35s;
}

.circles li:nth-child(10){
    left: 50%;
    width: 150px;
    height: 150px;
    animation-delay: 2s;
    animation-duration: 11s;
}

.circles li:nth-child(11){
    left: 55%;
    width: 100px;
    height: 100px;
    animation-delay: 5s;
    animation-duration: 9s;
}

.circles li:nth-child(12){
    left: 60%;
    width: 120px;
    height: 110px;
    animation-delay: 0s;
    animation-duration: 9s;
}

.circles li:nth-child(13){
    left: 65%;
    width: 90px;
    height: 120px;
    animation-delay: 5s;
    animation-duration: 6s;
}

.circles li:nth-child(14){
    left: 70%;
    width: 40px;
    height: 40px;
    animation-delay: 3s;
    animation-duration: 9s;
}

.circles li:nth-child(15){
    left: 75%;
    width: 50px;
    height: 50px;
    animation-delay: 6s;
    animation-duration: 4s;
}

.circles li:nth-child(16){
    left: 80%;
    width: 200px;
    height: 200px;
    animation-delay: 0s;
    animation-duration: 8s;
}

.circles li:nth-child(17){
    left: 85%;
    width: 60px;
    height: 60px;
    animation-delay: 3s;
    animation-duration: 7s;
}

.circles li:nth-child(18){
    left: 90%;
    width: 40px;
    height: 40px;
    animation-delay: 5s;
    animation-duration: 3s;
}

.circles li:nth-child(19){
    left: 95%;
    width: 90px;
    height: 120px;
    animation-delay: 6s;
    animation-duration: 10s;
}

*{
    margin: 0px;
    padding: 0px;
}


@keyframes animate {

    0%{
        transform: translateY(0) rotate(0deg);
        opacity: 1;
        border-radius: 0;
    }

    100%{
        transform: translateY(-1000px) rotate(720deg);
        opacity: 0;
        border-radius: 50%;
    }

}
/* body {
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
} */

@font-face {
    font-family: 'MapoPeacefull';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoPeacefullA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
body {
  font-family: 'MapoPeacefull';
}
</style>