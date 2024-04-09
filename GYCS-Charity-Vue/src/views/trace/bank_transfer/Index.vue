<script setup>
import {reactive, ref, toRefs, watch} from "vue";
import {selectBankTransferInfo} from "@/api/charity/raiseFund.js";
import {getPage, VsLoadingFn} from "vuesax-alpha";
import {
  BankBold, Buildings2Bold,
  KeyBold,
  MessageEditBold,
  NotificationStatusBold,
  ShieldSearchBold,
  UserBold
} from "@vuesax-alpha/icons-vue";
import * as echarts from "echarts";
import {selectBankTransferByWeek} from "@/api/charity/data.js";
const transfer = ref()
const transferInfo = ref({})
const total = ref(0)
const active = ref(false)
const cardIdValue = ref("")
const selected = ref()
const main = ref()
const data = reactive({
  queryParams: {
    pageNum: 1,
    pageSize: 10
  },
  tableData: [],
  option: {
    color: ['#80FFA5', '#00DDFF', '#37A2FF', '#FF0087', '#FFBF00'],
    title: {
      text: '当前公益募资活动近七天交易情况'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        label: {
          backgroundColor: '#6a7985'
        }
      }
    },
    legend: {
      data: ['转账数量', '转账金额']
    },
    toolbox: {
      feature: {
        saveAsImage: {}
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: [
      {
        type: 'category',
        boundaryGap: false,
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周七']
      }
    ],
    yAxis: [
      {
        type: 'value'
      }
    ],
    series: [
      {
        name: '转账数量',
        type: 'line',
        stack: 'Total',
        smooth: true,
        lineStyle: {
          width: 0
        },
        showSymbol: false,
        areaStyle: {
          opacity: 0.8,
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            {
              offset: 0,
              color: 'rgb(128, 255, 165)'
            },
            {
              offset: 1,
              color: 'rgb(1, 191, 236)'
            }
          ])
        },
        emphasis: {
          focus: 'series'
        },
        data: []
      },
      {
        name: '转账金额',
        type: 'line',
        stack: 'Total',
        smooth: true,
        lineStyle: {
          width: 0
        },
        showSymbol: false,
        areaStyle: {
          opacity: 0.8,
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            {
              offset: 0,
              color: 'rgb(0, 221, 255)'
            },
            {
              offset: 1,
              color: 'rgb(77, 119, 255)'
            }
          ])
        },
        emphasis: {
          focus: 'series'
        },
        data: []
      }
    ]
  }
})
const {queryParams,tableData,option} = toRefs(data)

const raiseId = ref(0)
watch(selected,(val,old) => {
  raiseId.value = val.raiseId
  selectBankTransferByWeek({raiseId: raiseId.value}).then(res => {
    option.value.series[0].data = res.transaction_amount
    option.value.series[1].data = res.transaction_count
    var mainEcharts = echarts.init(main.value);
    mainEcharts.setOption(option.value)
  })
  const loadingInstance = VsLoadingFn({
    target: transfer
  })
  setTimeout(() => {
    loadingInstance.close()
  }, 500)
})

// 根据身份证查询银行卡转账信息
function handleSearch(){
  selectBankTransferInfo({cardId: cardIdValue.value}).then(res => {
    tableData.value = res.data
    total.value = res.total
    transferInfo.value =  res.data[0]

    if (res.code == 200 && res.total != 0) {
      raiseId.value = res.data[0].raiseId
      selectBankTransferByWeek({raiseId: res.data[0].raiseId}).then(res => {
        option.value.series[0].data = res.transaction_amount
        option.value.series[1].data = res.transaction_count
        var mainEcharts = echarts.init(main.value);
        mainEcharts.setOption(option.value)
      })
      active.value = true
    }
  })
}
</script>

