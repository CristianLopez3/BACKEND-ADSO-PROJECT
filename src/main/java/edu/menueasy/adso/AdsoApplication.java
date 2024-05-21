package edu.menueasy.adso;

import edu.menueasy.adso.s3.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class AdsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdsoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(S3Service service) {
        return args -> {
           service.putObject("menueasy-pictures", "testfoo", "Cristian".getBytes());

           byte[] res = service.getObject("menueasy-pictures", "testfoo");
              System.out.println(new String(res, StandardCharsets.UTF_8));
        };
    }

}
