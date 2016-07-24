package ao.co.always.financeiro.usuario;
import java.util.List;
import ao.co.always.financeiro.categoria.CategoriaRN;
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
		Integer codigo = usuario.getIdUsuario();
		if(codigo == null || codigo == 0){
			usuario.getPermissao().add("ROLE_USUARIO");
			this.usuarioDAO.salvar(usuario);
			
			CategoriaRN categoriaRN = new CategoriaRN();
			categoriaRN.salvaEstruturaPadrao(usuario);
		}else{
			this.usuarioDAO.actualizar(usuario);
		}
	}
	public void excluir(Usuario usuario){
		CategoriaRN categoriaRN = new CategoriaRN();
		categoriaRN.excluir(usuario);
		this.usuarioDAO.excluir(usuario);
	}
	public List<Usuario> listar(){
		return this.usuarioDAO.listar();
	}
}
