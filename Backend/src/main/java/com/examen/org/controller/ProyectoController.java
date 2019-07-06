package com.examen.org.controller;

import com.examen.org.model.Proyecto;
import com.examen.org.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    private ProyectoService proyectoService;

    @Autowired
    public ProyectoController(ProyectoService proyectoService){
        this.proyectoService = proyectoService;
    }

    @RequestMapping
    public List<Proyecto> listarTodosLosProyectos() throws Exception{
        return proyectoService.listarTodosLosProyectos();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Proyecto guardarProyecto(@RequestBody Proyecto p) throws Exception{
        return proyectoService.guardarProyecto(p);
    }

    @RequestMapping(path="/sueldoMayorA/{sueldo}",method = RequestMethod.GET)
    public List<Proyecto> listarPorSueldoMayorA(@PathVariable("sueldo") double sueldo) throws Exception{
        return proyectoService.listarPorSueldoMayorA(sueldo);
    }

    @RequestMapping(path="/{tipoProyecto}",method = RequestMethod.GET)
    public List<Proyecto> listarPorTipoProyecto(@PathVariable("tipoProyecto") String tipoProyecto) throws Exception{
        return proyectoService.listarPorTipoProyecto(tipoProyecto);
    }

    @RequestMapping(path="/{id}/actualizarEstado/{estado}")
    int actualizarEstado(@PathVariable("estado") String estado, @PathVariable("id") int id) throws Exception{
        return proyectoService.actualizarEstado(estado, id);
    }

}
