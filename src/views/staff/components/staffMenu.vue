<template>
  <el-container class="home-container">
    <el-header class="top" />
      <el-container>
        <el-aside class="side">
          <div style="margin-left: 50px;">
            <el-avatar :src="imageUrl" :size=100></el-avatar>
            <el-menu
              :default-active="this.$route.name"
              router
              class="el-menu-vertical"
              @open="handleOpen"
              @close="handleClose"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b"
            >
              <el-sub-menu index="1">
                <template #title>书库管理</template>
                  <el-menu-item-group>
                    <el-menu-item index="/addBook">添加图书</el-menu-item>
                    <el-menu-item index="/manageBook">管理图书</el-menu-item>
                    <el-menu-item index="/searchBook">借阅查询</el-menu-item>
                  </el-menu-item-group>
              </el-sub-menu>
              <el-menu-item index="2">
                <span slot="title">借阅</span>
              </el-menu-item>
              <el-menu-item index="3">
                <span slot="title">账号</span>
              </el-menu-item>
            </el-menu>
          </div>
        </el-aside>
        <el-main><router-view /></el-main>
      </el-container>
  </el-container>
</template>

<script lang="ts">
import {
  ElInput,
  ElButton,
  ElSelect,
  ElMenu,
} from "element-plus"
import { defineComponent } from "vue";

export default defineComponent({
  name:"staffMenu",
  data(){
    return{
      imageUrl:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
    };
  },

  methods: {
    handleOpen(key:any, keyPath:any) {
      console.log(key, keyPath);
    },
    handleClose(key:any, keyPath:any) {
      console.log(key, keyPath);
    }
  },

  created(){
    
  },

  components:{
    ElInput,
    ElButton,
    ElSelect,
    ElMenu,
  }
})



</script>

<style scoped>
.top{
  background-color: #545c64;
  width: 100%;
  height: 10vh;
}

.side{
  background-color: #545c64;
  height: 88vh;
  width: 15%;
}

.home-container{
  height: 100%;
}
</style>