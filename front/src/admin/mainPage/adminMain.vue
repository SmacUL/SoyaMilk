<template>
    <Layout>
      <top-nav></top-nav>

      <div class="mainDiv">
        <Layout :style="{padding: '0 50px'}">
          <Content :style="{padding: '0 0 0 0', minHeight: '280px', background: '#fff'}">
            <Layout>

              <Sider hide-trigger :style="{background: '#fff'}">
                <Menu active-name="/index/bookTable" theme="light" width="auto" :open-names="['1']" accordion  @on-select="selectMenuItem">
                  <MenuItem name="/index">修改书籍</MenuItem>
                  <MenuItem name="/index/addBook" >添加书籍</MenuItem>
                </Menu>
              </Sider>

              <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
                <!--更换的路由组件区域-->
                <router-view></router-view>
              </Content>
            </Layout>
          </Content>
        </Layout>
        <Footer class="layout-footer-center">2011-2016 &copy; 李航</Footer>
      </div>
    </Layout>
</template>

<script>
  import topNav from '../components/adminTopNav.vue'
  import addBook from '../components/addBook'
  import bookTable from '../components/bookTable'

    export default {
        name: "",
        data(){
          return{
              leftNav:{
                currentSubmenu:"",
                currentMenuItem:"",
              }
          }
        },
        methods:{
          selectSubmenu(name){
            this.leftNav.currentSubmenu = name;
          },
          selectMenuItem(name){
            this.leftNav.currentMenuItem = name;
            this.$router.push(name);
          },
        },
        components:{
            topNav,
            default:bookTable
        }
    }
</script>

<style scoped>
  .ivu-layout-sider{
    margin: 40px 0 0;
    background-color: #FFFFFF;
  }
  .ivu-layout-content{
    margin: 40px 0 0;
  }
  .mainDiv{
    width: 1600px;
    margin: auto;
  }
</style>
