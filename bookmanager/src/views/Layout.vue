<!--布局页-->
<template>
    <div class="container">
        <div class="left">
            <Aside></Aside>
        </div>
        <div class="right">
            <router-view></router-view>
        </div>
    </div>
</template>

<script lang ="ts">
import Aside from "../components/Aside.vue";
export default {
    name: "Home",
    components: {
        Aside,

    }
}
</script>

<style>
.container {
    width: 100vw;
    min-height: 100vh;
    display: flex;
}

.left {
    width: 250px;
    background-color: #010154
}

.right {
    flex: 1
}
</style>


  