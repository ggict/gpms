<%-- <%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%
/* -------------------------------------------------------
 * 2019년 고도화 사업 - 포트홀_신고관리 접수경로 구분 (스마트카드, 국토부, 시군관리)
 * sttemntVO.pthmode - 접수경로별 구분
 * T(default) : 스마트카드
 * M : 국토부 신고앱
 * C : 시군관리
 * -------------------------------------------------------
 * sttemntVO.imode - 편집모드 구분 (수정, 등록)
 * EDIT(default) : 수정
 * ADD : 등록
 * -------------------------------------------------------
*/
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap" style="overflow-y: auto; min-width:600px;" class="cu sttemnt change-loc <c:if test="${ (sttemntVO.imode eq 'ADD')}">edit</c:if>">
    <!-- 필수 파라메터(START) -->
    <input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
    <input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
    <input type="hidden" id="opener_id" name="opener_id" value=""/>
    <input type="hidden" id="wnd_id" name="wnd_id" value=""/>
    <input type="hidden" id="result" name="result" value=""/>
    <!-- hidden -->
    <input type="hidden" id="RPRDTLS_MNG_NO" name="RPRDTLS_MNG_NO" value="${rpairVO.RPRDTLS_MNG_NO}" />

    <div style="margin-top: -5px;">
        <br />

        <!-- Content -->
        <div class="conBx">
            <div class="Pop_Section">
                <div id="goExcel" class="PopTitBx apply">

                    <form id="frm" name="frm" method="post" action="">
                    <!-- 필수 파라메터(END) -->
                    <input type="hidden" id="EDEPT_CODE" name="EDEPT_CODE" value="${sttemntVO.EDEPT_CODE}" />
                    <input type="hidden" id="CORTN_X" name="CORTN_X" value="${sttemntVO.CORTN_X}" />
                    <input type="hidden" id="CORTN_Y" name="CORTN_Y" value="${sttemntVO.CORTN_Y}" />
                    <input type="hidden" id="TM_X" name="TM_X" value="${sttemntVO.TM_X}" />
                    <input type="hidden" id="TM_Y" name="TM_Y" value="${sttemntVO.TM_Y}" />
                    <input type="hidden" id="HEADG" name="HEADG" value="${sttemntVO.HEADG}" />
                    <input type="hidden" id="LCDO" name="LCDO" value="${sttemntVO.LCDO}" />
                    <input type="hidden" id="DPLCT_STTEMNT_AT" name="DPLCT_STTEMNT_AT" value="${sttemntVO.DPLCT_STTEMNT_AT}" />
                    <input type="hidden" id="DSM_RP_NO" name="DSM_RP_NO" value="${sttemntVO.DSM_RP_NO}" />
                    <input type="hidden" id="CPT_MNG_NO" name="CPT_MNG_NO" value="${sttemntVO.CPT_MNG_NO}" />
                    <input type="hidden" id="pthmode" name="pthmode" value="${sttemntVO.pthmode}" />
                    <input type="hidden" id="imode" name="imode" value="${sttemntVO.imode}" />
                    <input type="hidden" id="GG_ID" name="GG_ID" value="${sttemntVO.GG_ID}" />

                    <div class="content" style="margin: 0px 10px 0px 10px; padding: 0px 10px">
                        <div class="titbx">
                            <h4 class='excel' style="padding: 2px 10px;">처리정보</h4>
                            <table id="goExcelTb" class="tbview" summary="처리상태를 제공합니다.">
                                <tbody>
                                    <tr>
                                        <th style="width: 15%; border: solid 0px;">처리상태 <span style="color: red;">*</span></th>
                                        <td id='sel_prcs' style="width: 85%; border: solid 0px; font-size: 11px;">
                                            <%-- <select id="PRCS_STTUS" name="PRCS_STTUS" style="width: 40%;">
                                                <c:forEach items="${prcsList}" var="prcs">
                                                    <option value="${prcs.CODE_VAL}"<c:if test="${sttemntVO.PRCS_STTUS == prcs.CODE_VAL }">selected="selected"</c:if>>${prcs.CODE_NM}</option>
                                                </c:forEach>
                                            </select> --%>
                                            <c:forEach items="${prcsList}" var="prcs">
                                                <input id="PRCS_STTUS" name="PRCS_STTUS" type="radio" value="${prcs.CODE_VAL}" style="margin: 0px 5px;" <c:if test="${sttemntVO.PRCS_STTUS == prcs.CODE_VAL }">checked="checked"</c:if> />${prcs.CODE_NM}
                                            </c:forEach>

                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="content" style="margin: 5px 10px 0px 10px; padding: 0px 10px">
                        <div class="titbx">
                            <h4 class='excel' style="padding: 0px 10px;">신고정보 ( ${sttemntVO.RCPT_ROUTE} )</h4>
                            <table class="tbview" summary="신고정보를 제공합니다.">
                                <colgroup>
                                    <col width="15%" />
                                    <col width="35%" />
                                    <col width="15%" />
                                    <col width="35%" />
                                </colgroup>

                                <tbody>
                                    <tr class='excel'>
                                        <th>등록번호</th>
                                        <td>
                                        <c:if test="${sttemntVO.imode ne 'ADD'}">
                                        <input type="text" id="PTH_RG_NO" name="PTH_RG_NO" value="${sttemntVO.PTH_RG_NO}" readonly style="width: 96%;" />
                                        </c:if>
                                        <c:if test="${sttemntVO.imode eq 'ADD'}">&nbsp;-</c:if>
                                        </td>
                                        <th>관할기관</th>
                                        <td>
                                            <select id="DEPT_CODE" name="DEPT_CODE" class='sel_dept_code' style="width: 100%; display:none;">
                                                <option value="">=== 전체 ===</option>
                                                <option value="9999999" <c:if test="${sttemntVO.DEPT_CODE == '9999999' }">selected="selected"</c:if>>관할기관 없음</option>
                                                <c:forEach items="${deptList}" var="dept">
                                                    <option value="${dept.DEPT_CODE}" <c:if test="${sttemntVO.DEPT_CODE == dept.DEPT_CODE }">selected="selected"</c:if>>${dept.LOWEST_DEPT_NM}</option>
                                                </c:forEach>
                                            </select>
                                            <c:if test="${ fn:indexOf(sttemntVO.DEPT_CODE, '70003') > -1 && sttemntVO.DEPT_CODE != '7000300' }">
                                                <input type="text" class='input_dept_code' value="민자도로사업자 (${sttemntVO.LOWEST_DEPT_NM})" readonly style="width: 96%;" />
                                            </c:if>
                                            <c:if test="${ fn:indexOf(sttemntVO.DEPT_CODE, '70003') == -1 }">
                                                <input type="text" class='input_dept_code' value="${sttemntVO.LOWEST_DEPT_NM}" readonly style="width: 96%;" />
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <% /*  신고자 - 시군관리 등록일 경우에만 입력가능    */ %>
                                        <th>신고자</th>
                                        <td><input type="text" id="BSNM_NM" name="BSNM_NM" value="${sttemntVO.BSNM_NM}" <c:if test="${sttemntVO.imode ne 'ADD'}">readonly</c:if> style="width: 96%;" /></td>
                                        <% /*  접수경로 - 시군관리, 국토부 신고앱, 스마트카드(default)    */ %>
                                        <th>접수경로</th>
                                        <td>${sttemntVO.RCPT_ROUTE}</td>
                                    </tr>

                                    <tr>
                                        <th>신고일시</th>
                                        <td>
                                            <fmt:parseDate value="${sttemntVO.STTEMNT_DT}" var="STTEMNT_DT" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            <input type="text" id="STTEMNT_DT" name="STTEMNT_DT" value="<fmt:formatDate value="${STTEMNT_DT}" pattern="yyyy-MM-dd HH:mm:ss"/>" <c:if test="${sttemntVO.imode ne 'ADD'}">readonly</c:if> style="width: 40%; margin-right: 3px;" />
                                        </td>
                                        <% /*  차량번호 - 시군관리, 국토부 신고앱 제외    */ %>
                                        <c:if test="${ (sttemntVO.pthmode ne 'C') && (sttemntVO.pthmode ne 'M')}">
                                        <th>차량번호</th>
                                        <td><input type="text" id="VHCLE_NO" name="VHCLE_NO" value="${sttemntVO.VHCLE_NO}" readonly style="width: 96%;" /></td>
                                        </c:if>
                                    </tr>

                                    <tr>
                                        <th>도로명</th>
                                        <td colspan="3" style="width: 400px;">
                                            <input type="text" id="RN_ADRES" name="RN_ADRES" value="${sttemntVO.RN_ADRES}" style="width: 98%;" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>지번주소</th>
                                        <td colspan="3" style="width: 400px;">
                                            <input type="text" id="LNM_ADRES" name="LNM_ADRES" value="${sttemntVO.LNM_ADRES}" style="width: 98%;" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    </form>

                    <form id="frm2" name="frm2" method="post" action="">
                    <input type="hidden" id="BFEflag" name="BFEflag" value="${rpairVO.BFEflag}" />
                    <input type="hidden" id="AFTflag" name="AFTflag" value="${rpairVO.AFTflag}" />

                    <div class="content" style="margin: 5px 10px 0px 10px; padding: 0px 10px">
                        <div class="titbx">
                            <h4 class='excel' style="padding: 0px 10px;">
                                보수정보 <c:if test="${rpairVO.RPRDTLS_MNG_NO == '' || rpairVO.RPRDTLS_MNG_NO == undefined || rpairVO.RPRDTLS_MNG_NO == null}"><span style="color: rgba(253, 237, 93, 1); font-size: 12px; margin-left: 5px;">등록된 보수정보가 없습니다.</span></c:if>
                                <c:if test="${rpairVO.RPRDTLS_MNG_NO == '' || rpairVO.RPRDTLS_MNG_NO == undefined || rpairVO.RPRDTLS_MNG_NO == null}"><span style="background: #aaaaaa; padding: 0px 6px; border: 1px solid rgba(255, 255, 255, 0.2); border-image: none; line-height: 20px; font-size: 11px; margin-top: 4px; margin-right: 3px; float: right; box-shadow: 0px 0px 3px rgba(255,255,255,0.6);" class="schbtn edit">보수정보삭제</span></c:if>
                                <c:if test="${rpairVO.RPRDTLS_MNG_NO != '' && rpairVO.RPRDTLS_MNG_NO != undefined && rpairVO.RPRDTLS_MNG_NO != null}"><a href="#" onclick="fnDelete();" style="padding: 0px 6px; border: 1px solid rgba(255, 255, 255, 0.2); border-image: none; line-height: 20px; font-size: 11px; margin-top: 4px; margin-right: 3px; float: right; box-shadow: 0px 0px 3px rgba(255,255,255,0.6);" class="schbtn edit">보수정보삭제</a></c:if>

                            </h4>
                            <table class="tbview" style="margin-bottom: -5px;" summary="보수정보를 제공합니다.">
                                <colgroup>
                                    <col width="15%" />
                                    <col width="35%" />
                                    <col width="15%" />
                                    <col width="35%" />
                                </colgroup>

                                <tbody>
                                    <tr>
                                        <th>파손유형</th>
                                        <td colspan="3">
                                            <select id="DMG_TYPE" name="DMG_TYPE" style="width: 40%;" >
                                                <option value="">=== 전체 ===</option>
                                                <c:forEach items="${dmgtList}" var="dmgt">
                                                    <option value="${dmgt.CODE_VAL}" <c:if test="${sttemntVO.DMG_TYPE == dmgt.CODE_VAL }">selected="selected"</c:if>>${dmgt.CODE_NM}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>담당자</th>
                                        <td><input type="text" id="CHARGER_NM" name="CHARGER_NM" value="${sttemntVO.CHARGER_NM}" style="width: 96%;" /></td>
                                        <th>연락처</th>
                                        <td colspan="1"><input type="text" id="CTTPC" name="CTTPC" value="${sttemntVO.CTTPC}" readonly style="width: 96%;" /></td>
                                    </tr>

                                    <%-- <tr>
                                        <th>도로명</th>
                                        <td colspan="4"><input type="text" id="ROUTE_NM" name="ROUTE_NM" value="${cmptncVO.ROUTE_NM}" style="width: 98%;" /></td>
                                    </tr> --%>

                                    <tr>
                                        <th>시공사</th>
                                        <td><!-- <input type="radio" id="CO_NO" name="CO_NO" value="23" checked="checked" /> -->현장팀</td>
                                        <th>보수금액(원)</th>
                                        <td colspan="1"><input type="text" id="RPAIR_AMOUNT" name="RPAIR_AMOUNT" value="<fmt:formatNumber value="${rpairVO.RPAIR_AMOUNT}" pattern="#,###.##" />" class='DT_INT' style="width: 96%;" /></td>
                                    </tr>

                                    <tr>
                                        <th>보수규모</th>
                                        <td colspan="3">
                                            가로(cm) <input type="text" id="RPRSCL_WIDTH" name="RPRSCL_WIDTH" value="<fmt:formatNumber value="${rpairVO.RPRSCL_WIDTH}" pattern="#,###.##" />" class='DT_FLOAT' style="width: 10.5%; margin: 0 2.5px;" />
                                            세로(cm) <input type="text" id="RPRSCL_VRTICL" name="RPRSCL_VRTICL" value="<fmt:formatNumber value="${rpairVO.RPRSCL_VRTICL}" pattern="#,###.##" />" class='DT_FLOAT' style="width: 11%; margin: 0 2.5px;" />
                                            면적(㎠) <input type="text" id="RPRSCL_AR" name="RPRSCL_AR" value="<fmt:formatNumber value="${rpairVO.RPRSCL_AR}" pattern="#,###.##" />" class='DT_FLOAT' style="width: 10.5%; margin: 0 2.5px;" />
                                            깊이(cm) <input type="text" id="RPRSCL_DP" name="RPRSCL_DP" value="<fmt:formatNumber value="${rpairVO.RPRSCL_DP}" pattern="#,###.##" />" class='DT_FLOAT' style="width: 11%; margin: 0 2.5px;" />
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>비고</th>
                                        <td colspan="4"><input type="text" id="RM" name="RM" value="${rpairVO.RM}" style="width: 98%;" /></td>
                                    </tr>

                                    <tr style="vertical-align: bottom;">
                                        <th colspan="2" style="border-right: solid 0px; background-color: #ffffff;">보수 전</th>
                                        <th colspan="2" style="border-left: solid 0px; background-color: #ffffff;">보수 후</th>
                                    </tr>

                                    <tr>
                                        <th>확인일자</th>
                                        <td colspan="">
                                            <fmt:parseDate value="${rpairVO.CNFIRM_DT}" var="CNFIRM_DT" pattern="yyyy-MM-dd"/>
                                            <input type="text" id="CNFIRM_DT" name="CNFIRM_DT" value="<fmt:formatDate value="${CNFIRM_DT}" pattern="yyyy-MM-dd"/>" style="width: 84%; margin-right: 3px;" />
                                        </td>
                                        <th>보수일자</th>
                                        <td colspan="">
                                            <fmt:parseDate value="${rpairVO.RPAIR_DT}" var="RPAIR_DT" pattern="yyyy-MM-dd"/>
                                            <input type="text" id="RPAIR_DT" name="RPAIR_DT" value="<fmt:formatDate value="${RPAIR_DT}" pattern="yyyy-MM-dd"/>" style="width: 86%; margin-right: 3px;" />
                                        </td>
                                    </tr>
                                    <tr>

                                        <td colspan="2">
                                            <span class='show_bfe_img' >
                                                <span class="addFile1 showImg" style="">
	                                                <img id="b_img0" src="<c:if test = "${bfe_file_info0.FILE_NM != null }" >${file_path}${bfe_file_info0.FILE_COURS}/${bfe_file_info0.FILE_NM}</c:if>"
	                                                alt="" title="보수 전 사진" style="width: 98%; height: 150px; display:block; text-align: center;" />
	                                                <%-- <br/>등록된 이미지 : <c:out value="${bfe_file_info0.ORGINL_FILE_NM}"/> --%>
		                                            <input type="file" accept="image/*" id="RPAIR_BFE_PHOTO0" name="RPAIR_BFE_PHOTO0" title='img1' value="" class="MX_50 DT_FILE_IMAGE" style="width: 90%; margin:10px 0px;" />
		                                            <img src="<c:url value='/images/del_img.png' />" onclick="fnDelImg('b_img0');" style="cursor:pointer"/>
                                                </span>

                                                <span class="addFile2" style="">
	                                                <img id="b_img1" src="<c:if test = "${bfe_file_info1.FILE_NM != null }" >${file_path}${bfe_file_info1.FILE_COURS}/${bfe_file_info1.FILE_NM}</c:if>"
	                                                alt="" title="보수 전 사진" style="width: 98%; height: 150px; display:block; text-align: center;" />
	                                                <%-- <br/>등록된 이미지 : <c:out value="${bfe_file_info0.ORGINL_FILE_NM}"/> --%>
		                                            <input type="file" accept="image/*" id="RPAIR_BFE_PHOTO1" name="RPAIR_BFE_PHOTO1" title='img1' value="" class="MX_50 DT_FILE_IMAGE" style="width: 90%; margin:10px 0px;" />
		                                            <img src="<c:url value='/images/del_img.png' />" onclick="fnDelImg('b_img1');" style="cursor:pointer"/>
                                                </span>

												<span class="addFile3" style="">
	                                                <img id="b_img2" src="<c:if test = "${bfe_file_info2.FILE_NM != null }" >${file_path}${bfe_file_info2.FILE_COURS}/${bfe_file_info2.FILE_NM}</c:if>"
	                                                alt="" title="보수 전 사진" style="width: 98%; height: 150px; display:block; text-align: center;" />
	                                                <%-- <br/>등록된 이미지 : <c:out value="${bfe_file_info0.ORGINL_FILE_NM}"/> --%>
		                                            <input type="file" accept="image/*" id="RPAIR_BFE_PHOTO2" name="RPAIR_BFE_PHOTO2" title='img1' value="${rpairVO.RPAIR_BFE_PHOTO}" class="MX_50 DT_FILE_IMAGE" style="width: 90%; margin:10px 0px;" />
                                                    <img src="<c:url value='/images/del_img.png' />" onclick="fnDelImg('b_img2');" style="cursor:pointer"/>
                                                </span>

                                                <span class="addFile4" style="">
	                                                <img id="b_img3" src="<c:if test = "${bfe_file_info3.FILE_NM != null }" >${file_path}${bfe_file_info3.FILE_COURS}/${bfe_file_info3.FILE_NM}</c:if>"
	                                                alt="" title="보수 전 사진" style="width: 98%; height: 150px; display:block; text-align: center;" />
	                                                <%-- <br/>등록된 이미지 : <c:out value="${bfe_file_info0.ORGINL_FILE_NM}"/> --%>
		                                            <input type="file" accept="image/*" id="RPAIR_BFE_PHOTO3" name="RPAIR_BFE_PHOTO3" title='img1' value="${rpairVO.RPAIR_BFE_PHOTO}" class="MX_50 DT_FILE_IMAGE" style="width: 90%; margin:10px 0px;" />
		                                            <img src="<c:url value='/images/del_img.png' />" onclick="fnDelImg('b_img3');" style="cursor:pointer"/>
                                                </span>
                                            </span>
                                        </td>

                                        <td colspan="2">
                                            <span class='show_aft_img'>
                                                <span class="addFile1 showImg" style="">
	                                                <img id="a_img0" src="<c:if test = "${aft_file_info0.FILE_NM != null }" >${file_path}${aft_file_info0.FILE_COURS}/${aft_file_info0.FILE_NM}</c:if>"
	                                                alt="" title="보수 후 사진" style="width: 98%; height: 150px; display:block; text-align: center;" />
		                                            <input type="file" accept="image/*" id="RPAIR_AFT_PHOTO0" name="RPAIR_AFT_PHOTO0" title='img2' value="${rpairVO.RPAIR_AFT_PHOTO}" class="MX_50 DT_FILE_IMAGE" style="width: 90%; margin:10px 0px;" />
		                                            <img src="<c:url value='/images/del_img.png' />" onclick="fnDelImg('a_img0');" style="cursor:pointer"/>
                                                </span>

                                                <span class="addFile2" style="">
	                                                <img id="a_img1" src="<c:if test = "${aft_file_info1.FILE_NM != null }" >${file_path}${aft_file_info1.FILE_COURS}/${aft_file_info1.FILE_NM}</c:if>"
	                                                alt="" title="보수 후 사진" style="width: 98%; height: 150px; display:block; text-align: center;" />
		                                            <input type="file" accept="image/*" id="RPAIR_AFT_PHOTO1" name="RPAIR_AFT_PHOTO1" title='img2' value="${rpairVO.RPAIR_AFT_PHOTO}" class="MX_50 DT_FILE_IMAGE" style="width: 90%; margin:10px 0px;" />
		                                            <img src="<c:url value='/images/del_img.png' />" onclick="fnDelImg('a_img1');" style="cursor:pointer"/>
                                                </span>

                                                <span class="addFile3" style="">
	                                                <img id="a_img2" src="<c:if test = "${aft_file_info2.FILE_NM != null }" >${file_path}${aft_file_info2.FILE_COURS}/${aft_file_info2.FILE_NM}</c:if>"
	                                                alt="" title="보수 후 사진" style="width: 98%; height: 150px; display:block; text-align: center;" />
		                                            <input type="file" accept="image/*" id="RPAIR_AFT_PHOTO2" name="RPAIR_AFT_PHOTO2" title='img2' value="${rpairVO.RPAIR_AFT_PHOTO}" class="MX_50 DT_FILE_IMAGE" style="width: 90%; margin:10px 0px;" />
		                                            <img src="<c:url value='/images/del_img.png' />" onclick="fnDelImg('a_img2');" style="cursor:pointer"/>
                                                </span>

                                                <span class="addFile4" style="">
	                                                <img id="a_img3" src="<c:if test = "${aft_file_info3.FILE_NM != null }" >${file_path}${aft_file_info3.FILE_COURS}/${aft_file_info3.FILE_NM}</c:if>"
	                                                alt="" title="보수 후 사진" style="width: 98%; height: 150px; display:block; text-align: center;" />
		                                            <input type="file" accept="image/*" id="RPAIR_AFT_PHOTO3" name="RPAIR_AFT_PHOTO3" title='img2' value="${rpairVO.RPAIR_AFT_PHOTO}" class="MX_50 DT_FILE_IMAGE" style="width: 90%; margin:10px 0px;" />
		                                            <img src="<c:url value='/images/del_img.png' />" onclick="fnDelImg('a_img3');" style="cursor:pointer"/>
                                                </span>

                                            </span>
                                        </td>

                                    </tr>

                                    <tr>
                                        <td colspan="4" style="text-align:right">
                                            <a href="#" onclick="fnAddImgRow();" class="graybtn edit" style="vertical-align:middle; ">보수전ㆍ후 사진 추가</a>
                                            <a href="#" onclick="fnDelImgRow();" class="graybtn edit" style="vertical-align:middle; ">보수전ㆍ후 사진 삭제</a>
                                        </td>

                                    </tr>

                                    <tr>
                                        <%-- <th>처리일시</th>
                                        <td><input type="text" id="PROCESS_DT" name="PROCESS_DT" value="${rpairVO.PROCESS_DT}" style="width: 60px;" readonly /></td> --%>
                                        <th colspan="2">처리기간 ( 처리일자 - 신고일자 )</th>
                                        <td colspan="3">
                                            <%-- <input type="text" id="DAYCNT" name="DAYCNT" <c:if test="${sttemntVO.PRCS_STTUS == 'PRCS0003' || ( rpairVO.RPAIR_DT != '' && rpairVO.RPAIR_DT != undefined && rpairVO.RPAIR_DT != null ) }" >value="${rpairVO.DAYCNT}"</c:if> style="width: 30px; text-align: center; margin-right: 5px;" readonly />일 --%>
                                            <input type="text" id="DAYCNT" name="DAYCNT" <c:if test="${sttemntVO.PRCS_STTUS == 'PRCS0003'}" >value="${rpairVO.DAYCNT}"</c:if> style="width: 30px; text-align: center; margin-right: 5px;" readonly />일
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="btnbx" style="margin-right: 15px;">
            <div style="width: 30%; float: left; text-align: left; margin-left: 20px;">
                <!-- <a href="#" onclick="fnPrintScreens();" class="schbtn edit" style="display:none">출력</a> -->
                <% /*  위치보정 - 시군관리 등록 제외    */ %>
                <c:if test="${sttemntVO.imode ne 'ADD'}">
                    <a href="#" onclick="fnLocCpstn();" class="schbtn edit" id="locCpstn">위치보정</a>
                </c:if>
            </div>
            <div style="width: 65%; float: right;">
                <a href="#" onclick="fnClearLoc(); $('#frm').reset(); $('#frm2').reset(); return false;" id="reset" class="schbtn edit">초기화</a>
                <a href="#" onclick="fnUpdate();" class="schbtn edit">
                 <% /*  등록    */ %>
                <c:choose>
                    <c:when test="${ sttemntVO.imode eq 'ADD' }">등록</c:when>
                    <c:otherwise>저장</c:otherwise>
                </c:choose>
                </a>
                <div class="tc mt20" id="loadingBar" style="display:none; z-index:9999; position:absolute; left:45%; top:40%;" ><img src='<c:url value="/images/loading/loading.gif" />' alt="로딩바" /></div>
                <a href="#" onclick="fnCancel();" class="schbtn">취소</a>
                <a href="#" onclick="fnClose();" class="schbtn">닫기</a>
            </div>
        </div>
    </div>

    <%@ include file="/include/common.jsp" %>

