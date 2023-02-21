package cn.tedu.csmall.password.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu.csmall.password.mapper")
public class MybatisConfiguration {
}
