package dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Usuario")
	@SequenceGenerator(name = "seq_Usuario", sequenceName = "s_Usuario", allocationSize = 1)
	private Long CODIGOUSUARIO;
	
	@Column(length = 150, nullable = false)
	private String NOME;
	
	@Column(length = 150, nullable = false)
	private String EMAIL;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CODIGOUSUARIOLIMITE", referencedColumnName = "CODIGOUSUARIOLIMITE", nullable = true, unique=true)
	private UsuarioLimite CODIGOUSUARIOLIMITE;
	
	@OneToMany(mappedBy = "CODIGOUSUARIO", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Despesa> despesasUsuario = new ArrayList<>();
	
	@Column(nullable = false)
	private LocalDate DATANASCIMENTO;
	
	@CreationTimestamp
	private LocalDateTime DATACRIACAO;
	
	public Usuario() {
		this.DATACRIACAO = LocalDateTime.now();
	}

	public Usuario(String nome, String email, LocalDate dataNascimento) {
		this.NOME = nome;
		this.EMAIL = email;
		this.DATANASCIMENTO = dataNascimento;
		this.DATACRIACAO = LocalDateTime.now();
	}

	public Long getId() {
		return CODIGOUSUARIO;
	}

	public void setId(Long codigousuario) {
		this.CODIGOUSUARIO = codigousuario;
	}

	public String getNome() {
		return NOME;
	}

	public void setNome(String nome) {
		this.NOME = nome;
	}

	public String getEmail() {
		return EMAIL;
	}

	public void setEmail(String email) {
		this.EMAIL = email;
	}

	public LocalDate getDataNascimento() {
		return DATANASCIMENTO;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.DATANASCIMENTO = dataNascimento;
	}

	public UsuarioLimite getLimite() {
		return CODIGOUSUARIOLIMITE;
	}

	public void setLimite(UsuarioLimite usuarioLimite) {
		this.CODIGOUSUARIOLIMITE = usuarioLimite;
	}

	public LocalDateTime getDataCriacao() {
		return DATACRIACAO;
	}	
	
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.DATACRIACAO = dataCriacao;
	}
	
	public List<Despesa> getDespesas() {
		return despesasUsuario;
	}
	
	public void setDespesas(List<Despesa> despesas) {
		this.despesasUsuario = despesas;
	}
	public void addDespesa(Despesa despesa) {
		this.despesasUsuario.add(despesa);
		despesa.setCodigoUsuario(this);
	}
	
	public void removeDespesa(Despesa despesa) {
		this.despesasUsuario.remove(despesa);
		despesa.setCodigoUsuario(null);
	}
}