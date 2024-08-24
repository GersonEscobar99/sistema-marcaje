package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Marcaje;
import com.sistema.examenes.modelo.Usuario;

import java.util.List;

public interface MarcajeServicio {
    Marcaje registrarEntrada(Usuario usuario);
    Marcaje registrarSalida(Usuario usuario);
    List<Marcaje> obtenerMarcajes(Usuario usuario);
//    Marcaje obtenerMarcajeActivo(Usuario usuario);
}
