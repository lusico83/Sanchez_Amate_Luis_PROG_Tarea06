
package modelo.dao;

import mvc.dominio.ExcepcionAlquilerVehiculos;
import mvc.dominio.Alquiler;
import mvc.dominio.Turismo;
import mvc.dominio.Turismo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Turismos {
    
private Turismo[] turismos;   
private final int MAX_CLIENTES = 20;

//Constructor del objeto Turismos que es un array 

public Turismos() {
    turismos = new Turismo[MAX_CLIENTES];
}

//Metodo get

public Turismo[] getTurismo() {
    return turismos.clone();
}

  

// Vamos a crear 4 metodos que nos van a facilitar la vida a la hora de buscar indices, indices libres,
// comprobar que el array no esta lleno, desplazar a la izquierda indices
// evitando multiplicar los mismos bucles en varios metodos de turismo
// Metodo buscarPrimerIndiceComprobandoExistencia(Turismo) que localiza la primera posicion vacia
// haciendo otra llamada al metodo indiceNoSuperaTamano que devuelve verdadero si el indice no supera 
// la longitud del array


private int buscarPrimerIndiceLibreComprobandoExistencia(Turismo turismo) {
    int indiceVacio = 0;
    boolean turismoEncontrado = false;
        while (indiceNoSuperaTamano(indiceVacio) && !turismoEncontrado) {
            if (turismos[indiceVacio] == null)
		turismoEncontrado = true;
            else
		if (turismos[indiceVacio].getMatricula().equals(turismo.getMatricula()))
                    throw new ExcepcionAlquilerVehiculos("Ya existe un turismo con ese DNI");
		else
                    indiceVacio++;
	}
	return indiceVacio;
    
    
}

private boolean indiceNoSuperaTamano(int indiceVacio) {
    return indiceVacio < turismos.length;
}

// Metodo desplazarUnaPosicionHaciaIzquierda que nos servirá a la hora de borrar un registro y desplazar 
// el resto de turismo a la izquierda para ocupar la posicion vacia

private void desplazarUnaPosicionHaciaIzquierda(int indice) {
    for (int i = indice; i < turismos.length - 1 && turismos[i] != null; i++) {
	turismos[i] = turismos[i+1];
    }
    if (indice == turismos.length - 1)
	turismos[turismos.length - 1] = null;
    }


//Metodo que coge un matricula y nos devuelve la posicion donde se encuentra ese turismo 

private int buscarIndiceTurismo(String matricula) {
    int indiceBuscado = 0;
    boolean turismoEncontrado = false;
	while (indiceNoSuperaTamano(indiceBuscado) && !turismoEncontrado && turismos[indiceBuscado] != null) {
            if (turismos[indiceBuscado].getMatricula().equals(matricula))
		turismoEncontrado = true;
            else
		indiceBuscado++;
	}
	return turismoEncontrado ? indiceBuscado : turismos.length;
}

//Metodo para añadir turismo  aprovechando los metodos para trabajar creados

public void anadirTurismo(Turismo turismo) {
    int indice = buscarPrimerIndiceLibreComprobandoExistencia(turismo);
    
	if (indiceNoSuperaTamano(indice))
            turismos[indice] = new Turismo(turismo);
        else
            throw new ExcepcionAlquilerVehiculos("El array de turismo está lleno.");
}

// Utilizando los metodos creados anteriormente, el metodo para borrar se simplifica significativamente

public void borrarTurismo(String matricula) {
    int indice = buscarIndiceTurismo(matricula);
	if (indiceNoSuperaTamano(indice)) {
		desplazarUnaPosicionHaciaIzquierda(indice);
	}
	else 
            throw new ExcepcionAlquilerVehiculos("El turismo a borrar no existe");
	
}

// Metodo para buscar turismo

public Turismo buscarTurismo(String matricula) {
int posicion = buscarIndiceTurismo(matricula);

    if (indiceNoSuperaTamano(posicion))
	return new Turismo(turismos[posicion]);
    else
	return null;
}

        
}
