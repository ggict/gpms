<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
<%--

--%>
.srtmenuarea .ui-accordion-header {color:black !important;background-color:#e6e6e6 !important;border-color:#e6e6e6 !important;font-size:14px;}
.srtmenuarea .ui-accordion .ui-accordion-icons {
     padding-left: 0em !important;
}
.srtmenuarea .ui-accordion .ui-accordion-header .ui-accordion-header-icon{
left:0em  !important;
}
.srtmenuarea .ui-widget-content{
background-color:#e6e6e6 !important;
}
.srtmenuarea .ui-helper-reset{
line-height:1.5em !important;
}

.srtmenuarea .ui-accordion .ui-accordion-content {
    padding: 1em 1em 1em 2.2em;
    border-top: 0;
    overflow: auto;

}

.srtmenuarea .ui-accordion-header-active{
background-color:#ffd9ad  !important;
}
.srtmenuarea .ui-state-default, .srtmenuarea .ui-widget-content .ui-state-default, .srtmenuarea .ui-widget-header .ui-state-default, .srtmenuarea .ui-widget-header .ui-state-default{
font-weight:normal !important;
}

</style>
<div class="srtmenuarea" style="width:220px;height:777px;">
	<div id="sub_repairtargets">
		<h2  class="tl pl15">보수대상 선정 <a href="#" class="leftmenu" onclick="fnOpenRpairConfig();">환경설정</a></h2>
		<h4 class="leftTit">보수대상 선정</h4>
		<div>
		<div class="schbx mt10" style="min-height:250px">
		<ul class="sch" style="height:160px;">
			<li class="wid100 mt10"><label  class="wid100" >보수대상선정명</label></li>
			<li class="wid100">
			<input type="hidden" id="SLCTN_MTH" name="SLCTN_MTH" value=""/>
			<input type="hidden" id="SLCTN_STTUS" name="SLCTN_STTUS" value="RTSS0001"/>
			<input type="hidden" id="SLCTN_PURPS" name="SLCTN_PURPS" value="RTSP0002"/>
			<input type="hidden" id="TRGET_SLCTN_NO" name="TRGET_SLCTN_NO" value=""/>
			<input type="text" id="SLCTN_OPERT_NM" value="" style="width:192px"   />
			</li>
			<li class="wid100 mt15"><label class="wid100">보수대상 선정일</label></li>
			<li class="wid100"><span class="calendar btn_calendar">
			 	<input type="text" id="SLCTN_DT" name="SLCTN_DT" style="width:174px;" />
			</span></li>
			<li class="wid100 mt10">
				<a href="#" class="playbtn" onclick="fnRepairTargetStart();">선정 시작</a>
			</li>
		</ul>
		</div>
		<div class="posila">
		<h4 class="leftTit">보수대상선정이력</h4>
		<div class="scroll" id="repairTargetList">
		<ul class="lst2">
		<%--
		 <li><a href="#"><img src="images/ic_date.png" alt="날짜선택" class="vm" /> 2017.7.01</a><ul>
                 <li>선정명칭1<a href="#" class="btndel"><img src="images/btn_del.png" alt="닫기" /></a></li>
                 <li>선정명칭2<a href="#" class="btndel"><img src="images/btn_del.png" alt="닫기" /></a></li>
                 <li>선정명칭3<a href="#" class="btndel"><img src="images/btn_del.png" alt="닫기" /></a></li>
         </ul></li>
		 --%>
		</ul>
		</div>
		</div></div>

</div>
</div>
<script type="text/javascript" defer="defer">
$( document ).ready(function() {
	//달력 생성
	COMMON_UTIL.cmCreateDatepicker('SLCTN_DT', 22, "/images/btn_calendar.gif");

	$("#repairTargetList").height($(parent.window).height() - 510);

	$( "#repairTargetList" ).accordion({
        collapsible: true
     });
});

$(window).resize(function(){
$("#repairTargetList").height($(parent.window).height() - 510);

	$( "#repairTargetList" ).accordion("refresh");
});


function initRepairTargets(){
	var currentDate = new Date();
	var yyyyMMddNow = currentDate.format("yyyy-MM-dd");
	$("#sub_repairtargets").find("#SLCTN_DT").val(yyyyMMddNow);
	$("#sub_repairtargets").find("#TRGET_SLCTN_NO").val('');
	$("#sub_repairtargets").find("#SLCTN_OPERT_NM").val('');
	$("#sub_repairtargets").find("#SLCTN_OPERT_NM").text('');

	$("#repairTargetList").css("display", "");
	COMMON_UTIL.repairMenuUrlContent( '<c:url value="rpairtrgetslctn/intro.do"/>');
	$("#repairTargetList").css("display", "");
	fnRefreshRepairTarget();
}

function fnRefreshRepairTarget(){
	var action = '<c:url value="/api/rpairtrgetslctn/selectRpairTrgetSlctnList.do"/>';
	//var vForm = $("#sub_repairtargets");
	//vForm.find('#TRGET_SLCTN_NO').val("");



	var postData ={"USE_AT":"Y"};
	$.ajax({
		url: action,
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
		success: function(data){
			vTag = '<ul class="lst2" >';
			vTag ='';
			//alert(JSON.stringify(data));
			var vTagList = "";
			var vTagMonth = "";
			var monthChange = false;
			if(data.length > 0){
				var prevMonth="";
				var changeTagBegin = false;
				for(var i=0;i<data.length;i++){
					var item = data[i];
					if(prevMonth!="" ){
						if( prevMonth == item.SLCTN_MONTH){}
						else{
							monthChange = true;
						}
					}
					else{
						prevMonth = item.SLCTN_MONTH;

					}


					if(monthChange){
						//vTagMonth ='<li ><a href="#"><img src="images/ic_date.png" alt="날짜선택" class="vm" />'+ prevMonth +'</a><ul>';
						//vTag += vTagMonth + vTagList+"</ul></li>";
						//vTagMonth ='<h5><img src="images/ic_date.png" alt="날짜선택" class="vm" />'+ prevMonth +'</h5><div style="height:100%;"><ul><li ><a href="#"><img src="images/ic_date.png" alt="날짜선택" class="vm" />'+ prevMonth +'</a><ul>';
						//vTag += vTagMonth + vTagList+"</ul></li></ul></div>";
						vTagMonth ='<h5><img src="images/ic_date.png" alt="날짜선택" class="vm" style="padding-left: 15px;" />&nbsp;&nbsp;'+ prevMonth +'</h5><div style="overflow-y:auto;"><ul>';
						vTag += vTagMonth + vTagList+" </ul></div>";
						vTagList = "";
						monthChange  = false;
						prevMonth = item.SLCTN_MONTH;
						if( (i+1)<=data.length){
							var opertName = item.SLCTN_OPERT_NM;
							var operateSLCTN_STTUS = (item.SLCTN_STTUS == "RTSS0010")?"":"(작업중)";
							var editImgTag = "";
							if(item.SLCTN_STTUS != "RTSS0010"){
								editImgTag ='<img src="images/btn_list_mod.png" alt="작업중" title="작업중" />';
							}
							if(getTextLength(opertName)>18){
								opertName = getSubText(opertName, 19)+"..";
							}
							vTagList +='<li  ><a href="javascript:loadRepairTargets(\''+ item.SLCTN_OPERT_NM+'\','+ item.TRGET_SLCTN_NO+')">'+ opertName +'</a>&nbsp;'+editImgTag+'<a class="btndel" style="float:right;"    href="javascript:deleteRepairTargetCheck(\''+ item.SLCTN_OPERT_NM+'\','+ item.TRGET_SLCTN_NO+')"><img src="images/btn_del.png" alt="삭제" title="삭제"/></a></li>'	;
						}
					}
					else{
						var opertName = item.SLCTN_OPERT_NM;
						var operateSLCTN_STTUS = (item.SLCTN_STTUS == "RTSS0010")?"":"(작업중)";
						var isEdit = item.SLCTN_STTUS != "RTSS0010";
						var editImgTag = "";
						if(item.SLCTN_STTUS != "RTSS0010"){
							editImgTag ='<img src="images/btn_list_mod.png" alt="작업중" title="작업중" />';
						}
						if(getTextLength(opertName)>18){
							opertName = getSubText(opertName, 19)+"..";
						}
						vTagList +='<li  ><a href="javascript:loadRepairTargets(\''+ item.SLCTN_OPERT_NM+'\','+ item.TRGET_SLCTN_NO+')">'+ opertName +'</a>&nbsp;'+editImgTag+'<a class="btndel" style="float:right;"  href="javascript:deleteRepairTargetCheck(\''+ item.SLCTN_OPERT_NM+'\','+ item.TRGET_SLCTN_NO+')"><img src="images/btn_del.png" alt="삭제" title="삭제" /></a></li>'	;
					}

				}
			}
			if(vTagList!="" && vTagList!=null){
				//vTagMonth ='<h5><img src="images/ic_date.png" alt="날짜선택" class="vm" />'+ prevMonth +'</h5><div style="height:100%;"><ul><li ><a href="#"><img src="images/ic_date.png" alt="날짜선택" class="vm" />'+ prevMonth +'</a><ul>';
				//vTag += vTagMonth + vTagList+"</ul></li></ul></div>";
				vTagMonth ='<h5><img src="images/ic_date.png" alt="날짜선택" class="vm" style="padding-left: 15px;" />&nbsp;&nbsp;'+ prevMonth +'</h5><div style="overflow-y:auto;"><ul>';
				vTag += vTagMonth + vTagList+" </ul></div>";

				vTagList = "";
				monthChange  = false;
			}
			//vTag += '</ul>';
			$("#repairTargetList").accordion("destroy");
			$("#repairTargetList").html(vTag);
			$("#repairTargetList").css("display", "");
			//vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);

			$( "#repairTargetList" ).accordion({
	               collapsible: true,
	               heightStyle: "content"
	            });
			//ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active
		},
		error: function(a,b,msg){
			alert(JSON.stringify(data));
		}
	});
}

function repairTargetStart(){


	var vForm = $("#sub_repairtargets");
	var postData ={"SLCTN_OPERT_NM":vForm.find('#SLCTN_OPERT_NM').val()
			,"SLCTN_DT":vForm.find('#SLCTN_DT').val()
			,"SLCTN_MTH":vForm.find('#SLCTN_MTH').val()
			,"SLCTN_STTUS":vForm.find('#SLCTN_STTUS').val()
			,"SLCTN_PURPS":vForm.find('#SLCTN_PURPS').val()};
	var action = '<c:url value="/api/rpairtrgetslctn/addRpairTrgetSlctn.do"/>';

	vForm.find('#TRGET_SLCTN_NO').val("");
	if(COMMON_LANG.isnotempty(postData.SLCTN_OPERT_NM)==false){
		alert("보수대상선정명을 입력하십시오.");
		return;
	}
	if(COMMON_LANG.isnotempty(postData.SLCTN_DT)==false){
		alert("보수대상선정일을 선택 하십시오.");
		return;
	}

	if($("#content_repairtargets")[0].contentWindow.fnInitRangeSelection!=null){
		if(typeof($("#content_repairtargets")[0].contentWindow.fnInitRangeSelection)=="function" ){
			$("#content_repairtargets")[0].contentWindow.fnInitRangeSelection(false);
		}
	}

	$.ajax({
		url: action,
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
		success: function(data){
			//alert(JSON.stringify(data));
			vForm.find('#TRGET_SLCTN_NO').val(data.TRGET_SLCTN_NO);
			fnRefreshRepairTarget();
			closeRTDialog();
			COMMON_UTIL.repairMenuUrlContent( '<c:url value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO='+data.TRGET_SLCTN_NO);
		},
		error: function(a,b,msg){
			alert(JSON.stringify(data));
		}
	});
}
function fnRepairTargetStart(){
	var msgContents = "보수대상 선정작업을 시작하시겠습니까?<br/>선정과정 중이라면 모든 선정작업이 초기화됩니다.<br />";
	$("#divRTDialog").dialog({title : "보수대상 선정 - 선정 시작" , width: "380px" });
	var buttonTags = '<a href="#" class="schbtn" onclick="repairTargetStart();" style="width:70px" >선정 시작</a>&nbsp;&nbsp;';
	buttonTags += '<a href="#" class="graybtn" onclick="closeRTDialog();" style="width:70px" >취소</a>';
	$("#divRTButtn").html(buttonTags);
	$("#txtRTContents").html(msgContents);
	$("#divRTDialog").dialog("open");
}

function fnRepairTargetLoad(trget_slctn_no){
	COMMON_UTIL.repairMenuUrlContent( '<c:url value="rpairtrgetslctn/selectRpairTrgetSlctn.do"/>?TRGET_SLCTN_NO='+trget_slctn_no);
	closeRTDialog();
}
function closeRTDialog(){
	$("#lodingbar").hide();
	$("#divRTDialog").dialog("close");
}
function loadRepairTargets(slctn_opert_nm, trget_slctn_no){

	var msgContents = "\""+slctn_opert_nm + "\" 불러오기를 수행 하시겠습니까?<br/>불러온 정보로보수대상 선정 정보가 자동설정 됩니다.<br />";
	$("#divRTDialog").dialog({title : "보수대상 선정 – 보수대상 선정 이력 불러오기" , width: "380px" });
	var buttonTags = '<a href="#" class="schbtn" onclick="fnRepairTargetLoad(\''+trget_slctn_no+'\');" style="width:70px" >불러오기</a>&nbsp;&nbsp;';
	buttonTags += '<a href="#" class="graybtn" onclick="closeRTDialog();" style="width:70px" >취소</a>';
	$("#divRTButtn").html(buttonTags);
	$("#txtRTContents").html(msgContents);
	$("#divRTDialog").dialog("open");


}

function deleteRepairTargetCheck(slctn_opert_nm, trget_slctn_no){
	msg = "\""+slctn_opert_nm + "\" 삭제를 수행 하시겠습니까?";

	if(confirm(msg)==false){
		return;
	}
	var action = '<c:url value="/api/rpairtrgetslctn/selectRpairTrgetSlctn.do"/>';
	//var vForm = $("#sub_repairtargets");
	//vForm.find('#TRGET_SLCTN_NO').val("");


	var postData ={"TRGET_SLCTN_NO":  trget_slctn_no, "USE_AT":"Y"};
	$.ajax({
		url: action,
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
		success: function(data){
			if(data!=null && data.TRGET_SLCTN_NO!=""){
				deleteRepairTarget(trget_slctn_no);
			}
			//alert(JSON.stringify(data));


		},
		error: function(a,b,msg){
			alert(JSON.stringify(data));
		}
	});
}
function deleteRepairTarget(trget_slctn_no){
	var action = '<c:url value="/api/rpairtrgetslctn/deleteRpairTrgetSlctn.do"/>';
	//var vForm = $("#sub_repairtargets");
	//vForm.find('#TRGET_SLCTN_NO').val("");


	var postData ={"TRGET_SLCTN_NO":  trget_slctn_no};
	$.ajax({
		url: action,
        contentType: 'application/json',
        data: JSON.stringify(postData),
        dataType: "json",
        cache: false,
        type: 'POST',
        processData: false,
		success: function(data){
			if(data!=null ){
				if( data.resultSuccess=="true"){
				 	alert("정상적으로 삭제되었습니다.");
				}
				else{
					alert(data.resultMSG);
				}
			}
			else{
				alert("삭제 도중 오류가 발생하였습니다.");
			}
			fnRefreshRepairTarget();

		},
		error: function(a,b,msg){
			alert(JSON.stringify(data));
		}
	});
}
function getTextLength(str) {
    var len = 0;
    for (var i = 0; i < str.length; i++) {
        if (escape(str.charAt(i)).length == 6) {
            len++;
        }
        len++;
    }
    return len;
}
function getSubText(str, limit) {
    var len = 0;
    var subTxt = "";
    for (var i = 0; i < str.length; i++) {
        if (escape(str.charAt(i)).length == 6) {
            len++;
        }
        len++;
        if(len>=limit){break;}
        subTxt+=str.charAt(i);
    }
    return subTxt;
}


function fnCntrwkStatsSearch(){
	var rw = $(window).width()/3;
	var deptCd = $("#SCH_DEPT_CODE").val();
	var strDt = $("#SCH_STRWRK_DE").val();
	var endDt = $("#SCH_COMPET_DE").val();

	//날짜 '-'삭제
	strDt = strDt.replace(/[^0-9]/g,'');
	endDt = endDt.replace(/[^0-9]/g,'');

	//노선별 통계가 선택되었을 시
	if($('.btab_menu li.sel a').hasClass("tab1") == true){
		parent.content_stArea.fnRoutSearch(deptCd,strDt,endDt,rw);
	}
	//관리기관별 통계가 선택되었을 시
	else if($('.btab_menu li.sel a').hasClass("tab2") == true){
		parent.content_stArea.fnDeptSearch(deptCd,strDt,endDt,rw);
	}
	//포장공법별 통계가 선택되었을 시
	else if($('.btab_menu li.sel a').hasClass("tab3") == true){
		parent.content_stArea.fnMthdSearch(deptCd,strDt,endDt,rw);
	}
}

/**
 * 보수대상 환경설정 창 생성
 * skc@muhanit.kr
 */
function fnOpenRpairConfig(){
	COMMON_UTIL.cmWindowOpen("보수대상 환경설정", contextPath + "rpairmthduntpc/selectRpairMthdUntpcList.do", "900", "750", true, null);
}

</script>
<%-- --%>