package cn.liaozh.common.mapper.system;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.system.OperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统操作日志
 */
@Mapper
public interface SystemLogOperateMapper extends IBaseMapper<OperationLog> {
}
