package com.examen.org.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "proyecto")
public class Proyecto {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "proyecto_id")
private Integer id;

@Column(name="nombre", length = 30, nullable = false)
private String nombre;

@Column(name="descripcion", length = 100, nullable = false)
private String descripcion;

@Column(name="tipo_proyecto", length = 50, nullable = false)
private String tipoProyecto;

@Column(name="sueldo", length = 15, nullable = false)
private double sueldo;

@Column(name="estado", length = 20, nullable = false)
private String estado;
}
