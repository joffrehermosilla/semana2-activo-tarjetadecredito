package nttdata.bootcamp.microservicios.activo.tarjetadecredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class MicroservicioActivoTarjetadecreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioActivoTarjetadecreditoApplication.class, args);
	}

}
