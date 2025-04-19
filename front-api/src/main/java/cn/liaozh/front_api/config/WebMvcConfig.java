package cn.liaozh.front_api.config;

import cn.liaozh.common.config.GlobalConfig;
import cn.liaozh.common.util.YmlUtils;
import cn.liaozh.front_api.LikeFrontInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    LikeFrontInterceptor likeFrontInterceptor;

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
        registry.addInterceptor(likeFrontInterceptor).addPathPatterns("/**");
    }

    /**
     * 资源目录映射
     */
    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        String directory = YmlUtils.get("like.upload-directory");
        registry.addResourceHandler("/"+ GlobalConfig.publicPrefix +"/**")
                .addResourceLocations("file:" + directory);
    }

}
