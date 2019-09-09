<!-- 创建笔记 -->
<template>
  <Layout style="background: #FFFFFF;">
<!--    <Header></Header>-->

    <div id="top-panel">
      <div style="display: inline-block; height:80px; width: 80px; vertical-align: top">
        <img src="../assets/logo.jpg" style="margin: 10px; height:60px; width:60px">
      </div>
      <div style="display: inline-block; height:80px; width: 200px; vertical-align: top">
        <span style="font-size:x-large;display: inline-block; margin: 20px;">编辑您的笔记</span>
      </div>
    </div>

    <div id="title-panel" style="text-align: center; margin-bottom: 10px">
      <span style="font-size:large;">您的标题：</span>
      <Input style="width: 74%;" size="large" type="text" v-model="title" placeholder="请输入标题"></Input>
    </div>
    <div id="markdown">
      <div ref="toolbar" class="toolbar"
           style="height:25px;border: 1px;border-style: solid; border-color: #f1f1f1; background-color:#f1f1f1">
      </div>
      <div ref="textplace" class="textplace"
           style="height:600px;max-height:600px; border: 1px;border-style: solid; border-color: #f1f1f1;">
      </div>
    </div>

    <div id="buttons-panel" style="text-align: center; width: auto;">
      <Button type="primary" @click="returnNewNote" style="display:inline-block;width:100px;">发布</Button>
      <Button type="primary" @click="returnBook" style="display:inline-block;width:100px;">取消</Button>
    </div>

  </Layout>
</template>

<script>
  import WangEditor from  'wangeditor'
  import Divider from "iview/src/components/divider/divider";

  export default {
    components: {
      Divider

    },
    data(){
      return{
          editorContent:'',
          bookId:'',
          newNote: "",
          title: "",

        }
    },
    beforeRouteEnter(to, from, next) {
      next(vm => {
        vm.bookId = from.params.bookId;
      })
    },
    methods:{
      upload(){

      },
      returnNewNote() {
        // 传笔记
        var url = window.location.href;
        var id = url.substr(url.lastIndexOf('/') + 1, url.length);
        this.$axios.post('/api/note/userNewNote',{
          title: this.title,
          content: this.newNote,
          belongBook_id: id,
        }).then((response) => {
          if(response.data){
            this.$Message.success("发表成功");
            this.$router.push('/book/bookNote/' + id);
          } else {
            this.$Message.success("发表失败，请重试");

          }
        })
      },
      returnBook() {
        var url = window.location.href;
        var id = url.substr(url.lastIndexOf('/') + 1, url.length);
        this.$router.push('/book/bookNote/' + id);
      }
    },
    computed: {
    },
    mounted() {
      let editor = new WangEditor(this.$refs.toolbar, this.$refs.textplace);

      editor.customConfig.onchange = (html) => {
        this.newNote = html
      };
      editor.customConfig.menus = [
        'source',
        '|',
        'bold',
        'underline',
        'italic',
        'strikethrough',
        'eraser',
        'forecolor',
        'bgcolor',
        '|',
        'quote',
        'fontfamily',
        'fontsize',
        'head',
        'unorderlist',
        'orderlist',
        'alignleft',
        'aligncenter',
        'alignright',
        '|',
        'link',
        'unlink',
        'table',
        'emotion',
        '|',
        'img',
        'video',
        'location',
        'insertcode',
        '|',
        'undo',
        'redo',
        'fullscreen'
      ];
      editor.create();

    }
  }
</script>

<style scoped>

  #markdown{
    width: 80%;
    height: 700px;
    margin: 0 auto;
  }

  .ivu-layout-header{
    background-color: #FFFFFF;
    border-bottom: 1px #f6f6f6 solid;
  }
  .ivu-layout-content{
    background-color: #FFFFFF;
  }


</style>
