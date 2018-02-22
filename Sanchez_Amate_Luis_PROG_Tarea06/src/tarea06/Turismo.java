
package tarea06;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Turismo {
    
    public String matricula;
    public String marca;
    public String modelo;
    public int cilindrada;
    public boolean disponible;
    
    //Constructor con parametros
    
    
    public Turismo(String matricula, String marca, String modelo, int cilindrada){
        
        if (compruebaMatricula(matricula))
		this.matricula = matricula;
	else
		throw new ExcepcionAlquilerVehiculos("Matrícula no válida");
        
        this.marca=marca;
        this.modelo=modelo;
        
        if (cilindrada>0)
            this.cilindrada=cilindrada;
        else
            throw new ExcepcionAlquilerVehiculos("La cilindrada no puede ser menor o igual a 0");
        
        disponible=true;
        
    }
    
    //Constructor copia
    
    public Turismo(Turismo turismo){
        
        matricula=turismo.getMatricula();
        marca=turismo.getMarca();
        modelo=turismo.getModelo();
        cilindrada=turismo.getCilindrada();
        
    }
   
    //Metodo para comprobar matriculas
    
    private boolean compruebaMatricula(String matricula) {
		Pattern patron = Pattern.compile("[0-9]{4}[B-DF-HJ-NP-TV-Z]{3}");
		Matcher emparejador = patron.matcher(matricula);
		return emparejador.matches();
    }
    
    //Metodos Get y toString
    
    public String getMatricula(){
        return matricula;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public String getModelo(){
        return modelo;
    }
    
    public int getCilindrada(){
        return cilindrada;
    }
                
    public boolean getDisponible(){
        return disponible;
    }
    
    public String toString(){
        return String.format("Matricula: %s Marca: %s Modelo: %s Cilindrada: %s",matricula, marca,modelo,cilindrada);
     }   
}
