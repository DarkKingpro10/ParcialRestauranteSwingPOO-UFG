package application.clasess;

import application.enums.EstadoPedido;
import application.utils.ResultadoOperacion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Jesus Esquivel
 */
public class Restaurante {

    //Atributos de clase
    private static List<Producto> inventario = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<Pedido>();
    private static List<Plato> platos = new ArrayList<>();
    private static List<Promocion> promociones = new ArrayList<>();
    private static List<TipoPlato> tiposPlato = new ArrayList<>();

    //GETTERS Y SETTERS
    public static List<Producto> getInventario() {
        return inventario;
    }

    public static void setInventario(List<Producto> inventario) {
        Restaurante.inventario = inventario;
    }

    public static List<Pedido> getPedidos() {
        return pedidos;
    }

    public static void setPedidos(List<Pedido> pedidos) {
        Restaurante.pedidos = pedidos;
    }

    public static List<Plato> getPlatos() {
        return platos;
    }

    public static void setPlatos(List<Plato> platos) {
        Restaurante.platos = platos;
    }

    public static List<Promocion> getPromociones() {
        return promociones;
    }

    public static void setPromociones(List<Promocion> promociones) {
        Restaurante.promociones = promociones;
    }

    public static List<TipoPlato> getTiposPlato() {
        return tiposPlato;
    }

    public static void setTiposPlato(List<TipoPlato> tiposPlato) {
        Restaurante.tiposPlato = tiposPlato;
    }

    //Métodos de las clases
    //Métodos para productos
    /**
     * Método para añadir un producto al inventario
     *
     * @param nombreProducto representa el nombre del producto a añadir
     * @param cantidadProducto representa la cantidad del producto a añadir
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion agregarProducto(String nombreProducto, int cantidadProducto) {
        //Validamos que el nombre del producto no exista
        try {
            int id = inventario.size() + 1;
            nombreProducto = nombreProducto.trim();

            if (nombreProducto.isEmpty()) {
                return new ResultadoOperacion(false, "El nombre del producto no puede estar vacio");
            } else if (cantidadProducto < 0) {
                return new ResultadoOperacion(false, "La cantidad no puede ser menor a cero");
            }

            Producto productoNuevo = new Producto(id, nombreProducto, cantidadProducto);

            //Validamos que el nombre del producto no este ya dentro
            for (Producto producto : inventario) {
                if (producto.fueBorrado()) {
                    continue;
                }

                if (producto.getNombreProducto().equals(nombreProducto)) {
                    return new ResultadoOperacion(false, "¡El producto ya se encuentra registrado, si desea añadir más cantidad de él modifiqulo!");
                }
            }

            //Ya que el producto no se ha registrado, entonces lo añadiremos al arreglo
            inventario.add(productoNuevo);
            return new ResultadoOperacion(true, "¡Se agrego el producto con exito!");
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al agregar producto!");
        }
    }

    /**
     * Método para actualizar un producto
     *
     * @param id representa el id y la posición más 1 en la lista
     * @param nombre representa el nombre del producto a modificar
     * @param cantidad representa la cantidad del producto a añadir
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion modificarProducto(int id, String nombre, int cantidad) {
        try {
            Producto productoAModificar = inventario.get(id - 1);

            //Validando si no ha sido eliminado
            if (productoAModificar.getCantidadProducto() == -1) {
                return new ResultadoOperacion(false, "¡El producto a modificar no existe!");
            }

            //Como el producto existe entonces modificaremos el producto
            //Validamos que él nombre del producto no exista
            nombre = nombre.trim();

            if (nombre.isEmpty()) {
                return new ResultadoOperacion(false, "El nombre del producto no puede estar vacio");
            } else if (cantidad <= 0) {
                return new ResultadoOperacion(false, "La cantidad nueva a añadir no puede ser menor o igual a 0");
            }

            //Validamos que el nombre del producto no este ya dentro
            for (Producto producto : inventario) {
                if (producto.fueBorrado()) {
                    continue;
                }

                if (producto.getNombreProducto().equals(nombre) && producto.getIdProducto() != id) {
                    return new ResultadoOperacion(false, "¡El producto ya se encuentra registrado, si desea añadir más cantidad de él modifiqulo!");
                }
            }

            productoAModificar.setNombreProducto(nombre);
            int cantidadActual = productoAModificar.getCantidadProducto();
            productoAModificar.setCantidadProducto(cantidad + cantidadActual);

            return new ResultadoOperacion(true, "¡El producto ha sido modificado con exito!");
        } catch (IndexOutOfBoundsException e) {
            return new ResultadoOperacion(false, """
                                                  ¡Error al actualizar el producto!
                                                  El id del producto no ha sido encontrado
                                                  """);
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al actualizar el producto!");
        }
    }

    /**
     * Producto a eliminar
     *
     * @param id representa el producto a eliminar
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion eliminarProducto(int id) {
        try {
            Producto productoAEliminar = inventario.get(id - 1);

            //Validando si no ha sido eliminado
            if (productoAEliminar.getCantidadProducto() == -1) {
                return new ResultadoOperacion(false, "¡El producto a modificar no existe!");
            }

            //Como el producto existe entonces eliminaremos el producto
            productoAEliminar.setCantidadProducto(-1);
            return new ResultadoOperacion(true, "¡El producto ha sido eliminado con éxito!");
        } catch (IndexOutOfBoundsException e) {
            return new ResultadoOperacion(false, """
                                                  ¡Error al eliminar!
                                                  El id del producto no ha sido encontrado
                                                  """);
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al eliminar el producto!");
        }
    }

    /**
     * Método para buscar un producto
     *
     * @param nombre representa el nombre del producto a buscar
     * @return objeto de tipo Producto
     */
    public static List<Producto> buscarProductos(String nombre) {
        //Recorremos la lista para buscar
        nombre = nombre.trim().toLowerCase();
        List<Producto> coincidencias = new ArrayList<>();
        for (Producto producto : inventario) {
            if (producto.getNombreProducto().toLowerCase().contains(nombre) && !producto.fueBorrado()) {
                coincidencias.add(producto);
            }
        }
        return coincidencias;
    }

