package org.example;

import org.example.controller.clienteController;
import org.example.model.cliente;

public class Main {

    public static void main(String[] args) {
        clienteController clienteController = new clienteController();

        new cliente(1L, "Francisco", 10L, true);
        new cliente(2L, "Adrian", 5L, true);
        new cliente(3L, "Pablo", 8L, false);
        new cliente(4L, "Alejandro", 2L, false);
        new cliente(5L, "Samuel", 12L, true);

        clienteController.getCliente(4L);
        clienteController.listarMejoresClientes(11L);
        clienteController.estadisticas();

        clienteController.cerrarConexion();
    }
}

