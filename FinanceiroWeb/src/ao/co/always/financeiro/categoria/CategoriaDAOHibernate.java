package ao.co.always.financeiro.categoria;
import java.util.List;
import org.hibernate.*;
import org.hibernate.Session;
import ao.co.always.financeiro.usuario.Usuario;

public class CategoriaDAOHibernate implements CategoriaDAO{
	
	private Session session; 
	
	public void setSession(Session session){
		this.session = session;
	}
	public Categoria salvar(Categoria categoria){
		Categoria merged = (Categoria)this.session.merge(categoria);
		this.session.flush();
		this.session.clear();
		return merged;
	}
	public void excluir(Categoria categoria){
		categoria = (Categoria) this.carregar(categoria.getIdCategoria());
		this.session.delete(categoria);
		this.session.flush();
		this.session.clear();
	}
	public Categoria carregar(Integer categoria){
		return (Categoria) this.session.get(Categoria.class, categoria);
	}
	@SuppressWarnings("unchecked")
	public List<Categoria> listar(Usuario usuario){
		String hql = "select c from Categoria c where c.pai is null and c.usuario = :usuario";
		Query query = this.session.createQuery(hql);
		query.setInteger("usuario", usuario.getIdUsuario());
		List<Categoria> lista = query.list();
		return lista;
	}
}
