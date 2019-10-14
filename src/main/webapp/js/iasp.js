//노선 번호 변경 시 노선명 자동 조회
	function fn_change_sigungu() {
	    var adm_code = $("#ADM_CODE").val();

	    if(adm_code == "") {
	        $("#ADM_CODE").val("");
	        return;
	    }
	    
	  
	    $.ajax({
	        url: contextPath + 'gmap/selectGuLocation_iasp.do'
	        ,type: 'POST'
	        ,dataType: 'json'
	//        ,contentType : 'application/json'
	        ,data : {'ADM_CODE' : adm_code}
	    	,cache : false
	        ,success: function(data){
	        	debugger;
	        	var txtHtml;
	        	if(data.admCodeGuList.length==0){
	        		 txtHtml += "<option value=''></option>";
	        	}else{
	        		 for(var i=0; i < data.admCodeGuList.length; i++){
		                   txtHtml += "<option value='" + data.admCodeGuList[i].bjcd+ "'>" + data.admCodeGuList[i].name + "</option>";
		               }
	        	}
	        	  
	        	   $("#GU_CODE").html(txtHtml);
	        	   
	        }
	        ,error: function(a,b,msg){
	            $("#GU_CODE").val("");
	        }
	    });
	    
	    return false;
	 
	}
	
	function fn_change_newsigungu() {
	    var adm_code = $("#ADM_CODE1").val();
	    if(adm_code == "") {
	        $("#ADM_CODE1").val("");
	        return;
	    }
	    debugger;
	    
	  
	    $.ajax({
	        url: contextPath + 'gmap/selectnewGuLocation_iasp.do'
	        ,type: 'POST'
	        ,dataType: 'json'
	//        ,contentType : 'application/json'
	        ,data : {'ADM_CODE1' : adm_code}
	        ,success: function(data){
	        	debugger;
	        	var txtHtml;
	        	if(data.admCodeGuList.length==0){
	        		 txtHtml += "<option value=''></option>";
	        	}else{
	        		 for(var i=0; i < data.admCodeGuList.length; i++){
		                   txtHtml += "<option value='" + data.admCodeGuList[i].bjcd+ "'>" + data.admCodeGuList[i].name + "</option>";
		               }
	        	}
	        	   $("#GU_CODE1").html(txtHtml);
	        	   
	        }
	        ,error: function(a,b,msg){
	            $("#GU_CODE1").val("");
	        }
	    });
	    
	    return false;
	 
	}
	

	var marker;
	var mapLocIdx = 0;
	var object = null;
	
	/**
	 * 통합주소검색
	 * **/
	function searchPlaces(obj){

	    // 총 검색 갯수 처리를 위한 ojbect
	    object = obj;

		// 경기도 내에 있는 키워드를 검색하기 위해 "경기도"을 덧붙여 검색
		var keyword = "경기도 " + document.getElementById('keyword').value;

	    if (!keyword.replace(/^\s+|\s+$/g, '')) {
	        alert('키워드를 입력해주세요!');
	        return false;
	    }

	    // 장소 검색 객체를 생성합니다
	    var ps = new parent.daum.maps.services.Places();

		// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
		ps.keywordSearch( keyword, placesSearchCB );
	}
	/**
	 * 경기도 주소검색
	 */
	function searchJusoPlaces(obj){
	    // 총 검색 갯수 처리를 위한 ojbect
	    object = obj;

	    var juso = '';
	    	juso += $("#ADM_CODE option:selected").text()+
	    	$("#GU_CODE option:selected").text()+
	    	$("#DONG").val()+ 
	    	$("#BON").val();
	    	if($("#BU").val()!=''){
	    		
	    		juso += '-'+$("#BU").val();
	    	}else{
	    		juso+= $("#BU").val();
	    	}
	    // 장소 검색 객체를 생성합니다
	    var ps = new parent.daum.maps.services.Places();

		// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
		ps.keywordSearch( juso, placesSearchCB );
	}
	/**
	 * 경기도 주소검색
	 */
	function searchNewJusoPlaces(obj){
	    // 총 검색 갯수 처리를 위한 ojbect
	    object = obj;

	    var juso = '';
	    	juso += $("#ADM_CODE option:selected").text()+
	    	$("#GU_CODE option:selected").text()+
	    	$("#RO").val()+ 
	    	$("#BON").val();
	    	if($("#BU").val()!=''){
	    		
	    		juso += '-'+$("#BU").val();
	    	}else{
	    		juso+= $("#BU").val();
	    	}
	    // 장소 검색 객체를 생성합니다
	    var ps = new parent.daum.maps.services.Places();

		// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
		ps.keywordSearch( juso, placesSearchCB );
	}

	//장소검색이 완료됐을 때 호출되는 콜백함수 입니다
	function placesSearchCB(data, status, pagination) {

		if ( status === parent.daum.maps.services.Status.OK ) {

	        // 정상적으로 검색이 완료됐으면
	        // 검색 목록과 마커를 표출합니다
	        displayPlaces(data);

	        // 페이지 번호를 표출합니다
	        displayPagina
	        tion(pagination);

	        // JOY. 2017. 11. 24. 검색 결과 갯수
	        object.parent().siblings().find("strong").text(pagination.totalCount);

	    } else if ( status === parent.daum.maps.services.Status.ZERO_RESULT ) {

	        alert('검색 결과가 존재하지 않습니다.');
	        return;

	    } else if ( status === parent.daum.maps.services.Status.ERROR ) {

	        alert('검색 결과 중 오류가 발생했습니다.');
	        return;

	    }

		// 팝업창 사이즈 조절
		fnPopupResize(550);
		$("#result").slideDown("slow");

	}


	//검색 결과 목록과 마커를 표출하는 함수입니다
	function displayPlaces(places) {

	    var listEl = document.getElementById('placesList'),
	    menuEl = document.getElementById('menu_wrap'),
	    fragment = document.createDocumentFragment(),
	    bounds = new parent.daum.maps.LatLngBounds(),

	    listStr = '';

	    // 검색 결과 목록에 추가된 항목들을 제거합니다
	    removeAllChildNods(listEl);

	    // 지도에 표시되고 있는 마커를 제거합니다
	    removeMarker();

	    for ( var i = 0; i < places.length; i++ ) {

	        // 마커를 생성하고 지도에 표시합니다
	        var placePosition = new parent.daum.maps.LatLng(places[i].y, places[i].x),
	            marker = addMarker(placePosition, i),
	            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        bounds.extend(placePosition);

	        fragment.appendChild(itemEl);

	    }

	    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
	    listEl.appendChild(fragment);
	    menuEl.scrollTop = 0;

	    //다음지도에서 구한 bounds에서 남,서,동,북 값을 얻어와 재 bound처리를 해준다
	    var sw =  bounds.getSouthWest(parent.gMap);
	    var ne =  bounds.getNorthEast(parent.gMap);
	    var oSw = new OpenLayers.LonLat(sw.getLng(), sw.getLat());
	    var oNe = new OpenLayers.LonLat(ne.getLng(), ne.getLat());
	    oSw.transform(fn_get_daumMap().projection.projCode, parent.gMap.getProjection());
	    oNe.transform(fn_get_daumMap().projection.projCode, parent.gMap.getProjection());

	    parent.gMap.zoomToExtent([oSw.lon, oSw.lat, oNe.lon, oNe.lat]);
	}

	//검색결과 항목을 Element로 반환하는 함수입니다
	function getListItem(index, places) {

	    var el = document.createElement('li'),
	        itemStr = '<span class="markerbg marker_' + ( index + 1 ) + '"></span>'
	                + '<div class="info" onclick="fn_move_daumPosition(' + places.x + ',' + places.y + ')">'
	                + '   <h5>' + places.place_name + '</h5>';

	    if ( places.road_address_name ) {

	        itemStr += '    <span>' + places.road_address_name + '</span>'
	                 + '    <span class="jibun gray">' + places.address_name + '</span>';

	    } else {

	        itemStr += '    <span>' + places.address_name + '</span>';

	    }

	    itemStr += '  <span class="tel">' + places.phone + '</span>'
	             + '</div>';

	    el.innerHTML = itemStr;
	    el.className = 'item';

	    return el;
	}

	//마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position, idx, title) {
	      var imageSrc = 'http://i1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
	          imageSize = new parent.daum.maps.Size(36, 37),  // 마커 이미지의 크기
	          imgOptions =  {
	              spriteSize : new parent.daum.maps.Size(36, 691), // 스프라이트 이미지의 크기
	              spriteOrigin : new parent.daum.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
	              offset: new parent.daum.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
	          },
	          markerImage = new parent.daum.maps.MarkerImage(imageSrc, imageSize, imgOptions),
	              marker = new parent.daum.maps.Marker({
	              position: position, // 마커의 위치
	              image: markerImage
	          });
	      marker.setMap(fn_get_daumMap().map); // 지도 위에 마커를 표출합니다
	      parent.markersLoc.push(marker);  // 배열에 생성된 마커를 추가합니다

	      return marker;
	}


	//지도 위에 표시되고 있는 마커를 모두 제거합니다
	function removeMarker() {
	    for ( var i = 0; i < parent.markersLoc.length; i++ ) {
	        parent.markersLoc[i].setMap(null);
	    }
	    parent.markersLoc = [];
	}

	//검색결과 목록 하단에 페이지번호를 표시는 함수입니다
	function displayPagination(pagination) {

	      var paginationEl = document.getElementById('pagination'),
	          fragment = document.createDocumentFragment(),
	          i;

	      // 기존에 추가된 페이지번호를 삭제합니다
	      while (paginationEl.hasChildNodes()) {
	          paginationEl.removeChild (paginationEl.lastChild);
	      }

	      for (i=1; i<=pagination.last; i++) {
	          var el = document.createElement('a');
	          el.href = "#";
	          el.innerHTML = i;

	          if (i===pagination.current) {
	              el.className = 'on';
	          } else {
	              el.onclick = (function(i) {
	                  return function() {
	                      pagination.gotoPage(i);
	                  }
	              })(i);
	          }

	          fragment.appendChild(el);
	      }
	      paginationEl.appendChild(fragment);

	}

	//검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
	//인포윈도우에 장소명을 표시합니다
	function displayInfowindow(marker, title) {
	    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

	    infowindow.setContent(content);
	    infowindow.open(parent.daumMap, marker);
	}

	//검색결과 목록의 자식 Element를 제거하는 함수입니다
	function removeAllChildNods(el) {
	    while (el.hasChildNodes()) {
	        el.removeChild (el.lastChild);
	    }
	}

	/**
	 * @memberof MAP
	 * @method
	 * @description 배경지도(다음 맵) 리턴
	 * @author 임상수(2015.07.31)
	 * @returns {String} 배경지도(다음 맵)
	 */
	var fn_get_daumMap = function() {
		return parent.daumMap;
	}

	/**
	* @method
	* @description 다음 키워드 API를 이용한 장소 이동
	* @author 이상호(2016.02.25)
	* @param {String} _sLat - daumMap의 위도 좌표
	* @param {String} _sLng - daumMap의 경도 좌표
	*/
	var fn_move_daumPosition = function (_sLat, _sLng){
		//oLatLng = new OpenLayers.LonLat(_sLng, _sLat);
	    oLatLng = new OpenLayers.LonLat(_sLat, _sLng);
		var oTrans_sLatLng = oLatLng.transform(fn_get_daumMap().projection.projCode, parent.gMap.getProjection());
		var oDaumMap = fn_get_daumMap();
		//parent.gMap.zoom =9; // 유영기. 이동 후 마우스 이동 안되는 에러발생으로 인해 삭제

		parent.gMap.setCenter([oTrans_sLatLng.lon, oTrans_sLatLng.lat], 9);


		//parent.gMap.setCenterScale([oTrans_sLatLng.lon, oTrans_sLatLng.lat], parent.gMap.zoom);

		//parent.gMap.setCenter([oTrans_sLatLng.lon, oTrans_sLatLng.lat], oDaumMap.getLevel());
		//oDaumMap.setCenter(new parent.daum.maps.LatLng(_sLat, _sLng));
		//oDaumMap.syncMap(parent.gMap);

		/*var marker = new daum.maps.Marker({
			map: oDaumMap.map,
			position: new daum.maps.LatLng(_sLat, _sLng)
		});*/
	}