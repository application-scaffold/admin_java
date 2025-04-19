package cn.liaozh.front_api.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "文章收藏Vo")
public class ArticleCollectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "收藏主键")
    private Integer id;

    @Schema(description = "文章ID")
    private Integer articleId;

    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "文章封面")
    private String image;

    @Schema(description = "文章简介")
    private String desc;

    @Schema(description = "是否显示:1-是.0-否")
    private Integer isShow;

    @Schema(description = "虚拟浏览量")
    private Integer clickVirtual;

    @Schema(description = "实际浏览量")
    private Integer clickActual;

    @Schema(description = "创建时间")
    private Object createTime;

    @Schema(description = "收集时间")
    private Object collectTime;

//    @Schema(description = "创建时间")
//    @JsonProperty("create_time")
//    private String createTimeStr;
//
//    @Schema(description = "收集时间")
//    @JsonProperty("collect_time")
//    private String collectTimeStr;
}
