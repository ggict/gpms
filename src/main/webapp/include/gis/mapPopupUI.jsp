<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- 로딩바 시작 -->
<div id="lodingbar" style="display:none;">
	<div id="divSrvyDelete" class="content loadingbx" style="display:none;">
		<div id="t_srvydelete" class="txtbx">
		</div>
		 <div class="tc mt20" id="divDeleteBar" style="display:none;"><img src='<c:url value="/images/loadingBar.gif" />' alt="로딩바" /></div>
		 <div class="tc mt20" id="divButtn">
            <a href="#" class="schbtn" onclick="SRVY.deleteEvlList();" style="width:50px">삭제</a>
            <a href="#" class="graybtn" onclick="$('#lodingbar').hide();" style="width:50px">취소</a>
        </div>
	</div>
    <div id="divSrvyProc1" class="content loadingbx">
        <table class="tbview">
            <caption class="hidden">정보</caption>
            <colgroup>
                <col width="30%" />
                <col width="70%" />
            </colgroup>
            <tbody>
                <tr>
                    <th scope="row">
                        포장상태 평가 진행단계
                    </th>
                    <td>
                        포장상태 평가(GPCI) <img src='<c:url value="/images/ic_arrow.png" />' alt=">" /> GPS 공간보정 <img src='<c:url value="/images/ic_arrow.png" />' alt=">" /> 200m 집계(Section 셀)
                    </td>
                </tr>
                <tr>
                    <th scope="row">
                        평가 소요시간
                    </th>
                    <td>
                        5~6분/10km
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="txtbx">
            총 <span id="s_srvyProcCnt"></span>건의 평가대상을 선택하였습니다.<br />
            평가를 진행하시겠습니까?
        </div>
        <div class="tc mt20">
            <a href="#" class="schbtn" onclick="SRVY.evlSrvyDta();" style="width:50px">평가</a>
            <a href="#" class="graybtn" onclick="$('#lodingbar').hide();" style="width:50px">취소</a>
        </div>
    </div>
    <div id="divSrvyProc2" class="content loadingbx" style="display:none;">
        <div id="t_srvyProgress" class="txtbx">
                   	포장상태 평가 진행중입니다.
        </div>
        <div class="txtbx">
        	조사자료 건당 평가 소요시간 : 5~6분/10km
        </div>
        <div class="tc mt20">
            <ul class="stateul">
                <li>
                    <span id="evl_state" class="state_b" style="width:120px;height:60px;">
                        <span>상태평가</span>
                    	<span id="evl_success" style="display:none;"><img src='<c:url value="/images/ic_s.png" />' alt="" class="vm"/> 성공</span>
                    	<span id="evl_fail" style="display:none;"><img src='<c:url value="/images/ic_f.png" />' alt="" class="vm"/> 실패</span>
                    </span>

                </li>
                <li>
                    <span  id="gps_sm_state" class="state_b" style="width:120px;height:60px;">
                        <span>공간보정 및 Section집계</span>
                        <span id="gps_sm_success" style="display:none;"><img src='<c:url value="/images/ic_s.png" />' alt="" class="vm"/> 성공</span>
                    	<span id="gps_sm_fail" style="display:none;"><img src='<c:url value="/images/ic_f.png" />' alt="" class="vm"/> 실패</span>
                    </span>

                </li>
            </ul>
        </div>
        <div class="tc mt20"><img src='<c:url value="/images/loadingBar.gif" />' alt="로딩바" /></div>
    </div>
    <div id="divSrvyProc3" class="content loadingbx" style="display:none;">
        <div id="t_srvyResult" class="txtbx">
        </div>
        <a href="#" class="graybtn" onclick="$('#lodingbar').hide();" style="width:50px">확인</a>
    </div>
    <div id="divPredct" class="content loadingbx" style="display:none;">
    	<div class="txtbx">
    		평가 소요시간 5~6분/1건<br/>
    		총 <span id="s_predctCnt"></span>건의 예측대상을 선택하였습니다.<br /><br />
			<div id="t_predct">예측자료를 생성하시겠습니까?</div>
		</div>
		 <div class="tc mt20" id="divPredctBar" style="display:none;"><img src='<c:url value="/images/loadingBar.gif" />' alt="로딩바" /></div>
		 <div class="tc mt20" id="divPredctButtn">
            <a href="#" class="schbtn" onclick="SRVY.createPredct();" style="width:50px">생성</a>
            <a href="#" class="graybtn" onclick="$('#lodingbar').hide();" style="width:50px">취소</a>
        </div>
	</div>

</div>
<div id="divRTDialog" class="content " style="display:none;z-index:9999;">
<div class="txtbx" id="txtRTContents" >컨텐츠</div>
<div class="tc mt20" id="divRTBar" style="display:none;"><img src='<c:url value="/images/loadingBar.gif" />' alt="로딩바" /></div>
<div class="tc mt20" id="divRTButtn">버튼</div>
</div>

<!-- 프로그래스 바 -->
<div id="dvProgress" title="진행 중 ..." style="display:none;">
	<div style="font-size:12px; padding-bottom:7px; color: black; margin: 2px;">
		<br/><span id="t_progress"></span>
	</div>
	<div id="d_procImg" style="text-align: center;"><br/><img src='<c:url value="/images/loading/progress.gif" />' /></div>
</div>


<!-- 지도로딩바 -->
<div id="dvMapLoading" style="z-index: 99; position: absolute; width: 62px; height: 21px;display: none;">
   <img src="<c:url value='/images/loading/loading.gif'/>" />
</div>

