package cn.liaozh.admin_api.validate.commons;

import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "ID参数")
public class IdValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

}
