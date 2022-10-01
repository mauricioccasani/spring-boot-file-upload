package pe.com.mauricio.javac;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;
@Log4j2
@SpringBootApplication
public class SpringBootFileUploadApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFileUploadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("UUID: {}",UUID.randomUUID().toString());

    }
}
