package com.sistema.examenes.repositorio;

import com.sistema.examenes.modelo.Marcaje;
import com.sistema.examenes.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarcajeRespository  extends JpaRepository<Marcaje, Long> {
    List<Marcaje> findByUsuario(Usuario usuario);

}
