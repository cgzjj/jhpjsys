package cn.com.hyxc.hcpmidsys.config;

import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 错误页面跳转
 *
 * @param
 * @return 
 * @author yuanyc
 * @date  
 */
@Configuration
public class ErrorConfigurar {


    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
                factory.addErrorPages(errorPage404);
                factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
            }
        };
    }
}
