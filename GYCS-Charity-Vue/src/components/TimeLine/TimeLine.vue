<script setup>

</script>
<template>
  <div class="container">
    <div class="timeline-block timeline-block-right">
      <div class="marker"></div>
      <div class="timeline-content">
        <h3>First Year</h3>
        <span>Some work experience</span>
        <p>Some work experience</p>
      </div>
    </div>
    <div class="timeline-block timeline-block-right">
      <div class="marker"></div>
      <div class="timeline-content">
        <h3>Seconed Year</h3>
        <span>Some work experience</span>
        <p>Some work experience</p>
      </div>
    </div>

    <div class="timeline-block timeline-block-right">
      <div class="marker"></div>
      <div class="timeline-content">
        <h3>Third Year</h3>
        <span>Some work experience</span>
        <p>Some work experience</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
h1 {
  text-align: center;
  font-weight: 300;
  color: #777;
}

h1 span {
  font-weight: 600;
}

.container {
  width: 80%;
  margin: 5px -35%;
  position: relative;
  overflow: hidden;

}

.container:before {
  content: "";
  position: absolute;
  top: 0;
  left: 50%;
  margin-left: -1px;
  width: 2px;
  height: 100%;
  background: #ccd1d9;
  z-index: 1;
}

.timeline-block {
  width: -webkit-calc(50% + 8px);
  width: -moz-calc(50% + 8px);
  width: calc(50% + 8px);
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-box;
  display: flex;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  -moz-box-pack: justify;
  justify-content: space-between;
  clear: both;
}

.timeline-block-right {
  float: right;
}


.marker {
  width: 16px;
  height: 16px;
  border-radius: 50%;
  border: 2px solid #f5f7fa;
  background: #4fc1e9;
  margin-top: 10px;
  z-index: 9999;
}

.timeline-content {
  width: 95%;
  padding: 0 15px;
  color: #666;
}

.timeline-content h3 {
  margin-top: 5px;
  margin-bottom: 5px;
  font-size: 25px;
  font-weight: 500;
}

.timeline-content span {
  font-size: 15px;
  color: #a4a4a4;
}

.timeline-content p {
  font-size: 14px;
  line-height: 1.5em;
  word-spacing: 1px;
  color: #888;
}

@media screen and (max-width: 768px) {
  .container:before {
    left: 8px;
    width: 2px;
  }
  .timeline-block {
    width: 100%;
    margin-bottom: 30px;
  }
  .timeline-block-right {
    float: none;
  }

}

</style>
