package ao.co.always.financeiro.util;

import javax.naming.NamingException;

import org.apache.commons.mail.EmailException;

public class UtilException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public UtilException(String string){
		
	}

	public UtilException(NamingException e) {
		// TODO Auto-generated constructor stub
	}

	public UtilException(EmailException e) {
		// TODO Auto-generated constructor stub
	}

}
