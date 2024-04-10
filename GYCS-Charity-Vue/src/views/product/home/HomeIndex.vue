<script>

import {defineComponent} from "vue";
import Footer from "@/components/Layout/Footer.vue";

export default defineComponent({
  components: {Footer}
})
</script>

<template>
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
              <a class="navbar-brand" href="/">
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
                    <a href="#home" class="page-scroll active"
                       aria-label="Toggle navigation">首页</a>
                  </li>
                  <li class="nav-item">
                    <a href="#features" class="page-scroll"
                       aria-label="Toggle navigation">特点</a>
                  </li>
                  <li class="nav-item">
                    <a href="#overview" class="page-scroll"
                       aria-label="Toggle navigation">概述</a>
                  </li>
                  <li class="nav-item">
                    <a href="#pricing" class="page-scroll"
                       aria-label="Toggle navigation">公益</a>
                  </li>
                  <li class="nav-item">
                    <a href="#team" class="page-scroll" aria-label="Toggle navigation">团队</a>
                  </li>
                  <li class="nav-item">
                    <a href="/about"  aria-label="Toggle navigation">关于我们</a>
                  </li>
                </ul>
              </div> <!-- navbar collapse -->
              <div class="button add-list-button">
                <a href="/login" class="btn">登录</a>
              </div>
            </nav>
            <!-- End Navbar -->
          </div>
        </div>
      </div> <!-- row -->
    </div> <!-- container -->
  </header>
  <!-- End Header Area -->

  <!-- Start Hero Area -->
  <section id="home" class="hero-area">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-5 col-md-12 col-12">
          <div class="hero-content">
            <h1 class="wow fadeInLeft" data-wow-delay=".4s">为公益提供更透明、更安全的交易和溯源</h1>
            <p class="wow fadeInLeft" data-wow-delay=".6s">融合区块链技术的数字公益产品，通过更完整更透明真实可信的溯源记录，保障每一笔交易的安全。</p>
            <div class="button wow fadeInLeft" data-wow-delay=".8s">
              <a href="https://giv.drill.wiki" class="btn">快速开始</a>
              <a href="#"
                 class="glightbox video-button"><span class="video"><i
                  class="lni lni-play"></i></span><span class="text">介绍</span></a>
            </div>
          </div>
        </div>
        <div class="col-lg-7 col-md-12 col-12">
          <div class="hero-image wow fadeInRight" data-wow-delay=".4s">
            <img src="../../../assets/product/images/hero/laptop.png" alt="#" style="border-radius: 10px">
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Hero Area -->

  <!-- Start Features Area -->
  <section id="features" class="features section">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="section-title">
            <h3 class="wow zoomIn" data-wow-delay=".2s">项目特点</h3>
            <h2 class="wow fadeInUp" data-wow-delay=".4s">随着时间的迁移，你的体验会变得越来越好
            </h2>
            <p class="wow fadeInUp" data-wow-delay=".6s">GivTech一直在尝试如何把公益慈善的交易透明度做到更好，所以融入了FISCO BCOS联盟链，也在让应用变得更轻量。</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-4 col-md-6 col-12">
          <!-- Start Single Feature -->
          <div class="single-feature wow fadeInUp" data-wow-delay=".2s">
            <i class="lni lni-cloud-upload"></i>
            <h3>持续集成</h3>
            <p>使用GitHub Actions作为CI/CD工具，实现了自动化测试、构建和部署，为项目的持续集成和交付提供了高效可靠的支持
            </p>
          </div>
          <!-- End Single Feature -->
        </div>
        <div class="col-lg-4 col-md-6 col-12">
          <!-- Start Single Feature -->
          <div class="single-feature wow fadeInUp" data-wow-delay=".4s">
            <i class="lni lni-dropbox-original"></i>
            <h3>轻量便捷</h3>
            <p>使用Docker容器化技术实现了项目的部署，使得应用程序在开发、测试和部署过程中更加轻便、灵活和可移植，极大地提升了开发效率和部署的便捷性</p>
          </div>
          <!-- End Single Feature -->
        </div>
        <div class="col-lg-4 col-md-6 col-12">
          <!-- Start Single Feature -->
          <div class="single-feature wow fadeInUp" data-wow-delay=".6s">
            <i class="lni lni-reload"></i>
            <h3>去中心化</h3>
            <p>集成了区块链技术，实现了项目的去中心化架构，为数据安全性和可信性提供了强大支持，确保了系统的高度透明性和抗审查性</p>
          </div>
          <!-- End Single Feature -->
        </div>
        <div class="col-lg-4 col-md-6 col-12">
          <!-- Start Single Feature -->
          <div class="single-feature wow fadeInUp" data-wow-delay=".2s">
            <i class="lni lni-shield"></i>
            <h3>透明安全</h3>
            <p>整合了区块链技术，确保捐赠流程以及所有的公益慈善交易记录的透明性和安全性，为公益事业提供了可靠的支持和保障</p>
          </div>
          <!-- End Single Feature -->
        </div>
        <div class="col-lg-4 col-md-6 col-12">
          <!-- Start Single Feature -->
          <div class="single-feature wow fadeInUp" data-wow-delay=".4s">
            <i class="lni lni-lock-alt"></i>
            <h3>不可篡改</h3>
            <p>融合了智能合约的不可篡改特性，利用区块链技术确保了数据的永久性和透明性，进一步提升了项目的可信度和稳定性</p>
          </div>
          <!-- End Single Feature -->
        </div>
        <div class="col-lg-4 col-md-6 col-12">
          <!-- Start Single Feature -->
          <div class="single-feature wow fadeInUp" data-wow-delay=".6s">
            <i class="lni lni-joomla"></i>
            <h3>溯源追踪</h3>
            <p>利用区块链技术实现了溯源追踪功能，通过智能合约确保数据不可篡改，使得产品的生产、流通和销售过程具备了可追溯性和可验证性</p>
          </div>
          <!-- End Single Feature -->
        </div>
      </div>
    </div>
  </section>
  <!-- End Features Area -->

  <!-- Start App Info Area -->
  <section id="overview" class="app-info section">
    <div class="container">
      <div class="info-one">
        <div class="row align-items-center">
          <div class="col-lg-6 col-md-12 col-12">
            <!-- Start Info Text -->
            <div class="info-text wow fadeInLeft" data-wow-delay=".3s">
              <div class="main-icon">
                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" fill="none" version="1.1" style="width: 50px;height: 50px" viewBox="0 0 523.90087890625 812.968017578125"><g><g><g><g style="opacity:0.44999998807907104;"><path d="M391.93,0.0000152588L261.226,232.302L0,232.302L130.614,0L391.93,0.0000152588Z" fill="#000000" fill-opacity="1"/></g><g style="opacity:0.6000000238418579;"><path d="M261.2259984130859,232.30201525878905L522.543998413086,232.30201525878905L391.9299984130859,0.0000152587890625L130.61399841308594,0.0000152587890625L261.2259984130859,232.30201525878905Z" fill="#000000" fill-opacity="1"/></g><g style="opacity:0.800000011920929;"><path d="M130.614,464.5140152587891L261.226,232.30201525878905L130.614,0.0000152587890625L0,232.30201525878905L130.614,464.5140152587891Z" fill="#000000" fill-opacity="1"/></g><g style="opacity:0.44999998807907104;"><path d="M131.87899780273438,812.9670625L262.5829978027344,580.6640625L523.9009978027343,580.6640625L393.1959978027344,812.9670625L131.87899780273438,812.9670625Z" fill="#000000" fill-opacity="1"/></g><g style="opacity:0.6000000238418579;"><path d="M262.5820012969971,580.6650390625L1.2650012969970703,580.6650390625L131.87800129699707,812.9680390625L393.1950012969971,812.9680390625L262.5820012969971,580.6650390625Z" fill="#000000" fill-opacity="1"/></g><g style="opacity:0.800000011920929;"><path d="M393.1960007324219,348.4530029296875L262.5820007324219,580.6650029296875L393.1960007324219,812.9680029296875L523.9000007324219,580.6650029296875L393.1960007324219,348.4530029296875Z" fill="#000000" fill-opacity="1"/></g></g></g></g></svg>
              </div>
              <h2>FISCO BCOS + WeBASE + Solidity</h2>
              <p>项目采用了FISCO BCOS作为底层区块链技术，结合了WeBASE作为区块链开发和管理平台，以及Solidity智能合约语言，构建了一个强大而高效的区块链解决方案。这一技术组合不仅保障了项目的安全性和可扩展性。</p>
              <div class="button">
                <a href="javascript:void(0)" class="btn">快速开始</a>
              </div>
            </div>
            <!-- End Info Text -->
          </div>
          <div class="col-lg-6 col-md-12 col-12">
            <div class="info-image wow fadeInRight" data-wow-delay=".5s">
              <img class="ss1" src="../../../assets/product/images/app-ss/app-ss1.png" alt="#">
            </div>
          </div>
        </div>
      </div>
      <div class="info-one style2">
        <div class="row align-items-center">
          <div class="col-lg-6 col-md-12 col-12">
            <div class="info-image wow fadeInLeft" data-wow-delay=".3s">
              <img class="ss1" src="../../../assets/product/images/app-ss/app-ss2.png" alt="#">
            </div>
          </div>
          <div class="col-lg-6 col-md-12 col-12">
            <!-- Start Info Text -->
            <div class="info-text wow fadeInRight" data-wow-delay=".5s">
              <div class="main-icon">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" version="1.1" style="width: 50px;height: 50px" viewBox="0 0 811 766"><defs><clipPath id="master_svg0_11_17"><rect x="0" y="0" width="811" height="766" rx="0"/></clipPath></defs><g clip-path="url(#master_svg0_11_17)"><g><path d="M499.117,0.00337906L405.472,176.901L311.83,0.00337906L-0.0154913,0.00337906L405.475,765.997L810.966,0.00337906L499.117,0.00337906Z" fill="#41B883" fill-opacity="1"/></g><g><path d="M499.11715234375,0.00341796875L405.47215234375,176.90041796875L311.83015234375,0.00341796875L162.18115234375,0.00341796875L405.47515234375,459.58941796875L648.76915234375,0.00341796875L499.11715234375,0.00341796875Z" fill="#34495E" fill-opacity="1"/></g></g></svg>
              </div>
              <h2>Vue3 + Vuesax4.0 + SpringBoot2</h2>
              <p>
                项目采用了Vue3和Vuesax 4.0，展现了精美、现代化的用户界面设计，搭配Spring Boot 2作为后端框架，为用户提供了优质的视觉体验，同时确保了项目的稳定性和安全性。
              </p>
              <div class="button">
                <a href="javascript:void(0)" class="btn">快速开始</a>
              </div>
            </div>
            <!-- End Info Text -->
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End App Info Area -->

  <!-- Start Achievement Area -->
  <section class="our-achievement section">
    <div class="container">
      <div class="row">
        <div class="col-lg-10 offset-lg-1 col-md-12 col-12">
          <div class="title">
            <h2>欢迎更多的开发者加入我们不断的优化我们的产品</h2>
            <p>不管你是区块链的开发者还是后端开发者，你要你喜欢我们的产品都可以加入我们</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-8 offset-lg-2 col-md-12 col-12">
          <div class="row">
            <div class="col-lg-4 col-md-4 col-12">
              <div class="single-achievement wow fadeInUp" data-wow-delay=".2s">
                <h3 class="counter"><span id="secondo1" class="countup" cup-end="100">100</span>%</h3>
                <p>满意程度</p>
              </div>
            </div>
            <div class="col-lg-4 col-md-4 col-12">
              <div class="single-achievement wow fadeInUp" data-wow-delay=".4s">
                <h3 class="counter"><span id="secondo2" class="countup" cup-end="120">100</span>%</h3>
                <p>技术体系</p>
              </div>
            </div>
            <div class="col-lg-4 col-md-4 col-12">
              <div class="single-achievement wow fadeInUp" data-wow-delay=".6s">
                <h3 class="counter"><span id="secondo3" class="countup" cup-end="125">100</span>%</h3>
                <p>服务质量</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Achievement Area -->

  <!-- Start Pricing Table Area -->
  <section id="pricing" class="pricing-table section">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="section-title">
            <h3 class="wow zoomIn" data-wow-delay=".2s">公益活动</h3>
            <h2 class="wow fadeInUp" data-wow-delay=".4s">灾区物资捐赠</h2>
            <p class="wow fadeInUp" data-wow-delay=".6s">自然灾害难以避免，包括了地震、海啸、火灾等自然灾害，我们呼吁大家一起奉献一份爱心让世界更美好。</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-3 col-md-6 col-12">
          <!-- Single Table -->
          <div class="single-table wow fadeInUp" data-wow-delay=".2s">
            <!-- Table Head -->
            <div class="table-head">
              <img src="../../../assets/product/images/area/image1.png" style="border-radius: 10px">
            </div>
            <!-- End Table Head -->
            <!-- Start Table Content -->
            <div class="table-content">
              <h4 class="middle-title">浸没的村庄</h4>
              <!-- Table List -->
              <ul class="table-list">
                <li><i class="lni lni-checkmark-circle"></i> 需要提供医疗物品</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供食品干粮</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供保暖衣物</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供救助工具</li>
              </ul>
              <!-- End Table List -->
            </div>
            <!-- End Table Content -->
          </div>
          <!-- End Single Table-->
        </div>
        <div class="col-lg-3 col-md-6 col-12">
          <!-- Single Table -->
          <div class="single-table wow fadeInUp" data-wow-delay=".4s">
            <!-- Table Head -->
            <div class="table-head">
              <img src="../../../assets/product/images/area/image2.png" style="border-radius: 10px">
            </div>
            <!-- End Table Head -->
            <!-- Start Table Content -->
            <div class="table-content">
              <h4 class="middle-title">倾倒的轮船</h4>
              <!-- Table List -->
              <ul class="table-list">
                <li><i class="lni lni-checkmark-circle"></i> 需要提供医疗物品</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供食品干粮</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供保暖衣物</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供救助工具</li>
              </ul>
              <!-- End Table List -->
            </div>
            <!-- End Table Content -->
          </div>
          <!-- End Single Table-->
        </div>
        <div class="col-lg-3 col-md-6 col-12">
          <!-- Single Table -->
          <div class="single-table wow fadeInUp" data-wow-delay=".6s">
            <!-- Table Head -->
            <div class="table-head">
              <img src="../../../assets/product/images/area/image3.png" style="border-radius: 10px">
            </div>
            <!-- End Table Head -->
            <!-- Start Table Content -->
            <div class="table-content">
              <h4 class="middle-title">干枯的荒地</h4>
              <!-- Table List -->
              <ul class="table-list">
                <li><i class="lni lni-checkmark-circle"></i> 需要提供食品和水</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供发电工具</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供防晒衣物</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供绿植种子</li>
              </ul>
              <!-- End Table List -->
            </div>
            <!-- End Table Content -->
          </div>
          <!-- End Single Table-->
        </div>
        <div class="col-lg-3 col-md-6 col-12">
          <!-- Single Table -->
          <div class="single-table wow fadeInUp" data-wow-delay=".8s">
            <!-- Table Head -->
            <div class="table-head">
              <img src="../../../assets/product/images/area/image4.png" style="border-radius: 10px">
            </div>
            <!-- End Table Head -->
            <!-- Start Table Content -->
            <div class="table-content">
              <h4 class="middle-title">坍塌的家园</h4>
              <!-- Table List -->
              <ul class="table-list">
                <li><i class="lni lni-checkmark-circle"></i> 需要提供修建工具</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供暂住房屋</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供食品干粮</li>
                <li><i class="lni lni-checkmark-circle"></i> 需要提供发电工具</li>
              </ul>
              <!-- End Table List -->
            </div>
            <!-- End Table Content -->
          </div>
          <!-- End Single Table-->
        </div>
      </div>
    </div>
  </section>
  <!--/ End Pricing Table Area -->

  <!-- Start Team Area -->
  <section id="team" class="team section">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="section-title">
            <h3 class="wow zoomIn" data-wow-delay=".2s">项目团队</h3>
            <h2 class="wow fadeInUp" data-wow-delay=".4s">给我们的团队点个Star</h2>
            <p class="wow fadeInUp" data-wow-delay=".6s">项目托管在Github上，存在任何问题和错误请及时联系我们，记得给我们的项目点个Star。</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-3 col-md-6 col-12">
          <!-- Start Single Team -->
          <div class="single-team wow fadeInUp" data-wow-delay=".2s">
            <div class="image">
              <img src="../../../assets/picture/zyh.jpg" alt="#">
            </div>
            <div class="content">
              <div class="row align-items-center">
                <div class="col-lg-7 col-12">
                  <div class="text">
                    <h3><a href="javascript:void(0)">GivTech Z</a></h3>
                    <h5>全栈开发</h5>
                  </div>
                </div>
                <div class="col-lg-5 col-12">
                  <ul class="social">
                    <li><a href="javascript:void(0)"><i class="lni lni-github-original"></i></a>
                    </li>
                    <li><a href="javascript:void(0)"><i class="lni lni-blogger"></i></a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <!-- End Single Team -->
        </div>
        <div class="col-lg-3 col-md-6 col-12">
          <!-- Start Single Team -->
          <div class="single-team wow fadeInUp" data-wow-delay=".4s">
            <div class="image">
              <img src="../../../assets/picture/czk.png" alt="#">
            </div>
            <div class="content">
              <div class="row align-items-center">
                <div class="col-lg-7 col-12">
                  <div class="text">
                    <h3><a href="javascript:void(0)">GivTech C</a></h3>
                    <h5>安全开发</h5>
                  </div>
                </div>
                <div class="col-lg-5 col-12">
                  <ul class="social">
                    <li><a href="javascript:void(0)"><i class="lni lni-github-original"></i></a>
                    </li>
                    <li><a href="javascript:void(0)"><i class="lni lni-blogger"></i></a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <!-- End Single Team -->
        </div>
        <div class="col-lg-3 col-md-6 col-12">
          <!-- Start Single Team -->
          <div class="single-team wow fadeInUp" data-wow-delay=".6s">
            <div class="image">
              <img src="../../../assets/picture/zwh.png" alt="#">
            </div>
            <div class="content">
              <div class="row align-items-center">
                <div class="col-lg-7 col-12">
                  <div class="text">
                    <h3><a href="javascript:void(0)">GivTech W</a></h3>
                    <h5>前端开发</h5>
                  </div>
                </div>
                <div class="col-lg-5 col-12">
                  <ul class="social">
                    <li><a href="javascript:void(0)"><i class="lni lni-github-original"></i></a>
                    </li>
                    <li><a href="javascript:void(0)"><i class="lni lni-blogger"></i></a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <!-- End Single Team -->
        </div>
        <div class="col-lg-3 col-md-6 col-12">
          <!-- Start Single Team -->
          <div class="single-team wow fadeInUp" data-wow-delay=".6s">
            <div class="image">
              <img src="../../../assets/picture/zwh.png" alt="#">
            </div>
            <div class="content">
              <div class="row align-items-center">
                <div class="col-lg-7 col-12">
                  <div class="text">
                    <h3><a href="javascript:void(0)">GivTech S</a></h3>
                    <h5>UI设计</h5>
                  </div>
                </div>
                <div class="col-lg-5 col-12">
                  <ul class="social">
                    <li><a href="javascript:void(0)"><i class="lni lni-github-original"></i></a>
                    </li>
                    <li><a href="javascript:void(0)"><i class="lni lni-blogger"></i></a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <!-- End Single Team -->
        </div>

      </div>
    </div>
  </section>
  <!-- End Team Area -->

  <!-- Start Testimonials Area -->
  <section class="testimonials section">
    <img class="testi-patern1" src="../../../assets/product/images/testimonial/testi-patern.svg" alt="#">
    <img class="testi-patern2" src="../../../assets/product/images/testimonial/testi-patern.svg" alt="#">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 offset-lg-2 col-md-12 col-12">
          <div class="testimonial-slider">
            <!-- Start Single Testimonial -->
            <div class="single-testimonial">
              <div class="text">
                <div class="brand-icon">
                  <img src="../../../assets/product/images/testimonial/brand2.svg" alt="#">
                </div>
                <p>" 优秀的UI直接影响用户体验，简洁直观的设计能提高用户满意度和忠诚度，降低使用难度，提升产品竞争力"</p>
              </div>
              <div class="author">
                <img src="../../../assets/picture/zwh.png" alt="#">
                <h4 class="name">
                  GivTech W
                  <span class="deg">GivTech-Web/UIX</span>
                </h4>
              </div>
            </div>
            <!-- End Single Testimonial -->
            <!-- Start Single Testimonial -->
            <div class="single-testimonial">
              <div class="text">
                <div class="brand-icon">
                  <img src="../../../assets/product/images/testimonial/brand1.svg" alt="#">
                </div>
                <p>" 良好的架构是产品成功的基石，它确保系统稳定、易扩展和易维护，提高开发效率并为未来发展奠定坚实基础"</p>
              </div>
              <div class="author">
                <img src="../../../assets/picture/zyh.jpg" alt="#">
                <h4 class="name">
                  GivTech Z
                  <span class="deg">GivTech-Arch/Web</span>
                </h4>
              </div>
            </div>
            <!-- End Single Testimonial -->
            <!-- Start Single Testimonial -->
            <div class="single-testimonial">
              <div class="text">
                <div class="brand-icon">
                  <img src="../../../assets/product/images/testimonial/brand3.svg" alt="#">
                </div>
                <p>"安全对产品至关重要，它直接关系到用户隐私和数据安全。有效的安全措施可以防止恶意攻击和数据泄露，保护用户的权益和信任"</p>
              </div>
              <div class="author">
                <img src="../../../assets/picture/czk.png" alt="#">
                <h4 class="name">
                  GivTech C
                  <span class="deg">GivTech-Web/Security</span>
                </h4>
              </div>
            </div>
            <!-- End Single Testimonial -->
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Testimonial Area -->

  <!-- Start Call To Action Area -->
  <section class="section call-action">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 offset-lg-2 col-md-12 col-12">
          <div class="cta-content">
            <h2 class="wow fadeInUp" data-wow-delay=".2s">参与公益慈善立即开始使用GivTech 注册一个新的账户</h2>
            <p class="wow fadeInUp" data-wow-delay=".4s">
              立即注册一个新账户，开始体验GivTech慈善公益平台！通过注册账户，您将获得访问我们广泛慈善项目的权限，参与到慈善活动中来，为需要帮助的人们贡献一份爱心。立即行动，加入我们的大家庭，共同为社会公益事业做出贡献！</p>
            <div class="button wow fadeInUp" data-wow-delay=".6s">
              <a href="/register" class="btn">注册</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Call To Action Area -->

  <!-- Start Faq Area -->
  <section class="faq section">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="section-title">
            <h3 class="wow zoomIn" data-wow-delay=".2s">疑问</h3>
            <h2 class="wow fadeInUp" data-wow-delay=".4s">常见的问题</h2>
            <p class="wow fadeInUp" data-wow-delay=".6s">这里有关一些常见的问题进行解答，以至于能够更好的快速了解我们的产品，首先需要知道如何快速部署我们的服务以及我们的产品未来如何。</p>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <div class="accordion" id="accordionExample">
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <span class="title"><span class="serial">01</span>怎么样去部署我们的服务？</span><i class="lni lni-plus"></i>
                </button>
              </h2>
              <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne"
                   data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <p>
                    我们的项目采用了Docker一键部署的方案，只需提供相应的Dockerfile和Docker-compose文件，即可轻松完成部署。这种快速、便捷的部署方式大大简化了整个部署流程，提高了效率，让开发人员能够更专注于项目的开发和优化。
                  </p>
                  <p>通过我们的部署方案，您无需复杂的配置和手动操作，只需准备好所需的Docker文件，即可轻松快速地将项目部署到目标环境中。这种一键部署的方式不仅简化了部署流程，还提高了部署的一致性和可靠性，为您的项目提供了稳定的运行环境。</p>
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    <span class="title"><span class="serial">02</span>怎么才能加入你们的团队？</span><i class="lni lni-plus"></i>
                </button>
              </h2>
              <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                   data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <p>如果你对开源项目和区块链技术以及web应用开发有浓厚兴趣，欢迎通过GitHub直接联系我们。我们团队致力于开发创新的区块链技术和基于web的应用程序，期待与志同道合的开发者一起合作，共同推动技术的发展和项目的进步。
                  </p>
                  <p>通过GitHub，您可以轻松了解我们的项目、参与讨论，并提出宝贵的建议和改进建议。我们期待与您建立联系，共同探索区块链技术和web应用开发的无限可能性。Github地址：https://github.com/CN-ZHANGYH/GYCS-SZGY</p>
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingThree">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    <span class="title"><span class="serial">03</span>怎么才能给灾区进行物资捐赠呢？</span><i class="lni lni-plus"></i>
                </button>
              </h2>
              <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                   data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <p>您可以通过注册用户身份，在平台上浏览灾区活动，并选择您希望捐赠的物资项目。在捐赠过程中，您可以方便地绑定个人身份信息，确保捐赠的准确性和透明度。此外，您还可以选择指定的物流公司进行运输，而运费将由平台承担，让您的善举更加便捷和无负担。
                  </p>
                  <p>
                    在这个平台上，您可以通过简单的操作完成捐赠流程，为灾区提供急需的支持和帮助。同时，我们致力于提供安全、高效的捐赠渠道，确保您的捐助能够准确、及时地送达灾区，为灾民带来温暖和希望。
                  </p>
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingFour">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    <span class="title"><span class="serial">04</span>使用的区块链技术是以太坊还是联盟链？</span><i class="lni lni-plus"></i>
                </button>
              </h2>
              <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour"
                   data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <p>
                    我们的项目采用了国产联盟链技术 FISCO BCOS，这是一种基于区块链的企业级解决方案。FISCO BCOS 提供了高度可定制化的功能，能够满足各种复杂业务场景的需求，同时具备高性能和高安全性的特点。通过 FISCO BCOS，我们能够构建稳健的区块链网络，为项目提供可靠的基础支持。
                  </p>
                  <p>借助 FISCO BCOS，我们的项目能够充分发挥区块链技术的优势，实现去中心化、透明化、不可篡改等特性。这样的特点使得我们的项目能够更好地满足用户的需求，提供可信的数据交换和共享平台，为企业和个人带来更安全、更高效的应用体验。</p>
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingFive">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                    <span class="title"><span class="serial">05</span>你们的产品未来究竟如何？</span><i class="lni lni-plus"></i>
                </button>
              </h2>
              <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive"
                   data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <p>我们的产品未来将继续不断演进和完善，致力于为用户提供更多功能和更好的体验。我们将不断借助最新的技术和创新的理念，持续优化产品性能和用户界面，以满足不断变化的市场需求。同时，我们也将积极跟进行业发展趋势，拓展产品的应用领域，助力用户在各个领域取得更大的成就。
                  </p>
                  <p>
                    未来，我们将致力于构建更加开放和互联的生态系统，与更多的合作伙伴共同推动产品的发展。通过不断创新和合作，我们的产品将成为行业的领导者，为用户提供更多元化、更全面的解决方案，实现我们共同的愿景和目标。
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!--/ End Faq Area -->

  <!-- Start Client Logo Section Area -->
  <div class="client-logo-section">
    <div class="container">
      <div class="client-logo-wrapper">
        <div class="client-logo-carousel d-flex align-items-center justify-content-between">
          <div class="client-logo">
            <img src="../../../assets/product/images/logo/logo.png" alt="">
          </div>
          <div class="client-logo">
            <img src="../../../assets/product/images/logo/white-logo.png" alt="">
          </div>
          <div class="client-logo">
            <img src="../../../assets/product/images/logo/solidity.png" alt="">
          </div>
          <div class="client-logo">
            <img src="../../../assets/product/images/logo/Vue.png" alt="">
          </div>
          <div class="client-logo">
            <img src="../../../assets/product/images/logo/Docker.png" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- End Client Logo Section Area -->

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
</style>