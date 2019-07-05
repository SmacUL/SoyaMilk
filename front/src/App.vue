<template>
  <div id="app">
    <router-view v-if="isRouterAlive"/>
  </div>
</template>

<script>
  // document.documentElement.style.overflowX='hidden';
export default {
  name: 'App',
  provide (){
    return {
      reload:this.reload
    }
  },
  data(){
    return {
      isRouterAlive:true
    }
  },
  methods:{
    reload (){
      this.isRouterAlive = false;
      this.$nextTick(function(){
        this.isRouterAlive = true
      })
    }
  },
  watch:{
    $route:function () {
      // this.isRouterAlive = false;
      // this.$nextTick(function(){
      //   this.isRouterAlive = true
      // })
      // console.log(111);
      // const marginTop = window.screen.height * 0.2;
      // $("#loginDiv").css("margin-top",marginTop);
    }
  },
}
</script>

<style>
  body{
    background-color: #f6f6f6;
    font-family: -apple-system,BlinkMacSystemFont,Helvetica Neue,PingFang SC,Microsoft YaHei,Source Han Sans SC,Noto Sans CJK SC,WenQuanYi Micro Hei,sans-serif
  }
  ul,li{
    list-style-type:none;
  }
#app {
  margin: 0 auto;
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /*text-align: center;*/
  color: #2c3e50;
}
.Card{
  margin-bottom: 10px;
  overflow: hidden;
  border-radius: 2px;
  box-shadow: 0 1px 3px rgba(26,26,26,.1);
  box-sizing: border-box;
}
  .ellipsis{
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
  }


</style>
