package cn.liaozh.front_api.validate.users;

import cn.liaozh.common.validator.annotation.StringContains;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
@Schema(name = "绑定手机参数")
public class UserPhoneBindValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "type参数缺失")
    @StringContains(values = {"bind", "change"})
    @Schema(description = "操作类型", required = true, example = "bind=绑定,change=修改")
    private String type;

    @NotNull(message = "mobile参数缺失")
    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @Schema(description = "手机号", required = true)
    private String mobile;

    @NotNull(message = "code参数缺失")
    @NotEmpty(message = "验证码不能为空")
    @Schema(description = "验证码", required = true)
    private String code;

}
