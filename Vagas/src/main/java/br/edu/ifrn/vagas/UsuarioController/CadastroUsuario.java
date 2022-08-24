package br.edu.ifrn.vagas.UsuarioController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.vagas.DTO.AutocompleteDTO;
import br.edu.ifrn.vagas.Model.Arquivo;
import br.edu.ifrn.vagas.Model.Usuario;
import br.edu.ifrn.vagas.Model.Vagas;
import br.edu.ifrn.vagas.Repositorios.ArquivoRepository;
import br.edu.ifrn.vagas.Repositorios.UsuarioRepository;
import br.edu.ifrn.vagas.Repositorios.VagasRepository;

@Controller
 @RequestMapping("/Usuario")  // Url de inicio para unir com outra url espefica de cada método
public class CadastroUsuario {
    
	@Autowired
	private UsuarioRepository UsuarioRepository;
	
	@Autowired
	private VagasRepository vagasRepository;
	
	@Autowired
	private ArquivoRepository arquivoRp;
	
	 // método para abrir a página de cadastro de usuario
	@GetMapping("/cadastro")
	public String iniciaCadastro(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		return "view/CadastroUsuario";
	}
	
	                             
	 @PostMapping("/salvar")  //método para salvar usuario no banco de dados na tebela usuario
	 @Transactional(readOnly = false)
	public String salvarUsuario(Usuario usuario, RedirectAttributes at,
	@RequestParam("file") MultipartFile  arquivo) {
		
		try {
			
			if(arquivo != null && !arquivo.isEmpty()) {
				// realizando a norma do nome do arquivo
				String nomeArquivo = 
						StringUtils.cleanPath(arquivo.getOriginalFilename());
				
				//instância da classe Arquivo, atribuindo os dados como parâmetros
				Arquivo arquivoBD = new Arquivo(nomeArquivo, arquivo.getContentType(), arquivo.getBytes());
				
				//salvando o arquivo no banco
				arquivoRp.save(arquivoBD);
			
				// caso tenha outro arquivo salvo, remover
				
				if(usuario.getFoto() != null 
						&& usuario.getFoto().getId() > 0){
					
				  arquivoRp.delete(usuario.getFoto());
				  
				}       //salvando um nova arquivo no Usuário
				        usuario.setFoto(arquivoBD);
				
			}else {
				
				usuario.setFoto(null);
			
			}
		 // criptografia para senha Segura do usuário
		String senhaSegura = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaSegura);
		 
		// Passando a mensagem para o usuário
		at.addFlashAttribute("msgSucesso", "Usuário salvo com sucesso!");
		//inserir os dados do usuário no banco de dados
		UsuarioRepository.save(usuario);
	
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	   return "redirect:/Usuario/cadastro";
		
	 } 
	//método que inicia a pagina de listagem ou de busca
		 @GetMapping("/Listar")
		public String iniciolista() {
			return "view/ListaUsuario";
		}
	 
	 // método para lista os usuarios
	@GetMapping("/listagem")                  //passando os dados como parametros para lista os dados
		public String listarUsuario(@RequestParam(name="nome" ,required = false) String nome,
				                     @RequestParam(name="email", required = false) String email,
				                     @RequestParam(name="listarTodosUsuarios", required = false) Boolean listarTodosUsuarios, HttpSession session, ModelMap model) {
		

	    	List<Usuario> listaUsuarios = UsuarioRepository.findByNomeAndEmail(nome, email);
	      
	        model.addAttribute("listaUsuarios",listaUsuarios);
    
	   if(listarTodosUsuarios != null) {
		   model.addAttribute("listarTodosUsuarios",true);
	   }
	    return "view/ListaUsuario";
			
			
		}
	
        //MÉTODO PARA ADICIONAR DADOS PELO ID DO USUÁRIO
		@GetMapping("/adicionar/{id}")
		 public String edicao(@PathVariable("id") Integer idUsu,
				               ModelMap model) {
			 
		 Usuario usu = UsuarioRepository.findById(idUsu).get();
	
			 model.addAttribute("usuario",usu);
			  
			 return "view/CadastroUsuario";

		 }
		
		//MÉTODO PARA EXCLUIR DADOS PELO ID DO USUÁRIO
		 @GetMapping("/excluir/{id}")
		 public String deletar(@PathVariable("id") Integer idusuario ,RedirectAttributes at) {
		            
			     UsuarioRepository.deleteById(idusuario);
			
				 at.addFlashAttribute("msgSucesso","deletado com sucesso");
			
			 
			 return "redirect:/Lista/listagem?nome=&email=";
			 
		 }
		 
		 // método para abrir a pagina de se inscrever nas vagas e passar o objeto usuario
			@GetMapping("/inscricao")
			public String inscricaoVagas(ModelMap model) {
				model.addAttribute("usuario", new Usuario());
				return "view/InscricaoVagas";
			}
			
			@GetMapping("/autocompleteVagas")
			@Transactional(readOnly = true)
			@ResponseBody
			public List<AutocompleteDTO>  autocompleteVagas(
					@RequestParam("term")String termo){
				
				 List<Vagas> vaga =vagasRepository.findByTitulo(termo);
				 
				 List<AutocompleteDTO> eficacia = new ArrayList<>();
				 vaga.forEach(v -> eficacia.add(new AutocompleteDTO(v.getTitulo(),v.getId())));
				 
				 return eficacia;
			}
			
			// método para abrir a página para as ações de usuário
			@GetMapping("/opcao")
			public String opcaoUsuario() {
				return "view/OpcaoUsuario";
			}
}
