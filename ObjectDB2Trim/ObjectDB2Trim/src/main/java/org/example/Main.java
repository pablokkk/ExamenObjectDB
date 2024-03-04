package org.example;

import org.example.controller.clienteController;
import org.example.model.cliente;

public class Main {

    public static void main(String[] args) {
        clienteController clienteController = new clienteController();

        var cliente1 = new cliente(1L, "Francisco", 10L, true );
        var cliente2 = new cliente(2L, "Adrian", 5L, true );
        var cliente3 = new cliente(3L, "Pablo", 8L, false );
        var cliente4 = new cliente(4L, "Alejandro", 2L, false );
        var cliente5 = new cliente(5L, "Samuel", 12L, true );

        clienteController.getCliente(4L);
        clienteController.listarMejoresClientes(11L);
        clienteController.estadisticas();

        clienteController.cerrarConexion();
    }
}

