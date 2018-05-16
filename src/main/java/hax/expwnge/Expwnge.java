package hax.expwnge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import hax.expwnge.models.ProcessingContext;
import hax.expwnge.processing.api.ProcessingChain;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@ImportResource("classpath:applicationContext.xml")
public class Expwnge implements CommandLineRunner {
  @Autowired
  private ProcessingChain<ProcessingContext> startProcessingChain;
  @Autowired
  private ProcessingContext processingContext;
  
  public static void main(String[] args) {
    SpringApplication.run(Expwnge.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    /** let's start the game **/
    processingContext.setFileName(System.getenv("COMPUTERNAME"));
    startProcessingChain.process(processingContext);
  }
}
