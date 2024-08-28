package com.sistema.marcaje.modelo;

import jakarta.persistence.*;

@Entity
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usarioRolId;

    //muchos a uno
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne
    private Rol rol;

    public UsuarioRol(){

    }
    public UsuarioRol(Rol rol, Usuario usuario, Long usarioRolId) {
        this.rol = rol;
        this.usuario = usuario;
        this.usarioRolId = usarioRolId;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getUsarioRolId() {
        return usarioRolId;
    }

    public void setUsarioRolId(Long usarioRolId) {
        this.usarioRolId = usarioRolId;
    }
}
