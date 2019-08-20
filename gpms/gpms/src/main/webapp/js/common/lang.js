
var COMMON_LANG = (function($, undefined){
	
	/**	
	* @description input에 key 입력시 숫자만 나오도록 한다.
	* @param {Object} _oInput : 대상 input
	*/
	var onlyNumber = function(_oInput){
		var regexp = /[^0-9]/gi;
		v = $(_oInput).val();

		if (regexp.test(v)) {
			$(_oInput).val(v.replace(regexp, ''));
		}
	};
	
	/**	
	* @description key event를 이용하여 숫자 입력 여부를 체크한다.
	*/
	var fn_checkNumber = function(e){
		e = e || window.event;
		var key = (e.which) ? e.which : e.keyCode;
		var ctrlKey = e.ctrlKey;

		// ctrl 키
		if (ctrlKey)
			return;
		// 보조키 (전체선택, 복사, 붙여넣기, 잘라내기, 다시실행, 실행취소)
		else if ((ctrlKey && key == 65) || (ctrlKey && key == 67)
				|| (ctrlKey && key == 86) || (ctrlKey && key == 88)
				|| (ctrlKey && key == 89) || (ctrlKey && key == 90))
			return;
		// 숫자일 때
		else if ((48 <= key && key <= 57) || (96 <= key && key <= 105))
			return;
		// backspace, delete, left, right 키
		else if (key == 8 || key == 46 || key == 37 || key == 39 || key == 9
				|| key == 144 || key == 16)
			return;
		// 그 외
		else {
			alert("숫자만 입력 가능합니다");
			return false;
		}
	};
	
	/**	
	* @description 날짜 여부를 체크한다.
	* @param {String} _oDateid : 날짜 여부 체크 대상 Input ID
	* @param {String} _oFormat : 날짜 체크 format
	*/
	var fn_checkDate = function(_oDateid, _oFormat){
		var orgFormat = "yyyy-MM-dd";
		if(isnullempty(_oFormat)==false){
			orgFormat = _oFormat;
		}
		var currentDate, beforeDate;
		try {
			currentDate = Date.parseString( $("#"+_oDateid).val(), orgFormat);
			
			if(currentDate == null){return false;}
			
			return true;
		} catch (e) {
			return false;
		}
		return false;
	};
	
	/**	
	* @description key event를 이용하여 숫자와 '.'의 입력 여부를 체크한다.
	*/
	var fn_checkNumDot = function(e){
		e = e || window.event;
		var key = (e.which) ? e.which : e.keyCode;
		var ctrlKey = e.ctrlKey;

		// ctrl 키
		if (ctrlKey)
			return;
		// 보조키 (전체선택, 복사, 붙여넣기, 잘라내기, 다시실행, 실행취소)
		else if ((ctrlKey && key == 65) || (ctrlKey && key == 67)
				|| (ctrlKey && key == 86) || (ctrlKey && key == 88)
				|| (ctrlKey && key == 89) || (ctrlKey && key == 90))
			return;
		// 숫자 또는 . 일 때
		else if ((48 <= key && key <= 57) || (96 <= key && key <= 105)
				|| key == 110 || key == 190)
			return;
		// backspace, delete, left, right, tab, numlock, shift 키
		else if (key == 8 || key == 46 || key == 37 || key == 39 || key == 9
				|| key == 144 || key == 16)
			return;
		// 그 외
		else {
			alert("숫자 또는 .만 입력 가능합니다");
			return false;
		}
	};
	
	/**	
	* @description 앞/뒤 공백을 제거한다
	* @param {String} _oText : 제거 대상 text
	*/
	var trimdata = function(_oText){
		if (_oText == null || _oText == undefined || _oText == "")
			return "";
		return _oText.replace(/^\s+|\s+$/g, '');
	};
	
	/**	
	* @description 왼쪽 공백을 제거한다
	* @param {String} _oText : 제거 대상 text
	*/
	var ltrimdata = function(_oText){
		if (_oText == null || _oText == undefined || _oText == "")
			return "";
		return _oText.replace(/^\s+/, '');
	};
	
	/**	
	* @description 오른쪽 공백을 제거한다
	* @param {String} _oText : 제거 대상 text
	*/
	var rtrimdata = function(_oText){
		if (_oText == null || _oText == undefined || _oText == "")
			return "";
		return _oText.replace(/\s+$/, '');
	};
	
	/**	
	* @description 전체 공백을 제거한다
	* @param {String} _oText : 제거 대상 text
	*/
	var fulltrimdata = function(_oText){
		if (_oText == null || _oText == undefined || _oText == "")
			return "";
		return _oText.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g, '').replace(/\s+/g, ' ');
	};
	
	/**	
	* @description null 여부를 체크한다.
	* @param {Object} _obj : 체크 대상 object
	*/
	var isnullempty = function(_obj){
		if (_obj == undefined || _obj == null || _obj == "" || _obj == "null")
			return true;
		return false;
	};
	
	/**	
	* @description 배열안의 값 존재 여부를 체크한다. 
	* @param {Array} _oList : 체크 대상 배열
	* @param {Object} _obj : 찾고자 하는 대장
	*/
	var contains = function(_oList, _obj){
		for (var i = 0; i < _oList.length; i++) {
			if (_oList[i] === _obj) {
				return true;
			}
		}
		return false;
	};
	
	/**	
	* @description null이 아닌 여부를 체크한다.
	* @param {Object} _obj : 체크 대상 object
	*/
	var isnotempty = function(_obj){
		if (_obj == undefined || _obj == null || _obj == "" || _obj == "null")
			return false;
		return true;
	};
	
	var getByteLength = function(s, b, i, c){
		for (b = i = 0; c = s.charCodeAt(i++); b += c >> 11 ? 3 : c >> 7 ? 2 : 1);
		return b;
	};
	
	var getParameterByName = function(_oName){
		_oName = _oName.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
		var regex = new RegExp("[\\?&]" + _oName + "=([^&#]*)"), results = regex.exec(location.search);
		return results == null ? "" : decodeURI(results[1].replace(/\+/g, " "));
	};
	
	var formatCurrency = function(_oNum, _oCurrency, _oFixed){
		if (_oCurrency != null && _oCurrency != undefined) {
			// return currency + " " +
			// Number(n).toFixed(0).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
			if (_oFixed > 0) {
				return _oCurrency
						+ " "
						+ Number(_oNum).toFixed(_oFixed).replace(/(\d)(?=(\d{3})+\.)/g,
								"$1,").split(".")[0];
			}
			return _oCurrency
					+ " "
					+ Number(_oNum).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,")
							.split(".")[0];
		}
		// return Number(n).toFixed(0).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
		if (_oFixed > 0) {
			return Number(_oNum).toFixed(_oFixed).replace(/(\d)(?=(\d{3})+\.)/g, "$1,")
					.split(".")[0];
		}
		return Number(_oNum).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,")
				.split(".")[0];
	};
	
	var formatDate = function(_oDate, _oOrgFormat, _oTgtFormat){
		if (_oDate != null && _oDate != "" && _oDate != "null") {
			return Date
					.parseString(_oDate.substring(0, _oOrgFormat.length), _oOrgFormat)
					.format(_oTgtFormat);
		}
		return "";
	};
	
	var getJQGridSelectRowID = function(_oGridid){
		var selr = jQuery('#' + _oGridid).jqGrid('getGridParam', 'selrow');
		if (selr) {
			return selr;
		}
		return null;
	}
	
	var getJQGridSelectRowData = function(_oGridid){
		var selr = getJQGridSelectRowID(_oGridid);
		if (isnullempty(selr) == false)
			return jQuery('#' + _oGridid).getRowData(selr);
		return null;
	}
	
	var getJQGridRowData = function(_oGridid, _oRowindex){
		return jQuery('#' + _oGridid).getRowData(_oRowindex);
	}
	
	var daydiff = function(_oFirst, _oSecond){
		return (_oSecond - _oFirst) / (1000 * 60 * 60 * 24);
	}
	
	var pad = function(_oN, _oWidth, _oZ){
		_oZ = _oZ || '0';
		_oN = _oN + '';
		return _oN.length >= _oWidth ? _oN : new Array(_oWidth - _oN.length + 1).join(_oZ) + _oN;
	}
	
	return {
		onlyNumber				:		onlyNumber,
		
		fn_checkNumber			:		fn_checkNumber,
		fn_checkNumDot			:		fn_checkNumDot,
		fn_checkDate			:		fn_checkDate, //cntrwkRegist / cntrwkUpdate
		
		trimdata				:		trimdata,
		ltrimdata				:		ltrimdata,
		rtrimdata				:		rtrimdata,
		fulltrimdata			:		fulltrimdata,
		
		isnullempty				:		isnullempty,
		isnotempty				:		isnotempty,
		contains				:		contains,
		
		getByteLength			:		getByteLength,
		getParameterByName		:		getParameterByName,
		
		formatCurrency			:		formatCurrency,
		formatDate				:		formatDate,
		
		getJQGridSelectRowID	:		getJQGridSelectRowID,
		getJQGridSelectRowData	:		getJQGridSelectRowData,
		getJQGridRowData		:		getJQGridRowData,
		
		daydiff					:		daydiff,
		pad						:		pad
	}
	
}(jQuery));


