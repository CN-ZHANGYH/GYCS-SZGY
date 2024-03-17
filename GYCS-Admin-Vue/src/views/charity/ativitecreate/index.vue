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
        <el-form v-if="stepNumber == 1" size="large" label-position="left" :model="form" :rules="rules" ref="activiteRef" >
          <div style="display: flex;justify-content: space-between">
            <!-- 表单内容 -->
            <el-form-item label="活动标题">
              <el-input placeholder="请输入活动标题" v-model="form.title"></el-input>
            </el-form-item>
            <el-form-item label="发布作者" >
              <el-input placeholder="请输入发布作者" v-model="form.author"></el-input>
            </el-form-item>
          </div>
          <el-form-item label="活动内容">
            <el-input type="textarea" placeholder="请输入当前的公益活动文章或者描述信息" :rows="20" v-model="form.content"></el-input>
          </el-form-item>
        </el-form>

        <el-form v-if="stepNumber == 2" size="large" label-position="left">
          <div style="display: flex;justify-content: space-between">
            <el-form-item label="开始时间">
              <el-date-picker
                  v-model="form.startTime"
                  type="datetime"
                  placeholder="请选择开始的时间"
                  style="width: 240px"
                  format="YYYY-MM-DD HH:mm:ss"
                  value-format="YYYY-MM-DD h:m:s"
              />
            </el-form-item>
            <el-form-item label="结束时间">
              <el-date-picker
                  v-model="form.endTime"
                  type="datetime"
                  placeholder="请选择结束的时间"
                  style="width: 240px"
                  format="YYYY-MM-DD HH:mm:ss"
                  value-format="YYYY-MM-DD h:m:s"
              />
            </el-form-item>
          </div>
          <div style="display: flex;justify-content: space-between;">
            <el-form-item label="物流方式">
              <el-select
                  v-model="form.logisticType"
                  class="m-2"
                  placeholder="请选择物流的运输方式"
                  size="large"
                  style="width: 240px"
              >
                <el-option
                    v-for="item in transportOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="物流商">
              <el-select
                  v-model="form.logisticAddress"
                  class="m-2"
                  placeholder="请选择提供的物流商公司"
                  size="large"
                  style="width: 240px"
              >
                <el-option
                    v-for="item in logisticsOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
            </el-form-item>
          </div>

          <el-form-item label="代理机构">
            <el-input placeholder="请输入代理的机构地址信息" v-model="form.lncomeAddress" disabled></el-input>
          </el-form-item>

          <el-form-item label="活动图片">
            <el-upload
                style="width: 300px;"
                class="upload-demo"
                drag
                action="#"
                :http-request="upload"
                :before-upload="beforeAvatarUpload"
                multiple
                :show-file-list="false"
            >
              <img v-if="imageUrl" :src="imageUrl" style="width: 300px;height: 200px"/>
              <el-icon v-else class="el-icon--upload"><upload-filled /></el-icon>
              <template #tip>
                <div style="text-align: center;">
                  上传的 jpg/png 图片最好小于500KB
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
            {{form.title}}
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
            {{form.author}}
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
            {{form.content}}
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
            <el-tag size="small">{{form.startTime}}</el-tag>
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
            {{form.endTime}}
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
            {{form.logisticType}}
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
            {{form.logisticAddress}}
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
            {{form.lncomeAddress}}
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
            <img :src="form.img" width="400" height="300"  style="border-radius: 10px">
          </el-descriptions-item>
        </el-descriptions>


        <div class="form-button">
          <el-button type="success" v-if="stepNumber != 3" @click="stepNumber++">下一步</el-button>
          <el-button type="danger"  v-if="stepNumber != 1" @click="stepNumber--">上一步</el-button>
          <el-button type="primary" v-if="stepNumber == 3" @click="InitActiviteHandle">确认</el-button>
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
import {getAllLogisticAddress} from "@/api/charity/logistic.js";
import {onMounted} from "vue";
import {getOrgAddress} from "@/api/charity/org.js";
import {uploadImage} from "@/api/charity/upload.js";
import {initActivite} from "@/api/charity/activite.js";
import {ElMessage} from "element-plus";
const activiteRef = ref("activiteRef")
const stepNumber = ref(1)
const value = ref()
const data = reactive({
  form: {},
  logisticsOptions: [],
  rules: {

  }
});
const imageUrl = ref("")
const localAddress = ref("")
const size = ref('')
const { form, rules,logisticsOptions } = toRefs(data);

const transportOptions = [
  {
    value: '航空运输',
    label: '航空运输（邮寄）',
  },
  {
    value: '海上运输',
    label: '海上运输（邮寄）',
  },
  {
    value: '陆地运输',
    label: '陆地运输（邮寄）',
  }
]

// 上传图片的校验
function beforeAvatarUpload(file) {
  // 允许的图片格式
  const allowedFormats = ['jpeg', 'jpg', 'png', 'gif'];

  const isLt2M = file.size / 1024 / 1024 < 2;
  const fileExtension = file.name.split('.').pop().toLowerCase();

  if (!isLt2M) {
    proxy.$modal.msgError('上传头像图片大小不能超过 2MB!');
  }

  if (!allowedFormats.includes(fileExtension)) {
    proxy.$modal.msgError('上传头像图片格式不支持!');
  }
}

//使用element plus上传的话类型应该是UploadProps，取文件得用file.raw
function upload(file) {
  uploadImage(file).then(res => {
    if (res.code == 200){
      imageUrl.value = res.imgUrl
      form.value.img = imageUrl.value
      ElMessage({
        message: '上传图片成功',
        type: 'success',
      })
    }
  }).catch(error => {
    ElMessage.error('上传图片失败')
  })
}

onMounted(() => {
  // 获取所有的快递公司选项
  getAllLogisticAddress().then(res => {
    if (res.code == 200) {
      logisticsOptions.value =  res.data
    }
  })
  getOrgAddress().then(res => {
    if (res.code == 200) {
      console.log(res)
      localAddress.value = res.localAddress;
      form.value.lncomeAddress = localAddress.value
    }
  })
})


function  InitActiviteHandle() {
  initActivite(form.value).then(res => {
    if (res.code == 200) {
      stepNumber.value = 1
      form.value = {}
      ElMessage({
        message: '发布公益活动成功',
        type: 'success',
      })
    }else {
      ElMessage.error('发布公益活动失败')
    }
  })
}

</script>


<style>
.step-card {
  margin-top: 10px;
  width: 100%;
  height: 100%;
  border-radius: 15px;
  padding: 10px 100px;


}

.form-container {
  display: flex;
  justify-content: center;
  height: auto;

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
