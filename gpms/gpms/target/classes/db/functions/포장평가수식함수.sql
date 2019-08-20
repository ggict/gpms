/**
 * 포장평가 수식 도출 함수
 * author : skc@muhanit.kr
 * 2017-12-06 
 */


/**
 * 포장평가 수식 - AC_IDX
 * 거북등 균열 지수
 * 
 * @param p_LOW_VAL : 거북등 균열 LOW 값
 * @param p_MED_VAL : 거북등 균열 MED 값
 * @param p_HI_VAL : 거북등 균열 HI 값
 */
CREATE OR REPLACE FUNCTION FN_GET_AC_IDX( p_LOW_VAL IN NUMBER,p_MED_VAL IN NUMBER,p_HI_VAL IN NUMBER)
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
BEGIN
    /* 
	        거북등 균열 지수(AC_INDEX) 산출
	        
	        생성일 : 2017-07-21
	        생성자 : skc@muhanit.kr
    */
    WITH FORMULAIDX AS(        
    SELECT        
          tnpavfrmulaidx.FRMULA_NO    /* 포장_수식_지수.수식_번호 */
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수1', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) IDX_1    
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수2', tnpavfrmulaidx.IDX_VAL ) ) ,'4'))  IDX_2   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수3', tnpavfrmulaidx.IDX_VAL ) ) ,'10.5'))  IDX_3   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수4', tnpavfrmulaidx.IDX_VAL ) ) ,'10.5'))  IDX_4  
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수5', tnpavfrmulaidx.IDX_VAL ) ) ,'10.5'))  IDX_5  
         --,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수6', tnpavfrmulaidx.IDX_VAL ) ) ,'25'))  IDX_6           
         --INTO v_SCR_FRMULA_NO,  v_SCR_IDX1, v_SCR_IDX2 ,     v_SCR_IDX3 , v_SCR_IDX4 , v_SCR_IDX5, v_SCR_IDX6, v_SCR_MAX
      FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
      INNER JOIN TN_PAV_FRMULA_IDX  tnpavfrmulaidx  /*** 포장_수식_지수 테이블 ***/
      ON  tnpavfrmulaidx.FRMULA_NO = tnpavfrmula.FRMULA_NO                 /* 포장_수식_지수.수식_번호 */
      AND tnpavfrmulaidx.DELETE_AT =  'N'                    /* 포장_수식_지수.삭제_여부 */
       AND tnpavfrmulaidx.USE_AT = 'Y'                          /* 포장_수식_지수.사용_여부 */ 
     WHERE tnpavfrmula.FRMULA_NM = 'AC_IDX'                              /* 포장_수식.수식_번호 */
       AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
       AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
       GROUP BY tnpavfrmulaidx.FRMULA_NO)
    SELECT IDX_1 - (IDX_2 * ((p_LOW_VAL / IDX_3 ) + (p_MED_VAL / IDX_4) + ( p_HI_VAL / IDX_5 )) ) INTO RET_VALUE FROM FORMULAIDX;  
   
    RET_VALUE :=GREATEST(RET_VALUE, 0);   
       
    RETURN (RET_VALUE);
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 포장평가 수식 - BC_IDX
 * 블럭 균열 지수
 * 
 * @param p_LOW_VAL : 블럭 균열 LOW 값
 * @param p_MED_VAL : 블럭 균열 MED 값
 * @param p_HI_VAL : 블럭 균열 HI 값
 */
