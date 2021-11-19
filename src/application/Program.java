package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.dbIntegrityException;

public class Program {

	public static void main(String[] args) {

		// Representa uma conexão ao banco de dados
		Connection conn = null;
		// É usado para criar um objeto que representa a instrução SQL que será
		// executada
		PreparedStatement st = null;

		try {
			// Conexão com o banco de dados
			conn = DB.getConnection();

			st = conn.prepareStatement("DELETE FROM department " + "WHERE " + "Id = ?");

			st.setInt(1, 5);

			// Número de linhas que foram alteradas
			int rowsAffected = st.executeUpdate();

			System.out.println("Done! Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			//Podemos capturar uma exceção personalizada
			throw new dbIntegrityException(e.getMessage());

		} finally {
			DB.closseStatement(st);
			DB.closeConnection();
		}

	}
}