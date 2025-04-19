package cn.liaozh.admin_api.validate.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

@Data
@Schema(name = "系统管理员自更新参数")
public class SystemAdminUpInfoValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "昵称不能为空")
    @Length(min = 2, max = 30, message = "昵称必须在2~30个字符内")
    @Schema(description = "用户昵称")
    private String nickname;

    @Length(max = 64, message = "密码必须在6~32个字符内")
    private String password="";

    @Length(max = 64, message = "当前密码错误")
    private String currPassword="";

    @Length(max = 200, message = "头像不能超出200个字符")
    private String avatar = "";

}
