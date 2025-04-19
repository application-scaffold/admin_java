package cn.liaozh.admin_api.validate.decorate;

import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "页面装修参数")
public class DecoratePageValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    @Schema(description = "id", required = true)
    private Integer id;

    private String name;

    private Integer type;

    @NotNull(message = "pageData参数缺失")
    @Schema(description = "装修数据", required = true)
    private String data;

    @NotNull(message = "pageMeta")
    @Schema(description = "装修数据", required = true)
    private String meta;

}
