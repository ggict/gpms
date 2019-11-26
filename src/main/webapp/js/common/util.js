
var progress_spinner;// 프로그래스 스피너

//startswith 함수 추가
if(!String.prototype.startsWith){
    String.prototype.startsWith = function(searchString, position) {
        position = position || 0;
        return this.indexOf(searchString, position) === position;
    };
}

//endswith 함수 추가
if(!String.prototype.endsWith){
    String.prototype.endsWith = function(str){
        if (this.length < str.length) { return false; }
        return this.lastIndexOf(str) + str.length == this.length;
    };
}

//lpad
$.strPad = function(i,l,s) {
    var o = i.toString();
    if (!s) { s = '0'; }
    while (o.length < l) {
        o = s + o;
    }
    return o;
};
$.fn.center = function () {
    this.css("position","absolute");
    this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) +
                                                $(window).scrollTop()) + "px");
    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) +
                                                $(window).scrollLeft()) + "px");
    return this;
};
//객체가 속해 있는 클래스 목록 가져오기
$.fn.getClasses = function(){
    var ca = this.attr('class');
    var rval = [];
    if(ca && ca.length && ca.split){
        ca = $.trim(ca);
        ca = ca.replace(/\s+/g,' '); /* remove doube spaces */
        rval = ca.split(' ');
    }
    return rval;
};
// 폼내부 파라메터 값을 배열로 추출 (jqgrid 용)
$.fn.cmSerializeObject = function()
{
    var o = {};
    var nm = "";
    var val = "";
    $(this).find("input[type='color'],input[type='date'],input[type='datetime-local'],input[type='email'],input[type='month'],input[type='number'],input[type='range'],input[type='search'],input[type='tel'],input[type='time'],input[type='url'],input[type='week'],input[type='hidden'], input[type='text'], input[type='password'], input[type='checkbox']:checked, input[type='radio']:checked, select,textarea").each( function(){
        nm = $(this).attr('name');
        // mask 처리 된 경우, cleanVal 함수를 통해 마스크 제거된 값을 가져오게 처리
        try {
            val = $(this).cleanVal();
        }catch(E){
            val = $(this).val();
        }

        // class 값으로 int, float, doublt 인 경우, 콤마 제거
        var cs = $(this).getClasses();

        if( cs.indexOf("DT_INT")!=-1 || cs.indexOf("DT_DOUBLE")!=-1 || cs.indexOf("DT_FLOAT")!=-1 ) {
            val = val.replace(/[^0-9\.\-]/g,'');
        }

        if(cs.indexOf("DT_DATE")!=-1) { // 날짜의 경우, '-'가 업는 형태로 DB가 구성되어 잇어서 '-' 삭제
            val = val.replace(/[\-]/g,'');
        }


        if (o[nm] !== undefined) {
            if (!o[nm].push) {
                o[nm] = [o[nm]];
            }            o[nm].push(val || '');
        }
        else {
            o[nm] =val || '';
        }

    });
    var tick = new Date().getTime();
    //o["t"+tick] =tick || '';

    delete o.undefined;

    return o;
};

$.fn.cmSerializeObjectExChkbox = function()
{
    var o = {};
    var nm = "";
    var val = "";
    $(this).find("input[type='color'],input[type='date'],input[type='datetime-local'],input[type='email'],input[type='month'],input[type='number'],input[type='range'],input[type='search'],input[type='tel'],input[type='time'],input[type='url'],input[type='week'],input[type='hidden'], input[type='text'], input[type='password'], select,textarea").each( function(){
        nm = $(this).attr('name');
        // mask 처리 된 경우, cleanVal 함수를 통해 마스크 제거된 값을 가져오게 처리
        try {
            val = $(this).cleanVal();
        }catch(E){
            val = $(this).val();
        }

        // class 값으로 int, float, doublt 인 경우, 콤마 제거
        var cs = $(this).getClasses();

        if( cs.indexOf("DT_INT")!=-1 || cs.indexOf("DT_DOUBLE")!=-1 || cs.indexOf("DT_FLOAT")!=-1 ) {
            val = val.replace(/[^0-9\.\-]/g,'');
        }

        if(cs.indexOf("DT_DATE")!=-1) { // 날짜의 경우, '-'가 업는 형태로 DB가 구성되어 잇어서 '-' 삭제
            val = val.replace(/[\-]/g,'');
        }


        if (o[nm] !== undefined) {
            if (!o[nm].push) {
                o[nm] = [o[nm]];
            }            o[nm].push(val || '');
        }
        else {
            o[nm] =val || '';
        }

    });
    var tick = new Date().getTime();
    //o["t"+tick] =tick || '';

    delete o.undefined;

    return o;
};

// url 형태로 파라미터 생성
$.fn.fnGetParameter = function()
{
    var param = "";
    var nm = "";
    var val = "";
    $(this).find("input[type='color'],input[type='date'],input[type='datetime-local'],input[type='email'],input[type='month'],input[type='number'],input[type='range'],input[type='search'],input[type='tel'],input[type='time'],input[type='url'],input[type='week'],input[type='hidden'], input[type='text'], input[type='password'], input[type='checkbox']:checked, input[type='radio']:checked, select,textarea").each( function(){
        nm = $(this).attr('name');
        // mask 처리 된 경우, cleanVal 함수를 통해 마스크 제거된 값을 가져오게 처리
        try {
            val = $(this).cleanVal();
        }catch(E){
            val = $(this).val();
        }

        // class 값으로 int, float, double 인 경우, 콤마 제거
        var cs = $(this).getClasses();

        if( cs.indexOf("DT_INT")!=-1 || cs.indexOf("DT_DOUBLE")!=-1 || cs.indexOf("DT_FLOAT")!=-1 ) {
            val = val.replace(/[^0-9\.\-]/g,'');
        }

        if(cs.indexOf("DT_DATE")!=-1) { // 날짜의 경우, '-'가 없는 형태로 DB가 구성되어 잇어서 '-' 삭제
            val = val.replace(/[\-]/g,'');
        }

        param += "&" + nm + "=" + val;

    });

    var tick = new Date().getTime();

    return param;
};

