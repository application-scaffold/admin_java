package cn.liaozh.admin_api.validate.setting;

import cn.liaozh.admin_api.vo.setting.SettingCopyrightVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(name = "版权信息设置参数")
public class SettingCopyrightValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "版权列表")
    private List<SettingCopyrightVo> config;

}
