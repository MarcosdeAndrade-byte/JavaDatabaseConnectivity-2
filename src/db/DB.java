package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DB {
	
private static Connection conn = null;
	
    //M�todo para conectar ao banco de dados
	public static Connection getConnection() {
		//Se vari�vel conn for igual a nulo
		if(conn == null) {
			try {
				//Carregamos as conex�es para vari�vel props
				Properties props = loadProperties();
				//A vari�vel URL do tipo String recebe a propriedade dburl que est� no arquivo db.properties
				String url = props.getProperty("dburl");
				//conn recebe um m�todo que faz a leitura das propriedades URL e props
				conn = DriverManager.getConnection(url,props);
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	//M�todo para fechar conex�o
	public static void closeConnection() {
		//Se conn for diferente de null utilizamos o m�todo close para fexhar a conex�o com o banco de dados
		if(conn != null) {
			try{
				conn.close();
			}
			catch(SQLException e){
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//M�todo para guardar propriedades(Informa��es do banco de dados que est�o na pasta db.properties)
	private static Properties loadProperties() {
		//Acessamos o arquivo db.properties
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			//Criamos(Instanciamos) uma vari�vel do tipo Properties,chamada props.
			Properties props = new Properties();
			//o m�todo load armazena todas as propriedades dentro do arquivo na vari�vel props
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
}
}