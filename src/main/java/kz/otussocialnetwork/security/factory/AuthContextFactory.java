package kz.otussocialnetwork.security.factory;

import kz.otussocialnetwork.security.context.AuthContextHolder;
import kz.otussocialnetwork.security.context.AuthContextHolderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthContextFactory {

  @Bean
  public AuthContextHolder authContextHolder() {
    return new AuthContextHolderImpl();
  }
}
