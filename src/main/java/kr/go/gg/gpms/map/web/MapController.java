package kr.go.gg.gpms.map.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.cmmn.util.SaveMapUtils;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.security.service.impl.CustomAuthenticationProvider;
import kr.go.gg.gpms.base.web.BaseController;
import kr.go.gg.gpms.cell10.service.Cell10Service;
import kr.go.gg.gpms.cell10.service.model.Cell10VO;
import kr.go.gg.gpms.cmmn.service.CmmnService;
import kr.go.gg.gpms.code.service.CodeService;
import kr.go.gg.gpms.code.service.model.CodeVO;
import kr.go.gg.gpms.dept.service.DeptService;
import kr.go.gg.gpms.dept.service.model.DeptVO;
import kr.go.gg.gpms.map.service.MapService;
import kr.go.gg.gpms.map.service.model.PolygonSymbolVO;
import kr.go.gg.gpms.routeinfo.service.RouteInfoService;
import kr.go.gg.gpms.routeinfo.service.model.RouteInfoVO;
import kr.go.gg.gpms.rpairmthd.service.RpairMthdService;
import kr.go.gg.gpms.rpairmthd.service.model.RpairMthdVO;
import kr.go.gg.gpms.sysuser.service.SysUserService;
import kr.go.gg.gpms.sysuser.service.model.MemberInfo;
//import org.springframework.security.core.context.SecurityContextHolder;

@Controller("mapController")
public class MapController extends BaseController {

	@Resource(name = "sysUserService")
	private SysUserService sysUserService;

	@Resource(name = "cmmnService")
	private CmmnService cmmnService;

	@Resource(name = "codeService")
	private CodeService codeService;

	@Resource(name = "deptService")
	protected DeptService deptService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	@Resource(name = "customAuthenticationProvider")
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Resource(name = "mapService")
	private MapService mapService;

	@Resource(name = "routeInfoService")
	private RouteInfoService routeInfoService;

	@Resource(name = "rpairMthdService")
	private RpairMthdService rpairMthdService;

	@Resource(name = "cell10Service")
	private Cell10Service cell10Service;

	private static final Logger LOGGER = LoggerFactory.getLogger(MapController.class);

	@RequestMapping(value = "/map.do")
	public String map(@ModelAttribute MemberInfo memberInfoVO, DeptVO deptVO, ModelMap model,
			HttpServletRequest request, HttpSession session) throws Exception {

		// 레이어 정보 조회

		/*
		 * 임시주석_sdh List<EgovMap> layerInfoList = cmmnService.selectLaygerList(null);
		 * List<EgovMap> layerInfoListTheme = cmmnService.selectLayerListTheme(null);
		 * List<EgovMap> layerGroupInfoList = cmmnService.selectLyrGroupInfo(null);
		 */
		// 노선 번호
		RouteInfoVO routeInfoVO = new RouteInfoVO();
		routeInfoVO.setUsePage(false);
		routeInfoVO.setSidx("ROAD_NO");
		List<RouteInfoVO> roadNoList = routeInfoService.selectRouteInfoList(routeInfoVO);

		// 도로 등급
		List<CodeVO> roadGradList = getCodeList("RDGD");

		// 포장공법별
		RpairMthdVO rpairMthdVO = new RpairMthdVO();
		rpairMthdVO.setUsePage(false);
		List<RpairMthdVO> rpairMthdList = rpairMthdService.selectRpairMthdList(rpairMthdVO);

		// 통계연도
		Cell10VO cell10vo = new Cell10VO();
		List<Cell10VO> statsYear = cell10Service.selectStatsYearList(cell10vo);

		/*
		 * 임시주석_sdh model.addAttribute("layerInfoList", layerInfoList);
		 * model.addAttribute("layerInfoListTheme", layerInfoListTheme);
		 * model.addAttribute("layerGroupInfoList", layerGroupInfoList);
		 */

		// stats div(통계)에서 사용할 부서정보/노선코드/ 도로 등급조회
		model.addAttribute("deptCdList", deptService.selectCntrwkDeptList(deptVO));
		model.addAttribute("roadNoList", roadNoList);
		model.addAttribute("roadGradList", roadGradList);
		model.addAttribute("RpairMthds", rpairMthdList);
		model.addAttribute("statsYear", statsYear);

		session.setAttribute("system", "map");
		return "/map/map";
	}

	@RequestMapping(value = "/proxyPost.do")
	public void proxyPost(HttpServletRequest request, HttpServletResponse res) throws Exception {
		Map<String, String> req = requestToHashMap(request);

		String urlStr = URLDecoder.decode(req.get("url"), "UTF-8");
		String params = URLDecoder.decode(req.get("params"), "UTF-8");

		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		HttpURLConnection huc = (HttpURLConnection) connection;
		huc.setRequestMethod("POST");
		huc.setDoOutput(true);
		huc.setDoInput(true);
		huc.setUseCaches(false);
		huc.setDefaultUseCaches(false);

		IOUtils.copy(IOUtils.toInputStream(params, "UTF-8"), huc.getOutputStream());
		// PrintWriter pOut = new PrintWriter(huc.getOutputStream());
		// pOut.println(params);
		// pOut.close();

		res.reset();
		res.setContentType(huc.getContentType());

		OutputStream ios = res.getOutputStream();
		IOUtils.copy(huc.getInputStream(), ios);

		ios.close();
	}

