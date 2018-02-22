
package tarea06;

import mvc.dominio.Cliente;
import tarea06.AlquilerVehiculos;
import mvc.dominio.Alquiler;
import mvc.dominio.ExcepcionAlquilerVehiculos;
import mvc.dominio.Turismo;
import utilidades.Entrada;


public class Principal {
    
    public static void main(String[] args) {
        

        //Creamos un array de AlquilerVehiculos con los maximos de campos que establecimos en esa clase
        AlquilerVehiculos misAlquileres=new AlquilerVehiculos();
        
        //Precargamos 3 clientes y 3 turismos para poder hacer pruebas mas agilmente
        Cliente cliente1 = new Cliente("Luis", "11111111A", "Calle Granada", "Almería", "04008");
	Cliente cliente2 = new Cliente("Pepe", "22222222B", "Calle Saturno", "Huércal de Almería", "04230");
        Cliente cliente3 = new Cliente("Maria", "33333333C", "Calle Zacatin", "Bentarique", "04569");
	misAlquileres.addCliente(cliente1);
	misAlquileres.addCliente(cliente2);
        misAlquileres.addCliente(cliente3);
	Turismo turismo1 = new Turismo ("2222BBB", "Seat", "Ibiza",1900);
	Turismo turismo2 = new Turismo("3333CCC", "Mercedes", "ClaseC", 3200);
        Turismo turismo3 = new Turismo("4444DDD", "Lamborghini", "Diablo", 12000);
	misAlquileres.addTurismo(turismo1);
	misAlquileres.addTurismo(turismo2);
        misAlquileres.addTurismo(turismo3);
        
        
        //Menu para elegir opciones
        //desde cada una de las opciones llamamos a los metodos que hemos ido creando para todas las funciones que 
        //queremos realizar y lanzando excepciones cuando corresponda
        
        int opcion;
		do {
                        System.out.println("************************");
			System.out.println("   ALQUILERES TUCARRO  ");
			System.out.println("************************");
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
			
                        //validamos que la opcion este entre 0 y 9
			do {
				System.out.print("\nElige una de estas opciones (0-9): ");
				opcion = Entrada.entero();
			} while (opcion < 0 || opcion > 12);
			switch (opcion) {
                            
                            
				case 1:
					Cliente nuevoCliente = null;
					do {
						System.out.println("\nAñadir cliente");
						System.out.println("*********************");
						System.out.print("Nombre: ");
						String nombre = Entrada.cadena();
						System.out.print("DNI: ");
						String dni = Entrada.cadena();
						System.out.print("Dirección: ");
						String direccion = Entrada.cadena();
						System.out.print("Localidad: ");
						String localidad = Entrada.cadena();
						System.out.print("Código postal: ");
						String codigoPostal = Entrada.cadena();
						try {
							nuevoCliente = new Cliente(nombre, dni, direccion, localidad, codigoPostal);
						} catch (ExcepcionAlquilerVehiculos e) {
							System.out.printf("ERROR: %s%n%n", e.getMessage());
							System.out.println("Vuelve a introducir los datos de forma correcta");
						}
					} while (nuevoCliente == null);
                                        try {
						misAlquileres.addCliente(nuevoCliente);
					} catch (ExcepcionAlquilerVehiculos e) {
						System.out.printf("ERROR: %s%n%n", e.getMessage());
					}
					break;
					
				case 2:
					System.out.println("\nBorrar cliente");
					System.out.println("********************");
					System.out.print("\nIntroduce el DNI del cliente a borrar: ");
					String dniBorrar = Entrada.cadena();
					try {
                                                misAlquileres.borrarCliente(dniBorrar);
						System.out.println("El cliente ha sido borrado\n");
					} catch (Exception e) {
						System.out.printf("ERROR: %s%n%n", e.getMessage());
					}
					break;

				case 3:
					System.out.println("\nListado de clientes");
					System.out.println("*********************");
					for (Cliente cliente: misAlquileres.getCliente()) {
						if (cliente != null)
							System.out.println(cliente);
					}
					System.out.println("");
					break;
				case 4:
					Turismo nuevoTurismo=  null;
					System.out.println("\nAñadir turismo");
					System.out.println("********************");
					System.out.print("Matrícula: ");
					String matricula = Entrada.cadena();
					System.out.print("Marca: ");
					String marca = Entrada.cadena();
					System.out.print("Modelo: ");
					String modelo = Entrada.cadena();
					System.out.print("Cilindrada: ");
					int cilindrada = Entrada.entero();
					try {
						nuevoTurismo = new Turismo(matricula, marca, modelo, cilindrada);
						misAlquileres.addTurismo(nuevoTurismo);
					} catch (ExcepcionAlquilerVehiculos e) {
						System.out.printf("ERROR: %s%n%n", e.getMessage());
					}
					break;
				case 5:
					System.out.println("\nBorrar turismo");
					System.out.println("********************");
					System.out.print("\nIntroduce la matrícula del turismo a borrar: ");
					String matriculaBorrar = Entrada.cadena();
					try {
						misAlquileres.borrarTurismo(matriculaBorrar);
						System.out.println("Turismo borrado satisfactoriamente\n");
					} catch (ExcepcionAlquilerVehiculos e) {
						System.out.printf("ERROR: %s%n%n", e.getMessage());
					}
					break;
                                        
        			case 6:
					System.out.println("\nListado de turismos");
					System.out.println("*********************");
					for (Turismo turismo: misAlquileres.getTurismo()) {
						if (turismo != null)
							System.out.println(turismo);
					}
					System.out.println("");
					break;
				case 7:
					System.out.println("\nNuevo Alquiler");
					System.out.println("-------------");
                                        System.out.print("\nIntroduce el DNI del cliente: ");
                                        String dniBuscar = Entrada.cadena();
                                        Cliente clienteBuscado = misAlquileres.getCliente(dniBuscar);
                                        if (clienteBuscado == null)
                                            System.out.println("ERROR: No existe un cliente con ese DNI\n");
                                        
					System.out.print("\nIntroduce la matrícula del turismo: ");
					String matriculaBuscar = Entrada.cadena();
					Turismo vehiculoBuscado = misAlquileres.getTurismo(matriculaBuscar);
                                        if (vehiculoBuscado == null)
                                            System.out.println("ERROR: No existe un turismo con dicha matrícula\n");
                                        

                                        
                                        if (clienteBuscado == null || vehiculoBuscado == null)
                                            System.out.println("ERROR: No se puede crear el alquiler");
                                        
                                        else{
                        
                                            try {
                                                misAlquileres.openAlquiler(clienteBuscado, vehiculoBuscado);
                                                System.out.println("Alquiler dado de alta\n");
                                            }
                                            catch (ExcepcionAlquilerVehiculos e) {
                                                System.out.printf("ERROR: %s%n%n", e.getMessage());
                                            }
                                        }
                                        
                        
					
					break;
				case 8:
					System.out.println("\nCerrar alquiler");
					System.out.println("--------------");
                                        System.out.print("\nIntroduce el DNI del cliente: ");
					dniBuscar = Entrada.cadena();
					clienteBuscado = misAlquileres.getCliente(dniBuscar);
                                        if (clienteBuscado == null)
                                            System.out.println("ERROR: No existe un cliente con ese DNI\n");
					System.out.print("\nIntroduce la matrícula del turismo: ");
					matriculaBuscar = Entrada.cadena();
					vehiculoBuscado =misAlquileres.getTurismo(matriculaBuscar);
					if (vehiculoBuscado == null)
						System.out.println("ERROR: No existe un turismo con dicha matrícula\n");
                                        
                                        if (clienteBuscado == null || vehiculoBuscado == null)
                                            System.out.println("ERROR: No existe este alquiler");
                                        
                                        else{
					try {
                                                misAlquileres.closeAlquiler(clienteBuscado,vehiculoBuscado);
						System.out.println("Alquiler dado de baja");
					} catch (ExcepcionAlquilerVehiculos e) {
						System.out.printf("ERROR: %s%n%n", e.getMessage());
					}
                                        }
					break;
				
				case 9:
					System.out.println("\nListado de trabajos");
					System.out.println("---------------------");
					for (Alquiler alquiler: misAlquileres.getTrabajo()) {
						if (alquiler != null)
							System.out.println(alquiler);
					}
					System.out.println("");
					break;
				default:
					break;
			}
		} while (opcion != 0);
        

        
    }
    
}
