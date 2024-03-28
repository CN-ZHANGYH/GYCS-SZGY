<template>
  <div class="trace_container">
    <div style="display: flex;flex: 1;justify-content: center;align-items: center">
      <div>
        <vs-input v-model="cardId" placeholder="请输入身份证号码查询公益溯源信息" style="width: 600px;height: 50px">
          <template #icon>
            <vs-icon><SearchFavorite1Bold /></vs-icon>
          </template>
        </vs-input>
      </div>
      <div>
        <vs-button type="relief" color="success" style="width: 80px;height: 45px" @click="handleSearch">搜索</vs-button>
      </div>
    </div>
    <div class="trace_table">
      <div class="table">
        <vs-table style="width: 1100px">
          <template #thead>
            <vs-tr>
              <vs-th> Name </vs-th>
              <vs-th> Email </vs-th>
              <vs-th> Id </vs-th>
            </vs-tr>
          </template>
          <template #tbody>
            <vs-tr
                v-for="(item, i) in getPage(activiteTrace, queryParams.pageNum, queryParams.pageSize)"
                :key="i"
                :data="item"
            >
              <vs-td>
              </vs-td>
              <vs-td>
              </vs-td>
              <vs-td>
              </vs-td>
            </vs-tr>
          </template>
          <template #footer>
            <vs-pagination
                v-model:current-page="queryParams.pageNum"
                v-model:page-size="queryParams.pageSize"
                :page-sizes="[3, 5, 7]"
                :total="activiteTotal"
            />
          </template>
        </vs-table>
      </div>
      <div class="table">
        <vs-table style="width: 1100px">
          <template #thead>
            <vs-tr>
              <vs-th> Name </vs-th>
              <vs-th> Email </vs-th>
              <vs-th> Id </vs-th>
            </vs-tr>
          </template>
          <template #tbody>
            <vs-tr
                v-for="(item, i) in getPage(donationTrace, queryParams.pageNum, queryParams.pageSize)"
                :key="i"
                :data="item"
            >
              <vs-td>
                {{ item.donorAddress }}
              </vs-td>
              <vs-td>
                {{ item.amount }} ￥
              </vs-td>
              <vs-td>
                {{ item.transTime }}
              </vs-td>
            </vs-tr>
          </template>
          <template #footer>
            <vs-pagination
                v-model:current-page="queryParams.pageNum"
                v-model:page-size="queryParams.pageSize"
                :page-sizes="[3, 5, 7]"
                :total="donationTotal"
            />
          </template>
        </vs-table>
      </div>
    </div>
  </div>
</template>

<script setup>
import {
  SearchFavorite1Bold
} from '@vuesax-alpha/icons-vue'
import {onMounted, reactive, ref, toRefs} from "vue";
import {getPage, VsLoadingFn} from 'vuesax-alpha'
import {selectTraceByCardId} from "@/api/charity/trace.js";
const page = ref(1)
const cardId = ref("")
const activiteTotal = ref(0)
const donationTotal = ref(0)

const data = reactive({
  queryParams: {
    pageNum: 1,
    pageSize: 5
  },
  activiteTrace: [],
  donationTrace: []
})

const {activiteTrace,donationTrace,queryParams} = toRefs(data)



function handleSearch(){
  selectTraceByCardId({cardId: cardId.value}).then(res => {
    if (res.code == 200) {
      console.log(res)
      activiteTrace.value = res.activiteTrace.rows
      donationTrace.value = res.donationTrace.rows
      activiteTotal.value = res.activiteTrace.total
      donationTotal.value = res.donationTrace.total

      const loadingInstance = VsLoadingFn()
      setTimeout(() => {
        loadingInstance.close()
      }, 1000)
    }
  })

}
onMounted(() => {

})
</script>

<style scoped>
.trace_container {
  display: flex;
  flex-direction: column;
}

.trace_table {
  margin: 30px 10px;
  display: flex;
  flex-direction: column;
}

.trace_table > .table {
  display: flex;
  justify-content: center;
}

:deep(.vs-table) {
  margin-top: 20px;
}
</style>
