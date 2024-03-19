<template>
  <div class="transfer_container">
    <div class="modal">
      <div class="form">
        <div class="banner"></div>
        <label class="title">通过银行卡转账参与公益慈善活动</label>
        <p class="description">所有的转账记录都将实现数据加密进行存储在区块链链中，保证您的数据是真实可信可溯源的。</p>
        <div class="benefits">
          <div class="benefit-form">
            <vs-input v-model="form.donorName" state="primary" placeholder="捐款人姓名"  label-float style="width: 300px">
              <template #icon>
                <vs-icon><UserBold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.donorCardId" state="primary" placeholder="捐款人身份证" label-float style="width: 300px">
              <template #icon>
                <vs-icon><ShieldSearchBold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.donorBank" state="primary" placeholder="捐款人开户银行" label-float style="width: 300px">
              <template #icon>
                <vs-icon><BankBold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.donorAccount" state="primary" placeholder="捐款人开户账户" label-float style="width: 300px">
              <template #icon>
                <vs-icon><KeyBold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.donorWorkUnit" state="primary" placeholder="工作单位" label-float style="width: 300px">
              <template #icon>
                <vs-icon><Buildings2Bold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.donorAmount" state="primary" placeholder="捐款金额" label-float style="width: 300px">
              <template #icon>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-currency-yen" viewBox="0 0 16 16">
                  <path d="M8.75 14v-2.629h2.446v-.967H8.75v-1.31h2.445v-.967H9.128L12.5 2h-1.699L8.047 7.327h-.086L5.207 2H3.5l3.363 6.127H4.778v.968H7.25v1.31H4.78v.966h2.47V14h1.502z"/>
                </svg>
              </template>
            </vs-input>
            <vs-input v-model="form.donorAmountCN" state="primary" placeholder="捐款金额大写(￥)" label-float style="width: 300px">
              <template #icon>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-currency-yen" viewBox="0 0 16 16">
                  <path d="M8.75 14v-2.629h2.446v-.967H8.75v-1.31h2.445v-.967H9.128L12.5 2h-1.699L8.047 7.327h-.086L5.207 2H3.5l3.363 6.127H4.778v.968H7.25v1.31H4.78v.966h2.47V14h1.502z"/>
                </svg>
              </template>
            </vs-input>
          </div>
          <div style="margin-top: 15%;margin-left: 5%;margin-right: 5%;">
            <div class="flip-card">
              <div class="flip-card-inner">
                <div class="flip-card-front">
                  <p class="heading_8264">GYCS-SZGY</p>
                  <svg class="logo" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="36" height="36" viewBox="0 0 48 48">
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
                  <p class="number">0x0ca652921509414a799a07021432</p>
                  <p class="valid_thru">VALID THRU</p>
                  <p class="date_8264">1 2 / 2 4</p>
                  <p class="name">BRUCE WAYNE</p>
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
          <div class="benefit-form">
            <vs-input v-model="form.aidedName" state="primary" placeholder="受助人姓名" label-float style="width: 300px">
              <template #icon>
                <vs-icon><UserBold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.aideCardId" state="primary" placeholder="受助人身份证号" label-float style="width: 300px">
              <template #icon>
                <vs-icon><ShieldSearchBold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.aideBank" state="primary" placeholder="受助人开户银行" label-float style="width: 300px">
              <template #icon>
                <vs-icon><BankBold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.aideAccount" state="primary" placeholder="受助人开户账户" label-float style="width: 300px">
              <template #icon>
                <vs-icon><KeyBold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.aideWorkUnit" state="primary" placeholder="受助人工作单位" label-float style="width: 300px">
              <template #icon>
                <vs-icon><Buildings2Bold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.donorDescription" state="primary" placeholder="捐款描述" label-float style="width: 300px">
              <template #icon>
                <vs-icon><NotificationStatusBold /></vs-icon>
              </template>
            </vs-input>
            <vs-input v-model="form.donorRemarks" state="primary" placeholder="捐款备注" label-float style="width: 300px">
              <template #icon>
                <vs-icon><MessageEditBold /></vs-icon>
              </template>
            </vs-input>
          </div>
        </div>
        <div class="modal--footer">
          <vs-button type="relief" color="danger"> 取消 </vs-button>
          <vs-button  color="success" @click="handleTransfer"> 确定 </vs-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {
  UserBold,
  ShieldSearchBold,
  BankBold,
  KeyBold,
  Buildings2Bold,
  NotificationStatusBold,
  MessageEditBold
} from '@vuesax-alpha/icons-vue'
import {onMounted, reactive, ref, toRefs} from "vue";
import {useRouter} from "vue-router";
import {bankToBankTransfer} from "@/api/charity/raiseFund.js";
import {VsLoadingFn, VsNotification} from "vuesax-alpha";

