package com.example.demo;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BaseController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/")
	public static String layout(Model template) {
		
		return "home";
	}
	
	@GetMapping("/nosotros")
	public static String nosotros(Model template) {
		template.addAttribute("claseNosotros", "active");
		return "nosotros";
	}

	@GetMapping("/series")
	public static String series(Model template) {

		return "series";  
	}
	@GetMapping("/bandas")
	public static String bandas(Model template) {

		return "bandas";  
	}
	@GetMapping("/pedidosPersonalizados")
	public static String pedidosPersonalizados(Model template, HttpServletRequest request)throws SQLException{
		
		String usuarioLogueado = UsuariosBaseDeDatosController.controlarMarquita(request);
		if(usuarioLogueado == null)  {
			return "redirect:/login";
		}else {
			return "pedidosPersonalizados";
		} // ACA PEDIDOS PERSONALIZADOS ESTA PROTEGIDO

	}

	
	
	
	
	
	
	
	
	
}
