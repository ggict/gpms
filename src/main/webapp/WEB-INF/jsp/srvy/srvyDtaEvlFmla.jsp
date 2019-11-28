<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>포장상태 평가수식조회</title>
<%@ include file="/include/common_head.jsp" %>

</head>
<body id="wrap">
<%@ include file="/include/topMenu.jsp" %>
<!-- 필수 파라메터(START) -->
<input id="" name="" type="hidden" id="callBackFunction" name="callBackFunction" value=""/>
<input id="" name="" type="hidden" id="opener_id" name="opener_id" value=""/>
<input id="" name="" type="hidden" id="wnd_id" name="wnd_id" value=""/>
<!-- 필수 파라메터(END) -->
<form id="frm" name="frm" method="post" action="">
	<div class="scroll" id="container">
           <div class="admin_content">
               	<h2>포장상태 평가수식조회</h2>
				<div>
				    <table class="tbview fmla">
				        <caption>포장상태 평가수식조회</caption>
				        <colgroup>
				            <col width="10%" />
				            <col width="*" />
				            <col width="5%" />
				            <col width="5%" />
				        </colgroup>
				        <tbody>
                            <tr>
                                <th>INDEX</th>
                                <th>수식</th>
                                <th>최소값</th>
                                <th>최대값</th>
                            </tr>
				            <tr id='AC'>
				                <td class="center">거북등균열<br/>(ACI)</td>
				                <td>
				                    = <input class="ac_var1" idxNm='지수1' readonly > - AC <br/>
				                    = <input class="ac_var1" idxNm='지수1' readonly > - <input class="ac_var2" idxNm='지수2' readonly > * 망상균열량(㎡)/<input class="ac_var3" idxNm='지수3' readonly >(㎡) <br/>
				                    = <input id="AC_VAR1" name="AC_VAR1" class="ac_var1" idxNm='지수1' readonly >
				                        - <input id="AC_VAR2" name="AC_VAR2" class="ac_var2" idxNm='지수2' readonly >
				                        * ( Low거북등균열량(㎡)/<input id="AC_VAR3" name="AC_VAR3" class="ac_var3" readonly>(㎡)
				                        + Med거북등균열량(㎡)/<input id="AC_VAR4" name="AC_VAR4" class="ac_var3" idxNm='지수3' readonly >(㎡)
				                        + Hi거북등균열량(㎡)/<input id="AC_VAR5" name="AC_VAR5" class="ac_var3" readonly>(㎡) )
				                </td>
				                <td class="center">0</td>
				                <td class="center">10</td>
				            </tr>
                            <tr>
                                <td class="center">블럭균열<br/>(BCI)</td>
                                <td>
                                    = <input class="bc_var1" idxNm='지수1' readonly> - BC <br/>
                                    = <input class="bc_var1" idxNm='지수1' readonly> - <input class="bc_var2" idxNm='지수2' readonly> * 망상균열량(㎡)/<input class="bc_var3" idxNm='지수3' readonly>(㎡) <br/>
                                    = <input id="BC_VAR1" name="BC_VAR1" class="bc_var1" idxNm='지수1' readonly>
                                        - <input id="BC_VAR2" name="BC_VAR2" class="bc_var2" idxNm='지수2' readonly>
                                        * ( Low블럭균열량(㎡)/<input id="BC_VAR3" name="BC_VAR3" class="bc_var3" readonly>(㎡)
                                        + Med블럭균열량(㎡)/<input id="BC_VAR4" name="BC_VAR4" class="bc_var3" idxNm='지수3' readonly>(㎡)
                                        + Hi블럭균열량(㎡)/<input id="BC_VAR5" name="BC_VAR5" class="bc_var3" readonly>(㎡) )
                                </td>
                                <td class="center">0</td>
                                <td class="center">10</td>
                            </tr>
				            <tr>
                                <td class="center">종방향균열<br/>(LCI)</td>
                                <td>
                                    = <input class="lc_var1" idxNm='지수1' readonly> - LC <br/>
                                    = <input class="lc_var1" idxNm='지수1' readonly> - <input class="lc_var2" idxNm='지수2' readonly> * 선형균열량(m)/<input class="lc_var3" idxNm='지수3' readonly>(m) <br/>
                                    = <input id="LC_VAR1" name="LC_VAR1" class="lc_var1" idxNm='지수1' readonly>
                                        - <input id="LC_VAR2" name="LC_VAR2" class="lc_var2" idxNm='지수2' readonly>
                                        * ( Low선형균열량(m)/<input id="LC_VAR3" name="LC_VAR3" class="lc_var3" readonly>(m)
                                        + Med선형균열량(m)/<input id="LC_VAR4" name="LC_VAR4" class="lc_var3" idxNm='지수3' readonly>(m)
                                        + Hi선형균열량(m)/<input id="LC_VAR5" name="LC_VAR5" class="lc_var3" readonly>(m) )
                                </td>
                                <td class="center">0</td>
                                <td class="center">10</td>
                            </tr>
                            <tr>
                                <td class="center">횡방향균열<br/>(TCI)</td>
                                <td>
                                    = <input class="tc_var1" idxNm='지수1' readonly> - TC <br/>
                                    = <input class="tc_var1" idxNm='지수1' readonly> - <input class="tc_var2" idxNm='지수2' readonly> * 선형균열량(m)/<input class="tc_var3" idxNm='지수3' readonly>(m) <br/>
                                    = <input id="TC_VAR1" name="TC_VAR1" class="tc_var1" idxNm='지수1' readonly>
                                        - <input id="TC_VAR2" name="TC_VAR2" class="tc_var2" idxNm='지수2' readonly>
                                        * ( Low선형균열량(m)/<input id="TC_VAR3" name="TC_VAR3" class="tc_var3" readonly>(m)
                                        + Med선형균열량(m)/<input id="TC_VAR4" name="TC_VAR4" class="tc_var3" idxNm='지수3' readonly>(m)
                                        + Hi선형균열량(m)/<input id="TC_VAR5" name="TC_VAR5" class="tc_var3" readonly>(m) )
                                </td>
                                <td class="center">0</td>
                                <td class="center">10</td>
                            </tr>
                            <tr>
                                <td class="center">패칭<br/>(PATI)</td>
                                <td>
                                    = <input class="ptchg_var1" idxNm='지수1' readonly> - PAT <br/>
                                    = <input id="PTCHG_VAR1" name="PTCHG_VAR1" class="ptchg_var1" idxNm='지수1' readonly>
                                        - <input id="PTCHG_VAR2" name="PTCHG_VAR2" class="ptchg_var2" idxNm='지수2' readonly> * 소파보수(㎡)/<input id="PTCHG_VAR3" name="PTCHG_VAR3" class="ptchg_var3" idxNm='지수3' readonly>(㎡) <br/>
                                </td>
                                <td class="center">0</td>
                                <td class="center">10</td>
                            </tr>
                            <tr>
                                <td class="center">포트홀<br/>(POTI)</td>
                                <td>
                                    = <input class="pot_var1" idxNm='지수1' readonly> - POT <br/>
                                    = <input id="POT_VAR1" name="POT_VAR1" class="pot_var1" idxNm='지수1' readonly>
                                        - <input id="POT_VAR2" name="POT_VAR2" class="pot_var2" idxNm='지수2' readonly> * 소파보수(㎡)/<input id="POT_VAR3" name="POT_VAR3" class="pot_var3" idxNm='지수3' readonly>(㎡) <br/>
                                </td>
                                <td class="center">0</td>
                                <td class="center">10</td>
                            </tr>
                            <tr>
                                <td class="center">소성변형<br/>(RDI)</td>
                                <td>
                                    = <input class="rd_var1" idxNm='지수1' readonly> - RD <br/>
                                    = <input class="rd_var1" idxNm='지수1' readonly> - <input class="rd_var2" idxNm='지수2' readonly> * 소성변형깊이(mm)/<input class="rd_var3" idxNm='지수3' readonly>(mm) <br/>
                                    = <input id="RD_VAR1" name="RD_VAR1" class="rd_var1" idxNm='지수1' readonly>
                                        - <input id="RD_VAR2" name="RD_VAR2" class="rd_var2" idxNm='지수2' readonly>
                                        * ( Low소성변형깊이(mm)/<input id="RD_VAR3" name="RD_VAR3" class="rd_var3" readonly>(mm)
                                        + Med소성변형깊이(mm)/<input id="RD_VAR4" name="RD_VAR4" class="rd_var3" idxNm='지수3' readonly>(mm)
                                        + Hi소성변형깊이(mm)/<input id="RD_VAR5" name="RD_VAR5" class="rd_var3" readonly>(mm) )
                                </td>
                                <td class="center">0</td>
                                <td class="center">10</td>
                            </tr>
                            <tr>
                                <td class="center">RCI</td>
                                <td>
                                    = (e^<input id="RCI_VAR1" name="RCI_VAR1" class="rci_var1" idxNm='지수1' readonly>
                                        x IRI^(<input id="RCI_VAR2" name="RCI_VAR2" class="rci_var2" idxNm='지수2' readonly>))/<input id="RCI_VAR3" name="RCI_VAR3" class="rci_var3" idxNm='지수3' readonly>
                                </td>
                                <td class="center">4</td>
                                <td class="center">10</td>
                            </tr>
                            <tr>
                                <td class="center">SCR</td>
                                <td>
                                    = <input id="SCR_VAR1" name="SCR_VAR1" class="scr_var1" idxNm='지수1' readonly> - ( AC + BC + LC + TC + PAT + POT + RD )<br/>
                                    &nbsp;&nbsp;&nbsp; 미국 FHWA 모델 인용, SCR >= 0, AC, BC, LC, TC, PAT, POT, RD >= 0<br/><br/>

                                    <table style="width: 200px;" class="inner">
                                        <colgroup>
                                            <col width="40px" />
                                            <col width="*" />
                                        </colgroup>
                                        <tbody>
	                                        <tr>
	                                            <td>* AC</td>
	                                            <td>= <input id="SCR_VAR2" name="SCR_VAR2" class="scr_var2" idxNm='지수2' readonly> - ACI</td>
	                                        </tr>
	                                        <tr>
                                                <td>* BC</td>
                                                <td>= <input id="SCR_VAR3" name="SCR_VAR3" class="scr_var3" idxNm='지수3' readonly> - BCI</td>
                                            </tr>
	                                        <tr>
	                                            <td>* LC</td>
	                                            <td>= <input id="SCR_VAR4" name="SCR_VAR4" class="scr_var4" idxNm='지수4' readonly> - LCI</td>
	                                        </tr>
	                                        <tr>
	                                            <td>* TC</td>
	                                            <td>= <input id="SCR_VAR5" name="SCR_VAR5" class="scr_var5" idxNm='지수5' readonly> - TCI</td>
	                                        </tr>
	                                        <tr>
	                                            <td>* PAT</td>
	                                            <td>= <input id="SCR_VAR6" name="SCR_VAR6" class="scr_var6" idxNm='지수6' readonly> - PATI</td>
	                                        </tr>
	                                        <tr>
	                                            <td>* POT</td>
	                                            <td>= <input id="SCR_VAR7" name="SCR_VAR7" class="scr_var7" idxNm='지수7' readonly> - POTI</td>
	                                        </tr>
	                                        <tr>
	                                            <td>* RD</td>
	                                            <td>= <input id="SCR_VAR8" name="SCR_VAR8" class="scr_var8" idxNm='지수8' readonly> - RDI</td>
	                                        </tr>
                                        </tbody>
                                    </table>
                                </td>
                                <td class="center">0</td>
                                <td class="center">10</td>
                            </tr>
                            <tr>
                                <td class="center">GPCI</td>
                                <td colspan="3">
                                    = α (SCR : 구조적 표면상태) + β (RCI : 평탄성)<br/>
                                    = <input id="GPCI_VAR1" name="GPCI_VAR1" class="gpci_var1" idxNm='SCR가중도계수' readonly> * SCR + <input id="GPCI_VAR2" name="GPCI_VAR2" class="gpci_var2" idxNm='RCI가중도계수' readonly> * RCI<br/><br/>
                                    * α, β : 가중분배계수
                                </td>
                            </tr>
				        </tbody>
				    </table>
				</div>
           </div>
	</div>
