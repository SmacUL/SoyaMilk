import Vue from 'vue'
import Router from 'vue-router'

import Login from '../mainPage/adminLogin'
import index from '../mainPage/adminMain'


import addBook from '../components/addBook'
import hello  from '../components/Hello'
import bookTable from '../components/bookTable'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path:"/",
      name:"Login",
      component:Login,
      alias:'/login'
    },
    {
      path:"/index",
      name:"index",
      component:index,
      children:[
        {
          path:'/index/addBook',
          name:'addBook',
          component:addBook
        },{
          path:'/',
          name:'bookTable',
          component:bookTable
        },
      ]
    }
  ]
});
