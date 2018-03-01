
package mvc.vista;

import mvc.dominio.Cliente;
import modelo.dao.Clientes;
import modelo.dao.Alquileres;
import mvc.dominio.Alquiler;
import mvc.dominio.ExcepcionAlquilerVehiculos;
import mvc.dominio.Turismo;
import modelo.dao.Turismos;
import mvc.dominio.DireccionPostal;
import static mvc.vista.Consola.elegirOpcion;
import static mvc.vista.Consola.leerCliente;
import static mvc.vista.Consola.leerDni;
import static mvc.vista.Consola.leerMatricula;
import static mvc.vista.Consola.leerTurismo;
import static mvc.vista.Consola.mostrarCabecera;
import static mvc.vista.Consola.mostrarMenu;
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

int opcionElegida=1;

do{
    

mostrarCabecera("ALQUILERES TUCARRO");
mostrarMenu();
opcionElegida=elegirOpcion();
switch (opcionElegida) {


case 1:

        Cliente nuevoCliente = null;

        try {
            nuevoCliente=leerCliente();
        }
        catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("ERROR: %s%n%n", e.getMessage());
            System.out.println("Vuelve a introducir los datos de forma correcta");
        }

        try {
             misClientes.anadirCliente(nuevoCliente);
        } catch (ExcepcionAlquilerVehiculos e) {
                System.out.printf("ERROR: %s%n%n", e.getMessage());
        }
        break;

case 2:
        System.out.println("\nBorrar cliente");
        System.out.println("********************");
        String dniBorrar = leerDni();
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

        try {
                nuevoTurismo = leerTurismo();
                misTurismos.anadirTurismo(nuevoTurismo);
        } catch (ExcepcionAlquilerVehiculos e) {
                System.out.printf("ERROR: %s%n%n", e.getMessage());
        }
        break;
case 5:
        System.out.println("\nBorrar turismo");
        System.out.println("********************");
        System.out.print("\nIntroduce la matrícula del turismo a borrar: ");
        String matriculaBorrar =leerMatricula();
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



        

  
    
    
} while (opcionElegida != 0);    
    
    
    
}
}
