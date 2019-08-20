package kr.go.gg.gpms.base.web;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.go.gg.gpms.attachfile.service.AttachFileService;
import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.company.service.CompanyService;
import kr.go.gg.gpms.company.service.model.CompanyVO;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import egovframework.cmmn.util.FileUploadUtils;
import egovframework.cmmn.web.SessionManager;
import egovframework.security.service.impl.ReloadableFilterInvocationSecurityMetadataSource;

public class BaseController {

	@Resource(name = "codeService")
	private CodeService codeService;

	@Resource(name = "attachFileService")
	private AttachFileService attachFileService;

	@Resource(name = "pathInfoProperties")
	protected Properties pathInfoProperties;

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Autowired
	ServletContext context;
	@Autowired
	SessionManager sessionManager;
	@Autowired
	ReloadableFilterInvocationSecurityMetadataSource reloadFilter;

	/**
	 * 시작년월부터 올해까지 년도 목록을 가져온다.
	 *
	 * @param startYear
	 * @return
	 */
	public List<String> getYears(int startYear) {
		List<String> yearList = new ArrayList<String>();

		Calendar now = GregorianCalendar.getInstance();
		for (int i = startYear; i <= now.get(Calendar.YEAR); i++) {
			yearList.add("" + i);
		}
		return yearList;
	}

