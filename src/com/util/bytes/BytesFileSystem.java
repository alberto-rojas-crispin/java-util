package com.util.bytes;

import java.io.IOException;
import java.util.Base64;

public class BytesFileSystem {

	public static void main(String[] args) throws IOException {
		String originalInput = "test input";
		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
		System.out.println(encodedString);

		String originalUrl = "https://www.google.co.nz/?gfe_rd=cr&ei=dzbFV&gws_rd=ssl#q=java";
		String encodedUrl = Base64.getUrlEncoder().encodeToString(originalUrl.getBytes());
		System.out.println(encodedUrl);

		byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedUrl);
		String decodedUrl = new String(decodedBytes);
		System.out.println(decodedUrl);

	}

}
