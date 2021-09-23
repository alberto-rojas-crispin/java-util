package com.util.tasks;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
*
* @author ALBERT ROJAS
* @date 10/07/2020
* @version 1.0 @category
*
*/
public class GeneradorInsertsSql {
	private static final String RUTA_PROPERTIES = "C:\\Test\\properties\\paramGenerardoInserts.properties";
	private static final String RUTA_SQL_GENERADO = "C:\\Test\\properties\\sqlGenerado.sql";
	
	private static final String PROP_GENERAR_ARCHIVO_SQL = "generarArchivoSQL";
	private static final String PROP_QUERY 		= "query";
	private static final String PROP_NIVEL_LOOP 	= "nivel_loop";
	private static final String PROP_LOOP_NIVEL_UNO 	= "loop_nivel_1";
	private static final String PROP_LOOP_NIVEL_DOS 	= "loop_nivel_2";
	private static final String PROP_LOOP_NIVEL_TRES 	= "loop_nivel_3";
	private static final String PROP_LOOP_NIVEL_CUATRO 	= "loop_nivel_4";
	private static final String PROP_LOOP_NIVEL_CINCO 	= "loop_nivel_5";
	private static final String PROP_LOOP_NIVEL_SEIS 	= "loop_nivel_6";
	private static final String PROP_LOOP_NIVEL_SIETE 	= "loop_nivel_7";
	
	private static final String PROP_COLUMNAS_TABLA_NOMBRE 	= "columnas_nombre";
	private static final String PROP_COLUMNAS_TABLAS_INFO 	= "columnas_valor";
	private static final String PARAM_QUERY_COLUMNAS 		= "#COLUMNAS#";
	private static final String PARAM_QUERY_INFO_COLUMNAS 	= "#INFO_COLUMNAS#";
	
	private static final String SEPARADOR_COMA = ",";
	private static final String SEPARADOR_NUMERAL = "#";
	public static final String NEW_LINE_SEPARATOR = "\n";
	private static final String EXITO = "Exito";
	private static final String ERROR = "Error";
	
	private static final String RESPUESTA_SI_ARCHIVO_SQL = "SI";

	private static final String  MENSAJE_ERROR = "Error en el procesamiento";

	private static List<String> QUERYS = new ArrayList<String>();
	
