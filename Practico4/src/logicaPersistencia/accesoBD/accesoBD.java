package logicaPersistencia.accesoBD;

import java.sql.*;
import java.util.*;
import java.io.*;

public class accesoBD {
	private Connection con;
	
	public accesoBD() throws SQLException, IOException, ClassNotFoundException{
		Properties prop = new Properties();
		String nomArch = "src/config/datos.dat";
		
		prop.load(new FileInputStream(nomArch));
		
		String urlBD = prop.getProperty("url");
		String usr = prop.getProperty("user");
		String pwd = prop.getProperty("password");
		String driver = prop.getProperty("drivers");
		
		Class.forName(driver);
		con = DriverManager.getConnection(urlBD, usr, pwd);
		System.out.println("Conexion exitosa...");	
	}
	
	public void agregarFolio(String codigo, String caratula, int paginas) {
		try {
			PreparedStatement pstmt = con.prepareStatement(Consultas.AgregarFolio());
			pstmt.setString(1, codigo);
			pstmt.setString(2, caratula);
			pstmt.setInt(3, paginas);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregarRevision(int numero, String codFolio, String descripcion) {
		try {
			PreparedStatement pstmt = con.prepareStatement(Consultas.AgregarRevision());
			pstmt.setInt(1, numero);
			pstmt.setString(2, codFolio);
			pstmt.setString(3, descripcion);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void borrarFolio(String codFolio) {
		try {
			PreparedStatement pstmt = con.prepareStatement(Consultas.BorrarFolio());
			pstmt.setString(1, codFolio);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listarFolios() {
		try {
			PreparedStatement pstmt = con.prepareStatement(Consultas.ListarFolios());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listarRevisiones(String codFolio) {
		try {
			PreparedStatement pstmt = con.prepareStatement(Consultas.ListarRevisiones());
			pstmt.setString(1, codFolio);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void folioMasRevisado() {
		try {
			PreparedStatement pstmt = con.prepareStatement(Consultas.FolioMasRevisado());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
