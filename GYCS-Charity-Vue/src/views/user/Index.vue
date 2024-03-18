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
        <div>
          <vs-table style="width: 900px;margin-top: 20px">
            <template #thead>
              <vs-tr>
                <vs-th> Name </vs-th>
                <vs-th> Email </vs-th>
                <vs-th> Id </vs-th>
              </vs-tr>
            </template>
            <template #tbody>
              <vs-tr
                  v-for="(tr, i) in getPage(totalUser, page, pageSize)"
                  :key="i"
                  :data="tr"
              >
                <vs-td>
                  {{ tr.name }}
                </vs-td>
                <vs-td>
                  {{ tr.email }}
                </vs-td>
                <vs-td>
                  {{ tr.id }}
                </vs-td>
              </vs-tr>
            </template>
            <template #footer>
              <vs-pagination
                  v-model:current-page="page"
                  v-model:page-size="pageSize"
                  :page-sizes="[3, 5, 7]"
                  :total="totalUser.length"
              />
            </template>
          </vs-table>
        </div>
      </div>
      <div class="profile-down-right">
        <vs-card>
          <template #text>
            <div style="display: flex;justify-content: space-between;padding: 50px 30px;margin: 20px 10px;">
              <div class="card__profile">
                <p>名称：</p>
                <p>手机：</p>
                <p>邮箱：</p>
                <p>类型：</p>
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
            <div style="margin-top: -20px">
              <ul class="example-2">
                <li class="icon-content">
                  <a
                      href="#"
                      aria-label="Spotify"
                      data-social="spotify"
                  >
                    <div class="filled"></div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-credit-card-2-back-fill" viewBox="0 0 16 16">
                      <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v5H0V4zm11.5 1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h2a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-2zM0 11v1a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2v-1H0z"/>
                    </svg>
                  </a>
                  <div class="tooltip">200</div>
                </li>
                <li class="icon-content">
                  <a
                      href="#"
                      aria-label="Pinterest"
                      data-social="pinterest"
                  >
                    <div class="filled"></div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plug-fill" viewBox="0 0 16 16">
                      <path d="M6 0a.5.5 0 0 1 .5.5V3h3V.5a.5.5 0 0 1 1 0V3h1a.5.5 0 0 1 .5.5v3A3.5 3.5 0 0 1 8.5 10c-.002.434-.01.845-.04 1.22-.041.514-.126 1.003-.317 1.424a2.083 2.083 0 0 1-.97 1.028C6.725 13.9 6.169 14 5.5 14c-.998 0-1.61.33-1.974.718A1.922 1.922 0 0 0 3 16H2c0-.616.232-1.367.797-1.968C3.374 13.42 4.261 13 5.5 13c.581 0 .962-.088 1.218-.219.241-.123.4-.3.514-.55.121-.266.193-.621.23-1.09.027-.34.035-.718.037-1.141A3.5 3.5 0 0 1 4 6.5v-3a.5.5 0 0 1 .5-.5h1V.5A.5.5 0 0 1 6 0z"/>
                    </svg>
                  </a>
                  <div class="tooltip">30</div>
                </li>
                <li class="icon-content">
                  <a
                      href="#"
                      aria-label="Dribbble"
                      data-social="dribbble"
                  >
                    <div class="filled"></div>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box2-heart-fill" viewBox="0 0 16 16">
                      <path d="M3.75 0a1 1 0 0 0-.8.4L.1 4.2a.5.5 0 0 0-.1.3V15a1 1 0 0 0 1 1h14a1 1 0 0 0 1-1V4.5a.5.5 0 0 0-.1-.3L13.05.4a1 1 0 0 0-.8-.4h-8.5ZM8.5 4h6l.5.667V5H1v-.333L1.5 4h6V1h1v3ZM8 7.993c1.664-1.711 5.825 1.283 0 5.132-5.825-3.85-1.664-6.843 0-5.132Z"/>
                    </svg>
                  </a>
                  <div class="tooltip">3</div>
                </li>
                <li class="icon-content">
                  <a
                      href="#"
                      aria-label="Telegram"
                      data-social="telegram"
                  >
                    <div class="filled"></div>
                    <svg version="1.1" viewBox="0 0 100 100">
                      <path
                          d="M95,9.9c-1.3-1.1-3.4-1.2-7-0.1c0,0,0,0,0,0c-2.5,0.8-24.7,9.2-44.3,17.3c-17.6,7.3-31.9,13.7-33.6,14.5  c-1.9,0.6-6,2.4-6.2,5.2c-0.1,1.8,1.4,3.4,4.3,4.7c3.1,1.6,16.8,6.2,19.7,7.1c1,3.4,6.9,23.3,7.2,24.5c0.4,1.8,1.6,2.8,2.2,3.2  c0.1,0.1,0.3,0.3,0.5,0.4c0.3,0.2,0.7,0.3,1.2,0.3c0.7,0,1.5-0.3,2.2-0.8c3.7-3,10.1-9.7,11.9-11.6c7.9,6.2,16.5,13.1,17.3,13.9  c0,0,0.1,0.1,0.1,0.1c1.9,1.6,3.9,2.5,5.7,2.5c0.6,0,1.2-0.1,1.8-0.3c2.1-0.7,3.6-2.7,4.1-5.4c0-0.1,0.1-0.5,0.3-1.2  c3.4-14.8,6.1-27.8,8.3-38.7c2.1-10.7,3.8-21.2,4.8-26.8c0.2-1.4,0.4-2.5,0.5-3.2C96.3,13.5,96.5,11.2,95,9.9z M30,58.3l47.7-31.6  c0.1-0.1,0.3-0.2,0.4-0.3c0,0,0,0,0,0c0.1,0,0.1-0.1,0.2-0.1c0.1,0,0.1,0,0.2-0.1c-0.1,0.1-0.2,0.4-0.4,0.6L66,38.1  c-8.4,7.7-19.4,17.8-26.7,24.4c0,0,0,0,0,0.1c0,0-0.1,0.1-0.1,0.1c0,0,0,0.1-0.1,0.1c0,0.1,0,0.1-0.1,0.2c0,0,0,0.1,0,0.1  c0,0,0,0,0,0.1c-0.5,5.6-1.4,15.2-1.8,19.5c0,0,0,0,0-0.1C36.8,81.4,31.2,62.3,30,58.3z"
                          fill="currentColor"
                      ></path>
                    </svg>
                  </a>
                  <div class="tooltip">Telegram</div>
                </li>
              </ul>

            </div>
          </template>
        </vs-card>
      </div>
    </div>
  </div>