<script id="jsalert" src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javascript" >

var imgPath = '${file_path}';
var b_f_info0 = '${bfe_file_info0.FILE_NO}';
var b_f_info1 = '${bfe_file_info1.FILE_NO}';
var b_f_info2 = '${bfe_file_info2.FILE_NO}';
var b_f_info3 = '${bfe_file_info3.FILE_NO}';
var b_imgRow = '${bfe_file_length}';
var a_imgRow = '${aft_file_length}';
var imgRow = b_imgRow > a_imgRow ? b_imgRow : a_imgRow ;

var del_b ;
var del_a ;

var bfeImg = '';
var aftImg = '';
var geocoder = new daum.maps.services.Geocoder();
var dept_code= '${sttemntVO.DEPT_CODE}';
var orgTmX = "${sttemntVO.TM_X}";
var orgTmY = "${sttemntVO.TM_Y}";

$(document).ready(function() {

    // 2018. 05. 10. JOY. 좌표오류 시 공간보정 버튼 hide
    if ( "${sttemntVO.PRCS_STTUS}" == "PRCS0007" ) {

        $("#locCpstn").hide();

    }

    // DAYCNT 값 세팅
    /* if ( $("input:radio[name=PRCS_STTUS]:checked").val() != "PRCS0003" ) {

        $("#DAYCNT").val("");

    } else {

        calcDayCnt();

    } */

    // 2018. 02. 05. JOY. 검색조건 부서 setting
    var usrGrp = "${sessionScope.userinfo.REQ_USER_GRP}";
    var usrDept = "${usrDeptCode}" ;

    // 관할기관 셀렉트박스 표현여부
    if ( usrGrp == 'ROLE_ADMIN' || usrDept == $('#DEPT_CODE').val()) {
        $('.sel_dept_code').show();
        $('.input_dept_code').hide();

    }
    else {
    	$('.sel_dept_code').hide();
    	$('.input_dept_code').show();

    	$('#sel_prcs input[value="PRCS0005"]').click(function(){
            $('.sel_dept_code').show();
            $('.input_dept_code').hide();
        })

        $('#sel_prcs input[value!="PRCS0005"]').click(function(){
            $('.sel_dept_code').hide();
            $('.input_dept_code').show();
            $('#DEPT_CODE').val(dept_code)

        })
    }


    // 달력 생성
    COMMON_UTIL.cmCreateDatepickerLinked('CNFIRM_DT', 'CNFIRM_DT', 30);
    COMMON_UTIL.cmCreateDatepickerLinked('RPAIR_DT', 'RPAIR_DT', 30);

    // 편집모드 구분 (수정, 등록)
    // 시군관리 등록시 신고일시 달력컨트롤 추가
    var imode = $("#imode").val();
    var pthmode = $("#pthmode").val();
    if(pthmode == "C" && imode == "ADD"){

    	// 달력 생성
        COMMON_UTIL.cmCreateDatepickerLinked('STTEMNT_DT', 'STTEMNT_DT', 30);
        setChargeNm();

    }


/*
    // YYK 이미지 로드
    $('#RPAIR_BFE_PHOTO0').val(b_f_info0);
    $('#RPAIR_BFE_PHOTO1').val(b_f_info1);
    $('#RPAIR_BFE_PHOTO2').val(b_f_info2);
    $('#RPAIR_BFE_PHOTO3').val(b_f_info3);
 */


    // 파일첨부 시 이미지 로드
    $(":file").change(function() {

	    if ( this.files[0].type != 'image/jpg'
	    	 && this.files[0].type != 'image/jpeg'
	    	 && this.files[0].type != 'image/bmp'
	    	 && this.files[0].type != 'image/png'
	    	 && this.files[0].type != 'image/gif'
	    ) {
            alert('이미지파일(jpg/jpeg/bmp/png/gif 타입)만 첨부가 가능합니다.');
            $("#" + this.id).replaceWith( $("#" + this.id).clone(true) );
            return
        }
	    // 2018.03.31. YYK. 파일첨부시 1MB 용량 제한
        if ( this.files[0].size > 1048576 ) {
            alert('파일 크기가 너무 큽니다. 파일은 1MB 까지 첨부가 가능합니다.');
            $("#" + this.id).replaceWith( $("#" + this.id).clone(true) );
            return
        }

        readURL(this);
    });


    // 이미지 row 보여주기
    if (imgRow == 0) imgRow = 1;
    for (var i=1; i <= imgRow ; i++){
        $('.addFile'+i).addClass('showImg');
    }


    // 이미지 경로 저장

    var b_f_nm0 = "${bfe_file_info0.FILE_NM}";
    var b_f_nm1 = "${bfe_file_info1.FILE_NM}";
    var b_f_nm2 = "${bfe_file_info2.FILE_NM}";
    var b_f_nm3 = "${bfe_file_info3.FILE_NM}";
    var a_f_nm0 = "${aft_file_info0.FILE_NM}";
    var a_f_nm1 = "${aft_file_info1.FILE_NM}";
    var a_f_nm2 = "${aft_file_info2.FILE_NM}";
    var a_f_nm3 = "${aft_file_info3.FILE_NM}";

    if ( !(b_f_nm0 == null) && !(b_f_nm0 == "") && !(b_f_nm0 == undefined) ) {
    	bfeImg0 = "${file_path}${bfe_file_info0.FILE_COURS}/${bfe_file_info0.FILE_NM}";
    }
    if ( !(b_f_nm1 == null) && !(b_f_nm1 == "") && !(b_f_nm1 == undefined) ) {
        bfeImg1 = "${file_path}${bfe_file_info1.FILE_COURS}/${bfe_file_info1.FILE_NM}";
    }
    if ( !(b_f_nm2 == null) && !(b_f_nm2 == "") && !(b_f_nm2 == undefined) ) {
        bfeImg2 = "${file_path}${bfe_file_info2.FILE_COURS}/${bfe_file_info2.FILE_NM}";
    }
    if ( !(b_f_nm3 == null) && !(b_f_nm3 == "") && !(b_f_nm3 == undefined) ) {
        bfeImg3 = "${file_path}${bfe_file_info3.FILE_COURS}/${bfe_file_info3.FILE_NM}";
    }

    if ( !(a_f_nm0 == null) && !(a_f_nm0 == "") && !(a_f_nm0 == undefined) ) {
        aftImg0 = "${file_path}${aft_file_info0.FILE_COURS}/${aft_file_info0.FILE_NM}";
    }
    if ( !(a_f_nm1 == null) && !(a_f_nm1 == "") && !(a_f_nm1 == undefined) ) {
        aftImg1 = "${file_path}${aft_file_info1.FILE_COURS}/${aft_file_info1.FILE_NM}";
    }
    if ( !(a_f_nm2 == null) && !(a_f_nm2 == "") && !(a_f_nm2 == undefined) ) {
        aftImg2 = "${file_path}${aft_file_info2.FILE_COURS}/${aft_file_info2.FILE_NM}";
    }
    if ( !(a_f_nm3 == null) && !(a_f_nm3 == "") && !(a_f_nm3 == undefined) ) {
        aftImg3 = "${file_path}${aft_file_info3.FILE_COURS}/${aft_file_info3.FILE_NM}";
    }


    // 초기화 시 이미지 초기화
    $("#reset").click(function() {
       $("#b_img0").attr("src", bfeImg0);
       $("#b_img1").attr("src", bfeImg1);
       $("#b_img2").attr("src", bfeImg2);
       $("#b_img3").attr("src", bfeImg3);
       $("#a_img0").attr("src", aftImg0);
       $("#a_img1").attr("src", aftImg1);
       $("#a_img2").attr("src", aftImg2);
       $("#a_img3").attr("src", aftImg3);

    });

    // 천 단위 콤마
    $('#RPAIR_AMOUNT, #RPRSCL_WIDTH, #RPRSCL_VRTICL, #RPRSCL_AR, #RPRSCL_DP').keyup(function(e) {
        var s = $(this).val();
        s = COMMON_UTIL.commaFomatter(s);

        $(this).val(s);
    });

    // 담당자 - 연락처 매핑
    $("#CHARGER_NM").change(function() {
        chargerCttpc();
    });


    $("#CHARGER_NM").keydown(function(event) {

        if ( event.keyCode == 13 ) {

            chargerCttpc();

        }
    });


    // 부서 변경 시 담당자 매핑
    $("#DEPT_CODE").change(function() {

    	setChargeNm();

    });

    // 처리기간 계산
    /* $("#RPAIR_DT").change(function() {

        // 처리완료인 경우에만 계산
        if ( $("#RPAIR_DT").val() == "" || $("#RPAIR_DT").val() == null || $("#RPAIR_DT").val() == undefined
                || $("input:radio[name=PRCS_STTUS]:checked").val() != "PRCS0003" ) {

            $("#DAYCNT").val("");

        } else {

            calcDayCnt();

        }

    }); */

    /* $("input:radio[name=PRCS_STTUS]").click(function() {

        if ( $("input:radio[name=PRCS_STTUS]:checked").val() != "PRCS0003" ) {

            $("#DAYCNT").val("");

        } else {

            calcDayCnt();

        }

    }) */


});

