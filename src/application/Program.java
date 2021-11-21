package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.dbIntegrityException;

public class Program {

	public static void main(String[] args) {

		// Representa uma conexão ao banco de dados
		Connection conn = null;
		// É usado para criar um objeto que representa a instrução SQL que será executada
		Statement st = null;

		try {
			conn = DB.getConnection();
			
			//Através da função setAutoCommit não salvamos as operações automaticamente
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			/*
			int x =1;
			if(x < 2) {
				throw new SQLException("Fake ERROR!");
			}*/
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			//A função .commit salva a operação realizada
			conn.commit();
			
			System.out.println("rows1" + rows1);
			System.out.println("rows2" + rows2);
			
		} catch (SQLException e) {
			try {
				//A função rollback() tenta retornar ao estado original do banco / passível a erro
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