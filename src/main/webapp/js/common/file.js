
var COMMON_FILE = (function($,undefined){

	var multiFileInfo = {
			_fileCnt : 0,
			_fileIns : []
	};

	/**
	* @description 파일을 다운로드를 한다. (공통)
	* @param {String} _oFileNo : 다운로드 대상 파일 번호
	*/
	var cmFileDownloadByNo = function(_oFileNo) {
		try {
			$("#download_frm").find("#FILE_NO").val( _oFileNo );
			$("#download_frm").attr("action", contextPath + "attachfile/downloadFile.do");
			$("#download_frm").attr("target","proc_frm");
			$("#download_frm").submit();
		}catch(E){alert(E);}
	}

	/**
	* @description 파일을 생성한다. (Multiple)
	* @param {String} _oDivId : 파일 리스트 div id
	* @param {String} _oMaxCnt : 최대 업로드 파일 개수
	*/
	var addMultiFile = function(_oDivId, _oInputId, _oMaxCnt){
		var file_len = $(_oDivId).find("span[name=filename]").length;

        if (_oMaxCnt != undefined && _oMaxCnt != null) {

            if(file_len >= _oMaxCnt) {

                alert("파일 추가는 최대 " + _oMaxCnt + "개 까지만 가능합니다.");
                return;
            }
        }

        $(_oInputId).trigger("click");
	}

	/**
	* @description 등록할 파일 목록을 세팅한다. (Multiple)
	* @param {String} divId : 검사 대상 div id
	* @param {String} file_ext : 검사 대상 확장자명
	* @param {String} file_ext : 검사 대상 확장자명
	* @param {String} file_ext : 검사 대상 확장자명
	* @param {String} file_ext : 검사 대상 확장자명
	*/
	var setMultiFiles = function(_oDivId, _oInfo, _oExtn, _oDupCheck, _oMaxCnt){
		var isFileChk = null;
    	var isCntChk = true;
    	var fileLen = $(_oInfo).get(0).files.length;
    	var file_ext = {};
    	var tagFileLength = $(_oDivId).find("ul[name=fileSet] li").length;

    	if(_oMaxCnt != undefined && _oMaxCnt != null) {

    		var cntChk = fileLen + tagFileLength;

    		if(cntChk > _oMaxCnt) {

    		    alert("파일개수는 최대 " + _oMaxCnt + "개 까지만 가능합니다.");
    			isCntChk = false;
    			return;
    		}
    	}

    	if(isCntChk){

    		for(var i=0; i< fileLen; i++) {

        		file_ext[i] = $(_oInfo).get(0).files[i].name.split('.').pop().toLowerCase();
        	}

            if(_oInfo != undefined && _oInfo != null && $(_oInfo).length > 0 ) {
            	//ext : 선택 가능한 확장자
            	if(_oExtn != undefined && _oExtn != null) {
                   	//extn : 선택 가능한 확장자 리스트   /     file_ext : 선택 된 확장자
            		for(var i in file_ext) {

	            		if($.inArray(file_ext[i], _oExtn) == '-1') {

	            		    //alert(' 선택할 수 없는 확장자가 포함되어 있습니다. ');
	            			alert(' 선택할 수 없는 확장자가 포함되어 있습니다./n[ ' + _oExtn + ' ] 를 선택 해 주세요.');
	            		    //<br>[  ' + extn + '  ] 를 선택 해 주세요.'
	                   		isFileChk = false;
	                   		break;
	                   	}

	            		if((fileLen-1) == i) {

	            			isFileChk = true;
	            		}
            		}

            		if(isFileChk) {

            			if(_oDupCheck == "Y") {

	            			var uniqueNames = [];

							$.each(file_ext, function(i, el) {

								if($.inArray(el, uniqueNames) === -1) {

									uniqueNames.push(el);

								} else {

								    alert('동일한 확장자는 선택할 수 없습니다.');
			                   		isFileChk = false;
			                   		return false;
								}
							});

            			}

						if(isFileChk) {
							//dupCheck : 'Y' 일 경우 올려진 파일과 선택 된 파일의 확장자 중복체크를 한다.
							if(_oDupCheck != undefined && _oDupCheck != null && _oDupCheck == 'Y') {

			            		for(var i in file_ext) {

			            			isFileChk = dupChk(_oDivId, file_ext[i]);

			            			if(!isFileChk) {
			            				break;
			            			}
			            		}

			               	} else {

			               		isFileChk = true;
			               	}
						}
            		}

           		} else {
                 	//dupCheck : 'Y' 일 경우 올려진 파일과 선택 된 파일의 확장자 중복체크를 한다.
           			if(_oDupCheck != undefined && _oDupCheck != null && _oDupCheck == 'Y') {

                		for(var i in file_ext) {

                			isFileChk = dupChk(_oDivId, file_ext[i]);

                			if(!isFileChk) {
                				break;
                			}
                		}

                   	} else {

                   		isFileChk = true;
                   	}
           		}

            	//체크내역 이상 없을 시 선택 된 파일정보 추가
            	if(isFileChk) {

                	//선택 된 파일에 대한 정보 및 이름을 표출하기 위한 HTML
            		var index = multiFileInfo._fileCnt;
                    var fileArr = $(_oInfo).get(0).files;
                    var fileLength = fileArr.length;

                    for(var i = 0; i < fileLength; i++) {

	                    var fileNm = $(_oInfo).get(0).files[i].name;

	                    if(checkDupleFile(fileNm)){
	                    	alert(fileNm + "는 이미 선택된 파일입니다.");
	                    	continue;
	                    }

	                    var shpTitle = "";

	                    var fileExt = fileNm.substring(fileNm.lastIndexOf('.'), fileNm.length).toLowerCase();

	                    var fileData = '<li id="file_' + index + '">'
	                             	 + '    <span id="fileNm_' + index + '" name="fileNm" class="filename">' + fileNm + '</span>'
	                             	 + '	<a href="#" class="delbtn" id="fileDel_' + index + '" onclick="COMMON_FILE.delMultiFile(this)"><img src="' + contextPath + 'images/ic_del.png" alt="삭제" /></a>'
	                             	 + '</li>';

	                    $(_oDivId).find("ul[name=fileSet]").append(fileData);

	                    //선택 한 파일 정보
	                    multiFileInfo._fileIns[index] = ($(_oInfo).get(0).files[i]);
	                    index++;
	                    multiFileInfo._fileCnt = index;
                    }
            	}
            }

            //요청 된 파일정보 초기화
            //$(_oDivId).find("input[name=files]").val("");
            //$(_oInfo).val('');
    	}
	};

	/**
	* @description 파일 확장자 중복을 체크한다.
	* @param {String} divId : 검사 대상 div id
	* @param {String} file_ext : 검사 대상 확장자명
	*/
	var dupChk = function(_oDivId, _oFileExt) {

    	var file_arr = new Array();
    	var rtn = null;

   		//현재 등록 된 파일확장자명 호출 및 비교대상 배열에 담음
       	for(var i=0; $(_oDivId).find('[name=fileNm]').length > i; i++) {

       		var fileNm = $(_oDivId).find('[name=fileNm]:eq('+ i +')').text();

       		if(fileNm != undefined && fileNm != null) {

       			file_arr.push(fileNm.split('.').pop().toLowerCase());
       		}

   		}
       	//동일한 확장자 있을경우 등록되면 안됨
       	if(file_arr.indexOf(_oFileExt) != '-1') {

       	    alert('동일한 확장자는 등록할 수 없습니다.');
       		rtn = false;

       	} else {
       		rtn = true;
       	}
       	return rtn;
    };

    /**
	* @description 파일 이름 중복 체크한다. (Multiple)
	* @param {String} _oFileNm : 검사 대상 파일명
	*/
    var checkDupleFile = function(_oFileNm) {
    	var result = false;
    	for(var i in multiFileInfo._fileIns){
    		var file = multiFileInfo._fileIns[i];

    		if(multiFileInfo._fileIns[i].name == _oFileNm){
    			return true;
    		}
    	}
    	return result;
    }

    /**
	* @description 파일을 삭제한다. (Multiple)
	* @param {Object} _oInfo : 삭제 대상
	*/
    var delMultiFile = function(_oInfo) {

        var id = $(_oInfo).parents().get(0).id;
        var index = id.split("_")[1];

        multiFileInfo._fileIns[index] = "";

        $("#"+id).remove();
    };

    /**
	* @description 파일 설정을 초기화 한다. (Multiple)
	* @param {String} _oDivId : 파일 리스트 div id
	* @param {String} _oMaxCnt : 파일 inut id
	*/
    var clearMultiFile = function(_oDivId, _oInputId){
    	multiFileInfo = {
    			_fileCnt : 0,
    			_fileIns : []
    	};

    	$(_oDivId).find("ul[name=fileSet] li").remove();
    	$(_oInputId).val("");
    }

    /**
	* @description 등록 대상 파일 목록을 가져온다. (Multiple)
	*/
    var getMultiFileIns = function(){
    	return multiFileInfo._fileIns;
    }

	return {
		cmFileDownloadByNo				:			cmFileDownloadByNo,

		addMultiFile					:			addMultiFile,
		setMultiFiles					:			setMultiFiles,
		delMultiFile					:			delMultiFile,
		clearMultiFile					:			clearMultiFile,
		getMultiFileIns					:			getMultiFileIns
	}

}(jQuery));
