
package tarea06;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Alquiler {
    
    private Cliente cliente;
    private Turismo turismo;
    private Date fecha;
    private int dias;
    private final SimpleDateFormat FORMATO_FECHA= new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final double PRECIO_DIA = 30.0;
    //Me he dado cuenta tarde de que la funcion getTime devuelve el resultado en milisegundos
    private final int MILISEGUNDOS_DIA = 1000 * 60 * 60 * 24;
    
    
    //Constructor que coge como parametros dos objetos
    
    public Alquiler(Cliente cliente, Turismo turismo) {
        
        this.cliente= cliente;
	this.turismo = turismo;
	fecha = new Date();
	dias = 0;
        turismo.disponible=false;
        
	}
    
        public Cliente getCliente(){
            return cliente;
        }
    
        public Turismo getTurismo() {
            return turismo;
	}
	
	public Date getFecha() {
            return fecha;
	}
	
	public int getDias() {
            return dias;
	}
        
        //Metodo que calcula la diferencia de dias entre la fecha de devolucion del coche y la que se inicio el alquiler
        //  el cual hace una llamada a difDias para calcular el numero de dias(se cobra un minimo de un dia)
        public void close() {
            Date ahora = new Date();
            dias = difDias(ahora, fecha);
            turismo.disponible=true;
	}
        
        private int difDias(Date fechaFin, Date fechaInicio) {
            long milisegundos = fechaFin.getTime() - fechaInicio.getTime();
            long dias = milisegundos / MILISEGUNDOS_DIA;
            return (int) dias + 1;    
         	
	}
        //Calculo del precio
        
        public double getPrecio(){ 
            return dias*PRECIO_DIA+turismo.cilindrada/100;
        }
	
        public String toString() {
		return String.format("Cliente: %s, Fecha entrada: %s, Días: %d Precio: %.2f Finalizado: %b%n\tVehiculo: %s", 
				cliente,FORMATO_FECHA.format(fecha), dias, getPrecio(), turismo.disponible, turismo);
	}
    
    
    
}
