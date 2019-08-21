package egovframework.cmmn.util;

public class NumberUtil {

	public static int parseInt(Object str) throws Exception {
		int result = 0;
		if (str != null) {
			try {
				result = Integer.parseInt((String) str);
			} catch (Exception e) {
				throw new Exception(e);
			}
		}

		return result;
	}

	public static int parseInt(Object str, int defValue) throws Exception {
		int result = defValue;
		if (str != null) {
			try {
				result = Integer.parseInt((String) str);
			} catch (Exception e) {
				throw new Exception(e);
			}
		}

		return result;
	}

	public static Double parseDouble(Object str) throws Exception {
		Double result = 0d;
		if (str != null) {
			try {
				result = Double.parseDouble(str.toString());
			} catch (Exception e) {
				throw new Exception(e);
			}
		}

		return result;
	}
}
