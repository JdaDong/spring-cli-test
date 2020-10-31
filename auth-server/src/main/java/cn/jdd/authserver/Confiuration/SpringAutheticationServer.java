package cn.jdd.authserver.Confiuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
@AutoConfigureAfter(AuthorizationServerConfigurerAdapter.class)
public class SpringAutheticationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    RedisTokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    TokenStore tokenStore(){
        return  new InMemoryTokenStore();
     //   return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("123");
        return jwtAccessTokenConverter;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
  //      security.passwordEncoder(new BCryptPasswordEncoder());
       security.passwordEncoder(new BCryptPasswordEncoder()).tokenKeyAccess("isAuthenticated()").checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("my-client").redirectUris("http://localhost:8098/hello").resourceIds("resource_1").autoApprove(true)
                .secret(new BCryptPasswordEncoder().encode("root")).scopes("app").autoApprove(false).authorities("read")
                .authorizedGrantTypes("password", "authorization_code", "refresh_code", "implicit", "client_credentials");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
 //       endpoints.tokenStore(tokenStore());
    //    endpoints.tokenEnhancer(jwtAccessTokenConverter());
        endpoints.userDetailsService(userDetailsService());
        endpoints.tokenServices(defaultTokenServices());
   //     endpoints.accessTokenConverter(jwtAccessTokenConverter());
    }

    @Bean
    @Primary
    AuthorizationServerTokenServices defaultTokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
     //   TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
     //   tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter()));
      //  defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        defaultTokenServices.setAccessTokenValiditySeconds(60*60);
        defaultTokenServices.setRefreshTokenValiditySeconds(60*60);
       // defaultTokenServices.setuser();
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("jdd").password(new BCryptPasswordEncoder().encode("jdd")).authorities("read").build());
        return manager;
    }
}