CREATE OR REPLACE FUNCTION FN_GET_BC_IDX( p_LOW_VAL IN NUMBER,p_MED_VAL IN NUMBER,p_HI_VAL IN NUMBER)
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
BEGIN
    /* 
	        블럭 균열 지수(BC_INDEX) 산출
	
	        생성일 : 2017-08-23
	        생성자 : skc@muhanit.kr
    */
    WITH FORMULAIDX AS(        
    SELECT        
          tnpavfrmulaidx.FRMULA_NO    /* 포장_수식_지수.수식_번호 */
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수1', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) IDX_1    
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수2', tnpavfrmulaidx.IDX_VAL ) ) ,'4'))  IDX_2   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수3', tnpavfrmulaidx.IDX_VAL ) ) ,'10.5'))  IDX_3   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수4', tnpavfrmulaidx.IDX_VAL ) ) ,'10.5'))  IDX_4  
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수5', tnpavfrmulaidx.IDX_VAL ) ) ,'10.5'))  IDX_5  
         --,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수6', tnpavfrmulaidx.IDX_VAL ) ) ,'25'))  IDX_6           
         --INTO v_SCR_FRMULA_NO,  v_SCR_IDX1, v_SCR_IDX2 ,     v_SCR_IDX3 , v_SCR_IDX4 , v_SCR_IDX5, v_SCR_IDX6, v_SCR_MAX
      FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
      INNER JOIN TN_PAV_FRMULA_IDX  tnpavfrmulaidx  /*** 포장_수식_지수 테이블 ***/
      ON  tnpavfrmulaidx.FRMULA_NO = tnpavfrmula.FRMULA_NO                 /* 포장_수식_지수.수식_번호 */
      AND tnpavfrmulaidx.DELETE_AT =  'N'                    /* 포장_수식_지수.삭제_여부 */
       AND tnpavfrmulaidx.USE_AT = 'Y'                          /* 포장_수식_지수.사용_여부 */ 
     WHERE tnpavfrmula.FRMULA_NM = 'BC_IDX'                              /* 포장_수식.수식_번호 */
       AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
       AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
       GROUP BY tnpavfrmulaidx.FRMULA_NO)
    SELECT IDX_1 - (IDX_2 * ((p_LOW_VAL / IDX_3 ) + (p_MED_VAL / IDX_4) + (p_HI_VAL / IDX_5 )) ) INTO RET_VALUE FROM FORMULAIDX;  

    RET_VALUE :=GREATEST(RET_VALUE, 0);   

    RETURN (RET_VALUE);

EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 포장평가 수식 - LC_IDX
 * 종방향 균열 지수
 * 
 * @param p_LOW_VAL : 종방향 균열 LOW 값
 * @param p_MED_VAL : 종방향 균열 MED 값
 * @param p_HI_VAL : 종방향 균열 HI 값
 */
CREATE OR REPLACE FUNCTION FN_GET_LC_IDX( p_LOW_VAL IN NUMBER,p_MED_VAL IN NUMBER,p_HI_VAL IN NUMBER)
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
BEGIN
    /* 
	        종방향 균열 지수(LC_INDEX) 산출
	        
	        생성일 : 2017-07-21
	        생성자 : skc@muhanit.kr
    */
    WITH FORMULAIDX AS(        
    SELECT        
          tnpavfrmulaidx.FRMULA_NO    /* 포장_수식_지수.수식_번호 */
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수1', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) IDX_1   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수2', tnpavfrmulaidx.IDX_VAL ) ) ,'4'))  IDX_2   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수3', tnpavfrmulaidx.IDX_VAL ) ) ,'20'))  IDX_3  
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수4', tnpavfrmulaidx.IDX_VAL ) ) ,'20'))  IDX_4  
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수5', tnpavfrmulaidx.IDX_VAL ) ) ,'20'))  IDX_5  
         --INTO v_SCR_FRMULA_NO,  v_SCR_IDX1, v_SCR_IDX2 ,     v_SCR_IDX3 , v_SCR_IDX4 , v_SCR_IDX5, v_SCR_IDX6, v_SCR_MAX
      FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
      INNER JOIN TN_PAV_FRMULA_IDX  tnpavfrmulaidx  /*** 포장_수식_지수 테이블 ***/
      ON  tnpavfrmulaidx.FRMULA_NO = tnpavfrmula.FRMULA_NO                 /* 포장_수식_지수.수식_번호 */
      AND tnpavfrmulaidx.DELETE_AT =  'N'                    /* 포장_수식_지수.삭제_여부 */
       AND tnpavfrmulaidx.USE_AT = 'Y'                          /* 포장_수식_지수.사용_여부 */ 
     WHERE tnpavfrmula.FRMULA_NM = 'LC_IDX'                              /* 포장_수식.수식_번호 */
       AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
       AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
       GROUP BY tnpavfrmulaidx.FRMULA_NO)
    SELECT IDX_1 - IDX_2 * ((p_LOW_VAL / IDX_3 ) + ( p_MED_VAL / IDX_4 ) + (p_HI_VAL  / IDX_5 ) )            INTO RET_VALUE FROM FORMULAIDX;  
    
    RET_VALUE :=GREATEST(RET_VALUE, 0);   
       
    RETURN (RET_VALUE);
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 포장평가 수식 - TC_IDX
 * 횡방향 균열 지수
 * 
 * @param p_LOW_VAL : 횡방향 균열 LOW 값
 * @param p_MED_VAL : 횡방향 균열 MED 값
 * @param p_HI_VAL : 횡방향 균열 HI 값
 */
