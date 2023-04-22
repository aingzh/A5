<template>
    <div style="font-size: xx-large;">
      点击确认归还，系统核对后会更新您的借阅状态。
    </div>
    <router-link to="/2">确认归还，<br>返回首页</router-link>
  </template>
  
  <script lang="ts">
  import {
    ElInput,
    ElButton,
    ElSelect,
  } from "element-plus"
  import { defineComponent } from "vue";
  
  export default defineComponent({
    name:"ReturnBook3",
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
  