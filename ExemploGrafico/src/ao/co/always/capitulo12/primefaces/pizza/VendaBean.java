package ao.co.always.capitulo12.primefaces.pizza;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;@ManagedBean(name = "vendaBean")

@RequestScoped
public class VendaBean {
	
	private List<Venda> vendaPais;
	
	public VendaBean(){
		this.vendaPais = new ArrayList<Venda>();
		this.vendaPais.add(new Venda("Angola", 540.12f));
		this.vendaPais.add(new Venda("Brasil", 547.47f));
		this.vendaPais.add(new Venda("Portugal", 325.14f));
		this.vendaPais.add(new Venda("Espanha", 254.10f));
		this.vendaPais.add(new Venda("Estado Unidos", 784.12f));
	}
	public List<Venda> getVendaPais() {
		return vendaPais;
	}
	public void setVendaPais(List<Venda> vendaPais) {
		this.vendaPais = vendaPais;
	}
}
