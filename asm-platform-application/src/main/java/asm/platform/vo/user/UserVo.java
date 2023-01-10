package asm.platform.vo.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * 用户信息 返回对象
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
@Data
@ApiModel(description = "用户信息返回vo")
public class UserVo {
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
}
