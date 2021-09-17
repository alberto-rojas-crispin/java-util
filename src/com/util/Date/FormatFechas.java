package com.util.Date;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatFechas {
	public static void main(String[] args) {

		Date fecha = new Date();
		// ddmmaa_hhmm
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");

		System.out.println("Fecha: " + sdf.format(fecha));

		// DecimalFormat df = new DecimalFormat("###,###,###,##0.000000");

		// System.out.println(df.format(12232323988.2390));

		DecimalFormat df = new DecimalFormat("###,###,###,###");

		System.out.println(df.format(12232323988.5390));

		DecimalFormat formateador = new DecimalFormat("###,###,###");
		System.out.println(formateador.format(1000));
		System.out.println(formateador.format(100000.78));

		String str = "123,133,223.00";
		System.out.println("str: " + str.replaceAll(",", ""));

	}
}
