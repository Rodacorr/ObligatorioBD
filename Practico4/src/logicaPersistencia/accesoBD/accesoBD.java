package logicaPersistencia.accesoBD;

import java.sql.*;
import java.util.*;

import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VORevision;

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

	public boolean existeFolio(String codigo) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(Consultas.consultaFolio());
		pstmt.setString(1,codigo);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	public void agregarFolio(VOFolio folio) throws SQLException {
		PreparedStatement ps = con.prepareStatement(Consultas.AgregarFolio());
        ps.setString(1, folio.getCodigo());
        ps.setString(2, folio.getCaratula());
        ps.setInt(3, folio.getPaginas());
        ps.executeUpdate();
	}
	
	public int obtenerUltimaRevision(String codFolio) {
		try {
			PreparedStatement pstmt = con.prepareStatement(Consultas.obtenerRevisiones());
			pstmt.setString(1, codFolio);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public void agregarRevision(String codFolio, VORevision revision) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(Consultas.AgregarRevision());
        pstmt.setString(1, codFolio);
        pstmt.setInt(2, revision.getNumero());
        pstmt.setString(3, revision.getDescripcion());
        pstmt.executeUpdate();
    }
	
	public void borrarFolioRevisiones(String codFolio) throws SQLException {
		if(existeFolio(codFolio)){
			try {
				PreparedStatement pstmt1 = con.prepareStatement(Consultas.BorrarRevisiones());
				pstmt1.setString(1, codFolio);
				pstmt1.executeUpdate();
				pstmt1.close();
				
				PreparedStatement pstmt2 = con.prepareStatement(Consultas.BorrarFolio());
				pstmt2.setString(1, codFolio);
				pstmt2.executeUpdate();
				pstmt2.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
