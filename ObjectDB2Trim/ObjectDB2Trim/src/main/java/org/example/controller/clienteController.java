package org.example.controller;

import org.example.model.cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class clienteController {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public clienteController() {
        emf = Persistence.createEntityManagerFactory("objectdb:/path/to/your/database.odb");
        em = emf.createEntityManager();
    }

    public clienteController(EntityManagerFactory emf, EntityManager em) {
        this.emf = emf;
        this.em = em;
    }

    public void cerrarConexion() {
        em.close();
        emf.close();
    }

    public void insertarCliente(cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public void getCliente(Long id) {
        cliente cliente = em.find(org.example.model.cliente.class, id);
        if (cliente != null) {
            System.out.println("Información del Cliente:");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Total de Ventas: " + cliente.getTotalVentas());
            System.out.println("Estado: " + (cliente.isActivo() ? "Activo" : "Inactivo"));
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void listarMejoresClientes(Long cantidad) {
        Query query = em.createQuery("SELECT c FROM cliente c WHERE c.activo = true AND c.totalVentas > :cantidad");
        query.setParameter("cantidad", cantidad);

        List<cliente> clientes = query.getResultList();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes con total de ventas mayor a " + cantidad);
        } else {
            System.out.println("Clientes activos con total de ventas mayor a " + cantidad + ":");
            for (cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre());
            }
        }
    }

    public void estadisticas() {
        Query queryTotalVentas = em.createQuery("SELECT SUM(c.totalVentas) FROM cliente c");
        Long totalVentas = (Long) queryTotalVentas.getSingleResult();

        Query queryPromedioVentas = em.createQuery("SELECT AVG(c.totalVentas) FROM cliente c WHERE c.activo = true");
        Double promedioVentas = (Double) queryPromedioVentas.getSingleResult();

        Query queryClientesInactivos = em.createQuery("SELECT COUNT(c) FROM cliente c WHERE c.activo = false AND c.totalVentas > 0");
        Long clientesInactivosConVentas = (Long) queryClientesInactivos.getSingleResult();

        System.out.println("Resumen Estadístico:");
        System.out.println("Total de Ventas: " + totalVentas);
        System.out.println("Promedio de Ventas: " + promedioVentas);
        System.out.println("Cantidad de clientes inactivos : " + clientesInactivosConVentas);
    }
}

