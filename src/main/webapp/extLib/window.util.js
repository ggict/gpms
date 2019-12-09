var global_init_data = null;

$.window.prepare({
	dock : 'bottom', // change the dock direction: 'left', 'right', 'top', 'bottom'
	animationSpeed : 300, // set animation speed
	minWinLong : 120
// set minimized window long dimension width in pixel
});

function wDialogOpen(div_title, call_url, width, height, modal, opener_id,
		init_data) {
	var wid = '';

	var allw = $.window.getAll();
	var exist_id = "";

	for (var i = 0; i < allw.length; i++) {
		if (allw[i].getTitle() == div_title)
			exist_id = allw[i].getWindowId();
	}

	if (exist_id == "") {
		try {
			// 오프너가 앞으로 오도록 지정
			//if( opener_id!='' ) {
			//  $.window.getWindow( opener_id ).select();
			//}

			var minimize_tf = true;
			if (modal)
				minimize_tf = false;

			var wnd = $
					.window({
						title : div_title,
						url : call_url,
						width : width,
						height : height,
						maxWidth : width,
						maxHeight : height,
						showModal : modal,
						bookmarkable : false,
						checkBoundary : true,
						minimizable : false,
						maximizable : false,
						draggable : false,
						resizable : false,
						//showRoundCorner: true,
						onIframeEnd : function(wnd, url) { // 현재 생성된 윈도우 아이디를 팝업창에 등록
							try {
								wnd.getContainer().find("iframe").contents()
										.find("#wnd_id").val(wnd.getWindowId());
								wnd.getContainer().find("iframe").contents()
										.find("#opener_id").val(opener_id);
								// 포커스 IN
								wnd.getContainer().find("iframe").contents()
										.find("input[type='text']").first()
										.focus();
							} catch (E) {
							}
						},
						onClose : function(wnd) { //도형 입력 후 완료하지 않고, 창을 닫을 경우 입력한 그래픽 객체 지우기(맵 초기화)
							if (parent != null
									&& parent.m_CurrentEditLayer != null
									&& parent.m_EditGraphicLayer != null) {
								parent.m_EditGraphicLayer.clear();
							}

							// 2018. 02. 28. YYK. 포트홀 _ 이미지저장 팝업 닫을 때 포트홀신고현황 레이어 visible true
							var popupBody = $("#" + wid).find("iframe")
									.contents().find("body")
							if (popupBody.hasClass("saveImgMap")) {
								gMap.getLayerByName("GStatusLayer")
										.setVisibility(true);
								// 좌측 메뉴
								$(".mtBtn").parent("li")
										.removeClass("active");
								$("#mCtrlPan").parent().addClass("active");
								gMap.activeControls("drag");
								return;
							}

							if (typeof gMap != 'undefined' && gMap != null) {
								gMap.cleanMap();

								//좌측 툴 이동모드 활성화
								$(".mtBtn").parent("li")
										.removeClass("active");
								$("#mCtrlPan").parent("li").addClass("active");
								gMap.activeControls("drag");

							}

						}
					});

			wid = wnd.getWindowId();
		} catch (E) {
			alert('오류가 발생하였습니다. : ' + E);
		}
	} else {
		var wnd = $.window.getWindow(exist_id);
		wnd.show();
		wnd.setUrl(call_url);
		wnd.getContainer().mousedown();

		// 오프너가 앞으로 오도록 지정
		if (opener_id != '') {
			$.window.getWindow(opener_id).select();
		}

		wid = wnd.getWindowId();
	}
	return wid;
}

