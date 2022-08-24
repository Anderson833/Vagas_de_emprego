package br.edu.ifrn.vagas.Model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Arquivo {
   
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long id;
	 
	  private String Nomearquivo;
	  private String tipoArquivo;
	  @Lob
	  @Basic(fetch = FetchType.LAZY)
	  private byte [] dados;
	  
	  public Arquivo(String Nomearquivo,String tipoArquivo,byte []dados) { 
		  this.Nomearquivo=Nomearquivo;
		  this.tipoArquivo=tipoArquivo;
		  this.dados=dados;
	  }
	  
	  public Arquivo() {
		  
	  }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomearquivo() {
		return Nomearquivo;
	}

	public void setNomearquivo(String nomearquivo) {
		Nomearquivo = nomearquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}
}
