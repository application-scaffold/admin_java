package cn.liaozh.front_api.validate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(name = "新用户更新信息参数")
public class RechargeValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "money参数缺失")
    private BigDecimal money;

}
