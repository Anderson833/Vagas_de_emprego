package br.edu.ifrn.vagas.VagasController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.edu.ifrn.vagas.Model.Vagas;
import br.edu.ifrn.vagas.Repositorios.VagasRepository;

@Controller  //método para deixar a class VagasController  ficar responsável pelo controler de todas operações dinamica que pertence a vagas 
 @RequestMapping("/Vagas") // Url para inicia a pagina com outra url de um método especifico
 class VagasController {
     
	@Autowired   //Instância, criar um objeto do repository vagas para realiza as operações  dos dados para banco de dados
	private VagasRepository vagasRp;
	
	//método para abrir a página de lista 
	 @GetMapping("/listaDeVagas")
	 public String  inicioVagas() {
		 return "view/listaVagas";
	 }
	   // método para abrir a pagina de cadastro de vagas e passar o objeto da classe Vagas
		@GetMapping("/cadastro")
		@PreAuthorize("hasAuthority('admin')")
		public String cadastroVagas(ModelMap model) {
			model.addAttribute("vagas", new Vagas());
			return "view/Vagas";
		}
		//método para salvar as vagas na tabela vagas no banco de dados
		@PostMapping("/salvar")
		@PreAuthorize("hasAuthority('admin')")  //permitindo o acessor  apenas para quem tem o perfil de admin
		public String salvaVagas(Vagas vaga, RedirectAttributes att, ModelMap model) {
			  
			// atribuindo uma mensagem de sucesso para página html de vagas 
			att.addFlashAttribute("msgSucesso","Vaga cadastrada com sucesso!");
			// salvando os dados na tabela vagas no banco de dados mysql
			vagasRp.save(vaga);
			
			// redirecionado para a página html de vagas
			return "redirect:/Vagas/cadastro";
		}
		
	
	    
	 // método para listar as vagas
		@GetMapping("/listaVgs")                  //passando os dados como parâmetros para lista os dados das vagas	
		public String listarVagas(@RequestParam(name="estado" ,required = false) String estado,
					                     @RequestParam(name="cidade", required = false) String cidade,
					                     @RequestParam(name="area", required = false) String area,
					                     @RequestParam(name="listarTodasVagas", required = false) Boolean listarTodasVagas, HttpSession session, ModelMap model) {
			
                       // Passando uma lista  como parâmetros os dados que pertence a vaga para criar um objeto
		    	List<Vagas> listaVagas = vagasRp.findByEstadoAndCidadeAndArea(estado,cidade,area);
		      
		    	// atribuindo o objeto do tipo vaga
		        model.addAttribute("listaVagas",listaVagas);
	    
		        // condição para saber se a lista de vagas está sem dados 
		   if(listarTodasVagas != null) {
			   
			   //atribuindo a lista de vagas 
			   model.addAttribute("listarTodasVagas",true);
		   }
		   // redirecionado para página de lista de vagas
		    return "view/ListaVagas";
				
				
			}
		
		
		
		
		  //método para editat vagas
		@GetMapping("/editarVaga/{id}")
		@PreAuthorize("hasAuthority('admin')")
		 public String edicao(@PathVariable("id") Integer idVaga,
				               ModelMap model, RedirectAttributes at) {
 // Passando o objeto do tipo vaga para o objeto do repository vagas armazenando numa variável vg do tipo vaga
		 Vagas vg = vagasRp.findById(idVaga).get();
	
	        // atribuindo o objeto vaga para a página html
			 model.addAttribute("vagas",vg);
			 
			 //atribuindo uma mensagem para a página html de vagas
			 at.addFlashAttribute("msgSucesso","Vaga editada com sucesso!");
			 
			 // redirecionar para a página de lista de vagas
			 return "redirect:/Vagas/listaVgs";

		 }
		//método para excluir vagas pelo id
		 @GetMapping("/excluir/{id}")
		 @PreAuthorize("hasAuthority('admin')")
		 public String deletar(@PathVariable("id") Integer idVaga ,RedirectAttributes at) {
		            
		     
			  // com esse método já deleta a vaga 
			     vagasRp.deleteById(idVaga);
			
			       // atribuindo uma mensagem de sucesso para a página html de lista vagas
				 at.addFlashAttribute("msgSucesso","Vaga deletada com sucesso");
			
			  // redirecionando a página para 
			 return "redirect:/Vagas/listaVgs?estado=&cidade=&area=";
		 }
			
		
		 
		
		 
		 
		
}
