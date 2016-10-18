package server;

public class Sala {
	private String nombre;
	
	public Sala(String nombre){
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object obj){
		return nombre.equals(((Sala)obj).nombre);
		
	}
	
	 @Override
	    public int hashCode() {
	        int hash = 1;
	        hash = hash * 17 + Integer.parseInt(nombre);
	        return hash;
	    }
}
