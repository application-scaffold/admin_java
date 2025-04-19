package cn.liaozh.admin_api.config;

import cn.liaozh.admin_api.LikeAdminInterceptor;
import cn.liaozh.common.config.GlobalConfig;
import cn.liaozh.common.util.YmlUtils;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Web配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    LikeAdminInterceptor likeAdminInterceptor;

    /**
     * 配置允许跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    /**
     * 登录拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(likeAdminInterceptor)
                .addPathPatterns("/**");
    }

    /**
     * 资源目录映射
     */
    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
        String directory = YmlUtils.get("like.upload-directory");
        registry.addResourceHandler("/"+ GlobalConfig.adminPublicPrefix +"/**")
                .addResourceLocations("file:" + directory);
    }

}
