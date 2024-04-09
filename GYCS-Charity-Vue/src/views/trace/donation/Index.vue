<script setup>
import {onMounted, ref} from "vue";
import { useRouter} from "vue-router";
import {selectRaiseFundDetailByRaiseId} from "@/api/charity/trace.js";
import {getTransactionInfo} from "@/api/blockchain/blockchain.js";
const route = useRouter()
const raiseId = ref(0)
const steps = ref([
  {
    title: '支付记录',
    description: '✔支付记录信息'
  },
  {
    title: '交易信息',
    description: '✔交易上链回执'
  },
  {
    title: '公益贺卡',
    description: '✔回馈公益贺卡'
  },
]);

const stepActive = ref(1)
const selectedIndex = ref(null);
const donationInfo = ref({})
const transactionReceipt = ref({})
const selectStep = (index) => {
  selectedIndex.value = index;
};

function handleNext(){
  stepActive.value++
  if (stepActive.value > 3) {
    stepActive.value = 1
  }
}

onMounted(() => {
  raiseId.value = route.currentRoute.value.query.donationId
  selectRaiseFundDetailByRaiseId({raiseId: raiseId.value}).then(res => {
    donationInfo.value = res.data

    getTransactionInfo({hash: donationInfo.value.transactionHash}).then(res => {
      transactionReceipt.value = res.transactionReceipt
    })
  })
})
</script>

<template>
<div style="display: flex;justify-content: center;padding: 20px 20px">
  <div style="flex-direction: column">
    <div>
      <vs-alert color="success">
        参与一次公益活动即可免费获得一张公益数字纪念卡
      </vs-alert>
    </div>
    <div style="width: 900px;display:flex;flex-direction: column;align-items: center;margin-top: 10px">
      <div class="timeline">
        <div class="timeline-line"></div>
        <div
            v-for="(step, index) in steps"
            :key="index"
            class="timeline-item"
            :class="{ 'active': selectedIndex === index }"
            @click="selectStep(index)"
        >
          <div class="timeline-dot" :class="{ 'active': selectedIndex === index }"></div>
          <div class="timeline-content">
            <h3 class="timeline-title">{{ step.title }}</h3>
            <p class="timeline-description">{{ step.description }}</p>
          </div>
        </div>
      </div>
      <div class="cookie-card">
        <div v-if="stepActive == 1">
          <div style="padding: 20px 20px">
            <div class="header">
              <span class="trace__title">支付记录详细信息</span>
              <hr class="separator">
            </div>
            <div class="content">
              <div class="info">
                <p><strong>支付方式：</strong><span class="p__span">{{ donationInfo.transType }}</span></p>
                <p><strong>捐款金额：</strong><span class="p__span">{{ donationInfo.amount }}</span></p>
                <p><strong>支付状态：</strong><span class="p__span">{{ donationInfo.isDonation ? '是' : '否'}}</span></p>
                <p><strong>捐款来源：</strong><span class="p__span">{{ donationInfo.source }}</span></p>
                <p><strong>描述信息：</strong><span class="p__span">{{ donationInfo.description }}</span></p>
                <div style="display: flex;flex: 1;align-items: center">
                  <p style="margin-bottom: 10px;font-size: 16px;font-weight: bold;"><strong>捐款账户：</strong></p>
                  <p><vs-button color="primary" type="border">{{ donationInfo.donorAddress }}</vs-button></p>
                </div>
                <div style="display: flex;flex: 1;align-items: center">
                  <p style="margin-bottom: 10px;font-size: 16px;font-weight: bold;"><strong>受助账户：</strong></p>
                  <p><vs-button color="primary" type="border">{{ donationInfo.destAddress }}</vs-button></p>
                </div>
                <p><strong>捐款时间：</strong><span class="p__span">{{ donationInfo.transTime }}</span></p>
                <p><strong>交易哈希：</strong><span class="p__span">{{ donationInfo.transactionHash }}</span></p>
              </div>
            </div>
          </div>
        </div>
        <div v-if="stepActive == 2">
          <div style="padding: 20px 20px">
            <h2 class="expand__p">区块哈希: <span class="expand__span">{{transactionReceipt.blockHash}}</span></h2>
            <h2 class="expand__p">Gas消耗: <span class="expand__span">{{transactionReceipt.gasUsed}}</span></h2>
            <h2 class="expand__p">区块高度: <span class="expand__span">{{transactionReceipt.blockNumber}}</span></h2>
            <h2 class="expand__p">合约地址: <span class="expand__span">{{transactionReceipt.contractAddress}}</span></h2>
            <h2 class="expand__p">发起地址: <span class="expand__span">{{transactionReceipt.from}}</span></h2>
            <h2 class="expand__p">交易索引: <span class="expand__span">{{transactionReceipt.transactionIndex}}</span></h2>
            <h2 class="expand__p">调用地址: <span class="expand__span">{{transactionReceipt.to}}</span></h2>
            <h2 class="expand__p">交易日志: <span class="expand__span">{{transactionReceipt.logsBloom}}</span></h2>
            <h2 class="expand__p">交易哈希: <span class="expand__span">{{transactionReceipt.transactionHash}}</span></h2>
            <h2 class="expand__p">交易状态: <span class="expand__span">{{transactionReceipt.status}}</span></h2>
            <h2 class="expand__p">事件触发: <span class="expand__span">{{transactionReceipt.logs}}</span></h2>
          </div>
        </div>
        <div style="display: flex;justify-content: center" v-if="stepActive == 3">
          <a href="https://www.mythrillfiction.com/the-dark-rider" alt="Mythrill" target="_blank">
            <div class="card">
              <div class="wrapper">
                <img src="https://ggayane.github.io/css-experiments/cards/dark_rider-cover.jpg" class="cover-image" />
              </div>
              <img src="https://ggayane.github.io/css-experiments/cards/dark_rider-title.png" class="title" />
              <img src="https://ggayane.github.io/css-experiments/cards/dark_rider-character.webp" class="character" />
            </div>
          </a>

        </div>
        <div class="actions">
          <button class="accept" @click="handleNext">
            下一页
          </button>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<style scoped>
