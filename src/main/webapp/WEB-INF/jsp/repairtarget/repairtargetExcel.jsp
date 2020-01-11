<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<?xml version="1.0" encoding="utf-8"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Author>Administrator</Author>
  <LastAuthor>leehb</LastAuthor>
  <Created>2017-09-29T02:16:36Z</Created>
  <LastSaved>2017-09-29T02:22:50Z</LastSaved>
  <Version>15.00</Version>
 </DocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <AllowPNG/>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>10695</WindowHeight>
  <WindowWidth>25875</WindowWidth>
  <WindowTopX>0</WindowTopX>
  <WindowTopY>0</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Center"/>
   <Borders/>
   <Font ss:FontName="맑은 고딕" x:CharSet="129" x:Family="Modern" ss:Size="11"
    ss:Color="#000000"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s17" ss:Name="쉼표 [0]">
   <NumberFormat ss:Format="_-* #,##0_-;\-* #,##0_-;_-* &quot;-&quot;_-;_-@_-"/>
  </Style>
  <Style ss:ID="s62">
   <Font ss:FontName="맑은 고딕" x:CharSet="129" x:Family="Modern" ss:Color="#000000"/>
  </Style>
  <Style ss:ID="s63">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="맑은 고딕" x:CharSet="129" x:Family="Modern" ss:Color="#000000"/>
  </Style>
  <Style ss:ID="s64">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Font ss:FontName="맑은 고딕" x:CharSet="129" x:Family="Modern" ss:Color="#FFFFFF"/>
   <Interior ss:Color="#818792" ss:Pattern="Solid"/>
  </Style>
  <Style ss:ID="s108">
   <Alignment ss:Horizontal="Right" ss:Vertical="Bottom"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous"/>
   </Borders>
   <Font ss:FontName="Dialog" x:Family="Swiss"/>
   <Interior ss:Color="#E5E5E5" ss:Pattern="Solid"/>
  </Style>
  <Style ss:ID="s109">
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous"/>
   </Borders>
   <Font ss:FontName="맑은 고딕" x:CharSet="129" x:Family="Modern" ss:Color="#000000"/>
   <Interior ss:Color="#E5E5E5" ss:Pattern="Solid"/>
  </Style>
  <Style ss:ID="s110">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous"/>
   </Borders>
   <Font ss:FontName="맑은 고딕" x:CharSet="129" x:Family="Modern" ss:Color="#000000"/>
   <Interior ss:Color="#E5E5E5" ss:Pattern="Solid"/>
  </Style>
  <Style ss:ID="s111">
   <Alignment ss:Horizontal="Right" ss:Vertical="Bottom"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous"/>
   </Borders>
   <Font ss:FontName="Dialog" x:Family="Swiss"/>
   <Interior ss:Color="#E5E5E5" ss:Pattern="Solid"/>
   <NumberFormat ss:Format="Fixed"/>
  </Style>
  <Style ss:ID="s112" ss:Parent="s17">
   <Alignment ss:Horizontal="Right" ss:Vertical="Bottom"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous"/>
   </Borders>
   <Font ss:FontName="Dialog" x:Family="Swiss"/>
   <Interior ss:Color="#E5E5E5" ss:Pattern="Solid"/>
  </Style>
  <Style ss:ID="s113">
   <Alignment ss:Horizontal="Right" ss:Vertical="Bottom"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous"/>
   </Borders>
   <Font ss:FontName="Dialog" x:Family="Swiss"/>
  </Style>
  <Style ss:ID="s114">
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous"/>
   </Borders>
   <Font ss:FontName="맑은 고딕" x:CharSet="129" x:Family="Modern" ss:Color="#000000"/>
  </Style>
  <Style ss:ID="s115">
   <Alignment ss:Horizontal="Center" ss:Vertical="Center"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous"/>
   </Borders>
   <Font ss:FontName="맑은 고딕" x:CharSet="129" x:Family="Modern" ss:Color="#000000"/>
  </Style>
  <Style ss:ID="s116">
   <Alignment ss:Horizontal="Right" ss:Vertical="Bottom"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous"/>
   </Borders>
   <Font ss:FontName="Dialog" x:Family="Swiss"/>
   <NumberFormat ss:Format="Fixed"/>
  </Style>
  <Style ss:ID="s117" ss:Parent="s17">
   <Alignment ss:Horizontal="Right" ss:Vertical="Bottom"/>
   <Borders>
    <Border ss:Position="Bottom" ss:LineStyle="Continuous"/>
    <Border ss:Position="Left" ss:LineStyle="Continuous"/>
    <Border ss:Position="Right" ss:LineStyle="Continuous"/>
    <Border ss:Position="Top" ss:LineStyle="Continuous"/>
   </Borders>
   <Font ss:FontName="Dialog" x:Family="Swiss"/>
  </Style>
 </Styles>
