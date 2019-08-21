<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>

</head>

<body class="cu">
<!-- 필수 파라메터(START) -->
<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input type="hidden" id="opener_id" name="opener_id" value=""/>
<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<input type="hidden" id="ENTRST_DE_SET" name="" value="${result.ENTRST_DE}"/>

<div id="Pop_wrap">
    <br/>
    <!-- Content -->
    <div class="ConBx">
        <div class="Pop_Section">
        <form id="frm" name="frm" method="post" action="">
            <div class="PopTitBx apply">
                <div class="content" style="padding: 0px 10px;">
                    <div class="titbx">
                        <h4>기본정보</h4>
                        <table class="tbview" summary="정보를 제공합니다.">
                            <caption>정보</caption>
                            <colgroup>
                                <col width="20%" />
                                <col width="30%" />
                                <col width="20%" />
                                <col width="30%" />
                            </colgroup>
                            <tbody>
                            	<tr>
					        		<th scope="row"><span class="fcred">* </span>모니터링단원</th>
							        <td colspan="3">
							        	<label style="margin-left:5px; margin-right:10px;"><input type="radio" id="MTDT001" name="MTDT" style="margin-right:3px;" <c:if test= "${ sort == 'update' }" > disabled </c:if> <c:if test= "${ result.VHCLE_NO ne 'MTDT002' }" > checked </c:if> />스마트카드 택시</label>
							        	<label><input type="radio" id="MTDT002" name="MTDT" style="margin-right:3px;" <c:if test= "${ sort == 'update' }" > disabled </c:if> <c:if test= "${ result.VHCLE_NO eq 'MTDT002' }" > checked </c:if> />국토부앱 도민</label>
							        </td>
						        </tr>
                                <tr>
                                    <th scope="row"><span class="fcred">* </span>사업자명</th>
                                    <td colspan="3">
                                        <input type="text" id="BSNM_NM" name="BSNM_NM" alt="사업자명" value="${result.BSNM_NM}" class="input notnull"  />
                                    </td>
                                </tr>
                                <tr id="VHCLE_NO_HIDDEN">
                                    <th scope="row"><span class="fcred">* </span>차량번호</th>
                                    <td colspan="3">
                                        <input type="text" id="VHCLE_NO" name="VHCLE_NO"
                                            <c:if test= "${ sort == 'update' }" >  readonly="readonly" </c:if>
                                        alt="차량번호" value="${result.VHCLE_NO}" class="input notnull"  />
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">지역</th>
                                    <td colspan="3">
                                    <input type="hidden" value="${result.DEPT_CODE}"/>
                                        <select id="DEPT_CODE" name="DEPT_CODE" class="select sBx120" style="width: 150px; margin-left: 1px;">
                                            <option value="">===== 전체 =====</option>
                                            <c:forEach var="selectData" items="${ RegionList }">
                                               <option value="${selectData.DEPT_CODE}"  <c:if test="${selectData.DEPT_CODE == result.DEPT_CODE }">selected="selected"</c:if> >${selectData.LOWEST_DEPT_NM}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">주소</th>
                                    <td colspan="3">
                                        <input type="text" id="ADRES" name="ADRES" alt="주소" value="${result.ADRES}" class="input"  />
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row"><span class="fcred">* </span>연락처</th>
                                    <td colspan="3">
                                        <input type="tel" id="CTTPC" name="CTTPC" id="divTel" alt="연락처" value="${result.CTTPC}" class="input notnull"  />
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">미터기제작사</th>
                                    <td colspan="3">
                                         <input type="text" id="METER_MAKR" name="METER_MAKR" alt="미터기제작사" value="${result.METER_MAKR}" class="input"  />
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">모델명</th>
                                    <td colspan="3">
                                         <input type="text" id="MODEL_NM" name="MODEL_NM" alt="모델명" value="${result.MODEL_NM}" class="input"  />
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">카드제작사</th>
                                    <td colspan="3">
                                         <input type="text" id="CARD_MAKR" name="CARD_MAKR" alt="카드제작사" value="${result.CARD_MAKR}" class="input"  />
                                    </td>
                                </tr>

                                <tr>
                                    <th scope="row">장착여부</th>
                                    <td>
                                    <input type="hidden" value="${result.MNTNG_AT}" />
                                    <select id="MNTNG_AT" name="MNTNG_AT" class="select sBx120" style="width: 120px; margin-left: 1px;">
                                        <option value="Y" <c:if test="${result.MNTNG_AT == 'Y' }">selected="selected"</c:if>>예</option>
                                        <option value="N" <c:if test="${result.MNTNG_AT == 'N' }">selected="selected"</c:if>>아니오</option>
                                    </select>
                                    <th scope="row"><span class="fcred">* </span>위촉여부</th>
                                    <td>
                                    <input type="hidden" value="${result.ENTRST_AT}" />
                                    <select id="ENTRST_AT" name="ENTRST_AT" class="select sBx120" style="width: 120px; margin-left: 1px;">
                                        <option value="Y" <c:if test="${result.ENTRST_AT == 'Y' }">selected="selected"</c:if>>예</option>
                                        <option value="N" <c:if test="${result.ENTRST_AT == 'N' }">selected="selected"</c:if>>아니오</option>
                                    </select>
                                </tr>
                                <tr>
                                    <th scope="row">비고</th>
                                    <td colspan="3">
                                        <textarea id="RM" name="RM" maxLength="1000" style="resize:none; width:95.5%;">${result.RM}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th scope="row">위촉일자</th>
                                    <td colspan="3">
                                        <span class="calendar" style="width: 96%" >
                                            <input type="text" id="ENTRST_DE" name="ENTRST_DE" alt="위촉일자" value="<fmt:formatDate value="${result.ENTRST_DE}" pattern="yyyy-MM-dd"/>" class="input" readonly="readonly" />
                                        </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </form>
            <div class="btnbx" style="margin-right:15px;">
                <a href="#" onclick="$('#frm').reset(); return false;" class="schbtn edit">초기화</a>
            <c:if test= "${ sort == 'insert' }" >
                <a href="#" onclick="check = false; fn_confm(); return false;" class="schbtn edit">등록</a>
            </c:if>
            <c:if test= "${ sort == 'update' }" >
                <a href="#" onclick="check = false; fn_update(); return false;" class="schbtn edit">수정</a>
                <a href="#" onclick="check = false; fn_delete(); return false;" class="schbtn edit">삭제</a>
            </c:if>
                <a href="#" onclick="fn_close_dialog();" class="schbtn">닫기</a>
            </div>
        </div>
    </div>
