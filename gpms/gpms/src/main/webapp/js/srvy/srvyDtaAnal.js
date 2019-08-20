/**
 *  조사 자료 관련 JS
 * 
 */

// 조사자료 화면 초기 실행
function fn_init_svryDtaAnal() {
	
	//프로그래스바
	$("#dvProgress").dialog({
		width : 170,
		modal : true,
		open: function (event, ui) {
			$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
		}
	});
	$("#dvProgress").dialog("close");

	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: 'api/srvyDtaAnalTrgetList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["SRVY_YEAR", "SRVY_MT", "선택", "조사자료", "산정할 년도", "자료(행)", "삭제"]
	   	,colModel:[
			{name:'SRVY_YEAR',index:'SRVY_YEAR', hidden: true}
			,{name:'SRVY_MT',index:'SRVY_MT', hidden: true}
			,{name:'radio',index:'radio', align:'center', width:20, sortable: false, formatter: function (cellValue, option) {
                return '<input type="radio" name="radio_' + option.gid + '" />';} }
			,{name:'SRVY_DT',index:'SRVY_DT', align:'center', width:70, sortable: false}
			,{name:'SRVY_DT',index:'SRVY_DT', align:'center', width:70, sortable: false}
			,{name:'DTA_CNT',index:'DTA_CNT', align:'center', width:70, sortable: false}
			,{name:'btn_del',index:'btn_del', align:'center', width:70, sortable:false, formatter: fn_create_btn_del} 
	   	]
		,async : false
	   	,rowNum: 50
	   	,rowList: [20,50,100]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
		}
	   	,onSelectRow: function(rowId) {		// 클릭 처리
			if( rowId != null ) {
				var rowData =$( "#gridArea" ).getRowData(rowId);
			}
		}
	   	,loadBeforeSend:function(tsObj, ajaxParam, settings){
	   		if(this.p.mtype==="POST"&& $.type(this.p.postData)!=="string" ){
	   			delete this.p.postData.nd;
	   			delete this.p.postData._search;
	   			if(this.p.postData.pageUnit != this.p.postData.rows){
	   				this.p.postData.pageUnit = this.p.postData.rows;
	   			}
	   			/*this.p.postData.sidx = this.p.sortname;
	   			this.p.postData.sord = this.p.sortorder;*/
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
	   	,loadComplete: function(object) {
	   	}
		,multiselect: false
		,multiboxonly: false
		//,scroll: true
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	COMMON_UTIL.cmInitPopupGridIncludeLeft('gridArea', 450, 300);
	
	// LOG
	$("#logArea").append("<span>"+ COMMON_UTIL.fnGetTimeStampAll() +" >> 조사자료 분석<br/></span>");
	
	fn_search();
}


//검색 처리
function fn_search() {
	$('#frm')[0].reset(); //폼 초기화(리셋);
	$(".cbox").prop("checked", false);
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
	}).trigger("reloadGrid");
}

//파일 삭제 버튼 생성
function fn_create_btn_del(cellValue, options, rowObject) {
	var btn = "";
	var nm = options.colModel.name;
	
	switch(nm) {
	case "btn_del" :
		// 클릭시 파일 다운로드
		btn = "<a href='#' onclick=\"fn_delete('" + rowObject.SRVY_YEAR + "', '" + rowObject.SRVY_MT + "');\">삭제</a>";
		break;
	}
	
	return btn;
}

