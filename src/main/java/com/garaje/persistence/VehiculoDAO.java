package com.garaje.persistence;

import com.garaje.model.Vehiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO que maneja las operaciones CRUD con la tabla vehiculos.
 */
public class VehiculoDAO {

    private Connection con;

    public VehiculoDAO(Connection con) {
        this.con = con;
    }

    // LISTAR todos los vehículos
    public List<Vehiculo> listar() throws SQLException {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            Vehiculo v = new Vehiculo(
                rs.getInt("id"),
                rs.getString("placa"),
                rs.getString("marca"),
                rs.getString("modelo"),
                rs.getInt("anio"),
                rs.getString("color"),
                rs.getString("propietario")
            );
            lista.add(v);
        }
        rs.close();
        st.close();
        return lista;
    }

    // BUSCAR un vehículo por ID
    public Vehiculo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM vehiculos WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Vehiculo v = null;
        if (rs.next()) {
            v = new Vehiculo(
                rs.getInt("id"),
                rs.getString("placa"),
                rs.getString("marca"),
                rs.getString("modelo"),
                rs.getInt("anio"),
                rs.getString("color"),
                rs.getString("propietario")
            );
        }
        rs.close();
        ps.close();
        return v;
    }

    // COMPROBAR si una placa ya existe
    public boolean existePlaca(String placa) throws SQLException {
        String sql = "SELECT COUNT(*) FROM vehiculos WHERE placa = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, placa);
        ResultSet rs = ps.executeQuery();
        rs.next();
        boolean existe = rs.getInt(1) > 0;
        rs.close();
        ps.close();
        return existe;
    }

    // AGREGAR un nuevo vehículo
    public void agregar(Vehiculo v) throws SQLException {
        String sql = "INSERT INTO vehiculos (placa, marca, modelo, anio, color, propietario) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, v.getPlaca());
        ps.setString(2, v.getMarca());
        ps.setString(3, v.getModelo());
        ps.setInt(4, v.getAnio());
        ps.setString(5, v.getColor());
        ps.setString(6, v.getPropietario());
        ps.executeUpdate();
        ps.close();
    }

    // ACTUALIZAR vehículo existente
    public void actualizar(Vehiculo v) throws SQLException {
        String sql = "UPDATE vehiculos SET placa=?, marca=?, modelo=?, anio=?, color=?, propietario=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, v.getPlaca());
        ps.setString(2, v.getMarca());
        ps.setString(3, v.getModelo());
        ps.setInt(4, v.getAnio());
        ps.setString(5, v.getColor());
        ps.setString(6, v.getPropietario());
        ps.setInt(7, v.getId());
        ps.executeUpdate();
        ps.close();
    }

    // ELIMINAR vehículo
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM vehiculos WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
}
