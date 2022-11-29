package asm.platform.common.constant;

/**
 * 正则表达式
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
public interface ConstantRegular {

    /**
     * 域名
     */
    String domain = "^[0-9A-Za-z-]{1,26}(\\.[0-9A-Za-z-]{1,26}){1,}$";
    /**
     * IP
     */
    String ip = "^((25[0-5]|(2[0-4]\\d)|(1\\d{2})|([1-9]\\d)|(\\d))\\.){3}(25[0-5]|(2[0-4]\\d)|(1\\d{2})|([1-9]\\d)|(\\d))$";
    /**
     * 邮箱
     */
    String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
}
