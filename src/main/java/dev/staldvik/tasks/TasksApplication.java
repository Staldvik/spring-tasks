package dev.staldvik.tasks;

import dev.staldvik.tasks.domain.task.Task;
import dev.staldvik.tasks.infrastructure.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TasksApplication {
    private static final Logger log = LoggerFactory.getLogger(TasksApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(TaskRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Task("Skift til sommerdekk"));
            repository.save(new Task("Vask bil"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(customer -> {
                log.info(customer.toString());
            });
            log.info("");

            // fetch an individual customer by ID
            Task task = repository.findById(1L).orElseThrow();
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(task.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByTitle("sommerdekk").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info("");
        };
    }

}
