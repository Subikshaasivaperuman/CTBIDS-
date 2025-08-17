package com.ctbids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import com.ctbids.service.SeleniumService;

@SpringBootApplication
@RestController
public class McpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    @Bean
    public SeleniumService seleniumService() {
        return new SeleniumService();
    }
}
