package com.woodwing.distancecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.woodwing.distancecalculator")
public class DistanceCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistanceCalculatorApplication.class, args);
    }

}
