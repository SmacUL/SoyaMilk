<script>
  /**
   * 该组件显示书籍的简介，较简单。
   * 该组件需要上层传入书籍的索引id，该组件自行向后端查询。
   * */
</script>
<template>
  <div class="Card">
    <section v-if="book.bookBriefIntroduction !=''">
      <h2>内容简介</h2>
      <div class="h2text"  v-html="book.bookBriefIntroduction">{{ book.bookBriefIntroduction }}</div>
    </section>
    <section v-if="book.authorInfo != ''">
      <h2>作者简介</h2>
      <div class="h2text" v-html="book.authorInfo">{{ book.authorInfo }}</div>
    </section>
    <section v-if="book.catalog != undefined">
      <h2>目录</h2>
      <div class="h2text" v-if="book.catalog.length > 10"  v-html="book.catalog">
        {{ book.catalog }}
      </div>
    </section>

  </div>
</template>

<script>

  export default {
    data(){
      return {
        book:{
          bookId:'',
          bookName:'',
          bookContributors:'',
          bookCover:'',
          bookBriefIntroduction:'',
          authorInfo:'',
          catalog:'',
        },

        content:'李银河退休后隐居乡间海滨六年，专心读书写作，关注点从社会问题转向个体精神生活。本书为李银河随笔新作，收录了她对人生本质、自我存在的价值、交友之道、激情之爱、生活方式、艺术与阅读等问题的思考，呈现了一位女性主义者独立而自由的精神世界。这本心灵笔记宁静而优美，清醒而通透，为读者提供了一种超脱但不避世的人生视角，呈现了理性和文艺兼具的独到的生命体验。',
        author:'李银河，美国匹兹堡大学社会学博士，中国社会科学院社会学所研究员、教授。主要研究领域为家庭婚姻、性别与性。业余写作小说、随笔和诗。曾被《亚洲周刊》评为中国ZUI具影响力的五十人之一。',
        contents:[
          {text :'第一章　空洞 0001'},
          {text :'第二章　回家 0067'},
          {text :'第三章　乾安 0227'},
          {text :'第四章　别人 0343'},
          {text :'第五章　桂林路 0485'},
          {text :'第六章　局限 0597'},
          {text :'第七章　南湖 0753'},
          {text :'第八章　北京 0895'},
          {text :'第九章　决定 1021'}
        ]
      }
    },
    methods:{

    },
    created(){
      this.$axios.post('/api/book/getBook/'+this.$route.params.bookId).then(response => {
        this.book = response.data;
      });
    }
  }
</script>

<style scoped>
  section{
    margin: 20px;
  }
  h2{
    font-size: 18px;
    margin-bottom: 10px;
  }
  .h2text{
    line-height: 24px;
    font-size: 15px;
    color: #646464;
  }
</style>
