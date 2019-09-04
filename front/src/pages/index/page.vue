// 该组件接受 分类参数 id, 并向后端请求该类数据。并分页显示
<template>
    <div class="pageDiv">
      <section>
        <ul>
          <li v-for="book in bookList">
            <Divider style="margin: 0px"/>
            <div style="width: 100%;height: 120px;">
              <div class="imgCard Card">
                <router-link :to="{ name:'book',params:{bookId:book.bookId}}" >
                  <img :src="book.bookCover" class="bookImg">
                </router-link>
              </div>
              <div style="float: left;display: inline-block;width: 700px;height: 100%;margin: 5px">
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
      </section>
    </div>
</template>

<script>
    export default {
      name: "",
      data(){
        return{
          bookList:"",
          subdivisionId:this.$route.params.subdivisionId,
          page:{
            current:1,
            total:100,
            pageSize:6
          }
        }
      },
      methods:{
        changePageNo(pageNo){       // 当改变页数时
          this.$axios.post('/api/book/getBookByPage/'+this.subdivisionId,{
            pageNo:pageNo,
            pageSize:this.page.pageSize,
          }).then(response => {
            this.bookList = response.data.list;
            this.page.total = response.data.totalCount;
          });
        }
      },
      created(){                  // 当这个组件第一次被加载时执行，刷新
        this.$axios.post('/api/book/getBookByPage/'+this.subdivisionId,{
          pageNo:this.page.current,
          pageSize:this.page.pageSize,
        }).then(response => {
          this.bookList = response.data.list;
          this.page.total = response.data.totalCount;
        });
      },
      watch:{
        '$route'(to,from){      //通过监听路由参数的变化，请求不同的数据。 非第一次
            this.subdivisionId = this.$route.params.subdivisionId;
            this.$axios.post('/api/book/getBookByPage/'+this.subdivisionId,{
                pageNo:1,
                pageSize:this.page.pageSize,
            }).then(response => {
              this.bookList = response.data.list;
              this.page.total = response.data.totalCount;
            });
        },
      },
    }
</script>

<style scoped>
  .pageDiv{
    width: 100%;
    margin: 0 auto;
  }
 .tabs-button > .ivu-divider-horizontal{
    margin: 10px 0px;
  }
  section{
    width: 100%;
  }
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
