package cn.liaozh.front_api.vo.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "文章详情Vo")
public class ArticleDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "文章ID")
    private Integer id;

    @Schema(description = "摘要")
    @JsonProperty(value = "abstract")
    private String abstractField;
    @Schema(description = "文章作者")
    private String author;
    @Schema(description = "分类ID")
    private Integer cid;
    @Schema(description = "浏览数量")
    private Integer click;
    @Schema(description = "文章标题")
    private String title;
    @Schema(description = "是否收藏")
    private Boolean collect;
    @Schema(description = "文章内容")
    private String content;
    @Schema(description = "创建时间")
    private String createTime;
    @Schema(description = "更新时间")
    private String updateTime;
    @Schema(description = "简介")
    private String desc;
    @Schema(description = "文章封面")
    private String image;
    @Schema(description = "是否显示: [0=否, 1=是]")
    private Integer isShow;
    @Schema(description = "排序编号")
    private Integer sort;

}