</template>
<script setup>


import {onMounted, reactive, ref} from "vue";
import { getPage } from 'vuesax-alpha'
import BankCard from "@/components/BankCard/BankCard.vue";
import Avatar from "@/components/Avatar/Avatar.vue";
import {getAllLogisticAddress} from "@/api/charity/logistic.js";
const activeTab = ref(0);
const tabs = reactive([
  { id: '1', title: '银行卡信息' },
  { id: '2', title: '个人信息' },
])
const selectTab = (index) => {
  activeTab.value = index;
};



const page = ref(1)
const pageSize = ref(3)
const totalUser = reactive([
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
])

onMounted(() => {
  getAllLogisticAddress().then(res => {
    console.log(res)
  })
})
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
  height: 380px;
}
:deep(.vs-card:hover) {
  box-shadow: 0 5px 10px 0 rgba(0,0,0,var(--vs-shadow-opacity)) !important;
}

.card__profile > p {
  margin-bottom: 10px;
  font-size: 17px;
  font-weight: bold;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}





ul {
  list-style: none;
}

.example-2 {
  display: flex;
  justify-content: center;
  align-items: center;
}
.example-2 .icon-content {
  margin: 0 10px;
  position: relative;
}
.example-2 .icon-content .tooltip {
  position: absolute;
  top: -30px;
  left: 50%;
  transform: translateX(-50%);
  color: #fff;
  padding: 6px 10px;
  border-radius: 15px;
  opacity: 0;
  visibility: hidden;
  font-size: 14px;
  transition: all 0.3s ease;
}
.example-2 .icon-content:hover .tooltip {
  opacity: 1;
  visibility: visible;
  top: -50px;
}
.example-2 .icon-content a {
  position: relative;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50px;
  height: 50px;
  border-radius: 20%;
  color: #4d4d4d;
  background-color: #ffff;
  transition: all 0.3s ease-in-out;
}
.example-2 .icon-content a:hover {
  /*box-shadow: 3px 2px 45px 0px rgb(0 0 0 / 50%);*/
}
.example-2 .icon-content a svg {
  position: relative;
  z-index: 1;
  width: 30px;
  height: 30px;
}
.example-2 .icon-content a:hover {
  color: white;
}
.example-2 .icon-content a .filled {
  position: absolute;
  top: auto;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 0;
  background-color: #000;
  transition: all 0.3s ease-in-out;
}
.example-2 .icon-content a:hover .filled {
  height: 100%;
}
.example-2 .icon-content a[data-social="spotify"] .filled,
.example-2 .icon-content a[data-social="spotify"] ~ .tooltip {
  background-color: #1db954;
}
.example-2 .icon-content a[data-social="pinterest"] .filled,
.example-2 .icon-content a[data-social="pinterest"] ~ .tooltip {
  background-color: #bd081c;
}
.example-2 .icon-content a[data-social="dribbble"] .filled,
.example-2 .icon-content a[data-social="dribbble"] ~ .tooltip {
  background-color: #ea4c89;
}
.example-2 .icon-content a[data-social="telegram"] .filled,
.example-2 .icon-content a[data-social="telegram"] ~ .tooltip {
  background-color: #0088cc;
}

</style>
