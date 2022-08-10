package br.edu.ifrn.vagas.UsuarioController;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.vagas.Model.Usuario;
import br.edu.ifrn.vagas.Repositorios.UsuarioRepository;

@Controller
@RequestMapping("/Lista")
public class ListaUsuario {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	//método que inicia a pagina de listagem ou de busca
	 @GetMapping("/Listar")
	public String iniciolista() {
		return "view/ListaUsuario";
	}
	 // método para lista os usuarios

	@GetMapping("/listagem")                  //passando os dados como parametros para lista os dados
		public String listarUsuario(@RequestParam(name="nome" ,required = false) String nome,
				                     @RequestParam(name="email", required = false) String email,
				                     @RequestParam(name="listarTodosUsuarios", required = false) Boolean listarTodosUsuarios, ModelMap model) {
		
	
		    	List<Usuario> listaUsuarios =  usuarioRepository.findByNomeAndEmail(nome, email);
		      
		        model.addAttribute("listaUsuarios",listaUsuarios);
           
		   if(listarTodosUsuarios != null) {
			   model.addAttribute("listarTodosUsuarios",true);
		   }
              
	    return "view/ListaUsuario";
			
	 }
		
	@GetMapping("/adicionar/{id}")
	 public String edicao(@PathVariable("id") Integer idUsu,
			               ModelMap model,RedirectAttributes at) {
		 
	 Usuario usu = usuarioRepository.findById(idUsu).get();
             
	 at.addAttribute("msgSucesso","Usuário adicionar com sucesso");
	 
		 model.addAttribute("usuario",usu);
		  
		 return "redirect:/Lista/Listar";

	 }

	 
	 @GetMapping("/deletar/{id}")
	 public String deletar(@PathVariable("id") Integer idusuario , RedirectAttributes at) {
	            
		     usuarioRepository.deleteById(idusuario);
		
			 at.addFlashAttribute("msgSucesso","deletado com sucesso");
		
		 
		 return "redirect:/Lista/Listar";
		 
	 }
}
