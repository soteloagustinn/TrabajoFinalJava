package com.apiRest.TrabajoFinal.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import com.apiRest.TrabajoFinal.entity.Usuario;
import com.apiRest.TrabajoFinal.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
  @Autowired
  private UsuarioRepository usuarioRepository;

  @GetMapping
  public ResponseEntity<?> getUsers(){
    return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping(params = "ciudad")
  public ResponseEntity<?> getUsersByCiudad(@RequestParam String ciudad){
    return new ResponseEntity<>(usuarioRepository.findByCiudadLike(ciudad), HttpStatus.OK);
  }

  @GetMapping(params = "date")
  public ResponseEntity<?> getUsersByFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
    return new ResponseEntity<>(usuarioRepository.findByFechaCreacionIsAfter(date), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> createUser(@Valid @RequestBody Usuario usuario){
    if(usuario.getEmail() != null){
      Usuario existe = usuarioRepository.findByEmailLike(usuario.getEmail());
      if(existe == null){
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/{usuarioId}")
  public ResponseEntity<?> deleteUser(@PathVariable Long usuarioId){
    Usuario usuarioDelete = usuarioRepository.getOne(usuarioId);
      usuarioRepository.delete(usuarioDelete);
      return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{usuarioId}")
  public ResponseEntity<?> editUser(@PathVariable Long usuarioId, @Valid @RequestBody Usuario usuario){
    Usuario usuarioEdit = usuarioRepository.getOne(usuarioId);
    try {
      if (usuario.getNombre() != null) {usuarioEdit.setNombre(usuario.getNombre());};
      if (usuario.getApellido() != null) {usuarioEdit.setApellido(usuario.getApellido());};
      if (usuario.getEmail() != null) {usuarioEdit.setEmail(usuario.getEmail());};
      if (usuario.getPassword() != null) {usuarioEdit.setPassword(usuario.getPassword());};
      if (usuario.getCiudad() != null) {usuarioEdit.setCiudad(usuario.getCiudad());};
      if (usuario.getProvincia() != null) {usuarioEdit.setProvincia(usuario.getProvincia());};
      if (usuario.getPais() != null) {usuarioEdit.setPais(usuario.getPais());};
      return new ResponseEntity<>(usuarioRepository.save(usuarioEdit),HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
