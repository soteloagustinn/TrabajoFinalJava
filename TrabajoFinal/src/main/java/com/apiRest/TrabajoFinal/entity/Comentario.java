package com.apiRest.TrabajoFinal.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Comentario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Autor", referencedColumnName = "id")
  private Usuario usuario;
  private LocalDate fechaCreacion = LocalDate.now();
  @Size(max = 200)
  private String comentario;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public LocalDate getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion() {
    this.fechaCreacion = LocalDate.now();
  }
}
