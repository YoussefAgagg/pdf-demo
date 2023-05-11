package com.github.youssefagagg.pdfdemo;

import com.github.youssefagagg.pdfdemo.entity.User;
import com.github.youssefagagg.pdfdemo.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PdfDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfDemoApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            userRepository.saveAll(List.of(
                    new User(1L,"Jo","Egypt"),
                    new User(2l,"Mo","مصر"),
                    new User(3L,"Mohamed","Cairo")
            ));
        };
    }

}
