package ao.co.always.capitulo14.gmail;
import java.util.Date;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ao.co.always.capitulo14.autenticacao.AutenticaUsuario;

@ManagedBean(name = "gmailBean")
@RequestScoped
public class GmailBean {
	
	public static final String	SERVIDOR_SMTP		= "smtp.gmail.com";
	public static final String	PORTA_SERVIDOR_SMTP	= "465";
	private static final String	CONTA_GMAIL		= "edysoft20@gmail.com";
	private static final String	SENHA_GMAIL		= "edcg201527$gmail";

	private String	de;
	private String	para;
	private String	assunto;
	private String	mensagem;

	public void enviarEmail() {
		FacesContext context = FacesContext.getCurrentInstance();
		AutenticaUsuario autenticaUsuario = new AutenticaUsuario(GmailBean.CONTA_GMAIL, GmailBean.SENHA_GMAIL); 
		Session session = Session.getDefaultInstance(this.configuracaoEmail(), autenticaUsuario);
		//Habilita o LOG das ações executadas durante o envio do email
		session.setDebug(true); 

		try {
			Transport envio = null;
			MimeMessage email = new MimeMessage(session);
			email.setRecipient(Message.RecipientType.TO, new InternetAddress(this.para));
			email.setFrom(new InternetAddress(this.de));
			email.setSubject(this.assunto);
			email.setContent(this.mensagem, "text/plain");
			email.setSentDate(new Date());
			envio = session.getTransport("smtp");
			envio.connect(GmailBean.SERVIDOR_SMTP, GmailBean.CONTA_GMAIL, GmailBean.SENHA_GMAIL);
			email.saveChanges(); 
			envio.sendMessage(email, email.getAllRecipients());
			envio.close();

			context.addMessage(null, new FacesMessage("E-mail enviado com sucesso"));

		} catch (AddressException e) {
			FacesMessage msg = new FacesMessage("Erro ao montar mensagem de e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		} catch (MessagingException e) {
			FacesMessage msg = new FacesMessage("Erro ao enviar e-mail! Erro: " + e.getMessage());
			context.addMessage(null, msg);
			return;
		}
	}
	public Properties configuracaoEmail() {
		Properties config = new Properties();

		//Configuração adicional para servidor proxy.
		//Descomentar somente se utliza servidor com proxy.
		/*
		props.setProperty("proxySet", "true");
		props.setProperty("socksProxyHost","127.0.0.1"); //IP do Servidor Proxy
		props.setProperty("socksProxyPort","8080");  //Porta do servidor Proxy
		*/
		config.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
		config.put("mail.smtp.starttls.enable", "true");
		config.put("mail.smtp.host", SERVIDOR_SMTP); //servidor SMTP do GMAIL
		config.put("mail.smtp.auth", "true"); //ativa autenticacao
		config.put("mail.smtp.user", GmailBean.CONTA_GMAIL); // conta que esta enviando o email
		config.put("mail.debug", "true");
		config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP); //porta
		config.put("mail.smtp.socketFactory.port", PORTA_SERVIDOR_SMTP); //mesma porta para o socket
		config.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		config.put("mail.smtp.socketFactory.fallback", "false");
		return config;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
