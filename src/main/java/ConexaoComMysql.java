
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoComMysql {
    		
	private static final String URL = "jdbc:mysql://localhost:3306/kav---pedidos";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "12345";
	
	public Connection conexaoBanco() throws SQLException {
		Connection conexao = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
		return conexao;
	}
	
}
