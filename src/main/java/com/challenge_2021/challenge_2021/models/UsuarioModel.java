package com.challenge_2021.challenge_2021.models;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}

