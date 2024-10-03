package co.com.samuel_falla.microservice.resolveEnigmaApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = { "co.com.samuel_falla.microservice.resolveEnigmaApi", "co.com.samuel_falla..microservice.resolveEnigmaApi.api" , "co.com.solis.microservice.resolveEnigmaApi.config"})
public class SwaggerSpringBootApplication  {

    

    public static void main(String[] args) throws Exception {
        new SpringApplication(SwaggerSpringBootApplication.class).run(args);
    }

    
}
