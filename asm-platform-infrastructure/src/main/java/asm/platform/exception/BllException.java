package asm.platform.exception;

/**
 * Exception for business operate.
 *
 * @author 吕亚洲 <lvyazhou@qq.cn>
 * @date 2022/11/11
 */
public class BllException extends Throwable {
    private int rCode;

    /**
     * Get return code.
     *
     * @return return code.
     */
    public int getrCode() {
        return rCode;
    }

    public void setrCode(int rCode) {
        this.rCode = rCode;
    }

    /**
     * Constructs a new Bll exception with the specified detail message.
     *
     * @param rCode   return code.
     * @param message exception detail message.
     */
    public BllException(int rCode, String message) {
        super(message);
        this.rCode = rCode;
    }

    /**
     * Constructs a new Bll exception with the specified detail message.
     *
     * @param message
     */
    public BllException(String message) {
        super(message);
    }
}
