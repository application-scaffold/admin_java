package cn.liaozh.common.mapper.smsLog;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.smsLog.SmsLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信记录
 */
@Mapper
public interface SmsLogMapper extends IBaseMapper<SmsLog> {
}
