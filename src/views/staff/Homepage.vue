<template>
    <div style="font-size: xx-large;">
      此处展示首页四大功能
    </div>
    <router-link to="/3">“我的借阅”点击此处跳转</router-link><br>
    <router-link to="/ReturnBook1">“归还旧书”点击此处跳转</router-link><br>
    <router-link to="/staff">“预约书架”点击此处跳转</router-link><br>
    <router-link to="/1">“个人空间”点击此处跳转</router-link>
  </template>
  
  <script lang="ts">
  import {
    ElInput,
    ElButton,
    ElSelect,
  } from "element-plus"
  import { defineComponent } from "vue";
  
  export default defineComponent({
    name:"Homepage",
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
  