var COMMON_UTIL = (function($,undefined){

    /**
    * @description 이미지 사이즈를 조절한다.
    * @param {String} _objId : 조절 대상 이미지 id
    * @param {Integer} _oMaxWidth : 최대 넓이 값
    * @param {Integer} _oMaxHeight : 최대 높이 값
    */
    var cmImageResize = function(_objId, _oMaxWidth, _oMaxHeight){
        var width  = $('#'+_objId).width();
        var height = $('#'+_objId).height();

        if(_oMaxWidth < width) {
            newWidth  = _oMaxWidth;
            newHeight = parseInt(newWidth*height/width);

            width = newWidth;
            height = newHeight;
        }

        if(_oMaxHeight < height) {
            newHeight = _oMaxHeight;
            newWidth  = parseInt(newHeight*width/height);
        }

        $('#'+_objId).css({'height':newHeight+'px','width':newWidth+'px'});

        // 상세 이미지 원본 보기 처리
        $('#'+_objId+'_LINK').magnificPopup({
            type: 'image',
            closeOnContentClick: true,
            image: {
                verticalFit: false
            }
        });
    };

    /**
    * @description form을 초기화한다.
    * @param {String} _oFormNm : 초기화할 form ID
    */
    var cmFormReset = function(_oFormNm){
        $("#"+_oFormNm).each(function() {
            this.reset();
        });
    };

    /**
    * @description form 내의 모든 객체를 변경한다
    * @param {String} _oFormNm : 대상 form ID
    */
    var cmFormObjectInit = function(_oFrmId, noFocusScroll){
        $("#"+_oFrmId+" input[type='file'], input[type='text'], select, textarea").each(
                function(i){
                    var inputObj = this;
                    var cs = $(this).getClasses();

                    var MX = "";
                    var DT = "";
                    var DD = "";

                    $.each( cs, function(index,value){
                        try {
                            var class_nm = value;

                            if( class_nm!='' ) {
                                // CS_ 로 시작될 경우, 자리수 크기 지정
                                if( class_nm.match(/CS_/) ) {
                                    $(inputObj).attr('size', parseInt(class_nm.substr(3)));
                                }
                                // 타입
                                if( class_nm.match(/DT_/) ) {
                                    DT = class_nm.substr(3);
                                }
                                // 최대 자리수
                                if( class_nm.match(/MX_/) ) {
                                    MX = parseInt(class_nm.substr(3));
                                }
                                // 소수점 자리수
                                if( class_nm.match(/DD_/) ) {
                                    DD = parseInt(class_nm.substr(3));
                                }
                            }
                        }catch(E){ alert(E); }
                    });

                    if( DT=='DATE' ) {
                        $(inputObj).mask("0000-00-00");
                        //$(inputObj).attr('size',10);
                        $(inputObj).width(80);
                    }
                    else if( DT=='FILE_IMAGE' ) {
                        $(inputObj).change(function() {
                            var val = $(inputObj).val().toLowerCase();
                            if (val!='' && !val.match(/(?:gif|jpg|png|bmp|jpeg)$/)) {
                                alert("이미지 파일을 선택해 주십시오.");
                                $(inputObj).val('').focus();
                            }
                        });
                    }
                    else if( DT=='INT' || DT=='DOUBLE' || DT=='FLOAT' ) {
                        // 숫자 타입 마스크 처리
                        // 소숫점 입력 가능인 경우
                        if( DD>0 ) {
                            MX = MX-DD;
                            $(inputObj).keyup(function (e) {
                                if( event.keyCode!=37 && event.keyCode!=39 ) {
                                    var cur_val = this.value.replace(/[^0-9\.\-]/g,'');
                                    var x = cur_val.split('.');
                                    var x1 = x[0];

                                    if( x1.length>MX )      x1 = x1.substr(0,MX);
                                    if( x.length > 1 ) {
                                        var x2 = x[1];

                                        if( x2.length>DD )          x2 = x2.substr(0,DD);

                                        this.value = x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + "." + x2;
                                    }
                                    else {
                                        this.value = x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
                                    }
                                }
                            });

                            // --------------------------------------
                            // 초기 값에 콤마 추가
                            var init_val = $(inputObj).val();
                            var cur_val = init_val.replace(/[^0-9\.\-]/g,'');
                            var x = cur_val.split('.');
                            var x1 = x[0];

                            if( x.length > 1 ) {
                                var x2 = x[1];
                                 $(inputObj).val( x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + "." + x2 );
                            }
                            else {
                                 $(inputObj).val( x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") );
                            }
                            // --------------------------------------
                        }
                        else {
                            $(inputObj).keyup(function () {
                                if( event.keyCode!=37 && event.keyCode!=39 ) {
                                    var cur_val = this.value.replace(/[^0-9\.\-]/g,'');
                                    var x = cur_val.split('.');
                                    var x1 = x[0];

                                    if( x1.length>MX )      x1 = x1.substr(0,MX);
                                    this.value = x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
                                }
                            });

                            // --------------------------------------
                            // 초기 값에 콤마 추가
                            var init_val = $(inputObj).val();
                            var cur_val = init_val.replace(/[^0-9\.\-]/g,'');
                            var x = cur_val.split('.');
                            var x1 = x[0];
                             $(inputObj).val( x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") );
                            // --------------------------------------
                        }
                        $(inputObj).css("text-decoration", "underline").css("text-align", "right");
                        // 값이 없을때는 0값 세팅
                        if( $(inputObj).attr("readonly")==undefined && ($(inputObj).val()=='' || $(inputObj).val() == undefined) )
                            $(inputObj).val('');
                    }
                    else if(MX>0) {
                        $(inputObj).attr('maxlength', MX);
                        //20160405 ksnam 한글 자리수(2byte) 검증 추가
                        $(inputObj).keyup(fnInputLengthCheck);
                    }

                    // readonly 인 경우 배경색 회색 지정
                    if( $(inputObj).attr("readonly") != undefined )
                        $(inputObj).css("background","#E5E5E5");
                }
        );

        // 첨부파일 선택시, 추가 처리
        $("input[type='file']").change(function() {
            try {
                var input_id = $(this).attr('id');
                var filename = $(this).val().replace(/.*(\/|\\)/, '');
            } catch(E) {

            }
        });

        if (typeof noFocusScroll === 'undefined') {
        	$("#"+_oFrmId).find("input[type='text']:enabled").first().focus();
        }

    };

    /**
    * @description input 값 길이를 체크한다.
    * @param {Object} _oEventInput : 대상 Input
    */
    var fnInputLengthCheck = function(_oEventInput){
        var eventInput = _oEventInput.target;
        var inputText = $(eventInput).val();
        var inputMaxLength = $(eventInput).prop("maxlength");
        var j = 0;
        var count = 0;
        for (var i = 0; i < inputText.length; i++) {
            val = escape(inputText.charAt(i)).length;
            if (val == 6) {
                j++;
            }
            j++;
            if (j <= inputMaxLength) {
                count++;
            }
        }
        if (j > inputMaxLength) {
            $(eventInput).val(inputText.substr(0, count));
        }
    };

    /**
    * @description 이미지 확장자인지 체크한다.
    * @param {String} _oVal : 경로 및 전체 파일명
    */
    var validateExtension = function(_oVal){
       var allowedExtensions = new Array("gif","jpg","png","bmp","jpeg");
          for(var ct=0;ct<allowedExtensions.length;ct++)
          {
              sample = _oVal.toLowerCase().lastIndexOf(allowedExtensions[ct]);
              if(sample != -1){return true;}
          }
          return false;
    };

    /**
    * @description jqgrid를 초기화 한다.
    * @param {String} _oGridId : jqgrid ID
    * @param {Integer} _oHeightGap : jqgrid의 높이 gap
    */
    var cmInitGrid = function(_oGridId, _oHeightGap ){
        var iframe_h = $('#content_area',parent.document).height();
        var iframe_w = $('#content_area',parent.document).width()-5;

        $('#'+_oGridId).jqGrid('setGridHeight', iframe_h-_oHeightGap);
        $('#'+_oGridId).jqGrid('setGridWidth', iframe_w);

        $(window).resize(function() {
            var iframe_h = $('#content_area',parent.document).height();
            var iframe_w = $('#content_area',parent.document).width()-5;
        });
    };

    /**
    * @description jqgrid를 초기화 한다.(팝업창)
    * @param {String} _oGridId : jqgrid ID
    * @param {Integer} _oHeightGap : jqgrid의 높이 gap
    */
    var cmInitPopupGrid = function(_oGridId , _oHeightGap){
        var iframe_h = $(document).height();
        var iframe_w = $(document).width()-45;

        $('#'+_oGridId).jqGrid('setGridHeight', iframe_h-_oHeightGap);
        $('#'+_oGridId).jqGrid('setGridWidth', iframe_w);

        $(window).resize(function() {
            var iframe_h = $(document).height();
            var iframe_w = $(document).width()-45;

            $('#'+_oGridId).setGridHeight( iframe_h-_oHeightGap);
            $('#'+_oGridId).jqGrid('setGridWidth', iframe_w);

            if(grid_id == "dsmGrid" || grid_id == "gcmGrid"){
                $('#'+_oGridId).jqGrid('setGridWidth', 600);
            }
        });
    };

    /**
    * @description jqgrid를 초기화 한다.( 팝업창 / 좌측메뉴포함 )
    * @param {String} _oGridId : jqgrid ID
    * @param {Integer} _oHeightGap : jqgrid의 높이 gap
    * @param {Integer} _oWidthGap : jqgrid의 넓이 gap
    */
    var cmInitPopupGridIncludeLeft = function(_oGridId , _oHeightGap, _oWidthGap){
        var iframe_h = $(document).height();
        var iframe_w = $(document).width();

        $('#'+_oGridId).jqGrid('setGridHeight', iframe_h-_oHeightGap);
        $('#'+_oGridId).jqGrid('setGridWidth', iframe_w-_oWidthGap);
    };

    /**
    * @description jqgrid를 사이즈에 따라 초기화 한다.
    * @param {String} _oGridId : jqgrid ID
    * @param {String} _oContainerId : jqgrid의 container ID
    * @param {Integer} _oHeight : jqgrid의 높이
    */
    var cmInitGridSize = function(_oGridId, _oContainerId, _oHeight){
        var gap_width = 1;
        var iframe_h = $('#'+_oContainerId).height();
        var iframe_w = $('#'+_oContainerId).width()-gap_width;

        if(typeof height != "undefined" && height != "" ){
            if(iframe_h >= _oHeight){
                iframe_h = _oHeight;
            }
            else{
                iframe_h = Math.max(_oHeight,iframe_h);
            }

        }

        $('#'+_oGridId).jqGrid('setGridHeight', _oHeight);
        $('#'+_oGridId).jqGrid('setGridWidth', iframe_w);

        $(window).resize(function() {
            //var iframe_h = $('#content_area',parent.document).height();
            var iframe_w = $('#'+_oContainerId).width()-gap_width;
            $('#'+_oGridId).jqGrid('setGridWidth', iframe_w);
        });
    };

    /**
    * @description 진행 프로그래스 바를 생성한다.
    */
    var cmShowProgressBar = function(){
        var opts = {
                  lines: 13, // The number of lines to draw
                  length: 20, // The length of each line
                  width: 10, // The line thickness
                  radius: 30, // The radius of the inner circle
                  corners: 1, // Corner roundness (0..1)
                  rotate: 0, // The rotation offset
                  direction: 1, // 1: clockwise, -1: counterclockwise
                  color: '#000', // #rgb or #rrggbb or array of colors
                  speed: 1, // Rounds per second
                  trail: 60, // Afterglow percentage
                  shadow: false, // Whether to render a shadow
                  hwaccel: false, // Whether to use hardware acceleration
                  className: 'spinner', // The CSS class to assign to the spinner
                  zIndex: 2e9, // The z-index (defaults to 2000000000)
                  top: 'auto', // Top position relative to parent in px
                  left: 'auto' // Left position relative to parent in px
                };

        try {
            $('#progressbar').center();
            var target = document.getElementById('progressbar');
            progress_spinner = new Spinner(opts).spin(target);
        } catch(E) {alert(E);}
    };

    /**
    * @description 진행 프로그래스 바를 숨긴다.
    */
    var cmHideProgressBar = function(){
        try {
            progress_spinner.stop();
        } catch(E) {}
    };

    /**
    * @description 유니크한 id값을 생성한다.
    * @param {Integer} _oNum : 생성할 id값의 길이
    */
    var cmCreateRandomId = function(_oNum){
        if(!_oNum)
            _oNum = 5;

        var text = '';
        var charGroup = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

        for(var i=0; i < _oNum; i++)
        {
            text += charGroup.charAt(Math.floor(Math.random() * charGroup.length));
        }
        return text;
    };
    /**
     * @description div 팝업을 생성한다.
     * @param {String} _oDivTitle : div 상단 타이틀명
     * @param {String} _oCallUrl : 호출 url
     * @param {integer} _oWidth : div 넓이
     * @param {integer} _oHeight : div 높이
     * @param {boolean} _oModal : modal 여부
     * @param {String} _oOpenerId : div를 호출하는 opener id
     * @param {String} _oPosition : div position
     * @param {Object} _oInitData : 초기 데이터
     * @param {String} _oSeesionChek : 세션 체크 여부
     */
     var cmWindowOpenIasp = function(_oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oPosition, _oInitData , _oSeesionChek){

         var strUrl = "";

         if(_oCallUrl.startsWith(contextPath)){
             _oCallUrl = _oCallUrl.replace(contextPath, "");
         }

         if(!_oCallUrl.startsWith("/")){
             _oCallUrl = "/"+_oCallUrl;
         }


         if(_oCallUrl.indexOf("?") > -1){
             strUrl = _oCallUrl.substring(0, _oCallUrl.indexOf("?"));
         }else{
             strUrl = _oCallUrl;
         }


         $.ajax({
             url: contextPath + 'userauth/checkAuth.do'
             ,type: 'post'
             ,dataType: 'json'
             ,data : {"url" : strUrl}
             ,success: function(res){
                 if(!res.result){
                     alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
                     return;
                 }

                 _oCallUrl = contextPath + _oCallUrl.substring(1);

                 try {
                     if( _oModal ) {
                         parent.wDialogOpen( _oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oInitData   );
                     } else {
                         parent.wWindowOpen( _oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oPosition, _oInitData  );
                     }
                 } catch(E) {
                     alert('오류가 발생하였습니다. : ' +E);
                 }
             }
             ,error: function(a,b,msg){

             }
         });

     };
    /**
    * @description div 팝업을 생성한다.
    * @param {String} _oDivTitle : div 상단 타이틀명
    * @param {String} _oCallUrl : 호출 url
    * @param {integer} _oWidth : div 넓이
    * @param {integer} _oHeight : div 높이
    * @param {boolean} _oModal : modal 여부
    * @param {String} _oOpenerId : div를 호출하는 opener id
    * @param {String} _oPosition : div position
    * @param {Object} _oInitData : 초기 데이터
    * @param {String} _oSeesionChek : 세션 체크 여부
    */
    var cmWindowOpen = function(_oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oPosition, _oInitData , _oSeesionChek){

        if(_oSeesionChek == undefined || _oSeesionChek != "N"){
            fn_check_session();
        }

        var strUrl = "";

        if(_oCallUrl.startsWith(contextPath)){
            _oCallUrl = _oCallUrl.replace(contextPath, "");
        }

        if(!_oCallUrl.startsWith("/")){
            _oCallUrl = "/"+_oCallUrl;
        }


        if(_oCallUrl.indexOf("?") > -1){
            strUrl = _oCallUrl.substring(0, _oCallUrl.indexOf("?"));
        }else{
            strUrl = _oCallUrl;
        }


        $.ajax({
            url: contextPath + 'userauth/checkAuth.do'
            ,type: 'post'
            ,dataType: 'json'
            ,data : {"url" : strUrl}
            ,success: function(res){
                if(!res.result){
                    alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
                    return;
                }

                _oCallUrl = contextPath + _oCallUrl.substring(1);

                try {
                    if( _oModal ) {
                        parent.wDialogOpen( _oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oInitData   );
                    } else {
                        parent.wWindowOpen( _oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oPosition, _oInitData  );
                    }
                } catch(E) {
                    alert('오류가 발생하였습니다. : ' +E);
                }
            }
            ,error: function(a,b,msg){

            }
        });

    };


    /**
    * @description div 팝업을 생성한다.
    * @param {String} _oDivTitle : div 상단 타이틀명
    * @param {String} _oCallUrl : 호출 url
    * @param {integer} _oWidth : div 넓이
    * @param {integer} _oHeight : div 높이
    * @param {boolean} _oModal : modal 여부
    * @param {String} _oOpenerId : div를 호출하는 opener id
    * @param {String} _oPosition : div position
    * @param {Object} _oInitData : 초기 데이터
    * @param {String} _oSeesionChek : 세션 체크 여부
    * cmWindowOpen()에서 세션 삭제로 mainNoticeList에서 사용
    */
    var noticeWindowOpen = function(_oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oPosition, _oInitData , _oSeesionChek){
        try {
            if( _oModal ) {
                parent.wDialogOpen( _oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oInitData   );
            } else {
                parent.wWindowOpen( _oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oPosition, _oInitData  );
            }
        } catch(E) {
            alert('오류가 발생하였습니다. : ' +E);
        }
    };


    /**
    * @description 달력 팝업을 생성한다. (시작일 - 종료일)
    * @param {String} _oStartId : 달력 시작일 id
    * @param {String} _oEndId : 달력 종료일 id
    * @param {integer} _oSize : 크기
    *
    * 2018.01.29 YYK : 달력 위치 조정 추가 ( _loc_top, _loc_left )
    * 2018.03.05 YYK : 달력 위치 버튼 이미지 경로설정 추가 ( _imgPath )
    */
    var cmCreateDatepickerLinked = function(_oStartId, _oEndId, _oSize, _loc_top, _loc_left, _imgPath){

    	// 디폴트 이미지 경로
    	var imgPath = contextPath+ "/images/btn_calendar.gif" ;

    	if ( _imgPath ){
    		imgPath = _imgPath
    	}

        $( "#"+_oStartId ).datepicker({
            changeMonth: true,changeYear: true,numberOfMonths: 1,showOn: "button",buttonImage: imgPath ,buttonImageOnly: true
            ,onClose: function( selectedDate ) {
                $( "#"+_oEndId ).datepicker( "option", "minDate", selectedDate );
                $( "#"+_oEndId ).parent().find("img").css("margin-bottom", "3px");
            }
            ,beforeShow : function(input){
	            var offset = $(input).offset();
	            var height = $(input).height();
	            window.setTimeout(function () {
	                $("#ui-datepicker-div").css({ top: (offset.top + height- _loc_top) + 'px', left: (offset.left +_loc_left )+ 'px' }) }, 1); }
        });
        $( "#"+_oEndId ).datepicker({
            changeMonth: true,changeYear: true,numberOfMonths: 1,showOn: "button",buttonImage: imgPath ,buttonImageOnly: true
            ,onClose: function( selectedDate ) {
                $( "#"+_oStartId ).datepicker( "option", "maxDate", selectedDate );
                $( "#"+_oStartId ).parent().find("img").css("margin-bottom", "3px");
            }
	        ,beforeShow : function(input){
	            var offset = $(input).offset();
	            var height = $(input).height();
	            window.setTimeout(function () {
	            	$("#ui-datepicker-div").css({ top: (offset.top + height-_loc_top) + 'px', left: (offset.left +_loc_left )+ 'px' }) }, 1); }
        });
    };

    /**
    * @description 달력 팝업을 생성한다.
    * @param {String} _oId : 달력 id
    * @param {integer} _oSize : 크기
    */
    var cmCreateDatepicker = function(_oId, _oSize, imgPath){
        var vbtnImg = contextPath+ "/images/ico_date.png";
        if(imgPath!=null && imgPath!=undefined  && imgPath!=""){
            vbtnImg = contextPath+ imgPath;
        }
        $( "#"+_oId ).width(_oSize*8).datepicker({
            changeMonth: true,changeYear: true,numberOfMonths: 1,showOn: "button",buttonImage: vbtnImg,buttonImageOnly: true
        });
    };

    /**
    * @description 달력 팝업을 생성한다. (오늘 이후 선택 불가)
    * @param {String} _oId : 달력 id
    * @param {integer} _oSize : 크기
    */
    var cmCreateDatepickerRest = function(_oId, _oSize){
        $( "#"+_oId ).width(_oSize*8).datepicker({
            changeMonth: true,changeYear: true,numberOfMonths: 1,showOn: "button",buttonImage: contextPath+ "/images/ico_date.png",buttonImageOnly: true, maxDate : "+0d"
        });
    };

    /**
    * @description iframe 내의 사용할 수 있는 객체를 가져온다
    * @param {Object} _oFrame : iframe 객체
    */
    var cmGetIframeBody = function(_oFrame){
        var doc = null;

        // IE8 cascading access check
        try {
            if (_oFrame.contentWindow) {
                doc = _oFrame.contentWindow.document;
            }
        } catch(err) {}

        if (doc) { // successful getting content
        }
        else {
            try {
                doc = _oFrame.contentDocument ? _oFrame.contentDocument : _oFrame.document;
            } catch(err) {
                doc = _oFrame.document;
            }
        }

        var $doc = doc;
        var $body = $($doc.body) ? $($doc.body) : $($doc.documentElement);
        $body.html('');

        return $body;
    };

    /**
    * @description 마스크 혹은 기타 처리가 된 값을 필터링 하여 순수 데이터만 뽑은 form을 생성하여 submit한다.
    * @param {String} _oFrmId : 데이터를 가지고 있는 form id
    * @param {String} _oTargetIframe : form 데이터를 복사할 iframe id
    * @param {String} _oAction : 처리할 form action
    * @param {String} _oCallback : submit 처리 후 실행할 함수명
    */
    var cmFormSubmit = function(_oFrmId, _oTargetIframe, _oAction, _oCallback){
        $("#callBackFunction").val(_oCallback); // 처리후 복귀 함수

        var uniq_id = cmCreateRandomId(10);

        var $iframe = cmGetIframeBody($("#"+_oTargetIframe)[0]);
        var $form = $("<form/>").attr( "action", _oAction ).attr( "id", uniq_id ).attr("method", "post");

        try {
            var input_type = "";
            var input_val = "";
            var $ctrl = "";

            $iframe.append( $form );

            $("#"+_oFrmId).find("input[type='hidden'], input[type='text'],input[type='password'], input[type='checkbox']:checked, input[type='radio']:checked, select,textarea").each( function(){
                // disabled 된것은 배제
                if( $(this).is(':enabled') ) {
                    input_type = $(this).attr("type");
                    try {   // 마스크 등 제거
                        input_val = $(this).cleanVal();
                    }catch(E){
                        input_val = $(this).val();
                    }

                    // class 값으로 int, float, doublt 인 경우, 콤마 제거
                    var cs = $(this).getClasses();

                    if (cs.length > 0) { //IE7 대응
                        if($.inArray("DT_INT", cs) > -1 || $.inArray("DT_DOUBLE", cs) > -1 || $.inArray("DT_FLOAT", cs) > -1) {
                            input_val = input_val.replace(/[^0-9\.\-]/g,'');
                        }
                        if($.inArray("DT_DATE", cs) > -1) { // 날짜의 경우, '-'가 업는 형태로 DB가 구성되어 잇어서 '-' 삭제
                            input_val = input_val.replace(/[\-]/g,'');
                        }
                    }

                    $ctrl = $('<input/>').attr({ type: 'text', name:$(this).attr("name"), value: input_val });

                    $iframe.find("#"+uniq_id).append($ctrl);
                }
            });
        } catch(E) {
            alert("내용을 전송하는 과정에서 오류가 발생하였습니다.\n관리자에게 문의하시기 바랍니다.\n오류내용 : " + E);
        }

        $iframe.find("#"+uniq_id).submit();
    };

    /**
    * @description multipart/form-data 방식으로 대상 form을 submit한다.
    * @param {String} _oFrmId : 데이터를 가지고 있는 form id
    * @param {String} _oTargetIframe : form 데이터를 복사할 iframe id
    * @param {String} _oAction : 처리할 form action
    * @param {String} _oCallback : submit 처리 후 실행할 함수명
    */
    var cmFileFormSubmit = function(_oFrmId, _oTargetIframe, _oAction, _oCallback, _sFlag, _oWndid){

        $("#callBackFunction").val(_oCallback); // 처리후 복귀 함수

        $("#"+_oFrmId).attr("action", _oAction);
        $("#"+_oFrmId).attr("target", _oTargetIframe);
        $("#"+_oFrmId).attr("enctype","multipart/form-data");
        $("#"+_oFrmId).attr("encoding","multipart/form-data");

        try {
            var nm = Array();
            var val= Array();

            $("#"+_oFrmId).find("input[type='hidden'], input[type='text'],input[type='password'], input[type='checkbox']:checked, input[type='radio']:checked, select,textarea").each( function(){
                try {
                    // disabled 된것은 배제
                    if( $(this).is(':enabled') ) {
                        nm.push( $(this).attr("name") );
                        val.push( $(this).val() );

                        try {   // 마스크 등 제거
                            input_val = $(this).cleanVal();
                        }catch(E){
                            input_val = $(this).val();
                        }

                        // class 값으로 int, float, doublt 인 경우, 콤마 제거
                        var cs = $(this).getClasses();

                        if (cs.length > 0) { //IE7 대응
                            if($.inArray("DT_INT", cs) > -1 || $.inArray("DT_DOUBLE", cs) > -1 || $.inArray("DT_FLOAT", cs) > -1) {
                                input_val = input_val.replace(/[^0-9\.\-]/g,'');
                            }
                            if($.inArray("DT_DATE", cs) > -1) { // 날짜의 경우, '-'가 업는 형태로 DB가 구성되어 잇어서 '-' 삭제
                                input_val = input_val.replace(/[\-]/g,'');
                            }
                        }
                        $(this).val( input_val );
                    }
                }catch(E){}
            });

            $("#"+_oFrmId).submit();

            for (var i=0; i < nm.length; i++) {
                $("#"+nm[i]).val( val[i] );
            }

        } catch(E) {}
    };

    /**
    * @description 팝업 창을 열어준 부모 객체의 id를 가져온다.
    * @param {String} _oWndId : 부모 객체를 찾고자 하는 창의 id
    */
    var cmGetWindowOpener = function(_oWndId){
        var opener = parent.$.window.getWindow( _oWndId );
        // 오프너가 윈도루 팝업인 경우
        if (opener != undefined) {
            try {
                opener = opener.getContainer().find("iframe")[0].contentWindow;
            }catch(E){
                alert('함수를 호출 할 수 없습니다.(팝업을 다시 실행해 주십시오.)');
            }
        }
        // 오프너가 팝업이 아닌 경우 iframe에서 오픈 한것으로 판단
        else {
            try {
                opener = parent.document.getElementById("content_area").contentWindow;
            }catch(E){
                alert('함수를 호출 할 수 없습니다.(팝업을 다시 실행해 주십시오.)');
            }
        }

        return opener;
    };

    /**
    * @description div 팝업 창을 닫는다
    * @param {String} _oWndId : 닫고자 하는 창의 id
    */
    var cmWindowClose = function(_oWndId){
        try {
            var wnd = window.parent.$.window.getWindow( _oWndId );
            if( wnd!=null )
                wnd.close();
        } catch(E) {
            //alert('오류가 발생하였습니다. : ' +E);
        }
    };

    /**
    * @description div 팝업 창을 닫는다
    * @param {String} _oWndId : 닫고자 하는 창의 id
    */
    var cmWindowHide = function(_oWndId){
        try {
            var wnd = window.parent.$.window.getWindow( _oWndId );
            if( wnd!=null )
                wnd.minimize();//wnd.hide();
        } catch(E) {
            //alert('오류가 발생하였습니다. : ' +E);
        }
    };

    /**
    * @description div 팝업 창을 연다
    * @param {String} _oWndId : 열고자 하는 창의 id
    */
    var cmWindowShow = function(_oWndId){
        try {
            //var wnd = window.parent.$.window.getSelectedWindow();
            var wnd = window.parent.$.window.getWindow( _oWndId );
            if( wnd!=null )
                wnd.restore();//wnd.show();
        } catch(E) {
            //alert('오류가 발생하였습니다. : ' +E);
        }
    };

    /**
    * @description 페이지를 이동한다.
    * @param {String} _oFrmId : form id
    * @param {String} _oUrl : 이동 url
    */
    var cmMovePage = function(_oFrmId, _oUrl){
        fn_check_session();

        var strUrl = "";

        if(_oUrl.startsWith(contextPath)){
            _oUrl = _oUrl.replace(contextPath, "");
        }

        if(!_oUrl.startsWith("/")){
            _oUrl = "/"+_oUrl;
        }


        if(_oUrl.indexOf("?") > -1){
            strUrl = _oUrl.substring(0, _oUrl.indexOf("?"));
        }else{
            strUrl = _oUrl;
        }


        $.ajax({
            url: contextPath + 'userauth/checkAuth.do'
            ,type: 'post'
            ,dataType: 'json'
            ,data : {"url" : strUrl}
            ,success: function(res){
                if(!res.result){
                    alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
                    return;
                }

                $("#"+_oFrmId).removeAttr("enctype");
                $("#"+_oFrmId).removeAttr("encoding");

                $("#"+_oFrmId).find("input[type='hidden'], input[type='text']").each( function(){
                    try {
                        // disabled 된것은 배제
                        if( $(this).is(':enabled') ) {
                            try {   // 마스크 등 제거
                                input_val = $(this).cleanVal();
                            }catch(E){
                                input_val = $(this).val();
                            }

                            // class 값으로 int, float, doublt 인 경우, 콤마 제거
                            var cs = $(this).getClasses();

                            if( cs.indexOf("DT_INT")!=-1 || cs.indexOf("DT_DOUBLE")!=-1 || cs.indexOf("DT_FLOAT")!=-1 ) {
                                input_val = input_val.replace(/[^0-9\.\-]/g,'');
                            }

                            $(this).val( input_val );
                        }
                    }catch(E){}
                });

                $("#"+_oFrmId).attr("action", contextPath + _oUrl.substring(1));
                $("#"+_oFrmId).attr("target","_self");
                $("#"+_oFrmId).submit();
            }
            ,error: function(a,b,msg){

            }
        });


    };

    /**
    * @description 페이지를 이동한다.(form 제외)
    * @param {String} _oUrl : 이동 url
    */
    var cmMoveUrl = function(_oUrl){
        fn_check_session();
        var strUrl = "";

        if(_oUrl.startsWith(contextPath)){
            _oUrl = _oUrl.replace(contextPath, "");
        }

        if(!_oUrl.startsWith("/")){
            _oUrl = "/"+_oUrl;
        }


        if(_oUrl.indexOf("?") > -1){
            strUrl = _oUrl.substring(0, _oUrl.indexOf("?"));
        }else{
            strUrl = _oUrl;
        }


        $.ajax({
            url: contextPath + 'userauth/checkAuth.do'
            ,type: 'post'
            ,dataType: 'json'
            ,data : {"url" : strUrl}
            ,success: function(res){
                if(!res.result){
                    alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
                    return;
                }

                $(location).attr('href', contextPath + _oUrl.substring(1));
            }
            ,error: function(a,b,msg){

            }
        });
    };

    /**
    * @description 페이지를 이동한다.(form 제외)
    * @param {String} _oUrl : 이동 url
    * cmMoveUrl()에서 세션 체크 삭제
    * mainNoticeList file down에서 사용
    */
    var fileMoveUrl = function(_oUrl){

        if(_oUrl.startsWith(contextPath+"/")){
            $(location).attr('href', _oUrl);
            return;
        }
        if(contextPath.endsWith("/")){
            if(_oUrl.startsWith("/")){
                $(location).attr('href', contextPath + _oUrl.substring(1) );
            }
            else
                $(location).attr('href', contextPath + _oUrl);
        }
        else{
            if(_oUrl.startsWith("/")){
                $(location).attr('href', contextPath+_oUrl);
            }
            else
                $(location).attr('href', contextPath+"/"+_oUrl);
        }
    };

    /**
    * @description 하단 콘텐츠에 페이지를 삽입한다.
    * @param {String} _oUrl : 삽입할 url
    */
    var cmMenuUrlContent = function(_oUrl, _oClearFlag){
    	$("#repairtargets").hide();

        fn_check_session();

        if (_oClearFlag == undefined || _oClearFlag == false) {
            if(gMap != undefined && gMap != null){
                gMap.cleanMap();
            }
        }

        if(contextPath.endsWith("/")){
            if(_oUrl.startsWith("/")){
                _oUrl = contextPath + _oUrl.substring(1);
            }
            else
                _oUrl = contextPath + _oUrl;
        }
        else{
            if(_oUrl.startsWith("/")){
                _oUrl = contextPath+_oUrl;
            }
            else
                _oUrl = contextPath+"/"+_oUrl;
        }

        bottomOpen();
        $("#content_area").attr("src", _oUrl);
    };

    var cmViewContent = function(_oUrl){

    }

    /**
    * @description 하단 콘텐츠에 통계페이지를 삽입한다.
    * @param {String} _oUrl : 삽입할 url
    */
    var statsMenuUrlContent = function(_oUrl){
        fn_check_session();

        if(contextPath.endsWith("/")){
            if(_oUrl.startsWith("/")){
                _oUrl = contextPath + _oUrl.substring(1);
            }
            else
                _oUrl = contextPath + _oUrl;
        }
        else{
            if(_oUrl.startsWith("/")){
                _oUrl = contextPath+_oUrl;
            }
            else
                _oUrl = contextPath+"/"+_oUrl;
        }

        statsOpen();
        $("#content_stArea").attr("src", _oUrl);
    };
    /* full windows style 적용    */
    var repairMenuUrlContent = function(_oUrl){
        //alert(_oUrl);
        fn_check_session();

        if(contextPath.endsWith("/")){
            if(_oUrl.startsWith("/")){
                _oUrl = contextPath + _oUrl.substring(1);
            }
            else
                _oUrl = contextPath + _oUrl;
        }
        else{
            if(_oUrl.startsWith("/")){
                _oUrl = contextPath+_oUrl;
            }
            else
                _oUrl = contextPath+"/"+_oUrl;
        }

        //repairtargetsOpen();
        $("#content_repairtargets").attr("src", _oUrl);

    };

    var untpcMenuUrlContent = function(_oUrl){
        //alert(_oUrl);
        fn_check_session();

        if(contextPath.endsWith("/")){
            if(_oUrl.startsWith("/")){
                _oUrl = contextPath + _oUrl.substring(1);
            }
            else
                _oUrl = contextPath + _oUrl;
        }
        else{
            if(_oUrl.startsWith("/")){
                _oUrl = contextPath+_oUrl;
            }
            else
                _oUrl = contextPath+"/"+_oUrl;
        }

        //repairtargetsOpen();
        $("#content_unptcSenario").attr("src", _oUrl);

    };

    /**
    * @description 배열을 unique 연산한다.
    * @param {Array} _oArr : 대상 배열
    */
    var unique = function(_oArr){
        var hash = {}, result = [];
        for ( var i = 0, l = _oArr.length; i < l; ++i ) {
            if ( !hash.hasOwnProperty(_oArr[i]) ) { //it works with objects! in FF, at least
                hash[ _oArr[i] ] = true;
                result.push(_oArr[i]);
            }
        }
        return result;
    };

    /**
    * @description 값을 float형으로 변환한다.
    * @param {String} _oVal : 변환 대상 문자열
    */
    var cmFormatFloat = function(_oVal){
        try {
            _oVal = _oVal.replace(/[^0-9\.\-]/g,'');

            if( isNaN(_oVal) )
                return 0;
            else
                return parseFloat(_oVal);
        } catch(E) {
            return 0;
        }
    };

    /**
    * @description email format 여부를 체크한다.
    * @param {String} _objEmail : 체크 대상 email 문자열
    */
    var fnEmailChk = function(_objEmail){
        var regDoNot = /(@.*@)|(\.\.)|(@\.)|(\.@)|(^\.)/;
        var regMust = /^[a-zA-Z0-9\-\.\_]+@[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,3})$/;
        if (!regDoNot.test(_objEmail) && regMust.test(_objEmail)) {
            return true;
        } else {
            alert('잘못된 E-mail입니다.');
            return false;
        }
    };

    /**
    * @description 내용 길이를 체크한다.
    * @param {String} _objMsg : 체크 대상 내용
    * @param {Integer} _oStartNum : 최소 문자열 길이
    * @param {Integer} _oEndNum : 최대 문자열 길이
    * @param {String} _oAlertMsg : 체크 대상 항목명
    */
    var fnMsgRangeChk = function(_objMsg, _oStartNum, _oEndNum, _oAlertMsg){
        var nbytes = 0;
        for (var i = 0; i < _objMsg.length; i++) {
            var szChr = _objMsg.charAt(i);
            if (escape(szChr).length > 4) {
                nbytes += 2;
            } else if (szChr == '\n') {
                if (_objMsg.charAt(i - 1) != '\r') nbytes += 1;
            } else if (szChr == '<' || szChr == '>') {
                nbytes += 4;
            } else if (szChr == "'") {
                nbytes += 2;
            } else {
                nbytes += 1;
            }
        }
        if (nbytes < _oStartNum) {
            alert(_oAlertMsg + ' 항목을 너무 짧게 입력하셨습니다.     \n영문/숫자는 ' + _oStartNum + '자, 한글은 ' + _oStartNum / 2 + '자 이상으로 입력해 주십시요.     ');
            return false;
        }
        if (nbytes > _oEndNum) {
            alert(_oAlertMsg + ' 항목을 너무 길게 입력하셨습니다.     \n영문/숫자는 ' + _oEndNum + '자, 한글은 ' + _oEndNum / 2 + '자 이내로 입력해 주십시요.     ');
            return false;
        }
        return true;
    };

    /**
    * @description 영문과 숫자 혼용 사용 여부를 체크한다.
    * @param {String} _oStr : 체크 대상 문자열
    */
    var fn_chkCharWithNum = function(_oStr){
         var res1 = false;
         var res2 = false;
         var res3 = false;

         res1 = (/[a-z]/i).test(_oStr); //영문이 있는지
         res2 = (/[0-9]/).test(_oStr); //숫자가 있는지
         res3 = (/^[0-9a-z_]*$/i).test(_oStr); //영문, 숫자, _ 이외엔 없는지

        //영소문이 있고, 숫자가 있으며, 영문과 숫자 이외엔 없으면 'true'
        //문자, 숫자가 둘다 있어야만함
         if (res1 && res2 && res3)
             return true;
         else
             return false;
    };

    /**
    * @description 특수문자 사용 여부를 체크한다.
    * @param {String} _oStr : 체크 대상 문자열
    */
    var fn_chkSpecialChar = function(_oStr){
        var res1 = false;

        res1 = (/[~!@\#$%<>^&*\()\-=+_\’]/gi).test(_oStr); //특수문자 포함 확인

        if (res1)
            return true;
        else
            return false;
    };

    /**
    * @description 배열에서 유니크한 항목만 남긴다.
    * @param {Array} _oArr : 대상 배열
    * @param {String} _oSortYn : 정렬 여부
    */
    var cmUniqArrayData = function(_oArr, _oSortYn){
        if( _oSortYn=='Y')
            _oArr = _oArr.sort(function(a,b) {return a-b;});

        var uniq = _oArr.reduce(function(a,b) {
            if(a.indexOf(b)<0) a.push(b);
            return a;
        },[]);

        return uniq;
    };

    /**
    * @description 숫자 3자리마다 콤마(,)를 입력한다.
    * @param {Integer} _oNum : 대상 숫자 값
    */
    var cmAddComma = function(_oNum){
        return _oNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    };

    /**
    * @description select 박스에서 특정 값이 선택되었을 경우, 텍스트 input 박스가 나타나도록 한다.
    * @param {Object} _obj : select box
    * @param {Integer} _oCheckVal : 비교 값
    * @param {String} _oInputId : input id
    */
    var cmShowMemoBox = function(_obj, _oCheckVal, _oInputId){
        if( $(_obj).val()==_oCheckVal )
            $('#'+_oInputId).show();
        else {
            $('#'+_oInputId).hide();
            $('#'+_oInputId).val('');
        }
    };

    /**
    * @description 팝업창 타이틀을 이용하여 div 팝업을 닫는다.
    * @param {String} _oWndTitle : div 팝업창 타이틀
    */
    var cmWindowCloseByTitle = function(_oWndTitle){
        try {
            var allw = window.parent.$.window.getAll();

            for( var i=0; i<allw.length; i++ ) {
                if( allw[i].getTitle()==_oWndTitle ) {
                    var wnd = window.parent.$.window.getWindow( allw[i].getWindowId() );
                    if( wnd!=null )
                        wnd.close();
                }
            }
        } catch(E) {
            alert('오류가 발생하였습니다. : ' +E);
        }
    };

    /**
    * @description IE의 Version을 가져온다.
    */
    var fnGetIeVersion = function(){
        var word;
        var version = "N/A";
        var agent = navigator.userAgent.toLowerCase();
        var name = navigator.appName;
        // IE old version ( IE 10 or Lower )
        if (name == "Microsoft Internet Explorer")
            word = "msie ";
        else {
            // IE 11
            if (agent.search("trident") > -1)
                word = "trident/.*rv:";
            // Microsoft Edge
            else if (agent.search("edge/") > -1)
                word = "edge/";
        }
        var reg = new RegExp(word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})");
        if (reg.exec(agent) != null)
            version = RegExp.$1 + RegExp.$2;
        return version;
    };

    /**
    * @description 현재 시간을 가져온다. (yyyy-mm-dd hh:mi:ss)
    */
    var fnGetTimeStampAll = function(){
        var d = new Date();

        var s =
        leadingZeross(d.getFullYear(), 4) + '-' +
        leadingZeross(d.getMonth() + 1, 2) + '-' +
        leadingZeross(d.getDate(), 2) + ' ' +

        leadingZeross(d.getHours(), 2) + ':' +
        leadingZeross(d.getMinutes(), 2) + ':' +
        leadingZeross(d.getSeconds(), 2);

        return s;
    };

    /**
    * @description 입력한 길이만큼 0을 채워넣는다.
    * @param {Integer} _oNum : 대상 숫자 값
    * @param {Integer} _oDigits : 전체 길이
    */
    var leadingZeross = function(_oNum, _oDigits){
        var zero = '';
        _oNum = _oNum.toString();

        if (_oNum.length < _oDigits) {
        for (var i = 0; i < _oDigits - _oNum.length; i++)
        zero += '0';
        }

        return zero + _oNum;
    };

    /**
    * @description 사업자 등록 번호를 체크한다.
    * @param {String} _oBizID : 체크 대상 사업자번호
    */
    var checkBizID = function(_oBizID){
        // bizID는 숫자만 10자리로 해서 문자열로 넘긴다.
        var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1);
        var tmpBizID, i, chkSum=0, c2, remander;
        _oBizID = _oBizID.replace(/-/gi,'');

        for (i=0; i<=7; i++) chkSum += checkID[i] * _oBizID.charAt(i);
        c2 = "0" + (checkID[8] * _oBizID.charAt(8));
        c2 = c2.substring(c2.length - 2, c2.length);
        chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1));
        remander = (10 - (chkSum % 10)) % 10 ;

        if (Math.floor(_oBizID.charAt(9)) == remander) return true ; // OK!
          return false;
    };

    /**
    * @description 전화번호 여부를 체크한다.
    * @param {String} _oTelNo : 체크 대상 전화번호
    */
    var validatePhone = function(_oTelNo){
        var a = _oTelNo;
        var filter = /^((\+[1-9]{1,4}[ \-]*)|(\([0-9]{2,3}\)[ \-]*)|([0-9]{2,4})[ \-]*)*?[0-9]{3,4}?[ \-]*[0-9]{3,4}?$/;
        if (filter.test(a)) {
            return true;
        }
        else {
            return false;
        }
    };

    /**
    * @description 전화번호 format으로 변환한다.
    * @param {String} _oStr : 변환 대상 문자열
    */
    var phoneNumFomatter = function(_oStr){
        _oStr = _oStr.replace(/[^0-9]/g, '');
        if(_oStr.length == 1 && _oStr.substring(0, 1) != '0'){
            _oStr = '';
        }

        var tmp = '';
        if (_oStr.substring(0, 2) == '02') {
            if (_oStr.length < 3) {
                return _oStr;
            } else if (_oStr.length < 6) {
                tmp += _oStr.substr(0, 2);
                tmp += '-';
                tmp += _oStr.substr(2);
                return tmp;
            } else if (_oStr.length < 10) {
                tmp += _oStr.substr(0, 2);
                tmp += '-';
                tmp += _oStr.substr(2, 3);
                tmp += '-';
                tmp += _oStr.substr(5);
                return tmp;
            } else {
                tmp += _oStr.substr(0, 2);
                tmp += '-';
                tmp += _oStr.substr(2, 4);
                tmp += '-';
                tmp += _oStr.substr(6);
                return tmp.substr(0, 12);
            }
        } else {
            if (_oStr.length < 4) {
                return _oStr;
            } else if (_oStr.length < 7) {
                tmp += _oStr.substr(0, 3);
                tmp += '-';
                tmp += _oStr.substr(3);
                return tmp;
            } else if (_oStr.length < 11) {
                tmp += _oStr.substr(0, 3);
                tmp += '-';
                tmp += _oStr.substr(3, 3);
                tmp += '-';
                tmp += _oStr.substr(6);
                return tmp;
            } else {
                tmp += _oStr.substr(0, 3);
                tmp += '-';
                tmp += _oStr.substr(3, 4);
                tmp += '-';
                tmp += _oStr.substr(7);
                return tmp.substr(0, 13);
            }
        }
        return _oStr;
    };


    /**
     * 2018.01.26 yyk
     * @description 천단위 콤마 찍기 + 첫 숫자 0 일 경우 제거
     * @param {String} _oStr : 변환 대상 문자열
     */
     var commaFomatter = function(_oStr){
         _oStr = _oStr.replace(/[^0-9]/g, '');
         if(_oStr.length == 1 && _oStr.substring(0, 1) == '0'){
             _oStr = '';
         }

         var tmp = '';
         return _oStr.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
     };



    /**
    * @description cookie를 생성한다.
    * @param {String} _oCookieNm : 생성할 쿠키 명
    * @param {String} _oValue : 쿠키 값
    * @param {Integer} _oExdays : 만료일
    */
    var setCookieData = function(_oCookieNm, _oValue, _oExdays){
        var exdate = new Date();
        exdate.setDate(exdate.getDate() + _oExdays);
        var cookieValue = escape(_oValue) + ((_oExdays==null) ? "" : "; expires=" + exdate.toGMTString());
        document.cookie = _oCookieNm + "=" + cookieValue;
    };

    /**
    * @description cookie를 삭제한다.
    * @param {String} _oCookieNm : 삭제할 쿠키 명
    */
    var deleteCookieData = function(_oCookieNm){
        var expireDate = new Date();
        expireDate.setDate(expireDate.getDate() - 1);
        document.cookie = _oCookieNm + "= " + "; expires=" + expireDate.toGMTString();
    };

    /**
    * @description cookie 값을 가져온다.
    * @param {String} _oCookieNm : 쿠키 명
    */
    var getCookieData = function(_oCookieNm){
        _oCookieNm = _oCookieNm + '=';
        var cookieData = document.cookie;
        var start = cookieData.indexOf(_oCookieNm);
        var cookieValue = '';
        if(start != -1){
            start += _oCookieNm.length;
            var end = cookieData.indexOf(';', start);
            if(end == -1)end = cookieData.length;
            cookieValue = cookieData.substring(start, end);
        }
        return unescape(cookieValue);
    };

    /**
    * @description 메뉴로 이동한다(팝업창용)
    * @param {String} _oUrl : 이동할 url
    * @param {String} _oTitle : 팝업 타이틀명
    */
    var goMenuPopup = function(_oUrl, _oTitle){
        fn_check_session();
        cmWindowOpen(_oTitle, _oUrl , 1000, 700, false, $("#wnd_id").val(), 'center');
    };

    /**
    * @description 부서 정보가 변경될때마다 하위 select box 정보를 바인딩 한다.
    * @param {String} _oParentDivId : 상위 div id
    * @param {String} _oDivId : 바인딩 대상 div id
    * @param {String} _oOdr : 순서
    * @param {String} _oLastDivId : 최하위 div id
    * @param {String} _oSelVal : 선택된 값
    */
    var fn_change_dept = function(_oParentDivId, _oDivId, _oOdr, _oLastDivId, _oSelVal){
        //부서 항목 초기화
        if(_oOdr == "3"){
            $("#" + _oDivId).html("<option value=''>====== 전체 ======</option>");
            $("#" + _oLastDivId).html("<option value=''>====== 전체 ======</option>");
        }else{
            $("#" + _oDivId).html("<option value=''>====== 전체 ======</option>");
        }

        var sDeptCd = $("#" + _oParentDivId).val();
        var postdata = { "SEHIGH_DEPT_CODE" : sDeptCd
                        , "ODR" : _oOdr};

        if(sDeptCd == ""){
            $("#" + _oDivId).html("<option value=''>====== 전체 ======</option>");
            return;
        }

        $.ajax({
            url: contextPath + 'api/dept/selectDeptCdList.do'
            ,data: postdata
            ,type: 'post'
            ,dataType: 'json'
            ,success: function(res){
                var innerHtml = "<option value=''>====== 전체 ======</option>";

                if(res.length < 1) {return;}

                for(var i=0; i<res.length; i++){
                    if(typeof _oSelVal != "undefined" && _oSelVal != "" && _oSelVal == res[i].DEPT_CODE){
                        innerHtml += "<option value='"+res[i].DEPT_CODE+"' selected='selected' >"+res[i].LOWEST_DEPT_NM + "</option>";
                    }else {
                        innerHtml += "<option value='"+res[i].DEPT_CODE+"'>"+res[i].LOWEST_DEPT_NM + "</option>";
                    }

                }

                $("#" + _oDivId).html(innerHtml);

            }
            ,error: function(a,b,msg){

            }
        });
    };

    /**
    * @description input에 값을 입력한다.
    * @param {String} _oVal : 입력할 값
    * @param {String} _oInputId : input id
    */
    var fn_set_value = function(_oVal, _oInputId){
        $("#" + _oInputId).val(_oVal);
    };

    /**
    * @description input의 null 여부를 체크한다.
    * @param {String} _oParentType : 상위 element type
    */
    var fn_check_notnull = function(_oParentType){
        var check = false;

        var inputNotNull = $(".notnull");
        for( var i=0; i < inputNotNull.length; i++) {
            var inputNm = inputNotNull[i];
            var display = $(inputNm).parents(_oParentType).css("display");
            var name = $(inputNm).attr("alt");
            var val = $(inputNm).val();

            if(display != "none" && val==""){
                alert( name + " 값을 입력하시기 바랍니다.");
                $(inputNm).focus();
                check = true;
                return check;
            }
        }

        return check;
    };

    /**
    * @description 이메일과 전화번호의 유효성을 검사한다.
    * @param {String} _oType : 전화번호와 이메일 여부 (email/tel)
    * @param {String} _oInputId : input id
    */
    var fn_check_format = function(_oType, _oInputId){
        var value = $("#"+_oInputId).val();
        // 정규식 - 이메일 유효성 검사
        var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        // 정규식 -전화번호 유효성 검사
        var regPhone = /^(([0-9]{1,3}\-)?([0-9]{1,4})\-([0-9]{1,4}))$/;

        switch(_oType){
            case "email":
                 if(!regEmail.test(value)) {
                        alert('이메일 주소가 유효하지 않습니다');
                        $("#"+_oInputId).focus();
                        return false;
                    }
                break;
            case "tel":
                if(!regPhone.test(value)) {
                    alert('전화번호가 유효하지 않습니다');
                    $("#"+_oInputId).focus();
                    return false;
                }
                break;
        }

        return true;
    };

    /**
    * @description 키보드 입력 시, 문자면 삭제한다.
    */
    var removeChar = function(e){
        e = e || window.event;
        var keyID = (e.which) ? e.which : e.keyCode;
        if ( keyID == 8 || keyID == 9 || keyID == 46 || keyID == 37 || keyID == 39 )
            return;
        else
            e.target.value = e.target.value.replace(/[^0-9]/g, "");
    }

    /**
    * @description 입력한 데이터를 입력한 날짜 format으로 보내준다. (jqgrid)
    * @param {String} _oCellValue : 선택된 cell의 값
    * @param {Object} _oOptions : format options
    * @param {Object} _oRowObject : 선택된 row 객체
    */
    var fn_set_dateFormat = function(_oCellValue, _oOptions, _oRowObject){
        if(_oCellValue == "" || _oCellValue == null ){return "";}
        if( _oOptions.colModel.formatoptions.extTxt == _oCellValue){return _oCellValue;}

        if(_oOptions.colModel.formatoptions.dateYN != undefined &&!_oOptions.colModel.formatoptions.dateYN){
            _oCellValue = _oCellValue.toString().substring(0,4) + "/" + _oCellValue.toString().substring(4,6)+ "/" + _oCellValue.toString().substring(6,8);
        }
        var d = new Date(_oCellValue).format(_oOptions.colModel.formatoptions.tgtFormat);

        return d;
    }

    /**
    * @description 세션이 유지되어 있는지 여부를 체크한다.
    */
    var fn_check_session = function(callback, param){
        $.ajax({
            url: contextPath + 'sessionCheck.do'
            ,type: 'post'
            ,dataType: 'json'
            ,async: false
            ,success: function(res){
                if(!res.result){
                    alert("세션이 종료되었습니다. 로그인을 다시 실행해주시기 바랍니다.");

                    if(parent.window){
                        parent.window.location.href = contextPath + "main.do";
                    }else{
                        window.top.location.href = contextPath + "main.do";
                    }
                }

                if(callback != undefined && callback != null){
                    [callback](param);
                }
            }
            ,error: function(a,b,msg){

            }
        });
    };

    /**
    * @description 팝업창 복원 dialog를 종료한다.
    */
    function fnRestore() {
        $("#dvRestore").dialog("close");
    }

    /**
    * @description 기타일 경우 input text가 생기도록 한다.
    * @param {String} _oVal : 체크 값
    * @param {String} _oSrcSelectId : 값을 검사할 select id
    * @param {String} _oTargetInputId : 보여줄 input id
    */
    function fn_show_etcBox(_oVal, _oSrcSelectId, _oTargetInputId) {
        if($("#" + _oSrcSelectId).val() == _oVal){
            $("#" + _oTargetInputId).show();
        }else {
            $("#" + _oTargetInputId).hide();
            $("#" + _oTargetInputId).val('');
        }
    }

    /**
    * @description m값을 km 단위로 변환한다.
    * @param {String} _oVal : 변환 값
    */
    function fn_m2km(_oVal){
        var result = null;

        try{
            result = parseInt(_oVal) / 1000;
        }catch(e){}

        return result;
    }

    /**
    * @description km값을 m 단위로 변환한다.
    * @param {String} _oVal : 변환 값
    */
    function fn_km2m(_oVal){
        var result = null;
        try{
            result = parseInt(_oVal) * 1000;
        }catch(e){}

        return result;
    }

    /**
    * @description 서브메뉴 화면을 변경한다
    * @param {String} _oDivId : 변환 값
    * 20180221 YYK  _oClearFlag : map clear 여부 추가
    */
    function fn_set_subMenu(_oDivId, _oUrl, _oType, _oClearFlag){
        var strUrl = "";
        if(_oUrl!=null && _oUrl!=undefined){
            if(_oUrl.startsWith(contextPath)){
                _oUrl = _oUrl.replace(contextPath, "");
            }


            if(!_oUrl.startsWith("/")){
                _oUrl = "/"+_oUrl;
            }

            if(_oUrl.indexOf("?") > -1){
                strUrl = _oUrl.substring(0, _oUrl.indexOf("?"));
            }else{
                strUrl = _oUrl;
            }
        }


        $.ajax({
            url: contextPath + 'userauth/checkAuth.do'
            ,type: 'post'
            ,dataType: 'json'
            ,data : {"url" : strUrl}

            ,success: function(res){
                if(!res.result){
                    alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
                    return;
                }

                // 2018. 01. 04. JOY. 파라미터에 url 추가
                // 2018. 02. 21. YYK. 파라미터에 _oClearFlag map clear여부 추가
                fn_open_subMenu(_oDivId, _oType, _oUrl, _oClearFlag);
            }
            ,error: function(a,b,msg){

            }
        });
        //통계메뉴일경우 stats div show 하도록 조건 추가

    }

    /**
    * @description 서브메뉴 화면을 변경한다
    * @param {String} _oDivId : 변환 값
    */
    function fn_open_subMenu(_oDivId, _oType, _oUrl, _oClearFlag){

        if ( _oDivId == "" || _oDivId == null || _oDivId == undefined || _oDivId == "good" ){

            $(".btmenuarea").find("div").hide();
            $("#good_stats").addClass("hidden");
            $("#good_stats").removeClass("active");
            //COMMON_UTIL.cmMenuUrlContent(_oUrl);
            COMMON_UTIL.cmMenuUrlContent(_oUrl, _oClearFlag);

        } else {

            if ( _oDivId == "good_stat" ) {

                // 2018. 01. 19. JOY 추가.
                // 경기도로 모니터링단 시스템 통계 페이지인 경우

                $("#good_stats").addClass("active");
                goodStatsOpen();

                $(".btmenuarea").find("div").hide();
                COMMON_UTIL.statsMenuUrlContent(_oUrl);

            } else if ( _oDivId.startsWith("sub_stat") ) {

                // 2017. 11. 16. JOY 추가.
                // 단, 통계메뉴일 경우 div id는 sub_stat으로 시작해야 함.

                $("#stats").addClass("active");
                $("#repairtargets").removeClass("active");
                $("#unptcSenario").removeClass("active");
                statsOpen();
                $(".stmenuarea").find("div").hide();
                $("#sub_stat").find("div").hide();
                $("#sub_stat_route").find("div").hide();
                $("#sub_stat_survey").find("div").show();

            }
            else if(_oDivId == "sub_repairtargets"){
                // 보수대상선정인 경우 div show 하도록 조건 추가.
                /* full windows style 적용    */
                $("#repairtargets").addClass("active");
                $("#unptcSenario").removeClass("active");
                $("#stats").removeClass("active");
                repairtargetsOpen();
                //$(".srtmenuarea").find("div").hide();


            }
            else if(_oDivId == "sub_unptcSenario"){
                $("#unptcSenario").addClass("active");

                $("#stats").removeClass("active");
                $("#repairtargets").removeClass("active");
                unptcSenarioOpen(_oType);
            } else{
                bottomOpen();
                $(".btmenuarea").find("div").hide();
                $("#repairtargets").removeClass("active");
                $("#unptcSenario").removeClass("active");
            }

            $("#" + _oDivId).show();
            $("#" + _oDivId).find(".tab1").click();
        }
    }

    /**
    * @description img modal 창을 보여준다.
    * @param {String} _oModalId : modal id
    * @param {String} _oImgId : 보여줄 img id
    * @param {String} _oSrc : 이미지 경로
    */
    function fn_show_imgScreen(_oModalId, _oImgId, _oSrc){
        $("#" + _oModalId).show();
        $("#" + _oImgId).attr("src", _oSrc);
    }

    /**
    * @description 검색 데이터가 없는경우 row로 메세지를 보여준다.(jqgrid)
    * @param {String} _oGridId : grid id
    * @param {String} _oMsg : 표출할 메세지
    * @param {String} _oLen : 데이터 개수
    */
    function fn_set_grid_noRowMsg(_oGridId, _oMsg, _oLen){
        if(_oLen < 1){
            var txtHtml = "<tr role='row' id='1' tabindex='-1' class='ui-widget-content jqgrow ui-row-ltr'>"
                     + "    <td colspan='" + ($("#" + _oGridId).jqGrid("getGridParam").colNames.length) + "' role='gridcell' style='text-align:center;' aria-describedby='gridArea_NONE'>" + _oMsg + "</td>"
                     + "</tr>";

            $("#" + _oGridId).find("tbody").append(txtHtml);
        }
    }

    /**
    * @description 도로 등급별에 따른 노선 번호 목록을 가져온다
    * @param {String} gradId : 도로등급 INPUT ID
    * @param {String} noId : 노선번호 INPUT ID
    * @param {String} nameId : 도로명 INPUT ID
    */
    function fn_change_roadNo(gradId, noId, nameId) {
        var roadGrad = $("#" + gradId).val();

        $.ajax({
            url: contextPath + 'api/routeinfo/selectRouteInfoListByGrad.do'
            ,type: 'post'
            ,dataType: 'json'
            ,contentType : 'application/json'
            ,data : JSON.stringify({ROAD_GRAD : roadGrad})
            ,success: function(data){
                var txtHtml = "<option value=''>== 전체 ==</option>";

                for(var i=0; i < data.length; i++){
                    txtHtml += "<option value='" + data[i].ROAD_NO + "'>" + data[i].ROAD_NO_VAL + "</option>";
                }

                $("#" + noId).html(txtHtml);
                $("#" + nameId).val("");
            }
            ,error: function(a,b,msg){

            }
        });
    }

    /**
    * @description 노선번호 변경에 따른 노선명을 가져온다
    * @param {String} noId : 노선번호 INPUT ID
    * @param {String} nameId : 도로명 INPUT ID
    * @param {String} gradId : 도로등급 INPUT ID
    */
    function fn_change_roadNm(noId, nameId, gradId) {
        var roadNo = $("#" + noId).val();

        if(roadNo == "") {
            $("#" + nameId).val("");
            return;
        }

        $.ajax({
            url: contextPath + 'api/routeinfo/selectRouteInfo.do'
            ,type: 'post'
            ,dataType: 'json'
            ,contentType : 'application/json'
            ,data : JSON.stringify({ROAD_NO : roadNo})
            ,success: function(data){
                $("#" + nameId).val(data.ROAD_NAME);
                $("#" + gradId).val(data.ROAD_GRAD);
            }
            ,error: function(a,b,msg){

            }
        });
    }


    var cmWindowOpen2 = function(_oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oPosition, _oInitData , _oSeesionChek){

        if(_oSeesionChek == undefined || _oSeesionChek != "N"){
            fn_check_session();
        }

        var strUrl = "";

        if(_oCallUrl.startsWith(contextPath)){
            _oCallUrl = _oCallUrl.replace(contextPath, "");
        }

        if(!_oCallUrl.startsWith("/")){
            _oCallUrl = "/"+_oCallUrl;
        }


        if(_oCallUrl.indexOf("?") > -1){
            strUrl = _oCallUrl.substring(0, _oCallUrl.indexOf("?"));
        }else{
            strUrl = _oCallUrl;
        }


        $.ajax({
            url: contextPath + 'userauth/checkAuth.do'
            ,type: 'post'
            ,dataType: 'json'
            ,data : {"url" : strUrl}
            ,success: function(res){
                if(!res.result){
                    alert("접근 권한이 없습니다. 자세한 사항은 관리자에게 문의하시기 바랍니다.");
                    return;
                }

                _oCallUrl = "http://localhost:8080/gpms/" + _oCallUrl.substring(1);

                try {
                    if( _oModal ) {
                        parent.wDialogOpen( _oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oInitData   );
                    } else {
                        parent.wWindowOpen( _oDivTitle, _oCallUrl, _oWidth, _oHeight, _oModal, _oOpenerId, _oPosition, _oInitData  );
                    }
                } catch(E) {
                    alert('오류가 발생하였습니다. : ' +E);
                }
            }
            ,error: function(a,b,msg){

            }
        });

    };





    return {
        cmImageResize               :           cmImageResize,
        cmFormReset                 :           cmFormReset,
        cmFormObjectInit            :           cmFormObjectInit,

        fnInputLengthCheck          :           fnInputLengthCheck,
        validateExtension           :           validateExtension,

        cmInitGrid                  :           cmInitGrid,
        cmInitPopupGrid             :           cmInitPopupGrid,
        cmInitPopupGridIncludeLeft  :           cmInitPopupGridIncludeLeft,
        cmInitGridSize              :           cmInitGridSize,

        cmShowProgressBar           :           cmShowProgressBar,
        cmHideProgressBar           :           cmHideProgressBar,

        cmCreateRandomId            :           cmCreateRandomId,
        cmWindowOpen                :           cmWindowOpen,
        noticeWindowOpen            :           noticeWindowOpen,

        fnRestore                   :           fnRestore,

        cmCreateDatepickerLinked    :           cmCreateDatepickerLinked,
        cmCreateDatepicker          :           cmCreateDatepicker,
        cmCreateDatepickerRest      :           cmCreateDatepickerRest,

        cmGetIframeBody             :           cmGetIframeBody,
        cmFormSubmit                :           cmFormSubmit,
        cmFileFormSubmit            :           cmFileFormSubmit,

        cmGetWindowOpener           :           cmGetWindowOpener,
        cmWindowClose               :           cmWindowClose,
        cmWindowHide                :           cmWindowHide,
        cmWindowShow                :           cmWindowShow,

        cmMovePage                  :           cmMovePage,
        cmMoveUrl                   :           cmMoveUrl,
        fileMoveUrl                 :           fileMoveUrl,
        cmMenuUrlContent            :           cmMenuUrlContent,
        statsMenuUrlContent         :           statsMenuUrlContent,
        /* full windows style 적용    */
        repairMenuUrlContent        :           repairMenuUrlContent,
        untpcMenuUrlContent         :           untpcMenuUrlContent,

        unique                      :           unique,
        cmFormatFloat               :           cmFormatFloat,
        fnEmailChk                  :           fnEmailChk,
        fnMsgRangeChk               :           fnMsgRangeChk,
        fn_chkCharWithNum           :           fn_chkCharWithNum,
        fn_chkSpecialChar           :           fn_chkSpecialChar,

        cmUniqArrayData             :           cmUniqArrayData,
        cmAddComma                  :           cmAddComma,

        cmShowMemoBox               :           cmShowMemoBox,
        cmWindowCloseByTitle        :           cmWindowCloseByTitle,

        fnGetIeVersion              :           fnGetIeVersion,
        fnGetTimeStampAll           :           fnGetTimeStampAll,
        leadingZeross               :           leadingZeross,

        checkBizID                  :           checkBizID,
        validatePhone               :           validatePhone,
        phoneNumFomatter            :           phoneNumFomatter,

        commaFomatter               :           commaFomatter,

        setCookieData               :           setCookieData,
        deleteCookieData            :           deleteCookieData,
        getCookieData               :           getCookieData,

        goMenuPopup                 :           goMenuPopup,

        fn_change_dept              :           fn_change_dept,
        fn_set_value                :           fn_set_value,

        fn_check_notnull            :           fn_check_notnull,
        fn_check_format             :           fn_check_format,

        removeChar                  :           removeChar,

        fn_set_dateFormat           :           fn_set_dateFormat,
        fn_check_session            :           fn_check_session,

        fn_show_etcBox              :           fn_show_etcBox,

        fn_m2km                     :           fn_m2km,
        fn_km2m                     :           fn_km2m,

        fn_set_subMenu              :           fn_set_subMenu,

        fn_show_imgScreen           :           fn_show_imgScreen,
        fn_set_grid_noRowMsg        :           fn_set_grid_noRowMsg,

        fn_change_roadNo            :           fn_change_roadNo,
        fn_change_roadNm            :           fn_change_roadNm,
        cmWindowOpen2              :           cmWindowOpen2,
        cmWindowOpenIasp            :           cmWindowOpenIasp
    }
}(jQuery));

