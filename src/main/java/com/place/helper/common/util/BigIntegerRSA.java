package com.place.helper.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BigIntegerRSA {
	private static final Log logger = LogFactory.getLog(BigIntegerRSA.class);

	public String rsaCrypt(String modeHex, String exponentHex, String messageg) {
		BigInteger m = new BigInteger(modeHex, 16);
		BigInteger e = new BigInteger(exponentHex, 16);
		RSAPublicKeySpec spec = new RSAPublicKeySpec(m, e);
		byte[] encryptedContentKey = null;

		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKey publicKey = (RSAPublicKey)keyFactory.generatePublic(spec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(1, publicKey);
			encryptedContentKey = cipher.doFinal(messageg.getBytes("GB2312"));
		} catch (InvalidKeySpecException e1) {
			logger.error(e1);
		} catch (NoSuchAlgorithmException e2) {
			logger.error(e2);
		} catch (NoSuchPaddingException e3) {
			logger.error(e3);
		} catch (InvalidKeyException e4) {
			logger.error(e4);
		} catch (IllegalBlockSizeException e5) {
			logger.error(e5);
		} catch (BadPaddingException e6) {
			logger.error(e6);
		} catch (UnsupportedEncodingException e7) {
			logger.error(e7);
		}

		return new String(Hex.encodeHex(encryptedContentKey));
	}
}
