<template>
  <div style="width: 100%">
    <div style="width: 100%;min-height: 400px" v-if="page.total == 0">
      <img src="../../assets/noContent.png">
    </div>
    <div v-else>
      <ul>
        <li v-for="note in noteList">
          <div style="width: 100%;height: 100%;">
            <div style="width: 100%;height:33px;">
              <router-link :to="{name:'note', params:{noteId:note.notebookId }}">
                <h2 style="color:#1a1a1a;">{{ note.title }}</h2>
              </router-link>
            </div>
            <div v-html="note.content" class="ellipsis" style="font-size: 15px;color: #1a1a1a;line-height: 1.6;max-height: 50px">
              <!--{{ note.content }}-->
            </div>
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
  let noteList = [
    {
      title:'色发完发违法',
      content:'阅读是运用语言文字来获取信息，认识世界，发展思维，并获得审美体验的活动。它是从视觉材料中获取信息的过程。视觉材料主要是文字和…',
      publicTime:'2018-10-12',
      likeNumber:1,
      notebookId:1,
    }
  ]

  export default {
    data(){
      return{
        userId:this.$route.params.userId,
        noteList:noteList,
        page:{
          current:1,
          total:100,
          pageSize:4
        }
      }
    },
    created(){
        this.$axios.post('/api/note/getNoteBookByUserId/'+this.userId,{
            pageNo:1,
            pageSize:this.page.pageSize,
        }).then(response=>{
            this.noteList = response.data.list;
            this.page.total = response.data.totalCount;

            for (var i = 0; i < this.noteList.length; i++) {
              var ymd = this.noteList[i].publicTime.substr(0, 10);
              var hms = this.noteList[i].publicTime.substr(11, 8);
              var time = ymd + ' ' + hms;
              this.noteList[i].publicTime = time;
            }

        });
    },
    methods:{
      changePageNo(pageNo){
        this.$axios.post('/api/note/getNoteBookByUserId/'+this.userId,{
          pageNo:pageNo,
          pageSize:this.page.pageSize,
        }).then(response=>{
          this.noteList = response.data.list;
          this.page.total = response.data.totalCount;

          for (var i = 0; i < this.noteList.length; i++) {
            var ymd = this.noteList[i].publicTime.substr(0, 10);
            var hms = this.noteList[i].publicTime.substr(11, 8);
            var time = ymd + ' ' + hms;
            this.noteList[i].publicTime = time;
          }

        });
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
    min-height: 100px;
    padding: 16px 20px;
    border-bottom: 1px #f6f6f6 solid;
  }
  h2{
    font-size: 18px;
  }
  img{
    width: 30px;
    height: 30px;
  }
  span{
    /*float: left;*/
  }
</style>
