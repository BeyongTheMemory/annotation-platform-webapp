package com.hongying;

import com.hongying.service.APServiceApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@Import(value = {
        APServiceApplicationContext.class
})
public class APApplicationContext {
    public static void main(String[] args) {
        SpringApplication.run(APApplicationContext.class, args);
    }
}
