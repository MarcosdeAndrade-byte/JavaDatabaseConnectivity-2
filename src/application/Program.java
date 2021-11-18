package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
	
		//Formato da data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		
		//Instânciamos Connection e PreparedStatement para utilização no sistema
		Connection conn = null;
		//Objeto utilizado para fazer uma inserção no banco de dados
		PreparedStatement st = null;
		
		try {
			//Nos conectamos ao banco de dados
			conn = DB.getConnection();
			
			//Objeto utilizado para fazer inserção no banco de dados(Script SQL como argumento)
			st = conn.prepareStatement(
					//Comando SQL
					"INSERT INTO seller"
					//Campos que queremos preencher 
				   +"(Name,Email,BirthDate,BaseSalary,DepartmentId)"
				   //Comando SQL
				   + "VALUES"
				   //Comando SQL(Cada interrogação deve coresponder aos campos que queremos preencher)
				   + "(?,?,?,?,?)",
				   Statement.RETURN_GENERATED_KEYS);
			
			//Utilizamos a função set correspondente com o tipo de dado com o capo(Campo,NovoDado)
			st.setString(1,"Irineu");
			st.setString(2, "teste1@gmail.com");
			//Podemos inserir datas apenas do tipo SQL
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4, 1000.1);
			st.setInt(5, 2);

			//Linhas que forma modificadas 
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				 ResultSet rs =  st.getGeneratedKeys();
				 
				 while(rs.next()) {
					 int id = rs.getInt(1);
					 System.out.println("Feito! Id =" + id);
				 }
			}
			else {
				System.out.println("No rown affected!");
			}
		
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
		    DB.closseStatement(st);
			DB.closeConnection();
		}
 }
}