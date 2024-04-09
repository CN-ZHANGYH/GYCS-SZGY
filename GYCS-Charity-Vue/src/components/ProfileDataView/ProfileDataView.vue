<script setup>
import * as echarts from "echarts"
import {onMounted, reactive, ref, toRefs} from "vue";
import {selectUserOrderStatusByMonth} from "@/api/charity/data.js";
// Generate data
var category = ref([])
var lineData = ref([])
var barData = ref([])
var rateData = ref([])
for (var i = 0; i < 33; i++) {
  // var date = i+2001;
  // category.push(date)
  var rate=barData[i]/lineData[i];
  rateData[i] = rate.toFixed(2);
}
const main = ref()
const data = reactive({
  profileOption: {
    tooltip: {
      trigger: 'axis',
      backgroundColor:'rgba(255,255,255,0.1)',
      axisPointer: {
        type: 'shadow',
        label: {
          show: true,
          backgroundColor: '#7B7DDC'
        }
      }
    },
    legend: {
      data: ['完成率','发货', '签收'],
      textStyle: {
        color: '#B4B4B4',
        fontSize: 14
      },
      top:'2%',
    },
    grid:{
      x:'12%',
      width:'82%',
      y:'12%',
    },
    xAxis: {
      data: category,
      axisLine: {
        lineStyle: {
          color: '#B4B4B4'
        }
      },
      axisTick:{
        show:false,
      },
    },
    yAxis: [{

      splitLine: {show: false},
      axisLine: {
        lineStyle: {
          color: '#B4B4B4',
        }
      },

      axisLabel:{
        formatter:'{value} ',
      }
    },
      {

        splitLine: {show: false},
        axisLine: {
          lineStyle: {
            color: '#B4B4B4',
          }
        },
        axisLabel:{
          formatter:'{value} ',
        }
      }],

    series: [{
      name: '完成率',
      type: 'line',
      smooth: true,
      showAllSymbol: true,
      symbol: 'emptyCircle',
      symbolSize: 8,
      itemStyle: {
        normal: {
          color:'#F02FC2'},
      },
      data: rateData
    },

      {
        name: '发货',
        type: 'bar',
        barWidth: 10,
        itemStyle: {
          normal: {
            barBorderRadius: 5,
            color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [
                  {offset: 0, color: '#956FD4'},
                  {offset: 1, color: '#3EACE5'}
                ]
            )
          }
        },
        data: barData
      },

      {
        name: '签收',
        type: 'bar',
        barGap: '-100%',
        barWidth: 10,
        itemStyle: {
          normal: {
            barBorderRadius: 5,
            color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [
                  {offset: 0, color: 'rgba(156,107,211,0.5)'},
                  {offset: 0.2, color: 'rgba(156,107,211,0.3)'},
                  {offset: 1, color: 'rgba(156,107,211,0)'}
                ]
            )
          }
        },
        z: -12,
        data: lineData
      },
    ]
  }
})

const {profileOption} = toRefs(data)
onMounted(() => {
  selectUserOrderStatusByMonth().then(res => {
    category.value = res.day
    lineData.value = res.delivery
    barData.value = res.sign
    rateData.value = res.rate
    var mainEcharts = echarts.init(main.value)
    mainEcharts.setOption(profileOption.value)
  })

})
</script>

<template>
<div id="main" ref="main" style="width: 720px;height: 240px;">
</div>
</template>

<style scoped>

</style>