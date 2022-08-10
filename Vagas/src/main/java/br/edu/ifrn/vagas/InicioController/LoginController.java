package br.edu.ifrn.vagas.InicioController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	 @GetMapping("/entrar")
	public String inicioLogin() {
		 return "view/login";
	}
}
