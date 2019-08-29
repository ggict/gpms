/**
 *  셀 속성 편집 관련 js
 */

//jqGrid
function fnSelectCellSect(g2Id) {
	//
	var postData = {"GID" : "1"};
	
	// 검색 목록 그리드 구성
	$("#gridArea").jqGrid({
		url: contextPath + 'api/selectCellSectList.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,postData: $("#frm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["GID","노선번호","노선명","관리주체","행정구역","도로등급","교통용량","SECTION구분","위치보기"]
	   	,colModel:[
	   	     {name:'GID',index:'GID', hidden: true}
	   	    ,{name:'ROUTE_CODE',index:'ROUTE_CODE', align:'center',width:30, sortable:true}
	   	    ,{name:'ROUTE_NM',index:'ROUTE_NM', align:'center',width:50, sortable:true}
			,{name:'DEPT_CODE',index:'DEPT_CODE', align:'center', width:50, sortable:true}
			,{name:'ADM_CODE',index:'ADM_CODE', align:'center', width:30, sortable:true}
			,{name:'ROAD_GRAD',index:'ROAD_GRAD', align:'center', width:50, sortable:true}
			,{name:'VMTC_GRAD',index:'VMTC_GRAD', align:'center', width:50, sortable:true}
			,{name:'CELL_TYPE',index:'CELL_TYPE', align:'center', width:50, sortable:true}
			,{name:'btn_loc',index:'btn_loc', align:'center', width:50, formatter: fn_create_btn, sortable: false}
	   	]
		,async : false
		,sortname: 'ROUTE_CODE'
	    ,sortorder: "asc" 
	   	,rowNum: 10
	   	,rowList: [10,20,50,100]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
			fnView(rowId);	// 대장 조회
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
	   			this.p.postData.sidx = this.p.sortname;
	   			this.p.postData.sord = this.p.sortorder;
	   			if(this.p.postData.pageUnit != this.p.postData.rows){
	   				this.p.postData.pageUnit = this.p.postData.rows;
	   			}
	   			ajaxParam.data = JSON.stringify(this.p.postData);
	   		}
	   	}
		,multiselect: false
		,multiboxonly: false
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	// 그리드 초기 설정 함수 [그리드아이디, 상단 여유공간 크기] (필수)
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 150);
		
	fnSearch(g2Id); 
}
	
//검색 처리
function fnSearch(g2Id) {
	var postData = {"USE_AT":"Y"};
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:   $("#frm").cmSerializeObject()
		,mtype: "POST"
		,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}
