<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%
 /**
  * @Class Name : ClCodeRegister.jsp
  * @Description : ClCode Register 화면
  * @Modification Information
  * 
  * @author leehb1592@gmail.com
  * @since 2017-05-25
  * @version 1.0
  * @see
  *  
  * Copyright (C) All right reserved.
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>
<script type="text/javaScript" language="javascript" defer="defer">

</script>
<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
</head>

<body class="cu">
<div class="tabcont">
	<!-- Content -->
	<div class="content">
		<div class="mt10 ml10 mr10">
			<form id="frm" name="frm" method="post" action="">
				<!-- 필수 파라메터(START) -->
				<input type="hidden" id="action_flag" name="action_flag" value="<c:out value="${action_flag}"/>"/>
				<input type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
				<input type="hidden" id="opener_id" name="opener_id" value=""/>
				<input type="hidden" id="wnd_id" name="wnd_id" value=""/>
				<!-- 필수 파라메터(END) --> 
				<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="${cntrwkDtlVO.CNTRWK_ID}"/>
				<input type="hidden" id="PAV_CELL_ID" name="PAV_CELL_ID" value=""/>
				<form:form commandName="searchVO" id="cntrwkdtl_Regist-form">
					<div class="titbx">			
						<h4>기본정보</h4>
				        <table class="tbview" summary="포장 세부공사 기본정보를 조회한다.">
				            <caption>포장 세부공사 기본정보</caption>
				            <colgroup>
				                <col width="20%" />
				                <col width="30%" />
				                <col width="20%" />
				                <col width="30%" />
				            </colgroup>
				            <tbody>
				            	<tr style="display: none;">
									<th id="a2" >세부공사ID</th>
									<td colspan="3" >
										<form:input path="DETAIL_CNTRWK_ID" maxLength="30" style="width:20px;" cssClass="iptxt" />
											<form:errors path="DETAIL_CNTRWK_ID" />
									</td>
								</tr>
				            	<tr>
									<th scope="row">착공일 ~ 준공일<span class="fcred"> *</span></th>
									<td colspan="3">
										<form:input path="RPAIR_BEGIN_DE" style="margin-right: 5px;" class="DT_DATE input"/>
										<form:errors path="RPAIR_BEGIN_DE" />
										~
										<form:input path="RPAIR_END_DE" style="margin: 0px 5px;" class="DT_DATE input"/>
										<form:errors path="RPAIR_END_DE" />
									</td>
								</tr>
								<tr>							
									<th scope="row">시공사</th>
									<td>
										<label for="CNSTRCT_CO_NO"></label>
										<input type="hidden" name="CNSTRCT_CO_NM" id="CNSTRCT_CO_NM" value="${cntrwkDtlVO.CNSTRCT_CO_NM }"  maxLength="22"  />
										<select name="CNSTRCT_CO_NO" id="CNSTRCT_CO_NO" onchange="fnChangeCompany('CNSTRCT_CO')" class="select" style="width: 100%;">
											<option value="">== 전체 ==</option>
											<c:forEach var="company" items="${companyList}">
											<option value="${company.CO_NO}" <c:if test = "${company.CO_NO == cntrwkDtlVO.CNSTRCT_CO_NO}"> selected="selected" </c:if> >${company.CO_NM}</option>
											</c:forEach>
										</select>
									</td>
									<th scope="row">도급비(천원)</th>
									<td>
										<form:input path="OUTSRCCT" maxLength="100" style="width:96%;" class="MX_10 CS_10 DT_INT" />
										<form:errors path="OUTSRCCT" /> 
									</td>
									<%-- <th scope="row">감리사</th>
									<td>
										<label for="SPRVISN_CO_NO"></label>
										<input type="hidden" name="SPRVISN_CO_NM" id="SPRVISN_CO_NM" value="${cntrwkDtlVO.SPRVISN_CO_NM }" maxLength="22"  />
										<select name="SPRVISN_CO_NO" id="SPRVISN_CO_NO" onchange="fnChangeCompany('SPRVISN_CO')" class="select">
											<option value="">선택</option>
											<c:forEach var="company" items="${companyList}">
											<option value="${company.CO_NO}" <c:if test = "${company.CO_NO == cntrwkDtlVO.SPRVISN_CO_NO}"> selected="selected" </c:if> >${company.CO_NM}</option>
											</c:forEach>
										</select>
									</td> --%>
								</tr>
								<tr>
									<th scope="row">시공사 대표자</th>
									<td>
										<form:input path="CNSTRCT_CO_RPRSNTV_NM" maxLength="100" style="width:96%;" cssClass="iptxt" />
										<form:errors path="CNSTRCT_CO_RPRSNTV_NM" />
									</td>
									<th scope="row">관급비(천원)</th>
									<td>
										<form:input path="GVSLCT" maxLength="100" style="width:96%;" class="MX_10 CS_10 DT_INT" />
										<form:errors path="GVSLCT" />
									</td>
									<%-- <th scope="row">감리사 대표자</th>
									<td>
										<form:input path="SPRVISN_CO_RPRSNTV_NM" maxLength="100" style="width:150px;" cssClass="iptxt" />
										<form:errors path="SPRVISN_CO_RPRSNTV_NM" />
									</td> --%>
								</tr>
								<tr>							
									<th scope="row">시공사 대표번호</th>
									<td>
										<form:input path="CNSTRCT_CO_TELNO" maxLength="100" style="width:96%;" cssClass="iptxt" />
										<form:errors path="CNSTRCT_CO_TELNO" />
									</td>
									<th scope="row">총 공사비(천원)<span class="fcred"> *</span></th>
									<td>
										<form:input path="CNTRWK_AMOUNT" maxLength="100" style="width:96%;" class="MX_10 CS_10 DT_INT" readonly="true"/>
										<form:errors path="CNTRWK_AMOUNT" />
										<br/><span style="float:right;">(도급비+관급비)</span>
									</td>
									<%-- <th scope="row">감리사 대표번호</th>
									<td>
										<form:input path="SPRVISN_CO_RPRSNT_NO" maxLength="100" style="width:150px;" cssClass="iptxt" />
										<form:errors path="SPRVISN_CO_RPRSNT_NO" />
									</td> --%>
								</tr>
								<%-- <tr>
									<th scope="row">현장소장 성명</th>
									<td>
										<form:input path="SPT_HDCH_NM" maxLength="100" style="width:100px;" cssClass="iptxt" />
										<form:errors path="SPT_HDCH_NM" />
									</td>
									<th scope="row">감리원 성명</th>
									<td>
										<form:input path="SPRVISOR_NM" maxLength="100" style="width:100px;" cssClass="iptxt" />
										<form:errors path="SPRVISOR_NM" />
									</td>
								</tr>
								<tr>
									<th scope="row">현장소장 전화번호</th>
									<td>
										<form:input path="SPT_HDCH_TELNO" maxLength="100" style="width:100px;" cssClass="iptxt" />
										<form:errors path="SPT_HDCH_TELNO" />
									</td>							
									<th scope="row">감리원 전화번호</th>
									<td>
										<form:input path="SPRVISOR_TELNO" maxLength="100" style="width:100px;" cssClass="iptxt" />
										<form:errors path="SPRVISOR_TELNO" />
									</td>
								</tr>
								<tr>
									<th scope="row">도급비(천원)</th>
									<td>
										<form:input path="OUTSRCCT" maxLength="100" style="width:100px;" class="MX_10 CS_10 DT_INT" />
										<form:errors path="OUTSRCCT" /> 
									</td>
									<th scope="row">관급비(천원)</th>
									<td>
										<form:input path="GVSLCT" maxLength="100" style="width:100px;" class="MX_10 CS_10 DT_INT" />
										<form:errors path="GVSLCT" />
									</td>
								</tr>
								<tr>
									<th scope="row">기타 이설비(천원)</th>
									<td>
										<form:input path="ETC_RLOCATCT" maxLength="100" style="width:100px;" class="MX_10 CS_10 DT_INT" />
										<form:errors path="ETC_RLOCATCT" />
										<br/>(폐기물, 차선도색비 제외)
									</td>
									<th scope="row">총 공사비(천원)<span class="fcred"> *</span></th>
									<td>
										<form:input path="CNTRWK_AMOUNT" maxLength="100" style="width:100px;" class="MX_10 CS_10 DT_INT" readonly="true"/>
										<form:errors path="CNTRWK_AMOUNT" />
										<br/>(도급비+관급비+기타이설비)
									</td>
								</tr> --%>
							</tbody>
						</table>
					</div>
					<div class="titbx mt20">
						<h4>위치정보</h3>
				        <table class="tbview" summary="포장 세부공사 위치정보를 조회한다.">
				            <caption>포장 세부공사 위치정보</caption>
				            <colgroup>
				                <col width="20%" />
				                <col width="30%" />
				                <col width="20%" />
				                <col width="30%" />
				            </colgroup>
				            <tbody>
								<tr>
									<th scope="row">세부 공사위치<span class="fcred"> *</span></th>
									<td colspan="3">
										<form:input path="DETAIL_CNTRWK_NM" maxLength="500" cssClass="iptxt" style="width: 98.5%;" />
										<form:errors path="DETAIL_CNTRWK_NM" />
									</td>
								</tr>
								<tr>
									<th scope="row" >(노선번호) 노선명<span class="fcred"> *</span></th>
									<td>
										(
										<form:input path="ROUTE_CODE" maxLength="100" style="width:50px;" class="CS_20 input" onchange="fn_castRouteCode();" hidden="true"/>
										<form:errors path="ROUTE_CODE" />
										<input id="ROUTE_CODE1" name="ROUTE_CODE1" style="width:17%;" class="CS_20 input" readonly="true" />
										)
										<form:input path="ROUTE_NM" maxLength="100" style="float:right; width:60%;" class="CS_20 input" readonly="true" />
										<form:errors path="ROUTE_NM" />
									</td>
									<th scope="row">도로명</th>
									<td>
										<form:input path="ROAD_NM" maxLength="100" style="width:96%;" class="CS_20 input" />
										<form:errors path="ROAD_NM" />
									</td>
								</tr>
								<tr>
									<th scope="row">행선<span class="fcred"> *</span></th>
									<td>
										<form:input path="DIRECT_CODE"  maxLength="100" style="display:none;" />
										<form:input path="DIRECT_NM" maxLength="100" style="width:96%;" cssClass="iptxt" readonly="true"/>
										<form:errors path="DIRECT_NM" />
									</td>
									<th scope="row">공사 차로 수<span class="fcred"> *</span></th>
									<td>
										<form:input path="TRACK" maxLength="1" style="width:50px;" class="CS_10 input" readonly="true" />
										<form:errors path="TRACK" /> 차로
									</td>
								</tr>
								<tr>
									<th scope="row">보수시점(m)<span class="fcred"> *</span></th>
									<td>
										<form:input path="STRTPT" maxLength="100" style="width:96%;" class="MX_8 CS_8 DT_INT input" />
										<form:errors path="STRTPT" />
									</td>
									<th scope="row">보수종점(m)<span class="fcred"> *</span></th>
									<td>
										<form:input path="ENDPT" maxLength="100" style="width:96%;" class="MX_8 CS_8 DT_INT input" />
										<form:errors path="ENDPT" />
									</td>
								</tr>
								<tr>
									<th scope="row">공사연장(m)<span class="fcred"> *</span></th>
									<td>
										<form:input path="TRACK_LEN" maxLength="100" style="width:96%;" class="MX_8 CS_8 DT_INT input" />
										<form:errors path="TRACK_LEN" />
									</td>
									<th scope="row">공사폭(m)<span class="fcred"> *</span></th>
									<td>
										<form:input path="RPAIR_BT" maxLength="100" style="width:96%;" class="MX_5 DD_2 CS_5 DT_FLOAT input"/>
										<form:errors path="RPAIR_BT" />
									</td>
								</tr>							
								<tr>
									<th scope="row">공사면적(㎡)<span class="fcred"> *</span></th>
									<td>
										<form:input path="RPAIR_AR" maxLength="100" style="width:96%;" class="MX_10 DD_4 CS_10 DT_FLOAT input" />
										<form:errors path="RPAIR_AR" />
									</td>
									<th scope="row">포장공법<span class="fcred"> *</span></th>
									<td>
										<select name="RPAIR_MTHD_CODE" id="RPAIR_MTHD_CODE" class="select" onchange="COMMON_UTIL.fn_show_etcBox('RPIR0000', 'RPAIR_MTHD_CODE', 'PAV_MSRC_ETC')" style="width: 100%;">
											<option value="">== 전체 ==</option>
											<c:forEach var="selectData" items="${RpairMthds}">
											<option value="${selectData.RPAIR_MTHD_CODE}" <c:if test = "${selectData.RPAIR_MTHD_CODE == cntrwkDtlVO.RPAIR_MTHD_CODE}"> selected="selected" </c:if> >${selectData.MSRC_CL_NM}</option>
											</c:forEach>
										</select>
										<input type="text" name="PAV_MSRC_ETC" id="PAV_MSRC_ETC" value="${cntrwkDtlVO.PAV_MSRC_ETC }" class="MX_100 CS_20 input"  style="display: none;"/>
									</td>
								</tr>
								<tr>
									<th scope="row" rowspan="3">포장두께(cm)<span class="fcred"> *</span></th>
									<td>표층　 : 
										<form:input path="RPAIR_THICK_ASCON" maxLength="100" style="width:71%;" class="MX_5 DD_2 CS_5 DT_FLOAT input" />
										<form:errors path="RPAIR_THICK_ASCON" /> 
									</td>
									<th scope="row" rowspan="3">포장재료</th>
									<td>표층<span class="fcred"> *</span> :
										<select name="PAV_MATRL_ASCON_CODE" id="PAV_MATRL_ASCON_CODE" class="select" style="width: 75%;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_ASCON_CODE', 'PAV_MATRL_ASCON_ETC')">
											<option value="">== 전체 ==</option>
											<c:forEach var="selectData" items="${PavMatrls}">
											<option value="${selectData.PAV_MATRL_CODE}" <c:if test = "${selectData.PAV_MATRL_CODE == cntrwkDtlVO.PAV_MATRL_ASCON_CODE}"> selected="selected" </c:if> >${selectData.PAV_MATRL_NM}</option>
											</c:forEach>
										</select>
										<input type="text" name="PAV_MATRL_ASCON_ETC" id="PAV_MATRL_ASCON_ETC" value="${cntrwkDtlVO.PAV_MATRL_ASCON_ETC}" class="MX_100 CS_20 input" style="display: none;" />
									</td>
								</tr>
								<tr>	
									<td>중간층 : 
										<form:input path="RPAIR_THICK_CNTR" maxLength="100" style="width:71%;" class="MX_5 DD_2 CS_5 DT_FLOAT input"/>
										<form:errors path="RPAIR_THICK_CNTR" />
									</td>
									<td>중간층 :
										<select name="PAV_MATRL_CNTR_CODE" id="PAV_MATRL_CNTR_CODE" class="select" style="width: 75%;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_CNTR_CODE', 'PAV_MATRL_CNTR_ETC')">
											<option value="">== 전체 ==</option>
											<c:forEach var="selectData" items="${PavMatrls}">
											<option value="${selectData.PAV_MATRL_CODE}" <c:if test = "${selectData.PAV_MATRL_CODE == cntrwkDtlVO.PAV_MATRL_CNTR_CODE}"> selected="selected" </c:if> >${selectData.PAV_MATRL_NM}</option>
											</c:forEach>
										</select>
										<input type="text" name="PAV_MATRL_CNTR_ETC" id="PAV_MATRL_CNTR_ETC" value="${cntrwkDtlVO.PAV_MATRL_CNTR_ETC}" class="MX_100 CS_20 input" style="display: none;"/>
									</td>
								</tr>
								<tr>
									<td>기층　 :
										<form:input path="RPAIR_THICK_BASE" maxLength="100" style="width:71%;" class="MX_5 DD_2 CS_5 DT_FLOAT input" />
										<form:errors path="RPAIR_THICK_BASE" />
									</td>
									<td>기층　 :
										<select name="PAV_MATRL_BASE_CODE" id="PAV_MATRL_BASE_CODE" class="select" style="width: 75%;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_BASE_CODE', 'PAV_MATRL_BASE_ETC')">
											<option value="">== 전체 ==</option>
											<c:forEach var="selectData" items="${PavMatrls}">
											<option value="${selectData.PAV_MATRL_CODE}" <c:if test = "${selectData.PAV_MATRL_CODE == cntrwkDtlVO.PAV_MATRL_BASE_CODE}"> selected="selected" </c:if> >${selectData.PAV_MATRL_NM}</option>
											</c:forEach>
										</select>
										<input type="text" name="PAV_MATRL_BASE_ETC" id="PAV_MATRL_BASE_ETC" value="${cntrwkDtlVO.PAV_MATRL_BASE_ETC}" class="MX_100 CS_20 input"  style="display: none;"/>
									</td>
								</tr>
							</tbody>
						</table>
					</h4>
					<%-- <div class="titbx mt20">
						<h4>도색물량 총괄</h4>
				        <table class="tbview" summary="포장 세부공사 도색물량 총괄을 조회한다.">
				            <caption>포장 세부공사 도색물량 총괄</caption>
				            <colgroup>
				                <col width="15%" />
				                <col width="35%" />
				                <col width="15%" />
				                <col width="35%" />
				            </colgroup>
				            <tbody>
				            	<tr>
									<th scope="row">황색실선(㎡)</th>
									<td><input type="text" name="PAINT_YLWSLLN_AR" id="PAINT_YLWSLLN_AR" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_YLWSLLN_AR}"/>" class="MX_5 DD_4 CS_5 DT_FLOAT input" placeholder="예) 5.5" /></td>
									<th scope="row">백색실선(㎡)</th>
									<td><input type="text" name="PAINT_WHSLLN_AR" id="PAINT_WHSLLN_AR" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_WHSLLN_AR}"/>" class="MX_5 DD_4 CS_5 DT_FLOAT input" placeholder="예) 5.5"/></td>
								</tr>
								<tr>
									<th scope="row">황색파선(㎡)</th>
									<td><input type="text" name="PAINT_YLWDASHLN_AR" id="PAINT_YLWDASHLN_AR" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_YLWDASHLN_AR}"/>" class="MX_5 DD_4 CS_5 DT_FLOAT input" placeholder="예) 5.5" /></td>
									<th scope="row">백색파선(㎡)</th>
									<td><input type="text" name="PAINT_WHDASHLN_AR" id="PAINT_WHDASHLN_AR" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_WHDASHLN_AR}"/>" class="MX_5 DD_4 CS_5 DT_FLOAT input" placeholder="예) 5.5" /></td>
								</tr>
								<tr>
									<th scope="row" rowspan="2">횡단보도</th>
									<td>개소　 :
										<input type="text" name="PAINT_CRSLK_CO" id="PAINT_CRSLK_CO" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_CRSLK_CO}"/>" class="MX_5 CS_5 DT_INT input" placeholder="예) 5"/>
									</td>
									<th scope="row" rowspan="2">방지턱</th>
									<td>개소　 :
										<input type="text" name="PAINT_SPDBMP_CO" id="PAINT_SPDBMP_CO" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_SPDBMP_CO}"/>" class="MX_5 CS_5 DT_INT input" placeholder="예) 5" />
									</td>
								</tr>
								<tr>
									<td>㎡　 　: <input type="text" name="PAINT_CRSLK_AR" id="PAINT_CRSLK_AR" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_CRSLK_AR}"/>" class="MX_5 DD_4 CS_5 DT_FLOAT input" placeholder="예) 5.5"/></td>
									<td>㎡　 　: <input type="text" name="PAINT_SPDBMP_AR" id="PAINT_SPDBMP_AR" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_SPDBMP_AR}"/>" class="MX_5 DD_4 CS_5 DT_FLOAT input" placeholder="예) 5.5"/></td>
								</tr>
								<tr>
									<th scope="row" rowspan="2">정지선</th>
									<td>개소　 :
										<input type="text" name="PAINT_STOPLN_CO" id="PAINT_STOPLN_CO" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_STOPLN_CO}"/>" class="MX_5 CS_5 DT_INT input" placeholder="예) 5"/>
									</td>
									<th scope="row" rowspan="2">문자기호</th>
									<td>개소　 :
										<input type="text" name="PAINT_CHRCTRSYMBL_CO" id="PAINT_CHRCTRSYMBL_CO" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_CHRCTRSYMBL_CO}"/>" class="MX_5 CS_5 DT_INT input" placeholder="예) 5" />
									</td>
								</tr>
								<tr>
									<td>㎡　 　: 
										<input type="text" name="PAINT_STOPLN_AR" id="PAINT_STOPLN_AR" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_STOPLN_AR}"/>" class="MX_5 DD_4 CS_5 DT_FLOAT input" placeholder="예) 5.5"/>
									</td>
									<td>㎡　 　: 
										<input type="text" name="PAINT_CHRCTRSYMBL_AR" id="PAINT_CHRCTRSYMBL_AR" style="width:100px;" value="<c:out value="${cntrwkDtlVO.PAINT_CHRCTRSYMBL_AR}"/>" class="MX_5 DD_4 CS_5 DT_FLOAT input" placeholder="예) 5.5"/>
									</td>
								</tr>
								<tr>
									<th scope="row">차선도색공법</th>
									<td colspan="3">
										<form:input path="PAINT_MSRC" maxLength="150" style="width:120px;" cssClass="iptxt" />
										<form:errors path="PAINT_MSRC" />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="titbx mt20">
						<h4>공사사진</h4>
				        <table class="tbview" summary="포장 세부공사 공사사진을 조회한다.">
				            <caption>포장 세부공사 공사사진</caption>
				            <colgroup>
				                <col width="15%" />
				                <col width="35%" />
				                <col width="15%" />
				                <col width="35%" />
				            </colgroup>
				            <tbody>	
				            	<tr> 
						     	  	<th scope="row" colspan="2" class="tx_center">공사 전 사진</th>
						     	  	<th scope="row" colspan="2" class="tx_center">공사 후 사진</th>
					     	  	</tr>
					     	  	<tr> 
						     	  	<td colspan="2" class="center" style="vertical-align:top;min-height:70px;">
						     	  		<input type="hidden" name="OPERT_BFE_PHOTO_NO" id="OPERT_BFE_PHOTO_NO" value="${cntrwkDtlVO.OPERT_BFE_PHOTO_NO}"/>
						     	  		<c:import url="/attachfile/getfileForm.do" >
											<c:param name="FILE_MODE" value="FILE_EDIT" />
											<c:param name="FILE_COLUMN" value="file_before" />
											<c:param name="FILE_NO" value="${cntrwkDtlVO.OPERT_BFE_PHOTO_NO}" />
											<c:param name="FILE_TYPE" value="IMAGE" />
										</c:import>
						     	  	</td>
						     	  	<td colspan="2" class="center" style="vertical-align:top;min-height:70px;">
						     	  		<input type="hidden" name="OPERT_AFT_PHOTO_NO" id="OPERT_AFT_PHOTO_NO" value="${cntrwkDtlVO.OPERT_AFT_PHOTO_NO}"/>
						     	  		<c:import url="/attachfile/getfileForm.do" >
											<c:param name="FILE_MODE" value="FILE_EDIT" />
											<c:param name="FILE_COLUMN" value="file_after" />
											<c:param name="FILE_NO" value="${cntrwkDtlVO.OPERT_AFT_PHOTO_NO}" />
											<c:param name="FILE_TYPE" value="IMAGE" />
										</c:import>
						     	  	</td>
								</tr>
							</tbody>
						</table>
					</div> --%>
					<div class="titbx mt20">
						<h4>기타</h4>
				        <table class="tbview" summary="포장 세부공사 기타를 조회한다.">
				            <caption>포장 세부공사 기타</caption>
				            <colgroup>
				                <col width="20%" />
				                <col width="30%" />
				                <col width="20%" />
				                <col width="30%" />
				            </colgroup>
				            <tbody>		
								<%-- <tr>
									<th scope="row">공급플랜트<span class="fcred"> *</span></th>
									<td>
										<label for="RPAIR_MATRL_PRDCT_CO_NM"></label>
										<input type="text" name="RPAIR_MATRL_PRDCT_CO_NM" id="RPAIR_MATRL_PRDCT_CO_NM" value="<c:out value="${cntrwkDtlVO.RPAIR_MATRL_PRDCT_CO_NM}"/>" class="MX_50 CS_20 input" placeholder="예) 00아스콘(표층), 00아스콘(기층)"/>
									</td>
									<th scope="row">연락처<span class="fcred"> *</span></th>
									<td>
										<label for="PRDCT_CO_TELNO"></label>
										<input type="text" name="PRDCT_CO_TELNO" id="PRDCT_CO_TELNO" value="<c:out value="${cntrwkDtlVO.PRDCT_CO_TELNO}"/>" class="MX_15 CS_15 input" />
									</td>
								</tr> --%>
								<tr>
									<th scope="row">공사시간</th>
									<td style="text-align: center">
										<label for="CNTRWK_TIME1"></label>
										<input style="margin-left: 5px;" type="radio" name="CNTRWK_TIME" id="CNTRWK_TIME1" value="주간" <c:if test = "${cntrwkDtlVO.CNTRWK_TIME=='주간' || cntrwkDtlVO.CNTRWK_TIME==null}"> checked </c:if> />주간
										<label for="CNTRWK_TIME2" style="margin-left: 15px;"></label>
										<input style="margin-left: 5px;" type="radio" name="CNTRWK_TIME" id="CNTRWK_TIME2" value="야간" <c:if test = "${cntrwkDtlVO.CNTRWK_TIME=='야간'}"> checked </c:if> />야간
									</td>
									<th scope="row">비고</th>
									<td>
										<form:input path="RM" maxLength="100" cssClass="iptxt" />
										<form:errors path="RM" />
									</td>
									<%-- <th scope="row">날씨</th>
									<td>
										<select name="CNTRWK_WETHR" id="CNTRWK_WETHR" class="select">
										<option value="">선택</option>
											<c:forEach var="selectData" items="${codesWETH}">
											<option value="${selectData.CODE_VAL}" <c:if test = "${selectData.CODE_VAL == cntrwkDtlVO.CNTRWK_WETHR}"> selected="selected" </c:if> >${selectData.CODE_NM}</option>
											</c:forEach>
										</select>
									</td> --%>
									
								</tr>
								<%-- <tr>
									<th scope="row">비고</th>
									<td colspan="3">
										<form:input path="RM" maxLength="100" cssClass="iptxt" />
										<form:errors path="RM" />
									</td>
								</tr>
								<tr>
									<th scope="row" id="a1">기타 첨부파일</th>
									<td colspan="3">
										<input type="hidden" name="FILE_NO" id="FILE_NO" value="${cntrwkDtlVO.FILE_NO}"/>
										<c:import url="/attachfile/getfileForm.do" >
											<c:param name="FILE_NO" value="${cntrwkDtlVO.FILE_NO}" />
											<c:param name="FILE_MODE" value="FILE_EDIT" />
											<c:param name="FILE_COLUMN" value="file_no" />
											<c:param name="FILE_TYPE" value="ETC" />
										</c:import>
									</td>
								</tr> --%>
							</tbody>
						</table>
					</div>
				</form:form>
			</form>
			<form id="cellFrm">
				<input type="hidden" id="PAV_CELL_IDS" name="PAV_CELL_IDS" value=""/>
				<input type="hidden" name="DETAIL_CNTRWK_ID" value="${cntrwkDtlVO.DETAIL_CNTRWK_ID}"/>
				<div class="titbx mt20">
					<h4>포장셀 직전포장년도</h4>
					<div style="width: 100%;">
						<div id="div_grid" style="width:100%; height:240px;">
							<table id="gridArea"></table>
							<div id="gridPager"></div>
						</div>
					</div>
				</div>
			</form>		
			<div class="mt10 tc">
				<div class="fr">
					<a href="#" onclick="fn_modify_cell();" class="schbtn" >위치수정</a>
					<a href="#"  class="schbtn"  onclick="check = false; fn_update(); return false;">저장</a>
					<a href="#" onclick="COMMON_UTIL.cmMoveUrl('cntrwkdtl/selectCntrwkDtl.do?DETAIL_CNTRWK_ID=${cntrwkDtlVO.DETAIL_CNTRWK_ID}&CNTRWK_ID=${cntrwkDtlVO.CNTRWK_ID}');" class="graybtn" >취소</a>
					<a href="#" onclick="fn_reset_form();" class="graybtn" >초기화</a>
				</div>
			</div>
		</div>	
	</div>
</div>

<!--For Commons Validator Client Side-->
<!-- script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script -->
<!-- validator:javascript formName="clCodeVO" staticJavascript="false" xhtml="true" cdata="false"/ -->

<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
/* function resize(obj) {
    obj.style.height = "10px";
    obj.style.height = (obj.scrollHeight) + "px";
}
 */
