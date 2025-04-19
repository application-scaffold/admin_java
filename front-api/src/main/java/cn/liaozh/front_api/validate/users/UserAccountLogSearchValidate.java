package cn.liaozh.front_api.validate.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "用户资金变更参数")
public class UserAccountLogSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String type;
    private Integer action;
}