function setChargeNm(){

	var postData = { "DEPT_CODE" : $("#DEPT_CODE").val() };

    $.ajax({
        url : contextPath + 'api/pothole/sttemnt/selectChargerNm.do',
        type : 'post',
        dataType : 'json',
        contentType : 'application/json; charset=UTF-8',
        data : JSON.stringify(postData),
        success : function( data ) {

            if ( data != null && data != undefined && data != "" ) {

                $("#CHARGER_NM").val(data.chargernm);
                $("#CTTPC").val(data.cttpc);

            } else {

                $("#CHARGER_NM").val("");
                $("#CTTPC").val("");

            }

        }
    });
}

function calcDayCnt() {

    // 신고일자
    var sttemntDtStr = $("#STTEMNT_DT").val();
    var sttemntDtArr = sttemntDtStr.split(" ")[0].split("-");
    var sttemntDate = new Date(sttemntDtArr[0], parseInt(sttemntDtArr[1]) - 1, sttemntDtArr[2]);

    // 현재시간
    var date = new Date();

    // 보수일자
    /* var rpairDtStr = $("#RPAIR_DT").val();
    var rpairDtArr = rpairDtStr.split(" ")[0].split("-");
    var rpairDate = new Date(rpairDtArr[0], parseInt(rpairDtArr[1]) - 1, rpairDtArr[2]); */

    // 차이 계산
    /* var diff = Math.abs(rpairDate.getTime() - sttemntDate.getTime());
    diff = Math.ceil(diff / (24 * 60 * 60 * 1000)); */
    var diff = Math.abs(date.getTime() - sttemntDate.getTime());
    diff = Math.ceil(diff / (24 * 60 * 60 * 1000));

    $("#DAYCNT").val(diff);

}

