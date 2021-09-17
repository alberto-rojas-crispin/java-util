package com.util;

import java.text.Normalizer;

public class Acentos {
	public static void main(String[] args) {
		System.out.println("En Ejecuci\u00F3n");
		
		String cadena = "métodos  crispín transformación ";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "Esta transformación transforma los caracteres con tildes y diacríticos separandolos en dos carac";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "áéíóú";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "àèìòù";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "äëïöü";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "âêîôû";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "2² + 2³";
		System.out.println(cadena + " = " + cleanString(cadena));
	}

	public static String cleanString(String texto) {
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return texto;
		
		
	}
}
