package cn.jdd.springcenter;

import Log.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableEurekaServer
@SpringBootApplication
public class SpringCenterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringCenterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LogFactory logFactory = new LogFactory(SpringCenterApplication.class);
		for (String arg : args) {
			logFactory.debugLog(arg);
		}
	}

	@EnableWebSecurity
	private static class WebSecurityConfigure extends WebSecurityConfigurerAdapter{

		public WebSecurityConfigure(){}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().ignoringAntMatchers("/eureka/**");
			super.configure(http);
		}
	}
}
