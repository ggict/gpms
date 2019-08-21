package kr.go.gg.gpms.map.service.model;

import java.awt.Color;

public class LineSymbolVO extends SymbolVO {
	private Color color;
	private String strColor;
	private Integer arrow;
	private Integer marker;

	public Integer getMarker() {
		return marker;
	}

	public void setMarker(Integer marker) {
		this.marker = marker;
	}

	public Integer getArrow() {
		return arrow;
	}

	public void setArrow(Integer arrow) {
		this.arrow = arrow;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	protected String getHexToDec(String hex) {
		long v = Long.parseLong(hex, 16);
		return String.valueOf(v);
	}

	public String getStrColor() {
		return strColor;
	}

	public void setStrColor(String strColor) {
		Integer r = Integer.parseInt(getHexToDec(strColor.substring(0, 2)));
		Integer g = Integer.parseInt(getHexToDec(strColor.substring(2, 4)));
		Integer b = Integer.parseInt(getHexToDec(strColor.substring(4, 6)));
		this.color = new Color(r, g, b);
		
		this.strColor = strColor;
	}
}
