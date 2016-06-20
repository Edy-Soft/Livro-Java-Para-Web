package ao.co.always.financeiro.webapp;
import javax.faces.bean.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import ao.co.always.financeiro.usuario.Usuario;
import ao.co.always.financeiro.usuario.UsuarioRN;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {
	
	Usuario usuario = new Usuario();
	private String confirmaSenha;
	
	public String novo(){
		this.usuario = new Usuario();
		this.usuario.setActivo(true);
		return "usuario";
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
		
		return "usuarioSucesso";
	}
	public Usuario getUsuario() {return usuario;}
	public void setUsuario(Usuario usuario) {this.usuario = usuario;}
	public String getConfirmaSenha() {return confirmaSenha;}
	public void setConfirmaSenha(String confirmaSenha) {this.confirmaSenha = confirmaSenha;}

}
