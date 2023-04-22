<template>
    <div style="font-size: xx-large;">
      归还书籍时，请确保您在本图书馆的自主还书分拣机或者人工还书柜台处。
    </div>
    <router-link to="/ReturnBook2">下一步-></router-link>
  </template>
  
  <script lang="ts">
  import {
    ElInput,
    ElButton,
    ElSelect,
  } from "element-plus"
  import { defineComponent } from "vue";
  
  export default defineComponent({
    name:"ReturnBook1",
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
  