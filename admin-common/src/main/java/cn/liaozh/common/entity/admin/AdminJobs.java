package cn.liaozh.common.entity.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "岗位关联表实体")
public class AdminJobs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "管理员id")
    private Integer adminId;

    @Schema(description = "岗位id")
    private Integer jobsId;

}
