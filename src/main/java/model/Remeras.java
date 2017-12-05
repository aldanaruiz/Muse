package model;

public class Remeras {
	int id;
	String nombre;
	String diseño;
	String categoria;
	int stock;
	
	
	
	public  Remeras(int id, String n, String d, String c, int s) {
		this.id= id;
		this.nombre = n;
		this.diseño = d;
		this.categoria = c;
		this.stock = s;
		

	}
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nuevoNombre) {
		this.nombre = nuevoNombre;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiseño() {
		return diseño;
	}
	public void setDiseño(String diseño) {
		this.diseño = diseño;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}