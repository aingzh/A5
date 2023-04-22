<!--布局页-->
<template>
    <div class="container">
        <el-container >
            <el-aside width="200px" >
                <Aside></Aside>
            </el-aside>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </div>
</template>

<script lang ="ts">
import Aside from "../components/Aside.vue";
export default{
    name:"Home",
    components:{
        Aside,
        
    }
}
</script>

<style>
.container{
    width: 100vw;
    height: 100vh;
    display: flex;
    background:rgb(211, 211, 211);
}
.el-aside{
    background-color: #010154
}
</style>


  