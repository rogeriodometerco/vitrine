package modelo;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import adaptador.DateAdapter;



@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "item")
public class Item implements Serializable {

	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "estabelecimentoId")
	private Estabelecimento estabelecimento;	
	//@Column(name="estabelecimentoId", nullable=false, unique=false)
	//private Long EstabelecimentoId;

	private String codigo;

	private String titulo;

	//@ManyToOne
	//@JoinColumn(name = "usuarioIdCriacao")
	//private Usuario usuarioCriacao;
	@Column(name="usuarioIdCriacao", nullable=false, unique=false)
	private Long usuarioIdCriacao;

	private Date dataCriacao;

	//@ManyToOne
	//@JoinColumn(name = "usuarioIdTransacao")
	//private Usuario usuarioTransacao;
	@Column(name="usuarioIdTransacao", nullable=false, unique=false)
	private Long usuarioIdTransacao;

	private Date dataTransacao;

	private Boolean excluido;

	private String descricao;

	private BigDecimal preco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
/*
	public Long getEstabelecimentoId() {
		return EstabelecimentoId;
	}

	public void setEstabelecimentoId(Long estabelecimentoId) {
		EstabelecimentoId = estabelecimentoId;
	}
*/
	
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

	public Long getUsuarioIdCriacao() {
		return usuarioIdCriacao;
	}

	public void setUsuarioIdCriacao(Long usuarioIdCriacao) {
		this.usuarioIdCriacao = usuarioIdCriacao;
	}

	@XmlElement(name = "dataCriacao")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getUsuarioIdTransacao() {
		return usuarioIdTransacao;
	}

	public void setUsuarioIdTransacao(Long usuarioIdTransacao) {
		this.usuarioIdTransacao = usuarioIdTransacao;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Boolean getExcluido() {
		return excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
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

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" +
				"id = "                  + getId() +                  ", " +
				"codigo = "              + getCodigo() +              ", " +
				"titulo = "              + getTitulo() +              ", " +
				//"estabelecimentoId = "   + getEstabelecimentoId() +   ", " +
				"estabelecimentoId = "   + estabelecimento.getId() +   ", " +
				"usuarioIdCriacao  = "   + getUsuarioIdCriacao() +    ", " +
				"dataCriacao = "         + getDataCriacao() +         ", " +
				"usuarioIdTransacao  = " + getUsuarioIdTransacao() +  ", " +
				"dataTransacao = "       + getDataTransacao() +       ", " +
				"preco = "               + getPreco() +               ", " +
				"descricao = "           + getDescricao() +
				")";
	}

}
