package com.apiRest.TrabajoFinal.controller;

import javax.validation.Valid;

import com.apiRest.TrabajoFinal.dto.PostDTO;
import com.apiRest.TrabajoFinal.entity.Post;
import com.apiRest.TrabajoFinal.entity.Usuario;
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
@RequestMapping("/api/v1/post")
public class PostController {
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping
  public ResponseEntity<?> getPosts2(){
    return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping(params = "titulo")
  public ResponseEntity<?> getPosts3(@RequestParam String titulo){
    return new ResponseEntity<>(postRepository.findByTituloContaining(titulo), HttpStatus.OK);
  }

  @GetMapping(params = "publicado")
  public ResponseEntity<?> getPosts4(@RequestParam Boolean publicado){
    return new ResponseEntity<>(postRepository.findByPublicadoLike(publicado), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> postPost(@Valid @RequestBody PostDTO newPost){
    try {
      Usuario user = usuarioRepository.getOne(newPost.getUsuario());
      Post post = newPost.getPost();
      if(user != null){
        user.setPost(post);
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<?> deletePost(@PathVariable Long postId){
    Post postDelete = postRepository.getOne(postId);
    if(postDelete != null){
      postDelete.setUsuario(null);
      postRepository.delete(postDelete);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{postId}")
  public ResponseEntity<?> putPost(@PathVariable Long postId, @Valid @RequestBody Post post){
    try {
      Post postPut = postRepository.getOne(postId);
      if (post.getTitulo() != null) {postPut.setTitulo(post.getTitulo());};
      if (post.getDescripcion() != null) {postPut.setDescripcion(post.getDescripcion());};
      if (post.getContenido() != null) {postPut.setContenido(post.getContenido());};
      if (post.getPublicado() != null) {postPut.setPublicado(post.getPublicado());};
      return new ResponseEntity<>(postRepository.save(postPut), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
