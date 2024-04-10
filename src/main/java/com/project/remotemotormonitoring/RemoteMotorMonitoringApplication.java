package com.project.remotemotormonitoring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "REMOTE MOTOR MONITORING", version = "1.0", description = "remote monitoring"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "authorization",scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class RemoteMotorMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemoteMotorMonitoringApplication.class, args);
    }

}
