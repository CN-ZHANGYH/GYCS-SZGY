<template>
  <div class="cards">
    <div class="card blue">
      <div class="footer-content">
        <div>
          <div>深圳职业技术大学</div>
          <br>
          <div>地址：深圳市南山区沙河西路4089号</div>
          <br>
          <div>26011652(3652) 26018468(5468)</div>
        </div>
        <div>
          <div>Github: https://github.com/</div>
          <br>
          <div>邮箱：2756864799@qq.com</div>
          <br>
          <div>2024 © GYCS-SZGY Platform.</div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.cards {
  display: flex;
  flex-direction: column;
  gap: 15px;
}
.cards .blue {
  background-color: #3b82f6;
}
.cards .card {
  padding: 30px 20%;
  flex-direction: column;
  text-align: center;
  height: 200px;
  width: 100%;
  color: white;
  cursor: pointer;
  transition: 400ms;
}
.footer-content {
  display: flex;
  justify-content: space-between;
}

</style>
<script setup>
</script>