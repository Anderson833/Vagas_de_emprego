package br.edu.ifrn.vagas.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.vagas.Model.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo,Long>{

}