    //Método para obtener los productos existentes (No se han eliminado)
    public static List<Producto> obtenerProductosExistentes() {
        return inventario.stream()
                .filter(producto -> producto.getCantidadProducto() != -1)
                .collect(Collectors.toList());
    }

    //Método para buscar un producto y retornarlo
    public static Producto buscarProducto(String nombre) {
        nombre = nombre.trim().toLowerCase();
        for (Producto producto : inventario) {
            if (producto.getNombreProducto().toLowerCase().equals(nombre)) {
                return producto;
            }
        }

        return null;
    }

    //Método para la clase Promociones
    //Métodos de las clases
    //Métodos para productos
    /**
     * Método para añadir una promocion
     *
     * @param nombre representa el nombre de la promoción a añadir
     * @param descripcion representa la descripcion de la promocion
     * @param plato representa el plato a añadir a la promocion
     * @param porcentajeDescuento representa el porcentaje de descuento de la
     * promocion
     * @param platosMinimos representa los platos minimos para que la promocion
     * sea valida
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion agregarPromocion(String nombre, String descripcion, Plato plato, int porcentajeDescuento, int platosMinimos) {
        //Validamos que el nombre del producto no exista
        try {
            int id = promociones.size() + 1;
            nombre = nombre.trim();
            descripcion = descripcion.trim();

            if (plato == null || !plato.isDisponibilidad()) {
                return new ResultadoOperacion(false, "Plato inexistente");
            }

            if (nombre.isEmpty()) {
                return new ResultadoOperacion(false, "El nombre de la promoción no puede estar vacio");
            }
            if (porcentajeDescuento <= 0) {
                return new ResultadoOperacion(false, "Porcentaje de descuento invalido debe ser mayor o igual a 1");
            }
            if (platosMinimos <= 0) {
                return new ResultadoOperacion(false, "Platos minimos invalido, debe ser mayor o igual a 1");
            }

            Promocion promocionNueva = new Promocion(id, nombre, descripcion, plato, porcentajeDescuento, platosMinimos);

            //Validamos que él producto no exista
            if (inventario.contains(promocionNueva)) {
                return new ResultadoOperacion(false, "¡La promoción ya se encuentra registrada!");
            }

            //Ya que el producto no se ha registrado, entonces lo añadiremos al arreglo
            promociones.add(promocionNueva);
            return new ResultadoOperacion(true, "¡Se agrego la promoción con exito!");
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al agregar la promocióno!");
        }
    }

    /**
     * Método para actualizar una promocion
     *
     * @param id representa la posicion en la lista más 1
     * @param nombre representa el nombre de la promoción a modificar
     * @param descripcion representa la descripcion de la promocion
     * @param plato representa el plato a modificar a la promocion
     * @param porcentajeDescuento representa el porcentaje de descuento de la
     * promocion
     * @param platosMinimos representa los platos minimos para que la promocion
     * sea valida
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion modificarPromocion(int id, String nombre, String descripcion, Plato plato, int porcentajeDescuento, int platosMinimos) {
        try {
            Promocion promocionAModificar = promociones.get(id - 1);

            //Como la promocion existe entonces modificaremos
            //Validamos que la promocion no exista
            nombre = nombre.trim();
            descripcion = descripcion.trim();

            if (nombre.isEmpty()) {
                return new ResultadoOperacion(false, "El nombre de la promoción no puede estar vacio");
            }

            if (plato == null || !plato.isDisponibilidad()) {
                return new ResultadoOperacion(false, "Plato inexistente");
            }
            if (porcentajeDescuento <= 0) {
                return new ResultadoOperacion(false, "Porcentaje de descuento invalido debe ser mayor o igual a 1");
            }
            if (platosMinimos <= 0) {
                return new ResultadoOperacion(false, "Platos minimos invalido, debe ser mayor o igual a 1");
            }

            Promocion promocionPrueba = new Promocion(id, nombre, descripcion, plato, porcentajeDescuento, platosMinimos);

            //Validamos que él producto no exista
            if (inventario.contains(promocionPrueba)) {
                return new ResultadoOperacion(false, "¡La promoción ya se encuentra registrada!");
            }

            promocionAModificar.setNombrePromocion(nombre);
            promocionAModificar.setDescripcion(descripcion);
            promocionAModificar.setPlato(plato);
            promocionAModificar.setPorcentajeDescuento(porcentajeDescuento);
            promocionAModificar.setPlatosMinimos(platosMinimos);

            return new ResultadoOperacion(true, "¡La promocion ha sido modificada con exito!");
        } catch (IndexOutOfBoundsException e) {
            return new ResultadoOperacion(false, """
                                                  ¡Error al actualizar la promoción!
                                                  El id de la promoción no ha sido encontrado
                                                  """);
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al actualizar la promoción!");
        }
    }

    /**
     * Promocion a eliminar
     *
     * @param id representa la promoción a eliminar
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion eliminarPromocion(int id) {
        try {
            promociones.remove(id - 1);
            return new ResultadoOperacion(true, "¡La promoción ha sido eliminada con éxito!");
        } catch (IndexOutOfBoundsException e) {
            return new ResultadoOperacion(false, """
                                                  ¡Error al eliminar!
                                                  El id del producto no ha sido encontrado
                                                  """);
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al eliminar la promocion!");
        }
    }

    /**
     * Método para buscar una promocion
     *
     * @param query representa la coincidencia a buscar
     * @return objeto de tipo Producto
     */
    public static List<Promocion> buscarPromociones(String query) {
        //Recorremos la lista para buscar
        query = query.trim().toLowerCase();
        List<Promocion> coincidencias = new ArrayList<>();

        for (Promocion promocion : promociones) {
            if (promocion.getNombrePromocion().toLowerCase().contains(query)) {
                coincidencias.add(promocion);
            } else if (promocion.getDescripcion().toLowerCase().contains(query)) {
                coincidencias.add(promocion);
            } else if (promocion.getPlato().getNombrePlato().toLowerCase().contains(query)) {
                coincidencias.add(promocion);
            }
        }

        return coincidencias;
    }

