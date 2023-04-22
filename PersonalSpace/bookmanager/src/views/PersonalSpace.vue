<template>
    <div class="container">
  <div class="box">
    <!-- 头像 -->
    <div class="top">
      <div class="data">
        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      </div>

      <div class="data">
        <!-- 不可修改 -->
        <span class="one">UID</span>
        <el-text class="mx-1" size="Large">:123456</el-text>
      </div>
      <!-- 点击可修改 -->
      <div class="data"><el-link href="#" target="_blank">用户昵称</el-link></div>
      <!-- 点击可修改 -->
      <div class="data"><el-link href="#" target="_blank">邮箱:123123@qqcom</el-link></div>
    </div>

    <div class="foot">
        <div class="left">
          <span><el-link :icon="Edit">借阅历史</el-link></span>
          <!-- 点击应跳转至借阅历史界面 -->
        </div>
        <div class="right">
          <span><el-link :icon="IconView">权限查询</el-link></span>
        </div>
        <div class="data">
          <el-button plain>账号注销</el-button>
        </div>
    </div>


  </div>

</div>
</template>

<script lang = "ts">
import { Edit, View as IconView } from '@element-plus/icons-vue'
export default{
    name:'PersonalSpace',
    data(){
    return{
      Edit,
      IconView
    };
  },

}
</script>


<style scoped>
.container{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  /* background-color: black; */
}
.box{
  width: 60%;
  height: 70%;
  margin: auto;
  background-color:rgb(235, 246, 250,0.1);
  vertical-align: auto;
  /* border-style: inset; */
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.enter {
  display: inline-block;
  margin-left: 35px;
}

.data{
  /* background-color: pink; */
  padding: 20px;
  text-align: center
}

.left{
  display: inline-block;
  width: 100px;
  height: 50px;
  /* background-color: green; */
  margin-left: 170px;
  margin-bottom: 30px;
}

.right{
  display: inline-block;
  width: 100px;
  height: 50px;
  /* background-color: green; */
  margin-left: 180px;
  margin-bottom: 30px;
}

span{
  display: inline-block;
  vertical-align: middle
}

</style>