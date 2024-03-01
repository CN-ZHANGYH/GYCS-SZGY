<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="审核人" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入审核人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['charity:audit:edit']"
        >审批</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['charity:audit:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['charity:audit:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="auditList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="募资活动编号" align="center" prop="raiseId" />
      <el-table-column label="审核状态" align="center" prop="applyStatus">
        <template #default="scope">
          <el-tag v-if="scope.row.applyStatus == 1" type="danger" effect="dark">待审核</el-tag>
          <el-tag v-if="scope.row.applyStatus == 2" type="success" effect="dark">上链</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核人" align="center" prop="username" />
      <el-table-column label="申请时间" align="center" prop="applyTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.applyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核时间" align="center" prop="auditTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.auditTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="View" @click="HandleCertificateInfo(scope.row)" >查看</el-button>
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['charity:audit:edit']">审核</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['charity:audit:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改审核对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-steps :active="3" style="margin-bottom: 20px;padding: 20px 20px">
        <el-step title="审核信息" :icon="View" />
        <el-step title="确认审核" :icon="CircleCheck" />
        <el-step title="审核通过" :icon="Paperclip" />
      </el-steps>
      <el-form ref="auditRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动ID" prop="raiseId">
          <el-input v-model="form.raiseId" placeholder="请输入募资活动ID" disabled/>
        </el-form-item>
        <el-form-item label="审核人" prop="username">
          <el-input v-model="form.username" placeholder="请输入审核人" />
        </el-form-item>
        <el-form-item label="申请时间" prop="applyTime">
          <el-date-picker clearable
            v-model="form.applyTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择申请时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核时间" prop="auditTime">
          <el-date-picker clearable
            v-model="form.auditTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择审核时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核状态" prop="apply_status">
          <el-radio-group v-model="apply_status" size="large">
            <el-radio-button label="通过"/>
            <el-radio-button label="不通过"/>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>


    <el-drawer
        v-model="isInfoView"
        title="证明信息审核"
        direction="ltr"
        size="40%"
    >
      <el-alert title="如下是需要进行审批的公益活动信息以及上传证明资料" type="success" :closable="false" style="margin-bottom: 10px"/>
      <div style="margin-left: 5%">
        <div class="card">
          <el-descriptions title="公益募资活动详细信息" column="1" border>
            <el-descriptions-item label="公益名称">{{raiseFundInfo.title}}</el-descriptions-item>
            <el-descriptions-item label="描述信息">{{raiseFundInfo.description}}</el-descriptions-item>
            <el-descriptions-item label="发起时间">{{raiseFundInfo.createTime}}</el-descriptions-item>
            <el-descriptions-item label="发起人">
              <el-tag size="small">{{raiseFundInfo.promoterAddress}}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="开始时间">{{raiseFundInfo.startTime}}</el-descriptions-item>
            <el-descriptions-item label="结束时间">{{raiseFundInfo.endTime}}</el-descriptions-item>
            <el-descriptions-item label="总共金额">{{raiseFundInfo.totalAmount}}</el-descriptions-item>
            <el-descriptions-item label="已完成金额">{{raiseFundInfo.overAmount}}</el-descriptions-item>
            <el-descriptions-item label="参与人数">{{raiseFundInfo.totalPeople}}</el-descriptions-item>
            <el-descriptions-item label="提现金额">{{raiseFundInfo.withdrawAmount}}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="card" style="margin-top: 30px">
          <el-descriptions title="公益募资活动详细信息" column="1" border>
            <el-descriptions-item label="姓名">{{certificateInfo.name}}</el-descriptions-item>
            <el-descriptions-item label="身份证号码">{{certificateInfo.cardId}}</el-descriptions-item>
            <el-descriptions-item label="家庭地址">{{certificateInfo.address}}</el-descriptions-item>
            <el-descriptions-item label="联系人">
              <el-tag size="small">{{certificateInfo.phone}}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="第三方认证">
              <img src="@/assets/images/img.png" style="width: 200px;height: 100px;border-radius: 5px;">
            </el-descriptions-item>
            <el-descriptions-item label="申请的原因">
              <img src="@/assets/images/img.png"  style="width: 200px;height: 100px;border-radius: 5px;">
            </el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
    </el-drawer>

  </div>
</template>

<script setup name="Audit">
import { listAudit, getAudit, delAudit, addAudit, updateAudit } from "@/api/charity/audit";
import {getCertificateInfo, getRaiseFundDetail} from "@/api/charity/raiseFund.js";
import {CircleCheck, View,Paperclip} from "@element-plus/icons-vue";

const { proxy } = getCurrentInstance();

const auditList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const isInfoView = ref(false)
const apply_status = ref("通过")
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    apply_status: null,
    username: null,
  },
  rules: {
  }
});
const size = ref('')
const { queryParams, form, rules } = toRefs(data);

/** 查询审核列表 */
function getList() {
  loading.value = true;
  listAudit(queryParams.value).then(response => {
    auditList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    raiseId: null,
    applyStatus: null,
    username: null,
    applyTime: null,
    auditTime: null
  };
  proxy.resetForm("auditRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加审核";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getAudit(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改审核";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["auditRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        if (apply_status.value == "通过"){
          form.value.applyStatus = 2
        }else if (apply_status.value == "不通过") {
          form.value.applyStatus = 1
        }
        updateAudit(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addAudit(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除审核编号为"' + _ids + '"的数据项？').then(function() {
    return delAudit(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('charity/audit/export', {
    ...queryParams.value
  }, `audit_${new Date().getTime()}.xlsx`)
}



const raiseFundInfo = ref({})
const certificateInfo = ref({})
/** 查看审批信息对应的上传证明信息*/
function HandleCertificateInfo(row) {
  isInfoView.value = true
  getRaiseFundDetail({raiseId: row.raiseId}).then(res => {
    if (res.code == 200) {
      raiseFundInfo.value = res.data
      console.log(res.data)
    }
  })

  getCertificateInfo({raiseId: row.raiseId}).then(res => {
    if (res.code == 200) {
      certificateInfo.value = res.data
      console.log(res.data)
    }
  })

}

getList();
</script>


<style scoped>
.card {
  cursor: pointer;
  width: 600px;
  height: 500px;
  background: rgb(255, 255, 255);
  border-radius: 5px;
  border: 1px solid rgba(0, 0, 255, .2);
  transition: all .2s;
  box-shadow: 12px 12px 2px 1px rgba(0, 0, 255, .2);
  padding: 20px 30px;
}

.card:hover {
  box-shadow: -12px 12px 2px -1px rgba(0, 0, 255, .2);
}


.el-descriptions {
  margin-top: 20px;
}
.cell-item {
  display: flex;
  align-items: center;
}
.margin-top {
  margin-top: 20px;
}
</style>
