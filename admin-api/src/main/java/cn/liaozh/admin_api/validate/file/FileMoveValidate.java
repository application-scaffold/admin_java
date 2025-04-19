package cn.liaozh.admin_api.validate.file;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(name = "附件移动参数")
public class FileMoveValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ids参数缺失")
    @Schema(description = "附件ID", required = true)
    private List<Integer> ids;

    @NotNull(message = "cid参数缺失")
    @Schema(description = "类目ID", required = true)
    private Integer cid;

}
