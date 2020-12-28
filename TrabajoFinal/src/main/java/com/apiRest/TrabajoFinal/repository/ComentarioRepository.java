package com.apiRest.TrabajoFinal.repository;

import com.apiRest.TrabajoFinal.entity.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
  
}
