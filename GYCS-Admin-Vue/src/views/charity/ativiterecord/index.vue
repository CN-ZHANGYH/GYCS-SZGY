<template>
  <div class="active-container">
    <div class="flex-container">
      <div>
        <div class="card-left">
          <div class="card-content">
            <p class="card-title">hover effect
            </p><p class="card-para">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
          </div>
        </div>
      </div>
      <div class="right-container" style="width: 80%;height: 600px" v-loading="loading">
        <template v-for="item in activitieRecordList">
          <div class="card " >
            <div class="header">
              <img :src="item.img" class="card-image">
            </div>
            <div class="info">
              <p class="title">{{item.title}}</p>
            </div>
            <div class="footer">
              <p class="tag">⭐反馈：5星</p>
              <button class="button" @click="openModal(item)">
                <svg class="svgIcon" viewBox="0 0 384 512">
                  <path
                      d="M214.6 41.4c-12.5-12.5-32.8-12.5-45.3 0l-160 160c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L160 141.2V448c0 17.7 14.3 32 32 32s32-14.3 32-32V141.2L329.4 246.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3l-160-160z"
                  ></path>
                </svg>
              </button>
            </div>
          </div>
        </template>
      </div>
    </div>
    <div style="display: flex;justify-content: center;margin-left: 15%;margin-top: 10%">
      <pagination
          v-show="total>0"
          :total="total"
          v-model:page="queryParams.pageNum"
          v-model:limit="queryParams.pageSize"
          @pagination="getList"
      />
    </div>
  </div>
  <el-drawer
      v-model="drawer"
      title="公益活动详细信息"
      :direction="direction"
      size="70%"
      style="padding: 20px 20px"
  >
    <div>
      <el-descriptions
          title="⭐请确认当前的公益活动信息"
          :column="2"
          size="large"
          border
      >
        <el-descriptions-item >
          <template #label>
            <div class="cell-item">
              <el-icon class="iconStyle">
                <document />
              </el-icon>
              活动标题
            </div>
          </template>
          {{form.title}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon class="iconStyle">
                <user />
              </el-icon>
              发布作者
            </div>
          </template>
          {{form.author}}
        </el-descriptions-item>
        <el-descriptions-item :span="2">
          <template #label>
            <div class="cell-item">
              <el-icon class="iconStyle">
                <collection-tag />
              </el-icon>
              文章信息
            </div>
          </template>
          {{form.content}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon class="iconStyle">
                <alarm-clock />
              </el-icon>
              开始时间
            </div>
          </template>
          <el-tag size="small">{{form.startTime}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon class="iconStyle">
                <alarm-clock />
              </el-icon>
              结束时间
            </div>
          </template>
          {{form.endTime}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon class="iconStyle">
                <van />
              </el-icon>
              物流方式
            </div>
          </template>
          {{form.logisticType}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon class="iconStyle">
                <coordinate />
              </el-icon>
              物流商地址
            </div>
          </template>
          {{form.logisticAddress}}
        </el-descriptions-item>
        <el-descriptions-item :span="2">
          <template #label>
            <div class="cell-item">
              <el-icon class="iconStyle">
                <place />
              </el-icon>
              代理机构地址
            </div>
          </template>
          {{form.lncomeAddress}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon class="iconStyle">
                <Picture />
              </el-icon>
              活动图片
            </div>
          </template>
          <img :src="form.img" width="400" height="300"  style="border-radius: 10px">
        </el-descriptions-item>
      </el-descriptions>
    </div>
  </el-drawer>
</template>

<script setup name="ActivitieRecord">

import {getActivityList} from "@/api/charity/activite.js";
import {AlarmClock, CollectionTag, Coordinate, Document, Picture, Place, User, Van} from "@element-plus/icons-vue";

const { proxy } = getCurrentInstance();

const activitieRecordList = ref([]);

const total = ref(0);
const title = ref("");
const direction = ref('rtl')
const loading = ref(true);
const drawer = ref(false)
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 8
  },
  rules: {
  }
});
const { queryParams, form, rules } = toRefs(data);


function openModal(item) {
  drawer.value = true
  form.value = item
}

function getList(){
  loading.value = true
  getActivityList(queryParams.value).then(res => {
    activitieRecordList.value = res.rows
    total.value = res.total;
    loading.value = false;
  })
}
onMounted(() => {
  getList()
})

</script>


<style>
.flex-container {
  display: flex;
  justify-content: space-between;
}


.right-container {
  width: 85%;
  height: 600px;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
}

.card-left {
  width: 300px;
  height: 200px;
  margin-right: 40px;
  background-color: #FF3CAC;
  background-image: linear-gradient(225deg, #FF3CAC 0%, #784BA0 50%, #2B86C5 100%);
  color: white;
  border-radius: 8px;
  overflow: hidden;
  position: relative;
  perspective: 1000px;
  transition: all 0.5s cubic-bezier(0.23, 1, 0.320, 1);
  cursor: pointer;
}

.card-content {
  padding: 10px;
  position: relative;
  z-index: 1;
}

.card-left:hover {
  transform: scale(1.05);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}

.card-left:before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 60%;
  background-color: rgba(0, 0, 0, 0.1);
  opacity: 0;
  transition: all 0.5s cubic-bezier(0.23, 1, 0.320, 1);
  z-index: 1;
}

.card-left:hover:before {
  opacity: 1;
}

.card-left .card-title {
  font-size: 24px;
  font-weight: 700;
  margin-bottom: 8px;
  text-transform: capitalize;
}

.card-left .card-para {
  font-size: 16px;
  opacity: 0.8;
}

.card-content {
  transform: translateY(50%);
  transition: all 0.5s cubic-bezier(0.23, 1, 0.320, 1);
}

.card-left:hover .card-content {
  transform: translateY(0%);
}

.card {
  border: 2px solid rgb(99 102 241);
  margin-right: 10px;
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  border-radius: 0.75rem;
  background-color: white;
  width: 290px;
  height: 370px;
  box-shadow: 0 4px 6px -1px rgba(0,0,0,.1),
  0 2px 4px -2px rgba(0,0,0,.1);
}

.header {
  position: relative;
  background-clip: border-box;
  margin-top: 1.5rem;
  margin-left: 1rem;
  margin-right: 1rem;
  border-radius: 0.75rem;
  background-color: rgb(33 150 243);
  box-shadow: 0 10px 15px -3px rgba(33,150,243,.4),0 4px 6px -4px rgba(33,150,243,.4);
  height: 197px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.info {
  border: none;
  padding: 1rem;
  text-align: left;
  margin-left: 10px;
}

.title {
  color: rgb(38 50 56);
  letter-spacing: 0;
  line-height: 1.375;
  font-weight: 600;
  font-size: 1.1rem;
  margin-bottom: 0.5rem;
}

.footer {
  padding: 0.75rem;
  border: 1px solid rgb(236 239 241);
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: rgba(0, 140, 255, 0.082);
  border-bottom-right-radius: 10px;
  border-bottom-left-radius: 10px;
}

.tag {
  font-weight: 300;
  font-size: .75rem;
  display: block;
}


.card-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
}





.button {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: rgb(20, 20, 20);
  border: none;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0px 0px 0px 4px rgba(180, 160, 255, 0.253);
  cursor: pointer;
  transition-duration: 0.3s;
  overflow: hidden;
  position: relative;
}

.svgIcon {
  width: 12px;
  transition-duration: 0.3s;
}

.svgIcon path {
  fill: white;
}

.button:hover {
  width: 140px;
  border-radius: 50px;
  transition-duration: 0.3s;
  background-color: rgb(33 150 243);
  align-items: center;
}

.button:hover .svgIcon {
  /* width: 20px; */
  transition-duration: 0.3s;
  transform: translateY(-200%);
}

.button::before {
  position: absolute;
  bottom: -20px;
  content: "查看更多";
  color: white;
  /* transition-duration: .3s; */
  font-size: 0px;
}

.button:hover::before {
  font-size: 13px;
  opacity: 1;
  bottom: unset;
  /* transform: translateY(-30px); */
  transition-duration: 0.3s;
}


.active-container {
  display: flex;
  flex-direction: column;
}

</style>
