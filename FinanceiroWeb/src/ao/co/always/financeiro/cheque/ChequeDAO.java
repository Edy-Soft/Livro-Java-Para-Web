package ao.co.always.financeiro.cheque;
import java.util.List;
import ao.co.always.financeiro.conta.Conta;

public interface ChequeDAO {

	public void salvar(Cheque cheque);
	public void excluir(Cheque cheque);
	public Cheque carregar (ChequeId chequeId);
	public List<Cheque> listar(Conta conta);
}
