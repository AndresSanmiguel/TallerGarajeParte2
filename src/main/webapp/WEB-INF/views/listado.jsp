<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Listado de Vehículos</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 30px; }
        table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 0 10px #ccc; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #222; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .btn { padding: 5px 10px; text-decoration: none; border-radius: 4px; }
        .btn-add { background: #28a745; color: white; }
        .btn-edit { background: #ffc107; color: black; }
        .btn-delete { background: #dc3545; color: white; }
        .alert { padding: 10px; margin-bottom: 15px; border-radius: 5px; }
        .success { background: #d4edda; color: #155724; }
        .error { background: #f8d7da; color: #721c24; }
    </style>
</head>
<body>
<h1>🚗 Listado de Vehículos</h1>

<c:if test="${not empty success}">
    <div class="alert success">${success}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert error">${error}</div>
</c:if>

<a href="${pageContext.request.contextPath}/vehiculos?action=new" class="btn btn-add">➕ Agregar vehículo</a>
<br><br>

<table>
    <tr>
        <th>ID</th>
        <th>Placa</th>
        <th>Marca</th>
        <th>Modelo</th>
        <th>Año</th>
        <th>Color</th>
        <th>Propietario</th>
        <th>Acciones</th>
    </tr>
    <c:forEach var="v" items="${vehiculos}">
        <tr>
            <td>${v.id}</td>
            <td>${v.placa}</td>
            <td>${v.marca}</td>
            <td>${v.modelo}</td>
            <td>${v.anio}</td>
            <td>${v.color}</td>
            <td>${v.propietario}</td>
            <td>
                <a href="${pageContext.request.contextPath}/vehiculos?action=edit&id=${v.id}" class="btn btn-edit">✏️ Editar</a>
                <a href="${pageContext.request.contextPath}/vehiculos?action=delete&id=${v.id}"
                   class="btn btn-delete" onclick="return confirm('¿Seguro que deseas eliminar este vehículo?')">🗑 Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
