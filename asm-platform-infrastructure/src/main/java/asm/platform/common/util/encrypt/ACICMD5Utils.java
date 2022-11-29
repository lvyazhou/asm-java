package asm.platform.common.util.encrypt;

public class ACICMD5Utils {
    // Used to convert 16-byte hexadecimal characters.
    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getMd5Code(String source) {
        String md5Value = null;

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source.getBytes("UTF-8"));
            // MD5 calculation is a 128-bit long integer, that is with 16-byte byte.
            byte tmp[] = md.digest();
            // Each byte expressed in hexadecimal using 2 characters, so that 32 bytes as hexadecimal.
            char str[] = new char[16 * 2];

            int k = 0; // The index of character in convert result.
            // Convert each byte to hexadecimal of MD5.
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            // Convert the result from byte to string.
            md5Value = new String(str);
        } catch (Exception e) {

        }

        return md5Value;
    }
}
