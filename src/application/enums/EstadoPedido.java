/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package application.enums;

/**
 *
 * @author Jesus Esquivel
 */
public enum EstadoPedido {
    PREPARADO {
        @Override
        public String toString() {
            return "Preparado";
        }
    },
    PREPARANDOSE {
        @Override
        public String toString() {
            return "Preparandose";
        }
    },
    PAGADO {
        @Override
        public String toString() {
            return "Pagado";
        }
    },
    CREANDOSE {
        @Override
        public String toString() {
            return "Creandose";
        }
    },
    CANCELADO{
        @Override
        public String toString(){
            return "Cancelado";
        }
    }
}
