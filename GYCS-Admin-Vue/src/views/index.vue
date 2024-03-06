<template>
  <div class="app-container home">
    <el-card style="border-radius: 10px;padding: 15px 10px">
      <el-row :gutter="30" style="display: flex;margin-left: 1.2%">
        <el-col :sm="24" :lg="6">
          <div class="e-card playing">
            <div class="image"></div>
            <div class="wave"></div>
            <div class="wave"></div>
            <div class="wave"></div>
            <div class="infotop">
              <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-box-fill" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M15.528 2.973a.75.75 0 0 1 .472.696v8.662a.75.75 0 0 1-.472.696l-7.25 2.9a.75.75 0 0 1-.557 0l-7.25-2.9A.75.75 0 0 1 0 12.331V3.669a.75.75 0 0 1 .471-.696L7.443.184l.004-.001.274-.11a.75.75 0 0 1 .558 0l.274.11.004.001 6.971 2.789Zm-1.374.527L8 5.962 1.846 3.5 1 3.839v.4l6.5 2.6v7.922l.5.2.5-.2V6.84l6.5-2.6v-.4l-.846-.339Z"/>
              </svg>
              <div style="margin-top: -20px">
                <h2>{{blockNumber}}</h2>
                <p>区块数量</p>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :sm="24" :lg="6" >
          <div class="e-card playing">
            <div class="image"></div>
            <div class="wave"></div>
            <div class="wave"></div>
            <div class="wave"></div>
            <div class="infotop">
              <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-hdd-rack-fill" viewBox="0 0 16 16">
                <path d="M2 2a2 2 0 0 0-2 2v1a2 2 0 0 0 2 2h1v2H2a2 2 0 0 0-2 2v1a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2v-1a2 2 0 0 0-2-2h-1V7h1a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H2zm.5 3a.5.5 0 1 1 0-1 .5.5 0 0 1 0 1zm2 0a.5.5 0 1 1 0-1 .5.5 0 0 1 0 1zm-2 7a.5.5 0 1 1 0-1 .5.5 0 0 1 0 1zm2 0a.5.5 0 1 1 0-1 .5.5 0 0 1 0 1zM12 7v2H4V7h8z"/>
              </svg>
              <div style="margin-top: -20px">
                <h2>{{nodeList}}</h2>
                <p>节点数量</p>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :sm="24" :lg="6" >
          <div class="e-card playing">
            <div class="image"></div>

            <div class="wave"></div>
            <div class="wave"></div>
            <div class="wave"></div>
            <div class="infotop">
              <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-currency-exchange" viewBox="0 0 16 16">
                <path d="M0 5a5.002 5.002 0 0 0 4.027 4.905 6.46 6.46 0 0 1 .544-2.073C3.695 7.536 3.132 6.864 3 5.91h-.5v-.426h.466V5.05c0-.046 0-.093.004-.135H2.5v-.427h.511C3.236 3.24 4.213 2.5 5.681 2.5c.316 0 .59.031.819.085v.733a3.46 3.46 0 0 0-.815-.082c-.919 0-1.538.466-1.734 1.252h1.917v.427h-1.98c-.003.046-.003.097-.003.147v.422h1.983v.427H3.93c.118.602.468 1.03 1.005 1.229a6.5 6.5 0 0 1 4.97-3.113A5.002 5.002 0 0 0 0 5zm16 5.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0zm-7.75 1.322c.069.835.746 1.485 1.964 1.562V14h.54v-.62c1.259-.086 1.996-.74 1.996-1.69 0-.865-.563-1.31-1.57-1.54l-.426-.1V8.374c.54.06.884.347.966.745h.948c-.07-.804-.779-1.433-1.914-1.502V7h-.54v.629c-1.076.103-1.808.732-1.808 1.622 0 .787.544 1.288 1.45 1.493l.358.085v1.78c-.554-.08-.92-.376-1.003-.787H8.25zm1.96-1.895c-.532-.12-.82-.364-.82-.732 0-.41.311-.719.824-.809v1.54h-.005zm.622 1.044c.645.145.943.38.943.796 0 .474-.37.8-1.02.86v-1.674l.077.018z"/>
              </svg>
              <div style="margin-top: -20px">
                <h2>{{totalTransactions}}</h2>
                <p>交易数量</p>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :sm="24" :lg="6">
          <div class="e-card playing">
            <div class="image"></div>

            <div class="wave"></div>
            <div class="wave"></div>
            <div class="wave"></div>
            <div class="infotop">
              <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-hourglass-split" viewBox="0 0 16 16">
                <path d="M2.5 15a.5.5 0 1 1 0-1h1v-1a4.5 4.5 0 0 1 2.557-4.06c.29-.139.443-.377.443-.59v-.7c0-.213-.154-.451-.443-.59A4.5 4.5 0 0 1 3.5 3V2h-1a.5.5 0 0 1 0-1h11a.5.5 0 0 1 0 1h-1v1a4.5 4.5 0 0 1-2.557 4.06c-.29.139-.443.377-.443.59v.7c0 .213.154.451.443.59A4.5 4.5 0 0 1 12.5 13v1h1a.5.5 0 0 1 0 1h-11zm2-13v1c0 .537.12 1.045.337 1.5h6.326c.216-.455.337-.963.337-1.5V2h-7zm3 6.35c0 .701-.478 1.236-1.011 1.492A3.5 3.5 0 0 0 4.5 13s.866-1.299 3-1.48V8.35zm1 0v3.17c2.134.181 3 1.48 3 1.48a3.5 3.5 0 0 0-1.989-3.158C8.978 9.586 8.5 9.052 8.5 8.351z"/>
              </svg>
              <div style="margin-top: -20px">
                <h2>{{pendingTransactions}}</h2>
                <p>待交易数量</p>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-card>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <el-card style="border-radius: 10px;margin-top: 20px;padding: 10px 10px 0 10px;">
          <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="id" label="节点ID" width="1050" />
            <el-table-column prop="blockNumber" label="块高" width="180" />
            <el-table-column prop="pbftView" label="pbftView" />
            <el-table-column prop="status" label="状态">
              <template  #default="scope">
                <el-tag
                    type="success"
                    effect="dark"
                    style="height: 5px;"
                    size="small"
                ></el-tag>
                运行
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>联系信息</span>
            </div>
          </template>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>更新日志</span>
            </div>
          </template>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>捐赠支持</span>
            </div>
          </template>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name="Index">
