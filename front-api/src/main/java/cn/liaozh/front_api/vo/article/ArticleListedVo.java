package cn.liaozh.front_api.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "文章列表Vo")
public class ArticleListedVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "文章ID")
    private Integer id;

    @Schema(description = "分类ID")
    private Integer cid;

    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "文章封面")
    private String image;

    @Schema(description = "文章简介")
    private String desc;

    @Schema(description = "浏览数量")
    private Integer click;

    @Schema(description = "是否收藏")
    private Boolean collect;

    @Schema(description = "创建时间")
    private String createTime;

}
