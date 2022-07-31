package br.edu.ifrn.vagas.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.vagas.Model.Usuario;
import br.edu.ifrn.vagas.repositorios.UsuarioRepository;

@Controller
 @RequestMapping("/Usuario")  // Url de inicio para unir com outra url espefica de cada método
public class UsuarioController {
    
	@Autowired
	private UsuarioRepository Usuario;
	
	
	 // método para abrir a página de cadastra usuario
	@GetMapping("/cadastro")
	public String iniciaCadastro(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		return "view/CadastroUsuario";
	}
	
	//método para salvar usuario no banco de dados na tebela usuario
	 @RequestMapping(value = "/adicionar",method = RequestMethod.POST)
	public String salvarUsuario(Usuario usuario, RedirectAttributes at, HttpSession session) {
		Usuario.save(usuario);
		return "view/CadastroUsuario";
	}
}