$(document).ready(function(){
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
	
	// 달력 생성
	COMMON_UTIL.cmCreateDatepickerLinked('RPAIR_BEGIN_DE','RPAIR_END_DE', 10);
	
	//도색물량합계
	//fn_paintSum();
	
	// 도급비, 관급비, 기타 이설비 입력시 합계 > 총 공사비
	$('#OUTSRCCT').keyup(function () { fnSumCNTRWK_AMOUNT(); });
	$('#GVSLCT').keyup(function () { fnSumCNTRWK_AMOUNT(); });
	/* $('#ETC_RLOCATCT').keyup(function () { fnSumCNTRWK_AMOUNT(); }); */
	// 공사면적 계산
	$('#TRACK_LEN').keyup(function () { fnSumRPAIR_AR(); });
	$('#RPAIR_BT').keyup(function () { fnSumRPAIR_AR(); });
	
	$('#ROUTE_CODE1').val($('#ROUTE_CODE').val()*1);
	
	COMMON_UTIL.fn_show_etcBox('RPIR0000', 'RPAIR_MTHD_CODE', 'PAV_MSRC_ETC');
	COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_ASCON_CODE', 'PAV_MATRL_ASCON_ETC');
	COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_CNTR_CODE', 'PAV_MATRL_CNTR_ETC');
	COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_BASE_CODE', 'PAV_MATRL_BASE_ETC');
	
	$("#gridArea").jqGrid({
		url: '<c:url value="/"/>'+'api/cntrwkcellinfo/selectPavYearListByCellId.do'
		,autoencode: true
		,contentType : 'application/json'
		,datatype: "local"
		,mtype: "POST"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		//,postData: JSON.stringify( $("#frm").cmSerializeObject()) 
		,postData: $("#cellFrm").cmSerializeObject()
		,ignoreCase: true
		,colNames:["포장셀_관리_ID","포장년도","위치보기"]
	   	,colModel:[
			{name:'PAV_CELL_ID',index:'PAV_CELL_ID', align:'center', width:120, sortable:false}
			,{name:'PAV_YEAR',index:'PAV_YEAR', align:'center', width:70, sortable:false}
			,{name:'btn_loc',index:'btn_loc', align:'center', width:70, sortable:false, formatter: fn_create_btn}
	   	]
		,async : false
	   	,sortname: ''
	    ,sortorder: ""
	   	,rowNum: 50
	   	,rowList: [20,50,100,500]
	    ,viewrecords: true
	   	,pager: '#gridPager'
	    ,rownumbers: true
	    ,loadtext: "검색 중입니다."
		,emptyrecords: "검색된 데이터가 없습니다."
		,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
		,ondblClickRow: function(rowId) {		// 더블클릭 처리
			fn_view(rowId);	// 대장 조회
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
		//,scroll: true
	}).navGrid('#gridPager',{edit:false,add:false,del:false,search:false,refresh:false});
	
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 180);
	
	fn_search();
	
	//fn_tmp('');
	
	var rpairBeginDe = '<c:out value="${cntrwkDtlVO}"/>';
	
	var size = rpairBeginDe.length;
		alert(rpairBeginDe.keys(0));	

});

 
//위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
	var btn = "";
	var nm = options.colModel.name;
	
	switch(nm) {
		case "btn_loc" :
			btn = "<a href='#' onclick=\"fn_select_cell('" + rowObject.PAV_CELL_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
			break;
	}
	
	return btn;
}

