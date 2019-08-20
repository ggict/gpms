package kr.go.gg.gpms.map.service.model;

import java.awt.Color;

public class PolygonSymbolVO extends LineSymbolVO {
	private Color fillColor;
	private String strFillColor;
	
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public String getStrFillColor() {
		return strFillColor;
	}
	public void setStrFillColor(String strFillColor) {
		Integer r = Integer.parseInt(getHexToDec(strFillColor.substring(0, 2)));
		Integer g = Integer.parseInt(getHexToDec(strFillColor.substring(2, 4)));
		Integer b = Integer.parseInt(getHexToDec(strFillColor.substring(4, 6)));
		this.fillColor = new Color(r, g, b);
		
		this.strFillColor = strFillColor;
	}
}
