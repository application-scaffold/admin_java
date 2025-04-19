package cn.liaozh.admin_api.validate.setting;

import cn.liaozh.admin_api.vo.setting.SettingSearchObjectVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(name = "热门搜索设置参数")
public class SettingSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "是否开启热门搜索")
    private Integer status;

    @Schema(description = "关键词列表")
    private List<SettingSearchObjectVo> data;

}
