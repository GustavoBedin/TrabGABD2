package dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "DESPESA")
public class Despesa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Despesa")
	@SequenceGenerator(name = "seq_Despesa", sequenceName = "s_Despesa", allocationSize = 1)
	private Long CODIGODESPESA;
	
	@Column(length = 150, nullable = false)
	private String DESCRICAO;
	
	@Column(nullable = false)
	private BigDecimal QUANTIDADE;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario CODIGOUSUARIO;
	
	@ManyToMany
    @JoinTable(name="DESPESA_CATEGORIA", joinColumns=
    {@JoinColumn(name="CODIGODESPESA")}, inverseJoinColumns=
      {@JoinColumn(name="CODIGOCATEGORIA")})
	private List<Categoria> categorias = new ArrayList<>();
	
	@CreationTimestamp
	private LocalDateTime DATACRIACAO;

	public Despesa() {
		this.DATACRIACAO = LocalDateTime.now();
	}
	
	public Despesa(String descricao, BigDecimal quantidade) {
		this.DESCRICAO = descricao;
		this.QUANTIDADE = quantidade;
		this.DATACRIACAO = LocalDateTime.now();
	}

	public Long getCodigoDespesa() {
		return CODIGODESPESA;
	}

	public void setCodigoDespesa(Long codigodespesa) {
		this.CODIGODESPESA = codigodespesa;
	}

	public String getDescricao() {
		return DESCRICAO;
	}

	public void setDescricao(String descricao) {
		this.DESCRICAO = descricao;
	}

	public BigDecimal getQuantidade() {
		return QUANTIDADE;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.QUANTIDADE = quantidade;
	}

	public Usuario getCodigoUsuario() {
		return CODIGOUSUARIO;
	}

	public void setCodigoUsuario(Usuario codigousuario) {
		this.CODIGOUSUARIO = codigousuario;
	}

	public LocalDateTime getDataCriacao() {
		return DATACRIACAO;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.DATACRIACAO = dataCriacao;
	}
	
	public List<Categoria> getCategoria() {
		return categorias;
	}

	public void setCategoria(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public void addCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}
	
	public void removeCategoria(Categoria categoria) {
		this.categorias.remove(categoria);
	}
	
}