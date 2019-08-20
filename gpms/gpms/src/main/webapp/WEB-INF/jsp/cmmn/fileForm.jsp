<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>

	<c:when test = "${file_mode == 'VIEW'}">
		<c:if test = "${file_info.FILE_NM != null && file_info.FILE_NM != ''}">
		<%-- <a href="#" onclick="javascript:cmFileDownloadByNo('${file_info.FILE_NO}');"><c:out value="${file_info.ORGINL_FILE_NM}"/></a> --%>
		<c:if test = "${file_type == 'IMAGE'}">
			<c:out value="${file_info.ORGINL_FILE_NM}"/>
			<br/>
				<a href="#" id="${file_column}_IMG_LINK" class='gridBtnGreen' onclick="COMMON_UTIL.fn_show_imgScreen('imgModal', 'imgBox', '${file_path}${file_info.FILE_COURS}/${file_info.FILE_NM}');">
				<font color=white>보기</font>
				<img src="${file_path}${file_info.FILE_COURS}/${file_info.FILE_NM}" id="${file_column}_IMG"  alt="공사 전후 사진" style="display:none;">
				</a>
				<script>$("#${file_column}_IMG").load(function() {COMMON_UTIL.cmImageResize('${file_column}_IMG','100','100');});</script>
		</c:if>
		<c:if test = "${file_type == 'ETC'}">
			<c:out value="${file_info.ORGINL_FILE_NM}"/>
			<%-- <a href="#" onclick="javascript:COMMON_FILE.cmFileDownloadByNo('${file_info.FILE_NO}');" class='gridBtnBlue'><font color=white>다운로드</font></a> --%>
			<a href="#" onclick="javascript:COMMON_UTIL.fileMoveUrl('attachfile/downloadFile.do?FILE_NO=${file_info.FILE_NO}');" class='gridBtnBlue'><font color=white>다운로드</font></a>
		</c:if>
		</c:if>
	</c:when>

	<c:otherwise>
		<div id="${file_column}_DP"></div>
		<c:if test = "${file_info.FILE_NM != null && file_info.FILE_NM != ''}">
		<c:if test = "${file_type == 'IMAGE'}">
			<span style="color:red;">※ 가장 최근에 올린 사진자료가 등록됩니다.</span>
			<br/>등록된 파일 : <c:out value="${file_info.ORGINL_FILE_NM}"/> <%-- <input type="checkbox" name="DEL_${file_info.FILE_COLUMN}" value="Y"/>삭제체크 --%>
			<br/><a href="#" onclick="javascript:COMMON_FILE.cmFileDownloadByNo('${file_info.FILE_NO}');" class='gridBtnBlue'><font color=white>다운로드</font></a>
			<c:if test = "${file_type == 'IMAGE'}">
				<a href="#" id="${file_column}_IMG_LINK" class='gridBtnGreen' onclick="COMMON_UTIL.fn_show_imgScreen('imgModal', 'imgBox', '${file_path}${file_info.FILE_COURS}/${file_info.FILE_NM}');">
				<font color=white>보기</font>
				<img src="${file_path}${file_info.FILE_COURS}/${file_info.FILE_NM}" id="${file_column}_IMG"  alt="공사 전후 사진" style="display:none;">
				</a>
				<script>$("#${file_column}_IMG").load(function() {COMMON_UTIL.cmImageResize('${file_column}_IMG','100','100');});</script>
			</c:if>
			<span class="gridBtnRed"><input type="checkbox" id="DEL_${file_column}" name="DEL_${file_column}" value="Y"/><font color=white>삭제체크</font></span>
		</c:if>
		<c:if test = "${file_type == 'NOTICE_FILE'}">
			<span style="color:red;">※ 가장 최근에 올린 자료가 등록됩니다.</span><br/>
			등록된 파일 : <c:out value="${file_info.ORGINL_FILE_NM}"/><br/> <%-- <input type="checkbox" name="DEL_${file_info.FILE_COLUMN}" value="Y"/>삭제체크 --%>
		</c:if>
		</c:if>
		<label for="${file_column}"></label>
		<c:if test = "${file_type == 'IMAGE'}">
			<input type="file" name="${file_column}" id="${file_column}" value="" class="MX_50 DT_FILE_${file_type}" accept="image/*" style="width: 100%;"/>
		</c:if>
		<c:if test = "${file_type == 'ETC'}">
			<input type="file" name="${file_column}" id="${file_column}" value="" class="MX_50 DT_FILE_${file_type}" accept="*" style="width: 100%;"/>
		</c:if>
		<c:if test = "${file_type == 'NOTICE_FILE'}">
			<input type="file" name="${file_column}" id="${file_column}" value="" class="MX_50 DT_FILE_${file_type}" accept="*" style="width: 100%;"/>
		</c:if>
	</c:otherwise>
</c:choose>
<!-- The Modal -->
<div id="imgModal" class="modal">
  <!-- The Close Button -->
  <span class="close" onclick="$('#imgModal').hide();">&times;</span>

  <!-- Modal Content (The Image) -->
  <img class="modal-content" id="imgBox">

  <!-- Modal Caption (Image Text) -->
  <div id="caption"></div>
</div>