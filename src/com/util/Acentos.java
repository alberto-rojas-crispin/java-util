package com.util;

import java.text.Normalizer;

public class Acentos {
	public static void main(String[] args) {
		System.out.println("En Ejecuci\u00F3n");
		
		String cadena = "m�todos  crisp�n transformaci�n ";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "Esta transformaci�n transforma los caracteres con tildes y diacr�ticos separandolos en dos carac";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "�����";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "�����";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "�����";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "�����";
		System.out.println(cadena + " = " + cleanString(cadena));
		cadena = "2� + 2�";
		System.out.println(cadena + " = " + cleanString(cadena));
	}

	public static String cleanString(String texto) {
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return texto;
		
		
	}
}
