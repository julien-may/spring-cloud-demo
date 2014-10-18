package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.security.resource.EnableOAuth2Resource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableOAuth2Resource
@RestController
public class ResourceServerApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(ResourceServerApplication.class);
    }

    @RequestMapping(value = "/foo", produces = MediaType.APPLICATION_JSON_VALUE)
    public String foo(final Authentication auth) {
        return "{\"id\": \"http://localhost:8081/foo#" + auth.getPrincipal().toString() + "\"}";
    }
}
