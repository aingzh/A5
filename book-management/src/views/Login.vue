
<template>

    <div class="login">
    <div class="mylogin" align="center">
    <h1>log in</h1>
    <el-form @submit.native.prevent="login">
      <el-form-item label="username">
        <el-input v-model="username"></el-input>
      </el-form-item>
      <el-form-item v-if="error.name" error>
        <span>{{ error.name }}</span>
      </el-form-item>
      <el-form-item label="password">
        <el-input type="password" v-model="password"></el-input>
      </el-form-item>
      <el-form-item v-if="error.pwd" error>
        <span>{{ error.pwd }}</span>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" native-type="submit">log in</el-button>
      </el-form-item>
      <div class="unlogin">

          <router-link :to="{ path: '/register' }">
            <a href="register.vue" target="_blank" align="right">register</a>
          </router-link>
        </div>
  <router-link to="/staff">如登陆后为工作人员点击此处跳转</router-link>
</el-form>
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

export default defineComponent({
  name:"Login",
  data(){
    return{
      username: '',
      password: '',
      correctUsername: 'correct_username',
      correctPassword: 'correct_password',
      recaptchaSiteKey: 'YOUR_RECAPTCHA_SITE_KEY',
      error : {
          name: '',
          pwd : ''
        }
      
    };
  },

  methods: {
    login() {
      // TODO: Implement login logic
      if (this.username !== 'correct_username' || this.password !== 'correct_password') {

        this.error.pwd ='The password is incorrect'
        return;
      }
      console.log('Logging in...')
    },


    initData(){
      
    }
  },

  created(){
    this.initData()
  },

  components:{

    ElInput,
    ElButton,
    ElSelect
  }

})



</script>

<style scoped>

.box{
  padding: 100px
}

h1 {
  font-size: 2rem;
  text-align:left;
  position: absolute;
  top: 10%;
  left: 50%;
  transform: translate(-50%, -50%);
}
form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 2rem;
}
label {
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
}
input {
  padding: 0.5rem;
  font-size: 1.2rem;
  border-radius: 0.5rem;
  border: none;
  margin-bottom: 1rem;
}
button {
  padding: 0.5rem 1rem;
  font-size: 1.2rem;
  border-radius: 0.5rem;
  border: none;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}
button:hover {
  background-color: #0069d9;
}



.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: url('~@/assets/images/back3.jpg');

}

button[type="submit"] {
  padding: 0.5rem 1rem;
  font-size: 1.2rem;
  border-radius: 0.5rem;
  border: none;
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
  width: 100%;
  box-sizing: border-box;
}
button[type="submit"]:hover {
  background-color: #0069d9;
}

input[type="text"], input[type="password"] {
  padding: 0.5rem;
  font-size: 1.2rem;
  border-radius: 0.5rem;
  border: none;
  margin-bottom: 1rem;
  width: 100%;
  box-sizing: border-box;
}

.mylogin{
  width: 240px;
  height: 280px;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  padding: 50px 40px 40px 40px;
  box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
  opacity: 1;
  background: linear-gradient(
    230deg,
    rgba(53, 57, 74, 0) 0%,
    rgb(149, 189, 232) 100%
  );
}

</style>
