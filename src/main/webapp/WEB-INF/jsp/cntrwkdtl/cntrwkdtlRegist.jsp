<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/include/common_head.jsp" %>

</head>

<body>
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
			<!-- KEY 파라메터 -->
			<input type="hidden" id="CNTRWK_ID" name="CNTRWK_ID" value="<c:out value="${cntrwkVO.CNTRWK_ID}"/>"/>
			<input type="hidden" id="PAV_CELL_ID" name="PAV_CELL_ID" value=""/>
			<input type="hidden" id="DETAIL_CNTRWK_ID" name="DETAIL_CNTRWK_ID" value="<c:out value="${cntrwkDtlVO.DETAIL_CNTRWK_ID}"/>"/>
			<input type="hidden" id="DETAIL_CNTRWK_GROUP" name="DETAIL_CNTRWK_GROUP" value="<c:out value="${cntrwkDtlVO.DETAIL_CNTRWK_GROUP}"/>"/>
			<!-- 기타 파라메터 -->
			<input type="hidden" id="EXT_TAB" name="EXT_TAB" value="<c:out value="${param.EXT_TAB}"/>"/>
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
			            	<tr>
								<th scope="row">착공일 ~ 준공일<span class="fcred"> *</span></th>
								<td colspan="3">
									<label for="RPAIR_BEGIN_DE"></label>
									<input type="text" name="RPAIR_BEGIN_DE" id="RPAIR_BEGIN_DE" style="margin-right: 5px;" value="<c:out value="${cntrwkDtlVO.RPAIR_BEGIN_DE}"/>" class="DT_DATE input" />
									-
									<label for="RPAIR_END_DE"></label>
									<input type="text" name="RPAIR_END_DE" id="RPAIR_END_DE" style="margin: 0px 5px;" value="<c:out value="${cntrwkDtlVO.RPAIR_END_DE}"/>" class="DT_DATE input" />
								
								    <a href="#" onclick="fnApplyCntrwk();" class="schbtn"  style="float:right;">공사기본정보 반영</a>
								</td>
								<!-- <td></td> -->
							</tr>
							<tr>							
								<th scope="row">시공사</th>
								<td>
									<label for="CNSTRCT_CO_NO"></label>
									<input type="hidden" name="CNSTRCT_CO_NM" id="CNSTRCT_CO_NM"  maxLength="22"  />
									<select name="CNSTRCT_CO_NO" id="CNSTRCT_CO_NO" onchange="fnChangeCompany('CNSTRCT_CO')" class="select" style="width: 100%;">
										<option value="">== 전체 ==</option>
										<c:forEach var="company" items="${companyList}">
										<option value="${company.CO_NO}" <c:if test = "${company.CO_NO == cntrwkDtlVO.CNSTRCT_CO_NO}"> selected="selected" </c:if> >${company.CO_NM}</option>
										</c:forEach>
									</select>
								</td>
								<th scope="row">도급비(천원)</th>
								<td>
									<label for="OUTSRCCT"></label>
									<input type="text" name="OUTSRCCT" id="OUTSRCCT" style="width:96%;" value="<c:out value="${cntrwkDtlVO.OUTSRCCT}"/>" class="MX_10 CS_10 DT_INT input" /> 
								</td>
								<%-- <th scope="row">감리사</th>
								<td>
									<label for="SPRVISN_CO_NO"></label>
									<input type="hidden" name="SPRVISN_CO_NM" id="SPRVISN_CO_NM"  maxLength="22"  />
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
									<label for="CNSTRCT_CO_RPRSNTV_NM"></label>
									<input type="text" name="CNSTRCT_CO_RPRSNTV_NM" id="CNSTRCT_CO_RPRSNTV_NM" style="width:96%;" value="<c:out value="${cntrwkDtlVO.CNSTRCT_CO_RPRSNTV_NM}"/>" class="MX_20 CS_15 input" />
								</td>
								<th scope="row">관급비(천원)</th>
								<td>
									<label for="GVSLCT"></label>
									<input type="text" name="GVSLCT" id="GVSLCT" style="width:96%;" value="<c:out value="${cntrwkDtlVO.GVSLCT}"/>" class="MX_10 CS_10 DT_INT input" />
								</td>
								<%-- <th scope="row">감리사 대표자</th>
								<td>
									<label for="SPRVISN_CO_RPRSNTV_NM"></label>
									<input type="text" name="SPRVISN_CO_RPRSNTV_NM" id="SPRVISN_CO_RPRSNTV_NM" style="width:150px;" value="<c:out value="${cntrwkDtlVO.SPRVISN_CO_RPRSNTV_NM}"/>" class="MX_20 CS_15 input" />
								</td> --%>
							</tr>
							<tr>							
								<th scope="row">시공사 대표번호</th>
								<td>
									<label for="CNSTRCT_CO_TELNO"></label>
									<input type="text" name="CNSTRCT_CO_TELNO" id="CNSTRCT_CO_TELNO" style="width:96%;" value="<c:out value="${cntrwkDtlVO.CNSTRCT_CO_TELNO}"/>" class="MX_15 CS_15 input" />
								</td>
								<th scope="row">총 공사비(천원)<span class="fcred"> *</span></th>
								<td>
									<label for="CNTRWK_AMOUNT"></label>
									<input type="text" name="CNTRWK_AMOUNT" id="CNTRWK_AMOUNT" style="width:96%;" value="<c:out value="${cntrwkDtlVO.CNTRWK_AMOUNT}"/>" readonly="readonly" class="MX_10 CS_10 DT_INT input" placeholder="자동계산" />
									<br/><span style="float: right;">(도급비+관급비)</span>
								</td>
								<%-- <th scope="row">감리사 대표번호</th>
								<td>
									<label for="SPRVISN_CO_RPRSNT_NO"></label>
									<input type="text" name="SPRVISN_CO_RPRSNT_NO" id="SPRVISN_CO_RPRSNT_NO" style="width:150px;" value="<c:out value="${cntrwkDtlVO.SPRVISN_CO_RPRSNT_NO}"/>" class="MX_15 CS_15 input" />
								</td> --%>
							</tr>
							<%-- <tr>
								<th scope="row">현장소장 성명</th>
								<td>
									<label for="SPT_HDCH_NM"></label>
									<input type="text" name="SPT_HDCH_NM" id="SPT_HDCH_NM" style="width:100px;" value="<c:out value="${cntrwkDtlVO.SPT_HDCH_NM}"/>" class="MX_20 CS_15 input" />
								</td>
								<th scope="row">감리원 성명</th>
								<td>
									<label for="SPRVISOR_NM"></label>
									<input type="text" name="SPRVISOR_NM" id="SPRVISOR_NM" style="width:100px;" value="<c:out value="${cntrwkDtlVO.SPRVISOR_NM}"/>" class="MX_20 CS_15 input" />
								</td>
							</tr> --%>
							<%-- <tr>
								<th scope="row">현장소장 전화번호</th>
								<td>
									<label for="SPT_HDCH_TELNO"></label>
									<input type="text" name="SPT_HDCH_TELNO" id="SPT_HDCH_TELNO" style="width:100px;" value="<c:out value="${cntrwkDtlVO.SPT_HDCH_TELNO}"/>" class="MX_15 CS_15 input" />
								</td>							
								<th scope="row">감리원 전화번호</th>
								<td>
									<label for="SPRVISOR_TELNO"></label>
									<input type="text" name="SPRVISOR_TELNO" id="SPRVISOR_TELNO" style="width:100px;" value="<c:out value="${cntrwkDtlVO.SPRVISOR_TELNO}"/>" class="MX_15 CS_15 input" />
								</td>
							</tr> 
							<tr>
								<th scope="row">도급비(천원)</th>
								<td>
									<label for="OUTSRCCT"></label>
									<input type="text" name="OUTSRCCT" id="OUTSRCCT" style="width:100px;" value="<c:out value="${cntrwkDtlVO.OUTSRCCT}"/>" class="MX_10 CS_10 DT_INT input" /> 
								</td>
								<th scope="row">관급비(천원)</th>
								<td>
									<label for="GVSLCT"></label>
									<input type="text" name="GVSLCT" id="GVSLCT" style="width:100px;" value="<c:out value="${cntrwkDtlVO.GVSLCT}"/>" class="MX_10 CS_10 DT_INT input" />
								</td>
							</tr>
							<tr>
								<th scope="row">기타 이설비(천원)</th>
								<td>
									<label for="ETC_RLOCATCT"></label>
									<input type="text" name="ETC_RLOCATCT" id="ETC_RLOCATCT" style="width:100px;" value="<c:out value="${cntrwkDtlVO.ETC_RLOCATCT}"/>" class="MX_10 CS_10 DT_INT input" />
									<br/>(폐기물, 차선도색비 제외)
								</td>
								<th scope="row">총 공사비(천원)<span class="fcred"> *</span></th>
								<td>
									<label for="CNTRWK_AMOUNT"></label>
									<input type="text" name="CNTRWK_AMOUNT" id="CNTRWK_AMOUNT" style="width:100px;" value="<c:out value="${cntrwkDtlVO.CNTRWK_AMOUNT}"/>" readonly="readonly" class="MX_10 CS_10 DT_INT input" placeholder="자동계산" />
									<br/>(도급비+관급비+기타이설비)
								</td>
							</tr> --%>
						</tbody>
					</table>
				</div>
				<div class="titbx mt20">
					<h4>위치정보</h4>
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
									<label for="DETAIL_CNTRWK_NM"></label>
									<input type="text" name="DETAIL_CNTRWK_NM" id="DETAIL_CNTRWK_NM" value="<c:out value="${cntrwkDtlVO.DETAIL_CNTRWK_NM}"/>" class="MX_100 CS_80 input" placeholder="예) 김포 CC삼거리 ~ 하성교차로"  style="width: 98.5%"/>
								</td>
							</tr>
							<tr>
								<th scope="row" >(노선번호) 노선명<span class="fcred"> *</span></th>
								<td>
									(
									<label for="ROUTE_CODE"></label>
									<input type="text" name="ROUTE_CODE" id="ROUTE_CODE" value="<c:out value="${cntrwkDtlVO.ROUTE_CODE}"/>" class="CS_10 input" style="width:50px;" onchange="fn_castRouteCode();" hidden="true"/>
									<input type="text" name="ROUTE_CODE1" id="ROUTE_CODE1" value="" style="width:17%;" class="CS_10 input" readonly/>
									)
									<label for="ROUTE_NM"></label>
									<input type="text" name="ROUTE_NM" id="ROUTE_NM" style="width:60%; float: right;" value="<c:out value="${cntrwkDtlVO.ROUTE_NM}"/>" class="CS_20 input" readonly/>
								</td>
								<th scope="row">도로명</th>
								<td>
									<label for="ROAD_NM"></label>
									<input type="text" name="ROAD_NM" id="ROAD_NM" style="width:96%;" value="<c:out value="${cntrwkDtlVO.ROAD_NM}"/>" class="CS_20 input" placeholder="예) 김포대로"/>
								</td>
							</tr>
							<tr>
								<th scope="row">행선<span class="fcred"> *</span></th>
								<td>
									<label for="DIRECT_NM"></label>
									<input type="text" name="DIRECT_NM" id="DIRECT_NM" style="width:96%;" value="<c:out value="${cntrwkDtlVO.DIRECT_NM}"/>" class="CS_20 input" readonly/>
									<label for="DIRECT_CODE"></label>
									<input type="hidden" name="DIRECT_CODE" id="DIRECT_CODE" value="<c:out value="${cntrwkDtlVO.DIRECT_CODE}"/>" class="CS_20 input" />
								</td>
								<th scope="row">공사 차로 수<span class="fcred"> *</span></th>
								<td>
									<label for="TRACK"></label>
									<input type="text" name="TRACK" id="TRACK" style="width:50px;" value="<c:out value="${cntrwkDtlVO.TRACK}"/>" class="CS_10 input" readonly/> 차로
									<label for="TRACK_LIST"></label>
									<input type="hidden" name="TRACK_LIST" id="TRACK_LIST" value="<c:out value="${cntrwkDtlVO.TRACK}"/>" />
								</td>
							</tr>
							<tr>
								<th scope="row">보수시점(m)<span class="fcred"> *</span></th>
								<td>
									<label for="STRTPT_KM"></label>
									<input type="text" name="STRTPT_KM" id="STRTPT_KM" style="width:96%;" value="<c:out value="${cntrwkDtlVO.STRTPT}"/>" class="MX_8 CS_8 DT_INT input" />
									<label for="STRTPT"></label>
									<input type="hidden" name="STRTPT" id="STRTPT" value="<c:out value="${cntrwkDtlVO.STRTPT}"/>" />
								</td>
								<th scope="row">보수종점(m)<span class="fcred"> *</span></th>
								<td>
									<label for="ENDPT_KM"></label>
									<input type="text" name="ENDPT_KM" id="ENDPT_KM" style="width:96%;" value="<c:out value="${cntrwkDtlVO.ENDPT}"/>" class="MX_8 CS_8 DT_INT input" />
									<label for="ENDPT"></label>
									<input type="hidden" name="ENDPT" id="ENDPT" value="<c:out value="${cntrwkDtlVO.ENDPT}"/>" />
								</td>
							</tr>
							<tr>
								<th scope="row">공사연장(m)<span class="fcred"> *</span></th>
								<td>
									<label for="TRACK_LEN"></label>
									<input type="text" name="TRACK_LEN" id="TRACK_LEN" style="width:96%;" value="<c:out value="${cntrwkDtlVO.TRACK_LEN}"/>" class="MX_8 CS_8 DT_INT input"/>
								</td>
								<th scope="row">공사폭(m)<span class="fcred"> *</span></th>
								<td>
									<label for="RPAIR_BT"></label>
									<input type="text" name="RPAIR_BT" id="RPAIR_BT" style="width:96%;" value="<c:out value="${cntrwkDtlVO.RPAIR_BT}"/>" class="MX_5 DD_2 CS_5 DT_FLOAT input"/>
								</td>
							</tr>							
							<tr>
								<th scope="row">공사면적(㎡)<span class="fcred"> *</span></th>
								<td>
									<label for="RPAIR_AR"></label>
									<input type="text" name="RPAIR_AR" id="RPAIR_AR" value="<c:out value="${cntrwkDtlVO.RPAIR_AR}"/>" class="MX_10 DD_4 CS_10 DT_FLOAT input" style="width: 96%" />
								</td>
								<th scope="row">포장공법<span class="fcred"> *</span></th>
								<td>
									<select name="RPAIR_MTHD_CODE" id="RPAIR_MTHD_CODE" class="select" onchange="COMMON_UTIL.fn_show_etcBox('RPIR0000', 'RPAIR_MTHD_CODE', 'PAV_MSRC_ETC')" style="width: 100%">
										<option value="">== 전체 ==</option>
										<c:forEach var="selectData" items="${RpairMthds}">
										<option value="${selectData.RPAIR_MTHD_CODE}" <c:if test = "${selectData.RPAIR_MTHD_CODE == cntrwkDtlVO.RPAIR_MTHD_CODE}"> selected="selected" </c:if> >${selectData.MSRC_CL_NM}</option>
										</c:forEach>
									</select>
									<label for="PAV_MSRC_ETC"></label>
									<input type="text" name="PAV_MSRC_ETC" id="PAV_MSRC_ETC" value="<c:out value="${cntrwkDtlVO.PAV_MSRC_ETC}"/>" class="MX_100 CS_20 input"  <c:if test="${empty cntrwkDtlVO.PAV_MSRC_ETC}">style="display: none;"</c:if>/>
								</td>
							</tr>
							<tr>
								<th scope="row" rowspan="3">포장두께(cm)<span class="fcred"> *</span></th>
								<td>표층　 : 
									<label for="RPAIR_THICK_ASCON"></label><input type="text"  style="width:71%;" name="RPAIR_THICK_ASCON" id="RPAIR_THICK_ASCON" value="<c:out value="${cntrwkDtlVO.RPAIR_THICK_ASCON}"/>" class="MX_5 DD_2 CS_5 DT_FLOAT input" placeholder="예) 5.5"/> 
								</td>
								<th scope="row" rowspan="3">포장재료</th>
								<td>표층<span class="fcred"> *</span> :
									<select name="PAV_MATRL_ASCON_CODE" id="PAV_MATRL_ASCON_CODE"  class="select" style="width: 75%;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_ASCON_CODE', 'PAV_MATRL_ASCON_ETC')">
										<option value="">== 전체 ==</option>
										<c:forEach var="selectData" items="${PavMatrls}">
										<option value="${selectData.PAV_MATRL_CODE}" <c:if test = "${selectData.PAV_MATRL_CODE == cntrwkDtlVO.PAV_MATRL_ASCON_CODE}"> selected="selected" </c:if> >${selectData.PAV_MATRL_NM}</option>
										</c:forEach>
									</select>
									<label for="PAV_MATRL_ASCON_ETC"></label>
									<input type="text" name="PAV_MATRL_ASCON_ETC" id="PAV_MATRL_ASCON_ETC" value="<c:out value="${cntrwkDtlVO.PAV_MATRL_ASCON_ETC}"/>" class="MX_100 CS_20 input"  <c:if test="${empty cntrwkDtlVO.PAV_MATRL_ASCON_ETC}">style="display: none;"</c:if>/>
								</td>
							</tr>
							<tr>	
								<td>중간층 : 
									<label for="RPAIR_THICK_CNTR"></label><input type="text" name="RPAIR_THICK_CNTR" id="RPAIR_THICK_CNTR" style="width:71%;" value="<c:out value="${cntrwkDtlVO.RPAIR_THICK_CNTR}"/>" class="MX_5 DD_2 CS_5 DT_FLOAT input" placeholder="예) 5.5"/>
								</td>
								<td>중간층 :
									<select name="PAV_MATRL_CNTR_CODE" id="PAV_MATRL_CNTR_CODE" class="select" style="width: 75%;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_CNTR_CODE', 'PAV_MATRL_CNTR_ETC')">
										<option value="">== 전체 ==</option>
										<c:forEach var="selectData" items="${PavMatrls}">
										<option value="${selectData.PAV_MATRL_CODE}" <c:if test = "${selectData.PAV_MATRL_CODE == cntrwkDtlVO.PAV_MATRL_CNTR_CODE}"> selected="selected" </c:if> >${selectData.PAV_MATRL_NM}</option>
										</c:forEach>
									</select>
									<label for="PAV_MATRL_CNTR_ETC"></label>
									<input type="text" name="PAV_MATRL_CNTR_ETC" id="PAV_MATRL_CNTR_ETC" value="<c:out value="${cntrwkDtlVO.PAV_MATRL_CNTR_ETC}"/>" class="MX_100 CS_20 input"  <c:if test="${empty cntrwkDtlVO.PAV_MATRL_CNTR_ETC}">style="display: none;"</c:if>/>
								</td>
							</tr>
							<tr>
								<td>기층　 :
									<label for="RPAIR_THICK_BASE"></label><input type="text" style="width:71%;" name="RPAIR_THICK_BASE" id="RPAIR_THICK_BASE" value="<c:out value="${cntrwkDtlVO.RPAIR_THICK_BASE}"/>" class="MX_5 DD_2 CS_5 DT_FLOAT input" placeholder="예) 5.5"/>
								</td>
								<td>기층　 :
									<select name="PAV_MATRL_BASE_CODE" id="PAV_MATRL_BASE_CODE" class="select" style="width: 75%;" onchange="COMMON_UTIL.fn_show_etcBox('PM00000008', 'PAV_MATRL_BASE_CODE', 'PAV_MATRL_BASE_ETC');">
										<option value="">== 전체 ==</option>
										<c:forEach var="selectData" items="${PavMatrls}">
										<option value="${selectData.PAV_MATRL_CODE}" <c:if test = "${selectData.PAV_MATRL_CODE == cntrwkDtlVO.PAV_MATRL_BASE_CODE}"> selected="selected" </c:if> >${selectData.PAV_MATRL_NM}</option>
										</c:forEach>
									</select>
									<label for="PAV_MATRL_BASE_ETC"></label>
									<input type="text" name="PAV_MATRL_BASE_ETC" id="PAV_MATRL_BASE_ETC" value="<c:out value="${cntrwkDtlVO.PAV_MATRL_BASE_ETC}"/>" class="MX_100 CS_20 input"  <c:if test="${empty cntrwkDtlVO.PAV_MATRL_BASE_ETC}">style="display: none;"</c:if>/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
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
									<label for="PAINT_MSRC"></label>
									<input type="text" name="PAINT_MSRC" id="PAINT_MSRC" style="width:120px;" value="<c:out value="${cntrwkDtlVO.PAINT_MSRC}"/>" class="MX_100 CS_20 input"/>
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
					     	  		<c:import url="/attachfile/getfileForm.do" >
										<c:param name="FILE_MODE" value="FILE_EDIT" />
										<c:param name="FILE_COLUMN" value="file_before" />
										<c:param name="FILE_NO" value="${cntrwkDtlVO.OPERT_BFE_PHOTO_NO}" />
										<c:param name="FILE_TYPE" value="IMAGE" />
									</c:import>
					     	  	</td>
					     	  	<td colspan="2" class="center" style="vertical-align:top;min-height:70px;">
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
								<td style="text-align: center;">
									<label for="CNTRWK_TIME1"></label>
									<input style="margin-left: 5px;" type="radio" name="CNTRWK_TIME" id="CNTRWK_TIME1" value="주간" <c:if test = "${cntrwkDtlVO.CNTRWK_TIME=='주간' || cntrwkDtlVO.CNTRWK_TIME==null}"> checked </c:if> />주간
									<label for="CNTRWK_TIME2" style="margin-left: 15px;"></label>
									<input style="margin-left: 5px;" type="radio" name="CNTRWK_TIME" id="CNTRWK_TIME2" value="야간" <c:if test = "${cntrwkDtlVO.CNTRWK_TIME=='야간'}"> checked </c:if> />야간
								</td>
								<th scope="row">비고</th>
								<td>
									<label for="RM"></label>
									<input type="text" name="RM" id="RM" value="<c:out value="${cntrwkDtlVO.RM}"/>" class="MX_100 CS_30 input" placeholder="예) 교차로에서 남쪽으로 5미터 지점 부근"/>
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
									<label for="RM"></label>
									<input type="text" name="RM" id="RM" value="<c:out value="${cntrwkDtlVO.RM}"/>" class="MX_100 CS_30 input" placeholder="예) 교차로에서 남쪽으로 5미터 지점 부근"/>
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
			</form>
            <form id="cellFrm2">
                <input type="hidden" id="CELL_IDS" name="CELL_IDS" value=""/>
                <div class="titbx mt20">
                    <h4>셀 선택</h4>
                    <table class="tbview" summary="포장 세부공사 위치정보를 조회한다.">
                        <colgroup>
                            <col width="10%" />
                            <col width="10%" />
                            <col width="10%" />
                            <col width="10%" />
                            <col width="10%" />
                            <col width="10%" />
                            <col width="10%" />
                            <col width="10%" />
                            <col width="20%" />
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row">행선</th>
                                <td>
                                    <select name="search_direct" id="search_direct" onchange="javascript:;" class="select" style="width: 100%;">
                                        <option value="">전체</option>
                                        <option value="상행">상행</option>
                                        <option value="하행">하행</option>
                                    </select>
                                </td>
                                <th scope="row">차로</th>
                                <td>
                                    <label for="search_track"></label>
                                    <input type="text" name="search_track" id="search_track" value="" class="MX_100 CS_80 input" />
                                </td>
                                <th scope="row">시점(m)</th>
                                <td>
                                    <label for="search_strtpt"></label>
                                    <input type="text" name="search_strtpt" id="search_strtpt" value="" class="MX_100 CS_80 input" />
                                </td>
                                <th scope="row">종점(m)</th>
                                <td>
                                    <label for="search_endpt"></label>
                                    <input type="text" name="search_endpt" id="search_endpt" value="" class="MX_100 CS_80 input" />
                                </td>
                                <td>
                                    <div class="fr"><input type="button" id="search_btn" class="schbtn" value="검색" /></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div style="width: 100%;">
                        <div id="div_grid2" style="width:100%; height:240px;">
                            <table id="gridArea2"></table>
                            <div id="gridPager2"></div>
                        </div>
                    </div>
                </div>
            </form>     
			<form id="cellFrm">
				<input type="hidden" name="DETAIL_CNTRWK_ID" value="${cntrwkDtlVO.DETAIL_CNTRWK_ID}"/>
				<input type="hidden" id="PAV_CELL_IDS" name="PAV_CELL_IDS" value=""/>
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
					<a href="#" onclick="check = false; fnSave();" class="schbtn" >등록</a>
				</div>
			</div>
		</div>
	
	</div>
	<!-- // Content -->