function chargerCttpc() {

    var postData = { "CHARGER_NM" : $("#CHARGER_NM").val() };

    $.ajax({
        url : contextPath + 'api/pothole/sttemnt/selectChargerCttpc.do',
        type : 'post',
        dataType : 'json',
        contentType : 'application/json; charset=UTF-8',
        data : JSON.stringify(postData),
        success : function( data ) {

            if ( data != null && data != undefined && data != "" ) {

                $("#CTTPC").val(data.cttpc);

            } else {

                $("#CTTPC").val("");

            }
        }
    });

}

// 파일첨부 시 이미지 로드
function readURL(obj) {

    if ( obj.files && obj.files[0] ) {
	    var reader = new FileReader();

	    reader.onload = function(e) {
	        var id ;
	        var obj_id = obj.id;
	        var i = obj_id.charAt(obj_id.length-1);

	        if ( obj_id.indexOf("RPAIR_BFE_PHOTO") != -1 ) {
	            id = "b_img"+ i;
	            $("#BFEflag").val($("#BFEflag").val().replace("b_img"+ i, ""));
	        }
	        else if ( obj_id.indexOf("RPAIR_AFT_PHOTO") != -1 ) {
	            id = "a_img"+ i;
	            $("#AFTflag").val($("#AFTflag").val().replace("a_img"+ i, ""));
	        }

	        $("#" + id).attr("src", e.target.result);
	        //$("#" + id).parent().css("display", "block");
	    }
	    reader.readAsDataURL(obj.files[0]);
    }
}

