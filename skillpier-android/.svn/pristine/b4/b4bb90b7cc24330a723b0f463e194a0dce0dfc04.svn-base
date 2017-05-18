package com.quark.skillopedia.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import Decoder.BASE64Encoder;

/**
 * 加密解密工具包
 * @author Winter Lau
 * @date 2011-12-26
 */
public class Des3Utils {

	public static String des3EncodeCBCStr( String dataStr,String keyStr)
			throws Exception {
		byte[] iv = { 117, 20, 36, 64, 5, 89, 7, 29};
//		keyStr = "2cc2e7-b027-41bb-af4b-f6";
		dataStr = "000000";
		byte[] key;
		byte[] data;
		String rtnString;
		key = keyStr.getBytes("UTF-8");
		data = dataStr.getBytes("UTF-8");
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);
		rtnString = (new BASE64Encoder()).encode(bOut);
		// rtnString = keyStr + "@#" + CFunction.bytesToHexString(bOut);
		return rtnString;

	}
}
