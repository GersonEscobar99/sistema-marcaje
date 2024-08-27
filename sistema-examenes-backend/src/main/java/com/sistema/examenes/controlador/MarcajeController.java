//package com.sistema.examenes.controlador;
//
//import com.sistema.examenes.modelo.Marcaje;
//import com.sistema.examenes.modelo.Usuario;
//import com.sistema.examenes.servicios.MarcajeServicio;
//import com.sistema.examenes.servicios.UsuarioServicio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/marcajes")
//@CrossOrigin("http://localhost:4200")
//public class MarcajeController {
//
//    @Autowired
//    private MarcajeServicio marcajeServicio;
//
//    @Autowired
//    private UsuarioServicio usuarioServicio;
//
//    @PostMapping("/entrada/{username}")
//    public Marcaje registrarEntrada(@PathVariable String username) {
//        Usuario usuario = usuarioServicio.obtenerUsuario(username);
//        if (usuario != null) {
//            return marcajeServicio.registrarEntrada(usuario);
//        }
//        return null;
//    }
//
//    @PostMapping("/salida/{username}")
//    public Marcaje registrarSalida(@PathVariable String username) {
//        Usuario usuario = usuarioServicio.obtenerUsuario(username);
//        if (usuario != null) {
//            Marcaje marcaje = marcajeServicio.registrarEntrada(usuario);
//
//            //return new ResponseEntity<>(marcaje, HttpStatus.CREATED);
//            return marcajeServicio.registrarSalida(usuario);
//        }
//        //return new ResponseEntity<>("el usuario no existe", HttpStatus.NOT_FOUND);
//        return null;
//    }
//
//    @GetMapping("/historial/{username}")
//    public List<Marcaje> obtenerMarcajes(@PathVariable String username) {
//        Usuario usuario = usuarioServicio.obtenerUsuario(username);
//        if (usuario != null) {
//            return marcajeServicio.obtenerMarcajes(usuario);
//        }
//        return null;
//    }
//}

package com.sistema.examenes.controlador;

import com.sistema.examenes.modelo.Marcaje;
import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.servicios.MarcajeServicio;
import com.sistema.examenes.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcajes")
@CrossOrigin("http://localhost:4200")
public class MarcajeController {

    @Autowired
    private MarcajeServicio marcajeServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/entrada/{username}")
    public ResponseEntity<?> registrarEntrada(@PathVariable String username) {
        Usuario usuario = usuarioServicio.obtenerUsuario(username);
        if (usuario != null) {
            Marcaje marcaje = marcajeServicio.registrarEntrada(usuario);
            return new ResponseEntity<>(marcaje, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("El usuario no existe", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/salida/{username}")
    public ResponseEntity<?> registrarSalida(@PathVariable String username) {
        Usuario usuario = usuarioServicio.obtenerUsuario(username);
        if (usuario != null) {
            Marcaje marcaje = marcajeServicio.registrarSalida(usuario); // Llama a registrarSalida
            return new ResponseEntity<>(marcaje, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("El usuario no existe", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/historial/{username}")
    public ResponseEntity<?> obtenerMarcajes(@PathVariable String username) {
        Usuario usuario = usuarioServicio.obtenerUsuario(username);
        if (usuario != null) {
            List<Marcaje> marcajes = marcajeServicio.obtenerMarcajes(usuario);
            return new ResponseEntity<>(marcajes, HttpStatus.OK);
        }
        return new ResponseEntity<>("El usuario no existe", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ultimo/{username}")
    public Marcaje obtenerUltimoMarcaje(@PathVariable String username) {
        Usuario usuario = usuarioServicio.obtenerUsuario(username);
        if (usuario != null) {
            return marcajeServicio.obtenerUltimoMarcaje(usuario);
        }
        return null;
    }


}

