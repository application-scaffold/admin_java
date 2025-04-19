package cn.liaozh.admin_api.service.system;

import cn.liaozh.admin_api.validate.system.SystemAdminLoginsValidate;
import cn.liaozh.admin_api.vo.system.SystemCaptchaVo;
import cn.liaozh.admin_api.vo.system.SystemLoginVo;

/**
 * 系统登录服务接口类
 */
public interface ISystemLoginService {

    /**
     * 验证码
     *
     * @author fzr
     * @return SystemCaptchaVo
     */
    SystemCaptchaVo captcha();

    /**
     * 登录
     *
     * @author fzr
     * @param loginsValidate 登录参数
     * @return SystemLoginVo
     */
    SystemLoginVo login(SystemAdminLoginsValidate loginsValidate);

    /**
     * 退出
     *
     * @author fzr
     * @param token 令牌
     */
    void logout(String token);

}
