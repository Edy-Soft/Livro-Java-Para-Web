package ao.co.always.capitulo12.primafaces.coluna;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "vendaColunaBean")
@RequestScoped
public class VendaColunaBean {

		private List<VendaColuna> vendaColunas;
		
		public VendaColunaBean(){
			this.vendaColunas = new ArrayList<VendaColuna>();
			
			this.vendaColunas.add(new VendaColuna(2008, 191, 163, 178));
			this.vendaColunas.add(new VendaColuna(2009, 210, 300, 275));
			this.vendaColunas.add(new VendaColuna(2010, 32, 45, 60));
		}

		public List<VendaColuna> getVendaColunas() {
			return vendaColunas;
		}
		public void setVendaColunas(List<VendaColuna> vendaColunas) {
			this.vendaColunas = vendaColunas;
		}
		
}