<!-- 창 복원 -->
<div id="dvRestore" style="display: none;" title="창 복원">
	<a style="width: 80%" href="#" onclick="COMMON_UTIL.fnRestore();" class="schbtn">상세보기 창 복원</a>
</div>

<!-- i검색 -->
<div class="divAttr" style="background-color:white;  display:none; clear:both; position:static;">
  	<div id="attContent">
  		<div id="divAttMnu"></div>
    	<div id="divAttContent"></div>
    </div>
</div>

<!-- 포장CELL 선택기능 팝업 -->
<div id="dvCellSelctionTool" style="display: none;" title="포장셀 선택">
	<div class="content">
		<ul class="toolbtn dv_selCells">
			<li>
	            <a href="#" id="btn_cellSelectWithClick"><img src="<c:url value='/images/ic_t1.png'/>" alt="단일선택" /></a>
	            <span>단일선택</span>
	        </li>
	        <li>
	        	<a href="#" id="btn_openMultiSelectDv"><img src="<c:url value='/images/ic_t2.png'/>" alt="다중선택" /></a>
	            <span>다중선택</span>
	        </li>
	        <li>
	            <a href="#" id="btn_cellSelectReset"><img src="<c:url value='/images/ic_t3.png'/>" alt="선택해제" /></a>
	            <span>선택해제</span>
	        </li>
	        <li>
	            <a href="#" id="btn_cellSelectFinish"><img src="<c:url value='/images/ic_t4.png'/>" alt="선택완료" /></a>
	            <span>선택완료</span>
	        </li>
        </ul>
        <ul class="toolbtn dv_selCells mt10" id="dv_multiSelectPoly" style="display:none;">
        	<li>
	        	<a href="#" id="btn_cellSelectWithPolygon"><img src="<c:url value='/images/ic_t2.png'/>" alt="다중선택" /></a>
	            <span>다각형선택</span>
	        </li>
	        <li>
	        	<a href="#" id="btn_cellSelectWithPolyline"><img src="<c:url value='/images/ic_t2.png'/>" alt="다중선택" /></a>
	            <span>선형선택</span>
	        </li>
	        <%-- <li>
	        	<a href="#" id="btn_cellSelectWithInput"><img src="<c:url value='/images/ic_t2.png'/>" alt="다중선택" /></a>
	            <span>노선검색</span>
	        </li> --%>
        </ul>
        <div id="dv_inputForSelectCells" class="mt10" style="display:none;">
        	<ul style="width:100%;">
        		<li><span>노선</span><select id="sel_cs_route"></select></li>
        		<li><span>방향</span><select id="sel_cs_direct"></select></li>
        		<li><span>차로</span><select id="sel_cs_track"></select></li>
        		<li><span>시종점</span>
        			<input type="text" id="ip_cs_strtpt" value="0" placeholder="0"/>m
        			&nbsp;~&nbsp;
        			<input type="text" id="ip_cs_endpt" value="0" placeholder="0"/>m
        		</li>
        	</ul>
        	<div style="width:100%; text-align:right; margin-top:5px;">
        		<a style="margin-right:5px;" href="javascript:void(0);" id="btn_addCellsFromInput" class="schbtn">추가</a>
        	</div>
        </div>
		<div id="dv_cellSelectionHelp" class="mt10 ml10 mr10" style="display:none;">
			<span style="color:#f00;">* <b>Ctrl</b>키 + 도형그리기로 <b>선택제외</b>할 수 있습니다.</span>
			<!--
			<span style="display: none; color: red;">* <b>클릭</b>으로 도형 <b>추가</b><br />* <b>Ctrl + 클릭</b>으로 선택 <b>제외</b></span>
			<span style="display: none; color: red;">* <b>다각형 그리기</b>로 선택 영역의 Cell <b>추가</b><br /> * <b>Ctrl + 다각형 그리기</b>로 선택 영역 <b>제외</b></span>
			<span style="display: none; color: red;">* <b>시점을 다각형으로 선택 후 이정 입력<br />* 상하행을 구분하여 선택</b></span>
			-->
		</div>
	</div>
</div>

<!-- 노선선택팝업 -->
<div id="dvChoiceNSRes" title="노선 선택" style="display:none;">
	<div class="content" id="res_ChoidNSList">

	</div>
</div>

<!-- 섹션선택팝업 -->
<div id="dvChoiceSctnRes" title="섹션 선택" style="display:none;">
    <div class="content" id="res_ChoiceSctnList">

    </div>
</div>

<!-- 마커 -->
<div id="dvMarker" title="중심마커" style="display: none; position: relative; width: 50px; z-index: 9999;" >
    <img src='<c:url value="/images/icon_loc.png" />' />
</div>

<!-- 확인버튼 -->
<div id="dvComplete" title="위치보정" style="display: none;" >
    <div class="content" style="text-align: center;">
        <a class="selbtn btncursor">
	        <span class="roundbx" onmouseover="$(this).css('background', '#4587ff');" onmouseout="$(this).css('background', '#aab1bd');"><img src="<c:url value='/images/ic_shape5.png' />" alt="다각형" /></span>
	        <span>보정완료</span>
	        <input id="pWindow" type="hidden" value=""/>
        </a>
    </div>
</div>

<script type="text/javascript">

$(document).ready(function() {

    // 확인버튼 다이얼로그 액션
    $("#dvComplete > div > a").click(function() {

        // UI
        parent.$("#dvMarker").hide();
        $("#dvComplete").dialog("close");
        $("#dvComplete").removeClass("on");
        //wWindowShowAll();

        var wndId = $("#pWindow").val();
        var wnd = $.window.getWindow(wndId).getContainer().find("iframe")[0].contentWindow;
        wnd.fnSaveLoc();

    });

})

</script>
