/**
 * 파손원인 도출 함수
 * author : skc@muhanit.kr
 * 2017-12-06 
 */



/* 
        파손원인 Object : 기후 / 교통량 / 기타

        생성일 : 2017-07-21
        생성자 : skc@muhanit.kr
*/
CREATE OR REPLACE TYPE "DMG_CUZ" AS OBJECT
(
    /* 
        파손원인 Object
            : 기후 / 교통량 / 기타

        생성일 : 2017-07-21
        생성자 : skc@muhanit.kr
    */
    DMG_CUZ_CLMT NUMBER(10,2),
    DMG_CUZ_VMTC NUMBER(10,2),
    DMG_CUZ_ETC NUMBER(10,2)
);



/**
 * 주 파손(지배결함) 도출
 * 
 * @param p_AC_IDX : 거북등 균열 지수
 * @param p_BC_IDX : 블럭 균열 지수
 * @param p_LC_IDX : 종방향 균열 지수
 * @param p_TC_IDX : 횡방향 균열 지수
 * @param p_PTCHG_IDX : 패칭 지수
 * @param p_POT_IDX : 포트홀 지수
 * @param p_RD_IDX : 소성변형 지수
 * @param p_RCI : 표면 조도 지수
 */
CREATE OR REPLACE FUNCTION FN_GET_CNTL_DFECT( p_AC_IDX IN NUMBER, p_BC_IDX IN NUMBER, p_LC_IDX IN NUMBER, p_TC_IDX IN NUMBER, p_PTCHG_IDX IN NUMBER, p_POT_IDX IN NUMBER, p_RD_IDX IN NUMBER, p_RCI IN NUMBER)
RETURN varchar2
IS
    RET_VALUE varchar2(10);
    MIN_VALUE number;
    IDX number;
BEGIN
    /* 
        지배결함(CNTL_DFECT) 산출
        
        생성일 : 2017-07-21
        생성자 : skc@muhanit.kr
    */
    MIN_VALUE := LEAST(p_AC_IDX, p_BC_IDX, p_LC_IDX, p_TC_IDX, p_PTCHG_IDX, p_POT_IDX, p_RD_IDX, p_RCI);
    RET_VALUE := NULL;
    IDX       := 0;
    
    IF MIN_VALUE = p_AC_IDX THEN
        RET_VALUE := 'DFCT0001';
        IDX       := IDX + 1;
    END IF;
    IF MIN_VALUE = p_BC_IDX THEN
        RET_VALUE := 'DFCT0008';
        IDX       := IDX + 1;
    END IF;
    IF MIN_VALUE = p_LC_IDX THEN
        RET_VALUE := 'DFCT0002';
        IDX       := IDX + 1;
    END IF;
    IF MIN_VALUE = p_TC_IDX THEN
        RET_VALUE := 'DFCT0003';
        IDX       := IDX + 1;
    END IF;
    IF MIN_VALUE = p_PTCHG_IDX THEN
        RET_VALUE := 'DFCT0004';
        IDX       := IDX + 1;
    END IF;
    IF MIN_VALUE = p_POT_IDX THEN
        RET_VALUE := 'DFCT0005';
        IDX       := IDX + 1;
    END IF;
    IF MIN_VALUE = p_RD_IDX THEN
        RET_VALUE := 'DFCT0006';
        IDX       := IDX + 1;
    END IF;
    IF MIN_VALUE = p_RCI THEN
        RET_VALUE := 'DFCT0007';
        IDX       := IDX + 1;
    END IF;
    
    IF IDX > 1 THEN
        RET_VALUE := 'DFCT0009';
    END IF;
    
    IF (MIN_VALUE = p_AC_IDX) 
        AND (MIN_VALUE = p_BC_IDX) AND (MIN_VALUE = p_LC_IDX) 
        AND (MIN_VALUE = p_TC_IDX) AND (MIN_VALUE = p_PTCHG_IDX) 
        AND (MIN_VALUE = p_POT_IDX) AND (MIN_VALUE = p_RD_IDX) 
        AND (MIN_VALUE = p_RCI) AND (MIN_VALUE = 100) THEN 
        RET_VALUE := NULL;
    END IF;
    
    RETURN (RET_VALUE);
  
EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 파손 원인 도출
 * 
 * @param p_AC_IDX : 거북등 균열 지수
 * @param p_BC_IDX : 블럭 균열 지수
 * @param p_LC_IDX : 종방향 균열 지수
 * @param p_TC_IDX : 횡방향 균열 지수
 * @param p_PTCHG_IDX : 패칭 지수
 * @param p_POT_IDX : 포트홀 지수
 * @param p_RD_IDX : 소성변형 지수
 * @param p_RCI : 표면 조도 지수
 */
create or replace FUNCTION FN_GET_DMG_CUZ( v_AC_IDX IN NUMBER,v_BC_IDX IN NUMBER,v_LC_IDX IN NUMBER,v_TC_IDX IN NUMBER,v_PTCHG_IDX IN NUMBER,v_POT_IDX IN NUMBER,v_RD_IDX IN NUMBER,v_RCI IN NUMBER)
RETURN DMG_CUZ
AS
v_DMG_CUZ_CLMT NUMBER; /* 최소_구간_조사_자료.파손_원인_기후 */
v_DMG_CUZ_VMTC NUMBER; /* 최소_구간_조사_자료.파손_원인_교통량 */
v_DMG_CUZ_ETC NUMBER; /* 최소_구간_조사_자료.파손_원인_기타 */
BEGIN
    /* 
        파손 원인(DMG_CUZ) 산출 : 기후 / 교통량 / 기타

        생성일 : 2017-07-21
        생성자 : skc@muhanit.kr
    */
    WITH DMGCUZTB AS( 
              SELECT (10 - v_AC_IDX) AC_RATE
                   , (10 - v_BC_IDX) BC_RATE
                   , (10 - v_LC_IDX) LC_RATE
                   , (10 - v_TC_IDX) TC_RATE
                   , (10 - v_PTCHG_IDX) PTCHG_RATE
                   , (10 - v_POT_IDX) POT_RATE
                   , (10 - v_RD_IDX) RD_RATE
                   , (10 - v_RCI) RCI_RATE
                   , ((10 - v_AC_IDX) + (10 - v_BC_IDX) + (10 - v_LC_IDX) + (10 - v_TC_IDX) + (10 - v_PTCHG_IDX) + (10 - v_POT_IDX) + (10 - v_RD_IDX) + (10 - v_RCI)) SUM_RATE
              FROM DUAL )
            SELECT DECODE(SUM_RATE, 0, 0, ROUND((PTCHG_RATE + POT_RATE)/SUM_RATE * 100,2))
                 , DECODE(SUM_RATE, 0, 0, ROUND((AC_RATE + BC_RATE + LC_RATE + RD_RATE + RCI_RATE)/SUM_RATE * 100,2))
                 , DECODE(SUM_RATE, 0, 0, ROUND((TC_RATE)/SUM_RATE * 100,2))
              INTO v_DMG_CUZ_CLMT /* 최소_구간_조사_자료.파손_원인_기후 */
                 , v_DMG_CUZ_VMTC /* 최소_구간_조사_자료.파손_원인_교통량 */
                 , v_DMG_CUZ_ETC /* 최소_구간_조사_자료.파손_원인_기타 */
            FROM DMGCUZTB;  

    RETURN DMG_CUZ(v_DMG_CUZ_CLMT,v_DMG_CUZ_VMTC,v_DMG_CUZ_ETC);

EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;