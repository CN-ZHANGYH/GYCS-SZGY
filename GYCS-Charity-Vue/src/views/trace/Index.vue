<template>
  <div class="trace_container">
    <div style="display: flex;flex: 1;justify-content: center;align-items: center">
      <div>
        <vs-input v-model="value1" placeholder="请输入身份证号码查询公益溯源信息" style="width: 600px;height: 50px">
          <template #icon>
            <vs-icon><SearchFavorite1Bold /></vs-icon>
          </template>
        </vs-input>
      </div>
      <div>
        <vs-button type="relief" color="success" style="width: 80px;height: 45px">搜索</vs-button>
      </div>
    </div>
    <div class="trace_table">
      <div class="table">
        <vs-table style="width: 1100px">
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
      <div class="table">
        <vs-table style="width: 1100px">
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
  </div>
</template>

<script setup>
import {
  SearchFavorite1Bold
} from '@vuesax-alpha/icons-vue'
import {reactive, ref} from "vue";
import { getPage } from 'vuesax-alpha'
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
</script>

<style scoped>
.trace_container {
  display: flex;
  flex-direction: column;
}

.trace_table {
  margin: 30px 10px;
  display: flex;
  flex-direction: column;
}

.trace_table > .table {
  display: flex;
  justify-content: center;
}

:deep(.vs-table) {
  margin-top: 20px;
}
</style>
