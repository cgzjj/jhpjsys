package cn.com.hyxc.hcpmidsys.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Predicates.and;

/**
 * Spring Security 配置
 *
 * @author yuanyc
 * @return
 * @date
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebAccessDeniedHandler accessDeniedHandler;


    /**
     * 添加角色
     *
     * @param auth
     * @return
     * @author yuanyc
     * @date
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(encoder.encode("123")).roles("ADMIN", "USER")
                .and()
                .withUser("ccc").password(encoder.encode("sss")).roles("USER")
                .and()
                .withUser("yuanyc").password(encoder.encode("1")).roles("USER");
    }

    /**
     * 授权
     *
     * @param http
     * @return
     * @author yuanyc
     * @date
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //admin 级别的
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                // 用户级别的
                .antMatchers("/user/**")
                .access("hasAnyRole('ADMIN','USER')")
                 //数据管理员级别
                .antMatchers("/db/**")
                .access("hasAnyRole('ADMIN') and hasAnyRole('DBA')")

                .anyRequest()
                .authenticated() //任何请求登录后可以访问

                //添加表单验证
                .and()
                .formLogin()
                //设置登录跳转页面
                .loginPage("/demo_login")
                //设置表单action提交地址
                .loginProcessingUrl("/login_validate")
                //处理请求的接口
                .usernameParameter("username")
                .passwordParameter("password")
                //成功跳转的页面
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.sendRedirect("/home");
                    }
                })
                //登陆失败后
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        AuthenticationException e)
                            throws IOException {
                        //获取登陆失败原因
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(401);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 401);
                        if (e instanceof LockedException) {
                            map.put("msg", "账户被锁定，登录失败!");
                        } else if (e instanceof BadCredentialsException) {
                            map.put("msg", "账户名或密码输入错误，登录失败!");
                        } else if (e instanceof DisabledException) {
                            map.put("msg", "账户被禁用，登录失败!");
                        } else if (e instanceof AccountExpiredException) {
                            map.put("msg", "账户已过期，登录失败!");
                        } else if (e instanceof CredentialsExpiredException) {
                            map.put("msg", "密码已过期，登录失败!");
                        } else {
                            map.put("msg", "登录失败!");
                        }
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                //开启注销登陆
                .logout()
                //注销登陆请求url
                .logoutUrl("/logout")
                //清除身份信息
                .clearAuthentication(true)
                //session失效
                .invalidateHttpSession(true)
                //注销处理
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest req,
                                       HttpServletResponse resp,
                                       Authentication auth) {
                    }
                })
                //注销成功处理
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req,
                                                HttpServletResponse resp,
                                                Authentication auth)
                            throws IOException {
                        //跳转到自定义登陆页面
                        resp.sendRedirect("/login_page");
                    }
                })
                .and()
                //关闭csrf
                .csrf()
                .disable();


        // 异常处理
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
