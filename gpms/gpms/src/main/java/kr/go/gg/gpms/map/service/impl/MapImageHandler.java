package kr.go.gg.gpms.map.service.impl;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.imageio.ImageIO;

import kr.go.gg.gpms.map.service.MapService;
import kr.go.gg.gpms.map.service.model.PolygonSymbolVO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.util.Base64;
import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;

@Service("mapService")
public class MapImageHandler extends Component implements MapService {
	private static final long serialVersionUID = 3692632129860736086L;
	private BufferedImage bi = null;
	private final int A3WIDHT = 1190;	//A3 width  Pixel
	private final int A4WIDHT = 842;	//A3 height Pixel
	private final int A3HEIGHT = 842;	//A4 width  Pixel
	private final int A4HEIGHT = 595;	//A4 height Pixel
	private int width;					//origin width Pixel
	private int height;					//origin height Pixel
	private String type;
	private String rootPath;			//image root Path
	private String saveType;			//saveType - [typeScale, typeArea]
	private String saveSize;			//saveSize - [a3, a4]
	private String savePow;			//saveSize - [a3, a4]
	private String sldBody;				//sld String - 현재 WMS 하나 밖에 안됨
	private double centerX;				//중심 X 좌표
	private double centerY;				//중심 Y 좌표
	private double left;				//지도 left 좌표
	private double bottom;				//지도 bottom 좌표
	private double right;				//지도 right 좌표
	private double top;					//지도 top 좌표
	private double resolution;			//resolution
	private double areaResoultion;

