package ao.co.always.financeiro.categoria;
import java.util.List;
import ao.co.always.financeiro.usuario.Usuario;

public interface CategoriaDAO {
	public Categoria salvar(Categoria categoria);
	public void excluir(Categoria categoria);
	public Categoria carregar(Integer categoria);
	public List<Categoria> listar (Usuario usuario);
}
