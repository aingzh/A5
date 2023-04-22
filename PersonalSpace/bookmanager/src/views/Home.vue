<template>
    <div>
        <div style="margin-top: 120px;margin-left: 200px;margin-right:200px">
            <Search></Search>
        </div>
        
    </div>
    <div>
        <Buttons></Buttons>
    </div>
</template>

<script lang = "ts">
import Search from "../components/Search.vue";
import Buttons from "../components/Buttons.vue";
export default{
    name:'Home',
    components:{Search,Buttons
        
    }
}
</script>
