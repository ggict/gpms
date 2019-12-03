
$(function(){

	if($("nav").hasClass("nav")) {

		var cssTop = parseInt($("#sky").css("top"));
		 $(window).scroll(function(){
		  var position = $(window).scrollTop();
		  $("#sky").stop().animate({"top":position+cssTop+"px"},500);
		 });



		$('.nav ul a').focus(function() {
			$(this).parents("li").addClass('on');
			$('header.header').addClass('on');
		});
		$('.nav ul a').blur(function() {
			$(this).parents("li").removeClass('on');
            $('header.header').removeClass('on');
		});
		$('.nav ul').mouseover(function() {
			$(this).parents("li").addClass('on');
		 	$('header.header').addClass('on');
		});

		$('.nav').mouseout(function() {
			$('.nav li').removeClass('on');
		 	$('header.header').removeClass('on');
		});

		$('.navClose').click(function() {
		 $('header.header').removeClass('on');
		})

		// nav 제일 갯수 많은 li기준으로 addClass 하기

		var m = 0;
		$('.nav ul ul').each(function(){
		    if(m < $(this).children("li").length){
		        m = $(this).children("li").length;
		    }
		});

		$('.header').addClass("nav"+m)
	};



	// 달력 데이터 피커
	if($("input").hasClass("datepicker")) {
		$( ".datepicker").datepicker({
			minDate: 1						//시작 날짜 0 오늘 1 내일 2 내일 모래 순
			,dateFormat: 'yy-mm-dd' //Input Display Format 변경
            ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
            ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
            ,changeYear: true //콤보박스에서 년 선택 가능
            ,changeMonth: true //콤보박스에서 월 선택 가능
            ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
            ,buttonImage: "/human/resources/images/iconCalendar.gif" //버튼 이미지 경로
            ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
            ,buttonText: "달력" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트
            ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
            ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
            ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
            ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
		});

	}
	
	$(".userArea").mouseover(function(){
		$(this).addClass("on");
	});	
	$(".userArea").mouseout(function(){
		$(this).removeClass("on");
	});
	

	// 팝업 띄우기
	$("a.jsDiv").click(function(){
		var jsDivHref = $(this).attr("href");
		var jsDiv = jsDivHref.replace('#','');
		$("#"+ jsDiv).addClass("on");

		var height = $(jsDivHref + "> .jsDivContent" ).height();
		$(jsDivHref + "> .jsDivContent" ).css("top","50%").css("margin-top", - height * 0.5 );

		$("body").addClass("no_scroll");
		return false;

		// var windowheight = $(window).height();
		// var jsDivContent = $('"#"+jsDiv').innerHeight();
		// alert(jsDivContent)
	});

	$("a.jsDivClose").click(function(){
		$(this).parents(".jsDiv").removeClass("on")
		$("body").removeClass("no_scroll");
		return false;
	});

    // jsOn 해당 id에 클래스on 부여
	$("a.jsOn").click(function(){
		var jsOnHref = $(this).attr("href");
		var jsOn = jsOnHref.replace('#','');
		$("#"+ jsOn).addClass("on");
	});

    // sidDiv 띄우기
	$("a.sideDiv").click(function(){
		try{
			var sideDivHref = $(this).attr("href");
			var sideDiv = sideDivHref.replace('#','');
			$("#"+ sideDiv).addClass("on");
			
			var height = $(sideDivHref + "> .sideDivContent" ).height();
			$(sideDivHref + "> .sideDivContent" ).css("top","50%").css("margin-top", - height * 0.5 );
			return false;
		}catch(e){
			console.log("a < sideDiv click event error");
		}
	});

	$("a.sideDivClose").click(function(){
		$(this).parents(".sideDiv").removeClass("on")
		return false;
	});

	// var scroll = new SmoothScroll('[data-scroll]');


	// 탭메뉴
	$(".tab a").click(function(){
		$(".tab a").removeClass("on");
		$(this).addClass("on");
	});

	// 모바일 메뉴 여닫기
	$("input.navOpen").click(function(){
		$(".header").addClass("navOpen");
	});
	$("input.navClose").click(function(){
		$(".header").removeClass("navOpen");
	});


	if($("textarea").hasClass("js-elasticArea")) {
		(function() {

			  'use strict';

			  function elasticArea() {
			    $('.js-elasticArea').each(function(index, element) {
			       var elasticElement = element,
			          $elasticElement = $(element),
			          initialHeight = initialHeight || $elasticElement.height(),
			          delta = parseInt( $elasticElement.css('paddingBottom') ) + parseInt( $elasticElement.css('paddingTop') ) || 0,
			          resize = function() {
			            $elasticElement.height(initialHeight);
			            $elasticElement.height( elasticElement.scrollHeight - delta );
			        };

			      $elasticElement.on('input change keyup', resize);
			      resize();
			    });

			  };

			  //Init function in the view
			  elasticArea();


			})();
	};


	$(".btnContentOpen").click(function(){
		$(this).parents(".contentsWrap").addClass("on");
	});
	$(".btnContentClose").click(function(){
		$(this).parents(".contentsWrap").removeClass("on");
	});

	$(".indexMapWrap button").click(function(){
		$(this).parent().toggleClass("on");
	});

	// 정보통합조회 >일반지도,위성지도
	$(".toggleBtn button").click(function(){
		$(".toggleBtn button").removeClass("on");
		$(this).addClass("on");
	});



	$("#btnlocSearch2").click(function(){
		$("#locSearchResult").addClass("on");
	});
	$("#locSearch .btnClose").click(function(){
		$("#locSearchResult").removeClass("on");
	});

	// 정보통합조회 > 1단계 > 3단계 열고닫음
	$(".totalSearch_step3 .btnOpen2").click(function(){
		$(".totalSearch_step3").addClass("on");
	});
	$(".totalSearch_step3 .btnClose2").click(function(){
		$(".totalSearch_step3").removeClass("on");
	});

	// 정보통합조회 > 1단계 - 항목선택
	$("a.tSt11").click(function(){
		$("#totalSearch").removeClass("tSt12").removeClass("tSt13");
		$("#totalSearch").addClass("tSt11");
	});
	$("a.tSt12").click(function(){
		$("#totalSearch").removeClass("tSt11").removeClass("tSt13").addClass("tSt12");
	});
	$("a.tSt13").click(function(){
		$("#totalSearch").removeClass("tSt11").removeClass("tSt12").addClass("tSt13");
	});




});
