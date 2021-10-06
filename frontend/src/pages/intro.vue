<template>
    <div class="intro_container">
        <div class="intro_content">
            <div class="book">
                <div class="book__page"></div>
                <div class="book__page"></div>
                <div class="book__page"></div>
            </div>
            <div class="intro_logo_box">
                <q-img class="intro_logo" src="~assets/images/logo.png"/>
            </div>
            <div class="intro_word_box">
                <div class="intro_word"></div>
            </div>
        </div>
    </div>
</template>

<script>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'

export default {
    setup () {
        const router = useRouter()

        onMounted(() => {

            var words = ['감성기반 책 추천 서비스'],
                part,
                i = 0,
                offset = 0,
                len = words.length,
                forwards = true,
                skip_count = 0,
                skip_delay = 5,
                speed = 200

            var wordflick = setInterval(function(){
                    if (forwards) {
                        if(offset >= words[i].length){
                        ++skip_count
                        if (skip_count == skip_delay) {
                            forwards = false
                            skip_count = 0
                            }
                        }
                    }
                    else {
                        if(offset == 0){
                            forwards = true;
                            i++
                            offset = 0
                            if(i >= len){
                            i=0
                            } 
                        }
                    }
                    part = words[i].substr(0, offset);
                    if (skip_count == 0) {
                        if (forwards) {
                        offset++
                        }
                        else {
                        offset--
                        }
                    }
                    const word = document.getElementsByClassName('intro_word')[0]
                    word.innerText = part

                },speed)

            setTimeout(function() {
                clearInterval(wordflick)
                router.push('/login')
            }, 3500)
        })
        
        return{

        }
    }
}
</script>

<style scoped lang="scss">
$peter-river:#3498db;
$clouds:#ecf0f1;

.intro_container{
    display: flex;
    background: $peter-river;
    height:100vh;
    justify-content: center;
    align-items: center;
}
.intro_content{
    margin-top:100px;
    width:1000px;
    height:300px;
}
.intro_logo_box{
    width:300px;
    margin:0 auto;
}
.intro_logo{
}
// book animation
.book{
  transform: translateY(-50%);
  margin:0 auto;
  border:5px solid $clouds;
  width:120px;
  height:80px;
}
.book__page{
  position:absolute;
  left:50%;
  top:-5px;
  margin:0 auto;
  border-top:5px solid $clouds;
  border-bottom:5px solid $clouds;
  border-right:5px solid $clouds;
  background:$peter-river;
  width:60px;
  height:80px;
  transform-origin:0% 50%;
  animation:flip 1.2s infinite linear;
  animation-fill-mode:forwards;
  
  @for $i from 1 through 3 {
    &:nth-child(#{$i}) { 
      z-index:-$i;
      animation-delay:.5s*$i;
    }
  }
}
@keyframes flip {
  0%{
      transform: perspective( 600px )
      rotateY( -0deg );
  }
  
  5%{
    background:darken($peter-river,10%);
  }
  
  29.9%{
      background:darken($peter-river,10%);
  }
  30%{
      transform: perspective( 200px )
      rotateY( -90deg );
      background:$peter-river;
  }
  
  54.999%{
    opacity:1;
  }
  55%{
    opacity:0;
  }
  
  60%{
    transform: perspective( 200px )
    rotateY( -180deg );
    background:$peter-river;
  }
  
  100%{
    transform: perspective( 200px )
    rotateY( -180deg );
    background:$peter-river;
  }
}
// typing animation
@function longshadow($num,$color: white){
  $shadow: '';
  $i: 0;
  @for $i from 1 through $num{
    $shadow: $shadow + (1 * $i) + 'px' + ' ' + (1 * ($i * 2)) + 'px' + ' ' + $color;
    @if $i < $num{
      $shadow: $shadow + ', ';
    }
  }
  @return unquote($shadow);
}

@import url(https://fonts.googleapis.com/css?family=Montserrat:700);

$key: #55A4E9;

.intro_word_box{
    width:430px;
    margin:0 auto;
}
.intro_word{
  margin: auto;
  color: white;
  font: 700 normal 3em/1.5 'MapoPeacefull',sans-serif;
  text-shadow: longshadow(10,darken($key,10%));
}

@font-face {
    font-family: 'MapoPeacefull';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoPeacefullA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>