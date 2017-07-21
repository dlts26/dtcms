package com.dt.cms.util;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;


/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author 岳海亮
 * @date 2017年7月18日
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public static String lowerFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	public static String upperFirst(String str){
		if(StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
	}
		
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}

	/**
	 * 删除URL 请求中特定 的请求参数
	 * 
	 * @param queryStr
	 *            //get 方式请求字符串
	 * @param paramName
	 *            //要删除的请求参数
	 * 
	 */
	public static String removeRequestParameter(String queryStr, String paramName) {
		if (queryStr == null || paramName == null || queryStr.length() == 0 || paramName.length() == 0)
			return "";
		String paramStr = paramName + "=";

		if (queryStr.indexOf(paramStr) == 0) {
			// 如果请求参数在第一个，并且还有其它请求参数
			if (queryStr.indexOf("&") > 0) {
				queryStr = queryStr.substring(queryStr.indexOf("&") + 1);
			} else {
				// 如果只有要删除的请求参数
				queryStr = "";
			}
		} else if (queryStr.indexOf(paramStr) > 0) {
			// 暂存从请求参数所在位置起始的字串
			String temp = queryStr.substring(queryStr.indexOf(paramStr));
			if (temp.indexOf("&") >= 0) {
				// 如果请求参数不在最后一个且不为第一个，则用请求参数之前的字串+请求参数之后的字串
				queryStr = queryStr.substring(0, queryStr.indexOf(paramStr) - 1) + temp.substring(temp.indexOf("&"));
			} else {
				// 如果请求参数为最后一个，则截取该参数之前的字串
				queryStr = queryStr.substring(0, queryStr.indexOf(paramStr) - 1);
			}
		} else {
			// 不做处理 queryStr = queryStr;
		}
		return queryStr;
	}

	public static boolean isDotStr(String input) {
		if (input == null)
			return true;
		String trimInput = input.trim();
		int len = trimInput.length();
		for (int i = 0; i < len; i++) {
			char c = trimInput.charAt(i);
			if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) {
				continue;
			}
			if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.SPACING_MODIFIER_LETTERS) {
				continue;
			}
			if (!Character.isLetterOrDigit(c)) {
				continue;
			}
			return false;
		}
		return true;
	}

	/**
	 * 16进制转 10 进制
	 */
	public long xh2int(String hxString) {
		return Long.parseLong(hxString, 16);
	}

	/**
	 * 给定位数的16进制转数的最大值
	 * 
	 * @param hxStrLen
	 *            16进制数位数
	 */
	public double maxValueOfXHLenth(int hxStrLen) {
		return Math.pow(16, hxStrLen) - 1;
	}

	/**
	 * 判断字符串是否为空:true->为空串
	 * 
	 * @return null 或长度为0 返回true 否则返回false
	 */
	public static boolean isEmpty(String source) {
		return source == null || source.trim().isEmpty();
	}

	/**
	 * 判断字符串是否为正整数值 如果串为空则
	 */
	public static boolean isPositiveNumber(String numberStr) {
		return ("" + numberStr).matches("\\d{1,}");
	}

	/**
	 * 将字符串转为整数 如果串为空则
	 */
	public static int getIntValue(String numberStr) {
		return Integer.parseInt(numberStr);
	}

	/**
	 * 将字符串转为整数 如果串为空则
	 */
	public static float getFlaotValue(String falatStr) {
		return Float.parseFloat(falatStr);
	}

	/**
	 * 将字符串转为整数 如果串为空则
	 */
	public static long getLongValue(String longStr) {
		return Long.parseLong(longStr);
	}

	/**
	 * 删除输入串中的表情符号，mysql入库需要
	 * 
	 * @param input
	 * @return
	 */
	public static String removeEmoji(String input) {
		Pattern p_emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
				Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		Matcher m_emoji = p_emoji.matcher(input);
		input = m_emoji.replaceAll(""); // 过滤空格回车标签
		return input;

	}

	/**
	 * Method getCurTimeStamp.
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getCurTimeStamp() {
		long current = (new java.util.Date()).getTime();
		// java.util.Date curDate = new java.util.Date(current);
		Timestamp cT = new Timestamp(current);
		return cT;
	}

	/**
	 * 是否为数字（包含小数）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || str.equals(""))
			return false;
		Pattern pattern = Pattern.compile("-?[0-9]+.*[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断字符串是否为英文字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isENString(String str) {
		Pattern pattern = Pattern.compile("[a-zA-Z]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) str);
		return matcher.matches();
	}

	public static boolean isZero(String str) {
		Pattern pattern = Pattern.compile("^0+(\\.0+)?$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 将字符串转为数值型
	 */
	public static Number str2Numer(String numberStr, Class<?> numberType) {
		try {
			if ("java.lang.Long".equals(numberType.getName())) {
				return new Long(numberStr);
			} else if ("java.lang.Integer".equals(numberType.getName())) {
				return new Integer(numberStr);
			} else if ("java.lang.Float".equals(numberType.getName())) {
				return new Float(numberStr);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 截取中文字符串 中字符按两个计算，其它按一个计算
	 * 
	 * @param offset
	 *            起始位置
	 * @param length
	 *            截取长度
	 * @return 源字符串
	 */
	public static String subString(int offset, int length, String srcStr) {
		if (offset < 0)
			offset = 0;
		if (length < 0)
			return srcStr;
		if (srcStr == null)
			return "";
		StringBuilder sb = new StringBuilder();
		double valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		// 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
		String lastStr = "";
		boolean addLast = true;
		for (int i = 0; i < srcStr.length(); i++) {
			// 获取一个字符
			String temp = srcStr.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
				// 中文字符长度为2
				valueLength += 2;
			} else {
				// 其他字符长度为1
				valueLength += 1;
			}
			if (valueLength > offset && valueLength < offset + length) {
				if (addLast) {
					sb.append(lastStr);
					addLast = false;
				}
				sb.append(temp);
			}
			if (valueLength >= offset + length) {
				return sb.append("...").toString();
			}

			if (!addLast)
				lastStr = temp;
		}
		// 进位取整
		return sb.toString();
	}

	/**
	 * 获取字符串的长度，中文占两个字符,英文数字占一个字符
	 * 
	 * @param value
	 *            指定的字符串
	 * @return 字符串的长度
	 */
	public static double length(String value) {
		double valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		// 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
		for (int i = 0; i < value.length(); i++) {
			// 获取一个字符
			String temp = value.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
				// 中文字符长度为2
				valueLength += 2;
			} else {
				// 其他字符长度为1
				valueLength += 1;
			}
		}
		// 进位取整
		return Math.ceil(valueLength);
	}

	public static String getChinese(String input) {
		StringBuffer sb = new StringBuffer();
		String chinese = "[\u4e00-\u9fa5]";
		// 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
		for (int i = 0; i < input.length(); i++) {
			// 获取一个字符
			String temp = input.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
				sb.append(temp);
			}
		}
		return sb.toString();
	}

	public static boolean containsDot(String input) {
		if (input == null)
			return false;
		String trimInput = input.trim();
		int len = trimInput.length();
		for (int i = 0; i < len; i++) {
			char c = trimInput.charAt(i);
			if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) {
				return true;
			}
			if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.SPACING_MODIFIER_LETTERS) {
				return true;
			}
			if (!Character.isLetterOrDigit(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 去除字符串中的标点符号
	 * 
	 * @param input
	 * @return
	 */
	public static String removeDots(String input) {
		if (input == null)
			return null;
		String trimInput = input.trim();
		int len = trimInput.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = trimInput.charAt(i);
			if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION)
				continue;
			if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.SPACING_MODIFIER_LETTERS)
				continue;
			if (!Character.isLetterOrDigit(c))
				continue;
			sb.append(c);

		}
		return sb.toString();
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 * @return
	 */
	public static String toDBCCase(String input) {
		if (input == null)
			return null;
		String trimInput = input.trim();
		StringBuilder sb = new StringBuilder(trimInput.length());
		for (int i = 0; i < trimInput.length(); i++) {
			char c = trimInput.charAt(i);
			sb.append(CharUtil.toDBCCase(c));
		}
		return sb.toString();
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 * @return
	 */
	public static String toDBCCaseKeepCJK(String input) {
		if (input == null)
			return null;
		String trimInput = input.trim();
		StringBuilder sb = new StringBuilder(trimInput.length());
		for (int i = 0; i < trimInput.length(); i++) {
			char c = trimInput.charAt(i);
			if (c == '，') {
				sb.append(c);
				continue;
			}
			if (c == '；') {
				sb.append(c);
				continue;
			}
			if (c == '。') {
				sb.append(c);
				continue;
			}
			if (c == '！') {
				sb.append(c);
				continue;
			}
			if (c == '、') {
				sb.append(c);
				continue;
			}
			sb.append(CharUtil.toDBCCase(c));
		}
		return sb.toString();
	}

	/**
	 * 计算短语文本A与短语文本B之间的相似度
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static float similarity(String A, String B) {
		return (sc(A, B) + sc(B, A)) / 2;
	}

	/**
	 * 计算短文本A相对于短语文本B的相似度sc
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	private static float sc(String A, String B) {
		float sc = 0.0f;
		for (int i = 0; i < A.length(); i++) {
			sc += cc(A, i, B);
		}
		sc /= A.length();
		return sc;
	}

	/**
	 * 计算匹配文字Ai对整体相似度的贡献量
	 * 
	 * @param A
	 * @param i
	 * @param B
	 * @return
	 */
	private static float cc(String A, int i, String B) {
		return (B.length() - posOffset(A, i, B)) / (float) B.length();
	}

	/**
	 * 计算匹配文件Ai的最小匹配偏移值posOffset
	 * 
	 * @param A
	 * @param i
	 * @param B
	 * @return
	 */
	private static int posOffset(String A, int i, String B) {
		int posOffset = B.length();
		for (int j = 0; j < B.length(); j++) {
			if (i - j >= 0 && i - j < posOffset && A.charAt(i) == B.charAt(i - j))
				return j;
			if (i + j < posOffset && A.charAt(i) == B.charAt(i + j))
				return j;
		}
		return posOffset;
	}

	public static String toNormal(String input) {
		input = toDBCCase(input);
		if (input == null)
			return null;
		return input.toLowerCase();
	}

	public static String toShow(String input) {
		return toDBCCase(input);
	}

	public static List<String> spiltSentence(String document) {
		List<String> sentences = new ArrayList<String>();
		for (String line : document.split("[\r\n]")) {
			line = line.trim();
			if (line.length() == 0)
				continue;
			for (String sent : line.split("[，,。:：“”？?！!；;]")) {
				sent = sent.trim();
				if (sent.length() == 0)
					continue;
				sentences.add(sent);
			}
		}
		return sentences;
	}

	public static boolean containsCNStr(String input) {
		Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher matcher = pattern.matcher((CharSequence) input);
		return matcher.find();
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {

		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);

		return returnString;
	}

	
	/**
	 * 删除所有空格字符
	 * @param result
	 * @return
	 */
	public static String removeSpace(String result) {
		result = result.replaceAll("[\t|　| |\n]", "");
		return result;
	}

}
