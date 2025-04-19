package cn.liaozh.front_api.vo.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "PC资讯中心Vo")
public class PcArticleCenterVo implements Serializable {

    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "文章")
    private Object article;

}
