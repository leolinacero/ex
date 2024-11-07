package org.example;

import org.example.model.Opinion;
import org.example.model.Pelicula;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        DataServi dataServi = new DataServi(sessionFactory);

        // Crear y guardar una nueva Pelicula
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("New Movie");
        dataServi.savePelicula(pelicula);

        // Crear y guardar una nueva Opinion
        Opinion opinion = new Opinion();
        opinion.setUsuario("User123");
        opinion.setDescripcion("Great movie!");
        opinion.setPuntuacion(5);
        dataServi.saveOpinion(opinion);

        // Añadir Opinion a una Pelicula existente
        dataServi.añadirOpinionAunaPeliculaYaExistente(opinion, "New Movie");

        // Obtener y mostrar opiniones por usuario
        dataServi.obteneropinionUsuario("User123");

        // Listar y mostrar peliculas con baja puntuacion
        List<Pelicula> lowRatedMovies = dataServi.listadodePeliculasconbajapuntuacion();
        lowRatedMovies.forEach(System.out::println);
    }
    }


