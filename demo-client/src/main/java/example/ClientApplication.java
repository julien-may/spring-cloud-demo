package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.security.sso.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableOAuth2Sso
@EnableZuulProxy
@RestController
public class ClientApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(ClientApplication.class);
    }

    @RequestMapping(value = "/")
    public String home() {
        return "Hello";
    }

    @Bean
    public ContentUrlRewritingFilter contentUrlRewritingFilter() {
        return new ContentUrlRewritingFilter("localhost:8081", "localhost:8080/proxy");
    }

    @Bean
    public HeaderUrlRewritingFilter headerUrlRewritingFilter() {
        return new HeaderUrlRewritingFilter("localhost:8081", "localhost:8080/proxy");
    }
}
