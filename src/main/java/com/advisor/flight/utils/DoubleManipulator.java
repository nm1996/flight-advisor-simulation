package com.advisor.flight.utils;

public class DoubleManipulator {

	private DoubleManipulator() {
	}

	public static Double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		double result = (double) tmp / factor;
		return Double.valueOf(result);
	}
}
