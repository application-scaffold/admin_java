package cn.liaozh.front_api.validate.users;

import cn.liaozh.common.validator.annotation.StringContains;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;

@Data
@Schema(name = "用户更新参数")
public class UserUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "field参数缺失")
    @StringContains(values = {"avatar", "account", "nickname", "sex"})
    @Schema(description = "操作字段", required = true, example = "avatar,account,nickname,sex")
    private String field;

    @NotNull(message = "value参数缺失")
    @Schema(description = "变更的值", required = true)
    private String value;

}
