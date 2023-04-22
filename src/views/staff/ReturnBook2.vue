<template>
    <div style="text-align: center;" class="box">
        请输入ISBN码 <el-input style="width: 250px" placeholder="请输入ISBN码" v-model="ISBN"></el-input>
    </div>
    <div class="main">
      <router-link to="/ReturnBook3">下一步-></router-link>
    </div>
    <!-- <div class="main">
      <router-link to="/ReturnBook2">继续还书</router-link>
    </div> -->
  </template>
  
  <script lang="ts">
  import {
    ElInput,
    ElButton,
    ElSelect,
  } from "element-plus"
  import { defineComponent } from "vue";
  
  export default defineComponent({
    name:"ReturnBook2",
    data(){
      return{
        ISBN:"",        
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

  .main{
    text-align: center;
    background-color: #fff;
    border-radius: 20px;
    width: 300px;
    height: 350px;
    position: absolute;
    left: 50%;
    top: 60%;
    transform: translate(-50%,-50%);
}


  </style>

  