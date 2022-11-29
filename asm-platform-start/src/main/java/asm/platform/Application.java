package asm.platform;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@Slf4j
@EnableMongoRepositories(basePackages = "asm.platform.repository.mongo")
@EnableElasticsearchRepositories(basePackages = "asm.platform.repository.es")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("start asm platform app ...");
    }
}