    public static Promocion buscarPromocion(String query) {
        query = query.trim().toLowerCase();
        for (Promocion promocion : promociones) {
            if (promocion.getNombrePromocion().toLowerCase().equals(query)) {
                return promocion;
            }
        }

        return null;
    }

    //Métodos de Tipo Plato
    /**
     * Método para añadir un tipo de plato
     *
     * @param nombre representa el nombre del tipo de plato a añadir
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion agregarTipoPlato(String nombre) {
        //Validamos que el nombre del producto no exista
        try {
            int id = tiposPlato.size() + 1;

            nombre = nombre.trim();

            if (nombre.isEmpty()) {
                return new ResultadoOperacion(false, "El nombre del tipo de plato no puede estar vacio");
            }

            TipoPlato tipoNuevo = new TipoPlato(id, nombre, true);

            //Validamos que no exista
            if (tiposPlato.contains(tipoNuevo)) {
                return new ResultadoOperacion(false, "¡El tipo de plato ya se encuentra registrado!");
            }

            //Ya que el producto no se ha registrado, entonces lo añadiremos al arreglo
            tiposPlato.add(tipoNuevo);
            return new ResultadoOperacion(true, "¡Se agrego el tipo de plato con exito!");
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al agregar el tipo de plato!");
        }
    }

    /**
     * Método para actualizar un tipo de plato
     *
     * @param id representa la posicion en la lista más 1
     * @param nombre representa el nombre el tipo de plato a modificar
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion modificarTipoPlato(int id, String nombre) {
        try {
            TipoPlato tipoAModificar = tiposPlato.get(id - 1);

            //Como existe entonces modificaremos
            //Validamos que no exista
            nombre = nombre.trim();
            if (nombre.isEmpty()) {
                return new ResultadoOperacion(false, "El nombre del tipo de plato no puede estar vacio");
            }

            TipoPlato tipoPrueba = new TipoPlato(id, nombre, true);

            //Validamos que no exista
            if (tiposPlato.contains(tipoPrueba)) {
                return new ResultadoOperacion(false, "¡El tipo de plato ya se encuentra registrado!");
            }

            tipoAModificar.setNombreTipoPlato(nombre);

            return new ResultadoOperacion(true, "¡El tipo de plato ha sido modificado con exito!");
        } catch (IndexOutOfBoundsException e) {
            return new ResultadoOperacion(false, """
                                                  ¡Error al actualizar el tipo de plato!
                                                  El id no ha sido encontrado
                                                  """);
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al actualizar el tipo de plato!");
        }
    }

    /**
     * Tipo de plato a eliminar
     *
     * @param id representa el elemento a eliminar
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion eliminarTipoPlato(int id) {
        try {
            tiposPlato.get(id - 1).setDisponibilidad(false);
            return new ResultadoOperacion(true, "¡La promoción ha sido eliminada con éxito!");
        } catch (IndexOutOfBoundsException e) {
            return new ResultadoOperacion(false, """
                                                  ¡Error al eliminar!
                                                  El id del producto no ha sido encontrado
                                                  """);
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al eliminar la promocion!");
        }
    }

    /**
     * Método para buscar un tipo de plato
     *
     * @param query representa la coincidencia a buscar
     * @return objeto de tipo Producto
     */
    public static List<TipoPlato> buscarTiposPlato(String query) {
        //Recorremos la lista para buscar
        query = query.trim().toLowerCase();
        List<TipoPlato> coincidencias = new ArrayList<>();

        for (TipoPlato tipo : tiposPlato) {
            if (tipo.getNombreTipoPlato().toLowerCase().contains(query) && tipo.isDisponibilidad()) {
                coincidencias.add(tipo);
            }
        }

        return coincidencias;
    }

