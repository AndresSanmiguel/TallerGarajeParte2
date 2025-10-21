package com.garaje.facade;

import com.garaje.exception.BusinessException;
import com.garaje.model.Vehiculo;
import com.garaje.persistence.VehiculoDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

/**
 * Clase de lógica de negocio: aplica las reglas del taller.
 */
public class VehiculoFacade {

    // Datos de conexión a la base de datos
    private final String URL = "jdbc:mysql://localhost:3306/garaje?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASS = "root123"; // pon tu contraseña de MySQL si tienes

    // Colores permitidos
    private final List<String> coloresPermitidos =
            Arrays.asList("Rojo", "Blanco", "Negro", "Azul", "Gris");

    // Método para obtener conexión
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Listar todos
    public List<Vehiculo> listar() throws SQLException {
        try (Connection con = getConnection()) {
            VehiculoDAO dao = new VehiculoDAO(con);
            return dao.listar();
        }
    }

    // Buscar por ID
    public Vehiculo buscarPorId(int id) throws SQLException {
        try (Connection con = getConnection()) {
            VehiculoDAO dao = new VehiculoDAO(con);
            return dao.buscarPorId(id);
        }
    }

    // Agregar nuevo vehículo (con validaciones)
    public void agregar(Vehiculo v) throws SQLException, BusinessException {
        validarVehiculo(v, false);
        try (Connection con = getConnection()) {
            VehiculoDAO dao = new VehiculoDAO(con);
            if (dao.existePlaca(v.getPlaca())) {
                throw new BusinessException("La placa ya está registrada.");
            }
            dao.agregar(v);

            // Notificación si es Ferrari
            if ("Ferrari".equalsIgnoreCase(v.getMarca())) {
                System.out.println("🚗 Notificación: Se agregó un Ferrari (" + v.getPlaca() + ")");
            }
        }
    }

    // Actualizar
    public void actualizar(Vehiculo v) throws SQLException, BusinessException {
        validarVehiculo(v, true);
        try (Connection con = getConnection()) {
            VehiculoDAO dao = new VehiculoDAO(con);
            Vehiculo existente = dao.buscarPorId(v.getId());
            if (existente == null) {
                throw new BusinessException("El vehículo no existe.");
            }

            // Si cambia la placa, validar duplicado
            if (!existente.getPlaca().equalsIgnoreCase(v.getPlaca()) &&
                dao.existePlaca(v.getPlaca())) {
                throw new BusinessException("La nueva placa ya está en uso.");
            }

            dao.actualizar(v);
        }
    }

    // Eliminar
    public void eliminar(int id) throws SQLException, BusinessException {
        try (Connection con = getConnection()) {
            VehiculoDAO dao = new VehiculoDAO(con);
            Vehiculo v = dao.buscarPorId(id);
            if (v == null) throw new BusinessException("El vehículo no existe.");

            if ("Administrador".equalsIgnoreCase(v.getPropietario())) {
                throw new BusinessException("No se puede eliminar un vehículo cuyo propietario es 'Administrador'.");
            }
            dao.eliminar(id);
        }
    }

    // --- Validaciones de negocio ---
    private void validarVehiculo(Vehiculo v, boolean esActualizacion) throws BusinessException {
        if (v.getPlaca() == null || v.getPlaca().trim().length() < 3)
            throw new BusinessException("La placa debe tener al menos 3 caracteres.");

        if (v.getMarca() == null || v.getMarca().trim().length() < 3)
            throw new BusinessException("La marca debe tener al menos 3 caracteres.");

        if (v.getModelo() == null || v.getModelo().trim().length() < 3)
            throw new BusinessException("El modelo debe tener al menos 3 caracteres.");

        if (v.getPropietario() == null || v.getPropietario().trim().length() < 5)
            throw new BusinessException("El propietario debe tener al menos 5 caracteres.");

        if (!coloresPermitidos.contains(capitalizar(v.getColor())))
            throw new BusinessException("Color no permitido. Colores válidos: " + coloresPermitidos);

        int currentYear = Year.now().getValue();
        if (v.getAnio() < currentYear - 20)
            throw new BusinessException("No se aceptan vehículos con más de 20 años de antigüedad.");

        // Prevención simple contra SQL Injection
        if (contienePalabrasSQL(v.getPlaca()) || contienePalabrasSQL(v.getMarca()) || contienePalabrasSQL(v.getModelo()))
            throw new BusinessException("Campo contiene caracteres o palabras no permitidas.");
    }

    private boolean contienePalabrasSQL(String texto) {
        if (texto == null) return false;
        String t = texto.toLowerCase();
        return t.contains("drop") || t.contains("delete") || t.contains("insert") || t.contains("--");
    }

    private String capitalizar(String s) {
        if (s == null || s.isEmpty()) return s;
        s = s.toLowerCase();
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
