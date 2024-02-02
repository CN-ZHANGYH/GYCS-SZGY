<template>
  <router-view />
</template>

<script setup>
import useSettingsStore from '@/store/modules/settings'
import { handleThemeStyle } from '@/utils/theme'

onMounted(() => {
  nextTick(() => {
    // 初始化主题样式
    handleThemeStyle(useSettingsStore().theme)
  })
})
</script>


<style>
.el-dialog {
  border-radius: 15px;
}
.el-button {
  border: none;
}
.el-button:hover {
  transform: translateY(-0.5px);
}

.el-button:hover  {
  transform: scale(1.1);
}

.el-button:active {
  transform: translateY(5px);
  transition: 200ms;
}


</style>
