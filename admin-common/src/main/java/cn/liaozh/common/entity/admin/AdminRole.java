package cn.liaozh.common.entity.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "角色关联表实体")
public class AdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "管理员id")
    private Integer adminId;

    @Schema(description = "角色id")
    private Integer roleId;

}
