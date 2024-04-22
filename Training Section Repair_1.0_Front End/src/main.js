import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import ElementUI from 'element-ui'
import VueAxios from "vue-axios"
import VueRouter from 'vue-router'
import 'element-ui/lib/theme-chalk/index.css';
import index from './components/index.vue'
import Vuex from 'vuex'
// import Store  from 'vuex'
import login from './components/login.vue'
import hytable from './components/hytable.vue'
// import ceshi from './components/ceshi.vue'
import safetable from './components/safetable.vue'
import tabbar from './components/tabbar.vue'
import { get, post } from '../src/api/axios'
// 图片封装 （pic）

Vue.prototype.get = get
Vue.prototype.post = post
Vue.prototype.url_a = "http://10.180.56.100:8080"//图片地址
// Vue.config.productionTip = false
// Vue.prototype.$axios = axios
// axios.defaults.baseURL = '/api'
// Vue.config.productionTip = false
Vue.use(VueAxios, axios);
Vue.use(VueRouter)
Vue.use(ElementUI);
Vue.use(Vuex);
Vue.component("tabbar",tabbar)
const store = new Vuex.Store({
  // state:{
  //   // 存储token
  //   Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : ''
  // },
  // mutations:{
  //   // 修改token，并将token存入localStorage
  //   changeLogin (state, user) {
  //     state.Authorization = user.Authorization;
  //     localStorage.setItem('Authorization', user.Authorization);
  //   }
  // }
})
export default store;
// const originalPush = VueRouter.prototype.push
// 避免到当前位置的冗余导航
// VueRouter.prototype.push = function push(location) {
//   return originalPush.call(this, location).catch(err => err)
// }


const router = new VueRouter({
  // routes 路由规则
  // route 一天路由规则
  routes: [
    {
      path: '/',
      name: 'index',
      component: index,
      alias: '/index',
    },
    {
      path: '/login',
      name: 'login',
      component: login,
    },
    {
      path: '/hytable',
      name: 'hytable',
      component: hytable,
    },
    {
      path: '/safetable',
      name: 'safetable',
      component: safetable,
    },
    {
      path: '/tabbar',
      name: 'tabbar',
      component: tabbar,
    },
    // {
    //   path: '/ceshi',
    //   name: 'ceshi',
    //   component: ceshi,
    // }
  ]
  
})
// 设置登录过期时间（一天）
// 一天:86400000
let EXPIRESTIME = 6000
// 校验登录

// router.beforeEach(function(to,_form,next) {
//   if (to.meta.needLogin) {
//     //页面是否登录，本地存储中是否有token(uid)数据，否：跳转登录页面
//     let item = localStorage.getItem("Authorization");
//     if (item) {
//       let date = new Date().getTime();
//       // 如果大于就是过期了，如果小于或等于就还没过期
//       if (date - item.startTime > EXPIRESTIME) {
//           localStorage.removeItem('Authorization');
//           alert("登录失效");
//       } else {
//         next();
//       }
//     } 
//   }
// });
// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
// router.beforeEach((to, from, next) => {
//   if (to.path === '/login') {
//     next();
//   } else {
//     let token = localStorage.getItem('Authorization');
 
//     if (token === null || token === '') {
//       next('/login');
//     } else {
//       next();
//     }
//   }
// });

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  store:store
}).$mount('#app')
