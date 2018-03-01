
package mvc.dominio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DireccionPostal {
	
	private String direccion;
	private String localidad;
	private String codigoPostal;
	
	public DireccionPostal(DireccionPostal direccionPostal) {
		direccion = direccionPostal.getCalle();
		localidad = direccionPostal.getLocalidad();
		codigoPostal = direccionPostal.getCodigoPostal();
	}
	
	public DireccionPostal(String direccion, String localidad, String codigoPostal) {
		setDireccion(direccion);
		setLocalidad(localidad);
		setCodgioPostal(codigoPostal);
	}

	private void setCodgioPostal(String codigoPostal) {
		if (compruebaCodigoPostal(codigoPostal))
			this.codigoPostal = codigoPostal;
		else
			throw new ExcepcionAlquilerVehiculos("Código Postal no válido");
	}

	private void setLocalidad(String localidad) {
		if (localidad != null && !localidad.equals(""))
			this.localidad = localidad;
		else
			throw new ExcepcionAlquilerVehiculos("Localidad no válida");
	}

	private void setDireccion(String direccion) {
		if (direccion != null && !direccion.equals(""))
			this.direccion = direccion;
		else 
			throw new ExcepcionAlquilerVehiculos("Dirección no válida");
	}
	
	boolean compruebaCodigoPostal(String codigoPostal) {
		Pattern patron = Pattern.compile("[0-9]{5}");
		Matcher emparejador = patron.matcher(codigoPostal);
		return emparejador.matches();
	}
	
	public String getCalle() {
		return direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	public String toString() {
		return String.format("Direccion: %s Localidad: %s Código Postal: %s", direccion, localidad, codigoPostal);
	}

}