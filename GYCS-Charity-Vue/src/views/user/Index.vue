<template>
  <div class="container-profile">
    <div class="profile-up">
      <div class="profile-up-left">
        <div class="bank-and-profile-card">
          <transition name="tab-fade" mode="out-in">
            <div class="tab-content">
              <div>
                <BankCard/>
              </div>
              <div class="info-card" style="margin-left: 30px;padding: 10px 20px;">
                <h2><span>账号地址：</span>0x0ca652921509414a799a070214323dc6044c3351</h2>
                <h2><span>用户名称：</span>张宇豪</h2>
                <h2><span>身份证号：</span>441622200305132857</h2>
                <h2><span>链上余额：</span>10000￥</h2>
                <h2><span>已用余额：</span>500￥</h2>
              </div>
            </div>
          </transition>
        </div>
      </div>
      <div class="profile-up-right">
        <Avatar/>
      </div>
    </div>
    <div class="profile-down">
      <div class="profile-down-left">

      </div>
      <div class="profile-down-right">
        <vs-card>
          <template #text>
            <div style="display: flex;justify-content: space-between;padding: 30px 30px;margin: 20px 20px;">
              <div class="card__profile">
                <p>用户名称：</p>
                <p>手机号码：</p>
                <p>邮箱：</p>
                <p>用户类型：</p>
                <p>性别：</p>
              </div>
              <div class="card__profile">
                <p>张宇豪</p>
                <p>18933200910</p>
                <p>27568647992qq.com</p>
                <p>普通用户</p>
                <p>男</p>
              </div>
            </div>
          </template>
        </vs-card>
      </div>
    </div>
  </div>
</template>
<script setup>


import {reactive, ref} from "vue";
import BankCard from "@/components/BankCard/BankCard.vue";
import Badge from "@/components/Badge/Badge.vue";
import Avatar from "@/components/Avatar/Avatar.vue";
import Tab from "@/components/Tab/Tab.vue";
const activeTab = ref(0);
const tabs = reactive([
  { id: '1', title: '银行卡信息' },
  { id: '2', title: '个人信息' },
])
const selectTab = (index) => {
  activeTab.value = index;
};
</script>


<style scoped>

.container-profile {
  display: flex;
  margin: 10px 20px;
  margin-left: 20%;
  flex-direction: column;
}

.profile-up {
  display: flex;
  justify-content: space-between;
  padding: 10px 20px;
}

.profile-up-left {
  margin-right: 50px;
}

.profile-down {
  display: flex;
  justify-content: space-between;
  padding: 10px 20px;
}




.tabs {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  border-radius: 0.5rem;
  background-color: #EEE;
  box-sizing: border-box;
  box-shadow: 0 0 0px 1px rgba(0, 0, 0, 0.06);
  padding: 0.25rem;
  width: 300px;
  font-size: 14px;
}

.tabs .radio {
  flex: 1 1 auto;
  text-align: center;
}

.tabs .radio input {
  display: none;
}

.tabs .radio .name {
  display: flex;
  cursor: pointer;
  align-items: center;
  justify-content: center;
  border-radius: 0.5rem;
  border: none;
  padding: .5rem 0;
  color: rgba(51, 65, 85, 1);
  transition: all .15s ease-in-out;
}

.tabs .radio input:checked + .name {
  background-color: #fff;
  font-weight: 600;
}

.info-card > h2:not(:last-child){
  white-space: nowrap;
  margin-bottom: 10px;
  width: 100%;
}
.info-card > h2 > span{
  padding-right: 20px;
}

.tab-content {
  padding: 20px;
  border-top: none;
  border-radius: 0 0 10px 10px;
  transition: opacity 0.5s;
  display: flex;
  justify-content: space-between;
  margin: 10px 20px;

}


.bank-and-profile-card {
  box-sizing: border-box;
  width: 900px;
  height: 300px;
  background: rgba(217, 217, 217, 0.58);
  border: 1px solid white;
  box-shadow: 12px 17px 51px rgba(0, 0, 0, 0.22);
  backdrop-filter: blur(6px);
  border-radius: 17px;
  cursor: pointer;
  transition: all 0.5s;
  display: flex;
  align-items: center;
  justify-content: center;
  user-select: none;
  font-weight: bolder;
  color: black;
}
.bank-and-profile-card:hover {
  transform: scale(1.05);
}

.bank-and-profile-card:active {
  transform: scale(0.95) rotateZ(1.7deg);
}




/*修改card的深度属性*/

:deep(.vs-card) {
  width: 350px;
  height: 300px;
}
:deep(.vs-card:hover) {
  box-shadow: 0 5px 10px 0 rgba(0,0,0,var(--vs-shadow-opacity)) !important;
}

.card__profile > p {
  margin-bottom: 10px;
}
</style>
