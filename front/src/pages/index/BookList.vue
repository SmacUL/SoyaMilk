<script>
  /***
   * 此组件 任务是展示 推荐的书籍， 最新的书籍，最热的书籍。
   * 此组件 完成页面区域划分以及向后端请求数据，将数据传入子组件 动态分组以及渲染。
   * */
</script>
<template>
  <div class="booksDiv">
    <section>
      <Divider orientation="left">热门图书</Divider>
      <div class="slide-book">
        <book-carousel :books="hotBookList" :number="hotBookListNumber"></book-carousel>
      </div>
    </section>
  </div>
</template>

<script>
    import bookCarousel from '../../components/BookCarousel';

    export default {
        components:{
          bookCarousel
        },
        name: "",
        data(){
          return {
            hotBookList:'',
            hotBookListNumber:0,
          }
        },
        created(){
          this.accept();
        },
        methods:{
          accept(){
              this.$axios.post('/api/book/getHotBook').then(response =>{
                this.hotBookList = response.data;
                console.log(this.hotBookList);
                this.hotBookListNumber = this.hotBookList.length;
              });
          },
        },
        ///分成三部分 最近书籍，最火，最热门、
    }
</script>

<style scoped>
  .booksDiv{
    width: 100%;
    margin: 0 auto;
  }
</style>
