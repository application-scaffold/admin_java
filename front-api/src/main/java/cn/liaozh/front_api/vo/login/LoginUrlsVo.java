package cn.liaozh.front_api.vo.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "跳转链接Vo")
public class LoginUrlsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "http链接")
    private String url;

}
