package com.examen.org.service;

import com.examen.org.model.Proyecto;

import java.util.List;

public interface ProyectoService {


    Proyecto guardarProyecto(Proyecto p) throws Exception;

    List<Proyecto> listarPorTipoProyecto(String tipoProyecto) throws Exception;

    List<Proyecto> listarPorSueldoMayorA(double sueldo) throws Exception;

    int actualizarEstado(String estado, int id) throws Exception;

    List<Proyecto> listarTodosLosProyectos() throws Exception;
}
