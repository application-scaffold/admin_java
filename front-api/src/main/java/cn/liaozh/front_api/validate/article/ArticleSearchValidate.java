package cn.liaozh.front_api.validate.article;

import cn.liaozh.common.validator.annotation.StringContains;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Schema(name = "文章搜索参数")
public class ArticleSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cid;

    @Length(max = 100, message = "关键词过长了")
    @Schema(description = "关键词")
    private String keyword;

    @StringContains(values = {"hot", "new", "default"})
    @Schema(description = "排序号")
    private String sort;

}
