import Vue from 'vue'
import Router from 'vue-router'

import Login from '../pages/Login'
import Sign from '../pages/Signin'
import index from '../pages/index/index'
import Book from '../pages/book/Book'
import note from '../pages/Note'
import self from '../pages/self/Self'
import editor from '../pages/editor'
import collect from '../pages/collect'

// 首页面的子路由
import books from '../pages/index/BookList.vue'
import page from '../pages/index/page'

// book 页面的子路由
import synopsis from '../pages/book/synopsis'
import bookContent from '../pages/book/bookContent'
import bookNote from '../pages/book/bookNote'

// 个人页面的子路由
import follow from '../pages/self/follow'
import followBook from '../pages/self/followBook'
import myNote from '../pages/self/myNote'
import collectFolder from '../pages/self/collectFolder'

// 别人界面的子路由


Vue.use(Router);

export default new Router({
  routes: [
    {
      path:"/",
      alias:'/login',
      name:"login",
      component:Login,
    },
    {
      path:"/sign",
      name:"Sign",
      component:Sign
    },
    {   // 首页有两个子路由
      path:"/index",
      name:"index",
      component:index,
      children: [
        {
          path:'/index',                         // 推荐书籍的轮播图
          alias: "/index/books",
          name:'books',
          component: books
        },
        {
          path: '/index/page/:subdivisionId',         // 具体分类的书籍
          name: 'page',
          component: page
        }
      ]
    },
    {   // 菜单栏切换路由
      path:"/book/:bookId",
      name:"book",
      // redirect: '/book/synopsis/'+this.$route.params.bookId,// 默认路由
      component:Book,
      children: [
        {
          path: '/book/synopsis/:bookId',                // 书籍简介
          name:"synopsis",
          component: synopsis,
          alias:"/book/:bookId",
        },
        {
          name:"bookContent",                   //  评论没有页面。。
          path: "/book/bookContent/:bookId",
          component: bookContent
        },
        {
          name:"bookNote",                   //  评论没有页面。。
          path: "/book/bookNote/:bookId",
          component: bookNote
        }
      ]
    },
    {
      path:"/note/:noteId",                  // 笔记显示页面 无子路由
      name:"note",
      component:note
    },
    {

      name:"self",
      path:'/self/:userId',   //没有参数代表了自己的页面,后端可通过session获取数据
      component:self,
      children:[
        {
          name:"follow",                   // 关注的人
          path:'/self/follow/:userId',
          component: follow
        },
        {
          name:"followBook",               // 关注的书籍
          path:'/self/followBook/:userId',
          component: followBook
        },
        {
          name:"myNote",                   // 我的笔记
          path:'/self/myNotes/:userId',
          component: myNote
        },
        {
          name:"collectFolder",           // 收藏夹
          path:'/self/collectFolder/:userId',
          component: collectFolder
        }
      ]
    },
    {
      path:"/editor/:bookId",            // 编辑文章页面 无子路由
      name:"editor",
      component:editor
    },
    {
      path:"/collect/:collectId",        // 收藏文章页面 无子路由
      name:'collect',
      component:collect
    },
  ]
});

