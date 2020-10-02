package cn.jdd.reactwebworkflowvertx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReactWebWorkflowVertxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactWebWorkflowVertxApplication.class, args);
	}

}
