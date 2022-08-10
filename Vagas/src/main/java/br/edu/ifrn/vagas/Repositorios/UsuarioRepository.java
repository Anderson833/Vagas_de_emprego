package br.edu.ifrn.vagas.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.vagas.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query("select u from Usuario u where u.nome like %:nome%"+
			" and u.email like %:email%")
	List<Usuario> findByNomeAndEmail(@Param("nome")String nome,
			                         @Param("email")String email);
	
	@Query("select u from Usuario u where  u.email like %:email%")
	Optional<Usuario> findByEmail(@Param("email")String email);
}
