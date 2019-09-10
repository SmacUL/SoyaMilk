<!-- 用来显示 个人与他人关注的书籍, 个人空间可以取消关注 -->
<template>
  <div style="width: 100%;min-height: 200px">
    <div style="width: 100%;min-height: 400px" v-if="page.total == 0">
      <img src="../../assets/noContent.png">
    </div>
    <div v-else>
      <ul>
        <li v-for="(book,index) in bookList">
          <Divider style="margin: 0px"/>
          <div style="width: 100%;height: 120px;">
            <div class="imgCard Card">
              <router-link :to="{ name:'book',params:{bookId:book.bookId}}" >
                <img :src="book.bookCover" class="bookImg">
              </router-link>
            </div>
            <div style="float: left;display: inline-block;width: 570px;height: 100%;margin: 5px">
              <div v-if="userId == 0" style="float: right;margin: 5px 20px">
                <Button class="button" type="error" @click="cancelFollow(book.bookId,index)">取消关注</Button>
              </div>
              <router-link :to="{ name:'book',params:{bookId:book.bookId}}" >
                <h3 style="font-size: 18px;margin: 5px;color: #2c3e50;">{{book.bookName}}</h3>
              </router-link>
              <p style="font-size: 15px;margin: 3px 5px">{{ book.bookContributors }}</p>
              <div class="p1"  v-html="book.bookBriefIntroduction">
                {{ book.bookBriefIntroduction }}
              </div>
            </div>
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
    name:'followBook',
    data(){
      return{
        userId:this.$route.params.userId,
        bookList:'',
        page:{
            current:1,
            pageSize:6,
            total:100
        },
      }
    },
    methods:{
        changePageNo(pageNo){
          this.$axios.post('/api/book/getMyFollowBook/'+this.userId,{
            pageNo:pageNo,
            pageSize:this.page.pageSize,
          }).then(response =>{
              this.bookList = response.data.list;
              this.page.total = response.data.totalCount;
          });
        },
        cancelFollow(bookId,index){
          if ($('.button > span').eq(index).text() == "取消关注") {
            this.$axios.post('/api/book/unfollow/'+bookId).then(response =>{
              if (response.data){
                this.$Message.success("取消关注成功！");
                $('.button > span').eq(index).text("关注书籍");
                $('.button').eq(index).removeClass("ivu-btn-error");
                $('.button').eq(index).addClass("ivu-btn-success");
              }
            });
          }else {
            this.$axios.post('/api/book/follow/'+bookId).then(response =>{
              if (response.data){
                this.$Message.success("关注成功！");
                $('.button > span').eq(index) .text("取消关注");
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
  img{
    width: 100%;
    height: 100%;
  }
  .imgCard{
    float: left;
    display: inline-block;
    width: 80px;
    height: 100%;
    margin: 10px;
  }

  .p1{
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    font-size: 14px;
    font-weight: 500;
    color: #8590a6;
    margin: 5px;
    max-height: 60px
  }
</style>