// 로드된 이미지 삭제
function fnDelImg(img){

	var i = img.charAt(img.length-1);

	if ( img.indexOf('b_img') != -1 ) {
		$("#"+img).attr("src", "");

		// ie 로드된 이미지 삭제
		if (get_version_of_IE() == "msie" || get_version_of_IE() == "msie11"){
			$("#RPAIR_BFE_PHOTO"+ i).replaceWith( $("#RPAIR_BFE_PHOTO"+ i).clone(true) );
		}
		// ie 제외 이미지 삭제
		else {
			$('#RPAIR_BFE_PHOTO'+i).val('');
		}

	    del_b += img;
	    $("#BFEflag").val(del_b);
	}
	else if ( img.indexOf('a_img') != -1 ) {
		$("#"+img).attr("src", "");

		// ie 로드된 이미지 삭제
        if (get_version_of_IE() == "msie" || get_version_of_IE() == "msie11"){
            $("#RPAIR_AFT_PHOTO"+ i).replaceWith( $("#RPAIR_AFT_PHOTO"+ i).clone(true) );
        }
        // ie 제외 이미지 삭제
        else {
            //$("#"+img).css("display", "none");
        	$('#RPAIR_AFT_PHOTO'+i).val('');
        }

		del_a += img;
        $("#AFTflag").val(del_a);
	}

}

// 엑셀 출력
function fnPrintScreens() {

   var frm = $('#frm').cmSerializeObject();
   var frm2 = $('#frm2').cmSerializeObject();


   var postData = $.extend({}, frm, frm2);

   var img1 = $("#img1").attr('src').replace(imgPath, '${fileRealPath}');
   var img2 = $("#img2").attr('src').replace(imgPath, '${fileRealPath}');

   $("#img1").attr('src', img1);
   $("#img2").attr('src', img2);

   var img1Path = $("#img1").attr('src');
   var img2Path = $("#img2").attr('src');

   postData["RPAIR_BFE_PHOTO_PATH"] = img1Path;
   postData["RPAIR_AFT_PHOTO_PATH"] = img2Path;

   $.ajax({
       url : contextPath + 'api/sttemnt/sttemntViewExcel.do',
       type : 'post',
       dataType : 'json',
       contentType : 'application/json; charset=UTF-8',
       data : JSON.stringify(postData),
       success : function(res) {

           alert("D:/포트홀-신고관리.xls 로 저장되었습니다.");

       },
       error : function() {

           return;
       }
   })
}

