package com.evg.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TestDAO test;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	String jdbcUrl = getServletContext().getInitParameter("jdbcURL")+"?useSSL=false&serverTimezone=UTC";
    	String jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
    	String jdbcpassword = getServletContext().getInitParameter("jdbcPassword");
    	System.out.println("--> esta es la conexion: "+jdbcUrl);
    	try {
			test = new TestDAO(jdbcUrl, jdbcUserName, jdbcpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//insertamos un articulo
		
//		Articulo nuevoArticulo = new Articulo(0, "Televisor", "Televisor LG 25''", 560);
//		if(test.registrar(nuevoArticulo)) {
//			System.out.println("Se registro el arituculo"+nuevoArticulo.toString());
//		}else {
//			System.out.println("No se agrego el nuevo articulo");
//		}
		
		String op = request.getParameter("opcion");
		switch (op) {
		case "1":
			Articulo a1 = new Articulo(0, "televisor", "televisor lg 45''", 560);
			if(test.registrar(a1)) {
				System.out.println("controller: Se agrego un articulo ");
			}else {
				System.out.println("controller: NO se agrego un articulo ");
			}
			break;

		case "2":
			Articulo a2 = new Articulo(2, "Computador", "televisor lg 45''", 560);
			if(test.editar(a2)) {
				System.out.println("controller: Se modifico un articulo ");
			}else {
				System.out.println("controller: NO se modifico un articulo ");
			}
			break;
			
		case "3":
			Articulo a3 = new Articulo(3, "Computador", "televisor lg 45''", 560);
			if(test.eliminar(a3)) {
				System.out.println("controller: Se elimino un articulo ");
			}else {
				System.out.println("controller: NO se elimino un articulo ");
			}
			break;

		case "4":
			test.obtenerArticulo(2);
			break;
		
		case "5":
			test.obtenerArticulos();
			break;
			
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
