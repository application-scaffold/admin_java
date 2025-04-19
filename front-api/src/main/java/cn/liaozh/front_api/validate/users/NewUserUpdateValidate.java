package cn.liaozh.front_api.validate.users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "新用户更新信息参数")
public class NewUserUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "昵称参数缺失")
    @Schema(description = "昵称", required = true)
    private String nickname;

    @NotNull(message = "头像参数缺失")
    @Schema(description = "头像", required = true)
    private String avatar;

}
