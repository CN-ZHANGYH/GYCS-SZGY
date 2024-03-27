<template>
  <div style="display: flex;flex-direction: column">
    <!--  搜索框  -->
    <div style="font-size: 20px;font-weight: bold;flex: 1;display: flex;margin-bottom: 20px;justify-content: center">
      <vs-input v-model="value1" placeholder="根据关键词搜索你想要捐赠物资的灾区" style="width: 600px;height: 50px">
        <template #icon>
          <vs-icon><SearchStatusBold /></vs-icon>
        </template>
      </vs-input>
      <vs-button style="height: 48px;width: 80px;" >
        搜索
      </vs-button>
    </div>
    <div style="margin-left: 5%">
      <vs-row style="padding: 5px 15%;">
        <vs-col v-for="(item,index) in getPage(activityList, queryParams.pageNum, queryParams.pageSize)" :key="index" :sm="4" style="margin-bottom: 10px">
          <div class="disaster-container">
            <vs-card>
              <template #title>
                <h3>{{item.title}}</h3>
              </template>
              <template #img>
                <img :src="item.img" alt="" style="width: 270px;height: 160px"/>
              </template>
              <template #text>
                <p>{{item.content.substring(0,30) + "......"}}</p>
                <div style="display: flex;justify-content: center;margin-top: 20px">
                  <vs-button style="width: 200px" @click="donationMaterials(item)">
                    捐赠物资
                  </vs-button>
                </div>
              </template>
              <template #interactions>
                <vs-button color="danger" icon>
                  <vs-icon><HeartBold /></vs-icon>
                </vs-button>
                <vs-button class="btn-chat" type="shadow">
                  <vs-icon><EyeBold /></vs-icon>
                  <span class="span" @click="viewActivity(item)"> 查看 </span>
                </vs-button>
              </template>
            </vs-card>
          </div>
        </vs-col>
      </vs-row>
    </div>
    <div class="con-pagination">
      <vs-pagination
          v-model:current-page="queryParams.pageNum"
          v-model:page-size="queryParams.pageSize"
          :layout="['total', 'prev', 'pager', 'next', 'jumper', 'sizes']"
          :total="total"
          :page-sizes="[1,3,5,6]"
      />
    </div>
  </div>
  <vs-dialog v-model="isDialogOpen" scroll lock-scroll auto-width>
    <template #header>
      <h2>关于{{form.title}}的公益活动详细内容</h2>
    </template>
    <div class="con-content-scroll">
      <vs-alert type="shadow" color="dark">
        <template #title>发布作者： {{form.author}}</template>
        <div>
          {{form.content}}
        </div>

        <template #footer>发布时间： {{form.createTime}}</template>
      </vs-alert>
      <vs-alert style="margin-top: 10px" type="shadow">
        <div style="display: flex;flex: 1;">
          <div>
            <img :src="form.img" alt="灾区活动的图片" width="400" height="250" style="border-radius: 10px">
          </div>
          <div style="margin-top: 20px;">
            <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
              <h2 style="margin: 10px 10px;font-size: 14px">当前状态: </h2>
              <vs-input v-model="form.status" placeholder="Name" disabled/>
            </div>
            <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
              <h2 style="margin: 10px 10px;font-size: 14px">开始时间: </h2>
              <vs-input v-model="form.startTime" placeholder="Name" disabled/>
            </div>
            <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
              <h2 style="margin: 10px 10px;font-size: 14px">结束时间: </h2>
              <vs-input v-model="form.endTime" placeholder="Name" disabled/>
            </div>
            <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
              <h2 style="margin: 10px 10px;font-size: 14px">运输方式: </h2>
              <vs-input v-model="form.logisticType" placeholder="Name" disabled/>
            </div>
          </div>
        </div>
        <div style="margin-top: 20px;">
          <div style="display: flex;flex: 1">
            <div>
              <vs-button
                  color="primary"
                  type="flat"
              >
                物流公司地址
              </vs-button>
            </div>
            <div style="margin-top: 13px;font-size: 15px;font-weight: bold">
              {{form.logisticAddress}}
            </div>
          </div>
          <div style="display: flex;flex: 1">
            <div>
              <vs-button
                  color="primary"
                  type="flat"
              >
                代理机构地址
              </vs-button>
            </div>
            <div style="margin-top: 13px;font-size: 15px;font-weight: bold">
              {{form.lncomeAddress}}
            </div>
          </div>
        </div>
      </vs-alert>
    </div>
  </vs-dialog>
</template>

<script setup>
import {
  EyeBold,
  HeartBold,
  SearchStatusBold
} from '@vuesax-alpha/icons-vue'
import {reactive, ref, toRefs, watch} from "vue";

import {getActivityList} from "@/api/charity/activite.js";
import {getPage} from "vuesax-alpha";
import {useRouter} from "vue-router";
const route = useRouter()
const isDialogOpen = ref(false)
const activityList = ref([])
const total = ref(0)
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 6
  },
});

const { queryParams, form } = toRefs(data);

function donationMaterials(item){
  route.push({
    name: "materials",
    query: {
      id: item.id,
      lncomeAddress: item.lncomeAddress,
      logisticAddress: item.logisticAddress
    }
  })
}


function viewActivity(item){
  isDialogOpen.value = true
  form.value = item

}

function selectActivityList() {
  getActivityList().then(res => {
    activityList.value = res.rows
    total.value = res.total;
  })
}

selectActivityList()
</script>

<style lang="scss" scoped>
.disaster-container {
  padding: 5px 5px;
}

:deep(.vs-card) {
  width: 270px;
  height: 330px;
}


:deep(.vs-card:hover) {
  box-shadow: 0 5px 10px 0 rgba(0,0,0,var(--vs-shadow-opacity)) !important;
}

.con-pagination {
  display: flex;
  justify-content: center;
  margin-top: 5px;
}

.con-content-scroll {
  p {
    margin: 20px 0px;
    position: relative;
    display: block;
    font-size: 0.9rem;
    width: 100%;
  }
}
</style>
