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

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>

</head>

<body id="wrap" style="overflow-y: auto; min-width:600px;" class="sttemnt <c:if test="${ (sttemntVO.imode eq 'ADD')}">edit</c:if>">
    <!-- 필수 파라메터(START) -->
    <input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
    <input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
    <input type="hidden" id="opener_id" name="opener_id" value=""/>
    <input type="hidden" id="wnd_id" name="wnd_id" value=""/>

    <!-- 2018.03.12. YYK 위치도 이미지 -->
    <input type="hidden" id="imgUrl" name="imgUrl" value="${imgUrl}"/>
    <input type="hidden" id="mapType" name="mapType" value="${param.mapType }"/>

    <!-- hidden -->
    <input type="hidden" id="HEADG" name="HEADG" value="${sttemntVO.HEADG}" />
    <input type="hidden" id="DPLCT_STTEMNT_AT" name="DPLCT_STTEMNT_AT" value="${sttemntVO.DPLCT_STTEMNT_AT}" />
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
                    <input type="hidden" id="LCDO" name="LCDO" value="${sttemntVO.LCDO}"/>
                    <input type="hidden" id="LOC_VIEW" name="LOC_VIEW" value=""/>

                    <input type="hidden" id="CODE_NM_PRCS" name="CODE_NM_PRCS" value="" />
                    <input type="hidden" id="CODE_NM_DMGT" name="CODE_NM_DMGT" value="" />
                    <input type="hidden" id="PRCS_STTUS_NM" name="PRCS_STTUS_NM" value="${sttemntVO.PRCS_STTUS_NM}" />
                    <input type="hidden" id="PTH_RG_NO" name="PTH_RG_NO" value="${sttemntVO.PTH_RG_NO}" />
                    <input type="hidden" id="DMG_TYPE_NM" name="DMG_TYPE_NM" value="${sttemntVO.DMG_TYPE_NM}" />
                    <input type="hidden" id="LOWEST_DEPT_NM" name="LOWEST_DEPT_NM" value="${sttemntVO.LOWEST_DEPT_NM}" />
                    <input type="hidden" id="pthmode" name="pthmode" value="${sttemntVO.pthmode}" />



                    <div class="content" style="margin: 0px 10px 10px 10px; padding: 0px 10px">
                        <div class="titbx">
                            <h4 class='excel' style="padding: 2px 10px;">처리정보</h4>
                            <table id="goExcelTb" class="tbview" summary="처리상태를 제공합니다.">
                                <colgroup>
                                    <col width="12%" />
                                    <col width="38%" />
                                    <col width="14%" />
                                    <col width="36%" />
                                </colgroup>

                                <tbody>
                                    <tr>
                                        <th style="width: 12%;">처리상태 <span style="color: #ff7575;">*</span></th>
                                        <td style="width: 88%; font-weight: bold; font-size: 11px;" id="PRCS_STTUS">
                                            <%-- <span style="line-height: 23px"><c:out value="${sttemntVO.PRCS_STTUS_NM}" /></span> --%>
                                            <c:forEach items="${prcsList}" var="prcs">
                                                <input id="PRCS_STTUS" name="PRCS_STTUS" type="radio" value="${prcs.CODE_VAL}" style="margin: 0px 2px 0px 4px;" <c:if test="${sttemntVO.PRCS_STTUS == prcs.CODE_VAL }">checked="checked"</c:if> disabled />
                                                <span <c:if test='${sttemntVO.PRCS_STTUS == prcs.CODE_VAL }'>style="color: red;"</c:if> >${prcs.CODE_NM}</span>
                                            </c:forEach>
                                            <c:if test="${ (sttemntVO.pthmode ne 'C') && (sttemntVO.pthmode ne 'M')}">
                                            <c:if test="${sessionScope.userinfo.REQ_USER_GRP == 'ROLE_ADMIN'}">
                                                <c:if test="${sttemntVO.PRCS_STTUS == 'PRCS0005'}">
                                                    <a href="#" id="smssend" class="schbtn" onclick="fnSendSMS(${sttemntVO.PTH_RG_NO})" style="float: right;">SMS전송</a>
                                                </c:if>
                                            </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="content" style="margin: 5px 10px 0px 10px; padding: 0px 10px">
                        <div class="titbx">
                            <h4 class='excel' style="padding: 2px 10px;">신고정보 ( ${sttemntVO.RCPT_ROUTE} )</h4>
                            <table class="tbview" style="table-layout:fixed" summary="신고정보를 제공합니다.">
                                <colgroup>
                                    <col width="12%" />
                                    <col width="38%" />
                                    <col width="13%" />
                                    <col width="37%" />
                                </colgroup>

                                <tbody>
                                    <tr class='excel'>
                                        <th>등록번호</th>
                                        <td><c:out value="${sttemntVO.PTH_RG_NO}" /></td>
                                        <th>관할기관</th>
                                        <td>
                                            <c:if test="${ fn:indexOf(sttemntVO.DEPT_CODE, '70003') > -1 && sttemntVO.DEPT_CODE != '7000300' }">민자도로사업자 (<c:out value="${sttemntVO.LOWEST_DEPT_NM}" />)</c:if>
                                            <c:if test="${ fn:indexOf(sttemntVO.DEPT_CODE, '70003') == -1 }"><c:out value="${sttemntVO.LOWEST_DEPT_NM}" /></c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>신고자</th>
                                        <td>
                                            <input type="hidden" id="BSNM_NM" name="BSNM_NM" value="${sttemntVO.BSNM_NM}" />
                                            <c:out value="${sttemntVO.BSNM_NM}" />
                                        </td>
                                        <% /*  접수경로 - 시군관리, 국토부 신고앱, 스마트카드(default)    */ %>
                                        <th>접수경로</th>
                                        <td>${sttemntVO.RCPT_ROUTE}</td>
                                    </tr>
                                    <tr>
                                        <th>신고일시</th>
                                        <td>
                                            <fmt:parseDate value="${sttemntVO.STTEMNT_DT}" var="STTEMNT_DT" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            <fmt:formatDate value="${STTEMNT_DT}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            <input type="hidden" id="STTEMNT_DT" name="STTEMNT_DT" value="<fmt:formatDate value="${STTEMNT_DT}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
                                        </td>
                                        <% /*  차량번호 - 시군관리, 국토부 신고앱 제외    */ %>
                                        <c:if test="${ (sttemntVO.pthmode ne 'C') && (sttemntVO.pthmode ne 'M')}">
                                        <th>차량번호</th>
                                        <td>
                                            <input type="hidden" id="VHCLE_NO" name="VHCLE_NO" value="${sttemntVO.VHCLE_NO}" />
                                            <c:out value="${sttemntVO.VHCLE_NO}" />
                                        </td>
                                        </c:if>
                                         <% /*  국토부 신고앱 사진조회    */ %>
                                        <c:if test="${ (sttemntVO.pthmode eq 'M' ) }">
                                        <th>사진조회</th>
                                        <td>
							                  <div style="float:left;">
							                  	<img id="M_img0" src="<c:if test = "${PTH_PHOTO0.FILE_NM != null }" >${file_path}${PTH_PHOTO0.FILE_COURS}/${PTH_PHOTO0.ORGINL_FILE_NM}</c:if>"
						                      	alt="" title="국토부앱 포트홀 신고사진" style="width: 50px; height:35px; text-align: center; margin-right: 10px; cursor:pointer;" onclick="fn_PthPhotoPop(0);" />
						                      	<input type="hidden" id="pth_photo0" value="<c:if test = "${PTH_PHOTO0.FILE_NM != null }" >${file_path}${PTH_PHOTO0.FILE_COURS}/${PTH_PHOTO0.ORGINL_FILE_NM}</c:if>">
						                      </div>

						                      <div style="float:left;">
						                      	<img id="M_img1" src="<c:if test = "${PTH_PHOTO1.FILE_NM != null }" >${file_path}${PTH_PHOTO1.FILE_COURS}/${PTH_PHOTO1.ORGINL_FILE_NM}</c:if>"
						                      	alt="" title="국토부앱 포트홀 신고사진" style="width: 50px; height:35px; text-align: center; margin-right: 10px; cursor:pointer;"onclick="fn_PthPhotoPop(1);" />
						                     	<input type="hidden" id="pth_photo1" value="<c:if test = "${PTH_PHOTO1.FILE_NM != null }" >${file_path}${PTH_PHOTO1.FILE_COURS}/${PTH_PHOTO1.ORGINL_FILE_NM}</c:if>">
						                      </div>

						                      <div style="float:left;">
						                      	<img id="M_img2" src="<c:if test = "${PTH_PHOTO2.FILE_NM != null }" >${file_path}${PTH_PHOTO2.FILE_COURS}/${PTH_PHOTO2.ORGINL_FILE_NM}</c:if>"
						                      	alt="" title="국토부앱 포트홀 신고사진" style="width: 50px; height:35px; text-align: center; cursor:pointer;" onclick="fn_PthPhotoPop(2);" /></div>
						                      	<input type="hidden" id="pth_photo2" value="<c:if test = "${PTH_PHOTO2.FILE_NM != null }" >${file_path}${PTH_PHOTO2.FILE_COURS}/${PTH_PHOTO2.ORGINL_FILE_NM}</c:if>">
                                        </td>
                                        </c:if>
                                    </tr>
                                    <tr>
                                        <th>도로명</th>
                                        <td colspan="3" style="width: 400px;">
                                            <input type="hidden" id="RN_ADRES" name="RN_ADRES" value="${sttemntVO.RN_ADRES}" />
                                            <c:out value="${sttemntVO.RN_ADRES}" /></td>
                                    </tr>
                                    <tr>
                                        <th>지번주소</th>
                                        <td colspan="3" style="width: 400px;">
                                            <span style="line-height: 23px;">
                                            <input type="hidden" id="LNM_ADRES" name="LNM_ADRES" value="${sttemntVO.LNM_ADRES}" />
                                            <c:out value="${sttemntVO.LNM_ADRES}" /></span>
                                            <a href="#" id="overlap" class="schbtn" onclick="fnShowOverlap($(this), ${sttemntVO.PTH_RG_NO})" style="background-color: gray; float: right;">중복신고 위치조회</a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    </form>

                    <form id="frm2" name="frm2" method="post" action="">
                    <input type="hidden" id="RPAIR_BFE_PHOTO_PATH" name="RPAIR_BFE_PHOTO_PATH" value="" />
                    <input type="hidden" id="RPAIR_AFT_PHOTO_PATH" name="RPAIR_AFT_PHOTO_PATH" value="" />


                    <div class="content" style="margin: 5px 10px 0px 10px; padding: 0px 10px">
                        <div class="titbx">
                            <h4 class='excel' style="padding: 2px 10px;">보수정보 <c:if test="${rpairVO.RPRDTLS_MNG_NO == '' || rpairVO.RPRDTLS_MNG_NO == undefined || rpairVO.RPRDTLS_MNG_NO == null}"><span style="color: rgba(253, 237, 93, 1); font-size: 12px; margin-left: 5px;">등록된 보수정보가 없습니다.</span></c:if></h4>
                            <table class="tbview" style="margin-bottom: -5px; table-layout:fixed;" summary="보수정보를 제공합니다.">
                                <colgroup>
                                    <col width="13%" />
                                    <col width="36%" />
                                    <col width="15%" />
                                    <col width="36%" />
                                </colgroup>

                                <tbody>
                                    <tr>
                                        <th>파손유형</th>
                                        <td colspan="3"><c:out value="${sttemntVO.DMG_TYPE_NM}" /></td>
                                    </tr>

                                    <tr>
                                        <th>담당자</th>
                                        <td>
                                            <input type="hidden" id="CHARGER_NM" name="CHARGER_NM" value="${sttemntVO.CHARGER_NM}" />
                                            <c:out value="${sttemntVO.CHARGER_NM}" />
                                        </td>
                                        <th>연락처</th>
                                        <td>
                                            <input type="hidden" id="CTTPC" name="CTTPC" value="${sttemntVO.CTTPC}" />
                                            <c:out value="${sttemntVO.CTTPC}" />
                                        </td>
                                    </tr>

                                    <%-- <tr>
                                        <th>도로명</th>
                                        <td colspan="3">
                                            <input type="hidden" id="ROUTE_NM" name="ROUTE_NM" value="${cmptncVO.ROUTE_NM}" />
                                            <c:out value="${cmptncVO.ROUTE_NM}" />
                                        </td>
                                    </tr> --%>

                                    <tr>
                                        <th>시공사</th>
                                        <td>
                                            <!-- <input type="radio" id="CO_NO" name="CO_NO" value="23" checked="checked" /> -->
                                            현장팀
                                        </td>
                                        <th>보수금액(원)</th>
                                        <td class="num" >
                                            <input type="hidden" id="RPAIR_AMOUNT" name="RPAIR_AMOUNT" value="${rpairVO.RPAIR_AMOUNT}" />
                                            <fmt:formatNumber value="${rpairVO.RPAIR_AMOUNT}" pattern="#,###" />
                                        </td>

                                    </tr>

                                    <tr>
                                        <th>보수규모</th>
                                        <td colspan="3">
                                            가로(cm) <input type="text" id="RPRSCL_WIDTH" name="RPRSCL_WIDTH" class="num" value="<fmt:formatNumber value="${rpairVO.RPRSCL_WIDTH}" pattern="#,###.##" />" style="width: 10.5%; margin: 0 2.5px;" readonly />
                                            세로(cm) <input type="text" id="RPRSCL_VRTICL" name="RPRSCL_VRTICL" class="num" value="<fmt:formatNumber value="${rpairVO.RPRSCL_VRTICL}" pattern="#,###.##" />"style="width: 11%; margin: 0 2.5px;" readonly />
                                            면적(㎠) <input type="text" id="RPRSCL_AR" name="RPRSCL_AR" class="num" value="<fmt:formatNumber value="${rpairVO.RPRSCL_AR}" pattern="#,###.##" />" style="width: 10.5%; margin: 0 2.5px;" readonly />
                                            깊이(cm) <input type="text" id="RPRSCL_DP" name="RPRSCL_DP" class="num" value="<fmt:formatNumber value="${rpairVO.RPRSCL_DP}" pattern="#,###.##" />" style="width: 11%; margin: 0 2.5px;" readonly />
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>비고</th>
                                        <td colspan="3">
                                            <input type="hidden" id="RM" name="RM" value="${rpairVO.RM}" />
                                            <c:out value="${rpairVO.RM}" />
                                        </td>
                                    </tr>
