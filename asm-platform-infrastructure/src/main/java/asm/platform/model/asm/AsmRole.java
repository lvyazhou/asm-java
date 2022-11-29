package asm.platform.model.asm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("asm_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsmRole {
    /**
     * mongo id
     */
    @Id
    private Long id;

    /**
     * 角色名称
     */
    @Field("role_name")
    private String rName;

}
