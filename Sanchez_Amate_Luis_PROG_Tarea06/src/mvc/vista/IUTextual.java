
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



public class IUTextual {
    
    
    public static void main(String[] args) {
        

        //Creamos arrays de Clientes, Turismos y Alquileres
        Clientes misClientes=new Clientes();
        Turismos misTurismos=new Turismos();
        Alquileres misAlquileres=new Alquileres();
    
        
        //Precargamos 3 direcciones postales para clientes y 3 turismos para poder hacer pruebas mas agilmente
        DireccionPostal direccion1 = new DireccionPostal("Calle Granada","Almería","04008");
        DireccionPostal direccion2 = new DireccionPostal("Calle Saturno", "Huércal de Almería", "04230");
        DireccionPostal direccion3 = new DireccionPostal("Calle Zacatin", "Bentarique", "04569");
        Cliente cliente1 = new Cliente("Luis", "11111111A",direccion1);
	Cliente cliente2 = new Cliente("Pepe", "22222222B", direccion2);
        Cliente cliente3 = new Cliente("Maria", "33333333C", direccion3);
	misClientes.anadirCliente(cliente1);
	misClientes.anadirCliente(cliente2);
        misClientes.anadirCliente(cliente3);
	Turismo turismo1 = new Turismo ("2222BBB", "Seat", "Ibiza",1900);
	Turismo turismo2 = new Turismo("3333CCC", "Mercedes", "ClaseC", 3200);
        Turismo turismo3 = new Turismo("4444DDD", "Lamborghini", "Diablo", 12000);
	misTurismos.anadirTurismo(turismo1);
	misTurismos.anadirTurismo(turismo2);
        misTurismos.anadirTurismo(turismo3);
        
        
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
                                        DireccionPostal nuevaDireccion=null;
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
                                                        nuevaDireccion= new DireccionPostal(direccion,localidad,codigoPostal);
							nuevoCliente = new Cliente(nombre, dni, nuevaDireccion);
						} catch (ExcepcionAlquilerVehiculos e) {
							System.out.printf("ERROR: %s%n%n", e.getMessage());
							System.out.println("Vuelve a introducir los datos de forma correcta");
						}
					} while (nuevoCliente == null);
                                        try {
						misClientes.anadirCliente(nuevoCliente);
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
                                                misClientes.borrarCliente(dniBorrar);
						System.out.println("El cliente ha sido borrado\n");
					} catch (Exception e) {
						System.out.printf("ERROR: %s%n%n", e.getMessage());
					}
					break;

				case 3:
					System.out.println("\nListado de clientes");
					System.out.println("*********************");
					for (Cliente cliente: misClientes.getCliente()) {
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
						misTurismos.anadirTurismo(nuevoTurismo);
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
						misTurismos.borrarTurismo(matriculaBorrar);
						System.out.println("Turismo borrado satisfactoriamente\n");
					} catch (ExcepcionAlquilerVehiculos e) {
						System.out.printf("ERROR: %s%n%n", e.getMessage());
					}
					break;
                                        
        			case 6:
					System.out.println("\nListado de turismos");
					System.out.println("*********************");
					for (Turismo turismo: misTurismos.getTurismo()) {
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
                                        Cliente clienteBuscado = misClientes.buscarCliente(dniBuscar);
                                        if (clienteBuscado == null)
                                            System.out.println("ERROR: No existe un cliente con ese DNI\n");
                                        
					System.out.print("\nIntroduce la matrícula del turismo: ");
					String matriculaBuscar = Entrada.cadena();
					Turismo vehiculoBuscado = misTurismos.buscarTurismo(matriculaBuscar);
                                        if (vehiculoBuscado == null)
                                            System.out.println("ERROR: No existe un turismo con dicha matrícula\n");
                                        

                                        
                                        if (clienteBuscado == null || vehiculoBuscado == null)
                                            System.out.println("ERROR: No se puede crear el alquiler");
                                        
                                        else{
                        
                                            try {
                                                misAlquileres.abrirAlquiler(clienteBuscado, vehiculoBuscado);
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
					clienteBuscado = misClientes.buscarCliente(dniBuscar);
                                        if (clienteBuscado == null)
                                            System.out.println("ERROR: No existe un cliente con ese DNI\n");
					System.out.print("\nIntroduce la matrícula del turismo: ");
					matriculaBuscar = Entrada.cadena();
					vehiculoBuscado =misTurismos.buscarTurismo(matriculaBuscar);
					if (vehiculoBuscado == null)
						System.out.println("ERROR: No existe un turismo con dicha matrícula\n");
                                        
                                        if (clienteBuscado == null || vehiculoBuscado == null)
                                            System.out.println("ERROR: No existe este alquiler");
                                        
                                        else{
					try {
                                                misAlquileres.cerrar(clienteBuscado,vehiculoBuscado);
						System.out.println("Alquiler dado de baja");
					} catch (ExcepcionAlquilerVehiculos e) {
						System.out.printf("ERROR: %s%n%n", e.getMessage());
					}
                                        }
					break;
				
				case 9:
					System.out.println("\nListado de trabajos");
					System.out.println("---------------------");
					for (Alquiler alquiler: misAlquileres.getAlquileres()) {
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
