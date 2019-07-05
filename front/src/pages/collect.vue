<script>


</script>

<template>
  <Layout>
    <top-nav></top-nav>
    <Content>
        <div class="collectDiv">
            <div class="left">
              <div style="width: 100%;height: 100px;background-color: #FFFFFF">
                  <div>
                    <Button size="large" type="text" style="color: #3399ff"> <<返回我的收藏 </Button>
                  </div>
                  <div style="font-size: 18px;font-weight: 600;margin: 0px 15px">{{ collectName }}</div>
                <div>
                  <Button size="large" type="text">编辑</Button>
                  <span style="font-size: 14px;font-weight: 600">·</span>
                  <Button size="large" type="text">删除</Button>
                </div>
              </div>
              <div class="noteDiv">
                <ul>
                  <li>
                      <!--该部分可共享组件-->
                  </li>
                </ul>
              </div>
            </div>
            <div class="right">

            </div>
        </div>
    </Content>
    <Footer class="layout-footer-center">2011-2016 &copy; TalkingData</Footer>
  </Layout>
</template>

<script>
  import topNav from '../components/TopNav'
  export default {
      name:'collect',
      components:{
        topNav
      },
      data(){
        return{
          collectName:'英语',
        }
      },
  }
</script>

<style scoped>
  .collectDiv{
    width: 1000px;
    margin: 0 auto;
    margin-top: 70px;
    min-height: 200px;
  }
  .left{
    width: 69%;
    height: 100%;
    display: inline-block;

  }
  .right{
    width: 30%;
    height: 100%;
    display: inline-block;
    background-color: #FFFFFF;
  }
  .noteDiv{
      margin-top: 10px;
      width: 100%;
      min-height: 200px;
      background-color: #FFFFFF;
  }
</style>
