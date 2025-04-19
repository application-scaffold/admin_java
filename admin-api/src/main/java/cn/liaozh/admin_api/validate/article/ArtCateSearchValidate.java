package cn.liaozh.admin_api.validate.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "文章分类搜索参数")
public class ArtCateSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "是否显示")
    private Integer isShow;

}
