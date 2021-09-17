package com.util.String;

import java.math.BigDecimal;

public class ConversionStringToBigDecimal {
	public static void main(String[] args) {

		String s = "$12,655,454,899,800.00";

		String str = s.replace("$", "").replaceAll(",", "").trim();
		System.out.println(str + "\n\n");
		BigDecimal x = new BigDecimal(str);
		System.out.println("\n\n" + x);

	}
}
