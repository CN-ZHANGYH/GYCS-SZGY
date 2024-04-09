<template>
  <div style="display: flex;flex-direction: column">
    <!-- 页面总布局 -->
    <div style="display: flex;flex: 1">
      <vs-row>
        <vs-col :sm="4">
          <!-- 左边的div   -->
          <div v-for="(item,index) in getPage(votesList,queryParams.pageNum,queryParams.pageSize)" :key="index" style="padding: 10px 10px;">
            <div class="card">
              <h3 class="card__title">
                {{ item.title }}
              </h3>
              <p class="card__content">{{ item.description.substring(0,40) + "......"}}</p>
              <div class="card__date">
                {{ item.createTime }}
              </div>
              <div class="card__arrow" @click="viewDetail(item)">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" height="15" width="15">
                  <path fill="#fff" d="M13.4697 17.9697C13.1768 18.2626 13.1768 18.7374 13.4697 19.0303C13.7626 19.3232 14.2374 19.3232 14.5303 19.0303L20.3232 13.2374C21.0066 12.554 21.0066 11.446 20.3232 10.7626L14.5303 4.96967C14.2374 4.67678 13.7626 4.67678 13.4697 4.96967C13.1768 5.26256 13.1768 5.73744 13.4697 6.03033L18.6893 11.25H4C3.58579 11.25 3.25 11.5858 3.25 12C3.25 12.4142 3.58579 12.75 4 12.75H18.6893L13.4697 17.9697Z"></path>
                </svg>
              </div>
            </div>
          </div>
        </vs-col>
      </vs-row>
      <!-- 右边的div   -->
      <div class="content-card">
        <div class="bg">
          <div style="padding: 10px 10px;height: 780px">
            <div ref="$content" type="shadow" style="height: 100%;width: 100%;border-radius: 10px">
              <vs-alert type="relief" style="margin-bottom: 20px">
                <template #title> 参与审核和投票的规则 </template>
                您当前所参与进行公开投票，将会决定当前的公益募资活动是否通过，所有的投票记录将会上链，确保了信息的公开透明、不可篡改，并通过分布式存储和智能合约执行保障数据的安全性和可靠性。
              </vs-alert>
              <div v-if="votesList.length == 0 || raiseId == 0" style="padding: 8% 30%">
                <svg height="300"  node-id="1" sillyvg="true" template-height="127" template-width="250" version="1.1" viewBox="0 0 250 127" width="600" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><defs node-id="61"><linearGradient gradientUnits="objectBoundingBox" id="linearGradient-1" node-id="7" spreadMethod="pad" x1="0.5" x2="0.5" y1="0" y2="1"><stop offset="0" stop-color="#c1dbfe"/><stop offset="1" stop-color="#ffffff"/></linearGradient><linearGradient gradientUnits="objectBoundingBox" id="linearGradient-2" node-id="10" spreadMethod="pad" x1="0.5" x2="0.5" y1="0" y2="1"><stop offset="0" stop-color="#b4cef5"/><stop offset="1" stop-color="#97b9f3"/></linearGradient><linearGradient gradientUnits="objectBoundingBox" id="linearGradient-3" node-id="13" spreadMethod="pad" x1="0.5" x2="0.5" y1="0" y2="1"><stop offset="0" stop-color="#95b3ff"/><stop offset="1" stop-color="#608ded"/></linearGradient><linearGradient gradientUnits="objectBoundingBox" id="linearGradient-4" node-id="16" spreadMethod="pad" x1="0.970297" x2="0.02970304" y1="0.10599462" y2="0.89400536"><stop offset="0" stop-color="#8bacfb"/><stop offset="1" stop-color="#5f87da"/></linearGradient><filter height="1.278" id="filter-6" node-id="20" width="1.263" x="-0.158" y="-0.111"><feMorphology color-interpolation-filters="linearRGB" in="SourceAlpha" operator="dilate" radius="1.5 1.5" result="shadowSpreadOuter1"></feMorphology><feOffset color-interpolation-filters="linearRGB" dx="-1" dy="1" in="shadowSpreadOuter1" result="shadowOffsetOuter1"></feOffset><feMorphology color-interpolation-filters="linearRGB" in="SourceAlpha" operator="erode" radius="1.5 1.5" result="shadowInner"></feMorphology><feOffset color-interpolation-filters="linearRGB" dx="-1" dy="1" in="shadowInner" result="shadowInner"></feOffset><feComposite color-interpolation-filters="linearRGB" in="shadowOffsetOuter1" in2="shadowInner" operator="out" result="shadowOffsetOuter1"></feComposite><feColorMatrix color-interpolation-filters="linearRGB" in="shadowOffsetOuter1" result="result6" type="matrix" values="0 0 0 0 0.341636089 0 0 0 0 0.49265456 0 0 0 0 0.806017889 0 0 0 1 0"></feColorMatrix></filter></defs><g node-id="154"><g node-id="155"><path d="M -4.00 -20.00 L 249.00 -20.00 L 249.00 130.00 L -4.00 130.00 Z" fill="none" group-id="1,2" id="矩形备份" node-id="30" stroke="none" target-height="150" target-width="253" target-x="-4" target-y="-20"/><path d="M 249.28 127.00 L 243.55 120.33 L 237.46 114.03 L 230.99 108.10 L 224.13 102.54 L 216.97 97.38 L 209.52 92.62 L 201.77 88.27 L 193.69 84.31 L 185.43 80.81 L 176.95 77.74 L 168.25 75.12 L 159.29 72.93 L 150.26 71.23 L 141.09 70.00 L 131.76 69.26 L 122.26 69.00 L 113.06 69.27 L 104.07 70.02 L 95.27 71.26 L 86.64 72.97 L 78.09 75.16 L 69.77 77.78 L 61.69 80.82 L 53.81 84.29 L 46.11 88.18 L 38.69 92.45 L 31.54 97.08 L 24.64 102.10 L 18.01 107.47 L 11.70 113.16 L 5.70 119.17 L 0.00 125.52 L 5.81 125.55 L 21.73 125.65 L 45.51 125.79 L 74.92 125.96 L 107.69 126.16 L 141.59 126.36 L 174.37 126.55 L 203.77 126.73 L 227.56 126.87 L 243.48 126.97 L 249.28 127.00 Z" fill="url(#linearGradient-1)" fill-rule="evenodd" group-id="1,2" id="椭圆形" node-id="31" stroke="none" target-height="58" target-width="249.2835" target-x="0" target-y="69"/><g node-id="156"><g node-id="159"><g node-id="162"><path d="M 55.52 91.53 L 50.85 91.53 L 50.85 75.52 L 51.03 74.61 L 51.53 73.88 L 52.26 73.39 L 53.17 73.21 L 53.19 73.21 L 54.11 73.39 L 54.84 73.88 L 55.33 74.61 L 55.52 75.52 L 55.52 91.53 Z" fill="#bae637" fill-rule="evenodd" group-id="1,2,3,6,9" node-id="110" stroke="none" target-height="18.32122" target-width="4.6739006" target-x="50.845627" target-y="73.20788"/></g><path d="M 57.84 95.45 L 53.16 95.45 L 53.16 87.09 L 53.34 86.21 L 53.82 85.50 L 54.54 85.02 L 55.43 84.84 L 55.57 84.84 L 56.46 85.02 L 57.18 85.50 L 57.66 86.21 L 57.84 87.09 L 57.84 95.45 Z" fill="#389e0d" fill-rule="evenodd" group-id="1,2,3,6" id="Fill-3" node-id="36" stroke="none" target-height="10.608734" target-width="4.6875" target-x="53.15625" target-y="84.84001"/><path d="M 51.53 91.52 L 46.81 91.52 L 46.81 82.43 L 46.99 81.54 L 47.48 80.83 L 48.20 80.35 L 49.09 80.17 L 49.25 80.17 L 50.15 80.35 L 50.86 80.83 L 51.35 81.54 L 51.53 82.43 L 51.53 91.52 Z" fill="#7cb305" fill-rule="evenodd" group-id="1,2,3,6" id="Fill-5" node-id="37" stroke="none" target-height="11.351036" target-width="4.71875" target-x="46.8125" target-y="80.1697"/><path d="M 58.12 104.80 L 45.85 104.80 L 45.23 104.55 L 44.97 103.93 L 44.97 91.86 L 45.23 91.24 L 45.85 90.99 L 58.12 90.99 L 58.74 91.24 L 59.00 91.86 L 59.00 103.93 L 58.74 104.55 L 58.12 104.80" fill="#3b68b8" fill-rule="evenodd" group-id="1,2,3,6,10" node-id="116" stroke="none" target-height="13.814209" target-width="14.032349" target-x="44.96875" target-y="90.98799"/></g></g><g node-id="157"><g node-id="160"><g node-id="164"><path d="M 80.18 73.89 L 77.37 73.89 L 77.37 64.39 L 77.49 63.85 L 77.78 63.42 L 78.22 63.13 L 78.77 63.02 L 78.78 63.02 L 79.33 63.13 L 79.77 63.42 L 80.07 63.85 L 80.18 64.39 L 80.18 73.89 Z" fill="#bae637" fill-rule="evenodd" group-id="1,2,4,7,11" node-id="124" stroke="none" target-height="10.878227" target-width="2.8043365" target-x="77.37462" target-y="63.01674"/></g><path d="M 81.57 76.22 L 78.76 76.22 L 78.76 71.26 L 78.87 70.73 L 79.16 70.31 L 79.59 70.03 L 80.13 69.92 L 80.21 69.92 L 80.75 70.03 L 81.17 70.31 L 81.46 70.73 L 81.57 71.26 L 81.57 76.22 Z" fill="#389e0d" fill-rule="evenodd" group-id="1,2,4,7" id="Fill-3" node-id="44" stroke="none" target-height="6.2989426" target-width="2.8125" target-x="78.76099" target-y="69.92332"/><path d="M 77.79 73.89 L 74.95 73.89 L 74.95 68.49 L 75.06 67.97 L 75.36 67.54 L 75.79 67.26 L 76.32 67.15 L 76.42 67.15 L 76.96 67.26 L 77.39 67.54 L 77.68 67.97 L 77.79 68.49 L 77.79 73.89 Z" fill="#7cb305" fill-rule="evenodd" group-id="1,2,4,7" id="Fill-5" node-id="45" stroke="none" target-height="6.7396774" target-width="2.831253" target-x="74.954735" target-y="67.15032"/><path d="M 81.74 81.78 L 74.38 81.78 L 74.00 81.62 L 73.85 81.26 L 73.85 74.09 L 74.00 73.73 L 74.38 73.57 L 81.74 73.57 L 82.11 73.73 L 82.27 74.09 L 82.27 81.26 L 82.11 81.62 L 81.74 81.78" fill="#3b68b8" fill-rule="evenodd" group-id="1,2,4,7,12" node-id="130" stroke="none" target-height="8.202187" target-width="8.419411" target-x="73.84849" target-y="73.573685"/></g></g><g node-id="158"><path d="M 92.38 0.00 L 152.53 0.00 L 154.41 0.78 L 156.03 1.97 L 157.43 3.61 L 158.60 5.81 L 159.53 8.67 L 160.15 12.37 L 160.38 17.07 L 160.38 78.00 L 111.01 78.00 L 108.82 77.80 L 106.81 77.25 L 104.95 76.36 L 103.27 75.17 L 101.84 73.74 L 100.65 72.06 L 99.76 70.20 L 99.21 68.19 L 99.01 66.00 L 99.01 17.07 L 99.01 17.07 L 98.88 12.30 L 98.56 9.08 L 98.12 7.03 L 97.26 5.20 L 95.47 2.90 L 92.38 0.00 Z" fill="url(#linearGradient-2)" fill-rule="evenodd" group-id="1,2,5" id="路径-41" node-id="49" stroke="none" target-height="78" target-width="68" target-x="92.375" target-y="0"/><path d="M 132.38 62.00 L 194.38 62.00 L 196.25 62.25 L 197.91 62.96 L 199.32 64.05 L 200.42 65.47 L 201.12 67.12 L 201.38 69.00 L 201.38 95.00 L 201.38 95.00 L 142.24 95.00 L 142.24 73.92 L 141.90 71.47 L 141.17 69.43 L 140.07 67.72 L 138.59 66.23 L 136.14 64.36 L 132.38 62.00 Z" fill="url(#linearGradient-3)" fill-rule="evenodd" group-id="1,2,5" id="路径-42" node-id="50" stroke="none" target-height="33" target-width="69" target-x="132.375" target-y="62"/><path d="M 113.38 78.00 L 115.49 73.24 L 118.30 68.17 L 120.10 65.88 L 122.28 64.07 L 124.73 62.68 L 127.15 61.74 L 129.56 61.16 L 131.46 61.00 L 133.18 61.22 L 134.81 61.78 L 136.38 62.72 L 138.49 64.56 L 140.43 66.88 L 141.34 68.53 L 142.00 70.81 L 142.35 73.89 L 142.27 78.00 L 113.38 78.00 Z" fill="#5f87da" fill-rule="evenodd" group-id="1,2,5" id="路径-43" node-id="51" stroke="none" target-height="17" target-width="28.971756" target-x="113.375" target-y="61"/><path d="M 83.39 22.00 L 99.18 22.00 L 99.37 15.82 L 99.15 11.21 L 98.63 7.86 L 97.91 5.51 L 97.05 3.91 L 94.82 1.20 L 93.43 0.16 L 92.57 0.02 L 89.70 1.35 L 87.54 2.93 L 85.98 4.74 L 84.92 6.83 L 84.10 10.01 L 83.53 14.88 L 83.39 22.00 Z" fill="#5f87da" fill-rule="evenodd" group-id="1,2,5" id="路径-44" node-id="52" stroke="none" target-height="21.976095" target-width="15.985794" target-x="83.388" target-y="0.02390526"/><path d="M 111.38 18.50 L 148.38 18.50" fill="none" group-id="1,2,5" id="路径-36" node-id="53" stroke="#ffffff" stroke-linecap="round" stroke-width="2" target-height="0" target-width="37" target-x="111.375" target-y="18.5"/><path d="M 111.38 29.50 L 134.38 29.50" fill="none" group-id="1,2,5" id="路径-36备份-3" node-id="54" stroke="#ffffff" stroke-linecap="round" stroke-width="2" target-height="0" target-width="23" target-x="111.375" target-y="29.5"/><path d="M 111.38 38.50 L 134.38 38.50" fill="none" group-id="1,2,5" id="路径-36备份-4" node-id="55" stroke="#ffffff" stroke-linecap="round" stroke-width="2" target-height="0" target-width="23" target-x="111.375" target-y="38.5"/><path d="M 102.65 64.55 L 104.53 65.78 L 104.96 66.42 L 104.82 67.17 L 104.82 67.18 L 99.48 75.17 L 98.85 75.60 L 98.10 75.45 L 96.22 74.22 L 95.79 73.58 L 95.93 72.83 L 95.93 72.82 L 101.27 64.83 L 101.90 64.40 L 102.65 64.55 Z" fill="url(#linearGradient-4)" fill-rule="evenodd" group-id="1,2,5" id="矩形" node-id="56" stroke="none" target-height="11.197266" target-width="9.177658" target-x="95.78617" target-y="64.40137"/><g node-id="161"><g node-id="166"><path d="M 117.38 58.00 L 117.21 59.64 L 116.78 61.14 L 116.08 62.54 L 114.60 64.36 L 112.67 65.77 L 110.42 66.67 L 107.88 67.00 L 105.33 66.67 L 103.08 65.77 L 101.15 64.36 L 99.67 62.54 L 98.97 61.14 L 98.54 59.64 L 98.38 58.00 L 98.54 56.36 L 98.97 54.86 L 99.67 53.46 L 101.15 51.64 L 103.08 50.23 L 105.33 49.33 L 107.88 49.00 L 110.42 49.33 L 112.67 50.23 L 114.60 51.64 L 116.08 53.46 L 116.78 54.86 L 117.21 56.36 L 117.38 58.00 Z" fill="#000000" fill-rule="evenodd" filter="url(#filter-6)" group-id="1,2,5,8,13" node-id="147" stroke="none" target-height="18" target-width="19" target-x="98.375" target-y="49"/></g><path d="M 117.38 58.00 L 117.21 59.64 L 116.78 61.14 L 116.08 62.54 L 114.60 64.36 L 112.67 65.77 L 110.42 66.67 L 107.88 67.00 L 105.33 66.67 L 103.08 65.77 L 101.15 64.36 L 99.67 62.54 L 98.97 61.14 L 98.54 59.64 L 98.38 58.00 L 98.54 56.36 L 98.97 54.86 L 99.67 53.46 L 101.15 51.64 L 103.08 50.23 L 105.33 49.33 L 107.88 49.00 L 110.42 49.33 L 112.67 50.23 L 114.60 51.64 L 116.08 53.46 L 116.78 54.86 L 117.21 56.36 L 117.38 58.00 Z" fill="none" group-id="1,2,5,8,14" node-id="152" stroke="#d7e7ff" stroke-linecap="butt" stroke-width="3" target-height="18" target-width="19" target-x="98.375" target-y="49"/></g></g></g></g></svg>
              </div>
              <div v-if="raiseId != 0">
                <div  style="display: flex;flex: 1">
                  <div style="display: flex;justify-content: space-between">
                    <div style="width: 400px;font-size: 15px;color: #0b0b0b;font-weight: bolder;margin-left: 10px">
                      {{raise_info.description}}
                      <div style="">
                        {{}}
                      </div>
                    </div>
                    <div class="raise_fund_img_container">
                      <img :src="certificate_info.raiseImage" alt="">
                      <img :src="certificate_info.orgImage" alt="">
                    </div>
                  </div>
                  <div>
                    <div>
                      <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
                        <h2 style="font-size: 17px;font-weight: bold;margin-top: 9px;">交易地址：</h2>
                        <vs-input state="primary" v-model="raise_info.promoterAddress" input-style="shadow" disabled style="width: 500px;border-radius: 10px"/>
                      </div>
                      <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
                        <h2 style="font-size: 17px;font-weight: bold;margin-top: 9px;">家庭住址：</h2>
                        <vs-input state="primary" v-model="certificate_info.address" input-style="shadow" disabled style="width: 500px;border-radius: 10px"/>
                      </div>
                      <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
                        <h2 style="font-size: 17px;font-weight: bold;margin-top: 9px;">身份证号：</h2>
                        <vs-input state="primary" v-model="certificate_info.cardId" input-style="shadow" disabled style="width: 500px;border-radius: 10px"/>
                      </div>
                      <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
                        <h2 style="font-size: 17px;font-weight: bold;margin-top: 9px;">真实姓名：</h2>
                        <vs-input state="primary" v-model="certificate_info.name" input-style="shadow" disabled style="width: 500px;border-radius: 10px"/>
                      </div>
                      <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
                        <h2 style="font-size: 17px;font-weight: bold;margin-top: 9px;">联系电话：</h2>
                        <vs-input state="primary" v-model="certificate_info.phone" input-style="shadow" disabled style="width: 500px;border-radius: 10px"/>
                      </div>
                      <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
                        <h2 style="font-size: 17px;font-weight: bold;margin-top: 9px;">开始时间：</h2>
                        <vs-input state="primary" v-model="raise_info.startTime" input-style="shadow" disabled style="width: 500px;border-radius: 10px"/>
                      </div>
                      <div style="display: flex;justify-content: space-between;margin-bottom: 15px">
                        <h2 style="font-size: 17px;font-weight: bold;margin-top: 9px;">结束时间：</h2>
                        <vs-input state="primary" v-model="raise_info.endTime" input-style="shadow" disabled style="width: 500px;border-radius: 10px"/>
                      </div>
                    </div>
                    <div style="display: flex;flex-direction: column">
                      <div style="display: flex;justify-content: space-between;width: 100%">
                        <vs-button
                            type="gradient"
                            style="min-width: 60px"
                            color="success"
                            animation-type="scale"
                        >
                          募资金额
                          <template #animate> {{raise_info.totalAmount}} </template>
                        </vs-button>
                        <vs-button
                            type="gradient"
                            style="min-width: 60px"
                            color="success"
                            animation-type="scale"
                        >
                          完成金额
                          <template #animate> {{raise_info.overAmount}} </template>
                        </vs-button>
                        <vs-button
                            type="gradient"
                            style="min-width: 60px"
                            color="success"
                            animation-type="scale"
                        >
                          活动状态
                          <template #animate> 待投票中 </template>
                        </vs-button>
                        <vs-button
                            type="gradient"
                            style="min-width: 60px"
                            color="success"
                            animation-type="scale"
                        >
                          参与人数
                          <template #animate> {{raise_info.totalPeople}} </template>
                        </vs-button>
                      </div>
                    </div>
                  </div>
                </div>
                <div style="display: flex;justify-content: space-between;margin: 20px;padding: 10px 30%">
                  <vs-button block @click="handleAgreeVote">
                    同意
                  </vs-button>
                  <vs-button block color="danger" @click="handleDenyVote">
                    拒绝
                  </vs-button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="blob"></div>
      </div>
    </div>
    <div style="display: flex;justify-content: center;margin-top: 10px">
      <vs-pagination v-model:current-page="queryParams.pageNum" v-model:page-size="queryParams.pageSize" buttons-dotted :total="total" :page-sizes="[1,2,3,4]"/>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref, toRefs} from "vue";
