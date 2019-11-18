CREATE OR REPLACE FUNCTION FN_REPAIR_TARGET_RANGE_VALUE(P_MODE TEXT, P_TRGET_SLCTN_NO NUMERIC, P_ROUTE_CODE TEXT, P_DIRECT_CODE TEXT, P_TRACK TEXT, P_STRTPT NUMERIC, P_ENDPT NUMERIC)
 RETURNS TEXT
 LANGUAGE plpgsql
AS $function$
    /*
        보수_대상_항목에서 시작점 종료점에 대한 그룹 데이터 추출

        생성일 : 2019-11-13
        생성자 : muhanit.kr
    */
DECLARE
    RET_VALUE VARCHAR(100);

BEGIN

    IF P_MODE = 'TQ' THEN  -- 교통량
        SELECT
            AVG(A.TRNSPORT_QY)::TEXT INTO RET_VALUE
        FROM TN_RPAIR_TRGET A
        WHERE
            1 = 1
            AND A.TRGET_SLCTN_NO = P_TRGET_SLCTN_NO  /* 대상_선정_번호 */
            AND A.ROUTE_CODE     = P_ROUTE_CODE     /* 노선_코드 */
            AND A.DIRECT_CODE    = P_DIRECT_CODE  /* 행선_코드 */
            AND A.TRACK          = P_TRACK           /* 차로 */
            AND A.STRTPT         BETWEEN P_STRTPT AND P_ENDPT  /* 시점 */
            AND A.ENDPT          BETWEEN P_STRTPT AND P_ENDPT;  /* 종점 */
    ELSIF P_MODE = 'LN' THEN  -- 연장
        SELECT
            SUM(AA.LEN)::TEXT INTO RET_VALUE
        FROM TN_RPAIR_TRGET A
        WHERE
            1 = 1
            AND A.TRGET_SLCTN_NO = P_TRGET_SLCTN_NO  /* 대상_선정_번호 */
            AND A.ROUTE_CODE     = P_ROUTE_CODE     /* 노선_코드 */
            AND A.DIRECT_CODE    = P_DIRECT_CODE  /* 행선_코드 */
            AND A.TRACK          = P_TRACK           /* 차로 */
            AND A.STRTPT         BETWEEN P_STRTPT AND P_ENDPT  /* 시점 */
            AND A.ENDPT          BETWEEN P_STRTPT AND P_ENDPT;  /* 종점 */
    ELSIF P_MODE = 'AR' THEN  -- 면적
        SELECT
            SUM(AA.AR)::TEXT INTO RET_VALUE
        FROM TN_RPAIR_TRGET A
        WHERE
            1 = 1
            AND A.TRGET_SLCTN_NO = P_TRGET_SLCTN_NO  /* 대상_선정_번호 */
            AND A.ROUTE_CODE     = P_ROUTE_CODE     /* 노선_코드 */
            AND A.DIRECT_CODE    = P_DIRECT_CODE  /* 행선_코드 */
            AND A.TRACK          = P_TRACK           /* 차로 */
            AND A.STRTPT         BETWEEN P_STRTPT AND P_ENDPT  /* 시점 */
            AND A.ENDPT          BETWEEN P_STRTPT AND P_ENDPT;  /* 종점 */
    ELSIF P_MODE = 'RM' THEN  -- 보수_공법_코드
        SELECT
            A.RPAIR_MTHD_CODE INTO RET_VALUE
        FROM (
            SELECT A.RPAIR_MTHD_CODE, COUNT(*) CNT
            FROM TN_RPAIR_TRGET A
            WHERE
                1 = 1
                AND A.TRGET_SLCTN_NO = P_TRGET_SLCTN_NO  /* 대상_선정_번호 */
                AND A.ROUTE_CODE     = P_ROUTE_CODE     /* 노선_코드 */
                AND A.DIRECT_CODE    = P_DIRECT_CODE  /* 행선_코드 */
                AND A.TRACK          = P_TRACK           /* 차로 */
                AND A.STRTPT         BETWEEN P_STRTPT AND P_ENDPT  /* 시점 */
                AND A.ENDPT          BETWEEN P_STRTPT AND P_ENDPT  /* 종점 */
            GROUP BY
                A.RPAIR_MTHD_CODE
        ) A
        ORDER BY CNT DESC
        LIMIT 1;
    ELSIF P_MODE = 'MD' THEN  -- 공법선정비율_결정방식_코드
        SELECT
            A.MSR_DM_CODE INTO RET_VALUE
        FROM (
            SELECT A.MSR_DM_CODE, COUNT(*) CNT
            FROM TN_RPAIR_TRGET A
            WHERE
                1 = 1
                AND A.TRGET_SLCTN_NO = P_TRGET_SLCTN_NO  /* 대상_선정_번호 */
                AND A.ROUTE_CODE     = P_ROUTE_CODE     /* 노선_코드 */
                AND A.DIRECT_CODE    = P_DIRECT_CODE  /* 행선_코드 */
                AND A.TRACK          = P_TRACK           /* 차로 */
                AND A.STRTPT         BETWEEN P_STRTPT AND P_ENDPT  /* 시점 */
                AND A.ENDPT          BETWEEN P_STRTPT AND P_ENDPT  /* 종점 */
            GROUP BY
                A.MSR_DM_CODE
        ) A
        ORDER BY CNT DESC
        LIMIT 1;
    END IF;

    IF NOT FOUND THEN
        RET_VALUE := NULL;
    END IF;

    RETURN (RET_VALUE);

EXCEPTION
    WHEN OTHERS THEN
        RETURN NULL;

END;
$function$
;