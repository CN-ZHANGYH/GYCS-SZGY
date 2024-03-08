<template>
  <div class="center example-nav">
    <vs-navbar v-model="active" shadow shape="square"
               center-collapsed
               target-scroll="#padding-scroll-content"
               padding-scroll
    >
      <template #left>
        <vs-button type="flat" icon @click="activeSidebar = !activeSidebar">
          <vs-icon><Category/></vs-icon>
        </vs-button>
      </template>
      <vs-navbar-item id="home" :active="active == 'home'">
        首页
      </vs-navbar-item>
      <vs-navbar-item id="activity" :active="active == 'activity'">
        活动中心
      </vs-navbar-item>
      <vs-navbar-item id="user" :active="active == 'user'">
        个人中心
      </vs-navbar-item>
      <vs-navbar-item id="about" :active="active == 'about'">
        关于
      </vs-navbar-item>
      <template #right>
        <vs-button type="flat">Login</vs-button>
        <vs-button>Get Started</vs-button>
      </template>
    </vs-navbar>
    <vs-sidebar v-model="active" v-model:open="activeSidebar" absolute>
      <template #logo>
        <svg
            class="logo-nav"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 401.69 98.4"
            width="100px"
            height="100px"
        >
          <g id="Capa_2" data-name="Capa 2">
            <g id="Capa_1-2" data-name="Capa 1">
              <path
                  d="M105.79,27.23h12.78l16.12,44.65L150.9,27.23h12.77L141.48,85.56h-13.4Z"
              />
              <path
                  d="M168.29,82.12Q164,77.77,164,69.53V42.63h12.23V69.26q0,4.26,2,6.25c1.36,1.33,3.45,2,6.29,2a13.73,13.73,0,0,0,5.75-1.22A16,16,0,0,0,195,73.06V42.63h12.22V85.56H195.91l-.45-4.08a23.47,23.47,0,0,1-15.12,5Q172.54,86.47,168.29,82.12Z"
              />
              <path
                  d="M222.22,80.8q-6.37-5.65-6.38-16.8,0-10.5,5.57-16.39t16.26-5.89q9.78,0,15.17,5.12t5.39,13.72v7.79H227.16c.6,3.32,2.13,5.65,4.57,7s6.06,2,10.83,2a39.59,39.59,0,0,0,7-.64,31.7,31.7,0,0,0,6.11-1.63v8.7q-6,2.72-15.67,2.72Q228.61,86.47,222.22,80.8Zm25.32-20V58.48q0-8-9.51-8c-4,0-6.79.8-8.42,2.4s-2.45,4.24-2.45,7.92Z"
              />
              <path
                  d="M272.81,85.83a31.36,31.36,0,0,1-7.07-1.72V74a31.67,31.67,0,0,0,7,1.94,44,44,0,0,0,7.52.68,23.39,23.39,0,0,0,6.47-.59,2.33,2.33,0,0,0,1.77-2.4,2.8,2.8,0,0,0-1.63-2.53,39.68,39.68,0,0,0-6.25-2.36c-.91-.24-2.06-.6-3.44-1.08q-6.35-2.09-9.29-5c-2-1.93-2.94-4.64-2.94-8.15q0-6.24,4.57-9.51t14.36-3.26a47.58,47.58,0,0,1,7.29.55,34.85,34.85,0,0,1,6.2,1.45V53.77a24.42,24.42,0,0,0-5.52-1.72,30.87,30.87,0,0,0-6-.64,25.74,25.74,0,0,0-6.43.59c-1.39.4-2.08,1.17-2.08,2.31a2.47,2.47,0,0,0,1.49,2.31,38.75,38.75,0,0,0,5.75,2l3,.91A34.78,34.78,0,0,1,295.63,63a10.69,10.69,0,0,1,4,4.21,13.91,13.91,0,0,1,1.23,6.25q0,6.43-4.8,9.74t-14.22,3.31A63,63,0,0,1,272.81,85.83Z"
              />
              <path
                  d="M312,83q-4.39-3.49-4.39-9.83A12.49,12.49,0,0,1,312.16,63q4.57-3.75,13.18-3.76H338V58a8.13,8.13,0,0,0-.95-4.26,5.63,5.63,0,0,0-3.35-2.26,24.6,24.6,0,0,0-6.84-.73A44.66,44.66,0,0,0,312.75,53v-8.6a36,36,0,0,1,7.47-2,55.67,55.67,0,0,1,9.19-.73q20.29,0,20.29,16.58V85.56H339.11l-.64-3.89q-5.25,4.8-15.58,4.8A17,17,0,0,1,312,83Zm21.19-5.89A12.69,12.69,0,0,0,338,73.6V66.81H325.61q-7.16,0-7.15,5.89,0,5.7,7.78,5.7A17.2,17.2,0,0,0,333.17,77.09Z"
              />
              <path
                  d="M372,63.73l-15.85-21.1h13.23l9.24,12.5,9.23-12.5h13.23L385.3,63.73l16.39,21.83H388.38L378.6,72.43l-9.79,13.13H355.5Z"
              />
              <polygon
                  points="92.25 8.05 75.25 8.05 46.14 53.12 17.04 8.05 0.04 8.05 46.15 79.45 92.25 8.05"
              />
              <polygon
                  points="46.15 87.39 7.11 26.94 0 26.94 46.14 98.4 92.29 26.94 85.18 26.94 46.15 87.39"
              />
              <polygon
                  points="70.34 1.37 46.14 38.17 21.95 1.37 39.75 5.87 39.75 0 52.54 0 52.54 5.87 70.34 1.37"
              />
            </g>
          </g>
        </svg>
      </template>
      <template #header />
      <vs-sidebar-item id="home">
        <template #icon>
          <vs-icon><Home2Bold /></vs-icon>
        </template>
        首页
      </vs-sidebar-item>
      <vs-sidebar-group>
        <template #header>
          <vs-sidebar-item arrow>
            <template #icon>
              <vs-icon><UserBold /></vs-icon>
            </template>
            个人中心
          </vs-sidebar-item>
        </template>
        <vs-sidebar-item id="profile">
          <template #icon>
            <vs-icon><SecurityUserBold /></vs-icon>
          </template>
          身份信息
        </vs-sidebar-item>
        <vs-sidebar-item id="donation_record">
          <template #icon>
            <vs-icon><MobileProgrammingBold /></vs-icon>
          </template>
          捐款记录
        </vs-sidebar-item>
        <vs-sidebar-item id="feedback_record">
          <template #icon>
            <vs-icon><SmsEditBold /></vs-icon>
          </template>
          反馈记录
        </vs-sidebar-item>
      </vs-sidebar-group>
      <vs-sidebar-group>
        <template #header>
          <vs-sidebar-item arrow>
            <template #icon>
              <vs-icon><Send2Bold /></vs-icon>
            </template>
            公益中心
          </vs-sidebar-item>
        </template>
        <vs-sidebar-item id="disaster_area">
          <template #icon>
            <vs-icon><BoxTickBold /></vs-icon>
          </template>
          灾区公益
        </vs-sidebar-item>
        <vs-sidebar-item id="raise_fund">
          <template #icon>
            <vs-icon><ScreenmirroringBold /></vs-icon>
          </template>
          慈善公益
        </vs-sidebar-item>
        <vs-sidebar-item id="rank">
          <template #icon>
            <vs-icon><HeartBold /></vs-icon>
          </template>
          公益排行
        </vs-sidebar-item>
      </vs-sidebar-group>
      <vs-sidebar-item id="trace">
        <template #icon>
          <vs-icon><HierarchySquareBold /></vs-icon>
        </template>
        溯源中心
      </vs-sidebar-item>
      <template #footer>
        <vs-row justify="space-between">
          <vs-avatar>
            <img src="" alt="" />
          </vs-avatar>

          <vs-avatar badge-color="danger" badge-position="top-right">
            <i class="bx bx-bell" />
            <template #badge> 28 </template>
          </vs-avatar>
        </vs-row>
      </template>
    </vs-sidebar>
    <div id="padding-scroll-content" class="square">
      <div class="child">
        <!-- 所有的主体的Card -->
        <div class="main-card">
          <router-view/>
        </div>
      </div>
      <!--页脚内容-->
      <Footer>
      </Footer>
    </div>
  </div>

