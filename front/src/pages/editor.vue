<template>
  <Layout style="background: #FFFFFF;">
    <Header>
      <div style="display: inline-block">
        <img src="../assets/logoDouban.png">
      </div>
      <Button type="primary" @click="upload">Primary</Button>
    </Header>
    <Content id="markdown">
    </Content>
    <Footer>

    </Footer>
  </Layout>
</template>

<script>
  import WangEditor from  'wangeditor'

  export default {
    components: {

    },
    data(){
      return{
          editorContent:'',
          bookId:'',
        }
    },
    beforeRouteEnter(to, from, next) {
      next(vm => {
        vm.bookId = from.params.bookId;
      })
    },
    methods:{
      upload(){

      }
    },
    computed: {
    },
    mounted() {
      let editor = new WangEditor('#markdown')  // 两个参数也可以传入 elem 对象，class 选择器
      editor.customConfig.onchange = (html) => {
        this.editorContent = html
      }
      editor.create()
    }
  }
</script>

<style scoped>

  #markdown{
    width: 40%;
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
