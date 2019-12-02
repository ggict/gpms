-- CELL_10 인덱스 생성
CREATE INDEX CELL_10_IDX ON CELL_10(ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT);

-- 200m VIEW 생성
CREATE VIEW V_CELL_200 AS
    SELECT
        A.GID
        , A.OBJECT_ID
        , A.ROUTE_CODE
        , A.DIRECT_CODE
        , A.TRACK
        , A.STRTPT
        , MAX(B.ENDPT) ENDPT
        , COUNT(*) PT_LENGTH
        , A.CELL_ID
--        , LEAD(A.STRTPT) OVER(ORDER BY A.ROUTE_CODE ASC, A.DIRECT_CODE DESC, A.TRACK ASC, A.STRTPT ASC, A.ENDPT) STRTPT2
--        , B.STRTPT STRTPT_
        , ST_COLLECT(B.GEOM) GEOM
        , ARRAY_TO_STRING(ARRAY_AGG(B.CELL_ID), ',') CELL_IDS
--        , A.DIRECT_CODE
    FROM CELL_10 A
    INNER JOIN CELL_10 B
        ON B.ROUTE_CODE = A.ROUTE_CODE
        AND B.DIRECT_CODE = A.DIRECT_CODE
        AND B.TRACK = A.TRACK
        AND B.STRTPT >= A.STRTPT
        AND B.STRTPT < A.STRTPT + 200
    GROUP BY
        A.GID
        , A.OBJECT_ID
        , A.ROUTE_CODE
        , A.DIRECT_CODE
        , A.TRACK
        , A.STRTPT
        , A.ENDPT
        , A.CELL_ID
    ORDER BY
        A.ROUTE_CODE ASC
        , A.DIRECT_CODE DESC
        , A.TRACK ASC
        , A.STRTPT ASC
        , A.ENDPT
;

-- 1km VIEW 생성
CREATE VIEW V_CELL_1000 AS
    SELECT
        A.GID
        , A.OBJECT_ID
        , A.ROUTE_CODE
        , A.DIRECT_CODE
        , A.TRACK
        , A.STRTPT
        , MAX(B.ENDPT) ENDPT
        , COUNT(*) PT_LENGTH
        , A.CELL_ID
--        , LEAD(A.STRTPT) OVER(ORDER BY A.ROUTE_CODE ASC, A.DIRECT_CODE DESC, A.TRACK ASC, A.STRTPT ASC, A.ENDPT) STRTPT2
--        , B.STRTPT STRTPT_
        , ST_COLLECT(B.GEOM) GEOM
        , ARRAY_TO_STRING(ARRAY_AGG(B.CELL_ID), ',') CELL_IDS
--        , A.DIRECT_CODE
    FROM CELL_10 A
    INNER JOIN CELL_10 B
        ON B.ROUTE_CODE = A.ROUTE_CODE
        AND B.DIRECT_CODE = A.DIRECT_CODE
        AND B.TRACK = A.TRACK
        AND B.STRTPT >= A.STRTPT
        AND B.STRTPT < A.STRTPT + 1000
    GROUP BY
        A.GID
        , A.OBJECT_ID
        , A.ROUTE_CODE
        , A.DIRECT_CODE
        , A.TRACK
        , A.STRTPT
        , A.ENDPT
        , A.CELL_ID
    ORDER BY
        A.ROUTE_CODE ASC
        , A.DIRECT_CODE DESC
        , A.TRACK ASC
        , A.STRTPT ASC
        , A.ENDPT
;



