package cn.jdd.resourceserver.Confiuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.jwt.crypto.sign.SignerVerifier;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.swing.plaf.basic.BasicViewportUI;

@Configuration
@EnableResourceServer
public class SpringResourcesServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${spring.security.user.password}")
    String passWord;

    @Value("${spring.security.user.name")
    String userName;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
//        resources.authenticationEntryPoint(authenticationEntryPoint);
//        resources.tokenStore(tokenStore());
//        resources.tokenExtractor(jwtAccessTokenConverter());
        resources.tokenServices(tokenServices());
        resources.resourceId("resource_1");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().exceptionHandling().authenticationEntryPoint(new SecurityEntryPoint()).and()
//                .authorizeRequests().antMatchers(HttpMethod.GET, "/test/**").hasAnyAuthority("read")
//                .antMatchers("/rest/**").authenticated()
//                .and().httpBasic().disable();
        http.formLogin().loginPage("/login");
        http.csrf().disable();
        http.requestMatchers().antMatchers("/test/**").and().authorizeRequests().antMatchers("/test/**").authenticated();
        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
        //http.authorizeRequests().antMatchers("/test/**").authenticated();


//        OAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter = new OAuth2AuthenticationProcessingFilter();
//        http.addFilterBefore(oAuth2AuthenticationProcessingFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }

    @Bean
    @Primary
    public ResourceServerTokenServices tokenServices(){
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId("my-client");
        remoteTokenServices.setClientSecret("root");
        String checkTokenEndpointUrl = "http://localhost:8097/oauth/check_token";
        remoteTokenServices.setCheckTokenEndpointUrl(checkTokenEndpointUrl);
      //  remoteTokenServices.setAccessTokenConverter(jwtAccessTokenConverter());
        return remoteTokenServices;
    }

    @Bean
    TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

//    @Bean
//    @Primary
//    DefaultTokenServices tokenService(){
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        return defaultTokenServices;
//    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("123");
        return jwtAccessTokenConverter;
    }
}
