<script setup>
import {toRefs, ref, onMounted, watch} from "vue";
import * as echarts from "echarts"
import {selectBankTransferByWeek} from "@/api/charity/data.js";
import {VsLoadingFn} from "vuesax-alpha";


const props = defineProps({
  raiseId: {
    type: Number
  }
})

watch(props.raiseId,(val,old) => {
  console.log(val)
})


onMounted(() => {
  console.log(props.raiseId)


  const loadingInstance = VsLoadingFn()

  setTimeout(() => {
    loadingInstance.close()
  }, 500)

})
</script>

<template>

</template>

<style scoped>
</style>