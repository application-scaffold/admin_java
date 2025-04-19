package cn.liaozh.common.entity.decorate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "页面装修实体")
public class DecoratePage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "页面类型")
    private Integer type;

    @Schema(description = "页面名称")
    private String name;

    @Schema(description = "页面数据")
    private String data;

    @Schema(description = "页面设置")
    private String meta;

    @Schema(description = "创建时间")
    private Long createTime;

    @Schema(description = "更新时间")
    private Long updateTime;

}
