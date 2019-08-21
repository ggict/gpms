package kr.go.gg.gpms.base.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
public class BaseVO implements Serializable {
		
	private String OWNER = "";
	
	/** 검색형태 */
    private String searchType = "";
    
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
    
    /** 수정자 id */
    private String UPDUSR_ID ="";
    
    /** 등록자 id */
    private String CRTR_ID ="";
    
    /** 수정자 명 */
    private String USER_NM ="";
    
    /** 행선_명 */
	@XmlElement
	private String DIRECT_NM = "";
    
    /**
     * 결과처리 성공여부
     */
    private String resultSuccess = "";
    /**
     * 결과메시지
     */
    private String resultMSG = "";
    /**
     * 오류메시지
     */
    private String errorMSg = "";
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
    
    /** 현재페이지 */
    private int page = 1;
    
    /** 페이지갯수 */
    private int rows = 10;
    
    /** 로우 넘버 */
    private int rownum = 0;
    
    /** jquery sort index	*/
    private String sidx="";
    /** jquery sort column	*/
    private String sord="";
    /** order by */
    private String orderByColumn = "";
    private String orderByType = "";
    /**
     * 랭크 번호
     */
    private String RNK_NO ="";
    /**
     * 레벨번호
     */
    private String LVL_NO = "";
    
    /**
     * 페이지사용여부
     */
    private boolean usePage = true;
    
    private String USER_NO = "";

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

    /**
     * callback 함수 명
     */
    private String callBackFunction = "";
    
    private String prc_mode = "";
	 
    
        
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
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        this.pageIndex = page;
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
        this.rows = pageSize;
    }

 	public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
        this.pageSize=rows;
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

	public boolean isUsePage() {
		return usePage;
	}

	public void setUsePage(boolean usePage) {
		this.usePage = usePage;
	}
	@JsonProperty(value="resultSuccess") 
	public String getResultSuccess() {
		return resultSuccess;
	}

	public void setResultSuccess(String resultSuccess) {
		this.resultSuccess = resultSuccess;
	}

	public String getResultMSG() {
		return resultMSG;
	}
	@JsonProperty(value="resultMSG") 
	public void setResultMSG(String resultMSg) {
		this.resultMSG = resultMSg;
	}

	public String getErrorMSg() {
		return errorMSg;
	}

	public void setErrorMSg(String errorMSg) {
		this.errorMSg = errorMSg;
	}
	/**
	 * TN_RN_WORK_STEP.REG_USER, 
	 * 갱신단위작업절차.등록자번호 값읽기
	 * @return
	 */
	@JsonProperty(value="RNK_NO") 
	public String getRNK_NO() {
		return RNK_NO;
	}

	public void setRNK_NO(String rNK_NO) {
		RNK_NO = rNK_NO;
	}
	
	@JsonProperty(value="LVL_NO") 
	public String getLVL_NO() {
		return LVL_NO;
	}

	public void setLVL_NO(String lVL_NO) {
		LVL_NO = lVL_NO;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
		this.orderByColumn = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
		this.orderByType = sord;
	}

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.sidx = orderByColumn;
		this.orderByColumn = orderByColumn;
	}

	public String getOrderByType() {
		return orderByType;
	}

	public void setOrderByType(String orderByType) {
		this.orderByType = orderByType;
		this.sord = orderByType;
	}
	

	@JsonProperty(value="USER_NO") 
	public String getUSER_NO() {
		return USER_NO;
	}

	public void setUSER_NO(String uSER_NO) {
		USER_NO = uSER_NO;
	}
	@JsonProperty(value="UPDUSR_ID") 
	public String getUPDUSR_ID() {
		return UPDUSR_ID;
	}

	public void setUPDUSR_ID(String uPDUSR_ID) {
		UPDUSR_ID = uPDUSR_ID;
	}
	@JsonProperty(value="CRTR_ID") 
	public String getCRTR_ID() {
		return CRTR_ID;
	}

	public void setCRTR_ID(String cRTR_ID) {
		CRTR_ID = cRTR_ID;
	}

	public String getOWNER() {
		return OWNER;
	}

	public void setOWNER(String oWNER) {
		OWNER = oWNER;
	}

	/**
	 * @return the searchType
	 */
	@JsonProperty(value="searchType") 
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	@JsonProperty(value="DIRECT_NM")
	public java.lang.String getDIRECT_NM() {
		
		return this.DIRECT_NM;
	}

	public void setDIRECT_NM(java.lang.String DIRECT_CODE) {
		if("S".equals(DIRECT_CODE)){
			DIRECT_NM = "상행";
		}
		else if("E".equals(DIRECT_CODE)){
			DIRECT_NM =  "하행";
		}
		else if("SE".equals(DIRECT_CODE)){
			DIRECT_NM =  "양방향";
		}
	}

	@JsonProperty(value="USER_NM")
	public String getUSER_NM() {
		return this.USER_NM;
	}

	public void setUSER_NM(String uSER_NM) {
		this.USER_NM = uSER_NM;
	}

	@JsonProperty(value="callBackFunction")
	public String getCallBackFunction() {
		return callBackFunction;
	}

	public void setCallBackFunction(String callBackFunction) {
		this.callBackFunction = callBackFunction;
	}

	@JsonProperty(value="prc_mode")
	public String getPrc_mode() {
		return prc_mode;
	}

	public void setPrc_mode(String prc_mode) {
		this.prc_mode = prc_mode;
	}
	
	

}
