package br.edu.ifrn.vagas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.vagas.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
