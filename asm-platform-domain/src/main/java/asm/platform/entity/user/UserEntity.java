package asm.platform.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户领域对象
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
@Data
public class UserEntity implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账户名称
     */
    private String account;

    /**
     * 邮件
     */
    private String email;

    /**
     * 姓名
     */
    private String uName;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 登录密码
     */
    private String uPwd;

    /**
     * 用户状态：1:正常；2：禁用；3：已删除
     */
    private Short uStatus;

    private Timestamp createTime;

    private Long createUserId;

    private Timestamp updateTime;

    private Long updateUserId;

}
