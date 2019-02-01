package ru.cft.starterkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class StarterKitApplication {

    public static void main(String[] args) {

        SpringApplication.run(StarterKitApplication.class, args);
    }

}

