<script setup>
import {toRefs} from "vue";
import {uploadImage} from "@/api/charity/upload.js";
import {ElMessage} from "element-plus";
import {getUserAddress} from "@/api/charity/charityuser.js";
import {initRaiseFund, uploadCertificate} from "@/api/charity/raiseFund.js";

const rules = reactive({
  title: [
    { required: true, message: '请输入发布的公益募资标题', trigger: 'blur' },
  ],
  promoterAddress: [
    {
      required: true,
      message: '请输入发布的账户',
      trigger: 'blur',
    },
  ],
  totalAmount: [
    {
      required: true,
      message: '请输入募资金额',
      trigger: 'blur',   
    },
  ],
  startTime: [
    {
      type: 'date',
      required: true,
      message: '请选择开始时间',
      trigger: 'change',
    },
  ],
  endTime: [
    {
      type: 'date',
      required: true,
      message: '请选择结束时间',
      trigger: 'change',
    },
  ],
  type: [
    {
      type: 'array',
      required: true,
      message: 'Please select at least one activity type',
      trigger: 'change',
    },
  ],
  resource: [
    {
      required: true,
      message: 'Please select activity resource',
      trigger: 'change',
    },
  ],
  description: [
    { required: true, message: '请输入公益描述信息', trigger: 'blur' },
  ],
  cardId: [
    { required: true, message: '请输入身份证号', trigger: 'blur' },
  ],
  name: [
    { required: true, message: '请输入真实姓名', trigger: 'blur' },
  ],
  phone: [
    { required: true, message: '请输入手机号码', trigger: 'blur' },
  ],
  address: [
    { required: true, message: '请输入家庭地址详细信息', trigger: 'blur' },
  ],
})
const ruleFormRef = ref()
const active = ref(1)
const raiseImage = ref("")
const orgImage = ref("")
const data = reactive({
  form: {}
})

const {form} = toRefs(data)
const submitForm = async (ruleFormRef) => {
  if (!ruleFormRef) return
  await ruleFormRef.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
      console.log(form.value)
      active.value++
    } else {
      console.log('error submit!', fields)
    }
  })
}

const resetForm = (formEl) => {
  if (!formEl) return
  formEl.resetFields()
}


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
function handleRaiseImageUpload(file) {
  uploadImage(file).then(res => {
    if (res.code == 200){
      raiseImage.value = res.imgUrl
      form.value.raiseImage = raiseImage.value
      ElMessage({
        message: '上传图片成功',
        type: 'success',
      })
    }
  }).catch(error => {
    ElMessage.error('上传图片失败')
  })
}


function handleOrgImageUpload(file) {
  uploadImage(file).then(res => {
    if (res.code == 200){
      orgImage.value = res.imgUrl
      form.value.orgImage = orgImage.value
      ElMessage({
        message: '上传图片成功',
        type: 'success',
      })
    }
  }).catch(error => {
    ElMessage.error('上传图片失败')
  })
}

async function handleInitRaiseFund(){
  await initRaiseFund(form.value).then(res => {
    if (res.code == 200) {
      // 根据当前的公益信息查询一下
      ElMessage.success(res.msg)   
      form.value.raiseId = res.id
      uploadCertificate(form.value).then(response => {
        active.value++
        form.value = {}
      })
    }
  })
}

onMounted(()=> {
  getUserAddress().then(res => {
    form.value.promoterAddress = res.userAddress
  })
})
</script>

