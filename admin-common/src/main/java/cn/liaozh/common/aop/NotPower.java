package cn.liaozh.common.aop;

import java.lang.annotation.*;

/**
 * 免权限校验注解类
 * 该注解无需实现类,由拦截器监听
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotPower {
}
