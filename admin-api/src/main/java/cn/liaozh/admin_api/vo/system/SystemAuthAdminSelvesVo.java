package cn.liaozh.admin_api.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "当前系统管理员Vo")
public class SystemAuthAdminSelvesVo {

    @Schema(description = "用户信息")
    private Object user;

    @Schema(description = "权限集合: [[*]=>所有权限, ['article:add']=>部分权限]")
    private Object permissions;

}
