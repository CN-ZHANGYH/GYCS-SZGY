import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'product',
      component: () => import('../views/product/home/HomeIndex.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/product/login/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/product/register/Register.vue')
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/product/about/AboutIndex.vue')
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('../views/home/Index.vue'),
      children: [
        {
          path: 'user',
          name: 'user',
          component: () => import('../views/profile/Index.vue')
        },
        {
          path: 'rank',
          name: 'rank',
          component: () => import('../views/charity/rank/Index.vue')
        },
        {
          path: 'disaster_area',
          name: 'disaster_area',
          component: () => import('../views/charity/disaster/Index.vue'),
        },
        {
          path: 'raise_fund',
          name: 'raise_fund',
          component: () => import('../views/charity/donation/Index.vue')
        },
        {
          path: 'vote',
          name: 'vote',
          component: () => import('../views/charity/vote/Index.vue')
        },
        {
          path: 'online',
          name: 'online',
          component: ()=> import('../views/payment/online/Index.vue')
        },
        {
          path: 'bank_transfer',
          name: 'bank_transfer',
          component: ()=> import('../views/payment/banktransfer/Index.vue')
        },
        {
          path: 'trace',
          name: 'trace',
          component: () => import('../views/trace/Index.vue')
        },
        {
          path: 'materials',
          name: 'materials',
          component: () => import('../views/charity/materials/Index.vue'),
        },
        {
          path: 'donation_record',
          name: 'donation_record',
          component: () => import('../views/record/donation/Index.vue'),
        },
        {
          path: 'material_record',
          name: 'material_record',
          component: () => import('../views/record/material/Index.vue'),
        },
        {
          path: 'donation_detail',
          name: 'donation_detail',
          component: () => import('../views/trace/donation/Index.vue'),
        },
        {
          path: 'material_detail',
          name: 'material_detail',
          component: () => import('../views/trace/material/Index.vue'),
        },
        {
          path: 'transfer_trace',
          name: 'transfer_trace',
          component: () => import('../views/trace/bank_transfer/Index.vue'),
        }
      ]
    },
  ]
})

export default router
