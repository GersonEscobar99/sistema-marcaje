package com.sistema.marcaje.servicios;

import com.sistema.marcaje.modelo.Marcaje;
import com.sistema.marcaje.modelo.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MarcajeServicio {
    Marcaje registrarEntrada(Usuario usuario);
    Marcaje registrarSalida(Usuario usuario);
    List<Marcaje> obtenerMarcajes(Usuario usuario);
    Marcaje obtenerUltimoMarcaje(Usuario usuario);
    List<Marcaje> obtenerTodosLosMarcajes();
    Page<Marcaje> obtenerMarcajesPaginados(Pageable pageable);
}
