<template>
        <div style="">
        <el-row :gutter="2">
        <el-col :span="20"><el-input 
          v-model="input" 
          placeholder="Please input" />
        </el-col>
        <el-col :span="4"><el-button >Search</el-button>
        </el-col>
        </el-row>
        </div>
</template>

<script lang = "ts">
import {reactive} from 'vue'
export default{
    name:'Search',
    
    
}
</script>

