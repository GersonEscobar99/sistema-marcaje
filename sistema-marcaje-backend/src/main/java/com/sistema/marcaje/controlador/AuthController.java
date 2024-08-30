package com.sistema.marcaje.controlador;

import com.sistema.marcaje.modelo.Usuario;
import com.sistema.marcaje.seguridad.LoginRequest;
import com.sistema.marcaje.servicios.UsuarioServicio;
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
}
