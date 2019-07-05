// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import VueSimplemde from 'vue-simplemde';
import 'simplemde/dist/simplemde.min.css';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
Vue.prototype.$axios = axios;
import $ from 'jquery';

import utils from './js/utils'
Vue.prototype.$utils=utils;
Vue.use(ElementUI);
Vue.use(VueSimplemde);
Vue.use(iView);


// Vue.use(iView, {
//   i18n: function(path, options) {
//     let value = i18n.t(path, options)
//     if (value !== null && value !== undefined) {
//       return value
//     }
//     return ''
//   }
// })

Vue.config.productionTip = false;
axios.defaults.withCredentials = true; // 解决代理跨域请求,session丢失问题

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
