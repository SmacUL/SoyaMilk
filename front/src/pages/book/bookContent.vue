<template>
  <div style="width: 100%">
    <div style="width: 100%;min-height: 400px" v-if="total == 0">
      <img src="../../assets/noContent.png">
    </div>
    <div v-else>
    <ul>
      <li v-for="comment in commentList">
        <div style="width: 100%;height: 100%;">
          <h2>{{ comment.title}}</h2>
          <div style="width: 100%;height:33px;margin: 5px">
            <router-link :to="{ name:'self',params:{userId:comment.pageUser.userId}}" >
              <div style="display: inline-block;width:33px;height: 100%;float: left;">
                <img :src="comment.pageUser.avatarUrl">
              </div>
            </router-link>
            <div style="display: inline-block;width:500px;height: 100%;margin: 0px 10px;float: left">
              <div style="display: inline-block;font-size: 16px;line-height: 1.1;font-weight: 600;">{{ comment.pageUser.name }}</div>
              <div style="display: inline-block;font-size: 14px;color: #1a1a1a;line-height: 1.6">,&nbsp;&nbsp;{{ comment.pageUser.selfStyle }}</div>
              <div style="display: inline-block;margin: 0px 10px" >
                <Rate v-model="comment.rate" allow-half show-text disabled><span style="color: #f5a623">{{ comment.rate }}</span></Rate>
              </div>
            </div>
          </div>
          <div style="font-size: 15px;line-height: 1.6;color: #646464;font-weight: 500;margin-left: 50px">{{ comment.comment }}</div>
        </div>
      </li>
    </ul>
      <Divider/>
      <div style="width: 100%;height: 50px;text-align: center">
        <Page :current="current" :total="total" :page-size="pageSize" simple  @on-change="changePageNo"/>
      </div>
    </div>
  </div>
</template>

<script>
  import bus from '../../js/bus';

  export default {
    data(){
      return {
        commentList:'',
        current:1,
        total:100,
        pageSize:5,
        haveMessage:false
      }
    },
    computed:{
      bookId(){
        return this.$route.params.bookId;
      }
    },
    methods:{
      changePageNo(PageNo){
          this.current = PageNo;
      },
      loadComment(pageNo){
        this.$axios.post('/api/comment/loadComment/'+this.bookId, {
          pageNo:pageNo,
          pageSize:this.pageSize,
        }).then(response => {
          this.commentList = response.data.list;   // 记得改
          this.total = response.data.totalCount;
        });
      },
    },
    created(){
      this.loadComment(1);

      bus.$on('commentBook',params=>{
        this.haveMessage = params;
      });
    },
    watch:{
      current(){
        this.loadComment(this.current);
      },
      haveMessage(){
        this.loadComment();
      }
    },
    // beforeRouteEnter(to,from,next){
    //     next(vm =>{
    //       vm.bookId = from.params.bookId;
    //     });
    // },

  }
</script>

<style scoped>
  ul{
    width: 100%;
  }
  li{
    width: 100%;
    min-height: 100px;
    padding: 16px 20px;
    border-bottom: 1px #f6f6f6 solid;
  }
  h2{
    font-size: 18px;
  }
  img{
    width: 100%;
    height: 100%;
  }
</style>
