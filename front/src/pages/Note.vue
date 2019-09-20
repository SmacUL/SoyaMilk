<template>
    <div>
      <top-nav></top-nav>
      <div class="mainDiv">
        <div class="noteMain">
            <div style="width: 100%;height: 170px;">
              <div class="imgDiv">
                <img :src="book.bookCover">
              </div>
              <div style="width:540px;height: 150px;float: left;margin: 15px">
                <p style="font-size: 22px;font-weight: 600;margin-bottom: 10px;">
                  <router-link :to="{name:'book',params:{bookId:this.book.bookId}}" style="color: #1a1a1a">{{ book.bookName }}</router-link>
                </p>
                <div class="ellipsis" style="font-size: 16px;color: #646464;height: 100px;" v-html="book.bookBriefIntroduction">
                  {{ book.bookBriefIntroduction }}
                </div>
              </div>
            </div>
            <h1>{{ note.title }}</h1>
            <div>
              <Button id="follow" style="float: right;margin: 0px 10px" type="success" size="large" v-bind:disabled="disabled" @click="follow">关注TA</Button>
              <div style="float: left;margin-right: 20px">
                <router-link :to="{name:'collectFolder',params:{userId:user.userId }}">
                  <img :src="user.avatarUrl">
                </router-link>
              </div>
              <div style="margin: 10px">
                <router-link :to="{name:'collectFolder',params:{userId:user.userId }}">
                  <div style="color:#2c3e50;font-size: 18px;font-weight: 600;">{{ user.name }}</div>
                </router-link>
                <div style="margin: 10px 0px;font-size: 15px;color: #646464">{{ user.selfStyle }}</div>
              </div>
            </div>
            <div v-html="note.content" style="word-break: break-word;line-height: 1.6;font-size: 16px;color: #1a1a1a;margin-top: 30px">
              {{ note.content }}
            </div>
        </div>
        <!--<div style="width:165px;height: 35px;margin: 0 auto;">-->
          <!--<Button style="margin: 0px 25px" size="large" icon="md-thumbs-up" :type="isLike.type1" shape="circle" @click="likeIt"></Button>-->
          <!--<Button size="large" icon="md-thumbs-down" :type="isLike.type2" shape="circle" @click="dislikeIt"></Button>-->
        <!--</div>-->
        <comment :noteId="notebookId"></comment>
        <Footer class="layout-footer-center">2011-2016 &copy; TalkingData</Footer>
      </div>
      <BackTop :height="100" :bottom="100"></BackTop>
    </div>

</template>

<script>
  import topNav from '../components/TopNav.vue'
  import marked from 'marked'
  import comment from '../components/Comment'

  let mark = new marked.Renderer();
  marked.setOptions({
    renderer: mark,
    gfm: true,
    tables: true,
    breaks: false,
    pedantic: false,
    sanitize: false,
    smartLists: true,
    smartypants: false
  });

  export default {
        name: "",
        components:{
          topNav,comment
        },
        data(){
          return{
              notebookId:this.$route.params.noteId,
              disabled:false,
              user:{
                name:'宠头条',
                selfStyle:'已认证的官方帐号',
                avatarUrl:'',
                userId:1,
                avatarUrl:'/static/avatar/3-1545469708341.jpg'
              },
              note:{
                title:'这种照片，很日本！',
                content:'在大量的留言当中，很多童鞋介绍了Vscode。话说这个到底怎么样呢？\n' +
                  '\n' +
                  '\n' +
                  '\n' +
                  'Visual Studio Code是Microsoft在2015年4月30日Build 开发者大会上正式宣布的项目：一个运行于 Mac OS X、Windows和 Linux 之上的，针对于编写现代 Web 和云应用的跨平台源代码编辑器。\n' +
                  '\n' +
                  '由此可见其后台不是一般的强大。\n' +
                  '\n' +
                  '它具有以下几个优势：\n' +
                  '\n' +
                  '\n' +
                  '\n' +
                  '满足智能感知：除了语法突出和自动完成的智能感知之外，它还提供了基于变量类型、函数定义和导入模块的智能完成。\n' +
                  '调试：从编辑器中调试代码。启动或附加到你的运行应用程序和调试的断点，调用堆栈，和一个交互式控制台。\n' +
                  'Git：内置的Git命令。',
              },
              book:{

              },
              markdownToHtml:'',
              isFollow:false,
              isLike:{
                type1:'success',
                type2:'success',
              }
          }
        },
        beforeRouteEnter(to,from,next){
            next(vm =>{
                vm.noteId = from.params.noteId;
            });
        },
        created(){
            this.$axios.post('/api/note/getUserNoteAndBookModel/'+this.notebookId).then(response=>{
                this.user = response.data.user;
                this.note = response.data.note;
                this.book = response.data.book;
                if (response.data.self) {
                    this.disabled = "disabled";
                } else {
                    this.disabled = false;
                }
            });

            this.$axios.post('/api/isFollow/'+this.user.userId).then(response=>{
                this.isFollow = response.data;
            });

        },
        methods:{
          follow(){
            if (!this.isFollow){
              this.$axios.post('/api/follow/'+this.user.userId).then(response=>{
                if (response.data){
                  this.$Message.success("关注成功");
                  this.isFollow = true;
                }
              });
            }else{
              this.$axios.post('/api/unfollow/'+this.user.userId).then(response=>{
                if (response.data){
                  this.$Message.success("取消关注成功");
                  this.isFollow = false;
                }
              });
            }
          },
          likeIt(){

          },
          dislikeIt(){

          }
        },
        watch:{
          isFollow(){
            if (this.isFollow){
              $('#follow > span').text("已关注");
              $('#follow').removeClass("ivu-btn-success");
              $('#follow').addClass("ivu-btn-error");
            } else {
              $('#follow > span').text("关注TA");
              $('#follow').removeClass("ivu-btn-error");
              $('#follow').addClass("ivu-btn-success");
            }
          }
        }
    }
</script>

<style scoped>
  .mainDiv{
    width: 700px;
    margin: 0 auto;
  }
  .noteMain{
    margin-top: 70px;
    background-color: #FFFFFF;
    width: 100%;
  }
  .layout-footer-center{
    text-align: center;
    background-color: #FFFFFF;
  }
  img{
    width: 60px;
    height: 60px;
    border-radius: 4px;
  }
  .imgDiv{
    width: 100px;height: 150px;float: left;margin: 15px
  }
  .imgDiv > img {
    width: 100%;
    height: 100%
  }
</style>
