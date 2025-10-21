package com.garaje.model;

/**
 * Clase que representa un vehículo en la base de datos garaje.
 */
public class Vehiculo {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private String color;
    private String propietario;

    public Vehiculo() {}

    public Vehiculo(int id, String placa, String marca, String modelo, int anio, String color, String propietario) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.propietario = propietario;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getPropietario() { return propietario; }
    public void setPropietario(String propietario) { this.propietario = propietario; }
}