const route = useRouter()
const raiseId = ref(0)

const data = reactive({
  form: {
    "raiseId": 0,
    "donorName": "张三",
    "donorCardId": "441622200305132857",
    "donorWorkUnit": "深圳",
    "donorBank": "平安银行",
    "donorAccount": "1111111111111111111111111111111",
    "donorAmount": 2000,
    "donorAmountCN": "贰仟元整",
    "donorDescription": "个人捐款",
    "donorRemarks": "捐款200元整",
    "aidedName": "李四",
    "aideCardId": "441622200305132859",
    "aideWorkUnit": "北京",
    "aideBank": "招商银行",
    "aideAccount": "22222222222222222222222222222"
  }
});

const {form} = toRefs(data)

onMounted(() => {
  raiseId.value = route.currentRoute.value.query.raiseId;
  form.value.raiseId = raiseId.value

  const loadingInstance = VsLoadingFn()
  setTimeout(() => {
    loadingInstance.close()
  }, 1000)
})

function handleTransfer(){
  bankToBankTransfer(form.value).then(res => {
    if (res.code == 200) {
      openNotification("success","转账通知","银行卡转账成功，记录已上链")
      form.value = {}
    }
  })
}

const openNotification = (color,title,msg) => {
  VsNotification({
    color,
    position: 'top-left',
    title: title,
    content: msg,

  })
}
</script>


<style scoped>

.transfer_container {
  display: flex;
  justify-content: center;
  padding: 30px 20px;
}
.modal {
  width: 70%;
  background: linear-gradient(180deg, #b4e9fd 0%, #FFFFFF 30.21%);
  box-shadow: 0px 187px 75px rgba(0, 0, 0, 0.01), 0px 105px 63px rgba(0, 0, 0, 0.05), 0px 47px 47px rgba(0, 0, 0, 0.09), 0px 12px 26px rgba(0, 0, 0, 0.1), 0px 0px 0px rgba(0, 0, 0, 0.1);
  border-radius: 16px;
}

.banner {
  width: 100%;
  height: 30px;
  margin: 0;
  background-size: 100%;
  background-repeat: no-repeat;
}

.title {
  font-weight: 700;
  font-size: 17px;
  line-height: 21px;
  text-align: center;
  color: #2B2B2F;
  margin-bottom: 15px;
}

.description {
  max-width: 80%;
  margin: auto;
  font-weight: 600;
  font-size: 11px;
  line-height: 16px;
  text-align: center;
  color: #5F5D6B;
}

.benefits {
  padding: 20px;
  display: flex;
  flex: 1;
  gap: 20px;
  justify-content: center;
}
.benefit-form > .vs-input {
  margin-bottom: 15px;
}

.modal--footer {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  border-top: 1px solid #ebebec;
}

.form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

/*银行卡样式*/

.flip-card {
  background-color: transparent;
  width: 240px;
  height: 154px;
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

.logo {
  position: absolute;
  top: 6.8em;
  left: 11.7em;
}

.chip {
  position: absolute;
  top: 2.3em;
  left: 1.5em;
}

.contactless {
  position: absolute;
  top: 3.5em;
  left: 12.4em;
}

.number {
  position: absolute;
  font-weight: bold;
  font-size: .6em;
  top: 8.3em;
  left: 1.6em;
}

.valid_thru {
  position: absolute;
  font-weight: bold;
  top: 635.8em;
  font-size: .01em;
  left: 140.3em;
}

.date_8264 {
  position: absolute;
  font-weight: bold;
  font-size: 0.5em;
  top: 13.6em;
  left: 3.2em;
}

.name {
  position: absolute;
  font-weight: bold;
  font-size: 0.5em;
  top: 16.1em;
  left: 2em;
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
</style>