	@RequestMapping(value = "/proxyGet.do")
	public void proxyGet(HttpServletRequest request, HttpServletResponse res) throws Exception {
		Map<String, String> req = requestToHashMap(request);

		String urlStr = URLDecoder.decode(req.get("url"), "UTF-8");
		String params = URLDecoder.decode(req.get("params"), "UTF-8");

		URL url = new URL(urlStr + params);
		URLConnection connection = url.openConnection();
		HttpURLConnection huc = (HttpURLConnection) connection;
		huc.setRequestMethod("GET");
		huc.setDoOutput(true);
		huc.setDoInput(true);
		huc.setUseCaches(false);
		huc.setDefaultUseCaches(false);

		res.reset();
		res.setContentType(huc.getContentType());

		OutputStream ios = res.getOutputStream();

		IOUtils.copy(huc.getInputStream(), ios);

		ios.close();
	}

	@RequestMapping(value = "/geoProxyPost.do")
	public void geoProxyPost(HttpServletRequest request, HttpServletResponse res) throws Exception {
		try {
			String reqUrl = request.getQueryString();
			reqUrl = java.net.URLDecoder.decode(reqUrl, "UTF-8");

			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// con.setConnectTimeout(1000*30);
			con.setDoOutput(true);
			con.setRequestMethod(request.getMethod());

			if (request.getContentType() != null) {
				con.setRequestProperty("Content-Type", request.getContentType());
			}

			con.setRequestProperty("Referer", request.getHeader("Referer"));
			int clength = request.getContentLength();
			if (clength > 0) {
				con.setDoInput(true);
				InputStream istream = request.getInputStream();
				OutputStream os = con.getOutputStream();
				final int length = 5000;
				byte[] bytes = new byte[length];
				int bytesRead = 0;
				while ((bytesRead = istream.read(bytes, 0, length)) > 0) {
					os.write(bytes, 0, bytesRead);
				}
			} else {
				con.setRequestMethod("GET");
			}

			// out.clear();
			// out = pageContext.pushBody();
			res.reset();
			OutputStream ostream = res.getOutputStream();
			res.setContentType(con.getContentType());
			InputStream in = con.getInputStream();
			final int length = 5000;
			byte[] bytes = new byte[length];
			int bytesRead = 0;
			while ((bytesRead = in.read(bytes, 0, length)) > 0) {
				ostream.write(bytes, 0, bytesRead);
			}

		} catch (IOException e) {
			throw e;

		}
	}

	@RequestMapping(value = "/gmap/attr/getAlias.do")
	public String getAlias(HttpServletRequest request, ModelMap model) throws Exception {
		Map<String, String> req = requestToHashMap(request);
		String params = req.get("data");

		List<Map<String, Object>> listRet = new ArrayList<Map<String, Object>>();

		String[] tmpArr = params.split(":");
		for (int i = 0, len = tmpArr.length; i < len; i++) {
			Map<String, Object> mapLayers = new HashMap<String, Object>();

			Map<String, String> mapFields = new HashMap<String, String>();
			String[] fields = tmpArr[i].split(",");
			for (int j = 0, fieldLen = fields.length; j < fieldLen; j++) {
				// 레이어 명 추출
				if (j == 0) {
					mapLayers.put(fields[0], cmmnService.selectTableAlias(fields[0]));
				}
				// 필드명 추출
				else {
					mapFields.put(fields[j], cmmnService.selectFieldAlias(fields[0], fields[j]));
				}
			}
			mapLayers.put("fields", mapFields);
			listRet.add(mapLayers);
		}

		model.addAttribute("data", listRet);

		return "jsonViewHTML";
	}

	@RequestMapping(value = "/api/attr/getTableAliasList.do")
	public String getTableAliasList(HttpServletRequest request, ModelMap model) throws Exception {

		model.addAttribute("data", cmmnService.selectTableAliasList());
		return "jsonView";
	}

	@RequestMapping(value = "/gmap/attr/getCodeVal.do")
	public String selectCodeList(@ModelAttribute("codeVO") CodeVO codeVO, ModelMap model) throws Exception {

		model.addAttribute("resultData", codeService.selectCodeList(codeVO));

		return "jsonViewHTML";
	}

