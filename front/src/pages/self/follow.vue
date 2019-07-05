<!--
  该组件用于显示用户关注的用户, 本人显示取消关注
  完成
-->
<template>
  <div style="width: 100%;min-height: 200px">
    <div style="width: 100%;min-height: 400px" v-if="page.total == 0">
      <img src="../../assets/noContent.png">
    </div>
    <div v-else>
      <ul>
        <li v-for="(user,index) in users">
          <div style="float: left;margin: 15px 10px 15px 15px">
            <router-link :to="{name:'self',params:{userId:user.userId }}">
              <img :src="user.avatarUrl">
            </router-link>
          </div>
          <div style="float: left;margin: 10px">
            <router-link :to="{name:'self',params:{userId:user.userId }}">
              <div style="color:#2c3e50;font-size: 18px;font-weight: 600;">{{ user.name }}</div>
            </router-link>
            <div style="margin: 10px 0px;font-size: 15px;color: #646464">{{ user.selfStyle }}</div>
            <div style="font-size: 15px;color:#8590a6;">{{ user.noteNumber }}笔记&nbsp;·&nbsp;{{ user.fansNumber }}关注者</div>
          </div>
          <div v-if="userId == 0" style="float: right;margin: 40px 20px">
            <Button class="button" type="error" size="large" @click="cancelFollow(user.userId,index)">取消关注</Button>
          </div>
        </li>
      </ul>
      <Divider/>
      <div style="width: 100%;height: 50px;text-align: center">
        <Page :current="page.current" :total="page.total" :page-size="page.pageSize" simple  @on-change="changePageNo"/>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name:'follow',
    data(){
      return{
        userId:this.$route.params.userId,
        users:[
          {
            userId:'',
            name:'渤海小莉',
            selfStyle:'公号：2300年封建脉络的百场转折之战',
            noteNumber:'8',
            fansNumber:'100',
            avatarUrl:'../../assets/touxiang.jpg'
          }
        ],
        page:{
          current:1,
          pageSize:6,
          total:100
        }
      }
    },
    methods:{
      changePageNo(pageNo){
        this.$axios.post('/api/loadMyFollow/'+this.userId,{
          pageNo:pageNo,
          pageSize:this.page.pageSize,
        }).then(response=>{
          this.users = response.data.list;
          this.page.total = response.data.totalCount;
        });
      },
      cancelFollow(userId,index){
        if ($('.button > span').eq(index).text()== "取消关注") {
          this.$axios.post('/api/unfollow/'+userId).then(response =>{
            if (response.data){
              this.$Message.success("取消关注成功！");
              $('.button > span').eq(index).text("关注此人");
              $('.button').eq(index).removeClass("ivu-btn-error");
              $('.button').eq(index).addClass("ivu-btn-success");
            }
          });
        }else {
          this.$axios.post('/api/follow/'+userId).then(response =>{
            if (response.data){
              this.$Message.success("关注成功！");
              $('.button > span').eq(index).text("取消关注");
              $('.button').eq(index).removeClass("ivu-btn-success");
              $('.button').eq(index).addClass("ivu-btn-error");
            }
          });
        }
      }
    },
    created(){
        this.changePageNo(1);
    }
  }
</script>
<style scoped>
  li{
    width: 100%;
    height: 120px;
    border-bottom: 1px #f6f6f6 solid;
  }
  img{
    width: 60px;
    height: 60px;
    border-radius: 4px;
  }
</style>
