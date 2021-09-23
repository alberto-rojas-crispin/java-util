/***********************************
* Copyright (c) 2019.
* All rights reserved.
************************************/
package com.util.tasks;

import java.io.File;
import java.util.regex.Pattern;

/**
 *
 * @author AR998040 
 * @date 13/09/2019 
 * @version 1.0 @category
 *
 */
public class ConvetirUrlWSX {
	/**
	 * @author AR998040
	 * @date 13/09/2019
	 * @version 1.0
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "C:WSXXTestWSXcsvWSX";
		String[] strPart = str.split(Pattern.quote("WSX"));
		
		StringBuffer rutaFinal = new StringBuffer();
		for (int i = 0; i < strPart.length; i++) {
			System.out.println("strPart[i]: " + strPart[i]);
			rutaFinal.append(strPart[i] + File.separator);
		}
		System.out.println("UR FINAL:" + rutaFinal.toString());

	}
}
