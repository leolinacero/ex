package org.example.model;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pelicula")
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @OneToMany (mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Opinion> opinion = new ArrayList<>();

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", opinion=" + opinion +
                '}';
    }
    public void addOpinion(Opinion o){
        o.setPelicula(this);
        this.opinion.add(o);
    }

}