function fn_select_cell(cell_id){
	var tables = ["CELL_10"];
	var fields = ["CELL_ID"];
	var values = [cell_id];
	
	// 모든 팝업창 최소화
	parent.wWindowHideAll();
	// 하단 목록 창 내리기
	parent.bottomClose();
	
	var attribute_base = {
	        attributes : {
	            fillColor : '#ffffff',
                strokeColor : '#ffffff'
			}
	};
	
	//MAP.fn_get_selectFeatureByAttr(parent.gMap, tables, fields, values);
	MAP.fn_get_selectFeatureByAttrMulti(parent.gMap, tables, fields, values, null, "AND", attribute_base, true,  0, 1);
}

//검색 처리
function fn_search() {
	
	var postData = {"USE_AT":"Y"};
	$("#gridArea").jqGrid("setGridParam",{
		datatype: "json"
		,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
		,contentType: "application/json"
		,page: 1
		,postData:   $("#cellFrm").cmSerializeObject()
		,mtype: "POST"
	   	,loadComplete: function(data) {
	   		COMMON_UTIL.fn_set_grid_noRowMsg('gridArea', $("#gridArea").jqGrid("getGridParam").emptyrecords, data.records);
	   	}
	}).trigger("reloadGrid");
}

/* 글 등록 function */
function fn_update() {
	
	//위치등록 기능 구현 후 주석 제거
	if(!validateInsert('frm')){return;}
	
	//null check
	if(COMMON_UTIL.fn_check_notnull("tr")) {
		return;	
	}
	
	if( confirm('<spring:message code="warn.update.msg" />') ) {
		// 진행 프로그래스바 생성
		COMMON_UTIL.cmShowProgressBar();
		parent.gMap.cleanMap();	
		
		fn_set_del_file("file_before", "OPERT_BFE_PHOTO_NO");
		fn_set_del_file("file_after", "OPERT_AFT_PHOTO_NO");
		fn_set_del_file("file_no", "FILE_NO");

		COMMON_UTIL.cmFileFormSubmit("frm", "proc_frm", contextPath + "api/cntrwkdtl/updateCntrwkDtl.do", "fn_update_callback");
	}
}

