package cn.tedu.csmall.password.config;

import cn.tedu.csmall.password.filter.JwtAuthorizationFilter;
import cn.tedu.csmall.password.web.JsonResult;
import cn.tedu.csmall.password.web.ServiceCode;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Spring Security的配置类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance(); // NoOpPasswordEncoder是“不加密”的密码编码器
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http); // 父类方法配置要求所有请求都是认证过的

        // 设置Security的异常处理器
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request,
                                 HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                String message = "未检测到登录信息，请登录！（开发过程中，请检查请求头中是否携带了有效的JWT）";
                JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED, message);
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.println(JSON.toJSONString(jsonResult));
                writer.close();
            }
        });

        // 白名单URL
        // 注意：所有路径使用 / 作为第1个字符
        // 可以使用 * 通配符，例如 /admins/* 可以匹配 /admins/add-new，但是，不能匹配多级，例如不能匹配到 /admins/9527/delete
        // 可以使用 ** 通配符，例如 /admins/** 可以匹配若干级，例如可以匹配 /admins/add-new，也可以匹配到 /admins/9527/delete
        String[] urls = {
                "/admins/login", // 新增
                "/doc.html",
                "/**/*.css",
                "/**/*.js",
                "/a.jpg",
                "/favicon.ico",
                "/swagger-resources",
                "/v2/api-docs"
        };

        // 启用Security框架自带的CorsFilter过滤器，可以对OPTIONS请求放行
        http.cors();

        // 配置请求是否需要认证
        http.authorizeRequests() //配置请求的认证授权
                //.mvcMatchers(HttpMethod.OPTIONS, "/**")
                //.permitAll()
                .mvcMatchers(urls) //匹配某些请求路径
                .permitAll()  //允许直接访问，即不需要通过认证
                .anyRequest() //其他任何请求
                .authenticated(); //需要是通过认证的

        //禁用"防止伪造的跨域攻击"这样的防御机制
        http.csrf().disable();

        // 将JWT过滤器添加在Spring Security的“用户名密码认证信息过滤器”之前
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        // 设置Session创建策略：从不创建
        // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

        // http.formLogin(); // 开启登录表单

//        http.csrf().disable()
//                .formLogin();
        // 链式语法
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and() // 重点
//                .csrf().disable()
//                .formLogin();
    }
}
