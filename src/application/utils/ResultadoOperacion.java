package application.utils;

/**
 *
 * @author Jesus Esquivel
 */

//ESTA CLASE SOLO ES PARA MANEJAR LOS RESULTADOS DE LAS OPERACIONES
public class ResultadoOperacion {
    //Atributos de la clase
    private boolean exito;
    private String mensaje;
    
    //Constructor parametrizado
    public ResultadoOperacion(boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }
    
    //GETTERS y SETTERS

    public boolean isExito() {
        return exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
