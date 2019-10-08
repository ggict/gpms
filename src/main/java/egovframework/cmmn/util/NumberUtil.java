package egovframework.cmmn.util;

import java.math.BigDecimal;

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

	/**
	 * PostgreSQL numeric 타입 데이터 관련 유틸 - 소수점 뒤에 붙은 0을 제거
	 * NumberUtils.stripTrailingZeros(1234.5670000000d) = "1234.567"
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String stripTrailingZeros(Object obj) throws Exception {
		
		if (obj instanceof String) {
			return String.valueOf(obj);
			
		} else if (obj instanceof BigDecimal) {
			Double bigDecimalSrc = parseDouble(obj);
			if (bigDecimalSrc != 0d) {
				return BigDecimal.valueOf(bigDecimalSrc).stripTrailingZeros().toPlainString();
			} else {
				return "0";
			}
			
		} else if (obj instanceof Double) {
			Double doubleSrc = parseDouble(obj);
			if (doubleSrc != 0d) {
				return BigDecimal.valueOf(doubleSrc).stripTrailingZeros().toPlainString();
			} else {
				return "0";
			}
			
		} else if (obj instanceof Integer) {
			return String.valueOf(obj);
		}
		
		return "0";
	}
}
