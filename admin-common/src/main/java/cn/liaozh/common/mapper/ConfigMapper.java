package cn.liaozh.common.mapper;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.Config;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置
 */
@Mapper
public interface ConfigMapper extends IBaseMapper<Config> {
}
