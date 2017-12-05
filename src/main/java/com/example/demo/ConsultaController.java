package com.example.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import model.Consulta;


@Controller
public class ConsultaController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/contacto")
	public static String contacto(Model template) {

		
		return "contacto";  
		
	}
	@PostMapping("/recibirConsulta")
		public static String procesarInfoContacto(@RequestParam String nombre, 
												  @RequestParam String consulta,
												  @RequestParam String email,
												  Model template) throws SQLException {
								
								if(nombre.equals("") || consulta.equals("") || email.equals("")){ //si hubo algun error
								//cargar formula de vuelta
								
								template.addAttribute("mensajeError", "No puede haber campos vacios");
								template.addAttribute("nombreAnterior", nombre);
								template.addAttribute("consultaAnterior", consulta);
								template.addAttribute("emailAnterior", email);
								
								return "contacto"; 
								} else {
								enviarCorreo("no-responder@pepito.com",
								"aldananevara@gmail.com",
								"Mensaje de contacto de " + nombre,
								"nombre: " + nombre + " email: " + email + " comentario: " + consulta);
								enviarCorreo(
								"no-responder@pepito.com", 
								email ,
								"Gracias por contactarte!",
								"Recibimos tu consulta, nos vamos a contactar con vos");
								
								Connection connection;
								connection = DriverManager.getConnection(Settings.db_url, Settings.db_user, Settings.db_password);
								
								PreparedStatement ps = connection.prepareStatement("INSERT INTO consultas(nombre , email, consulta) VALUES(?,?,?);");
								ps.setString(1, nombre);
								ps.setString(2, email);
								ps.setString(3, consulta);
								
								ps.executeUpdate();
								// executeUpdate sirve para insert y para update
								return "graciasConsulta";
								}
								
							}
	
		public static void enviarCorreo(String de, String para, String asunto, String contenido){
	        Email from = new Email(de);
	        String subject = asunto;
	        Email to = new Email(para);
	        Content content = new Content("text/plain",contenido);
	        Mail mail = new Mail(from, subject, to, content);

	        SendGrid sg = new SendGrid("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");
	        Request request = new Request();
	        try {
	          request.method = Method.POST;
	          request.endpoint = "mail/send";
	          request.body = mail.build();
	          Response response = sg.api(request);
	          System.out.println(response.statusCode);
	          System.out.println(response.body);
	          System.out.println(response.headers);
	        } catch (IOException ex) {
	          System.out.println(ex.getMessage()); ;
	        }
	    }
	
	
		@GetMapping("/consultas")
			public static String consultas(Model template) throws SQLException {
				
				 Connection connection;
			     connection = DriverManager.getConnection(Settings.db_url, Settings.db_user, Settings.db_password);
				
			     PreparedStatement ps = connection.prepareStatement("SELECT * FROM consultas;"); 
			     ResultSet resultado = ps.executeQuery();
			     
			     ArrayList<Consulta> listaConsultas;
			     listaConsultas= new ArrayList<Consulta>();

			     while( resultado.next() ){
			    	 Consulta miConsulta = new Consulta(resultado.getInt("id"),
			    			 							resultado.getString("nombre"),
			    			 							resultado.getString("email"),
			    			 							resultado.getString("consulta"));
			    	 listaConsultas.add(miConsulta);
			     }
			    
			     template.addAttribute("listaConsultas", listaConsultas);
			     
			     return "consultas";
			}

			
		}
	
	
	
	
	
	
	
	
	
	
	
	