<template>
  <div class="background-container">
    <!-- 页面内容 -->
    <div class="InputContainer">
      <input v-model="cardIdValue" type="text" name="text" class="input" id="input" placeholder="请输入身份证查询银行卡转账溯源记录" @keyup.enter="handleSearch">

      <label for="input" class="labelforsearch">
        <svg @click="handleSearch" style="width: 25px;height: 25px" viewBox="0 0 512 512" class="searchIcon"><path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"></path></svg>
      </label>
    </div>
  </div>

  <vs-dialog v-model="active" lock-scroll full-screen overlay-blur>
    <template #header>
      <h4 class="not-margin">银行卡转账记录详细</h4>
    </template>
    <div class="con-form">
      <div style="display: flex;justify-content: space-evenly">
        <div style="width: 40%">
          <div style="display: flex;justify-content: center;align-content: center;margin-bottom: 20px">
            <div id="main" ref="main" style="width: 100%;height: 200px"></div>
          </div>
          <vs-table v-model="selected" Striped>
            <template #thead>
              <vs-tr>
                <vs-th> 公益ID </vs-th>
                <vs-th> 捐款姓名 </vs-th>
                <vs-th> 开户银行 </vs-th>
                <vs-th> 开户账户 </vs-th>
                <vs-th> 转账金额 </vs-th>
                <vs-th> 转账时间 </vs-th>
              </vs-tr>
            </template>
            <template #tbody>
              <vs-tr v-for="(item, i) in getPage(tableData,queryParams.pageNum,queryParams.pageSize)" :key="i" :data="item">
                <vs-td>
                  {{ item.raiseId }}
                </vs-td>
                <vs-td>
                  {{ item.donorName }}
                </vs-td>
                <vs-td>
                  {{ item.donorBank }}
                </vs-td>
                <vs-td>
                  {{ item.donorAccount }}
                </vs-td>
                <vs-td>
                  {{ item.donorAmount }} ￥
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
                  :page-sizes="[5,10,13]"
                  :total="total"
                  progress
                  infinite
              />
            </template>

          </vs-table>
        </div>
        <div style="width: 50%;">
          <div ref="transfer" id="transfer">
            <div class="modal">
              <form class="form">
                <div class="banner"></div>
                <label class="title">银行转账记录交易信息</label>
                <div class="benefits">
                  <div class="benefit-form">
                    <vs-input v-model="transferInfo.donorName" state="dark" placeholder="捐款人姓名"  label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><UserBold /></vs-icon>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.donorCardId" state="dark" placeholder="捐款人身份证" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><ShieldSearchBold /></vs-icon>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.donorBank" state="dark" placeholder="捐款人开户银行" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><BankBold /></vs-icon>
                      </template>
                    </vs-input >
                    <vs-input v-model="transferInfo.donorAccount" state="dark" placeholder="捐款人开户账户" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><KeyBold /></vs-icon>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.donorWorkUnit" state="dark" placeholder="工作单位" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><Buildings2Bold /></vs-icon>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.donorAmount" state="dark" placeholder="捐款金额" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-currency-yen" viewBox="0 0 16 16">
                          <path d="M8.75 14v-2.629h2.446v-.967H8.75v-1.31h2.445v-.967H9.128L12.5 2h-1.699L8.047 7.327h-.086L5.207 2H3.5l3.363 6.127H4.778v.968H7.25v1.31H4.78v.966h2.47V14h1.502z"/>
                        </svg>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.donorAmountCN" state="dark" placeholder="捐款金额大写(￥)" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-currency-yen" viewBox="0 0 16 16">
                          <path d="M8.75 14v-2.629h2.446v-.967H8.75v-1.31h2.445v-.967H9.128L12.5 2h-1.699L8.047 7.327h-.086L5.207 2H3.5l3.363 6.127H4.778v.968H7.25v1.31H4.78v.966h2.47V14h1.502z"/>
                        </svg>
                      </template>
                    </vs-input>
                  </div>
                  <div style="display: flex;align-items: center">
                    <svg style="width: 120px;height: 120px" height="60" node-id="1" sillyvg="true" template-height="60" template-width="60" version="1.1" viewBox="0 0 60 60" width="60" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><defs node-id="24"></defs><g node-id="67"><path d="M 35.40 51.00 L 35.60 51.10 L 35.40 51.20 C 34.10 51.50 33.00 52.60 32.70 53.90 L 32.60 54.00 L 32.50 53.80 C 32.20 52.50 31.10 51.40 29.80 51.10 L 29.60 51.00 L 29.80 50.90 C 31.10 50.60 32.20 49.50 32.50 48.20 L 32.60 48.00 L 32.70 48.20 C 33.00 49.60 34.00 50.70 35.40 51.00 L 35.40 51.00 Z" fill="#5d8ef9" fill-rule="nonzero" group-id="1" node-id="30" stroke="none" target-height="6" target-width="5.999998" target-x="29.6" target-y="48"/><path d="M 29.00 52.60 L 29.10 52.60 L 29.00 52.60 C 28.20 52.90 27.50 53.50 27.30 54.40 L 27.30 54.50 L 27.30 54.40 C 27.10 53.60 26.40 52.90 25.60 52.70 L 25.50 52.70 L 25.60 52.70 C 26.40 52.50 27.10 51.80 27.30 51.00 L 27.30 50.90 L 27.30 51.00 C 27.50 51.70 28.20 52.40 29.00 52.60 L 29.00 52.60 Z" fill="#5d8ef9" fill-rule="nonzero" group-id="1" node-id="32" stroke="none" target-height="3.5999985" target-width="3.6000004" target-x="25.5" target-y="50.9"/><path d="M 26.50 9.10 L 26.70 9.20 L 26.50 9.30 C 25.20 9.60 24.30 10.60 24.00 11.80 L 23.90 12.00 L 23.80 11.80 C 23.50 10.50 22.50 9.60 21.30 9.30 L 21.10 9.20 L 21.30 9.10 C 22.60 8.80 23.50 7.80 23.80 6.60 L 23.90 6.40 L 24.00 6.60 C 24.20 7.80 25.20 8.80 26.50 9.10 L 26.50 9.10 Z" fill="#ffbe1b" fill-rule="nonzero" group-id="1" node-id="34" stroke="none" target-height="5.6" target-width="5.6000004" target-x="21.1" target-y="6.4"/><path d="M 18.80 11.00 L 18.90 11.00 L 18.80 11.00 C 18.00 11.30 17.30 11.90 17.10 12.80 L 17.10 12.90 L 17.10 12.80 C 16.90 12.00 16.20 11.30 15.40 11.10 L 15.30 11.10 L 15.40 11.10 C 16.20 10.90 16.90 10.20 17.10 9.40 L 17.10 9.30 L 17.10 9.40 C 17.30 10.20 18.00 10.80 18.80 11.00 L 18.80 11.00 Z" fill="#ffbe1b" fill-rule="nonzero" group-id="1" node-id="36" stroke="none" target-height="3.5999994" target-width="3.5999994" target-x="15.3" target-y="9.3"/><path d="M 51.60 29.30 L 51.80 29.30 L 51.60 29.30 C 50.40 29.70 49.50 30.60 49.30 31.70 L 49.30 31.90 L 49.30 31.70 C 49.00 30.50 48.10 29.60 47.00 29.40 L 46.80 29.40 L 47.00 29.40 C 48.20 29.10 49.10 28.20 49.30 27.10 L 49.30 26.90 L 49.30 27.10 C 49.50 28.10 50.40 29.00 51.60 29.30 L 51.60 29.30 Z" fill="#2371f5" fill-rule="nonzero" group-id="1" node-id="38" stroke="none" target-height="5" target-width="5" target-x="46.8" target-y="26.9"/><path d="M 31.90 27.00 C 32.20 26.00 32.40 24.90 32.40 23.80 C 32.40 17.90 27.60 13.10 21.70 13.10 C 15.80 13.10 11.00 17.90 11.00 23.80 C 11.00 29.70 15.80 34.50 21.70 34.50 C 23.30 34.50 24.80 34.10 26.20 33.50 C 27.10 30.60 29.20 28.20 31.90 27.00 Z" fill="#ffbe1b" fill-rule="nonzero" group-id="1" node-id="40" stroke="none" target-height="21.4" target-width="21.400002" target-x="11" target-y="13.1"/><path d="M 29.70 23.80 C 29.70 28.20 26.10 31.80 21.70 31.80 C 17.30 31.80 13.70 28.20 13.70 23.80 C 13.70 19.40 17.30 15.80 21.70 15.80 C 26.10 15.70 29.70 19.30 29.70 23.80 Z" fill="#ffe37b" fill-rule="nonzero" group-id="1" node-id="42" stroke="none" target-height="16.099998" target-width="16" target-x="13.7" target-y="15.7"/><path d="M 47.10 36.70 C 47.10 42.60 42.30 47.40 36.40 47.40 C 30.50 47.40 25.70 42.60 25.70 36.70 C 25.70 30.80 30.50 26.00 36.40 26.00 C 42.30 26.00 47.10 30.70 47.10 36.70 Z" fill="#ffbe1b" fill-rule="nonzero" group-id="1" node-id="44" stroke="none" target-height="21.400002" target-width="21.399998" target-x="25.7" target-y="26"/><path d="M 44.40 36.70 C 44.40 41.10 40.80 44.70 36.40 44.70 C 32.00 44.70 28.40 41.10 28.40 36.70 C 28.40 32.30 32.00 28.70 36.40 28.70 C 40.80 28.60 44.40 32.20 44.40 36.70 Z" fill="#ffe37b" fill-rule="nonzero" group-id="1" node-id="46" stroke="none" target-height="16.1" target-width="16.000002" target-x="28.4" target-y="28.6"/><path d="M 47.90 22.00 C 47.60 21.80 47.20 22.00 47.00 22.30 L 45.80 24.80 C 44.80 21.30 42.80 18.10 40.00 15.70 C 36.80 13.10 32.80 11.60 28.70 11.60 C 28.30 11.60 28.00 11.90 28.00 12.30 C 28.00 12.70 28.30 13.00 28.70 13.00 C 36.20 13.00 42.70 18.10 44.60 25.30 L 42.00 24.00 C 41.70 23.80 41.30 24.00 41.10 24.30 C 40.90 24.60 41.10 25.00 41.40 25.20 L 45.40 27.20 C 45.50 27.20 45.60 27.30 45.70 27.30 C 45.90 27.30 46.20 27.20 46.30 26.90 L 48.30 23.00 C 48.40 22.60 48.20 22.20 47.90 22.00 Z" fill="#2371f5" fill-rule="nonzero" group-id="1" node-id="48" stroke="none" target-height="15.699999" target-width="20.400002" target-x="28" target-y="11.6"/><path d="M 9.90 38.20 C 10.20 38.40 10.60 38.20 10.80 37.90 L 12.00 35.40 C 13.00 38.90 15.00 42.10 17.80 44.50 C 21.00 47.10 25.00 48.60 29.10 48.60 C 29.50 48.60 29.80 48.30 29.80 47.90 C 29.80 47.50 29.50 47.20 29.10 47.20 C 21.60 47.20 15.10 42.10 13.20 34.90 L 15.80 36.20 C 16.10 36.40 16.50 36.20 16.70 35.90 C 16.90 35.60 16.70 35.20 16.40 35.00 L 12.40 33.00 C 12.30 33.00 12.20 32.90 12.10 32.90 C 11.90 32.90 11.60 33.00 11.50 33.30 L 9.50 37.20 C 9.40 37.60 9.60 38.00 9.90 38.20 Z" fill="#5d8ef9" fill-rule="nonzero" group-id="1" node-id="50" stroke="none" target-height="15.699997" target-width="20.4" target-x="9.4" target-y="32.9"/></g><g node-id="68"><g node-id="70"><path d="M 17.60 20.70 C 17.60 21.20 18.30 21.70 19.50 22.00 C 20.70 22.30 22.00 22.30 23.20 22.00 C 24.40 21.70 25.10 21.20 25.10 20.70 C 25.10 19.90 23.40 19.20 21.40 19.20 C 19.30 19.20 17.60 19.90 17.60 20.70 L 17.60 20.70 Z M 22.70 24.00 C 23.30 23.50 24.10 23.20 24.80 23.20 C 25.00 23.10 25.00 22.90 25.00 22.70 L 25.00 21.30 C 24.90 22.10 23.30 22.80 21.30 22.80 C 19.30 22.80 17.50 22.10 17.50 21.30 L 17.50 22.60 C 17.50 23.40 19.20 24.10 21.20 24.10 C 21.80 24.10 22.30 24.10 22.70 24.00 L 22.70 24.00 Z M 17.60 23.10 L 17.60 24.40 C 17.60 25.20 19.30 25.90 21.30 25.90 L 21.60 25.90 C 21.70 25.40 21.90 24.90 22.20 24.60 C 21.90 24.60 21.60 24.70 21.30 24.70 C 19.30 24.60 17.60 23.90 17.60 23.10 L 17.60 23.10 Z M 21.30 26.50 C 19.20 26.50 17.60 25.90 17.60 25.00 L 17.60 26.30 C 17.60 27.10 19.30 27.80 21.30 27.80 L 21.80 27.80 C 21.60 27.40 21.50 27.00 21.50 26.50 L 21.30 26.50 Z M 26.70 24.60 C 26.10 23.90 25.10 23.70 24.20 23.90 C 23.30 24.10 22.60 24.80 22.40 25.70 C 22.20 26.60 22.40 27.60 23.10 28.20 C 24.10 29.20 25.70 29.20 26.70 28.20 C 27.70 27.30 27.70 25.60 26.70 24.60 L 26.70 24.60 Z M 25.40 27.10 C 25.20 27.30 25.00 27.60 24.80 27.90 C 24.60 27.60 24.40 27.40 24.20 27.10 C 24.00 26.90 23.70 26.70 23.40 26.50 C 23.70 26.30 23.90 26.10 24.20 25.90 C 24.40 25.70 24.60 25.40 24.80 25.10 C 25.00 25.40 25.20 25.60 25.40 25.90 C 25.60 26.10 25.90 26.30 26.20 26.50 C 25.90 26.70 25.70 26.90 25.40 27.10 L 25.40 27.10 Z M 25.40 27.10" fill="#ffffff" fill-rule="nonzero" group-id="2,4" node-id="57" stroke="none" target-height="10" target-width="10.200001" target-x="17.5" target-y="19.2"/></g></g><path d="M 32.10 33.80 C 32.10 34.30 32.80 34.80 34.00 35.10 C 35.20 35.40 36.50 35.40 37.70 35.10 C 38.90 34.80 39.60 34.30 39.60 33.80 C 39.60 33.00 37.90 32.30 35.90 32.30 C 33.80 32.30 32.10 33.00 32.10 33.80 L 32.10 33.80 Z M 37.20 37.10 C 37.80 36.60 38.60 36.30 39.30 36.30 C 39.50 36.20 39.50 36.00 39.50 35.80 L 39.50 34.40 C 39.40 35.20 37.80 35.90 35.80 35.90 C 33.80 35.90 32.00 35.20 32.00 34.40 L 32.00 35.70 C 32.00 36.50 33.70 37.20 35.70 37.20 C 36.30 37.20 36.70 37.20 37.20 37.10 L 37.20 37.10 Z M 32.10 36.20 L 32.10 37.50 C 32.10 38.30 33.80 39.00 35.80 39.00 L 36.10 39.00 C 36.20 38.50 36.40 38.00 36.70 37.70 C 36.40 37.70 36.10 37.80 35.80 37.80 C 33.80 37.70 32.10 37.00 32.10 36.20 L 32.10 36.20 Z M 35.80 39.60 C 33.70 39.60 32.10 39.00 32.10 38.10 L 32.10 39.40 C 32.10 40.20 33.80 40.90 35.80 40.90 L 36.30 40.90 C 36.10 40.50 36.00 40.10 36.00 39.60 L 35.80 39.60 Z M 41.20 37.70 C 40.60 37.00 39.60 36.80 38.70 37.00 C 37.80 37.20 37.10 37.90 36.90 38.80 C 36.70 39.70 36.90 40.70 37.60 41.30 C 38.60 42.30 40.20 42.30 41.20 41.30 C 42.10 40.40 42.20 38.80 41.20 37.70 L 41.20 37.70 Z M 39.90 40.20 C 39.70 40.40 39.50 40.70 39.30 41.00 C 39.10 40.70 38.90 40.50 38.70 40.20 C 38.50 40.00 38.20 39.80 37.90 39.60 C 38.20 39.40 38.40 39.20 38.70 39.00 C 38.90 38.80 39.10 38.50 39.30 38.20 C 39.50 38.50 39.70 38.70 39.90 39.00 C 40.10 39.20 40.40 39.40 40.70 39.60 C 40.40 39.80 40.20 40.00 39.90 40.20 L 39.90 40.20 Z M 39.90 40.20" fill="#ffffff" fill-rule="nonzero" group-id="3,5" node-id="65" stroke="none" target-height="10" target-width="10.200001" target-x="32" target-y="32.3"/></svg>
                  </div>
                  <div class="benefit-form">
                    <vs-input v-model="transferInfo.aidedName" state="dark" placeholder="受助人姓名" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><UserBold /></vs-icon>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.aideCardId" state="dark" placeholder="受助人身份证号" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><ShieldSearchBold /></vs-icon>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.aideBank" state="dark" placeholder="受助人开户银行" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><BankBold /></vs-icon>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.aideAccount" state="dark" placeholder="受助人开户账户" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><KeyBold /></vs-icon>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.aideWorkUnit" state="dark" placeholder="受助人工作单位" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><Buildings2Bold /></vs-icon>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.donorDescription" state="dark" placeholder="捐款描述" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><NotificationStatusBold /></vs-icon>
                      </template>
                    </vs-input>
                    <vs-input v-model="transferInfo.donorRemarks" state="dark" placeholder="捐款备注" label-float style="width: 300px;color: #181818" disabled>
                      <template #icon>
                        <vs-icon><MessageEditBold /></vs-icon>
                      </template>
                    </vs-input>
                  </div>
                </div>
                <div class="modal--footer">
                </div>
              </form>
            </div>
          </div>

        </div>
      </div>
    </div>
  </vs-dialog>
