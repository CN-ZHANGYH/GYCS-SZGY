<script setup>

import Footer from "@/components/Layout/Footer.vue";
</script>

<template>
  <!-- Preloader -->
  <div class="preloader">
    <div class="preloader-inner">
      <div class="preloader-icon">
        <span></span>
        <span></span>
      </div>
    </div>
  </div>
  <!-- /End Preloader -->

  <!-- Start Header Area -->
  <header class="header navbar-area">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-12">
          <div class="nav-inner">
            <!-- Start Navbar -->
            <nav class="navbar navbar-expand-lg">
              <a class="navbar-brand" href="index.html">
                <img src="../../../assets/product/images/logo/white-logo.png" alt="Logo">
              </a>
              <button class="navbar-toggler mobile-menu-btn" type="button" data-bs-toggle="collapse"
                      data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                      aria-expanded="false" aria-label="Toggle navigation">
                <span class="toggler-icon"></span>
                <span class="toggler-icon"></span>
                <span class="toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse sub-menu-bar" id="navbarSupportedContent">
                <ul id="nav" class="navbar-nav ms-auto">
                  <li class="nav-item">
                    <a href="/" aria-label="Toggle navigation">首页</a>
                  </li>
                  <li class="nav-item">
                    <a href="/#features" aria-label="Toggle navigation">特点</a>
                  </li>
                  <li class="nav-item">
                    <a href="/#overview" aria-label="Toggle navigation">概述</a>
                  </li>
                  <li class="nav-item">
                    <a href="/#pricing" aria-label="Toggle navigation">公益</a>
                  </li>
                  <li class="nav-item">
                    <a href="/#team" aria-label="Toggle navigation">团队</a>
                  </li>
                  <li class="nav-item">
                    <a href="/about" class="active" aria-label="Toggle navigation">关于我们</a>
                  </li>
                </ul>
              </div> <!-- navbar collapse -->
            </nav>
            <!-- End Navbar -->
          </div>
        </div>
      </div> <!-- row -->
    </div> <!-- container -->
  </header>
  <!-- End Header Area -->

  <!-- Start Breadcrumbs -->
  <div class="breadcrumbs">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 offset-lg-2 col-md-12 col-12">
          <div class="breadcrumbs-content">
            <h1 class="page-title">关于我们</h1>
          </div>
          <ul class="breadcrumb-nav">
            <li><a href="/home">首页</a></li>
            <li>关于我们</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <!-- End Breadcrumbs -->

  <!-- Start Contact Area -->
  <section id="contact-us" class="contact-us section">
    <div class="container">
      <div class="contact-head wow fadeInUp" data-wow-delay=".4s">
        <div class="row">
          <div class="col-12">
            <div class="section-title">
              <h3 class="wow zoomIn" data-wow-delay=".2s">联系我们</h3>
              <h2 class="wow fadeInUp" data-wow-delay=".4s">什么样的群体可以使用我们的平台</h2>
              <p class="wow fadeInUp" data-wow-delay=".6s">任何需要进行公益慈善参与与发起的人都可以使用该平台，所有的公益记录都是公开、透明、不可篡改、真实可信的。</p>
            </div>
          </div>
        </div>
        <div class="contact-info">
          <div class="row">
            <div class="col-lg-4 col-md-12 col-12">
              <div class="single-info">
                <i class="lni lni-question-circle"></i>
                <h3>解决什么问题？</h3>
                <ul>
                  <li>慈善公益是社会发展中不可或缺的一部分，然而，传统的慈善捐款和公益活动存在一些问题，如捐款信息的安全性和透明度难以保证，以及捐款过程中的效率和透明度不足。为了解决这些问题，基于区块链的慈善公益系统应运而生。</li>
                </ul>
              </div>
            </div>
            <div class="col-lg-4 col-md-12 col-12">
              <div class="single-info">
                <i class="lni lni-layers"></i>
                <h3>有哪些特性？</h3>
                <ul>
                  <li>基于区块链的慈善公益系统利用区块链技术的去中心化、不可篡改和安全可靠等特点，能够确保慈善捐款信息不被篡改，提高捐款过程中的效率和透明度。区块链技术能够记录和追踪每一笔捐款交易，使其变得透明可信。</li>
                </ul>
              </div>
            </div>
            <div class="col-lg-4 col-md-12 col-12">
              <div class="single-info">
                <i class="lni lni-star-half"></i>
                <h3>哪些案例场景？</h3>
                <ul>
                  <li>
                    基于区块链的慈善系统适用于各公益场景，保障捐款信息安全、透明。例如，灾区救援中，支持公益活动和物资捐赠，记录物流和募资过程，能够查询完整的溯源生命周期包括对溯源的追踪，实现操作透明。</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!--/ End Contact Area -->

  <!-- Start Contact Form Head Area -->
  <div class="contact-form-head section">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 col-md-12 col-12">
          <div class="contact-inner-title">
            <h2>与我们合作? <br>发送消息给我们</h2>
            <p>请将您的问题或顾虑，添加留言消息发送给我们</p>
            <div class="question">
              <img src="../../../assets/picture/zyh.jpg" alt="#">
              <h4><span>联系电话</span>
                +86 18933200910
              </h4>
            </div>
            <ul class="day-list">
              <li>星期一 - 星期五:</li>
              <li>9.00 - 6.00</li>
              <li>周日 & 公共节假日 (Closed)
              </li>
            </ul>
          </div>
        </div>
        <div class="col-lg-6 col-md-12 col-12">
          <div class="form-main">
            <form class="form" method="post" action="../../../assets/product/mail/mail.php">
              <div class="row">
                <div class="col-lg-6 col-12">
                  <div class="form-group">
                    <input name="name" type="text" placeholder="你的名字" required="required">
                  </div>
                </div>
                <div class="col-lg-6 col-12">
                  <div class="form-group">
                    <input name="subject" type="text" placeholder="你的主题"
                           required="required">
                  </div>
                </div>
                <div class="col-lg-6 col-12">
                  <div class="form-group">
                    <input name="email" type="email" placeholder="你的邮箱" required="required">
                  </div>
                </div>
                <div class="col-lg-6 col-12">
                  <div class="form-group">
                    <input name="phone" type="text" placeholder="你的手机" required="required">
                  </div>
                </div>
                <div class="col-12">
                  <div class="form-group message">
                    <textarea name="message" placeholder="你的信息"></textarea>
                  </div>
                </div>
                <div class="col-12">
                  <div class="form-group button">
                    <button type="submit" class="btn ">提交留言</button>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- End Contact Form Head Area -->


  <!-- Start Footer Area -->
  <Footer/>
  <!--/ End Footer Area -->

  <!-- ========================= scroll-top ========================= -->
  <a href="#" class="scroll-top">
    <i class="lni lni-chevron-up"></i>
  </a>
</template>

<style lang="scss" scoped>
@import "../../../assets/product/css/bootstrap.min.css";
@import "../../../assets/product/css/LineIcons.2.0.css";
@import "../../../assets/product/css/animate.css";
@import "../../../assets/product/css/tiny-slider.css";
@import "../../../assets/product/css/glightbox.min.css";
@import "../../../assets/product/css/main.css";


.mapouter {
  position: relative;
  text-align: right;
  height: 500px;
  width: 100%;
}

.gmap_canvas {
  overflow: hidden;
  background: none !important;
  height: 500px;
  width: 100%;
}
</style>

