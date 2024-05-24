package application.controllers;

import application.clasess.Plato;
import application.clasess.Promocion;
import application.clasess.Restaurante;
import application.tablesModel.PromocionTableModel;
import application.utils.ResultadoOperacion;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import raven.application.form.other.IndexPromociones;
import raven.toast.Notifications;

/**
 *
 * @author Jesus Esquivel
 */
public class PromocionController {
    //Método para retornar el modelo de la tabla
    public PromocionTableModel obtenerPromociones(){
        List<Promocion> promociones = Restaurante.getPromociones();

        if (promociones.size() <= 0) {
            IndexPromociones.lblErrorMsg.setVisible(true);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay promociones actualmente");
        } else {
            IndexPromociones.lblErrorMsg.setVisible(false);
        }
        return new PromocionTableModel(promociones);
    }
    
    //Método para retornar el modelo de la tabla pero cuando se busca
    public PromocionTableModel buscarPromociones(String query){
        List<Promocion> promociones = Restaurante.buscarPromociones(query);

        if (promociones.size() <= 0) {
            IndexPromociones.lblErrorMsg.setVisible(true);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay promociones actualmente");
        } else {
            IndexPromociones.lblErrorMsg.setVisible(false);
        }
        return new PromocionTableModel(promociones);
    }
    
    //Método para obtener el combobox de los tipos de plato
    public DefaultComboBoxModel obtenerPlatos() {
        List<Plato> platos = Restaurante.obtenerPlatosExistentes();

        if (platos.size() <= 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay platos añadidos");
        }
        
        DefaultComboBoxModel<Plato> model = new DefaultComboBoxModel();
        model.addElement(new Plato(-1, "Seleccionar Plato"));
        for (Plato plato : platos) {
            model.addElement(plato);
        }

        return model;
    }
    
    /**
     * Método para añadir una promocion
     * @param nombre
     * @param descripcion
     * @param plato representa el plato al que se le aplicaría el descuento
     * @param descuento representa el porcentaje de descuento a aplicar
     * @param platoMn representa los platos minimos de ese plato para aplicar el descuento
     * @return ResultadoOperacion
     */
    public ResultadoOperacion addPromo(String nombre, String descripcion, Plato plato, int descuento, int platoMn){
        return Restaurante.agregarPromocion(nombre, descripcion, plato, descuento, platoMn);
    }
    
    /**
     * Método para eliminar una promoción
     * @param idPromocion
     * @return ResultadoOperacion
     */
    public ResultadoOperacion delPromo(int idPromocion){
        return Restaurante.eliminarPromocion(idPromocion);
    }
    
    /**
     * Método para añadir una promocion
     * @param nombre
     * @param descripcion
     * @param plato representa el plato al que se le aplicaría el descuento
     * @param descuento representa el porcentaje de descuento a aplicar
     * @param platoMn representa los platos minimos de ese plato para aplicar el descuento
     * @return ResultadoOperacion
     */
    public ResultadoOperacion modPromo(int id,String nombre, String descripcion, Plato plato, int descuento, int platoMn){
        return Restaurante.modificarPromocion(id,nombre, descripcion, plato, descuento, platoMn);
    }
}
