package br.edu.ifrn.vagas.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifrn.vagas.Model.Vagas;

public interface VagasRepository extends JpaRepository<Vagas, Integer> {
     // BUSCANDO O ESTADO,CIDADE, AREA 
	@Query("select u from Vagas u where u.estado like %:estado%"+
			" and u.cidade like %:cidade% and u.area like %:area%")
	List<Vagas> findByEstadoAndCidadeAndArea(@Param("estado")String estado,
			                         @Param("cidade")String cidade,
			                         @Param("area")String area);
	
	
	// BUSCANDO O TITULO DA VAGA 
	@Query("select v from Vagas v where v.titulo like %:titulo%")
	 List<Vagas> findByTitulo(@Param("titulo")String titulo);
}
