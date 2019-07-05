<template>
  <div style="width: 100%;padding-top: 50px">
    <div style="width: 100%;height: 40px">
      <el-input style="width: 85%;float: left;display: inline-block" v-model="input" placeholder="写下你的评论.."></el-input>
      <el-button style="width: 15%;float: left;display: inline-block" type="primary" @click="sendComment(noteId)">发送</el-button>
    </div>

    <div style="width: 100%;">
      <ul>
        <li v-for="(userComment,index) in commentList">
          <Divider/>
          <div style="width: 100%;height: 35px;margin: 10px">
            <img style="float: left" :src="userComment.user.avatarUrl">
            <span style="font-size: 18px;font-weight: 600;float: left;margin: 10px 10px 0px 10px">{{ userComment.user.name }}</span>
            <div style="clear: both"></div>
          </div>
          <div style="margin-left:55px;width: 80%;">
            <span style="font-size: 15px;float: left;">{{ userComment.comment }}</span>
          </div>
          <div style="width: 100%;height: 24px;float: left;margin:15px 55px;color:#8590A6;font-size: 15px;font-height:24px; font-weight: 500;">
            <div style="cursor:pointer;display: inline-block" @click="lookRecomment(userComment.commentId,index)">
              <Icon type="ios-chatbubbles" size="20" color="#8590A6"/>
              <span style="margin: 5px;color: #8590A6">查看回复</span>
            </div>
            <div style="cursor:pointer;display: inline-block" @click="showIt(index)">
              <Icon type="ios-undo" size="20" color="#8590A6"/>
              <span style="margin: 5px;color: #8590A6">回复</span>
            </div>
          </div>
          <div v-show="canShow(index)" style="width: 92%;height: 40px;display: inline-block;margin-left: 55px">
            <el-input style="width: 85%;float: left;display: inline-block" v-model="inputToUser" placeholder="写下你的评论.."></el-input>
            <el-button style="width: 15%;float: left;display: inline-block" type="primary" @click="sendRecomment(userComment.commentId)">发送</el-button>
          </div>
        </li>
      </ul>
      <Divider/>
      <div style="width: 100%;height: 50px;text-align: center">
        <Page :current="page.current" :total="page.total" :page-size="page.pageSize" simple  @on-change="changePageNo"/>
      </div>
    </div>

    <!--查看回复的对话框-->
    <Modal
      style="background-color: #646464"
      v-model="modal"
      title="查看对话"
      @on-ok="ok"
      :scrollable="scrollable"
      width="700">
      <div style="width: 100%">
        <div style="width: 100%;height: 35px;">
          <img style="float: left" :src="lookComment.user.avatarUrl">
          <span style="font-size: 18px;font-weight: 600;float: left;margin: 10px 10px 0px 10px">{{ lookComment.user.name }}</span>
        </div>
        <div style="margin-left:55px;width: 80%;padding-top: 10px;font-size: 15px;float: left;">{{ lookComment.comment }}</div>
      </div>
      <div style="float:left;width: 100%;height: 10px;background-color: #f6f6f6;margin: 20px 0px"></div>
      <div style="clear: both"></div>

      <div style="width: 100%;min-height: 400px">
        <ul style="min-height: 300px">
          <li v-for="userComment in reComment">
            <div style="width: 100%;height: 35px;margin: 10px">
              <img style="float: left" :src="userComment.user.avatarUrl">
              <span style="font-size: 18px;font-weight: 600;float: left;margin: 10px 10px 0px 10px">{{ userComment.user.name }}</span>
            </div>
            <div style="margin-left:55px;margin-bottom:20px;width: 80%;font-size: 15px;float: left;">{{ userComment.content }}</div>
            <Divider/>
          </li>
        </ul>
        <div style="width: 100%;height: 50px;text-align: center">
          <Page :current="rePage.current" :total="rePage.total" :page-size="rePage.pageSize" simple  @on-change="changeRePageNo"/>
        </div>
        <div style="width: 100%;height: 40px">
          <el-input style="width: 85%;float: left;display: inline-block" v-model="inputToUser" placeholder="写下你的评论.."></el-input>
          <el-button style="width: 15%;float: left;display: inline-block" type="primary" @click="sendRecomment(lookComment.commentId)">发送</el-button>
        </div>
      </div>
    </Modal>

  </div>
</template>

