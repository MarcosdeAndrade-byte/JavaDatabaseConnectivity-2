package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;


public class DB {
	
private static Connection conn = null;

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