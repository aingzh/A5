<template>
        <div style="">
        <el-row :gutter="2">
        <el-col :span="20"><el-input 
          type="text"
          v-model="inputText" 
          placeholder="Input bookname here" />
        </el-col>
        <el-col :span="4"><el-button >Search</el-button>
        </el-col>
        </el-row>
        </div>
</template>

<script lang = "ts">
import { method } from 'lodash';
import {reactive} from 'vue'
export default{
    name:'Search',
    data(){
      return{
        input:{
          id:'',
          bookName:'',
          inputText:""
        },
        bookList:[
          {id:'1',bookName:"Book1"},
          {id:'2',bookName:"Book2"},
          {id:'3',bookName:"Book3"},
          {id:'4',bookName:"Book4"}
        ],
        newBooklist:[]
      }
    }
}
</script>

