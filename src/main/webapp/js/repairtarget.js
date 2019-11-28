function fn_grid_btn(cellValue, options, rowObject) {
	var btn = "";
	var nm = options.colModel.name;

	switch (nm) {
	case "btn_down": {
		// 클릭시 파일 다운로드
		btn = "<a href='#' onclick=\"fn_select_dwg('" + rowObject.ROAD_NO + "')\"><img src='" + contextPath + "/images/ic_download.png' alt='다운로드' title='다운로드' ></a>";
	}
		break;
	case "btn_loc": {
		btn = "<a href='#' onclick=\"javascript:fn_select_cellSectRange('" + rowObject.ROUTE_CODE + "', '" + rowObject.DIRECT_CODE + "', '" + rowObject.TRACK + "', '" + rowObject.STRTPT + "', '" + rowObject.ENDPT + "');\"><img src='" + contextPath + "/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
	}
		break;
	case "btn_check": {
		if (rowObject.TMPR_SLCTN_AT == "Y") {
			btn = "<input type='checkbox' id='ck" + rowObject.GROUP_ITEM_NO + "' checked onclick=\"javascript:fn_checkItem(this, '" + rowObject.GROUP_ITEM_NO + "', '" + rowObject.TMPR_SLCTN_AT + "', '" + rowObject.FIX_AMOUNT_CALC + "');\" /><label for='ck" + rowObject.GROUP_ITEM_NO + "' class='hiddenLabel onlyCk'>선택</label>";
		} else {
			btn = "<input type='checkbox' id='ck" + rowObject.GROUP_ITEM_NO + "' onclick=\"javascript:fn_checkItem(this, '" + rowObject.GROUP_ITEM_NO + "', '" + rowObject.TMPR_SLCTN_AT + "', '" + rowObject.FIX_AMOUNT_CALC + "');\" /><label for='ck" + rowObject.GROUP_ITEM_NO + "' class='hiddenLabel onlyCk'>선택</label>";
		}

	}
		break;
	}

	return btn;
}
function fn_checkItem(objThis, group_item_no, tmpr_slctn_at, fix_amount_calc) {
	// alert(group_item_no);
	var footData = $('#gridArea').jqGrid('footerData', 'get');
	var checked = objThis.checked;
	var action = contextPath +'/api/rpairtrgetgroup/updateToggleTMPR_SLCTN_AT.do';
	var postData = {
		"GROUP_ITEM_NO" : group_item_no
	};
	var totalAmount = parseInt(footData.FIX_AMOUNT_CALC.replace(/,/gi, ""));
	if (checked) {
		totalAmount += parseInt(fix_amount_calc);
		$(objThis).closest('tr').addClass('notempty-price');
	} else {
		totalAmount -= parseInt(fix_amount_calc);
		$(objThis).closest('tr').removeClass('notempty-price');
	}
	$('#gridArea').jqGrid('footerData', 'set', {
		DEPT_NM : '합계',
		FIX_AMOUNT_CALC : totalAmount
	});
	
	$.ajax({
		url : action,
		contentType : 'application/json',
		data : JSON.stringify(postData),
		dataType : "json",
		cache : false,
		type : 'POST',
		processData : false,
		success : function(data) {
			// alert(JSON.stringify(data));
			// vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);
			// parent.fnRefreshRepairTarget();
			// parent.closeRTDialog();
			// parent.COMMON_UTIL.repairMenuUrlContent( '<c:url
			// value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO='+data.TRGET_SLCTN_NO);
		},
		error : function(a, b, msg) {
			alert(JSON.stringify(data));
		}
	});

}