// 수정
function fnUpdate() {

	// 편집모드 구분 (수정, 등록)
	// 시군관리 등록시 포트홀_관리_번호 부여되지 않았으므로 위치정보ID(G2_ID) 로 넘김.
	var imode = $("#imode").val();
	var pthmode = $("#pthmode").val();
	var g2_id = $("#GG_ID").val();
	var pthRgNo = "${sttemntVO.PTH_RG_NO}";

	// default - 시군관리 등록 제외한 나머지
	var msgWord = "수정";

	// default - 접수경로는 스마트카드가 기본값
	if((pthmode != "M") && (pthmode != "C")) {
		pthmode = "T";
	}

    // 시군관리 등록일 경우
    if( imode == "ADD"){
    	msgWord = "등록";

    	//시군 등록은 [접수] 단계로 설정함.
    	$("input:radio[name='PRCS_STTUS']")[1].click();

    	// 필수 입력 값 확인
        if ( $("#PRCS_STTUS").val().trim() == undefined
                || $("#PRCS_STTUS").val().trim() == ""
                || $("#PRCS_STTUS").val().trim() == null ) {

            alert("처리상태 값을 입력해주세요.");
            return;
        }
    }

    // 필수 입력 값 확인
    if ( $("#PRCS_STTUS").val().trim() == undefined
            || $("#PRCS_STTUS").val().trim() == ""
            || $("#PRCS_STTUS").val().trim() == null ) {

        alert("처리상태 값을 입력해주세요.");
        return;
    }

    if ( ( $("#DMG_TYPE").val() != "" || $("#RPAIR_DT").val() != "" || $("#RPAIR_AFT_PHOTO").val() != undefined )
            && $("input[name='PRCS_STTUS']:checked").val() != "PRCS0003" ) {

        if ( confirm("[보수정보]가 입력되었습니다.\n[처리상태]를 '처리완료'로 변경하시겠습니까?") ) {

            $("input:radio[name='PRCS_STTUS']")[2].click();

        }

    }

    if ( confirm("[신고정보] 및 [보수정보]를 " + msgWord + "하시겠습니까?") ) {

        // 위치보정 마커 숨기기
        parent.$("#dvMarker").hide();

        var postData = $("#frm").cmSerializeObject();
        postData["CHARGER_NM"] = $("#CHARGER_NM").val();
        postData["CTTPC"] = $("#CTTPC").val();
        postData["ROUTE_NM"] = $("#ROUTE_NM").val();
        postData["RPAIR_DT"] = $("#RPAIR_DT").val();

        var postData2 = $("#frm2").cmSerializeObject();

        var resDate = '';

        // 신고정보 수정
        $.ajax({
            url: contextPath + 'api/pothole/sttemnt/updateSttemntViewSttemnt.do'
            , type: 'post'
            , dataType : 'json'
            , contentType : 'application/json; charset=UTF-8'
            , data : JSON.stringify(postData)
            , success : function(res) {

            	// 시군관리 등록일 경우, 포트홀 관리 번호가 넘어옴.
                if( imode == "ADD"){
                	//alert("res : " + res);
                	pthRgNo = String(res).substring(0,8) + "-" + String(res).substring(8);
                	//alert("pthRgNo : " + pthRgNo);
                }

                if ( res > -1 ) {

                    // 2018. 04. 20. JOY. 마커 색상 및 현황 카운트 변경
                    if ( "${sttemntVO.PRCS_STTUS}" != $("input:radio[name=PRCS_STTUS]:checked").val() ) {

                        // 신고현황
                        var sLyr = parent.gMap.getLayerByName("GStatusLayer");
                        var sFts = sLyr.features;

                        for ( var i = 0; i < sFts.length; i++ ) {

                            // 2019년 고도화 사업 - 포트홀 관리번호와 접수경로를 추가로 비교하여야 함.
                        	if ( ( pthRgNo == cvtPthRgNo(sFts[i].data.pthRgNo))
                        		 &&	( pthmode == cvtPthRgNo(sFts[i].data.allData.pthmode)))	{

                                sFts[i].data.data = $("input:radio[name=PRCS_STTUS]:checked").val();
                                sFts[i].data.allData.PRCS_STTUS = $("input:radio[name=PRCS_STTUS]:checked").val();
                                sFts[i].attributes.data = $("input:radio[name=PRCS_STTUS]:checked").val();
                                sFts[i].attributes.allData.PRCS_STTUS = $("input:radio[name=PRCS_STTUS]:checked").val();

                                var bfStus = "${sttemntVO.PRCS_STTUS}".substring(7, 8);
                                var afStus = $("input:radio[name=PRCS_STTUS]:checked").val().substring(7, 8);
                                parent.$("#PRCS_STTUS" + bfStus).text( COMMON_UTIL.cmAddComma( parseInt( parent.$("#PRCS_STTUS" + bfStus).text().replace(",", "") ) - 1 ) );
                                parent.$("#PRCS_STTUS" + afStus).text( COMMON_UTIL.cmAddComma( parseInt( parent.$("#PRCS_STTUS" + afStus).text().replace(",", "") ) + 1 ) );

                                break;

                            }

                        }

                        sLyr.redraw();

                    }

                    // 2018. 05. 28. JOY. 보수현황 마커색상 및 카운트 변경
                    if ( "${rpairVO.RPRDTLS_MNG_NO}" == '' || "${rpairVO.RPRDTLS_MNG_NO}" == undefined || "${rpairVO.RPRDTLS_MNG_NO}" == null ) {
                        // 1. 보수정보 신규등록

                        if ( $("input:radio[name=PRCS_STTUS]:checked").val() == "PRCS0003" ) {
                            // 1-(1). 변경 처리상태 값이 '처리완료'인 경우

                            // addFeature, TotalCount
                            parent.PTH_UTIL.featureUtil("type", "add", pthmode, pthRgNo, $("#TM_X").val(), $("#TM_Y").val(), $("#HEADG").val(), $("#DMG_TYPE").val() );

                            if ( $("#DMG_TYPE").val() != "" && $("#DMG_TYPE").val() != null && $("#DMG_TYPE").val() != undefined ) {
                                // 1-(1)-[1]. 변경 파손유형 값이 있는 경우

                                // addCurrentCount
                                parent.PTH_UTIL.countUtil("type", "", $("#DMG_TYPE").val());

                            }

                        }

                    } else {
                        // 2. 보수정보 수정

                        if ( $("input:radio[name=PRCS_STTUS]:checked").val() == "PRCS0003" ) {
                            // 2-(1). 변경 처리상태 값이 '처리완료'인 경우

                            if ( "${sttemntVO.PRCS_STTUS}" == "PRCS0003" ) {
                                // 2-(1)-[1]. 기존 처리상태 값이 '처리완료'인 경우

                                if ( $("#DMG_TYPE").val() != "" && $("#DMG_TYPE").val() != null && $("#DMG_TYPE").val() != undefined ) {
                                    // 2-(1)-[1]-{1}. 변경 파손유형 값이 있는 경우

                                    if ( "${rpairVO.DMG_TYPE}" != "" && "${rpairVO.DMG_TYPE}" != null && "${rpairVO.DMG_TYPE}" != undefined ) {
                                        // 2-(1)-[1]-{1}-<1>. 기존 파손유형 값이 있는 경우

                                        // changeMarker, add/rmvCurrentCount
                                        parent.PTH_UTIL.changeMarker("type", pthmode, pthRgNo, $("#DMG_TYPE").val());
                                        parent.PTH_UTIL.countUtil("type", "${rpairVO.DMG_TYPE}", $("#DMG_TYPE").val());

                                    } else {
                                        // 2-(1)-[1]-{1}-<2>. 기존 파손유형 값이 없는 경우

                                        // changeMarker, addCurrentCount
                                        parent.PTH_UTIL.changeMarker("type", pthmode, pthRgNo, $("#DMG_TYPE").val());
                                        parent.PTH_UTIL.countUtil("type", "", $("#DMG_TYPE").val());

                                    }

                                } else {
                                    // 2-(1)-[1]-{2}. 변경 파손유형 값이 없는 경우

                                    if ( "${rpairVO.DMG_TYPE}" != "" && "${rpairVO.DMG_TYPE}" != null && "${rpairVO.DMG_TYPE}" != undefined ) {
                                        // 2-(1)-[1]-{2}-<1>. 기존 파손유형 값이 있는 경우

                                        // changeMarker, rmvCurrentCount
                                        parent.PTH_UTIL.changeMarker("type", pthmode, pthRgNo, $("#DMG_TYPE").val());
                                        parent.PTH_UTIL.countUtil("type", "${rpairVO.DMG_TYPE}", "");

                                    }

                                }

                            } else {
                                // 2-(1)-[2]. 기존 처리상태 값이 '처리완료'가 아닌 경우

                                // addFeatures, TotalCount
                                parent.PTH_UTIL.featureUtil("type", "add", pthmode, pthRgNo, $("#TM_X").val(), $("#TM_Y").val(), $("#HEADG").val(), $("#DMG_TYPE").val() );

                                if ( $("#DMG_TYPE").val() != "" && $("#DMG_TYPE").val() != null && $("#DMG_TYPE").val() != undefined ) {
                                    // 2-(1)-[2]-{1}. 변경 파손유형 값이 있는 경우

                                    if ( "${rpairVO.DMG_TYPE}" != "" && "${rpairVO.DMG_TYPE}" != null && "${rpairVO.DMG_TYPE}" != undefined ) {
                                        // 2-(1)-[2]-{1}-<1>. 기존 파손유형 값이 있는 경우

                                        // changeMarker, addCurrentCount
                                        parent.PTH_UTIL.changeMarker("type", pthmode, pthRgNo, $("#DMG_TYPE").val());
                                        parent.PTH_UTIL.countUtil("type", "", $("#DMG_TYPE").val());

                                    } else {
                                        // 2-(1)-[2]-{1}-<2>. 기존 파손유형 값이 없는 경우

                                        // addCurrentCount
                                        parent.PTH_UTIL.countUtil("type", "", $("#DMG_TYPE").val());

                                    }

                                } else {
                                    // 2-(1)-[2]-{2}. 변경 파손유형 값이 없는 경우

                                    if ( "${rpairVO.DMG_TYPE}" != "" && "${rpairVO.DMG_TYPE}" != null && "${rpairVO.DMG_TYPE}" != undefined ) {
                                        // 2-(1)-[2]-{2}-<1>. 기존 파손유형 값이 있는 경우

                                        // rmvCurrentCount
                                        parent.PTH_UTIL.countUtil("type", "${rpairVO.DMG_TYPE}", "");

                                    }

                                }

                            }

                        } else {
                            // 2-(2). 변경 처리상태 값이 '처리완료'가 아닌 경우

                            if ( "${sttemntVO.PRCS_STTUS}" == "PRCS0003" ) {
                                // 2-(2)-[1]. 기존 처리상태 값이 '처리완료'인 경우

                                // rmvFeatures, TotalCount
                                parent.PTH_UTIL.featureUtil("type", "rmv", pthmode, pthRgNo);

                                if ( "${rpairVO.DMG_TYPE}" != "" && "${rpairVO.DMG_TYPE}" != null && "${rpairVO.DMG_TYPE}" != undefined ) {
                                    // 2-(2)-[1]-{1}. 기존 파손유형 값이 있는 경우

                                    // rmvCurrentCount
                                    parent.PTH_UTIL.countUtil("type", "${rpairVO.DMG_TYPE}", "");

                                }

                            }

                        }

                    }

                    // alert 안뜨게
                    $("body").removeClass("cu");
                    check = false;

                    $('#loadingBar').show();

                    var urlParam = "";
                    if ( $("input[name=PRCS_STTUS]:checked").val() == "PRCS0003" ) {

                        urlParam += "&DAYCNT=1";
                    }

                    urlParam += "&STTEMNT_DT=" + $("#STTEMNT_DT").val();
                    urlParam += "&pthmode=" + pthmode;


                    var PTH_RG_NO = pthRgNo;

                    // 시군관리 등록시 G2_ID 넘겨줌.
                    if(imode == "ADD"){
                    	urlParam += "&imode=" + $("#imode").val();
                    	urlParam += "&GG_ID=" + $("#GG_ID").val();
                    }

                    // update
                    COMMON_UTIL.cmFileFormSubmit("frm2", "proc_frm", contextPath + "api/pothole/sttemnt/updateSttemntViewRpair.do?PTH_RG_NO=" + PTH_RG_NO + urlParam);

                    // 2018.03.25. YYK. 신고정보(처리상태) 수정 후 해당하는 신고정보 마커 띄움
                    var sttemnt = '';
                    for( var i=0; i < parent.gMap.getLayerByName('SttemntLayer').features.length; i++ ){
                    	if ((cvtPthRgNo(pthRgNo) == parent.gMap.getLayerByName('SttemntLayer').features[i].data.pthRgNo )
                    			&& (pthmode == parent.gMap.getLayerByName('SttemntLayer').features[i].data.allData.pthmode)) {

                            $('input[name="PRCS_STTUS"]').length
                            for( var v=0; v < $('input[name="PRCS_STTUS"]').length ; v++ ){
                                if ($('input[name="PRCS_STTUS"]')[v].checked == true ){
                                    sttemnt = $('input[name="PRCS_STTUS"]')[v].value
                                }
                            }

                            var sttemnt_pthRgNo = pthRgNo;
                            sttemnt_pthRgNo = sttemnt_pthRgNo.substring(0, 8) + pad(sttemnt_pthRgNo.substring(9), 6)

                            if ( sttemnt != '' ) {
                                var SttemntLayer = parent.gMap.getLayerByName("SttemntLayer");

                                for( var i=0; i < SttemntLayer.features.length; i++) {
                                    if (( SttemntLayer.features[i].data.pthRgNo == sttemnt_pthRgNo )
                                    		&& ( SttemntLayer.features[i].data.allData.pthmode == pthmode ) ){
                                        SttemntLayer.features[i].attributes.image = sttemnt ;
                                    }
                                }
                                SttemntLayer.redraw();
                            }

                    	}
                    }

                    for( var i=0; i < parent.gMap.getLayerByName('DmgtLayer').features.length; i++ ){
                        if ((cvtPthRgNo(pthRgNo) == parent.gMap.getLayerByName('DmgtLayer').features[i].data.pthRgNo )
                        		&& (pthmode == parent.gMap.getLayerByName('DmgtLayer').features[i].data.allData.pthmode )) {

		                    var dmgt = $('#DMG_TYPE').val() ;

		                    var dmgt_pthRgNo = pthRgNo ;
		                    dmgt_pthRgNo = dmgt_pthRgNo.substring(0, 8) + pad(dmgt_pthRgNo.substring(9), 6)

		                    var tm_x = '${sttemntVO.TM_X}'  ;
		                    var tm_y = '${sttemntVO.TM_Y}'  ;
		                    var headG = '${sttemntVO.HEADG}' ;

		                    if ( dmgt != '' ) {
		                        var DmgtLayer = parent.gMap.getLayerByName("DmgtLayer");

		                        var cnt = 0;

		                        for( var i=0; i < DmgtLayer.features.length; i++) {
		                            if ( DmgtLayer.features[i].attributes.pthRgNo == dmgt_pthRgNo ) {
		                                DmgtLayer.features[i].attributes.image = dmgt ;
		                                cnt ++ ;
		                            }
		                        }
		                        if ( cnt <= 0 ) {  // 기존 마커가 없을 경우 추가
		                            DmgtLayer.addFeatures(
		                                    new OpenLayers.Feature.Vector(
		                                            new OpenLayers.Geometry.Point( tm_x, tm_y )
		                                            ,{
		                                                image       : dmgt
		                                                , angle     : headG * 1 - 90
		                                                , pthRgNo   : dmgt_pthRgNo
		                                                , pthmode   : pthmode
		                                            }
		                                    )
		                            );
		                        }
		                        DmgtLayer.redraw() ;
		                    }
                        }
                    }

                    // 1초 딜레이
                    setTimeout(function() {
                    	var urlParamView = "&pthmode=" + pthmode;

                        // 시군관리 등록시 G2_ID 넘겨줌.
                        if(imode == "ADD"){
                        	urlParamView += "&imode=" + $("#imode").val();
                        	urlParamView += "&GG_ID=" + $("#GG_ID").val();

                        	parent.fnSelectLayer();
                        }

                        COMMON_UTIL.cmMoveUrl('pothole/sttemnt/selectSttemntView.do?PTH_RG_NO=' + pthRgNo + urlParamView);
                        $('#loadingBar').hide();

                    }, 3000);

                }

            }, error : function(err) {

                alert("신고정보 " + msgWord + "에 실패했습니다." + err);

                return;
            }

        });
    }

}

