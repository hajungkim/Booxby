<template>
  <div>
    <q-card class="my-card" id="card">
        <q-img :src="selectbook.img_url" class="worldcup_img">
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
      const card = document.getElementById('card')
      card.addEventListener('mouseover',function(){
        store.commit('module/setTextMode',true)
      },true)
      card.addEventListener('mouseout',function(){
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
.my-card{
    margin-top:30px;
    margin-left:80px;
    width:375px;
}
.worldcup_img{
  width:375px;
  height:500px;
}
</style>