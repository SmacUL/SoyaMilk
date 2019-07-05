<script>
  /**
   * 该组件是书籍的轮播图展示
   * prop : book 对象集合 期待4个属 -- 名字 图片链接 作者 id.
   * 通过传入的集合以及数量 完成动态分组。
   * */
</script>
<template>
  <Carousel dots="inside" arrow="always" radius-dot>
    <CarouselItem v-for="(list,index) in bookArrayList" :key="index">
      <ul class="bookUl">
        <li class="bookLi" v-for="book in list">
          <Card :padding=10>
            <div class="bookDiv">
              <div class="bookImgDiv">
                <router-link :to="{name:'book',params:{bookId:book.bookId}}" >
                  <img :src="book.bookCover" class="bookImg">
                </router-link>
              </div>
              <div class="bookNameLink ellipsis">
                <router-link :to="{name:'book',params:{bookId:book.bookId}}">{{book.bookName}}</router-link>
              </div>
              <div class="contributesName">
                <span>{{book.bookContributors}}</span>
              </div>
            </div>
          </Card>
        </li>
      </ul>
    </CarouselItem>
  </Carousel>
</template>

<script>
  const liNumber = 5;
  export default {
    props:['books','number'],
    data(){
      return{
        itemNumber:0,
        bookArrayList:""
      }
    },
    created() {
      this.toArrayList(this.books,this.number);
    },
    methods:{
      /**
       * 将上层组件传入的值 动态分组。
       * */
      toArrayList(array,number){
        let i = 0;
        let newArray = new Array();
        let listNumber = Math.floor(number / liNumber);
        while (i < listNumber){
          newArray[listNumber] = new Array();
          newArray[i] = array.slice(i*liNumber,(i+1)*liNumber);
          i++;
        }
        newArray[i] = array.slice(i*liNumber,number);
        this.bookArrayList = newArray;
      }
    },
    watch:{
      books(){
        this.toArrayList(this.books,this.number);
      }
    }
  }
</script>


<style scoped>
  .bookUl{
    width: 100%;
    height: 100%;
    display: inline-block;
    margin-left: 35px;
  }
  .bookLi{
    float: left;
    margin: 20px 5px ;
  }
  .bookDiv{
    text-align: center;
    width: 120px;
    height: 220px;
    background-color: #FFFFFF;
  }
  .bookImgDiv{
    width: 110px;
    height: 150px;
  }
  .bookImg{
    width: 100%;
    height: 100%;
  }
  .bookNameLink{
    width: 100%;
    height: 40px;
    text-align: left;
  }
  .bookNameLink > a{
    color: #825d5b;
  }
  .contributesName{
    width: 100%;
    height: 10px;
    color: #afafaf;
    text-align: left;
  }
  section{
    width: 100%;
    height: 301px;
  }
</style>
