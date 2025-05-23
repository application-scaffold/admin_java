package cn.liaozh.common.exception;

import cn.liaozh.common.enums.ErrorEnum;

/**
 * 登录异常类
 */
public class LoginException extends BaseException {

    public LoginException(Integer code, String msg) {
        super(code, msg, ErrorEnum.SHOW_MSG.getCode());
    }

    public LoginException(Integer code, String msg, Integer show) {
        super(code, msg, show);
    }

}
