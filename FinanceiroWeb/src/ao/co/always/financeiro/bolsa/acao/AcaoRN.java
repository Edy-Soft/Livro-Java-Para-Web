package ao.co.always.financeiro.bolsa.acao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ao.co.always.financeiro.usuario.Usuario;
import ao.co.always.financeiro.util.DAOFactory;
import ao.co.always.financeiro.util.RNException;
import ao.co.always.financeiro.webapp.YahooFinanceUtil;

public class AcaoRN {

	private AcaoDAO acaoDAO;
	
	public AcaoRN(){
		this.acaoDAO = DAOFactory.criarAcaoDAO();
	}
	public void salvar(Acao acao){
		this.acaoDAO.salvar(acao);
	}
	public void excluir(Acao acao){
		this.acaoDAO.excluir(acao);
	}
	public Acao carregar(String codigo){
		return this.acaoDAO.carregar(codigo);
	}
	public List<Acao> listar(Usuario usuario){
		return this.acaoDAO.listar(usuario);
	}
	@SuppressWarnings("unused")
	public List<AcaoVirtual> listarAcaoVirtual(Usuario usuario) throws RNException{
		List<Acao> listaAcao = null;
		List<AcaoVirtual> listaAcaoVirtual = new ArrayList<AcaoVirtual>();
		AcaoVirtual acaoVirtual = null;
		String cotacao = null;
		float ultimoPreco = 0.0f;
		float total = 0.0f;
		int quantidade = 0;
		try{
			listaAcao = this.listar(usuario);
			for (Acao acao : listaAcao){
				acaoVirtual = new AcaoVirtual();
				acaoVirtual.setAcao(acao);
				cotacao = this.retornaCotacao(YahooFinanceUtil.ULTIMO_PRECO_DIA_ACAO_INDICE, acao);
				if (cotacao != null){
					ultimoPreco = new Float(cotacao).floatValue();
					quantidade = acao.getQuantidade();
					acaoVirtual.setUltimoPreco(ultimoPreco);
					acaoVirtual.setTotal(total);
					listaAcaoVirtual.add(acaoVirtual);
				}
			}
		}catch(RNException e){
			throw new RNException("Não foi possivel listar acções. Erro; " + e.getMessage());
		}
		return listaAcaoVirtual;
	}
	public String retornaCotacao(int indiceInformacao, Acao acao) throws RNException{
		YahooFinanceUtil yahooFinanceUtil = null;
		String informacao = null;
		try{
			yahooFinanceUtil = new YahooFinanceUtil(acao);
			informacao = yahooFinanceUtil.retornaCotacao(indiceInformacao, acao.getSigla());
		}catch (IOException e){
			throw new RNException("Não foi possivel recuperar cotação. Erro: " + e.getMessage());
		}
		return informacao;
	}
	public String montaLinkAcao(Acao acao){
		String link = null;
		if (acao != null){
			if (acao.getOrigem() != null){
				if (acao.getOrigem() == YahooFinanceUtil.ORIGEM_BOVESPA){
					link = acao.getSigla() + YahooFinanceUtil.POSFIXO_ACAO_BOVESPA;
				}else{
					link = acao.getSigla();
				}
			}else{
				link = YahooFinanceUtil.INDICE_BOVESPA;
			}
		}else{
			link = YahooFinanceUtil.INDICE_BOVESPA;
		}
		return link;
	}
}