//저장 callback
function fn_update_callback(){
	COMMON_UTIL.cmHideProgressBar();
	var openerId = $("#opener_id").val();
	COMMON_UTIL.cmGetWindowOpener( openerId ).fnSearch();
	/* COMMON_UTIL.cmMovePage( "frm", "<c:url value='/cntrwkdtl/selectCntrwkDtl.do'/>"); */
	COMMON_UTIL.cmMoveUrl('cntrwkdtl/selectCntrwkDtl.do?DETAIL_CNTRWK_ID=${cntrwkDtlVO.DETAIL_CNTRWK_ID}&CNTRWK_ID=${cntrwkDtlVO.CNTRWK_ID}');
}

//파일 삭제 처리
function fn_set_del_file(colNm, inputId){
	
	if($("#DEL_"+colNm).is(":checked")){
		$("#"+inputId).val("");
	}
}

//도색물량합계
function fn_paintSum(){
	var paintSum=0;
	var arr =[$("#PAINT_YLWSLLN_AR").val()
	  		,$("#PAINT_YLWDASHLN_AR").val()
			,$("#PAINT_WHSLLN_AR").val()
			,$("#PAINT_CRSLK_AR").val()
			,$("#PAINT_STOPLN_AR").val()
			,$("#PAINT_SPDBMP_AR").val()
			,$("#PAINT_WHDASHLN_AR").val()
			,$("#PAINT_CHRCTRSYMBL_AR").val()];
	
	for(var i in arr){
		paintSum += Number(arr[i]);
	}
	
	document.getElementById("paintSum").innerHTML = paintSum;
}

