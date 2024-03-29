<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名称" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入用户的名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证ID" prop="cardId">
        <el-input
          v-model="queryParams.cardId"
          placeholder="请输入身份证ID"
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
          v-hasPermi="['charity:charityuser:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['charity:charityuser:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['charity:charityuser:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['charity:charityuser:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="charityuserList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" align="center" prop="id" />
      <el-table-column label="用户名称" align="center" prop="username" />
      <el-table-column label="用户余额" align="center" prop="amount" />
      <el-table-column label="用户积分" align="center" prop="credit" />
      <el-table-column label="身份证ID" align="center" prop="cardId" />
      <el-table-column label="用户称号" align="center" prop="designation" />
      <el-table-column label="投票次数" align="center" prop="voteCount" />
      <el-table-column label="提现次数" align="center" prop="withdrawCount" />
      <el-table-column label="用户地址" align="center" prop="userAddress">
        <template #default="scope">
          <el-popover
              placement="top-start"
              title="区块链账户"
              :width="350"
              trigger="hover"
              :content="scope.row.userAddress"
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
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['charity:charityuser:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['charity:charityuser:remove']">删除</el-button>
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

    <!-- 添加或修改用户对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="charityuserRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户的名称" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户的名称" />
        </el-form-item>
        <el-form-item label="用户的余额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入用户的余额" />
        </el-form-item>
        <el-form-item label="用户积分" prop="credit">
          <el-input v-model="form.credit" placeholder="请输入用户积分" />
        </el-form-item>
        <el-form-item label="身份证ID" prop="cardId">
          <el-input v-model="form.cardId" placeholder="请输入身份证ID" />
        </el-form-item>
        <el-form-item label="用户称号" prop="designation">
          <el-input v-model="form.designation" placeholder="请输入用户称号" />
        </el-form-item>
        <el-form-item label="参与投票的次数" prop="voteCount">
          <el-input v-model="form.voteCount" placeholder="请输入参与投票的次数" />
        </el-form-item>
        <el-form-item label="提现的次数" prop="withdrawCount">
          <el-input v-model="form.withdrawCount" placeholder="请输入提现的次数" />
        </el-form-item>
        <el-form-item label="用户的地址" prop="userAddress">
          <el-input v-model="form.userAddress" placeholder="请输入用户的地址" />
        </el-form-item>
        <el-form-item label="用户的私钥" prop="privateKey">
          <el-input v-model="form.privateKey" placeholder="请输入用户的私钥" />
        </el-form-item>
        <el-form-item label="用户的公钥" prop="publicKey">
          <el-input v-model="form.publicKey" placeholder="请输入用户的公钥" />
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

<script setup name="Charityuser">
import { listCharityuser, getCharityuser, delCharityuser, addCharityuser, updateCharityuser } from "@/api/charity/charityuser.js";

const { proxy } = getCurrentInstance();

const charityuserList = ref([]);
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
    username: null,
    cardId: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询用户列表 */
function getList() {
  loading.value = true;
  listCharityuser(queryParams.value).then(response => {
    charityuserList.value = response.rows;
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
    username: null,
    amount: null,
    credit: null,
    cardId: null,
    designation: null,
    voteCount: null,
    withdrawCount: null,
    userAddress: null,
    privateKey: null,
    publicKey: null
  };
  proxy.resetForm("charityuserRef");
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
  title.value = "添加用户";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCharityuser(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改用户";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["charityuserRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCharityuser(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCharityuser(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除用户编号为"' + _ids + '"的数据项？').then(function() {
    return delCharityuser(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('charity/charityuser/export', {
    ...queryParams.value
  }, `charityuser_${new Date().getTime()}.xlsx`)
}

getList();
</script>
