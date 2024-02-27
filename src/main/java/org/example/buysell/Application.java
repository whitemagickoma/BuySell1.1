package org.example.buysell;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import java.util.Locale;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final MessageSource messageSource;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Running Message Property Data");
        System.out.println(messageSource.getMessage("greeting.message", null, Locale.getDefault()));
        System.out.println("End Message Property Data");
    }
}
