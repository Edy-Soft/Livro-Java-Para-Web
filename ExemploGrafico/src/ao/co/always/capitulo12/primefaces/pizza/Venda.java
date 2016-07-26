package ao.co.always.capitulo12.primefaces.pizza;

public class Venda {
	private String pais;
	private float total;
	
	public Venda(String pais, float total){
		this.pais = pais;
		this.total = total;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
}
