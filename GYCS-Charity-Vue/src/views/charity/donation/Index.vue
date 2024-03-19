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
        <vs-col v-for="(item,index) in getPage(raiseFundList, queryParams.pageNum, queryParams.pageSize)" :key="index" :sm="4" style="margin-bottom: 10px">
          <div class="donation-container">
            <vs-card type="3">
              <template #title>
                <h3>{{item.title}}</h3>
              </template>
              <template #img>
                <img src="../../../assets/matter/two.png" alt="" style="width: 500px;height: 155px"/>
              </template>
              <template #text>
                <p>{{item.description.substring(0,35) + "......" }} </p>
              </template>
              <template #interactions>
                <vs-button color="danger" @click="donationHandle(item)">
                  捐款
                </vs-button>
                <vs-button class="btn-chat" type="shadow">
                  <vs-icon><EyeBold /></vs-icon>
                  <span class="span" @click="viewRaiseFund(item)"> 查看 </span>
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
      <h3>关于{{raiseInfo.title}}募资活动的详细信息</h3>
    </template>
    <div class="con-content-scroll">
      <vs-alert type="shadow" color="dark">
        <template #title> 募资标题：{{raiseInfo.title}} </template>
        {{raiseInfo.description}}
        <template #footer> 发布时间：{{raiseInfo.createTime}} </template>
      </vs-alert>
      <vs-alert type="shadow" color="primary" style="margin-top: 10px">
        <template #title> 证明信息 </template>
        <div>
          <div style="display: flex;flex: 1;margin-bottom: 10px">
            <div style="font-weight: bold;margin-top: 6px">
              真实姓名：
            </div>
            <div>
              <vs-input v-model="form.name" state="primary" placeholder="Primary" disabled style="height: 35px;width: 400px;color: black;font-weight: bold"/>
            </div>
          </div>
          <div style="display: flex;flex: 1;margin-bottom: 10px">
            <div style="font-weight: bold;margin-top: 6px">
              电话号码：
            </div>
            <div>
              <vs-input v-model="form.phone" state="primary" placeholder="Primary" disabled style="height: 35px;width: 400px;color: black;font-weight: bold"/>
            </div>
          </div>
          <div style="display: flex;flex: 1;margin-bottom: 10px">
            <div style="font-weight: bold;margin-top: 6px">
              身份证号：
            </div>
            <div>
              <vs-input v-model="form.cardId" state="primary" placeholder="Primary" disabled style="height: 35px;width: 400px;color: black;font-weight: bold"/>
            </div>
          </div>
          <div style="display: flex;flex: 1;margin-bottom: 10px">
            <div style="font-weight: bold;margin-top: 6px">
              家庭住址：
            </div>
            <div>
              <vs-input v-model="form.address" state="primary" placeholder="Primary" disabled style="height: 35px;width: 400px;color: black;font-weight: bold"/>
            </div>
          </div>
          <div style="display: flex;flex: 1;margin-bottom: 10px">
            <div style="font-weight: bold;margin-top: 6px">
              募资证明：
            </div>
            <div>
              <img :src="form.raiseImage" alt="募资证明图片" style="width: 400px;height: 250px;border-radius: 10px;margin-top: 10px">
            </div>
          </div>
          <div style="display: flex;flex: 1;margin-bottom: 10px">
            <div style="font-weight: bold;margin-top: 6px">
              机构证明：
            </div>
            <div>
              <img :src="form.orgImage" alt="募资证明图片" style="width: 400px;height: 250px;border-radius: 10px;margin-top: 10px">
            </div>
          </div>
        </div>
      </vs-alert>
      <vs-alert v-model:page="page" style="margin-top: 10px">
        <template #title> 活动进度 </template>

        <template #page-1>
          <div style="display: flex;justify-content: space-between">
            <div style="display: flex;flex: 1;margin-bottom: 10px">
              <div style="font-weight: bold;margin-top: 7px">
                开始时间：
              </div>
              <div>
                <vs-input v-model="raiseInfo.startTime"  placeholder="Name" disabled state="success"/>
              </div>
            </div>
            <div style="display: flex;flex: 1;margin-bottom: 10px">
              <div style="font-weight: bold;margin-top: 7px">
                结束时间：
              </div>
              <div>
                <vs-input v-model="raiseInfo.endTime"  placeholder="Name" disabled state="success"/>
              </div>
            </div>
          </div>
        </template>
        <template #page-2>
          <div style="display: flex;flex: 1;margin-bottom: 10px">
            <div style="font-weight: bold;margin-top: 7px">
              发起人地址：
            </div>
            <div>
              <vs-input v-model="raiseInfo.promoterAddress"  placeholder="Name" disabled style="width: 400px" state="success"/>
            </div>
          </div>
        </template>

        <template #page-3>
          <div style="display: flex;justify-content: space-between">
            <div style="display: flex;flex: 1;margin-bottom: 10px">
              <div style="font-weight: bold;margin-top: 7px">
                参与人数：
              </div>
              <div>
                <vs-input v-model="raiseInfo.totalPeople"  placeholder="Name" disabled state="success" style="width: 60px"/>
              </div>
            </div>
            <div style="display: flex;flex: 1;margin-bottom: 10px">
              <div style="font-weight: bold;margin-top: 7px">
                总需金额：
              </div>
              <div>
                <vs-input v-model="raiseInfo.totalAmount"  placeholder="Name" disabled state="success" style="width: 80px"/>
              </div>
            </div>
            <div style="display: flex;flex: 1;margin-bottom: 10px">
              <div style="font-weight: bold;margin-top: 7px">
                完成捐款：
              </div>
              <div>
                <vs-input v-model="raiseInfo.overAmount"  placeholder="Name" disabled state="success" style="width: 80px"/>
              </div>
            </div>
            <div style="display: flex;flex: 1;margin-bottom: 10px">
              <div style="font-weight: bold;margin-top: 7px">
                提现金额：
              </div>
              <div>
                <vs-input v-model="raiseInfo.withdrawAmount"  placeholder="Name" disabled state="success" style="width: 80px"/>
              </div>
            </div>
          </div>
        </template>
      </vs-alert>
    </div>
  </vs-dialog>

</template>

<script setup>
import {
  EyeBold,
  SearchStatusBold
} from '@vuesax-alpha/icons-vue'
import {reactive, ref, toRefs} from "vue";
import {getCertificateInfo, getRaiseFundList} from "@/api/charity/raiseFund.js";
import {getPage, VsLoadingFn} from "vuesax-alpha";
import { useRouter } from 'vue-router'

const route = useRouter();
const isDialogOpen = ref(false)
const raiseFundList = ref([])
const total = ref(0)
const page = ref(1)
const data = reactive({
  form: {},
  raiseInfo: {},
  queryParams: {
    pageNum: 1,
    pageSize: 6
  },
});

const { queryParams, form ,raiseInfo } = toRefs(data);
function donationHandle(item){
  console.log(item)
  route.push({
    name: 'online',
    query: {
      raiseId: item.id
    }
  })
}

function selectRaiseFundList() {
  getRaiseFundList().then(res => {
    raiseFundList.value = res.rows
    total.value = res.total
  })
}



function viewRaiseFund(item){
  const loadingInstance = VsLoadingFn()
  isDialogOpen.value = true
  setTimeout(() => {
    loadingInstance.close()
  }, 1000)
  getCertificateInfo({raiseId: item.id}).then(res => {
    form.value = res.data
    raiseInfo.value = item
  })
}
selectRaiseFundList()
</script>

<style  lang="scss" scoped>
.donation-container {
  padding: 5px 5px;
  margin-bottom: 30px;
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
