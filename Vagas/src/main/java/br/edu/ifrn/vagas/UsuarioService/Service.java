package br.edu.ifrn.vagas.UsuarioService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.ifrn.vagas.Model.Usuario;
import br.edu.ifrn.vagas.Repositorios.EmpresaRepository;
import br.edu.ifrn.vagas.Repositorios.UsuarioRepository;

        // Essa classe  vai servi para saber se o usuário fez o login corretamente 

@org.springframework.stereotype.Service
public class Service implements UserDetailsService {
     
	@Autowired
	private UsuarioRepository repository;  // Instância do repository do UsuarioRepository 
    
	
	@Override            // a classe se vai servi para saber se o usuário fez o login corretamente    
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	         
		// Pegando os dados  do usuário no parâmetro 
		Usuario usuario = repository.findByEmail(username)
		
				 .orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado"));
		return new User(
				// obtendo os dados de usuário como email e senha
				usuario.getEmail(),
				usuario.getSenha(),
		           AuthorityUtils.createAuthorityList(usuario.getPerfil())
				);
		
	     }
	
	
	   
	}
	