//직전포장년도 추출
function fnMaxBssjilja() {
	$('#display_msg1').html('검색중입니다.').blink(10,1000);
	
	$.ajax({
		type: "post"
		,dataType: "json"
		,data: {ROUTE_CODE : $('#ROUTE_CODE').val(), oidList : $('#OID').val()}
		,url: "/dsm/minBssjiljaJson.do"
		,success: function(data) {
			if( data.res==0 ) {
				alert('검색된 포장공사 정보가 없습니다.');
				$('#BEFORE_PAV_YEAR').val( '없음' );
			}
			else
				$('#BEFORE_PAV_YEAR').val( data.res );
			
			$('#display_msg1').html('').blink(0,1000);
		}
		,error: function(a, b, msg) {
			alert('직전포장년도 추출 오류 : ' + msg);
			
			$('#display_msg1').html('').blink(0,1000);
		}
	});
}

//총 공사비 자동 계산 처리
function fnSumCNTRWK_AMOUNT() {
	try {
		var tot = COMMON_UTIL.cmFormatFloat( $('#OUTSRCCT').val() ) + COMMON_UTIL.cmFormatFloat( $('#GVSLCT').val() );// + COMMON_UTIL.cmFormatFloat( $('#ETC_RLOCATCT').val() );
		$('#CNTRWK_AMOUNT').val( tot ).trigger('keyup');
	} catch(E) {}
}