	public static void main(String[] args) {
		try {
			Properties p = new Properties();
			p.load(new FileReader(RUTA_PROPERTIES));
			
			Boolean generarArchivoSQL = false;
			String propGenerarArchivoSql = p.getProperty(PROP_GENERAR_ARCHIVO_SQL);
			if(propGenerarArchivoSql != null && propGenerarArchivoSql.equals(RESPUESTA_SI_ARCHIVO_SQL)) {
				generarArchivoSQL = true;
			}

			String propNivelLoop = p.getProperty(PROP_NIVEL_LOOP);
			Integer nivelLoop = Integer.parseInt(propNivelLoop);
			if(nivelLoop < 2 || nivelLoop > 7) {
				System.out.println("El nivel de loop tiene que ser mayor a 1 y menor que 8");
				System.exit(0);
			}
			List<String> listNivelProcesar = new ArrayList<String>();
			String resProcess = "";
			switch (nivelLoop) {
			case 2:
				resProcess = procesaLoopNivelDos(p);
				if(generarArchivoSQL) {
					try {
						generarArchivo(QUERYS);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("ERROR AL GENERAR EL ARCHIVO SQL");
					}
				}
				for (String query : QUERYS) {
					System.out.println(query);
				}
				break;
			case 3:
				procesaLoopNivelDos(p);
				listNivelProcesar.add(PROP_LOOP_NIVEL_TRES);
				resProcess = procesaLoopNivelN(p, listNivelProcesar, generarArchivoSQL);
				break;
			case 4:
				procesaLoopNivelDos(p);
				listNivelProcesar.add(PROP_LOOP_NIVEL_TRES);
				listNivelProcesar.add(PROP_LOOP_NIVEL_CUATRO);
				resProcess = procesaLoopNivelN(p, listNivelProcesar, generarArchivoSQL);
				break;
			case 5:
				procesaLoopNivelDos(p);
				listNivelProcesar.add(PROP_LOOP_NIVEL_TRES);
				listNivelProcesar.add(PROP_LOOP_NIVEL_CUATRO);
				listNivelProcesar.add(PROP_LOOP_NIVEL_CINCO);
				resProcess = procesaLoopNivelN(p, listNivelProcesar, generarArchivoSQL);
				break;
			case 6:
				procesaLoopNivelDos(p);
				listNivelProcesar.add(PROP_LOOP_NIVEL_TRES);
				listNivelProcesar.add(PROP_LOOP_NIVEL_CUATRO);
				listNivelProcesar.add(PROP_LOOP_NIVEL_CINCO);
				listNivelProcesar.add(PROP_LOOP_NIVEL_SEIS);
				resProcess = procesaLoopNivelN(p, listNivelProcesar, generarArchivoSQL);
				break;
			case 7:
				procesaLoopNivelDos(p);
				listNivelProcesar.add(PROP_LOOP_NIVEL_TRES);
				listNivelProcesar.add(PROP_LOOP_NIVEL_CUATRO);
				listNivelProcesar.add(PROP_LOOP_NIVEL_CINCO);
				listNivelProcesar.add(PROP_LOOP_NIVEL_SEIS);
				listNivelProcesar.add(PROP_LOOP_NIVEL_SIETE);
				resProcess = procesaLoopNivelN(p, listNivelProcesar, generarArchivoSQL);
				break;
			default:
				resProcess = ERROR;
				break;
			}
			
			switch (resProcess) {
			case ERROR:
				System.out.println(MENSAJE_ERROR);
				break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String procesaLoopNivelDos(Properties p) {
		try {
			String propNombreColumnas = p.getProperty(PROP_COLUMNAS_TABLA_NOMBRE);
			String propInfoColumnas = p.getProperty(PROP_COLUMNAS_TABLAS_INFO);
			String propQuery = p.getProperty(PROP_QUERY);
			
			String propLoopNivelUno = p.getProperty(PROP_LOOP_NIVEL_UNO);
			String[] arrayLoopNivelUno = propLoopNivelUno.split(SEPARADOR_COMA);
			
			for (String valorNivelUno : arrayLoopNivelUno) {
				String propLoopNivelDos = p.getProperty(PROP_LOOP_NIVEL_DOS);
				
				String[] arrayLoopNivelDos = propLoopNivelDos.split(SEPARADOR_COMA);
				
				for (String valorNivelDos : arrayLoopNivelDos) {
					String[] arrayInfoColumnas = propInfoColumnas.split(SEPARADOR_COMA);
					String infoColumnas = "";
					int numColumnaActual = 0;
					for (String infoColumna : arrayInfoColumnas) {
						numColumnaActual++;
						String _infoColumn = infoColumna; 
						if(infoColumna.contains(SEPARADOR_NUMERAL + PROP_LOOP_NIVEL_UNO + SEPARADOR_NUMERAL)) {
							_infoColumn = infoColumna.replace(SEPARADOR_NUMERAL + PROP_LOOP_NIVEL_UNO + SEPARADOR_NUMERAL, valorNivelUno);
						}
						if(infoColumna.contains(SEPARADOR_NUMERAL + PROP_LOOP_NIVEL_DOS + SEPARADOR_NUMERAL)) {
							_infoColumn = infoColumna.replace(SEPARADOR_NUMERAL + PROP_LOOP_NIVEL_DOS + SEPARADOR_NUMERAL, valorNivelDos);
						}
						infoColumnas += _infoColumn;
						
						if(numColumnaActual < arrayInfoColumnas.length) {
							infoColumnas += SEPARADOR_COMA;
						}
					}
					
					String strQuery = propQuery.replace(PARAM_QUERY_COLUMNAS, propNombreColumnas);
					strQuery = strQuery.replace(PARAM_QUERY_INFO_COLUMNAS, infoColumnas);
					
					QUERYS.add(strQuery);
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return EXITO;
	}
	
	private static String procesaLoopNivelN(Properties p, List<String> listNivelProcesar, Boolean generarArchivo) {
		if(listNivelProcesar.size() == 0) {
			return ERROR;
		}
		
		List<String> listaQuerysActualizada = QUERYS;
		for (String nivelProcesar : listNivelProcesar) {
			listaQuerysActualizada = procesaNivelEspecifico(p, listaQuerysActualizada, nivelProcesar);
			
			if(listaQuerysActualizada.size() >= QUERYS.size()) {
				continue;
			}else {
				break;
			}
			
		}
		
		if(listaQuerysActualizada.size() < QUERYS.size()) {
			return ERROR;
		}
		if(generarArchivo) {
			try {
				generarArchivo(listaQuerysActualizada);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERROR AL GENERAR EL ARCHIVO SQL");
			}
		}
		
		for (String query : listaQuerysActualizada) {
			System.out.println(query);
		}
		
		return EXITO;
	}
	
	private static void generarArchivo(List<String> listaQuerysActualizada) throws Exception {
		FileWriter fileWriter = null;
		try {
			
			fileWriter = new FileWriter(RUTA_SQL_GENERADO);
			
			for (String query : listaQuerysActualizada) {
				fileWriter.append(query);
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			
		} catch (Exception e) {
			throw e;
			
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				throw e;
			}
		}
		
	}

	private static List<String> procesaNivelEspecifico(Properties p, List<String> querys, String nivelProcesar) {
		List<String> listaQuerysNueva = new ArrayList<String>();
		
		try {
			String propLoopNivelProcesar = p.getProperty(nivelProcesar);
			String[] arrayLoopNivelProcesar = propLoopNivelProcesar.split(SEPARADOR_COMA);
			
			for (String queryProcesar : querys) {
				
				for (String valorNivelProcesar : arrayLoopNivelProcesar) {
					String _infoColumn = ""; 
					if(queryProcesar.contains(SEPARADOR_NUMERAL + nivelProcesar + SEPARADOR_NUMERAL)) {
						_infoColumn = valorNivelProcesar;
					}
						
					String  strQuery = queryProcesar.replace(SEPARADOR_NUMERAL + nivelProcesar + SEPARADOR_NUMERAL, _infoColumn);
					
					listaQuerysNueva.add(strQuery);
					
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaQuerysNueva;
	}
}
