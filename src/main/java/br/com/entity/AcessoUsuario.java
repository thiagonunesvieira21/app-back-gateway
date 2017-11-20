package br.com.entity;
// Generated 17/11/2015 23:54:02 by Hibernate Tools 4.3.1.Final

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import java.util.*;

/**
 * AcessoUsuario generated by hbm2java
 */
@Entity
@Table(name = "acesso_usuario", schema = "suporte", uniqueConstraints = @UniqueConstraint(columnNames = "de_login") )
@SequenceGenerator(name="seq_acesso_usuario", sequenceName="suporte.seq_nu_acesso_usuario",initialValue=1, allocationSize=1)
public class AcessoUsuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idUsuario;
	private String nome;
	private String deLogin;
	private String email;
	private String cpf;
	private String rg;
	private String rgOrgaoEmissor;
	private Date rgDataEmissao;
	private Date dataNascimento;
	private String nroTelFixo;
	private String nroCelular;
	private String sexo;
	private Date dtHrInclusao;
	private Integer status;
	private Date dtHrStatus;
	private Date dtHrUltimoAcesso;
	private String senha;
	private Boolean senhaExpirada;
	private Date dtHrUltimaTrocaSenha;
	private List<AcessoGrupo> acessoGrupos = new ArrayList<AcessoGrupo>();
	private Set<AcessoHistoricoSenha> acessoHistoricoSenhas = new HashSet<AcessoHistoricoSenha>();

	public AcessoUsuario() {
	}

	public AcessoUsuario(Integer idUsuario, String nome, String deLogin, String cpf, String email, String nroCelular, String sexo,
						 Date dtHrInclusao, Integer status, Date dtHrStatus, String senha, Boolean senhaExpirada) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.deLogin = deLogin;
		this.email = email;
		this.cpf = cpf;
		this.nroCelular = nroCelular;
		this.sexo = sexo;
		this.dtHrInclusao = dtHrInclusao;
		this.status = status;
		this.dtHrStatus = dtHrStatus;
		this.senha = senha;
		this.senhaExpirada = senhaExpirada;
	}

	public AcessoUsuario(Integer idUsuario, String nome, String deLogin, String email, String cpf, String nroTelFixo,
						 String nroCelular, String sexo, Date dtHrInclusao, Integer status, Date dtHrStatus,
						 Date dtHrUltimoAcesso, String senha, Date dtHrUltimaTrocaSenha, List<AcessoGrupo> acessoGrupos,
						 Set<AcessoHistoricoSenha> acessoHistoricoSenhas) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.deLogin = deLogin;
		this.email = email;
		this.cpf = cpf;
		this.nroTelFixo = nroTelFixo;
		this.nroCelular = nroCelular;
		this.sexo = sexo;
		this.dtHrInclusao = dtHrInclusao;
		this.status = status;
		this.dtHrStatus = dtHrStatus;
		this.dtHrUltimoAcesso = dtHrUltimoAcesso;
		this.senha = senha;
		this.dtHrUltimaTrocaSenha = dtHrUltimaTrocaSenha;
		this.acessoGrupos = acessoGrupos;
		this.acessoHistoricoSenhas = acessoHistoricoSenhas;
	}
	
	public AcessoUsuario(AcessoUsuario usuario) {
		this.idUsuario = usuario.idUsuario;
		this.nome = usuario.nome;
		this.deLogin = usuario.deLogin;
		this.email = usuario.email;
		this.cpf = usuario.cpf;
		this.nroTelFixo = usuario.nroTelFixo;
		this.nroCelular = usuario.nroCelular;
		this.sexo = usuario.sexo;
		this.dtHrInclusao = usuario.dtHrInclusao;
		this.status = usuario.status;
		this.dtHrStatus = usuario.dtHrStatus;
		this.dtHrUltimoAcesso = usuario.dtHrUltimoAcesso;
		this.senha = usuario.senha;
		this.dtHrUltimaTrocaSenha = usuario.dtHrUltimaTrocaSenha;
		this.acessoGrupos = usuario.acessoGrupos;
		this.senhaExpirada = usuario.senhaExpirada;
		this.acessoHistoricoSenhas = usuario.acessoHistoricoSenhas;
	}
	
	

	@Id
	@Column(name = "nu_usuario", unique = true, nullable = false, precision = 6, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso_usuario")
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "no_usuario", nullable = false, length = 80)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "de_login", unique = true, nullable = false, length = 80)
	public String getDeLogin() {
		return this.deLogin;
	}

	public void setDeLogin(String deLogin) {
		this.deLogin = deLogin;
	}

	@Column(name = "de_email", nullable = false, length = 100)
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "de_cpf", nullable = false, precision = 11, scale = 0)
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "de_telefone_fixo")
	public String getNroTelFixo() {
		return this.nroTelFixo;
	}

	public void setNroTelFixo(String nroTelFixo) {
		this.nroTelFixo = nroTelFixo;
	}

	@Column(name = "de_celular", nullable = false)
	public String getNroCelular() {
		return this.nroCelular;
	}

	public void setNroCelular(String nroCelular) {
		this.nroCelular = nroCelular;
	}

	@Column(name = "ic_sexo", nullable = false)
	@JsonIgnore
	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_inclusao", nullable = false, length = 29)
	public Date getDtHrInclusao() {
		return this.dtHrInclusao;
	}

	public void setDtHrInclusao(Date dtHrInclusao) {
		this.dtHrInclusao = dtHrInclusao;
	}

	@Column(name = "ic_status", nullable = false, precision = 1, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_status", nullable = false, length = 29)
	public Date getDtHrStatus() {
		return this.dtHrStatus;
	}

	public void setDtHrStatus(Date dtHrStatus) {
		this.dtHrStatus = dtHrStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_ultimo_acesso", length = 29)
	public Date getDtHrUltimoAcesso() {
		return this.dtHrUltimoAcesso;
	}

	public void setDtHrUltimoAcesso(Date dtHrUltimoAcesso) {
		this.dtHrUltimoAcesso = dtHrUltimoAcesso;
	}

	@Column(name = "de_senha", nullable = false, length = 100)
	@JsonIgnore
	@ApiIgnore
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Column(name = "de_senha_expirada")
	public Boolean getSenhaExpirada() {
		return this.senhaExpirada;
	}
	
	public void setSenhaExpirada(Boolean senhaExpirada) {
		this.senhaExpirada = senhaExpirada;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_ultima_troca_senha", length = 29)
	public Date getDtHrUltimaTrocaSenha() {
		return this.dtHrUltimaTrocaSenha;
	}

	public void setDtHrUltimaTrocaSenha(Date dtHrUltimaTrocaSenha) {
		this.dtHrUltimaTrocaSenha = dtHrUltimaTrocaSenha;
	}

	@Column(name = "de_identidade")
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Column(name = "no_orgao_emissor", nullable = false)
	public String getRgOrgaoEmissor() {
		return rgOrgaoEmissor;
	}

	public void setRgOrgaoEmissor(String rgOrgaoEmissor) {
		this.rgOrgaoEmissor = rgOrgaoEmissor;
	}

	@Column(name = "dt_emissao_identidade", nullable = false)
	public Date getRgDataEmissao() {
		return rgDataEmissao;
	}


	public void setRgDataEmissao(Date rgDataEmissao) {
		this.rgDataEmissao = rgDataEmissao;
	}

	@Column(name = "dt_nascimento")
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name = "acesso_grupo_usuario", schema = "suporte", joinColumns = {
			@JoinColumn(name = "nu_usuario", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "nu_grupo", nullable = false, updatable = false) })
	public List<AcessoGrupo> getAcessoGrupos() {
		return this.acessoGrupos;
	}

	public void setAcessoGrupos(List<AcessoGrupo> acessoGrupos) {
		this.acessoGrupos = acessoGrupos;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acessoUsuario", cascade= CascadeType.ALL)
	public Set<AcessoHistoricoSenha> getAcessoHistoricoSenhas() {
		return this.acessoHistoricoSenhas;
	}

	public void setAcessoHistoricoSenhas(Set<AcessoHistoricoSenha> acessoHistoricoSenhas) {
		this.acessoHistoricoSenhas = acessoHistoricoSenhas;
	}

	@PrePersist
	  protected void onCreate() {
		this.dtHrInclusao = new Date();
		this.dtHrStatus = new Date();
	  }
	
	public void addGrupo(AcessoGrupo grupo) {
		if(acessoGrupos == null) {
			acessoGrupos = new ArrayList<>();
		}
		acessoGrupos.add(grupo);
	}
}