<template>
  <div>
    <vs-alert type="relief">
      <template #title>公益积分排行榜介绍</template>
      公益积分排行榜是一个权威的榜单，旨在表彰和激励社会公益行为，展示和推广公益领域的优秀表现和成就，促进社会的公益事业发展。通过公正评选机制，公布并表彰在公益领域取得突出成就的个人和组织，为社会的进步与发展贡献力量。
    </vs-alert>
  </div>
  <div class="rank-main">
    <div>
      <Badge/>
    </div>
    <div>
      <div class="rank-table">
        <vs-table>
          <template #thead>
            <vs-tr>
              <vs-th> Name </vs-th>
              <vs-th> Email </vs-th>
              <vs-th> Id </vs-th>
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

              <template #expand>
                <div class="con-content">
                  <div>
                    <vs-avatar>
                      <img :src="`/avatars/avatar-${i + 1}.png`" alt="" />
                    </vs-avatar>
                    <p>
                      {{ tr.name }}
                    </p>
                  </div>
                  <div>
                    <vs-button type="flat" icon>
                      <i class="bx bx-lock-open-alt" />
                    </vs-button>
                    <vs-button type="flat" icon> Send Email </vs-button>
                    <vs-button type="border" color="danger">
                      Remove User
                    </vs-button>
                  </div>
                </div>
              </template>
            </vs-tr>
          </template>
        </vs-table>
      </div>
    </div>
  </div>
</template>

<script setup>

import Badge from "@/components/Badge/Badge.vue";
import {reactive} from "vue";
const users = reactive([
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
</script>

<style scoped>
.rank-main {
  display: flex;
  flex: 1;
}

.rank-table {
  width: 1320px;
  margin: 10px 20px;
}

/*修改card的深度属性*/

:deep(.vs-card) {
  width: 350px;
  height: 380px;
}
:deep(.vs-card:hover) {
  box-shadow: 0 5px 10px 0 rgba(0,0,0,var(--vs-shadow-opacity)) !important;
}
</style>
