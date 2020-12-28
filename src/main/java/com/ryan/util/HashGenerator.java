package com.ryan.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashGenerator {

	public static String generateHash(String inputStr) {
		String result = "";
//		generate random salt
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
//		generate hash from input String
		KeySpec spec = new PBEKeySpec(inputStr.toCharArray(), salt, 65536, 128);
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			for (int i=0; i<hash.length; i++) {
				result += hash[i];
				if (i < hash.length - 1) {
					result += ",";
				}
			}
			result += ".";
			for (int i=0; i<salt.length; i++) {
				result += salt[i];
				if (i < salt.length -1) {
					result += ",";
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return result;
	}
}
