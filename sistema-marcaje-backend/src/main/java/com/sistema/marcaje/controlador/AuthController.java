package com.sistema.marcaje.controlador;

import com.sistema.marcaje.modelo.Usuario;
import com.sistema.marcaje.seguridad.LoginRequest;
import com.sistema.marcaje.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://3.133.145.220")
public class AuthController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioServicio.obtenerUsuario(loginRequest.getUsername());

        if (usuario != null && usuario.getPassword().equals(loginRequest.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login Exitoso");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Credenciales incorrectas");
            return ResponseEntity.ok("login incorrecto");
        }
    }
    @GetMapping("/roles/{idUsuario}")
    public ResponseEntity<?> obtenerRolesPorUsuario(@PathVariable("idUsuario") int idUsuario) {
        String sql = "SELECT rol_rol_id FROM usuario_rol WHERE usuario_id = ?";
        List<String> roles = jdbcTemplate.queryForList(sql, new Object[]{idUsuario}, String.class);

        Map<String, Object> response = new HashMap<>();
        response.put("idUsuario", idUsuario);
        response.put("roles", roles);

        return ResponseEntity.ok(response);
    }
}
