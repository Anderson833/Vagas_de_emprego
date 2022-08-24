package br.edu.ifrn.vagas.InicioController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller    

public class InicioController {
    
	@GetMapping("/")//Método para inicia o sistema com a pagina de inicio
	 public String iniciaSistema() {
		 return "view/Inicio";
	 }

	
		@GetMapping("/login")//Método para inicia o sistema com a pagina de login
		 public String acessaLogin() {
			 return "view/login";
		 }
	//método para caso de error na autenticação de login exibir uma mensagem de error 
	@GetMapping("/login-Error")
	public String loginError(ModelMap model) {
		model.addAttribute("msgError"," Email ou  senha estão incorretos. Tente novamente!");
		return "view/login";
	}
	
	
}
