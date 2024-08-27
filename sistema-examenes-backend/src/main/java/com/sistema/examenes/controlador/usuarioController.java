package com.sistema.examenes.controlador;

import com.sistema.examenes.modelo.Rol;
import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.modelo.UsuarioRol;
import com.sistema.examenes.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("http://localhost:4200")
public class usuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("defaul.png");
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNombre("Normal");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);

        return usuarioServicio.guardarUsuario(usuario, usuarioRoles);
    }

    @GetMapping("/")
    public List<Usuario> obtenerTodosLosUsuarios(){
        return  usuarioServicio.obtenerTodosLosUsuarios();
    }

    @GetMapping("/paginados/")
    public Page<Usuario> obtenerUsuariosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioServicio.obtenerUsuariosPaginados(pageable);
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return  usuarioServicio.obtenerUsuario(username);
    }


    @PutMapping("/{usuarioId}")
    public Usuario actualizarUsuario(
            @PathVariable("usuarioId") Long usuarioId,
            @RequestBody Usuario usuarioActualizado){
        return usuarioServicio.actualizarUsuario(usuarioId, usuarioActualizado);
    }


    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioServicio.eliminarUsiario(usuarioId);
    }
}
