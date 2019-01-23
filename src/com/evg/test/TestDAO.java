package com.evg.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.api.jdbc.Statement;

public class TestDAO {
	private Conexion con;
	
	public TestDAO(String url, String userName, String password) throws SQLException {
		con = new Conexion(url, userName, password);
		//con.connection();
		if (con.getConnection()!=null) {
			
			System.out.println("Esta es la conexion: "+con.getConnection());
			System.out.println("Estamos conectados a la bd ");
		}
	}
	
	/**
	 * registra una revista
	 * @return true 
	 */
	public boolean registrar(Articulo a) {
		boolean estado = false; 
		Statement stm;
		//el ID es null porque la base de datos lo tiene autoincrementable
		String sql = "INSERT INTO articulos VALUES(NULL,'"+a.getNombre()+"','"+a.getDescripcion()+"',"+a.getPrecio()+" )";
		
		try {
			con.connection(); //abrimos la conexion
			con.getConnection().setAutoCommit(false); // se indica el inicio de la transaccion
			stm = (Statement) con.getConnection().createStatement();
			stm.executeUpdate(sql);
			con.getConnection().commit(); // si todo va bien hacemos comit y guardamos los datos
			stm.close();
			con.discconect();
			estado = true;
			
		} catch (SQLException e) {
			try {
				con.getConnection().rollback(); // si algo salio mal en la transaccion hacemos roollback y no se guarda ningun registro
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		}
		return estado;
	}
	
	/**
	 * Atualiza el nombre de un articulo
	 * @param a
	 * @return
	 */
	public boolean editar(Articulo a) {
		boolean estado = false; 
		Statement stm;
		
		String sql = "UPDATE articulos SET nombre='"+a.getNombre()+"' WHERE id="+a.getId();
		
		try {
			con.connection(); //abrimos la conexion
			stm = (Statement) con.getConnection().createStatement();
			stm.executeUpdate(sql);
			stm.close();
			con.discconect();
			estado = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estado;
	}
	
	/**
	 * Elimina el articulo por ID 
	 * @param a
	 * @return
	 */
	public boolean eliminar(Articulo a) {
		boolean estado = false; 
		Statement stm;
		
		String sql = "DELETE FROM articulos WHERE id="+a.getId();
		
		try {
			con.connection(); //abrimos la conexion
			stm = (Statement) con.getConnection().createStatement();
			stm.executeUpdate(sql);
			stm.close();
			con.discconect();
			estado = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estado;
	}
	
	/**
	 * Obtiene todos los articulos de la BD
	 */
	public void obtenerArticulos() {
		Statement stm;
		ResultSet res = null;
		Articulo a;
		
		String sql = "SELECT * FROM articulos";
		
		try {
			con.connection(); //abrimos la conexion
			stm = (Statement) con.getConnection().createStatement();
			res = stm.executeQuery(sql);
			
			System.out.println("--> Estos son los articulos en la BD:");
			while (res.next()) {
				a = new Articulo(res.getInt("id"),res.getString("nombre"),res.getString("descripcion"), res.getDouble("precio"));
				System.out.println(a.toString());
			}
			stm.close();
			con.discconect();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtiene el articulo por el id
	 * @param id
	 */
	public void obtenerArticulo(int id) {
		Statement stm;
		ResultSet res = null;
		Articulo a;
		
		String sql = "SELECT * FROM articulos WHERE id="+id;
		
		try {
			con.connection(); //abrimos la conexion
			stm = (Statement) con.getConnection().createStatement();
			res = stm.executeQuery(sql);
			
			System.out.println("--> Este es el articulo buscado en la BD:");
			if (res.next()) {
				a = new Articulo(res.getInt("id"),res.getString("nombre"),res.getString("descripcion"), res.getDouble("precio"));
				System.out.println(a.toString());
			}
			stm.close();
			con.discconect();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
