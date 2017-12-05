package com.example.demo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Remeras;
import model.StockGeneral;


@Controller
public class UsuariosAdminController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@GetMapping("/admin/stock")
	public static String paginaAdmin(Model template, HttpServletRequest request) throws SQLException {
		
		if(controlarMarquita(request) ) {
			
			 Connection connection;
		     connection = DriverManager.getConnection(Settings.db_url, Settings.db_user, Settings.db_password);
			
		     PreparedStatement ps = connection.prepareStatement("SELECT * FROM stock;");
		     
		     ResultSet resultado = ps.executeQuery();
		     
		     ArrayList<StockGeneral> listaStock;
		     listaStock= new ArrayList<StockGeneral>();

		     while( resultado.next() ){
		    	 StockGeneral miStock = new StockGeneral(	resultado.getInt("id"),
		    			 					resultado.getString("mes"),
		    			 					resultado.getInt("total"),
		    			 					resultado.getInt("series"),
		    			 					resultado.getInt("bandas"));

		    	 listaStock.add(miStock);
		     }
		    
		     return "admin";
		}else {
			return "redirect:/admin/login";
		}
		
	}
	@GetMapping("/admin/stock/remeras")
	public static String paginaAdminRemeras(Model template, HttpServletRequest request) throws SQLException {
		
		if(controlarMarquita(request) ) {
			
			 Connection connection;
		     connection = DriverManager.getConnection(Settings.db_url, Settings.db_user, Settings.db_password);
			
		     PreparedStatement ps = connection.prepareStatement("SELECT * FROM remeras;");
		     
		     ResultSet resultado = ps.executeQuery();
		     
		     ArrayList<Remeras> listaRemeras;
		     listaRemeras= new ArrayList<Remeras>();

		     while( resultado.next() ){
		    	 Remeras miRemera = new Remeras(	resultado.getInt("id"),
					    			 					resultado.getString("nombre"),
					    			 					resultado.getString("diseño"),
					    			 					resultado.getString("categoria"),
					    			 					resultado.getInt("stock"));

		    	 listaRemeras.add(miRemera);
		     }
		    
		     return "remeras";
		}else {
			return "redirect:/admin/login";
		}
		
	}
	
	@GetMapping("/admin/logout")
	public static String logoutAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("marquita", null);
		
		return "redirect:/";
	}
	@GetMapping("/admin/login")
	public static String loginAdmin() {
		
		return "adminLogin";
	}
	
	public static boolean controlarMarquita(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String marquita = (String) session.getAttribute("marquita");
		
		if(marquita!=null && marquita.equals("AUTORIZADO")) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	
	@PostMapping("/admin/procesarLogin")
	public static String procesarLoginAdmin(@RequestParam String usuario, 
											@RequestParam String password,
											HttpServletRequest request) {
		System.out.println("dd");
		if(usuario.equals("admin") && password.equals("comunidad_it")) {
			//ACEPTADO
			HttpSession session = request.getSession();
			session.setAttribute("marquita","AUTORIZADO");
		
			System.out.println(usuario);
			System.out.println(password);
			
			return "redirect:/admin/stock";
		} else {
			System.out.println("rechazado");
			//RECHAZADO
			// TODO : agregar mensaje de error: usuario y/o contraseña invalidos
			return "adminLogin";
		}
		
		
	}
	
	
	
	
	
	
	
	
	
}
