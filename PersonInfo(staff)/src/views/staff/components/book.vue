<template>
 <div class="container clearfix">
  <div class="left">
    <el-image :src="info.url" class="image"/>
  </div>
  <div class="content">
    <div class="item">书名：{{ info.name }}</div>
    <div class="item">类别：{{ info.type }}</div>
    <div class="item">ISBN码：{{ info.ISBN }}</div>
    <div class="item">索书号：{{ info.searchNumber }}</div>
    
  </div>
  <div class="right">
    <el-col>
      <el-button type="info" class="button"> 更改图书信息</el-button>
    </el-col>
    <el-col>
      <el-button type="info" class="button"> 删除图书信息</el-button>
    </el-col>
  </div>
</div>

</template>


<script lang="ts">
import {
  ElImage,
  ElCol
} from "element-plus"
import { defineComponent } from "vue";


export default defineComponent({
  name:"ManageBook",
  props:['info'],
  data(){
    return{
    };
  },

  methods: {
    
  },

  created(){
    
  },

  components:{
    ElImage,
    ElCol
  }

})



</script>

<style scoped>
.left {
  /* 1. 左列容器开启左浮动 */
  margin:10px;
  float: left;
  width: 10%;
  border:1px solid #DCDCDC;
  height:180px
}

.image{
  width:100%;
  height:180px
}

.item{
  margin:20px;
}
.content {
  /* 自适应元素设置 overflow 会创建一个BFC 完成自适应 */
  overflow: hidden;
  float: left;
}
.right {
  /* 2. 右列容器开启右浮动 */
  float: right;
}
.button{
  margin: 30px;
}
</style>