</div>
    <!-- // Content -->
<!-- // wrap -->

<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
$(document).ready(function() {

	if($('#VHCLE_NO').val() == 'MTDT002' ){
		$("#VHCLE_NO_HIDDEN").hide();
	}

    // 달력 생성 (전체)
    COMMON_UTIL.cmCreateDatepicker('ENTRST_DE', 46);

    //현재 날짜
    var date = new Date();

    // 위촉일시 초기값 설정
    if ( $('#ENTRST_AT').val() == 'Y' && !$('#ENTRST_DE_SET').val() ){
        $('#ENTRST_DE').val($.datepicker.formatDate('yy-mm-dd', new Date()));
    }

	// 전화번호
	$('#CTTPC').keyup(function(e) {

	    var s = $(this).val();
	    s = COMMON_UTIL.phoneNumFomatter(s);
	    $(this).val(s);
	});
});


$("input:radio[name=MTDT]").click(function()
{
	if($("input:radio[id=MTDT002]:checked").val()){
		$("#VHCLE_NO").val('MTDT002');
		$("#VHCLE_NO_HIDDEN").hide();
	} else if ($("input:radio[id=MTDT001]:checked").val()){
		$("#VHCLE_NO").val('');
		$("#VHCLE_NO_HIDDEN").show();
	}
})

// 위촉일시 변화값 설정
$('#ENTRST_AT').change(function(e){
    if ( this.value == 'Y' && !$('#ENTRST_DE_SET').val()){
        $('#ENTRST_DE').val($.datepicker.formatDate('yy-mm-dd', new Date()));
    }
    else if ( this.value == 'N' && !$('#ENTRST_DE_SET').val() ){
        $('#ENTRST_DE').val('');
    }
});


//팝업창 닫기
function fn_close_dialog(){

    if ( confirm("입력한 내용이 저장되지 않습니다.\n정말 종료하시겠습니까?") ) {

        var wnd_id = $("#wnd_id").val();
        COMMON_UTIL.cmWindowClose(wnd_id);
    }
}


// 모니터링단원 등록
/* 2018.11.13 insert 중 사용자 정보를 조회하여 사용자가 있을 경우 처리
    1. 삭제된 사용자일 경우 delete_at = 'Y'
    2. 삭제된 사용자도 아니고 그냥 중복입력된 경우 */
