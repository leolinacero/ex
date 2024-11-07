package org.example;

import org.example.model.Opinion;
import org.example.model.Pelicula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DataServi {
    private SessionFactory sessionFactory;
    public DataServi(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory;}



    public void savePelicula(Pelicula pelicula) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(pelicula);
        transaction.commit();

    }

    public void saveOpinion(Opinion opinion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(opinion);
        transaction.commit();
    }
    public void obteneropinionUsuario (String email){
        try(Session session = sessionFactory.openSession()){
            Query<Opinion> query = session.createQuery("from Opinion o where o.usuario = :email", Opinion.class);
            query.setParameter("email", email);
            List<Opinion> opiniones = query.list();
            opiniones.forEach(System.out::println);
        }
    }


    public void añadirOpinionAunaPeliculaYaExistente(Opinion opinion, String titulo){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Pelicula pelicula = session.createQuery("from Pelicula p where p.titulo = :titulo", Pelicula.class)
                    .setParameter("titulo", titulo)
                    .uniqueResult();
            opinion.setPelicula(pelicula);
            session.save(opinion);
            transaction.commit();
        }
    }







    public List<Pelicula>listadodePeliculasconbajapuntuacion(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from Pelicula p where p.puntuacion < 5", Pelicula.class)
                    .list();
        }
    }


}
