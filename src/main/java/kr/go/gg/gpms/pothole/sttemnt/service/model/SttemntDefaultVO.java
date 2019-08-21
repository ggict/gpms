package kr.go.gg.gpms.pothole.sttemnt.service.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 포트홀 신고관리 Default VO
 * @Classname : SttemntDefaultVO.java
 *
 * @author    : JOY
 * @company   : MUHAN IT
 * @date      : 2018. 1. 4.
 */
@SuppressWarnings("serial")
public class SttemntDefaultVO implements Serializable {

	/** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 검색String */
    private String searchString = "";

    /** 검색Field */
    private String searchField = "";

    /** 검색Operate */
    private String searchOperate = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** 페이지갯수 */
    private int rows = 10;

    /** 로우 넘버 */
    private int rownum = 0;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;


	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchOperate() {
        return searchOperate;
    }

    public void setSearchOperate(String searchOperate) {
        this.searchOperate = searchOperate;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

 	public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }



 	public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }



    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
