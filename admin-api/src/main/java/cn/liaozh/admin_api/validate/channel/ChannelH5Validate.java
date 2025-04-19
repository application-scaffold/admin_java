package cn.liaozh.admin_api.validate.channel;

import cn.liaozh.common.validator.annotation.IntegerContains;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

@Data
@Schema(name = "H5渠道参数")
public class ChannelH5Validate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "渠道开关参数缺失")
    @IntegerContains(values = {0, 1}, message = "渠道开关不是合法值")
    @Schema(description = "渠道开关", required = true)
    private Integer status;

    @NotNull(message = "pageStatus参数缺失")
    @IntegerContains(values = {0, 1}, message = "页面状态不是合法值")
    @Schema(description = "状态", required = true)
    private Integer pageStatus;


    @Length(max = 500, message = "url不能超500个字符")
    @Schema(description = "关闭链接")
    private String url;

    @Length(max = 500, message = "pageUrl不能超500个字符")
    @Schema(description = "自定义链接")
    private String pageUrl;

}
