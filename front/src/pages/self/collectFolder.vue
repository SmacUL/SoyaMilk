<template>
  <div style="width: 100%;min-height: 200px">
    <div style="width: 100%;min-height: 400px" v-if="page.total == 0">
      <img src="../../assets/noContent.png">
    </div>
    <div v-else>
      <ul>
        <li v-for="folder in folders">
          <router-link :to="{name:'collect',params:{ collectId:folder.folderId }}">
            <div style="font-size: 18px;font-weight: 600;margin: 10px 15px;color: #1a1a1a">{{ folder.folderName }}</div>
          </router-link>
            <div style="font-size: 14px;font-weight: 500;color: #8590a6;margin: 10px 15px">{{ folder.update_time }}更新&nbsp;·&nbsp;{{ folder.number }}条内容  </div>
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

  let folders = [
    { folderName:'操作系统',update_time:'2019-1-1', number:8 ,folderId:1 },
  ]
  export default {
    name:'collectFolder',
    data(){
      return{
        userId:this.$route.params.userId,
        folders:folders,
        page:{
          current:1,
          pageSize:6,
          total:100
        }
      }
    },
    created(){
      this.changePageNo(1);
    },
    methods:{
      changePageNo(pageNo){
        this.$axios.post('/api/collect/getCollectionFolderPage/'+this.userId,{
          pageNo:pageNo,
          pageSize:this.page.pageSize,
        }).then(response=>{
          this.folders = response.data.list;
          this.page.total = response.data.totalCount;
        });
      }
    }

  }
</script>

<style scoped>
  li{
    border-bottom: 1px #f6f6f6 solid;
  }
</style>
