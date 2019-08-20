/**
 * CELL_10 공간레이어 재구축 시 실행 내용
 * skc@muhanit.kr
 * 
 */

alter table cell_10 add (G2_CENTROID sdo_geometry);
alter table cell_10 add (G2_EXTENT sdo_geometry);

update cell_10
 set g2_centroid = SDO_GEOM.SDO_CENTROID (G2_SPATIAL ,1)
 where g2_centroid is null;
 
update cell_10
 set G2_EXTENT = MDSYS.SDO_GEOMETRY(2003,5181,NULL,MDSYS.SDO_ELEM_INFO_ARRAY(1,1003,3),MDSYS.SDO_ORDINATE_ARRAY(g2_xmin/1000,g2_ymin/1000, g2_xmax/1000, g2_ymax/1000))
 where G2_EXTENT is null;
 
insert into user_sdo_geom_metadata (table_name,column_name,diminfo,srid) 
select table_name, 'G2_CENTROID', diminfo, srid from user_sdo_geom_metadata
where table_name = 'CELL_10'
and rownum = 1;

insert into user_sdo_geom_metadata (table_name,column_name,diminfo,srid) 
select table_name, 'G2_EXTENT', diminfo, srid from user_sdo_geom_metadata
where table_name = 'CELL_10'
and rownum = 1;

CREATE INDEX CELL_10_CENTROID_IDX ON CELL_10(G2_CENTROID)
    INDEXTYPE IS MDSYS.SPATIAL_INDEX;
    
    
CREATE INDEX CELL_10_EXTENT_IDX ON CELL_10(G2_EXTENT)
    INDEXTYPE IS MDSYS.SPATIAL_INDEX;
    
CREATE INDEX CELL_10_ID_IDX ON CELL_10(CELL_ID);
    
CREATE INDEX CELL_10_ROUTE_IDX ON CELL_10(ROUTE_CODE, DIRECT_CODE, TRACK, STRTPT, ENDPT);
    
alter table tn_mumm_sctn_srvy_dta add (G2_LONLAT sdo_geometry);

alter table tn_mumm_sctn_srvy_dta add (G2_LONLAT_BUFFER sdo_geometry);


insert into user_sdo_geom_metadata (table_name,column_name,diminfo,srid) 
select 'TN_MUMM_SCTN_SRVY_DTA', 'G2_LONLAT', diminfo, srid from user_sdo_geom_metadata
where table_name = 'CELL_10'
and rownum = 1;

insert into user_sdo_geom_metadata (table_name,column_name,diminfo,srid) 
select 'TN_MUMM_SCTN_SRVY_DTA', 'G2_LONLAT_BUFFER', diminfo, srid from user_sdo_geom_metadata
where table_name = 'CELL_10'
and rownum = 1;


CREATE INDEX TN_MUMM_LONLAT_IDX ON TN_MUMM_SCTN_SRVY_DTA(G2_LONLAT)
    INDEXTYPE IS MDSYS.SPATIAL_INDEX;
    
    
CREATE INDEX TN_MUMM_LONLAT_BUFFER_IDX ON TN_MUMM_SCTN_SRVY_DTA(G2_LONLAT_BUFFER)
    INDEXTYPE IS MDSYS.SPATIAL_INDEX;