package application.controllers;

import application.clasess.Restaurante;
import application.clasess.TipoPlato;
import application.tablesModel.TipoPlatoTableModel;
import application.utils.ResultadoOperacion;
import java.util.List;
import raven.application.form.other.IndexTiposPlato;
import raven.toast.Notifications;

/**
 *
 * @author Jesus Esquivel
 */
public class TipoPlatoController {

    //Creamos metodo que devuelva un modelo de la tabla
    public TipoPlatoTableModel obtenerTiposPlato() {
        List<TipoPlato> tiposPlato = Restaurante.obtenerTiposPlatoDisponibles();

        if (tiposPlato.size() <= 0) {
            IndexTiposPlato.lblErrorMsg.setVisible(true);
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay tipos de platos creados");
        } else {
            System.out.println(tiposPlato.get(0).isDisponibilidad());
            IndexTiposPlato.lblErrorMsg.setVisible(false);
        }

        return new TipoPlatoTableModel(tiposPlato);
    }

    //Creamos método para añadir un tipo de plato
    public ResultadoOperacion addTipo(String nombre) {
        return Restaurante.agregarTipoPlato(nombre);
    }

    //Método para buscar los datos de una tabla dependiendo de la query
    public TipoPlatoTableModel buscarTipos(String query) {
        List<TipoPlato> tiposPlato = Restaurante.buscarTiposPlato(query);

        if (tiposPlato.size() <= 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "No hay coincidencias");
        }

        return new TipoPlatoTableModel(tiposPlato);
    }

    //Método para eliminar un tipo de plato
    public ResultadoOperacion delTipo(int id) {
        return Restaurante.eliminarTipoPlato(id);
    }

    //Método para buscar un tipo de plato
    public ResultadoOperacion modTipo(int id, String nombre) {
        return Restaurante.modificarTipoPlato(id, nombre);
    }
}
