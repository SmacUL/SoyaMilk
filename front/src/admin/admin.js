import Vue from 'vue'
import admin from './Admin.vue'
import router from './router'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import axios from 'axios';

Vue.use(ElementUI);
Vue.use(iView);
Vue.config.productionTip = false;
Vue.prototype.$axios = axios
/* eslint-disable no-new */
new Vue({
  el: '#admin',
  router,
  components: { admin },
  template: '<admin/>'
})
