import { createRouter, createWebHistory } from 'vue-router'
import {getToken} from "@/utils/auth.js";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'product',
      meta: {title: '产品'},
      component: () => import('../views/product/home/HomeIndex.vue')
    },
    {
      path: '/login',
      name: 'login',
      meta: {title: '登录'},
      component: () => import('../views/product/login/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      meta: {title: '注册'},
      component: () => import('../views/product/register/Register.vue')
    },
    {
      path: '/about',
      name: 'about',
      meta: {title: '关于'},
      component: () => import('../views/product/about/AboutIndex.vue')
    },
    {
      path: '/404',
      name: 'NotFound',
      meta: {title: '404'},
      component: () => import('../views/product/404/Index.vue')
    },
    {
      path: '/home',
      name: 'home',
      meta: {title: '首页'},
      component: () => import('../views/home/Index.vue'),
      children: [
        {
          path: 'index',
          name: 'index',
          meta: {title: '文档'},
          component: () => import('../views/main/Index.vue')
        },
        {
          path: 'user',
          name: 'user',
          meta: {title: '用户'},
          component: () => import('../views/profile/Index.vue')
        },
        {
          path: 'rank',
          name: 'rank',
          meta: {title: '排行'},
          component: () => import('../views/charity/rank/Index.vue')
        },
        {
          path: 'disaster_area',
          name: 'disaster_area',
          meta: {title: '公益活动'},
          component: () => import('../views/charity/disaster/Index.vue'),
        },
        {
          path: 'raise_fund',
          name: 'raise_fund',
          meta: {title: '公益募资'},
          component: () => import('../views/charity/donation/Index.vue')
        },
        {
          path: 'vote',
          name: 'vote',
          meta:  {title: '公益投票'},
          component: () => import('../views/charity/vote/Index.vue')
        },
        {
          path: 'online',
          name: 'online',
          meta:  { title: '在线支付'},
          component: ()=> import('../views/payment/online/Index.vue')
        },
        {
          path: 'bank_transfer',
          name: 'bank_transfer',
          meta: {title: '银行转账'},
          component: ()=> import('../views/payment/banktransfer/Index.vue')
        },
        {
          path: 'trace',
          name: 'trace',
          meta: {title: '溯源记录'},
          component: () => import('../views/trace/Index.vue')
        },
        {
          path: 'materials',
          name: 'materials',
          meta: {title: '物资捐赠'},
          component: () => import('../views/charity/materials/Index.vue'),
        },
        {
          path: 'donation_record',
          name: 'donation_record',
          meta: {title: '捐款记录'},
          component: () => import('../views/record/donation/Index.vue'),
        },
        {
          path: 'material_record',
          name: 'material_record',
          meta: {title: '物资捐赠记录'},
          component: () => import('../views/record/material/Index.vue'),
        },
        {
          path: 'donation_detail',
          name: 'donation_detail',
          meta: {title: '捐款详细'},
          component: () => import('../views/trace/donation/Index.vue'),
        },
        {
          path: 'material_detail',
          name: 'material_detail',
          meta: {title: '物资捐赠详细'},
          component: () => import('../views/trace/material/Index.vue'),
        },
        {
          path: 'transfer_trace',
          name: 'transfer_trace',
          meta: {title: '银行转账溯源详细'},
          component: () => import('../views/trace/bank_transfer/Index.vue'),
        }
      ]
    },
  ]
})

router.beforeEach((to, from, next) => {

  // 检查目标路由是否存在
  const foundRoute = router.getRoutes().find(route => route.path === to.path)
  if (!foundRoute) {
    next({ name: 'NotFound' })
  }
  var token = getToken();
  console.log(token)
  // 如果token存在则直接返回Home页面
  if (token != undefined && to.name == "login") {
    router.push({
      name: 'index',
    })
  }
  // 如果当前的Token不存在 需要直接跳转home页面
  if (token == undefined) {
    if (to.name == "product" || to.name == "register" || to.name == "about" || to.name == "login") {
      next()
    }else {
      router.push({
        name: 'login'
      })
    }
  }
  next()
})


router.afterEach((to) => {
  document.title = to.meta.title || '默认标题' // 如果meta.title不存在，则使用默认标题
})

export default router
