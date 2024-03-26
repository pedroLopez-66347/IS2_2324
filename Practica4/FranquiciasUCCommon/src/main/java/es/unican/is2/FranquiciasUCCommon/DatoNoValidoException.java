package es.unican.is2.FranquiciasUCCommon;

public class DatoNoValidoException extends RuntimeException{

    public DatoNoValidoException(String mensaje){
        super(mensaje);
    }
}
