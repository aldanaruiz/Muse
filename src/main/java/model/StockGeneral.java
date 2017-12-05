package model;

public class StockGeneral {
	int id;
	String mes;
	int total;
	int series;
	int bandas;
	
	
	
	public  StockGeneral(int id, String m, int t, int s, int b) {
		this.id= id;
		this.mes = m;
		this.total = t;
		this.series = s;
		this.bandas = b;
		

	}
	public String getMes() {
		return this.mes;
	}
	
	public void setMes(String nuevoMes) {
		this.mes = nuevoMes;
		
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public int getId() {
		return this.id;
	}
	
	public int getBandas() {
		return bandas;
	}
	public void setBandas(int bandas) {
		this.bandas = bandas;
	}
	
	
}
