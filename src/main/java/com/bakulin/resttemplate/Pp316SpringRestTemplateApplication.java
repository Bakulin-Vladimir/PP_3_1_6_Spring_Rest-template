package com.bakulin.resttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Pp316SpringRestTemplateApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Pp316SpringRestTemplateApplication.class, args);

        RestTemplateIm templateIm = context.getBean(RestTemplateIm.class);
        System.out.println(templateIm.getAllUsers());
        System.out.println("Ответ: " + templateIm.getAnswer());
    }

}
