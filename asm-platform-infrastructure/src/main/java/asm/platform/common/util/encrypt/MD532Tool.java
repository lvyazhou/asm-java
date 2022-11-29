package asm.platform.common.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5 32不可逆加密算法
 *
 * @author lvyz
 * @date 2022-11-11
 * @version 1.0
 */
public class MD532Tool {
	public static void main(String[] args) {
		System.out.println(encryption("201309877686"));
	}

	static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f' };
	static final String _CODE = "?sign=";
	public static String md5(String text) {
	    MessageDigest msgDigest = null;
	    try {
	      msgDigest = MessageDigest.getInstance("MD5");
	    } catch (NoSuchAlgorithmException e) {
	      throw new IllegalStateException("System doesn't support MD5 algorithm.");
	    }
	    try {
	      msgDigest.update(text.getBytes("UTF-8"));
	    } catch (UnsupportedEncodingException e) {
	      throw new IllegalStateException("System doesn't support your  EncodingException.");
	    }
	    byte[] bytes = msgDigest.digest();
	    String md5Str = new String(encodeHex(bytes));
	    return md5Str;
	  }
	/**
	 * 
	 * @param plainText
	 *            明文
	 * @return 32位密文
	 */
	public static String encryption(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return re_md5;
	}

	public static String md5(String url, String text) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(
					"System doesn't support MD5 algorithm.");
		}
		try {
			msgDigest.update(text.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(
					"System doesn't support your  EncodingException.");
		}
		byte[] bytes = msgDigest.digest();
		String md5Str = new String(encodeHex(bytes));
		return url + _CODE + md5Str;
	}

	public static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}
		return out;
	}

}
