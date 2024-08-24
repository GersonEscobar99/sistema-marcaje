package com.sistema.examenes.controlador;

import com.sistema.examenes.modelo.Marcaje;
import com.sistema.examenes.modelo.Usuario;
import com.sistema.examenes.seguridad.LoginRequest;
import com.sistema.examenes.servicios.MarcajeServicio;
import com.sistema.examenes.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioServicio usuarioServicio;

//    @Autowired
//    private MarcajeServicio marcajeServicio;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateuser(@RequestBody LoginRequest loginRequest){
        Usuario usuario = usuarioServicio.obtenerUsuario(loginRequest.getUsername());

        if (usuario != null && usuario.getPassword().equals(loginRequest.getPassword())){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login Exitoso");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Credenciales incorrectas");
            return ResponseEntity.ok("login incorrecto");
        }
    }

//    @PostMapping("/entrada/{username}")
//    public Marcaje registrarEntrada(@PathVariable("username") String username) throws Exception {
//        Usuario usuario = usuarioServicio.obtenerUsuario(username);
//        Marcaje marcajeActivo = marcajeServicio.obtenerMarcajeActivo(usuario);
//
//        if (marcajeActivo == null) {
//            return marcajeServicio.registrarEntrada(usuario);
//        } else {
//            throw new Exception("El usuario ya ha registrado la entrada y no ha marcado la salida.");
//        }
//    }
//
//    @PostMapping("/salida/{username}")
//    public Marcaje registrarSalida(@PathVariable("username") String username) throws Exception {
//        Usuario usuario = usuarioServicio.obtenerUsuario(username);
//        Marcaje marcajeActivo = marcajeServicio.obtenerMarcajeActivo(usuario);
//
//        if (marcajeActivo != null) {
//            return marcajeServicio.registrarSalida(usuario);
//        } else {
//            throw new Exception("El usuario no tiene una entrada activa para marcar la salida.");
//        }
//    }
//
//    @GetMapping("/estado/{username}")
//    public String obtenerEstadoMarcaje(@PathVariable("username") String username) throws Exception {
//        Usuario usuario = usuarioServicio.obtenerUsuario(username);
//        Marcaje marcajeActivo = marcajeServicio.obtenerMarcajeActivo(usuario);
//
//        if (marcajeActivo == null) {
//            return "entrada";
//        } else {
//            return "salida";
//        }
//    }
}
