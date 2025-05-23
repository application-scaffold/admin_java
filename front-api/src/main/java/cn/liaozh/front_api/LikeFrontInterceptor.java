package cn.liaozh.front_api;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.entity.user.User;
import cn.liaozh.common.entity.user.UserSession;
import cn.liaozh.common.enums.ClientEnum;
import cn.liaozh.common.enums.ErrorEnum;
import cn.liaozh.common.exception.LoginException;
import cn.liaozh.common.mapper.user.UserMapper;
import cn.liaozh.common.mapper.user.UserSessionMapper;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.common.util.YmlUtils;
import cn.liaozh.front_api.cache.TokenLoginCache;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 拦截器
 */
@Component
public class LikeFrontInterceptor implements HandlerInterceptor {

    @Resource
    UserMapper userMapper;
    @Resource
    UserSessionMapper userSessionMapper;

    /**
     * 前置处理器
     *
     * @param request 请求
     * @param response 响应
     * @param handler 处理
     * @return boolean
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {

        // 判断请求接口
        response.setContentType("application/json;charset=utf-8");
        String reqUri = request.getRequestURI();
        if (!(handler instanceof HandlerMethod) || !reqUri.startsWith("/api")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // 读取请求令牌
        String token = request.getHeader(YmlUtils.get("sa-token.token-name"));
        LikeFrontThreadLocal.put("token", token);
        // 登录权限校验
        try {
            // 记录当前平台
            String terminal = request.getHeader("terminal");
            if (StringUtils.isEmpty(terminal)) {
                //userSessionMapper.
                if (StringUtils.isEmpty(token)) {
                    LikeFrontThreadLocal.put("terminal", ClientEnum.PC.getCode());
                } else {
                    UserSession userSession = userSessionMapper.selectOne(new QueryWrapper<UserSession>().eq("token", token).gt("expire_time", System.currentTimeMillis() / 1000).orderByDesc("id").last("limit 1"));
                    LikeFrontThreadLocal.put("terminal", StringUtils.isNull(userSession) ? ClientEnum.H5.getCode() : userSession.getTerminal());
                }
            } else {
                LikeFrontThreadLocal.put("terminal", terminal);
            }

            Method method = this.obtainAop(handler);
            this.checkLogin(method, token);

        } catch (LoginException e) {
            AjaxResult<Object> result = AjaxResult.failed(e.getCode(), e.getMsg());
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        // 验证通过继续操作
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 后置处理器
     *
     * @param request 请求
     * @param response 响应
     * @param handler 处理
     * @param ex 异常
     * @throws Exception 异常
     */
    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler, Exception ex) throws Exception {
        LikeFrontThreadLocal.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 提取注解
     *
     * @param handler 处理器
     * @return Method
     * @throws Exception 异常
     */
    private Method obtainAop(@NotNull Object handler) throws Exception {
        String[] objArr = handler.toString().split("#");
        String methodStr = objArr[1].split("\\(")[0];
        String classStr = objArr[0];
        Class<?> clazz = Class.forName(classStr);

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodStr)) {
                return method;
            }
        }

        return null;
    }

    /**
     * 登录验证
     *
     * @param method 方法类
     * @author fzr
     */
    private void checkLogin(Method method, String token) {
        for (int i=0; i<=0; i++) {
            // 免登校验
            if (StringUtils.isNotNull(method) && method.isAnnotationPresent(NotLogin.class)) {
                try {
                    //Object id = StpUtil.getLoginId();
                    Integer userId = TokenLoginCache.get();
                    //if (StringUtils.isNotNull(id)) {
                    if (userId > 0) {
                        User user = userMapper.selectOne(new QueryWrapper<User>()
                                .select("id,sn,account")
                                .eq("id", userId)
                                .eq("is_disable", 0)
                                .isNull("delete_time")
                                .last("limit 1"));

                        Integer uid = StringUtils.isNull(user) ? 0 : userId;
                        LikeFrontThreadLocal.put("userId", uid);
                    }
                } catch (Exception ignored) {}
                break;
            }

            // 令牌校验
//            String token = StpUtil.getTokenValue();
//            if (StringUtils.isNull(token) || StringUtils.isBlank(token)) {
//                Integer errCode = ErrorEnum.TOKEN_EMPTY.getCode();
//                String errMsg = ErrorEnum.TOKEN_EMPTY.getMsg();
//                throw new LoginException(errCode, errMsg);
//            }
//
//            // 登录校验
//            Object id = StpUtil.getLoginId();
//            if (StringUtils.isNull(id)) {
//                Integer errCode = ErrorEnum.TOKEN_INVALID.getCode();
//                String errMsg = ErrorEnum.TOKEN_INVALID.getMsg();
//                throw new LoginException(errCode, errMsg);
//            }

            // 令牌校验
            if (StringUtils.isNull(token) || StringUtils.isBlank(token)) {
                Integer errCode = ErrorEnum.TOKEN_EMPTY.getCode();
                String errMsg = ErrorEnum.TOKEN_EMPTY.getMsg();
                throw new LoginException(errCode, errMsg);
            }

            // 登录校验
            Integer userId = TokenLoginCache.get();
            if (userId <= 0) {
                Integer errCode = ErrorEnum.TOKEN_INVALID.getCode();
                String errMsg = ErrorEnum.TOKEN_INVALID.getMsg();
                throw new LoginException(errCode, errMsg);
            }

            // 用户信息缓存
            //Integer userId = Integer.parseInt(id.toString());
            User user = userMapper.selectOne(new QueryWrapper<User>()
                    .select("id,sn,account,nickname,mobile,is_disable")
                    .eq("id", userId)
                    .isNull("delete_time")
                    .last("limit 1"));

            // 删除校验
            if (StringUtils.isNull(user)) {
                Integer errCode = ErrorEnum.TOKEN_INVALID.getCode();
                String errMsg = ErrorEnum.TOKEN_INVALID.getMsg();
                throw new LoginException(errCode, errMsg);
            }

            // 禁用校验
            if (user.getIsDisable().equals(1)) {
                Integer errCode = ErrorEnum.LOGIN_DISABLE_ERROR.getCode();
                String errMsg = ErrorEnum.LOGIN_DISABLE_ERROR.getMsg();
                throw new LoginException(errCode, errMsg);
            }

            // 写入线程
            LikeFrontThreadLocal.put("userId", user.getId());
            LikeFrontThreadLocal.put("userSn", user.getSn());
            LikeFrontThreadLocal.put("username", user.getAccount());
            LikeFrontThreadLocal.put("nickname", user.getNickname());
            LikeFrontThreadLocal.put("mobile", user.getMobile());
        }
    }

}
