package cn.liaozh.front_api.validate.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Schema(name = "微信登录参数")
public class LoginCodeValidate {

    @NotNull(message = "code参数缺失")
    @Schema(description = "微信code", required = true)
    private String code;

    @Schema(description = "终端")
    private Integer terminal;

}
