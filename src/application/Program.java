package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {

		// Representa uma conexão ao banco de dados
		Connection conn = null;
		// É usado para criar um objeto que representa a instrução SQL que será executada
		PreparedStatement st = null;

		try {
			// Conexão com o banco de dados
			conn = DB.getConnection();

			st = conn.prepareStatement("UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE "
					+ "(DepartmentId = ?)");

			//Atualizamos os salários dos funcionários em 200 reais
			st.setDouble(1, 200.0);
			//Selecionamos apenas os funcionários do departamento 2
			st.setInt(2, 2);

			//Número de linhas que foram alteradas 
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