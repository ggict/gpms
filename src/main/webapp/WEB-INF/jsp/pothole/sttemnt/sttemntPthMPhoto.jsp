<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!DOCTYPE html>
<html lang="ko">
<head>

<link rel="stylesheet" type="text/css" href="/gpms/css/slick.css">
<link rel="stylesheet" type="text/css" href="/gpms/css/slick-theme.css">

<%@ include file="/include/common_head.jsp" %>
</head>

<body>

<div class="tabcont">
	<!-- Content -->
	<div class="content">
		<div class="ml10 mr10">
			<div class="mt10 tc">
				<div class="show_bfe_img btncursor slider-for">
                       <div><img id="b_img0" src="<c:if test = "${PTH_PHOTO0.FILE_NM != null }" >${file_path}${PTH_PHOTO0.FILE_COURS}/${PTH_PHOTO0.ORGINL_FILE_NM}</c:if>"
                       alt="" title="국토부앱 포트홀 신고사진" style="width: 100%; text-align: center; margin-bottom:10px;" /></div>

                       <div><img id="b_img1" src="<c:if test = "${PTH_PHOTO1.FILE_NM != null }" >${file_path}${PTH_PHOTO1.FILE_COURS}/${PTH_PHOTO1.ORGINL_FILE_NM}</c:if>"
                       alt="" title="국토부앱 포트홀 신고사진" style="width: 100%; text-align: center; margin-bottom:10px;" /></div>

                       <div><img id="b_img2" src="<c:if test = "${PTH_PHOTO2.FILE_NM != null }" >${file_path}${PTH_PHOTO2.FILE_COURS}/${PTH_PHOTO2.ORGINL_FILE_NM}</c:if>"
                       alt="" title="국토부앱 포트홀 신고사진" style="width: 100%; text-align: center;" /></div>
                 </div>

                 <div class="show_bfe_img btncursor slider-nav">
                       <div><img id="b_img0" src="<c:if test = "${PTH_PHOTO0.FILE_NM != null }" >${file_path}${PTH_PHOTO0.FILE_COURS}/${PTH_PHOTO0.ORGINL_FILE_NM}</c:if>"
                       alt="" title="국토부앱 포트홀 신고사진" style="width: 100%; text-align: center; margin-bottom:10px;" /></div>

                       <div><img id="b_img1" src="<c:if test = "${PTH_PHOTO1.FILE_NM != null }" >${file_path}${PTH_PHOTO1.FILE_COURS}/${PTH_PHOTO1.ORGINL_FILE_NM}</c:if>"
                       alt="" title="국토부앱 포트홀 신고사진" style="width: 100%; text-align: center; margin-bottom:10px;" /></div>

                       <div><img id="b_img2" src="<c:if test = "${PTH_PHOTO2.FILE_NM != null }" >${file_path}${PTH_PHOTO2.FILE_COURS}/${PTH_PHOTO2.ORGINL_FILE_NM}</c:if>"
                       alt="" title="국토부앱 포트홀 신고사진" style="width: 100%; text-align: center;" /></div>
                 </div>
			</div>
		</div>
	</div>
	<!-- // Content -->
</div>
<!-- // wrap -->

<%-- <%@ include file="/include/common.jsp" %> --%>

<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/gpms/js/slick.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
    	$('.slider-for').slick({
    		  slidesToShow: 1, <!--이건 큰사진-->
    		  slidesToScroll: 1, <!--몇개씩 넘기는지-->
    		  arrows: false,
    		  fade: true, <!--효과-->
    		  asNavFor: '.slider-nav'
   		});
   		$('.slider-nav').slick({
   		  slidesToShow: 3,
   		  slidesToScroll: 1,
   		  asNavFor: '.slider-for',
   		  dots: false,
   		  centerMode: false,
   		  focusOnSelect: true
   		});
    });
</script>

</body>
</html>