package ao.co.always.capitulo3.conexao;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class ConectaMysql {

		public static void main(String[] args) {
			
		Connection conexao = null;
			
			try {
				// Registrando a classe JDBC no sistema em tempo de execu??o
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost/agenda";
				String usuario = "root";
				String senha = "2016";

				conexao = (Connection) DriverManager.getConnection(url, usuario, senha);
				System.out.println("Conectou!");
			} catch (ClassNotFoundException e) {
				System.out.println("Classe não encontrada. Erro: " + e.getMessage());
			} catch (SQLException e) {
				System.out.println("Ocorreu um erro de SQL. Erro: " + e.getMessage());
			} finally {
				try {
					conexao.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar a conexão. Erro: " + e.getMessage());
				} 		
			}
		}
	}

