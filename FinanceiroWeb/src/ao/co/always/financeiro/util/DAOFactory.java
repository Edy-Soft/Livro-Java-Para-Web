package ao.co.always.financeiro.util;
import ao.co.always.financeiro.conta.ContaDAO;
import ao.co.always.financeiro.conta.ContaDAOHibernate;
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

}
