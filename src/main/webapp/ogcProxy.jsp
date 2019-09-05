<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page session="false"%>
<%@page import="java.net.*,java.io.*" %>
<%
try {
	String reqUrl = request.getQueryString();
	reqUrl = java.net.URLDecoder.decode(reqUrl);
	
	URL url = new URL(reqUrl);
	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	//con.setConnectTimeout(1000*30);
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
	
	out.clear();
	out = pageContext.pushBody();
	OutputStream ostream = response.getOutputStream();
	response.setContentType(con.getContentType());
	InputStream in = con.getInputStream();
	final int length = 5000;
	byte[] bytes = new byte[length];
	int bytesRead = 0;
	while ((bytesRead = in.read(bytes, 0, length)) > 0) {
		ostream.write(bytes, 0, bytesRead);
	}

} catch (IOException e) {
	//System.out.println("error: " + e);
	int no = 0;
}
%>
