
package modelo.dao;

import mvc.dominio.ExcepcionAlquilerVehiculos;
import mvc.dominio.Alquiler;
import mvc.dominio.Turismo;
import mvc.dominio.Cliente;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clientes {
    
private Cliente[] clientes;   
private final int MAX_CLIENTES = 20;

//Constructor del objeto Clientes que es un array 

public Clientes() {
    clientes = new Cliente[MAX_CLIENTES];
}

//Metodo get

public Cliente[] getCliente() {
    return clientes.clone();
}

  

// Vamos a crear 4 metodos que nos van a facilitar la vida a la hora de buscar indices, indices libres,
// comprobar que el array no esta lleno, desplazar a la izquierda indices
// evitando multiplicar los mismos bucles en varios metodos de cliente
// Metodo buscarPrimerIndiceComprobandoExistencia(Cliente) que localiza la primera posicion vacia
// haciendo otra llamada al metodo indiceNoSuperaTamano que devuelve verdadero si el indice no supera 
// la longitud del array


private int buscarPrimerIndiceLibreComprobandoExistencia(Cliente cliente) {
    int indiceVacio = 0;
    boolean clienteEncontrado = false;
        while (indiceNoSuperaTamano(indiceVacio) && !clienteEncontrado) {
            if (clientes[indiceVacio] == null)
		clienteEncontrado = true;
            else
		if (clientes[indiceVacio].getDni().equals(cliente.getDni()))
                    throw new ExcepcionAlquilerVehiculos("Ya existe un cliente con ese DNI");
		else
                    indiceVacio++;
	}
	return indiceVacio;
    
    
}

private boolean indiceNoSuperaTamano(int indiceVacio) {
    return indiceVacio < clientes.length;
}

// Metodo desplazarUnaPosicionHaciaIzquierda que nos servirá a la hora de borrar un registro y desplazar 
// el resto de clientes a la izquierda para ocupar la posicion vacia

private void desplazarUnaPosicionHaciaIzquierda(int indice) {
    for (int i = indice; i < clientes.length - 1 && clientes[i] != null; i++) {
	clientes[i] = clientes[i+1];
    }
    if (indice == clientes.length - 1)
	clientes[clientes.length - 1] = null;
    }


//Metodo que coge un dni y nos devuelve la posicion donde se encuentra ese cliente 

private int buscarIndiceCliente(String dni) {
    int indiceBuscado = 0;
    boolean clienteEncontrado = false;
	while (indiceNoSuperaTamano(indiceBuscado) && !clienteEncontrado && clientes[indiceBuscado] != null) {
            if (clientes[indiceBuscado].getDni().equals(dni))
		clienteEncontrado = true;
            else
		indiceBuscado++;
	}
	return clienteEncontrado ? indiceBuscado : clientes.length;
}

//Metodo para añadir clientes  aprovechando los metodos para trabajar creados

public void anadirCliente(Cliente cliente) {
    int indice = buscarPrimerIndiceLibreComprobandoExistencia(cliente);
    
	if (indiceNoSuperaTamano(indice))
            clientes[indice] = new Cliente(cliente);
        else
            throw new ExcepcionAlquilerVehiculos("El array de clientes está lleno.");
}

// Utilizando los metodos creados anteriormente, el metodo para borrar se simplifica significativamente

public void borrarCliente(String dni) {
    int indice = buscarIndiceCliente(dni);
	if (indiceNoSuperaTamano(indice)) {
		desplazarUnaPosicionHaciaIzquierda(indice);
	}
	else 
            throw new ExcepcionAlquilerVehiculos("El cliente a borrar no existe");
	
}

// Metodo para buscar clientes

public Cliente buscarCliente(String dni) {
int posicion = buscarIndiceCliente(dni);

    if (indiceNoSuperaTamano(posicion))
	return new Cliente(clientes[posicion]);
    else
	return null;
}

        
}
