package com.secretk.move.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***/
public class PatternUtils {
	/**英文字符串*/
	public static boolean isLetter(String str) {
		Pattern p = Pattern.compile("^[A-Za-z]+$");
		Matcher mt = p.matcher(str);
		return mt.matches();
	}

}