// 조사자료 분석 대상 삭제
function fn_delete(srvyYear, srvyMt){
	if(srvyYear=="" || srvyMt == ""){
		alert("선택값이 유효하지 않습니다.");
		return false;
	}
	
	msg = "선택하신 조사내역에 대한 이력을 삭제 하시겠습니까?\n이력을 삭제하여도 조사자료는 삭제되지 않습니다.";
	if (confirm(msg)){
	
		$("#dvProgress").dialog("open");
		$("#t_progress").text("자료를 삭제 중 입니다.");
		
		// 조사 년/월에 해당하는 JGGUGANYN= 'N' 정보 삭제
		$.ajax({
			url:'api/delSrvyDtaAnalTrget.do'
			,data:{"SRVY_YEAR" : srvyYear, "SRVY_MT" : srvyMt}
			,type:'post'
			,dataType:'json'
			,success: function(data){
				$("#dvProgress").dialog("close");
				$("#logArea").append("<span>"+ COMMON_UTIL.fnGetTimeStampAll() + " >> " + data.srvyYear + "년 " + data.srvyMt + "월 조사자료 등록 현황내역 삭제 <br/></span>");
				fn_search();
			}
			,error: function(a,b,msg){
				$("#dvProgress").dialog("close");
				$("#logArea").append("<span>"+ COMMON_UTIL.fnGetTimeStampAll() + " >> 조사자료 등록 현황내역 삭제 오류 <br/></span>");
			}
		});
	}
}

// 조사자료 분석
function fn_analyze(){
	var $selRadio = $('input[name=radio_' + $("#gridArea")[0].id + ']:checked'), $tr;
	if ($selRadio.length > 0) {
		$tr = $selRadio.closest('tr');
		if ($tr.length > 0) {
			var rowData = $("#gridArea").getRowData( $tr.attr('id') );
			
			var msg = "";
			var y = rowData.SRVY_YEAR;
			var m = rowData.SRVY_MT;
			
			// 순차적 등록 유도
			if( $tr.attr('id') > 1 ) {
				msg = "[주의] 선택하신 분석대상 보다 이전의 자료가 있습니다.\n계속 진행하시겠습니까?";
				if(!confirm(msg)) {
					return false;
				}
			}
			
	    	msg = rowData.SRVY_DT + " 조사자료에 대한 집계/산정 작업을 진행 하시겠습니까?";
	    	
	    	if(confirm(msg)){
	    		// 조사년도와 조사월이 있는지 확인
	    		if(y=="" || m==""){
	    			alert("입력값의 오류로 산정 작업을 진행할 수 없습니다.");
	    			$("#logArea").append("<span>"+ COMMON_UTIL.fnGetTimeStampAll() + " >> 입력값 오류<br/></span>");
	    			return false;
	    		}
	    		
	    		// 집계/산정 시작
	    		$("#dvProgress").dialog("open");
				$("#t_progress").text("자료를 분석 중 입니다.");
		    	$("#logArea").append("<span>"+ COMMON_UTIL.fnGetTimeStampAll() + " >> " + y + "년 " + m + "월 조사자료를 이용한 집계/산정 작업 시작 ... </span>");
		    	
	    		$.ajax({
	    			url:'/gpms/srvy/analyzeDtaList.do'
	    			,data:{"SRVY_YEAR" : y, "SRVY_MT" : m}
	    			,type:'post'
	    			,dataType:'json'
	    			,success: function(data){
	    				$("#dvProgress").dialog("close");
	    					
    					if("true"===data.result)
    					{
	    					$("#logArea").append("<font color='blue'>완료<br/></font>");
	    					$("#logArea").append("<span>"+ COMMON_UTIL.fnGetTimeStampAll() + " >> 조사자료 집계/산정이 완료되었습니다. <br/></span>");
	    					
	    				} else {
	    					$("#logArea").append("<font color='blue'>실패<br/></font>");
	    					$("#logArea").append("<span>"+ COMMON_UTIL.fnGetTimeStampAll() + " >> 조사자료 집계/산정이 실패했습니다. <br/></span>");
	    				}
    					
    					fn_search();
	    			}
	    			,error: function(a,b,msg){
	    				$("#dvProgress").dialog("close");
	    				$("#logArea").append("<font color='blue'>오류발생<br/></font>");
	    				$("#logArea").append("<span>"+ COMMON_UTIL.fnGetTimeStampAll() + " >> 조사자료 집계/산정에 오류가 발생되어 작업이 중지되었습니다. <br/></span>");
	    			}
	    		});
	    	}
	    }
	} else {
		alert("분석할 대상을 선택해주세요.");
	}
}