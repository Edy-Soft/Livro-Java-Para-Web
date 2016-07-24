package ao.co.always.financeiro.categoria;
import java.util.List;
import ao.co.always.financeiro.usuario.Usuario;
import ao.co.always.financeiro.util.DAOFactory;

public class CategoriaRN {
	
	private CategoriaDAO categoriaDAO;
	
	public CategoriaRN(){
		this.categoriaDAO = DAOFactory.criarCategoriaDAO();
	}
	public List<Categoria> listar (Usuario usuario){
		return this.categoriaDAO.listar(usuario);
	}
	public Categoria salvar(Categoria categoria){
		
		Categoria pai = categoria.getPai();
		
		if(pai == null){
			String msg = "A Categoria " + categoria.getDescricao() + " deve ter um pai definido";
			throw new IllegalArgumentException(msg);
		}
		boolean mudouFactor = pai.getFactor() != categoria.getFactor();
		categoria.setFactor(pai.getFactor());
		categoria = this.categoriaDAO.salvar(categoria);
		
		if(mudouFactor){
			categoria = this.carregar(categoria.getIdCategoria());
			this.replicarFactor(categoria, categoria.getFactor());
		}
		return categoria;
	}
	private void replicarFactor(Categoria categoria, int factor){
		if(categoria.getFilhos() != null){
			for (Categoria filho : categoria.getFilhos()){
				filho.setFactor(factor);
				this.categoriaDAO.salvar(filho);
				this.replicarFactor(filho, factor);
			}
		}
	}
	public void excluir(Categoria categoria){
		this.categoriaDAO.excluir(categoria);
	}
	public void excluir(Usuario usuario){
		List<Categoria> lista = this.listar(usuario);
			for(Categoria categoria:lista){
				this.categoriaDAO.excluir(categoria);
			}
	}
	public Categoria carregar(Integer categoria){
		return this.categoriaDAO.carregar(categoria);
	}
	public void salvaEstruturaPadrao(Usuario usuario){
		
		Categoria despesas = new Categoria(null, usuario, "DESPESAS", -1);
		despesas = this.categoriaDAO.salvar(despesas);
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Moradia", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Alimentação", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Vestuário", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Deslocamento", -1));
		this.categoriaDAO.salvar(new Categoria(despesas, usuario, "Saúde", -1));
		
		Categoria receitas = new Categoria(null, usuario, "RECEITAS", 1);
		receitas = this.categoriaDAO.salvar(receitas);
		this.categoriaDAO.salvar(new Categoria(receitas, usuario, "Salário", 1));
		this.categoriaDAO.salvar(new Categoria(receitas, usuario, "Rendimento", 1));
	}
}
