<template>
  <div class="app-container">
    <div>
      <el-card style="border-radius: 20px">
        <div style="display: flex;justify-content: space-evenly;">
          <div style="width: 260px;">
            <div class="card">
              <div class="title">
            <span>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-capslock-fill" viewBox="0 0 16 16">
                  <path d="M7.27 1.047a1 1 0 0 1 1.46 0l6.345 6.77c.6.638.146 1.683-.73 1.683H11.5v1a1 1 0 0 1-1 1h-5a1 1 0 0 1-1-1v-1H1.654C.78 9.5.326 8.455.924 7.816L7.27 1.047zM4.5 13.5a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1h-5a1 1 0 0 1-1-1v-1z"/>
                </svg>
            </span>
                <p class="title-text">
                  新增订单
                </p>
                <p class="percent">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1792 1792" fill="currentColor" height="20" width="20">
                    <path d="M1408 1216q0 26-19 45t-45 19h-896q-26 0-45-19t-19-45 19-45l448-448q19-19 45-19t45 19l448 448q19 19 19 45z">
                    </path>
                  </svg>
                </p>
              </div>
              <div class="data">
                <p>
                  {{totalOrders}}
                </p>
                <el-progress
                    :text-inside="true"
                    :stroke-width="17"
                    :percentage="100"
                    status="success"
                />
              </div>
            </div>
          </div>
          <div style="width: 260px;">
            <div class="card">
              <div class="title">
          <span>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-archive-fill" viewBox="0 0 16 16">
              <path d="M12.643 15C13.979 15 15 13.845 15 12.5V5H1v7.5C1 13.845 2.021 15 3.357 15h9.286zM5.5 7h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1 0-1zM.8 1a.8.8 0 0 0-.8.8V3a.8.8 0 0 0 .8.8h14.4A.8.8 0 0 0 16 3V1.8a.8.8 0 0 0-.8-.8H.8z"/>
            </svg>
          </span>
                <p class="title-text">
                  已完成
                </p>
                <p class="percent">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1792 1792" fill="currentColor" height="20" width="20">
                    <path d="M1408 1216q0 26-19 45t-45 19h-896q-26 0-45-19t-19-45 19-45l448-448q19-19 45-19t45 19l448 448q19 19 19 45z">
                    </path>
                  </svg>
                </p>
              </div>
              <div class="data">
                <p>
                  {{signedOrders}}
                </p>

                <el-progress
                    :text-inside="true"
                    :stroke-width="17"
                    :percentage="signedPercentage"
                    status="warning"
                />
              </div>
            </div>
          </div>
          <div style="width: 260px;">
            <div class="card">
              <div class="title">
            <span>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send-fill" viewBox="0 0 16 16">
                  <path d="M15.964.686a.5.5 0 0 0-.65-.65L.767 5.855H.766l-.452.18a.5.5 0 0 0-.082.887l.41.26.001.002 4.995 3.178 3.178 4.995.002.002.26.41a.5.5 0 0 0 .886-.083l6-15Zm-1.833 1.89L6.637 10.07l-.215-.338a.5.5 0 0 0-.154-.154l-.338-.215 7.494-7.494 1.178-.471-.47 1.178Z"/>
                </svg>
            </span>
                <p class="title-text">
                  运输中
                </p>
                <p class="percent">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1792 1792" fill="currentColor" height="20" width="20">
                    <path d="M1408 1216q0 26-19 45t-45 19h-896q-26 0-45-19t-19-45 19-45l448-448q19-19 45-19t45 19l448 448q19 19 19 45z">
                    </path>
                  </svg>
                </p>
              </div>
              <div class="data">
                <p>
                  {{unsignedOrders}}
                </p>

                <el-progress
                    :text-inside="true"
                    :stroke-width="17"
                    :percentage="unsignedPercentage"
                    status="exception"
                />
              </div>
            </div>
          </div>
          <div style="width: 260px;">
            <div class="card">
              <div class="title">
              <span>
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                  </svg>
              </span>
                <p class="title-text">
                  已签收
                </p>
                <p class="percent">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1792 1792" fill="currentColor" height="20" width="20">
                    <path d="M1408 1216q0 26-19 45t-45 19h-896q-26 0-45-19t-19-45 19-45l448-448q19-19 45-19t45 19l448 448q19 19 19 45z">
                    </path>
                  </svg>
                </p>
              </div>
              <div class="data">
                <p>
                  {{signedOrders}}
                </p>

                <el-progress
                    :text-inside="true"
                    :stroke-width="17"
                    :percentage="signedPercentage"
                />
              </div>
            </div>
          </div>
        </div>
      </el-card>
    </div>
    <div>
      <el-table :data="orderList" :border="parentBorder" style="width: 100%;margin-top: 10px">
        <el-table-column type="expand">
          <template #default="props">
            <div style="display: flex;justify-content: space-between;padding: 20px 100px">
              <div>
                <p>寄件账户：{{props.row.sourceAddress}}</p>
                <p>物流账户：{{props.row.logisticAddress}}</p>
                <p>机构账户：{{props.row.destAddress}}</p>
              </div>
              <div>
                <p>物流信息：{{props.row.deliveryMessage}}</p>
                <p>签收信息：{{props.row.signMessage}}</p>
                <p>结束信息：{{props.row.endMessage}}</p>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="订单ID" prop="id" width="100"/>
        <el-table-column label="订单编号" prop="orderUuid" />
        <el-table-column label="寄送物品" prop="itemName" width="100"/>
        <el-table-column label="订单时间" prop="createTime"/>
        <el-table-column label="签收状态" prop="isSign" width="100">
          <template #default="props">
            <el-tag :type="props.row.isSign == false ? 'danger' : 'success'">
              {{props.row.isSign == false ? '未签收' : '已签收'}}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="公益ID" prop="activityId" width="100"/>
        <el-table-column label="操作">
          <template #default="props">
            <div>
              <el-button type="success" @click="addLogisticMessage(props.row)" v-if="props.row.endMessage == null">添加物流</el-button>
              <el-button type="primary" @click="endOrder(props.row)" v-if="props.row.endMessage != null">结束订单</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <pagination
          v-show="total>0"
          :total="total"
          v-model:page="queryParams.pageNum"
          v-model:limit="queryParams.pageSize"
          :page-sizes="[1, 5, 7]"
          @pagination="getList"
      />
    </div>

  </div>

  <el-dialog
      v-model="dialogVisible"
      title="添加运输物流信息 "
      width="500"
  >
    <el-steps
        class="mb-4"
        style="max-width: 600px;margin-bottom: 50px"
        :space="200"
        :active="stepNumber"
        simple
    >
      <el-step title="派送" :icon="Promotion" />
      <el-step title="签收" :icon="SuccessFilled" />
      <el-step title="结束" :icon="Flag" />
    </el-steps>
    <el-form v-model="form">
      <el-form-item label="派送信息" v-if="stepNumber == 1">
        <el-input v-model="form.deliveryMessage"  type="textarea" ></el-input>
      </el-form-item>
      <el-form-item label="签收信息"  v-if="stepNumber == 2">
        <el-input v-model="form.signMessage"  type="textarea"></el-input>
      </el-form-item>
      <el-form-item label="结束信息"  v-if="stepNumber == 3">
        <el-input v-model="form.endMessage"  type="textarea"></el-input>
      </el-form-item>

      <div v-if="stepNumber == 4">
        <el-result
            icon="success"
            title="当前订单已完成"
            sub-title="该订单的公益物资捐赠已完成"
        >
        </el-result>
      </div>
    </el-form>
    <template #footer>
      <div class="dialog-footer" v-if="stepNumber != 4">
        <el-button  type="danger" @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="handleUpdateMessage">
          添加
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>