import {getBlockInfo, getNodeInfo} from "@/api/blockchain/blockchain.js";

const blockNumber = ref(0)
const nodeList = ref(0)
const pendingTransactions = ref(0)
const totalTransactions = ref(0)
const tableData = ref([])

function goTarget(url) {
  window.open(url, '__blank')
}

function getBlockChainInfo() {
  getBlockInfo().then(res => {
    if (res.code == 200) {
      blockNumber.value = res.data.blockNumber
      nodeList.value = res.data.nodeList
      totalTransactions.value = res.data.totalTransactions
      pendingTransactions.value = res.data.pendingTransactions
    }
  })

  getNodeInfo().then(res => {
    if (res.code == 200) {
      tableData.value = res.data
    }
  })
}

getBlockChainInfo()
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}



.e-card {
  background: transparent;
  box-shadow: 0px 8px 28px -9px rgba(0,0,0,0.45);
  position: relative;
  width:320px;
  height: 160px;
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

.icon {
  width: 3em;
  margin-top: -1em;
  padding-bottom: 1em;
}

.infotop {
  padding: 10px 30px;
  display: flex;
  justify-content: space-between;
  text-align: center;
  font-size: 20px;
  position: absolute;
  top: 2.6em;
  left: 0;
  right: 0;
  color: rgb(255, 255, 255);
  font-weight: 600;
}

.name {
  font-size: 14px;
  font-weight: 100;
  position: relative;
  top: 1em;
  text-transform: lowercase;
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
</style>

