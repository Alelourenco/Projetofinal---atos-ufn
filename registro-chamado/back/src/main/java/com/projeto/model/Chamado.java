package com.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Chamado {
    @Id
    @Column(name="chamado", nullable = false, unique = true)
    private Long chamado;

    @Column(name="titulo", nullable = false)
    private String titulo;

    @Column(name="status", nullable = false)
    private String status;

    @Column(name="comentario", nullable = false)
    private String comentario;


    //--------------- GATTERS AND SETTERS ----------------//

    public Long getChamado() {
        return chamado;
    }

    public void setChamado(Long chamado) {
        this.chamado = chamado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    
}
