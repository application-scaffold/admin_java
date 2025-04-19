package cn.liaozh.common.entity.server;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "CPU相关信息实体")
public class Cpu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "核心数")
    private int cpuNum;

    @Schema(description = "CPU总的使用率")
    private double total;

    @Schema(description = "CPU系统使用率")
    private double sys;

    @Schema(description = "CPU用户使用率")
    private double used;

    @Schema(description = "CPU当前等待率")
    private double wait;

    @Schema(description = "CPU当前空闲率")
    private double free;

}
