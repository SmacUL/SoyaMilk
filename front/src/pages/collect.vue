<template>
  <Layout>
    <top-nav></top-nav>
    <div>
      <div class="collectDiv">
        <div class="left">
          <div style="width: 100%;height: 100px;background-color: #FFFFFF">
            <div>
              <Button
                size="large"
                type="text"
                style="color: #3399ff"
                @click="returnPreviousPage"
              >返回我的收藏</Button>
            </div>
            <div style="font-size: 18px;font-weight: 600;margin: 0px 15px">{{ collectFolderName }}</div>
            <div>
              <Button size="large" type="text">编辑</Button>
              <span style="font-size: 14px;font-weight: 600">·</span>
              <Button size="large" type="text" @click="deleteAll">删除</Button>
            </div>
          </div>
          <div class="noteDiv">
            <ul>
              <li v-for="notebook in notebooksList">
                <div style="width: 100%;height: 100%;">
                  <div style="width: 100%;height:33px;">
<!--                    <router-link :to="{name:'note', params:{noteId:notebook.notebookId }}">-->
                      <h2 style="color:#1a1a1a;">{{ notebook.title }}</h2>
<!--                    </router-link>-->
                  </div>
                  <div v-html="notebook.content" class="ellipsis" style="font-size: 15px;color: #1a1a1a;line-height: 1.6;max-height: 50px">
                    <!--{{ note.content }}-->
                  </div>
                  <div style="font-size: 14px;color: #1a1a1a;line-height: 1.6;height: 20px">
              <span>
                <Icon type="ios-calendar" size="20" />
                <span style="height: 20px;line-height: 20px">{{ notebook.createTime }}</span>
              </span>
                    <span style="margin: 0px 20px">
                <Icon type="ios-happy" size="20"/>
                <span>{{ notebook.likeNumber }}</span>
              </span>
                  </div>
                </div>
              </li>
            </ul>

            <ul>

            </ul>
          </div>
        </div>
        <div class="right"></div>
      </div>
    </div>
    <Footer class="layout-footer-center">2011-2016 &copy; TalkingData</Footer>
  </Layout>
</template>

<script>
import topNav from "../components/TopNav";
let folders = [
  {notebookId: 0, title:'操作系统',createTime:'2019-1-1', likeNumber: 0, content: "ds"},
]
export default {
  name: "collect",
  components: {
    topNav
  },
  data() {
    return {
      collectFolderName: "数学",
      notebooksList: folders,
    };
  },

  methods: {
    returnPreviousPage() {
      // this.$router.push({ name: 'collectFolder', params:{userId:this.userId}});
      this.$router.push('/self/collectFolder/' + this.$route.params.userId);
    },
    /**
     * 2019-09-10 SmacUL
     * 删除此收藏文件夹
     */
    deleteAll() {
      if(confirm("确认删除？")){
        // alert("继续");
        this.$axios.post('/api/collect/deleteFolder/' + this.$route.params.collectId,{
        }).then(response =>{
          if (response.data == true){
            this.$Message.success('删除成功!');
            this.$router.push('/self/collectFolder/' + this.$route.params.userId);
          }else{
            this.$Message.error("删除失败");
            this.$router.push('/self/collectFolder/' + this.$route.params.userId);
          }
        });
      }else{
        return;
      }
    },
  },
  /**
   * 2019-09-10 SmacUL
   * 新增初始化函数
   */
  created(){
    // this.init();
    this.$axios.post('/api/note/getFolderNotebooks/' + this.$route.params.collectId,{
    }).then(response =>{
      this.notebooksList = response.data;
      for (var i = 0; i < this.notebooksList.length; i++) {
        var ymd = this.notebooksList[i].createTime.substr(0, 10);
        var hms = this.notebooksList[i].createTime.substr(11, 8);
        var time = ymd + ' ' + hms;
        this.notebooksList[i].createTime = time;
      }
    });

    this.$axios.post('/api/collect/getCollectionFolderName/' + this.$route.params.collectId,{
    }).then(response =>{
      this.collectFolderName = response.data;
    });

  }
};
</script>

<style scoped>
.collectDiv {
  width: 1000px;
  margin: 0 auto;
  margin-top: 70px;
  min-height: 200px;
}
.left {
  width: 69%;
  height: 100%;
  display: inline-block;
}
.right {
  width: 30%;
  height: 100%;
  display: inline-block;
  background-color: #ffffff;
}
.noteDiv {
  margin-top: 10px;
  width: 100%;
  min-height: 200px;
  background-color: #ffffff;
}

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
</style>
