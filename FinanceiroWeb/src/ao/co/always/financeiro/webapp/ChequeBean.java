package ao.co.always.financeiro.webapp;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import ao.co.always.financeiro.cheque.Cheque;
import ao.co.always.financeiro.cheque.ChequeRN;
import ao.co.always.financeiro.conta.Conta;
import ao.co.always.financeiro.util.ContextoUtil;
import ao.co.always.financeiro.util.MensagemUtil;
import ao.co.always.financeiro.util.RNException;

@ManagedBean(name = "chequeBean")
@RequestScoped
public class ChequeBean {
	
	private Cheque selecionado = new Cheque();
	private List<Cheque> lista = null;
	private Integer chequeInicial;
	private Integer chequeFinal;
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		Conta conta = contextoBean.getContaActiva();
		
		int totalCheques;
		if(this.chequeFinal == null || this.chequeFinal == null){
			String texto = MensagemUtil.getMensagem("cheque_erro_sequencia");
			FacesMessage msg = new FacesMessage(texto);
			context.addMessage(null, msg);
		}else if (this.chequeFinal.intValue() < this.chequeInicial.intValue()){
			String texto = MensagemUtil.getMensagem("cheque_erro_inicial_final", this.chequeInicial, this.chequeFinal);
			FacesMessage msg = new FacesMessage(texto);
			context.addMessage(null, msg);
		}else {
			ChequeRN chequeRN = new ChequeRN();
			totalCheques = chequeRN.salvarSequencia(conta, this.chequeInicial, this.chequeFinal);
			String texto = MensagemUtil.getMensagem("cheque_info_cadastro", this.chequeFinal, totalCheques);
			FacesMessage msg = new FacesMessage(texto);
			context.addMessage(null, msg);
			this.lista = null;
		}
	}
	public void excluir(){
		ChequeRN chequeRN = new ChequeRN();
		try{
			chequeRN.excluir(this.selecionado);
		}catch(RNException e){
			FacesContext context = FacesContext.getCurrentInstance();
			String texto = MensagemUtil.getMensagem("cheque_erro_cancelar");
			FacesMessage msg = new FacesMessage(texto);
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			context.addMessage(texto, msg);
		}
		this.lista = null;
	}
	public List<Cheque> getLista(){
		if (this.lista == null){
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			Conta conta = contextoBean.getContaActiva();
			
			ChequeRN chequeRN = new ChequeRN();
			this.lista = chequeRN.listar(conta);
		}
		return this.lista;
	}
	public Cheque getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(Cheque selecionado) {
		this.selecionado = selecionado;
	}
	public Integer getChequeInicial() {
		return chequeInicial;
	}
	public void setChequeInicial(Integer chequeInicial) {
		this.chequeInicial = chequeInicial;
	}
	public Integer getChequeFinal() {
		return chequeFinal;
	}
	public void setChequeFinal(Integer chequeFinal) {
		this.chequeFinal = chequeFinal;
	}
	public void setLista(List<Cheque> lista) {
		this.lista = lista;
	}
	
}
