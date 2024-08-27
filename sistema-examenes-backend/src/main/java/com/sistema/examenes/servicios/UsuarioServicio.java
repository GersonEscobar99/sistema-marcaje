package com.sistema.examenes.servicios;

import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UsuarioServicio{

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public List<Usuario> obtenerTodosLosUsuarios();

    public Usuario obtenerUsuario(String username);

    public Usuario actualizarUsuario(Long usuarioId, Usuario usuario);

    public void eliminarUsiario(Long usuarioId);

    public Page<Usuario> obtenerUsuariosPaginados(Pageable pageable);


}
