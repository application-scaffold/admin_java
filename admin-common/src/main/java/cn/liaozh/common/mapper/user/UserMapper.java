package cn.liaozh.common.mapper.user;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 */
@Mapper
public interface UserMapper extends IBaseMapper<User> {
}
