package ao.co.always.financeiro.webapp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import ao.co.always.financeiro.categoria.Categoria;
import ao.co.always.financeiro.cheque.Cheque;
import ao.co.always.financeiro.cheque.ChequeId;
import ao.co.always.financeiro.cheque.ChequeRN;
import ao.co.always.financeiro.conta.Conta;
import ao.co.always.financeiro.lancamento.Lancamento;
import ao.co.always.financeiro.lancamento.LancamentoRN;
import ao.co.always.financeiro.util.ContextoUtil;
import ao.co.always.financeiro.util.RNException;

@ManagedBean(name="lancamentoBean")
@ViewScoped
public class LancamentoBean {
	
	private List<Lancamento> lista;
	private List<Double> saldos = new ArrayList<Double>();
	private List<Lancamento> listaAtehoje;
	private List<Lancamento> listaFuturos;
	private float saldoGeral;
	private Lancamento editado = new Lancamento();
	private Integer numeroCheque;

	public LancamentoBean(){
		this.novo();
	}
	public void novo(){
		this.editado = new Lancamento();
		this.editado.setData(new Date(System.currentTimeMillis()));
		this.numeroCheque = null;
	}
	public void editar(){
		Cheque cheque = this.editado.getCheque();
		if (cheque == null){
			this.numeroCheque = cheque.getChequeId().getCheque();
		}
	}
	
	public List<Lancamento> listar() {
		if (this.lista == null) {
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			Conta conta = contextoBean.getContaActiva();
			
			Calendar dataSaldo = new GregorianCalendar();
			dataSaldo.add(Calendar.MONTH, -1);
			dataSaldo.add(Calendar.DAY_OF_MONTH, -1);

			Calendar inicio = new GregorianCalendar();
			inicio.add(Calendar.MONTH, -1);

			LancamentoRN lancamentoRN = new LancamentoRN();
			this.saldoGeral = lancamentoRN.saldo(conta, dataSaldo.getTime());
			this.lista = lancamentoRN.listar(conta, inicio.getTime(), null);

			Categoria categoria = null;
			double saldo = this.saldoGeral;
			for (Lancamento lancamento : this.lista) {
				categoria = lancamento.getCategoria();
				saldo = saldo + (lancamento.getValor().floatValue() * categoria.getFactor());
				this.saldos.add(saldo);
			}
		}
		return this.lista;
	}
	public List<Lancamento> getListaAteHoje(){
		if (this.listaAtehoje == null){
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			Conta conta = contextoBean.getContaActiva();
			
			Calendar hoje = new GregorianCalendar();
			LancamentoRN lancamentoRN = new LancamentoRN();
			this.listaAtehoje = lancamentoRN.listar(conta, null, hoje.getTime());
		}
		return this.listaAtehoje;
	}
	public List<Lancamento> getListaFuturos(){
		if (this.listaFuturos == null){
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			Conta conta = contextoBean.getContaActiva();
			
			Calendar amanha = new GregorianCalendar();
			amanha.add(Calendar.DAY_OF_MONTH, 1);
			
			LancamentoRN lancamentoRN = new LancamentoRN();
			this.listaFuturos = lancamentoRN.listar(conta, amanha.getTime(), null);
		}
		return this.listaFuturos;
	}
	public void salvar(){
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.editado.setUsuario(contextoBean.getUsuarioLogado());
		this.editado.setConta(contextoBean.getContaActiva());
		
		ChequeRN chequeRN = new ChequeRN();
		ChequeId chequeId = null;
		if (this.numeroCheque != null){
			chequeId = new ChequeId();
			chequeId.setConta(contextoBean.getContaActiva().getIdConta());
			chequeId.setCheque(this.numeroCheque);
			Cheque cheque = chequeRN.carregar(chequeId);
			FacesContext context = FacesContext.getCurrentInstance();
			if (cheque == null){
				FacesMessage msg = new FacesMessage("Cheque não cadastrado");
				context.addMessage(null, msg);
				return;
			}else if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_CANCELADO){
				FacesMessage msg = new FacesMessage("Cheque já cancelado");
				context.addMessage(null, msg);
			}else{
				this.editado.setCheque(cheque);
				chequeRN.baixarCheque(chequeId, this.editado);
			}
		}
		
		LancamentoRN lancamentoRN = new LancamentoRN();
		lancamentoRN.salvar(this.editado);
		this.novo();
		this.lista = null;
	}
	public void mudouCheque(ValueChangeEvent event){
		Integer chequeAnterior = (Integer) event.getOldValue();
		if (chequeAnterior == null){
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			ChequeRN chequeRN = new ChequeRN();
			try{
				chequeRN.desvincularLancamento(contextoBean.getContaActiva(), chequeAnterior);
			}catch(RNException e){
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(e.getMessage());
				context.addMessage(null, msg);
				return;
			}
		}
	}

	public void excluir(){
		LancamentoRN lancamentoRN = new LancamentoRN();
		this.editado = lancamentoRN.carregar(this.editado.getIdLancamento());
		lancamentoRN.excluir(this.editado);
		this.lista = null;
	}
	public List<Lancamento> getLista() {
		return lista;
	}
	public void setLista(List<Lancamento> lista) {
		this.lista = lista;
	}
	public List<Double> getSaldos() {
		return saldos;
	}
	public void setListaAtehoje(List<Lancamento> listaAtehoje) {
		this.listaAtehoje = listaAtehoje;
	}
	public float getSaldoGeral() {
		return saldoGeral;
	}
	public void setSaldoGeral(float saldoGeral) {
		this.saldoGeral = saldoGeral;
	}
	public Lancamento getEditado() {
		return editado;
	}
	public void setEditado(Lancamento editado) {
		this.editado = editado;
	}
	public Integer getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(Integer numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public List<Lancamento> getListaAtehoje() {
		return listaAtehoje;
	}
	public void setSaldos(List<Double> saldos) {
		this.saldos = saldos;
	}
	public void setListaFuturos(List<Lancamento> listaFuturos) {
		this.listaFuturos = listaFuturos;
	}
	
}
 