package com.sistema.marcaje.servicios.impl;

import com.sistema.marcaje.modelo.Marcaje;
import com.sistema.marcaje.modelo.Usuario;
import com.sistema.marcaje.repositorio.MarcajeRespository;
import com.sistema.marcaje.servicios.MarcajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<Marcaje> obtenerTodosLosMarcajes() {
        return marcajeRespository.findAll();
    }

    @Override
    public Page<Marcaje> obtenerMarcajesPaginados(Pageable pageable) {
        return marcajeRespository.findAll(pageable);
    }

}
