package hbv501g.SoftProjectOne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// Arnar Sigurðsson commit

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SoftProjectOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftProjectOneApplication.class, args);
	}

}