</template>

<style lang="scss" scoped>
.background-container {
  /* 设置背景图片的路径和属性 */
  background-image: url("@/assets/images/bg_2.png");
  background-size: cover; /* 背景图片铺满整个容器 */
  background-position: center; /* 图片在容器中居中 */
  /* 其他样式 */
  width: 100%;
  height: 100%; /* 使用视口高度，使背景铺满整个页面 */
  display: flex;
  justify-content: center;
  padding: 20px 20px;
}

.InputContainer {
  margin-top: 1%;
  height: 60px;
  width: 900px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgb(255, 255, 255);
  border-radius: 10px;
  overflow: hidden;
  cursor: pointer;
  padding-left: 15px;
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.075);
}

.input {
  width: 900px;
  height: 100%;
  border: none;
  outline: none;
  font-weight: bold;
  font-size: 1.1em;
  caret-color: rgb(255, 81, 0);
}

.labelforsearch {
  cursor: text;
  padding: 0px 12px;
}

.searchIcon {
  width: 13px;
}


.searchIcon path {
  fill: rgb(114, 114, 114);
}

.micIcon path {
  fill: rgb(255, 81, 0);
}

.not-margin {
  margin: 0px;
  font-weight: bold;
  padding: 10px;

}
.con-form {
  width: 100%;

  .flex {
    display: flex;
    align-items: center;
    justify-content: space-between;

    a {
      font-size: 0.8rem;
      opacity: 0.7;

      &:hover {
        opacity: 1;
      }
    }
  }
}