<%--
                                    <tr>
                                        <th colspan="4">위치도</th>
                                    </tr>
                                    <tr>
                                        <td id="loc_view" colspan="4" style="height: 300px;">
                                            <c:if test = "${ lcdo.FILE_NM != null }" >
                                            <img id="lcdo_view" src="${file_path}${lcdo.FILE_COURS}${lcdo.FILE_NM}" alt='위치도' title='위치도' style='width:100%; height: 300px;'>
                                            </c:if>
                                        </td>
                                    </tr>
 --%>
                                    <tr style="vertical-align: bottom;">
                                        <th colspan="2" style="border-right: solid 0px; background-color: #ffffff;">보수 전</th>
                                        <th colspan="2" style="border-left: solid 0px; background-color: #ffffff;">보수 후</th>
                                    </tr>

                                    <tr>
                                        <th>확인일자</th>
                                        <td>
                                            <fmt:parseDate value="${rpairVO.CNFIRM_DT}" var="CNFIRM_DT" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate value="${CNFIRM_DT}" pattern="yyyy-MM-dd"/>
                                            <input type="hidden" id="CNFIRM_DT" name=CNFIRM_DT value="<fmt:formatDate value="${CNFIRM_DT}" pattern="yyyy-MM-dd"/>" />
                                        </td>
                                        <th>보수일자</th>
                                        <td>
                                            <fmt:parseDate value="${rpairVO.RPAIR_DT}" var="RPAIR_DT" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate value="${RPAIR_DT}" pattern="yyyy-MM-dd"/>
                                            <input type="hidden" id="RPAIR_DT" name="RPAIR_DT" value="<fmt:formatDate value="${RPAIR_DT}" pattern="yyyy-MM-dd"/>" />
                                        </td>
                                    </tr>
                                    <tr style="height: 200px;">
                                        <td id="" colspan="2">
                                            <img id="show_b_img" src="" alt="" title="보수 전 사진" style="width: 98%; height: 195px; text-align: center;" />
                                        </td>
                                        <td id="" colspan="2">
                                            <img id="show_a_img" src="" alt="" title="보수 후 사진" style="width: 98%; height: 195px; text-align: center;" />
                                        </td>
                                    </tr>
                                    <tr style="height: 50px;">
                                        <td colspan="2">
                                            <span class='show_bfe_img btncursor' style="display: ;">
                                                <img id="b_img0" src="<c:if test = "${bfe_file_info0.FILE_NM != null }" >${file_path}${bfe_file_info0.FILE_COURS}/${bfe_file_info0.FILE_NM}</c:if>"
                                                alt="" title="보수 전 사진" style="width: 24%; height: 50px; text-align: center; opacity:0.5 " />
                                                <%-- <br/>등록된 이미지 : <c:out value="${bfe_file_info0.ORGINL_FILE_NM}"/> --%>

                                                <img id="b_img1" src="<c:if test = "${bfe_file_info1.FILE_NM != null }" >${file_path}${bfe_file_info1.FILE_COURS}/${bfe_file_info1.FILE_NM}</c:if>"
                                                alt="" title="보수 전 사진" style="width: 24%; height: 50px; text-align: center; opacity:0.5" />
                                                <%-- <br/>등록된 이미지 : <c:out value="${bfe_file_info0.ORGINL_FILE_NM}"/> --%>

                                                <img id="b_img2" src="<c:if test = "${bfe_file_info2.FILE_NM != null }" >${file_path}${bfe_file_info2.FILE_COURS}/${bfe_file_info2.FILE_NM}</c:if>"
                                                alt="" title="보수 전 사진" style="width: 24%; height: 50px; text-align: center; opacity:0.5" />
                                                <%-- <br/>등록된 이미지 : <c:out value="${bfe_file_info0.ORGINL_FILE_NM}"/> --%>

                                                <img id="b_img3" src="<c:if test = "${bfe_file_info3.FILE_NM != null }" >${file_path}${bfe_file_info3.FILE_COURS}/${bfe_file_info3.FILE_NM}</c:if>"
                                                alt="" title="보수 전 사진" style="width: 24%; height: 50px; text-align: center; opacity:0.5" />
                                                <%-- <br/>등록된 이미지 : <c:out value="${bfe_file_info0.ORGINL_FILE_NM}"/> --%>

                                            </span>
                                        </td>
                                        <td colspan="2">
                                            <span class='show_aft_img btncursor' style="display: ;">
                                                <img id="a_img0" src="<c:if test = "${aft_file_info0.FILE_NM != null }" >${file_path}${aft_file_info0.FILE_COURS}/${aft_file_info0.FILE_NM}</c:if>"
                                                alt="" title="보수 후 사진" style="width: 24%; height: 50px; text-align: center; opacity:0.5" />

                                                <img id="a_img1" src="<c:if test = "${aft_file_info1.FILE_NM != null }" >${file_path}${aft_file_info1.FILE_COURS}/${aft_file_info1.FILE_NM}</c:if>"
                                                alt="" title="보수 후 사진" style="width: 24%; height: 50px; text-align: center; opacity:0.5" />

                                                <img id="a_img2" src="<c:if test = "${aft_file_info2.FILE_NM != null }" >${file_path}${aft_file_info2.FILE_COURS}/${aft_file_info2.FILE_NM}</c:if>"
                                                alt="" title="보수 후 사진" style="width: 24%; height: 50px; text-align: center; opacity:0.5" />

                                                <img id="a_img3" src="<c:if test = "${aft_file_info3.FILE_NM != null }" >${file_path}${aft_file_info3.FILE_COURS}/${aft_file_info3.FILE_NM}</c:if>"
                                                alt="" title="보수 후 사진" style="width: 24%; height: 50px; text-align: center; opacity:0.5" />

                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>처리일시</th>
                                        <%-- <td><c:if test="${sttemntVO.PRCS_STTUS == 'PRCS0003' || ( rpairVO.RPAIR_DT != '' && rpairVO.RPAIR_DT != undefined && rpairVO.RPAIR_DT != null ) }" ><c:out value="${rpairVO.PROCESS_DT}" /></c:if></td> --%>
                                        <td><c:if test="${sttemntVO.PRCS_STTUS == 'PRCS0003'}" ><c:out value="${rpairVO.PROCESS_DT}" /></c:if></td>
                                        <th>처리기간</th>
                                        <%-- <td><c:if test="${sttemntVO.PRCS_STTUS == 'PRCS0003' || ( rpairVO.RPAIR_DT != '' && rpairVO.RPAIR_DT != undefined && rpairVO.RPAIR_DT != null ) }" ><c:out value="${rpairVO.DAYCNT}" /><c:if test="${rpairVO.DAYCNT != null}">일</c:if></c:if></td> --%>
                                        <td><c:if test="${sttemntVO.PRCS_STTUS == 'PRCS0003'}" ><c:out value="${rpairVO.DAYCNT}" /><c:if test="${rpairVO.DAYCNT != null}">일</c:if></c:if></td>
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
                <!-- <a href="#" onclick="fnPrintScreens();" class="schbtn edit" >출력</a> -->
                <a href="#" onclick="fnLocCpstn();" class="schbtn edit" style="display:none">위치보정</a>
            </div>
            <div style="width: 99%; float: left;">
                <c:if test="${sessionScope.userinfo.REQ_USER_GRP == 'ROLE_ADMIN'}">
                    <a href="#" onclick="fnUpdate();" class="schbtn edit">보수결과입력</a>
                    <c:if test="${ sttemntVO.pthmode eq 'C' }">
                        <a href="#" onclick="fnDelete();" class="schbtn edit">삭제</a>
                    </c:if>
                </c:if>
                <c:if test="${sessionScope.userinfo.REQ_USER_GRP != 'ROLE_ADMIN'}">
                    <c:if test="${matchDept == 'm'}">
                        <a href="#" onclick="fnUpdate();" class="schbtn edit">보수결과입력</a>
                        <c:if test="${ sttemntVO.pthmode eq 'C' }">
	                        <a href="#" onclick="fnDelete();" class="schbtn edit">삭제</a>
	                    </c:if>
                    </c:if>
                </c:if>
                <a href="#" onclick="fnClose();" class="schbtn">닫기</a>
            </div>
        </div>
    </div>

    <%@ include file="/include/common.jsp" %>


