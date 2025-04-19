package cn.liaozh.front_api.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "文章分类Vo")
public class ArticleCateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分类ID")
    private Integer id;

    @Schema(description = "分类名称")
    private String name;

}
