<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动名称" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入活动名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代理人地址" prop="lncomeAddress">
        <el-input
          v-model="queryParams.lncomeAddress"
          placeholder="请输入代理人地址"
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
          v-hasPermi="['charity:activitieInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['charity:activitieInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['charity:activitieInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['charity:activitieInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="activitieInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动ID" align="center" prop="id" />
      <el-table-column label="活动名称" align="center" prop="title" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="物流方式" align="center" prop="logisticType" />
      <el-table-column label="物流商地址" align="center" prop="logisticAddress">
        <template #default="scope">
          <el-popover
              placement="top-start"
              title="物流商地址"
              :width="350"
              trigger="hover"
              :content="scope.row.logisticAddress"
          >
            <template #reference>
              <el-button class="m-2">查看物流商</el-button>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="代理人地址" align="center" prop="lncomeAddress">
        <template #default="scope">
          <el-popover
              placement="top-start"
              title="代理人地址"
              :width="350"
              trigger="hover"
              :content="scope.row.lncomeAddress"
          >
            <template #reference>
              <el-button class="m-2">查看代理机构</el-button>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="总参与人数" align="center" prop="totalPeople" />
      <el-table-column label="总物资数量" align="center" prop="totalMaterias" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['charity:activitieInfo:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['charity:activitieInfo:remove']">删除</el-button>
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

    <!-- 添加或修改公益灾区捐赠活动对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="activitieInfoRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="物流商地址" prop="logisticAddress">
          <el-input v-model="form.logisticAddress" placeholder="请输入物流商地址" />
        </el-form-item>
        <el-form-item label="代理人地址" prop="lncomeAddress">
          <el-input v-model="form.lncomeAddress" placeholder="请输入代理人地址" />
        </el-form-item>
        <el-form-item label="总参与人数" prop="totalPeople">
          <el-input v-model="form.totalPeople" placeholder="请输入总参与人数" />
        </el-form-item>
        <el-form-item label="总物资数量" prop="totalMaterias">
          <el-input v-model="form.totalMaterias" placeholder="请输入总物资数量" />
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

<script setup name="ActivitieInfo">
import { listActivitieInfo, getActivitieInfo, delActivitieInfo, addActivitieInfo, updateActivitieInfo } from "@/api/charity/activitieInfo";

const { proxy } = getCurrentInstance();

const activitieInfoList = ref([]);
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
    title: null,
    lncomeAddress: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询公益灾区捐赠活动列表 */
function getList() {
  loading.value = true;
  listActivitieInfo(queryParams.value).then(response => {
    activitieInfoList.value = response.rows;
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
    title: null,
    createTime: null,
    startTime: null,
    endTime: null,
    logisticType: null,
    logisticAddress: null,
    lncomeAddress: null,
    status: null,
    totalPeople: null,
    totalMaterias: null
  };
  proxy.resetForm("activitieInfoRef");
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
  title.value = "添加公益灾区捐赠活动";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getActivitieInfo(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改公益灾区捐赠活动";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["activitieInfoRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateActivitieInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addActivitieInfo(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除公益灾区捐赠活动编号为"' + _ids + '"的数据项？').then(function() {
    return delActivitieInfo(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('charity/activitieInfo/export', {
    ...queryParams.value
  }, `activitieInfo_${new Date().getTime()}.xlsx`)
}

getList();
</script>
