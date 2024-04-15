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
      <vs-navbar-item id="index" :active="active == 'index'">
        首页
      </vs-navbar-item>
      <vs-navbar-item id="disaster_area" :active="active == 'disaster_area'">
        活动中心
      </vs-navbar-item>
      <vs-navbar-item id="user" :active="active == 'user'">
        个人中心
      </vs-navbar-item>
      <template #right>
        <vs-button @click="handleLogout" >退出登录</vs-button>
      </template>
    </vs-navbar>
    <vs-sidebar v-model="active" v-model:open="activeSidebar" absolute>
      <template #logo>
        <img src="https://blog-1304715799.cos.ap-nanjing.myqcloud.com/imgs/9ec97930b394aea04eaa6d3b1253333.png" alt="">
      </template>
      <template #header />
      <vs-sidebar-item id="index">
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
        <vs-sidebar-item id="user">
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
        <vs-sidebar-item id="material_record">
          <template #icon>
            <vs-icon><SmsEditBold /></vs-icon>
          </template>
          公益记录
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
        <vs-sidebar-item id="vote">
          <template #icon>
            <vs-icon><TrashBold /></vs-icon>
          </template>
          参与投票
        </vs-sidebar-item>
        <vs-sidebar-item id="rank">
          <template #icon>
            <vs-icon><HeartBold /></vs-icon>
          </template>
          公益排行
        </vs-sidebar-item>
      </vs-sidebar-group>
      <vs-sidebar-group>
        <template #header>
          <vs-sidebar-item arrow>
            <template #icon>
              <vs-icon><Send2Bold /></vs-icon>
            </template>
            溯源中心
          </vs-sidebar-item>
        </template>
        <vs-sidebar-item id="trace">
          <template #icon>
            <vs-icon><HierarchySquareBold /></vs-icon>
          </template>
          公益溯源
        </vs-sidebar-item>
        <vs-sidebar-item id="transfer_trace">
          <template #icon>
            <vs-icon><HierarchySquareBold /></vs-icon>
          </template>
          转账记录
        </vs-sidebar-item>
      </vs-sidebar-group>
    </vs-sidebar>
    <div id="padding-scroll-content" class="square">
      <div class="child">
        <!-- 所有的主体的Card -->
        <div class="main-card">
          <router-view/>
        </div>
      </div>
    </div>
    <!--页脚内容-->
    <Footer style="margin-top: 2%">
    </Footer>
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
  HierarchySquareBold,
  TrashBold
} from "@vuesax-alpha/icons-vue";
import Footer from "@/components/Layout/Footer.vue";
import router from '../../router/index.js';
import useUserStore from "@/stores/modules/user.js";
import TimeLine from "@/components/TimeLine/TimeLine.vue";
import {VsLoadingFn} from "vuesax-alpha";

const userStore = useUserStore()
const active = ref('home')
const activeSidebar = ref(false)

const menuOptions = reactive([
  {
    id: 1,
    name: "index"
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
  },
  {
    id: 12,
    name: "vote"
  },
  {
    id: 13,
    name: "online"
  },
  {
    id: 14,
    name: 'material_record'
  },
  {
    id: 15,
    name: 'transfer_trace'
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

      const loadingInstance = VsLoadingFn()
      setTimeout(() => {
        loadingInstance.close()
      }, 500)
    }
  })

})

// 退出登录
function handleLogout() {
  userStore.logOut().then(() => {
    location.href = '/login';
  })
}
</script>


<style scoped lang="scss">
.square {
  position: relative;
  overflow: hidden;
  width: 100%;
  .child {
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
  height: 100%;
  margin-top: 6%;
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

