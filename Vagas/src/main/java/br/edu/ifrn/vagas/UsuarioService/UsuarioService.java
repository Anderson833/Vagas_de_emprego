package br.edu.ifrn.vagas.UsuarioService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifrn.vagas.Model.Usuario;
import br.edu.ifrn.vagas.Repositorios.UsuarioRepository;



@Service
public class UsuarioService implements UserDetailsService {
     
	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Usuario usuario = repository.findByEmail(username)
				 .orElseThrow(()-> new UsernameNotFoundException("Usuario não encontrado"));
		return new User(
				usuario.getEmail(),
				usuario.getSenha(),
			new	ArrayList<>()
				
				
				);
		
	     }
	}
	

