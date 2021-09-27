<template>
  <div>
    <q-card v-if="change" class="my-card" id="card">
        <q-img src="~assets/images/html.jpg" class="worldcup_img">
            <div class="absolute-bottom" v-if="textMode">
                <div class="text-h6" style="font-weight:bold;">{{ title }}</div>
                <div class="text-subtitle2" style="font-weight:bold;">{{ description }}</div>
            </div>
        </q-img>
    </q-card>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { onMounted, computed, toRefs, watchEffect } from 'vue'
export default {
  props:{
    abc:{
      type:Object,
    },
    change:{
      type:Boolean,
    }
  },
  setup (props) {
    const store = useStore()
    const textMode = computed(()=> store.getters['module/getTextMode'])
    onMounted(()=>{
      const card = document.getElementById('card')
      card.addEventListener('mouseover',function(){
        store.commit('module/setTextMode',true)
      },true)
      card.addEventListener('mouseout',function(){
        store.commit('module/setTextMode',false)
      },true)
    })
    let title = ''
    let description = ''
    // let aaa = props.abc
    watchEffect(()=>{
      title = store.getters['module/getselectOxbooks'].title
      console.log(title)
      console.log(props.change,'~~~')
      // props.change = !props.change
      description = store.getters['module/getselectOxbooks'].description
      console.log(description)
    })
    return {
      title,
      description,
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
</style>