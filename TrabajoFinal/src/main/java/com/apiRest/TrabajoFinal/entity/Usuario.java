package com.apiRest.TrabajoFinal.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String apellido;
  @Column(unique = true)
  private String email;
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate fechaCreacion = LocalDate.now();
  private String ciudad;
  private String provincia;
  private String pais;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCiudad() {
    return ciudad;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public LocalDate getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDate fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public void setPost(Post post) {
    post.setUsuario(this);
  }

  public void setComentario(Comentario comentario) {
    comentario.setUsuario(this);
  }
}
