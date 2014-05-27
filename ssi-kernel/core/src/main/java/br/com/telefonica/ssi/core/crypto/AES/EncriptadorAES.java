package br.com.telefonica.ssi.core.crypto.AES;

import java.io.Serializable;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class EncriptadorAES implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 419265503235234064L;
	static byte[] raw = new byte[]{'V', 'i', 'V', '0', '1', 'p', '3', 'i', 'x', '3','0','1','2','3','4','5'};
	public static String rawtxt = "ViV01p3ix3012345";
	static SecureRandom rnd = new SecureRandom();
	static IvParameterSpec iv = new IvParameterSpec(rnd.generateSeed(16));
	
	public static String encrypt(String value) {
	    try {

	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);
	        byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
	        
	        BASE64Encoder b64 = new BASE64Encoder();
	        String ciphertextbuffer = b64.encodeBuffer(encrypted);
	        
	        return ciphertextbuffer;
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}


	public static String decrypt(String key, String encrypted) {
	    try {
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec,iv);
	        
	        BASE64Decoder b64 = new BASE64Decoder();
	        byte[] original = cipher.doFinal(b64.decodeBuffer(encrypted));
	        
	        return new String(original);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
	
}
