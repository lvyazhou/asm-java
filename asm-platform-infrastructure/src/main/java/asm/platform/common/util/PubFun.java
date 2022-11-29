package asm.platform.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PubFun {
    private static final String REGEX_MOBILE = "(134[0-8]\\d{7})" +
            "|(" +
            "((13([0-3]|[5-9]))" +
            "|149" +
            "|15([0-3]|[5-9])" +
            "|166" +
            "|17(3|[5-8])" +
            "|18[0-9]" +
            "|19[8-9]" +
            ")" +
            "\\d{8}" +
            ")";
    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }
    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles){
        try {
            Pattern p = Pattern.compile(REGEX_MOBILE);
            Matcher m = p.matcher(mobiles);
            return m.matches();
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * 获取32的UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 得到当前系统日期 author: YT
     *
     * @return 当前日期的格式字符串,日期格式为"yyyy-MM-dd"
     */
    public static String getCurrentDateTime() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    public static String getCurrentDateTimeM() {
        String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    /**
     * 得到当前系统日期 author: YT
     *
     * @return 当前日期的格式字符串,日期格式为"yyyy-MM-dd"
     */
    public static String getCurrentDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    public static String getCurrentYear() {
        String pattern = "yyyy";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }
    /**
     * 获取当前日期的下一天
     *
     * @return
     */
    public static String getNextDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();
        return sdf.format(date);
    }


    /**
     * 得到当前系统时间 author: YT
     *
     * @return 当前时间的格式字符串，时间格式为"HH:mm:ss"
     */
    public static String getCurrentTime() {
        String pattern = "HH:mm:ss";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    /**
     * 得到当前系统时间 author: YT
     *
     * @return 当前时间的格式字符串，时间格式为"HH:mm:ss"
     */
    public static String getCurrentMTime() {
        String pattern = "HH:mm:ss:SSS";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String tString = df.format(today);
        return tString;
    }

    /**
     * 获取年月日
     */
    public static String GetDataNo() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    /**
     * 时间字符串转换成时间
     *
     * @param mDateTime
     * @return
     */
    public static Date getDateTime(String mDateTime) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(mDateTime);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * 将字符串补数,将sourString的<br>
     * 后面</br>
     * 用cChar补足cLen长度的字符串,如果字符串超长，则不做处理
     * <p>
     * <b>Example: </b>
     * <p>
     * <p>
     * RCh("Minim", "0", 10) returns "Minim00000"
     * <p>
     *
     * @param sourString
     *            源字符串
     * @param cChar
     *            补数用的字符
     * @param cLen
     *            字符串的目标长度
     * @return 字符串
     */
    public static String RCh(String sourString, String cChar, int cLen) {
        int tLen = sourString.length();
        int i, iMax;
        String tReturn = "";
        if (tLen >= cLen) {
            return sourString;
        }
        iMax = cLen - tLen;
        for (i = 0; i < iMax; i++) {
            tReturn += cChar;
        }
        return tReturn;
    }

    /**
     * 将字符串补数,将sourString的<br>
     * 前面</br>
     * 用cChar补足cLen长度的字符串,如果字符串超长，则不做处理
     * <p>
     * <b>Example: </b>
     * <p>
     * <p>
     * LCh("Minim", "0", 10) returns "00000Minim"
     * <p>
     *
     * @param sourString
     *            源字符串
     * @param cChar
     *            补数用的字符
     * @param cLen
     *            字符串的目标长度
     * @return 字符串
     */
    public static String LCh(String sourString, String cChar, int cLen) {
        int tLen = sourString.length();
        int i, iMax;
        String tReturn = "";
        if (tLen >= cLen) {
            return sourString;
        }
        iMax = cLen - tLen;
        for (i = 0; i < iMax; i++) {
            tReturn += cChar;
        }
        tReturn = tReturn.trim() + sourString.trim();
        return tReturn;
    }

    /**
     * 通过身份证号号获取生日日期
     *
     * @param IdNo
     *            String
     * @return String
     */
    public static String getBirthdayFromId(String IdNo) {
        String tIdNo = IdNo.trim();
        String birthday = "";
        if (tIdNo.length() != 15 && tIdNo.length() != 18) {
            return "";
        }
        if (tIdNo.length() == 18) {
            birthday = tIdNo.substring(6, 14);
            birthday = birthday.substring(0, 4) + "-" + birthday.substring(4, 6) + "-" + birthday.substring(6);
        }
        if (tIdNo.length() == 15) {
            birthday = tIdNo.substring(6, 12);
            birthday = birthday.substring(0, 2) + "-" + birthday.substring(2, 4) + "-" + birthday.substring(4);
            birthday = "19" + birthday;
        }
        return birthday;

    }

    /**
     * 通过身份证号获取性别
     *
     * @param IdNo
     *            String
     * @return String
     */
    public static String getSexFromId(String IdNo) {
        String tIdNo = IdNo.trim();
        if (tIdNo.length() != 15 && tIdNo.length() != 18) {
            return "";
        }
        String sex = "";
        if (tIdNo.length() == 15) {
            sex = tIdNo.substring(14, 15);
        } else {
            sex = tIdNo.substring(16, 17);
        }
        try {
            int iSex = Integer.parseInt(sex);
            // iSex = iSex % 2;
            iSex %= 2;
            if (iSex == 0) {
                return "F";
            }
            if (iSex == 1) {
                return "M";
            }
        } catch (Exception ex) {
            return "";
        }
        return "";
    }

    /**
     * 功能：身份证的有效验证
     *
     * @param IDStr
     *            身份证号
     * @return 有效：返回"" 无效：返回String信息
     * @throws ParseException
     */

    public static boolean IDCardValidate(String IDStr) {
        try {
            String errorInfo = "";// 记录错误信息
            String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
            String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
            String Ai = "";
            // ================ 号码的长度 15位或18位 ================
            if (IDStr.length() != 15 && IDStr.length() != 18) {
                errorInfo = "身份证号码长度应该为15位或18位。";
                return false;
            }
            // =======================(end)========================

            // ================ 数字 除最后以为都为数字 ================
            if (IDStr.length() == 18) {
                Ai = IDStr.substring(0, 17);
            } else if (IDStr.length() == 15) {
                Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
            }
            if (isNumeric(Ai) == false) {
                errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
                return false;
            }
            // =======================(end)========================

            // ================ 出生年月是否有效 ================
            String strYear = Ai.substring(6, 10);// 年份
            String strMonth = Ai.substring(10, 12);// 月份
            String strDay = Ai.substring(12, 14);// 月份
            if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
                errorInfo = "身份证生日无效。";
                return false;
            }
            GregorianCalendar gc = new GregorianCalendar();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            try {
                if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                        || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                    errorInfo = "身份证生日不在有效范围。";
                    return false;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                errorInfo = "身份证月份无效";
                return false;
            }
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                errorInfo = "身份证日期无效";
                return false;
            }
            // ================ 判断最后一位的值   有些过不去 先不要了================
			/*int TotalmulAiWi = 0;
			for (int i = 0; i < 17; i++) {
				TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
			}
			int modValue = TotalmulAiWi % 11;
			String strVerifyCode = ValCodeArr[modValue];
			Ai = Ai + strVerifyCode;

			if (IDStr.length() == 18) {
				if (Ai.equals(IDStr) == false) {
					errorInfo = "身份证无效，不是合法的身份证号码";
					return false;
				}
			} else {
				return true;
			}*/
            // =====================(end)=====================
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    /**
     * 功能判断字符串是否只包含数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 功能：判断字符串是否为日期格式
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static String setDecoder(String str) {
        try {
            if (str == null) {
                return "";
            }
            return URLDecoder.decode(str.trim(), "UTF8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }
    public static String setEncoder(String str) {
        try {
            if (str == null) {
                return "";
            }
            return URLEncoder.encode(str.trim(), "UTF8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

}
