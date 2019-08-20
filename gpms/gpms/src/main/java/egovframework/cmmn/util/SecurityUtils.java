package egovframework.cmmn.util;

public class SecurityUtils {
	public static String fileSecurity1(Object str) {
		String s = "";
		if (str == null) {
			return s;
		} else {
			s = str.toString();
			s = s.replaceAll("/", "");
			s = s.replaceAll("\\\\", "");
			s = s.replaceAll("\\.", "");
			s = s.replaceAll("&", "");
		}
		return s;
	}

	public static String pathSecurity(String str) {
		String s = str;
		//보안 취약점 개선 --> 없앰
		if (s != null && !"".equals(s) ) {
			s = s.replaceAll("/", "");
			s = s.replaceAll("\\\\", "");
			//s = s.replaceAll("[.]", "");
			s = s.replaceAll("&", "");
		}
		return s;
	}
}
