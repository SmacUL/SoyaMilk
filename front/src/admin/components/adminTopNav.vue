<template>
  <Header>
    <div class="searchDiv">
      <Input search enter-button size="large" placeholder="Enter something..." />
    </div>
    <div style="float: right;">
      <Menu mode="horizontal" theme="light" active-name="1">
        <MenuItem name="3">
          <Avatar>U</Avatar>
        </MenuItem>
        <MenuItem name="1">
          <Icon type="ios-paper"/>
          个人中心
        </MenuItem>
        <MenuItem name="2">
          <Icon type="ios-people"/>
          个人中心
        </MenuItem>
      </Menu>
    </div>
  </Header>
</template>

<script>
  export default {
    name: ""
  }
</script>

<style scoped>
  .searchDiv{
    width: 300px;
    display: inline-block;
    margin: auto;
    padding-top: 15px;
  }
  .layout-logo{
    width: 100px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
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
    width: 100%;
    z-index: 1;
    /*margin-bottom: 10px;*/
  }
</style>
