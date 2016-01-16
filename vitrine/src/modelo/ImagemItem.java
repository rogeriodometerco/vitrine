package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;


@Entity
public class ImagemItem implements Serializable {

	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "item_id")
	@JsonBackReference
	private Item item;

	@Lob
	@Column(name="imagem")
	private String imagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