	public void setType(String type) {
		this.type = type;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public BufferedImage createImages(String pStr) throws Exception {
		Document doc = new SAXBuilder().build(new StringReader(pStr));

		parseMap(doc);

		this.bi = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = this.bi.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if(this.type != null && this.type.compareTo("save") == 0) {
			graphics.setBackground(Color.white);
			graphics.fillRect(0, 0, this.width, this.height);
			//drawMashupLayer(doc, graphics); // YYK 이미지 인쇄/저장 시 메시업레이어 제거
			drawLayers(doc, graphics);
		}
		drawVectorLayers(doc, graphics);
		drawVectorPopups(doc, graphics);

		graphics.dispose();

		return this.bi;
	}

	/**
	 * Map Parsing
	 * @return
	 * @throws Exception
	 */
	protected void parseMap(Document doc) throws Exception {
		Iterator<?> itrLayer = doc.getDescendants(new ElementFilter("MAP"));
		while(itrLayer.hasNext()) {
			Content conLayer = (Content)itrLayer.next();
			if(conLayer instanceof Element) {
				Element element = (Element)conLayer;

				if(this.saveSize != null && this.saveSize.compareTo("a3") == 0) {
					this.width = this.A3WIDHT;
					this.height = this.A3HEIGHT;
				}
				else if(this.saveSize != null && this.saveSize.compareTo("a4") == 0) {
					this.width = this.A4WIDHT;
					this.height = this.A4HEIGHT;
				}
				else {
					this.width = Integer.parseInt(getValueXML(element, "width"));
					this.height = Integer.parseInt(getValueXML(element, "height"));
				}

				this.resolution = Double.parseDouble(getValueXML(element, "resolution"));
				this.left = Double.parseDouble(getValueXML(element, "left"));
				this.right = Double.parseDouble(getValueXML(element, "right"));
				this.bottom = Double.parseDouble(getValueXML(element, "bottom"));
				this.top = Double.parseDouble(getValueXML(element, "top"));
				this.centerX = (this.right + this.left) / 2;
				this.centerY = (this.top + this.bottom) / 2;

				//축척 기준
				if(this.saveType != null && this.saveType.compareTo("typeScale") == 0) {
					Double left = Double.parseDouble(getValueXML(element, "left"));
					Double right = Double.parseDouble(getValueXML(element, "right"));
					Double bottom = Double.parseDouble(getValueXML(element, "bottom"));
					Double top = Double.parseDouble(getValueXML(element, "top"));

					double deltaX = 0;
					double deltaY = 0;

					deltaX = ((right-left) * this.width) / Integer.parseInt(getValueXML(element, "width"));
					deltaY = ((top-bottom) * this.height) / Integer.parseInt(getValueXML(element, "height"));

					this.left = this.centerX - deltaX / 2;
					this.bottom = this.centerY - deltaY / 2;
					this.right = this.centerX + deltaX / 2;
					this.top = this.centerY + deltaY / 2;
				}
				//영역기준
				else if(this.saveType != null && this.saveType.compareTo("typeArea") == 0) {
					int tmpWidth = Integer.parseInt(getValueXML(element, "width"));
					int tmpHeight = Integer.parseInt(getValueXML(element, "height"));

					double subValue = 0;

					if(tmpWidth > tmpHeight) {
						if(this.saveSize != null && this.saveSize.compareTo("a3") == 0) {
							subValue = (double)tmpWidth / this.A3WIDHT;
						}
						else if(this.saveSize != null && this.saveSize.compareTo("a4") == 0) {
							subValue = (double)tmpWidth / this.A4WIDHT;
						}
					}
					else {
						if(this.saveSize != null && this.saveSize.compareTo("a3") == 0) {
							subValue = (double)tmpHeight / this.A3HEIGHT;
						}
						else if(this.saveSize != null && this.saveSize.compareTo("a4") == 0) {
							subValue = (double)tmpHeight / this.A4HEIGHT;
						}
					}

					if(subValue < 0.5) {
						this.areaResoultion = 0.5;
					}
					else if(subValue <= 1) {
						this.areaResoultion = 1;
					}
					else {
						this.areaResoultion = 1 / Math.ceil(subValue);
					}
					this.width = (int)(tmpWidth * this.areaResoultion);
					this.height = (int) (tmpHeight * this.areaResoultion);
				}
				if(this.savePow != null) {
					int tmpWidth = Integer.parseInt(getValueXML(element, "width"));
					int tmpHeight = Integer.parseInt(getValueXML(element, "height"));

					this.width = tmpWidth * Integer.parseInt(this.savePow);
					this.height = tmpHeight * Integer.parseInt(this.savePow);
					this.resolution = this.resolution / Integer.parseInt(this.savePow);
				}
			}
		}
	}

	protected void drawLayers(Document doc, Graphics2D graphics) throws Exception {
		Iterator<?> itrLayer = doc.getDescendants(new ElementFilter("LAYER"));

		MediaTracker tracker = new MediaTracker(this);

		while(itrLayer.hasNext()) {
			Content conLayer = (Content)itrLayer.next();
			if(conLayer instanceof Element) {
				Element element = (Element)conLayer;

				if(element.getAttributeValue("type").equalsIgnoreCase("wms")) {
					URL url = new URL(getValueXML(element, "url"));
					String params = "layers=" + URLEncoder.encode(getValueXML(element, "layers"), "UTF-8");
					params += "&styles=" + URLEncoder.encode(getValueXML(element, "styles"), "UTF-8");
					params += "&format=" + URLEncoder.encode(getValueXML(element, "format"), "UTF-8");
					params += "&version=" + URLEncoder.encode(getValueXML(element, "version"), "UTF-8");
					params += "&crs=" + URLEncoder.encode(getValueXML(element, "crs"), "UTF-8");
					params += "&service=" + URLEncoder.encode(getValueXML(element, "service"), "UTF-8");
					params += "&request=" + URLEncoder.encode(getValueXML(element, "request"), "UTF-8");
					params += "&exceptions=" + URLEncoder.encode(getValueXML(element, "exceptions"), "UTF-8");
					params += "&bbox=" + this.left +","+ this.bottom +","+ this.right +","+ this.top;
					params += "&width=" + this.width;
					params += "&height=" + this.height;
					params += "&TRANSPARENT=true";
/* YYK 이미지 인쇄/저장 시 sldBody 제거(값 안넘기게끔)
					String sldBody = getValueXML(element, "sldbody");

					if(sldBody != null && !sldBody.equalsIgnoreCase("")) {
						if(sldBody.indexOf("%") > 0) {
							sldBody = sldBody.replace("%", "%2525");
						}
						params += "&SLD_BODY=" + sldBody;
					}
*/
					URLConnection connection = url.openConnection();
					HttpURLConnection huc = (HttpURLConnection)connection;
					huc.setRequestMethod("POST");
					huc.setDoOutput(true);
					huc.setDoInput(true);
					huc.setUseCaches(false);
					huc.setDefaultUseCaches(false);
				/*	PrintWriter out = new PrintWriter(huc.getOutputStream());
					out.println(params);
					out.close();*/
					IOUtils.copy(IOUtils.toInputStream(params, "UTF-8"), huc.getOutputStream());

					BufferedImage tempImage = ImageIO.read(huc.getInputStream());
					//BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
					//tracker.addImage(tempImage, 0);
					//tracker.waitForAll();
					graphics.drawImage(tempImage, 0, 0, this.width, this.height, null);

					//System.out.println(br.readLine());
					//System.out.println("url-" + url);
					//System.out.println(params);
					//System.out.println("widht-" + this.width + " : height-" + this.height);
				}
				else if(element.getAttributeValue("type").equalsIgnoreCase("tilecache")) {
					URL url = new URL(getValueXML(element, "url"));
					int tileZ = Integer.parseInt(getValueXML(element, "scaleLevel"));
					String extension = getValueXML(element, "extension");
					Double maxLeft = Double.parseDouble(getValueXML(element, "maxLeft"));     //extent
					Double maxBottom = Double.parseDouble(getValueXML(element, "maxBottom"));

					int buffer = 1;

					int minRows = (int)(Math.ceil(this.height/256) + Math.max(1, 2 * buffer));
					int minCols = (int)(Math.ceil(this.width/256) + Math.max(1, 2 * buffer));

					double layoutTilelon = this.resolution * 256;
					double layoutTilelat = this.resolution * 256;

					double offsetlon = this.left - maxLeft;
					int tilecol = (int) (Math.floor(offsetlon/layoutTilelon) - buffer);
					double tilecolremain = offsetlon/layoutTilelon - tilecol;
					double layoutTileoffsetx = -tilecolremain * 256;
					double layoutTileoffsetlon = maxLeft + tilecol * layoutTilelon;

					double offsetlat = this.top - (maxBottom + layoutTilelat);
					int tilerow = (int) (Math.ceil(offsetlat/layoutTilelat) + buffer);
					double tilerowremain = tilerow - offsetlat/layoutTilelat;
					double layoutTileoffsety = -tilerowremain * 256;
					double layoutTileoffsetlat = maxBottom + tilerow * layoutTilelat;

					int tileoffsetx = (int) Math.round(layoutTileoffsetx); // heaven help us
					int tileoffsety = (int) Math.round(layoutTileoffsety);

					double tileoffsetlon = layoutTileoffsetlon;
					double tileoffsetlat = layoutTileoffsetlat;

					double tilelon = layoutTilelon;
					double tilelat = layoutTilelat;

			        int startX = tileoffsetx;
			        double startLon = tileoffsetlon;

			        double rowidx = 0;

			        int layerContainerDivLeft = 0;
			        int layerContainerDivTop = 0;


			        do {
			        	rowidx++;
			        	tileoffsetlon = startLon;
			        	tileoffsetx = startX;
			            int colidx = 0;

			            do {
			            	double offLeft = tileoffsetlon;
			            	double offBottom = tileoffsetlat;

			            	int x = tileoffsetx;
			            	x -= layerContainerDivLeft;

			                int y = tileoffsety;
			                y -= layerContainerDivTop;

			                colidx++;

			                String params = getTileURL(offLeft, offBottom, tileZ);

			                Image tileImg = Toolkit.getDefaultToolkit().getImage(new URL(url + params + extension));

			                //tracker.addImage(tileImg, 0);
							//tracker.waitForAll();



							graphics.drawImage(tileImg, x, y, 256, 256, null);

			                //System.out.println(url+params+extension + " , x : " + x + ", : y : " + y);

			                tileoffsetlon += tilelon;
			                tileoffsetx += 256;
			            }while((tileoffsetlon <= this.right + tilelon * buffer) || colidx < minCols);
			            tileoffsetlat -= tilelat;
			            tileoffsety += 256;
			        }while((tileoffsetlat >= this.bottom - tilelat * buffer) || rowidx < minRows);
				}
				// 서울 재난 프로젝트 ArcGIS Cache 이미지 저장...
				else if(element.getAttributeValue("type").equalsIgnoreCase("ArcGISCache")) {
					URL url = new URL(getValueXML(element, "url"));
					int tileZ = Integer.parseInt(getValueXML(element, "scaleLevel"));
					//String extension = getValueXML(element, "extension");
					Double maxLeft = Double.parseDouble(getValueXML(element, "maxLeft"));     //extent
					Double maxTop = Double.parseDouble(getValueXML(element, "maxTop"));
					Double maxBottom = Double.parseDouble(getValueXML(element, "maxBottom"));
					Double resolution = Double.parseDouble(getValueXML(element, "resolution"));

					Double centerX = Double.parseDouble(getValueXML(element, "centerX"));
					Double centerY = Double.parseDouble(getValueXML(element, "centerY"));

					int buffer = 1;

					int minRows = (int)(Math.ceil(this.height/1024) + Math.max(1, 2 * buffer));
					int minCols = (int)(Math.ceil(this.width/1024) + Math.max(1, 2 * buffer));

					Double tileOriginLon = Double.parseDouble(getValueXML(element, "tileOriginLon"));
					Double tileOriginLat = Double.parseDouble(getValueXML(element, "tileOriginLat"));


					double layoutTilelon = resolution * 1024;
					double layoutTilelat = resolution * 1024;

					double offsetlon = this.left - maxLeft;
					int tilecol = (int) (Math.floor(offsetlon/layoutTilelon) - buffer);
					double tilecolremain = offsetlon/layoutTilelon - tilecol;
					double layoutTileoffsetx = -tilecolremain * 1024;
					double layoutTileoffsetlon = maxLeft + tilecol * layoutTilelon;

					double offsetlat = this.top - (maxBottom + layoutTilelat);
					int tilerow = (int) (Math.ceil(offsetlat/layoutTilelat) + buffer);
					double tilerowremain = tilerow - offsetlat/layoutTilelat;
					double layoutTileoffsety = -tilerowremain * 1024;
					double layoutTileoffsetlat = maxBottom + tilerow * layoutTilelat;


					int tileoffsetx = (int) Math.round(layoutTileoffsetx); // heaven help us
					int tileoffsety = (int) Math.round(layoutTileoffsety);

					double tileoffsetlon = layoutTileoffsetlon;
					double tileoffsetlat = layoutTileoffsetlat;

					double tilelon = layoutTilelon;
					double tilelat = layoutTilelat;

			        int startX = tileoffsetx;
			        double startLon = tileoffsetlon;

			        double rowidx = 0;

			        int layerContainerDivLeft = 0;
			        int layerContainerDivTop = 0;


			        do {
			        	rowidx++;
			        	tileoffsetlon = startLon;
			        	tileoffsetx = startX;
			            int colidx = 0;

			            do {
			            	double offLeft = tileoffsetlon;
			            	double offBottom = tileoffsetlat;

			            	int x = tileoffsetx;
			            	x -= layerContainerDivLeft;

			                int y = tileoffsety;
			                y -= layerContainerDivTop;

			                colidx++;

			                String params = getTileURL_ArcGISCache(offLeft, offBottom, tileZ,tileOriginLon,tileOriginLat,resolution);


			                Image tileImg = Toolkit.getDefaultToolkit().getImage(new URL(url + params));

			                tracker.addImage(tileImg, 0);
							tracker.waitForAll();



							graphics.drawImage(tileImg, x, y, 1024, 1024, null);

			                //System.out.println(url+params+extension + " , x : " + x + ", : y : " + y);

			                tileoffsetlon += tilelon;
			                tileoffsetx += 1024;
			            }while((tileoffsetlon <= this.right + tilelon * buffer) || colidx < minCols);
			            tileoffsetlat -= tilelat;
			            tileoffsety += 1024;
			        }while((tileoffsetlat >= this.bottom - (tilelat * buffer)) || rowidx < minRows);
				}
			}
		}
	}

	protected String getTileURL(double offLeft, double offBottom, int tileZ) throws Exception {
		double tileX = offBottom;
		double tileY = offLeft;

		String params = "";
		params += "/" + zeroPad(tileZ, 2);
		params += "/" + zeroPad((int)Math.floor(tileX / 1000000), 3);
		params += "/" + zeroPad((int)Math.floor(tileX / 1000) % 1000, 3);
		params += "/" + zeroPad((int)Math.floor(tileX) % 1000, 3);
		params += "/" + zeroPad((int)Math.floor(tileY / 1000000), 3);
		params += "/" + zeroPad((int)Math.floor(tileY / 1000) % 1000, 3);
		params += "/" + zeroPad((int)Math.floor(tileY) % 1000, 3);

		return params;
	}

	protected String getTileURL_ArcGISCache(double offLeft, double offBottom, int tileZ, double tileOriginLon, double tileOriginLat, double resolution) throws Exception {

		// tile center
        double originTileX = (tileOriginLon + (resolution * 1024/2));
        double originTileY = (tileOriginLat - (resolution * 1024/2));

        double centerX = (offLeft + (resolution * 1024 / 2));
        double centerY = (offBottom + (resolution * 1024 / 2));

        int z = tileZ;

		String params = "/tile";
		params += "/" + z;
		params += "/" + (int) (Math.round(Math.abs((originTileY - centerY) / (resolution * 1024))));
		params += "/" + (int) (Math.round(Math.abs((centerX - originTileX) / (resolution * 1024))));

		return params;
	}

	protected String zeroPad(int number, int length) {
		String temp = new Integer(number).toString();
		String result = "";

		if(temp.length() <= 3) {
			for(int i=0; i < length-temp.length(); i++) {
				result += "0";
			}
			result += temp;
		}
		return result;
	}

	protected void drawVectorLayers(Document doc, Graphics2D graphics) throws Exception {
		Iterator<?> itrLayer = doc.getDescendants(new ElementFilter("VECTORLAYER"));

		while(itrLayer.hasNext()) {
			Content conLayer = (Content)itrLayer.next();
			if(conLayer instanceof Element) {
				Element element = (Element)conLayer;

				Iterator<?> itrFeature = element.getDescendants(new ElementFilter("FEATURE"));
				while(itrFeature.hasNext()) {
					Content conFeature = (Content)itrFeature.next();
					if(conFeature instanceof Element) {
						Element eleFeature = (Element)conFeature;

						if(eleFeature.getAttributeValue("type").equalsIgnoreCase("point") && !eleFeature.getValue().isEmpty() ) {
							drawSymbol(eleFeature, graphics);
						}
						else if(eleFeature.getAttributeValue("type").equalsIgnoreCase("lineString")) {
							drawLineString(eleFeature, graphics);
						}
						else if(eleFeature.getAttributeValue("type").equalsIgnoreCase("arrow")) {
							drawArrow(eleFeature, graphics);
						}
						else if(eleFeature.getAttributeValue("type").equalsIgnoreCase("polygon")) {
							drawPolygon(eleFeature, graphics);
						}
					}
				}
			}
		}
	}

	/**
	 *
	 * @param element
	 * @param graphics
	 * @throws Exception
	 */
	protected void drawSymbol(Element element, Graphics2D graphics) throws Exception {

		try {
			//좌표 구함
			double lon = Double.parseDouble(getValueXML(element, "x"));
			double lat = Double.parseDouble(getValueXML(element, "y"));

			//int x = (int)Math.round(1/this.resolution * (lon - this.left));
			//int y = (int)Math.round(1/this.resolution * (this.top - lat));

			int x = (int)Math.round(1/this.resolution * (lon - this.left));
			int y = (int)Math.round(1/this.resolution * (this.top - lat));

			if(this.saveType != null && this.saveType.compareTo("typeArea") == 0) {
				x = (int) Math.round(x * this.areaResoultion);
				y = (int) Math.round(y * this.areaResoultion);
			}

			//선 투명도 구함
			AlphaComposite alpha;
			if((!getValueXML(element, "opacity").equalsIgnoreCase("")) && (!getValueXML(element, "opacity").equalsIgnoreCase("undefined"))) {
				alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Float.parseFloat(getValueXML(element, "opacity")));
			}
			else {
				alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1.0);
			}

			//면 투명도 구함
			AlphaComposite alphaFill;
			if((!getValueXML(element, "fillOpacity").equalsIgnoreCase("")) && (!getValueXML(element, "fillOpacity").equalsIgnoreCase("undefined"))) {
				alphaFill = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Float.parseFloat(getValueXML(element, "fillOpacity")));
			}
			else {
				alphaFill = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1.0);
			}

			//도형 타입 구함
			String featureType = getValueXML(element, "featureType");

			//이미지
			if(featureType.equalsIgnoreCase("Image")) {
				//이미지
				String imgPath = getValueXML(element, "image");

				Image img;
				//Image img;
				if(imgPath.subSequence(0, 1).equals("/")) {
					// 2018.02.25. YYK. 경로 재설정 (gpms 삭제)
					if (imgPath.subSequence(0, 6).equals("/gpms/")){
						imgPath = imgPath.replaceFirst("/gpms/", "");
					}
					imgPath = imgPath.replaceAll("/", "\\\\");

					img = Toolkit.getDefaultToolkit().getImage(this.rootPath + imgPath);
				}
				else {
					try {
						img = Toolkit.getDefaultToolkit().getImage(new URL(imgPath));
					} catch (Exception e) {
						img = ImageIO.read(new ByteArrayInputStream(Base64.decodeBase64(imgPath.split(",")[1])));
					}
				}

				//크기
				int width = Integer.parseInt(getValueXML(element, "width"));
				int height = Integer.parseInt(getValueXML(element, "height"));

				if(this.savePow != null) {
					width *= Integer.parseInt(this.savePow);
					height *= Integer.parseInt(this.savePow);
				}

				//투명도
				graphics.setComposite(alpha);



				// 2018.02.28 YYK. 왼쪽 상단 위치 좌표
				int x2= x - width / 2;
				int y2= y - height / 2;

				// 2018.02.24 YYK 마커 이미지 회전
				String rotate = getValueXML(element, "rotation");
				if (rotate == null || rotate.isEmpty() || rotate == "" ){
					graphics.drawImage(img, x, y, width, height, null);
				}
				else{

					//graphics.translate( x, y);
					graphics.translate( x, y );
					graphics.rotate(Math.toRadians(Double.parseDouble(getValueXML(element, "rotation"))));
					//graphics.translate( x* -1 ,y * -1 );
					graphics.translate( x * -1 , y * -1 );

					//이미지 로딩 대기
					MediaTracker tracker = new MediaTracker(this);
					tracker.addImage(img, 0);
					tracker.waitForAll();

					//이미지 그림
					graphics.drawImage(img, x2, y2, width, height, null);

					//graphics.translate( x ,y );
					graphics.translate( x , y );
					graphics.rotate(Math.toRadians(Double.parseDouble(getValueXML(element, "rotation"))*-1));
					//graphics.translate( x* -1 ,y * -1 );
					graphics.translate( x * -1 , y * -1 );

				}
			}


			else if(featureType.equalsIgnoreCase("Point")) {
				//면 색
				String rgb = getValueXML(element, "fillColor");
				int r = Integer.parseInt(getHexToDec(rgb.substring(1, 3)));
				int g = Integer.parseInt(getHexToDec(rgb.substring(3, 5)));
				int b = Integer.parseInt(getHexToDec(rgb.substring(5, 7)));
				Color colorFill = new Color(r, g, b);

				//선 색
				rgb = getValueXML(element, "color");
				r = Integer.parseInt(getHexToDec(rgb.substring(1, 3)));
				g = Integer.parseInt(getHexToDec(rgb.substring(3, 5)));
				b = Integer.parseInt(getHexToDec(rgb.substring(5, 7)));
				Color color = new Color(r, g, b);

				//반지름
				Integer radius = Integer.parseInt(getValueXML(element, "radius"));

				//도형 타입
				String graphicName = getValueXML(element, "graphicName");

				//선 굵기
				if((!getValueXML(element, "stroke").equalsIgnoreCase("")) && (!getValueXML(element, "stroke").equalsIgnoreCase("undefined"))) {
					graphics.setStroke(new BasicStroke(Float.parseFloat(getValueXML(element, "stroke"))));
				}
				else {
					graphics.setStroke(new BasicStroke(1.0f));
				}

				//사각형
				if(graphicName.equalsIgnoreCase("square")) {
					//좌표 구함
					int[] intX = new int[4];
					int[] intY = new int[4];
					intX[0] = x-radius;
					intX[1] = x-radius;
					intX[2] = x+radius;
					intX[3] = x+radius;

					intY[0] = y+radius;
					intY[1] = y-radius;
					intY[2] = y-radius;
					intY[3] = y+radius;

					//면 그림
					graphics.setComposite(alphaFill);
					graphics.setColor(colorFill);
					graphics.fillPolygon(intX, intY, 4);

					//선 그림
					graphics.setComposite(alpha);
					graphics.setColor(color);
					graphics.drawPolygon(intX, intY, 4);
				}
				//원
				else if(graphicName.equalsIgnoreCase("circle")) {
					//면 그림
					graphics.setComposite(alphaFill);
					graphics.setColor(colorFill);
					graphics.fillOval(x-radius, y-radius, radius*2, radius*2);

					//테두리 그림
					graphics.setComposite(alpha);
					graphics.setColor(color);
					graphics.drawOval(x-radius, y-radius, radius*2, radius*2);
				}

				//선 굵기, 투명도 초기화
				graphics.setStroke(new BasicStroke((float)1));
				graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1.0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void drawLineString(Element element, Graphics2D graphics) throws Exception {
		//좌표 구함
		String[] strX;
		String[] strY;

		String tempStr = getValueXML(element, "x");
		strX = tempStr.split(",");

		tempStr = getValueXML(element, "y");
		strY = tempStr.split(",");

		int[] intX = new int[strX.length];
		int[] intY = new int[strY.length];

		for(int i=0; i < strX.length; i++) {
			double lon = Double.parseDouble(strX[i]);
			double lat = Double.parseDouble(strY[i]);

			intX[i] = (int)Math.round(1/this.resolution * (lon - this.left));
			intY[i] = (int)Math.round(1/this.resolution * (this.top - lat));

			if(this.saveType != null && this.saveType.compareTo("typeArea") == 0) {
				intX[i] = (int) Math.round(intX[i] * this.areaResoultion);
				intY[i] = (int) Math.round(intY[i] * this.areaResoultion);
			}
		}

		//투명도 구함
		if((!getValueXML(element, "opacity").equalsIgnoreCase("")) && (!getValueXML(element, "opacity").equalsIgnoreCase("undefined"))) {
			AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Float.parseFloat(getValueXML(element, "opacity")));
			graphics.setComposite(alpha);
		}
		else {
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1.0));
		}

		//색 구함
		String rgb = getValueXML(element, "color");
		int r = Integer.parseInt(getHexToDec(rgb.substring(1, 3)));
		int g = Integer.parseInt(getHexToDec(rgb.substring(3, 5)));
		int b = Integer.parseInt(getHexToDec(rgb.substring(5, 7)));

		graphics.setColor(new Color(r, g, b));

		//굵기 구함
		Float strokeWidth;
		if((!getValueXML(element, "stroke").equalsIgnoreCase("")) && (!getValueXML(element, "stroke").equalsIgnoreCase("undefined"))) {
			strokeWidth = Float.parseFloat(getValueXML(element, "stroke"));
		}
		else {
			strokeWidth = new Float(1);
		}

		//모서리 스타일
		int strokeCap = BasicStroke.CAP_BUTT;
		if((!getValueXML(element, "strokeLinecap").equalsIgnoreCase("")) && (!getValueXML(element, "strokeLinecap").equalsIgnoreCase("undefined"))) {
			String capType = getValueXML(element, "strokeLinecap");

			if(capType.equalsIgnoreCase("butt")) {
				strokeCap = BasicStroke.CAP_BUTT;
			}
			else if(capType.equalsIgnoreCase("round")) {
				strokeCap = BasicStroke.CAP_ROUND;
			}
			else if(capType.equalsIgnoreCase("square")) {
				strokeCap = BasicStroke.CAP_SQUARE;
			}
		}

		//선 스타일
		float[] dashStyle = null;
		if((!getValueXML(element, "strokeDashstyle").equalsIgnoreCase("")) && (!getValueXML(element, "strokeDashstyle").equalsIgnoreCase("undefined"))) {
			String dashType = getValueXML(element, "strokeDashstyle");

			if(dashType.equalsIgnoreCase("dot")) {
				dashStyle = new float[] { 2.0f, 2.0f };
			}
			else if(dashType.equalsIgnoreCase("dash")) {
				dashStyle = new float[] { 7.0f, 3.0f };
			}
			else if(dashType.equalsIgnoreCase("dashdot")) {
				dashStyle = new float[] { 10.0f, 2.0f, 2.0f, 2.0f };
			}
			else if(dashType.equalsIgnoreCase("dashdotdot")) {
				dashStyle = new float[] { 10.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f };
			}
		}

		//스타일 적용
		if(dashStyle == null) {
			graphics.setStroke(new BasicStroke(strokeWidth, strokeCap, BasicStroke.JOIN_ROUND));
		}
		else {
			graphics.setStroke(new BasicStroke(strokeWidth, strokeCap, BasicStroke.JOIN_ROUND, 1.0f, dashStyle, 0f));
		}

		//선 그림
		graphics.drawPolyline(intX, intY, intX.length);

		//설정 초기화
		graphics.setStroke(new BasicStroke((float)1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1.0));
	}

	protected void drawArrow(Element element, Graphics2D graphics) throws Exception {
		String[] strX;
		String[] strY;

		if(!getValueXML(element, "opacity").equalsIgnoreCase("undefined")) {
			AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Float.parseFloat(getValueXML(element, "opacity")));
			graphics.setComposite(alpha);
		}

		String tempStr = getValueXML(element, "x");
		strX = tempStr.split(",");

		tempStr = getValueXML(element, "y");
		strY = tempStr.split(",");

		int[] intX = new int[strX.length];
		int[] intY = new int[strY.length];

		for(int i=0; i < strX.length; i++) {
			double lon = Double.parseDouble(strX[i]);
			double lat = Double.parseDouble(strY[i]);

			intX[i] = (int)Math.round(1/this.resolution * (lon - this.left));
			intY[i] = (int)Math.round(1/this.resolution * (this.top - lat));

			if(this.saveType != null && this.saveType.compareTo("typeArea") == 0) {
				intX[i] = (int) Math.round(intX[i] * this.areaResoultion);
				intY[i] = (int) Math.round(intY[i] * this.areaResoultion);
			}
		}

		//int lineStroke = Integer.parseInt(getValueXML(element, "stroke"), "UTF-8"));

		//4개점으로 중심을 정해서 테스트
		int[] originX = {-7, -7, -7, 0};
		int[] originY = {-5, 0, 5, 0};
		int[] triangleX = new int[4];
		int[] triangleY = new int[4];


		double degree = getAngle(intX[intX.length-2], intY[intY.length-2], intX[intX.length-1], intY[intY.length-1]);

		for(int i = 0; i < originX.length; i++) {
			triangleX[i] = Math.round((float)getRotatePointX(originX[i], originY[i], degree)) + intX[intX.length-1];
			triangleY[i] = Math.round((float)getRotatePointY(originX[i], originY[i], degree)) + intY[intY.length-1];
		}

		String rgb = getValueXML(element, "color");

		int r = Integer.parseInt(getHexToDec(rgb.substring(1, 3)));
		int g = Integer.parseInt(getHexToDec(rgb.substring(3, 5)));
		int b = Integer.parseInt(getHexToDec(rgb.substring(5, 7)));

		graphics.setColor(new Color(r, g, b));

		if(!getValueXML(element, "stroke").equalsIgnoreCase("undefined")) {
			graphics.setStroke(new BasicStroke(Float.parseFloat(getValueXML(element, "stroke"))));
		}

		graphics.drawPolyline(intX, intY, intX.length);
		graphics.fillPolygon(triangleX, triangleY, triangleX.length);

		graphics.setStroke(new BasicStroke((float)1));
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1.0));
	}

	protected void drawPolygon(Element element, Graphics2D graphics) throws Exception {
		//좌표 구함
		String[] strX;
		String[] strY;

		String tempStr = getValueXML(element, "x");
		strX = tempStr.split(",");

		tempStr = getValueXML(element, "y");
		strY = tempStr.split(",");

		int[] intX = new int[strX.length];
		int[] intY = new int[strY.length];

		for(int i=0; i < strX.length; i++) {
			double lon = Double.parseDouble(strX[i]);
			double lat = Double.parseDouble(strY[i]);

			intX[i] = (int)Math.round(1/this.resolution * (lon - this.left));
			intY[i] = (int)Math.round(1/this.resolution * (this.top - lat));

			if(this.saveType != null && this.saveType.compareTo("typeArea") == 0) {
				intX[i] = (int) Math.round(intX[i] * this.areaResoultion);
				intY[i] = (int) Math.round(intY[i] * this.areaResoultion);
			}
		}

		//선 투명도 구함
		AlphaComposite alpha;
		if((!getValueXML(element, "opacity").equalsIgnoreCase("")) && (!getValueXML(element, "opacity").equalsIgnoreCase("undefined"))) {
			alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Float.parseFloat(getValueXML(element, "opacity")));
		}
		else {
			alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1.0);
		}

		//면 투명도 구함
		AlphaComposite alphaFill;
		if((!getValueXML(element, "fillOpacity").equalsIgnoreCase("")) && (!getValueXML(element, "fillOpacity").equalsIgnoreCase("undefined"))) {
			alphaFill = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Float.parseFloat(getValueXML(element, "fillOpacity")));
		}
		else {
			alphaFill = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1.0);
		}

		//면 색
		String rgb = getValueXML(element, "fillColor");
		int r = Integer.parseInt(getHexToDec(rgb.substring(1, 3)));
		int g = Integer.parseInt(getHexToDec(rgb.substring(3, 5)));
		int b = Integer.parseInt(getHexToDec(rgb.substring(5, 7)));
		Color colorFill = new Color(r, g, b);

		//선 색
		Color color = null;
		if((!getValueXML(element, "stroke").equalsIgnoreCase("")) && (!getValueXML(element, "stroke").equalsIgnoreCase("undefined"))) {
			rgb = getValueXML(element, "color");
			r = Integer.parseInt(getHexToDec(rgb.substring(1, 3)));
			g = Integer.parseInt(getHexToDec(rgb.substring(3, 5)));
			b = Integer.parseInt(getHexToDec(rgb.substring(5, 7)));
			color = new Color(r, g, b);
		}

		//선 굵기
		if((!getValueXML(element, "stroke").equalsIgnoreCase("")) && (!getValueXML(element, "stroke").equalsIgnoreCase("undefined"))) {
			graphics.setStroke(new BasicStroke(Float.parseFloat(getValueXML(element, "stroke"))));
		}
		else {
			graphics.setStroke(new BasicStroke(1.0f));
		}

		//굵기 구함
		Float strokeWidth;
		if((!getValueXML(element, "stroke").equalsIgnoreCase("")) && (!getValueXML(element, "stroke").equalsIgnoreCase("undefined"))) {
			strokeWidth = Float.parseFloat(getValueXML(element, "stroke"));
		}
		else {
			strokeWidth = new Float(1);
		}

		//모서리 스타일
		int strokeCap = BasicStroke.CAP_BUTT;
		if((!getValueXML(element, "strokeLinecap").equalsIgnoreCase("")) && (!getValueXML(element, "strokeLinecap").equalsIgnoreCase("undefined"))) {
			String capType = getValueXML(element, "strokeLinecap");

			if(capType.equalsIgnoreCase("butt")) {
				strokeCap = BasicStroke.CAP_BUTT;
			}
			else if(capType.equalsIgnoreCase("round")) {
				strokeCap = BasicStroke.CAP_ROUND;
			}
			else if(capType.equalsIgnoreCase("square")) {
				strokeCap = BasicStroke.CAP_SQUARE;
			}
		}

		//선 스타일
		float[] dashStyle = null;
		if((!getValueXML(element, "strokeDashstyle").equalsIgnoreCase("")) && (!getValueXML(element, "strokeDashstyle").equalsIgnoreCase("undefined"))) {
			String dashType = getValueXML(element, "strokeDashstyle");

			if(dashType.equalsIgnoreCase("dot")) {
				dashStyle = new float[] { 2.0f, 2.0f };
			}
			else if(dashType.equalsIgnoreCase("dash")) {
				dashStyle = new float[] { 7.0f, 3.0f };
			}
			else if(dashType.equalsIgnoreCase("dashdot")) {
				dashStyle = new float[] { 10.0f, 2.0f, 2.0f, 2.0f };
			}
			else if(dashType.equalsIgnoreCase("dashdotdot")) {
				dashStyle = new float[] { 10.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f };
			}
		}

		//스타일 적용
		if(dashStyle == null) {
			graphics.setStroke(new BasicStroke(strokeWidth, strokeCap, BasicStroke.JOIN_ROUND));
		}
		else {
			graphics.setStroke(new BasicStroke(strokeWidth, strokeCap, BasicStroke.JOIN_ROUND, 1.0f, dashStyle, 0f));
		}

		//면 그리기
		graphics.setColor(colorFill);
		graphics.setComposite(alphaFill);
		graphics.fillPolygon(intX, intY, intX.length);

		//선 그리기
		if((!getValueXML(element, "stroke").equalsIgnoreCase("")) && (!getValueXML(element, "stroke").equalsIgnoreCase("undefined"))) {
			graphics.setColor(color);
			graphics.setComposite(alpha);
			graphics.drawPolygon(intX, intY, intX.length);
		}


		//설정 초기화
		graphics.setStroke(new BasicStroke((float)1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1.0));

		if(!getValueXML(element, "label").equalsIgnoreCase("undefined")) {
			if(!getValueXML(element, "centerX").equalsIgnoreCase("undefined") && !getValueXML(element, "centerY").equalsIgnoreCase("undefined")) {
				double lon = Double.parseDouble(getValueXML(element, "centerX"));
				double lat = Double.parseDouble(getValueXML(element, "centerY"));

				int centerX = (int)Math.round(1/this.resolution * (lon - this.left));
				int centerY = (int)Math.round(1/this.resolution * (this.top - lat));

				if(this.saveType != null && this.saveType.compareTo("typeArea") == 0) {
					centerX = (int) Math.round(centerX * this.areaResoultion);
					centerY = (int) Math.round(centerY * this.areaResoultion);
				}


				//라벨 색
				rgb = getValueXML(element, "fontColor");

				r = Integer.parseInt(getHexToDec(rgb.substring(1, 3)));
				g = Integer.parseInt(getHexToDec(rgb.substring(3, 5)));
				b = Integer.parseInt(getHexToDec(rgb.substring(5, 7)));

				graphics.setColor(new Color(r, g, b));

				String str = getValueXML(element, "label");

				int fontSize = 12;
				int offSetX = 15;
				int offSetY = 6;

				if(this.savePow != null) {
					//fontSize = fontSize * Integer.parseInt(this.savePow);
					//offSetX = offSetX * Integer.parseInt(this.savePow);
					//offSetY = offSetY * Integer.parseInt(this.savePow);
				}

				graphics.setFont(new Font("굴림", Font.PLAIN, fontSize));

				if(this.savePow != null)

				//라벨 그리기
				graphics.drawString(str, centerX-offSetX, centerY+offSetY);
			}
		}
	}

	protected void drawVectorPopups(Document doc, Graphics2D graphics) throws Exception {
		Iterator<?> itrLayer = doc.getDescendants(new ElementFilter("POPUPS"));

		while(itrLayer.hasNext()) {
			Content conPopups = (Content)itrLayer.next();

			if(conPopups instanceof Element) {
				Element element = (Element)conPopups;

				Iterator<?> itrPopup = element.getDescendants(new ElementFilter("POPUP"));
				while(itrPopup.hasNext()) {
					Content conPopup = (Content)itrPopup.next();
					if(conPopup instanceof Element) {
						Element elePopup = (Element)conPopup;
						double lon = Double.parseDouble(getValueXML(elePopup, "x"));
						double lat = Double.parseDouble(getValueXML(elePopup, "y"));
						String str = getValueXML(elePopup, "text");
						String fontFamily = getValueXML(elePopup, "fontFamily");
						int fontSize = Integer.parseInt(getValueXML(elePopup, "fontSize"));

						int x = (int)Math.round(1/this.resolution * (lon - this.left));
						int y = (int)Math.round(1/this.resolution * (this.top - lat));

						if(this.saveType != null && this.saveType.compareTo("typeArea") == 0) {
							x = (int) Math.round(x * this.areaResoultion);
							y = (int) Math.round(y * this.areaResoultion);
						}

						int width = Integer.parseInt(getValueXML(elePopup, "width"));
						int height = Integer.parseInt(getValueXML(elePopup, "height"));

						String[] strList = str.split("\r\n");

						int MaxSpaceCount = 0;
						for(int i=0; i < strList.length; i++) {
							String[] arrSpace = strList[i].split(" ");
							if(arrSpace.length > MaxSpaceCount) MaxSpaceCount = arrSpace.length;
						}

						width += Math.round(MaxSpaceCount * 0.2);

						String[] arrSpace = str.split(" ");

						if(fontFamily.equalsIgnoreCase("휴먼옛체")) {
							width += Math.round(arrSpace.length * 1);
						}
						else if(fontFamily.equalsIgnoreCase("신명조")) {
							width += width * -(arrSpace.length * 3);
						}
						else {
							width += Math.round(arrSpace.length * 0.2);
						}

						if(this.savePow != null) {
							width = width * Integer.parseInt(this.savePow);
							height = height * Integer.parseInt(this.savePow);
							fontSize = fontSize * Integer.parseInt(this.savePow);
						}

						graphics.setStroke(new BasicStroke((float)1));
						graphics.setColor(new Color(255, 255, 255));
						graphics.fillRect(x, y, width, height);
						graphics.setColor(new Color(0, 0, 0));
						graphics.drawRect(x, y, width, height);

						graphics.setFont(new Font(fontFamily, Font.PLAIN, fontSize));

						String rgb = getValueXML(element, "fontColor").replace("rgb(", "").replace(")", "");


						int r = Integer.parseInt(rgb.split(",")[0].trim());
						int g = Integer.parseInt(rgb.split(",")[1].trim());
						int b = Integer.parseInt(rgb.split(",")[2].trim());

						graphics.setColor(new Color(r, g, b));

						int leftBuffer = 4 + Math.round(fontSize/20);
						int bottomBuffer = 3 + Math.round(fontSize/5);

						for(int i=0; i < strList.length; i++) {
							int seq = strList.length-1 - i;
							graphics.drawString(strList[i], x+leftBuffer, Math.round(y+height-bottomBuffer-(seq*fontSize*1.2)));
						}
					}
				}

			}
		}
	}

	protected void drawMashupLayer(Document doc, Graphics2D graphics) throws Exception {
		Iterator<?> itrLayer = doc.getDescendants(new ElementFilter("MASHUPLAYER"));

		while(itrLayer.hasNext()) {
			Content conPopups = (Content)itrLayer.next();

			if(conPopups instanceof Element) {
				Element element = (Element)conPopups;
				if(element.getAttributeValue("type").equalsIgnoreCase("daum")){
					URL url = new URL(getValueXML(element, "url"));

					BufferedImage tempImage = ImageIO.read(url);

					graphics.drawImage(tempImage, 0, 0, this.width, this.height, null);
				} else if (element.getAttributeValue("type").equalsIgnoreCase("naver")) {
					String url= getValueXML(element, "url");
					int minX = Integer.parseInt(getValueXML(element, "minX"))  ;
					int maxX = Integer.parseInt(getValueXML(element, "maxX"));
					int minY = Integer.parseInt(getValueXML(element, "minY"));
					int maxY = Integer.parseInt(getValueXML(element, "maxY"));
					int w = maxX - minX +1;
					int h = maxY - minY +1;

					BufferedImage tempImage = new BufferedImage(256*w, 256*h, BufferedImage.TYPE_INT_RGB);
					Graphics2D tempGraphic = (Graphics2D) tempImage.getGraphics();
					for(int i=0;i<w;i++) {
						for(int j=0;j<h;j++) {
							tempGraphic.drawImage(ImageIO.read(new URL(url+(minX+i)+"-"+(maxY-j)+".png")), 256*i, 256*j, null);
						}
					}

					graphics.drawImage(tempImage.getSubimage((256*w-this.width*2)/2, (256*h-this.height*2)/2, this.width*2, this.height*2).getSubimage(this.width/2+109,this.height/2-63, this.width, this.height), 0, 0, this.width, this.height, null);
					/*tempImage.getSubimage(0, 0, this.width, this.height)*/
				}
			}
		}
	}

	/*else if(element.getAttributeValue("type").equalsIgnoreCase("daumMap")){
		URL url = new URL(getValueXML(element, "url"));

		URLConnection connection = url.openConnection();
		HttpURLConnection huc = (HttpURLConnection)connection;
		huc.setRequestMethod("POST");
		huc.setDoOutput(true);
		huc.setDoInput(true);
		huc.setUseCaches(false);
		huc.setDefaultUseCaches(false);
		PrintWriter out = new PrintWriter(huc.getOutputStream());
		out.println(params);
		out.close();

		BufferedImage tempImage = ImageIO.read(huc.getInputStream());

		graphics.drawImage(tempImage, 0, 0, this.width, this.height, null);
	}*/

	/**
	 * element 의 value 를 가져옴
	 * @param element
	 * @param searchString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected String getValueXML(Element element, String searchString) throws UnsupportedEncodingException {
		String result = "";
		Iterator<?> itrParams = element.getDescendants(new ElementFilter(searchString));
		while(itrParams.hasNext()) {
			Content conParams = (Content)itrParams.next();
			if(conParams instanceof Element) {
				Element layerElement = (Element)conParams;
				result = layerElement.getValue();
			}
		}
		return URLDecoder.decode(result, "UTF-8");
	}

	/**
	 * 16 진수를 10진수로 변환
	 * @param hex
	 * @return
	 */
	protected String getHexToDec(String hex) {
		long v = Long.parseLong(hex, 16);
		return String.valueOf(v);
	}

	/**
	 * 선의 각도를 구함
	 */
	protected double getAngle(int x1, int y1, int x2, int y2) {
		int dx = x2 - x1;
		int dy = y2 - y1;

		double rad = Math.atan2(dy, dx);
		double degree = (rad*180)/Math.PI;

		return degree;
	}

	protected double getRotatePointX(double x, double y, double angle) {
		double radian = Math.PI / 180;
		double newX = x * Math.cos((angle) * radian) - y * Math.sin((angle) * radian);
		return newX;
	}

	protected double getRotatePointY(double x, double y, double angle) {
		double radian = Math.PI / 180;
		double newY = x * Math.sin((angle) * radian) + y * Math.cos((angle) * radian);
		return newY;
	}

	public String encodingImgToBase64(BufferedImage image) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "png", byteArrayOutputStream);
		byteArrayOutputStream.flush();
		byte[] imageInByteArray = byteArrayOutputStream.toByteArray();
		byteArrayOutputStream.close();
		String base64 = Base64.encodeBase64String(imageInByteArray);
		return "data:image/png;base64,"+base64;
	}

	public BufferedImage resizeImages(BufferedImage image, int width, int height) throws Exception {
		Image imgTarget = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage returnImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int pixels[] = new int[width * height];
		PixelGrabber pixelGrabber = new PixelGrabber(imgTarget, 0, 0, width, height, pixels, 0, width);
		pixelGrabber.grabPixels();
		returnImage.setRGB(0, 0, width, height, pixels, 0, width);
		return returnImage;
	}

	/**
	 * 폴리곤 그림
	 */
	public BufferedImage drawPolygonSymbol(PolygonSymbolVO polygonSymbolVO)
			throws Exception {
		Integer width = polygonSymbolVO.getWidth()-1;
		Integer height = polygonSymbolVO.getHeight()-1;

		BufferedImage bi = new BufferedImage(polygonSymbolVO.getWidth(), polygonSymbolVO.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bi.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setColor(polygonSymbolVO.getFillColor());
		graphics.fillRect(0, 0, width, height);

		graphics.setColor(polygonSymbolVO.getColor());
		graphics.drawRect(0, 0, width, height);

		return bi;
	}
}

