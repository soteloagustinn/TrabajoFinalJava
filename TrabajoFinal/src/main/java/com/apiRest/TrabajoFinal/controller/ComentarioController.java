package com.apiRest.TrabajoFinal.controller;

import java.util.List;

import javax.validation.Valid;

import com.apiRest.TrabajoFinal.dto.ComentarioDTO;
import com.apiRest.TrabajoFinal.entity.Comentario;
import com.apiRest.TrabajoFinal.entity.Post;
import com.apiRest.TrabajoFinal.entity.Usuario;
import com.apiRest.TrabajoFinal.repository.ComentarioRepository;
import com.apiRest.TrabajoFinal.repository.PostRepository;
import com.apiRest.TrabajoFinal.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post/{postId}/comentario")
public class ComentarioController {
  @Autowired
  private ComentarioRepository comentarioRepository;

  @Autowired
  private PostRepository postRepository;
  
  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping
  public ResponseEntity<?> getComentarios(@PathVariable Long postId){
    Post post = postRepository.getOne(postId);
    List<Comentario> comentarios = post.getComentarios();
    return new ResponseEntity<>(comentarios, HttpStatus.OK);
    
  }
  
  @GetMapping(params = "cant")
  public ResponseEntity<?> getComentarios(@RequestParam Integer cant, @PathVariable Long postId){
    Post post = postRepository.getOne(postId);
    if(post != null) {
      List<Comentario> comentarios = post.getComentarios();
      if(cant != null && cant <= comentarios.size() && cant > 0){
        List<Comentario> comentarios2 = comentarios.subList(0, cant);
        return new ResponseEntity<>(comentarios2, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping
  public ResponseEntity<?> postComentario(@PathVariable Long postId, @Valid @RequestBody ComentarioDTO comentarioDTO){
    try {
      Usuario user = usuarioRepository.getOne(comentarioDTO.getUsuario());
      Post post = postRepository.getOne(postId);
      Comentario comentario = new Comentario();
      user.setComentario(comentario);
      post.setComentario(comentario);
      comentario.setComentario(comentarioDTO.getComentario());
      return new ResponseEntity<>(comentarioRepository.save(comentario) , HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{comentarioId}")
  public ResponseEntity<?> deleteComentario(@PathVariable Long comentarioId, @PathVariable Long postId){
      Post post = postRepository.getOne(postId);
      Comentario comentDelete = comentarioRepository.getOne(comentarioId);
      post.deleteComentario(comentDelete);
      comentarioRepository.delete(comentDelete);
      return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{comentarioId}")
  public ResponseEntity<?> putComentario(@PathVariable Long comentarioId, @Valid @RequestBody Comentario comentario){
    try {
      Comentario comentPut = comentarioRepository.getOne(comentarioId);
      if (comentario.getComentario() != null) {comentPut.setComentario(comentario.getComentario());};
      return new ResponseEntity<>(comentarioRepository.save(comentPut), HttpStatus.OK); 
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}