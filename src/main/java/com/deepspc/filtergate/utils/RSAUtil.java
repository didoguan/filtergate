package com.deepspc.filtergate.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description RSA非对称算法
 * @Author didoguan
 * @Date 2020/3/7
 **/
public class RSAUtil {

	private static final String RSA = "RSA";
	private static final String PRIVATE_KEY = "privateKey";
	private static final String PUBLIC_KEY = "publicKey";

	private static final Map<String, String> keyMap = new HashMap<>(2);

	static {
		try {
			generateSenderKey();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 构建密钥对
	 * @return
	 */
	private static void generateSenderKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance(RSA);
		senderKeyPairGenerator.initialize(512);
		KeyPair keyPair = senderKeyPairGenerator.generateKeyPair();

		RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		keyMap.put(PUBLIC_KEY, publicKeyString);

		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
		keyMap.put(PRIVATE_KEY, privateKeyString);
	}

	/**
	 * 获取公钥
	 * @return
	 */
	public static String getPublicKey() {
		return keyMap.get(PUBLIC_KEY);
	}

	/**
	 * 公钥加密
	 * @param str 要加密的串
	 * @return
	 */
	private static String encodePublic(String str) throws NoSuchAlgorithmException,
					InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
					BadPaddingException, IllegalBlockSizeException {
		String publicKeyStr = keyMap.get(PUBLIC_KEY);
		byte[] decoded = Base64.decodeBase64(publicKeyStr);

		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decoded);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		byte[] result = cipher.doFinal(str.getBytes());

		return Base64.encodeBase64String(result);
	}

	/**
	 * 私钥解密
	 * @param str 要解密的串
	 * @return
	 */
	public static String decodePrivate(String str) throws NoSuchAlgorithmException,
				InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
				BadPaddingException, IllegalBlockSizeException {
		String privateKeyStr = keyMap.get(PRIVATE_KEY);
		byte[] encoded = Base64.decodeBase64(privateKeyStr);

		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encoded);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		byte[] strByte = Base64.decodeBase64(str.getBytes());
		byte[] result = cipher.doFinal(strByte);

		return new String(result);
	}

	public static void main(String[] args) {
		try {
			String name = encodePublic("admin");
			String pwd = encodePublic("111111");
			System.out.println("加密串：" + name + " | " + pwd);
			//String decodeStr = decodePrivate(name);
			//System.out.println("解密串：" + decodeStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
