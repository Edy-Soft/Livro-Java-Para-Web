package ao.co.always.financeiro.webapp;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import ao.co.always.financeiro.bolsa.acao.Acao;
import ao.co.always.financeiro.bolsa.acao.AcaoRN;
import ao.co.always.financeiro.bolsa.acao.AcaoVirtual;
import ao.co.always.financeiro.util.ContextoUtil;
import ao.co.always.financeiro.util.RNException;

@ManagedBean(name = "acaoBean")
@RequestScoped
public class AcaoBean {
	
	private AcaoVirtual selecionado = new AcaoVirtual();
	private List<AcaoVirtual> lista = null;
	private String linkCodigoAcao = null;
	
	public void salvar(){
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		AcaoRN acaoRN = new AcaoRN();
		Acao acao = this.selecionado.getAcao();
		acao.setSigla(acao.getSigla().toUpperCase());
		acao.setUsuario(contextoBean.getUsuarioLogado());
		acaoRN.salvar(acao);
		this.selecionado = new AcaoVirtual();
		this.lista = null;
	}
	public void excluir(){
		AcaoRN acaoRN = new AcaoRN();
		acaoRN.excluir(this.selecionado.getAcao());
		this.selecionado = new AcaoVirtual();
		this.lista = null;
	}
	public List<AcaoVirtual> getLista(){
		FacesContext context = FacesContext.getCurrentInstance();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		AcaoRN acaoRN = new AcaoRN();
		try{
			if (this.lista == null){
				this.lista = acaoRN.listarAcaoVirtual(contextoBean.getUsuarioLogado());
			}
		}catch(RNException e){
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return this.lista;
	}
	public String getLinkCodigoAcao(){
		AcaoRN acaoRN = new AcaoRN();
		if (this.selecionado != null){
			this.linkCodigoAcao = acaoRN.montaLinkAcao(this.selecionado.getAcao());
		}else{
			this.linkCodigoAcao = YahooFinanceUtil.INDICE_BOVESPA;
		}
		return this.linkCodigoAcao;
	}
	public AcaoVirtual getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(AcaoVirtual selecionado) {
		this.selecionado = selecionado;
	}
	public void setLista(List<AcaoVirtual> lista) {
		this.lista = lista;
	}
	public void setLinkCodigoAcao(String linkCodigoAcao) {
		this.linkCodigoAcao = linkCodigoAcao;
	}
	
}