CREATE OR REPLACE FUNCTION FN_GET_TC_IDX( p_LOW_VAL IN NUMBER,p_MED_VAL IN NUMBER,p_HI_VAL IN NUMBER)
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
BEGIN
    /* 
	        횡방향 균열 지수(TC_INDEX) 산출
	        
	        생성일 : 2017-07-21
	        생성자 : skc@muhanit.kr
    */
    WITH FORMULAIDX AS(        
    SELECT        
          tnpavfrmulaidx.FRMULA_NO    /* 포장_수식_지수.수식_번호 */
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수1', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) IDX_1     
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수2', tnpavfrmulaidx.IDX_VAL ) ) ,'4'))  IDX_2   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수3', tnpavfrmulaidx.IDX_VAL ) ) ,'20'))  IDX_3  
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수4', tnpavfrmulaidx.IDX_VAL ) ) ,'20'))  IDX_4  
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수5', tnpavfrmulaidx.IDX_VAL ) ) ,'20'))  IDX_5  
         --,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수6', tnpavfrmulaidx.IDX_VAL ) ) ,'4.8'))  IDX_6  
         --INTO v_SCR_FRMULA_NO,  v_SCR_IDX1, v_SCR_IDX2 ,     v_SCR_IDX3 , v_SCR_IDX4 , v_SCR_IDX5, v_SCR_IDX6, v_SCR_MAX
      FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
      INNER JOIN TN_PAV_FRMULA_IDX  tnpavfrmulaidx  /*** 포장_수식_지수 테이블 ***/
      ON  tnpavfrmulaidx.FRMULA_NO = tnpavfrmula.FRMULA_NO                 /* 포장_수식_지수.수식_번호 */
      AND tnpavfrmulaidx.DELETE_AT =  'N'                    /* 포장_수식_지수.삭제_여부 */
       AND tnpavfrmulaidx.USE_AT = 'Y'                          /* 포장_수식_지수.사용_여부 */ 
     WHERE tnpavfrmula.FRMULA_NM = 'TC_IDX'                              /* 포장_수식.수식_번호 */
       AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
       AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
       GROUP BY tnpavfrmulaidx.FRMULA_NO)
    SELECT IDX_1 - IDX_2 * ((p_LOW_VAL / IDX_3 ) + ( p_MED_VAL / IDX_4 ) + ( p_HI_VAL  / IDX_5 ) )       INTO RET_VALUE FROM FORMULAIDX;  
    
    RET_VALUE :=GREATEST(RET_VALUE, 0);   
       
    RETURN (RET_VALUE);
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 포장평가 수식 - PTCHG_IDX
 * 패칭 지수
 * 
 * @param p_PTCHG_VAL : 패칭 값
 */
