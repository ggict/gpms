SELECT sm_no, FN_GET_RPAIR_MTHD(sect.cell_type, sect.vmtc_grad, tb.GPCI
        ,decode(((100-tb.AC_IDX) + (100-tb.bC_IDX) + (100-tb.lC_IDX) + (100-tb.tC_IDX) + (100-tb.PTCHG_IDX) + (100-tb.POTHOLE_IDX) + (100-tb.rd_IDX) + (100-tb.rci)), 0, 0, (((100-tb.lC_IDX) + (100-tb.tC_IDX))/((100-tb.AC_IDX) + (100-tb.bC_IDX) + (100-tb.lC_IDX) + (100-tb.tC_IDX) + (100-tb.PTCHG_IDX) + (100-tb.POTHOLE_IDX) + (100-tb.rd_IDX) + (100-tb.rci)))*100)
        ,decode(((100-tb.AC_IDX) + (100-tb.bC_IDX) + (100-tb.lC_IDX) + (100-tb.tC_IDX) + (100-tb.PTCHG_IDX) + (100-tb.POTHOLE_IDX) + (100-tb.rd_IDX) + (100-tb.rci)), 0, 0, (((100-tb.rd_IDX))/((100-tb.AC_IDX) + (100-tb.bC_IDX) + (100-tb.lC_IDX) + (100-tb.tC_IDX) + (100-tb.PTCHG_IDX) + (100-tb.POTHOLE_IDX) + (100-tb.rd_IDX) + (100-tb.rci)))*100)) rpair_mthd_code
        ,sect.cell_type
        , sect.vmtc_grad
        , tb.GPCI
        ,decode(((100-tb.AC_IDX) + (100-tb.bC_IDX) + (100-tb.lC_IDX) + (100-tb.tC_IDX) + (100-tb.PTCHG_IDX) + (100-tb.POTHOLE_IDX) + (100-tb.rd_IDX) + (100-tb.rci)), 0, 0, (((100-tb.lC_IDX) + (100-tb.tC_IDX))/((100-tb.AC_IDX) + (100-tb.bC_IDX) + (100-tb.lC_IDX) + (100-tb.tC_IDX) + (100-tb.PTCHG_IDX) + (100-tb.POTHOLE_IDX) + (100-tb.rd_IDX) + (100-tb.rci)))*100) lctc
        ,decode(((100-tb.AC_IDX) + (100-tb.bC_IDX) + (100-tb.lC_IDX) + (100-tb.tC_IDX) + (100-tb.PTCHG_IDX) + (100-tb.POTHOLE_IDX) + (100-tb.rd_IDX) + (100-tb.rci)), 0, 0, (((100-tb.rd_IDX))/((100-tb.AC_IDX) + (100-tb.bC_IDX) + (100-tb.lC_IDX) + (100-tb.tC_IDX) + (100-tb.PTCHG_IDX) + (100-tb.POTHOLE_IDX) + (100-tb.rd_IDX) + (100-tb.rci)))*100) rd
        from TN_SM_DTA_LAST_STTUS tb
        inner join cell_sect sect
        on sect.route_code = tb.route_code
        and sect.direct_code = tb.direct_code
        and sect.track = tb.track
        and sect.strtpt = tb.strtpt
        and sect.endpt = tb.endpt
        where 1=1
        and tb.use_at = 'Y'
        and tb.delete_at = 'N'
        order by sm_no;