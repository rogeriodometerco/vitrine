package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonManagedReference;


@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "estabelecimento_id")
	private Estabelecimento estabelecimento;

	@Column(name="codigo")
	private String codigo;

	@Column(name="titulo", nullable=false)
	private String titulo;

	@Column(name="descricao")
	private String descricao;

	@Column(name="preco")
	private BigDecimal preco;

	@Lob
	@Column(name="imagem")
	private String imagem;
	
	@OneToMany(mappedBy="item", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonManagedReference 
	private List<ImagemItem> imagens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public List<ImagemItem> getImagens() {
		return imagens;
	}

	public void setImagens(List<ImagemItem> imagens) {
		this.imagens = imagens;
	}

}
