package cn.liaozh.common.entity.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "角色菜单关系实体")
public class SystemRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色ID")
    private Integer roleId;

    @Schema(description = "菜单ID")
    private Integer menuId;

}
