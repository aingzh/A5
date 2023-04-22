<template>
  
  <div>
    <h1>个人信息</h1>
    <div class="box">
      <span class="one">姓名</span>
      <div class="enter">
        <el-input style="width: 250px" disabled placeholder="姓名" />
      </div>
    </div>
    

    <div class="box">
      <span class="one">工号</span>
      <div class="enter">
        <el-input style="width: 250px" disabled placeholder="工号" />
      </div>
    </div>

    <div class="box">
      <span class="one">入馆日期</span>
      <div style="display: inline-block;">
        <el-input style="width: 250px" disabled placeholder="2023/4/15" />
      </div>
    </div>
    
    <div class="box">
      <span class="one">密码</span>
      <div class="enter">
        <el-input
        style="width: 250px"
        type="password"
        placeholder="Please input password"
        show-password
        v-model="password"
        />    
        <el-button type="info" style="margin-left: 10px;">修改密码</el-button>
      </div>
    </div>

    <div class="box">
      <span class="one">电话</span>
      <div class="enter">
        <el-input style="width: 250px" placeholder="1234567890" v-model="phoneNum" />
      </div>
    </div>


    <div class="box">
      <span class="one">邮箱</span>
      <div class="enter">
        <el-input style="width: 250px" placeholder="1234567890" v-model="youXiang"/>
      </div>
    </div>

    <div class="box">
      <span class="one">微信</span>
      <div class="enter">
        <el-input style="width: 250px" placeholder="1234567890" v-model="weChat" />
      </div>
    </div>

    <div class="box">
      <span class="one">twitter</span>
      <div style="display: inline-block; margin-left: 15px;">
        <el-input style="width: 250px" placeholder="1234567890" v-model="twitter"/>
      </div>
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
  name:"PersonInfo",
  data(){
    return{
      types:[] as Array<any>,
        password:"",
      phoneNum:"",
      youXiang:"",
      weChat:"",
      twitter:""
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
  padding: 18px;
  margin-left:100px;
}

h1{
  padding-left:30px;
  display: block;
  width: 200px;
  height: 50px;
  line-height: 70px;
  font-size: 30px;
}

.enter {
  display: inline-block;
  margin-left: 35px;
}
</style>
