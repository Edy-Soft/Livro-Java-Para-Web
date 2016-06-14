package com.livro.capitulo3.consulta;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.livro.capitulo3.filme.Filme;
import com.livro.capitulo3.util.HibernateUtil;

public class Consultas {

	private Session	sessao	= HibernateUtil.getSessionFactory().openSession();

	public static void main(String[] args) {
		Consultas consultas = new Consultas();
		consultas.queryConsultas();
		consultas.criteriaConsultas();
		consultas.consultasNomeadas();
	}

	public void queryConsultas() {
			
	}

	public void criteriaConsultas() {

	}

	@SuppressWarnings("unchecked")
	public void consultasNomeadas() {
		this.sessao = HibernateUtil.getSessionFactory().openSession();
		Query consulta = null;
		Filme filme = null;
		List<Filme> filmes = null;

		//Buscando pela chave prim�ria
		consulta = this.sessao.getNamedQuery("filmePorChavePrimaria");
		consulta.setParameter("filme", 1);
		filme = (Filme) consulta.uniqueResult();
		System.out.println("Usando consultas nomeadas o nome do filme �: " + filme.getDescricao());

		//Buscando pela descri��o
		consulta = this.sessao.getNamedQuery("filmePorDescricao");
		consulta.setParameter("descricao", "%e%");
		filmes = consulta.list();
		System.out.println("Segue(m) o(s) filme(s) da descri��o pesquisada.");
		for (Filme f: filmes) {
			System.out.println("O nome do filme �: " + f.getDescricao());
		}

		//Buscando por categoria
		consulta = this.sessao.getNamedQuery("filmePorCategoria");
		consulta.setParameter("categoria", 1);
		filmes = consulta.list();
		System.out.println("Segue(m) o(s) filme(s) da categoria pesquisada.");
		for (Filme f: filmes) {
			System.out.println("O nome do filme �: " + f.getDescricao());
		}
	}
}
