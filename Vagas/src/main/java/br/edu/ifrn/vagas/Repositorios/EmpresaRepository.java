package br.edu.ifrn.vagas.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.vagas.Model.Empresa;
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    
	// realizando o comando para buscar pelo email na tabela de Empresa
	@Query("select u from Empresa u where  u.email like %:email%")
	 // Passando para o método findByEmail  como parâmento o email 
	Optional<Empresa> findByEmail(@Param("email")String email);
}
