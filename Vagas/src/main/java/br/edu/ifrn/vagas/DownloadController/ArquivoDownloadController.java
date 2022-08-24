package br.edu.ifrn.vagas.DownloadController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifrn.vagas.Model.Arquivo;
import br.edu.ifrn.vagas.Repositorios.ArquivoRepository;



@Controller     // Esse class vai servi para fazer o download dos arquivos
public class ArquivoDownloadController {
	 @Autowired
		private ArquivoRepository arquivoRepositiry;
		
		
		 @GetMapping("/download/{idArquivo}") // url para baixar os arquivos
		public ResponseEntity<?> DownloadFile(
				       @PathVariable Long idArquivo,
				       @Param("salvar") String salvar
			       ){
			               //Passando o id do arquivo no parâmetro
			Arquivo  arquivoBD = arquivoRepositiry.findById(idArquivo).get();
			 
		String texto = (salvar == null || salvar.equals("true")) ?
				  "attachment ; filename=\"" + arquivoBD.getNomearquivo() +"\"" 
				  : "inline; filename=\"" + arquivoBD.getNomearquivo() +"\"";
				
		
	      return ResponseEntity.ok()
	    		  .contentType(MediaType.parseMediaType(arquivoBD.getTipoArquivo()))
	    		  .header(HttpHeaders.CONTENT_DISPOSITION, texto)
	              .body(new ByteArrayResource(arquivoBD.getDados()));
	      
	      
		}
}
