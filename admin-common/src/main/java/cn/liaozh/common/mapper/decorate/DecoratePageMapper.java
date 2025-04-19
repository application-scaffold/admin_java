package cn.liaozh.common.mapper.decorate;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.decorate.DecoratePage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 页面装修Mapper
 */
@Mapper
public interface DecoratePageMapper extends IBaseMapper<DecoratePage> {
}
