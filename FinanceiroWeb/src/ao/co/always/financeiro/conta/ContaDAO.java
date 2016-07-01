package ao.co.always.financeiro.conta;
import java.util.List;
import ao.co.always.financeiro.usuario.Usuario;

public interface ContaDAO {
	
	public void salvar(Conta conta);
	public void excluir(Conta conta);
	public Conta carregar (Integer conta);
	public List<Conta> listar(Usuario usuario);
	public Conta buscarFavorita(Usuario usuario);
}
