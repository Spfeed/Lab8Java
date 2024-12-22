package com.example;

import com.example.models.Album;
import com.example.models.Artist;
import com.example.models.Track;
import com.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            Transaction transaction = session.beginTransaction();
            Artist artist = new Artist("Queen");
            session.persist(artist);

            Album album = new Album("A Night at the Opera", "Rock", artist);
            session.persist(album);

            Track track = new Track("Bohemian Rhapsody", 345, album);
            session.persist(track);

            transaction.commit();

            List<Artist> artists = session.createQuery("from Artist", Artist.class).list();
            System.out.println("Artists: ");
            for (Artist a : artists) {
                System.out.println(a.getName());
            }

            transaction = session.beginTransaction();
            artist.setName("Freddie Mercury");
            session.update(artist);
            transaction.commit();

            transaction = session.beginTransaction();
            session.delete(track);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.shutdown();
        }
    }
}
