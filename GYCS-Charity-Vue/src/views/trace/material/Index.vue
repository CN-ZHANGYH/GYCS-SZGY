
<template>
  <div class="con-form">
    <div style="display: flex;justify-content: space-evenly;padding: 20px 20px;">
      <div style="display: flex;flex-direction: column;width:50%">
        <div style="display: flex;justify-content: center">
          <TraceDataView/>
        </div>
        <div>
          <div class="panel">
            <div class="header">
              <span class="trace__title">🌎捐赠物资溯源信息</span>
            </div>
            <div class="content">
              <div class="info">
                <p><strong>捐赠物品：</strong>{{ traceInfo.materialName }}</p>
                <p><strong>物资数量：</strong>{{ traceInfo.materialCount }}</p>
                <p><strong>来源地址：</strong>{{ traceInfo.sourceAddress }}</p>
                <p><strong>物流地址：</strong>{{ traceInfo.logisticAddress }}</p>
                <p><strong>目的地址：</strong>{{ traceInfo.destAddress }}</p>
                <p><strong>转移时间：</strong>{{ traceInfo.transTime }}</p>
                <p><strong>是否签收：</strong>{{ traceInfo.isSign ? '是' : '否' }}</p>
                <p><strong>活动编号：</strong>{{ traceInfo.activitId }}</p>
                <p><strong>溯源状态：</strong>{{ traceInfo.status }}</p>
                <p><strong>交易哈希：</strong><span style="color: rgb(131,129,129);font-weight: bold">{{ traceInfo.transactionHash }}</span></p>
                <p><strong>溯源地址：</strong></p>
                <div class="trace">
                  <vs-tooltip
                      v-for="address in traceAddress"
                      v-model="activeTooltip"
                      placement="bottom"
                      type="shadow"
                      trigger="click"
                  >
                    <vs-button color="primary" @click="activeTooltip = !activeTooltip">
                      {{address.substring(0,30) + "......"}}
                    </vs-button>
                    <template #content>
                      <div class="content-tooltip">
                        <h4 class="center">溯源地址</h4>
                        <p>
                          {{address}}
                        </p>
                      </div>
                    </template>
                  </vs-tooltip>
                </div>
                <p><strong>溯源时间：</strong></p>
                <div class="trace">
                  <span v-for="time in traceTime"><vs-button color="success">{{ time }}</vs-button></span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div style="display: flex;flex-direction: column;width: 45%">
        <div>
          <div>

          </div>
        </div>
        <div class="order__card">
          <div class="timeline">
            <transition-group name="fade">
              <div v-for="(event, index) in events" :key="index" class="timeline-event" :class="{ active: index === activeIndex }">
                <div class="timeline-dot"></div>
                <div class="timeline-line" v-if="index !== 2"></div>
                <div class="timeline-header" @click="toggleEvent(index)">
                  <h3>{{ event.title }}</h3>
                  <span>{{ event.date }}</span>
                </div>
                <transition name="slide-fade">
                  <div v-if="index === activeIndex" class="timeline-content">
                    <p>用户: {{ event.user }}</p>
                    <br>
                    <p>溯源地址: {{ event.description }}</p>
                  </div>
                </transition>
              </div>
            </transition-group>
          </div>
          <hr class="separator">
          <div style="display: flex;flex: 1;align-items: center">
            <p style="margin-right: 20px">订单编号</p>
            <p>29749274829874230</p>
          </div>
          <div style="display: flex;flex: 1">
            <p style="margin-right: 20px">订单运费</p>
            <p>0元</p>
          </div>
          <div style="display: flex;flex: 1;align-items: center">
            <p style="margin-right: 20px">创建时间</p>
            <p>2024-03-28 19:22:00</p>
          </div>
          <div style="display: flex;flex: 1;align-items: center">
            <p style="margin-right: 20px">派送状态</p>
            <p>[正在派送] 顺丰公司正在为您派送当前的订单，当前的派送人为：张宇豪 18933200920</p>
          </div>
          <div style="display: flex;flex: 1;align-items: center">
            <p style="margin-right: 20px">签收状态</p>
            <p>[完成签收] 代理机构已经完成当前的物资签收，感谢您的爱心捐赠，祝您生活愉快。</p>
          </div>
          <div style="display: flex;flex: 1;align-items: center">
            <p style="margin-right: 20px">订单状态</p>
            <p>[参与公益] 当前的灾区物资捐赠参与成功，所有的溯源记录将存储至区块链。</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

