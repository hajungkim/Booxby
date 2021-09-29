<template>
<div>
    <div style="width:60%">
      <wordcloud
      font="fantasy"
      :fontSize="size"
      :data="words"
      nameKey="name"
      valueKey="value"
      :showTooltip="true">
      </wordcloud>
  </div>
</div>
</template>

<script>
import wordcloud from 'vue-wordcloud'
import axios from 'axios'

export default {
  components: {
    wordcloud
  },
  data() {
    return {
      size: [50,80],
      words : [],
    }
  },
  created() {
    this.getwords()
  },
  methods: {
    getwords() {
      axios.get('http://192.168.0.14:5000/data/nouns-count' + '/9788970940540')
      .then(res => {
        console.log(res)
        for (let i = 0; i < res.data.length; i++) {
          let j = {
            "name": '',
            "value": ''
          }
          j.name = res.data[i][0]
          j.value = res.data[i][1]
          this.words.push(j)
        }
      })
    }
  }
};
</script>
