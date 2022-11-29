package asm.platform.common.util.encrypt;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * 基础加密组件 单向加密算法：
 * 		BASE64 严格地说，属于编码格式，而非加密算法
 *  	MD5(Message Digest algorithm5，信息摘要算法) 
 *  	SHA(Secure Hash Algorithm，安全散列算法) 
 *  	HMAC(Hash Message Authentication Code，散列消息鉴别码)
 * 
 * 单向加密的用途主要是为了校验数据在传输过程中是否被修改。
 *
 * @author lvyz
 * @date 2022-11-11
 * @version 1.0
 */

public abstract class Coder {
	public static final String KEY_SHA = "SHA";// SHA密钥
	public static final String KEY_MD5 = "MD5";// MD5密钥
	/**
	 * MAC算法可选以下多种算法 HmacMD5 HmacSHA1 HmacSHA256 HmacSHA384 HmacSHA512 </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";// MAC密钥

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (Base64Utils.decode(key));
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (Base64Utils.encode(key));
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}
}