/*右边溯源记录详细样式*/
.modal {
  width: 100%;
  background: linear-gradient(180deg, #DCF9E0 0%, #FFFFFF 30.21%);
  border-radius: 16px;
}

.banner {
  width: 100%;
  height: 30px;
  margin: 0;
  background-size: 100%;
  background-repeat: no-repeat;
}

.title {
  font-weight: 700;
  font-size: 17px;
  line-height: 21px;
  text-align: center;
  color: #2B2B2F;
  margin-bottom: 15px;
}

.description {
  max-width: 80%;
  margin: auto;
  font-weight: 600;
  font-size: 11px;
  line-height: 16px;
  text-align: center;
  color: #5F5D6B;
}

.tab-container {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  position: relative;
  padding: 2px;
  background-color: #ebebec;
  border-radius: 9px;
  margin: 10px 20px 0px 20px;
}

.indicator {
  content: "";
  width: 50%;
  height: 28px;
  background: #FFFFFF;
  position: absolute;
  top: 2px;
  left: 2px;
  z-index: 9;
  border: 0.5px solid rgba(0, 0, 0, 0.04);
  box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.12), 0px 3px 1px rgba(0, 0, 0, 0.04);
  border-radius: 7px;
  transition: all 0.2s ease-out;
}

.tab {
  width: 50%;
  height: 28px;
  position: relative;
  z-index: 99;
  background-color: transparent;
  border: 0;
  outline: none;
  flex: none;
  align-self: stretch;
  flex-grow: 1;
  cursor: pointer;
  font-weight: 500;
}