<script type="text/javascript"  charset="utf-8">

//지도 저장 툴
var gSaveTool = null;
gSaveTool = new GSaveTool(parent.gMap);

var rootPath = '${file_path}' ;
var lcdoPath = '';
var imgPath = '${file_path}';
var locOnOff;

$(document).ready(function() {

	// 2018.06.07. YYK 보수정보의 초기 이미지 띄움
    $("#show_b_img").attr("src", $("#b_img0").attr("src"));
    $("#show_a_img").attr("src", $("#a_img0").attr("src"));

    // 2018.06.07. YYK 아래 이미지 클릭시 위 이미지 변경
    $(".show_bfe_img img").click(function(e){
    	$("#show_b_img").attr("src", $(this).attr("src"));
    })
    $(".show_aft_img img").click(function(e){
        $("#show_a_img").attr("src", $(this).attr("src"));
    })


    for( var i=0; i<4; i++){
    	if( $(".show_bfe_img img:eq("+i+")").attr("src") == "" ){
            $(".show_bfe_img img:eq("+i+")").css("display", "none");
        }
    	if( $(".show_aft_img img:eq("+i+")").attr("src") == "" ){
            $(".show_aft_img img:eq("+i+")").css("display", "none");
        }
    }

    // 2018. 04. 24. JOY. 위치조회 View에서
    var pthRgNo = cvtPthRgNo("${sttemntVO.PTH_RG_NO}");

    var tables = ["MV_POTHOLE_STTEMNT"];
    var fields = ["PTH_RG_NO"];
    var values = [pthRgNo];
    var attribute = {
            attributes : {
                fillColor : '#ff0000',
                strokeColor : '#ff0000'
            }
    };

    // default Action
    parent.bottomClose();

    if ( "${sttemntVO.PRCS_STTUS}" != "PRCS0007" ) {

	    // 신고현황 마커 위치 변경
	    var sLyr = parent.gMap.getLayerByName("GStatusLayer");
	    var sFts = sLyr.features;

	    for ( var i = 0; i < sFts.length; i++ ) {

	        if  ( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(sFts[i].data.pthRgNo) ) {

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

	        if  ( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(tFts[i].data.pthRgNo) ) {

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

	        if  ( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(ssFts[i].data.pthRgNo) ) {

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

	        if  ( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(stFts[i].data.pthRgNo) ) {

	            stFts[i].geometry.x = "${sttemntVO.TM_X}";
	            stFts[i].geometry.y = "${sttemntVO.TM_Y}";

	            break;

	        }

	    }

	    // 2018. 03. 02. JOY. 중복신고건 피처 추가
	    var ovl_pthRgNo;
	    var ovl_tmX;
	    var ovl_tmY;
	    var ovl_angle;
	    var ovl_dplct;

	    <c:forEach items="${overlapList}" var="ovl">

	    ovl_pthRgNo = '${ovl.PTH_RG_NO}';
	    ovl_tmX     = '${ovl.TM_X}';
	    ovl_tmY     = '${ovl.TM_Y}';
	    ovl_angle   = '${ovl.HEADG}';
	    ovl_dplct   = '${ovl.DPLCT_STTEMNT_AT}';

	    parent.gMap.getLayerByName("GOverlapLayer").addFeatures(
	            new OpenLayers.Feature.Vector(
	                    new OpenLayers.Geometry.Point( ovl_tmX, ovl_tmY )
	                    , {
	                        angle       : ovl_angle
	                        , pthRgNo   : ovl_pthRgNo
	                        , data      : ovl_dplct
	                    }
	            )
	    );

	    </c:forEach>

    }

    //if ('${sflag}' == 'Y') {
/*
        // 2018.03.25. YYK. 신고정보(처리상태) 수정 후 해당하는 신고정보 마커 띄움
        var sttemnt = '${sttemntVO.PRCS_STTUS}' ;

        var sttemnt_pthRgNo = '${sttemntVO.PTH_RG_NO}' ;
        sttemnt_pthRgNo = sttemnt_pthRgNo.substring(0, 8) + pad(sttemnt_pthRgNo.substring(9), 6)

        if ( sttemnt != '' ) {
            var SttemntLayer = parent.gMap.getLayerByName("SttemntLayer");

            for( var i=0; i < SttemntLayer.features.length; i++) {
                if ( SttemntLayer.features[i].attributes.pthRgNo == sttemnt_pthRgNo ) {
                    SttemntLayer.features[i].attributes.image = sttemnt ;
                }
            }
            SttemntLayer.redraw()
        }


        // 2018.03.25. YYK. 파손유형수정 후 해당하는 파손유형 마커 띄움
        var dmgt = '${rpairVO.DMG_TYPE}' ;
        var dmgt_pthRgNo = '${sttemntVO.PTH_RG_NO}' ;
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
                                    , angle     : headG
                                    , pthRgNo   : dmgt_pthRgNo
                                }
                        )
                );
            }
            DmgtLayer.redraw()
        }
         */
   // }

    // 이미지 표출 여부
    var mngno = $("#RPRDTLS_MNG_NO").val();
    var fileinfo1 = "${rpairVO.RPAIR_BFE_PHOTO}";
    var fileinfo2 = "${rpairVO.RPAIR_AFT_PHOTO}";

    if ( mngno != null && mngno != undefined && mngno != '' ) {
        if ( fileinfo1 != null && fileinfo1 != undefined && fileinfo1 != '' ) {
            $(".show_bfe_img img").parent().css("display", "block");
        }

        if ( fileinfo2 != null && fileinfo2 != undefined && fileinfo2 != '' ) {
            $(".show_aft_img img").parent().css("display", "block");
        }
    }

    // 리로드
    parent.$("iframe").get(0).contentWindow.$("#gridArea").trigger("reloadGrid");

    /* 2018.04.11 위치도 숨김처리
    // ============ YYK. 위치도 ===============
    if( $('#LCDO').val() == '' ){

        // 위치도 이미지 삽입 전 레이어 숨김
        if (parent.gMap.baseLayer.setVisibility(true)){
            locOnOff = 'on';
        }

        parent.gMap.baseLayer.setVisibility(false);
        parent.gMap.getLayerByName('GStatusLayer').setVisibility(false);
        parent.gMap.getLayerByName('GTypeLayer').setVisibility(false);
        parent.gMap.getLayerByName('SttemntLayer').setVisibility(false);
        parent.gMap.getLayerByName('DmgtLayer').setVisibility(false);

        // 2018. 03. 12. YYK. 위치도
        fn_createMapImage(function(_oRes){
            var imgId = "loc_view_img";
            var imgWidth = 6000;
            var imgHeight = $("#loc_view").height();

            var printViewImg = $("<img id='"+imgId +"'/>")[0];
            printViewImg.style.width = imgWidth + "px";
            printViewImg.style.height = imgHeight + "px";
            printViewImg.src = _oRes;

            document.getElementById("loc_view").appendChild(printViewImg);
        },$("#loc_view").width(),$("#loc_view").height());
    }

    // ============ YYK. 위치도 끝 ===============
     */

    // 2018. 03. 07. JOY. 마커 생성 및 필터링
    //fnShowMarker();
});

function pad(n, width) {
    n = n + '';
    return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}


function fn_createMapImage(_fCallback,_nWidth,_nHeight) {
    $.ajax({
        type: 'post',
        dataType: 'json',
        data: {datas:encodeURIComponent(fn_encodingMap()),width:_nWidth,height:_nHeight, PTH_RG_NO:$('#PTH_RG_NO').val() },
        url: contextPath + "/sttemnt/saveImageToSttemntView.do",
        async: false,
        success: function(_oRes) {
            //$('#loc_view').append("<img src='"+ _oRes.fileVO.file_COURS + _oRes.fileVO.file_NM + "'>");
/* 2018.04.11 위치도 숨김처리
            $('#loc_view').append("<img id='lcdo_view' src=" + imgPath + _oRes.fileVO.file_COURS + _oRes.fileVO.file_NM + " style='width:550px; height: 300px;'>");
            lcdoPath =  _oRes.fileVO.file_COURS + _oRes.fileVO.file_NM ;

            // 위치도 이미지 삽입 후 레이어 보임
            if (locOnOff == 'on'){
                parent.gMap.baseLayer.setVisibility(true);
            }
 */
            parent.gMap.getLayerByName('GStatusLayer').setVisibility(true);
            parent.gMap.getLayerByName('GTypeLayer').setVisibility(true);
            parent.gMap.getLayerByName('SttemntLayer').setVisibility(true);
            parent.gMap.getLayerByName('DmgtLayer').setVisibility(true);
        }
    });
}

/* 위치도
function fn_createMapBase64Image(_fCallback,_nWidth,_nHeight) {
    $.ajax({
        type: 'post',
        dataType: 'json',
        data: {datas:encodeURIComponent(fn_encodingMap()),width:_nWidth,height:_nHeight},
        url: contextPath + "/sttemnt/saveImageToSttemntView.do",
        async: false,
        success: function(_oRes) {
            if(typeof _fCallback == 'function') {
                _fCallback(_oRes.base64);
            }
        }
    });
}
 */
function fn_encodingMap(){
    var gData = null;
/*
    var oTempDiv = parent.document.createElement("div");
    oTempDiv.style.visibility = "hidden";
    oTempDiv.style.height = $(parent.gMap.div).height() + "px";
    oTempDiv.style.width = $(parent.gMap.div).width() + "px";

    var oTempMap = parent.daumMap.getStaticMap(oTempDiv);

    if($("#mapType").val() == "skyView"){
        oTempMap.setMapTypeId(parent.daum.maps.MapTypeId.SKYVIEW);
    }

    sTileMapUrl = $(oTempDiv).find("img").attr("src");
    sTileMapUrl = sTileMapUrl.replace("IW=0", "IW=" + $(parent.gMap.div).width());
    sTileMapUrl = sTileMapUrl.replace("IH=0", "IH=" + $(parent.gMap.div).height());
    $(oTempMap).remove();
 */
    gData = gSaveTool.getXML({
        /*
        type : 'daum',
        url : sTileMapUrl
         */
    });

    return gData;
}

/* 위치도
function fnShowMarker() {

    var pthRgNo = "${sttemntVO.PTH_RG_NO}";
    var tmX     = "${sttemntVO.TM_X}";
    var tmY     = "${sttemntVO.TM_Y}";
    var angle   = "${sttemntVO.HEADG}";
    var dplct   = "${sttemntVO.DPLCT_STTEMNT_AT}";

    var GOverlapLayer = parent.gMap.getLayerByName("GOverlapLayer");

    // 레이어 초기화
    GOverlapLayer.destroyFeatures();

    // features
    GOverlapLayer.addFeatures(
            new OpenLayers.Feature.Vector(
                    new OpenLayers.Geometry.Point( tmX, tmY )
                    , {
                        angle       : angle
                        , pthRgNo   : pthRgNo
                        , data      : dplct
                    }
            )
    );

    <c:forEach items="${overlapList}" var="ovl">

    var ovl_pthRgNo = '${ovl.PTH_RG_NO}';
    var ovl_tmX     = '${ovl.TM_X}';
    var ovl_tmY     = '${ovl.TM_Y}';
    var ovl_angle   = '${ovl.HEADG}';
    var ovl_dplct   = '${ovl.DPLCT_STTEMNT_AT}';

    GOverlapLayer.addFeatures(
            new OpenLayers.Feature.Vector(
                    new OpenLayers.Geometry.Point( ovl_tmX, ovl_tmY )
                    , {
                        angle       : ovl_angle
                        , pthRgNo   : ovl_pthRgNo
                        , data      : ovl_dplct
                    }
            )
    );

    </c:forEach>

    GOverlapLayer.redraw();

    fnSetFilter();
}
 */

 // 중복 신고 마커 그리기
function fnSetFilter() {

    var GOverlapLayer = parent.gMap.getLayerByName("GOverlapLayer");
    GOverlapLayer.setVisibility(true);

    var ruleArr = [];
    var layerStyle = new OpenLayers.Style(null);

    // 중복 신고건 마커
    if ( $("#overlap").hasClass("on") ) {

        ruleArr.push(MAP.overlapStyle[1]);

    }

    // 현재 신고건 마커
    ruleArr.push(MAP.overlapStyle[0]);
    layerStyle.addRules(ruleArr);

    GOverlapLayer.options.styleMap.styles.default = layerStyle;

    GOverlapLayer.redraw();
}


// YYK. 엑셀 출력
function fnPrintScreens() {

    if (confirm("엑셀파일로 저장하시겠습니까?")){

        var frm = $('#frm').cmSerializeObject();
        var frm2 = $('#frm2').cmSerializeObject();
        var postData = $.extend({}, frm, frm2);
/* 위치도
        // 위치도 이미지 첨부
        var a = $('#loc_view_img').attr('src');
        var b = a.indexOf(",");
        var c = a.substring(b+1);

        $("#LOC_VIEW").val(c ) ;
*/

        var b_imgName = [] ;
        var a_imgName = [] ;
        var b_imgPath = [] ;
        var a_imgPath = [] ;

        b_imgName[0] = '${bfe_file_info0.FILE_NM}' ;
        b_imgName[1] = '${bfe_file_info1.FILE_NM}' ;
        b_imgName[2] = '${bfe_file_info2.FILE_NM}' ;
        b_imgName[3] = '${bfe_file_info3.FILE_NM}' ;

        a_imgName[0] = '${aft_file_info0.FILE_NM}' ;
        a_imgName[1] = '${aft_file_info1.FILE_NM}' ;
        a_imgName[2] = '${aft_file_info2.FILE_NM}' ;
        a_imgName[3] = '${aft_file_info3.FILE_NM}' ;

        if ( !(b_imgName[0] == null) && !(b_imgName[0] == "") && !(b_imgName[0] == undefined) ) {
        	b_imgPath[0] = '${fileRealPath}${bfe_file_info0.FILE_COURS}/${bfe_file_info0.FILE_NM}';
        }
        if ( !(b_imgName[1] == null) && !(b_imgName[1] == "") && !(b_imgName[1] == undefined) ) {
        	b_imgPath[1] = '${fileRealPath}${bfe_file_info1.FILE_COURS}/${bfe_file_info1.FILE_NM}';
        }
		if ( !(b_imgName[2] == null) && !(b_imgName[2] == "") && !(b_imgName[2] == undefined) ) {
			b_imgPath[2] = '${fileRealPath}${bfe_file_info2.FILE_COURS}/${bfe_file_info2.FILE_NM}';
		}
	    if ( !(b_imgName[3] == null) && !(b_imgName[3] == "") && !(b_imgName[3] == undefined) ) {
	    	b_imgPath[3] = '${fileRealPath}${bfe_file_info3.FILE_COURS}/${bfe_file_info3.FILE_NM}';
        }

		if ( !(b_imgName[0] == null) && !(b_imgName[0] == "") && !(b_imgName[0] == undefined) ) {
	        a_imgPath[0] = '${fileRealPath}${aft_file_info0.FILE_COURS}/${aft_file_info0.FILE_NM}';
		}
		if ( !(b_imgName[1] == null) && !(b_imgName[1] == "") && !(b_imgName[1] == undefined) ) {
            a_imgPath[1] = '${fileRealPath}${aft_file_info1.FILE_COURS}/${aft_file_info1.FILE_NM}';
		}
		if ( !(b_imgName[2] == null) && !(b_imgName[2] == "") && !(b_imgName[2] == undefined) ) {
			a_imgPath[2] = '${fileRealPath}${aft_file_info2.FILE_COURS}/${aft_file_info2.FILE_NM}';
		}
		if ( !(b_imgName[3] == null) && !(b_imgName[3] == "") && !(b_imgName[3] == undefined) ) {
			a_imgPath[3] = '${fileRealPath}${aft_file_info3.FILE_COURS}/${aft_file_info3.FILE_NM}';
		}



        // img 이미지 경로 추가
        //var lcdoImgPath = '${fileRealPath}${file_info1.FILE_COURS}/${file_info1.FILE_NM}';

        //var aa = $("#lcdo_view").attr('src').replace(rootPath, '');

        /*  2018.04.11 위치도 숨김처리
        var lcdoImgPath = '${fileRealPath}' + $("#lcdo_view").attr('src').replace(rootPath, '');

        if ( lcdoImgPath !== ""  ){
            $("#LOC_VIEW").val(lcdoImgPath) ;
        }
         */

       	if ( b_imgName.length != 0 ){
       	    $("#RPAIR_BFE_PHOTO_PATH").val(b_imgPath);
       	}
       	if ( a_imgName.length != 0 ){
       	    $("#RPAIR_AFT_PHOTO_PATH").val(a_imgPath);
        }

/*         if ( img1Name !== ""  ){
            $("#RPAIR_BFE_PHOTO_PATH").val(img1Path);
        }
        if ( img2Name !== "" ){
            $("#RPAIR_AFT_PHOTO_PATH").val(img2Path);
        }
 */
        // 조회페이지 엑셀 출력
        COMMON_UTIL.cmFormSubmit("goExcel", "proc_frm", "<c:url value='/api/sttemnt/sttemntViewExcel.do'/>", "");

    }
}


// 수정 페이지
function fnUpdate() {

    $("body").addClass("edit");
    var PTH_RG_NO = $("#PTH_RG_NO").val();

    var wndId = $("#wnd_id").val();
    var wnd = parent.$.window.getAll();

    /* parent.COMMON_UTIL.cmWindowOpen('포트홀 신고정보 상세조회', "<c:url value='/pothole/sttemnt/selectSttemntUpdate.do'/>?PTH_RG_NO=" + PTH_RG_NO, 600, 1200, false, null, 'left');

    for ( var i = 0; i < wnd.length; i++ ) {

        if ( wnd[i].getWindowId() == wndId ) {

            wnd[i].close();

        }

    } */

    // 접수경로 구분값 추가
    var urlParam = "&pthmode=" + $("#pthmode").val();

    COMMON_UTIL.cmMoveUrl('pothole/sttemnt/selectSttemntUpdate.do?PTH_RG_NO=' + PTH_RG_NO + urlParam);

}

//삭제
function fnDelete() {

	var statusflag = "${sttemntVO.PRCS_STTUS}";
    var typeflag = "${rpairVO.DMG_TYPE}";
    var pthRgNo = "${sttemntVO.PTH_RG_NO}";
    var pthmode = $("#pthmode").val();
    var imode = $("#imode").val();
    var MGG_ID = "${sttemntVO.GG_ID}";

	if ( confirm("신고 정보 및 보수정보가 모두 삭제됩니다.\r\n삭제하시겠습니까?") ) {

        var postData = { "PTH_RG_NO" : pthRgNo
        		       , "RPRDTLS_MNG_NO" : $("#RPRDTLS_MNG_NO").val()
                       , "pthmode" : pthmode
        };

        $.ajax({
            url: contextPath + 'api/pothole/sttemnt/deletePotholeSttemntAll.do'
            , type: 'post'
            , dataType: 'json'
            , contentType : 'application/json; charset=UTF-8'
            , data : JSON.stringify(postData)
            , success : function(res) {

                if ( res != 1 ) {
                    alert("삭제 실패하였습니다.");
                    return;
                }

                //위치 정보 삭제하지 않음.
                if(MGG_ID != "") {

                    //MAP.fn_del_feature("PTH_CTSMNT",MGG_ID);
                    //parent.fnSelectLayer();

                    //parent.gMap.activeControls("drag");
                    //parent.gMap.getLayerByName('SttemntLayer').removeAllFeatures();
                    //parent.gMap.getLayerByName('DmgtLayer').removeAllFeatures();

                }

                alert( "삭제되었습니다.");

                if(imode != "ADD"){

                    parent.bottomOpen();
                }

                window.parent.$("iframe").get(0).contentWindow.fn_btnSchClick();

                var wnd_id = $("#wnd_id").val();
                COMMON_UTIL.cmWindowClose(wnd_id);

            }
        });
    }
}




// 팝업 닫기
function fnClose() {

    var wnd_id = $("#wnd_id").val();
    var imode = $("#imode").val();

    if(imode != "ADD") {
        parent.bottomOpen();
    }

    COMMON_UTIL.cmWindowClose(wnd_id);

}

// SMS전송
function fnSendSMS(pthRgNo) {

    if ( "${sttemntVO.CHARGER_NM}" == null || "${sttemntVO.CHARGER_NM}" == "" || "${sttemntVO.CHARGER_NM}" == undefined ) {

        alert("담당자가 없습니다.");
        return;

    }

    if ( "${sttemntVO.CTTPC}" == null || "${sttemntVO.CTTPC}" == "" || "${sttemntVO.CTTPC}" == undefined ) {

        alert("연락처가 없습니다.");
        return;

    }


    if ( confirm("SMS 전송 시 담당자에게 신고가 접수됩니다.\n${sttemntVO.LOWEST_DEPT_NM} 담당자에게 SMS를 전송하시겠습니까?") ) {

        var postData = {"PTH_RG_NO" : "${sttemntVO.PTH_RG_NO}", "STTEMNT_DT" : "${sttemntVO.STTEMNT_DT}", "LNM_ADRES" : "${sttemntVO.LNM_ADRES}", "LOWEST_DEPT_NM" : "${sttemntVO.LOWEST_DEPT_NM}", "CHARGER_NM" : "${sttemntVO.CHARGER_NM}", "CTTPC" : "${sttemntVO.CTTPC}"};

        $.ajax({
            url : contextPath + 'api/sttemnt/sendSMS.do'
            , type : 'post'
            , dataType : 'json'
            , contentType : 'application/json; charset=UTF-8'
            , data : JSON.stringify(postData)
            , success : function(jdata) {
                if ( jdata.msg == "success" ) {

                    $("#PRCS_STTUS > input").eq(1).attr("checked", "checked")
                    $("#PRCS_STTUS > span").css("color", "");
                    $("#PRCS_STTUS > span").eq(1).css("color", "red");

                    $("#PRCS_STTUS_NM").val("접수");

                    // 2018. 04. 23. JOY. SMS 전송 후 마커 색상 및 현황 카운트 변경
                    var lyr = parent.gMap.getLayerByName("GStatusLayer");
                    var fts = lyr.features;

                    for ( var i = 0; i < fts.length; i++ ) {

                        if  ( "${sttemntVO.PTH_RG_NO}" == cvtPthRgNo(fts[i].data.pthRgNo) ) {

                            fts[i].data.data = $("input:radio[name=PRCS_STTUS]:checked").val();
                            fts[i].data.allData.PRCS_STTUS = $("input:radio[name=PRCS_STTUS]:checked").val();
                            fts[i].attributes.data = $("input:radio[name=PRCS_STTUS]:checked").val();
                            fts[i].attributes.allData.PRCS_STTUS = $("input:radio[name=PRCS_STTUS]:checked").val();

                            var bfStus = "${sttemntVO.PRCS_STTUS}".substring(7, 8);
                            var afStus = $("input:radio[name=PRCS_STTUS]:checked").val().substring(7, 8);
                            parent.$("#PRCS_STTUS" + bfStus).text( COMMON_UTIL.cmAddComma( parseInt( parent.$("#PRCS_STTUS" + bfStus).text().replace(",", "") ) - 1 ) );
                            parent.$("#PRCS_STTUS" + afStus).text( COMMON_UTIL.cmAddComma( parseInt( parent.$("#PRCS_STTUS" + afStus).text().replace(",", "") ) + 1 ) );

                            break;

                        }

                    }

                    lyr.redraw();

                    $("#smssend").hide();
                    window.parent.$("iframe").get(0).contentWindow.fn_search();

                    alert("SMS 전송을 완료하였습니다.");
                    return;

                } else {

                    alert("SMS 전송을 실패하였습니다.");
                    return;

                }
            }
            , error : function(error) {

                alert(error);
                return;

            }
        });

    }

}

// 중복 건 수 조회
function fnShowOverlap(obj, pthRgNo) {

    if ( obj.hasClass("on") ) {

        // on -> off
        obj.css("background-color", "gray");
        obj.removeClass("on");
        obj.text("중복신고 위치조회");

    } else {

        if ( "${overlapCnt}" == 0 ) {

            alert("중복 건수가 없습니다.");
            return;

        }

        // off -> on
        obj.css("background-color", "#4587ff");
        obj.addClass("on");
        obj.text("중복신고 제외조회");

    }

    fnSetFilter();

}

//국토부 포트홀 신고건 사진조회 팝업
function fn_PthPhotoPop(num){
	var photo = "";

	if(num == '0'){
		photo = $("#pth_photo0").val();
	} else if(num == '1'){
		photo = $("#pth_photo1").val();
	} else if(num == '2'){
		photo = $("#pth_photo2").val();
	}

	COMMON_UTIL.noticeWindowOpen('국토부신고앱 사진조회', "<c:url value='/pothole/sttemnt/sttemntPthMPhoto.do'/>?pth_photo="+photo, 800, 700, true, null, 'center');
}

    </script>

</body>
</html>

