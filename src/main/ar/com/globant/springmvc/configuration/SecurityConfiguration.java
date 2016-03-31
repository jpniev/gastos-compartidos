package main.ar.com.globant.springmvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
 
 
/**
 * Expose the Spring Security Configuration
 * 
 */
@Configuration
@ImportResource({ "classpath:webSecurityConfig.xml" })
@ComponentScan("main.ar.com.globant.springmvc.configuration.security")
public class SecurityConfiguration {
 
    public SecurityConfiguration() {
        super();
    }
 
}