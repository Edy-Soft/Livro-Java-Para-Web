package ao.co.always.financeiro.util;
import ao.co.always.financeiro.categoria.CategoriaDAO;
import ao.co.always.financeiro.categoria.CategoriaDAOHibernate;
import ao.co.always.financeiro.cheque.ChequeDAO;
import ao.co.always.financeiro.cheque.ChequeDAOHibernate;
import ao.co.always.financeiro.conta.ContaDAO;
import ao.co.always.financeiro.conta.ContaDAOHibernate;
import ao.co.always.financeiro.lancamento.LancamentoDAO;
import ao.co.always.financeiro.lancamento.LancamentoDAOHibernate;
import ao.co.always.financeiro.usuario.UsuarioDAO;
import ao.co.always.financeiro.usuario.UsuarioDAOHibernate;

public class DAOFactory {
	
	public static UsuarioDAO criarUsuarioDAO(){
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	public static ContaDAO cirarContaDAO(){
		ContaDAOHibernate contaDAO = new ContaDAOHibernate();
		contaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return contaDAO;
	}
	public static CategoriaDAO criarCategoriaDAO(){
		CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();
		categoriaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return categoriaDAO;
	}
	public static LancamentoDAO criarLancamentoDAO(){
		LancamentoDAOHibernate lancamentoDAO = new LancamentoDAOHibernate();
		lancamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return lancamentoDAO;
	}
	public static ChequeDAO criarChequeDAO(){
		ChequeDAOHibernate chequeDAO = new ChequeDAOHibernate();
		chequeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return chequeDAO;
	}

}
