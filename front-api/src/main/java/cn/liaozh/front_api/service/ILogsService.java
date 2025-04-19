package cn.liaozh.front_api.service;

import cn.liaozh.common.core.PageResult;
import cn.liaozh.front_api.validate.common.PageValidate;
import cn.liaozh.front_api.vo.RechargeRecordVo;

/**
 * 日志接口服务类
 */
public interface ILogsService {

    PageResult<RechargeRecordVo> userMoney(PageValidate pageValidate, Integer userId, Integer type);

}
