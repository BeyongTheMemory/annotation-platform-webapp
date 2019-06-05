package com.hongying.service;

import com.hongying.repository.APRepositoryApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(value = {
        APRepositoryApplicationContext.class
})
@ComponentScan(value = {
        "com.hongying.service"
})
public class APServiceApplicationContext {
}
