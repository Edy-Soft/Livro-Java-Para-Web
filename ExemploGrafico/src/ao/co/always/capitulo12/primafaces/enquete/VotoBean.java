package ao.co.always.capitulo12.primafaces.enquete;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "votoBean")
@RequestScoped
public class VotoBean {
	private List<Voto> votos;
	
	public VotoBean(){
		this.votos = new ArrayList<Voto>();
		this.votos.add(new Voto("Marca A", 70));
		this.votos.add(new Voto("Marca B", 20));
		this.votos.add(new Voto("Marca C", 30));
	}

	public List<Voto> getVotos(){
		int valor1 = (int) (Math.random() * 1000);
		int valor2 = (int) (Math.random() * 1000);
		int valor3 = (int) (Math.random() * 1000);
		
		this.votos.get(0).setTotal(valor1);
		this.votos.get(1).setTotal(valor2);
		this.votos.get(2).setTotal(valor3);
		
		return this.votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}
	
}
