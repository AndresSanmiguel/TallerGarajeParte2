package com.garaje.controller;

import com.garaje.exception.BusinessException;
import com.garaje.facade.VehiculoFacade;
import com.garaje.model.Vehiculo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class VehiculoServlet extends HttpServlet {

    private VehiculoFacade vehiculoFacade = new VehiculoFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                List<Vehiculo> vehiculos = vehiculoFacade.listar();
                request.setAttribute("vehiculos", vehiculos);
                request.getRequestDispatcher("/WEB-INF/views/listado.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Vehiculo vehiculo = vehiculoFacade.buscarPorId(id);
                request.setAttribute("vehiculo", vehiculo);
                request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                vehiculoFacade.eliminar(id);
                response.sendRedirect("vehiculos");
            } else if (action.equals("new")) {
                request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
            }
        } catch (SQLException | BusinessException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String placa = request.getParameter("placa");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        int anio = Integer.parseInt(request.getParameter("anio"));
        String color = request.getParameter("color");
        String propietario = request.getParameter("propietario");

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(placa);
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setAnio(anio);
        vehiculo.setColor(color);
        vehiculo.setPropietario(propietario);

        try {
            if (idStr == null || idStr.isEmpty()) {
                vehiculoFacade.agregar(vehiculo);
            } else {
                vehiculo.setId(Integer.parseInt(idStr));
                vehiculoFacade.actualizar(vehiculo);
            }
            response.sendRedirect("vehiculos");
        } catch (SQLException | BusinessException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("vehiculo", vehiculo);
            request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
        }
    }
}
