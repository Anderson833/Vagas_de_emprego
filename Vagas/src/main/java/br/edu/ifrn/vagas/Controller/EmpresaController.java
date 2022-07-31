package br.edu.ifrn.vagas.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.vagas.Model.Empresa;

@Controller
@RequestMapping("/Empresa")   // url de inicio para junta com outra de cada método especifico
public class EmpresaController {
  
	@GetMapping("/inicio")
	public String inicioCadastro() {
		return "view/CadastraEmpresa";
	}
	// método para abrir a página de cadastrar os dados da empresa
	@GetMapping("/cadastro")
	public String iniciaCadastro(ModelMap model) {
		model.addAttribute("empresa", new Empresa());
		return "view/CadastraEmpresa";
	}
	
	@PostMapping("/salvar")
	public String salvar(Empresa empresa, RedirectAttributes at, HttpSession ss){
		return "view/CadastraEmpresa";
	}
}
