package model;

public class Consulta {
	int id;
	String nombre;
	String email;
	String consulta;
	
	
	public  Consulta(int id, String n, String e, String c) {
		this.id= id;
		this.nombre = n;
		this.email = e;
		this.consulta = c;
	}
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nuevoNombre) {
		this.nombre = nuevoNombre;
		
	}
	public String getEmail() {
		return email;
	}
	public void setTipo(String email) {
		this.email = email;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setDescripcion(String consulta) {
		this.consulta = consulta;
	}
	public int getId() {
		return this.id;
	}
	
	
	
	
}

	
	