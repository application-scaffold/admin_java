package cn.liaozh.admin_api.vo.setting;

import cn.liaozh.common.entity.setting.HotSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(name = "热门搜索详情Vo")
public class SettingSearchDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "是否开起热门搜索")
    private Integer status;

    @Schema(description = "热门搜索关键词")
    private List<HotSearch> data;

}