    //Método para buscar un tipo de plato
    public static TipoPlato buscarTipoPlato(String query) {
        //Recorremos la lista para buscar
        query = query.trim().toLowerCase();

        for (TipoPlato tipo : tiposPlato) {
            if (tipo.getNombreTipoPlato().toLowerCase().equals(query)) {
                return tipo;
            }
        }

        return null;
    }

    public static List<TipoPlato> obtenerTiposPlatoDisponibles() {
        List<TipoPlato> coincidencias = new ArrayList<>();

        for (TipoPlato tipo : tiposPlato) {
            if (tipo.isDisponibilidad()) {
                coincidencias.add(tipo);
            }
        }

        return coincidencias;
    }

    //Método para plato
    /**
     * *
     * Método para añadir un plato
     *
     * @param plato representa el plato a añadir
     * @return
     */
    public static ResultadoOperacion agregarPlato(Plato plato) {
        //Validamos que el nombre del producto no exista
        try {
            int id = platos.size() + 1;

            if (plato.getNombrePlato().trim().isEmpty()) {
                return new ResultadoOperacion(false, "El nombre del plato no puede estar vacio");
            } else if (plato.getDescripcion().trim().isEmpty()) {
                return new ResultadoOperacion(false, "La descripción del plato no puede estar vacia");
            } else if (plato.getTiempoEstimadoPreparacionMn() <= 0) {
                return new ResultadoOperacion(false, "El tiempo de relización estimado en minutos debe ser mayor a 0");
            } else if (plato.getIngredientes().size() <= 0) {
                return new ResultadoOperacion(false, "Un plato necesita los productos que contendrá");
            }

            //Validamos que no exista
            if (platos.contains(plato)) {
                return new ResultadoOperacion(false, "¡El tipo de plato ya se encuentra registrado!");
            }

            //Ya que el producto no se ha registrado, entonces lo añadiremos al arreglo
            plato.setIdPlato(id);
            plato.setDisponibilidad(true);
            platos.add(plato);
            return new ResultadoOperacion(true, "¡Se agrego el plato con exito!");
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al agregar el plato!");
        }
    }

