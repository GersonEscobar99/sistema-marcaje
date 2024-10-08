package com.sistema.marcaje.servicios.impl;

import com.sistema.marcaje.modelo.Usuario;
import com.sistema.marcaje.modelo.UsuarioRol;
import com.sistema.marcaje.repositorio.RolRepository;
import com.sistema.marcaje.repositorio.UsuarioRepository;
import com.sistema.marcaje.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        //valida si el usuario existe
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }
        else {
            for (UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long usuarioId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isPresent()) {
            return Optional.of(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + usuarioId);
        }
    }

    //ver si funciona
    @Override
    public  Usuario actualizarUsuario(Long usuarioId, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioExistente.setUsername(usuarioActualizado.getUsername());
        usuarioExistente.setPassword(usuarioActualizado.getPassword());
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setApellido(usuarioActualizado.getApellido());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
        usuarioExistente.setEnabled(usuarioActualizado.isEnabled());
        usuarioExistente.setPerfil(usuarioActualizado.getPerfil());

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void eliminarUsiario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public Page<Usuario> obtenerUsuariosPaginados(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

}
