package application.clasess;

import application.enums.EstadoPedido;
import application.utils.ResultadoOperacion;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Jesus Esquivel
 */
public class Pedido {

    //Atributos de clase
    private int idPedido;
    private String cliente;
    private LocalDate fechaPedido;
    private double total;
    private int tiempoEntregaEstimado; //Lo puse como entero pues solo tomare los minuto
    private ArrayList<HashMap<Plato, Integer>> platos;
    private EstadoPedido estadoPedido;

    //Constructor parametrizado
    public Pedido(int idPedido, String cliente, LocalDate fechaPedido, double total, int tiempoEntregaEstimado, ArrayList<HashMap<Plato, Integer>> platos, EstadoPedido estadoPedido) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.tiempoEntregaEstimado = tiempoEntregaEstimado;
        this.platos = platos;
        this.estadoPedido = estadoPedido;
    }

    //Constructor vacio
    public Pedido() {
        this.estadoPedido = EstadoPedido.CREANDOSE;
        this.platos = new ArrayList<>();
    }

    //GETTERS y SETTERS
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getTiempoEntregaEstimado() {
        return tiempoEntregaEstimado;
    }

    public void setTiempoEntregaEstimado(int tiempoEntregaEstimado) {
        this.tiempoEntregaEstimado = tiempoEntregaEstimado;
    }

    public ArrayList<HashMap<Plato, Integer>> getPlatos() {
        return platos;
    }

    public void setPlatos(ArrayList<HashMap<Plato, Integer>> platos) {
        this.platos = platos;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    /**
     * Métodos para saber si dos objetos de Pedido son iguales
     *
     * @param obj que representa el objeto a ser evaluado
     * @return {boolean} que representa si el objeto es igual o no es igual
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.cliente);
        hash = 89 * hash + Objects.hashCode(this.fechaPedido);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        hash = 89 * hash + this.tiempoEntregaEstimado;
        hash = 89 * hash + Objects.hashCode(this.platos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (Double.doubleToLongBits(this.total) != Double.doubleToLongBits(other.total)) {
            return false;
        }
        if (this.tiempoEntregaEstimado != other.tiempoEntregaEstimado) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.fechaPedido, other.fechaPedido)) {
            return false;
        }
        return Objects.equals(this.platos, other.platos);
    }

    /**
     * Método para agregar un plato al pedido
     *
     * @param pedido Representa el plato a agregar
     * @param cantidad Representa la cantidad del plato a agregar
     * @return objeto de tipo {ResultadoOperacion}
     */
    public ResultadoOperacion agregarPlato(Plato pedido, int cantidad) {
        try {
            //Validamos que el plato no este siendo otra vez agregado
            for (HashMap<Plato, Integer> plato : this.platos) {
                if (plato.containsKey(pedido)) {//Si esta siendo agregado de nuevo
                    //Actualizamos la cantidad del mismo en el pedido
                    int cantidadActual = plato.get(pedido);
                    plato.put(pedido, cantidadActual + cantidad);

                    //Actualizamos el total del pedido
                    HashMap<String, Double> promo = Restaurante.consultarDescuento(pedido, cantidad);

                    double promoResta = 0.0;
                    for (Map.Entry<String, Double> entry : promo.entrySet()) {
                        promoResta = entry.getValue();
                    }

                    System.out.println("El descuento a aplicar es de $" + promoResta);

                    //Actualizamos el total del pedido
                    this.total += ((pedido.getPrecio() * (cantidad+cantidadActual)) + promoResta);
                    this.tiempoEntregaEstimado += (pedido.getTiempoEstimadoPreparacionMn()*(cantidadActual + cantidad));

                    //this.total += pedido.getPrecio() * cantidad;
                    return new ResultadoOperacion(true, "Cantidad del plato incrementada exitosamente");
                }
            }

            //Como no lo esta lo añadimos
            HashMap<Plato, Integer> nuevoPlato = new HashMap<>();
            nuevoPlato.put(pedido, cantidad);
            this.platos.add(nuevoPlato);

            //Obtenemos la promoción
            HashMap<String, Double> promo = Restaurante.consultarDescuento(pedido, cantidad);

            double promoResta = 0.0;
            for (Map.Entry<String, Double> entry : promo.entrySet()) {
                promoResta = entry.getValue();
            }

            System.out.println("El descuento a aplicar es de $" + promoResta);

            //Actualizamos el total del pedido
            this.total += ((pedido.getPrecio() * cantidad) + promoResta);
            this.tiempoEntregaEstimado += pedido.getTiempoEstimadoPreparacionMn();

            return new ResultadoOperacion(true, "Plato agregado exitosamente");
        } catch (Exception e) {
            return new ResultadoOperacion(false, "No se pudo agregar el plato al pedido " + e);
        }
    }

    public ResultadoOperacion borrarPlato(Plato platoABorrar, int cantidad) {
        try {
            for (HashMap<Plato, Integer> plato : this.platos) {
                if (plato.containsKey(platoABorrar) && (this.estadoPedido != EstadoPedido.PREPARADO)) {
                    //Restamos el monto de ese plato
                    //Actualizamos la cantidad del mismo en el pedido
                    int cantidadActual = plato.get(platoABorrar);

                    //Obtenemos la promoción
                    HashMap<String, Double> promo = Restaurante.consultarDescuento(platoABorrar, cantidad);

                    double promoResta = 0.0;
                    for (Map.Entry<String, Double> entry : promo.entrySet()) {
                        promoResta = entry.getValue();
                    }

                    this.total -= ((platoABorrar.getPrecio() * cantidadActual) + promoResta);
                    System.out.println(platoABorrar.getTiempoEstimadoPreparacionMn());
                    this.tiempoEntregaEstimado -= (platoABorrar.getTiempoEstimadoPreparacionMn()*cantidadActual);
                    this.platos.remove(plato);
                    return new ResultadoOperacion(true, "Plato eliminado exitosamente.");
                } else {
                    return new ResultadoOperacion(false, "Si el pedido ya se realizo no se puede eliminar el plato");
                }
            }

            return new ResultadoOperacion(false, "No se encontro el plato en la lista para poder eliminar");
        } catch (Exception e) {
            return new ResultadoOperacion(false, "Plato no se borro.");
        }
    }

    //Método para facturar (Cambiar el estado del pedido)
    public ResultadoOperacion facturar() {
        this.setEstadoPedido(EstadoPedido.PAGADO);

        return new ResultadoOperacion(true, "Se facturo el pedido");
    }

    //Método para obtener la fecha en formato correto
    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.fechaPedido.format(formatter);
    }
}