// 삭제
function fnDelete() {

    // 보수정보 유무 확인
    if ( $("#RPRDTLS_MNG_NO").val() == undefined
            || $("#RPRDTLS_MNG_NO").val() == ""
            || $("#RPRDTLS_MNG_NO").val() == null )   {

        alert("등록된 보수 정보가 없습니다.");
        return;

    }

    var statusflag = "${sttemntVO.PRCS_STTUS}";
    var typeflag = "${rpairVO.DMG_TYPE}";
    var pthRgNo = "${sttemntVO.PTH_RG_NO}";
    var pthmode = $("#pthmode").val();

    // 보수정보 삭제
    if ( confirm("삭제하시겠습니까?") ) {

        var postData = { "RPRDTLS_MNG_NO" : $("#RPRDTLS_MNG_NO").val()
        		       , "pthmode" : pthmode
        };

        $.ajax({
            url: contextPath + 'api/pothole/sttemnt/deletePotholeRpair.do'
            , type: 'post'
            , dataType: 'json'
            , contentType : 'application/json; charset=UTF-8'
            , data : JSON.stringify(postData)
            , success : function(res) {

                if ( res != 1 ) {
                    alert("삭제를 실패하였습니다.");
                    return;
                }

                // 2018. 05. 28. JOY. 보수정보 삭제 시 마커 처리
                if ( statusflag == "PRCS0003" ) {
                    // 1. 처리상태 값이 '처리완료'인 경우

                    if ( typeflag != "" && typeflag != null && typeflag != undefined ) {
                        // 1-(1). 기존 파손유형 값이 있는 경우

                        // changeMarker, removeCurrentCount
                        parent.PTH_UTIL.changeMarker("type", pthmode, pthRgNo, "");
                        parent.PTH_UTIL.countUtil("type", typeflag, "");

                    }

                }

                alert( "삭제되었습니다.");

                var imode = $("#imode").val();

                if(imode != "ADD") {
                    parent.bottomOpen();
                }

                window.parent.$("iframe").get(0).contentWindow.fn_search();

                var wnd_id = $("#wnd_id").val();
                COMMON_UTIL.cmWindowClose(wnd_id);

            }
        });
    }
}

function fnCancel() {

	if ( confirm("입력한 내용이 저장되지 않습니다.\n정말 취소하시겠습니까?") ) {

		// 2019년 고도화 사업
        // 시군 등록일 경우, 등록한 위치정보를 삭제하고 팝업 close
        var imode = $("#imode").val();
        if(imode == "ADD"){

            var g2_id = $("#GG_ID").val();

            //alert("g2_id : " + g2_id);

            if(g2_id != "") {

                MAP.fn_del_feature("PTH_CTSMNT",g2_id);
            }

            var wnd_id = $("#wnd_id").val();

            COMMON_UTIL.cmWindowClose(wnd_id);

        } else {

			$("body").removeClass("edit");
		    $("body").removeClass("sttemnt");
		    $("body").removeClass("cu");

		    // 접수경로 구분값 추가
		    var urlParam = "&pthmode=" + $("#pthmode").val();

		    COMMON_UTIL.cmMoveUrl('pothole/sttemnt/selectSttemntView.do?PTH_RG_NO=' + $('#PTH_RG_NO').val() + urlParam);
        }

	}

}

// 팝업 닫기
function fnClose() {

    if ( confirm("입력한 내용이 저장되지 않습니다.\n정말 종료하시겠습니까?") ) {

    	// 2019년 고도화 사업
        // 시군 등록일 경우, 저장되지 않고 창 닫힐 때 등록한 위치정보를 삭제한다.
        var imode = $("#imode").val();
    	if(imode == "ADD"){

            var g2_id = $("#GG_ID").val();

            //alert("g2_id : " + g2_id);

    	    if(g2_id != "") {

                MAP.fn_del_feature("PTH_CTSMNT",g2_id);
            }
    	}

        var wnd_id = $("#wnd_id").val();

        COMMON_UTIL.cmWindowClose(wnd_id);

    }
}

//위치보정
function fnLocCpstn() {

    // 윈도우 팝업 hide
    //parent.wWindowHideAll();

    // 중심 마커 띄우기
    var width = parent.window.innerWidth;
    var height = parent.window.innerHeight;

    width = ( width / 2 ) - 21;
    height = ( height / 2 ) - 21;

    parent.$("#dvMarker").css({ "left" : ( width + 1 ) + "px", "top" : ( height + 8 ) + "px"  });
    parent.$("#dvMarker").show();

    // dvComplete Dialog
    parent.$("#dvComplete").dialog({
        width : 150,
        height : 120,
        autoOpen : false,
        position : { my : "center center-135", at : "center center", of : parent.window }
    });

    parent.$("#dvComplete").dialog();
    // 확인 버튼 다이얼로그 open
    parent.$("#dvComplete").dialog("open");
    parent.$("#dvComplete").addClass("on");

    parent.$("#dvComplete #pWindow").val($("#wnd_id").val());

    // Extent 변경 ( 현재 정보를 중심으로 이동 )
    parent.gMap.zoomToExtent(parent.gMap.getLayerByName('GOverlapLayer').getDataExtent());

}

