package br.edu.ifrn.vagas.InicioController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/principal")
public class LoginController {

	 @GetMapping("/entrar")
	public String inicioLogin() {
		 return "view/login";
	}
	//método para abrir  a página de opções de cadastros
		@GetMapping("/opcao")
		  public String opcaoCadastro() {
			  return "view/opcaoCadastro";
		  }
}
