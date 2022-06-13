package org.example;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

  private final JobSubmitter jobSubmitter;

  public static void main(String[] args) {
    log.info("Starting application");
    SpringApplication.run(Application.class, args);
    log.info("Application finished");
  }

  @Override
  public void run(String... args) {
    log.info("EXECUTING : command line runner");
    jobSubmitter.submitJob("test" + "-" + UUID.randomUUID());
  }
}