.tab--1:hover ~ .indicator {
  left: 2px;
}

.tab--2:hover ~ .indicator {
  left: calc(50% - 2px);
}

.benefits {
  padding: 20px;
  display: flex;
  justify-content: space-evenly;
  gap: 20px;
}

.benefits > span {
  font-size: 15px;
  color: #2B2B2F;
  font-weight: 700;
}

.benefits ul {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.benefits ul li {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  gap: 10px;
}

.benefits ul li span {
  font-weight: 600;
  font-size: 12px;
  color: #5F5D6B;
}

.modal--footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  border-top: 1px solid #ebebec;
}

.price {
  position: relative;
  font-size: 32px;
  color: #2B2B2F;
  font-weight: 900;
}

.price sup {
  font-size: 13px;
}

.price sub {
  width: fit-content;
  position: absolute;
  font-size: 11px;
  color: #5F5D6B;
  bottom: 5px;
  display: inline-block;
}

.upgrade-btn {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 215px;
  height: 40px;
  background: #0bdd12;
  box-shadow: 0px 0.5px 0.5px #EFEFEF, 0px 1px 0.5px rgba(239, 239, 239, 0.5);
  border-radius: 7px;
  border: 0;
  outline: none;
  color: #ffffff;
  font-size: 13px;
  font-weight: 600;
  transition: all 0.3s cubic-bezier(0.15, 0.83, 0.66, 1);
}

.upgrade-btn:hover {
  background-color: #07b90d;
}

.form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}


</style>
