<script setup>

</script>

<template>
  <div class="card">
    <div class="image">
      <img src="../../assets/images/img.png" alt="" style="border-radius: 50px;">
    </div>
    <div class="card-info">
      <span>George Johnson</span>
      <p>Support Specialist</p>
    </div>
    <a href="#" class="button">Folow</a>
  </div>
</template>

<style scoped>
.card {
  width: 350px;
  height: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  gap: 10px;
  /*background-color: rgba(255, 255, 254, 0.8); !* 使用半透明背景颜色 *!*/
  border-radius: 15px;
  position: relative;
  overflow: hidden;
}

.card::before {
  content: "";
  width: 350px;
  height: 100px;
  position: absolute;
  top: 0;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  border-bottom: 3px solid #fefefe;
  background-color: #0093E9;
  background-image: linear-gradient(160deg, #0093E9 0%, #80D0C7 100%);
  transition: all 0.5s ease;
}

.card * {
  z-index: 1;
}

.image {
  width: 85px;
  height: 85px;
  border-radius: 50%;
  border: 4px solid #fefefe;
  margin-top: 30px;
  transition: all 0.5s ease;
}

.card-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
  transition: all 0.5s ease;
}

.card-info span {
  font-weight: 600;
  font-size: 24px;
  color: #161A42;
  margin-top: 15px;
  line-height: 5px;
}

.card-info p {
  color: rgba(0, 0, 0, 0.5);
}

.button {
  text-decoration: none;
  background-color: #1468BF;
  color: white;
  padding: 5px 20px;
  border-radius: 5px;
  border: 1px solid white;
  transition: all 0.5s ease;
}

.card:hover::before {
  width: 350px;
  height: 300px;
  border-bottom: none;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  transform: scale(0.95);
}

.card:hover .card-info {
  transform: translate(0%,-25%);
}

.card:hover .image {
  transform: scale(2) translate(-60%,-40%);
}

.button:hover {
  background-color: #FF6844;
  transform: scale(1.1);
}
</style>
