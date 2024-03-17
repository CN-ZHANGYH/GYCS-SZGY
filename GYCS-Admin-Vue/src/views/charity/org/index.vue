<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="机构名称" prop="orgName">
        <el-input
          v-model="queryParams.orgName"
          placeholder="请输入机构的名称"
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
          v-hasPermi="['charity:org:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['charity:org:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['charity:org:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['charity:org:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orgList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="机构ID" align="center" prop="id" />
      <el-table-column label="机构的名称" align="center" prop="orgName" />
      <el-table-column label="机构的账户余额" align="center" prop="amount" />
      <el-table-column label="灾区活动发起记录ID" align="center" prop="activitiesList" />
      <el-table-column label="用户地址" align="center" prop="orgAddress">
        <template #default="scope">
          <el-popover
              placement="top-start"
              title="区块链账户"
              :width="350"
              trigger="hover"
              :content="scope.row.orgAddress"
          >
            <template #reference>
              <el-button class="m-2">查看区块链地址</el-button>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="用户私钥" align="center" prop="privateKey">
        <template #default="scope">
          <el-popover
              placement="top-start"
              title="用户私钥"
              :width="350"
              trigger="hover"
              :content="scope.row.privateKey"
          >
            <template #reference>
              <el-button class="m-2">查看私钥</el-button>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="用户公钥" align="center" prop="publicKey">
        <template #default="scope">
          <el-popover
              placement="top-start"
              title="用户公钥"
              :width="600"
              trigger="hover"
              :content="scope.row.publicKey"
          >
            <template #reference>
              <el-button class="m-2">查看公钥</el-button>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['charity:org:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['charity:org:remove']">删除</el-button>
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

    <!-- 添加或修改机构用户信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="orgRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="机构名称" prop="orgName">
          <el-input v-model="form.orgName" placeholder="请输入机构的名称" />
        </el-form-item>
        <el-form-item label="机构余额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入机构的账户余额" />
        </el-form-item>
        <el-form-item label="机构地址" prop="orgAddress">
          <el-input v-model="form.orgAddress" placeholder="请输入机构的地址" disabled/>
        </el-form-item>
        <el-form-item label="用户私钥" prop="privateKey">
          <el-input v-model="form.privateKey" placeholder="请输入用户的私钥" disabled/>
        </el-form-item>
        <el-form-item label="用户公钥" prop="publicKey">
          <el-input v-model="form.publicKey" placeholder="请输入用户的公钥"  type="textarea" :rows="3" disabled/>
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

<script setup name="Org">
import { listOrg, getOrg, delOrg, addOrg, updateOrg } from "@/api/charity/org";

const { proxy } = getCurrentInstance();

const orgList = ref([]);
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
    orgName: null,
    amount: null,
    activitiesList: null,
    orgAddress: null,
    privateKey: null,
    publicKey: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询机构用户信息列表 */
function getList() {
  loading.value = true;
  listOrg(queryParams.value).then(response => {
    orgList.value = response.rows;
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
    orgName: null,
    amount: null,
    activitiesList: null,
    orgAddress: null,
    privateKey: null,
    publicKey: null
  };
  proxy.resetForm("orgRef");
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
  title.value = "添加机构用户信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getOrg(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改机构用户信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["orgRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateOrg(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addOrg(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除机构用户信息编号为"' + _ids + '"的数据项？').then(function() {
    return delOrg(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('charity/org/export', {
    ...queryParams.value
  }, `org_${new Date().getTime()}.xlsx`)
}

getList();
</script>
