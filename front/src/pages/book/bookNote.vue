<template>
  <div style="width: 100%">
    <div v-if="page.total == 0">
      <img src="../../assets/noContent.png">
    </div>
    <div v-else>
    <ul>
      <li v-for="note in noteList">
        <div style="width: 100%;height: 100%;">
          <el-button type="primary" @click="modal = true" style="float: right;margin-right: 10px"  icon="el-icon-star-off" circle></el-button>

          <Modal
            v-model="modal"
            title="添加到收藏"
            @on-ok="addToCollectFolder"
            @on-cancel="cancel">
            <div style="width: 100%;height: 100%" v-for="(collectFolder,index) in collectFolderList">
              <Divider style="margin: 10px 0px"/>
              <div style="width: 100%;height: 40px">
                <span>{{ collectFolder.folderName }}</span>
                <Button class="button" style="float: right" @click="collect(collectFolder.folderId, note.notebookId,index)" type="success">收藏</Button>
              </div>
            </div>
            <Divider style="margin: 10px 0px"/>
            <div style="width: 100%;height: 20px">
              <Button style="margin: 0 auto" @click="modal2 = true" type="primary">创建新的收藏</Button>
              <Modal
                v-model="modal2"
                title="创建新的收藏夹"
                @on-ok="createCollectFolder"
                @on-cancel="cancel">
                <Input v-model="modal2Value" placeholder="请输入文件夹名..." style="width: 300px" />
              </Modal>
            </div>
          </Modal>

          <div style="width: 100%;height:33px;margin: 5px">
            <div style="display: inline-block;width:33px;height: 100%;float: left;">
              <router-link :to="{name:'self',params:{userId: note.userModel.userId }}">
                <img :src="note.userModel.avatarUrl">
              </router-link>
            </div>
            <div style="display: inline-block;width:500px;height: 100%;float: left;margin: 5px 10px 0px;">
              <router-link :to="{name:'self',params:{userId: note.userModel.userId }}">
                <div style="display: inline-block;font-size: 16px;line-height: 1.1;font-weight: 600;color: #1a1a1a">{{ note.userModel.name }}</div>
              </router-link>
              <div style="display: inline-block;font-size: 14px;color: #1a1a1a;line-height: 1.6">,&nbsp;&nbsp;{{ note.userModel.selfStyle }}</div>
            </div>
          </div>
          <router-link :to="{ name:'note', params:{ noteId: note.notebookId }}">
            <h2 style="color: #1a1a1a">{{ note.title }}</h2>
          </router-link>
          <div v-html="note.content" class="ellipsis" style="font-size: 15px;color: #1a1a1a;line-height: 1.6;max-height: 100px"></div>
          <div style="font-size: 14px;color: #1a1a1a;line-height: 1.6;height: 20px">
            <span>
              <Icon type="ios-calendar" size="20" />
              <span style="height: 20px;line-height: 20px">{{ note.publicTime }}</span>
            </span>
            <span style="margin: 0px 20px">
              <Icon type="ios-happy" size="20"/>
              <span>{{ note.likeNumber }}</span>
            </span>
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
    data(){
      return{
        noteList:'',
        collectFolderList:[
          {
            folderName:'python',
            folderId:'3',
          },{
            folderName:'python',
            folderId:'2',
          }
        ],
        page:{
          current:1,
          total:100,
          pageSize:5,
        },
        modal:false,
        modal2:false,
        modal2Value:''
      }
    },
    computed:{
      bookId(){
        return this.$route.params.bookId;
      }
    },
    created(){
        this.changePageNo(1);
        this.loadFolder();
    },
    methods:{

      loadFolder(){
        // 获取 收藏夹列表
        this.$axios.post('/api/collect/loadMyCollectFolder').then(response=>{
          this.collectFolderList = response.data;
        });
      },
      changePageNo(pageNo){
        this.$axios.post('/api/note/getNotebookByBookId/'+this.bookId, {
          pageNo:pageNo,
          pageSize:this.page.pageSize,
        }).then(response => {
          this.noteList = response.data.list;
          this.page.total = response.data.totalCount;
        });
      },
      collect(folderId,noteId,index){
          if ($('.button > span').eq(index).text() == "收藏"){
              this.$axios.post('/api/collect/collectBook',{
                folder_id:folderId,
                notebook_id:noteId
              }).then(response=>{
                if (response.data){
                  this.$Message.success("收藏成功");
                  $('.button > span').eq(index).text("已收藏");
                  $('.button').eq(index).removeClass("ivu-btn-success");
                  $('.button').eq(index).addClass("ivu-btn-error");
                }
              });
          }else {
            this.$axios.post('/api/collect/unCollectBook',{
              folder_id:folderId,
              notebook_id:noteId
            }).then(response=>{
              if (response.data){
                this.$Message.success("取消收藏成功");
                $('.button > span').eq(index).text("收藏");
                $('.button').eq(index).removeClass("ivu-btn-error");
                $('.button').eq(index).addClass("ivu-btn-success");
              }
            });
          }
      },
      addToCollectFolder(){
        this.modal = false;
      },
      createCollectFolder(){
        this.$axios.post('/api/collect/createdFolder',{
          folderName:this.modal2Value
        }).then(response=>{
            if (response.data){
              this.modal2 = false;
              this.$Message.success("创建成功");
              this.loadFolder();
            }
        });
      },
      cancel(){
        this.$Message.warning("点击了取消");
      }
    },
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
