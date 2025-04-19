package cn.liaozh.admin_api.vo.decorate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(name = "装修底部导航Vo")
public class DecorateTabbarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "样式")
    private DecorateTabsStyleVo style;

    @Schema(description = "列表")
    private List<DecorateTabsListsVo> list;

}
