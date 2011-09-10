/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 */

package com.alipay.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import android.util.Log;

public class Rsa {

	private static final String ALGORITHM = "RSA";

	/**
	 * @param algorithm
	 * @param ins
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws AlipayException
	 */
	private static PublicKey getPublicKeyFromX509(String algorithm,
			String bysKey) throws NoSuchAlgorithmException, Exception {
		byte[] decodedKey = Base64.decode(bysKey);
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		return keyFactory.generatePublic(x509);
	}

	public static String encrypt(String content, String key) {
		try {
			PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, key);

			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pubkey);

			byte plaintext[] = content.getBytes("UTF-8");
			byte[] output = cipher.doFinal(plaintext);

			String s = new String(Base64.encode(output));

			return s;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	public static String sign(String content, String privateKey) {
		// ***************
		// content = new Rsa().getOrderInfo();
//		content = "partner=\"2088401261653031\"&seller=\"2088301431295967\"&out_trade_no=\"110732992990\"&subject=\"酒店\"&body=\"null\"&total_fee=\"0.01\"&notify_url=\"http://210.51.165.170:9180/web/phone/pay/fPhonePayRt.jsp\"";
//		privateKey = "MIICXgIBAAKBgQDWTc3ZAwwQKzLas6GNpEttJQH1+42I8CbNUgFwFYPwgs5GpHLEptwEcUv1eGljeaOvoVMmMiOZ1EpNeYhJspzismNcNBlVTEfR1dkp7dhJvi5k4n8OIvkq9I2bN55XbmcCKGcOXotFV2VPFBdjQCf3R9JiZihOyLmSbOCzZcFTOwIDAQABAoGBAI4gO4zZAH8Ypzl0OIh/laZlDM/ebReMba9t25ZCLqarmbIwDg1tvC0WFpJNsVdPrMcl7sc+KW4z5LNNDp4R3wki9zWhown8FVMvULx+yIhl+mBLr7eXTqcRth9wPvgqICDURr8MaOcpqDl0diSJ7+56yCprE9f+k7cM44F0pI4BAkEA8odXHHLRIIWtgexw+eQBOMHZg9S8h/PQQ1A/OiTaI+/MBRqVEkxX92RaeT+y/8OOoAgmxe5zVID7coD5+1fnYQJBAOI1Hogq8p/FQY/KWZYD+pgpRJuXN1GScIGqYxFSPIrUQUd9LP4rTfRvAhm80xKExwUMH1XJmcFVcNrWAP0/bBsCQQDvdQdiVE79AF+erxeg8yjtpCAiVLJMvUL8o0e6HdavsrzKsUNMTMpxvcjCfE080BWLFmFJ/jQq1CrwFD49lr4hAkEArg+Lz+GkUnaZrUhaSQoEwSM4LLVeFsGlGtF+a6yo83bCSH00qtutn4HvztgyXpSXA/ZBAmUfj9nr/iggGL69swJAQN/acGycJLnIZQCmFY5RS45BU3W1XOYquLNEpSLXwuX2VGYiBQpud4vXJOmmXroldMkwqfEY7fnb73f4yJS3zg==";
		// Log.e("rsa 签名前·····content", content);
		// ***************
		String charset = "utf-8";
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64
					.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(charset));

			byte[] signed = signature.sign();
			// ------------------
			Log.e("rsa 签名后·····", Base64.encode(signed));
			// ------------------
			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

//	/**/
//
//	String getOrderInfo() {
//		HashMap<String, String> product = new HashMap<String, String>();
//		product.put("partner", "2088201962473581");
//		product.put("seller", "2088201962473581");
//		product.put("out_trade_no", "876534312");
//		product.put("subject", "测试标题");
//		product.put("total_fee", "0.01");
//		product.put("notify_url", "http://www.meituan.com");
//		product.put("body", "测试描述测试描述测试描述测试描述");
//
//		String strOrderInfo = "partner=" + "\"" + product.get("partner") + "\"";
//		strOrderInfo += "&";
//		strOrderInfo += "seller=" + "\"" + product.get("seller") + "\"";
//		strOrderInfo += "&";
//		strOrderInfo += "out_trade_no=" + "\"" + product.get("out_trade_no")
//				+ "\"";
//		strOrderInfo += "&";
//		strOrderInfo += "subject=" + "\"" + product.get("subject") + "\"";
//		strOrderInfo += "&";
//		strOrderInfo += "total_fee=" + "\"" + product.get("total_fee") + "\"";
//		strOrderInfo += "&";
//		strOrderInfo += "notify_url=" + "\"" + product.get("notify_url") + "\"";
//		strOrderInfo += "&";
//		strOrderInfo += "body=" + "\"" + product.get("body") + "\"";
//		return strOrderInfo;
//	}
//
//	/**/

	public static boolean doCheck(String content, String sign, String publicKey) {
		// ********
//		content = "partner=\"2088401261653031\"&seller=\"2088301431295967\"&out_trade_no=\"110732992990\"&subject=\"酒店\"&body=\"null\"&total_fee=\"0.01\"&notify_url=\"http://210.51.165.170:9180/web/phone/pay/fPhonePayRt.jsp\"&success=\"true\"";
//		sign = "cI2crp76OfRTHBd3+VwIOxrdf37r2weT26WZ0HiQDZl5aGfOvSuCMAsb0pQoOlbM7xgcQZORsEqw37UlQ0qk4ajzyzjiMdI9+Ts0tBmwcFDC5Og/xSMBISJ/PlbfWn/isaBSTm+tNJJXgTPLpMs8cYsJPSgEUFbT2y7MBkjHm2E=";
//		publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKBcUQoQ5U9xcgLPHfqjmQmr3oHLEv55ENnC/V 5AJ23z+lazj/kI02MvwcyyGdKJPKmfDeXwIwv9AJKhioKUmig4tvCp3xpkx4i9m1f4GhIg8Yh7rF FWu+7jm0RZUGPmYlbG5SOhSdjIDJKKuMAryAlH4DLKXXyTkOKXHu0T1pMwIDAQAB";
		// ********
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = Base64.decode(publicKey);
			PublicKey pubKey = keyFactory
					.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes("utf-8"));

			boolean bverify = signature.verify(Base64.decode(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
