package ao.co.always.financeiro.conta;
import java.util.Date;
import java.util.List;
import ao.co.always.financeiro.usuario.Usuario;
import ao.co.always.financeiro.util.DAOFactory;

public class ContaRN {
	
	private ContaDAO contaDAO;
	
	public ContaRN(){
		this.contaDAO = DAOFactory.cirarContaDAO();
	}
	public List<Conta> listar (Usuario usuario){
		return this.contaDAO.listar(usuario);
	}
	public Conta carregar (Integer conta){
		return this.contaDAO.carregar(conta);
	}
	public void salvar(Conta conta){
		conta.setDataCadastro(new Date());
		this.contaDAO.salvar(conta);
	}
	public void excluir(Conta conta){
		this.contaDAO.excluir(conta);
	}
	public void tornarFavorita(Conta contaFavorita){
		Conta conta = this.buscarFavorita(contaFavorita.getUsuario());
		if(conta != null){
			conta.setFavorita(false);
			this.contaDAO.salvar(conta);
		}
		contaFavorita.setFavorita(true);
		this.contaDAO.salvar(contaFavorita);
	}
	public Conta buscarFavorita(Usuario usuario){
		return this.contaDAO.buscarFavorita(usuario);
	}
}
