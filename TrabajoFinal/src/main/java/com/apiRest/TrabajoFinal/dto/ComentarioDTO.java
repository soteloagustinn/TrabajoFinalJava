package com.apiRest.TrabajoFinal.dto;

public class ComentarioDTO {
  private Long usuario;
  private String comentario;

  public Long getUsuario() {
    return usuario;
  }

  public void setUsuario(Long usuario) {
    this.usuario = usuario;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }
}
