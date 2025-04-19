package cn.liaozh.admin_api.validate.decorate;

import cn.liaozh.admin_api.vo.decorate.DecorateTabsListsVo;
import cn.liaozh.admin_api.vo.decorate.DecorateTabsStyleVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 底部导航装修参数
 */
@Data
@Schema(name = "底部装修参数")
public class DecorateTabsValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "样式")
    private DecorateTabsStyleVo style;

    @Schema(description = "列表")
    private List<DecorateTabsListsVo> list;

}
