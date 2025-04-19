package cn.liaozh.generator.validate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 分页参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PageParam implements Serializable {

    // 当前分页
    @DecimalMin(value = "1", message = "pageNo参数必须大于0的数字")
    public Integer page_no = 1;

    // 每页条数
    @DecimalMin(value = "1", message = "pageSize参数必须是大于0的数字")
    @DecimalMax(value = "60", message = "pageSize参数必须是小于60的数字")
    private Integer page_size = 20;

}