    /**
     * Método para actualizar un plato
     *
     * @param id representa la posicion en la lista más 1
     * @param plato representa el nombre el plato a modificar
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion modificarPlato(int id, Plato plato) {
        try {
            if (plato.getNombrePlato().trim().isEmpty()) {
                return new ResultadoOperacion(false, "El nombre del plato no puede estar vacio");
            } else if (plato.getDescripcion().trim().isEmpty()) {
                return new ResultadoOperacion(false, "La descripción del plato no puede estar vacia");
            } else if (plato.getTiempoEstimadoPreparacionMn() <= 0) {
                return new ResultadoOperacion(false, "El tiempo de relización estimado en minutos debe ser mayor a 0");
            } else if (plato.getIngredientes().size() <= 0) {
                return new ResultadoOperacion(false, "Un plato necesita los productos que contendrá");
            }

            //Validamos que no exista
            if (platos.contains(plato)) {
                return new ResultadoOperacion(false, "¡El tipo de plato ya se encuentra registrado!");
            }

            //Ya que el producto no se ha registrado, entonces lo añadiremos al arreglo
            Plato platoAModificar = platos.get(id);

            platoAModificar.setDescripcion(plato.getDescripcion());
            platoAModificar.setIngredientes(plato.getIngredientes());
            platoAModificar.setNombrePlato(plato.getNombrePlato());
            platoAModificar.setPrecio(plato.getPrecio());
            platoAModificar.setTiempoEstimadoPreparacionMn(plato.getTiempoEstimadoPreparacionMn());
            platoAModificar.setTipoPlato(plato.getTipoPlato());

            return new ResultadoOperacion(true, "¡El plato ha sido modificada con exito!");
        } catch (IndexOutOfBoundsException e) {
            return new ResultadoOperacion(false, """
                                                  ¡Error al actualizar el plato!
                                                  El id no ha sido encontrado
                                                  """);
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al actualizar el plato!");
        }
    }

    /**
     * Plato a eliminar
     *
     * @param id representa el elemento a eliminar
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion eliminarPlato(int id) {
        try {
            tiposPlato.remove(id - 1);
            return new ResultadoOperacion(true, "¡El plato ha sido eliminado con éxito!");
        } catch (IndexOutOfBoundsException e) {
            return new ResultadoOperacion(false, """
                                                  ¡Error al eliminar!
                                                  El id del plato no ha sido encontrado
                                                  """);
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al eliminar el plato!");
        }
    }

    /**
     * Método para buscar un plato
     *
     * @param query representa la coincidencia a buscar
     * @return objeto de tipo Plato
     */
    public static List<Plato> buscarPlatos(String query) {
        //Recorremos la lista para buscar
        query = query.trim().toLowerCase();
        List<Plato> coincidencias = new ArrayList<>();

        for (Plato plato : platos) {
            if (plato.getNombrePlato().toLowerCase().contains(query)) {
                coincidencias.add(plato);
            }
        }

        return coincidencias;
    }

