package com.sistema.examenes.servicios.impl;

import com.sistema.examenes.modelo.Marcaje;
import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.repositorio.MarcajeRespository;
import com.sistema.examenes.servicios.MarcajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MarcajeServicioImpl implements MarcajeServicio {

    @Autowired
    public MarcajeRespository marcajeRespository;

    @Override
    public Marcaje registrarEntrada(Usuario usuario) {
        Marcaje marcaje = new Marcaje();
        marcaje.setUsuario(usuario);
        marcaje.setHoraEntrada(LocalDateTime.now());
        return marcajeRespository.save(marcaje);
    }

    @Override
    public Marcaje registrarSalida(Usuario usuario) {
        List<Marcaje> marcajes = marcajeRespository.findByUsuario(usuario);
        if (!marcajes.isEmpty()) {
            Marcaje ultimoMarcaje = marcajes.get(marcajes.size() - 1);
            if (ultimoMarcaje.getHoraSalida() == null) {
                ultimoMarcaje.setHoraSalida(LocalDateTime.now());
                return marcajeRespository.save(ultimoMarcaje);
            }
        }
        return null;
    }


    @Override
    public List<Marcaje> obtenerMarcajes(Usuario usuario) {
        return marcajeRespository.findByUsuario(usuario);
    }

    @Override
    public Marcaje obtenerUltimoMarcaje(Usuario usuario) {
        return marcajeRespository.findTopByUsuarioOrderByIdDesc(usuario);
    }

//    @Override
//    public Marcaje obtenerMarcajeActivo(Usuario usuario) {
//        List<Marcaje> marcajes = marcajeRespository.findByUsuario(usuario);
//        // Buscamos el último marcaje sin hora de salida
//        return marcajes.stream()
//                .filter(marcaje -> marcaje.getHoraSalida() == null)
//                .findFirst()
//                .orElse(null);
//    }
}
