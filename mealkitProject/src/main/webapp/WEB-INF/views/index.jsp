<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">
  <head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="megakit,business,company,agency,multipurpose,modern,bootstrap4">
  
  <meta name="author" content="themefisher.com">

  <title>index</title>

  <!-- bootstrap.min css -->
  <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
  <!-- Icon Font Css -->
  <link rel="stylesheet" href="plugins/themify/css/themify-icons.css">
  <link rel="stylesheet" href="plugins/fontawesome/css/all.css">
  <link rel="stylesheet" href="plugins/magnific-popup/dist/magnific-popup.css">
  <!-- Owl Carousel CSS -->
  <link rel="stylesheet" href="plugins/slick-carousel/slick/slick.css">
  <link rel="stylesheet" href="plugins/slick-carousel/slick/slick-theme.css">

  <!-- Main Stylesheet -->
  <link rel="stylesheet" href="css/style.css">
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  <style type="text/css">
	  #comment-form fieldset{
  	    display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
 	    border: 0; /* 필드셋 테두리 제거 */
 	    font-family: 'Poppins', sans-serif;
		}
		#comment-form input[type=radio]{
		    display: none; /* 라디오박스 감춤 */
		}
		#comment-form label.star{
		    font-size:1em; /* 이모지 크기 */
		    color: transparent; /* 기존 이모지 컬러 제거 */
		    text-shadow: 0 0 0 #f0f0f0; /* 새 이모지 색상 부여 */
		}
		#comment-form fieldset{
	    display: inline-block; /* 하위 별점 이미지들이 있는 영역만 자리를 차지함.*/
	    direction: rtl; /* 이모지 순서 반전 */
	    border: 0; /* 필드셋 테두리 제거 */
		}
		#comment-form fieldset legend{
		    text-align: left;
		}
		#comment-form input[type=radio]:checked ~ label.star{
	    	text-shadow: 0 0 0 #a00; /* 마우스 클릭 체크 */
		}
	</style>

</head>

<body>


<!-- Header Start --> 
<jsp:include page="./layout/header.jsp"></jsp:include>
<!-- Header Close --> 

<div class="main-wrapper ">
<!-- Slider Start -->
<section class="slider">
	<div class="container">
		<div class="row">
			<div class="col-lg-9 col-md-10">
				<div class="block">
					<!-- <span class="d-block mb-3 text-white text-capitalize">Prepare for new future</span> -->
					<h1 class="animated fadeInUp mb-5">우선 먹자, <br>걱정은 잠시 내버려두고</h1>
					<a href="#" target="_blank" class="btn btn-main animated fadeInUp btn-round-full" >주문하기<i class="btn-icon fa fa-angle-right ml-2"></i></a>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Section Intro Start -->

<section class="section intro">
	<div class="container">
		<div class="row ">
			<div class="col-lg-8">
				<div class="section-title">
					<span class="h6 text-color" style="font-size: 40px;">오늘의 인기 상품</span>
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<!-- 인기 아이템 Start -->
			<c:forEach items="${p_list}" var="productVo">
			<div class="col-lg-4 col-md-6 col-12">
				<div class="intro-item mb-5 mb-lg-0">
					<a href="product/product_view?id=${productVo.id}">
						<img loading="lazy" src="/productPicture/${productVo.picture}" alt="blog" class="img-fluid rounded" style="width: 100%;height: 500px;">
					</a>
					<h4 class="mt-4 mb-3">${productVo.name}</h4>
					<span class="text-muted text-capitalize d-inline-block"><i class="ti-star mr-1" style="font-size: 2em;"></i></span>
					<span class="text-black text-capitalize d-inline-block">${productVo.rate}점</span>
					<p>${productVo.price}원 / ${productVo.serving }인분</p>
				</div>
			</div>
			</c:forEach>
			<!-- 인기 아이템 Close -->
		</div>
	</div>
</section>
<!-- Section Intro END -->


<!-- Section About Start -->
<section class="section about position-relative">
	<!-- 사진부분 -->
	<!-- <div class="bg-about"></div> -->
	<div class="container">
		<div class="row ">
			<div class="col-lg-8">
				<div class="section-title">
					<span class="h6 text-color" style="font-size: 40px;">Best 리뷰</span>
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<!-- Best 리뷰 Start -->
			<c:forEach items="${map.r_list}" var="reviewVo" varStatus="status">
				<div class="col-lg-4 col-md-6 col-12">
					<div class="intro-item mb-5 mb-lg-0">
						<a href="#">
							<img loading="lazy" src="/reviewPicture/${map.p_list[status.index]}" alt="blog" class="img-fluid rounded" style="width: 100%;height: 500px;">
						</a>
						<h4 class="mt-4 mb-3">${reviewVo.name}</h4>
						<form action="" id="comment-form">
						<fieldset>
				        <legend style="font-size: 20px;">별점</legend>
					        <input type="radio" name="rate" value="5" id="rate5" ${reviewVo.rate eq 5 ? "checked" : ""}><label class="star" for="rate5">⭐</label>
					        <input type="radio" name="rate" value="4" id="rate4" ${reviewVo.rate eq 4 ? "checked" : ""}><label class="star" for="rate4">⭐</label>
					        <input type="radio" name="rate" value="3" id="rate3" ${reviewVo.rate eq 3 ? "checked" : ""}><label class="star" for="rate3">⭐</label>
					        <input type="radio" name="rate" value="2" id="rate2" ${reviewVo.rate eq 2 ? "checked" : ""}><label class="star" for="rate2">⭐</label>
					        <input type="radio" name="rate" value="1" id="rate1" ${reviewVo.rate eq 1 ? "checked" : ""}><label class="star" for="rate1">⭐</label>
				  		</fieldset>
						</form>
						<p>${reviewVo.about}</p>
					</div>
				</div>
			</c:forEach>
			<!-- Best 리뷰 Close -->
		</div>
	</div>
</section>
<!-- Section About End -->
</div>

<!-- footer Start -->
<jsp:include page="./layout/footer.jsp"></jsp:include>

    <!-- 
    Essential Scripts
    =====================================-->

    
    <!-- Main jQuery -->
    <script src="plugins/jquery/jquery.js"></script>
    <script src="js/contact.js"></script>
    <!-- Bootstrap 4.3.1 -->
    <script src="plugins/bootstrap/js/popper.js"></script>
    <script src="plugins/bootstrap/js/bootstrap.min.js"></script>
   <!--  Magnific Popup-->
    <script src="plugins/magnific-popup/dist/jquery.magnific-popup.min.js"></script>
    <!-- Slick Slider -->
    <script src="plugins/slick-carousel/slick/slick.min.js"></script>
    <!-- Counterup -->
    <script src="plugins/counterup/jquery.waypoints.min.js"></script>
    <script src="plugins/counterup/jquery.counterup.min.js"></script>

    <!-- Google Map -->
    <script src="plugins/google-map/map.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkeLMlsiwzp6b3Gnaxd86lvakimwGA6UA&callback=initMap"></script>    
    
    <script src="js/script.js"></script>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  </body>
  </html>
   