// 위치보정 후 좌표 값 저장
function fnSaveLoc() {

    // 중심 값 조회
    var center = parent.gMap.getCenter();
    var pthmode = $("#pthmode").val();

    var postData = {
            "PTH_RG_NO" : $("#PTH_RG_NO").val()
            , "pthmode" : pthmode
            , "TM_X" : center.lon
            , "TM_Y" : center.lat
    };

    $.ajax({

        url : contextPath + 'api/pothole/sttemnt/selectLatLon.do',
        type : 'post',
        dataType : 'json',
        contentType : 'application/json; charset=UTF-8',
        data : JSON.stringify(postData),
        success : function(res) {

            if ( res.resultSuccess == "success" ) {

                $("#DEPT_CODE").val(res.DEPT_CODE);
                $("#CORTN_X").val(res.CORTN_X);
                $("#CORTN_Y").val(res.CORTN_Y);
                $("#TM_X").val(res.TM_X);
                $("#TM_Y").val(res.TM_Y);
                $("#LNM_ADRES").val(res.LNM_ADRES);
                $("#DPLCT_STTEMNT_AT").val(res.DPLCT_STTEMNT_AT);
                $("#DSM_RP_NO").val(res.DSM_RP_NO);
                $("#CPT_MNG_NO").val(res.CPT_MNG_NO);
                $("#CHARGER_NM").val(res.CHARGER_NM);
                $("#CTTPC").val(res.CTTPC);
                //$("#LCDO").val("");

            } else {

                $("#DEPT_CODE").val("");
                $("#CORTN_X").val("");
                $("#CORTN_Y").val("");
                $("#TM_X").val("");
                $("#TM_Y").val("");
                $("#LNM_ADRES").val("");
                $("#DPLCT_STTEMNT_AT").val("");
                $("#DSM_RP_NO").val("");
                $("#CPT_MNG_NO").val("");
                $("#CHARGER_NM").val("");
                $("#CTTPC").val("");
                //$("#LCDO").val("");

                alert("위치 보정은 도로 위로만 가능합니다.");
                return;

            }

            // 신고현황 마커 위치 변경
            var sLyr = parent.gMap.getLayerByName("GStatusLayer");
            var sFts = sLyr.features;

            for ( var i = 0; i < sFts.length; i++ ) {

                if  (( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(sFts[i].data.pthRgNo) )
                		&& (pthmode == sFts[i].data.allData.pthmode) ){

                    sFts[i].geometry.x = res.TM_X;
                    sFts[i].geometry.y = res.TM_Y;

                    sFts[i].data.allData.TM_X = res.TM_X;
                    sFts[i].data.allData.TM_Y = res.TM_Y;

                    break;

                }

            }

            sLyr.redraw();

            // 파손유형 마커 위치 변경
            var tLyr = parent.gMap.getLayerByName("GTypeLayer");
            var tFts = tLyr.features;

            for ( var i = 0; i < tFts.length; i++ ) {

                if  (( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(tFts[i].data.pthRgNo) )
                		&& (pthmode == tFts[i].data.allData.pthmode) ) {

                    tFts[i].geometry.x = res.TM_X;
                    tFts[i].geometry.y = res.TM_Y;

                    tFts[i].data.allData.TM_X = res.TM_X;
                    tFts[i].data.allData.TM_Y = res.TM_Y;

                    break;

                }

            }

            tLyr.redraw();

            // 공간검색 - 신고현황 마커 위치 변경
            var ssLyr = parent.gMap.getLayerByName("SttemntLayer");
            var ssFts = ssLyr.features;

            for ( var i = 0; i < ssFts.length; i++ ) {

                if  (( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(ssFts[i].data.pthRgNo) )
                		&& (pthmode == ssFts[i].data.allData.pthmode) ){

                    ssFts[i].geometry.x = res.TM_X;
                    ssFts[i].geometry.y = res.TM_Y;

                    ssFts[i].data.allData.TM_X = res.TM_X;
                    ssFts[i].data.allData.TM_Y = res.TM_Y;

                    break;

                }

            }

            ssLyr.redraw();

            // 공간검색 - 파손유형 마커 위치 변경
            var stLyr = parent.gMap.getLayerByName("DmgtLayer");
            var stFts = stLyr.features;

            for ( var i = 0; i < stFts.length; i++ ) {

                if  (( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(stFts[i].data.pthRgNo) )
                		&& (pthmode == stFts[i].data.allData.pthmode) ){

                    stFts[i].geometry.x = res.TM_X;
                    stFts[i].geometry.y = res.TM_Y;

                    stFts[i].data.allData.TM_X = res.TM_X;
                    stFts[i].data.allData.TM_Y = res.TM_Y;

                    break;

                }

            }

            stLyr.redraw();

            // 현재 신고정보 위치 변경
            var oLyr = parent.gMap.getLayerByName("GOverlapLayer");
            var oFts = oLyr.features;

            for ( var i = 0; i < oFts.length; i++ ) {

                if  (( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(oFts[i].data.pthRgNo))
                		&& (pthmode == oFts[i].data.pthmode) ) {

                    oFts[i].geometry.x = res.TM_X;
                    oFts[i].geometry.y = res.TM_Y;

                    break;

                }

            }

            oLyr.redraw();

            // Extent 변경
            parent.gMap.getLayerByName('GOverlapLayer').features[0].geometry.bounds = parent.gMap.getExtent();
            //wWindowShowAll();

            // alert
            alert("위치가 수정되었습니다.");
            return;

        },
        error : function(error) {

            alert(error);
            return;
        }

    });

}

// 초기화 / 닫기 / X버튼 클릭 시 위치보정 초기화
function fnClearLoc() {

	var pthmode = $("#pthmode").val();

    // 신고현황 마커 위치 변경
    var sLyr = parent.gMap.getLayerByName("GStatusLayer");
    var sFts = sLyr.features;

    for ( var i = 0; i < sFts.length; i++ ) {

        if  (( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(sFts[i].data.pthRgNo) )
        		&& (pthmode == sFts[i].data.allData.pthmode) ){

            sFts[i].geometry.x = "${sttemntVO.TM_X}";
            sFts[i].geometry.y = "${sttemntVO.TM_Y}";

            break;

        }

    }

    sLyr.redraw();

    // 파손유형 마커 위치 변경
    var tLyr = parent.gMap.getLayerByName("GTypeLayer");
    var tFts = tLyr.features;

    for ( var i = 0; i < tFts.length; i++ ) {

        if  (( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(tFts[i].data.pthRgNo) )
        		&& (pthmode == tFts[i].data.allData.pthmode) ){

            tFts[i].geometry.x = "${sttemntVO.TM_X}";
            tFts[i].geometry.y = "${sttemntVO.TM_Y}";

            break;

        }

    }

    tLyr.redraw();

    // 공간검색 - 신고현황 마커 위치 변경
    var ssLyr = parent.gMap.getLayerByName("SttemntLayer");
    var ssFts = ssLyr.features;

    for ( var i = 0; i < ssFts.length; i++ ) {

        if  (( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(ssFts[i].data.pthRgNo) )
        		&& (pthmode == ssFts[i].data.allData.pthmode) ){

            ssFts[i].geometry.x = "${sttemntVO.TM_X}";
            ssFts[i].geometry.y = "${sttemntVO.TM_Y}";

            break;

        }

    }

    ssLyr.redraw();

    // 공간검색 - 파손유형 마커 위치 변경
    var stLyr = parent.gMap.getLayerByName("DmgtLayer");
    var stFts = stLyr.features;

    for ( var i = 0; i < stFts.length; i++ ) {

        if  (( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(stFts[i].data.pthRgNo) )
        		&& (pthmode == stFts[i].data.allData.pthmode) ){

            stFts[i].geometry.x = "${sttemntVO.TM_X}";
            stFts[i].geometry.y = "${sttemntVO.TM_Y}";

            break;

        }

    }

    // 현재 신고정보 위치 변경
    var oLyr = parent.gMap.getLayerByName("GOverlapLayer");
    var oFts = oLyr.features;

    for ( var i = 0; i < oFts.length; i++ ) {

        if  (( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(oFts[i].data.pthRgNo) )
        		&& (pthmode == oFts[i].data.pthmode) ){

            oFts[i].geometry.x = "${sttemntVO.TM_X}";
            oFts[i].geometry.y = "${sttemntVO.TM_Y}";

            break;

        }

    }

    oLyr.redraw();

}

// 이미지 row 추가
function fnAddImgRow(){
	if (imgRow == 4){
		alert('이미지는 4개까지 첨부가 가능합니다.');
		return;
	}
	imgRow++ ;
	$('.addFile'+imgRow).addClass('showImg');
}

//이미지 row 제거
function fnDelImgRow(){
	if (imgRow == 1){
		alert('더 이상 삭제가 불가능합니다.');
        return;
    }

	fnDelImg('b_img'+ (imgRow-1));
	fnDelImg('a_img'+ (imgRow-1));

    $('.addFile'+imgRow).removeClass('showImg');
    imgRow-- ;
}


    </script>
</body>
</html>
