package ao.co.always.financeiro.usuario;
import java.util.List;

public interface UsuarioDAO {
	
	public void salvar(Usuario usuario);
	public void actualizar(Usuario usuario);
	public void excluir(Usuario usuario);
	public Usuario carregar(Integer codigo);
	public Usuario bucarPorLogin(String login);
	public List<Usuario> listar();
}
