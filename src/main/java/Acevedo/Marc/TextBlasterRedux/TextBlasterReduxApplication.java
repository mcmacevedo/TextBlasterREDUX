package Acevedo.Marc.TextBlasterRedux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TextBlasterReduxApplication {

	@Value(value = "${spring.application.name}")
	static String applicationName;

	public static void main(String[] args) {
		SpringApplication.run(TextBlasterReduxApplication.class, args);
		log.info("Starting up {}", applicationName);
	}
}
