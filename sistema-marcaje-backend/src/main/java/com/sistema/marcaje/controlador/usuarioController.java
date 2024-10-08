package com.sistema.marcaje.controlador;

import com.sistema.marcaje.modelo.Rol;
import com.sistema.marcaje.modelo.Usuario;
import com.sistema.marcaje.modelo.UsuarioRol;
import com.sistema.marcaje.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("http://3.133.145.220")
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

    @GetMapping("/id/{usuarioId}")
    public ResponseEntity<Optional<Usuario>> obtenerUsuarioPorId(@PathVariable("usuarioId") Long usuarioId) {
        try {
            Optional<Usuario> usuario = usuarioServicio.obtenerUsuarioPorId(usuarioId);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable("usuarioId") Long usuarioId,
            @RequestBody Usuario usuarioActualizado) {
        try {
            Usuario usuarioActualizadoEnDb = usuarioServicio.actualizarUsuario(usuarioId, usuarioActualizado);
            return new ResponseEntity<>(usuarioActualizadoEnDb, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioServicio.eliminarUsiario(usuarioId);
    }
}
