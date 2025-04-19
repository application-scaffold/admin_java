package cn.liaozh.admin_api.validate.file;

import cn.liaozh.common.validator.annotation.IDMust;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

@Data
@Schema(name = "附件重命名参数")
public class FileRenameValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    @Schema(description = "附件ID", required = true)
    private Integer id;

    @NotEmpty(message = "名称不能为空")
    @Length(min = 1, max = 30, message = "名称不能大于30个字符")
    @Schema(description = "附件名称", required = true)
    private String name;

}
