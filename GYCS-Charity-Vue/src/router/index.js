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
        }
      ]
    },
  ]
})

export default router
