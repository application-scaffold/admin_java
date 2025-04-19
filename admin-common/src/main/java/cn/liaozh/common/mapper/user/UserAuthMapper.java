package cn.liaozh.common.mapper.user;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.user.UserAuth;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户授权Mapper
 */
@Mapper
public interface UserAuthMapper extends IBaseMapper<UserAuth> {
}