</div>
<!-- // wrap -->


<%@ include file="/include/common.jsp" %>

<script src="<c:url value='/js/common/cu_alert.js'/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">
//화면 초기 설정
$(document).ready(function() {
	// input, select 항목 init
	COMMON_UTIL.cmFormObjectInit("frm");
	
	// 달력 생성 (현재 년도에 해당하는 달력만 표시)
	var nowDate = new Date();
    var getYear = nowDate.getFullYear();
    //cmCreateFixYearDatepicker('RPAIR_BEGIN_DE', 'RPAIR_END_DE', getYear, 10);
    
	// 달력 생성
	COMMON_UTIL.cmCreateDatepickerLinked('RPAIR_BEGIN_DE','RPAIR_END_DE', 10);
	
	// 도급비, 관급비, 기타 이설비 입력시 합계 > 총 공사비
	$('#OUTSRCCT').keyup(function () { fnSumCNTRWK_AMOUNT(); });
	$('#GVSLCT').keyup(function () { fnSumCNTRWK_AMOUNT(); });
	/* $('#ETC_RLOCATCT').keyup(function () { fnSumCNTRWK_AMOUNT(); }); */
	// 공사면적 계산
	$('#TRACK_LEN').keyup(function () { fnSumRPAIR_AR(); });
	$('#RPAIR_BT').keyup(function () { fnSumRPAIR_AR(); });
	
	// 전화번호
	$('#CNSTRCT_CO_TELNO, #SPRVISN_CO_RPRSNT_NO, #SPT_HDCH_TELNO, #SPRVISOR_TELNO, #PRDCT_CO_TELNO').keyup(function(e) {
		
	    var s = $(this).val();
	    s = COMMON_UTIL.phoneNumFomatter(s);
	    $(this).val(s);
	});
		
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
	
	//var cell_id_arrays = parent.gMap.getLayerByName('GAttrLayer').features.map(function(elem) { return elem.data.CELL_ID || elem.data.cell_id })
	var cell_id_arrays = $('#PAV_CELL_ID').val() && $('#PAV_CELL_ID').val().split(',');
	var route_code_value = $('#ROUTE_CODE').val();
	var postData2 = {"CNTRWK_ID":route_code_value, "PAV_CELL_ID_LIST":cell_id_arrays};
	// 리스트에서 셀 선택 grid
    $("#gridArea2").jqGrid({
        url: '<c:url value="/"/>'+'api/cntrwkcellinfo/selectCntrwkCellInfoAllList.do'
        ,autoencode: true
        ,contentType : 'application/json'
        ,datatype: "local"
        ,mtype: "POST"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        //,postData: $("#cellFrm2").cmSerializeObject()
        ,postData: postData2
        ,ignoreCase: true
        ,colNames:["CELL_ID","노선번호","노선명","행선","차로","시점(m)","종점(m)","위치보기"]
        ,colModel:[
            {name:'CELL_ID', index:'CELL_ID', hidden:true}
            ,{name:'ROUTE_CODE',index:'ROUTE_CODE', align:'center', width:50, sortable:false, formatter: 'integer'}
            ,{name:'ROAD_NAME',index:'ROAD_NAME', align:'center', width:70, sortable:false}
            //,{name:'ROAD_GRAD',index:'ROAD_GRAD', align:'center', width:70, sortable:false}
            ,{name:'DIRECT_NM',index:'DIRECT_NM', align:'center', width:70, sortable:false}
            ,{name:'TRACK',index:'TRACK', align:'center', width:70, sortable:false}
            ,{name:'STRTPT',index:'STRTPT', align:'center', width:70, sortable:false, formatter: 'integer'}
            ,{name:'ENDPT',index:'ENDPT', align:'center', width:70, sortable:false, formatter: 'integer'}
            ,{name:'btn_loc_cell',index:'btn_loc_cell', align:'center', width:70, sortable:false, formatter: fn_create_btn}
        ]
        ,async : false
        ,sortname: ''
        ,sortorder: ""
        ,rowNum: 99999
        ,rowList: []
        ,pgbuttons: false
        ,pgtext: null
        ,viewrecords: true
        ,pager: '#gridPager2'
        ,rownumbers: true
        ,loadtext: "검색 중입니다."
        ,emptyrecords: "검색된 데이터가 없습니다."
        ,recordtext: "총 <font color='#f42200'>{2}</font> 건 데이터 ({0}-{1})"
        ,ondblClickRow: function(rowId) {       // 더블클릭 처리
            //fn_view(rowId); // 대장 조회
        }
        ,onSelectRow: function(rowId, status, e) {     // 클릭 처리
            fnCalDetailInfo();
        }
        ,onSelectAll: function(aRowIds, status) {
        	console.log('[onSelectAll] ' + status + ' = ' + aRowIds.length);
        	fnCalDetailInfo();
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
        ,multiselect: true
        ,multiboxonly: false
        ,loadonce: true
        //,scroll: true
    }).navGrid('#gridPager2',{edit:false,add:false,del:false,search:false,refresh:false});  
	
	COMMON_UTIL.cmInitGridSize('gridArea','div_grid', 180);
	
	COMMON_UTIL.cmInitGridSize('gridArea2','div_grid2', 180);
	
	setTimeout(function() {
		 fn_search();
	}, 500);
	
	addBtnEventHandler();
	
});

// 위치이동 버튼 생성
function fn_create_btn(cellValue, options, rowObject) {
	var btn = "";
	var nm = options.colModel.name;
	
	switch(nm) {
		case "btn_loc" :
			btn = "<a href='#' onclick=\"fn_select_cell('" + rowObject.PAV_CELL_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
			break;
		case "btn_loc_cell" :
	        btn = "<a href='#' onclick=\"fn_select_cell('" + rowObject.CELL_ID + "');\"><img src='" + contextPath +"/images/ic_location.png' alt='위치이동' title='위치이동' /></a>";
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
	MAP.fn_get_selectFeatureByAttrMulti(parent.gMap, tables, fields, values, null, "AND", attribute_base, true, 0, 1);
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
	
	//var cell_id_arrays = parent.gMap.getLayerByName('GAttrLayerMulti').features.map(function(elem) { return elem.data.CELL_ID || elem.data.cell_id })
    var cell_id_arrays = $('#PAV_CELL_ID').val() && $('#PAV_CELL_ID').val().split(',');
    var route_code_value = $('#ROUTE_CODE').val();
    var postData2 = {"CNTRWK_ID":route_code_value, "PAV_CELL_ID_LIST":cell_id_arrays};
    $("#gridArea2").jqGrid("setGridParam",{
        datatype: "json"
        ,ajaxGridOptions: { contentType: 'application/json; charset=utf-8' }
        ,contentType: "application/json"
        ,page: 1
        //,postData:   $("#cellFrm2").cmSerializeObject()
        ,postData:   postData2
        ,mtype: "POST"
        ,loadComplete: function(data) {
            COMMON_UTIL.fn_set_grid_noRowMsg('gridArea2', $("#gridArea2").jqGrid("getGridParam").emptyrecords, data.records);
            $('.ui-search-toolbar th.ui-state-default').css('background', '#efefef');
        }
        ,gridComplete: function() {
        	$("#cb_gridArea2").click();
        }
    }).trigger("reloadGrid");
}

// 총 공사비 자동 계산 처리
function fnSumCNTRWK_AMOUNT() {
	try {
		var tot = COMMON_UTIL.cmFormatFloat( $('#OUTSRCCT').val() ) + COMMON_UTIL.cmFormatFloat( $('#GVSLCT').val() ); // + COMMON_UTIL.cmFormatFloat( $('#ETC_RLOCATCT').val() );
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
// 셀 선택에 따라 공사 위치정보 수치 자동 계산
function fnCalDetailInfo() {
	
    var rowIds = $('#gridArea2').jqGrid('getGridParam', 'selarrrow');
    if (rowIds && rowIds.length > 0 ) {
    	
        var rowData = [];
        rowIds.forEach(function(elem) { rowData.push($('#gridArea2').jqGrid('getRowData', elem)); });
        
        // 보수시점(m)
        var strtptData = rowData.map(function(elem) { return elem.STRTPT });
        var minStrtpt = strtptData.slice(0).sort(function compare(a,b) {return a-b;})[0];
        $('#STRTPT_KM').val(minStrtpt);
        $('#STRTPT').val(minStrtpt);
        // 보수종점(m)
        var endptData = rowData.map(function(elem) { return elem.ENDPT });
        var maxEndpt = endptData.slice(0).sort(function compare(a,b) {return a-b;}).reverse()[0];
        $('#ENDPT_KM').val(maxEndpt);
        $('#ENDPT').val(maxEndpt);
        // 공사연장(m)
        var trackLen = Number(maxEndpt) - Number(minStrtpt);
        $('#TRACK_LEN').val(trackLen >= 0 ? trackLen : 0);
        // 공사차로수 / 공사폭(m) / 행선
        var directAndTrackData = rowData.map(function(elem) { return ""+elem.DIRECT_NM+"_"+elem.TRACK });
        var filteredData = directAndTrackData.filter(function(value, idx, self) { return self.indexOf(value) === idx });
        $('#TRACK').val(filteredData.length);
        $('#TRACK_LIST').val(filteredData.length);
        var rpairBt = 3.5 * filteredData.length;
        $('#RPAIR_BT').val(rpairBt);
        var directData = filteredData.map(function(elem) { return elem.split("_")[0] })
                                     .filter(function(value, idx, self) { return self.indexOf(value) === idx });
        if (directData.length > 1) {
            $('#DIRECT_NM').val('양방향');
            $('#DIRECT_CODE').val('SE');
        } else if (directData.length > 0 && directData[0] === '상행') {
            $('#DIRECT_NM').val('상행');
            $('#DIRECT_CODE').val('S');
        } else if (directData.length > 0 && directData[0] === '하행') {
            $('#DIRECT_NM').val('하행');
            $('#DIRECT_CODE').val('E');             
        }
        fnSumRPAIR_AR();
    	
    } else {
        $('#STRTPT_KM').val(0);
        $('#STRTPT').val(0);
        $('#ENDPT_KM').val(0);
        $('#ENDPT').val(0);
        $('#TRACK_LEN').val(0);
        $('#TRACK').val('');
        $('#TRACK_LIST').val('');
        $('#DIRECT_NM').val('');
        $('#DIRECT_CODE').val('');
        $('#RPAIR_BT').val(0);
    }
    
    COMMON_UTIL.cmFormObjectInit("frm", true);
}

function addBtnEventHandler() {
	/*
	$("#search_direct").on('change', function () { filterOfSelectedCells('DIRECT_NM'); });
	$("#search_direct").on('change keyup paste', function () { SearchByEmployeeName('TRACK'); });
	$("#search_direct").on('change keyup paste', function () { SearchByEmployeeName('STRTPT'); });
	$("#search_direct").on('change keyup paste', function () { SearchByEmployeeName('ENDPT'); });
	*/
	
	$('#search_btn').click(function() {
		var search_direct = $('#search_direct option:selected').val();
		var search_track = $('#search_track').val();
		var search_strtpt = $('#search_strtpt').val();
		var search_endpt = $('#search_endpt').val();
		
		var f = { groupOp: "AND", rules: [] };
		if (search_direct && search_direct != '') {
			f.rules.push({field: 'DIRECT_NM', op: 'cn', data: search_direct});
		}
		if (search_track && search_track != '') {
			f.rules.push({field: 'TRACK', op: 'cn', data: search_track});
		}
		if (search_strtpt && search_strtpt != '') {
			f.rules.push({field: 'STRTPT', op: 'cn', data: search_strtpt});
		}
		if (search_endpt && search_endpt != '') {
			f.rules.push({field: 'ENDPT', op: 'cn', data: search_endpt});
		}
		
		// reload grid
		var grid = $('#gridArea2'); 
		grid[0].p.search = f.rules.length > 0; 
		$.extend(grid[0].p.postData, { filters: JSON.stringify(f) }); 
		grid.trigger("reloadGrid", [{ page: 1 }]);

	})
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
	

/* 	//java.lang.String
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
	}	 */
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
/* 	if(COMMON_LANG.isnullempty(COMMON_LANG.trimdata( vform.find('#ROAD_NM').val()))){
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
				a = true; break;		//등록된 첨부 파일이 있으면 true~
			} else {
				if( $(file)[i].value ){
					a = true; break;	//첨부 파일이 있으면 true~
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
	
	// [2019-10-25] 셀 선택 체크 추가
	var selectedCellArr = $('#gridArea2').jqGrid('getGridParam','selarrrow');
	if (selectedCellArr && selectedCellArr.length > 0) {
		var selectedCellArrToString = selectedCellArr.map(function(elem) { return $('#gridArea2').jqGrid('getRowData', elem).CELL_ID }).join();
		$('#PAV_CELL_ID').val(selectedCellArrToString);
	} else {
        alert('셀을 한 개 이상 체크해주세요');
        return false;		
	}
	
	
	return true;

}
	
 
//글 등록
function fnSave() {
	
	//위치등록 기능 구현 후 주석 제거
	if(!validateInsert('frm')){return;}
	
	if( confirm('<spring:message code="warn.insert.msg" />') ) {
		// 진행 프로그래스바 생성
		COMMON_UTIL.cmShowProgressBar();
		
		try {
			parent.gMap.cleanMap();	
			// multipart/form-data 아닌 경우, mask 처리 값을 제거하여 폼 데이터를 전송 처리함
			//COMMON_UTIL.cmFormSubmit("frm", "proc_frm", "<c:url value='/gsm/sbhProcWrite.do'/>","fnSaveCallback");
			COMMON_UTIL.cmFileFormSubmit("frm", "proc_frm", "<c:url value='/cntrwkdtl/addCntrwkDtl.do'/>","fnSaveCallback");
			
		} catch(E) {
			alert("폼데이터 변환중 오류가 발생하였습니다. :" +E);
		}
	}
	
}
//---------------------------
//처리 후 callback 함수들 (필수)
//---------------------------
function fnSaveCallback( insertKey ) {
	// 목록 화면 재검색
	try {
		COMMON_UTIL.cmHideProgressBar();
		// 오프너 윈도우 추출
		var openerId = $("#opener_id").val();
		COMMON_UTIL.cmGetWindowOpener( openerId ).fnSearch();
		
		var wnd_id = $("#wnd_id").val();
		$('#'+wnd_id, window.parent.document).remove();
		
		
	}catch(E) {alert(E);}

	
	//if ($("#action_flag").val() == "INSERT") $("#DETAIL_CNTRWK_GROUP").val(insertKey);	// 정상 등록시 등록된 키 값을 세팅하고 해당 상세 화면으로 이동
	//COMMON_UTIL.cmMoveUrl( "cntrwkdtl/updateCntrwkDtlView.do?DETAIL_CNTRWK_ID="+insertKey+"&EXT_TAB="+$("#EXT_TAB").val() );
	//COMMON_UTIL.cmMovePage("frm", "<c:url value='/cntrwkdtl/updateCntrwkDtlView.do'/>");	
}

//---------------------------
//기타 유틸 함수
//---------------------------
var blink_times = 0;
$.fn.blink = function(times, duration) {
	blink_times = times;
 while (blink_times--) {
     this.fadeTo(duration, 0).fadeTo(duration, 1);
 }
 return this;
};

function fnApplyCntrwk(){

	 var postDatas =  {CNTRWK_ID : $('#CNTRWK_ID').val()};

     $.ajax({
         url: "<c:url value='/api/cntrwk/selectCntrwk.do' />",
         contentType: 'application/json',
         data: JSON.stringify(postDatas),
         dataType: "json",
         cache: false,
         processData: false,
         type: 'POST',
         async: false,
         success: function (jdata) {
             if (jdata !=null && jdata.CNTRWK_ID!="")
           	 {
           		//alert('검색된 포장공사 정보가  있습니다.');
           		$('#RPAIR_BEGIN_DE').val(jdata.STRWRK_DE);/* 공사정보.착공_일자 */
           		$('#RPAIR_END_DE').val(jdata.COMPET_DE); /* 공사정보.준공_일자 */
           		 
           		$('#SPRVISOR_NM').val(jdata.SPRVISR_NM);/* 공사정보.감독원_명 */           		
           		$('#SPRVISOR_TELNO').val(jdata.SPRVISR_TELNO);/* 공사정보.감독원_전화번호 */
           		
           		$('#SPRVISN_CO_NO').val(jdata.SPRVISN_CO_NO); /* 공사정보.감리_업체_번호 */
           		$('#SPRVISN_CO_NM').val(jdata.SPRVISN_CO_NM); /* 공사정보.감리_업체_명 */           		
           		$('#SPRVISN_CO_RPRSNT_NO').val(jdata.SPRVISN_CO_RPRSNT_TELNO);/* 공사정보.감리_업체_대표_전화번호 */
           		$('#SPRVISN_CO_RPRSNTV_NM').val(jdata.SPRVISN_CO_RPRSNTV_NM); /* 공사정보.감리_업체_대표자_명 */
           		
           		$('#CNSTRCT_CO_NO').val(jdata.CNSTRCT_CO_NO); /* 공사정보.시공_업체_번호 */
           		$('#CNSTRCT_CO_NM').val(jdata.CNSTRCT_CO_NM); /* 공사정보.시공_업체_명 */
           		$('#CNSTRCT_CO_RPRSNTV_NM').val(jdata.CNSTRCT_CO_RPRSNTV_NM); /* 공사정보.시공_업체_대표자_명 */
           		$('#CNSTRCT_CO_TELNO').val(jdata.CNSTRCT_CO_TELNO);/* 공사정보.시공_업체_전화번호 */
           		$('#SPT_AGENT_NM').val(jdata.SPT_AGENT_NM); /* 공사정보.현장_대리인_명 */
           		$('#SPT_AGENT_TELNO').val(jdata.SPT_AGENT_TELNO); /* 공사정보.현장_대리인_전화번호 */
           		
           		 
           	 }
             else
                {
            	 alert('검색된 포장공사 정보가 없습니다.');
                }
         },

         error: function () {
             alert("값을 가져올 수 없습니다.");
             return;
         }
     });
    
}
function fnSearchCompany(searchType){
	COMMON_UTIL.cmWindowOpen('공사업체 검색', "<c:url value='/company/searchCompany.do'/>?searchType="+searchType, 850, 600, false, $("#wnd_id").val(), 'center'); //밑에가있음..;
}
function fnSearchCompanyCallback(searchType, company){
	if(searchType=='CNSTRCT_CO'){
		$('#CNSTRCT_CO_NO').val(company['CO_NO']);
		$('#CNSTRCT_CO_NM').val(company['CO_NM']);
		$('#CNSTRCT_CO_RPRSNTV_NM').val(company['RPRSNTV_NM']);
		$('#CNSTRCT_CO_TELNO').val(company['RPRSNT_TEL_NO']);
	}else if(searchType=='SPRVISN_CO'){
		$('#SPRVISN_CO_NO').val(company['CO_NO']);
		$('#SPRVISN_CO_NM').val(company['CO_NM']);
		$('#SPRVISN_CO_RPRSNTV_NM').val(company['RPRSNTV_NM']);
		$('#SPRVISN_CO_RPRSNT_NO').val(company['RPRSNT_TEL_NO']);
	}
}
function fnClearCompany(searchType){
	if(searchType=='CNSTRCT_CO'){
		$('#CNSTRCT_CO_NO').val('');
		$('#CNSTRCT_CO_NM').val('');
		$('#CNSTRCT_CO_RPRSNTV_NM').val('');
		$('#CNSTRCT_CO_TELNO').val('');
	}else if(searchType=='SPRVISN_CO'){
		$('#SPRVISN_CO_NO').val('');
		$('#SPRVISN_CO_NM').val('');
		$('#SPRVISN_CO_RPRSNTV_NM').val('');
		$('#SPRVISN_CO_RPRSNT_NO').val('');
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

//노선변호 String > Integer로 형변환
function fn_castRouteCode(){
	$('#ROUTE_CODE1').val($('#ROUTE_CODE').val()*1);
}

</script>
</body>
</html>