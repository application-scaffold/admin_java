package cn.liaozh.front_api.validate.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "账号登录参数")
public class LoginPwdValidate {

    @NotNull(message = "username参数缺失")
    @NotEmpty(message = "账号不能为空")
    @Schema(description = "登录账号", required = true)
    private String account;

    @Schema(description = "登录密码")
    private String password;

    @Schema(description = "验证码登录")
    private String code;

    @Schema(description = "终端")
    private Integer scene;


    @Schema(description = "终端")
    private Integer terminal;
}
