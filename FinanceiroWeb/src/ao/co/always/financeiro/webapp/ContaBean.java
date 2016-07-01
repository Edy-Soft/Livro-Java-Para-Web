package ao.co.always.financeiro.webapp;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ReferencedBean;
import ao.co.always.financeiro.conta.Conta;
import ao.co.always.financeiro.conta.ContaRN;
import ao.co.always.financeiro.util.ContextoUtil;

@ManagedBean(name="contaBean")
@ReferencedBean
public class ContaBean {
	
	private Conta selecionada = new Conta();
	private List<Conta> lista = null;
	
	public void salvar(){
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.selecionada.setUsuario(contextoBean.getUsuarioLogado());
		ContaRN contaRN = new ContaRN();
		contaRN.salvar(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
	}
	public void editar(){
	}
	public void excluir(){
		ContaRN contaRN = new ContaRN();
		contaRN.excluir(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
	}
	public void tornarFavorita(){
	}
	public Conta getSelecionada() {
		return selecionada;
	}
	public void setSelecionada(Conta selecionada) {
		this.selecionada = selecionada;
	}
	public List<Conta> getLista() {
		if(this.lista == null){
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			ContaRN contaRN = new ContaRN();
			this.lista = contaRN.listar(contextoBean.getUsuarioLogado());
		}
		return lista;
	}
	public void setLista(List<Conta> lista) {
		ContaRN contaRN = new ContaRN();
		contaRN.tornarFavorita(this.selecionada);
		this.selecionada = new Conta();
		this.lista = lista;
	}
	
	
}
