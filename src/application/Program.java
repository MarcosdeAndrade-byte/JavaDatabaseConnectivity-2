package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {

		// Representa uma conex�o ao banco de dados
		Connection conn = null;
		// � usado para criar um objeto que representa a instru��o SQL que ser� executada
		PreparedStatement st = null;

		try {
			// Conex�o com o banco de dados
			conn = DB.getConnection();

			st = conn.prepareStatement("UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE "
					+ "(DepartmentId = ?)");

			//Atualizamos os sal�rios dos funcion�rios em 200 reais
			st.setDouble(1, 200.0);
			//Selecionamos apenas os funcion�rios do departamento 2
			st.setInt(2, 2);

			//N�mero de linhas que foram alteradas 
			int rowsAffected = st.executeUpdate();
			System.out.println("Feito! " + rowsAffected);

		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			DB.closseStatement(st);
            DB.closeConnection();
		}

	}
}