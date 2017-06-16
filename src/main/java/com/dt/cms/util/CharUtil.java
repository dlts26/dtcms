package com.dt.cms.util;

/**
 * 字符处理工具类
 * 
 * @author bennyyue
 *
 */
public class CharUtil {

	/**
	 * 全角转换成半角字符
	 * 
	 * @param c
	 * @return
	 */
	public static char toDBCCase(char c) {
		if (c == 12288)
			return (char) 32;
		if (c >= 65281 && c <= 65374)
			return (char) (c - 65248);
		return c;
	}

	// 判断是不是中文
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
}
