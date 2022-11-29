package asm.platform.entity.asm;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户领域对象
 *
 * @author lvyazhou@qq.com
 * @date 2022-11-11
 */
@Data
public class AsmUserEntity implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账户名称
     */
    private String uName;
}
