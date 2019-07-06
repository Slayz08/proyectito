package com.examen.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examen.org.model.Proyecto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    @Query("SELECT p FROM Proyecto p where p.tipoProyecto = ?1")
    List<Proyecto> listarPorTipoProyecto(String tipoProyecto) throws Exception;

    @Query("SELECT p FROM Proyecto p where p.sueldo > ?1")
    List<Proyecto> listarPorSueldoMayorA(double sueldo);

    @Modifying
    @Query("UPDATE Proyecto SET estado = ?1 where id = ?2")
    @Transactional
    int actualizarEstado(String estado, int id) throws Exception;


}
