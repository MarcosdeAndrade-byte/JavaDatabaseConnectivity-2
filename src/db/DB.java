package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;


public class DB {
	
private static Connection conn = null;

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