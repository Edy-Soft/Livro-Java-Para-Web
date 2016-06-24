package ao.co.always.financeiro.usuario;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id_usuario")
	private Integer codigo;
	
	private String nome;
	private String email;
	
	@org.hibernate.annotations.NaturalId
	private String login;
	
	private String senha;
	private Date nascimento;
	private String telefone;
	private String idioma;
	private boolean activo;
	
	@ElementCollection(targetClass = String.class)
	@JoinTable(
			name="usuario_permissao",
			uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario", "permissao"})},
			joinColumns = @JoinColumn(name="usuario"))
	@Column(name="permissao", length =50)
	private Set<String> permissao = new HashSet<String>();
	
	public Integer getCodigo() {return codigo;}
	public void setCodigo(Integer codigo) {this.codigo = codigo;}
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getLogin() {return login;}
	public void setLogin(String login) {this.login = login;}
	public String getSenha() {return senha;}
	public void setSenha(String senha) {this.senha = senha;}
	public Date getNascimento() {return nascimento;}
	public void setNascimento(Date nascimento) {this.nascimento = nascimento;}
	public String getTelefone() {return telefone;}
	public void setTelefone(String telefone) {this.telefone = telefone;}
	public String getIdioma() {return idioma;}
	public void setIdioma(String idioma) {this.idioma = idioma;}
	public boolean isActivo() {return activo;}
	public void setActivo(boolean activo) {this.activo = activo;}
	public Set<String> getPermissao() {return permissao;}
	public void setPermissao(Set<String> permiss�o) {this.permissao = permiss�o;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activo ? 1231 : 1237);
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nascimento == null) ? 0 : nascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		return result;
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
		if (activo != other.activo)
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idioma == null) {
			if (other.idioma != null)
				return false;
		} else if (!idioma.equals(other.idioma))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nascimento == null) {
			if (other.nascimento != null)
				return false;
		} else if (!nascimento.equals(other.nascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}
	
	

}
