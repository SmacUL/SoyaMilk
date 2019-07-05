<template>
  <div class="layout">
      <top-nav></top-nav>
      <div class="mainDiv">
        <Layout>
          <Sider width="170" hide-trigger>
            <category-sider></category-sider>
          </Sider>
          <Content class="Card">
            <!--轮播图-->
            <div class="carouselDiv">
              <Carousel
                :autoplay="true"
                :autoplay-speed="3000"
                dots="inside"
                :radius-dot="false"
                trigger="click"
                arrow="hover">
                <CarouselItem>
                  <div class="demo-carousel"><img src="../../assets/carouselImg/6996.jpg"></div>
                </CarouselItem>
                <CarouselItem>
                  <div class="demo-carousel"><img src="../../assets/carouselImg/7021.jpg"></div>
                </CarouselItem>
                <CarouselItem>
                  <div class="demo-carousel"><img src="../../assets/carouselImg/7022.jpg"></div>
                </CarouselItem>
                <CarouselItem>
                  <div class="demo-carousel"><img src="../../assets/carouselImg/7023.jpg"></div>
                </CarouselItem>
              </Carousel>
            </div>
            <Divider/>
            <router-view/>
          </Content>
        </Layout>
      </div>

    <BackTop :height="100" :bottom="100"></BackTop>
  </div>
</template>

<script>
import topNav from '../../components/TopNav.vue'
import categorySider from './CategorySider.vue'

import books from './BookList.vue'
import page from './page'

export default {
      name: "index",
      components:{
        topNav,
        categorySider,
        page,
        default:books
      },
      data(){
        return{
        }
      }
    }
</script>

<style scoped>
  *{
    border: 0px;
    padding: 0px;
  }
  .layout{
    background-color: #f6f6f6;
  }

  .ivu-layout{
    width: 1000px;
    background-color: #f6f6f6;
    margin-top:50px;
  }
  .ivu-layout-sider{
    z-index: 1;
    position: relative;
    left: 0px;
    background-color: #f6f6f6;

  }
  .ivu-menu{
    background: #ffffff;
    margin-bottom: 10px;
    overflow: hidden;
    border-radius: 2px;
    box-shadow: 0 1px 3px rgba(26,26,26,.1);
    box-sizing: border-box;
    margin-top: 20px;
  }


  .ivu-layout-content{
    margin-top: 20px;
    position: relative;
    background-color: #FFFFFF;
    left: 10px;
    width: 900px;
  }

  .ivu-menu-light:after{
    background: #ffffff;
  }

  .mainDiv{
    background-color: #f6f6f6;
    width: 1000px;
    margin: 0 auto;
  }

    .carouselDiv{
      margin: 0 auto;
      width: 90%;
      height: 200px;
    }

      .ivu-carousel{
        width: 100%;
        height: 100%;
      }
    img{
      width: 100%;
      height: 220px;
    }
</style>
