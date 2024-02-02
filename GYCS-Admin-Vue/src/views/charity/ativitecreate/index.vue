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
            <el-form-item label="发布作者" >
              <el-input placeholder="请输入发布作者"></el-input>
            </el-form-item>
          </div>
          <el-form-item label="活动描述">
            <el-input type="textarea" placeholder="请输入当前的公益活动文章或者描述信息" :rows="20"></el-input>
          </el-form-item>
        </el-form>

        <el-form v-if="stepNumber == 2" size="large" label-position="left">
          <div style="display: flex;justify-content: space-between">
            <el-form-item label="开始时间">
              <el-date-picker
                  v-model="startTime"
                  type="datetime"
                  placeholder="请选择开始的时间"
                  style="width: 240px"
              />
            </el-form-item>
            <el-form-item label="结束时间" >
              <el-date-picker
                  v-model="endTime"
                  type="datetime"
                  placeholder="请选择结束的时间"
                  style="width: 240px"
              />
            </el-form-item>
          </div>
          <div style="display: flex;justify-content: space-between;">
            <el-form-item label="物流方式">
              <el-select
                  v-model="value"
                  class="m-2"
                  placeholder="请选择物流的运输方式"
                  size="large"
                  style="width: 240px"
              >
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="物流商">
              <el-select
                  v-model="value"
                  class="m-2"
                  placeholder="请选择提供的物流商公司"
                  size="large"
                  style="width: 240px"
              >
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
            </el-form-item>
          </div>

          <el-form-item label="代理机构">
            <el-input placeholder="请输入代理的机构地址信息"></el-input>
          </el-form-item>

          <el-form-item label="活动图片">
            <el-upload
                style="width: 400px"
                class="upload-demo"
                drag
                action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                multiple
            >
              <el-icon class="el-icon--upload"><upload-filled /></el-icon>
              <div class="el-upload__text">
                Drop file here or <em>click to upload</em>
              </div>
              <template #tip>
                <div style="text-align: center;">
                  jpg/png files with a size less than 500kb
                </div>
              </template>
            </el-upload>
          </el-form-item>
        </el-form>

        <el-descriptions
            class="margin-top"
            title="⭐请确认当前的公益活动信息"
            :column="2"
            size="large"
            border
            v-if="stepNumber == 3"
        >
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <document />
                </el-icon>
                活动标题
              </div>
            </template>
            kooriookami
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <user />
                </el-icon>
                发布作者
              </div>
            </template>
            18100000000
          </el-descriptions-item>
          <el-descriptions-item :span="2">
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <collection-tag />
                </el-icon>
                文章信息
              </div>
            </template>
            Suzhou
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <alarm-clock />
                </el-icon>
                开始时间
              </div>
            </template>
            <el-tag size="small">School</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <alarm-clock />
                </el-icon>
                结束时间
              </div>
            </template>
            No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <van />
                </el-icon>
                物流方式
              </div>
            </template>
            No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <coordinate />
                </el-icon>
                物流商地址
              </div>
            </template>
            No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province
          </el-descriptions-item>
          <el-descriptions-item :span="2">
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <place />
                </el-icon>
                代理机构地址
              </div>
            </template>
            No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon class="iconStyle">
                  <Picture />
                </el-icon>
                活动图片
              </div>
            </template>
            <img src="@/assets/images/img.png" width="400" height="300"  style="border-radius: 10px">
          </el-descriptions-item>
        </el-descriptions>


        <div class="form-button">
          <el-button type="success" v-if="stepNumber != 3" @click="stepNumber++">下一步</el-button>
          <el-button type="danger"  v-if="stepNumber != 1" @click="stepNumber--">上一步</el-button>
          <el-button type="primary" v-if="stepNumber == 3">确认</el-button>
        </div>
      </el-card>


    </div>




  </div>
</template>


<script setup>

import {
  AlarmClock,
  CollectionTag,
  Coordinate,
  Document,
  Edit, Picture, Place,
  Position,
  Setting,
  User,
  Van
} from "@element-plus/icons-vue";

const stepNumber = ref(1)

const value = ref()
const options = [
  {
    value: 'Option1',
    label: 'Option1',
  },
  {
    value: 'Option2',
    label: 'Option2',
  },
  {
    value: 'Option3',
    label: 'Option3',
  },
  {
    value: 'Option4',
    label: 'Option4',
  },
  {
    value: 'Option5',
    label: 'Option5',
  },
]


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
  margin-top: 15px;
  text-align: center;
}

.iconStyle {
  width: 1em;width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>