	/**
	 * 01~12월 목록을 가져온다.
	 *
	 * @return
	 */
	public List<String> getMonths() {
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			list.add(String.format("%02d", i));
		}
		return list;
	}

	/**
	 * @Method Name : getHourList
	 *
	 * @작성자 : Administrator
	 * @변경이력 :
	 * @Method 설명 :시간 목록 조회
	 * @return
	 */
	protected List<String> getHourList() {
		List<String> hours = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			String hour = String.format("%d", i);
			if (hour.length() == 1) {
				hour = "0" + hour;
			}
			hours.add(hour);
		}
		return hours;
	}

	/**
	 * @Method Name : getYNList
	 *
	 * @작성자 : Administrator
	 * @변경이력 :
	 * @Method 설명 :여부 목록을 조회한다.
	 * @return
	 */
	protected List<String> getYNList() {
		List<String> yesnos = new ArrayList<String>();
		yesnos.add("Y");
		yesnos.add("N");
		return yesnos;
	}
	/**
	 * @Method Name : BindBeansToActiveUser
	 *
	 * @작성자 : Administrator
	 * @변경이력 :
	 * @Method 설명 : 등록자, 수정자 정보를 현재 로그인한 사용자로 값을 적용한다.
	 * @param obj
	 * @throws Exception
	 */
	protected void BindBeansToActiveUser(Object obj) throws Exception {
		MemberInfo memberInfo = sessionManager.getUser();

		String CRTR_NO = ""; /* 등록자번호 */
		String UPDUSR_NO = ""; /* 수정자번호 */
		Map<String, Object>  properties = PropertyUtils.describe(obj);
		if (memberInfo != null) {
			if(properties.containsKey("CRTR_NO")){
				CRTR_NO = BeanUtils.getProperty(obj, "CRTR_NO");
				if (StringUtils.isNotEmpty(CRTR_NO) == false) {
					BeanUtils.setProperty(obj, "CRTR_NO", "" + memberInfo.getUSER_NO());
				}
			}
			if(properties.containsKey("UPDUSR_NO")){
				UPDUSR_NO = BeanUtils.getProperty(obj, "UPDUSR_NO");
				BeanUtils.setProperty(obj, "UPDUSR_NO", "" + memberInfo.getUSER_NO());
			}
		}
	}


	protected void BindBeansToActiveUser(Object obj, String[] columns) throws Exception {
		MemberInfo memberInfo = sessionManager.getUser();


		String CRTR_NO = ""; /* 등록자번호 */
		String UPDUSR_NO = ""; /* 수정자번호 */
		Map<String, Object>  properties = PropertyUtils.describe(obj);

		if (memberInfo != null) {
			if(properties.containsKey("CRTR_NO")){
				CRTR_NO = BeanUtils.getProperty(obj, "CRTR_NO");
				if (StringUtils.isNotEmpty(CRTR_NO) == false) {
					BeanUtils.setProperty(obj, "CRTR_NO", "" + memberInfo.getUSER_NO());
				}
			}
			if(properties.containsKey("UPDUSR_NO")){
				UPDUSR_NO = BeanUtils.getProperty(obj, "UPDUSR_NO");
				BeanUtils.setProperty(obj, "UPDUSR_NO", "" + memberInfo.getUSER_NO());
			}
		}
		for(String column : columns){
			try{
				if(properties.containsKey(column)){
					String columnValue = 	BeanUtils.getProperty(obj, column);
					if (StringUtils.isNotEmpty(columnValue) == false) {
						BeanUtils.setProperty(obj, column, "" + memberInfo.getUSER_NO());
					}
				}

			}catch(Exception e){}
		}
	}

	public static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}

	public List<CodeVO> getCodeList(String clCode) throws Exception{

		CodeVO codeParam = new CodeVO();
		codeParam.setCL_CODE(clCode);
		codeParam.setUsePage(false);
		codeParam.setUSE_AT("Y");
		codeParam.setDELETE_AT("N");

		List<CodeVO> codes = codeService.selectCodeList(codeParam);
		return codes;
	}

	public List<CompanyVO> getCompanyList() throws Exception{

		//공사 업체
		CompanyVO companyVO = new CompanyVO();
		companyVO.setUsePage(false);
		companyVO.setDELETE_AT("N");
		companyVO.setUSE_AT("Y");
		companyVO.setSidx("CO_NM");
		List<CompanyVO> company = companyService.selectCompanyList(companyVO);
		return company;
	}

	public void addCodeToModel(String clCode, ModelMap model) throws Exception{
		List<CodeVO> codes = getCodeList(clCode);
		model.addAttribute("codes"+clCode, codes);
	}

	public void addCodeToModel(String clCode, String codeNames, ModelMap model) throws Exception{
		List<CodeVO> codes = getCodeList(clCode);
		model.addAttribute(codeNames, codes);
	}

	public String nvl(Object str) {
		if( str==null )
			return "";
		else
			return (str.toString()).trim();
	}

	/**
     * value값을 초기화 한다.
     * @author    : lsk
     * @date      : 2019. 6. 20.
     */
    public String isEmptyValue(String chkValue, String value){

        //빈값이거나 null일 경우, 설정값을 넘긴다.
    	if("".equals(chkValue) || chkValue == null ){

        	return value;

        //값이 있으면 현재값을 그대로 넘긴다.
        }else{

        	return chkValue;
        }
    }

	public String getFileNo(HttpServletRequest request, String inputFileId, String fileCl) throws Exception {
		String fileNo = null;
		/** validate request type */
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		String filePath = pathInfoProperties.getProperty("file.upload.path");

		/** extract files */
		final MultipartFile requestFile = multiRequest.getFile(inputFileId);

		if(requestFile != null && !requestFile.isEmpty()){
			AttachFileVO file = FileUploadUtils.saveFile(filePath, fileCl, requestFile);

			file.setUSE_AT("Y");
			file.setDELETE_AT("N");
			BindBeansToActiveUser(file);
			fileNo = attachFileService.insertAttachFile(file);

		}

		return fileNo;
	}


	public String getFileNoArr(HttpServletRequest request, String inputFileId, String fileCl) throws Exception {
		String fileNo = null;

		/** validate request type */
		Assert.state(request instanceof MultipartHttpServletRequest,
				"request !instanceof MultipartHttpServletRequest");
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		String filePath = pathInfoProperties.getProperty("file.upload.path");

		/** extract files */
		final MultipartFile requestFile = multiRequest.getFile(inputFileId);

		if(requestFile != null && !requestFile.isEmpty()){
			AttachFileVO file = FileUploadUtils.saveFile(filePath, fileCl, requestFile);

			file.setUSE_AT("Y");
			file.setDELETE_AT("N");
			BindBeansToActiveUser(file);
			fileNo = attachFileService.insertAttachFile(file);
		}

		if (fileNo == null){
			return "";
		}
		return fileNo;

	}


	public void setSystem(String sysNm) throws Exception {
		sessionManager.setSystem(sysNm);
	}

	public void reloadFilter() throws Exception {
		reloadFilter.reload();
	}

	public boolean checkRole(String url) throws Exception {
		return reloadFilter.checkRole(url);
	}

	/**
	 * request to Map (with validation)
	 * author : skc@muhanit.kr
	 * @param request
	 * @return
	 */
	public Map requestToHashMap(HttpServletRequest request) throws Exception {
		Map<String, String> result = new HashMap<String, String>();

		Map<String, String[]> requestParameterMap = request.getParameterMap();

		for(String key : requestParameterMap.keySet()){
			String value = requestParameterMap.get(key)[0].replaceAll("<", "&lt").replaceAll(">", "&gt");
			value = value.replaceAll("\n", "").replaceAll("\r", "");

			result.put(key, value);
		}

		return result;
	}

	/**
	 * 경로순회(directory traversal) 문자열을 제거
	 * author : skc@muhanit.kr
	 * @param path
	 * @return
	 */
	public String checkFilePath(String filePath, String type) throws Exception {

		return FileUploadUtils.checkFilePath(filePath, type);
	}


}
