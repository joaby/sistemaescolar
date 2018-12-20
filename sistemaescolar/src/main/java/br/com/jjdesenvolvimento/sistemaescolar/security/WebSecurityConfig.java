package br.com.jjdesenvolvimento.sistemaescolar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	EscolaUserDetailsService escolaUserDetailsService;
	
	@Override // configura as solicitações por http
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()//permite restringir acesso
				.antMatchers("/js/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/escola").hasAnyRole("ADMIN")
				.antMatchers("/aluno").hasRole("SECRETARIO")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/entrar").permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/entrar?logout").permitAll();
	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(this.escolaUserDetailsService)
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
}
