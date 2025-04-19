package cn.liaozh.admin_api.validate.commons;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(name = "IDS参数")
public class IdArrayValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ids参数缺失")
    @Schema(description = "ID数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Integer> id;

}