<template>
  <div>
    <div style="margin-bottom: 20px">
      <el-steps style="border-radius: 15px" :active="active" finish-status="success" simple>
        <el-step title="发布公益募资" />
        <el-step title="上传证明信息" />
        <el-step title="等待审核投票" />
      </el-steps>
    </div>
    <div>
      <el-form
          v-if="active == 1"
          ref="ruleFormRef"
          style="width: auto;padding: 20px 50px"
          :model="form"
          :rules="rules"
          label-width="auto"
          class="demo-ruleForm"
          size="large"
          status-icon
      >
        <div style="display: flex;justify-content: space-between">
          <div>
            <el-form-item label="发布标题" prop="title" >
              <el-input v-model="form.title" placeholder="请输入发布标题" style="width: 400px"/>
            </el-form-item>
          </div>
          <div>
            <el-form-item label="募资金额" prop="totalAmount" >
              <el-input v-model="form.totalAmount" placeholder="请输入募资金额" type="number" style="width: 400px"/>
            </el-form-item>
          </div>
        </div>
        <el-form-item label="发布账户" prop="promoterAddress">
          <el-input v-model="form.promoterAddress" placeholder="请输入发布账户" disabled/>
        </el-form-item>
        <el-form-item label="活跃时间" required>
          <el-col :span="11">
            <el-form-item prop="startTime">
              <el-date-picker
                  v-model="form.startTime"
                  type="datetime"
                  label="开始时间"
                  placeholder="请选择开始时间"
                  style="width: 100%"
                  value-format="YYYY-MM-DD h:m:s"
              />
            </el-form-item>
          </el-col>
          <el-col class="text-center" :span="2">
            <span class="text-gray-500">-</span>
          </el-col>
          <el-col :span="11">
            <el-form-item prop="endTime">
              <el-date-picker
                  v-model="form.endTime"
                  type="datetime"  
                  label="结束时间"
                  placeholder="请选择结束时间"
                  style="width: 100%"
                  value-format="YYYY-MM-DD h:m:s"
              />
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="公益描述" prop="description">
          <el-input v-model="form.description" type="textarea" rows="8" placeholder="请输入公益描述信息"/>
        </el-form-item>
      </el-form>
      <el-form
          v-if="active == 2"
          ref="ruleFormRefss"
          style="width: auto;padding: 20px 50px"
          :model="form"
          :rules="rules"
          label-width="auto"
          class="demo-ruleForm"
          size="large"
          status-icon
      >
        <div style="display: flex;justify-content: space-between">
          <div>
            <el-form-item label="身份证号" prop="cardId">
              <el-input v-model="form.cardId"  style="width: 400px" placeholder="请输入身份证号码"></el-input>
            </el-form-item>
          </div>
          <div>
            <el-form-item label="真是姓名" prop="name">
              <el-input  v-model="form.name" style="width: 400px" placeholder="请输入姓名"></el-input>
            </el-form-item>
          </div>
          <div>
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone"   style="width: 400px" placeholder="请输入手机号码"></el-input>
            </el-form-item>
          </div>
        </div>
        <el-form-item label="家庭住址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细的家庭地址"></el-input>
        </el-form-item>
        <div style="display: flex;justify-content: space-between;padding: 20px 0;align-content: center">
          <div>
            <el-form-item label="募资证明">
              <el-upload
                  style="width: 400px;"
                  class="upload-demo"
                  drag
                  action="#"
                  :http-request="handleRaiseImageUpload"
                  :before-upload="beforeAvatarUpload"
                  multiple
                  :show-file-list="false"
              >
                <img v-if="raiseImage" :src="raiseImage" style="width: 300px;height: 200px"/>
                <el-icon v-else class="el-icon--upload"><upload-filled /></el-icon>
                <template #tip>
                  <div style="text-align: center;">
                    上传的 jpg/png 图片最好小于500KB
                  </div>
                </template>
              </el-upload>
            </el-form-item>
          </div>
          <div>
            <el-form-item label="三方证明">
              <el-upload
                  style="width: 400px;"
                  class="upload-demo"
                  drag
                  action="#"
                  :http-request="handleOrgImageUpload"
                  :before-upload="beforeAvatarUpload"
                  multiple
                  :show-file-list="false"
              >
                <img v-if="orgImage" :src="orgImage" style="width: 300px;height: 200px"/>
                <el-icon v-else class="el-icon--upload"><upload-filled /></el-icon>
                <template #tip>
                  <div style="text-align: center;">
                    上传的 jpg/png 图片最好小于500KB
                  </div>
                </template>
              </el-upload>
            </el-form-item>
          </div>
        </div>
      </el-form>
      <div v-if="active == 3">
        <el-result
            icon="success"
            title="发布公益募资活动成功"
            sub-title="请等待审核包括等待投票通知"
        >
          <template #extra>
            <el-button type="danger" @click="active = 1">关闭</el-button>
          </template>
        </el-result>

      </div>
      <div style="display: flex;justify-content: center">
        <el-button type="primary" @click="submitForm(ruleFormRef)" v-if="active != 3">
          下一步
        </el-button>
        <el-button type="success" @click="resetForm(ruleFormRef)" v-if="active == 1">重置</el-button>
        <el-button type="warning" @click="active--" v-if="active == 2">上一步</el-button>
        <el-button type="success" @click="handleInitRaiseFund" v-if="active == 2">发布</el-button>
      </div>
    </div>
  </div>

</template>

<style scoped lang="scss">

</style>