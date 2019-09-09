
<!-- 书本页面 -->

<template>
    <Layout>
      <top-nav></top-nav>
      <div class="mainDiv">
      <Layout>
        <Content>
          <div style="width: 100%;height: 240px;margin-bottom: 10px;background-color: #FFFFFF" class="Card">
            <div style="width: 100%;height: 170px;">
              <div class="imgDiv">
                <img :src="book.bookCover">
              </div>
              <div style="width:500px;height: 150px;float: left;margin: 15px">
                <p style="font-size: 22px;font-weight: 600;margin-bottom: 10px">{{ book.bookName }}</p>
                <div class="ellipsis" style="font-size: 16px;color: #646464;height: 70px;" v-html="book.bookBriefIntroduction">
                  {{ book.bookBriefIntroduction }}
                </div>
                <div style="width: 100%;margin-top: 10px">
                  <Button @click="turnButton" :type="button.buttonType">{{ button.buttonText }}</Button>
                  <Button @click="modal1 = true" type="primary">发表评论</Button>
                  <Modal
                    v-model="modal1"
                    title="发表评论"
                    :loading="loading"
                    @on-ok="comment">
                    <Form>
                      <FormItem label="标题">
                          <Input v-model="commentForm.title" placeholder="Enter something..."></Input>
                      </FormItem>
                      <FormItem label="评分">
                          <Rate show-text v-model="commentForm.rate" />
                      </FormItem>
                      <FormItem label="内容">
                        <Input v-model="commentForm.textarea" type="textarea" :rows="4" placeholder="Enter something..." />
                      </FormItem>
                    </Form>
                  </Modal>

                  <Button @click="createNote" type="primary">创建笔记</Button>
                </div>
              </div>
            </div>
            <Divider style="margin: 0px"/>
            <Menu mode="horizontal" theme="light" active-name="1" @on-select="clickMenu">
              <MenuItem name="synopsis">简介</MenuItem>
              <MenuItem name="bookContent">评论</MenuItem>
              <MenuItem name="bookNote">笔记</MenuItem>
            </Menu>
          </div>
          <div style="min-height: 200px;width: 100%;margin-top: 10px;background-color: #FFFFFF">
            <router-view/>
          </div>
        </Content>
        <Sider>
          <Card style="width:200px">
            <p slot="title">热度</p>
            <p>用户评分:
              <Rate show-text allow-half v-model="bookInfo.rate">
                <span style="color: #f5a623">{{ bookInfo.rate }}</span>
              </Rate>
            </p>
            <p>关注人次: <span>{{ bookInfo.followPeople }}</span></p>
            <p>笔记数量: <span>{{ bookInfo.noteNumber }}</span></p>
            <p>评论数量: <span>{{ bookInfo.commentNumber }}</span></p>
          </Card>
        </Sider>
      </Layout>
        <Footer class="layout-footer-center">2011-2016 &copy; TalkingData</Footer>
      </div>
      <BackTop :height="100" :bottom="100"></BackTop>
    </Layout>

</template>
<script>

  import topNav from '../../components/TopNav.vue'
  import synopsis from './synopsis'
  import bookContent from './bookContent'
  import bus from '../../js/bus'
    export default {
      components:{
        topNav,synopsis,bookContent
      },
      data(){
        return{
            button:{
              buttonText:"关注书籍",
              isFollow:false,
              buttonType:'success',
            },
            id:1,
            book:{
              bookId:'',
              bookName:'',
              bookContributors:'',
              bookCover:'',
              bookBriefIntroduction:'',
              authorInfo:'',
              catalog:'',
            },
            commentForm:{
              title:'',
              rate:0,
              textarea:''
            },
            questionForm:{
              title:'',
              describe:''
            },
            modal1:false,
            modal2:false,
            loading:true,
            bookInfo: {
              rate: 1,
              followPeople: 100,
              noteNumber: 20,
              commentNumber: 12
            }
          }
      },
      methods:{
          turnButton(){                 // 关注&取消关注书籍
              this.button.isFollow = !this.button.isFollow;

              if (this.button.isFollow == false){   // 没有关注显示关注
                this.$axios.post('/api/book/unfollow/' + this.id).then(response =>{
                    if (response.data){
                        this.$message.success("取消关注成功！");
                        this.button.buttonText = '关注书籍';
                        this.button.buttonType = 'success';
                    }
                });
            } else {
              this.$axios.post('/api/book/follow/'+this.id).then(response =>{
                  if (response.data){
                    this.$message.success("关注书籍成功！");
                    this.button.buttonText = '取消关注';
                    this.button.buttonType = 'error';
                  }
              });
            }
          },
          clickMenu(name){                 // 选择子路由
              this.$router.push({ name:name, params:{bookId:this.id}});
          },
          createNote(){                    // 创建笔记
              this.$router.push( {name:'editor', params:{bookId:this.id}})
          },
          comment(){    // 发表评论
              if (this.commentForm.title == ''|| this.commentForm.title == '' || this.commentForm.title == ''){
                  this.$Message.error("请输入完成！");
                  this.loading = false;
                  return;
              }

              this.$axios.post('/api/comment/commentBook',{
                  book_id:this.id,
                  comment:this.commentForm.textarea,
                  title:this.commentForm.title,
                  rate:this.commentForm.rate
              }).then(response=>{
                  if (response.data){
                    this.$Message.success("发表评论成功！");
                    this.loading = false;
                    this.modal1 = false;
                    bus.$emit('commentBook',true);         // vue 事件bus
                  }
              })
          }
      },
      created(){
        this.id = this.$route.params.bookId;

        this.$axios.post('/api/book/getBook/'+this.id).then(response => {
          this.book = response.data;
          // this.$Message.success(this.data.rate)
        });

        this.$axios.post('/api/book/isfollow/'+this.id).then(response => {
          console.log(response.data);
          this.button.isFollow = response.data;
        });

        this.$axios.post('/api/book/getBookInfo/'+this.id).then(response => {
          this.bookInfo = response.data;
        });

        this.$router.push({name:'synopsis',params:{bookId:this.id}})
      },
      watch:{
        'button.isFollow'(){
            if (this.button.isFollow){
              this.button.buttonText = '取消关注';
              this.button.buttonType = 'error';
            }else {
              this.button.buttonText = '关注书籍';
              this.button.buttonType = 'success';
            }
        }
      }
    }
</script>


<style scoped>
  ul,li{
    list-style-type:none;
  }

  .mainDiv{
    width: 1000px;
    margin: 0 auto;
    background-color: #f6f6f6;
  }

  .ivu-layout-sider{
    width: 15%;
    margin: 80px 20px 0;
    background-color: #FFFFFF;
  }

  .ivu-layout-content{
    width: 75%;
    margin-top: 80px ;
    background-color: #f6f6f6;
  }

  .layout-footer-center{
    text-align: center;
  }
  .imgDiv{
    width: 100px;height: 150px;float: left;margin: 15px
  }
  img {
    width: 100%;
    height: 100%
  }
  .ellipsis{
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-box-orient: vertical;
  }
</style>
