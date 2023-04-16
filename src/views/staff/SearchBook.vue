<template>
  <div class="box">
    <el-input style="width: 250px" placeholder="请输入ISBN码" v-model="ISBN"></el-input>
  </div>
    
</template>

<script lang="ts">
import {
  ElInput,
} from "element-plus"
import { defineComponent } from "vue";


export default defineComponent({
  name:"SearchBook",
  data(){
    return{
      ISBN:"",
    };
  },

  methods: {
    
  },

  created(){
    
  },

  components:{
    ElInput,
  }

})



</script>

<style scoped>
.box{
  padding: 200px;
  text-align: center
}
</style>
