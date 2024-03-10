<template>
  <div style="display: flex;flex-direction: column">
    <!--  搜索框  -->
    <div style="font-size: 20px;font-weight: bold;flex: 1;display: flex;margin-bottom: 20px;justify-content: center">
      <vs-input v-model="value1" placeholder="根据关键词搜索你想要捐款的公益活动" style="width: 600px;height: 50px">
        <template #icon>
          <vs-icon><SearchStatusBold /></vs-icon>
        </template>
      </vs-input>
      <vs-button style="height: 48px;width: 80px;" >
        搜索
      </vs-button>
    </div>
    <div style="margin-left: 5%">
      <vs-row style="padding: 20px 10%;">
        <vs-col v-for="_ in 6" :key="_" :sm="4" style="margin-bottom: 10px">
          <div class="donation-container">
            <vs-card type="3">
              <template #title>
                <h3>Live a life of madness</h3>
              </template>
              <template #img>
                <img src="../../../assets/matter/two.png" alt="" style="width: 500px;height: 155px"/>
              </template>
              <template #text>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
              </template>
              <template #interactions>
                <vs-button color="danger">
                  捐款
                </vs-button>
                <vs-button class="btn-chat" type="shadow">
                  <vs-icon><EyeBold /></vs-icon>
                  <span class="span"> 查看 </span>
                </vs-button>
              </template>
            </vs-card>
          </div>
        </vs-col>
      </vs-row>
    </div>
    <div class="con-pagination">
      <vs-pagination
          v-model:current-page="page"
          :layout="['total', 'prev', 'pager', 'next', 'jumper', 'sizes']"
          :total="50"
      />
    </div>
  </div>
</template>

<script setup>
import {
  HeartBold,
  EyeBold,
  SearchStatusBold
} from '@vuesax-alpha/icons-vue'
</script>

<style scoped>
.donation-container {
  padding: 5px 5px;
  margin-bottom: 30px;
}

.con-pagination {
  display: flex;
  justify-content: center;
  margin-top: 5px;
}
</style>