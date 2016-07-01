package ao.co.always.financeiro.webapp;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import ao.co.always.financeiro.conta.Conta;
import ao.co.always.financeiro.conta.ContaRN;
import ao.co.always.financeiro.usuario.Usuario;
import ao.co.always.financeiro.usuario.UsuarioRN;

@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {
	
	private Usuario usuarioLogado = null;
	private Conta contaActiva = null;
	
	public Usuario getUsuarioLogado(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		if(this.usuarioLogado == null || !login.equals(this.usuarioLogado.getLogin())){
			if(login != null){
				UsuarioRN usuarioRN = new UsuarioRN();
				this.usuarioLogado = usuarioRN.buscarPorLogin(login);
				this.contaActiva = null;
			}
		}
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuario){
		this.usuarioLogado = usuario;
	}
	public Conta getContaActiva(){
		if(this.contaActiva == null){
			Usuario usuario = this.getUsuarioLogado();
			ContaRN contaRN = new ContaRN();
			this.contaActiva = contaRN.buscarFavorita(usuario);
			if(this.contaActiva == null);
				List<Conta> contas = contaRN.listar(usuario);
				if(contas != null){
					for (Conta conta : contas){
						this.contaActiva = conta;
						break;
					}
				}
			}
		return this.contaActiva;
	}
	public void setContaActiva(ValueChangeEvent event){
		Integer conta = (Integer) event.getNewValue();
		ContaRN contaRN = new ContaRN();
		this.contaActiva = contaRN.carregar(conta);
	}
}
