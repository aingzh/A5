<template>
  <div>
    <div class="box">
      <el-input style="width: 250px" placeholder="请输入书名/ISBN码" v-model="search"></el-input>
    </div>
    <div v-for="(item, index) in books" class="book">
      <book
        :info="item"
      />
    </div>
  </div>
</template>

<script lang="ts">
import {
  ElInput,
} from "element-plus"
import { defineComponent } from "vue";
import book from './components/book.vue'

export default defineComponent({
  name:"ManageBook",
  data(){
    return{
      search:"",
      books:[
        {
          url:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
          name: "红楼梦",
          type: "学术界",
          ISBN: 111111,
          searchNumber: 11111,
        }, {
          url:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
          name: "水浒传",
          type: "学术界",
          ISBN: 222222,
          searchNumber: 22222,
        }
      ]
    };
  },

  methods: {
    
  },

  created(){
    
  },

  components:{
    ElInput,
    book
  }

})



</script>

<style scoped>
.box{
  padding: 50px;
  text-align: center
}
.book{
  margin: 50px;
  text-align: center;
  border:1px solid #DCDCDC;
  height: 200px;
}
</style>
