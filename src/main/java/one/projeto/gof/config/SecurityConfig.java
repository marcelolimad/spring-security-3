package one.projeto.gof.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

   /* @Bean
    //authentication
    public UserDetailsService userDetailsService() {
       UserDetails admin = User.withUsername("Basant")
               .password(passwordEncoder().encode("Pwd1"))
               .roles("ADMIN")
               .build();
       UserDetails user = User.withUsername("John")
               .password(passwordEncoder().encode("Pwd2"))
               .roles("ROLE_USER","ROLE_ADMIN","HR")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
     //   return new UserInfoUserDetailsService();
    }*/

	@Bean
	public UserDetailsService users() {
		// The builder will ensure the passwords are encoded before saving in memory
		UserBuilder users = User.withDefaultPasswordEncoder();
		UserDetails user = users
			.username("user")
			.password(passwordEncoder().encode("password"))
			.roles("USER")
			.build();
		UserDetails admin = users
			.username("admin")
			.password(passwordEncoder().encode("password"))
			.roles("USER", "ADMIN")
			.build();
		return new InMemoryUserDetailsManager(user, admin);
	}


	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/products/welcome","/products/new").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/products/**").hasRole("ROLE_USER")
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(users());
        authenticationProvider.setPasswordEncoder((PasswordEncoder) users());
        return authenticationProvider;
    }

}
