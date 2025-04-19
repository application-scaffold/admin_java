package cn.liaozh.front_api.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(name = "个人信息Vo")
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    private Integer id;

    @Schema(description = "用户编号")
    private Integer sn;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户账号")
    private String account;

    @Schema(description = "用户电话")
    private String mobile;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "是否为新用户: [0=否, 1=是]")
    private Integer isNewUser;

    @Schema(description = "有密码")
    private Boolean hasPassword;

    @Schema(description = "用户性别: [1=男, 2=女]")
    private String sex;

    @Schema(description = "用户钱包")
    private BigDecimal userMoney;

    @Schema(description = "是否绑定微信")
    private Boolean hasAuth;

    @Schema(description = "版本")
    private String version;

}
