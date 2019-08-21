package kr.go.gg.gpms.map.service;

import java.awt.image.BufferedImage;

import kr.go.gg.gpms.map.service.model.PolygonSymbolVO;

public interface MapService {
	
	public BufferedImage createImages(String pStr) throws Exception;
	
	public void setType(String type);
	
	public void setRootPath(String rootPath);
	
	public String encodingImgToBase64(BufferedImage image) throws Exception;
	
	public BufferedImage resizeImages(BufferedImage image, int width, int height) throws Exception;
	
	public BufferedImage drawPolygonSymbol(PolygonSymbolVO polygonSymbolVO) throws Exception;
}
