package cn.liaozh.front_api.validate.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "绑定微信小程序或公众号")
public class UserBindWechatValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "code参数缺失")
    @Schema(description = "微信code", required = true)
    private String code;

    @Schema(description = "key")
    private String key;
}
