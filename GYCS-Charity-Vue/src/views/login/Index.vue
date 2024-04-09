<template>
  <div class="container" :class="{ 'sign-up-mode': isSignUpMode }">
    <div class="form-warp">
      <form class="sign-in-form" ref="loginRef">
        <h2 class="form-title">登录</h2>
        <vs-input
            v-model="loginForm.username"
            label="用户名"
            label-float
            icon-after
            @click-icon="hasVisiblePassword = !hasVisiblePassword"
            style="width: 300px"
        >
          <template #icon>
            <i v-if="!hasVisiblePassword" class="bx bx-show-alt" />
            <vs-icon><UserBold /></vs-icon>
          </template>

          <template v-if="getProgress >= 100" #message-success>
            Secure password
          </template>
        </vs-input>
        <vs-input
            v-model="loginForm.password"
            type="password"
            :progress="getProgress"
            label="Password"
            label-float
            icon-after
            style="width: 300px"
            @click-icon="hasVisiblePassword = !hasVisiblePassword"
        >
          <template #icon>
            <i v-if="!hasVisiblePassword" class="bx bx-show-alt" />
            <vs-icon><LockCircleBold /></vs-icon>
          </template>

          <template v-if="getProgress >= 100" #message-success>
            Secure password
          </template>
        </vs-input>
        <div style="display: flex;justify-content: space-between;width: 300px">
          <div>
            <vs-input
                v-model="loginForm.code"
                label="验证码"
                label-float
                icon-after
                style="width: 200px"
                @click-icon="hasVisiblePassword = !hasVisiblePassword"
            >
              <template #icon>
                <i v-if="!hasVisiblePassword" class="bx bx-show-alt" />
                <vs-icon><ShieldSecurityBold /></vs-icon>
              </template>
              <template v-if="getProgress >= 100" #message-success>
                Secure password
              </template>
            </vs-input>
          </div>
          <div>
            <img :src="codeUrl" @click="getCode" class="login-code-img"/>
          </div>
        </div>
        <div class="submit-btn" @click="handleLogin">立即登录</div>
      </form>
      <form class="sign-up-form">
        <h2 class="form-title">注册</h2>
        <input placeholder="用户名" />
        <input type="password" placeholder="密码" />
        <div class="submit-btn">立即注册</div>
      </form>
    </div>
    <div class="desc-warp">
      <div class="desc-warp-item sign-up-desc">
        <div class="content">
          <button id="sign-up-btn" @click="toggleSignUpMode">注册</button>
        </div>
        <img src="../../assets/images/log.svg" alt="">
      </div>
      <div class="desc-warp-item sign-in-desc">
        <div class="content">
          <button id="sign-in-btn" @click="toggleSignInMode">登录</button>
        </div>
        <img src="../../assets/images/register.svg" alt="">
      </div>
    </div>
  </div>
</template>

<script setup>
import {
  LockCircleBold,
  UserBold,
  ShieldSecurityBold,
} from '@vuesax-alpha/icons-vue'
import {computed, ref} from "vue";
import router from "@/router/index.js";
import {getCodeImg} from "@/api/login.js";
import useUserStore from "@/stores/modules/user.js";
import {VsNotification} from "vuesax-alpha";
const userStore = useUserStore()

// 登录表单数据
const loginForm = ref({
  username: "",
  password: "",
  rememberMe: false,
  code: "",
  uuid: ""
});

// 页面控制属性
const hasVisiblePassword = ref(false)
const isSignUpMode = ref(false)
const captchaEnabled = ref(true)
const codeUrl = ref("")

// 校验密码的难度
const getProgress = computed(() => {
  let progress = 0
  // at least one number
  if (/\d/.test(loginForm.value.password)) {
    progress += 20
  }
  // at least one capital letter
  if (/(.*[A-Z].*)/.test(loginForm.value.password)) {
    progress += 20
  }
  // at menons a lowercase
  if (/(.*[a-z].*)/.test(loginForm.value.password)) {
    progress += 20
  }
  // more than 5 digits
  if (loginForm.value.password.length >= 6) {
    progress += 20
  }
  // at least one special character
  if (/[^A-Za-z0-9]/.test(loginForm.value.password)) {
    progress += 20
  }
  return progress
})


const toggleSignUpMode = () => {
  isSignUpMode.value = true
}

const toggleSignInMode = () => {
  isSignUpMode.value = false
}


// 获取验证码
function getCode() {
  getCodeImg().then(res => {
    captchaEnabled.value = res.captchaEnabled === undefined ? true : res.captchaEnabled;
    if (captchaEnabled.value) {
      codeUrl.value = res.img;
      loginForm.value.uuid = res.uuid;
      console.log(res)
    }
  });
}

// 登录接口
function handleLogin() {
  // 调用action的登录方法
  userStore.login(loginForm.value).then(() => {
    router.push({ path: "/home" });
    openNotification("success","系统通知","欢迎回来," + loginForm.value.username + " 登录成功")
  }).catch(() => {
    // 重新获取验证码
    if (captchaEnabled.value) {
      getCode();
    }
  });
}

const openNotification = (color,title,msg) => {
  VsNotification({
    color,
    position: 'top-left',
    title: title,
    content: msg,

  })
}
getCode()
</script>


<style scoped>

