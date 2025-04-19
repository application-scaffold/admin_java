package cn.liaozh.common.entity.setting;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "支付方式实体")
public class DevPayWay implements Serializable {

    @TableId(value="id", type= IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "支付配置ID")
    private Integer payConfigId;

    @Schema(description = "场景编码: [1=微信小程序, 2=微信公众号, 3=H5, 4=PC, 5=APP]")
    private Integer scene;

    @Schema(description = "默认支付: [0=否的, 1=是的]")
    private Integer isDefault;

    @Schema(description = "方式状态: [0=关闭, 1=开启]")
    private Integer status;

}
