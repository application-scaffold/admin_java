package cn.liaozh.admin_api.validate.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Schema(name = "系统登录参数")
public class SystemAdminLoginsValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "账号不能为空")
    @Length(min = 2, max = 20, message = "账号或密码错误")
    @Schema(description = "登录账号", required = true)
    private String account;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 64, message = "账号或密码错误")
    @Schema(description = "登录密码", required = true)
    private String password;

    @NotNull(message = "请选择设备类型")
    @Schema(description = "设备类型", required = true)
    private Integer terminal;

}
