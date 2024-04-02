<template>
  <div class="card">
    <div class="image">
      <img v-if="imageUrl" src="../../assets/images/img.png" alt="" style="border-radius: 50px;">
      <img v-if="!imageUrl" :src="form.avatar" alt="">
    </div>
    <div class="card-info">
      <span>{{form.userName}}</span>
      <p>这个人还没有描述信息</p>
    </div>
    <a href="#" class="button" @click="active = true">更新</a>
  </div>

  <vs-dialog v-model="active" overlay-blur>
    <template #header>
      <h4 class="not-margin">更新用户的信息</h4>
    </template>
    <div class="con-form">
      <div class="content-image">
        <div class="main" @click="handleAddImg">
          <img v-if="imageUrl" :src="form.avatar" class="content-image-img" />
          <div v-if="!imageUrl" >
            <text class="content-image-title">点击更换头像</text>
          </div>
        </div>
        <input
            id="upload"
            ref="fileInput"
            class="img-input"
            type="file"
            :multiple="true"
            @change="handleUploadFile"
        />
      </div>
      <div style="padding: 20px 20px;margin-right: 20px">
        <vs-input style="width: 400px" v-model="form.userName" color="primary" placeholder="请输入用户名称" disabled>
          <template #icon>
            <vs-icon><User /></vs-icon>
          </template>
        </vs-input>

        <vs-input style="width: 400px" v-model="form.nickName" color="primary" placeholder="请输入用户昵称">
          <template #icon>
            <vs-icon><UserBold /></vs-icon>
          </template>
        </vs-input>

        <vs-input style="width: 400px" v-model="form.cardId" color="primary" placeholder="请输入身份号码">
          <template #icon>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-vcard" viewBox="0 0 16 16">
              <path d="M5 8a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm4-2.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5ZM9 8a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4A.5.5 0 0 1 9 8Zm1 2.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5Z"/>
              <path fill-rule="evenodd" d="M2 2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H2ZM1 4a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H8.96c.026-.163.04-.33.04-.5C9 10.567 7.21 9 5 9c-2.086 0-3.8 1.398-3.984 3.181A1.006 1.006 0 0 1 1 12V4Z"/>
            </svg>
          </template>
        </vs-input>

        <vs-input style="width: 400px" v-model="form.phonenumber" color="primary" placeholder="请输入手机号">
          <template #icon>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-phone" viewBox="0 0 16 16">
              <path d="M11 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h6zM5 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H5z"/>
              <path d="M8 14a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
            </svg>
          </template>
        </vs-input>

        <vs-input style="width: 400px" v-model="form.email" color="primary" placeholder="请输入邮箱">
          <template #icon>
            <vs-icon><Sms /></vs-icon>
          </template>
        </vs-input>

        <vs-select  v-model="form.sex" placeholder="请选择性别" >
          <vs-option v-for="item in sexOptions" :label="item.label" :value="item.value" />
        </vs-select>
      </div>
    </div>

    <template #footer>
      <div class="footer-dialog">
        <vs-button block @click="handleUpdateProfile"> 确定 </vs-button>

        <div class="new">New Here? <a href="#">Create New Account</a></div>
      </div>
    </template>
  </vs-dialog>
</template>

<script setup>
import {inject, onMounted, reactive, ref, toRefs} from "vue";
import {
  User,
  UserBold,
  Sms
} from "@vuesax-alpha/icons-vue"
import {uploadImage} from "@/api/charity/upload.js";
import {updateUserProfileInfo} from "@/api/charity/charityuser.js";
import {VsNotification} from "vuesax-alpha";
const active = ref(false)
const imageUrl = ref('');
const data = reactive({
  form: {}
})
const {form} = toRefs(data)
const updateUserInfo = inject('user')
const sexOptions = reactive([
  {
    label: '男',
    value: 0 || '0',
  },
  {
    label: '女',
    value: 1
  }
])
const handleAddImg = () => {
  const input = document.querySelector('#upload')
  input.click()
}

const handleUploadFile = async (value) => {
  const files = value.target.files
  console.log('debug===>获取上传文件',files)
  const formData = new FormData()
  formData.append('file', files[0])
  //TODO调用后端接口，传入文件参数
  uploadImage(formData).then(res => {
    imageUrl.value = res.imgUrl
    form.value.avatar = imageUrl.value
  })
}

function handleUpdateProfile(){
  updateUserProfileInfo(form.value).then(res => {
    if (res.code == 200) {
      openNotification('success','操作通知','更新用户信息成功')
    }
  })
  active.value = false
}
onMounted(() => {
  form.value = updateUserInfo
})


const openNotification = (color,title,msg) => {
  VsNotification({
    color,
    position: 'top-left',
    title: title,
    content: msg,

  })
}
</script>

<style lang="scss" scoped>
.content-image {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* 设置阴影效果 */
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
  /* 设置边框样式 */
  border: 1px solid #ccc;
  border-radius: 20px;
  width: 300px;
  height: 300px;
  margin-left: 25px;
  margin-top: 35px;
  .content-image-main {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .content-image-img {
    width: 300px;
    height: 300px;
    border-radius: 20px;
  }
  .content-image-title {
    font-size: 25px;
  }
  .img-input {
    opacity: 0;
  }
}




.card {
  width: 350px;
  height: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  gap: 10px;
  /*background-color: rgba(255, 255, 254, 0.8); !* 使用半透明背景颜色 *!*/
  border-radius: 15px;
  position: relative;
  overflow: hidden;
}

.card::before {
  content: "";
  width: 350px;
  height: 100px;
  position: absolute;
  top: 0;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  border-bottom: 3px solid #fefefe;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 100%);
  transition: all 0.5s ease;
}

.card * {
  z-index: 1;
}

.image {
  width: 85px;
  height: 85px;
  border-radius: 50%;
  border: 4px solid #fefefe;
  margin-top: 30px;
  transition: all 0.5s ease;
}

.card-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
  transition: all 0.5s ease;
}

.card-info span {
  font-weight: 600;
  font-size: 24px;
  color: #161A42;
  margin-top: 15px;
  line-height: 5px;
}

.card-info p {
  color: rgba(0, 0, 0, 0.5);
}

.button {
  text-decoration: none;
  background-color: #1468BF;
  color: white;
  padding: 5px 20px;
  border-radius: 5px;
  border: 1px solid white;
  transition: all 0.5s ease;
}

.card:hover::before {
  width: 350px;
  height: 300px;
  border-bottom: none;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  transform: scale(0.95);
}

.card:hover .card-info {
  transform: translate(0%,-25%);
}

.card:hover .image {
  transform: scale(2) translate(-60%,-40%);
}

.button:hover {
  background-color: #FF6844;
  transform: scale(1.1);
}


@function -color($color, $alpha: 1) {
  @return unquote('rgba(var(--vs-#{$color}), #{$alpha})');
}
.not-margin {
  margin: 0px;
  font-weight: normal;
  padding: 10px;
}
.con-form {
  padding: 20px 10px;
  display: flex;
  justify-content: space-between;
  width: 800px;
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
  .vs-checkbox__label {
    font-size: 0.8rem;
  }
  .vs-input {
    margin: 10px 0px;
    width: calc(100%);
    .vs-input__original {
      width: 100%;
    }
  }
}
.footer-dialog {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: calc(100%);
  .new {
    margin: 0px;
    margin-top: 20px;
    padding: 0px;
    font-size: 0.7rem;
    a {
      color: -color('primary') !important;
      margin-left: 6px;
      &:hover {
        text-decoration: underline;
      }
    }
  }
  .vs-button {
    margin: 0px;
  }
}
</style>
