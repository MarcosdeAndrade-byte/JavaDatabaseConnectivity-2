package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.dbIntegrityException;

public class Program {

	public static void main(String[] args) {

		// Representa uma conex�o ao banco de dados
		Connection conn = null;
		// � usado para criar um objeto que representa a instru��o SQL que ser� executada
		Statement st = null;

		try {
			conn = DB.getConnection();
			
			//Atrav�s da fun��o setAutoCommit n�o salvamos as opera��es automaticamente
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			/*
			int x =1;
			if(x < 2) {
				throw new SQLException("Fake ERROR!");
			}*/
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			//A fun��o .commit salva a opera��o realizada
			conn.commit();
			
			System.out.println("rows1" + rows1);
			System.out.println("rows2" + rows2);
			
		} catch (SQLException e) {
			try {
				//A fun��o rollback() tenta retornar ao estado original do banco / pass�vel a erro
				conn.rollback();
				throw new dbIntegrityException("Transaction rolled back! Caused by :" + e.getMessage());
			} catch (SQLException e1) {
				throw new dbIntegrityException("Error trying to rollback!  Caused by :" + e1.getMessage());
			}

		} finally {
			DB.closseStatement(st);
			DB.closeConnection();
		}

	}
}