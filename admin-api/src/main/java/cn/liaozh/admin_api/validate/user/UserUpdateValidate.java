package cn.liaozh.admin_api.validate.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "用户更新参数")
public class UserUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "字段")
    private String field;

    @Schema(description = "值")
    private String value;

}
