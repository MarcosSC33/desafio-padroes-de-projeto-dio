package dio.padroesprojetos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class PadroesProjetosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadroesProjetosApplication.class, args);
	}

}
