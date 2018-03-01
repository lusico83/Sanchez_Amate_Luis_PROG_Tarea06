
package mvc.vista;

import mvc.dominio.Cliente;
import modelo.dao.Clientes;
import modelo.dao.Alquileres;
import mvc.dominio.Alquiler;
import mvc.dominio.ExcepcionAlquilerVehiculos;
import mvc.dominio.Turismo;
import modelo.dao.Turismos;
import mvc.dominio.DireccionPostal;
import utilidades.Entrada;
import mvc.vista.IUTextual;

public class Consola {
    

public static void mostrarCabecera(String mensaje) {
    System.out.printf("%n%s%n", mensaje);
    System.out.println(String.format("%0" + mensaje.length() + "d%n", 0).replace("0", "*"));
}    

public static void mostrarMenu() {

    System.out.println("1.- Añadir cliente");
    System.out.println("2.- Borrar cliente");
    System.out.println("3.- Listar clientes");
    System.out.println("4.- Añadir turismo");
    System.out.println("5.- Borrar turismoo");
    System.out.println("6.- Listar turismos");
    System.out.println("7.- Abrir alquiler");
    System.out.println("8.- Cerrar alquiler");
    System.out.println("9.- Listar alquileres");
    System.out.println("0.- Salir");
	
}

public static int elegirOpcion() {
    int opcion;
    do {
            System.out.print("\nElige una de estas opciones (0-9): ");
            opcion = Entrada.entero();
    } while (opcion < 0 || opcion > 12);
    
    return opcion;
}

public static Cliente leerCliente() {
		Cliente cliente = null;
		System.out.print("Nombre: ");
		String nombre = Entrada.cadena();
		System.out.print("DNI: ");
		String dni = Entrada.cadena();
		System.out.print("Teléfono: ");
		String telefono = Entrada.cadena();
		System.out.print("Dirección: ");
		String direccion = Entrada.cadena();
		System.out.print("Localidad: ");
		String localidad = Entrada.cadena();
		System.out.print("Código postal: ");
		String codigoPostal = Entrada.cadena();
		cliente = new Cliente(nombre, dni, new DireccionPostal(direccion, localidad, codigoPostal));
		return cliente;
}

public static String leerDni() {
		System.out.print("Introduce el DNI del cliente: ");
		String dniBorrar = Entrada.cadena();
		return dniBorrar;
}

public static Turismo leerTurismo() {
		Turismo nuevoTurismo = null;
		System.out.print("Matrícula: ");
		String matricula = Entrada.cadena();
		System.out.print("Marca: ");
		String marca = Entrada.cadena();
		System.out.print("Modelo: ");
		String modelo = Entrada.cadena();
		System.out.print("Cilindrada: ");
		int cilindrada = Entrada.entero();
		nuevoTurismo= new Turismo(matricula, marca, modelo, cilindrada);
		return nuevoTurismo;
}

public static String leerMatricula() {
		System.out.print("Introduce la matrícula del vehículo: ");
		String matriculaBorrar = Entrada.cadena();
		return matriculaBorrar;
}


    

}