import {getRaiseFundInfo, getRaiseFundWaitVotesList, voteOfRaiseFund} from "@/api/charity/raiseFund.js";
import {getPage, VsLoadingFn, VsNotification} from "vuesax-alpha";
const $content = ref()
const votesList = ref([])
const total = ref(0)
const raiseId = ref(0)
const data = reactive({
  certificate_info: {},
  raise_info: {},
  queryParams: {
    pageNum: 1,
    pageSize: 4
  },
})
const {queryParams,certificate_info,raise_info}  = toRefs(data)
function viewDetail(item){
  raiseId.value = item.id
  certificate_info.value = {}
  raise_info.value = {}
  getRaiseFundInfo({raiseId: raiseId.value}).then(res => {
    certificate_info.value = res.data.certificate_info
    raise_info.value = res.data.raise_info
  })
  const loadingInstance = VsLoadingFn({
    target: $content,
  })
  setTimeout(() => {
    loadingInstance.close()
  }, 1000)
}

function handleAgreeVote(){
  voteOfRaiseFund({
    raiseId: raiseId.value,
    status: true,
  }).then(res => {
    if (res.code == 200) {
      openNotification('success','投票通知',res.msg)
    }
  })
}

function handleDenyVote(){
  voteOfRaiseFund({
    raiseId: raiseId.value,
    status: false,
  }).then(res => {
    if (res.code == 200) {
      openNotification('success','投票通知',res.msg)
    }
  })
}
onMounted(() => {
  getRaiseFundWaitVotesList().then(res => {
    total.value = res.total
    votesList.value = res.rows
  })
})


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
/* this card is inspired form this - https://georgefrancis.dev/ */