//공사면적 자동 계산 처리
function fnSumRPAIR_AR() {
	try {
		var tot = COMMON_UTIL.cmFormatFloat( $('#TRACK_LEN').val() ) * COMMON_UTIL.cmFormatFloat( $('#RPAIR_BT').val() );
		if( isNaN(tot) )	tot = 0;
		$('#RPAIR_AR').val( tot ).trigger('keyup');
	} catch(E) {alert( E );}
}

//노선변호 String > Integer로 형변환
function fn_castRouteCode(){
	$('#ROUTE_CODE1').val($('#ROUTE_CODE').val()*1);
}

function fnChangeCompany(searchType){
	var co_no = $("#" + searchType + "_NO").val();
	$.ajax({
		url: contextPath + 'api/company/selectCompany.do'
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,data: JSON.stringify({CO_NO : co_no})
		,success: function(data){
			$('#' + searchType + '_NM').val(data.CO_NM);
			$('#' + searchType + '_RPRSNTV_NM').val(data.RPRSNTV_NM);
			$('#' + searchType + '_TELNO').val(data.RPRSNT_TEL_NO);
			$('#' + searchType + '_RPRSNT_NO').val(data.RPRSNT_TEL_NO);
		}
		,error: function(a,b,msg){
		}
	});
}

function validateInsert(frmId){
var vform = $('#'+frmId);
    
    //공사_ID
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CNTRWK_ID').val()))){
        alert("공사_ID에 값을 입력하세요.");
        return false;
    }

    //보수_시작_일자
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPAIR_BEGIN_DE').val()))){
        alert("착공일에 값을 입력하세요.");
        return false;
    }

    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#RPAIR_BEGIN_DE').val()).length>10){
        alert("착공일 값은 최대 10자까지 입력할 수 있습니다.");
        return false;
    }   

    //보수_종료_일자
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPAIR_END_DE').val()))){
        alert("준공일에 값을 입력하세요.");
        return false;
    }

    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#RPAIR_END_DE').val()).length>10){
        alert("준공일 값은 최대 10자까지 입력할 수 있습니다.");
        return false;
    }

    //총 공사비
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#CNTRWK_AMOUNT').val()))){
        alert("총 공사비에 값을 입력하세요.");
        return false;
    }

    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#CNTRWK_AMOUNT').val()).length>22){
        alert("총 공사비 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }   
    
    //세부_공사_명
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#DETAIL_CNTRWK_NM').val()))){
        alert("세부 공사위치에 값을 입력하세요.");
        return false;
    }
    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#DETAIL_CNTRWK_NM').val()).length>200){
        alert("세부 공사위치 값은 최대 200자까지 입력할 수 있습니다.");
        return false;
    }   
    

