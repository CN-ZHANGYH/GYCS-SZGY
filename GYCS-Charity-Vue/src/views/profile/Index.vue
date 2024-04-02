<script setup>
import {VsLoadingFn, VsNotification} from 'vuesax-alpha'
import {
  bindBankCard,
  getUserBindBankInfo,
  getUserDonationCount,
  getUserProfileInfo
} from "@/api/charity/charityuser.js";
import {onMounted, reactive, ref, toRefs} from "vue";
import {
  User,
  UserBold,
  Sms
} from "@vuesax-alpha/icons-vue"
import * as echarts from "echarts"
import {uploadImage} from "@/api/charity/upload.js";
import {updateUserProfileInfo} from "@/api/charity/charityuser.js";
const active = ref(false)
const imageUrl = ref('');
const bindBank = ref({})
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


const bindBankDialog = ref(false)
const page = ref(1)
const pageSize = ref(3)
const data = reactive({
  form: {},
  user: {},
  bindBank: {},
  bindInfo: {
    bankAccount: "暂无绑定",
    bankName: "暂无绑定",
    username: "暂无绑定",
    cardId: "暂无绑定",
    address: "暂无绑定"
  },
  transactionOption: {
    tooltip: {
      trigger: 'axis'
    },
    xAxis: [{
      show: false,
      type: 'category',
      data: ['2019-01','2019-02','2019-03','2019-04','2019-05','2019-06'],
      axisLine: {
        lineStyle: {
          color: "#999"
        }
      }
    }],
    yAxis: [{
      show: false,
      type: 'value',
      splitNumber: 4,
      splitLine: {
        lineStyle: {
          type: 'dashed',
          color: '#DDD'
        }
      },
      axisLine: {
        show: false,
        lineStyle: {
          color: "#333"
        },
      },
      nameTextStyle: {
        color: "#999"
      },
      splitArea: {
        show: false
      }
    }],
    series: [{
      type: 'line',
      data: [23,60,20,36,23,85],
      lineStyle: {
        normal: {
          width: 8,
          color: {
            type: 'linear',

            colorStops: [{
              offset: 0,
              color: '#A9F387' // 0% 处的颜色
            }, {
              offset: 1,
              color: '#48D8BF' // 100% 处的颜色
            }],
            globalCoord: false // 缺省为 false
          },
          shadowColor: 'rgba(72,216,191, 0.3)',
          shadowBlur: 10,
          shadowOffsetY: 20
        }
      },
      itemStyle: {
        normal: {
          color: '#fff',
          borderWidth: 10,
          /*shadowColor: 'rgba(72,216,191, 0.3)',
          shadowBlur: 100,*/
          borderColor: "#A9F387"
        }
      },
      smooth: true
    }]
  }
})
const points = ref(0);
const donations = ref(0);
const votes = ref(0);
const withdrawals = ref(0);
const transaction = ref()
const {form,bindInfo,user,transactionOption} = toRefs(data)
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
  getUserBindBankInfo().then(res => {
    if (res.code == 200) {
      bindInfo.value = res.data
    }
  })
  getUserProfileInfo().then(res => {
    user.value = res.userVo
    points.value = user.value.credit
    votes.value = user.value.voteCount
    withdrawals.value = user.value.withdrawCount
  })

  getUserDonationCount().then(res => {
    donations.value = res.count
  })

  var transactionEcharts = echarts.init(transaction.value);
  transactionEcharts.setOption(transactionOption.value)

  const loadingInstance = VsLoadingFn()
  setTimeout(() => {
    loadingInstance.close()
  }, 1000)
})

// 用户绑定银行卡
function handleBindBankCard(){
  bindBankCard(form.value).then(res => {
    if (res.code == 200){
      openNotification('succes','绑定通知',res.msg)
    }else  {
      openNotification('danger','绑定通知',res.msg)
    }
  })
  form.value =  {}
  bindBankDialog.value = false
}

const openNotification = (color,title,msg) => {
  VsNotification({
    color,
    position: 'top-left',
    title: title,
    content: msg,

  })
}


const counter = document.querySelector(".counter");
let count = 0;
setInterval(() => {
  if (count == 92) {
    clearInterval(count);
  } else {
    count += 1;
    counter.textContent = count + "%";
  }
}, 42);


const statistics = [
  { label: '积分', value: points },
  { label: '捐款次数', value: donations },
  { label: '投票次数', value: votes },
  { label: '提现次数', value: withdrawals }
];


const users = [
  {
    id: 1,
    name: 'Leanne Graham',
    username: 'Bret',
    email: 'Sincere@april.biz',
    website: 'hildegard.org',
  },
  {
    id: 2,
    name: 'Ervin Howell',
    username: 'Antonette',
    email: 'Shanna@melissa.tv',
    website: 'anastasia.net',
  },
  {
    id: 3,
    name: 'Clementine Bauch',
    username: 'Samantha',
    email: 'Nathan@yesenia.net',
    website: 'ramiro.info',
  },
  {
    id: 4,
    name: 'Patricia Lebsack',
    username: 'Karianne',
    email: 'Julianne.OConner@kory.org',
    website: 'kale.biz',
  },
  {
    id: 5,
    name: 'Chelsey Dietrich',
    username: 'Kamren',
    email: 'Lucio_Hettinger@annie.ca',
    website: 'demarco.info',
  },
  {
    id: 6,
    name: 'Mrs. Dennis Schulist',
    username: 'Leopoldo_Corkery',
    email: 'Karley_Dach@jasper.info',
    website: 'ola.org',
  },
  {
    id: 7,
    name: 'Kurtis Weissnat',
    username: 'Elwyn.Skiles',
    email: 'Telly.Hoeger@billy.biz',
    website: 'elvis.io',
  },
  {
    id: 8,
    name: 'Nicholas Runolfsdottir V',
    username: 'Maxime_Nienow',
    email: 'Sherwood@rosamond.me',
    website: 'jacynthe.com',
  },
  {
    id: 9,
    name: 'Glenna Reichert',
    username: 'Delphine',
    email: 'Chaim_McDermott@dana.io',
    website: 'conrad.com',
  },
  {
    id: 10,
    name: 'Clementina DuBuque',
    username: 'Moriah.Stanton',
    email: 'Rey.Padberg@karina.biz',
    website: 'ambrose.net',
  },
]
</script>

