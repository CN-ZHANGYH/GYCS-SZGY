<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物资名称" prop="materialName">
        <el-input
          v-model="queryParams.materialName"
          placeholder="请输入物资的名称"
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
          v-hasPermi="['charity:activite:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['charity:activite:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['charity:activite:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['charity:activite:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="activiteList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="溯源ID" align="center" prop="charityId" />
      <el-table-column label="种类" align="center" prop="materialType" />
      <el-table-column label="物资的名称" align="center" prop="materialName" width="150px"/>
      <el-table-column label="物资的数量" align="center" prop="materialCount" />
      <el-table-column label="源地址" align="center" prop="sourceAddress" width="350px"/>
      <el-table-column label="物流商地址" align="center" prop="logisticAddress" width="350px"/>
      <el-table-column label="目标地址" align="center" prop="destAddress" width="350px"/>
      <el-table-column label="交易时间" align="center" prop="transTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.transTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否签收" align="center" prop="isSign">
        <template #default="scope">
          <el-tag :type="scope.row.isSign == false ? 'danger' : 'success'">
            {{scope.row.isSign == false ? "未签收" : "已签收"}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="公益ID" align="center" prop="activitId" />
      <el-table-column label="溯源状态" align="center" prop="status" />
<!--      <el-table-column label="溯源的地址" align="center" prop="traceAddress" />
      <el-table-column label="溯源的时间" align="center" prop="traceTime" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200px">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['charity:activite:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['charity:activite:remove']">删除</el-button>
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

    <!-- 添加或修改公益活动溯源记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="activiteRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物资名称" prop="materialName">
          <el-input v-model="form.materialName" placeholder="请输入物资的名称" />
        </el-form-item>
        <el-form-item label="物资数量" prop="materialCount">
          <el-input v-model="form.materialCount" placeholder="请输入物资的数量" />
        </el-form-item>
        <el-form-item label="源地址" prop="sourceAddress">
          <el-input v-model="form.sourceAddress" placeholder="请输入源地址" />
        </el-form-item>
        <el-form-item label="物流地址" prop="logisticAddress">
          <el-input v-model="form.logisticAddress" placeholder="请输入物流商地址" />
        </el-form-item>
        <el-form-item label="目标地址" prop="destAddress">
          <el-input v-model="form.destAddress" placeholder="请输入目标地址" />
        </el-form-item>
        <el-form-item label="交易时间" prop="transTime">
          <el-date-picker clearable
            v-model="form.transTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择交易时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否签收" prop="isSign">
          <el-input v-model="form.isSign" placeholder="请输入是否签收" />
        </el-form-item>
        <el-form-item label="公益ID" prop="activitId">
          <el-input v-model="form.activitId" placeholder="请输入公益活动ID" />
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

<script setup name="Activite">
import { listActivite, getActivite, delActivite, addActivite, updateActivite } from "@/api/charity/activite";
import {parseTime} from "../../../utils/ruoyi.js";

const { proxy } = getCurrentInstance();

const activiteList = ref([]);
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
    materialType: null,
    materialName: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询公益活动溯源记录列表 */
function getList() {
  loading.value = true;
  listActivite(queryParams.value).then(response => {
    activiteList.value = response.rows;
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
    charityId: null,
    materialType: null,
    materialName: null,
    materialCount: null,
    sourceAddress: null,
    logisticAddress: null,
    destAddress: null,
    transTime: null,
    isSign: null,
    activitId: null,
    status: null,
    traceAddress: null,
    traceTime: null
  };
  proxy.resetForm("activiteRef");
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
  ids.value = selection.map(item => item.charityId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加公益活动溯源记录";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _charityId = row.charityId || ids.value
  getActivite(_charityId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改公益活动溯源记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["activiteRef"].validate(valid => {
    if (valid) {
      if (form.value.charityId != null) {
        updateActivite(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addActivite(form.value).then(response => {
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
  const _charityIds = row.charityId || ids.value;
  proxy.$modal.confirm('是否确认删除公益活动溯源记录编号为"' + _charityIds + '"的数据项？').then(function() {
    return delActivite(_charityIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('charity/activite/export', {
    ...queryParams.value
  }, `activite_${new Date().getTime()}.xlsx`)
}

getList();
</script>