<script setup>
import { ref } from 'vue'
import {
  endOrderById,
  selectOrderIsNotSignList,
  selectOrderProcessByLogistic,
  updateOrderMessage
} from "@/api/charity/order.js";
import {Flag, Promotion, SuccessFilled} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
const loading = ref(true);
const parentBorder = ref(false)
const total = ref(0)
const dialogVisible = ref(false)
const orderList = ref([]);
const stepNumber = ref(1)
const totalOrders = ref(0)
const signedOrders = ref(0)
const unsignedOrders = ref(0)
const signedPercentage = ref(0)
const unsignedPercentage = ref(0)

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 5,
  },
  rules: {
  }
});
const { queryParams, form, rules } = toRefs(data);
function getList() {
  loading.value = true;
  selectOrderIsNotSignList(queryParams.value).then(response => {
    console.log(response)
    orderList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}
getList()


function addLogisticMessage(row){
  form.value = {}
  console.log(row.deliveryMessage);
  console.log(row.signMessage);
  console.log(row.endMessage);
  console.log(stepNumber)
  if (row.deliveryMessage != null) {
    form.value.deliveryMessage = row.deliveryMessage
    stepNumber.value = 2
  }
  if (row.signMessage != null) {
    form.value.signMessage = row.signMessage
    stepNumber.value = 3
  }
  if (row.endMessage != null) {
    form.value.endMessage = row.endMessage
    stepNumber.value = 4
  }

  dialogVisible.value = true
  form.value.orderId = row.id
}

function handleCancel(){
  dialogVisible.value = false
  stepNumber.value = 1
}

function handleUpdateMessage(){
  updateOrderMessage(form.value).then(res => {
    ElMessage.success("添加物流信息成功")
    dialogVisible.value = false
    form.value = {}
    stepNumber.value = 1
    getList()
  })
}

function endOrder(row){
  endOrderById({orderId: row.id}).then(res => {
    if(res.code == 200) {
      ElMessage.success(res.msg);
      getList();
    }else {
      ElMessage.error(res.msg);
    }
  })
}

onMounted(() => {
  selectOrderProcessByLogistic().then(res => {
    signedPercentage.value = res.data.signedPercentage
    unsignedPercentage.value = res.data.unsignedPercentage
    signedOrders.value  = res.data.signedOrders
    unsignedOrders.value = res.data.unsignedOrders
    totalOrders.value = res.data.totalOrders
  })
})
</script>

<style scoped>

.card {
  padding: 1rem;
  background-color: #FFDEE9;
  background-image: linear-gradient(0deg, #FFDEE9 0%, #B5FFFC 100%);
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
  width: 100%;
  border-radius: 20px;
}

.title {
  display: flex;
  align-items: center;
}

.title span {
  position: relative;
  padding: 0.5rem;
  background-color: #10B981;
  width: 1.5rem;
  height: 1.5rem;
  border-radius: 9999px;
}

.title span svg {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: #ffffff;
  height: 1rem;
}

.title-text {
  margin-left: 0.5rem;
  color: #374151;
  font-size: 18px;
}

.percent {
  margin-left: 0.5rem;
  color: #02972f;
  font-weight: 600;
  display: flex;
}

.data {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.data p {
  margin-top: 1rem;
  margin-bottom: 1rem;
  color: #1F2937;
  font-size: 2.25rem;
  line-height: 2.5rem;
  font-weight: 700;
  text-align: left;
}

.data .range {
  position: relative;
  background-color: #E5E7EB;
  width: 100%;
  height: 0.5rem;
  border-radius: 0.25rem;
}

.data .range .fill {
  position: absolute;
  top: 0;
  left: 0;
  background-color: #10B981;
  width: 76%;
  height: 100%;
  border-radius: 0.25rem;
}
</style>
