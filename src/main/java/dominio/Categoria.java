package dominio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Categoria")
	@SequenceGenerator(name = "seq_Categoria", sequenceName = "s_Categoria", allocationSize = 1)
	private Long CODIGOCATEGORIA;
	
	@Column(length = 150, nullable = false)
	private String DESCRICAO;
	
	@ManyToMany(mappedBy = "categorias")
	private List<Despesa> despesas = new ArrayList<>();
	
	@CreationTimestamp
	private LocalDateTime DATACRIACAO;
	
	public Categoria() {
		this.DATACRIACAO = LocalDateTime.now();
	}
	
	public Categoria(String descricao) {
		this.DESCRICAO = descricao;
		this.DATACRIACAO = LocalDateTime.now();
	}

	public Long getCODIGOCATEGORIA() {
		return CODIGOCATEGORIA;
	}

	public void setCodigoCategoria(Long codigoCategoria) {
		this.CODIGOCATEGORIA = codigoCategoria;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String descricao) {
		this.DESCRICAO = descricao;
	}

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public void setDespesa(List<Despesa> despesas) {
		this.despesas = despesas;
	}

	public LocalDateTime getDataCriacao() {
		return DATACRIACAO;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.DATACRIACAO = dataCriacao;
	}
	
}
