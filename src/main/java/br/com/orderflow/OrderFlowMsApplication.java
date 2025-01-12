package br.com.orderflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "br.com.orderflow")
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
