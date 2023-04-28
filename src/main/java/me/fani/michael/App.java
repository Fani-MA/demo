package me.fani.michael;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("me")
public class App {
    public static void main( String[] args ) {
        System.out.println( "Starting web" );
        SpringApplication.run(App.class, args);
    }
}
