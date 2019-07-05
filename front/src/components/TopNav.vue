<template>
  <Header id="header">
    <div class="headerCenter">
      <div class="layout-logo">
        <img src="../assets/logoDouzhi.png" @click="returnIndex">
      </div>

      <div style="">
        <div class="searchDiv">
          <Input search enter-button size="large" placeholder="我只是一个装饰品" />
        </div>

        <Menu mode="horizontal" theme="light" active-name="1" style="display: inline-block;float: right" @on-select="entrySelf">
          <MenuItem name="1">
            <Avatar :src="user.avatarUrl" size="large">U</Avatar>
          </MenuItem>
          <!--<MenuItem name="2">-->
            <!--<Icon type="ios-people"/>-->
            <!--个人中心-->
          <!--</MenuItem>-->
        </Menu>
      </div>
    </div>
  </Header>
</template>

<script>

    export default {
        name: "",
        data(){
          return{
            userId:0,
            user:''
          }
        },
        methods:{
          entrySelf(name){
            if (name == '1') {
              this.$router.push({ name:'self',params:{userId: this.userId}})
            }
          },
          returnIndex(){
              this.$router.push({ path:"/index" });
          }
        },
        created(){
          this.$axios.post('/api/loadUser/0').then(response=>{
            this.user = response.data;
          });
        }
    }
</script>

<style scoped>
  .headerCenter{
    width: 1000px;
    margin: 0 auto;
    display: inline-block;
  }
  .searchDiv{
    width: 450px;
    display: inline-block;
    padding: 10px;
    margin: 0 auto;
  }
  .layout-logo{
    width: 123px;
    height: 31px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    left: 0px;
    top: 10px;

  }
  .layout-nav{
    width: 420px;
    margin: 0 auto;
    margin-right: 20px;
  }
  .ivu-layout-header{
    margin-top: 0px;
    padding: 0;
    background-color: #FFFFFF;
    height: 60px;
    border-bottom: #e8eaec 1px solid;
    position: fixed;
    top: 0px;
    width: 100%;
    z-index: 100;
    text-align: center;
  }
  img{
    width: 140px;
    height: 45px;
  }
</style>
