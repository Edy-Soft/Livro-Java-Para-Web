package ao.co.always.financeiro.webapp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ao.co.always.financeiro.categoria.Categoria;
import ao.co.always.financeiro.conta.Conta;
import ao.co.always.financeiro.lancamento.Lancamento;
import ao.co.always.financeiro.lancamento.LancamentoRN;
import ao.co.always.financeiro.util.ContextoUtil;

@ManagedBean(name="lancamentoBean")
@ViewScoped
public class LancamentoBean {
	
	private List<Lancamento> lista;
	private List<Double> saldos = new ArrayList<Double>();
	private float saldoGeral;
	private Lancamento editado = new Lancamento();

	public LancamentoBean(){
		this.novo();
	}
	
	public void novo(){
		this.editado = new Lancamento();
		this.editado.setData(new Date(System.currentTimeMillis()));
	}
	public void editar(){
		
	}
	public List<Lancamento> lista(){
		if(this.lista == null){
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
				for(Lancamento lancamento : this.lista){
					categoria = lancamento.getCategoria();
					saldo = saldo + (lancamento.getValor().floatValue() * categoria.getFactor());
					this.saldos.add(saldo);
				}
			}
		return this.lista;
	}
	public void salvar(){
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.editado.setUsuario(contextoBean.getUsuarioLogado());
		this.editado.setConta(contextoBean.getContaActiva());
		
		LancamentoRN lancamentoRN = new LancamentoRN();
		lancamentoRN.salvar(this.editado);
		
		this.novo();
		this.lista = null;
	}
	public void excluir(){
		LancamentoRN lancamentoRN = new LancamentoRN();
		this.editado = lancamentoRN.carregar(this.editado.getIdLancamento());
		lancamentoRN.excluir(this.editado);
		this.lista = null;
	}
	public Lancamento getEditado() {
		return editado;
	}
	public void setEditado(Lancamento editado) {
		this.editado = editado;
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
	public void setSaldos(List<Double> saldos) {
		this.saldos = saldos;
	}
	public float getSaldoGeral() {
		return saldoGeral;
	}
	public void setSaldoGeral(float saldoGeral) {
		this.saldoGeral = saldoGeral;
	}

}
 