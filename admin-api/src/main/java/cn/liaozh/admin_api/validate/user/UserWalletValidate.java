package cn.liaozh.admin_api.validate.user;

import cn.liaozh.common.validator.annotation.IntegerContains;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UserWalletValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "缺少用户id参数")
    @Min(value = 0, message = "用户id必须为数字")
    private Integer userId;

    @NotNull(message = "请输入变动类型")
    @IntegerContains(values = {2,1}, message = "变动类型错误")
    private Integer action;

    @NotNull(message = "请输入变动金额")
    private BigDecimal num;

    private String remark;

}
