package cn.liaozh.front_api.validate.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "修改密码参数")
public class ResetPasswordValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "password参数缺失")
    @Pattern(message = "密码必须是6-20字母+数字组合!", regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")
    @Schema(description = "新密码", required = true)
    private String password;

    @NotNull(message = "请确认密码")
    @Pattern(message = "确认密码密码必须是6-20字母+数字组合!", regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$")
    @Schema(description = "确认密码", required = true)
    private String passwordConfirm;

    @NotNull(message = "验证码不能为空")
    @Schema(description = "code", required = true)
    private String code;

    @NotNull(message = "手机号不能为空")
    @Schema(description = "mobile", required = true)
    private String mobile;
}
