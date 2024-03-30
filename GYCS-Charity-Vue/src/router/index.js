import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    // { path: '/', redirect: '/home' },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/login/Index.vue')
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/about/Index.vue')
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('../views/home/Index.vue'),
      children: [
        {
          path: 'user',
          name: 'user',
          component: () => import('../views/user/Index.vue')
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
        }
      ]
    },
  ]
})

export default router
