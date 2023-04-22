<template>
    <div class="rightContainer">
        <div class="top">

        </div>
        <div style="margin-left:150px;">
            <Search></Search>
        </div>
        <div>
            <Buttons></Buttons>
        </div>
    </div>
</template>

<script lang = "ts">
import Search from "../components/Search.vue";
import Buttons from "../components/Buttons.vue";
export default {
    name: 'Home',
    components: { Search, Buttons }
}
</script>

<style>
.rightContainer{
    width:100%;
    height: 100%;
    background:url("../assets/background.png");
    background-size: 100%,100%;
}
.top{
    height:80px;
}
</style>

