package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="senha", nullable=false)
	private String senha;

	@Column(name="nome")
	private String nome;
	
	@Column(name="role")
	private String role;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
