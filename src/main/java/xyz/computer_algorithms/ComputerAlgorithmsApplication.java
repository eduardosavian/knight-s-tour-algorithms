package xyz.computer_algorithms;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComputerAlgorithmsApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();

        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        String serverPort = dotenv.get("SERVER_PORT");
        System.out.println("SERVER_PORT from .env: " + serverPort);

        SpringApplication.run(ComputerAlgorithmsApplication.class, args);
    }
}