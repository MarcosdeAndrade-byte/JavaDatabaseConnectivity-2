package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		//O Statement serve para executarmos um comando sql que vai recuperar as informa��es no banco de dados
		Statement st = null;
		//J� o ResultSet serve para armazenar os dados(Em forma de tabela)
		ResultSet rs = null;
		
		try {
			//conn recebe uma conex�o com o banco de dados(Nos conectamos com o banco)
			conn = DB.getConnection();
			
			//A vari�vel ST do tipo Statement recebe  conn.createStatement()
			st = conn.createStatement();
			
			//atrav�s do m�todo executeQuery podemos passar um Script SQL
			rs = st.executeQuery("select * from department");
			
			//Enquanto houver linha ser� mostrado os dados na tela
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			//Utilizamos os m�todos da classe DB para fechar as inst�ncias feitas(Um m�todo diferente para cada uma)
			DB.closseResultSet(rs);
			DB.closseStatement(st);
			DB.closeConnection();
		}
	}

}
