package com.apiRest.TrabajoFinal.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String titulo;
  private String descripcion;
  private String contenido;
  private LocalDate fechaCreacion = LocalDate.now();
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Autor", referencedColumnName = "id")
  private Usuario usuario;
  @OneToMany
  private List<Comentario> comentarios = new ArrayList<>();
  private Boolean publicado;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getContenido() {
    return contenido;
  }

  public void setContenido(String contenido) {
    this.contenido = contenido;
  }

  public Boolean getPublicado() {
    return publicado;
  }

  public void setPublicado(Boolean publicado) {
    this.publicado = publicado;
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

  public void setComentario(Comentario comentario) {
    this.comentarios.add(comentario);
  }

  public void deleteComentario(Comentario comentario){
    this.comentarios.remove(comentario);
  }

  public List<Comentario> getComentarios(){
    return comentarios;
  }
}
