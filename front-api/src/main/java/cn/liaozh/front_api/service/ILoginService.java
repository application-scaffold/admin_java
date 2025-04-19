package cn.liaozh.front_api.service;

import cn.liaozh.front_api.validate.login.LoginPwdValidate;
import cn.liaozh.front_api.vo.login.LoginTokenVo;
import jakarta.servlet.http.HttpSession;


/**
 * 登录服务接口类
 */
public interface ILoginService {

    /**
     * 账号注册
     *
     * @author fzr
     * @param username 账号
     * @param password 密码
     * @param terminal 终端
     */
    void register(String account, String password, Integer terminal);

    /**
     * 账号登录
     *
     * @author fzr
     * @param account 账号
     * @param password 密码
     * @param terminal 终端
     * @return LoginTokenVo
     */
    LoginTokenVo accountLogin(LoginPwdValidate loginPwdValidate);

    /**
     * 手机登录
     *
     * @author fzr
     * @param mobile 手机号
     * @param code 验证码
     * @param terminal 终端
     * @return LoginTokenVo
     */
    LoginTokenVo mobileLogin(String mobile, String code, Integer terminal, Integer sceneId);

    /**
     * 微信登录
     *
     * @author fzr
     * @param code 微信code
     * @param terminal 终端
     * @return LoginTokenVo
     */
    LoginTokenVo mnpLogin(String code, Integer terminal);

    /**
     * 公众号登录
     *
     * @author fzr
     * @param code 微信Code
     * @param terminal 终端
     * @return LoginTokenVo
     */
    LoginTokenVo officeLogin(String code, Integer terminal);

    /**
     * 公众号跳转url
     *
     * @author fzr
     * @param url 连接
     * @return String
     */
    String oaCodeUrl(String url);

    /**
     * 扫码链接
     *
     * @author fzr
     * @param session session
     * @return String
     */
    String scanCodeUrl(String url, HttpSession session);

    /**
     * 扫码登录
     *
     * @param code 编码
     * @param state 标识
     * @param terminal 终端
     * @param session 会话
     * @return LoginTokenVo
     */
    LoginTokenVo scanLogin(String code, String state, Integer terminal, HttpSession session);

    /**
     * @notes 退出登录
     * @param $userInfo
     * @return bool
     * @author damonyuan
     */
    void logout();
}