/*  //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#BEFORE_PAV_YEAR').val()).length>4){
        alert("직전_포장_연도 값은 최대 4자까지 입력할 수 있습니다.");
        return false;
    }   
 */
    /* //1차로_연장
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#TRACK_LEN').val()))){
        alert("공사연장에 값을 입력하세요.");
        return false;
    }
 */

/* 
    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#SPRVISN_CO_NM').val()).length>200){
        alert("감리_업체_명 값은 최대 200자까지 입력할 수 있습니다.");
        return false;
    }   
    


    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#SPRVISN_CO_RPRSNT_NO').val()).length>15){
        alert("감리_업체_대표_번호 값은 최대 15자까지 입력할 수 있습니다.");
        return false;
    }   
    

    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#SPRVISN_CO_RPRSNTV_NM').val()).length>200){
        alert("감리_업체_대표자_명 값은 최대 200자까지 입력할 수 있습니다.");
        return false;
    }   
    
 


    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#SPRVISOR_NM').val()).length>200){
        alert("감리원_명 값은 최대 200자까지 입력할 수 있습니다.");
        return false;
    }   
    


    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#SPRVISOR_TELNO').val()).length>15){
        alert("감리원_전화번호 값은 최대 15자까지 입력할 수 있습니다.");
        return false;
    }   



    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#GVSLCT').val()).length>22){
        alert("관급비 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }    */
    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#OUTSRCCT').val()).length>22){
        alert("도급비 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }   
    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#ETC_RLOCATCT').val()).length>22){
        alert("기타_이설비 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }   

    //노선번호
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#ROUTE_CODE').val()))){
        alert("노선번호에 값을 입력하세요.");
        return false;
    }
    
    //도로명
   /*  if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#ROAD_NM').val()))){
        alert("도로명에 값을 입력하세요.");
        return false;
    }
     */
    //행선
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#DIRECT_NM').val()))){
        alert("행선에 값을 입력하세요.");
        return false;
    }
 
    //행선_코드
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#DIRECT_CODE').val()))){
        alert("행선_코드에 값을 입력하세요.");
        return false;
    }

  
    //공사 차로 수
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#TRACK').val()))){
        alert("공사 차로 수에 값을 입력하세요.");
        return false;
    }

    //보수시점
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#STRTPT').val()))){
        alert("보수시점에 값을 입력하세요.");
        return false;
    }

    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#STRTPT').val()).length>22){
        alert("보수시점 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }   

    //보수종점
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#ENDPT').val()))){
        alert("보수종점에 값을 입력하세요.");
        return false;
    }

    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#ENDPT').val()).length>22){
        alert("보수종점 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }   


    //공사연장
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#TRACK_LEN').val()))){
        alert("공사연장에 값을 입력하세요.");
        return false;
    }
    
    //공사폭
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPAIR_BT').val()))){
        alert("공사폭에 값을 입력하세요.");
        return false;
    }


    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#RPAIR_BT').val()).length>22){
        alert("공사폭 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }   
    
    
    //공사면적
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPAIR_AR').val()))){
        alert("공사면적에 값을 입력하세요.");
        return false;
    }

    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#RPAIR_AR').val()).length>22){
        alert("공사면적 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }   


    //포장공법
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPAIR_MTHD_CODE').val()))){
        alert("포장공법에 값을 입력하세요.");
        return false;
    }
    //기타인 경우 기타 텍스트 체크
    if(COMMON_LANG.trimdata( vform.find('#RPAIR_MTHD_CODE').val())=='RPIR0000'){
        //포장_공법_기타
        if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#PAV_MSRC_ETC').val()))){
            alert("포장_공법_기타에 값을 입력하세요.");
            return false;
        }

        //java.lang.String
        if(COMMON_LANG.trimdata( vform.find('#PAV_MSRC_ETC').val()).length>200){
            alert("포장_공법_기타 값은 최대 200자까지 입력할 수 있습니다.");
            return false;
        }
    }   
    
    

    //보수_두께_표층
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPAIR_THICK_ASCON').val()))){
        alert("포장두께 표층에 값을 입력하세요.");
        return false;
    }

    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#RPAIR_THICK_ASCON').val()).length>22){
        alert("포장두께 표층 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }   


    //포장두께 중간층
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPAIR_THICK_CNTR').val()))){
        alert("포장두께 중간층에 값을 입력하세요.");
        return false;
    }
    
    
    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#RPAIR_THICK_CNTR').val()).length>22){
        alert("포장두께 중간층 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }   
    
    //포장두께 기층
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPAIR_THICK_BASE').val()))){
        alert("포장두께 기층에 값을 입력하세요.");
        return false;
    }

    //java.math.BigDecimal
    if(COMMON_LANG.trimdata( vform.find('#RPAIR_THICK_BASE').val()).length>22){
        alert("포장두께 기층 값은 최대 22자까지 입력할 수 있습니다.");
        return false;
    }   
 
    // 표층의 포장두께는 0이면 안됨
    if( $("#RPAIR_THICK_ASCON").val() && $("#RPAIR_THICK_ASCON").val()<=0 ) {
        alert("표층의 포장두께를 다시 확인해주세요.");
        $("#RPAIR_THICK_ASCON").focus();
        return false;
    }
    
    
    
    
    
    
    //포장재료 표층코드
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#PAV_MATRL_ASCON_CODE').val()))){
        alert("포장재료 표층에 값을 입력하세요.");
        return false;
    }


    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#PAV_MATRL_ASCON_CODE').val())=="PM00000008"){
        //포장_재료_표층_기타
        if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#PAV_MATRL_ASCON_ETC').val()))){
            alert("포장재료 표층 기타에 값을 입력하세요.");
            return false;
        }
        //java.lang.String
        if(COMMON_LANG.trimdata( vform.find('#PAV_MATRL_ASCON_ETC').val()).length>100){
            alert("포장재료 표층 기타 값은 최대 100자까지 입력할 수 있습니다.");
            return false;
        }
    }


    /* //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#PAV_MATRL_BASE_CODE').val())=="PM00000008"){

        //포장_재료_기층_기타
        if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#PAV_MATRL_BASE_ETC').val()))){
            alert("포장재료 기층 기타에 값을 입력하세요.");
            return false;
        }

        //java.lang.String
        if(COMMON_LANG.trimdata( vform.find('#PAV_MATRL_BASE_ETC').val()).length>100){
            alert("포장재료 기층 기타 값은 최대 100자까지 입력할 수 있습니다.");
            return false;
        }   
    }   



    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#PAV_MATRL_CNTR_CODE').val()).length>10){
        alert("포장재료 중간층 코드 값은 최대 10자까지 입력할 수 있습니다.");
        return false;
    }   



    //포장_재료_중간층_기타
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#PAV_MATRL_CNTR_ETC').val()))){
        alert("포장재료 중간층 기타에 값을 입력하세요.");
        return false;
    }

 


    //java.lang.String
    if(COMMON_LANG.trimdata( vform.find('#PAV_MATRL_CNTR_ETC').val()).length>100){
        alert("포장재료 중간층 기타 값은 최대 100자까지 입력할 수 있습니다.");
        return false;
    }   
    
    
    
    
    
    //공급플랜트
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#RPAIR_MATRL_PRDCT_CO_NM').val()))){
        alert("공급플랜트에 값을 입력하세요.");
        return false;
    }
    
    //공급플랜트
    if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#PRDCT_CO_TELNO').val()))){
        alert("연락처에 값을 입력하세요.");
        return false;
    }
 */
    
    /* LTPP 선택 시
    if( $("#LTPP_CHECK").is(":checked") ) {
        // 1. LTPP 코드선택 확인
        var a = false;
        var chk = $("input:checkbox[name='LTPP_CODE']");
        
        $(chk).each(function(i){ 
            if( $(chk)[i].checked ){
                a = true;
            }
        });
        
        if(!a){
            alert("시험포장 코드를 선택해주세요.(다중선택가능)");
            $("input:checkbox[name='LTPP_CODE']").focus();
            return false;
        }
        
        // 2. 관리대장 한개이상 첨부필요
        a = false;
        var file = $("input[name^='CMN_FILE_COLUMN']");
        for(var i=0, l=$(file).length; i<l; i++){
            if( $($(file)[i]).css("display") == "none" ){
                a = true; break;        //등록된 첨부 파일이 있으면 true~
            } else {
                if( $(file)[i].value ){
                    a = true; break;    //첨부 파일이 있으면 true~
                }
            }
        }
        
        if(!a){
            alert("관리대장을 첨부해주세요.");
            $("input[name^='CMN_FILE_COLUMN']").focus();
            return false;
        }
    }
    */
    
    /* var sendform = document.frm;
    
    if(!validateSbhVO(sendform)){
        return;
    }else{
        
        // 버스전용차로 입력 체크
        if( $(':radio[name="BUS_DVR_TRACK_AT"]:checked').val()=='Y' ) {
            alert("버스전용차로 셀만 선택하셨습니까? \n일반차로를 포함하였으면 버스전용차로만 다시 선택하시기 바랍니다.");
        }
        
        
    } */
    return true;

}

