<template>
  <div>
    <div class="box">
        输入ISBN码 <el-input style="width: 250px" placeholder="请输入ISBN码" v-model="ISBN"></el-input>
    </div>
    <div class="box">
      选择图书类别
        <el-select placeholder="请选择" v-model="type">
          <el-option
            v-for="item in types"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            ></el-option>
        </el-select>
    </div>
    <div style="text-align: center;" class="box">
      <el-button type="info">创建图书</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import {
  ElInput,
  ElButton,
  ElSelect,
} from "element-plus"
import { defineComponent } from "vue";
import { types } from "../../staticData";

export default defineComponent({
  name:"AddBook",
  data(){
    return{
      types:[] as Array<any>,
      ISBN:"",
      type:""
    };
  },

  methods: {
    initData(){
      this.types = types;
    }
  },

  created(){
    this.initData()
  },

  components:{
    ElInput,
    ElButton,
    ElSelect,
  }

})



</script>

<style scoped>
.box{
  padding: 100px
}
</style>
