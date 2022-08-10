package br.edu.ifrn.vagas.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifrn.vagas.Repositorios.UsuarioRepository;
import br.edu.ifrn.vagas.UsuarioService.UsuarioService;




@EnableGlobalMethodSecurity(prePostEnabled =true )
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().permitAll();
		http.authorizeRequests()
				//.antMatchers("").permitAll() //as urls que tem acessor sem autenticação
				
				.anyRequest().authenticated() //Apenas Permiter o acesso as demais urls só depois que tive feito o login  
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/",true)
				.failureUrl("/login-Error")
				.permitAll()
				.and()
			.logout()
			.logoutSuccessUrl("/")
			.and()
			.rememberMe(); // isso serve com objetivo de capturar os dados de login salvo ao ter acessado a primeira vez
	}
	@Override
	 // especificando a forma de criptografia da senha para se usada 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
