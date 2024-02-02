<template>
  <div class="app-container">
    <div>
      <el-steps :space="200" :active="stepNumber" simple style="border-radius: 15px">
        <el-step title="添加公益活动文章内容" :icon="Edit" />
        <el-step title="设置公益活动基本功能" :icon="Setting" />
        <el-step title="确认并发布公益活动" :icon="Position" />
      </el-steps>
    </div>

    <div class="form-container">
      <el-card class="step-card" >
        <el-form v-if="stepNumber == 1" size="large" label-position="left">
          <div style="display: flex;justify-content: space-between">
            <!-- 表单内容 -->
            <el-form-item label="活动标题">
              <el-input placeholder="请输入活动标题"></el-input>
            </el-form-item>
            <el-form-item label="发布作者">
              <el-input placeholder="请输入发布作者"></el-input>
            </el-form-item>
          </div>
          <el-form-item label="活动描述">
            <el-input type="textarea" placeholder="请输入当前的公益活动文章或者描述信息" :rows="20"></el-input>
          </el-form-item>
        </el-form>


        <div class="form-button">
          <el-button type="success" @click="stepNumber++">下一步</el-button>
          <el-button type="danger" @click="stepNumber--">上一步</el-button>
          <el-button type="primary" v-if="stepNumber == 3">确认</el-button>
        </div>
      </el-card>


    </div>




  </div>
</template>


<script setup>

import {Edit, Position, Setting} from "@element-plus/icons-vue";

const stepNumber = ref(1)


</script>


<style>
.step-card {
  margin-top: 30px;
  width: 100%;
  border-radius: 15px;
  padding: 20px 20px;


}

.form-container {
  display: flex;
  justify-content: center;

}

.form-button{
  text-align: center;
}
</style>