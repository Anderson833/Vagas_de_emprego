package br.edu.ifrn.vagas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller    
public class InicioController {
    
	@GetMapping("/")     //Método para inicia o sistema com a pagina de inicio
	 public String iniciaSistema() {
		 return "view/Inicio";
	 }
}
