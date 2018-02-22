
package tarea06;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlquilerVehiculos {
    
    private Cliente[] clientes;
    private Turismo[] turismos;
    private Alquiler[] alquileres;
    private final int MAX_TURISMOS = 20;
    private final int MAX_CLIENTES = 20;
    private final int MAX_ALQUILERES = 20;
    
    //Constructor del objeto AlquilerVehiculos que es un array de tres tipos de objetos
    public AlquilerVehiculos() {
	clientes = new Cliente[MAX_CLIENTES];
	turismos = new Turismo[MAX_TURISMOS];
	alquileres = new Alquiler[MAX_ALQUILERES];
    }
    //Metodos get
    public Cliente[] getCliente() {
	return clientes;
    }
	
    public Turismo[] getTurismo() {
	return turismos;
    }
	
    public Alquiler[] getTrabajo() {
        return alquileres;
    }
    
    //Otro metodo getCliente pero este coge como parametro un DNI y hacemos una busqueda del array por si existe
    //en caso de existir, devuelve el contenido de cliente con ese DNI, sino, null
    
    public Cliente getCliente(String dni){
        int posicion = 0;
	boolean encontrado = false;
        
            while (posicion < clientes.length && !encontrado) {
                
		if (clientes[posicion] != null && clientes[posicion].getDni().equals(dni))
                    encontrado = true;
		else
                    posicion++;
		}
		if (encontrado)
                    return clientes[posicion];
		else
                    return null;
                
    }
    
    //Metodo para añadir un cliente al primer hueco libre(si quedan)del array
    public void addCliente(Cliente cliente){
        
        int posicion = 0;
	boolean posicionEncontrada = false;
        
            while (posicion < clientes.length && !posicionEncontrada) {
                    if (clientes[posicion] == null)
            		posicionEncontrada = true;
                    else
			if (clientes[posicion].getDni().equals(cliente.getDni()))
				throw new ExcepcionAlquilerVehiculos("Este DNI ya esta en uso");
			else
                                posicion++;
		}
            if (posicionEncontrada)
                    clientes[posicion] = cliente;
            else
		    throw new ExcepcionAlquilerVehiculos("No caben mas clientes");
        
    }
    
    //Metodo que pide un dni como parametro y recorre el array buscando coincidencias de DNI
    //y cuando encuentra una coincidencia, deslaza todos los clientes un hueco hacia la izquierda
    //y elimina el cliente coincidente
    
    public void borrarCliente(String dni) {
            int posicion = 0;
            boolean encontrado = false;
            
       	    while (posicion < clientes.length && !encontrado) {
		if (clientes[posicion] != null && clientes[posicion].getDni().equals(dni))
                    encontrado = true;
		else
                    posicion++;
		}
            
		if (encontrado){
                    for (int i=posicion;i<clientes.length-1;i++) {
			clientes[i]=clientes[i+1];
			}
			clientes[clientes.length-1]=null;
		}
		else {
                    throw new ExcepcionAlquilerVehiculos("Este DNI no esta registrado");
		}
    }
    
    //Muestra los datos de un cliente con una matricula que coge como parametro
    
    public Turismo getTurismo(String matricula){
        int posicion = 0;
	boolean posicionEncontrada = false;
                
            while (posicion < turismos.length && !posicionEncontrada) {
                    
                if (turismos[posicion] != null && turismos[posicion].getMatricula().equals(matricula))
                    posicionEncontrada = true;
                else
                    posicion++;
		}
		if (posicionEncontrada)
                    return turismos[posicion];
		else
                    return null;
                
    }

    //Metodo que funciona igual que el de addCliente pero con turismos, pero haciendo busquedas de matricula
    
     public void addTurismo(Turismo turismo){
        
        int posicion = 0;
	boolean posicionEncontrada = false;
        
            while (posicion < turismos.length && !posicionEncontrada) {
                    if (turismos[posicion] == null)
            		posicionEncontrada = true;
                    else
			if (turismos[posicion].getMatricula().equals(turismo.getMatricula()))
				throw new ExcepcionAlquilerVehiculos("Esta matricula ya esta en uso");
			else
                                posicion++;
		}

            if (posicionEncontrada)
                    turismos[posicion]=turismo;
            else
		    throw new ExcepcionAlquilerVehiculos("No caben mas clientes");
        
    }
    
    //Se comporta igual que borrarCliente pero con matriculas en lugar de DNI
     
    public void borrarTurismo(String matricula) {
            int posicion = 0;
            boolean encontrado = false;
            
       	    while (posicion < turismos.length && !encontrado) {
		if (turismos[posicion] != null && turismos[posicion].getMatricula().equals(matricula))
                    encontrado = true;
		else
                    posicion++;
		}
            
		if (encontrado){
                    for (int i=posicion;i<turismos.length-1;i++) {
			turismos[i]=turismos[i+1];
			}
			turismos[turismos.length-1]=null;
		}
		else {
                    throw new ExcepcionAlquilerVehiculos("Esta matricula no esta registrada");
		}
    } 
    
    //En este caso vamos recorriendo el array buscando si la matricula ya esta alquilada (disponible=false)
    //En caso contrario,buscaremos el primer hueco vacio en el array alquileres y si queda alguno disponible
    //ya podemos guardar los datos del cliente y el coche
    
    public void openAlquiler(Cliente cliente, Turismo turismo) {
		int posicion = 0;
		boolean posicionEncontrada = false;
		while (posicion < alquileres.length && !posicionEncontrada) {
			if (alquileres[posicion] == null)
				posicionEncontrada = true;
			else
				if (alquileres[posicion].getTurismo().getMatricula().equals(turismo.getMatricula()) && !alquileres[posicion].getTurismo().disponible){
                                posicionEncontrada=true;   
                                throw new ExcepcionAlquilerVehiculos("Este vehiculo esta alquilado");
                                }
				else
					posicion++;
		}
		if (posicionEncontrada)
			alquileres[posicion] = new Alquiler(cliente, turismo);
		else
			throw new ExcepcionAlquilerVehiculos("No caben mas alquileres");
                
    }
    //Vamos recorriendo el array de alquileres y comprobando que haya alguna coincidencia con el cliente y turismo aportados
    //comparando matriculas, dni y que el coche no este disponible
    //cuando lo encontremos llamamos al metodo close para que destruya esa posicion del array
    
    public void closeAlquiler(Cliente cliente, Turismo turismo) {
		int posicion = 0;
		boolean encontrado = false;
		while (posicion < alquileres.length && !encontrado) {
			if (alquileres[posicion] != null && 
					alquileres[posicion].getTurismo().getMatricula().equals(turismo.getMatricula()) &&
					alquileres[posicion].getDias()==0 &&
                                        alquileres[posicion].getCliente().getDni().equals(cliente.getDni()))
				encontrado = true;
			else
				posicion++;
		}
		if (encontrado)
			alquileres[posicion].close();
		else
			throw new ExcepcionAlquilerVehiculos("Este cliente no ha alquilado este coche");
    }
}    
