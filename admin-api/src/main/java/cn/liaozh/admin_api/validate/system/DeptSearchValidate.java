package cn.liaozh.admin_api.validate.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "系统部门搜索参数")
public class DeptSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "是否停用")
    private Integer status;

}