//위치 수정
function fn_modify_cell(){
	//181107 wijy : 기존 위치 추가 여부 확인
	if(confirm("기존 위치정보를 추가하시겠습니까?")) {
		$.ajax({
			url: contextPath + 'api/cntrwkcellinfo/selectPavYearListAll.do'
			,type: 'post'
			,data: JSON.stringify({"DETAIL_CNTRWK_ID" : $('#DETAIL_CNTRWK_ID').val() })
			,dataType: 'json'
			,contentType : 'application/json'
			,success: function(res){
				var cellIds = [];
				var fields = [];				
				if(res.length > 1){					
					for(var i in res){
						var data = res[i];
						cellIds.push(data.PAV_CELL_ID);
						fields.push("CELL_ID");
					}
				
				}else if(res.length > 0){
					fields = "CELL_ID";
					cellIds = res[0].PAV_CELL_ID;
				}
				
				var option = {
						iframe : window,
						callback : "fn_update_cntrwkCell",
						CNTRWK_ID : $("#CNTRWK_ID").val(), 
						DETAIL_CNTRWK_ID : $("#DETAIL_CNTRWK_ID").val(),
						cellIdList : cellIds,
						selField : fields,
						clearMap : false
				};
				
				parent.MAP.CONTROL.activate_cellSel("CELL_10", option);
			}
			,error: function(a,b,msg){
			}		
		});	
	} else {
		parent.MAP.CONTROL.activate_cellSel("CELL_10", {
			iframe : window,
			callback : "fn_update_cntrwkCell",
			CNTRWK_ID : $("#CNTRWK_ID").val(), 
			DETAIL_CNTRWK_ID : $("#DETAIL_CNTRWK_ID").val(),
			cellIdList : [],
			selField : [],
			clearMap : false
			});
	}
	
}

function fn_update_cntrwkCell(cellIdList, param){
	$.ajax({
		url: contextPath + 'api/cell10/selectRouteInfos.do'
		,type: 'post'
		,dataType: 'json'
		,contentType : 'application/json'
		,data: JSON.stringify( {CELL_IDS : cellIdList}) 
		,success: function(data){
			if(data.length < 1) {return;}
			
			if(data.length > 1){
				var roadNM = "";
				for(var i in data){
					if(i!=0){roadNM += ",";}
					roadNM += data[i].ROAD_NAME + "(" + parseInt(data[i].ROUTE_CODE) + ")";
				}
				alert( '단일 노선이 선택되지 않았습니다.('+roadNM+')' );
				return;
			}
			
			var routeCd = data[0].ROUTE_CODE;
			var roadNm = data[0].ROAD_NAME;
			var directCd = data[0].DIRECT_CODE;
			var directNm = data[0].DIRECT_NM;
			var track = data[0].TRACK;
			var strtpt = parseInt(data[0].STRTPT);
			var endpt = parseInt(data[0].ENDPT);
			var trackLen = parseInt(endpt) - parseInt(strtpt);
			var rpairBt = 3.5 * parseInt(track);
			var rpairAr = trackLen * rpairBt;
			
			$("#PAV_CELL_ID").val(cellIdList);
			$("#PAV_CELL_IDS").val(cellIdList);
			$("#ROUTE_CODE").val(routeCd);
			$("#ROUTE_CODE1").val(parseInt(routeCd));
			$("#ROUTE_NM").val(roadNm);
			$("#DIRECT_CODE").val(directCd);
			$("#DIRECT_NM").val(directNm);
			$("#TRACK").val(track);
			$("#STRTPT").val(strtpt);
			$("#ENDPT").val(endpt);
			$("#STRTPT_KM").val(COMMON_UTIL.cmAddComma(strtpt));
			$("#ENDPT_KM").val(COMMON_UTIL.cmAddComma(endpt));
			$("#TRACK_LEN").val(COMMON_UTIL.cmAddComma(trackLen));
			$("#RPAIR_BT").val(COMMON_UTIL.cmAddComma(rpairBt));
			$("#RPAIR_AR").val(COMMON_UTIL.cmAddComma(rpairAr));
			
			fn_search();
			
		}
		,error: function(a,b,msg){
		}
	});
}

function fn_reset_form(){
	$("#frm").reset();
}
//-->
</script>	
<%@ include file="/include/common.jsp" %>
</body>
</html>


