package dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "USUARIOLIMITE")
public class UsuarioLimite implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_UsuarioLimite")
	@SequenceGenerator(name = "seq_UsuarioLimite", sequenceName = "s_UsuarioLimite", allocationSize = 1)
	private Long CODIGOUSUARIOLIMITE;
	
	@Column(length = 150, nullable = false)
	private String DESCRICAO;
	
	@Column(nullable = false)
	private BigDecimal QUANTIDADE;
	
	@Column(nullable = false)
	private boolean ATIVO;
	
	@OneToOne(mappedBy = "CODIGOUSUARIOLIMITE")
	private Usuario CODIGOUSUARIO;
	
	@CreationTimestamp
	private LocalDateTime DATACRIACAO;
	
	public UsuarioLimite() {
		this.DATACRIACAO = LocalDateTime.now();
	}

	public UsuarioLimite(String descricao, BigDecimal quantidade, boolean ativo) {
		this.DESCRICAO = descricao;
		this.QUANTIDADE = quantidade;
		this.ATIVO = ativo;
		this.DATACRIACAO = LocalDateTime.now();
	}

	public Long getId() {
		return CODIGOUSUARIOLIMITE;
	}

	public void setId(Long id) {
		this.CODIGOUSUARIOLIMITE = id;
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

	public Usuario getUsuario() {
		return CODIGOUSUARIO;
	}

	public void setUsuario(Usuario usuario) {
		this.CODIGOUSUARIO = usuario;
	}

	public boolean isAtivo() {
		return ATIVO;
	}

	public void setAtivo(boolean ativo) {
		this.ATIVO = ativo;
	}

	public LocalDateTime getDataCriacao() {
		return DATACRIACAO;
	}
	
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.DATACRIACAO = dataCriacao;
	}
}