.login-code-img {
  margin-top: 27px;
  margin-left: 5px;
  border-radius: 20px;
}

/*
 * Author: Minyoung
 * CreateAt: 2021年10月14日23:44:45
 * License: MIT
 */
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
  color: #333;
}

.container {
  position: relative;
  min-height: 100vh;
  width: 100%;
  overflow: hidden;
}
.container::before {
  content: " ";
  position: absolute;
  width: 2000px;
  height: 2000px;
  border-radius: 50%;
  background-image: linear-gradient(-45deg, #6266f5 0%, #04befe 100%);
  transition: 1.8s ease-in-out;
  z-index: 6;
  top: -10%;
  right: 48%;
  transform: translateY(-50%);
}
.container.sign-up-mode::before {
  transform: translate(100%, -50%);
}

.form-warp {
  width: 50%;
  position: absolute;
  z-index: 5;
  left: 75%;
  top: 50%;
  z-index: 5;
  transform: translate(-50%, -50%);
  display: grid;
  grid-template-columns: 1fr;
  transition: 1s 0.7s ease-in-out;
}
.form-warp form {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  gap: 20px;
  /* 将两个 form 布局在 grid 同一位置 */
  grid-row: 1 / 2;
  grid-column: 1 / 2;
  transition: all 0.2s 0.7s;
  opacity: 1;
  z-index: 4;
}
.form-title {
  color: #6266f5;
}
.form-warp .sign-up-form {
  opacity: 0;
  z-index: 3;
}
.container.sign-up-mode .form-warp {
  left: 25%;
}
.container.sign-up-mode .sign-in-form {
  opacity: 0;
  z-index: 3;
}
.container.sign-up-mode .sign-up-form {
  opacity: 1;
  z-index: 4;
}
input,
.submit-btn {
  min-width: 300px;
  outline: none;
  padding: 12px 30px;
  line-height: 1;
  font-size: 16px;
  border-radius: 60px;
  color: #333;
  background-color: #6267f513;
  border: none;
}
input::placeholder {
  color: #cccc;
}
.submit-btn {
  background-color: #6266f5;
  color: #FFF;
  text-align: center;
  min-width: 150px;
  font-size: initial;
  font-weight: bold;
  letter-spacing: 1.5px;
  cursor: pointer;
}

.desc-warp {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
}
.desc-warp-item {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: space-around;
  text-align: center;
  text-align: center;
  padding: 3rem 17% 2rem 12%;
  z-index: 6;
}
/* 事件穿透 BEGIN */
.sign-in-desc {
  pointer-events: none;
}
.sign-up-mode .sign-in-desc {
  pointer-events: all;
}
.sign-up-mode .sign-up-desc {
  pointer-events: none;
}
/* 事件穿透 END */
.content {
  width: 100%;
  transition: transform 0.9s ease-in-out;
  transition-delay: .6s;
}
.sign-in-desc img,
.sign-in-desc .content {
  transform: translateX(800px);
}
.sign-up-mode .sign-in-desc img,
.sign-up-mode .sign-in-desc .content {
  transform: translateX(0);
}

.sign-up-mode .sign-up-desc img,
.sign-up-mode .sign-up-desc .content {
  transform: translateX(-800px);
}

button {
  outline: none;
  padding: 6px 8px;
  min-width: 100px;
  text-align: center;
  border-radius: 30px;
  border: 2px solid #FFF;
  background: none;
  color: #FFF;
  cursor: pointer;
  transition: all .3s ease;
}
button:active {
  background: rgba(255, 255, 255, .1);
}
img {
  width: 100%;
  display: block;
  transition: transform 0.9s ease-in-out;
  transition-delay: .5s;
}

/* 响应式 */
@media screen and (max-width: 870px) {
  .container::before {
    width: 1500px;
    height: 1500px;
    transform: translateX(-50%);
    left: 30%;
    bottom: 68%;
    right: initial;
    top: initial;
    transition: 2s ease-in-out;
  }
  .container.sign-up-mode::before {
    transform: translate(-50%, 100%);
    bottom: 32%;
    right: initial;
  }
  .form-warp {
    width: 100%;
    top: 75%;
    left: 50%;
    transform: translate(-50%, -100%);
    transition: 1s 0.8s ease-in-out;
  }
  .container.sign-up-mode .form-warp {
    top: 25%;
    left: 50%;
    transform: translate(-50%, 0);
  }
  img {
    width: 200px;
    transition: transform 0.9s ease-in-out;
    transition-delay: 0.7s;
  }
  .desc-warp {
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 2fr 1fr;
  }
  .desc-warp-item {
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    padding: 2.5rem 8%;
    grid-column: 1 / 2;
  }
  .sign-in-desc {
    grid-row: 3 / 4;
  }

  .sign-in-desc img,
  .sign-in-desc .content {
    transform: translateY(800px);
  }

  .sign-up-mode .sign-in-desc img,
  .sign-up-mode .sign-in-desc .content {
    transform: translateY(0);
  }

  .sign-up-mode .sign-up-desc img,
  .sign-up-mode .sign-up-desc .content {
    transform: translateY(-800px);
  }
}

@media screen and (max-width: 570px) {
  .container::before {
    bottom: 72%;
    left: 50%;
  }
  img {
    display: none;
  }
}


</style>