CREATE OR REPLACE FUNCTION      FN_GET_PATCH_IDX( p_PTCHG_VAL IN NUMBER )
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
BEGIN
    /* 
        패칭 지수(PATCH_INDEX) 산출
        
        생성일 : 2017-07-21
        생성자 : skc@muhanit.kr
    */
    WITH FORMULAIDX AS(        
    SELECT        
          tnpavfrmulaidx.FRMULA_NO    /* 포장_수식_지수.수식_번호 */
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수1', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) IDX_1   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수2', tnpavfrmulaidx.IDX_VAL ) ) ,'4'))  IDX_2   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수3', tnpavfrmulaidx.IDX_VAL ) ) ,'28'))  IDX_3  
         
         --INTO v_SCR_FRMULA_NO,  v_SCR_IDX1, v_SCR_IDX2 ,     v_SCR_IDX3 , v_SCR_IDX4 , v_SCR_IDX5, v_SCR_IDX6, v_SCR_MAX
      FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
      INNER JOIN TN_PAV_FRMULA_IDX  tnpavfrmulaidx  /*** 포장_수식_지수 테이블 ***/
      ON  tnpavfrmulaidx.FRMULA_NO = tnpavfrmula.FRMULA_NO                 /* 포장_수식_지수.수식_번호 */
      AND tnpavfrmulaidx.DELETE_AT =  'N'                    /* 포장_수식_지수.삭제_여부 */
       AND tnpavfrmulaidx.USE_AT = 'Y'                          /* 포장_수식_지수.사용_여부 */ 
     WHERE tnpavfrmula.FRMULA_NM = 'PTCHG_IDX'                              /* 포장_수식.수식_번호 */
       AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
       AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
       GROUP BY tnpavfrmulaidx.FRMULA_NO)
    SELECT IDX_1 - IDX_2 * (   p_PTCHG_VAL  / IDX_3 )            INTO RET_VALUE FROM FORMULAIDX;   
    --SELECT IDX_1 - IDX_2 * ( ( ( p_PTCHG_VAL + p_POTHOLE_VAL ) / ( 3.25 * 10 ) ) / IDX_3 )            INTO RET_VALUE FROM FORMULAIDX;  측정 면적으로 나뉘어진 값이 들어와야 함.
       
    RET_VALUE :=GREATEST(RET_VALUE, 0);
       
    RETURN (RET_VALUE);
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 포장평가 수식 - POT_IDX
 * 포트홀 지수
 * 
 * @param p_POTHOLE_VAL : 포트홀 값
 */
create or replace FUNCTION      FN_GET_POT_IDX( p_POTHOLE_VAL IN NUMBER )
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
BEGIN
    /* 
	        포트홀 지수(POT_INDEX) 산출
	        
	        생성일 : 2017-07-21
	        생성자 : skc@muhanit.kr
    */
    WITH FORMULAIDX AS(        
    SELECT        
          tnpavfrmulaidx.FRMULA_NO    /* 포장_수식_지수.수식_번호 */
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수1', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) IDX_1   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수2', tnpavfrmulaidx.IDX_VAL ) ) ,'4'))  IDX_2   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수3', tnpavfrmulaidx.IDX_VAL ) ) ,'28'))  IDX_3  
         
         --INTO v_SCR_FRMULA_NO,  v_SCR_IDX1, v_SCR_IDX2 ,     v_SCR_IDX3 , v_SCR_IDX4 , v_SCR_IDX5, v_SCR_IDX6, v_SCR_MAX
      FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
      INNER JOIN TN_PAV_FRMULA_IDX  tnpavfrmulaidx  /*** 포장_수식_지수 테이블 ***/
      ON  tnpavfrmulaidx.FRMULA_NO = tnpavfrmula.FRMULA_NO                 /* 포장_수식_지수.수식_번호 */
      AND tnpavfrmulaidx.DELETE_AT =  'N'                    /* 포장_수식_지수.삭제_여부 */
       AND tnpavfrmulaidx.USE_AT = 'Y'                          /* 포장_수식_지수.사용_여부 */ 
     WHERE tnpavfrmula.FRMULA_NM = 'POT_IDX'                              /* 포장_수식.수식_번호 */
       AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
       AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
       GROUP BY tnpavfrmulaidx.FRMULA_NO)
    SELECT IDX_1 - IDX_2 * (  p_POTHOLE_VAL  / IDX_3 )            INTO RET_VALUE FROM FORMULAIDX;   
    --SELECT IDX_1 - IDX_2 * ( ( ( p_PTCHG_VAL + p_POTHOLE_VAL ) / ( 3.25 * 10 ) ) / IDX_3 )            INTO RET_VALUE FROM FORMULAIDX;  측정 면적으로 나뉘어진 값이 들어와야 함.
    
    RET_VALUE :=GREATEST(RET_VALUE, 0);   
       
    RETURN (RET_VALUE);
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 포장평가 수식 - RD_IDX
 * 소성변형 지수
 * 
 * @param p_LOW_VAL : 소성변형 LOW 값
 * @param p_MED_VAL : 소성변형 MED 값
 * @param p_HI_VAL : 소성변형 HI 값
 */
