package br.edu.ifrn.vagas.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifrn.vagas.Model.Empresa;
import br.edu.ifrn.vagas.Model.Usuario;
import br.edu.ifrn.vagas.UsuarioService.EmpresaService;
import br.edu.ifrn.vagas.UsuarioService.Service;




@EnableGlobalMethodSecurity(prePostEnabled =true )
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private Service service;
	
	@Autowired
	private EmpresaService   empresaSv;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().permitAll();
		http.authorizeRequests()
				.antMatchers("/principal/opcao","/Usuario/cadastro","/Usuario/salvar",
						"/Empresa/cadastro","/Empresa/salvar").permitAll() //as urls que tem acessor sem autenticação
			
				
				// configuração das url para os usuários em comum
			.antMatchers("/Usuario/opcao").hasAuthority(Usuario.usuario_comum)
				
				//configuração das url para quem tem o perfil de admin 
			.antMatchers("/Empresa/opcao").hasAuthority(Empresa.admin)
				
	    .anyRequest().authenticated() 
				//Apenas Permiter o acesso as demais urls só depois que tive feito o login  
				.and()  
			.formLogin()
				.loginPage("/login")  
				.defaultSuccessUrl("/",true)
				.failureUrl("/login-Error")  // caso de error ao fazer login o  sistema retorna para essa página de error
				.permitAll()
				.and()
			.logout()
			.logoutSuccessUrl("/") //url para caso os dados do login estejão corretos
			.and()
			.rememberMe(); // isso serve com objetivo de capturar os dados de login salvo ao ter acessado a primeira vez
	}
	
	@Override
	 // especificando a forma de criptografia da senha para se usada 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  // Método para pegar os dados de email e senha do usuário  que está na classe service  
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
		
		//Método para fazer a buscar dos dados da empresa como email e senha na classe EmpresaService 
		auth.userDetailsService(empresaSv).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}
