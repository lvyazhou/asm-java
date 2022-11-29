package asm.platform.common.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author lvyz
 * DES-Data Encryption Standard,即数据加密算法 
 * DES算法的入口参数有三个:Key、Data、Mode。
 * 			    Key为8个字节共64位,是DES算法的工作密钥;
 * 				Data也为8个字节64位,是要被加密或被解密的数据;
 * 				Mode为DES的工作方式,有两种:加密或解密。 
 * DES算法把64位的明文输入块变为64位的密文输出块,它所使用的密钥也是64位。
 * 支持 DES、DESede(TripleDES,就是3DES)、AES、Blowfish、RC2、RC4(ARCFOUR) 
 * DES                  key size must be equal to 56 
 * DESede(TripleDES)    key size must be equal to 112 or 168 
 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
 * RC2                  key size must be between 40 and 1024 bits 
 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
 *
 */
public abstract class DESCoder extends Coder {
	
	public static void main(String[] args) {
		try {
			String inputStr = "DES";
			String key = DESCoder.initKey("472732787");
			System.err.println("原文:\t" + inputStr);

			System.err.println("密钥:\t" + key);
			byte[] inputData = inputStr.getBytes();
			inputData = DESCoder.encrypt(inputData, key);

			System.err.println("加密后:\t" + DESCoder.encryptBASE64(inputData));

			byte[] outputData = DESCoder.decrypt(inputData, key);
			String outputStr = new String(outputData);

			System.err.println("解密后:\t" + outputStr);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	 /** 
     * ALGORITHM 算法 <br> 
     * 可替换为以下任意一种算法，同时key值的size相应改变。 
     * DES                  key size must be equal to 56 
     * DESede(TripleDES)    key size must be equal to 112 or 168 
     * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
     * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
     * RC2                  key size must be between 40 and 1024 bits 
     * RC4(ARCFOUR)         key size must be between 40 and 1024 bits 
     * 在Key toKey(byte[] key)方法中使用下述代码 
     * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code> 替换 
     * <code> 
     * DESKeySpec dks = new DESKeySpec(key); 
     * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM); 
     * SecretKey secretKey = keyFactory.generateSecret(dks); 
     * </code> 
     */  
	 public static final String ALGORITHM = "DES";
	 /**
	  * 转换密钥
	  * @param key
	  * @return
	  * @throws Exception
	  */
	 private static Key toKey(byte[] key) throws Exception {  
	        DESKeySpec dks = new DESKeySpec(key);  
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
	        SecretKey secretKey = keyFactory.generateSecret(dks);  
	        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码  
	        // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);  
	        return secretKey;  
	    }  
	 /** 
	     * 解密 
	     *  
	     * @param data 
	     * @param key 
	     * @return 
	     * @throws Exception 
	     */  
	    public static byte[] decrypt(byte[] data, String key) throws Exception {  
	        Key k = toKey(decryptBASE64(key));  
	  
	        Cipher cipher = Cipher.getInstance(ALGORITHM);  
	        cipher.init(Cipher.DECRYPT_MODE, k);  
	  
	        return cipher.doFinal(data);  
	    }  
	  
	    /** 
	     * 加密 
	     *  
	     * @param data 
	     * @param key 
	     * @return 
	     * @throws Exception 
	     */  
	    public static byte[] encrypt(byte[] data, String key) throws Exception {  
	        Key k = toKey(decryptBASE64(key));  
	        Cipher cipher = Cipher.getInstance(ALGORITHM);  
	        cipher.init(Cipher.ENCRYPT_MODE, k);  
	        return cipher.doFinal(data);  
	    }  
	  
	    /** 
	     * 生成密钥 
	     *  
	     * @return 
	     * @throws Exception 
	     */  
	    public static String initKey() throws Exception {  
	        return initKey(null);  
	    } 
	    
	    /** 
	     * 生成密钥 
	     *  
	     * @param seed 
	     * @return 
	     * @throws Exception 
	     */  
	    public static String initKey(String seed) throws Exception {  
	        SecureRandom secureRandom = null;  
	        if (seed != null) {  
	            secureRandom = new SecureRandom(decryptBASE64(seed));  
	        } else {  
	            secureRandom = new SecureRandom();  
	        }  
	        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);  
	        kg.init(secureRandom);  
	        SecretKey secretKey = kg.generateKey();  
	        return encryptBASE64(secretKey.getEncoded());  
	    }  
}
