<template>
  <div id="app">
    <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import HelloWorld from './components/ProductList.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  },
//   created() {
//   this.$nextTick(() => {
//     const path = this.$router.currentRoute.path;
//     const whiteList = ['/login', '/UserRegister']; // 允许未登录访问的路径

//     if (!sessionStorage.getItem('user') && !whiteList.includes(path)) {
//       this.$router.replace('/login');
//     }
//   });
// }

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
