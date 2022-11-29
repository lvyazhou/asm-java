package asm.platform.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录 请求对象
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
@Data
@ApiModel(description = "登录请求dto")
public class LoginDto {

    @ApiModelProperty(value = "用户账号", example = "lvyazhou")
    @NotEmpty(message = "用户账号为空")
    private String account;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(value = "用户密码")
    @NotEmpty(message = "用户密码为空")
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotEmpty(message = "验证码为空")
    private String verifyCode;

}
