package ao.co.always.capitulo3.crudxml;
import java.sql.Date;
import java.util.List;
import org.hibernate.*;

import ao.co.always.capitulo.conexao.HibernateUtil;

public class ContatoCrudXML {

	public void salvar(Contato contato) {
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.save(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("N?o foi poss?vel inserir o contato. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar opera??o de inser??o. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Contato contato) {
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("N?o foi poss?vel alterar o contato. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar opera??o de atualiza??o. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Contato contato) {
		Session sessao = null;
		Transaction transacao = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("N?o foi poss?vel excluir o contato. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar opera??o de exclus?o. Mensagem: " + e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contato> listar() {
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;
		List<Contato> resultado = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			consulta = sessao.createQuery("from Contato");
			resultado = consulta.list();
			transacao.commit();
			return resultado;
		} catch (HibernateException e) {
			System.out.println("N?o foi poss?vel selecionar contatos. Erro: " + e.getMessage());
			throw new HibernateException(e);
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar opera??o de consulta. Mensagem: " + e.getMessage());
			}
		}
	}

	public Contato buscaContato(int valor) {
		Contato contato = null;
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null;

		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			consulta = sessao.createQuery("from Contato where codigo = :parametro");
			consulta.setInteger("parametro", valor);
			contato = (Contato) consulta.uniqueResult();
			transacao.commit();
			return contato;
		} catch (HibernateException e) {
			System.out.println("N?o foi poss?vel buscar contato. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar opera??o de buscar. Mensagem: " + e.getMessage());
			}
		}
		return contato;
	}

	public static void main(String[] args) {
		ContatoCrudXML contatoCrudXML = new ContatoCrudXML();
		String[] nomes = {"Fulano", "Beltrano", "Ciclano"};
		String[] fones = {"(47) 2222-1111", "(47) 7777-5555", "(47) 9090-2525"};
		String[] emails = {"fulano@teste.com.br", "beltrano@teste.com.br", "ciclano@teste.com.br"};
		String[] observacoes = {"Novo cliente", "Cliente em dia", "Ligar na quinta"};
		Contato contato = null;
		
		for (int i = 0; i < nomes.length; i++) {
			contato = new Contato();
			contato.setNome(nomes[i]);
			contato.setTelefone(fones[i]);
			contato.setEmail(emails[i]);
			contato.setDataCadastro(new Date(System.currentTimeMillis()));
			contato.setObservacao(observacoes[i]);
			contatoCrudXML.salvar(contato);
		}
		System.out.println("Total de registros cadastrados: " + contatoCrudXML.listar().size());
	}
}