<Worksheet ss:Name="보수대상 선정 목록">
<%--ss:ExpandedColumnCount="18"  x:FullColumns="1" ss:ExpandedRowCount="${totCnt}"
   x:FullRows="1" --%>
  <Table  >
    <Column ss:StyleID="s62" ss:Width="28.5"/>
   <Column ss:StyleID="s62" ss:Width="57.75"/>
   <Column ss:StyleID="s62" ss:Width="90.75"/>
   <Column ss:StyleID="s62" ss:Width="78.75"/>
   <Column ss:StyleID="s63" ss:Width="70.5"/>
   <Column ss:StyleID="s63" ss:Width="72.75"/>
   <Column ss:StyleID="s63" ss:Width="39"/>
   <Column ss:StyleID="s62" ss:Width="77.25"/>
   <Column ss:StyleID="s62" ss:Width="42.75" ss:Span="1"/>
   <Column ss:Index="11" ss:StyleID="s62" ss:Width="90"/>
   <Column ss:StyleID="s62" ss:Width="52.5"/>
   <Column ss:StyleID="s62" ss:Width="78"/>
   <Column ss:StyleID="s62" ss:Width="56.25"/>
   <Column ss:StyleID="s62" ss:Width="44.25"/>
   <Column ss:StyleID="s62" ss:Width="135"/>
   <Column ss:StyleID="s62" ss:Width="57.75"/>
   <Column ss:StyleID="s62" ss:Width="81.75"/>
   <Column ss:StyleID="s62" ss:Width="135"/>
   <Column ss:StyleID="s62" ss:Width="57.75"/>
   <Column ss:StyleID="s62" ss:Width="53.25"/>
   <Column ss:StyleID="s62" ss:Width="53.25"/>
   <Column ss:StyleID="s62" ss:Width="57.25"/>
   <Column ss:StyleID="s62" ss:Width="53.25"/>
   <Row ss:AutoFitHeight="0" ss:StyleID="s64">
    <Cell><Data ss:Type="String">순번</Data></Cell>
    <Cell><Data ss:Type="String">관리기관</Data></Cell>
    <Cell><Data ss:Type="String">도로등급</Data></Cell>
    <Cell><Data ss:Type="String">노선번호</Data></Cell>
    <Cell><Data ss:Type="String">노선번호</Data></Cell>
    <Cell><Data ss:Type="String">행선</Data></Cell>
    <Cell><Data ss:Type="String">차로</Data></Cell>
    <Cell><Data ss:Type="String">포장셀구분</Data></Cell>
    <Cell><Data ss:Type="String">시점(m)</Data></Cell>
    <Cell><Data ss:Type="String">종점(m)</Data></Cell>
    <Cell><Data ss:Type="String">교통량등급</Data></Cell>
    <Cell><Data ss:Type="String">행정구역</Data></Cell>
    <Cell><Data ss:Type="String">최근공사년도</Data></Cell>
    <Cell><Data ss:Type="String">연장(㎞)</Data></Cell>
    <Cell><Data ss:Type="String">면적(㎡)</Data></Cell>
    <Cell><Data ss:Type="String">보수공법</Data></Cell>
    <Cell><Data ss:Type="String">단가(원/㎡)</Data></Cell>
    <Cell><Data ss:Type="String">금액산정 (원)</Data></Cell>
    <Cell><Data ss:Type="String">예산금액산정 (원)</Data></Cell>
    <Cell><Data ss:Type="String">균열율(%)</Data></Cell>
    <Cell><Data ss:Type="String">소성변형(mm)</Data></Cell>
    <Cell><Data ss:Type="String">IRI(m/km)</Data></Cell>
    <Cell><Data ss:Type="String">GPCI</Data></Cell>
    <Cell><Data ss:Type="String">DMG_VAL</Data></Cell>
   </Row>
   <Row ss:AutoFitHeight="0" ss:StyleID="s64">
    <Cell><Data ss:Type="String">RN</Data></Cell>
    <Cell><Data ss:Type="String">DEPT_NM</Data></Cell>
    <Cell><Data ss:Type="String">ROAD_GRAD_NM</Data></Cell>
    <Cell><Data ss:Type="String">ROAD_NO_VAL</Data></Cell>
    <Cell><Data ss:Type="String">ROUTE_CODE</Data></Cell>
    <Cell><Data ss:Type="String">DIRECT_CODE</Data></Cell>
    <Cell><Data ss:Type="String">TRACK</Data></Cell>
    <Cell><Data ss:Type="String">CELL_TYPE_NM</Data></Cell>
    <Cell><Data ss:Type="String">STRTPT</Data></Cell>
    <Cell><Data ss:Type="String">ENDPT</Data></Cell>
    <Cell><Data ss:Type="String">VMTC_GRAD_NM</Data></Cell>
    <Cell><Data ss:Type="String">ADM_NM</Data></Cell>
    <Cell><Data ss:Type="String">CNTRWK_YEAR</Data></Cell>
    <Cell><Data ss:Type="String">KILLO_LEN</Data></Cell>
    <Cell><Data ss:Type="String">AR</Data></Cell>
    <Cell><Data ss:Type="String">MSRC_CL_NM</Data></Cell>
    <Cell><Data ss:Type="String">RPAIR_FEE</Data></Cell>
    <Cell><Data ss:Type="String">AMOUNT_CALC</Data></Cell>
    <Cell><Data ss:Type="String">BUDGET_ASIGN</Data></Cell>
    <Cell><Data ss:Type="String">CR_RT_AVRG</Data></Cell>
    <Cell><Data ss:Type="String">RD_VAL_SM</Data></Cell>
    <Cell><Data ss:Type="String">IRI_VAL_SM</Data></Cell>
    <Cell><Data ss:Type="String">CALC_GPCI</Data></Cell>
    <Cell><Data ss:Type="String">DMG_VAL</Data></Cell>
   </Row>