.card {
  --border-radius: 0.75rem;
  --primary-color: #7257fa;
  --secondary-color: #3c3852;
  width: 300px;
  font-family: "Arial";
  padding: 1rem;
  cursor: pointer;
  border-radius: var(--border-radius);
  position: relative;
  background-color: rgb(255, 255, 255);
  box-shadow: 2px 2px 20px rgba(0, 0, 0, 0.062);
}

.card > * + * {
  margin-top: 1.1em;
}

.card .card__content {
  color: var(--secondary-color);
  font-size: 0.86rem;
}

.card .card__title {
  padding: 0;
  font-size: 1.3rem;
  font-weight: bold;
}

.card .card__date {
  color: #6e6b80;
  font-size: 0.8rem;
}

.card .card__arrow {
  position: absolute;
  background: var(--primary-color);
  padding: 0.4rem;
  border-top-left-radius: var(--border-radius);
  border-bottom-right-radius: var(--border-radius);
  bottom: 0;
  right: 0;
  transition: 0.2s;
  display: flex;
  justify-content: center;
  align-items: center;
}

.card svg {
  transition: 0.2s;
}

/* hover */
.card:hover .card__title {
  color: var(--primary-color);
  text-decoration: underline;
}

.card:hover .card__arrow {
  background: #111;
}

