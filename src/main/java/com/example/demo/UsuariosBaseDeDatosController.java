package com.example.demo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UsuariosBaseDeDatosController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@GetMapping("/editar")
	public static String paginaAdmin(HttpServletRequest request) throws SQLException {
		String usuarioLogueado = controlarMarquita(request);
		if(usuarioLogueado == null ) {
			return "redirect:/login";
		}else {
			return null;
		}// aca /editar esta protegido
		
	}
	@GetMapping("/logout")
	public static String logoutAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("marquita", null);
		
		return "redirect:/";
	}
	@GetMapping("/login")
	public static String loginAdmin() {
		
		return "login";
	}
	
	public static String controlarMarquita(HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		String marquita = (String) session.getAttribute("marquita");
		
		if(marquita!= null) {
			Connection connection;
			connection = DriverManager.getConnection(Settings.db_url, Settings.db_user, Settings.db_password);
			
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuarios WHERE marquita = ?");
			ps.setString(1, marquita);
			
			ResultSet resultado = ps.executeQuery();
			if(resultado.next() ) {
				return resultado.getString("username");
			}else {
				return null;
			}
			
		}else {
			return null;
		}
		
	}
	
	
	
	
	@PostMapping("/procesarLogin")
	public static String procesarLoginAdmin(@RequestParam String usuario, 
											@RequestParam String password,
											HttpServletRequest request) throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection(Settings.db_url, Settings.db_user, Settings.db_password);
		
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuarios WHERE username= ? AND password = ?;");
		ps.setString(1, usuario);
		ps.setString(2, password);
		
		
		ResultSet resultado = ps.executeQuery();
		
		if(resultado.next() ) { // si hay next es que la consulta me trajo usuario!
			//ACEPTADO
			String marquitaNueva = UUID.randomUUID().toString();
			
			HttpSession session = request.getSession();
			session.setAttribute("marquita", marquitaNueva);
			
			ps= connection.prepareStatement("UPDATE usuarios SET marquita = ?");
			ps.setString(1, marquitaNueva);
			ps.executeUpdate();
			
			
			return "redirect:/pedidosPersonalizados";
		} else {
			//RECHAZADO
			// TODO : agregar mensaje de error: usuario y/o contraseña invalidos
			return "login";
		}
		
		
	}
	
	
	
	
	
	
	
	
	
}