function fn_confm(){
    //window.parent.fn_apply_confirm();
    if(COMMON_UTIL.fn_check_notnull("tr")) {
        return;
    }

    //전화번호 체크
    if($("#CTTPC").val() != "" && !COMMON_UTIL.fn_check_format("tel", "CTTPC")){ return; }

    var msg = "해당 모니터링 단원을 등록하시겠습니까?";
    var dataList =  { BSNM_NM : $("#BSNM_NM").val()
                      , VHCLE_NO : $("#VHCLE_NO").val()
                      , DEPT_CODE : $("#DEPT_CODE").val()
                      , ADRES : $("#ADRES").val()
                      , CTTPC : $("#CTTPC").val()
                      , METER_MAKR : $("#METER_MAKR").val()
                      , MODEL_NM : $("#MODEL_NM").val()
                      , CARD_MAKR : $("#CARD_MAKR").val()
                      , MNTNG_AT : $("#MNTNG_AT").val()
                      , ENTRST_AT : $("#ENTRST_AT").val()
                      , RM : $("#RM").val()
                      , ENTRST_DE : $("#ENTRST_DE").val()
                     }
    if(confirm(msg)){
        $.ajax({
            url: contextPath + 'monitor/insertMntrngMbr.do'
            ,data: JSON.stringify(dataList)
            ,type: 'post'
            ,dataType: 'json'
            ,contentType: "application/json;charset=UTF-8"
            ,success: function(data){

            	if(data.chk == "Y"){
            		alert( "이미 등록된 사용자입니다.");

            	}else if(data.chk == "D"){
            		if(confirm( "삭제된 사용자입니다. 다시 등록하시겠습니까?")){

            		    var dataList =  {
            		    		BSNM_NM : $("#BSNM_NM").val()
                                , VHCLE_NO : $("#VHCLE_NO").val()
                                , DEPT_CODE : $("#DEPT_CODE").val()
                                , ADRES : $("#ADRES").val()
                                , CTTPC : $("#CTTPC").val()
                                , METER_MAKR : $("#METER_MAKR").val()
                                , MODEL_NM : $("#MODEL_NM").val()
                                , CARD_MAKR : $("#CARD_MAKR").val()
                                , MNTNG_AT : $("#MNTNG_AT").val()
                                , ENTRST_AT : $("#ENTRST_AT").val()
                                , RM : $("#RM").val()
                                , ENTRST_DE : $("#ENTRST_DE").val()
            		    	    , USE_AT : 'Y'
            		    	    , DELETE_AT : 'N'
            		    	    }

           		        $.ajax({
           		            url: contextPath + 'monitor/updateMntrngMbr.do'
           		            ,data: JSON.stringify(dataList)
           		            ,contentType: "application/json;charset=UTF-8"
           		            ,dataType: "json"
           		            ,cache: false
           		            ,type: 'POST'
           		            ,processData: false
           		            ,success: function(res){
           		                alert("재등록되었습니다.");
           		                window.parent.fn_search();
           		                var wnd_id = $("#wnd_id").val();
           		                COMMON_UTIL.cmWindowClose(wnd_id);
           		            }
           		            ,error: function(a,b,msg){
           		                alert("등록처리에 문제가 발생하였습니다.");

           		            }
           		        });
            		}

            	}else{
            		alert( "등록되었습니다.");
                    window.parent.fn_search();
                    var wnd_id = $("#wnd_id").val();
                    COMMON_UTIL.cmWindowClose(wnd_id);
            	}
            }
            ,error: function(a,b,msg){
                alert( "등록처리에 문제가 발생하였습니다.");
                //window.parent.fn_search();
            }
        });
    }
}


// 모니터링단원 삭제
function fn_delete(){
    var vhcle_no = $("#VHCLE_NO").val();
    var bsnm_nm = $("#BSNM_NM").val();
    var msg = "모니터링단원 삭제를 진행하시겠습니까?";

    if(confirm(msg)){
        $.ajax({
            url: contextPath + 'monitor/deleteMntrngMbr.do'
            ,data: JSON.stringify({"VHCLE_NO" : vhcle_no, "BSNM_NM" : bsnm_nm})
            ,type: 'post'
            ,dataType: 'json'
            ,contentType: "application/json;charset=UTF-8"
            ,success: function(res){
                alert("모니터링단원이 삭제되었습니다.");
                window.parent.fn_search();
                var wnd_id = $("#wnd_id").val();
                COMMON_UTIL.cmWindowClose(wnd_id);
            }
            ,error: function(a,b,msg){
                alert("삭제처리에 문제가 발생하였습니다.");
            }
        });
    }
}

// 모니터링단원 정보 수정
function fn_update(){
    //null check
    if(COMMON_UTIL.fn_check_notnull("tr")) {
        return;
    }

    //전화번호 체크
    if($("#CTTPC").val() != "" && !COMMON_UTIL.fn_check_format("tel", "CTTPC")){ return; }
    var dataList =  { BSNM_NM : $("#BSNM_NM").val()
                    , VHCLE_NO : $("#VHCLE_NO").val()
                    , DEPT_CODE : $("#DEPT_CODE").val()
                    , ADRES : $("#ADRES").val()
                    , CTTPC : $("#CTTPC").val()
                    , METER_MAKR : $("#METER_MAKR").val()
                    , MODEL_NM : $("#MODEL_NM").val()
                    , CARD_MAKR : $("#CARD_MAKR").val()
                    , MNTNG_AT : $("#MNTNG_AT").val()
                    , ENTRST_AT : $("#ENTRST_AT").val()
                    , RM : $("#RM").val()
                    , ENTRST_DE : $("#ENTRST_DE").val()
                   }

    if(confirm('<spring:message code="warn.update.msg" />')){
        $.ajax({
            url: contextPath + 'monitor/updateMntrngMbr.do'
            ,data: JSON.stringify(dataList)
            ,contentType: "application/json;charset=UTF-8"
            ,dataType: "json"
            ,cache: false
            ,type: 'POST'
            ,processData: false
            ,success: function(res){
                alert("모니터링단원 정보가 수정되었습니다.");
                window.parent.fn_search();
                var wnd_id = $("#wnd_id").val();
                COMMON_UTIL.cmWindowClose(wnd_id);
            }
            ,error: function(a,b,msg){
                alert("수정처리에 문제가 발생하였습니다.");

            }
        });
    }
}

</script>


<%@ include file="/include/common.jsp" %>
</body>
</html>