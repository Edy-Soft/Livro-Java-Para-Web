package ao.co.always.financeiro.usuario;
import java.util.List;
import ao.co.always.financeiro.util.DAOFactory;

public class UsuarioRN {
private UsuarioDAO usuarioDAO;
	
	public UsuarioRN(){
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}
	public Usuario carregar(Integer codigo){
		return this.usuarioDAO.carregar(codigo);
	}
	public Usuario buscarPorLogin(String login){
		return this.usuarioDAO.bucarPorLogin(login);
	}
	public void salvar(Usuario usuario){
		Integer codigo = usuario.getCodigo();
		if(codigo == null || codigo == 0){
			this.usuarioDAO.salvar(usuario);
		}else{
			this.usuarioDAO.actualizar(usuario);
		}
	}
	public void excluir(Usuario usuario){
		this.usuarioDAO.excluir(usuario);
	}
	public List<Usuario> listar(){
		return this.usuarioDAO.listar();
	}
}
