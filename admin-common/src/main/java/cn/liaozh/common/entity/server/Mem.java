package cn.liaozh.common.entity.server;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "內存相关信息实体")
public class Mem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "内存总量")
    private double total;

    @Schema(description = "已用内存")
    private double used;

    @Schema(description = "剩余内存")
    private double free;

    @Schema(description = "使用率")
    private double usage;

}
