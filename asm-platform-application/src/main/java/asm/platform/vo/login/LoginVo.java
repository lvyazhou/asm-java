package asm.platform.vo.login;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * 用户登录 返回对象
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "登录返回vo")
public class LoginVo {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private BigInteger userId;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String account;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;

    /**
     * 是否管理员
     */
    @ApiModelProperty(value = "是否管理员 false-否 true-是")
    private Boolean adminFlag;

    /**
     * 是否首次登录
     */
    @ApiModelProperty(value = "是否第一次登录 false-否 true-是")
    private Boolean loginFirst;

    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private Boolean token;
}