CREATE OR REPLACE FUNCTION      FN_GET_RUT_IDX( p_LOW_VAL IN NUMBER,p_MED_VAL IN NUMBER,p_HI_VAL IN NUMBER)
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
BEGIN
    /* 
	        소성변형 지수(RUT_INDEX) 산출
	        
	        생성일 : 2017-07-21
	        생성자 : skc@muhanit.kr
	    */
    WITH FORMULAIDX AS(        
    SELECT        
          tnpavfrmulaidx.FRMULA_NO    /* 포장_수식_지수.수식_번호 */
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수1', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) IDX_1   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수2', tnpavfrmulaidx.IDX_VAL ) ) ,'4'))  IDX_2   
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수3', tnpavfrmulaidx.IDX_VAL ) ) ,'21'))  IDX_3  
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수4', tnpavfrmulaidx.IDX_VAL ) ) ,'21'))  IDX_4
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수5', tnpavfrmulaidx.IDX_VAL ) ) ,'21'))  IDX_5  
         --,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수6', tnpavfrmulaidx.IDX_VAL ) ) ,'50'))  IDX_6  
         --INTO v_SCR_FRMULA_NO,  v_SCR_IDX1, v_SCR_IDX2 ,     v_SCR_IDX3 , v_SCR_IDX4 , v_SCR_IDX5, v_SCR_IDX6, v_SCR_MAX
      FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
      INNER JOIN TN_PAV_FRMULA_IDX  tnpavfrmulaidx  /*** 포장_수식_지수 테이블 ***/
      ON  tnpavfrmulaidx.FRMULA_NO = tnpavfrmula.FRMULA_NO                 /* 포장_수식_지수.수식_번호 */
      AND tnpavfrmulaidx.DELETE_AT =  'N'                    /* 포장_수식_지수.삭제_여부 */
       AND tnpavfrmulaidx.USE_AT = 'Y'                          /* 포장_수식_지수.사용_여부 */ 
     WHERE tnpavfrmula.FRMULA_NM = 'RD_IDX'                              /* 포장_수식.수식_번호 */
       AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
       AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
       GROUP BY tnpavfrmulaidx.FRMULA_NO)
    SELECT IDX_1 - IDX_2 * ((p_LOW_VAL / IDX_3 ) + ( p_MED_VAL /IDX_4 ) + ( p_HI_VAL  / IDX_5 ))            INTO RET_VALUE FROM FORMULAIDX;  
   
    RET_VALUE := GREATEST(RET_VALUE, 0);
       
    RETURN (RET_VALUE);
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 포장평가 수식 - SCR
 * 포면 상태 지수 산출
 * 
 * @param p_AC_IDX : 거북등 균열 지수
 * @param p_BC_IDX : 블럭 균열 지수
 * @param p_LC_IDX : 종방향 균열 지수
 * @param p_TC_IDX : 횡방향 균열 지수
 * @param p_PTCHG_IDX : 패칭 지수
 * @param p_POT_IDX : 포트홀 지수
 * @param p_RD_IDX : 소성변형 지수
 */
