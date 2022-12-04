package Encrypt;

import java.util.Base64;

public class encryptClass {

	public static void main(String[] args) {
		String sample = "theone";
		
		//Need to place somewhere to hide this
		String BasicBase64format= Base64.getEncoder().encodeToString(sample.getBytes());
		
		System.out.println("Encrypted pass : " + BasicBase64format);
	}

}
