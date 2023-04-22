<template>
  <div style="font-size: xx-large;">
    此处写登陆页面
  </div>
  <router-link to="/staff">如登陆后为工作人员点击此处跳转</router-link>
</template>

<script lang="ts">
import {
  ElInput,
  ElButton,
  ElSelect,
} from "element-plus"
import { defineComponent } from "vue";

export default defineComponent({
  name:"Login",
  data(){
    return{
      
    };
  },

  methods: {
    initData(){
      
    }
  },

  created(){
    this.initData()
  },

  components:{
    ElInput,
    ElButton,
    ElSelect
  }

})



</script>

<style scoped>
.box{
  padding: 100px
}
</style>
