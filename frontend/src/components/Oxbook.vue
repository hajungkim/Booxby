<template>
  <div>
    <q-card class="my-card" id="card">
        <q-img :src="selectbook.img_url" id="worldcup_img" class="worldcup_img"
           v-bind:class="{'shadow_red':selectbook.emotion_score<24386 && selectbook.emotion_score>1540,
                        'shadow_orange':selectbook.emotion_score<1540 && selectbook.emotion_score>=778,
                        'shadow_yellow':selectbook.emotion_score<778 && selectbook.emotion_score>=296,
                        'shadow_green':selectbook.emotion_score<296 && selectbook.emotion_score>=1,
                        'shadow_blue':selectbook.emotion_score<1 && selectbook.emotion_score>=-240,
                        'shadow_navy':selectbook.emotion_score<-240 && selectbook.emotion_score>=-1169,
                        'shadow_purple':selectbook.emotion_score<-1169 && selectbook.emotion_score>=-29726
            }"     
        >
            <div class="absolute-bottom" v-if="textMode">
                <div class="text-h6" style="font-weight:bold;">{{ selectbook.title }}</div>
                <div class="text-subtitle2" style="font-weight:bold;">{{ selectbook.description }}</div>
            </div>
        </q-img>
    </q-card>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { onMounted, computed } from 'vue'
export default {
  props:{
    abc:{
      type:Object,
    },
  },
  setup () {
    const store = useStore()
    const textMode = computed(()=> store.getters['module/getTextMode'])
    const selectbook = computed(()=> store.getters['module/getselectOxbooks'])
    
    onMounted(()=>{
      const card = document.getElementById('worldcup_img')
      card.addEventListener('mouseenter',function(){
        store.commit('module/setTextMode',true)
      },true)
      card.addEventListener('mouseleave',function(){
        store.commit('module/setTextMode',false)
      },true)
    })
    return {
      selectbook,
      textMode,
    }
  }
}
</script>

<style scoped>
.shadow_red{
    box-shadow: 10px 10px 15px rgb(253, 185, 185);
}
.shadow_orange{
    box-shadow: 10px 10px 15px rgb(255, 202, 159);
}
.shadow_yellow{
    box-shadow: 10px 10px 15px rgb(254, 235, 168);
}
.shadow_green{
    box-shadow: 10px 10px 15px rgb(211, 236, 197);
}
.shadow_blue{
    box-shadow: 10px 10px 15px rgb(164, 194, 242);
}
.shadow_navy{
    box-shadow: 10px 10px 15px rgb(124, 138, 210);
}
.shadow_purple{
    box-shadow: 10px 10px 15px rgb(200, 167, 213);
}
.my-card{
  margin-top:100px;
  margin-left:130px;
  width:300px;
  height:400px;
  background-color:rgb(241, 237, 237);
}
.worldcup_img{
  position: absolute;
  width: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>