<template>
  <div class="wrapper">
    <div class="main-container">
      <div class="user-box first-box">
        <div class="activity card" style="--delay: .2s">
          <div class="title">User Activities</div>
          <div class="subtitle">
            <div class="statistics">
              <div v-for="(stat, index) in statistics" :key="index" class="statistic">
                <div class="statistic">
                  <div style="display: flex;flex-direction: column;align-items: center">
                    <div class="value">{{ stat.value }}</div>
                    <div class="label">{{ stat.label }}</div>
                  </div>
                </div>
                <div class="divider" v-if="index !== statistics.length - 1">

                </div>
              </div>
            </div>

          </div>
          <div class="activity-links">
            <div class="activity-link active">Current User</div>
            <div class="activity-link notify">User Request</div>
          </div>
          <div class="destination">

          </div>
        </div>
        <div class="discount card" style="--delay: .4s">
          <div class="discount-wrapper">
            <div class="plan-card">
              <h2>余额<span>FISCO BCOS区块链数字账户</span></h2>
              <div class="etiquet-price">
                <p>{{user.amount}}</p>
                <div></div>
              </div>
            </div>
          </div>

          <div class="button offer-button">充值余额</div>
          <div class="button offer-button" @click="bindBankDialog = true">绑定银行卡</div>
        </div>
        <div class="cards-wrapper" style="--delay: .6s">
          <div class="cards card" style="height: 100%">
            <div class="cards-head">
              <div class="cards-info">
                <div class="calendar-hour">08.20 <span class="am-pm">pm</span></div>
                <div class="degree">
                  <svg viewBox="0 0 512 512">
                    <circle cx="330.2" cy="240.1" fill="#feb137" r="78.9" />
                    <g fill="#ffd15b">
                      <path d="M320.5 130c-7.4.6-14-5-14.6-12.3l-4.1-47a13.5 13.5 0 0126.9-2.4l4 47c.7 7.4-4.8 14-12.2 14.6z" />
                      <path d="M320.5 130c-7.4.6-14-5-14.6-12.3l-4.1-47a13.5 13.5 0 0126.9-2.4l4 47c.7 7.4-4.8 14-12.2 14.6zM438 228.5c-.6-7.4 4.9-14 12.3-14.6l47-4a13.5 13.5 0 012.4 26.8l-47 4.1c-7.5.7-14-4.8-14.7-12.3zM413 307.7a13.5 13.5 0 0119-1.6l36.2 30.3a13.5 13.5 0 01-17.3 20.7l-36.2-30.4a13.5 13.5 0 01-1.6-19z" />
                      <path d="M413 307.7a13.5 13.5 0 0119-1.6l36.2 30.3a13.5 13.5 0 01-17.3 20.7l-36.2-30.4a13.5 13.5 0 01-1.6-19zM190 120.6a13.5 13.5 0 0119-1.7l36.2 30.4a13.5 13.5 0 01-17.3 20.6l-36.2-30.3a13.5 13.5 0 01-1.7-19zM447.4 98a13.5 13.5 0 011.7 19l-30.4 36.2A13.5 13.5 0 01398 136l30.4-36.2a13.5 13.5 0 0119-1.6z" />
                    </g>
                    <path d="M360 335.4a70.8 70.8 0 00-87.4-74A95 95 0 0085 286a85 85 0 00-3.4 170h273a60.4 60.4 0 005.2-120.6z" fill="#d8ecfe" />
                    <path d="M360 335.4a70.8 70.8 0 00-87.4-74 95 95 0 00-125.7-68.3 95 95 0 0190.6 74.2 70.8 70.8 0 0187.4 74A60.4 60.4 0 01345.6 456h9.2a60.4 60.4 0 005.1-120.6z" fill="#c4e2ff" />
                  </svg>
                  81.2° F in Sylhet
                </div>
              </div>
              <svg viewBox="0 0 512 512" fill="currentColor">
                <path d="M272 512h-32c-26 0-47.2-21.1-47.2-47.1V454c-11-3.5-21.8-8-32.1-13.3l-7.7 7.7a47.1 47.1 0 01-66.7 0l-22.7-22.7a47.1 47.1 0 010-66.7l7.7-7.7c-5.3-10.3-9.8-21-13.3-32.1H47.1c-26 0-47.1-21.1-47.1-47.1v-32.2c0-26 21.1-47.1 47.1-47.1H58c3.5-11 8-21.8 13.3-32.1l-7.7-7.7a47.1 47.1 0 010-66.7l22.7-22.7a47.1 47.1 0 0166.7 0l7.7 7.7c10.3-5.3 21-9.8 32.1-13.3V47.1c0-26 21.1-47.1 47.1-47.1h32.2c26 0 47.1 21.1 47.1 47.1V58c11 3.5 21.8 8 32.1 13.3l7.7-7.7a47.1 47.1 0 0166.7 0l22.7 22.7a47.1 47.1 0 010 66.7l-7.7 7.7c5.3 10.3 9.8 21 13.3 32.1h10.9c26 0 47.1 21.1 47.1 47.1v32.2c0 26-21.1 47.1-47.1 47.1H454c-3.5 11-8 21.8-13.3 32.1l7.7 7.7a47.1 47.1 0 010 66.7l-22.7 22.7a47.1 47.1 0 01-66.7 0l-7.7-7.7c-10.3 5.3-21 9.8-32.1 13.3v10.9c0 26-21.1 47.1-47.1 47.1zM165.8 409.2a176.8 176.8 0 0045.8 19 15 15 0 0111.3 14.5V465c0 9.4 7.7 17.1 17.1 17.1h32.2c9.4 0 17.1-7.7 17.1-17.1v-22.2a15 15 0 0111.3-14.5c16-4.2 31.5-10.6 45.8-19a15 15 0 0118.2 2.3l15.7 15.7a17.1 17.1 0 0024.2 0l22.8-22.8a17.1 17.1 0 000-24.2l-15.7-15.7a15 15 0 01-2.3-18.2 176.8 176.8 0 0019-45.8 15 15 0 0114.5-11.3H465c9.4 0 17.1-7.7 17.1-17.1v-32.2c0-9.4-7.7-17.1-17.1-17.1h-22.2a15 15 0 01-14.5-11.2c-4.2-16.1-10.6-31.6-19-45.9a15 15 0 012.3-18.2l15.7-15.7a17.1 17.1 0 000-24.2l-22.8-22.8a17.1 17.1 0 00-24.2 0l-15.7 15.7a15 15 0 01-18.2 2.3 176.8 176.8 0 00-45.8-19 15 15 0 01-11.3-14.5V47c0-9.4-7.7-17.1-17.1-17.1h-32.2c-9.4 0-17.1 7.7-17.1 17.1v22.2a15 15 0 01-11.3 14.5c-16 4.2-31.5 10.6-45.8 19a15 15 0 01-18.2-2.3l-15.7-15.7a17.1 17.1 0 00-24.2 0l-22.8 22.8a17.1 17.1 0 000 24.2l15.7 15.7a15 15 0 012.3 18.2 176.8 176.8 0 00-19 45.8 15 15 0 01-14.5 11.3H47c-9.4 0-17.1 7.7-17.1 17.1v32.2c0 9.4 7.7 17.1 17.1 17.1h22.2a15 15 0 0114.5 11.3c4.2 16 10.6 31.5 19 45.8a15 15 0 01-2.3 18.2l-15.7 15.7a17.1 17.1 0 000 24.2l22.8 22.8a17.1 17.1 0 0024.2 0l15.7-15.7a15 15 0 0118.2-2.3z"></path>
                <path d="M256 367.4c-61.4 0-111.4-50-111.4-111.4s50-111.4 111.4-111.4 111.4 50 111.4 111.4-50 111.4-111.4 111.4zm0-192.8a81.5 81.5 0 000 162.8 81.5 81.5 0 000-162.8z"></path>
              </svg>
            </div>
            <div class="items days">
              <div class="item">Mon</div>
              <div class="item">Tue</div>
              <div class="item">Wed</div>
              <div class="item">Thu</div>
              <div class="item">Fri</div>
              <div class="item">Sat</div>
              <div class="item">Sun</div>
            </div>
            <div class="items numbers">
              <div class="item">1</div>
              <div class="item">2</div>
              <div class="item">3</div>
              <div class="item">4</div>
              <div class="item">5</div>
              <div class="item">6</div>
              <div class="item">7</div>
              <div class="item">8</div>
              <div class="item">9</div>
              <div class="item">10</div>
              <div class="item">11</div>
              <div class="item">12</div>
              <div class="item">13</div>
              <div class="item">14</div>
              <div class="item">15</div>
              <div class="item">16</div>
              <div class="item is-active">17</div>
              <div class="item">18</div>
              <div class="item">19</div>
              <div class="item">20</div>
              <div class="item">21</div>
              <div class="item">22</div>
              <div class="item">23</div>
              <div class="item">24</div>
              <div class="item">25</div>
              <div class="item">26</div>
              <div class="item">27</div>
              <div class="item">28</div>
              <div class="item">29</div>
              <div class="item">30</div>
              <div class="item">31</div>
              <div class="item disable">1</div>
              <div class="item disable">2</div>
              <div class="item disable">3</div>
            </div>
          </div>
        </div>
        <div class="account-wrapper" style="--delay: .8s">
          <div class="account-profile">
            <img :src="user.avatar" alt="">
            <div class="blob-wrap">
              <div class="blob"></div>
              <div class="blob"></div>
              <div class="blob"></div>
            </div>
            <div class="account-name">{{user.userName}}</div>
            <div class="account-title"><vs-button @click="active = true">更新资料</vs-button></div>
          </div>
          <div class="flip-card">
            <div class="flip-card-inner">
              <div class="flip-card-front">
                <p class="heading_8264">{{bindInfo.bankName}}</p>
                <svg class="bank-logo" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="36" height="36" viewBox="0 0 48 48">
                  <path fill="#ff9800" d="M32 10A14 14 0 1 0 32 38A14 14 0 1 0 32 10Z"></path><path fill="#d50000" d="M16 10A14 14 0 1 0 16 38A14 14 0 1 0 16 10Z"></path><path fill="#ff3d00" d="M18,24c0,4.755,2.376,8.95,6,11.48c3.624-2.53,6-6.725,6-11.48s-2.376-8.95-6-11.48 C20.376,15.05,18,19.245,18,24z"></path>
                </svg>
                <svg version="1.1" class="chip" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="30px" height="30px" viewBox="0 0 50 50" xml:space="preserve">  <image id="image0" width="50" height="50" x="0" y="0" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAMAAAAp4XiDAAAABGdBTUEAALGPC/xhBQAAACBjSFJN
              AAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAB6VBMVEUAAACNcTiVeUKVeUOY
              fEaafEeUeUSYfEWZfEaykleyklaXe0SWekSZZjOYfEWYe0WXfUWXe0WcgEicfkiXe0SVekSXekSW
              ekKYe0a9nF67m12ZfUWUeEaXfESVekOdgEmVeUWWekSniU+VeUKVeUOrjFKYfEWliE6WeESZe0GS
              e0WYfES7ml2Xe0WXeESUeEOWfEWcf0eWfESXe0SXfEWYekSVeUKXfEWxklawkVaZfEWWekOUekOW
              ekSYfESZe0eXekWYfEWZe0WZe0eVeUSWeETAnmDCoWLJpmbxy4P1zoXwyoLIpWbjvXjivnjgu3bf
              u3beunWvkFWxkle/nmDivXiWekTnwXvkwHrCoWOuj1SXe0TEo2TDo2PlwHratnKZfEbQrWvPrWua
              fUfbt3PJp2agg0v0zYX0zYSfgkvKp2frxX7mwHrlv3rsxn/yzIPgvHfduXWXe0XuyIDzzISsjVO1
              lVm0lFitjVPzzIPqxX7duna0lVncuHTLqGjvyIHeuXXxyYGZfUayk1iyk1e2lln1zYTEomO2llrb
              tnOafkjFpGSbfkfZtXLhvHfkv3nqxH3mwXujhU3KqWizlFilh06khk2fgkqsjlPHpWXJp2erjVOh
              g0yWe0SliE+XekShhEvAn2D///+gx8TWAAAARnRSTlMACVCTtsRl7Pv7+vxkBab7pZv5+ZlL/UnU
              /f3SJCVe+Fx39naA9/75XSMh0/3SSkia+pil/KRj7Pr662JPkrbP7OLQ0JFOijI1MwAAAAFiS0dE
              orDd34wAAAAJcEhZcwAACxMAAAsTAQCanBgAAAAHdElNRQfnAg0IDx2lsiuJAAACLElEQVRIx2Ng
              GAXkAUYmZhZWPICFmYkRVQcbOwenmzse4MbFzc6DpIGXj8PD04sA8PbhF+CFaxEU8iWkAQT8hEVg
              OkTF/InR4eUVICYO1SIhCRMLDAoKDvFDVhUaEhwUFAjjSUlDdMiEhcOEItzdI6OiYxA6YqODIt3d
              I2DcuDBZsBY5eVTr4xMSYcyk5BRUOXkFsBZFJTQnp6alQxgZmVloUkrKYC0qqmji2WE5EEZuWB6a
              lKoKdi35YQUQRkFYPpFaCouKIYzi6EDitJSUlsGY5RWVRGjJLyxNy4ZxqtIqqvOxaVELQwZFZdkI
              JVU1RSiSalAt6rUwUBdWG1CP6pT6gNqwOrgCdQyHNYR5YQFhDXj8MiK1IAeyN6aORiyBjByVTc0F
              qBoKWpqwRCVSgilOaY2OaUPw29qjOzqLvTAchpos47u6EZyYnngUSRwpuTe6D+6qaFQdOPNLRzOM
              1dzhRZyW+CZouHk3dWLXglFcFIflQhj9YWjJGlZcaKAVSvjyPrRQ0oQVKDAQHlYFYUwIm4gqExGm
              BSkutaVQJeomwViTJqPK6OhCy2Q9sQBk8cY0DxjTJw0lAQWK6cOKfgNhpKK7ZMpUeF3jPa28BCET
              amiEqJKM+X1gxvWXpoUjVIVPnwErw71nmpgiqiQGBjNzbgs3j1nus+fMndc+Cwm0T52/oNR9lsdC
              S24ra7Tq1cbWjpXV3sHRCb1idXZ0sGdltXNxRateRwHRAACYHutzk/2I5QAAACV0RVh0ZGF0ZTpj
              cmVhdGUAMjAyMy0wMi0xM1QwODoxNToyOSswMDowMEUnN7UAAAAldEVYdGRhdGU6bW9kaWZ5ADIw
              MjMtMDItMTNUMDg6MTU6MjkrMDA6MDA0eo8JAAAAKHRFWHRkYXRlOnRpbWVzdGFtcAAyMDIzLTAy
              LTEzVDA4OjE1OjI5KzAwOjAwY2+u1gAAAABJRU5ErkJggg=="></image>
            </svg>
                <svg version="1.1" class="contactless" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="20px" height="20px" viewBox="0 0 50 50" xml:space="preserve">  <image id="image0" width="50" height="50" x="0" y="0" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAQAAAC0NkA6AAAABGdBTUEAALGPC/xhBQAAACBjSFJN
              AAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QA/4ePzL8AAAAJcEhZ
              cwAACxMAAAsTAQCanBgAAAAHdElNRQfnAg0IEzgIwaKTAAADDklEQVRYw+1XS0iUURQ+f5qPyjQf
              lGRFEEFK76koKGxRbWyVVLSOgsCgwjZBJJYuKogSIoOonUK4q3U0WVBWFPZYiIE6kuArG3VGzK/F
              fPeMM/MLt99/NuHdfPd888/57jn3nvsQWWj/VcMlvMMd5KRTogqx9iCdIjUUmcGR9ImUYowyP3xN
              GQJoRLVaZ2DaZf8kyjEJALhI28ELioyiwC+Rc3QZwRYyO/DH51hQgWm6DMIh10KmD4u9O16K49it
              VoPOAmcGAWWOepXIRScAoJZ2Frro8oN+EyTT6lWkkg6msZfMSR35QTJmjU0g15tIGSJ08ZZMJkJk
              HpNZgSkyXosS13TkJpZ62mPIJvOSzC1bp8vRhhCakEk7G9/o4gmZdbpsTcKu0m63FbnBP9Qrc15z
              bkbemfgNDtEOI8NO5L5O9VYyRYgmJayZ9nPaxZrSjW4+F6Uw9yQqIiIZwhp2huQTf6OIvCZyGM6g
              DJBZbyXifJXr7FZjGXsdxADxI7HUJFB6iWvsIhFpkoiIiGTJfjJfiCuJg2ZEspq9EHGVpYgzKqwJ
              qSAOEwuJQ/pxPvE3cYltJCLdxBLiSKKIE5HxJKcTRNeadxfhDiuYw44zVs1dxKwRk/uCxIiQkxKB
              sSctRVAge9g1E15EHE6yRUaJecRxcWlukdRIbGFOSZCMWQA/iWauIP3slREHXPyliqBcrrD71Amz
              Z+rD1Mt2Yr8TZc/UR4/YtFnbijnHi3UrN9vKQ9rPaJf867ZiaqDB+czeKYmd3pNa6fuI75MiC0uX
              XSR5aEMf7s7a6r/PudVXkjFb/SsrCRfROk0Fx6+H1i9kkTGn/E1vEmt1m089fh+RKdQ5O+xNJPUi
              cUIjO0Dm7HwvErEr0YxeibL1StSh37STafE4I7zcBdRq1DiOkdmlTJVnkQTBTS7X1FYyvfO4piaI
              nKbDCDaT2anLudYXCRFsQBgAcIF2/Okwgvz5+Z4tsw118dzruvIvjhTB+HOuWy8UvovEH6beitBK
              xDyxm9MmISKCWrzB7bSlaqGlsf0FC0gMjzTg6GgAAAAldEVYdGRhdGU6Y3JlYXRlADIwMjMtMDIt
              MTNUMDg6MTk6NTYrMDA6MDCjlq7LAAAAJXRFWHRkYXRlOm1vZGlmeQAyMDIzLTAyLTEzVDA4OjE5
              OjU2KzAwOjAw0ssWdwAAACh0RVh0ZGF0ZTp0aW1lc3RhbXAAMjAyMy0wMi0xM1QwODoxOTo1Nisw
              MDowMIXeN6gAAAAASUVORK5CYII="></image>
            </svg>
                <p class="number">{{bindInfo.bankAccount}}</p>
                <p class="valid_thru">GIV TECH</p>
                <p class="date_8264">0 0 / 0 0</p>
                <p class="name">{{bindInfo.username}}</p>
              </div>
              <div class="flip-card-back">
                <div class="strip"></div>
                <div class="mstrip"></div>
                <div class="sstrip">
                  <p class="code">***</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="user-box second-box">
        <div class="cards-wrapper" style="--delay: 1s">
          <div class="cards card">
            <vs-table>
              <template #thead>
                <vs-tr>
                  <vs-th> Name </vs-th>
                  <vs-th> Email </vs-th>
                  <vs-th> Id </vs-th>
                  <vs-th> 交易 </vs-th>
                </vs-tr>
              </template>
              <template #tbody>
                <vs-tr v-for="(tr, i) in users" :key="i" :data="tr">
                  <vs-td>
                    {{ tr.name }}
                  </vs-td>
                  <vs-td>
                    {{ tr.email }}
                  </vs-td>
                  <vs-td>
                    {{ tr.id }}
                  </vs-td>
                  <vs-td>
                    <div id="transaction" ref="transaction" style="width: 300px;height: 400px">
                    </div>
                  </vs-td>
                </vs-tr>
              </template>
            </vs-table>
          </div>
        </div>

        <div class="card transection" style="--delay: 1.2s">
          <div class="transection-header">
            <div class="head">详细信息</div>
            <div class="head is-wait">😶‍🌫️</div>
          </div>
          <div class="credit-wrapper">
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" fill="none" version="1.1" width="40" height="40" viewBox="0 0 40 40"><defs><clipPath id="master_svg0_0_2349"><rect x="0" y="0" width="40" height="40" rx="0"/></clipPath></defs><g clip-path="url(#master_svg0_0_2349)"><g><g style="opacity:0.30000001192092896;"><path d="M20,7C12.8203,7,7,12.8203,7,20C7,24.375,9.16213,28.245,12.47171,30.5995C14.59624,32.111000000000004,17.1958,33,20,33C22.8037,33,25.4029,32.1113,27.5272,30.6003C30.8374,28.2458,33,24.3755,33,20C33,12.8203,27.1797,7,20,7C20,7,20,7,20,7Z" fill-rule="evenodd" fill="#00B9FF" fill-opacity="1"/></g><g><path d="M25.131500000000003,19.225C25.131500000000003,22.14244,22.80394,24.45,20.00375,24.45C17.20356,24.45,14.87596,22.14244,14.87596,19.225C14.87596,16.30755,17.20356,14,20.00375,14C22.80394,14,25.131500000000003,16.30755,25.131500000000003,19.225C25.131500000000003,19.225,25.131500000000003,19.225,25.131500000000003,19.225ZM25.8536,28.2806C26.4199,28.7649,26.7668,29.3915,26.971600000000002,30.2228C27.082700000000003,30.6735,26.8595,31.1344,26.4478,31.3599C24.5402,32.4049,22.343780000000002,33,20.00375,33C17.660800000000002,33,15.46182,32.403400000000005,13.552556,31.356C13.142882,31.1313,12.9206555,30.6734,13.0260405,30.2236C13.230004,29.353,13.579481,28.6978,14.15365,28.200699999999998C15.03511,27.4375,16.69931,26.826700000000002,19.99027,26.825C23.284100000000002,26.8991,24.9615,27.517699999999998,25.8536,28.2806C25.8536,28.2806,25.8536,28.2806,25.8536,28.2806Z" fill="#00B9FF" fill-opacity="1"/></g></g></g></svg>
            <div class="credit-name">
              <div class="credit-type">昵称</div>
              <div class="credit-status">{{user.nickName}}</div>
            </div>
          </div>
          <div class="credit-wrapper">
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" fill="none" version="1.1" width="40" height="40" viewBox="0 0 40 40"><defs><clipPath id="master_svg0_0_1894"><rect x="0" y="0" width="40" height="40" rx="0"/></clipPath></defs><g clip-path="url(#master_svg0_0_1894)"><g><g transform="matrix(-0.4922545254230499,-0.8704513311386108,0.8614834547042847,-0.5077856779098511,3.473338287695327,42.98250845214102)" style="opacity:0.30000001192092896;"><path d="M19.52051615081787,21.28512213543701C20.00894615081787,21.47413913543701,21.09777615081787,22.061629135437013,22.09566615081787,22.86730913543701C22.63672615081787,23.30414913543701,23.191486150817873,23.83670913543701,23.62326615081787,24.446849135437013C24.05351615081787,25.05482913543701,24.39905615081787,25.793539135437012,24.427156150817872,26.624309135437013C24.42755615081787,26.63797913543701,24.42775615081787,26.65165913543701,24.42765615081787,26.665329135437013C24.42765615081787,26.665329135437013,24.42735615081787,26.70234913543701,24.42735615081787,26.70234913543701C24.42435615081787,27.072699135437013,24.42215615081787,27.339689135437013,24.391456150817874,27.57830913543701C24.17285615081787,29.27938913543701,22.861166150817873,30.67420913543701,21.14462615081787,30.91000913543701C20.91093615081787,30.94210913543701,20.649376150817872,30.94430913543701,20.31226615081787,30.947009135437014C20.31226615081787,30.947009135437014,19.56490615081787,30.953809135437012,19.56490615081787,30.953809135437012C18.892506150817873,30.959609135437013,18.35673615081787,30.964309135437013,17.91924615081787,30.89230913543701C17.57351615081787,30.83540913543701,17.291176150817872,30.69360913543701,17.10070615081787,30.586109135437013C16.66998615081787,30.343259135437012,16.336026150817872,29.98059913543701,16.07801615081787,29.641069135437014C15.815126150817871,29.295119135437012,15.581036150817871,28.90484913543701,15.37540615081787,28.547669135437012C15.308176150817872,28.430889135437013,15.244786150817871,28.31917913543701,15.183686150817872,28.211469135437014C15.04710515081787,27.97072913543701,14.921897150817871,27.750039135437014,14.790488150817872,27.537689135437013C14.600947150817872,27.231389135437013,14.447591150817871,27.02585913543701,14.31996015081787,26.90203913543701C14.137640650817872,26.72514913543701,14.04293825081787,26.46682913543701,14.11151525081787,26.22343913543701C14.33770915081787,25.42063913543701,14.45863115081787,24.574399135437012,14.45863115081787,23.700209135437014C14.45863115081787,23.02301913543701,14.38607015081787,22.362609135437012,14.248221150817871,21.72616913543701C14.097287690817872,21.02931313543701,14.61636915081787,20.312549135437013,15.33348615081787,20.377404325437013C16.338006150817872,20.468252135437012,17.133706150817872,20.59665413543701,17.75290615081787,20.73007013543701C18.36238615081787,20.86139513543701,18.80182615081787,20.99779013543701,19.101516150817872,21.10850613543701C19.251366150817873,21.163867135437012,19.36623615081787,21.21279613543701,19.44989615081787,21.251434135437012C19.47657615081787,21.263755135437012,19.500076150817872,21.27502713543701,19.52051615081787,21.28512213543701C19.52051615081787,21.28512213543701,19.52051615081787,21.28512213543701,19.52051615081787,21.28512213543701Z" fill-rule="evenodd" fill="#00B9FF" fill-opacity="1"/></g><g transform="matrix(-0.4922545254230499,-0.8704513311386108,0.8614834547042847,-0.5077856779098511,3.0173431190013105,70.11025283403433)"><path d="M28.71589337738037,34.58113013897705C30.069263377380373,34.30253413897705,32.11311337738037,34.02565083897705,34.94511337738037,34.001521308977054C35.03821337738037,34.00072830597705,35.13021337738037,34.00022419097705,35.22131337738037,34.00000014538705C35.60851337738037,33.99904749997705,35.962913377380374,34.22455913897705,36.09541337738037,34.58469413897705C36.42451337738037,35.47958713897705,36.60401337738037,36.445587138977054,36.60401337738037,37.453087138977054C36.60401337738037,38.04214713897705,36.54261337738037,38.61702713897705,36.42591337738037,39.17179713897705C36.321213377380374,39.66913713897705,35.81111337738037,39.94828713897705,35.29931337738037,39.90962713897705C35.09551337738037,39.89423713897705,34.87331337738037,39.88590713897705,34.63311337738037,39.88795713897705C33.42231337738037,39.89827713897705,32.55981337738037,40.16624713897705,32.24981337738037,40.27920713897705C32.21531337738037,40.29179713897705,32.16431337738037,40.31623713897705,32.083613377380374,40.36682713897705C32.00721337738037,40.41464713897705,31.93511337738037,40.46636713897705,31.86731337738037,40.521767138977054C31.741313377380372,40.62484713897705,31.582603377380373,40.81216713897705,31.385743377380372,41.11310713897705C31.208163377380373,41.384587138977054,31.035033377380373,41.69163713897705,30.841313377380374,42.03518713897705C30.841313377380374,42.03518713897705,30.78790337738037,42.129887138977054,30.78790337738037,42.129887138977054C30.58127337738037,42.49594713897705,30.34949337738037,42.90215713897705,30.096333377380372,43.26516713897705C29.84750337738037,43.62196713897705,29.532703377380372,44.00489713897705,29.13229337738037,44.28189713897705C28.84374337738037,44.48149713897705,28.426173377380373,44.72429713897705,27.907413377380372,44.788197138977054C27.51779337738037,44.83619713897705,27.03715337738037,44.84029713897705,26.439893377380372,44.84539713897705C26.439893377380372,44.84539713897705,25.74074337738037,44.85129713897705,25.74074337738037,44.85129713897705C25.398033377380372,44.85429713897705,25.13426337738037,44.85659713897705,24.89846337738037,44.82819713897705C23.17033337738037,44.61969713897705,21.865242377380373,43.22915713897705,21.673246977380373,41.51928713897705C21.64681066738037,41.28383713897705,21.64904129738037,41.01912713897705,21.652074157380373,40.65932713897705C21.652074157380373,40.65932713897705,21.65252360738037,40.60561713897705,21.65252360738037,40.60561713897705C21.65263615738037,40.59195713897705,21.653035157380373,40.57830713897705,21.653720567380372,40.564657138977054C21.731612677380372,39.01437713897705,22.71479337738037,37.786317138977054,23.70407337738037,36.93698713897705C24.70568337738037,36.077057138977054,25.869883377380372,35.46769713897705,26.66150337738037,35.15756713897705C26.66150337738037,35.15756713897705,26.68962337738037,35.14596713897705,26.68962337738037,35.14596713897705C26.71649337738037,35.13507713897705,26.75287337738037,35.12074713897705,26.79896337738037,35.10346713897705C26.89113337738037,35.06889713897705,27.02208337738037,35.02254713897705,27.19333337738037,34.96836613897705C27.53587337738037,34.85999313897705,28.03932337738037,34.72040413897705,28.71589337738037,34.58113013897705C28.71589337738037,34.58113013897705,28.71589337738037,34.58113013897705,28.71589337738037,34.58113013897705Z" fill="#00B9FF" fill-opacity="1"/></g></g></g></svg>
            <div class="credit-name">
              <div class="credit-type">手机</div>
              <div class="credit-status">{{user.phonenumber}}</div>
            </div>
          </div>
          <div class="credit-wrapper">
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" fill="none" version="1.1" width="40" height="40" viewBox="0 0 40 40"><defs><clipPath id="master_svg0_0_2376"><rect x="0" y="0" width="40" height="40" rx="0"/></clipPath></defs><g clip-path="url(#master_svg0_0_2376)"><g><g style="opacity:0.30000001192092896;"><path d="M30.1779,8.762955C29.3717,8.352174,28.4846,8.171864,27.4222,8.0850666C26.3809,7.9999879599,25.087,7.99999344226,23.4444,8.000000117985C23.4444,8.000000117985,17.5731,8.000000356403,17.5731,8.000000356403C15.91397,7.99999785301,14.37715,7.99999558926,13.09398,8.0838752C11.83874,8.165929,10.6633,8.334324,9.82207,8.762955C8.50493,9.43407,7.43407,10.50493,6.762955,11.82207C6.352174,12.62827,6.171864,13.51543,6.0850666,14.57778C5.9999879599,15.61909,5.99999344226,16.913040000000002,6.000000117985,18.5556C6.000000117985,18.5556,6.000000117985,21.4444,6.000000117985,21.4444C5.99999344226,23.087,5.9999879599,24.3809,6.0850666,25.4222C6.171864,26.4846,6.352174,27.3717,6.762955,28.1779C7.43407,29.4951,8.50493,30.5659,9.82207,31.237C10.6633,31.6657,11.83874,31.8341,13.09398,31.9161C14.37716,32,15.91399,32,17.5731,32C17.5731,32,23.4444,32,23.4444,32C25.087,32,26.3809,32,27.4222,31.9149C28.4846,31.8281,29.3717,31.6478,30.1779,31.237C31.4951,30.5659,32.5659,29.4951,33.236999999999995,28.1779C33.647800000000004,27.3717,33.8281,26.4846,33.9149,25.4222C34,24.3809,34,23.087,34,21.4444C34,21.4444,34,18.5556,34,18.5556C34,16.91303,34,15.61909,33.9149,14.57778C33.8281,13.51543,33.647800000000004,12.62827,33.236999999999995,11.82207C32.5659,10.50493,31.4951,9.43407,30.1779,8.762955C30.1779,8.762955,30.1779,8.762955,30.1779,8.762955Z" fill-rule="evenodd" fill="#00B9FF" fill-opacity="1"/></g><g><path d="M13.0546879296875,13.167949188476562C12.5951579296875,12.861597188476562,11.9742869296875,12.985770388476562,11.6679339296875,13.445299188476563C11.3615809296875,13.904828188476563,11.4857556296875,14.525693188476563,11.9452839296875,14.832053188476563C11.9452839296875,14.832053188476563,16.1614279296875,17.642813188476563,16.1614279296875,17.642813188476563C16.930287929687502,18.15541318847656,17.573397929687502,18.584183188476562,18.138727929687498,18.878273188476562C18.7365779296875,19.189283188476562,19.3277679296875,19.39445318847656,19.9999879296875,19.39445318847656C20.672207929687502,19.39445318847656,21.2633879296875,19.189283188476562,21.8612779296875,18.878273188476562C22.4265779296875,18.584183188476562,23.0696779296875,18.15541318847656,23.8385779296875,17.642813188476563C23.8385779296875,17.642813188476563,28.0546779296875,14.832053188476563,28.0546779296875,14.832053188476563C28.5141779296875,14.525693188476563,28.6383779296875,13.904828188476563,28.3320779296875,13.445299188476563C28.0256779296875,12.985770388476562,27.404777929687498,12.861597188476562,26.9452779296875,13.167949188476562C26.9452779296875,13.167949188476562,22.773477929687502,15.949153188476563,22.773477929687502,15.949153188476563C21.948777929687502,16.498963188476562,21.395777929687497,16.865973188476563,20.9382379296875,17.10399318847656C20.5023879296875,17.330733188476565,20.2360879296875,17.39445318847656,19.9999879296875,17.39445318847656C19.7638779296875,17.39445318847656,19.497577929687502,17.330733188476565,19.0617279296875,17.10399318847656C18.604187929687498,16.865973188476563,18.0512079296875,16.498963188476562,17.2264879296875,15.949153188476563C17.2264879296875,15.949153188476563,13.0546879296875,13.167949188476562,13.0546879296875,13.167949188476562C13.0546879296875,13.167949188476562,13.0546879296875,13.167949188476562,13.0546879296875,13.167949188476562Z" fill="#00B9FF" fill-opacity="1"/></g></g></g></svg>
            <div class="credit-name">
              <div class="credit-type">邮箱</div>
              <div class="credit-status">{{user.email}}</div>
            </div>
          </div>
          <div class="credit-wrapper">
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" fill="none" version="1.1" width="40" height="40" viewBox="0 0 40 40"><defs><clipPath id="master_svg0_0_2283"><rect x="0" y="0" width="40" height="40" rx="0"/></clipPath></defs><g clip-path="url(#master_svg0_0_2283)"><g><g style="opacity:0.30000001192092896;"><path d="M15,17.679560000000002C15,17.679560000000002,23.23059,12,23.23059,12C23.23059,12,24.366500000000002,13.64612,24.366500000000002,13.64612C24.366500000000002,13.64612,16.13591,19.32568,16.13591,19.32568C16.13591,19.32568,15,17.679560000000002,15,17.679560000000002C15,17.679560000000002,15,17.679560000000002,15,17.679560000000002ZM15,22.6461C15,22.6461,23.23059,28.3257,23.23059,28.3257C23.23059,28.3257,24.366500000000002,26.6796,24.366500000000002,26.6796C24.366500000000002,26.6796,16.13591,21,16.13591,21C16.13591,21,15,22.6461,15,22.6461C15,22.6461,15,22.6461,15,22.6461Z" fill="#00B9FF" fill-opacity="1"/></g><g><path d="M31,28.7273C31,28.6901,30.9995,28.6531,30.9986,28.6162C30.9681,27.4339,30.4531,26.3778,29.6467,25.6253C28.8674,24.8982,27.816,24.4545,26.6667,24.4545C25.5174,24.4545,24.465899999999998,24.8982,23.686700000000002,25.6253C22.881,26.3771,22.3662,27.432,22.334899999999998,28.6129C22.3338,28.6509,22.3333,28.689,22.3333,28.7273C22.3333,31.1043,24.2908,33,26.6667,33C29.0425,33,31,31.1043,31,28.7273C31,28.7273,31,28.7273,31,28.7273ZM9,20C9,22.377000000000002,10.95748,24.2727,13.33333,24.2727C14.48265,24.2727,15.53406,23.8291,16.31333,23.102C17.14497,22.326,17.66667,21.2271,17.66667,20C17.66667,18.7729,17.14497,17.674,16.31333,16.89803C15.53406,16.1709,14.48265,15.72727,13.33333,15.72727C12.18402,15.72727,11.13261,16.1709,10.35334,16.89803C9.5217,17.674,9,18.7729,9,20C9,20,9,20,9,20ZM26.6667,7C24.2908,7,22.3333,8.89575,22.3333,11.27273C22.3333,12.49985,22.855,13.59871,23.686700000000002,14.3747C24.465899999999998,15.10183,25.5174,15.54545,26.6667,15.54545C27.816,15.54545,28.8674,15.10183,29.6467,14.3747C30.4518,13.62345,30.9664,12.56958,30.9984,11.38968C30.9995,11.35083,31,11.31184,31,11.27273C31,8.89575,29.0425,7,26.6667,7C26.6667,7,26.6667,7,26.6667,7Z" fill="#00B9FF" fill-opacity="1"/></g></g></g></svg>
            <div class="credit-name">
              <div class="credit-type">性别</div>
              <div class="credit-status">{{user.sex == '0' ? '男' : '女'}}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <vs-dialog v-model="bindBankDialog" overlay-blur>
    <template #header>
      <h4 class="not-margin">绑定银行卡信息</h4>
    </template>

    <div class="bind-form">
      <vs-input style="width: 400px" v-model="bindBank.bankAccount" placeholder="请输入开户账户">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-square" viewBox="0 0 16 16">
            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
            <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm12 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1v-1c0-1-1-4-6-4s-6 3-6 4v1a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12z"/>
          </svg>
        </template>
      </vs-input>
      <vs-input  style="width: 400px" v-model="bindBank.bankName" placeholder="请输入开户银行">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bank" viewBox="0 0 16 16">
            <path d="m8 0 6.61 3h.89a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.5.5H15v7a.5.5 0 0 1 .485.38l.5 2a.498.498 0 0 1-.485.62H.5a.498.498 0 0 1-.485-.62l.5-2A.501.501 0 0 1 1 13V6H.5a.5.5 0 0 1-.5-.5v-2A.5.5 0 0 1 .5 3h.89L8 0ZM3.777 3h8.447L8 1 3.777 3ZM2 6v7h1V6H2Zm2 0v7h2.5V6H4Zm3.5 0v7h1V6h-1Zm2 0v7H12V6H9.5ZM13 6v7h1V6h-1Zm2-1V4H1v1h14Zm-.39 9H1.39l-.25 1h13.72l-.25-1Z"/>
          </svg>
        </template>
      </vs-input>
      <vs-input  style="width: 400px" v-model="bindBank.username" placeholder="请输入开户姓名">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-exclamation" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Z"/>
            <path d="M8.256 14a4.474 4.474 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10c.26 0 .507.009.74.025.226-.341.496-.65.804-.918C9.077 9.038 8.564 9 8 9c-5 0-6 3-6 4s1 1 1 1h5.256Z"/>
            <path fill-rule="evenodd" d="M16 12.5a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Zm-3.5-2a.5.5 0 0 1 .5.5v1.5a.5.5 0 0 1-1 0V11a.5.5 0 0 1 .5-.5Zm0 4a.5.5 0 1 0 0-1 .5.5 0 0 0 0 1Z"/>
          </svg>
        </template>
      </vs-input>
      <vs-input  style="width: 400px" v-model="bindBank.cardId" placeholder="请输入身份证号码">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-vcard" viewBox="0 0 16 16">
            <path d="M5 8a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm4-2.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5ZM9 8a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4A.5.5 0 0 1 9 8Zm1 2.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5Z"/>
            <path fill-rule="evenodd" d="M2 2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H2ZM1 4a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H8.96c.026-.163.04-.33.04-.5C9 10.567 7.21 9 5 9c-2.086 0-3.8 1.398-3.984 3.181A1.006 1.006 0 0 1 1 12V4Z"/>
          </svg>
        </template>
      </vs-input>
      <vs-input  style="width: 400px" v-model="bindBank.address" placeholder="请输入家庭住址">
        <template #icon>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-fill" viewBox="0 0 16 16">
            <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5Z"/>
            <path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6Z"/>
          </svg>
        </template>
      </vs-input>
    </div>

    <template #footer>
      <div class="footer-dialog">
        <vs-button block @click="handleBindBankCard"> 绑定 </vs-button>
      </div>
    </template>
  </vs-dialog>

  <vs-dialog v-model="active" overlay-blur>
    <template #header>
      <h4 class="not-margin">更新用户的信息</h4>
    </template>
    <div class="con-form">
      <div class="content-image">
        <div class="content-image-main" @click="handleAddImg">
          <img v-if="imageUrl" :src="form.avatar" class="content-image-img" />
          <div v-if="!imageUrl" >
            <text class="title">点击更换头像</text>
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
      </div>
    </template>
  </vs-dialog>
</template>

<style lang="scss" scoped>
$font-family: "Inter", sans-serif;
$bg-color: #252954;
$body-color: #9b9ca7;
$main-bg: #0e0e23;
:root {
  --delay: 0s;
}

* {
  outline: none;
  box-sizing: border-box;
}

.hidden {
  display: none !important;
}

html {
  box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
}

body {
  font-family: $font-family;
  background-color: $bg-color;
  color: $body-color;
}

.wrapper {
  //max-width: 1600px;
  //background-color: $main-bg;
  padding: 20px 20px;
  margin: 20px  20px;
  display: flex;
  height: 100vh;
  overflow: hidden;
}

.left-side {
  width: 6rem;
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
  align-items: center;
  padding: 117px 0 40px;
  animation: left 1s var(--delay) both;
  svg {
    width: 24px;
    cursor: pointer;
    & + svg {
      margin-top: 34px;
    }
    &:last-child {
      margin-top: auto;
    }
    &.active,
    &:hover {
      color: #4255d4;
    }
  }
}

@keyframes left {
  0% {
    transform: translateX(-30px);
    opacity: 0;
  }

  100% {
    opacity: 1;
    transform: none;
  }
}

@keyframes top {
  0% {
    transform: translateY(-30px);
    opacity: 0;
  }

  100% {
    opacity: 1;
    transform: none;
  }
}

.main-container {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  padding: 25px;
  overflow: auto;
  padding-left: 0;
}

.header {
  background: rgb(20, 24, 52);
  background: radial-gradient(
          circle,
          rgba(20, 24, 52, 1) 0%,
          rgba(19, 22, 47, 1) 100%
  );
  box-shadow: 0 16px 12px $main-bg;
  width: 100%;
  padding: 0 30px;
  animation: top 1s both;
  display: flex;
  align-items: center;
  border-radius: 6px;
  font-size: 15px;
  white-space: nowrap;
  position: sticky;
  top: 0;
  left: 0;
  z-index: 10;
  &:before {
    content: "";
    width: 100%;
    height: 25px;
    position: absolute;
    top: -25px;
    left: 0;
    background-color: $main-bg;
  }
  &-link {
    color: $body-color;
    text-decoration: none;
    display: flex;
    align-items: center;
    padding: 20px;
    transition: 0.3s;
    border-bottom: 3px solid transparent;
    transition: 0.3s;
    svg {
      width: 20px;
      margin-right: 14px;
    }
    &.active,
    &:hover {
      background: #11132c;
      border-bottom: 3px solid #4255d4;
    }
  }
}

.logo {
  padding: 20px 50px 20px 0;
  font-size: 16px;
  color: #e7e8ea;
  &-det {
    background: #4255d4;
    padding: 8px;
    margin-left: -2px;
    border-radius: 50%;
    font-size: 15px;
  }
}

.user-info {
  margin-left: auto;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  svg {
    width: 20px;
  }
  .profile {
    margin: 0 20px 0 12px;
    width: 18px;
  }
}
.button {
  display: flex;
  align-items: center;
  color: $body-color;
  background: #1a1b3c;
  border: none;
  padding: 2px 12px;
  border-radius: 4px;
  margin-right: 20px;
  svg {
    margin-left: 10px;
    width: 16px;
  }
}

.user-box {
  margin-top: 25px;
  display: flex;
  & + & {
    margin-top: 20px;
    .cards-wrapper {
      margin-left: 0;
      flex-grow: 1;
      max-width: none;
    }
    .today {
      margin-left: 30px;
    }
    .cards-header {
      padding: 20px 30px;
      justify-content: space-between;
    }
  }
  .cards-header,
  .cards-view {
    display: flex;
    align-items: center;
    justify-content: center;
    svg {
      width: 24px;
    }
    .title {
      margin: 0 16px;
      font-size: 15px;
    }
    &-date svg {
      width: 30px;
    }
    .cards-button {
      background: #15193c;
      padding: 10px 16px;
      margin-right: 0;
      svg {
        width: 16px;
        margin: 0 2px 0 0;
      }
    }
    .date-wrapper {
      display: flex;
      align-items: center;
      margin: auto;
      .title {
        margin: 0 16px;
      }
    }
  }
}

.cards-view > svg {
  margin-right: 12px;
}

.today {
  position: relative;
  &:before {
    content: "";
    position: absolute;
    width: 5px;
    height: 5px;
    background-color: #ef415c;
    bottom: -8px;
    right: 50%;
    border-radius: 50%;
  }
}
.card {
  background-color: white;
  box-shadow: 2px 1px 5px 2px rgba(0, 0, 0, 0.1);
  padding: 40px 30px;
  border-radius: 6px;
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  max-height: 430px;
  width: 100%;
  .title {
    font-size: 16px;
    font-weight: 500;
  }
  .subtitle {
    font-size: 13px;
    line-height: 1.6em;
  }
  & + & {
    margin-left: 20px;
  }
}

.activity {
  max-width: 800px;
  .title {
    margin-bottom: 20px;
  }
  &-links {
    display: flex;
    align-items: center;
    margin-top: 30px;
    font-size: 15px;
  }
  &-link {
    padding-bottom: 10px;
    position: relative;
    cursor: pointer;
    transition: 0.3s;
    + .activity-link {
      margin-left: 25px;
      &:before {
        content: "";
        position: absolute;
        width: 5px;
        height: 5px;
        background-color: #ef415c;
        top: -2px;
        right: -8px;
        border-radius: 50%;
      }
      &:hover:after {
        content: "";
        position: absolute;
        width: 22px;
        height: 2px;
        background: #4255d4;
        left: 0;
        bottom: 0;
      }
      &:hover {
        color: #bebec4;
        -webkit-text-stroke: 0.3px;
      }
    }
    &.active {
      color: #bebec4;
      font-weight: 500;
      &:before {
        content: "";
        position: absolute;
        width: 22px;
        height: 2px;
        background: #4255d4;
        left: 0;
        bottom: 0;
      }
    }
  }
}

.destination {
  display: flex;
  align-items: center;
  margin-top: auto;
  &-card {
    background: rgb(26, 29, 58);
    background: linear-gradient(
            45deg,
            rgba(26, 29, 58, 1) 0%,
            rgba(33, 39, 82, 1) 100%
    );
    padding: 20px;
    width: 100%;
    border-radius: 6px;
    & + & {
      margin-left: 20px;
      background: rgb(26, 29, 58);
      background: linear-gradient(
              325deg,
              rgba(26, 29, 58, 1) 0%,
              rgba(33, 39, 82, 1) 100%
      );
    }
  }
  &-profile {
    display: flex;
    align-items: center;
    font-size: 14px;
    svg {
      width: 18px;
      flex-shrink: 0;
      margin-right: 8px;
    }
  }
  &-length {
    margin-left: auto;
    display: flex;
    align-items: center;
    font-size: 13px;
  }
  &-points {
    margin-top: 30px;
  }
}

.profile-img {
  width: 46px;
  height: 46px;
  object-fit: cover;
  border-radius: 50%;
  padding: 2px;
  border: 1px solid #bebec4;
  flex-shrink: 0;
}

.point {
  font-size: 14px;
  color: #bebec4;
  font-weight: 500;
}

.sub-point {
  font-size: 13px;
  margin-top: 4px;
}

.discount {
  max-width: 320px;
  width: 100%;
  .title {
    margin-bottom: 30px;
  }
  .subtitle {
    margin-bottom: 8px;
    &-count {
      font-size: 17px;
      color: #17a98a;
      font-weight: 500;
      & + .subtitle {
        margin-top: 20px;
      }
      &.dist {
        color: #e85471;
      }
    }
  }
  &-wrapper {
    display: flex;
  }
  &-chart {
    margin-left: auto;
  }
  &-profile {
    display: flex;
    align-items: center;
    margin-top: 40px;
  }
  &-img {
    width: 48px;
    height: 48px;
    border-radius: 50%;
    object-fit: cover;
    margin: 0 16px 0 12px;
  }
  &-name {
    font-weight: 500;
    font-size: 15px;
  }
  &-type {
    font-size: 13px;
    margin-top: 4px;
  }
}

.circle {
  width: 100px;
  height: 100px;
  border: 3px solid #5b5f78;
  border-radius: 50%;
  position: relative;
}

.pie {
  position: relative;
  width: 120px;
  height: 120px;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%) rotate(-90deg);
  svg circle {
    fill: transparent;
    stroke: #4356d6;
    stroke-width: 14;
    stroke-dasharray: 275;
    stroke-dashoffset: 235;
    animation: pieChart 3.8s linear forwards;
  }
}

@keyframes pieChart {
  100% {
    stroke-dashoffset: 0;
  }
}

.counter {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  color: #3e50c5;
  font-weight: 500;
}

.offer-button {
  background: #4255d4;
  padding: 14px;
  text-align: center;
  justify-content: center;
  margin-top: auto;
  margin-right: 0;
  color: #fff;
  font-size: 13px;
  cursor: pointer;
}

.cards {
  &-header {
    background-color: white;
    box-shadow: 2px 1px 5px 2px rgba(0, 0, 0, 0.1);
    border-radius: 6px 6px 0 0;
    padding: 20px 45px;
    font-size: 14px;
    font-weight: 500;
    &-date {
      display: flex;
      align-items: center;
      justify-content: space-between;
      svg {
        color: #4154d1;
        background: #14183c;
        border-radius: 50%;
        padding: 5px;
        width: 30px;
      }
    }
  }
  &-wrapper {
    margin-left: 20px;
    max-width: 305px;
    width: 100%;
  }
  &-hour {
    font-size: 26px;
    .am-pm {
      font-size: 15px;
      font-weight: 500;
    }
  }
  &.card {
    border-radius: 0 0 6px 6px;
    padding: 30px 20px;
    svg {
      width: 24px;
      margin-left: auto;
      color: #818394;
    }
  }
  &-head {
    display: flex;
    align-items: center;
  }
}

.degree {
  margin-top: 10px;
  font-size: 13px;
  display: flex;
  align-items: center;
  color: #818394;
  font-weight: 500;
  svg {
    width: 24px;
    margin-right: 12px;
  }
}

.items {
  display: flex;
  flex-wrap: wrap;
  margin-top: 16px;
  font-size: 13px;
  font-weight: 500;
  &.numbers {
    margin-top: 0;
  }
}

.item {
  flex: 0 1 calc(100% / 7);
  padding: 10px 5px;
  text-align: center;
  &.is-active {
    background: #4255d4;
    border-radius: 50%;
    color: #fff;
  }
  &.disable {
    color: #595b5b;
  }
}

.account {
  width: 100%;
  height: 180px;
  margin-top: auto;
  flex-grow: 0;
  position: relative;
  transition: 0.3s;
  cursor: pointer;
  transition: 0.3s;
  &:hover {
    transform: scale(1.02);
  }
  &:before {
    content: "";
    position: absolute;
    width: 24px;
    height: 24px;
    box-shadow: -15px 0 0 0 #ef8741;
    background: #ef415c;
    top: 20px;
    left: 42px;
    border-radius: 50%;
  }
  &-wrapper {
    max-width: 310px;
    width: 100%;
    display: flex;
    flex-direction: column;
    margin-left: 20px;
    align-items: center;
  }
  &-profile {
    margin: auto;
    position: relative;
    text-align: center;
    position: relative;
    img {
      width: 84px;
      height: 84px;
      border-radius: 50%;
      object-fit: cover;
      object-position: left;
      border: 3px solid #4255d3;
      padding: 5px;
    }
    .blob {
      position: absolute;
      border-radius: 50%;
      animation: fly 5.8s linear infinite alternate;
      &:nth-child(1) {
        width: 14px;
        height: 14px;
        top: 25px;
        left: -20px;
        background: #28327a;
        animation-delay: 0.9s;
      }
      &:nth-child(2) {
        width: 18px;
        height: 18px;
        background: #87344c;
        right: -20px;
        top: -20px;
        animation-delay: 0.2s;
      }
      &:nth-child(3) {
        width: 12px;
        height: 12px;
        background: #13645b;
        right: -35px;
        top: 50%;
        animation-delay: 1.8s;
      }
    }
  }
  &-name {
    margin: 20px 0 10px;
  }
  &-title {
    font-size: 14px;
  }
  &-cash {
    font-size: 22px;
    font-weight: 500;
    margin-bottom: 6px;
    padding-top: 16px;
    position: relative;
    &:before {
      content: "";
      position: absolute;
      width: 5px;
      height: 5px;
      background: $body-color;
      right: 10px;
      border-radius: 50%;
      box-shadow: -10px 0 0 0 $body-color, 10px 0 0 0 $body-color;
      top: 24px;
    }
  }
  &-income {
    font-size: 14px;
  }
  &-iban {
    margin-top: auto;
    font-weight: 500;
  }
}

.time {
  height: 24px;
  padding: 4px 8px;
  border-radius: 4px;
  background-color: #9e5924;
  font-size: 13px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  &.is-wait {
    background: #4255d3;
  }
}

.table .status {
  color: #1aa385;
  display: inline-flex;
  align-items: center;
  svg {
    margin-right: 6px;
    width: 22px;
    height: 22px;
    padding: 3px;
    border-radius: 4px;
    background-color: #142940;
    color: currentColor;
  }
  &.is-red {
    color: #d14b69;
    svg {
      background: #2e2142;
      color: currentcolor;
    }
  }
  &.is-wait {
    color: #3e4ec2;
    position: relative;
    &:before {
      width: 22px;
      height: 22px;
      position: absolute;
      left: 0;
      top: 0;
      content: "";
      background: #1a214d;
      border-radius: 4px;
    }
    svg {
      background-color: transparent;
      color: currentcolor;
      animation: turn 2s linear infinite both;
    }
  }
}
@keyframes turn {
  100% {
    transform: rotate(1turn);
  }
}

@keyframes fly {
  40% {
    transform: translate(-6px, -6px);
  }
  60% {
    transform: translate(-12px, -2px);
  }
  100% {
    transform: translate(0px, 0px);
  }
}

.transection {
  flex-shrink: 0;
  max-width: 310px;
  width: 100%;
  margin-left: 20px;
  &-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    font-weight: 500;
  }
  .is-wait {
    color: #3e4ec2;
  }
}

.credit {
  &-wrapper {
    margin-top: auto;
    display: flex;
    align-items: center;
    font-size: 14px;
    font-weight: 500;
    svg {
      width: 38px;
      padding: 3px;
      border-radius: 6px;
      margin-right: 12px;
    }
    path {
      width: 24px;
    }
  }
  &-money {
    margin-left: auto;
    &.is-active {
      color: #1aa385;
    }
    &.is-cancel {
      color: #d14b69;
    }
    .is-wait {
      color: #3e4ec2;
    }
  }
  &-status {
    font-size: 13px;
    font-weight: normal;
  }
  &-type {
    color: #a9a9a9;
    margin-bottom: 6px;
  }
}

@media screen and (max-width: 1500px) {
  .wrapper {
    max-width: 1200px;
  }
  .activity {
    width: 49%;
    max-width: none;
  }
  .discount {
    width: 48%;
    max-width: none;
    height: 100%;
  }
  .first-box {
    flex-wrap: wrap;
    .cards-wrapper {
      width: 67%;
      max-width: none;
      margin: 20px 0;
      .item.is-active {
        background: none;
        color: inherit;
      }
    }
    .account-wrapper {
      max-width: none;
      width: calc(33% - 20px);
      margin: 20px 0 20px 20px;
    }
  }
  .second-box {
    flex-wrap: wrap;
    .cards-wrapper {
      margin-top: 40px;
      width: 66%;
    }
  }
  .transection {
    margin-top: 40px;
    max-width: none;
    width: 30%;
  }
}

@media screen and (max-width: 1200px) {
  .time {
    display: none;
  }
}

@media screen and (max-width: 1060px) {
  .user-info {
    .button,
    .hour {
      display: none;
    }
  }
}

@media screen and (max-width: 1020px) {
  .user-box .cards-view {
    display: none;
  }
  .user-box .cards-header .cards-button {
    display: none;
  }
  .cards-header-date {
    margin: auto;
  }
}

@media screen and (max-width: 930px) {
  .second-box .cards-wrapper {
    width: 100%;
  }
  .transection {
    width: 100%;
    margin-left: 0;
    height: 100%;
    margin-top: 20px;
  }
  .header-link {
    display: none;
  }
  .user-info .profile {
    margin-right: 0;
  }
}

@media screen and (max-width: 850px) {
  .activity-card,
  .discount {
    width: 100%;
  }
  .user-box .discount {
    margin-left: 0;
    margin-top: 20px;
  }
  .left-side {
    display: none;
  }
  .main-container {
    padding-left: 25px;
  }
  .activity-links,
  .destination {
    margin-top: 20px;
  }
}

@media screen and (max-width: 720px) {
  .first-box .account-wrapper {
    display: none;
  }
  .first-box .cards-wrapper {
    width: 100%;
  }
  .second-box .cards.card {
    overflow-y: auto;
  }
}

@media screen and (max-width: 420px) {
  .destination-card + .destination-card {
    display: none;
  }
}

.user-box > * {
  animation: top 1s var(--delay) both;
}


/*银行卡*/

.flip-card {
  background-color: transparent;
  width: 300px;
  height: 200px;
  perspective: 1000px;
  color: white;
}

.heading_8264 {
  position: absolute;
  letter-spacing: .2em;
  font-size: 0.5em;
  top: 2em;
  left: 18.6em;
}

.bank-logo {
  position: absolute;
  top: 8.8em;
  left: 14.7em;
}

.chip {
  position: absolute;
  top: 2.3em;
  left: 1.5em;
}

.contactless {
  position: absolute;
  top: 3.5em;
  left: 15.4em;
}

.number {
  position: absolute;
  font-weight: bold;
  font-size: 1.1em;
  top: 4.7em;
  left: 1.6em;
}

.valid_thru {
  position: absolute;
  font-weight: bold;
  top: 735.8em;
  font-size: .01em;
  left: 180.3em;
}

.date_8264 {
  position: absolute;
  font-weight: bold;
  font-size: 0.5em;
  top: 15.6em;
  left: 3.7em;
}

.name {
  position: absolute;
  font-weight: bold;
  font-size: 0.5em;
  top: 19.1em;
  left: 4em;
}

.strip {
  position: absolute;
  background-color: black;
  width: 15em;
  height: 1.5em;
  top: 2.4em;
  background: repeating-linear-gradient(
          45deg,
          #303030,
          #303030 10px,
          #202020 10px,
          #202020 20px
  );
}

.mstrip {
  position: absolute;
  background-color: rgb(255, 255, 255);
  width: 8em;
  height: 0.8em;
  top: 5em;
  left: .8em;
  border-radius: 2.5px;
}

.sstrip {
  position: absolute;
  background-color: rgb(255, 255, 255);
  width: 4.1em;
  height: 0.8em;
  top: 5em;
  left: 10em;
  border-radius: 2.5px;
}

.code {
  font-weight: bold;
  text-align: center;
  margin: .2em;
  color: black;
}

.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.8s;
  transform-style: preserve-3d;
}

.flip-card:hover .flip-card-inner {
  transform: rotateY(180deg);
}

.flip-card-front, .flip-card-back {
  box-shadow: 0 8px 14px 0 rgba(0,0,0,0.2);
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  border-radius: 1rem;
}

.flip-card-front {
  box-shadow: rgba(0, 0, 0, 0.4) 0px 2px 2px, rgba(0, 0, 0, 0.3) 0px 7px 13px -3px, rgba(0, 0, 0, 0.2) 0px -1px 0px inset;
  background-color: #171717;
}

.flip-card-back {
  box-shadow: rgba(0, 0, 0, 0.4) 0px 2px 2px, rgba(0, 0, 0, 0.3) 0px 7px 13px -3px, rgba(0, 0, 0, 0.2) 0px -1px 0px inset;
  background-color: #171717;
  transform: rotateY(180deg);
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

.bind-form {
  padding: 10px 20px;
}

.bind-form > .vs-input {
  margin-bottom: 15px;
}

.statistics {
  display: flex;
  justify-content: space-evenly;
}

.statistic {
  display: flex;
  align-items: center;
  margin-right: 20px;
}

.divider {
  width: 1px; /* 竖线的宽度 */
  height: 100%; /* 竖线的高度，这里设为与父元素高度相同 */
  background-color: #000000; /* 竖线的颜色 */
  margin-left: 50px;
}
.value {
  font-size: 24px;
  font-weight: bold;
}

.label {
  margin-top: 10px;
}


.plan-card {
  background: #fff;
  width: 15rem;
  padding-left: 2rem;
  padding-right: 2rem;
  padding-top: 10px;
  padding-bottom: 20px;
  border-radius: 10px;
  border-bottom: 4px solid #000446;
  box-shadow: 0 6px 30px rgba(207, 212, 222, 0.3);
  font-family: "Poppins", sans-serif;
}

.plan-card h2 {
  margin-bottom: 15px;
  font-size: 27px;
  font-weight: 600;
}

.plan-card h2 span {
  display: block;
  margin-top: -4px;
  color: #4d4d4d;
  font-size: 12px;
  font-weight: 400;
}

.etiquet-price {
  position: relative;
  background: #fdbd4a;
  width: 14.46rem;
  margin-left: -0.65rem;
  padding: .2rem 1.2rem;
  border-radius: 5px 0 0 5px;
}

.etiquet-price p {
  margin: 0;
  padding-top: .4rem;
  display: flex;
  font-size: 1.9rem;
  font-weight: 500;
}

.etiquet-price p:before {
  content: "￥";
  margin-right: 5px;
  font-size: 15px;
  font-weight: 300;
}

.etiquet-price p:after {
  margin-left: 5px;
  font-size: 15px;
  font-weight: 300;
}

.etiquet-price div {
  position: absolute;
  bottom: -23px;
  right: 0px;
  width: 0;
  height: 0;
  border-top: 13px solid #c58102;
  border-bottom: 10px solid transparent;
  border-right: 13px solid transparent;
  z-index: -6;
}

</style>
