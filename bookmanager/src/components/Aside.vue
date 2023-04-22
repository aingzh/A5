<template>
    <div class="Avatar">
        <el-avatar :size="100" :src="'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"/>
    </div>
    <el-menu 
        active-text-color="#ffd04b" 
        background-color="#010154" 
        class="el-menu-vertical-demo" 
        default-active="2"
        text-color="#fff"
        router>
        <el-menu-item index="/PersonalSpace" >
            <el-icon>
                <User />
            </el-icon>
            <span>Personal Space</span>
        </el-menu-item>
        <el-menu-item index="/Home">
            <el-icon>
                <House />
            </el-icon>
            <span>Home</span>
        </el-menu-item>
        <el-menu-item index="/BorrowHistory">
            <el-icon>
                <Clock />
            </el-icon>
            <span>Borrow History</span>
        </el-menu-item>
        <el-menu-item index="/MyAppointment">
            <el-icon>
                <Tickets />
            </el-icon>
            <span>My Appointment</span>
        </el-menu-item>
    </el-menu>
</template>

<script lang = "ts">
import {User,House,Clock,Tickets,Search, SetUp} from '@element-plus/icons-vue'
export default{
    name:'Aside',
    components:{
        User,House,Clock,Tickets
    }
}
</script>
<style>
.Avatar{
    padding-top:60px;
    padding-left:75px;
}
</style>

