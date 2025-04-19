package cn.liaozh.front_api.validate.article;

import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;

@Data
@Schema(name = "文章收藏参数")
public class ArticleCollectValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "articleId参数缺失")
    @IDMust(message = "articleId参数必须大于0")
    @Schema(description = "文章ID", required = true)
    private Integer id;

}