CREATE OR REPLACE FUNCTION FN_GET_SCR( p_AC_IDX IN NUMBER, p_BC_IDX IN NUMBER, p_LC_IDX IN NUMBER, p_TC_IDX IN NUMBER, p_PTCHG_IDX IN NUMBER, p_POT_IDX IN NUMBER, p_RD_IDX IN NUMBER )
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
BEGIN
    /* 
        표면 상태 지수(SRC) 산출
        
        생성일 : 2017-07-21
        생성자 : skc@muhanit.kr
    */
    WITH FORMULAIDX AS(        
       SELECT        
              tnpavfrmulaidx.FRMULA_NO    /* 포장_수식_지수.수식_번호 */
             ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수1', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) SCR_IDX1
             ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수2', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) SCR_IDX2
             ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수3', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) SCR_IDX3
             ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수4', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) SCR_IDX4
             ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수5', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) SCR_IDX5
             ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수6', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) SCR_IDX6
             ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수7', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) SCR_IDX7
             ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수8', tnpavfrmulaidx.IDX_VAL ) ) ,'10')) SCR_IDX8
             --,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '한계값', tnpavfrmulaidx.IDX_VAL ) ) ,'60')) SCR_MAX -- 2017.7 현재 사용하지 않음.
           --  INTO v_SCR_FRMULA_NO,  v_SCR_IDX1, v_SCR_IDX2 ,     v_SCR_IDX3 , v_SCR_IDX4 , v_SCR_IDX5, v_SCR_IDX6, v_SCR_MAX
          FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
          INNER JOIN TN_PAV_FRMULA_IDX  tnpavfrmulaidx  /*** 포장_수식_지수 테이블 ***/
          ON  tnpavfrmulaidx.FRMULA_NO = tnpavfrmula.FRMULA_NO                 /* 포장_수식_지수.수식_번호 */
          AND tnpavfrmulaidx.DELETE_AT =  'N'                    /* 포장_수식_지수.삭제_여부 */
           AND tnpavfrmulaidx.USE_AT = 'Y'                          /* 포장_수식_지수.사용_여부 */ 
         WHERE tnpavfrmula.FRMULA_NM = 'SCR'                              /* 포장_수식.수식_번호 */
           AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
           AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
           GROUP BY tnpavfrmulaidx.FRMULA_NO )
    
    SELECT SCR_IDX1 - (( SCR_IDX2 - p_AC_IDX ) + (SCR_IDX3 - p_BC_IDX) + (SCR_IDX4 - p_LC_IDX) + (SCR_IDX5 - p_TC_IDX) + (SCR_IDX6 - p_PTCHG_IDX) + (SCR_IDX7 - p_POT_IDX) + (SCR_IDX8 - p_RD_IDX))
        INTO RET_VALUE FROM FORMULAIDX;

    RET_VALUE := GREATEST(RET_VALUE, 0);   
   
    RETURN (RET_VALUE);
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 포장평가 수식 - RCI
 * 포면 조도 지수 산출
 * 
 * @param p_IRI_VAL : 종단평탄성 값
 */
CREATE OR REPLACE FUNCTION FN_GET_RCI( p_IRI_VAL IN NUMBER )
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
BEGIN
    /* 
	        표면 조도 지수(RCI) 산출
	        
	        생성일 : 2017-07-21
	        생성자 : skc@muhanit.kr
    */
    WITH FORMULAIDX AS(        
   SELECT        
          tnpavfrmulaidx.FRMULA_NO    /* 포장_수식_지수.수식_번호 */
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수1', tnpavfrmulaidx.IDX_VAL ) ) ,'4.514'))        RCI_IDX1
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수2', tnpavfrmulaidx.IDX_VAL ) ) ,'-0.234'))         RCI_IDX2
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수3', tnpavfrmulaidx.IDX_VAL ) ) ,'10'))  RCI_IDX3
         --,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '지수4', tnpavfrmulaidx.IDX_VAL ) ) ,'-0.2582677'))   RCI_IDX4
         --,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '최소값', tnpavfrmulaidx.IDX_VAL ) ) ,'40'))        RCI_MIN --현재 적용하지 않는 값
         --,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '최대값', tnpavfrmulaidx.IDX_VAL ) ) ,'1000'))      RCI_MAX --현재 적용하지 않는 값
        -- INTO v_RCI_FRMULA_NO,  v_RCI_IDX1, v_RCI_IDX2 ,     v_RCI_IDX3 , v_RCI_IDX4 , v_RCI_MIN, v_RCI_MAX
      FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
      INNER JOIN TN_PAV_FRMULA_IDX  tnpavfrmulaidx  /*** 포장_수식_지수 테이블 ***/
      ON  tnpavfrmulaidx.FRMULA_NO = tnpavfrmula.FRMULA_NO                 /* 포장_수식_지수.수식_번호 */
      AND tnpavfrmulaidx.DELETE_AT =  'N'                    /* 포장_수식_지수.삭제_여부 */
       AND tnpavfrmulaidx.USE_AT = 'Y'                          /* 포장_수식_지수.사용_여부 */ 
     WHERE tnpavfrmula.FRMULA_NM = 'RCI'                              /* 포장_수식.수식_번호 */
       AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
       AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
       GROUP BY tnpavfrmulaidx.FRMULA_NO )
   
    SELECT (EXP(RCI_IDX1) * POWER(p_IRI_VAL, RCI_IDX2)) / RCI_IDX3 
        INTO RET_VALUE FROM FORMULAIDX;
        
    RET_VALUE := GREATEST(RET_VALUE, 0);
    --    v_RCI := GREATEST(v_RCI, v_RCI_MIN);
    --    v_RCI := LEAST(v_RCI, v_RCI_MAX);
       
       
    RETURN (RET_VALUE);
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;



