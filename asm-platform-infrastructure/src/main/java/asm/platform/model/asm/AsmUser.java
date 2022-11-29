package asm.platform.model.asm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("asm_user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsmUser {
    /**
     * mongo id
     */
    @Id
    private Long id;

    /**
     * 用户名称
     */
    @Field("user_name")
    private String uName;

    /**
     * 所属部门
     */
    @DBRef
    @Field("dept")
    private AsmDept dept;

    /**
     * 角色列表
     */
    @DBRef
    @Field("role_list")
    private List<AsmRole> roleList;

    /**
     * 乐观锁
     */
    @Version
    Long version;

}
