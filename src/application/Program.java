package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.dbIntegrityException;

public class Program {

	public static void main(String[] args) {

		// Representa uma conex�o ao banco de dados
		Connection conn = null;
		// � usado para criar um objeto que representa a instru��o SQL que ser�
		// executada
		PreparedStatement st = null;

		try {
			// Conex�o com o banco de dados
			conn = DB.getConnection();

			st = conn.prepareStatement("DELETE FROM department " + "WHERE " + "Id = ?");

			st.setInt(1, 5);

			// N�mero de linhas que foram alteradas
			int rowsAffected = st.executeUpdate();

			System.out.println("Done! Rows affected: " + rowsAffected);

		} catch (SQLException e) {
			//Podemos capturar uma exce��o personalizada
			throw new dbIntegrityException(e.getMessage());

		} finally {
			DB.closseStatement(st);
			DB.closeConnection();
		}

	}
}