.card:hover .card__arrow svg {
  transform: translateX(3px);
}

.raise_fund_img_container > img {
  width: 400px;
  height: 250px;
  border: 10px white solid;
  border-radius: 20px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
  margin: 0 30px 20px;

}



.content-card{
  position: relative;
  width: 100%;
  height: 780px;
  border-radius: 14px;
  z-index: 1111;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  box-shadow: 5px 0px 10px #bebebe, 0px 0 10px #ffffff;
;
}

.bg {
  position: absolute;
  top: 5px;
  left: 5px;
  width: 99.3%;
  height: 770px;
  z-index: 2;
  background: rgba(255, 255, 255, .99);
  //backdrop-filter: blur(24px);
  border-radius: 10px;
  overflow: hidden;
  outline: 2px solid white;
}

.blob {
  position: absolute;
  z-index: 1;
  top: 50%;
  left: 50%;
  width: 100%;
  height: 800px;
  border-radius: 50%;
  background-color: #195afd;
  opacity: 1;
  //filter: blur(12px);
  animation: blob-bounce 5s infinite ease;
}

@keyframes blob-bounce {
  0% {
    transform: translate(-100%, -100%) translate3d(0, 0, 0);
  }

  25% {
    transform: translate(-100%, -100%) translate3d(100%, 0, 0);
  }

  50% {
    transform: translate(-100%, -100%) translate3d(100%, 100%, 0);
  }

  75% {
    transform: translate(-100%, -100%) translate3d(0, 100%, 0);
  }

  100% {
    transform: translate(-100%, -100%) translate3d(0, 0, 0);
  }
}

</style>
