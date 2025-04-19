package cn.liaozh.admin_api.validate.article;

import cn.liaozh.common.validator.annotation.IDMust;
import cn.liaozh.common.validator.annotation.IntegerContains;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Schema(name = "文章分类更新参数")
public class ArtCateUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @NotEmpty(message = "分类名称不能为空")
    @Length(min = 1, max = 60, message = "分类名称不能大于60个字符")
    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "排序号不能为空")
    @DecimalMin(value = "0", message = "排序号值不能少于0")
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer sort;

    @NotNull(message = "缺少isShow参数")
    @IntegerContains(values = {0, 1}, message = "isShow不是合法值")
    @Schema(description = "是否显示", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer isShow;

}