INSERT INTO TC_CL_CODE(CL_CODE, PRIOR_RANK, CL_CODE_NM, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('MNRD', 1, '관리도로코드', '노선의 구간에 대한 관리도로구분', 'Y', 'N', '3', NOW(), '3', NOW());


INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('MNRD0001', NULL, 'MNRD', 1, '시도', NULL, '시도', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('MNRD0002', NULL, 'MNRD', 2, '국도중용', NULL, '국도중용', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('MNRD0003', NULL, 'MNRD', 3, '국지도중용', NULL, '국지도중용', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('MNRD0004', NULL, 'MNRD', 4, '지방도중용', NULL, '지방도중용', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('MNRD0005', NULL, 'MNRD', 5, '공사', NULL, '공사', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('MNRD0006', NULL, 'MNRD', 6, '미개설', NULL, '미개설', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('MNRD0007', NULL, 'MNRD', 7, '지방도', NULL, '지방도', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('MNRD0008', NULL, 'MNRD', 8, '국지도', NULL, '국지도', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('MNRD0009', NULL, 'MNRD', 9, '민자도로', NULL, '민자도로', 'Y', 'N', '3', NOW(), '3', NOW());



INSERT INTO TC_CL_CODE(CL_CODE, PRIOR_RANK, CL_CODE_NM, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('FLSE', 1, '파일구분코드', '파일종류를 구분', 'Y', 'N', '3', NOW(), '3', NOW());


INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('FLSE0001', NULL, 'FLSE', 1, '기하구조', NULL, '기하구조', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('FLSE0002', NULL, 'FLSE', 2, '도로현황', NULL, '도로현황', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('FLSE0003', NULL, 'FLSE', 3, '분석결과', NULL, '분석결과', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('FLSE0004', NULL, 'FLSE', 4, '소성변형', NULL, '소성변형', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('FLSE0005', NULL, 'FLSE', 5, '종단평탄성', NULL, '종단평탄성', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('FLSE0006', NULL, 'FLSE', 6, '표면결함', NULL, '표면결함', 'Y', 'N', '3', NOW(), '3', NOW());


INSERT INTO TC_CL_CODE(CL_CODE, PRIOR_RANK, CL_CODE_NM, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('ANUN', 1, '분석단위코드', '보수대상 분석단위코드', 'Y', 'N', '3', NOW(), '3', NOW());


INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('ANUN0001', NULL, 'ANUN', 1, '200m', NULL, '200m', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('ANUN0002', NULL, 'ANUN', 2, '1km', NULL, '1km', 'Y', 'N', '3', NOW(), '3', NOW());


-- 보수_대상_선정
ALTER TABLE tn_rpair_trget_slctn ADD anals_unit_code varchar(8);
ALTER TABLE tn_rpair_trget_slctn ADD slctn_year varchar(4);

COMMENT ON COLUMN tn_rpair_trget_slctn.anals_unit_code IS '분석_단위_코드';
COMMENT ON COLUMN tn_rpair_trget_slctn.slctn_year IS '선정_년도';


-- 보수_대상_항목_그룹
ALTER TABLE tn_rpair_trget_group ADD pothole_qy numeric(22);
ALTER TABLE tn_rpair_trget_group ADD priort numeric(5);

COMMENT ON COLUMN tn_rpair_trget_group.pothole_qy IS '포트홀_량';
COMMENT ON COLUMN tn_rpair_trget_group.priort IS '우선순위';


-- 10m 셀
ALTER TABLE cell_10 ADD mng_rd_cd varchar(8);
ALTER TABLE cell_10 ADD sctn_no numeric(22);
ALTER TABLE cell_10 ADD cntrwk_at varchar(1);
ALTER TABLE cell_10 ADD unopn_at varchar(1);

COMMENT ON COLUMN cell_10.mng_rd_cd IS '관리_도로_코드';
COMMENT ON COLUMN cell_10.sctn_no IS '구간_번호';
COMMENT ON COLUMN cell_10.mng_rd_cd IS '공사_여부';
COMMENT ON COLUMN cell_10.sctn_no IS '미개통_여부';


-- SECTION 셀
ALTER TABLE cell_sect ADD mng_rd_cd varchar(8);
ALTER TABLE cell_sect ADD sctn_no numeric(22);
ALTER TABLE cell_sect ADD cntrwk_at varchar(1);
ALTER TABLE cell_sect ADD unopn_at varchar(1);
ALTER TABLE cell_sect ADD spcl_no numeric(22);

COMMENT ON COLUMN cell_sect.mng_rd_cd IS '관리_도로_코드';
COMMENT ON COLUMN cell_sect.sctn_no IS '구간_번호';
COMMENT ON COLUMN cell_sect.cntrwk_at IS '공사_여부';
COMMENT ON COLUMN cell_sect.unopn_at IS '미개통_여부';
COMMENT ON COLUMN cell_sect.sctn_no IS '특별_관리_도로_구간_번호';


-- 노선_구간_정보
CREATE TABLE tn_route_sctn_info (
    sctn_no numeric(22) NOT NULL -- 구간_번호
    , sctn_nm varchar(200) NULL -- 구간_명
  , sctn_cn varchar(254) NULL -- 구간_내용
    , CONSTRAINT pk_tn_route_sctn_info PRIMARY KEY (sctn_no)
);

COMMENT ON COLUMN tn_route_sctn_info.sctn_no IS '구간_번호';
COMMENT ON COLUMN tn_route_sctn_info.sctn_nm IS '구간_명';
COMMENT ON COLUMN tn_route_sctn_info.sctn_cn IS '구간_내용';


-- 공통첨부상세파일
CREATE TABLE tn_attach_detail_file (
    file_no numeric(30) NOT NULL -- 파일_번호
  , file_detail_no numeric(30) NOT NULL -- 파일_상세_번호
  , file_se_code varchar(8) NULL -- 파일구분코드
    , file_nm varchar(500) NULL -- 파일_명
    , orginl_file_nm varchar(500) NULL -- 원본_파일_명
    , file_cours varchar(500) NULL -- 파일_경로
    , file_size numeric(22) NULL -- 파일_크기
    , use_at varchar(1) NULL -- 사용_여부
    , delete_at varchar(1) NULL -- 삭제_여부
    , crtr_no numeric(30) NULL -- 생성자_번호
    , creat_dt date NULL -- 생성_일시
    , updusr_no numeric(30) NULL -- 수정자_번호
    , updt_dt date NULL -- 수정_일시
    , CONSTRAINT pk_tn_attach_detail_file PRIMARY KEY (file_no, file_detail_no)
);

COMMENT ON TABLE tn_attach_detail_file IS '공통첨부상세파일';

COMMENT ON COLUMN tn_attach_detail_file.file_no IS '파일_번호';
COMMENT ON COLUMN tn_attach_detail_file.file_detail_no IS '파일_상세_번호';
COMMENT ON COLUMN tn_attach_detail_file.file_se_code IS '파일구분코드';
COMMENT ON COLUMN tn_attach_detail_file.file_nm IS '파일_명';
COMMENT ON COLUMN tn_attach_detail_file.orginl_file_nm IS '원본_파일_명';
COMMENT ON COLUMN tn_attach_detail_file.file_cours IS '파일_경로';
COMMENT ON COLUMN tn_attach_detail_file.file_size IS '파일_크기';
COMMENT ON COLUMN tn_attach_detail_file.use_at IS '사용_여부';
COMMENT ON COLUMN tn_attach_detail_file.delete_at IS '삭제_여부';
COMMENT ON COLUMN tn_attach_detail_file.crtr_no IS '생성자_번호';
COMMENT ON COLUMN tn_attach_detail_file.creat_dt IS '생성_일시';
COMMENT ON COLUMN tn_attach_detail_file.updusr_no IS '수정자_번호';
COMMENT ON COLUMN tn_attach_detail_file.updt_dt IS '수정_일시';


-- 노선_대장_마스터
ALTER TABLE TN_ROUTE_INFO ADD DO_MANAGE_SCTN_LEN FLOAT4;

COMMENT ON COLUMN TN_ROUTE_INFO.DO_MANAGE_SCTN_LEN IS '도_관리_구간_연장';



-- 조사_요청_구간
CREATE TABLE tn_srvy_requst_sctn (
    srvy_requst_sctn_no   NUMERIC(22)  NOT NULL ,
    srvy_nm               VARCHAR(200)  NULL ,
    srvy_cn               VARCHAR(2000)  NULL ,
    srvy_requst_de        VARCHAR(8)  NULL ,
    route_code            VARCHAR(8)  NULL ,
    direct_code           VARCHAR(2)  NULL ,
    track                 VARCHAR(2)  NULL ,
    strtpt                NUMERIC(22)  NULL ,
    endpt                 NUMERIC(22)  NULL ,
    updusr_no             NUMERIC(30)  NULL ,
    updt_dt               DATE  NULL ,
  CONSTRAINT pk_tn_srvy_requst_sctn PRIMARY KEY (srvy_requst_sctn_no)
);

COMMENT ON TABLE tn_srvy_requst_sctn IS '조사_요청_구간';

COMMENT ON COLUMN tn_srvy_requst_sctn.srvy_requst_sctn_no IS '조사_요청_구간_번호';
COMMENT ON COLUMN tn_srvy_requst_sctn.srvy_nm IS '조사_명';
COMMENT ON COLUMN tn_srvy_requst_sctn.srvy_cn IS '조사_내용';
COMMENT ON COLUMN tn_srvy_requst_sctn.srvy_requst_de IS '조사_요청_일자';
COMMENT ON COLUMN tn_srvy_requst_sctn.route_code IS '노선_코드';
COMMENT ON COLUMN tn_srvy_requst_sctn.direct_code IS '행선_코드';
COMMENT ON COLUMN tn_srvy_requst_sctn.track IS '차로';
COMMENT ON COLUMN tn_srvy_requst_sctn.strtpt IS '시점';
COMMENT ON COLUMN tn_srvy_requst_sctn.endpt IS '종점';
COMMENT ON COLUMN tn_srvy_requst_sctn.updusr_no IS '수정자_번호';
COMMENT ON COLUMN tn_srvy_requst_sctn.updt_dt IS '수정_일시';

-- 조사_요청_구간_셀_정보
CREATE TABLE tn_srvy_requst_sctn_cell_info (
    srvy_requst_sctn_no   NUMERIC(22)  NOT NULL ,
    pav_cell_id           VARCHAR(18)  NOT NULL ,
    crtr_no               NUMERIC(30)  NULL ,
    creat_dt              DATE  NULL ,
  CONSTRAINT pk_tn_srvy_requst_sctn_cell_info PRIMARY KEY (srvy_requst_sctn_no, pav_cell_id)
);

COMMENT ON TABLE tn_srvy_requst_sctn_cell_info IS '조사_요청_구간_셀_정보';

COMMENT ON COLUMN tn_srvy_requst_sctn_cell_info.srvy_requst_sctn_no IS '조사_요청_구간_번호';
COMMENT ON COLUMN tn_srvy_requst_sctn_cell_info.pav_cell_id IS '포장_셀_ID';
COMMENT ON COLUMN tn_srvy_requst_sctn_cell_info.crtr_no IS '생성자_번호';
COMMENT ON COLUMN tn_srvy_requst_sctn_cell_info.creat_dt IS '생성_일시';



CREATE TABLE tn_srvy_dta (
    srvy_no numeric(22) NOT NULL, -- 조사_번호
    file_no numeric(30) NULL, -- 파일_번호
    data_co numeric(22) NULL, -- 데이터_건수
    route_code varchar(8) NULL, -- 노선_코드
    direct_code varchar(2) NULL, -- 행선_코드
    track varchar(2) NULL, -- 차로
    strtpt numeric(22) NULL, -- 시점
    endpt numeric(22) NULL, -- 종점
    srvy_de varchar(8) NULL, -- 조사_일자
    srvy_nm varchar(200) NULL, -- 조사_명
    exmnr_nm varchar(200) NULL, -- 조사자_명
    evl_process_at varchar(1) NULL, -- 평가_처리_여부
    sm_process_at varchar(1) NULL, -- 집계_처리_여부
    val_evl_at varchar(1) NULL, -- 유효성_평가_여부
    delete_at varchar(1) NULL DEFAULT 'n'::character varying, -- 삭제_여부
    use_at varchar(1) NULL DEFAULT 'y'::character varying, -- 사용_여부
    crtr_no numeric(30) NULL, -- 생성자_번호
    creat_dt timestamp NULL DEFAULT to_char(now(), 'yymmdd'::text)::date, -- 생성_일시
    updusr_no numeric(30) NULL, -- 수정자_번호
    updt_dt timestamp NULL, -- 수정_일시
    gps_cortn_process_at varchar(1) NULL, -- gps_보정_처리_여부
    prdtmdl_process_at varchar(1) NULL, -- 예측모델_처리_여부
    CONSTRAINT pk_tn_srvy_dta PRIMARY KEY (srvy_no)
);

COMMENT ON TABLE tn_srvy_dta IS '조사_자료';

COMMENT ON COLUMN tn_srvy_dta.srvy_no IS '조사_번호';
COMMENT ON COLUMN tn_srvy_dta.file_no IS '파일_번호';
COMMENT ON COLUMN tn_srvy_dta.data_co IS '데이터_건수';
COMMENT ON COLUMN tn_srvy_dta.route_code IS '노선_코드';
COMMENT ON COLUMN tn_srvy_dta.direct_code IS '행선_코드';
COMMENT ON COLUMN tn_srvy_dta.track IS '차로';
COMMENT ON COLUMN tn_srvy_dta.strtpt IS '시점';
COMMENT ON COLUMN tn_srvy_dta.endpt IS '종점';
COMMENT ON COLUMN tn_srvy_dta.srvy_de IS '조사_일자';
COMMENT ON COLUMN tn_srvy_dta.srvy_nm IS '조사_명';
COMMENT ON COLUMN tn_srvy_dta.exmnr_nm IS '조사자_명';
COMMENT ON COLUMN tn_srvy_dta.evl_process_at IS '평가_처리_여부';
COMMENT ON COLUMN tn_srvy_dta.sm_process_at IS '집계_처리_여부';
COMMENT ON COLUMN tn_srvy_dta.val_evl_at IS '유효성_평가_여부';
COMMENT ON COLUMN tn_srvy_dta.delete_at IS '삭제_여부';
COMMENT ON COLUMN tn_srvy_dta.use_at IS '사용_여부';
COMMENT ON COLUMN tn_srvy_dta.crtr_no IS '생성자_번호';
COMMENT ON COLUMN tn_srvy_dta.creat_dt IS '생성_일시';
COMMENT ON COLUMN tn_srvy_dta.updusr_no IS '수정자_번호';
COMMENT ON COLUMN tn_srvy_dta.updt_dt IS '수정_일시';
COMMENT ON COLUMN tn_srvy_dta.gps_cortn_process_at IS 'gps_보정_처리_여부';
COMMENT ON COLUMN tn_srvy_dta.prdtmdl_process_at IS '예측모델_처리_여부';


INSERT INTO tn_srvy_dta(srvy_no, file_no, data_co, route_code, direct_code, track, strtpt, endpt, srvy_de, srvy_nm, exmnr_nm, evl_process_at, sm_process_at, val_evl_at, delete_at, use_at, crtr_no, creat_dt, updusr_no, updt_dt, gps_cortn_process_at, prdtmdl_process_at)
(SELECT srvy_no, file_no, data_co, route_code, direct_code, track, strtpt, endpt, srvy_de, srvy_nm, exmnr_nm, evl_process_at, sm_process_at, val_evl_at, delete_at, use_at, crtr_no, creat_dt, updusr_no, updt_dt, gps_cortn_process_at, prdtmdl_process_at
FROM gpms.tn_srvy_dta_excel);

COMMIT;



CREATE SEQUENCE seq_tn_attach_detail_file START 1;


-- 달 차이 계산
-- CREATE OR REPLACE FUNCTION gpms.months_between(year text, month text)
--  RETURNS text
--  LANGUAGE plpgsql
-- AS $function$
-- DECLARE
-- result numeric;
-- begin
--  select COALESCE((to_char(localtimestamp,'YYYY')::int - year::int)*12 + case when to_char(localtimestamp,'DD')::int > 15  then ((to_char(localtimestamp,'MM')::int + 1 )- month::int) else (to_char(localtimestamp,'MM')::int - month::int) end, 0)
--  into result::text ;
-- return result;
-- end; $function$
-- ;




CREATE INDEX TN_MUMM_SCTN_SRVY_DTA_IDX1 ON TN_MUMM_SCTN_SRVY_DTA(ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT);
CREATE INDEX CELL_10_IDX1 ON CELL_10(ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT);
CREATE INDEX CELL_10_IDX2 ON CELL_10(CELL_ID);
CREATE INDEX CELL_SECT_IDX1 ON CELL_SECT(ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT);
CREATE INDEX CELL_SECT_IDX2 ON CELL_SECT(CELL_ID);
CREATE INDEX TN_CNTRWK_CELL_INFO_IDX1 ON TN_CNTRWK_CELL_INFO(PAV_CELL_ID);
CREATE INDEX TN_RPAIR_TRGET_IDX1 ON TN_RPAIR_TRGET(ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT);
CREATE INDEX TN_RPAIR_TRGET_IDX2 ON TN_RPAIR_TRGET(TRGET_SLCTN_NO);
CREATE INDEX TN_RPAIR_TRGET_IDX3 ON TN_RPAIR_TRGET(TRGET_SLCTN_NO, ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT);
CREATE INDEX TN_RPAIR_TRGET_GROUP_IDX1 ON TN_RPAIR_TRGET_GROUP(ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT);
CREATE INDEX TN_RPAIR_TRGET_GROUP_IDX2 ON TN_RPAIR_TRGET_GROUP(TRGET_SLCTN_NO);
CREATE INDEX TN_RPAIR_TRGET_GROUP_IDX3 ON TN_RPAIR_TRGET_GROUP(TRGET_SLCTN_NO, ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT);


-- 보수_대상_항목_그룹
ALTER TABLE TN_RPAIR_TRGET_GROUP ADD MNG_RD_CD VARCHAR(8);

COMMENT ON COLUMN TN_RPAIR_TRGET_GROUP.MNG_RD_CD IS '관리_도로_코드';


-- 공사구분 코드 삭제처리
UPDATE TC_CODE SET
    DELETE_AT = 'Y'
WHERE
    CL_CODE = 'CWSE';


-- 공사구분 코드 추가
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('CWSE0007', NULL, 'CWSE', 1, '신설공사구간', NULL, '신설공사구간', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('CWSE0008', NULL, 'CWSE', 2, '재포장 공사구간', NULL, '재포장 공사구간', 'Y', 'N', '3', NOW(), '3', NOW());
INSERT INTO TC_CODE(CODE_VAL, UPPER_CODE_VAL, CL_CODE, PRIOR_RANK, CODE_NM, ATRB_VAL, CN, USE_AT, DELETE_AT, CRTR_NO, CREAT_DT, UPDUSR_NO, UPDT_DT)
VALUES('CWSE0009', NULL, 'CWSE', 3, '굴착복구 공사구간', NULL, '굴착복구 공사구간', 'Y', 'N', '3', NOW(), '3', NOW());


UPDATE CELL_10 SET
    MNG_RD_CD = CONCAT(CONCAT(SUBSTRING(MNG_RD_CD, 1, 4), '0'), SUBSTRING(MNG_RD_CD, 5, 3))
    , SCTN_CODE = CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(CONCAT(ROUTE_CODE, '_'), DIRECT_CODE), '_'), TRACK), '_'), LPAD(SCTN_CODE, 4, '0'));


-- 200m셀 속성정보 통계
UPDATE CELL_SECT SET
    ROAD_GRAD = (
        SELECT
            A.ROAD_GRAD
        FROM (
            SELECT A.ROAD_GRAD, COUNT(*) CNT
            FROM CELL_10 A
            WHERE
                1 = 1
                AND A.ROUTE_CODE     = CELL_SECT.ROUTE_CODE     /* 노선_코드 */
                AND A.DIRECT_CODE    = CELL_SECT.DIRECT_CODE  /* 행선_코드 */
                AND A.TRACK          = CELL_SECT.TRACK           /* 차로 */
                AND A.STRTPT         BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 시점 */
                AND A.ENDPT          BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 종점 */
            GROUP BY
                A.ROAD_GRAD
        ) A
        ORDER BY CNT DESC
        LIMIT 1
    )
    , ADM_CODE = (
        SELECT
            A.ADM_CODE
        FROM (
            SELECT A.ADM_CODE, COUNT(*) CNT
            FROM CELL_10 A
            WHERE
                1 = 1
                AND A.ROUTE_CODE     = CELL_SECT.ROUTE_CODE     /* 노선_코드 */
                AND A.DIRECT_CODE    = CELL_SECT.DIRECT_CODE  /* 행선_코드 */
                AND A.TRACK          = CELL_SECT.TRACK           /* 차로 */
                AND A.STRTPT         BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 시점 */
                AND A.ENDPT          BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 종점 */
            GROUP BY
                A.ADM_CODE
        ) A
        ORDER BY CNT DESC
        LIMIT 1
    )
    , DEPT_CODE = (
        SELECT
            A.DEPT_CODE
        FROM (
            SELECT A.DEPT_CODE, COUNT(*) CNT
            FROM CELL_10 A
            WHERE
                1 = 1
                AND A.ROUTE_CODE     = CELL_SECT.ROUTE_CODE     /* 노선_코드 */
                AND A.DIRECT_CODE    = CELL_SECT.DIRECT_CODE  /* 행선_코드 */
                AND A.TRACK          = CELL_SECT.TRACK           /* 차로 */
                AND A.STRTPT         BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 시점 */
                AND A.ENDPT          BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 종점 */
            GROUP BY
                A.DEPT_CODE
        ) A
        ORDER BY CNT DESC
        LIMIT 1
    )
    , CELL_TYPE = (
        SELECT
            A.CELL_TYPE
        FROM (
            SELECT A.CELL_TYPE, COUNT(*) CNT
            FROM CELL_10 A
            WHERE
                1 = 1
                AND A.ROUTE_CODE     = CELL_SECT.ROUTE_CODE     /* 노선_코드 */
                AND A.DIRECT_CODE    = CELL_SECT.DIRECT_CODE  /* 행선_코드 */
                AND A.TRACK          = CELL_SECT.TRACK           /* 차로 */
                AND A.STRTPT         BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 시점 */
                AND A.ENDPT          BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 종점 */
            GROUP BY
                A.CELL_TYPE
        ) A
        ORDER BY CNT DESC
        LIMIT 1
    )
    , VMTC_GRAD = (
        SELECT
            A.VMTC_GRAD
        FROM (
            SELECT A.VMTC_GRAD, COUNT(*) CNT
            FROM CELL_10 A
            WHERE
                1 = 1
                AND A.ROUTE_CODE     = CELL_SECT.ROUTE_CODE     /* 노선_코드 */
                AND A.DIRECT_CODE    = CELL_SECT.DIRECT_CODE  /* 행선_코드 */
                AND A.TRACK          = CELL_SECT.TRACK           /* 차로 */
                AND A.STRTPT         BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 시점 */
                AND A.ENDPT          BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 종점 */
            GROUP BY
                A.VMTC_GRAD
        ) A
        ORDER BY CNT DESC
        LIMIT 1
    )
    , MNG_RD_CD = (
        SELECT
            A.MNG_RD_CD
        FROM (
            SELECT A.MNG_RD_CD, COUNT(*) CNT
            FROM CELL_10 A
            WHERE
                1 = 1
                AND A.ROUTE_CODE     = CELL_SECT.ROUTE_CODE     /* 노선_코드 */
                AND A.DIRECT_CODE    = CELL_SECT.DIRECT_CODE  /* 행선_코드 */
                AND A.TRACK          = CELL_SECT.TRACK           /* 차로 */
                AND A.STRTPT         BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 시점 */
                AND A.ENDPT          BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 종점 */
            GROUP BY
                A.MNG_RD_CD
        ) A
        ORDER BY CNT DESC
        LIMIT 1
    )
    , SCTN_CODE = (
        SELECT
            A.SCTN_CODE
        FROM (
            SELECT A.SCTN_CODE, COUNT(*) CNT
            FROM CELL_10 A
            WHERE
                1 = 1
                AND A.ROUTE_CODE     = CELL_SECT.ROUTE_CODE     /* 노선_코드 */
                AND A.DIRECT_CODE    = CELL_SECT.DIRECT_CODE  /* 행선_코드 */
                AND A.TRACK          = CELL_SECT.TRACK           /* 차로 */
                AND A.STRTPT         BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 시점 */
                AND A.ENDPT          BETWEEN CELL_SECT.STRTPT AND CELL_SECT.ENDPT  /* 종점 */
            GROUP BY
                A.SCTN_CODE
        ) A
        ORDER BY CNT DESC
        LIMIT 1
    )
;





COMMENT ON COLUMN DWG_INFO.SECT IS '구간_코드';
COMMENT ON COLUMN DWG_INFO.REM IS '비고_내용';
COMMENT ON COLUMN DWG_INFO.DWG_NAME IS '도면_명칭';
COMMENT ON COLUMN STR_DWG.SECT IS '구간_코드';
COMMENT ON COLUMN STR_DWG.REM IS '비고_내용';
COMMENT ON COLUMN STR_DWG.DWG_FILE IS '파일_명칭';
COMMENT ON COLUMN TN_ROUTE_INFO.ROAD_NAME IS '도로_명칭';
COMMENT ON COLUMN TN_ROUTE_INFO.MCO IS '관리기관';
COMMENT ON COLUMN TN_PREDCT_FRMULA_IDX.CELL_TYPE IS '셀_타입';
COMMENT ON COLUMN TN_CNTRWK.CNTRWK_YEAR IS '공사_년도';
COMMENT ON COLUMN TN_RPAIR_TRGET_GROUP.DEPT_CODE IS '부서_코드';
COMMENT ON COLUMN TN_RPAIR_TRGET.DEPT_CODE IS '부서_코드';
COMMENT ON COLUMN TN_ROUTE_INFO.REM IS '비고_내용';




ALTER TABLE STR_DWG ALTER COLUMN REM TYPE VARCHAR(50);
ALTER TABLE TN_SYS_USER ALTER COLUMN RM TYPE VARCHAR(2000);
ALTER TABLE STR_DWG ALTER COLUMN DWG_FILE TYPE VARCHAR(500);
ALTER TABLE TN_NOTICE ALTER COLUMN FILE_NM TYPE VARCHAR(500);
ALTER TABLE DWG_INFO ALTER COLUMN DWG_NAME TYPE VARCHAR(200);
ALTER TABLE TN_RPAIR_TRGET_GROUP ALTER COLUMN CALC_YEAR TYPE VARCHAR(8);
ALTER TABLE TN_RPAIR_TRGET ALTER COLUMN CALC_YEAR TYPE VARCHAR(8);
ALTER TABLE TC_CODE ALTER COLUMN CN TYPE VARCHAR(4000);
ALTER TABLE TC_CL_CODE ALTER COLUMN CN TYPE VARCHAR(4000);
ALTER TABLE TN_CNTRWK_DTL ALTER COLUMN CNTRWK_AMOUNT TYPE NUMERIC(22);
ALTER TABLE TN_CNTRWK ALTER COLUMN CNTRWK_YEAR TYPE VARCHAR(1000);
ALTER TABLE TL_USER_CONNECT ALTER COLUMN CONECT_LOG_NO TYPE NUMERIC(30);
ALTER TABLE TN_AUTHORITY ALTER COLUMN DC TYPE VARCHAR(4000);
ALTER TABLE TN_PAV_FRMULA ALTER COLUMN DC TYPE VARCHAR(4000);
ALTER TABLE TN_NOTICE ALTER COLUMN ORGINL_FILE_NM TYPE VARCHAR(500);
ALTER TABLE TN_ROUTE_INFO ALTER COLUMN REM TYPE VARCHAR(50);
ALTER TABLE TN_RPAIR_TRGET ALTER COLUMN ROAD_GRAD TYPE VARCHAR(12);
ALTER TABLE TN_RPAIR_TRGET_GROUP ALTER COLUMN ROAD_GRAD TYPE VARCHAR(12);
ALTER TABLE STR_DWG ALTER COLUMN SEQID TYPE VARCHAR(8);
ALTER TABLE TN_CNTRWK ALTER COLUMN TOT_AMOUNT TYPE NUMERIC(22);

ALTER TABLE CELL_SECT RENAME COLUMN DIRECT_COD TO DIRECT_CODE;
ALTER TABLE CELL_10 RENAME COLUMN DIRECT_COD TO DIRECT_CODE;

ALTER TABLE TN_ROUTE_SCTN_INFO RENAME COLUMN SCTN_NO TO SCTN_CODE;
ALTER TABLE TN_ROUTE_SCTN_INFO ALTER COLUMN SCTN_CODE TYPE VARCHAR(13);


ALTER TABLE M_CALS_T RENAME COLUMN BRDG_TPNM TO BRDG_TYPENM;
ALTER TABLE M_CALS_T RENAME COLUMN FACIL_CL TO FACIL_CLASS;
ALTER TABLE M_CALS_T RENAME COLUMN MANAGE_NM TO MANAGE_NAME;




CREATE TABLE TN_SRVY_LC_DALY_VMTC
(
    VMTC_SN               NUMERIC(22)  NOT NULL ,
    SPOT                  VARCHAR(20)  NULL ,
    DIRECT                VARCHAR(2)  NULL ,
    C1                    NUMERIC(12)  NULL ,
    C2                    NUMERIC(12)  NULL ,
    C3                    NUMERIC(12)  NULL ,
    C4                    NUMERIC(12)  NULL ,
    C5                    NUMERIC(12)  NULL ,
    C6                    NUMERIC(12)  NULL ,
    C7                    NUMERIC(12)  NULL ,
    C8                    NUMERIC(12)  NULL ,
    C9                    NUMERIC(12)  NULL ,
    C10                   NUMERIC(12)  NULL ,
    C11                   NUMERIC(12)  NULL ,
    C12                   NUMERIC(12)  NULL ,
    TOTAL                 NUMERIC(12)  NULL ,
    TIME                  VARCHAR(2)  NULL ,
    YEAR                  VARCHAR(4)  NULL,
    ROADKND               VARCHAR(200)  NULL,
    PRIMARY KEY (VMTC_SN)
);



CREATE TABLE TN_VMTC_SRVY_LC
(
    YEAR                  VARCHAR(4)  NULL ,
    SPOT                  VARCHAR(20)  NULL ,
    ROADKND               VARCHAR(200)  NULL ,
    MLLC                  VARCHAR(5)  NULL ,
    TRACK                 VARCHAR(2)  NULL ,
    "DO"                  VARCHAR(20)  NULL ,
    SIGUN                 VARCHAR(20)  NULL ,
    EUPMYEON              VARCHAR(20)  NULL ,
    DONGLI                VARCHAR(20)  NULL ,
    XCODE                 VARCHAR(10)  NULL ,
    YCODE                 VARCHAR(10)  NULL,
    PRIMARY KEY (YEAR, SPOT)
);


COMMENT ON TABLE TN_SRVY_LC_DALY_VMTC IS '조사_위치_일별_교통량';
        COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.VMTC_SN IS '교통량_일련번호';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.SPOT IS '지점';
        COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.ROADKND IS '도로종류';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.DIRECT IS '행선';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.YEAR IS '년도';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.TIME IS '시간';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C1 IS '차종1';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C2 IS '차종2';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C3 IS '차종3';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C4 IS '차종4';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C5 IS '차종5';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C6 IS '차종6';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C7 IS '차종7';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C8 IS '차종8';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C9 IS '차종9';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C10 IS '차종10';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C11 IS '차종11';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.C12 IS '차종12';
         COMMENT ON COLUMN TN_SRVY_LC_DALY_VMTC.TOTAL IS '총합';

COMMENT ON TABLE TN_VMTC_SRVY_LC IS '교통량_조사_위치';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC.YEAR IS '년도';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC.ROADKND IS '도로종류';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC.SPOT IS '지점';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC.MLLC IS '호선';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC.TRACK IS '차로';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC."DO" IS '도';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC.SIGUN IS '시군';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC.EUPMYEON IS '읍면';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC.DONGLI IS '동리';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC.XCODE IS 'XCODE';
         COMMENT ON COLUMN TN_VMTC_SRVY_LC.YCODE IS 'YCODE';




