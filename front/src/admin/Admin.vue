<template>
  <div id="admin">
    <router-view v-if="isRouterAlive"/>
  </div>
</template>

<script>
export default {
    name: "Admin",
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
    }
}
</script>

<style scoped>
  #admin {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
</style>
