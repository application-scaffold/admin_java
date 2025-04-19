package cn.liaozh.common.entity.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "账户变动实体")
public class UserAccountLog {

    @TableId(value="id", type= IdType.AUTO)
    @Schema(description = "主键")
    private Integer id;

    @Schema(description = "流水号")
    private String sn;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "变动对象")
    private Integer changeObject;

    @Schema(description = "关联单号")
    private String sourceSn;

    @Schema(description = "变动类型")
    private Integer changeType;

    @Schema(description = "变动数量")
    private BigDecimal changeAmount;

    @Schema(description = "变动后数量")
    private BigDecimal leftAmount;

    @Schema(description = "变动类型: [1=增加, 2=减少]")
    private Integer action;

    @Schema(description = "备注信息")
    private String remark;

    @Schema(description = "预留字段")
    private String extra;

    @Schema(description = "创建时间")
    private Long createTime;

    @Schema(description = "更新时间")
    private Long updateTime;

    @Schema(description = "删除时间")
    private Long deleteTime;

}