</form>
<!-- 공통 (START)-->
<%@ include file="/include/common.jsp" %>
<!-- 공통 (END)-->
<script type="text/javascript" language="javascript" defer="defer">
var lastsel;
//페이지 로딩 초기 설정
$( document ).ready(function() {

    // 메뉴 select
    fnAdminMenuSelect("menu4", 0);

    // 수식 변수 초기화
    fnInitFmlaVar();

    // 변수 변경 시 저장
    $("input").change(function() {

        //var userNo = ${sessionScope.userinfo.CRTR_NO};
        var userNo = ${sessionScope.userinfo.USER_NO};
        var classNm = $(this).attr("class");
        var idxNm = $(this).attr("idxNm");
        var value = $(this).val();

        // 같은 클래스의 값들 변경
        $("." + classNm).val(value);

        // 변경된 수식 파라미터
        var len = $("." + classNm).length;
        var fmlaId = $("." + classNm).eq(len-1).attr("id");

        var postDatas = {
            FRMULA_NM : fmlaId.split("_")[0].toString()
            /* , VAR_INDEX : fmlaId.substring(fmlaId.length, fmlaId.length - 1).toString() */
            , VAR_INDEX : idxNm
            , VAR : value.toString()
            , UPDUSR_NO : userNo.toString()
        };

        // DB값 변경
        $.ajax({
            url: "<c:url value='/srvy/api/srvyDtaEvlCngVal.do' />",
            contentType: 'application/json',
            data: JSON.stringify(postDatas),
            dataType: "json",
            cache: false,
            processData: false,
            type: 'POST',
            async: false,
            success: function (jdata) {
                if ( jdata != null && jdata != "" && jdata != undefined ) {
                    alert("수식이 변경되었습니다.");
                    return;
                }
            },
            error: function () {
                alert("값을 가져올 수 없습니다.");
                return;
            }
        });
    });
});

