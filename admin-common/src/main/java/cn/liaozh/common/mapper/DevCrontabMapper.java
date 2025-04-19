package cn.liaozh.common.mapper;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.DevCrontab;
import org.apache.ibatis.annotations.Mapper;

/**
 * 计划任务Mapper
 */
@Mapper
public interface DevCrontabMapper extends IBaseMapper<DevCrontab> {
}
