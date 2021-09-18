package com.util.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestFechas {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendarMesAnterior = Calendar.getInstance();
		//calendar.add(Calendar.YEAR, -1);
		System.out.println("calendar1: " + sdf.format(calendarMesAnterior.getTime()) + "\n");
		
		calendarMesAnterior.add(Calendar.MONTH, -1);
		System.out.println("calendar2: " + sdf.format(calendarMesAnterior.getTime()) + " \nultimo dia: " + calendarMesAnterior.getMaximum(Calendar.DAY_OF_MONTH));
		
		System.out.println("\n\nMENSUAL");
		calendarMesAnterior.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println("INICIO: " + sdf.format(calendarMesAnterior.getTime()));
		
		calendarMesAnterior.set(Calendar.DAY_OF_MONTH, calendarMesAnterior.getMaximum(Calendar.DAY_OF_MONTH));
		System.out.println("FIN: " + sdf.format(calendarMesAnterior.getTime()));

		calendarMesAnterior.add(Calendar.MONTH, 2);
		System.out.println("FIN MENSUAL: " + sdf.format(calendarMesAnterior.getTime()));
		
		calendarMesAnterior.add(Calendar.MONTH, 2);
		System.out.println("FIN BIMESTRAL: " + sdf.format(calendarMesAnterior.getTime()));
		
		/*System.out.println("\n\nBIMESTRAL");
		Calendar calendarBi = Calendar.getInstance();
		calendarBi.add(Calendar.MONTH, -2);
		calendarBi.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println("INICIO: " + sdf.format(calendarBi.getTime()));
		
		calendarBi.set(Calendar.DAY_OF_MONTH, calendarMesAnterior.getMaximum(Calendar.DAY_OF_MONTH));
		System.out.println("FIN: " + sdf.format(calendarBi.getTime()));
		
		System.out.println("\n\nTRIMESTRAL");
		Calendar calendarTri = Calendar.getInstance();
		calendarTri.add(Calendar.MONTH, -3);
		calendarTri.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println("INICIO: " + sdf.format(calendarTri.getTime()));
		
		calendarTri.set(Calendar.DAY_OF_MONTH, calendarMesAnterior.getMaximum(Calendar.DAY_OF_MONTH));
		System.out.println("FIN: " + sdf.format(calendarTri.getTime()));*/
	}

}
