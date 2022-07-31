package br.edu.ifrn.vagas.Model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// essa classe será para criar os atributos e métodos para usuario que deseja se inscrever na vaga

@Entity
public class Usuario {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	// @Column é para os dados não se inseridos nulos
	@Column (nullable = false)
	private String nome;
	
	@Column (nullable = false)
	private String cpf;
	
	@Column (nullable = false)
	private String telefone;
	
	@Column (nullable = false)
	private String sexo;
	@Column (nullable = false)
	private String cidade;
	
	@Column (nullable = false)
	private String email;
	
	@Column (nullable = false)
	private String senha;
	
	@Override
	public int hashCode() {
		return Objects.hash(cidade, cpf, curriculorPDF, email, id, nome, senha, sexo, telefone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cidade, other.cidade) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(curriculorPDF, other.curriculorPDF) && Objects.equals(email, other.email)
				&& id == other.id && Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha)
				&& Objects.equals(sexo, other.sexo) && Objects.equals(telefone, other.telefone);
	}
	@Column (nullable = false)
	private String curriculorPDF;
	
	//Métodos getters e setters 

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCurriculorPDF() {
		return curriculorPDF;
	}
	public void setCorriculoPDF(String curriculorPDF) {
		this.curriculorPDF = curriculorPDF;
	}
	
}
