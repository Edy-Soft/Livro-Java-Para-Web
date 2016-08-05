package ao.co.always.financeiro.bolsa.acao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ao.co.always.financeiro.usuario.Usuario;

public class AcaoDAOHibernate implements AcaoDAO{
	
	private Session session;
	
	public void SetSession(Session session){
		this.session = session;
	}
	public void salvar(Acao acao){
		this.session.saveOrUpdate(acao);
	}
	public void excluir(Acao acao){
		this.session.delete(acao);
	}
	public Acao carregar(String codigo){
		return (Acao) this.session.get(Acao.class, codigo);
	}
	@SuppressWarnings("unchecked")
	public List<Acao> listar(Usuario usuario){
		Criteria criteria = this.session.createCriteria(Acao.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		return criteria.list();
	}
}
