package com.examen.org.service.implementation;

import com.examen.org.model.Proyecto;
import com.examen.org.repository.ProyectoRepository;
import com.examen.org.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private ProyectoRepository proyectoRepository;

    @Autowired
    public ProyectoServiceImpl(ProyectoRepository proyectoRepository){
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public Proyecto guardarProyecto(Proyecto p) throws Exception {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        int c = 0;
        for(int i = 0; i < proyectos.size(); i++){
            if(proyectos.get(i).getNombre().equals(p.getNombre())){
                c++;
            }

        }
        if(c == 0){
            return proyectoRepository.save(p);
        }
        return new Proyecto();

    }

    @Override
    public List<Proyecto> listarPorTipoProyecto(String tipoProyecto) throws Exception {
        return proyectoRepository.listarPorTipoProyecto(tipoProyecto);
    }

    @Override
    public List<Proyecto> listarPorSueldoMayorA(double sueldo) {
        return proyectoRepository.listarPorSueldoMayorA(sueldo);
    }

    @Override
    public int actualizarEstado(String estado, int id) throws Exception {
        return proyectoRepository.actualizarEstado(estado, id);
    }

    @Override
    public List<Proyecto> listarTodosLosProyectos() throws Exception {
        return proyectoRepository.findAll();
    }
}