	/**
	 * 다음지도 위치 통합검색 한다.
	 *
	 * @param
	 * @return "/gmap/selectLocation.do"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/gmap/selectLocation.do" })
	public String selectLocation(@ModelAttribute("searchVO") CodeVO codeVO, ModelMap model) throws Exception {

		return "/map/mapLocationList";
	}

	/**
	 * iasp 위치검색을 한다.
	 *
	 * @param
	 * @return "/gmap/selectLocation_iasp.do"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/gmap/selectLocation_iasp.do" })
	public String selectLocation_iasp(@RequestParam Map<String, Object> param, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		CodeVO codeVO = new CodeVO();
		try {
			model.addAttribute("admCodeList", cmmnService.selectAdmCodeList(codeVO));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return "/map/mapLocationList_iasp";
	}

	@RequestMapping(value = { "/gmap/selectGuLocation_iasp.do" })
	public String selectGuLocation_iasp(CodeVO codeVO, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String cd = codeVO.getADM_CODE().substring(0, 4);
		try {

			codeVO.setCODE_VAL(cd);
			List<CodeVO> sigungu = cmmnService.selectAdmguCodeList(codeVO);
			sigungu.remove(0);
			model.addAttribute("admCodeGuList", sigungu);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return "jsonView";
	}

	@RequestMapping(value = { "/gmap/selectnewGuLocation_iasp.do" })
	public String selectnewGuLocation_iasp(CodeVO codeVO, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		String cd = codeVO.getADM_CODE1().substring(0, 4);
		try {

			codeVO.setCODE_VAL(cd);
			List<CodeVO> sigungu = cmmnService.selectAdmguCodeList(codeVO);
			sigungu.remove(0);
			model.addAttribute("admCodeGuList", sigungu);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return "jsonView";
	}

	@RequestMapping(value = { "/gmap/selectGungGuList.do" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<CodeVO> selectGungGuList(@RequestBody CodeVO codeVO, ModelMap model) throws Exception {
		codeVO.getADM_CODE();
		List<CodeVO> items = cmmnService.selectAdmguCodeList(codeVO);

		return items;
	}

	/**
	 * 지도 이미지를 base64로 리턴해주는 함수
	 *
	 * @param
	 * @return "/map/saveImageToView.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/map/saveImageToView.do")
	public String saveMapImageToView(HttpServletRequest request, HttpServletResponse res, Model model)
			throws Exception {
		Map<String, String> req = requestToHashMap(request);

		String decodeStr = URLDecoder.decode(req.get("datas"), "UTF-8");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
		String fileName = "Map_" + dateFormat.format(calendar.getTime());

		if (fileName.equals("")) {
			fileName = "save";
		}

		//mapService.setType("save");
		//mapService.setRootPath(request.getSession().getServletContext().getRealPath("/"));
		//BufferedImage image = mapService.createImages(decodeStr);

		SaveMapUtils saveMapUtil = new SaveMapUtils();
		saveMapUtil.setType("save");
		saveMapUtil.setRootPath(request.getSession().getServletContext().getRealPath("/"));
		BufferedImage image = saveMapUtil.createImages(decodeStr);

		try {
			// int width = Integer.parseInt(req.get("width"));
			// int height = Integer.parseInt(req.get("height"));
			// image = mapService.resizeImages(image, width, height);

			// 2018. 03. 12.
			// File file = new File(pathInfoProperties.getProperty("file.upload.path") + "/" + fileName + ".png"); // 이미지저장경로
			// ImageIO.write(image, "png", file); // 파일형식

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("base64", mapService.encodingImgToBase64(image));
		return "jsonView";
	}

	@RequestMapping(value = "/map/gmap/getPolygonSymbol.do")
	public void getPolygonSymbol(@ModelAttribute("polygonSymbolVO") PolygonSymbolVO polygonSymbolVO,
			HttpServletResponse res) throws Exception {
		res.reset();
		res.setContentType("Image/png");
		OutputStream ios = res.getOutputStream();
		ImageIO.write(mapService.drawPolygonSymbol(polygonSymbolVO), "png", ios);
		ios.close();
	}

	/**
	 * 지도 경위도 좌표이동 화면 조회
	 *
	 * @param
	 * @return "/selectLonLatMoveView.do"
	 * @exception Exception
	 */
	@RequestMapping(value = { "/selectLonLatMoveView.do" })
	public String selectLonLatMoveView(@ModelAttribute("searchVO") CodeVO codeVO, ModelMap model) throws Exception {

		return "/map/selectLonLatMoveView";
	}

	@RequestMapping(value = "/map/gmap/getImageFromBase64test.do")
	public String getImageFromBase64test(HttpServletRequest request, HttpServletResponse res, Model model)
			throws Exception {

		Map<String, String> req = requestToHashMap(request);

		String decodeStr = req.get("filenm");

		String imagePath = "C://PMS_UploadFile/" + decodeStr;
		File imageFile = new File(imagePath);
		BufferedImage image = ImageIO.read(imageFile); // 초기화

		System.out.println("test : " + decodeStr);
		model.addAttribute("base64", mapService.encodingImgToBase64(image));
		return "jsonView";
	}

	@RequestMapping(value = "/map/gmap/getImageFromBase64test2.do")
	public String getImageFromBase64test2(HttpServletRequest request, HttpServletResponse res, Model model)
			throws Exception {

		Map<String, String> req = requestToHashMap(request);

		String decodeStr = req.get("datas");

		// System.out.println("test 2 : " + req.get("datas"));
		model.addAttribute("base64_2", "ok");
		return "jsonView";
	}

}
