import { createApp } from 'vue'
import Vuesax from 'vuesax-alpha'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router/index.js'
import './assets/style/index.scss'

import 'vuesax-alpha/theme-chalk/index.css'
// dark mode
// import 'vuesax-alpha/theme-chalk/dark/css-vars.css'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(Vuesax)

app.mount('#app')
