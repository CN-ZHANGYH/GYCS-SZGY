<template>
  <div style="display: flex;flex-direction: column">
    <!--  搜索框  -->
    <div style="font-size: 20px;font-weight: bold;flex: 1;display: flex;margin-bottom: 20px;justify-content: center">
      <vs-input v-model="value1" placeholder="根据关键词搜索你想要捐赠物资的灾区" style="width: 600px;height: 50px">
        <template #icon>
          <vs-icon><SearchStatusBold /></vs-icon>
        </template>
      </vs-input>
      <vs-button style="height: 48px;width: 80px;" >
        搜索
      </vs-button>
    </div>
    <div style="margin-left: 5%">
      <vs-row style="padding: 5px 15%;">
        <vs-col v-for="_ in 6" :key="_" :sm="4" style="margin-bottom: 10px">
          <div class="disaster-container">
            <vs-card>
              <template #title>
                <h3>Trendy clothing</h3>
              </template>
              <template #img>
                <img src="../../../assets/matter/one.png" alt="" style="width: 270px;height: 160px"/>
              </template>
              <template #text>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.</p>
                <div style="display: flex;justify-content: center;margin-top: 20px">
                  <vs-button style="width: 200px" @click="donationMaterials()">
                    捐赠物资
                  </vs-button>
                </div>
              </template>
              <template #interactions>
                <vs-button color="danger" icon>
                  <vs-icon><HeartBold /></vs-icon>
                </vs-button>
                <vs-button class="btn-chat" type="shadow">
                  <vs-icon><EyeBold /></vs-icon>
                  <span class="span" @click="isDialogOpen = true"> 查看 </span>
                </vs-button>
              </template>
            </vs-card>
          </div>
        </vs-col>
      </vs-row>
    </div>
    <div class="con-pagination">
      <vs-pagination
          v-model:current-page="page"
          :layout="['total', 'prev', 'pager', 'next', 'jumper', 'sizes']"
          :total="50"
      />
    </div>
  </div>

  <vs-dialog v-model="isDialogOpen" scroll lock-scroll not-close auto-width>
    <template #header>
      <h3>Introduction</h3>
    </template>
    <div class="con-content-scroll">
      <h4>Whats is Vuesax?</h4>
      <p>
        Vuesax (pronounced / vjusacksː /, as view sacks) is a framework of UI
        components created with Vuejs to make projects easily and with a
        unique and pleasant style, vuesax is created from scratch and designed
        for all types of developers from the frontend lover to the backend who
        wants to easily create their visual approach to the end-user We are
        focused on streamlining the work of the programmer by giving
        components created in their entirety and with independent
        customization and very easy to implement, so creativity is in our
        hands but we do not neglect that each project is different both
        visually and in its ecosystem Vuesax does not have a design line such
        as other component frameworks based on Material Design, we believe
        that there are already emaciated frameworks that look visually and in
        UI / UX and we don't want to be one more of the bunch, apart from that
        we love to create and design new experiences and surprise you with new
        elements or details that we can only do by being visually free.
      </p>

      <h4>Why Vuesax?</h4>

      <p>
        Vuesax is a relatively new framework with a refreshing design and in
        the latest trends, vuesax based on vuejs which means that we go hand
        in hand with one of the most popular javascript frameworks in the
        world and with a huge community with which you will have all the help
        and documentation to create and make your project
        <br />
        <br />
        - Vuesax, unlike many frameworks, is designed from scratch and we are
        not anchored to any design line, this is something great since your
        project is going to be unique and very different from the others

        <br />
        <br />
        - We are focused on the quick and easy creation of projects giving a
        beautiful visual line but without forgetting the personalization and
        independence of the developer

        <br />
        <br />
        - Vuesax uses native css variables for better customization and
        production changes such as changing to dark theme or changing the main
        color of the entire application with few javascript lines
        <br />
        <br />

        - Vuesax is a frame designed to have a great visual impact and that is
        always in trend with respect to design.
        <br />
        <br />

        - An open-source community to create, improve and correct any
        component or function.
        <br />
        <br />

        - Independent components to avoid importing unnecessary code.
        <br />
        <br />

        - Markdown documents for better sustainability.
        <br />
        <br />

        - and much more.
      </p>
    </div>
  </vs-dialog>
</template>

<script setup>
import {
  EyeBold,
  HeartBold,
  SearchStatusBold
} from '@vuesax-alpha/icons-vue'
import {ref} from "vue";
import router from "@/router/index.js";

const isDialogOpen = ref(false)
function donationMaterials(){
  router.push({
    name: "materials"
  })
}

</script>

<style lang="scss" scoped>
.disaster-container {
  padding: 5px 5px;
}

:deep(.vs-card) {
  width: 270px;
  height: 330px;
}


:deep(.vs-card:hover) {
  box-shadow: 0 5px 10px 0 rgba(0,0,0,var(--vs-shadow-opacity)) !important;
}

.con-pagination {
  display: flex;
  justify-content: center;
  margin-top: 5px;
}

.con-content-scroll {
  p {
    margin: 20px 0px;
    position: relative;
    display: block;
    font-size: 0.9rem;
    width: 100%;
  }
}
</style>