/**
 * 포장평가 수식 - GPCI
 * GPCI 산출
 * 
 * @param p_SCR : 표면 상태 지수
 * @param p_RCI : 표면 조도 지수
 */
CREATE OR REPLACE FUNCTION FN_GET_GPCI( p_SCR IN NUMBER, p_RCI IN NUMBER )
RETURN NUMBER
IS
    RET_VALUE NUMBER(10,2);
BEGIN
    /* 
	        포장 상태 지수 (GPCI) 산출
	        
	        생성일 : 2017-07-21
	        생성자 : skc@muhanit.kr
	    */
    WITH FORMULAIDX AS(        
        
       SELECT        
          tnpavfrmulaidx.FRMULA_NO    /* 포장_수식_지수.수식_번호 */
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, 'SCR가중도계수', tnpavfrmulaidx.IDX_VAL ) ) ,'0.80'))  GPCI_SCR_IDX
         ,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, 'RCI가중도계수', tnpavfrmulaidx.IDX_VAL ) ) ,'0.20'))  GPCI_RCI_IDX
         --,TO_NUMBER(NVL( MAX(DECODE(tnpavfrmulaidx.IDX_NM, '한계값', tnpavfrmulaidx.IDX_VAL ) ) ,'60'))         GPCI_MAX 
      --    INTO v_GPCI_FRMULA_NO,  v_GPCI_SCR_IDX, v_GPCI_RCI_IDX ,  v_GPCI_MAX
      FROM TN_PAV_FRMULA  tnpavfrmula  /*** 포장_수식 테이블 ***/
      INNER JOIN TN_PAV_FRMULA_IDX  tnpavfrmulaidx  /*** 포장_수식_지수 테이블 ***/
      ON  tnpavfrmulaidx.FRMULA_NO = tnpavfrmula.FRMULA_NO                 /* 포장_수식_지수.수식_번호 */
      AND tnpavfrmulaidx.DELETE_AT =  'N'                    /* 포장_수식_지수.삭제_여부 */
       AND tnpavfrmulaidx.USE_AT = 'Y'                          /* 포장_수식_지수.사용_여부 */ 
     WHERE tnpavfrmula.FRMULA_NM = 'GPCI'                              /* 포장_수식.수식_번호 */
       AND tnpavfrmula.DELETE_AT = 'N'                              /* 포장_수식.삭제_여부 */
       AND tnpavfrmula.USE_AT = 'Y'                                    /* 포장_수식.사용_여부 */
       GROUP BY tnpavfrmulaidx.FRMULA_NO )
    
   SELECT (GPCI_SCR_IDX * p_SCR) + (GPCI_RCI_IDX * p_RCI)
        INTO RET_VALUE FROM FORMULAIDX;
    
   
    RETURN (RET_VALUE);
      
EXCEPTION 
WHEN OTHERS THEN 
  RETURN NULL;

END;