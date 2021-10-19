package org.web.base.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication()
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//@MapperScan(basePackages = {"org.web.example.springcloud.dao"})
@ImportResource({"classpath:mapper/mybatis-config-all.xml"})
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

}
