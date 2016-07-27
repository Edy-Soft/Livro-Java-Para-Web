package ao.co.always.capitulo12.primafaces.coluna;

public class VendaColuna {
	private int ano;
	private int vendaAngola;
	private int vendaBrasil;
	private int vendaAlemanha;
	
	public VendaColuna(){
	}
	public VendaColuna(int ano, int vendaAngola, int vendaBrasil, int vendaAlemanha){
		this.ano = ano;
		this.vendaAlemanha = vendaAngola;
		this.vendaAngola = vendaBrasil;
		this.vendaBrasil = vendaAlemanha;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getVendaAngola() {
		return vendaAngola;
	}
	public void setVendaAngola(int vendaAngola) {
		this.vendaAngola = vendaAngola;
	}
	public int getVendaBrasil() {
		return vendaBrasil;
	}
	public void setVendaBrasil(int vendaBrasil) {
		this.vendaBrasil = vendaBrasil;
	}
	public int getVendaAlemanha() {
		return vendaAlemanha;
	}
	public void setVendaAlemanha(int vendaAlemanha) {
		this.vendaAlemanha = vendaAlemanha;
	}
	
}
