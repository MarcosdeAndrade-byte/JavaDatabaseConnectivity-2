package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DB {
	
private static Connection conn = null;
	
    //Método para conectar ao banco de dados
	public static Connection getConnection() {
		//Se variável conn for igual a nulo
		if(conn == null) {
			try {
				//Carregamos as conexões para variável props
				Properties props = loadProperties();
				//A variável URL do tipo String recebe a propriedade dburl que está no arquivo db.properties
				String url = props.getProperty("dburl");
				//conn recebe um método que faz a leitura das propriedades URL e props
				conn = DriverManager.getConnection(url,props);
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	//Método para fechar conexão
	public static void closeConnection() {
		//Se conn for diferente de null utilizamos o método close para fexhar a conexão com o banco de dados
		if(conn != null) {
			try{
				conn.close();
			}
			catch(SQLException e){
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//Método para guardar propriedades(Informações do banco de dados que estão na pasta db.properties)
	private static Properties loadProperties() {
		//Acessamos o arquivo db.properties
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			//Criamos(Instanciamos) uma variável do tipo Properties,chamada props.
			Properties props = new Properties();
			//o método load armazena todas as propriedades dentro do arquivo na variável props
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
}
}