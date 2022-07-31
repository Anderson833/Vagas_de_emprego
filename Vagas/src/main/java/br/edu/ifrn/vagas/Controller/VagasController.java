package br.edu.ifrn.vagas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifrn.vagas.Model.Vagas;

@Controller
 @RequestMapping("/Vagas") // Url para inicia a pagina com outra url de um método especifico
 class VagasController {
     
	 @GetMapping("/dados")
	 public String  form() {
		 return "view/Vagas";
	 }
	   // método para abrir a pagina de cadastro de vagas
		@GetMapping("/cadastro")
		public String iniciaCadastro(ModelMap model) {
			model.addAttribute("vagas", new Vagas());
			return "view/Vagas";
		}
}
