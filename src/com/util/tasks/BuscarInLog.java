/***********************************
* Copyright (c) 2019.
* All rights reserved.
************************************/

package com.util.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author AR998040
 * @date 02/09/2019
 * @version 1.0 @category
 *
 */
public class BuscarInLog {
	/**
	 * @author AR998040
	 * @date 02/09/2019
	 * @version 1.0
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		List<String> lstResumen = new ArrayList<String>();
		try {
			if (args == null || args.length == 0) {
				System.out.println("No se esta recibiendo ningun parametro");
				System.exit(0);
			}
			if (args[0] == null) {
				System.out.println("No se esta recibiendo el patron a buscar");
				System.exit(0);
			}
			if (args.length < 2) {
				System.out.println("No se esta recibiendo el parametro: ruta de archivo");
				System.exit(0);
			}
			if (args[1] == null) {
				System.out.println("No se esta recibiendo la ruta del archivo donde se buscara");
				System.exit(0);
			}

			String path = args[1]; // "C:\\Test\\log\\SystemOut.log";//SystemOut.log
			if (!new File(path).isFile()) {
				System.out.println("El parametro ruta de archivo no es correcto");
				System.exit(0);
			}

			String strFicheroSalida = args[2] != null ? args[2] : "C:\\dev\\test\\logs\\ficheroSalidaDefault.txt";
			System.out.println("\n\nBusqueda iniciada, por favor espere...");
			try {
				Date fechaInicio = new Date();
				String patronBuscar = args[0];
				Long numeroLinea = 0L;
				String servicioError = "";
				Long lineaServicio = 0L;
				inputStream = new FileInputStream(path);
				sc = new Scanner(inputStream, "UTF-8");
				while (sc.hasNextLine()) {
					numeroLinea++;
					String line = sc.nextLine();
					if (line.contains("http://wasnci.profuturo.mx:8000")) {
						//System.out.println("URL:" + line);
						servicioError = line;
						lineaServicio = numeroLinea;
					}
					if (line.contains(patronBuscar)) {
						//System.out.println(line + " ::: linea: " + (numeroLinea));
						lstResumen.add("Servicio: " + servicioError + " ::: linea: " + lineaServicio);
						lstResumen.add("	" + line + " ::: linea: " + numeroLinea + "\n");
					}
				}
				// note that Scanner suppresses exceptions
				if (sc.ioException() != null) {
					throw sc.ioException();
				}

				generarArchivoResumen(patronBuscar, lstResumen, fechaInicio, strFicheroSalida);
				
				System.out.println("\n\nEl proceso ha finalizado, revise el detalle en: " + strFicheroSalida);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}

	}

	/**
	 * @author AR998040
	 * @date 18/12/2019
	 * @version 1.0
	 * @param lstResumen
	 */
	private static void generarArchivoResumen(String patronBuscar, List<String> lstResumen, Date fechaInicio,
			String strFicheroSalida) {
		Date fechaFin = new Date();
		/*System.out.println("\n\n######################## R E S U M E N ########################");
		System.out.println("Hora inicio:" + fechaInicio);
		System.out.println("Hora fin:" + fechaFin + "\n\n");
		if (lstResumen.size() == 0) {
			System.out.println("No se encontro nada relacionado con:" + patronBuscar);
		}
		for (String linea : lstResumen) {
			System.out.println(linea);
		}
		System.out.println("###############################################################");*/

		FileWriter fichero = null;
		PrintWriter pw = null;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		try {
			fichero = new FileWriter(strFicheroSalida);
			pw = new PrintWriter(fichero);

			pw.println("######################## R E S U M E N ########################");
			
			pw.println("Hora inicio: " + sdf.format(fechaInicio));
			pw.println("Hora fin: " + sdf.format(fechaFin) + "\n\n");
			if (lstResumen.size() == 0) {
				pw.println("No se encontro nada relacionado con: " + patronBuscar + "\n\n");
			}
			for (String linea : lstResumen) {
				pw.println(linea);
			}
			pw.println("###############################################################");
			
			/*for (int i = 0; i < 10; i++)
				pw.println("Linea " + i);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
}
