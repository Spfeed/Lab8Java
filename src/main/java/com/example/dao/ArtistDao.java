package com.example.dao;

import com.example.models.Artist;
import com.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ArtistDao {
    public List<Artist> getAllArtists() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Artist", Artist.class).getResultList();
        }
    }

    public Artist getArtistById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Artist.class, id);
        }
    }

    public void saveArtist(Artist artist) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(artist);
            transaction.commit();
        }
    }

    public void updateArtist(Artist artist) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Artist existingArtist = session.get(Artist.class, artist.getArtistId());
            if (existingArtist != null) {
                existingArtist.setName(artist.getName());
                session.update(existingArtist);
                transaction.commit();
            } else {
                System.out.println("Artist with ID " + artist.getArtistId() + " not found.");
            }
        }
    }

    public void deleteArtist(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Artist artist = session.get(Artist.class, id);
            if (artist != null) {
                session.delete(artist);
            }
            transaction.commit();
        }
    }

    public List<Artist> findArtistsByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Artist WHERE name ILIKE :query", Artist.class)
                    .setParameter("query", name + "%")
                    .setMaxResults(10)
                    .getResultList();
        }
    }
}
