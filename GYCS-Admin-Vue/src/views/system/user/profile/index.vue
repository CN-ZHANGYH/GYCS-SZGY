<template>
   <div class="app-container">
      <el-row :gutter="20">
         <el-col :span="6" :xs="24">
           <div class="e-card playing">

             <div class="image"></div>

             <div class="wave"></div>
             <div class="wave"></div>
             <div class="wave"></div>

             <div class="text-center" style="margin-top: 5px">
               <userAvatar />
             </div>

             <div class="infotop">
               <ul class="list-group list-group-striped">
                 <li class="list-group-item">
                   <div style="display: flex;justify-content: space-between">
                     <div style="margin-left: 40px"><svg-icon icon-class="user" />用户名称</div>
                     <div class="pull-right">{{ state.user.userName }}</div>
                   </div>
                 </li>
                 <li class="list-group-item">
                   <div style="display: flex;justify-content: space-between">
                     <div style="margin-left: 40px"><svg-icon icon-class="phone" />手机号码</div>
                     <div class="pull-right">{{ state.user.phonenumber }}</div>
                   </div>
                 </li>
                 <li class="list-group-item">
                   <div style="display: flex;justify-content: space-between">
                     <div style="margin-left: 40px"><svg-icon icon-class="email" />用户邮箱</div>
                     <div class="pull-right">{{ state.user.email }}</div>
                   </div>
                 </li>
                 <li class="list-group-item">
                   <div style="display: flex;justify-content: space-between">
                     <div style="margin-left: 40px"><svg-icon icon-class="tree" />所属部门</div>
                     <div class="pull-right" v-if="state.user.dept">{{ state.user.dept.deptName }} / {{ state.postGroup }}</div>
                   </div>
                 </li>
                 <li class="list-group-item">
                   <div style="display: flex;justify-content: space-between">
                     <div style="margin-left: 40px"><svg-icon icon-class="peoples" />所属角色</div>
                     <div class="pull-right">{{ state.roleGroup }}</div>
                   </div>
                 </li>
                 <li class="list-group-item">
                   <div style="display: flex;justify-content: space-between">
                     <div style="margin-left: 40px"><svg-icon icon-class="date" />创建日期</div>
                     <div class="pull-right">{{ state.user.createTime }}</div>
                   </div>
                 </li>
               </ul>
             </div>
           </div>
         </el-col>
         <el-col :span="18" :xs="24">
            <el-card>
               <template v-slot:header>
                 <div class="clearfix">
                   <span>基本资料</span>
                 </div>
               </template>
               <el-tabs v-model="activeTab" >
                  <el-tab-pane label="基本资料" name="userinfo">
                     <userInfo :user="state.user" />
                  </el-tab-pane>
                  <el-tab-pane label="修改密码" name="resetPwd">
                     <resetPwd />
                  </el-tab-pane>
               </el-tabs>
            </el-card>
         </el-col>
      </el-row>
   </div>
</template>

<script setup name="Profile">
import userAvatar from "./userAvatar";
import userInfo from "./userInfo";
import resetPwd from "./resetPwd";
import { getUserProfile } from "@/api/system/user";
const activeTab = ref("userinfo");
const state = reactive({
  user: {},
  roleGroup: {},
  postGroup: {}
});

function getUser() {
  getUserProfile().then(response => {
    state.user = response.data;
    state.roleGroup = response.roleGroup;
    state.postGroup = response.postGroup;
  });
};

getUser();
</script>


<style scoped>
.e-card {

  background: transparent;
  box-shadow: 0px 8px 28px -9px rgba(0,0,0,0.45);
  position: relative;
  width: auto;
  height: 383px;
  border-radius: 16px;
  overflow: hidden;
}

.wave {
  position: absolute;
  width: 540px;
  height: 700px;
  opacity: 0.6;
  left: 0;
  top: 0;
  margin-left: -50%;
  margin-top: -70%;
  background: linear-gradient(744deg,#af40ff,#5b42f3 60%,#00ddeb);
}

.infotop {
  text-align: center;
  font-size: 20px;
  position: absolute;
  top: 5.6em;
  left: 0;
  right: 0;
  color: rgb(255, 255, 255);
  font-weight: 600;
}


.wave:nth-child(2),
.wave:nth-child(3) {
  top: 210px;
}

.playing .wave {
  border-radius: 40%;
  animation: wave 3000ms infinite linear;
}

.wave {
  border-radius: 40%;
  animation: wave 55s infinite linear;
}

.playing .wave:nth-child(2) {
  animation-duration: 4000ms;
}

.wave:nth-child(2) {
  animation-duration: 50s;
}

.playing .wave:nth-child(3) {
  animation-duration: 5000ms;
}

.wave:nth-child(3) {
  animation-duration: 45s;
}

@keyframes wave {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}


:deep(.list-group-striped  .list-group-item) {
  border-left: 0;
  border-right: 0;
  border-radius: 0;
  padding-left: 0;
  padding-right: 35px;
  border-bottom: none;
  border-top: none;
}

:deep(.el-card) {
  border-radius: 15px;
  border: none;
}
</style>
