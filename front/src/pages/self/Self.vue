<template>
    <Layout>
      <top-nav></top-nav>
      <Content>
        <div class="selfMainDiv">
          <self-avatar></self-avatar>
          <Menu mode="horizontal" theme="light" :active-name="active" @on-select="routeSelecter" >
            <MenuItem name="collectFolder">
              <Icon type="ios-construct" />
              <span v-if="userId == 0">我的收藏</span>
              <span v-else>TA的收藏</span>
            </MenuItem>
            <MenuItem name="followBook">
              <Icon type="ios-paper" />
              关注的书籍
            </MenuItem>
            <MenuItem name="myNote">
              <Icon type="ios-people" />
              我的笔记
            </MenuItem>
            <MenuItem name="follow">
              <Icon type="ios-construct" />
              关注的人
            </MenuItem>
          </Menu>
          <div style="width: 100%">
            <div style="min-height: 200px;width: 69%;float:left;display:inline-block;margin-top: 10px;background-color: #FFFFFF" class="Card">
              <router-view />
            </div>
            <div style="min-height: 200px;width: 30%;float:right;display:inline-block;margin-top: 10px;background-color: #FFFFFF" class="Card">
                <Card>
                    <p slot="title">用户贡献</p>
                    <p>关注了 <span style="color: darkred; font-size: large;">{{ user.followed }}</span></p>
                    <p>关注者 <span style="color: darkred; font-size: large;">{{ user.follower }}</span></p>
                    <p>创作了 <span style="color: darkred; font-size: large;">{{ user.noteNumber }}</span> 篇笔记</p>
                    <p>评论了 <span style="color: darkred; font-size: large;">{{ user.commentNumber }}</span> 本书</p>
                </Card>
            </div>
          </div>
        </div>
      </Content>
      <Footer style="margin: 0 auto">2011-2016 &copy; TalkingData</Footer>
    </Layout>
</template>

<script>
  import topNav from '../../components/TopNav.vue'
  import selfAvatar from './selfAvatar'
  import bus from '../../js/bus';
    export default {
        name: "self",
        components:{
          topNav,selfAvatar
        },
        data(){
          return{
              user:{
                  followed:10,
                  follower:10,
                  noteNumber:10,
                  commentNumber:11
              },
              active:'collectFolder'
          }
        },
        computed:{
          userId(){
            return this.$route.params.userId
          }
        },
        created(){
          this.$router.push({ name: 'collectFolder',params:{userId:this.userId}});
          this.getUserInfo();
        },
        methods:{
          routeSelecter(name){
            this.$router.push({name:name, params:{userId:this.userId}})
            this.active = name;
          },
          getUserInfo(){
            this.$axios.post('/api/getUserInfo/'+this.userId).then(response=>{
              this.user = response.data;
            });
          }
        },
        beforeRouteUpdate(to,from,next){
          if (to.params.userId != from.params.userId) {
            this.$axios.post('/api/getUserInfo/'+to.params.userId).then(response=>{
              this.user = response.data;
            });

            bus.$emit('RouteUpdate',to.params.userId);
          }
          next();
        }
    }
</script>

<style scoped>
  .ivu-layout-content{
    margin: 60px 0 0;
  }
  .selfMainDiv{
    width: 1000px;
    margin: 0 auto;
  }
</style>
