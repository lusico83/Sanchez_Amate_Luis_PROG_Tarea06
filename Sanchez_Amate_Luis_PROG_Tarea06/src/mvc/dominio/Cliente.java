
package mvc.dominio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    
    private String nombre;
    private String dni;
    private DireccionPostal direccionPostal;
    private int identificador;
    private static int numClientes=0;
  
  //Constructor copia
    
  public Cliente(Cliente cliente){
        
        nombre=cliente.getNombre();
        dni=cliente.getDni();
        direccionPostal=cliente.getDireccionPostal();
        identificador=cliente.getIdentificador();        
        
    }
    
  //Constructor con parametros
  
    public Cliente(String nombre, String dni, DireccionPostal direccionPostal){
        
        this.nombre=nombre;
        
        if (compruebaDni(dni))
            this.dni=dni;
        else
            throw new ExcepcionAlquilerVehiculos("DNI no válido");
        
        this.direccionPostal=direccionPostal;
        
        numClientes=numClientes+1;
        identificador=numClientes;
        
    }
        // Metodo que comprueba con una expresion regular la validez de un DNI
        private boolean compruebaDni(String dni) {
		Pattern patron = Pattern.compile("[0-9]{8}[A-Z]");
		Matcher emparejador = patron.matcher(dni);
		return emparejador.matches();
	}
	
        //Metodo que comprueba la validez de un CP
	private boolean compruebaCodigoPostal(String codigoPostal) {
		Pattern patron = Pattern.compile("[0-9]{5}");
		Matcher emparejador = patron.matcher(codigoPostal);
		return emparejador.matches();
	}
	
        //Metodos Get y toString
        
	public String getNombre() {
		return nombre;
	}
	
	public String getDni() {
		return dni;
	}

	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}

	
	public int getIdentificador() {
		return identificador;
	}    
        
        
        public String toString(){
                return String.format("Nombre: %s DNI: %s Direccion: %s Localidad: %s Codigo Postal: %s Identificador %s", nombre, dni, direccionPostal, identificador);
        }
    
      
}
