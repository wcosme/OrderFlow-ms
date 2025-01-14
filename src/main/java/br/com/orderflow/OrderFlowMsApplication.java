package br.com.orderflow;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "br.com.orderflow")
@OpenAPIDefinition(
		info = @Info(
				title = "Order Flow API",
				version = "1.0",
				description = "API de gerenciamento de pedidos com MongoDB e Kafka"
		)
)
@EnableCaching
public class OrderFlowMsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(OrderFlowMsApplication.class, args);
		String[] activeProfiles = context.getEnvironment().getActiveProfiles();

		if (activeProfiles.length == 0) {
			System.out.println("⚠️ Nenhum profile específico foi configurado! Usando o profile padrão: 'default'.");
		} else {
			System.out.println("✅ Profiles ativos: " + String.join(", ", activeProfiles));
		}
	}
}