    //Método para buscar un plato
    public static Plato buscarPlato(String query) {
        //Recorremos la lista para buscar
        query = query.trim().toLowerCase();
        for (Plato plato : platos) {
            if (plato.getNombrePlato().toLowerCase().equals(query)) {
                return plato;
            }
        }

        return null;
    }

    public static List<Plato> obtenerPlatosDisponibles() {
        return platos.stream()
                .filter(plato -> (plato.disponibilidadIngredientes() == true && plato.isDisponibilidad() == true))
                .collect(Collectors.toList());
    }

    //Métodos para Pedidos
    /**
     * *
     * Método para añadir un pedido
     *
     * @param pedido representa el plato a añadir
     * @return
     */
    public static ResultadoOperacion agregarPedido(Pedido pedido) {
        //Validamos que el nombre del producto no exista
        try {
            int id = pedidos.size() + 1;

            if (pedido.getCliente().trim().isEmpty()) {
                return new ResultadoOperacion(false, "El nombre cliente no puede estar vacio");
            } else if (pedido.getTotal() <= 0) {
                return new ResultadoOperacion(false, "El total del pedido no esta correcto");
            } else if (pedido.getTiempoEntregaEstimado() <= 0) {
                return new ResultadoOperacion(false, "El tiempo de relización estimado en minutos debe ser mayor a 0");
            } else if (pedido.getPlatos().size() <= 0) {
                return new ResultadoOperacion(false, "Un pedido no puede conetener 0 platos");
            }

            //Ya que el producto no se ha registrado, entonces lo añadiremos al arreglo
            pedido.setIdPedido(id);
            pedido.setEstadoPedido(EstadoPedido.CREANDOSE);
            pedido.setFechaPedido(LocalDate.now());
            pedidos.add(pedido);
            return new ResultadoOperacion(true, "¡Se agrego el pedido con exito!");
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al agregar el pedido!");
        }
    }

    /**
     * Método para actualizar un pedido
     *
     * @param id representa la posicion en la lista más 1
     * @param pedio representa el pedido a modificar
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion modificarPedido(int id, Pedido pedido) {
        try {
            if (pedido.getCliente().trim().isEmpty()) {
                return new ResultadoOperacion(false, "El nombre cliente no puede estar vacio");
            } else if (pedido.getTotal() <= 0) {
                return new ResultadoOperacion(false, "El total del pedido no esta correcto");
            } else if (pedido.getTiempoEntregaEstimado() <= 0) {
                return new ResultadoOperacion(false, "El tiempo de relización estimado en minutos debe ser mayor a 0");
            } else if (pedido.getPlatos().size() <= 0) {
                return new ResultadoOperacion(false, "Un pedido no puede conetener 0 platos");
            }

            //Ya que el producto no se ha registrado, entonces lo añadiremos al arreglo
            Pedido pedidoAModificar = pedidos.get(id);

            pedidoAModificar.setEstadoPedido(pedido.getEstadoPedido());
            pedidoAModificar.setPlatos(pedido.getPlatos());
            pedidoAModificar.setTiempoEntregaEstimado(pedido.getTiempoEntregaEstimado());
            pedidoAModificar.setTotal(pedido.getTotal());

            return new ResultadoOperacion(true, "¡El pedido ha sido modificado con exito!");
        } catch (IndexOutOfBoundsException e) {
            return new ResultadoOperacion(false, """
                                                  ¡Error al actualizar el pedido!
                                                  El id no ha sido encontrado
                                                  """);
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al actualizar el pedido!");
        }
    }

    /**
     * Pedido a eliminar
     *
     * @param id representa el elemento a eliminar
     * @return objeto de tipo {ResultadoOperacion}
     */
    public static ResultadoOperacion eliminarPedido(int id) {
        try {
            pedidos.remove(id - 1);
            return new ResultadoOperacion(true, "¡El pedido ha sido eliminado con éxito!");
        } catch (IndexOutOfBoundsException e) {
            return new ResultadoOperacion(false, """
                                                  ¡Error al eliminar!
                                                  El id del pedido no ha sido encontrado
                                                  """);
        } catch (Exception e) {
            return new ResultadoOperacion(false, "¡Error al eliminar el pedido!");
        }
    }

