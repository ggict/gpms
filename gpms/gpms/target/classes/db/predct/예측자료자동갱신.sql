/**
 * [포장상태 예측]
 * 예측 자료 자동 갱신용 프로시저 
 *
 * @PARAM p_USER_NO : 싱행 사용자 번호
 * @PARAM p_MODE : 실행 모드
 * @PARAM o_PROCCODE : 싱행 결과 코드 (OUT)
 * @PARAM o_PROCMSG : 싱행 결과 메세지 (OUT)
 */
create or replace PROCEDURE      "PRC_JOB_PREDCT" 
/*
    Oracle Schedual Job 등록 - 예측자료 자동갱신

    생성일 : 2017-09-18
    생성자 : skc@muhanit.kr
*/
AS
v_USER_NO           NUMBER;
v_MODE              VARCHAR2(20);
v_PROCCODE          VARCHAR2(20);
v_PROCMSG           VARCHAR2(1024);

CURSOR route_list IS 
     SELECT ROAD_NO from TN_ROUTE_INFO;

-- DECLARE RAISE_EXT exception;
BEGIN
    v_USER_NO   := 3;
    v_MODE      := 'NONE';
    v_PROCCODE  := NULL;
    v_PROCMSG   := NULL;

    FOR v_erow IN route_list 
    LOOP 
        GPMS.PRC_CLAC_PREDCT_LAST(v_erow.ROAD_NO, NULL, v_USER_NO, v_MODE, v_PROCCODE, v_PROCMSG);

    END LOOP;

    v_PROCMSG  := '예측자료 자동갱신 완료';   
    v_PROCCODE :='true';

    DBMS_OUTPUT.PUT_LINE('v_PROCCODE= '||v_PROCCODE);
    DBMS_OUTPUT.PUT_LINE('v_PROCMSG= '||v_PROCMSG);

EXCEPTION  
    WHEN OTHERS
    THEN
        v_PROCMSG := SUBSTR(SQLERRM,1,1000);
        v_PROCCODE :='false';

        DBMS_OUTPUT.PUT_LINE('v_PROCCODE= '||v_PROCCODE);
        DBMS_OUTPUT.PUT_LINE('v_PROCMSG= '||v_PROCMSG);

    RAISE;      
END PRC_JOB_PREDCT;