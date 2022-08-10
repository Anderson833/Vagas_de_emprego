package br.edu.ifrn.vagas.VagasController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.vagas.Model.Usuario;
import br.edu.ifrn.vagas.Model.Vagas;
import br.edu.ifrn.vagas.Repositorios.VagasRepository;

@Controller
 @RequestMapping("/Vagas") // Url para inicia a pagina com outra url de um método especifico
 class VagasController {
     
	@Autowired
	private VagasRepository vagasRp;
	
	//método para abrir a página de lista 
	 @GetMapping("/listaDeVagas")
	 public String  inicioVagas() {
		 return "view/listaVagas";
	 }
	   // método para abrir a pagina de cadastro de vagas e passar o objeto da classe Vagas
		@GetMapping("/cadastro")
		public String cadastroVagas(ModelMap model) {
			model.addAttribute("vagas", new Vagas());
			return "view/Vagas";
		}
		//método para salvar as vagas na tabela vagas no banco de dados
		@PostMapping("/salvar")
		public String salvaVagas(Vagas vaga, RedirectAttributes att) {
			att.addFlashAttribute("msgSucesso","Vaga cadastrada com sucesso!");
			
			vagasRp.save(vaga);
			
			return "view/Vagas";
		}
		
	
	    
	 // método para listar as vagas
		@GetMapping("/listaVgs")                  //passando os dados como parâmetros para lista os dados das vagas
			public String listarVagas(@RequestParam(name="estado" ,required = false) String estado,
					                     @RequestParam(name="cidade", required = false) String cidade,
					                     @RequestParam(name="area", required = false) String area,
					                     @RequestParam(name="listarTodasVagas", required = false) Boolean listarTodasVagas, HttpSession session, ModelMap model) {
			

		    	List<Vagas> listaVagas = vagasRp.findByEstadoAndCidadeAndArea(estado,cidade,area);
		      
		        model.addAttribute("listaVagas",listaVagas);
	    
		   if(listarTodasVagas != null) {
			   model.addAttribute("listarTodasVagas",true);
		   }
		    return "view/ListaVagas";
				
				
			}
		  //método para editat vagas
		@GetMapping("/editarVaga/{id}")
		 public String edicao(@PathVariable("id") Integer idVaga,
				               ModelMap model) {
			 
		 Vagas vg = vagasRp.findById(idVaga).get();
	
			 model.addAttribute("usuario",vg);
			  
			 return "view/Vagas";

		 }
		//método para excluir vagas pelo id
		 @GetMapping("/excluirVaga/{id}")
		 public String deletar(@PathVariable("id") Integer idusuario ,RedirectAttributes at) {
		            
			     vagasRp.deleteById(idusuario);
			
				 at.addFlashAttribute("msgSucesso","Vaga deletada com sucesso");
			
			 
			 return "redirect:/Vagas/ListaDeVagas";
			 
		 }
}
