package cn.jdd.zipkinserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.ZipkinServer;
import zipkin2.server.internal.EnableZipkinServer;


@EnableZipkinServer
@SpringBootApplication
@EnableDiscoveryClient
public class ZipkinServerApplication {


	public static void main(String[] args) {
		//SpringApplication.run(ZipkinServerApplication.class, args);
		new SpringApplicationBuilder(ZipkinServer.class).run(args);
	}

}
