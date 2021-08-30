package com.advisor.flight.utils;

public class StringManipulator {

	private StringManipulator() {
	}

	public static String removeQuotes(String s) {
		if (s.equals("")) {
			return s;
		}
		if (s.equals("\\N")) {
			return null;
		}
		return s.replaceAll("^\"|\"$", "");
	}

	public static Double preventNForDouble(String s) {
		if (s.equals("\\N")) {
			return null;
		}
		return Double.parseDouble(s);
	}

	public static Integer preventNForInteger(String s) {
		if (s.equals("\\N")) {
			return null;
		}
		return Integer.parseInt(s);
	}

	public static Long preventNForLong(String s) {
		if (s.equals("\\N")) {
			return null;
		}
		return Long.parseLong(s);
	}

}
