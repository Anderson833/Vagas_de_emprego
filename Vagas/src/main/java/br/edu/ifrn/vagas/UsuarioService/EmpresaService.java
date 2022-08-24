package br.edu.ifrn.vagas.UsuarioService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifrn.vagas.Model.Empresa;
import br.edu.ifrn.vagas.Repositorios.EmpresaRepository;
@Service   
public class EmpresaService implements UserDetailsService {

	@Autowired
	private EmpresaRepository empresaRp;   // instância do repository de EmpresaRepository 
	@Override    
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   
		// passando os dados do EmpresaRepository para objeto empresa  
		Empresa empresa =empresaRp.findByEmail(username)
				                       // método para caso não encontre os dados da empresa
				 .orElseThrow(()-> new UsernameNotFoundException("Empresa não encontrado"));
		return new User(
				 //conseguindo os dados da empresa como email e senha
				empresa.getEmail(),
				empresa.getSenha(),
			        AuthorityUtils.createAuthorityList(empresa.getPerfilEmpresa())
				
				);
	
	}

}
