package cn.liaozh.front_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动器
 */
@Configuration
@ComponentScan(basePackages = {"cn.liaozh"})
@MapperScan(basePackages = {"cn.liaozh.*.mapper"})
@EnableTransactionManagement
@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class})
public class LikeFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(LikeFrontApplication.class, args);
    }

}