function wWindowOpen(div_title, call_url, width, height, modal, opener_id, position, init_data) {
	var wid = '';
	var wh = $(window).height() - 5;
	var ww = $(window).width() - 10;

	var allw = $.window.getAll();
	var exist_id = "";

	for (var i = 0; i < allw.length; i++) {
		if (allw[i].getTitle() == div_title)
			exist_id = allw[i].getWindowId();
	}

	// 초기화 데이터를 전역변수로 지정
	global_init_data = init_data;

	// 높이는 최대 범위를 넘지 못하도록 (해상도 낮은 PC 고려) 2015.12.23
	if ((wh - height) < 0)
		height = wh - 120;

	// 동일한 아이디 창이 있는 경우 기존 창 close (2015.12.23)
	if (exist_id != '') {
		$.window.getWindow(exist_id).close();
	}

	try {
		var wnd = $
				.window({
					title : div_title,
					url : call_url,
					width : width,
					height : height,
					maxWidth : ww,
					maxHeight : wh,
					showModal : modal,
					bookmarkable : false,
					checkBoundary : true,
					minimizable : true,
					maximizable : false,
					//showRoundCorner: true,
					onIframeEnd : function(wnd) { // 현재 생성된 윈도우 아이디를 팝업창에 등록
						try {
							wnd.getContainer().find("iframe").contents().find(
									"#wnd_id").val(wnd.getWindowId());
							wnd.getContainer().find("iframe").contents().find(
									"#opener_id").val(opener_id);
							// 포커스 IN
							wnd.getContainer().find("iframe").contents().find(
									"input[type='text']").first().focus();

							// 팝업 윈도우 BODY 선택시 포커스 처리
							wnd.getContainer().find("iframe").contents().click(
									function() {
										//$.window.getWindow( wnd.getWindowId() ).select();
									});

							// 초기 데이터 잇을 경우, 생성후 넣어줌
							if (global_init_data != null
									&& global_init_data != undefined) {

								// for문으로 변경 > 콜백때문 ㅠ.ㅠ
								for ( var key in global_init_data) {
									var val = global_init_data[key];
									wnd.getContainer().find("iframe")
											.contents().find("#" + key)
											.val(val);
								}

								var contentWindow = wnd.getContainer().find(
										"iframe")[0].contentWindow;

								// 콜백함수 있을경우 실행
								if (contentWindow.fnMapDataLoaded) {
									contentWindow.fnMapDataLoaded();
								}

								// 반드시 초기화 (남을 경우, 캐쉬의 데이터가 다시 세팅됨)
								global_init_data = null;
								inti_data = null;
							}

							// 윈도우 리사이즈 함수 호출 (2015.12.03)
							try {
								var h = wnd.getContainer().find("iframe")
										.contents().find("#wnd_height").val();

								wnd.getContainer().height(h);
							} catch (WE) {
							}
						} catch (E) {
							alert(E);
						}
					},
					onClose : function(wnd) { //도형 입력 후 완료하지 않고, 창을 닫을 경우 입력한 그래픽 객체 지우기(맵 초기화)

						//if  ($(this).attr("id") == "status")
						// ==============  2018.03.08. YYK. 포트홀 화면일 때 마커 onClose 셋팅 ==============

						var popupBody = $("#" + wid).find("iframe").contents()
								.find("body")

						if (popupBody.hasClass("searchLoc")) {
							gMap.cleanMap();
							gMap.activeControls("drag");
						}

						//if ( $("#" + wid).parent("#goodMap") ){

						// 2018. 02. 29. YYK. 포트홀 팝업 닫을 때 포트홀신고현황 레이어 visible true
						if (popupBody.hasClass("space-search")
								|| popupBody.hasClass("thememap")
								|| popupBody.hasClass("mapPrint")
								|| popupBody.hasClass("sttemnt")) {

							// 2018. 04. 04. JOY. 위치보정 팝업 닫기
							$("#dvMarker").hide();

							if ($("#dvComplete").hasClass("on")) {

								$("#dvComplete").dialog("close");
								$("#dvComplete").removeClass("on");

							}

							$("#mCtrlPan").parent().addClass("active");

							// YYK. sttemnt 포트홀신고관리 의 edit 페이지에서만 bottomOpen()
							if (popupBody.hasClass("sttemnt")
									&& !popupBody.hasClass("edit")) {

								if (popupBody.hasClass("change-loc")) {
									$("#" + wid).find("iframe").get(0).contentWindow
											.fnClearLoc(); // 위치보정 초기화
								}
								gMap.getLayerByName("GOverlapLayer")
										.removeAllFeatures();
								bottomOpen();
							}

							// YYK. 공간검색창 닫을때 공간검색 마커 삭제
							if (popupBody.hasClass("space-search")) {
								gMap.getLayerByName('SttemntLayer')
										.removeAllFeatures()
								gMap.getLayerByName('DmgtLayer')
										.removeAllFeatures()
							}

							gMap.cleanMap(CONFIG.fn_get_exceptLayerList());
							// 좌측 메뉴
							$(".mtBtn").parent("li").removeClass("active");
							$("#mCtrlPan").parent().addClass("active");
							gMap.activeControls("drag");

							return;
						}

						// 2018. 03. 04. YYK. 위치 통합검색 + 경위도좌표이동 마커 삭제
						if (popupBody.hasClass("searchLoc")) {
							for (var i = 0; i < markersLoc.length; i++) {
								markersLoc[i].setMap(null);
							}
							markersLoc = [];
							gMap.cleanMap(CONFIG.fn_get_exceptLayerList());
							// 좌측 메뉴
							$(".mtBtn").parent("li").removeClass("active");
							$("#mCtrlPan").parent().addClass("active");
							gMap.activeControls("drag");
							return;
						}

						// JOY. 2017. 09. 13 로그인 화면에서도 팝업창을 close 하는 경우가 있어 조건 수정
						if ($("#" + wid).find("iframe").contents().find("body")
								.hasClass("research")) {

							// 조사정보이미지, 로드뷰 팝업 닫을 때
							// JOY. 2017. 11. 29. 팝업 다 닫으면 맵 클리어
							rshInfoCnt--;

							if (rshInfoCnt == 0) {

								$("#mCtrlPan").parent().addClass("active");
								gMap.cleanMap();
								gMap.activeControls("drag");
								bottomClose();

							}

						} else if (typeof gMap != 'undefined' && gMap != null) {

							gMap.cleanMap();

							//좌측 툴 이동모드 활성화
							$(".mtBtn").parent("li").removeClass("active");
							$("#mCtrlPan").parent("li").addClass("active");
							gMap.activeControls("drag");

						}

						// JOY. 2017.10.16 팝업 닫을 때 메뉴 비활성화
						if ($("#" + wid).find("iframe").contents().find("body")
								.hasClass("right-tool")) {

							// 우측 메뉴
							$("#mCtrlPan").parent().addClass("active");
							gMap.cleanMap();
							gMap.activeControls("drag");

						} else if ($("#" + wid).find("iframe").contents().find(
								"body").hasClass("left-tool")) {

							// JOY. 2017. 11. 24. 위치 통합검색 팝업 닫을 때 마커 삭제
							if ($("#" + wid).find("iframe").contents().find(
									"body").hasClass("searchLoc")) {
								for (var i = 0; i < markersLoc.length; i++) {
									markersLoc[i].setMap(null);
								}
								markersLoc = [];

							}

							// 좌측 메뉴
							$(".mtBtn").parent("li").removeClass("active");
							$("#mCtrlPan").parent().addClass("active");
							gMap.cleanMap();
							gMap.activeControls("drag");

						}

						// 2018. 01. 23. JOY. 윈도우 팝업 닫을 때 하단 팝업 open
						if ($("#" + wid).find("iframe").contents().find("body")
								.hasClass("sttemnt")) {

							// 수정페이지로 가는 경우는 X
							if (!$("#" + wid).find("iframe").contents().find(
									"body").hasClass("edit")) {

								gMap.cleanMap();
								bottomOpen();
							}
						}
					}
				});

		wid = wnd.getWindowId();
		var w = $(window).width();
		var nWid = 0, nHei = 0;

		// 포지션 처리 (2015.12.18)
		if (position == 'left') {
			nWid = 50;
		}
		// 2017.09.04
		else if (position == "integrated") {
			if (w > 1240) {
				w -= 15;
			}

			w -= 625;

			nWid = w;
			nHei = 159;
		}

		// 2018.02.22 YYK , 신고정보 공간검색 위치조정
		else if (position == "sttemnt") {
			if (w > 1240) {
				w -= 15;
			}
			w -= 345;

			nWid = w;
			nHei = 185;
		}

		//181120 wijy 추가
		else if (position == "updCell") {
			if (w > 1240) {
				w -= 15;
			}
			//w -= 250; //인덱스맵 영역 buffer
			w -= 620; //팝업창 영역 buffer

			nWid = w;
			nHei = 160;
		}

		else if (position == "thememap") {
			if (w > 1240) {
				if ($(window).height() < 891) {
					w -= 32;
				} else {
					w -= 15;
				}
			}
			w -= 405;

			nWid = w;
			nHei = 159;
		}

		// 2018.02.28. YYK. 포트홀화면 테마지도 포지션 추가
		else if (position == "thememap_pth") {
			if (w > 1240) {
				if ($(window).height() < 891) {
					w -= 32;
				} else {
					w -= 15;
				}
			}
			w -= 405;

			nWid = w;
			nHei = 185;
		}

		else if (position == "locsearch") {
			var ifrId = wnd.getContainer().find("iframe");
			ifrId.attr("id", "ifr_locsearch");

			nWid = 52.5;
			nHei = 107;
		}

		else if (position == "mvLonlan") {
			nWid = 52.5;
			nHei = 145;
		}

		else if (position == "researchImg") {
			nWid = 0;
			nHei = 63.5;
		}

		else if (position == "roadView") {
			var ww = $("#" + wid).find("iframe").width();

			nWid = w - ww - 2;
			nHei = 63.5;
		} else {
			//기본 position은 center로 한다
			var ww = $("#" + wid).find("iframe").width();
			nWid = w - (w/2 + ww/2);
			nHei = 70;
		}

		wnd.move(nWid, nHei);

	} catch (E) {
		alert('오류가 발생하였습니다. : ' + E);
	}

	return wid;
}

function wWindowHideAll() {
	$.window.minimizeAll();
	/*$.window.hideAll();

	var allw = $.window.getAll();
	try {
	    if( allw.length>0 ) {
	        $("#dvRestore").dialog({
	            resizable : false,
	            width : 40,
	            height : 80,
	            close : function() {
	                // x 버튼으로 창 복원 추가 (2015.12.15)
	                try {
	                    wWindowShowAll();
	                    //$("#mCtrlClear").click();

	                    //bottomOpen(); //창복원후 하단 메뉴 open 할 필요 없으므로 주석처리
	                } catch(sE){}
	            }
	        }).parent('.ui-dialog').css('zIndex',8888);
	        $("div[aria-describedby='dvRestore']").css("left", "400px");
	        $("div[aria-describedby='dvRestore']").css("top", "180px");
	    }
	} catch(E) {}*/
}

function wWindowShowAll() {
	$.window.showAll();
}

function wWindowResize(wnd_id, w, h) {
	try {
		var wnd = $.window.getWindow(wnd_id);
		wnd.resize(w, h);
	} catch (E) {
	}
}

function wWindowsResizeMinHeight(height) {
	$.window.setDefHeight(height);
}