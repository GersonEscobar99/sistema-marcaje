package com.sistema.marcaje.servicios;

import com.sistema.marcaje.modelo.Marcaje;
import com.sistema.marcaje.modelo.Usuario;

import java.util.List;

public interface MarcajeServicio {
    Marcaje registrarEntrada(Usuario usuario);
    Marcaje registrarSalida(Usuario usuario);
    List<Marcaje> obtenerMarcajes(Usuario usuario);
    Marcaje obtenerUltimoMarcaje(Usuario usuario);
    List<Marcaje> obtenerTodosLosMarcajes();
}
