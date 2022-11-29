package asm.platform.dto.user;

import asm.platform.common.constant.ConstantRegular;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户信息 请求对象
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
@Data
@ApiModel(description = "用户信息请求dto")
public class UserDto {

    @ApiModelProperty(value = "用户账号", example = "lvyazhou")
    @NotEmpty(message = "用户账号为空")
    private String account;

    @ApiModelProperty(value = "用户名称", example = "吕亚洲")
    @NotEmpty(message = "用户名称为空")
    private String uName;

    @ApiModelProperty(value = "用户密码")
    @NotEmpty(message = "用户密码为空")
    private String uPwd;


    @ApiModelProperty(value = "用户邮箱", example = "lvyazhou@qq.com")
    @NotEmpty(message = "用户邮箱为空")
    @Pattern(regexp = ConstantRegular.email, message = "邮箱校验错误")
    private String email;

    @ApiModelProperty(value = "用户电话", example = "13111111111")
    private String tel;

}
