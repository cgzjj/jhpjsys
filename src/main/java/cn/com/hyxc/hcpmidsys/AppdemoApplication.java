package cn.com.hyxc.hcpmidsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.com.hyxc.hcpmidsys.*")

public class AppdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppdemoApplication.class, args);
    }

}
