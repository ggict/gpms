
/**
 *  조사 자료 관련 JS
 * 
 */

var SRVY = (function($,undefined){
	
	/**
	 * 평가 대상 총 개수
	 * @member {Number} srvyListTotCnt
	 */
	var srvyListTotCnt = 0;

	/**
	 * 반복 타이머
	 * @member {Object} interval
	 */
	var interval = null;


	/**
	 * 평가 대상 리스트
	 * @member {Array} srvyList
	 */
	var srvyList = [];
	/**
	 * 옵션
	 * @member {Object} option
	 */
	var option = {};
	
	/**	
	* @description 포장평가 대상을 삭제한다.
	*/
	var deleteEvlList = function(){
		
		$("#t_srvydelete").text("");
		$("#divDeleteBar").show();
		$("#divButtn").hide();
		var seqList = "";
		
		if(srvyList.length==0){
			alert("삭제할 조사자료를 선택해주세요");
			return false;
		}
		
		seqList = srvyList.join(",");
			    	
		$.ajax({
			url: contextPath + 'srvy/srvyDtaExcelDelete.do'
			,data: {"SRVY_NO" : seqList}
			,type: 'post'
			,dataType: 'json'
			,success: function(data){				
				alert( data.delCnt + "건의 조사자료가 삭제되었습니다.");
				option.iframe[option.callback]();
				$("#lodingbar").hide();
				$("#divDeleteBar").hide();
				$("#divButtn").show();
			}
			,error: function(a,b,msg){
				alert( "조사자료 삭제에 문제가 발생하였습니다.");
				option.iframe[option.callback]();
				$("#lodingbar").hide();
				$("#divDeleteBar").hide();
				$("#divButtn").show();
			}
		});
	};
	
	/**	
	* @description 포장상태를 평가한다.
	*/
	var evlSrvyDta = function(){
		srvyListTotCnt = 0;
		
		hideAllDiv();
		$("#divSrvyProc2").show();
		
		$("#t_srvyProgress").text("포장상태 평가 진행중입니다.");
		
		var seqList = "";
		
		if(srvyList.length==0){
			alert("평가할 조사자료를 선택해주세요");
			return false;
		}
		
		srvyListTotCnt = srvyList.length;
		seqList = srvyList.join(",");
		
		$.ajax({
			url: contextPath + 'srvy/saveDtaList.do'
			,data: {SRVY_NOS : seqList}
			,type: 'post'
			,dataType: 'json'
			,beforeSend: function(){
				checkSrvyList();
				interval = setInterval(checkSrvyList, 10000);
			}
			,success: function(data){
				clearInterval(interval);
				//$("#lodingbar").hide();
				
				var resultTxt = "<strong>[포장상태 평가 진행 결과]</strong><br><br>"
							  + "전체 포장상태 평가 건수 : " + data.totCount + "건<br>"
							  + "포장상태 평가 성공 건수 : " + data.successCount + "건<br>"
							  + "포장상태 평가 실패 건수 : " + (data.totCount - data.successCount) + "건<br><br>";
				
				if(data.noFileList.length > 0){
					resultTxt += "<br>"
							  +  "총 " + data.noFileList.length + "건의 엑셀 파일이 존재하지 않습니다.<br><br>";
				}
				
				$("#t_srvyResult").html(resultTxt);
				
				hideAllDiv();
				$("#divSrvyProc3").show();
				
				option.iframe[option.callback]();
				
				$("#evl_success").hide();
				$("#gps_sm_success").hide();
			}
			,error: function(a,b,msg){
				clearInterval(interval);
				$("#lodingbar").hide();
				
				option.iframe[option.callback]();
				
				$("#evl_success").hide();
				$("#gps_sm_success").hide();
			}
		});
	}
	
	/**	
	* @description 포장상태를 진행 상황을 체크한다.
	* @param {Boolean} _sTotYN : 전체 개수 체크 여부
	*/
	var checkSrvyList = function(){
		
		
		$.ajax({
			url: contextPath + 'srvy/api/getTargetSrvyDta.do'
			,type: 'post'
			,dataType: 'json'
			,data : JSON.stringify({"SRVY_NOS" : srvyList.join(",")})
			,contentType : 'application/json'
			,success: function(data){
				if(data == null || data.DATA_CO == null) {
					clearInterval(interval); 
					return;
				}
				
				if(srvyListTotCnt != 0 ){
					
					$("#t_srvyProgress").html((srvyListTotCnt - parseInt(data.DATA_CO) + 1) + "건 / 총 " + srvyListTotCnt + "건<br>"+data.FILE_NM);
					
					if(data.EVL_PROCESS_AT == "N"
						&& data.GPS_CORTN_PROCESS_AT == "N"
						&& data.SM_PROCESS_AT == "N"){
						
						$("#evl_success").hide();
						$("#gps_sm_success").hide();
						$("#evl_state").removeClass("state_s");
						$("#evl_state").addClass("state_b");
						$("#gps_sm_state").removeClass("state_s");
						$("#gps_sm_state").addClass("state_b");
						clearInterval(interval);
						interval = setInterval(checkSrvyList, 10000);
					}else{
						if(data.EVL_PROCESS_AT == "Y"){
							$("#evl_state").removeClass("state_b");
							$("#evl_state").addClass("state_s");
							$("#evl_success").show();
							clearInterval(interval);
							interval = setInterval(checkSrvyList, 5000);
						}
						
						if(data.GPS_CORTN_PROCESS_AT == "Y" && data.SM_PROCESS_AT == "Y"){
							$("#gps_sm_state").removeClass("state_b");
							$("#gps_sm_state").addClass("state_s");
							$("#gps_sm_success").show();
							clearInterval(interval);
							interval = setInterval(checkSrvyList, 10000);
						}
					}
					
				}
			}
			,error: function(a,b,msg){
				clearInterval(interval);
			}
		});
	}
	
	/**	
	* @description 예측자료를 생성
	*/
	var createPredct = function(){
		
		$("#divPredctBar").show();
		$("#divPredctButtn").hide();
		
		var seqList = "";
		
		if(srvyList.length==0){
			alert("예측자료 생성대상을 선택해주세요");
			return false;
		}
		
		seqList = srvyList.join(",");
		
		$("#t_predct").text("예측자료 생성을 진행중입니다.");
		
		$.ajax({
			url: contextPath + 'smdtalaststtus/createPredct.do'
			,data: {"SRVY_NO" : seqList}
			,type: 'post'
			,dataType: 'json'
			,success: function(data){				
				alert( "총 " + data.totCnt + "건 중 " + data.successCnt + "건의 예측자료 생성이 완료되었습니다.");
				option.iframe[option.callback]();
				$("#lodingbar").hide();
				$("#divPredctBar").hide();
				$("#divPredctButtn").show();
				$("#t_predct").text("예측자료를 생성하시겠습니까?");
			}
			,error: function(a,b,msg){
				alert( "예측자료 생성에 오류가 발생하였습니다.");
				option.iframe[option.callback]();
				$("#lodingbar").hide();
				$("#divPredctBar").hide();
				$("#divPredctButtn").show();
				$("#t_predct").text("예측자료를 생성하시겠습니까?");
			}
		});
	};
	
	/**	
	* @description 프로세스 div를 전부 숨긴다.
	*/
	var hideAllDiv = function(){
		$("#divSrvyProc1").hide();
		$("#divSrvyProc2").hide();
		$("#divSrvyProc3").hide();
		$("#divSrvyDelete").hide();
		$("#divPredct").hide();
	};

	/**	
	* @description 옵션을 세팅한다.
	* @param {Object} _oData : 옵션값
	*/
	var setOption = function(_oData){
		option = _oData;
	}
	
	/**	
	* @description 포장평가 대상을 세팅한다.
	* @param {Array} _oList : 포장평가 대상 리스트
	*/
	var setSrvyList = function(_oList){
		srvyList = _oList;
	}
	
	return {
		deleteEvlList			:			deleteEvlList,
		
		evlSrvyDta				:			evlSrvyDta,
		checkSrvyList			:			checkSrvyList,
		
		createPredct			:			createPredct,
		
		hideAllDiv				:			hideAllDiv,
		
		setOption				:			setOption,
		setSrvyList				:			setSrvyList
	}
}(jQuery));


