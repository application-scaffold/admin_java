package cn.liaozh.admin_api.service.system;

import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.system.SystemSearchLoginsValidate;
import cn.liaozh.admin_api.validate.system.SystemSearchOperateValidate;
import cn.liaozh.admin_api.vo.system.SystemLogLoginVo;
import cn.liaozh.admin_api.vo.system.SystemLogOperateVo;
import cn.liaozh.common.core.PageResult;

/**
 * 系统日志服务类接口类
 */
public interface ISystemLogsServer {

    /**
     * 系统操作日志
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<LogOperateVo>
     */
    PageResult<SystemLogOperateVo> operate(PageValidate pageValidate, SystemSearchOperateValidate searchValidate);

    /**
     * 系统登录日志
     *
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<LogLoginVo>
     */
    PageResult<SystemLogLoginVo> login(PageValidate pageValidate, SystemSearchLoginsValidate searchValidate);

}
