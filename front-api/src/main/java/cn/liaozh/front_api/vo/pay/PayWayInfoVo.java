package cn.liaozh.front_api.vo.pay;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "支付方式信息Vo")
public class PayWayInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "是否默认: [0=否, 1=是]")
    private Integer isDefault;

    private String extra;
    private Integer payWay;
    private String remark;
    private Integer sort;

}
