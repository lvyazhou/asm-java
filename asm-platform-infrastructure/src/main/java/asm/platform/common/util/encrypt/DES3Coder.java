package asm.platform.common.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;


/**
 * 可逆加密
 * @author lvyz
 * @date 2022-11-11
 * @version 1.0
 */
public class DES3Coder {
	public static void main(String[] args) {
		String s ="sino中文"; 
	     try {
	    	  byte[] key = Base64Utils.decode("035e1ddc1ecaa829f9f14d274c84c947");
	    	  String codes = Base64Utils.encode(DES3Coder.des3EncodeECB(key, s.getBytes("UTF-8")));
	          System.out.println("加密后的密文为:" +codes );
	          s = new String(DES3Coder.des3DecodeECB(key, Coder.decryptBASE64(codes)), "UTF-8");
	          System.out.println("解密后的明文为:" +s);
	     	} catch (Exception e) {
	    	 System.out.println(e);
		}
	   }
    public byte[] initKey(String license) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DESede");
            _generator.init(112, new SecureRandom(license.getBytes()));
            return _generator.generateKey().getEncoded();
        } catch (Exception e) {
        	System.out.println(e);
        }
        return null;
    }
   
    public String getDesString(String strMi, String eaKey) {
        String strMing = "";
        try {
            byte[] strMiby = Coder.decryptBASE64(strMi);
            byte[] keyArray = Base64Utils.decode(eaKey);
            byte[] byteMing = des3DecodeECB(keyArray, strMiby);
            strMing = new String(byteMing, "UTF8");
            return strMing;
        } catch (Exception e) {
        	 System.out.println(e);
        }
         return strMing;
    }
   
    public String getEncString(String strMing, String eaKey) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        try {
            byte[] keyArray = Base64Utils.decode(eaKey);
            byteMing = strMing.getBytes("UTF8");
            byteMi = des3EncodeECB(keyArray, byteMing);
            strMi = Coder.encryptBASE64(byteMi);
        } catch (Exception e) {
        	System.out.println(e);
        } finally {
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }
   
    public static byte[] des3EncodeECB(byte[] key, byte[] data) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
   
    public static byte[] des3DecodeECB(byte[] key, byte[] data) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }
}