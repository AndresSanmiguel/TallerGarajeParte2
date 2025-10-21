<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${vehiculo != null ? "Editar Vehículo" : "Agregar Vehículo"}</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 30px; }
        form { background: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px #ccc; width: 400px; margin: auto; }
        input, select { width: 100%; padding: 8px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 5px; }
        button { padding: 10px 15px; background: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; }
        button:hover { background: #0056b3; }
        .alert { padding: 10px; margin-bottom: 15px; border-radius: 5px; }
        .error { background: #f8d7da; color: #721c24; }
    </style>
</head>
<body>
<h1 style="text-align:center;">${vehiculo != null ? "✏️ Editar Vehículo" : "➕ Agregar Vehículo"}</h1>

<c:if test="${not empty error}">
    <div class="alert error">${error}</div>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/vehiculos">
    <input type="hidden" name="id" value="${vehiculo != null ? vehiculo.id : ''}" />

    <label>Placa:</label>
    <input type="text" name="placa" value="${vehiculo != null ? vehiculo.placa : ''}" required />

    <label>Marca:</label>
    <input type="text" name="marca" value="${vehiculo != null ? vehiculo.marca : ''}" required />

    <label>Modelo:</label>
    <input type="text" name="modelo" value="${vehiculo != null ? vehiculo.modelo : ''}" required />

    <label>Año:</label>
    <input type="number" name="anio" value="${vehiculo != null ? vehiculo.anio : ''}" required />

    <label>Color:</label>
    <select name="color">
        <option value="Rojo" ${vehiculo != null && vehiculo.color == 'Rojo' ? 'selected' : ''}>Rojo</option>
        <option value="Blanco" ${vehiculo != null && vehiculo.color == 'Blanco' ? 'selected' : ''}>Blanco</option>
        <option value="Negro" ${vehiculo != null && vehiculo.color == 'Negro' ? 'selected' : ''}>Negro</option>
        <option value="Azul" ${vehiculo != null && vehiculo.color == 'Azul' ? 'selected' : ''}>Azul</option>
        <option value="Gris" ${vehiculo != null && vehiculo.color == 'Gris' ? 'selected' : ''}>Gris</option>
    </select>

    <label>Propietario:</label>
    <input type="text" name="propietario" value="${vehiculo != null ? vehiculo.propietario : ''}" required />

    <button type="submit">💾 Guardar</button>
</form>

<br><br>
<div style="text-align:center;">
    <a href="${pageContext.request.contextPath}/vehiculos">⬅ Volver al listado</a>
</div>
</body>
</html>
