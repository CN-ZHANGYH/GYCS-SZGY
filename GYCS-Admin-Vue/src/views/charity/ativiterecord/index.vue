<template>
  <div class="app-container">
    <div v-for="(activite,index) in activitieRecordList" :key="index">
      <div style="width: 360px;height: 450px">
        <div class="card-container">
          <div class="circle"></div>
          <div class="circle"></div>
          <div class="card-inner">
            <div class="card">
              <div class="top">
                <div style="overflow: hidden">
                  <img src="@/assets/images/img.png" style="width: 100%;height: 100%;border-top-right-radius: 15px;border-top-left-radius: 15px">
                </div>
                <div class="title">
                  <div>{{activite.title}}</div>
                  <div style="display: flex;justify-content: space-between;margin-top: 15px">
                    <button class="button">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 36 24">
                        <path d="m18 0 8 12 10-8-4 20H4L0 4l10 8 8-12z"></path>
                      </svg>
                      评价：5星
                    </button>
                    <button class="Btn">

                      <div class="sign"><svg viewBox="0 0 512 512"><path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"></path></svg></div>

                      <div class="text">更多</div>
                    </button>
                  </div>
                </div>
                <div>

                </div>
              </div>
              <p class="desc">
                {{activite.desc}}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination=""
    />

  </div>
</template>

<script setup name="ActivitieRecord">

const { proxy } = getCurrentInstance();

const activitieRecordList = ref([
  {
    id: 1,
    title: "地震灾区",
    desc: "这是地震的灾区"
  },
  {
    id: 2,
    title: "地震灾区",
    desc: "这是地震的灾区"
  },
  {
    id: 3,
    title: "地震灾区",
    desc: "这是地震的灾区"
  },
  {
    id: 4,
    title: "地震灾区",
    desc: "这是地震的灾区"
  },
  {
    id: 5,
    title: "地震灾区",
    desc: "这是地震的灾区"
  },
]);

const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);





</script>
<style scoped>
.card {
  overflow: hidden;
  width: 290px;
  height: 404px;
  background: linear-gradient(10deg, rgba(38,146,255,1) 0%, rgba(0,63,255,1) 100%);
  border-radius: 16px;
  box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(10.5px);
  -webkit-backdrop-filter: blur(10.5px);
  border: 1px solid rgba(255, 255, 255, 0.3);
}

.top {
  background: rgba(255, 255, 255, 0.2);
  border-radius: 16px;
  box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(10.5px);
  -webkit-backdrop-filter: blur(10.5px);
  border: 1px solid rgba(255, 255, 255, 0.3);
  width: 290px;
  height: 320px;
}

.top .title {
  color: #fff;
  font-weight: bolder;
  font-size: x-large;
  margin-left: 10px;
  margin-top: 1%
}

.card .desc {
  color: #fff;
  opacity: 75%;
  font-size: small;
  font-weight: lighter;
  margin-left: 10px;
  margin-top: 2%
}

.top img {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 保持图片比例并填充整个容器 */
  transition: transform 0.3s ease; /* 添加过渡动画效果 */
}

.top:hover img {
  transform: scale(1.1); /* 鼠标悬停时应用内部放大效果 */
}


.card-container {
  width: 305px;
  height: 415px;
  transition: all 0.2s;
  position: relative;
  cursor: pointer;
}

.card-inner {
  width: inherit;
  height: inherit;
  background: rgba(255, 255, 255, 0.05);
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.25);
  backdrop-filter: blur(10px);
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center; /* 上下居中 */
}



.circle {
  width: 100px;
  height: 100px;
  background: repeating-linear-gradient(48deg, #3023ae 0%, #ff0099 100%);
  border-radius: 35% 30% 75% 30% / 49% 30% 70% 51%;
  position: absolute;
  animation: move-up6 2s ease-in infinite alternate-reverse;
}

.circle:nth-child(1) {
  top: -25px;
  left: -25px;
}

.circle:nth-child(2) {
  bottom: -25px;
  right: -25px;
  animation-name: move-down1;
}

@keyframes move-up6 {
  to {
    transform: translateY(-10px);
  }
}

@keyframes move-down1 {
  to {
    transform: translateY(10px);
  }
}

.button {
  width: fit-content;
  display: flex;
  padding: 1.2em 1rem;
  cursor: pointer;
  gap: 0.4rem;
  font-weight: bold;
  border-radius: 30px;
  text-shadow: 2px 2px 3px rgb(136 0 136 / 50%);
  background: linear-gradient(15deg, #880088, #aa2068, #cc3f47, #de6f3d, #f09f33, #de6f3d, #cc3f47, #aa2068, #880088) no-repeat;
  background-size: 300%;
  color: #fff;
  border: none;
  background-position: left center;
  box-shadow: 0 30px 10px -20px rgba(0,0,0,.2);
  transition: background .3s ease;
}

.button:hover {
  background-size: 320%;
  background-position: right center;
}

.button:hover svg {
  fill: #fff;
}

.button svg {
  width: 23px;
  fill: #f09f33;
  transition: .3s ease;
}



.Btn {
  margin-top: 3px;
  margin-right: 20px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 45px;
  height: 45px;
  border: none;
  border-radius: 50%;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition-duration: .3s;
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.199);
  background-color: rgb(255, 65, 65);
}

/* plus sign */
.sign {
  width: 100%;
  transition-duration: .3s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.sign svg {
  width: 17px;
}

.sign svg path {
  fill: white;
}
/* text */
.text {
  position: absolute;
  right: 0%;
  width: 0%;
  opacity: 0;
  color: white;
  font-size: 1.2em;
  font-weight: 600;
  transition-duration: .3s;
}
/* hover effect on button width */
.Btn:hover {
  width: 125px;
  border-radius: 40px;
  transition-duration: .3s;
}

.Btn:hover .sign {
  width: 30%;
  transition-duration: .3s;
  padding-left: 20px;
}
/* hover effect button's text */
.Btn:hover .text {
  opacity: 1;
  width: 70%;
  transition-duration: .3s;
  padding-right: 10px;
}
/* button click effect*/
.Btn:active {
  transform: translate(2px ,2px);
}
</style>


