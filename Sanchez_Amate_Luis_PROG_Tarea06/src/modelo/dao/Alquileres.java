
package modelo.dao;

import mvc.dominio.ExcepcionAlquilerVehiculos;
import mvc.dominio.Alquiler;
import mvc.dominio.Turismo;
import mvc.dominio.Cliente;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alquileres {
    
private Alquiler[] alquileres;
private final int MAX_ALQUILERES = 20;
    
 //Constructor del objeto AlquilerVehiculos 

 public Alquileres() {
    alquileres = new Alquiler[MAX_ALQUILERES];
}
 
//Metodo Get
 
public Alquiler[] getAlquileres() {
    return alquileres;
}
        
private int buscarPrimerIndiceLibreComprobandoExistencia(Turismo turismo) {
    int indiceVacio = 0;
    boolean turismoEncontrado = false;
        while (indiceNoSuperaTamano(indiceVacio) && !turismoEncontrado) {
            if (alquileres[indiceVacio] == null)
		turismoEncontrado = true;
            else
		if (alquileres[indiceVacio].getTurismo().equals(turismo.getMatricula()))
                    throw new ExcepcionAlquilerVehiculos("Ya existe un turismo con ese DNI");
		else
                    indiceVacio++;
	}
	return indiceVacio;
    
    
}

private boolean indiceNoSuperaTamano(int indiceVacio) {
    return indiceVacio < alquileres.length;
}

// Metodo desplazarUnaPosicionHaciaIzquierda que nos servirá a la hora de borrar un registro y desplazar 
// el resto de alquileres a la izquierda para ocupar la posicion vacia

private void desplazarUnaPosicionHaciaIzquierda(int indice) {
    for (int i = indice; i < alquileres.length - 1 && alquileres[i] != null; i++) {
	alquileres[i] = alquileres[i+1];
    }
    if (indice == alquileres.length - 1)
	alquileres[alquileres.length - 1] = null;
    }


//Metodo que coge un matricula y nos devuelve la posicion donde se encuentra ese turismo 

private int buscarIndiceTurismo(String matricula) {
    int indiceBuscado = 0;
    boolean turismoEncontrado = false;
	while (indiceNoSuperaTamano(indiceBuscado) && !turismoEncontrado && alquileres[indiceBuscado] != null) {
            if (alquileres[indiceBuscado].getTurismo().equals(matricula))
		turismoEncontrado = true;
            else
		indiceBuscado++;
	}
	return turismoEncontrado ? indiceBuscado : alquileres.length;
}

// Metodo para crear un nuevo alquiler tras buscar hueco vacio y comprobar que es posible almacenarlo

public void abrirTrabajo(Cliente cliente, Turismo turismo) {
    
    int indice = buscarPrimerIndiceLibreComprobandoExistenciaOtroAbierto(turismo);
    
    if (indiceNoSuperaTamano(indice))
	alquileres[indice] = new Alquiler(cliente, turismo) ;
    else
	throw new ExcepcionAlquilerVehiculos("El array de trabajos está lleno.");
}

// Metodo para cerrar un alquiler

public void cerrar(Cliente cliente, Turismo turismo) {
		int indice = buscarTrabajoAbierto(turismo);
		if (indiceNoSuperaTamano(indice))
			alquileres[indice].close();
		else
			throw new ExcepcionAlquilerVehiculos("No hay ningún trabajo abierto para ese vehículo");
}

// Metodos de busqueda de indices 


private int buscarTrabajoAbierto(Turismo turismo) {
		int indice = 0;
		boolean trabajoEncontrado = false;
		while (indiceNoSuperaTamano(indice) && !trabajoEncontrado && alquileres[indice] != null) {
			if (alquileres[indice].getTurismo().getMatricula().equals(turismo.getMatricula()) &&
					alquileres[indice].getDias()>0)
				trabajoEncontrado = true;
			else
				indice++;
		}
		return trabajoEncontrado ? indice : alquileres.length;
	}


private int buscarPrimerIndiceLibreComprobandoExistenciaOtroAbierto(Turismo turismo) {
    int indice = 0;
    boolean trabajoEncontrado = false;
	while (indiceNoSuperaTamano(indice) && !trabajoEncontrado) {
        	if (alquileres[indice] == null)
                	trabajoEncontrado = true;
		else
			if (alquileres[indice].getTurismo().getMatricula().equals(turismo.getMatricula()) && 
				alquileres[indice].getDias()>0)
				throw new ExcepcionAlquilerVehiculos("Ya existe un trabajo abierto para este vehículo");
			else
                        	indice++;
		}
    return indice;
}
}   