jQuery.fn.extend({
	readonly : function(isReadonly) {
		if (isReadonly) {
			$(this).prop("readonly", true);
			$(this).addClass("readonly");
		} else {
			$(this).prop("readonly", false);
			$(this).removeClass("readonly");
		}
	},
	kodatepicker : function() {
		$(this).datepicker(
				{
					dateFormat : 'yy-mm-dd',
					prevText : '이전 달',
					nextText : '다음 달',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					showMonthAfterYear : true,
					yearSuffix : '년',
					showOn : "button",
					buttonImage : "/gpms/images/content/btn_calendar.gif",
					buttonImageOnly : true,
					buttonText : "날짜선택"
				});
		$(this).prop("maxlength", 10);
	},
	afterme : function(selector) {
		$(this).change(
				function() {
					var orgFormat = "yyyy-MM-dd";
					var currentDate, beforeDate;
					try {
						currentDate = Date.parseString($(this).val().substring(
								0, 10), orgFormat);
					} catch (e) {
						alert("날짜 형식(yyyy-MM-dd)이 잘못 되었습니다.");
						$(this).val("");
						return;
					}
					try {
						beforeDate = Date.parseString($(selector).val()
								.substring(0, 10), orgFormat);
					} catch (e) {
						alert("날짜 형식(yyyy-MM-dd)이 잘못 되었습니다.");
						$(selector).val("");
						return;
					}
					if (currentDate < beforeDate) {
						alert("선택한 날짜가 시작 날짜보다 작을 수 없습니다.");
						$(this).val("");
						return;
					}
					// alert($(this).val());
					// alert($(selector).val());
				});
	},
	beforeme : function(selector) {
		$(this).change(
				function() {
					var orgFormat = "yyyy-MM-dd";
					var currentDate, beforeDate;
					try {
						currentDate = Date.parseString($(this).val().substring(
								0, 10), orgFormat);
					} catch (e) {
						alert("날짜 형식(yyyy-MM-dd)이 잘못 되었습니다.");
						$(this).val("");
						return;
					}
					try {
						beforeDate = Date.parseString($(selector).val()
								.substring(0, 10), orgFormat);
					} catch (e) {
						alert("날짜 형식(yyyy-MM-dd)이 잘못 되었습니다.");
						$(selector).val("");
						return;
					}
					if (beforeDate != null && (currentDate > beforeDate)) {
						alert("선택한 날짜가 종료 날짜보다 클 수 없습니다.");
						$(this).val("");
						return;
					}
				});
	}
});

if (!String.prototype.includes) {
	String.prototype.includes = function() {
		return String.prototype.indexOf.apply(this, arguments) !== -1;
	};
}
 