<script>
    export default {
        props:['noteId'],
        name: "comment",
        data() {
            return {
              scrollable:false,
              modal:false,
              input:'',
              inputToUser:'',
              commentList:[
                {
                  user:{
                    name:'李航',
                    avatarUrl:'/static/avatar/1-1545375741149.png'
                  },
                  commentId:1,
                  comment:'吃住',
                }
              ],
              reComment:[
                {
                  user:{
                    name:'李航',
                    avatarUrl:'/static/avatar/1-1545375741149.png'
                  },
                  commentId:1,
                  comment:'这主要取决于摄影师的水平，如果是个专业的摄影师，在中国的山沟沟里拍的风景并不比这个差。如果是拍照一般的人，你就是给他盛世美景他也拍不出感觉来。像照片里这样的风景，这样的霓虹灯，中国到处都是，甚至更多更丰富。中国虽然没有富士山，但中国有“五岳归来不看山，黄山归来不看岳”的千年名山，有“',
                }
              ],
              lookComment:{      // 点开的 recomment列表
                user:{
                  name:'李航',
                  avatarUrl:'/static/avatar/1-1545375741149.png'
                },
                commentId:1,
                comment:'这主要取决于摄影师的水平，如果是个专业的摄影师，在中国的山沟沟里拍的风景并不比这个差。如果是拍照一般的人，你就是给他盛世美景他也拍不出感觉来。像照片里这样的风景，这样的霓虹灯，中国到处都是，甚至更多更丰富。中国虽然没有富士山，但中国有“五岳归来不看山，黄山归来不看岳”的千年名山，有“',
              },
              page:{
                current:1,
                total:100,
                pageSize:6
              },
              rePage:{
                current:1,
                total:100,
                pageSize:6
              },
              showWhich:''
            }
        },
        created(){
          // 分页获取评论
          this.changePageNo(1);
        },
        methods: {
          // 分页获取评论
          changePageNo(pageNo){
            this.$axios.post('/api/comment/loadNoteBookCommentByPage/'+this.noteId,{
              pageNo:pageNo,
              pageSize:this.page.pageSize
            }).then(response=>{
              this.commentList = response.data.list;
              this.page.total = response.data.totalCount;
            });
          },


          // 加载 查看评论的内容
          lookRecomment(commentId,index){
            this.modal = true;
            this.inputToUser = '';
            this.showWhich = '';

            this.lookComment = this.commentList[index];
            this.loadRecomment(commentId);
          },
          loadRecomment(commentId){
            // 加载回复评论
            this.$axios.post('/api/comment/loadRecomment/'+commentId,{
              pageNo:1,
              pageSize:this.rePage.pageSize
            }).then(response=>{
              this.reComment = response.data.list;
              this.rePage.total = response.data.totalCount;
              // this.lookComment.commentId = response.data.comment_id;
            });
          },
          changeRePageNo(pageNo){
            this.$axios.post('/api/comment/loadRecomment/'+this.lookComment.commentId,{
              pageNo:pageNo,
              pageSize:this.rePage.pageSize
            }).then(response=>{
              this.reComment = response.data.list;
              this.rePage.total = response.data.totalCount;
            });
          },
          sendComment(noteId){
            this.$axios.post('/api/comment/sendComment',{
                notebook_id:noteId,
                comment:this.input
            }).then(response=>{
              if (response.data){
                this.$Message.success("发表成功");
                this.changePageNo(1);
                this.input = '';
              }
            });
          },
          sendRecomment(commentId){
            console.log(commentId)
              this.$axios.post('/api/comment/addRecomment',{
                comment_id:commentId,
                content:this.inputToUser
              }).then(response=>{
                  if (response.data){
                    this.$Message.success("发表成功");
                    this.loadRecomment(commentId);
                    this.inputToUser = '';
                  }
              });
          },
          ok(){
            this.$Message.success("点击了关闭");
          },

          // 显示recomment输入框
          showIt(index){
            if (this.showWhich === index){
                this.showWhich = '';
                return;
            }
            this.inputToUser = '';
            this.showWhich = index;
          },
          canShow(index){
            if (this.showWhich === index){
              return true;
            }
            return false;
          }
        }
    }
</script>

<style scoped>
  ul{
    width: 100%;
  }
  li{
    width: 100%;
  }
  img{
    width: 35px;
    height: 35px;
  }
  .ivu-modal-content{
    height: 600px;
    overflow-y: auto;
  }
</style>