</template>
<script lang="ts" setup>
import {onMounted, reactive, ref, watch} from 'vue'
import {
  Category,
  Home2Bold,
  UserBold,
  SecurityUserBold,
  MobileProgrammingBold,
  SmsEditBold,
  Send2Bold,
  BoxTickBold,
  ScreenmirroringBold,
  HeartBold,
  HierarchySquareBold
} from '@vuesax-alpha/icons-vue'
import Footer from "@/components/Layout/Footer.vue";
import router from '../../router/index.js'
const active = ref('home')
const activeSidebar = ref(false)

const menuOptions = reactive([
  {
    id: 1,
    name: "home"
  },
  {
    id: 2,
    name: "activity"
  },
  {
    id: 3,
    name: "user"
  },
  {
    id: 4,
    name: "about"
  },
  {
    id: 5,
    name: "profile"
  },
  {
    id: 6,
    name: "donation_record"
  },
  {
    id: 7,
    name: "feedback_record"
  },
  {
    id: 8,
    name: "disaster_area"
  },
  {
    id: 9,
    name: "raise_fund"
  },
  {
    id: 10,
    name: "rank"
  },
  {
    id: 11,
    name: "trace"
  }
])

// 使用路由
// 监听当前的菜单栏的值
watch(active,(val,old) => {
  menuOptions.forEach((item) =>{
    if (val == item.name) {
      router.push({
        name: item.name
      })
    }
  })
})

</script>


<style scoped lang="scss">
.square {
  height: 980px;
  position: relative;
  overflow: auto;
  width: 100%;
  .child {
    height: 980px;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    padding: 0px 10px;
  }
  .footer {
    height: 200px;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
  }
}
.example-nav {
  background: #f0f0f0 !important;
  padding: 0px !important;
  position: relative;
  img {
    max-height: 22px;
  }
}

.main-card {
  width: 100%;
  height: 88%;
  margin-top: 5%;
  padding: 20px;
  border-radius: 10px;
  background-color: #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.main-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
</style>

