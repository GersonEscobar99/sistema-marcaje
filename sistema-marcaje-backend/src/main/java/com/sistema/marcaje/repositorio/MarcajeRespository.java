package com.sistema.marcaje.repositorio;

import com.sistema.marcaje.modelo.Marcaje;
import com.sistema.marcaje.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcajeRespository  extends JpaRepository<Marcaje, Long> {
    List<Marcaje> findByUsuario(Usuario usuario);
    Marcaje findTopByUsuarioOrderByIdDesc(Usuario usuario);

}
