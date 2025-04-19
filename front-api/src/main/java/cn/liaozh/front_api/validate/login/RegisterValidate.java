package cn.liaozh.front_api.validate.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Schema(name = "注册账号参数")
public class RegisterValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "账号缺失")
    @NotEmpty(message = "账号不能为空")
    @Length(min = 3, max = 12, message = "账号必须在3~12个字符内")
    @Pattern(message = "账号应该为3-12位数字、字母组合", regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{3,12}$")
    @Schema(description = "登录账号", required = true)
    private String account;

    @NotNull(message = "password参数缺失")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 12, message = "密码必须在6~12个字符内")
    @Schema(description = "登录密码", required = true)
    private String password;

    @Schema(description = "终端")
    private Integer terminal;
}
