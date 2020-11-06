package cn.com.hyxc.hcpmidsys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2020年09月23日 17:03
 */
@Configuration//当前类是配置类
public class MyMvcConfig implements WebMvcConfigurer {

    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("lyl").setViewName("popupsignin");
        registry.addViewController("/").setViewName("popupsignin");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        //registry.addViewController("/login_page").setViewName("login");
        //registry.addViewController("user").setViewName("user");
        //registry.addViewController("/admin/*").setViewName("admin");
        //registry.addViewController("/db/*").setViewName("db");
        registry.addViewController("/403").setViewName("error/403");
        registry.addViewController("/404").setViewName("error/404");
        registry.addViewController("/500").setViewName("error/500");
        registry.addViewController("/demo_login").setViewName("login/demo_login");
        registry.addViewController("/user/hello").setViewName("hello");//业务办理界面
        registry.addViewController("/user/business").setViewName("business");//业务办理界面
    }


}