.cookie-card {
  width: 100%;
  padding: 1rem;
  margin-top: 10px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 3px 7px 7px rgba(0, 0, 0, .09);
}

.title {
  font-weight: 600;
  color: rgb(31 41 55);
}

.description a {
  --tw-text-opacity: 1;
  color: rgb(59 130 246);
}

.description a:hover {
  -webkit-text-decoration-line: underline;
  text-decoration-line: underline;
}

.actions {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 1rem;
  -moz-column-gap: 1rem;
  column-gap: 1rem;
  flex-shrink: 0;
}

.accept {
  font-size: 0.75rem;
  line-height: 1rem;
  background-color: rgb(17 24 39);
  font-weight: 500;
  border-radius: 0.5rem;
  color: #fff;
  padding-left: 1rem;
  padding-right: 1rem;
  padding-top: 0.625rem;
  padding-bottom: 0.625rem;
  margin-left: 90%;
  border: none;
  transition: all .15s cubic-bezier(0.4, 0, 0.2, 1);
}

.accept:hover {
  background-color: rgb(55 65 81);
}

.accept:focus {
  outline: 2px solid transparent;
  outline-offset: 2px;
}




.timeline {
  position: relative;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}

.timeline-item {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 20px;
  cursor: pointer;
  transition: transform 0.3s ease;
}

.timeline-item.active {
  color: #007bff;
}

.timeline-dot {
  width: 20px;
  height: 20px;
  background-color: #007bff;
  border-radius: 50%;
}

.timeline-dot.active {
  background-color: #28a745; /* 点击后的颜色 */
}

.timeline-content {
  margin-top: 10px;
  text-align: center;
}

.timeline-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
}

.timeline-description {
  font-size: 16px;
  margin-bottom: 10px;
}

.timeline-details {
  font-size: 14px;
}

.timeline-line {
  position: absolute;
  left: 15%;
  height: 2px;
  background-color: #007bff;
  width: calc(100% - 30%);
  transform: translateY(-40px);
}


.card {
  width: 200px;
  height: 300px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: flex-end;
  padding: 0 36px;
  perspective: 2500px;
  margin: 0 50px;
}

.cover-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.wrapper {
  transition: all 0.5s;
  position: absolute;
  width: 100%;
  z-index: -1;
}

.card:hover .wrapper {
  transform: perspective(900px) translateY(-5%) rotateX(25deg) translateZ(0);
  box-shadow: 2px 35px 32px -8px rgba(0, 0, 0, 0.75);
  -webkit-box-shadow: 2px 35px 32px -8px rgba(0, 0, 0, 0.75);
  -moz-box-shadow: 2px 35px 32px -8px rgba(0, 0, 0, 0.75);
}

.wrapper::before,
.wrapper::after {
  content: "";
  opacity: 0;
  width: 100%;
  height: 80px;
  transition: all 0.5s;
  position: absolute;
  left: 0;
}
.wrapper::before {
  top: 0;
  height: 100%;
  background-image: linear-gradient(
      to top,
      transparent 46%,
      rgba(12, 13, 19, 0.5) 68%,
      rgba(12, 13, 19) 97%
  );
}
.wrapper::after {
  bottom: 0;
  opacity: 1;
  background-image: linear-gradient(
      to bottom,
      transparent 46%,
      rgba(12, 13, 19, 0.5) 68%,
      rgba(12, 13, 19) 97%
  );
}

.card:hover .wrapper::before,
.wrapper::after {
  opacity: 1;
}

.card:hover .wrapper::after {
  height: 120px;
}
.title {
  width: 100%;
  transition: transform 0.5s;
}
.card:hover .title {
  transform: translate3d(0%, -50px, 100px);
}

.character {
  width: 100%;
  opacity: 0;
  transition: all 0.5s;
  position: absolute;
  z-index: -1;
}

.card:hover .character {
  opacity: 1;
  transform: translate3d(0%, -30%, 100px);
}


.header {
  margin-bottom: 10px;
}

.content {
  font-size: 16px;
}

.info {
  margin-bottom: 5px;
}

.info > p {
  margin-bottom: 10px;
  font-size: 16px;
  font-weight: bold;
}

.p__span {
  color: #4f4e4e;
}

.trace span {
  border-radius: 5px;
  margin-bottom: 5px;
}

.trace__title {
  font-family: '微软雅黑', Arial, sans-serif; /* 使用指定的字体 */
  font-size: 16px; /* 字体大小 */
  font-weight: bold; /* 字体粗细 */
  color: #333; /* 字体颜色 */
  text-transform: uppercase; /* 将文本转换为大写 */
  letter-spacing: 2px; /* 字符间距 */
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); /* 文字阴影 */
  margin-top: 5px;
}

.expand__span {
  color: #9e9da1;
  font-size: 15px;
  text-align: center;
  margin-left: 10px;

}

.expand__p {
  font-size: 15px;
  font-weight: bold;
  margin-bottom: 5px;
}

.separator {
  border-top: 1px solid #ccc;
  margin: 20px 0;
}
</style>