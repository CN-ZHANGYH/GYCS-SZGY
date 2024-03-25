<template>

</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
const data = ref([])
const props = defineProps(['raiseFundDataTotal'])

onMounted(() => {
  data.value = props.raiseFundDataTotal
})
</script>

<style scoped>

</style>
