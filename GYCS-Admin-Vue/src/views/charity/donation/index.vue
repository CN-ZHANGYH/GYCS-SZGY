<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="捐款地址" prop="donorAddress">
        <el-input
          v-model="queryParams.donorAddress"
          placeholder="请输入捐款人地址"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="捐款金额" prop="amount">
        <el-input
          v-model="queryParams.amount"
          placeholder="请输入捐款金额"
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
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['charity:donation:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['charity:donation:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['charity:donation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['charity:donation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="donationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="溯源ID" align="center" prop="donationId" />
      <el-table-column label="捐款人地址" align="center" prop="donorAddress" width="350px"/>
      <el-table-column label="捐款金额" align="center" prop="amount" />
      <el-table-column label="交易时间" align="center" prop="transTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.transTime, '{y}-{m}-{d} {hh}:{mm}:{ss}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交易类型" align="center" prop="transType" width="150px"/>
      <el-table-column label="交易状态" align="center" prop="isDonation" width="150px">
        <template #default="scope">
          <el-check-tag type="scope.row.isDonation == false ? 'danger' : 'success'" checked>
            {{scope.row.isDonation == false ? "交易失败" : "交易成功"}}
          </el-check-tag>
        </template>
      </el-table-column>
      <el-table-column label="捐款来源" align="center" prop="source" width="200px"/>
      <el-table-column label="捐款描述" align="center" prop="description" width="200px"/>
      <el-table-column label="捐款接收方机构" align="center" prop="destAddress" width="350px"/>
      <el-table-column label="集资ID" align="center" prop="raiseId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200px">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['charity:donation:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['charity:donation:remove']">删除</el-button>
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

    <!-- 添加或修改捐款信息记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="donationRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="捐款地址" prop="donorAddress">
          <el-input v-model="form.donorAddress" placeholder="请输入捐款人地址" />
        </el-form-item>
        <el-form-item label="捐款金额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入捐款金额" />
        </el-form-item>
        <el-form-item label="交易时间" prop="transTime">
          <el-date-picker clearable
            v-model="form.transTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择交易时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交易状态" prop="isDonation">
          <el-input v-model="form.isDonation" placeholder="请输入交易状态" />
        </el-form-item>
        <el-form-item label="捐款来源" prop="source">
          <el-input v-model="form.source" placeholder="请输入捐款来源" />
        </el-form-item>
        <el-form-item label="捐款描述" prop="desc">
          <el-input v-model="form.desc" placeholder="请输入捐款描述" />
        </el-form-item>
        <el-form-item label="接收机构" prop="destAddress">
          <el-input v-model="form.destAddress" placeholder="请输入捐款接收方机构" />
        </el-form-item>
        <el-form-item label="集资ID" prop="raiseId">
          <el-input v-model="form.raiseId" placeholder="请输入集资ID" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Donation">
import { listDonation, getDonation, delDonation, addDonation, updateDonation } from "@/api/charity/donation";
import {parseTime} from "../../../utils/ruoyi.js";

const { proxy } = getCurrentInstance();

const donationList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    donorAddress: null,
    amount: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询捐款信息记录列表 */
function getList() {
  loading.value = true;
  listDonation(queryParams.value).then(response => {
    donationList.value = response.rows;
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
    donationId: null,
    donorAddress: null,
    amount: null,
    transTime: null,
    transType: null,
    isDonation: null,
    source: null,
    desc: null,
    destAddress: null,
    raiseId: null
  };
  proxy.resetForm("donationRef");
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
  ids.value = selection.map(item => item.donationId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加捐款信息记录";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _donationId = row.donationId || ids.value
  getDonation(_donationId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改捐款信息记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["donationRef"].validate(valid => {
    if (valid) {
      if (form.value.donationId != null) {
        updateDonation(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addDonation(form.value).then(response => {
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
  const _donationIds = row.donationId || ids.value;
  proxy.$modal.confirm('是否确认删除捐款信息记录编号为"' + _donationIds + '"的数据项？').then(function() {
    return delDonation(_donationIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('charity/donation/export', {
    ...queryParams.value
  }, `donation_${new Date().getTime()}.xlsx`)
}

getList();
</script>
