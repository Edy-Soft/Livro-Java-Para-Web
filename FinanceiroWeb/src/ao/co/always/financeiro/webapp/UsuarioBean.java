package ao.co.always.financeiro.webapp;
import javax.faces.bean.*;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import ao.co.always.financeiro.conta.Conta;
import ao.co.always.financeiro.conta.ContaRN;
import ao.co.always.financeiro.usuario.Usuario;
import ao.co.always.financeiro.usuario.UsuarioRN;
import ao.co.always.financeiro.util.RNException;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {
	
	private Usuario 		usuario = new Usuario();
	private String 			confirmaSenha;
	private List<Usuario> 	lista;
	private String 			destinoSalvar;
	private Conta	   	    conta	= new Conta();
	
	public String novo(){
		destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		this.usuario.setActivo(true);
		return "usuario";
	 }
	public String editar(){
		this.confirmaSenha = this.usuario.getSenha();
		return "/publico/usuario";
	}
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		String senha = this.usuario.getSenha();
		if(!senha.equals(this.confirmaSenha)){
			FacesMessage facesMessage = new FacesMessage("Confirme a sua senha");
			context.addMessage(null, facesMessage);	
			return null;
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		
		if(this.conta.getDescricao() != null){
			this.conta.setUsuario(this.usuario);
			this.conta.setFavorita(true);
			ContaRN contaRN = new ContaRN();
			contaRN.salvar(this.conta);
		}
		if (this.destinoSalvar.equals("usuarioSucesso")) {
			try {
				usuarioRN.enviarEmailPosCadastramento(this.usuario);
			} catch (RNException e) {
				FacesMessage facesMessage = new FacesMessage("Não foi possível enviar o e-mail de cadastro do usuário. Erro: " + e.getMessage());
				context.addMessage(null, facesMessage);
				return null;
			}
		} 
		
		return this.destinoSalvar;
	}
	public String excluir(){
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		return null;
	}
	public String activar(){
		if(this.usuario.isActivo())
			this.usuario.setActivo(false);
		else
			this.usuario.setActivo(true);
		
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return null;
	}
	public List<Usuario> getLista(){
		if(this.lista == null){
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}
	public String atribuiPermissao(Usuario usuario, String permissao){
		this.usuario = usuario;
		java.util.Set<String> permissoes = this.usuario.getPermissao();
		if(permissoes.contains(permissao)){
			permissoes.remove(permissao);
		}else{
			permissoes.add(permissao);
		}
		return null;
	}
	
	public Usuario getUsuario() {
		return usuario;
		}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		}
	public String getConfirmaSenha() {
		return confirmaSenha;
		}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
		}
	public String getDestinoSalvar() {
		return destinoSalvar;
		}
	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
		}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	
}
