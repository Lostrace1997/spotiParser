package com.main.spotiPlayer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)  
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("user")
			.roles("USER");
			
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf()
	        .disable()
	        // указываем правила запросов
	        // по которым будет определятся доступ к ресурсам и остальным данным
	        .authorizeRequests()
	        .antMatchers("/index","/login","/").permitAll()
	        //.antMatchers("/auth/main").hasAnyAuthority("ADMIN", "USER")
	        .anyRequest()
            .authenticated()
	        .and();

		http.formLogin()
	        // указываем страницу с формой логина
	        .loginPage("/login")
	        // указываем action с формы логина
	        .loginProcessingUrl("/perform_login")
	        // указываем URL при неудачном логине
	        .failureUrl("/login?error")

            .defaultSuccessUrl("/auth/main")
	        // Указываем параметры логина и пароля с формы логина
	        .usernameParameter("p_username")
	        .passwordParameter("p_password")
	        // даем доступ к форме логина всем
	        .permitAll();

		http.logout()
	        // разрешаем делать логаут всем
	        .permitAll()
	        // указываем URL логаута
	        .logoutUrl("/logout")
	        // указываем URL при удачном логауте
	        .logoutSuccessUrl("/login?logout")
	        // делаем не валидной текущую сессию
	        .invalidateHttpSession(true);
	}
	
	
	@SuppressWarnings("deprecation")
	@Bean 
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}
} 


