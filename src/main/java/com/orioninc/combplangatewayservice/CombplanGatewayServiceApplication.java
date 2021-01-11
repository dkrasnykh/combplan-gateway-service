package com.orioninc.combplangatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySource({
        "classpath:kafka.properties"
})
public class CombplanGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CombplanGatewayServiceApplication.class, args);
    }

}
