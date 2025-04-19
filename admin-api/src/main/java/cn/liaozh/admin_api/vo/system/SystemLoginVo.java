package cn.liaozh.admin_api.vo.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "系统登录Vo")
public class SystemLoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "管理员ID")
    private Integer id;

    @Schema(description = "登录令牌")
    private String token;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "用户头像")
    private String avatar;
    @Schema(description = "用户头像")
    @JsonProperty("role_name")
    private String roleName;
}
