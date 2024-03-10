import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/home' },
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
          path: 'about',
          name: 'about',
          component: () => import('../views/about/Index.vue')
        },
        {
          path: 'rank',
          name: 'rank',
          component: () => import('../views/charity/rank/Index.vue')
        },
        {
          path: 'disaster_area',
          name: 'disaster_area',
          component: () => import('../views/charity/disaster/Index.vue')
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
        }
      ]
    },
  ]
})

export default router
