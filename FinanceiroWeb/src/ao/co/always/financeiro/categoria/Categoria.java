package ao.co.always.financeiro.categoria;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ao.co.always.financeiro.usuario.Usuario;

@SuppressWarnings("serial")
@Entity
@Table(name="categoria")
public class Categoria implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="cod_categoria")
	private Integer idCategoria;
	
	@ManyToOne
	@JoinColumn(name = "categoria_pai", nullable = true)
	@org.hibernate.annotations.ForeignKey(name="fk_categoria_categoria")
	private Categoria pai;
	
	@ManyToOne
	@JoinColumn(name="cod_usuario")
	@OnDelete(action=OnDeleteAction.CASCADE)
	@org.hibernate.annotations.ForeignKey(name="fk_categoria_usuario")
	private Usuario usuario;
	
	private String descricao;
	
	private int factor;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name="categoria_pai", updatable = false)
	@org.hibernate.annotations.OrderBy(clause="descricao asc")
	private List<Categoria> filhos;
	
	public Categoria(){}
	
	public Categoria(Categoria pai, Usuario usuario, String descricao, int factor){
		this.pai = pai;
		this.usuario = usuario;
		this.descricao = descricao;
		this.factor = factor;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Categoria getPai() {
		return pai;
	}
	public void setPai(Categoria pai) {
		this.pai = pai;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getFactor() {
		return factor;
	}
	public void setFactor(int factor) {
		this.factor = factor;
	}
	public List<Categoria> getFilhos() {
		return filhos;
	}
	public void setFilhos(List<Categoria> filhos) {
		this.filhos = filhos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + factor;
		result = prime * result + ((filhos == null) ? 0 : filhos.hashCode());
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((pai == null) ? 0 : pai.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Categoria other = (Categoria) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (factor != other.factor)
			return false;
		if (filhos == null) {
			if (other.filhos != null)
				return false;
		} else if (!filhos.equals(other.filhos))
			return false;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		if (pai == null) {
			if (other.pai != null)
				return false;
		} else if (!pai.equals(other.pai))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
}