import TraceDataView from "@/components/DataSource/TraceDataView.vue";
import {onBeforeMount, onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import {selectMaterialRelationship} from "@/api/charity/record.js";
import {selectMaterialDetailByActivityId} from "@/api/charity/trace.js";
const activeIndex = ref(0)
const activityId = ref(0)
const route = useRouter()
const traceInfo = ref({})
const activeTooltip = ref(false)
const traceAddress = ref([]);
const traceTime = ref([]);
const toggleEvent = (index) => {
  activeIndex.value = activeIndex.value === index ? -1 : index;
};


const events = ref([
  {
    title: '发货时间',
    date: '2024-03-27 11:11:15',
    user: '张宇豪',
    description: '0x559f73f71956cb047cba67efd7223b4df0839d6f'
  },
  {
    title: '运输时间',
    date: '2024-03-28 11:11:15',
    user: '物流商',
    description: '0x2c42f3c06efeec063cad46646611c96836799656'
  },
  {
    title: '签收时间',
    date: '2024-03-29 11:11:15',
    user: '代理机构',
    description: '0x275dfc295b11542a8950f3a057bef2da012e17d8'
  }
]);


onBeforeMount(() => {
  activityId.value = route.currentRoute.value.query.activityId
  selectMaterialRelationship({activityId: activityId.value}).then(res => {
    localStorage.setItem("traceNameList",JSON.stringify(res.data))
  })
  selectMaterialDetailByActivityId({activityId: activityId.value}).then(res => {
    traceInfo.value = res.data
    traceAddress.value = JSON.parse(res.data.traceAddress)
    traceTime.value = JSON.parse(res.data.traceTime)
  })
})
</script>


<style lang="scss" scoped>
.timeline {
  display: flex;
  flex-direction: column;
  width: 750px;
  height: 300px;
}

.timeline-event {
  display: flex;
  align-items: flex-start;
  position: relative;
  margin-bottom: 20px; /* 调整事件之间的垂直间距 */
}

.timeline-dot {
  width: 25px;
  height: 25px;
  background-color: #28a745; /* 绿色 */
  border-radius: 50%;
  margin-right: 20px;
  transition: all 0.5s ease; /* 添加动画效果 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* 添加阴影效果 */
}

.timeline-line {
  position: absolute;
  top: 25px;
  left: 12px;
  width: 2px; /* 线条粗细 */
  height: 110%;
  background-color: #000; /* 黑色 */
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.3); /* 添加阴影 */
}

.timeline-event.active .timeline-dot {
  background-color: #007bff; /* 活跃状态下的颜色 */
}

.timeline-header {
  background-color: #f2f2f2;
  padding: 10px;
  cursor: pointer;
  border-radius: 5px;
  transition: all 0.5s ease; /* 添加动画效果 */
}

.timeline-header:hover {
  background-color: #e0e0e0;
}

.timeline-content {
  padding: 10px;
  box-shadow: 3px 3px 5px 5px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  margin-left: 20px;
  color: #181818;
  font-weight: bolder;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter, .fade-leave-to /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
}

.slide-fade-enter-active {
  transition: all 0.5s ease; /* 添加动画效果 */
}

.slide-fade-leave-active {
  transition: all 0.5s ease; /* 添加动画效果 */
}

.slide-fade-enter, .slide-fade-leave-to {
  opacity: 0;
  transform: translateY(-20px); /* 添加垂直方向的移动 */
}

.order__card {
  width: 100%;
  margin-top: 20px;
  background-color: white;
  margin-bottom: 20px;
  border-radius: 8px;
  gap: 10px;
  border: 2px solid transparent;
  transition: all 0.3s ease-in-out;
  cursor: pointer;
  box-shadow: 3px 3px 5px 5px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  padding: 20px 20px;
}

.panel {
  margin-top: -5%;
  width: 100%;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.header {
  margin-bottom: 10px;
}

.content {
  font-size: 16px;
}

.info {
  margin-bottom: 5px;
}

.info > p {
  margin-bottom: 5px;
  font-size: 16px;
}
.trace {
  display: flex;
}

.trace span {
  border-radius: 5px;
  margin-bottom: 5px;
}


.content-tooltip {
  .body {
    display: flex;
    align-items: flex-start;
    justify-content: center;

    .vs-avatar-content {
      margin-top: -30px;
      border: 3px solid -color('theme-layout');
      box-shadow: 0px 4px 15px 0px rgba(0, 0, 0, 0.1);
    }

    .text {
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      font-size: 0.55rem;
      padding: 10px;
      font-weight: normal;

      span {
        font-weight: bold;
        font-size: 0.7rem;
      }
    }
  }
}

.trace__title {
  font-family: '微软雅黑', Arial, sans-serif; /* 使用指定的字体 */
  font-size: 16px; /* 字体大小 */
  font-weight: bold; /* 字体粗细 */
  color: #333; /* 字体颜色 */
  text-transform: uppercase; /* 将文本转换为大写 */
  letter-spacing: 2px; /* 字符间距 */
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); /* 文字阴影 */
  margin-top: 5px;
}

.separator {
  border-top: 1px solid #ccc;
  margin: 20px 0;
}
</style>