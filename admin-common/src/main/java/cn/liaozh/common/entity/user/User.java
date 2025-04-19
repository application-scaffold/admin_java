package cn.liaozh.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Schema(description = "用户信息实体")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "编号")
    private Integer sn;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户账号")
    private String account;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "用户电话")
    private String mobile;

    @Schema(description = "用户性别: [1=男, 2=女]")
    private Integer sex;

    @Schema(description = "注册渠道")
    private Integer channel;

    @Schema(description = "是否禁用: [0=否, 1=是]")
    private Integer isDisable;

    @Schema(description = "用户钱包")
    private BigDecimal userMoney;

    @Schema(description = "累计充值")
    private BigDecimal totalRechargeAmount;

//    @ApiModelProperty("加密盐巴")
//    private String salt;
    @Schema(description = "最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private Long loginTime;

    @Schema(description = "创建时间")
    private Long createTime;

    @Schema(description = "更新时间")
    private Long updateTime;

    @Schema(description = "删除时间")
    private Long deleteTime;

    @Schema(description = "是否为新用户: [0=否, 1=是]")
    private Integer isNewUser;

}
