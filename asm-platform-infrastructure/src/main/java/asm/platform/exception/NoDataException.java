package asm.platform.exception;

/**
 * Exception for has no data.
 *
 * @author 吕亚洲 <lvyazhou@qq.cn>
 * @date 2022/11/11
 */
public class NoDataException extends BllException {
    public NoDataException(int rCode, String exceptionMsg) {
        super(rCode, exceptionMsg);
    }

    public NoDataException(String message) {
        super(message);
        this.setrCode(21000);
    }
}
