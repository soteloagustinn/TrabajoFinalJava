package com.apiRest.TrabajoFinal.repository;

import java.time.LocalDate;
import java.util.List;

import com.apiRest.TrabajoFinal.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

  List<Usuario> findByCiudadLike(String ciudad);

  List<Usuario> findByFechaCreacionIsAfter(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

  Usuario findByEmailLike(String email);
}