// 수식 변수 초기화
function fnInitFmlaVar() {

    var postDatas = {};

    $.ajax({
        url: "<c:url value='/srvy/api/srvyDtaEvlInitFmlaVar.do' />",
        contentType: 'application/json',
        data: JSON.stringify(postDatas),
        dataType: "json",
        cache: false,
        processData: false,
        type: 'POST',
        async: false,
        success: function (jdata) {
            if ( jdata != null && jdata != "" && jdata != undefined ) {
                for ( var i = 0; i < jdata.length; i++ ) {

                    var obj = jdata[i];
                    var frmulaNm = obj.FRMULA_NM.split("_")[0].toLowerCase();
                    var classNm = "";
                    var value = "";

                    if ( frmulaNm != "gpci" ) {

                        var num = obj.IDX_NM.substring(obj.IDX_NM.length, obj.IDX_NM.length - 1);
                        classNm = frmulaNm + "_var" + num;
                        $("." + classNm).val(obj.IDX_VAL);

                    } else if ( frmulaNm == "gpci" ) {
                        if ( obj.IDX_NM.startsWith("SCR") ) {
                            $(".gpci_var1").val(obj.IDX_VAL);

                        } else if ( obj.IDX_NM.startsWith("RCI") ) {
                            $(".gpci_var2").val(obj.IDX_VAL);
                        }
                    }
                }
            }
        },
        error: function () {

            alert("값을 가져올 수 없습니다.");
            return;

        }
    });
}

</script>
</body>
</html>