    /**
     * Método para buscar los pedidos que coincidan
     *
     * @param query representa la coincidencia a buscar
     * @return objeto de tipo Plato
     */
    public static List<Pedido> buscarPedidos(String query) {
        //Recorremos la lista para buscar
        query = query.trim().toLowerCase();
        List<Pedido> coincidencias = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            if (pedido.getCliente().toLowerCase().contains(query)) {
                coincidencias.add(pedido);
            } else if (String.valueOf(pedido.getIdPedido()).contains(query)) {
                coincidencias.add(pedido);
            } else if (String.valueOf(pedido.getTiempoEntregaEstimado()).contains(query)) {
                coincidencias.add(pedido);
            } else {
                boolean found = false;
                for (HashMap<Plato, Integer> platoMap : pedido.getPlatos()) {
                    for (Plato plato : platoMap.keySet()) {
                        if (plato.getNombrePlato().toLowerCase().contains(query)) {
                            coincidencias.add(pedido);
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        break;
                    }
                }
            }
        }

        return coincidencias;
    }

    //Método para buscar un pedido
    public static Pedido buscarPedio(int id) {
        try {
            return pedidos.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    //Método para facturar un pedido
    public static ResultadoOperacion facturar(int id) {
        try {
            Pedido pedido = pedidos.get(id);
            pedido.setEstadoPedido(EstadoPedido.PAGADO);
            return new ResultadoOperacion(true, "Se facturo con exito $" + pedido.getTotal() + " del pedido #" + pedido.getIdPedido());
        } catch (Exception e) {
            return new ResultadoOperacion(false, "No se encontro el id del pedido");
        }
    }

    //Método para obtener el descuento de un plato si la cantidad de este corresponde a alguna promoción
    public static double consultarDescuento(Plato plato, int cantidad) {
        for (Promocion promo : promociones) {
            if (promo.getPlato().equals(plato) && promo.getPlatosMinimos() >= cantidad) {
                return (promo.getPlato().getPrecio() * (promo.getPorcentajeDescuento() / 100) * -1);
            }
        }

        return 0;
    }

    //Método para obtener el plato más vendido
    public static Plato obtenerPlatoMasPedido() {
        HashMap<Plato, Integer> conteoPlatos = new HashMap<>();

        for (Pedido pedido : pedidos) {
            for (HashMap<Plato, Integer> platoMap : pedido.getPlatos()) {
                for (HashMap.Entry<Plato, Integer> entry : platoMap.entrySet()) {
                    Plato plato = entry.getKey();
                    int cantidad = entry.getValue();
                    conteoPlatos.put(plato, conteoPlatos.getOrDefault(plato, 0) + cantidad);
                }
            }
        }

        Plato platoMasPedido = null;
        int maxConteo = 0;

        for (HashMap.Entry<Plato, Integer> entry : conteoPlatos.entrySet()) {
            if (entry.getValue() > maxConteo) {
                maxConteo = entry.getValue();
                platoMasPedido = entry.getKey();
            }
        }

        return platoMasPedido;
    }

    //Método para poder consultar las estadisticas del restaurante
    public static int numeroPedidosPagados() {
        return pedidos.stream()
                .reduce(0, (total, pedido) -> pedido.getEstadoPedido() == EstadoPedido.PAGADO ? total + 1 : total, Integer::sum);
    }

    //Métod para obtener el costo total de cada pedido pagado
    public static double totalVentas() {
        return pedidos.stream()
                .reduce(0.0, (total, pedido) -> pedido.getEstadoPedido() == EstadoPedido.PAGADO ? total + pedido.getTotal() : total, Double::sum);
    }
}
