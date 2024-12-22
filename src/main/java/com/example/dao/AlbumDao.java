package com.example.dao;

import com.example.models.Album;
import com.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AlbumDao {
    public List<Album> getAllAlbums() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Album", Album.class).getResultList();
        }
    }

    public Album getAlbumById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Album.class, id);
        }
    }

    public void saveAlbum(Album album) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(album);
            transaction.commit();
        }
    }

    public void deleteAlbum(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Album album = session.get(Album.class, id);
            if (album != null) {
                session.delete(album);
            }
            transaction.commit();
        }
    }

    public void updateAlbum(Album album) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Album existingAlbum = session.get(Album.class, album.getAlbumId());
            if (existingAlbum != null) {
                existingAlbum.setTitle(album.getTitle());
                existingAlbum.setGenre(album.getGenre());
                existingAlbum.setArtist(album.getArtist());
                session.update(existingAlbum);
                transaction.commit();
            } else {
                System.out.println("Album with ID " + album.getAlbumId() + " not found.");
            }
        }
    }

    public List<Album> findAlbumsByTitle(String query) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Album WHERE title ILIKE :query", Album.class)
                    .setParameter("query", query + "%")
                    .setMaxResults(10)
                    .getResultList();
        }
    }
}
