package cn.liaozh.front_api.vo.decorateTabbar;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name = "底部导航服务Vo")
public class DecorateTabbarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "导航名称")
    private String name;

    @Schema(description = "未选图标")
    private String selected;

    @Schema(description = "已选图标")
    private String unselected;

    @Schema(description = "链接地址")
    private JSONObject link;

    @Schema(description = "是否显示")
    private Integer isShow;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "更新时间")
    private String updateTime;


}