<c:forEach items="${rpairTrgetItems}" var="item" varStatus="i">
<Row>
<c:if test="${item.SLCTN_AT=='Y'}"><!-- inbuget -->
<Cell ss:StyleID="s108"><Data ss:Type="Number">${item.RN}</Data></Cell>
<Cell ss:StyleID="s109"><Data ss:Type="String">${item.DEPT_NM}</Data></Cell>
<Cell ss:StyleID="s109"><Data ss:Type="String">${item.ROAD_GRAD_NM}</Data></Cell>
<Cell ss:StyleID="s109"><Data ss:Type="String">${item.ROAD_NO_VAL}</Data></Cell>
<Cell ss:StyleID="s110"><Data ss:Type="String">${item.ROUTE_CODE}</Data></Cell>
<Cell ss:StyleID="s110"><Data ss:Type="String"><c:if test="${item.DIRECT_CODE=='S'}">상행</c:if><c:if test="${item.DIRECT_CODE=='E'}">하행</c:if></Data></Cell>
<Cell ss:StyleID="s110"><Data ss:Type="String">${item.TRACK}</Data></Cell>
<Cell ss:StyleID="s109"><Data ss:Type="String">${item.CELL_TYPE_NM}</Data></Cell>
<Cell ss:StyleID="s112"><Data ss:Type="Number"><fmt:formatNumber value="${item.STRTPT}" type="number"/></Data></Cell>
<Cell ss:StyleID="s112"><Data ss:Type="Number"><fmt:formatNumber value="${item.ENDPT}" type="number"/></Data></Cell>
<Cell ss:StyleID="s109"><Data ss:Type="String">${item.VMTC_GRAD_NM}</Data></Cell>
<Cell ss:StyleID="s109"><Data ss:Type="String">${item.ADM_NM}</Data></Cell>
<Cell ss:StyleID="s109"><Data ss:Type="String">${item.CNTRWK_YEAR}</Data></Cell>
<Cell ss:StyleID="s111"><Data ss:Type="Number"><fmt:formatNumber value="${item.KILLO_LEN}" type="number"/></Data></Cell>
<Cell ss:StyleID="s111"><Data ss:Type="Number"><fmt:formatNumber value="${item.AR}" type="number"/></Data></Cell>
<Cell ss:StyleID="s109"><Data ss:Type="String">${item.MSRC_CL_NM}</Data></Cell>
<Cell ss:StyleID="s112"><Data ss:Type="Number"><fmt:formatNumber value="${item.RPAIR_FEE}" type="number"/></Data></Cell>
<Cell ss:StyleID="s112"><Data ss:Type="Number"><fmt:formatNumber value="${item.AMOUNT_CALC}" type="number"/></Data></Cell>
<Cell ss:StyleID="s112"><Data ss:Type="Number"><fmt:formatNumber value="${item.BUDGET_ASIGN}" type="number"/></Data></Cell>
<Cell ss:StyleID="s108"><Data ss:Type="Number"><fmt:formatNumber value="${item.CR_RT_AVRG}" type="number"/></Data></Cell>
<Cell ss:StyleID="s108"><Data ss:Type="Number"><fmt:formatNumber value="${item.RD_VAL_SM}" type="number"/></Data></Cell>
<Cell ss:StyleID="s108"><Data ss:Type="Number"><fmt:formatNumber value="${item.IRI_VAL_SM}" type="number"/></Data></Cell>
<Cell ss:StyleID="s108"><Data ss:Type="Number"><fmt:formatNumber value="${item.CALC_GPCI}" type="number"/></Data></Cell>
<Cell ss:StyleID="s108"><Data ss:Type="Number"><fmt:formatNumber value="${item.DMG_VAL}" type="number"/></Data></Cell>
</c:if>
<c:if test="${item.SLCTN_AT=='N'}"><!-- overbuget -->
<Cell ss:StyleID="s113"><Data ss:Type="Number">${item.RN}</Data></Cell>
<Cell ss:StyleID="s114"><Data ss:Type="String">${item.DEPT_NM}</Data></Cell>
<Cell ss:StyleID="s114"><Data ss:Type="String">${item.ROAD_GRAD_NM}</Data></Cell>
<Cell ss:StyleID="s114"><Data ss:Type="String">${item.ROAD_NO_VAL}</Data></Cell>
<Cell ss:StyleID="s115"><Data ss:Type="String">${item.ROUTE_CODE}</Data></Cell>
<Cell ss:StyleID="s115"><Data ss:Type="String"><c:if test="${item.DIRECT_CODE=='S'}">상행</c:if><c:if test="${item.DIRECT_CODE=='E'}">하행</c:if></Data></Cell>
<Cell ss:StyleID="s115"><Data ss:Type="String">${item.TRACK}</Data></Cell>
<Cell ss:StyleID="s114"><Data ss:Type="String">${item.CELL_TYPE_NM}</Data></Cell>
<Cell ss:StyleID="s117"><Data ss:Type="Number"><fmt:formatNumber value="${item.STRTPT}" type="number"/></Data></Cell>
<Cell ss:StyleID="s117"><Data ss:Type="Number"><fmt:formatNumber value="${item.ENDPT}" type="number"/></Data></Cell>
<Cell ss:StyleID="s114"><Data ss:Type="String">${item.VMTC_GRAD_NM}</Data></Cell>
<Cell ss:StyleID="s114"><Data ss:Type="String">${item.ADM_NM}</Data></Cell>
<Cell ss:StyleID="s114"><Data ss:Type="String">${item.CNTRWK_YEAR}</Data></Cell>
<Cell ss:StyleID="s116"><Data ss:Type="Number"><fmt:formatNumber value="${item.KILLO_LEN}" type="number"/></Data></Cell>
<Cell ss:StyleID="s116"><Data ss:Type="Number"><fmt:formatNumber value="${item.AR}" type="number"/></Data></Cell>
<Cell ss:StyleID="s114"><Data ss:Type="String">${item.MSRC_CL_NM}</Data></Cell>
<Cell ss:StyleID="s117"><Data ss:Type="Number"><fmt:formatNumber value="${item.RPAIR_FEE}" type="number"/></Data></Cell>
<Cell ss:StyleID="s117"><Data ss:Type="Number"><fmt:formatNumber value="${item.AMOUNT_CALC}" type="number"/></Data></Cell>
<Cell ss:StyleID="s117"><Data ss:Type="Number"><fmt:formatNumber value="${item.BUDGET_ASIGN}" type="number"/></Data></Cell>
<Cell ss:StyleID="s108"><Data ss:Type="Number"><fmt:formatNumber value="${item.CR_RT_AVRG}" type="number"/></Data></Cell>
<Cell ss:StyleID="s108"><Data ss:Type="Number"><fmt:formatNumber value="${item.RD_VAL_SM}" type="number"/></Data></Cell>
<Cell ss:StyleID="s108"><Data ss:Type="Number"><fmt:formatNumber value="${item.IRI_VAL_SM}" type="number"/></Data></Cell>
<Cell ss:StyleID="s113"><Data ss:Type="Number"><fmt:formatNumber value="${item.CALC_GPCI}" type="number"/></Data></Cell>
<Cell ss:StyleID="s113"><Data ss:Type="Number"><fmt:formatNumber value="${item.DMG_VAL}" type="number"/></Data></Cell>
</c:if>
   </Row>
</c:forEach>
 <Row ss:AutoFitHeight="0">
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s115"/>
    <Cell ss:StyleID="s115"/>
    <Cell ss:StyleID="s115"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s117"><Data ss:Type="Number"><fmt:formatNumber value="${total_amount}" type="number"/></Data></Cell>
    <Cell ss:StyleID="s117"><Data ss:Type="Number"><fmt:formatNumber value="${total_fix_budget_asign}" type="number"/></Data></Cell>
    <Cell ss:StyleID="s114"/>
    <Cell ss:StyleID="s114"/>
   </Row>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <Print>
    <ValidPrinterInfo/>
    <HorizontalResolution>300</HorizontalResolution>
    <VerticalResolution>300</VerticalResolution>
   </Print>
   <Selected/>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveRow>10</ActiveRow>
     <ActiveCol>10</ActiveCol>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>