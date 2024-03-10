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
        <vs-col v-for="_ in 6" :key="_" :sm="4" style="margin-bottom: 10px">
          <div class="disaster-container">
            <vs-card>
              <template #title>
                <h3>Trendy clothing</h3>
              </template>
              <template #img>
                <img src="../../../assets/matter/one.png" alt="" style="width: 270px;height: 160px"/>
              </template>
              <template #text>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
                <div style="display: flex;justify-content: center;margin-top: 20px">
                  <vs-button style="width: 200px">
                    捐赠物资
                  </vs-button>
                </div>
              </template>
              <template #interactions>
                <vs-button color="danger" icon>
                  <vs-icon><HeartBold /></vs-icon>
                </vs-button>
                <vs-button class="btn-chat" type="shadow">
                  <vs-icon><LikeBold /></vs-icon>
                  <span class="span"> 54 </span>
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
  LikeBold,
  SearchStatusBold
} from '@vuesax-alpha/icons-vue'
</script>




<style scoped>
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
</style>