function fnInitRangeSelection(isSearch) {
	var action = '<c:url value="/api/rpairtrgetgroup/updateInitTMPR_SLCTN_AT.do"/>';
	var vForm = $("#frmRpairTrgetGroup"); 
	var vPostData = {
		"TRGET_SLCTN_NO" : vForm.find("#TRGET_SLCTN_NO").val()
	};

	
	$.ajax({
		url : action,
		contentType : 'application/json',
		data : JSON.stringify(vPostData),
		dataType : "json",
		cache : false,
		type : 'POST',
		processData : false,
		success : function(data) {
			// alert(JSON.stringify(data));
			// vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);
			// parent.fnRefreshRepairTarget();
			// parent.closeRTDialog();
			// parent.COMMON_UTIL.repairMenuUrlContent( '<c:url
			// value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO='+data.TRGET_SLCTN_NO);
			alert("초기화 하였습니다.");
			if (isSearch) {
				fnSearch();
			}
		},
		error : function(a, b, msg) {
			alert(JSON.stringify(data));
		}
	});
}
// 페이지 로딩 초기 설정
$(document).ready(function() {
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");

	// 달력 생성
	// COMMON_UTIL.cmCreateDatepickerLinked('SCH_STRWRK_DE','SCH_COMPET_DE',
	// 10);

	// 창 조절시 차트 width
	var rw = $(window).width() / 3;

	var vForm = $("#frmRpairTrgetGroup");
	var vPostData = {
		"TRGET_SLCTN_NO" : vForm.find("#TRGET_SLCTN_NO").val(),
		"ROAD_GRAD" : vForm.find("#ROAD_GRAD").val(),
		"ROUTE_CODE" : vForm.find("#ROUTE_CODE").val(),
		"ROAD_NO_VAL" : vForm.find("#ROAD_NO_VAL").val(),
		"ADM_CODE" : vForm.find("#ADM_CODE").val()
	};
	// 검색 목록 그리드 구성

	var colModels = new Array();
	var colIndex = 0;
	colModels[colIndex++] = {
		name : "btn_check",
		index : "btn_check",
		comments : "선택",
		width : 50,
		align : "center",
		hidden : false,
		sortable : false,
		formatter : fn_grid_btn
	}; /* 보수_대상_항목_그룹.행정구역코드 */
	colModels[colIndex++] = {
		name : "DEPT_NM",
		index : "DEPT_NM",
		comments : "관리<br/>기관",
		width : 80,
		align : "left",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.부서코드 */
	colModels[colIndex++] = {
		name : "ROAD_GRAD",
		index : "ROAD_GRAD",
		comments : "도로등급",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.도로등급 */
	colModels[colIndex++] = {
		name : "ROAD_GRAD_NM",
		index : "ROAD_GRAD_NM",
		comments : "도로<br/>등급",
		width : 80,
		align : "left",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.도로등급 */
	colModels[colIndex++] = {
		name : "ROUTE_CODE",
		index : "ROUTE_CODE",
		comments : "노선<br/>번호",
		width : 50,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.노선_코드 */
	colModels[colIndex++] = {
		name : "ROAD_NO_VAL",
		index : "ROAD_NO_VAL",
		comments : "노선<br/>번호",
		width : 50,
		align : "center",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.노선_코드 */

	colModels[colIndex++] = {
		name : "DIRECT_CODE",
		index : "DIRECT_CODE",
		comments : "행선",
		width : 40,
		align : "center",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.행선_코드 */
	colModels[colIndex++] = {
		name : "TRACK",
		index : "TRACK",
		comments : "차로",
		width : 40,
		align : "center",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.차로 */
	colModels[colIndex++] = {
		name : "CELL_TYPE",
		index : "CELL_TYPE",
		comments : "셀_타입",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.셀_타입 */
	colModels[colIndex++] = {
		name : "CELL_TYPE_NM",
		index : "CELL_TYPE_NM",
		comments : "포장셀<br/>구분",
		width : 90,
		align : "left",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.셀_타입 */
	colModels[colIndex++] = {
		name : "STRTPT",
		index : "STRTPT",
		comments : "시점<br/>(m)",
		width : 60,
		align : "right",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.시점 */
	colModels[colIndex++] = {
		name : "ENDPT",
		index : "ENDPT",
		comments : "종점<br/>(m)",
		width : 60,
		align : "right",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.종점 */
	colModels[colIndex++] = {
		name : "DEPT_CODE",
		index : "DEPT_CODE",
		comments : "부서코드",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.부서코드 */
	colModels[colIndex++] = {
		name : "VMTC_GRAD",
		index : "VMTC_GRAD",
		comments : "교통량등급",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.교통량등급 */
	colModels[colIndex++] = {
		name : "VMTC_GRAD_NM",
		index : "VMTC_GRAD_NM",
		comments : "교통<br/>용량",
		width : 80,
		align : "left",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.교통량등급 */
	colModels[colIndex++] = {
		name : "ADM_CODE",
		index : "ADM_CODE",
		comments : "행정구역코드",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.행정구역코드 */
	colModels[colIndex++] = {
		name : "ADM_NM",
		index : "ADM_NM",
		comments : "행정<br/>구역",
		width : 80,
		align : "left",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.행정구역코드 */
	colModels[colIndex++] = {
		name : "CNTRWK_YEAR",
		index : "CNTRWK_YEAR",
		comments : "최근공사<br/>년도",
		width : 80,
		align : "right",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.공사_년도 */
	colModels[colIndex++] = {
		name : "KILLO_LEN",
		index : "KILLO_LEN",
		comments : "연장<br/>(km)",
		width : 80,
		align : "right",
		hidden : false,
		sortable : false,
		formatter : 'number',
		formatoptions : {
			decimalSeparator : ".",
			thousandsSeparator : ",",
			decimalPlaces : 2,
			defaultValue : '0'
		}
	}; /* 보수_대상_항목_그룹.연장 */
	colModels[colIndex++] = {
		name : "AR",
		index : "AR",
		comments : "면적<br/>(m<sup>2</sup>)",
		width : 80,
		align : "right",
		hidden : false,
		sortable : false,
		formatter : 'number',
		formatoptions : {
			decimalSeparator : ".",
			thousandsSeparator : ",",
			decimalPlaces : 2,
			defaultValue : '0'
		}
	}; /* 보수_대상_항목_그룹.면적 */
	colModels[colIndex++] = {
		name : "RPAIR_MTHD_CODE",
		index : "RPAIR_MTHD_CODE",
		comments : "보수_공법_코드",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.보수_공법_코드 */
	colModels[colIndex++] = {
		name : "MSRC_NM",
		index : "MSRC_NM",
		comments : "보수<br/>공법",
		width : 150,
		align : "left",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.보수_공법_코드 */
	colModels[colIndex++] = {
		name : "RPAIR_FEE",
		index : "RPAIR_FEE",
		comments : "단가<br/>(원/m<sup>2</sup>)",
		width : 80,
		align : "right",
		hidden : false,
		sortable : false,
		formatter : 'number',
		formatoptions : {
			decimalSeparator : ".",
			thousandsSeparator : ",",
			decimalPlaces : 0,
			defaultValue : '0'
		}
	}; /* 보수_대상_항목_그룹.단가(원/m2) */
	colModels[colIndex++] = {
		name : "FIX_BUDGET_ASIGN",
		index : "FIX_BUDGET_ASIGN",
		comments : "금액산정 <br/>(천원)",
		width : 80,
		align : "right",
		hidden : true,
		sortable : false,
		formatter : 'number',
		formatoptions : {
			decimalSeparator : ".",
			thousandsSeparator : ",",
			decimalPlaces : 0,
			defaultValue : '0'
		}
	}; /* 보수_대상_항목_그룹.예산_배정 */
	colModels[colIndex++] = {
		name : "FIX_AMOUNT_CALC",
		index : "FIX_AMOUNT_CALC",
		comments : "금액산정 <br/>(천원)",
		width : 100,
		align : "right",
		hidden : false,
		sortable : false,
		formatter : 'number',
		formatoptions : {
			decimalSeparator : ".",
			thousandsSeparator : ",",
			decimalPlaces : 0,
			defaultValue : '0'
		}
	}; /* 보수_대상_항목_그룹.예산_배정 */
	colModels[colIndex++] = {
		name : "CALC_GPCI",
		index : "CALC_GPCI",
		comments : "GPCI",
		width : 50,
		align : "right",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.산정_GPCI */
	colModels[colIndex++] = {
		name : "DMG_VAL",
		index : "DMG_VAL",
		comments : "파손도",
		width : 50,
		align : "right",
		hidden : false,
		sortable : false
	}; /* 보수_대상_항목_그룹.파손도_값 */
	colModels[colIndex++] = {
		name : "btn_loc",
		index : "btn_loc",
		comments : "위치<br/>보기",
		width : 50,
		align : "center",
		hidden : false,
		sortable : false,
		formatter : fn_grid_btn
	}; /* 보수_대상_항목_그룹.행정구역코드 */

	colModels[colIndex++] = {
		name : "GROUP_ITEM_NO",
		index : "GROUP_ITEM_NO",
		comments : "그룹_항목_번호",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.그룹_항목_번호 */
	colModels[colIndex++] = {
		name : "TRGET_SLCTN_NO",
		index : "TRGET_SLCTN_NO",
		comments : "대상_선정_번호",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.대상_선정_번호 */
	colModels[colIndex++] = {
		name : "SLCTN_STEP",
		index : "SLCTN_STEP",
		comments : "선정_단계",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.선정_단계 */
	colModels[colIndex++] = {
		name : "ITEM_SLCTN_STTUS",
		index : "ITEM_SLCTN_STTUS",
		comments : "항목_선정_상태",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.항목_선정_상태 */
	colModels[colIndex++] = {
		name : "SRVY_MT",
		index : "SRVY_MT",
		comments : "조사_월",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.조사_월 */
	colModels[colIndex++] = {
		name : "SRVY_YEAR",
		index : "SRVY_YEAR",
		comments : "조사_년도",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.조사_년도 */
	colModels[colIndex++] = {
		name : "GPCI",
		index : "GPCI",
		comments : "GPCI",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.GPCI */
	colModels[colIndex++] = {
		name : "PC_GRAD",
		index : "PC_GRAD",
		comments : "포장상태등급",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.포장상태등급 */
	colModels[colIndex++] = {
		name : "CALC_YEAR",
		index : "CALC_YEAR",
		comments : "산정_년도",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.산정_년도 */
	colModels[colIndex++] = {
		name : "CALC_MT",
		index : "CALC_MT",
		comments : "산정_월",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.산정_월 */
	colModels[colIndex++] = {
		name : "CALC_PC_GRAD",
		index : "CALC_PC_GRAD",
		comments : "산정_포장상태등급",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.산정_포장상태등급 */
	colModels[colIndex++] = {
		name : "TRNSPORT_QY",
		index : "TRNSPORT_QY",
		comments : "교통_량",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.교통_량 */
	colModels[colIndex++] = {
		name : "RPAIR_MTHD_CODE",
		index : "RPAIR_MTHD_CODE",
		comments : "보수_공법_코드",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.보수_공법_코드 */
	colModels[colIndex++] = {
		name : "MSR_DM_CODE",
		index : "MSR_DM_CODE",
		comments : "공법선정비율_결정방식_코드",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.공법선정비율_결정방식_코드 */
	colModels[colIndex++] = {
		name : "THRHLD",
		index : "THRHLD",
		comments : "임계값",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.임계값 */
	colModels[colIndex++] = {
		name : "FIXING_AT",
		index : "FIXING_AT",
		comments : "고정_여부",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.고정_여부 */
	colModels[colIndex++] = {
		name : "BUDGET_CECK",
		index : "BUDGET_CECK",
		comments : "예산_체크",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false,
		summaryType : 'sum',
		summaryTpl : 'Totals :'
	}; /* 보수_대상_항목_그룹.예산_체크 */
	colModels[colIndex++] = {
		name : "ACCMLT_CALC",
		index : "ACCMLT_CALC",
		comments : "누적_산정",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.누적_산정 */
	colModels[colIndex++] = {
		name : "SLCTN_AT",
		index : "SLCTN_AT",
		comments : "선정_여부",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.선정_여부 */
	colModels[colIndex++] = {
		name : "SLCTN_DT",
		index : "SLCTN_DT",
		comments : "선정_일시",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.선정_일시 */
	colModels[colIndex++] = {
		name : "TMPR_SLCTN_AT",
		index : "TMPR_SLCTN_AT",
		comments : "임시_선정_여부",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.임시_선정_여부 */
	colModels[colIndex++] = {
		name : "NODE_CO",
		index : "NODE_CO",
		comments : "노드_개수",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.노드_개수 */
	colModels[colIndex++] = {
		name : "SLCTN_ORDR",
		index : "SLCTN_ORDR",
		comments : "선정_순서",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.선정_순서 */
	colModels[colIndex++] = {
		name : "DELETE_AT",
		index : "DELETE_AT",
		comments : "삭제_여부",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.삭제_여부 */
	colModels[colIndex++] = {
		name : "USE_AT",
		index : "USE_AT",
		comments : "사용_여부",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.사용_여부 */
	colModels[colIndex++] = {
		name : "CRTR_NO",
		index : "CRTR_NO",
		comments : "생성자_번호",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.생성자_번호 */
	colModels[colIndex++] = {
		name : "CREAT_DT",
		index : "CREAT_DT",
		comments : "생성_일시",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.생성_일시 */
	colModels[colIndex++] = {
		name : "UPDUSR_NO",
		index : "UPDUSR_NO",
		comments : "수정자_번호",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.수정자_번호 */
	colModels[colIndex++] = {
		name : "UPDT_DT",
		index : "UPDT_DT",
		comments : "수정_일시",
		width : 80,
		align : "center",
		hidden : true,
		sortable : false
	}; /* 보수_대상_항목_그룹.수정_일시 */

	// colModels[colIndex++]={name: "money", index: "money",
	// comments: "수정_일시", width: 80,align: "right",
	// sorttype:'int',formatter: 'integer', hidden: true,
	// sortable :false , formatoptions:{thousandsSeparator:","},
	// summaryType:'sum', summaryTpl: 'Totals :' };
	// /*보수_대상_항목_그룹.수정_일시 */

	var colNameList = new Array();
	for (var i = 0; i < colModels.length; i++) {
		colNameList[i] = colModels[i].comments;
	}
	/**
	 * [ {name:'MENU_ID',index:'MENU_ID', align:'center', width:50,
	 * sortable:false} ,{name:'UPPER_MENU_ID',index:'UPPER_MENU_ID',
	 * align:'center', width:60, sortable:false}
	 * ,{name:'MENU_NM',index:'MENU_NM', width:80, sortable:false}
	 * ,{name:'URL',index:'URL', align:'left', width:120, sortable:false}
	 * ,{name:'MENU_DC',index:'MENU_DC', align:'left', width:150,
	 * sortable:false} ,{name:'USE_AT',index:'USE_AT', align:'center', width:40,
	 * sortable:false} ,{name:'DELETE_AT',index:'DELETE_AT', align:'center',
	 * width:10, sortable:false, hidden:true}
	 * ,{name:'UPDUSR_ID',index:'UPDUSR_ID', align:'center', width:50,
	 * sortable:false, hidden:true} ,{name:'UPDT_DT',index:'UPDT_DT',
	 * align:'center', formatter:COMMON_UTIL.fn_set_dateFormat, formatoptions :
	 * {tgtFormat: "yyyy-MM-dd HH:mm:ss" }, width:70, sortable:false}
	 * ,{name:'CRTR_NO',index:'CRTR_NO', align:'center', width:10,
	 * sortable:false, hidden:true} ,{name:'CREAT_DT',index:'CREAT_DT',
	 * align:'center', width:30, sortable:false, hidden:true}
	 * ,{name:'SYS_CODE',index:'SYS_CODE', align:'center', width:50,
	 * sortable:false, hidden:true} ]
	 * 
	 */
	$("#gridArea").jqGrid({
		url : '<c:url value="/"/>' + 'api/rpairtrgetgroup/selectRpairTrgetGroupListPage.do',
		autoencode : true,
		contentType : 'application/json',
		datatype : "local",
		mtype : "POST",
		ajaxGridOptions : {
			contentType : 'application/json; charset=utf-8'
		},
		postData : vPostData,
		ignoreCase : true,
		colNames : colNameList,
		colModel : colModels,
		async : false,
		sortname : '',
		sortorder : "asc",
		rowNum : 50,
		rowList : [ 20, 50, 100, 500 ],
		viewrecords : true,
		pager : '#gridPager',
		rownumbers : true,
		loadtext : "검색 중입니다.",
		footerrow : true,
		userDataOnFooter : true,
		emptyrecords : "검색된 데이터가 없습니다.",
		recordtext : "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})",
		afterInsertRow : function(id, data) {
			if (data.TMPR_SLCTN_AT == 'Y') {
				$('tr#' + id).addClass('notempty-price');
			}
			/*
			 * if(parseInt(data.FIX_BUDGET_ASIGN) > 0) { $('tr#' +
			 * id).addClass('notempty-price'); }else
			 * 
			 * else if(data.SLCTN_AT == 'Y') { $('tr#' +
			 * id).addClass('notempty-price'); }
			 * 
			 */
		},
		ondblClickRow : function(rowId) { // 더블클릭
			// 처리
			fnView(rowId); // 대장 조회
		},
		onSelectRow : function(rowId) { // 클릭 처리
			if (rowId != null) {
				var rowData = $("#gridArea").getRowData(rowId);
			}
		},
		loadBeforeSend : function(tsObj, ajaxParam, settings) {
			if (this.p.mtype === "POST" && $.type(this.p.postData) !== "string") {
				delete this.p.postData.nd;
				delete this.p.postData._search;
				this.p.postData.sidx = this.p.sortname;
				this.p.postData.sord = this.p.sortorder;
				// {"USER_NM":"","USE_AT":"","DELETE_AT":"","pageIndex":1,"pageUnit":50,"sidx":"USER_NM","sord":"desc"}
				// this.p.postData =
				// JSON.stringify(this.p.postData);
				// var
				// pData=$("#frm").cmSerializeObject();
				if (this.p.postData.pageUnit != this.p.postData.rows) {
					this.p.postData.pageUnit = this.p.postData.rows;
				}
				ajaxParam.data = JSON.stringify(this.p.postData);
			}
		},
		multiselect : false,
		multiboxonly : false,
		scroll : true
	}).navGrid('#gridPager', {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : false
	});

	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea', 'div_grid', 200);

	fnSearch();
});
function showRTProgress(txt) {
	parent.$("#dvProgress").dialog("open");
	parent.$("#t_progress").text(txt);
}

function hideRTProgress() {
	parent.$("#dvProgress").dialog("close");
}
// 검색 처리
function fnSearch() {

	var vForm = $("#frmRpairTrgetGroup");
	var vPostData = {
		"TRGET_SLCTN_NO" : vForm.find("#TRGET_SLCTN_NO").val(),
		"ROAD_GRAD" : vForm.find("#ROAD_GRAD").val(),
		"ROUTE_CODE" : vForm.find("#ROUTE_CODE").val(),
		"ROAD_NO_VAL" : vForm.find("#ROAD_NO_VAL").val(),
		"ADM_CODE" : vForm.find("#ADM_CODE").val()
	};

	// alert(JSON.stringify( $("#frm").cmSerializeObject()));
	$("#gridArea").jqGrid("setGridParam", {
		datatype : "json",
		ajaxGridOptions : {
			contentType : 'application/json; charset=utf-8'
		},
		contentType : "application/json",
		page : 1
		// ,postData: JSON.stringify(
		// $("#frm").cmSerializeObject())
		,
		postData : vPostData,
		mtype : "POST",
		loadComplete : function(data) {
			COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
			// this.jqGrid('footerData','set', {ID: 'Total:',
			// amount: 1000000000});
			// var moneySum =
			// $("#gridArea").jqGrid('getCol','TOTAL_BUDGET_ASIGN,TOTAL_AMOUNT_CALC,TOTAL_TMPR_FIX_BUDGET_ASIGN',
			// false, 'cellvalue');
			var total_budget_asign = 0;
			if (data.records != null && data.records > 0) {
				total_budget_asign = data.rows[0].TOTAL_TMPR_FIX_BUDGET_ASIGN;
			}

			$('#gridArea').jqGrid('footerData', 'set', {
				DEPT_NM : '합계',
				FIX_AMOUNT_CALC : total_budget_asign
			});

		}
	}).trigger("reloadGrid");
}

// 상세 조회
function fnView(rowId) {
	if ($.type(rowId) === "undefined" || rowId == "")
		rowId = $("#gridArea").getGridParam("selrow");

	if (rowId != null) {
		var rowData = $("#gridArea").getRowData(rowId);
		var group_item_no = rowData["GROUP_ITEM_NO"];

		alert(group_item_no);
		// COMMON_UTIL.cmWindowOpen('시스템 메뉴 관리', "<c:url
		// value='/manage/menu/selectMenu.do'/>?MENU_ID="+menuId, 550, 400,
		// false, $("#wnd_id").val(), 'center');
	} else
		alert('<spring:message code="warn.checkplz.msg" />');
}

// 창 조절시 차트 resize
$(window).on('resize', function() {

});

function fnRangeSelection() {

	if ($("#SLCTN_BUDGET").val().includes(".")) {
		alert("정수로 입력하세요.");
		$("#SLCTN_BUDGET").val(0);
		return;
	}
	var vSLCTN_BUDGET = parseFloat($("#SLCTN_BUDGET").val());
	var vForm = $("#frmRpairTrgetSlctn");
	var postData = {
		"TRGET_SLCTN_NO" : vForm.find('#TRGET_SLCTN_NO').val(),
		"SLCTN_BUDGET" : vForm.find('#SLCTN_BUDGET').val(),
		"DEPT_CODE" : vForm.find('#DEPT_CODE').val(),
		"DECSN_MTHD_1_RATE" : vForm.find('#DECSN_MTHD_1_RATE').val(),
		"DECSN_MTHD_2_RATE" : vForm.find('#DECSN_MTHD_2_RATE').val(),
		"DECSN_MTHD_3_RATE" : vForm.find('#DECSN_MTHD_3_RATE').val()
	};
	var action = '<c:url value="/api/rpairtrgetslctn/rangeSelection.do"/>';

	// vForm.find('#TRGET_SLCTN_NO').val("");
	if (COMMON_LANG.isnotempty(postData.SLCTN_BUDGET) == false) {
		alert("총 예산을 입력하십시오.");
		return;
	}
	if (COMMON_LANG.isnotempty(postData.DECSN_MTHD_1_RATE) == false) {
		alert("Worst-First 예산 비율을 입력하십시오.");
		return;
	}
	if (COMMON_LANG.isnotempty(postData.DECSN_MTHD_2_RATE) == false) {
		alert("Best-First 예산 비율을 입력하십시오.");
		return;
	}
	if (COMMON_LANG.isnotempty(postData.DECSN_MTHD_3_RATE) == false) {
		alert("Best-First 예산 비율을 입력하십시오.");
		return;
	}
	showRTProgress("보수대상 선정 과정을 진행합니다.");
	$.ajax({
		url : action,
		contentType : 'application/json',
		data : JSON.stringify(postData),
		dataType : "json",
		cache : false,
		type : 'POST',
		processData : false,
		success : function(data) {
			// alert(JSON.stringify(data));
			vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);
			// parent.fnRefreshRepairTarget();
			// parent.closeRTDialog();
			hideRTProgress();
			parent.COMMON_UTIL.repairMenuUrlContent('<c:url value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO=' + data.TRGET_SLCTN_NO);
		},
		error : function(a, b, msg) {
			hideRTProgress();
			alert(JSON.stringify(data));
		}
	});

}

function fnSaveComplete() {

	var vForm = $("#frmRpairTrgetSlctn");
	var postData = {
		"TRGET_SLCTN_NO" : vForm.find('#TRGET_SLCTN_NO').val()
	};
	var action = '<c:url value="/api/rpairtrgetslctn/saveComplete.do"/>';

	$.ajax({
		url : action,
		contentType : 'application/json',
		data : JSON.stringify(postData),
		dataType : "json",
		cache : false,
		type : 'POST',
		processData : false,
		success : function(data) {
			alert(JSON.stringify(data));
			vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);
			// parent.fnRefreshRepairTarget();
			// parent.closeRTDialog();
			parent.COMMON_UTIL.repairMenuUrlContent('<c:url value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO=' + data.TRGET_SLCTN_NO);
		},
		error : function(a, b, msg) {
			alert(JSON.stringify(data));
		}
	});

}

function fnBudgetRateChange(objNo) {
	// alert($("#DECSN_MTHD_"+ objNo+"_RATE").val());
	var rateLimit = 100;
	var rateSum = 0;
	if (objNo > 0) {
		for (var i = 1; i <= 3; i++) {

			if ($("#DECSN_MTHD_" + i + "_RATE").val().includes(".")) {
				alert("정수로 입력하세요.");
				$("#DECSN_MTHD_" + objNo + "_RATE").val(0);
				return;
			}
			rateSum += parseInt($("#DECSN_MTHD_" + i + "_RATE").val());
			if (rateSum > rateLimit) {
				alert("예산분배 비율의 합은 100을 넘을 수 없습니다.");
				$("#DECSN_MTHD_" + objNo + "_RATE").val(0);
				return;
			}
		}
	}

	var rateVal = 0;
	var vSLCTN_BUDGET = parseInt($("#SLCTN_BUDGET").val());
	for (var i = 1; i <= 3; i++) {
		if ($("#DECSN_MTHD_" + i + "_RATE").val() != "") {
			rateVal = parseInt($("#DECSN_MTHD_" + i + "_RATE").val());
			$("#DECSN_MTHD_" + i + "_BUDGET").text(vSLCTN_BUDGET * rateVal / 100);
		}
	}
}
function fndownload_RepairTarget() {
	var vform = $("#frmRpairTrgetGroup");

	var action = '';
	action += '<c:url value=' / '/>rpairtrgetgroup/downloadExcel.do';

	vform.prop("action", action);

	vform.submit();
}
function fn_select_cellSectFilter() {
	var vForm = $("#frmRpairTrgetGroup");
	var vROUTE_CODE = vForm.find("#ROUTE_CODE").val();
	var vROAD_NO_VAL = vForm.find("#ROAD_NO_VAL").text();
	var vADM_CODE = vForm.find("#ADM_CODE").val();

	var tables = [ "CELL_SECT" ];
	var fields = [];// [["ROUTE_CODE","DIRECT_CODE","TRACK"]];
	var values = [];// [[route_code, direct_code, track]];
	var fieldNames = [];
	var fieldValues = [];
	var operator = null;
	if (COMMON_LANG.isnotempty(vROUTE_CODE) && COMMON_LANG.isnotempty(vADM_CODE)) {
		fieldNames.push("ROUTE_CODE");
		fieldValues.push(vROUTE_CODE);
		fieldNames.push("ADM_CODE");
		fieldValues.push(vADM_CODE);
		fields.push(fieldNames);
		values.push(fieldValues);
		operator = "AND";
	} else {
		if (COMMON_LANG.isnotempty(vROUTE_CODE)) {
			fields.push("ROUTE_CODE");
			values.push(vROUTE_CODE);
		}
		if (COMMON_LANG.isnotempty(vADM_CODE)) {
			fields.push("ADM_CODE");
			values.push(vADM_CODE);
		}
	}

	var attribute = {
		attributes : {
			fillColor : '#ff0000',
			strokeColor : '#ff0000'
		}
	};

	// 모든 팝업창 최소화
	// parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.repairtargetsHideBottom();

	var attrLayer = parent.gMap.getLayerByName('GAttrLayer');
	var multiLayer = parent.gMap.getLayerByName('GAttrLayerMulti');

	parent.gMap.setLayerIndex(attrLayer, 1);
	parent.gMap.setLayerIndex(multiLayer, 0);
	//
	MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values, null, operator, attribute, true, 1, 0);
}
// 위치조회
function fn_select_cellSectRange(route_code, direct_code, track, strtpt, endpt) {
	var tables = [ "CELL_SECT" ];
	var fields = [ [ "ROUTE_CODE", "DIRECT_CODE", "TRACK" ] ];
	var boundaryFields = [ [ "STRTPT", "ENDPT" ] ];
	var boundaryValues = [ [ strtpt, endpt ] ];
	var values = [ [ route_code, direct_code, track ] ];
	var attribute = {
		attributes : {
			fillColor : '#ff0000',
			strokeColor : '#ff0000'
		}
	};

	// 모든 팝업창 최소화
	// parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.repairtargetsHideBottom();

	var attrLayer = parent.gMap.getLayerByName('GAttrLayer');
	var multiLayer = parent.gMap.getLayerByName('GAttrLayerMulti');

	parent.gMap.setLayerIndex(attrLayer, 1);
	parent.gMap.setLayerIndex(multiLayer, 0);
	
	MAP.fn_get_selectFeatureByAttrBoundary(parent.gMap, tables, fields, values, null, "AND", attribute, true, 1, 0, boundaryFields, boundaryValues);
}
// 필터적용
function fnFilterApply() {
	// btnFilterSearch
	var vForm = $("#frmRpairTrgetGroup");

	var vUseFilter = vForm.find("#useFilter").val();
	if (vUseFilter == "false") {
		vForm.find("#useFilter").val(true);
		$("#btnFilterSearch").html("필터해제");
	} else {
		vForm.find("#useFilter").val(false);
		vForm.find("#ROAD_GRAD").val("");
		vForm.find("#ROUTE_CODE").val("");
		vForm.find("#ROAD_NO_VAL").text("");
		vForm.find("#ADM_CODE").val("");
		$("#btnFilterSearch").html("필터적용");
	}
	fnSearch();
}
function fnFilterSelect() {

	fnSearch();
}
